/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
* Author : J-G
* Date   : 26-08-2017
*/

package programs;

import biologic.FastaFile;
import biologic.BCFFile;
import biologic.BamBaiFile;
import biologic.VCFFile;
import biologic.BamFile;
import biologic.BedFile;
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
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.ArrayUtils;
import static program.RunProgram.status_running;


/**
 *
 * @author J-G
 * @date 26-08-2017
 *
 */
public class samtools_mpileup extends RunProgram {
    // CREATE VARIABLES HERE
    private String doImage        = "jego/samtools";
    private String doPgrmPath     = "samtools mpileup";
    private String doName         = "samtools_mpileup_armadilloWF_0";
    private String doInputs       = "/data/inputs/";
    private String doOutputs      = "/data/outputs/";
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
    private String[] inputsPath3= {};
    private String[] inputsIDs3 = {};
    private String input4       = "";
    private String inputId4     = "";
    private String inputPath4   = "";
    private String allDoInputs  = "";
    //OUTPUTS
    private String output1       = "";
    private String outputInDo1   = "";
    //PATHS
    private static final String outputsPath = "."+File.separator+"results"+File.separator+"samtools"+File.separator+"mpileup"+File.separator+"";
    private static final String[] Advanced_Options = {
        "AO_AO__illumina1DOT3PLUS_box",
        "AO_OO__BCF_box"
    };


