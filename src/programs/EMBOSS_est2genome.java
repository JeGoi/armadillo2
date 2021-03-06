/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
* Author : JG
* Date   : Feb 2016
*/

package programs;

import biologic.FastaFile;
import biologic.GenomeFile;
import biologic.EmblFile;
import biologic.Results;
import biologic.Est2genomeFile;
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
public class EMBOSS_est2genome extends RunProgram {
    // CREATE VARIABLES HERE
    private String allDoInputs = "";
    private HashMap<String,String> sharedFolders = new HashMap<String,String>();
    //INPUTS
    private String input1      = "";
    private String inputId1    = "";
    private String inputPath1  = "";
    private String input2      = "";
    private String inputId2    = "";
    private String inputPath2  = "";
    private String input3      = "";
    private String inputId3    = "";
    private String inputPath3  = "";
    //OUTPUTS
    private String output1     = "";
    private String outputInDo1 = "";
    //PATHS
    private static final String outputsPath = "."+File.separator+"results"+File.separator+"EMBOSS"+File.separator+"est2genome"+File.separator+"";
    private static final String[] Sq_panel = {
        "Sq_match_Box",
        //"Sq_match_Box_IntValue",
        "Sq_mismatch_Box",
        //"Sq_mismatch_Box_IntValue",
        "Sq_gappenalty_Box",
        //"Sq_gappenalty_Box_IntValue",
        "Sq_intronpenalty_Box",
        //"Sq_intronpenalty_Box_IntValue",
        "Sq_splicepenalty_Box",
        //"Sq_splicepenalty_Box_IntValue",
        "Sq_minscore_Box",
        //"Sq_minscore_Box_IntValue",
        "Sq_reverse_Box",
        //"Sq_reverse_Box_BooValue",
        "Sq_usesplice_Box",
        //"Sq_usesplice_Box_BooValue",
        "Sq_mode_Box",
        //"Sq_mode_Box_List",
        "Sq_best_Box",
        //"Sq_best_Box_BooValue",
        "Sq_space_Box",
        //"Sq_space_Box_FloValue",
        "Sq_shuffle_Box",
        //"Sq_shuffle_Box_IntValue",
        "Sq_seed_Box",
        //"Sq_seed_Box_IntValue",
        "Sq_align_Box",
        //"Sq_align_Box_BooValue",
        "Sq_width_Box"//,
        //"Sq_width_Box_IntValue"
    };


    public EMBOSS_est2genome(workflow_properties properties) {
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

        Vector<Integer>EmblFile_2    = properties.getInputID("EmblFile",PortInputDOWN);
        inputPath2 = EmblFile.getVectorFilePath(EmblFile_2);
        inputId2   = EmblFile.getVectorFileId(EmblFile_2);
        input2     = Util.getFileNameAndExt(inputPath2);

        Vector<Integer>GenomeFile_3    = properties.getInputID("GenomeFile",PortInputUP);
        inputPath3 = GenomeFile.getVectorFilePath(GenomeFile_3);
        inputId3   = GenomeFile.getVectorFileId(GenomeFile_3);
        input3     = Util.getFileNameAndExt(inputPath3);

        //INSERT YOUR TEST HERE
        if (FastaFile_1.isEmpty()||input1.equals("Unknown")||input1.equals("")) {
            setStatus(status_BadRequirements,Util.BRTypeFile("FastaFile"));
            return false;
        }
        else if (EmblFile_2.isEmpty()||input2.equals("Unknown")||input2.equals("")) {
            setStatus(status_BadRequirements,Util.BRTypeFile("EmblFile"));
            return false;
        }
        else if (GenomeFile_3.isEmpty()||input3.equals("Unknown")||input3.equals("")) {
            setStatus(status_BadRequirements,Util.BRTypeFile("GenomeFile"));
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
        output1 = specificPath+File.separator+"OutputOf_"+input1+".est2genome";
        outputInDo1 = doOutputs+"OutputOf_"+input1+".est2genome";
        output1 = Util.onlyOneOutputOf(output1);
        outputInDo1 = Util.onlyOneOutputOf(outputInDo1);
        
        // Prepare shared folders
        String[] allInputsPath = {inputPath1,inputPath2,inputPath3};
        String[] simpleId = {inputId1,inputId2,inputId3};
        sharedFolders = Docker.createSharedFolders(allInputsPath,simpleId,doInputs);
        sharedFolders = Docker.addInSharedFolder(sharedFolders,specificPath,doOutputs);

        // Prepare inputs
        HashMap<String,String> pathAndArg = new HashMap<String,String>();
        pathAndArg.put(inputPath1,"-estsequence");
        pathAndArg.put(inputPath2,"-estsequence");
        pathAndArg.put(inputPath3,"-genomesequence");
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
    
        // def functions for init_createCommandLine
        // In case program is started without edition and params need to be setted
        private void pgrmStartWithoutEdition (workflow_properties properties){
            if (!(properties.isSet("Default_Options"))
                && !(properties.isSet("Advanced_Options"))
            ){
                Util.getDefaultPgrmValues(properties,false);
            }
        }

    @Override
    public String[] init_createCommandLine() {

        // Program and Options
        String options = "";
        if (!properties.isSet("default_RButton")) {
            options += Util.findOptionsNew(Sq_panel,properties);
        }
        
        // Pre command line
        String preCli = options+" "+allDoInputs+" -outfile "+outputInDo1;
        
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
        Est2genomeFile.saveFile(properties,output1,"EMBOSS_est2genome","Est2genomeFile");
        Results.saveResultsPgrmOutput(properties,this.getPgrmOutput(),"EMBOSS_est2genome");
    }
}
