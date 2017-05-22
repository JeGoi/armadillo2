/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package programs;

import biologic.FastaFile;
import biologic.GenomeFile;
import biologic.Results;
import biologic.SamFile;
import biologic.Text;
import configuration.Util;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;
import program.RunProgram;
import static program.RunProgram.PortInputUP;
import static program.RunProgram.df;
import static program.RunProgram.status_error;
import workflows.workflow_properties;


/**
 *
 * @author Jérémy Goimard
 * @date Aout 2015
 *
 */
public class Bowtie1Index extends RunProgram{
    
    private String fastaFile1 ="";
    private String outputFile ="";
    private String outputPath ="."+File.separator+"indexed_genomes"+File.separator+"bowtie1";
    
    private String[] indexGenomeTab = {
        "IG_3_box",
        "IG_a_button",
        "IG_bmaxdivn_box",
        "IG_bmax_box",
        "IG_dcv_box",
        "IG_ntoa_box",
        "IG_nodc_box",
        "IG_o_box",
        "IG_p_box",
        "IG_q_box",
        "IG_r_box",
        "IG_seed_box",
        "IG_t_box",
        "IG_little_box",
        "IG_big_box"
    };
    
    public Bowtie1Index(workflow_properties properties) {
        this.properties=properties;
        execute();
    }
    
    @Override
    public boolean init_checkRequirements() {
        // File output directory
        if (properties.get("IDG_r_text").equals("") || !properties.isSet("IDG_r_text")) {
            properties.put("IDG_r_text",outputPath);
            if (!Util.DirExists(outputPath) && !Util.CreateDir(outputPath)) {
                setStatus(status_BadRequirements,"Can't create the directory "+outputPath);
                return false;
            }
        }
        
        // Input
        int fastaFile=properties.getInputID("FastaFile");
        if (fastaFile==0) {
            setStatus(status_BadRequirements,"No sequence found.");
            return false;
        }
        return true;
    }
    
    //@Override
    //public void init_createInput() {
    //}
    
    @Override
    public String[] init_createCommandLine() {
        // Inputs
        Vector<Integer>Fasta1 = properties.getInputID("FastaFile",PortInputDOWN);
        String optionsChoosed = "";
        
        fastaFile1 = FastaFile.getVectorFilePath(Fasta1);
        outputFile = properties.get("IDG_r_text")+File.separator+Util.getFileName(fastaFile1);
        
        if (properties.get("IG_AO_button").equals("true")){
            optionsChoosed = Util.findOptions(indexGenomeTab,properties);
        }
        
        String[] com = new String[30];
        for (int i=0; i<com.length;i++) com[i]="";
        
        com[0]="cmd.exe";
        com[1]="/C";
        com[2]=properties.getExecutable();
        com[3]=optionsChoosed;
        com[4]=fastaFile1;
        com[5]=outputFile;
        return com;
    }
    
    @Override
    public void post_parseOutput() {
        GenomeFile.saveFile(properties,outputFile,"Bowtie1_Index","GenomeFile");
        Results.saveResultsPgrmOutput(properties,this.getPgrmOutput(),"Bowtie1_Index");
    }
    
}