    public samtools_mpileup(workflow_properties properties){
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

        Vector<Integer>BedFile1    = properties.getInputID("BedFile",PortInputDOWN2);
        inputPath1 = BedFile.getVectorFilePath(BedFile1);
        inputId1   = BedFile.getVectorFileId(BedFile1);
        input1     = Util.getFileNameAndExt(inputPath1);

        Vector<Integer>FastaFile2    = properties.getInputID("FastaFile",PortInputUP);
        inputPath2 = FastaFile.getVectorFilePath(FastaFile2);
        inputId2   = FastaFile.getVectorFileId(FastaFile2);
        input2     = Util.getFileNameAndExt(inputPath2);

        Vector<Integer>BamFile3    = properties.getInputID("BamFile",PortInputDOWN);
        inputPath3 = BamFile.getVectorFilePath(BamFile3);
        inputId3   = BamFile.getVectorFileId(BamFile3);
        input3     = Util.getFileNameAndExt(inputPath3);
        // Get the multiple inputs
        inputsPath3 = BamFile.getAllVectorFilePath(BamFile3);
        inputsIDs3  = BamFile.getVectorFileIds(BamFile3);
        
        Vector<Integer>BamBaiFile4    = properties.getInputID("BamBaiFile",PortInputDOWN);
        inputPath4 = BamBaiFile.getVectorFilePath(BamBaiFile4);
        inputId4   = BamBaiFile.getVectorFileId(BamBaiFile4);
        input4     = Util.getFileNameAndExt(inputPath4);
        
        //INSERT YOUR INPUT TEST HERE
        if (BedFile1.isEmpty()||input1.equals("Unknown")||input1.equals("")){
            setStatus(status_warning,"No BedFile found.");
            //return false;
        }
        // Please, check if it's "else if" or it's a real "if"
        if (FastaFile2.isEmpty()||input2.equals("Unknown")||input2.equals("")){
            setStatus(status_warning,"No FastaFile found.");
            //return false;
        }
        // Please, check if it's "else if" or it's a real "if"
        if ((BamFile3.isEmpty()||input3.equals("Unknown")||input3.equals("")) &&
            (BamBaiFile4.isEmpty()||input4.equals("Unknown")||input4.equals(""))){
            setStatus(status_BadRequirements,"No Input Files found.");
            return false;
        }

        // DOCKER VARIABLES
        // Prepare ouputs
        output1 = specificPath+File.separator+"OutputOf_"+input3+".bam";
        outputInDo1 = doOutputs+"OutputOf_"+input3+".bam";
        if (properties.isSet("AO_OO__VCF_box")) {
            output1 = specificPath+File.separator+"OutputOf_"+input3+".vcf";
            outputInDo1 = doOutputs+"OutputOf_"+input3+".vcf";
        } else if (properties.isSet("AO_OO__BCF_box")) {
            output1 = specificPath+File.separator+"OutputOf_"+input3+".bcf";
            outputInDo1 = doOutputs+"OutputOf_"+input3+".bcf";
        }
        output1 = Util.onlyOneOutputOf(output1);
        outputInDo1 = Util.onlyOneOutputOf(outputInDo1);
        
        // Prepare shared folders
        String[] simplePath = {inputPath1,inputPath2,inputPath3,inputPath4};
        String[] allInputsPath = Util.merge2TablesWithoutDup(simplePath, inputsPath3);
        String[] simpleId = {inputId1,inputId2,inputId3,inputId4};
        String[] allInputsId = Util.merge2TablesWithoutDup(simpleId, inputsIDs3);
        sharedFolders = Docker.createSharedFolders(allInputsPath,allInputsId,doInputs);
        sharedFolders.put(specificPath,doOutputs);
        
        // Prepare inputs
        HashMap<String,String> allInputsPathArg  =  new HashMap<String,String>();
        allInputsPathArg.put(inputPath1,"--positions");
        allInputsPathArg.put(inputPath2,"--fasta-ref");
        allInputsPathArg.put(inputPath3,"");
        allInputsPathArg.put(inputPath4,"");
        for (String st:inputsPath3){
            if (allInputsPathArg.get(st)==null)
                allInputsPathArg.put(st,"");
        }
        allDoInputs = Docker.createAllDockerInputs(allInputsPathArg,allInputsPath,allInputsId,doInputs);
        
        // Prepare cluster relations
        Cluster.createLinkDockerClusterInputs(properties,allInputsPath,allInputsId,doInputs);
        Cluster.createLinkDockerClusterOutput(properties,output1,outputInDo1);
        
        // DOCKER INIT
        long startTime = System.nanoTime();
        if (Docker.isDockerHere(properties)){
            doName = Docker.getContainerName(properties,doName);
            if (!dockerInitContainer(properties,sharedFolders, doName, doImage))
                return false;
        } else {
            setStatus(status_BadRequirements,"Docker is not found. Please install docker");
            return false;
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        duration = TimeUnit.SECONDS.convert(duration, TimeUnit.NANOSECONDS);
        setStatus(status_running, "\t<TIME> Time to launch docker container is >"+duration+" s");
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
            options += Util.findOptionsNew(Advanced_Options,properties);
        
        // Docker command line
        String dockerCli = doPgrmPath+" "+options + allDoInputs + " --output " + outputInDo1;
        long startTime = System.nanoTime();
        Docker.prepareDockerBashFile(properties,doName,dockerCli);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        duration = TimeUnit.SECONDS.convert(duration, TimeUnit.NANOSECONDS);
        setStatus(status_running, "\t<TIME> Time to prepare docker bash file is >"+duration+" s");
        Cluster.createLinkDockerClusterCli(properties, dockerCli);
        setStatus(status_running,"DockerRunningCommandLine: \n$ "+dockerCli+"\n");
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
        long startTime = System.nanoTime();
        Docker.cleanContainer(properties,doName);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        duration = TimeUnit.SECONDS.convert(duration, TimeUnit.NANOSECONDS);
        setStatus(status_running, "\t<TIME> Time to stop and remove docker container is >"+duration+" s");
        if (output1.endsWith("vcf"))
            VCFFile.saveFile(properties,output1,"samtools_mpileup","VCFFile");
        if (output1.endsWith("bcf"))
            BCFFile.saveFile(properties,output1,"samtools_mpileup","BCFFile");
        if (output1.endsWith("bam"))
            BamFile.saveFile(properties,output1,"samtools_mpileup","BamFile");
        Results.saveResultsPgrmOutput(properties,this.getPgrmOutput(),"samtools_mpileup");
    }
}
