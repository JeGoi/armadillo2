/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
* Author : J-G
* Date   : 25-08-2017
*/

package programs;

import biologic.CramFile;
import biologic.SamFile;
import biologic.BamFile;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import static program.RunProgram.status_running;


/**
 *
 * @author J-G
 * @date 25-08-2017
 *
 */
public class samtools_sort extends RunProgram {
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
    private static final String outputsPath = "."+File.separator+"results"+File.separator+"samtools"+File.separator+"sort"+File.separator+"";
    private static final String[] Advanced_Options_1 = {
        "AO_AO1_T_box",
        //"AO_AO1_T_JTextFieldValue",
        "AO_AO1_H_box",
        "AO_AO1_O_box"//,
        //"AO_AO1_O_JComboBoxValue"
    };


    public samtools_sort(workflow_properties properties){
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
            setStatus(status_BadRequirements,Util.BROutputsDir());
            return false;
        }

        
        // TEST INPUT VARIABLES HERE
        // ports are 3-PortInputUp, 2-PortInputDOWN, 4-PortInputDOWN2

        Vector<Integer>SamFile1    = properties.getInputID("SamFile",PortInputDOWN);
        inputPath1 = SamFile.getVectorFilePath(SamFile1);
        inputId1   = BamFile.getVectorFileId(SamFile1);
        input1     = Util.getFileNameAndExt(inputPath1);

        Vector<Integer>BamFile2    = properties.getInputID("BamFile",PortInputDOWN);
        inputPath2 = BamFile.getVectorFilePath(BamFile2);
        inputId2   = BamFile.getVectorFileId(BamFile2);
        input2     = Util.getFileNameAndExt(inputPath2);

        Vector<Integer>CramFile3    = properties.getInputID("CramFile",PortInputDOWN);
        inputPath3 = CramFile.getVectorFilePath(CramFile3);
        inputId3   = CramFile.getVectorFileId(CramFile3);
        input3     = Util.getFileNameAndExt(inputPath3);
        
        //INSERT YOUR INPUT TEST HERE
        if ((SamFile1.isEmpty()||input1.equals("Unknown")||input1.equals("")) && 
            (BamFile2.isEmpty()||input2.equals("Unknown")||input2.equals("")) &&
            (CramFile3.isEmpty()||input3.equals("Unknown")||input3.equals(""))){
            setStatus(status_BadRequirements,Util.BRTypeFile("Input File"));
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
        String outputFinal="OutputOf_"+input1;
        if (input2!="")
            outputFinal="OutputOf_"+input2;
        if (input3!="")
            outputFinal="OutputOf_"+input3;
        if (properties.isSet("AO_AO1_O_box")){
            if (properties.get("AO_AO1_O_box")=="bam")
                outputFinal = outputFinal+".bam";
            if (properties.get("AO_AO1_O_box")=="sam")
                outputFinal = outputFinal+".sam";
            if (properties.get("AO_AO1_O_box")=="cram")
                outputFinal = outputFinal+".cram";
        }
        output1 = specificPath+File.separator+outputFinal;
        outputInDo1 = doOutputs+outputFinal;
        output1 = Util.onlyOneOutputOf(output1);
        outputInDo1 = Util.onlyOneOutputOf(outputInDo1);
        
        // Prepare shared folders
        String[] allInputsPath = {inputPath1,inputPath2,inputPath3};
        String[] simpleId = {inputId1,inputId2,inputId3};
        sharedFolders = Docker.createSharedFolders(allInputsPath,simpleId,doInputs);
        sharedFolders = Docker.addInSharedFolder(sharedFolders,specificPath,doOutputs);

        // Prepare inputs
        HashMap<String,String> allInputsPathArg = new HashMap<String,String>();
        allInputsPathArg.put(inputPath1,"");
        allInputsPathArg.put(inputPath2,"");
        allInputsPathArg.put(inputPath3,"");
        allDoInputs = Docker.createAllDockerInputs(allInputsPathArg,allInputsPath,simpleId,doInputs);

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
        if (properties.isSet("Advanced_Options"))
            options += Util.findOptionsNew(Advanced_Options_1,properties);
        
        // Pre command line
        String preCli = options+" "+allDoInputs+" -o "+outputInDo1;
        
        // Docker command line
        String dockerCli = properties.get("ExecutableDocker")+" "+preCli;
        long duration = Docker.prepareDockerBashFile(properties,dockerCli);
        setStatus(status_running, "\t<TIME> Time to prepare docker bash file is >"+duration+" s");
        setStatus(status_running,"Docker CommandLine: \n$ "+dockerCli);
        
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
    public void post_parseOutput(){
        long duration = Docker.removeContainer(properties);
        setStatus(status_running, Util.RUNDockerDuration("stop and remove",duration));
        if (output1.endsWith("bam"))
            BamFile.saveFile(properties,output1,"samtools_sort","BamFile");
        if (output1.endsWith("Cram"))
            CramFile.saveFile(properties,output1,"samtools_sort","CramFile");
        if (output1.endsWith("sam"))
            SamFile.saveFile(properties,output1,"samtools_sort","SamFile");
        Results.saveResultsPgrmOutput(properties,this.getPgrmOutput(),"samtools_sort");
    }
}
