/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
* Author : J-G
* Date   : 25-08-2017
*/

package programs;

import biologic.FastaFile;
import biologic.FastqFile;
import biologic.SamFile;
import biologic.GenomeFile;
import configuration.Docker;
import biologic.Results;
import configuration.Cluster;
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
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;
import org.apache.commons.lang.StringUtils;


/**
 *
 * @author J-G
 * @date 25-08-2017
 *
 */
public class bwa_mem extends RunProgram {
    // CREATE VARIABLES HERE
    private String doImage        = "jego/bwa";
    private String doPgrmPath     = "bwa mem";
    private String doName         = "bwa_mem_armadilloWF_0";
    private String doInputs       = "/data/inputs/";
    private String doOutputs      = "/data/outputs/";
    private String allDoInputs    = "";
    private HashMap<String,String> sharedFolders = new HashMap<String,String>();
    //INPUTS
    private String input1       = "";
    private String inputId1     = "";
    private String inputPath1   = "";
    private String input2       = "";
    private String inputId2     = "";
    private String inputPath2   = "";
    private String input3       = "";
    private String inputId3     = "";
    private String inputPath3   = "";
    private String input4       = "";
    private String inputId4     = "";
    private String inputPath4   = "";
    //OUTPUTS
    private String output1       = "";
    private String outputInDo1   = "";
    //PATHS
    private static final String outputsPath = "."+File.separator+"results"+File.separator+"bwa"+File.separator+"mem"+File.separator+"";
    private static final String[] Advanced_Options_1 = {
        "AO_AO1_t_box"//,
        //"AO_AO1_t_JSpinnerValue"
    };


    public bwa_mem(workflow_properties properties){
        this.properties=properties;
        execute();
    }

    @Override
    public boolean init_checkRequirements(){

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
            setStatus(status_BadRequirements,"Not able to access or create OUTPUTS directory files");
            return false;
        }
        
        // TEST INPUT VARIABLES HERE
        // ports are 3-PortInputUp, 2-PortInputDOWN, 4-PortInputDOWN2
        Vector<Integer>GenomeFile1    = properties.getInputID("GenomeFile",PortInputDOWN2);
        inputPath1 = GenomeFile.getVectorFilePath(GenomeFile1);
        inputId1   = GenomeFile.getVectorFileId(GenomeFile1);
        input1     = Util.getFileNameAndExt(inputPath1);

        Vector<Integer>FastaFile2    = properties.getInputID("FastaFile",PortInputDOWN2);
        inputPath2 = FastaFile.getVectorFilePath(FastaFile2);
        inputId2   = FastaFile.getVectorFileId(FastaFile2);
        input2     = Util.getFileNameAndExt(inputPath2);

        Vector<Integer>FastqFile3    = properties.getInputID("FastqFile",PortInputDOWN);
        inputPath3 = FastqFile.getVectorFilePath(FastqFile3);
        inputId3   = FastqFile.getVectorFileId(FastqFile3);
        input3     = Util.getFileNameAndExt(inputPath3);

        Vector<Integer>FastqFile4    = properties.getInputID("FastqFile",PortInputUP);
        inputPath4 = FastqFile.getVectorFilePath(FastqFile4);
        inputId4   = FastqFile.getVectorFileId(FastqFile4);
        input4     = Util.getFileNameAndExt(inputPath4);
        
        //INSERT YOUR INPUT TEST HERE
        if ((GenomeFile1.isEmpty()||input1.equals("Unknown")||input1.equals(""))&&
            (FastaFile2.isEmpty()||input2.equals("Unknown")||input2.equals(""))){
            setStatus(status_BadRequirements,"No FastaFile found.");
            return false;
        }
        /*
        // Please, check if it's "else if" or it's a real "if"
        if (FastqFile3.isEmpty()||input3.equals("Unknown")||input3.equals("")){
            setStatus(status_BadRequirements,"No FastqFile pair-end found.");
            return false;
        }
        */
        // Please, check if it's "else if" or it's a real "if"
        if (FastqFile4.isEmpty()||input4.equals("Unknown")||input4.equals("")){
            setStatus(status_BadRequirements,"No FastqFile found.");
            return false;
        }
        
        // DOCKER VARIABLES
        // Prepare ouputs
        output1 = specificPath+File.separator+"OutputOf_"+input4+".sam";
        outputInDo1 = doOutputs+"OutputOf_"+input4+".sam";
        output1 = Util.onlyOneOutputOf(output1);
        outputInDo1 = Util.onlyOneOutputOf(outputInDo1);
        
        // Prepare shared folders
        String[] allInputsPath = {inputPath1,inputPath2,inputPath3,inputPath4};
        String[] simpleId = {inputId1,inputId2,inputId3,inputId4};
        sharedFolders = Docker.createSharedFolders(allInputsPath,simpleId,doInputs);
        sharedFolders.put(specificPath,doOutputs);

        // Prepare inputs
        HashMap<String,String> pathAndArg = new HashMap<String,String>();
        pathAndArg.put(inputPath1,"");
        pathAndArg.put(inputPath2,"");
        pathAndArg.put(inputPath3,"");
        pathAndArg.put(inputPath4,"");
        allDoInputs = Docker.createAllDockerInputs(pathAndArg,allInputsPath,simpleId,doInputs);

        // Prepare cluster relations
        Cluster.createLinkDockerClusterInputs(properties,allInputsPath,simpleId,doInputs);
        Cluster.createLinkDockerClusterOutput(properties,output1,outputInDo1);
        
        // DOCKER INIT
        if (Docker.isDockerHere(properties)){
            doName = Docker.getContainerName(properties,doName);
            if (!dockerInitContainer(properties,sharedFolders, doName, doImage))
                return false;
        } else {
            setStatus(status_BadRequirements,"Docker is not found. Please install docker");
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
        if (properties.isSet("Advanced_Options"))
            options += Util.findOptionsNew(Advanced_Options_1,properties);
        
        // Docker command line
        String dockerCli = doPgrmPath+" "+options + allDoInputs +  " > " +  outputInDo1;
        Docker.prepareDockerBashFile(properties,doName,dockerCli);
        Cluster.createLinkDockerClusterCli(properties, dockerCli);
        setStatus(status_running,"DockerRunningCommandLine: \n$ "+dockerCli);
        String dockerBashCli = "exec -i "+doName+" sh -c './dockerBash.sh'";
        
        // Command line creation
        String[] com = new String[30];
        for (int i=0; i<com.length;i++) com[i]="";
        
        com[0]= "cmd.exe"; // Windows will de remove if another os is used
        com[1]= "/C";      // Windows will de remove if another os is used
        com[2]= properties.getExecutable();
        com[3]= dockerBashCli;
        return com;
    }

    /*
    * Output Parsing
    */

    @Override
    public void post_parseOutput(){
        Docker.cleanContainer(properties,doName);
        SamFile.saveFile(properties,output1,"bwa_mem","SamFile");
        Results.saveResultsPgrmOutput(properties,this.getPgrmOutput(),"bwa_mem");
    }
}
