/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
* Author : J-G
* Date   : 20-08-2017
*/

package programs;

import biologic.FastaFile;
import biologic.FaidxFile;
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
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author J-G
 * @date 20-08-2017
 *
 */
public class samtools_faidx extends RunProgram {
    // CREATE VARIABLES HERE
    private String allDoInputs  = "";
    private HashMap<String,String> sharedFolders = new HashMap<String,String>();
    //INPUTS
    private String input1       = "";
    private String inputId1     = "";
    private String inputPath1   = "";
    private String input2       = "";
    private String inputId2     = "";
    private String inputPath2   = "";
    //OUTPUTS
    private String output1       = "";
    private String outputInDo1   = "";
    private String outputPathDo1 = "";
    //PATHS
    private static final String outputsPath = "."+File.separator+"results"+File.separator+"SAMTOOLS"+File.separator+"faidx"+File.separator+"";

    public samtools_faidx(workflow_properties properties){
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
        
        Vector<Integer>FastaFile1    = properties.getInputID("FastaFile",PortInputDOWN);
        inputPath1 = FastaFile.getVectorFilePath(FastaFile1);
        inputId1   = FastaFile.getVectorFileId(FastaFile1);
        input1     = Util.getFileNameAndExt(inputPath1);
        //INSERT YOUR INPUT TEST HERE
        if (FastaFile1.isEmpty()||input1.equals("Unknown")||input1.equals("")){
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
        String outputFinal="OutputOf_"+input1+".fa.fai";
        output1 = specificPath+File.separator+outputFinal;
        outputInDo1 = doOutputs+outputFinal;
        output1 = Util.onlyOneOutputOf(output1);
        outputInDo1 = Util.onlyOneOutputOf(outputInDo1);

        // Prepare shared folders
        String[] allInputsPath = {inputPath1,inputPath2};
        String[] simpleId = {inputId1,inputId2};
        sharedFolders = Docker.createSharedFolders(allInputsPath,simpleId,doInputs);
        sharedFolders = Docker.addInSharedFolder(sharedFolders,specificPath,doOutputs);

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
            ){
                Util.getDefaultPgrmValues(properties,false);
            }
        }
        

    @Override
    public String[] init_createCommandLine() {
        // Program and Options
        String options = "";
        
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
        FaidxFile.saveFile(properties,output1,"samtools_faidx","FaidxFile");
        Results.saveResultsPgrmOutput(properties,this.getPgrmOutput(),"samtools_faidx");
    }
}
