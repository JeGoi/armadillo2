/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
* Author : J-G
* Date   : 25-08-2017
*/

package programs;

import biologic.BamCsiFile;
import biologic.CramFile;
import biologic.BamBaiFile;
import biologic.BamFile;
import biologic.CramCraiFile;
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


/**
 *
 * @author J-G
 * @date 25-08-2017
 *
 */
public class samtools_index extends RunProgram {
    // CREATE VARIABLES HERE
    private String allDoInputs  = "";
    private HashMap<String,String> sharedFolders = new HashMap<String,String>();
    //INPUTS
    private String input1      = "";
    private String inputId1    = "";
    private String inputPath1  = "";
    private String input2      = "";
    private String inputId2    = "";
    private String inputPath2  = "";
    //OUTPUTS
    private String output1     = "";
    private String outputInDo1 = "";
    //PATHS
    private static final String outputsPath = "."+File.separator+"results"+File.separator+"samtools"+File.separator+"index"+File.separator+"";
    private static final String[] Advanced_Options = {
        "AO_AO1_b_box",
        "AO_AO1_c_box",
        "AO_AO1_m_box"//,
        //"AO_AO1_m_JSpinnerValue"
    };


    public samtools_index(workflow_properties properties){
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
        Vector<Integer>BamFile1    = properties.getInputID("BamFile",PortInputDOWN);
        inputPath1 = BamFile.getVectorFilePath(BamFile1);
        inputId1   = BamFile.getVectorFileId(BamFile1);
        input1     = Util.getFileNameAndExt(inputPath1);

        Vector<Integer>CramFile2    = properties.getInputID("CramFile",PortInputDOWN);
        inputPath2 = CramFile.getVectorFilePath(CramFile2);
        inputId1   = CramFile.getVectorFileId(CramFile2);
        input2     = Util.getFileNameAndExt(inputPath2);
        
        //INSERT YOUR INPUT TEST HERE
        if ((BamFile1.isEmpty()||input1.equals("Unknown")||input1.equals("")) &&
            (CramFile2.isEmpty()||input2.equals("Unknown")||input2.equals(""))){
            setStatus(status_BadRequirements,Util.BRTypeFile("CramFile or BamFile"));
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
        String outputFinal="OutputOf_"+input1+".bam";
        if (properties.isSet("AO_AO1_c_box")||properties.isSet("AO_AO1_m_box"))
            outputFinal+=".csi";
        else
            outputFinal+=".bai";
        if (input2!="")
            outputFinal="OutputOf_"+input2+".cram.crai";
        output1 = specificPath+File.separator+outputFinal;
        outputInDo1 = doOutputs+outputFinal;
        output1 = Util.onlyOneOutputOf(output1);
        outputInDo1 = Util.onlyOneOutputOf(outputInDo1);

        // Prepare shared folders
        String[] allInputsPath = {inputPath1,inputPath2};
        String[] simpleId = {inputId1,inputId2};
        sharedFolders = Docker.createSharedFolders(allInputsPath,simpleId,doInputs);
        sharedFolders.put(Util.getCanonicalPath(specificPath),doOutputs);
        
        // Prepare inputs
        HashMap<String,String> allInputsPathArg = new HashMap<String,String>();
        allInputsPathArg.put(inputPath1,"");
        allInputsPathArg.put(inputPath2,"");
        allDoInputs = Docker.createAllDockerInputs(allInputsPathArg,allInputsPath,simpleId,doInputs);
        
        // Prepare cluster relations
        properties.put("ClusterLocalOutput_1",output1+"<<>>"+outputInDo1);
        Cluster.createLinkDockerClusterInputs(properties, allInputsPath,simpleId, doInputs);

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
        if (properties.isSet("Advanced_Options")&&input2=="")
            options += Util.findOptionsNew(Advanced_Options,properties);
        
        // Pre command line
        String preCli = options+" "+allDoInputs+" > "+outputInDo1;
        
        // Docker command line
        String dockerCli = properties.get("ExecutableDocker")+" "+preCli;
        long duration = Docker.prepareDockerBashFile(properties,dockerCli);
        setStatus(status_running, "\t<TIME> Time to prepare docker bash file is >"+duration+" s");
        setStatus(status_running,"Docker CommandLine: \n$ "+dockerCli);
        
        // Cluster
        String clusterCli = properties.get("ExecutableCluster")+" "+preCli;
        Cluster.createLinkDockerClusterCli(properties, clusterCli);
        setStatus(status_running,"Cluster CommandLine: \n$ "+clusterCli);
        
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
        if (output1.endsWith("bai"))
            BamBaiFile.saveFile(properties,output1,"samtools_index","BamBaiFile");
        if (output1.endsWith("crai"))
            CramCraiFile.saveFile(properties,output1,"samtools_index","CramCraiFile");
        if (output1.endsWith("csi"))
            BamCsiFile.saveFile(properties,output1,"samtools_index","BamCsiFile");
        Results.saveResultsPgrmOutput(properties,this.getPgrmOutput(),"samtools_index");
    }
}
