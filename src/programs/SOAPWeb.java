/*
 *  Armadillo Workflow Platform v1.0
 *  A simple pipeline system for phylogenetic analysis
 *  
 *  Copyright (C) 2009-2013  Etienne Lord, Mickael Leclercq
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the 
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package programs;

import biologic.Alignment;
import biologic.MultipleSequences;
import configuration.Config;
import workflows.workflow_properties;
import configuration.Util.*;
import org.apache.axis.Constants;
import org.apache.axis.client.Call;
import program.RunProgram;

public class SOAPWeb extends RunProgram {

    ////////////////////////////////////////////////////////////////////////////
    /// VARIABLES

    private String JobId="";
    private String urlString ="";
    private String name = "";
    MultipleSequences multitmp=new MultipleSequences();

    ////////////////////////////////////////////////////////////////////////////
    /// CONSTRUCTOR


    public SOAPWeb(workflow_properties properties) {
        super(properties);
        execute();
    }

    /**
     * Test constructor - June 2010
     */
    public SOAPWeb() {}


      @Override
    public boolean init_checkRequirements() {
//        if (config.get("email").equals("")) {
//            setStatus(status_BadRequirements,"Error: Please set you email in Preferences->Email");
//            return false;
//        }
//        int multiplesequences_id=properties.getInputID("input_multiplesequences_id");
//        JobId=(properties.isSet("JobId")?properties.get("JobId"):"");
//        if (multiplesequences_id==0&&JobId.equals("")) {
//            return false;
//        } else if (!JobId.isEmpty()) {
//            setStatus(status_running,"Retrieving result for "+JobId);
//            return true;
//        } else {
//           multitmp=new MultipleSequences(multiplesequences_id);
//           addInput(multitmp);
//           if (multitmp.getNbSequence()==0) {
//            setStatus(status_BadRequirements,"Error: No Sequences found..");
//            return false;
//           }
//        }
        return true;
    }

    @Override
    public void init_createInput() {
        //--Do notthing
    }

     @Override
    public boolean do_run() throws Exception {
        if (this.getJobId().isEmpty()) {
            this.RunNormalWebService();
        } else {
            this.RunRetreiveWebService();
        }
        return true;
    }


  public void RunNormalWebService() throws Exception {
      System.out.println("Ok");
      setStatus(Config.status_running,"Running "+properties.getName());
                      //--Initialize some variables here
                        Call call = new Call(this.getUrlString());
                        System.out.println(call.getOperationName());
                        System.out.println(call.getEncodingStyle());
                        //call.setEncodingStyleURI(Constants.NS_URIS);
                        System.out.println(call.getOperationName());
                        System.out.println(call.getOutputValues());
//       call.setMethodName("sayGreeting");
//       call.setEncodingStyleURI(Constants.NS_URI_SOAP_ENC);
//       
//       //creating a parameter list
//       Vector params = new Vector();
//       params.addElement(new Parameter("name", String.class, name,null));
//
////adding the parameter(s) to the Call object
//       call.setParams(params);
//
//
//                      WSKalignClient client = new WSKalignClient();
//                      InputParams param =new InputParams();
//
//                      //--Specific Client variables from properties
//
//                      param.setAsync(true);
//                      param.setEmail(config.get("email"));
//                      String moltype=(properties.get("moltype"));
//                      //--Put some default properties
//                      float gpo=  (moltype.equals("N")?80:11);
//                      float gpe=  (moltype.equals("N")?0.85f:11);
//                      float tgpe= (moltype.equals("N")?0.35f:11);
//                      float bonus=(moltype.equals("N")?0:11);
//                      if (properties.isSet("gpo")) gpo=properties.getFloat("gpo");
//                      if (properties.isSet("gpe")) gpe=properties.getFloat("gpe");
//                      if (properties.isSet("tgpe")) tgpe=properties.getFloat("tgpe");
//                      if (properties.isSet("bonus")) bonus=properties.getFloat("bonus");
//
//                      param.setMoltype(moltype);
//                      param.setGpo(gpo);
//                      param.setGpe(gpe);
//                      param.setTgpe(tgpe);
//                      param.setBonus(bonus);
//
//                      Data[] input=new Data[1];
//                      input[0]=new Data();
//                      input[0].setType("sequence");
//                      input[0].setContent(multitmp.ouputFastaWithSequenceID());
//
//                       //--Run the Job with 3 retry
//                      String wsJobId=client.runApp(param,input);
//                      setStatus(status_running,"JobId: "+wsJobId);
//                      String wsstatus="PENDING";
//                      int retry=0;
//                      boolean wsdone=false;
//                      while(!wsdone&&retry<3) {
//                            wsstatus = client.checkStatus(wsJobId);
//                            //--Output information
//                            setStatus(Config.status_running,"Running "+properties.getName()+" for "+getRunningTime()+" ms status: "+wsstatus+" jobib: "+wsJobId+"\n");
//                            //--CASE 1: Still computing
//                           if (wsstatus.equals("RUNNING") || wsstatus.equals("PENDING")) Thread.sleep(10000);
//                           //--CASE 2: Error
//                            if (wsstatus.equals("ERROR") || wsstatus.equals("NOT)FOUND")) {
//                                    setStatus(Config.status_error,"***Error with "+properties.getName()+" at "+getRunningTime()+" ms status: "+wsstatus+" jobib: "+wsJobId+"\n");
//                                    wsdone=true;
//                           }
//                           //--CASE 3: Done
//                           if (wsstatus.equals("DONE")) {
//                               String[] outfiles=client.getResults(wsJobId, null, null);
//                                if (outfiles==null||outfiles[0]==null) {
//                                    retry++;
//                                    setStatus(Config.status_running,"Warning : No results retreive from EBI Website. Retry "+retry+"\n");
//                                    Thread.sleep(15000);
//                                } else {
//                                    for (String filename:outfiles) {
//                                        if (filename!=null) {
//                                            Alignment newmulti=new Alignment();
//                                            newmulti.readSequenceFromClustalW2(filename);
//                                            newmulti.setName(properties.getName()+" ("+configuration.Util.returnCurrentDateAndTime()+")");
//                                            newmulti.setNote(filename);
//                                            newmulti.saveToDatabase();
//                                            deleteFile(filename);
//                                            addOutput(newmulti);
//                                            properties.put("output_alignment_id", newmulti.getId());
//                                        }
//                                    }
//                                    setStatus(Config.status_done,"");
//                                    wsdone=true;
//                                    }
//                            } //--End DONE
//                          } //-- End while

  }

  public void RunRetreiveWebService() throws Exception {
//
//       //--Initialize some variables here
//          WSKalignClient client = new WSKalignClient();
//          String wsJobId = properties.get("JobId");
//
//          //-- Retrieve the Alignment
//          setStatus(Config.status_running,"Retrieving JobID "+wsJobId);
//          String wsstatus="PENDING";
//          int retry=0;
//          boolean wsdone=false;
//          while(!wsdone&&retry<3) {
//               wsstatus = client.checkStatus(wsJobId);
//                //--Output information
//                setStatus(Config.status_running,"Running "+properties.getName()+" for "+getRunningTime()+" ms status: "+wsstatus+" jobib: "+wsJobId+"\n");
//                //--CASE 1: Still computing
//               if (wsstatus.equals("RUNNING") || wsstatus.equals("PENDING")) Thread.sleep(10000);
//                //--CASE 2: Error
//               if (wsstatus.equals("ERROR") || wsstatus.equals("NOT)FOUND")) {
//                        setStatus(Config.status_error,"***Error with "+properties.getName()+" at "+getRunningTime()+" ms status: "+wsstatus+" jobib: "+wsJobId+"\n");
//                        wsdone=true;
//               }
//                //--CASE 3: Done
//               if (wsstatus.equals("DONE")) {
//                   String[] outfiles=client.getResults(wsJobId, null, null);
//                    if (outfiles==null||outfiles[0]==null) {
//                        retry++;
//                        setStatus(Config.status_running,"Warning : No results retreive from EBI Website. Retry "+retry+"\n");
//                        Thread.sleep(15000);
//                    } else {
//                        for (String filename:outfiles) {
//                            if (filename!=null) {
//                                Alignment newmulti=new Alignment();
//                                newmulti.readSequenceFromClustalW2(filename);
//                                newmulti.setName(properties.getName()+" ("+configuration.Util.returnCurrentDateAndTime()+")");
//                                newmulti.setNote(filename);
//                                newmulti.saveToDatabase();
//                                deleteFile(filename);
//                                properties.put("output_alignment_id", newmulti.getId());
//                            }
//                        }
//                         setStatus(Config.status_done,"Done");
//                         wsdone=true;
//                        }
//                } //--End Done
//              } //-- End while
  }

    /**
     * @return the JobId
     */
    public String getJobId() {
        return JobId;
    }

    /**
     * @param JobId the JobId to set
     */
    public void setJobId(String JobId) {
        this.JobId = JobId;
    }

    /**
     * @return the urlString
     */
    public String getUrlString() {
        return urlString;
    }

    /**
     * @param urlString the urlString to set
     */
    public void setUrlString(String urlString) {
        this.urlString = urlString;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
