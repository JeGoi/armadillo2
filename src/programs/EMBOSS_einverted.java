/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
* Author : JG
* Date   : Feb 2016
*/

package programs;

import biologic.EinvertedFile;
import biologic.Results;
import biologic.FastaFile;
import configuration.Cluster;
import configuration.Docker;
import configuration.Util;
import java.io.File;
import java.util.Vector;
import java.util.Hashtable;
import java.util.Map;
import java.util.Iterator;
import program.RunProgram;
import static program.RunProgram.PortInputUP;
import static program.RunProgram.df;
import static program.RunProgram.status_error;
import workflows.workflow_properties;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import static program.RunProgram.status_BadRequirements;
import static program.RunProgram.status_running;


/**
 *
 * @author JG
 * @date Feb 2016
 *
 */
public class EMBOSS_einverted extends RunProgram {
    // CREATE VARIABLES HERE
    private String allDoInputs = "";
    private HashMap<String,String> sharedFolders = new HashMap<String,String>();
    //INPUTS
    private String input1      = "";
    private String inputId1    = "";
    private String inputPath1  = "";
    //OUTPUTS
    private String output1     ="";
    private String outputInDo1 ="";
    private String output2     ="";
    private String outputInDo2 ="";
    //PATHS
    private static final String outputsPath = "."+File.separator+"results"+File.separator+"EMBOSS"+File.separator+"einverted"+File.separator+"";

    private static final String[] Sq_panel = {
        "Sq_gap_Box",
        //"Sq_gap_Box_IntValue",
        "Sq_threshold_Box",
        //"Sq_threshold_Box_IntValue",
        "Sq_match_Box",
        //"Sq_match_Box_IntValue",
        "Sq_mismatch_Box",
        //"Sq_mismatch_Box_IntValue",
        "Sq_maxrepeat_Box"//,
        //"Sq_maxrepeat_Box_IntValue"
    };


    public EMBOSS_einverted(workflow_properties properties) {
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
        input1     = Util.getFileNameAndExt(inputPath1);

        //INSERT YOUR TEST HERE
        if (FastaFile_1.isEmpty()||input1.equals("Unknown")||input1.equals("")) {
            setStatus(status_BadRequirements,Util.BRTypeFile("FastaFile"));
            return false;
        }

        // Test docker Var presence
        if (Docker.areDockerVariablesNotInProperties(properties)){
            setStatus(status_BadRequirements,Util.BRDockerVariables());
            return false;
        }
        
        // Extract Docker Variables
        String doOutputs = properties.get("DockerOutputs");
        String doInputs = properties.get("DockerInputs");
        
        // Prepare ouputs
        output1 = specificPath+File.separator+"OutputOf_"+input1+".einverted";
        output2 = specificPath+File.separator+"OutputOf_"+input1+".fasta";
        outputInDo1 = doOutputs+"OutputOf_"+input1+".einverted";
        outputInDo2 = doOutputs+"OutputOf_"+input1+".fasta";
        output1 = Util.onlyOneOutputOf(output1);
        output2 = Util.onlyOneOutputOf(output2);
        outputInDo1 = Util.onlyOneOutputOf(outputInDo1);
        outputInDo2 = Util.onlyOneOutputOf(outputInDo2);
        
        // Prepare shared folders
        String[] allInputsPath = {inputPath1};
        String[] simpleId = {inputId1};
        sharedFolders = Docker.createSharedFolders(allInputsPath,simpleId,doInputs);
        sharedFolders = Docker.addInSharedFolder(sharedFolders,specificPath,doOutputs);

        // Prepare inputs
        HashMap<String,String> pathAndArg = new HashMap<String,String>();
        pathAndArg.put(inputPath1,"-sequence ");
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
            if (!(properties.isSet("default_RButton"))
                && !(properties.isSet("Advanced_Options_RButton"))
            ) {
                Util.getDefaultPgrmValues(properties,false);
            }
        }

    @Override
    public String[] init_createCommandLine() {

        // Program and Options
        String options = "";
        if (!properties.isSet("default_RButton"))
            options += Util.findOptionsNew(Sq_panel,properties);
        
        // Pre command line
        String preCli = options+" "+allDoInputs+" -outfile "+outputInDo1+" -outseq "+outputInDo2;
        
        // Docker command line
        String dockerCli = properties.get("ExecutableDocker")+" "+preCli;
        long duration = Docker.prepareDockerBashFile(properties,dockerCli);
        setStatus(status_running, Util.RUNDockerDuration("prepare",duration));
        setStatus(status_running, Util.RUNCommandLine("Docker",dockerCli));
        
        // Cluster
        String clusterCli = properties.get("ExecutableCluster")+" "+preCli;
        Cluster.createClusterRunningCLiFromDocker(properties, clusterCli);
        

        // Command line
        String[] com = {""};
        return com;
    }

    /*
    * Output Parsing
    */

    @Override
    public void post_parseOutput() {
        long duration = Docker.removeContainer(properties);
        setStatus(status_running, Util.RUNDockerDuration("stop and remove",duration));
        EinvertedFile.saveFile(properties,output1,"EMBOSS_einverted","EinvertedFile");
        FastaFile.saveFile(properties,output2,"EMBOSS_einverted","FastaFile");
        Results.saveResultsPgrmOutput(properties,this.getPgrmOutput(),"EMBOSS_einverted");
    }
}
