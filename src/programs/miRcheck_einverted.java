/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package programs;

import biologic.FastaFile;
import biologic.TextFile;
import biologic.Results;
import biologic.Text;
import configuration.Cluster;
import configuration.Docker;
import configuration.Util;
import java.util.Vector;
import program.RunProgram;
import static program.RunProgram.PortInputUP;
import static program.RunProgram.df;
import static program.RunProgram.status_error;
import workflows.workflow_properties;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import static program.RunProgram.PortInputDOWN;
import static program.RunProgram.status_BadRequirements;
import static program.RunProgram.status_running;
/**
 *
 * @author Jérémy Goimard
 * @date Aout 2015
 *
 */
public class miRcheck_einverted extends RunProgram{
    // CREATE VARIABLES HERE
    private String allDoInputs    = "";
    private HashMap<String,String> sharedFolders = new HashMap<String,String>();
    
    //INPUTS
    private String input1       = "";
    private String inputId1     = "";
    private String inputPath1   = "";
    //OUTPUTS
    private String output1       = "";
    private String outputInDo1   = "";

    private static final String outputsPath = "."+File.separator+"results"+File.separator+"miRcheck"+File.separator+"run_einverted"+File.separator+"";

    private static final String[] Advanced_Options = {
        "C_gap_box",
        "C_thresh_box",
        "C_m_box",
        "C_mm_box",
        "C_dist_box",
        "C_win_box",
        "C_step_box",
        "C_minPct_box",
        "C_minArm_box"
    };

    public miRcheck_einverted(workflow_properties properties) {
        this.properties=properties;
        execute();
    }
    
    @Override
    public boolean init_checkRequirements() {
        
        // In case program is started without edition
        pgrmStartWithoutEdition(properties);
        
        // TEST OUTPUT PATH
        String specificId = Util.returnRandomAndDate();
        if (properties.isSet("ObjectID")) {
            String oId = properties.get("ObjectID");
            oId = Util.replaceSpaceByUnderscore(oId);
            specificId = specificId+"_"+oId;
        }
        String specificPath = outputsPath+specificId;
        if (!Util.CreateDir(specificPath) && !Util.DirExists(specificPath)){
            setStatus(status_BadRequirements,Util.BROutputsDir());
            return false;
        }
        
        // TEST INPUT VARIABLES HERE
        // ports are 3-PortInputUp, 2-PortInputDOWN, 4-PortInputDOWN2
        Vector<Integer>FastaFile_1    = properties.getInputID("FastaFile",PortInputDOWN);
        inputPath1 = FastaFile.getVectorFilePath(FastaFile_1);
        inputId1   = FastaFile.getVectorFileId(FastaFile_1);
        input1     = Util.getFileNameAndExt(inputPath1);

        //INSERT YOUR TEST HERE
        if (FastaFile_1.isEmpty()||input1.equals("Unknown")||input1.equals("")) {
            setStatus(status_BadRequirements,Util.BRTypeFile("FastaFile"));
            return false;
        }

        // Test docker Var presence
        if (!Docker.areDockerVariablesInProperties(properties)){
            setStatus(status_BadRequirements,Util.BRDockerVariables());
            return false;
        }
        
        // Extract Docker Variables
        String doOutputs = properties.get("DockerOutputs");
        String doInputs = properties.get("DockerInputs");
        
        // Prepare ouputs
        output1 = specificPath+File.separator+"OutputOf_"+input1+".txt";
        outputInDo1 = doOutputs+"OutputOf_"+input1+".txt";
        output1 = Util.onlyOneOutputOf(output1);
        outputInDo1 = Util.onlyOneOutputOf(outputInDo1);
        
        // Prepare shared folders
        String[] allInputsPath = {inputPath1};
        String[] simpleId = {inputId1};
        sharedFolders = Docker.createSharedFolders(allInputsPath,simpleId,doInputs);
        sharedFolders.put(Util.getCanonicalPath(specificPath),doOutputs);

        // Prepare inputs
        HashMap<String,String> pathAndArg = new HashMap<String,String>();
        pathAndArg.put(inputPath1,"");
        allDoInputs = Docker.createAllDockerInputs(pathAndArg,allInputsPath,simpleId,doInputs);

        // Prepare cluster relations
        Cluster.createLinkDockerClusterInputs(properties,allInputsPath,simpleId,doInputs);
        Cluster.createLinkDockerClusterOutput(properties,output1,outputInDo1);
        
        // DOCKER INIT
        if (Docker.isDockerHere()){
            long duration = Docker.prepareContainer(properties,sharedFolders);
            if (!Docker.isDockerContainerIDPresentIn(properties)){
                setStatus(status_BadRequirements,Util.BRDockerInit());
                return false;
            }
            setStatus(status_running,Util.RUNDockerDuration("launch",duration));
        } else {
            setStatus(status_BadRequirements,Util.BRDockerNotFound());
            return false;
        }
        return true;
    }
    
        // Sub functions for init_createCommandLine
        // In case program is started without edition and params need to be setted
        private void pgrmStartWithoutEdition (workflow_properties properties) {
            if (!(properties.isSet("default_options_jbutton"))
                && !(properties.isSet("advanced_options_jbutton"))
            ) {
                Util.getDefaultPgrmValues(properties,false);
            }
        }

    @Override
    public String[] init_createCommandLine() {
        
        // Program and Options
        String options = "";
        if (properties.get("advanced_options_jbutton").equals("true")){
            options += Util.findOptionsNew(Advanced_Options,properties);
        }
        
        // Pre command line
        String preCli = allDoInputs+" "+outputInDo1+" "+options;
        
        // Docker command line
        String dockerCli = properties.get("ExecutableDocker")+" "+preCli;
        long duration = Docker.prepareDockerBashFile(properties,dockerCli);
        setStatus(status_running, Util.RUNDockerDuration("prepare",duration));
        setStatus(status_running, Util.RUNCommandLine("Docker",dockerCli));
        
        // Cluster
        String clusterCli = properties.get("ExecutableCluster")+" "+preCli;
        Cluster.createLinkDockerClusterCli(properties, clusterCli);
        setStatus(status_running, Util.RUNCommandLine("Cluster",clusterCli));

        // Command line
        String[] com = {""};
        return com;
    }
    

    @Override
    public void post_parseOutput() {
        long duration = Docker.removeContainer(properties);
        setStatus(status_running, Util.RUNDockerDuration("stop and remove",duration));
        TextFile.saveFile(properties,output1,"MIRCHECK_einverted","TextFile");
        Results.saveResultsPgrmOutput(properties,this.getPgrmOutput(),"MIRCHECK_einverted");
    }
}
