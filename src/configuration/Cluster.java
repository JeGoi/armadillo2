/*
*  Armadillo Workflow Platform v1.0
*  A simple pipeline system for phylogenetic analysis
*
*  Copyright (C) 2009-2011  Etienne Lord, Mickael Leclercq
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
package configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.URLConnection;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipInputStream;
import workflows.workflow_properties;
import configuration.Util;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserPrincipal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang.SystemUtils;
import program.RunProgram;
import static program.RunProgram.config;
import static program.RunProgram.status_running;
import static program.RunProgram.workbox;
import workflows.Workbox;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import workflows.armadillo_workflow;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import java.awt.Point;
import org.apache.commons.io.IOUtils;
import workflows.armadillo_workflow.*;

/**
 * Collection of util command
 * @author Jérémy Goimard
 */
public class Cluster {
    /**
     * TO BE USED IN CLUSTER the program properties must contain
     * ClusterProgramName=
     * In general it's the same as name, but sometimes not
     * and
     * Version=
     * It need to match the exact cluster name, or the name's search will fail.
     */
    public static int count=0; //--Internal variable for returnCount...
    public static final SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat dateyear = new SimpleDateFormat("yyyy");
    //--Logging function
    public PrintWriter log;
    
    public Cluster() {}
    
    /***************************************************************************
     * CLUSTER FUNCTIONS
     **************************************************************************/
    public static workflow_properties getClusterProperties(Workbox workbox){
        armadillo_workflow armadillo = workbox.getCurrentArmadilloWorkflow();
        return getClusterProperties(armadillo);
    }

    public static workflow_properties getClusterProperties(armadillo_workflow armadillo){
        workflow_object obj = getClusterObject(armadillo);
        if (obj!=null)
            return obj.properties;
        return null;
    }
    
    public static workflow_object getClusterObject(armadillo_workflow armadillo){
        for (workflow_object obj:armadillo.workflow.work)
            if (isCluster(obj))
                return obj;
        return null;
    }

    public static boolean isCluster(workflow_object tmp){
        return isCluster(tmp.getProperties());
    }

    public static boolean isCluster(workflow_properties p){
        if (p.isSet("ObjectType")) {
            String s = p.get("ObjectType");
            if (s.equals("Cluster"))
                return true;
        }
        return false;
    }

    public static boolean isClusterEnable(Workbox workbox){
        workflow_properties clusterP = getClusterProperties(workbox);
        return isClusterEnable(clusterP);
    }

    public static boolean isClusterEnable(armadillo_workflow armadillo){
        workflow_properties clusterP = getClusterProperties(armadillo);
        return isClusterEnable(clusterP);
    }

    public static boolean isClusterEnable(workflow_properties clusterP){
        if (clusterP!=null)
            if (!clusterP.isSet("clusterEnabled"))
                return false;
            else
                return Boolean.getBoolean(clusterP.get("clusterEnabled"));
        return false;
    }

    public static boolean isClusterHere(armadillo_workflow armadillo) {
        workflow_object cluster = getClusterObject(armadillo);
        if (cluster==null)
            return false;
        else
            return true;
    }
        
    public static boolean setClusterEnable(armadillo_workflow armadillo,boolean b) {
        workflow_properties clusterP = getClusterProperties(armadillo);
        if (clusterP==null)
            return false;
        else {
            if (!isClusterEnable(armadillo))
                clusterP.put("clusterEnabled", Boolean.toString(b));
            updateClusterObjectPosition(armadillo);
            return true;
        }
    }
        
    public boolean cleanClusterPresence(armadillo_workflow armadillo){
        ArrayList<workflow_object> list = new ArrayList<workflow_object>();
        Vector<workflow_object> workTmp = armadillo.workflow.work;
        for(int i=0; i<workTmp.size();i++) {
            workflow_object obj=workTmp.get(i);
            if (obj.getProperties().get("Name").equals("Cluster") &&
                    obj.getProperties().get("ObjectType").equals("Cluster")
                    ) {
                //--debug Config.log(obj.getName());
                list.add(obj);
            }
        }
        if (list.size()>1){
            list.remove(0);
            for (workflow_object obj:list)
                armadillo.workflow.work.remove(obj);
            return true;
        }
        return false;
    }

    public static void insertClusterObject(armadillo_workflow armadillo){
        workflow_properties tmp=new workflow_properties();
        tmp.load("./src/configuration/CLUSTER.properties");
        armadillo.createObject(tmp,new Point(95,5));
        if (tmp==null) {
            System.out.println("Unable to create object from cluster properties");
        }
        //updateClusterObjectPosition(armadillo);
        armadillo.force_redraw=true;
        armadillo.redraw();
    }
    
    // ??
    public void cleanClusterObject(armadillo_workflow armadillo){
        boolean b = cleanClusterPresence(armadillo);
        if (b){
            armadillo.force_redraw=true;
            armadillo.redraw();
        }
    }

    public static void updateClusterObjectPosition(armadillo_workflow armadillo){
        boolean b = isClusterHere(armadillo);
        if (b) {
            workflow_object tmp = getClusterObject(armadillo);
            if (tmp!=null){
                boolean b1 = isClusterEnable(armadillo);
                if (b1){
                    tmp.move(225,10);
                } else {
                    //Util.dm("move1");
                    tmp.move(-25000,0);
                }
                armadillo.updateCurrentWorkflow(tmp.getProperties());
            } else {
                System.out.println("Impossible to load Cluster object");
            }
        }
        armadillo.force_redraw=true;
        armadillo.redraw();
    }
    
    /////////////
    // ARMADILLO workflow functions used in .src/workflows/armadillo_workflow
    /**
     * Add Specific properties that will be used in bashfile preparation
     * During the creation updateDependance()
     */
    public static void addSpecificClusterProperties(armadillo_workflow.workflow_object source, armadillo_workflow.workflow_object dest, Integer i) {
        workflow_properties pSource = source.getProperties();
        workflow_properties pDest = dest.getProperties();
        if (pSource.isSet("FileNameFromId")){
            String s = pSource.get("FileNameFromId");
            pDest.put("ClusterLocalInput_"+Integer.toString(i), s);
        }
    }
    
    /////////////
    // Pre test
    public static String clusterAccessAddress(Workbox workbox) {
        workflow_properties clusterP = getClusterProperties(workbox);
        return clusterP.get("ClusterAccessAddress");
    }

    public static String getP2Rsa(Workbox workbox) {
        workflow_properties clusterP = getClusterProperties(workbox);
        return clusterP.get("PathToRSAFile");
    }
    
    public static boolean isP2RsaHere(Workbox workbox){
        workflow_properties clusterP = getClusterProperties(workbox);
        if (clusterP.isSet("PathToRSAFile")){
            String s = clusterP.get("PathToRSAFile");
            if (!s.contains("path to private key")){
                return true;
            }
        }
        return false;
    }

    public static ArrayList<String> getModules(workflow_properties properties){
        String c = "module avail";
        String p2rsa = getP2Rsa(properties);
        ArrayList<String> tab = runSshCommand(properties,p2rsa,c);
        return tab;
    }

    /////////////
    // Add ClusterAccessAddress and PathToRSAFile from internaljframe properties to program properties
    public static workflow_properties tansfertClusterEditorProperties(Workbox workbox, workflow_properties properties){
        workflow_properties clusterP = getClusterProperties(workbox);
        String[] tab = {"ClusterPWD","ClusterAccessAddress","PathToRSAFile","ClusterModules"};
        for (String s:tab){
            if (clusterP.isSet(s)){
                properties.put(s,clusterP.get(s));
            }
        }
        return properties;
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // Get some program properties
    public static boolean isDocker(workflow_properties p) {
        if (p.isSet("ExecutableDocker")){
            String s = p.get("ExecutableDocker");
            //Util.dm("ExecutableDocker>"+s);
            if (s==""||s.contains("Not Set")||s.isEmpty()){
                return false;
            }
            return true;
        }
        return false; 
    }
    
    public static String clusterAccessAddress(workflow_properties p) {
        return p.get("ClusterAccessAddress");
    }
    
    public static String clusterSeverAddress(workflow_properties properties) {
        String s = clusterAccessAddress(properties);
        int i = s.lastIndexOf("@");
        return s.substring(i+1,s.length());
    }
    
    public static String clusterSeverUserName(workflow_properties properties) {
        String s = clusterAccessAddress(properties);
        int i = s.lastIndexOf("@");
        return s.substring(0,i);
    }
    
    public static boolean isAClusterTasksNumberHere(workflow_properties properties) {
        if(properties.isSet("ClusterTasksNumber")){
            return true;
        }
        return false;
    }
    
    public static String getP2Rsa(workflow_properties p){
        return p.get("PathToRSAFile");
    }

    public static boolean isP2RsaHere(workflow_properties p){
        if (p.isSet("PathToRSAFile")){
            String s = p.get("PathToRSAFile");
            if (!s.contains("path to private key")){
                return true;
            }
        }
        return false;
    }
    
    public static String getCommandLineRunning(workflow_properties properties) {
        if (properties.isSet("Commandline_Running"))
            return properties.get("Commandline_Running");
        return "";
    }
    
    public static String getDockerCommandLineRunning(workflow_properties properties) {
        if (properties.isSet("ClusterDockerRunningCLi"))
            return properties.get("ClusterDockerRunningCLi");
        return "";
    }
    
    
    
    public static String updateCommandFromProgramToCluster(workflow_properties properties, String c){
        String clusterDir = properties.get("ClusterDirPath");
        Enumeration<Object> e = properties.keys();
        while(e.hasMoreElements()) {
            String key=(String)e.nextElement();
            if (key.contains("ClusterLocalInput_")){
                String fLocal = properties.get(key);
                String fRem  = getClusterFilePath(properties,fLocal);
                c = c.replace(fLocal, fRem);
            }
            if (key.contains("ClusterLocalOutput_")){
                String fLocal = properties.get(key);
                String fName = Util.getFileNameAndExt(fLocal);
                String fRem = clusterDir+"/outputs/"+fName;
                c = c.replace(fLocal, fRem);
            }
            if (key.contains("ExecutableCluster")){
                String pLoc = Util.getOSCommandLine(properties);
                String pRem = properties.get(key);
                c = c.replace(pLoc, pRem);
            }
        }
        return c;
    }
    
    public static String updateCommandFromDockerToCluster(workflow_properties properties, String c){
        String clusterDir = properties.get("ClusterDirPath");
        Enumeration<Object> e = properties.keys();
        while(e.hasMoreElements()) {
            String key=(String)e.nextElement();
            if (key.contains("ClusterDockerInput_")){
                String[] fLocal = restoreLocalDocker(properties.get(key));
                String fRem  = getClusterFilePath(properties,fLocal[0]);
                c = c.replace(fLocal[1], fRem);
            }
            if (key.contains("ClusterLocalOutput_")){
                String[] fLocal = restoreLocalDocker(properties.get(key));
                String fName = Util.getFileNameAndExt(fLocal[0]);
                String fRem = clusterDir+"/outputs/"+fName;
                c = c.replace(fLocal[1], fRem);
            }
            if (key.contains("ExecutableCluster")){
                String pLoc = properties.get("ExecutableDocker");
                String pRem = properties.get(key);
                c = c.replace(pLoc, pRem);
            }
        }
        return c;
    }
            
    public static String[] restoreLocalDocker(String c){
        //Util.dm("c"+c);
        String[] s = c.split("<<>>");
        //Util.dm("Taille de split>"+Integer.toString(s.length));
        return s;
    }
            
        

    /**
     * Prepare the remote cluster directory and file name
     */
    public static boolean getClusterDirPath(workflow_properties properties) {
        String clusterPWD = "";
        String prgmName = properties.get("ClusterProgramName");
        String prgmVersion = properties.get("Version");
        String prgmOrder = properties.get("ObjectID");
        
        if (properties.isSet("ClusterPWD"))
            clusterPWD = properties.get("ClusterPWD");
        if (clusterPWD!=""){
            String s = clusterPWD+"/"+prgmName+"_"+prgmVersion+"/"+prgmOrder;
            s = Util.replaceMultiSpacesByOne(s);
            s = Util.replaceSpaceByUnderscore(s);
            if (s!=""){
                properties.put("ClusterDirPath",s);
                return true;
            }
        }
        return false;
    }
    
    public static String getClusterFilePath(workflow_properties properties, String localPath) {
        String clusterDir = properties.get("ClusterDirPath");
        String fName = Util.getFileNameAndExt(localPath);
        if (clusterDir!=""){
            return clusterDir+"/"+fName;
        }
        return "";
    }
    
    /**
     * It's the way to add link between file and docker
     * Added by JG 2017
     */
    public static void createLinkDockerClusterInputs(workflow_properties properties, String[] tabPath, String[] tabId, String doInputs) {
        for(int i = 0; i < tabPath.length; i++) {
            if (tabPath[i]!="") {
                String c = Util.getCanonicalPath(tabPath[i]);
                String name = Util.getFileNameAndExt(c);
                properties.put("ClusterDockerInput_"+tabId[i],tabPath[i]+"<<>>"+doInputs+tabId[i]+"/"+name+"");
            }
        }
    }

    public static void createLinkDockerClusterOutput(workflow_properties properties, String localOutput, String dockerOutput) {
        properties.put("ClusterLocalOutput_1",localOutput+"<<>>"+dockerOutput);
    }
    
    public static void createLinkDockerClusterCli(workflow_properties properties, String dockerCli) {
        properties.put("ClusterDockerRunningCLi",dockerCli);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////
    /**
     * Test if the path is a Unix path
     * Added by JG 2017
     * @param s path ex: ./path/to/file/file.f or \sdfkbgk\ls sldkf\
     * @return true or false
     */
    public static boolean isASimpleUnixPath(String s) {
        return (s.matches("(/\\w*)+"));
    }
    /**
     * 
     * @param s
     * @return 
     */
    private static String getClusterServerName(String s){
        if (s.contains("briaree.calculquebec.ca"))
            return "biraree";
        if (s.contains("colosse.calculquebec.ca"))
            return "colosse";
        if (s.contains("cottos.calculquebec.ca"))
            return "cottos";
        if (s.contains("guillimin.hpc.mcgill.ca"))
            return "guillimin";
        if (s.contains("mp2.ccs.usherbrooke.ca"))
            return "mp2";
        if (s.contains("ms.ccs.usherbrooke.ca"))
            return "ms";
        if (s.contains("psi.concordia.ca"))
            return "psi";
    return "";
    }
    
    public static ArrayList<String> getListOfFilesinDirectory(String dir){
        ArrayList<String> list = new ArrayList<String>();
        File folder = new File(dir);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                list.add(Util.getCanonicalPath(listOfFiles[i].getPath()));
            }
        }
        return list;
    }
    
    public static boolean extractInfo(workflow_properties properties,List<String> lines){
        for (String l :lines){
            Enumeration<Object> e = properties.keys();
            boolean b = true;
            String s = "";
            while(e.hasMoreElements()&&b==true) {
                String key=(String)e.nextElement();
                if (key.contains(l)){
                    if (properties.get(key)!=""){
                        b = false;
                    }
                }
            }
            if (b){
                Util.dm("This is not in properties>>"+l);
                return true;
            }
        }
        return false;
    }
    
    public static Integer getExitValue(String s){
        Pattern p = Pattern.compile(".*ExitStatus>(\\d+)<.*");
        Matcher m = p.matcher(s);
        Util.dm(m.toString());
        if (m.find()){
            int i = Integer.parseInt(m.group(1));
            return i;
        }
        return -1;
    }
    

    ///////////////////////////////////////////////////////////////////////////
    // Functions to access to the server by ssh and scp
    ///////////////////////////////////////////////////////////////////////////
    public static ArrayList<String>  runSshCommand(workflow_properties properties,String p2rsa, String command) {
        ArrayList<String> tab = new ArrayList<String>();
        String hostName = clusterSeverAddress(properties);
        String userName = clusterSeverUserName(properties);
        try {
            Connection conn = new Connection(hostName);
            conn.connect();
            File f = new File(p2rsa);
            boolean isAuthenticated = conn.authenticateWithPublicKey(userName,f,"");
            if (isAuthenticated == false)
                    throw new IOException("Authentication failed.");
            /* Create a session */
            Session sess = conn.openSession();
            sess.execCommand(command);
            InputStream stdout = new StreamGobbler(sess.getStdout());
            InputStream stderr = new StreamGobbler(sess.getStderr());
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
            while (true) {
                String line = br.readLine();
                if (line == null)
                    break;
                tab.add(line);
            }
            br = new BufferedReader(new InputStreamReader(stderr));
            while (true) {
                String line = br.readLine();
                if (line == null)
                    break;
                tab.add(line);
            }
            tab.add("ExitCode: >" + sess.getExitStatus()+"<");
            sess.close();
            conn.close();
        }
        catch (IOException e)
        {
            e.printStackTrace(System.err);
            System.exit(2);
        }
        
        //Util.dm(Arrays.toString(tab.toArray()));
        
        return tab;
    }

    public static boolean runUploadFile(workflow_properties properties,String p2rsa, String local, String remote) {
        String hostName = clusterSeverAddress(properties);
        String userName = clusterSeverUserName(properties);
        try {
            Connection conn = new Connection(hostName);
            conn.connect();
            /* Now connect */
            File f = new File(p2rsa);
            boolean isAuthenticated = conn.authenticateWithPublicKey(userName, f,"");

            if (isAuthenticated == false) {
                throw new IOException("Authentication failed.");
            }
            SCPClient scp = conn.createSCPClient();
            scp.put(local, remote);
            conn.close();
        } catch (IOException ex) {
            Logger.getLogger(Cluster.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public static boolean runUploadDir(workflow_properties properties,String p2rsa, String local, String remote) {
        String hostName = clusterSeverAddress(properties);
        String userName = clusterSeverUserName(properties);
        String dirPath  = Util.getParentOfFile(Util.getCanonicalPath(local));
        if (!dirPath.endsWith(File.separator))
            dirPath += File.separator;
            
        try {
            Connection conn = new Connection(hostName);
            conn.connect();
            /* Now connect */
            File f = new File(p2rsa);
            boolean isAuthenticated = conn.authenticateWithPublicKey(userName, f,"");

            if (isAuthenticated == false) {
                throw new IOException("Authentication failed.");
            }
            SCPClient scp = conn.createSCPClient();
            scp.put(dirPath+"*", remote);
            conn.close();
        } catch (IOException ex) {
            Logger.getLogger(Cluster.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public static boolean runDownloadFile(workflow_properties properties,String p2rsa, String local, String remote) {
        String hostName = clusterSeverAddress(properties);
        String userName = clusterSeverUserName(properties);
        try {
            Connection conn = new Connection(hostName);
            conn.connect();
            /* Now connect */
            File f = new File(p2rsa);
            boolean isAuthenticated = conn.authenticateWithPublicKey(userName, f, "");
            if (isAuthenticated == false) {
                throw new IOException("Authentication failed.");
            }
            SCPClient scp = conn.createSCPClient();
            scp.get(remote, local);
            conn.close();
        } catch (IOException ex) {
            Logger.getLogger(Cluster.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public static boolean runDownloadDir(workflow_properties properties, String p2rsa, String local, String[] remote) {
        String hostName = clusterSeverAddress(properties);
        String userName = clusterSeverUserName(properties);
        try {
            Connection conn = new Connection(hostName);
            conn.connect();
            /* Now connect */
            File f = new File(p2rsa);
            boolean isAuthenticated = conn.authenticateWithPublicKey(userName, f, "");
            if (isAuthenticated == false) {
                throw new IOException("Authentication failed.");
            }
            SCPClient scp = conn.createSCPClient();
            scp.get(remote, local);
            conn.close();
        } catch (IOException ex) {
            Logger.getLogger(Cluster.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    /**
     * Tested on Cluster MP2 only to be validated in the others
     * @param workbox
     * @param pgName
     * @param pgVersion
     * @return 
     */
    public static boolean getModule(workflow_properties properties,ArrayList<String> tab,String ref){
        ref = ref.toLowerCase();
        Pattern pat1 = Pattern.compile(ref);
        Pattern pat2 = Pattern.compile("\\s*[(]\\w[)]");
        if (tab.size()>0){
            for (String s:tab){
                s = s.toLowerCase();
                Matcher mat1 = pat1.matcher(s);
                Matcher mat2 = pat2.matcher(s);
                if (mat1.find()){
                    if (mat2.find())
                        s = mat2.replaceAll("");
                    if (s!="") {
                        Util.removeTrailingSpace(s);
                        properties.put("ClusterModuleIs", s);
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * Execute bash file on Cluster
     * Added by JG 2017
     * @param s path ex: ./path/to/file/file.f or \sdfkbgk\ls sldkf\
     * @return true or false
     */
    public static String executePbsOnCluster(workflow_properties properties, String p2rsa, String file) {
        String c = "qsub ";
        ArrayList<String> tab = runSshCommand(properties,p2rsa,c+" "+file);
        if (tab.size()==2){
            if (tab.get(0).contains(".mp2.m"))
                return tab.get(0);
        }
        return "";
    }
    
    
    ////////////////////////////////////////////////////////////////////////////
    // STEPS
    ////////////////////////////////////////////////////////////////////////////
    // Step 0
    public static boolean isClusterNeededInfoHere(workflow_properties properties) {
        List<String> lines = Arrays.asList("ClusterLocalOutput_",
                "ClusterLocalInput_", "ClusterProgramName", "Version",
                "ObjectID", "ExecutableCluster", "ClusterAccessAddress",
                "PathToRSAFile");
        return extractInfo(properties,lines);
    }
    
    public static boolean isClusterDockerNeededInfoHere (workflow_properties properties){
        List<String> lines = Arrays.asList("ClusterDockerInput_",
                "ClusterDockerRunningCLi", "ExecutableDocker");
        return extractInfo(properties,lines);
    }
    
    /////////////
    // Step 1
    public static boolean getAccessToCluster(workflow_properties properties){
        String c = "pwd";
        String p2rsa = getP2Rsa(properties);
        ArrayList<String> tab = runSshCommand(properties,p2rsa,c);
        if (tab.size()==2 && isASimpleUnixPath(tab.get(0))){
            properties.put("ClusterPWD",tab.get(0));
            return true;
        }
        return false;
    }
    
    /////////////
    // Step 2
    public static boolean isTheProgramOnCluster(workflow_properties properties){
        String pgName    = properties.get("ClusterProgramName");
        String pgVersion = properties.get("Version");
        ArrayList<String> tab = getModules(properties);
        return getModule(properties,tab,pgName+"\\/"+pgVersion);
    }
    
    public static boolean isTheProgramOnClusterFromLocal(workflow_properties properties){
        String clusterM = properties.get("ClusterModules");
        ArrayList<String> tab = new ArrayList<String>(Arrays.asList(clusterM.split(",")));
        String pgName    = properties.get("ClusterProgramName");
        String pgVersion = properties.get("Version");
        return getModule(properties,tab,pgName+"\\/"+pgVersion);
    }
    
    /////////////
    // Step 3
    /**
     * Create the directory in cluster
     * Added by JG 2017
     * @param s path ex: ./path/to/file/file.f or \sdfkbgk\ls sldkf\
     * @return true or false
     */
    public static boolean createClusterDir(workflow_properties properties) {
        if(getClusterDirPath(properties)){
            String program = "mkdir -p";
            String p2rsa = getP2Rsa(properties);
            String clusterDir = properties.get("ClusterDirPath");
            ArrayList<String> tab = runSshCommand(properties,p2rsa, program+" "+clusterDir+"/outputs");
            //Util.dm(Arrays.toString(tab.toArray()));
            if (tab.size()==1)
                if(tab.get(0).contains("ExitCode: >0<")||tab.get(0).contains("ExitCode: >null<"))
                    return true;
        }
        return false;
    }
    
    /////////////
    // Step 4
    public static boolean sendFilesOnCluster(workflow_properties properties) {
        String clusterDir = properties.get("ClusterDirPath");
        String p2rsa = getP2Rsa(properties);
        Enumeration<Object> e = properties.keys();
        boolean b = true;
        while(e.hasMoreElements() && b==true) {
            String k = (String)e.nextElement();
            if (k.contains("ClusterLocalInput_")){
                String file = properties.get(k);
                String dir = Util.getParentOfFile(Util.getCanonicalPath(file));
                ArrayList<String> listFiles = getListOfFilesinDirectory(dir);
                if (listFiles.size()>0){
                    for (String f:listFiles){
                        boolean b2 = runUploadFile(properties,p2rsa,f,clusterDir);
                        if (!b2){
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }
    
    /////////////
    // Step 5
    public static boolean clusterPbs(workflow_properties properties) {
        // Server name
        String serverName = getClusterServerName(clusterAccessAddress(properties));
        String c = "";
        if(isDocker(properties)){
            // Prepare variables
            c = getDockerCommandLineRunning(properties);
            c = updateCommandFromDockerToCluster(properties,c);
        } else {
            // Prepare variables
            c = getCommandLineRunning(properties);
            c = updateCommandFromProgramToCluster(properties,c);
        }
        
        String stdOut = getClusterFilePath(properties,"stdOutFile");
        String stdErr = getClusterFilePath(properties,"stdErrFile");
        
        
        String hours = "00";
        String minutes = "05";
        String seconds = "00";
        String nodesV = "1";
        String ppn = "1";
        String emailV = "email@email.com";
        if (properties.isSet("WalltimeHours_box"))
            hours = properties.get("WalltimeHours_box");
        if (properties.isSet("WalltimeMinutes_box"))
            minutes = properties.get("WalltimeMinutes_box");
        if (properties.isSet("WalltimeSeconds_box"))
            seconds = properties.get("WalltimeSeconds_box");
        if (properties.isSet("BasicNodes_box"))
            nodesV = properties.get("BasicNodes_box");
        if (properties.isSet("BasicPPN_box"))
            ppn = properties.get("BasicPPN_box");
        if (properties.isSet("ContactEmail_box"))
            emailV = properties.get("ContactEmail_box");
        if (properties.isSet("ContactEmail_box"))
            seconds = properties.get("ContactEmail_box");
                
        
        String walltime = "#PBS -l walltime="+hours+":"+minutes+":"+seconds+"";
        String nodes    = "#PBS -l nodes="+nodesV+":ppn="+ppn+"";
        // Need to be setted depending on cluster choosed
        String qwork    = "#PBS -q qwork@mp2";
        String r        = "#PBS -r n";
        String stdDoc   = "#PBS -o "+stdOut+"";
        String stdDec   = "#PBS -e "+stdErr+"";
        String email    = "#PBS -M "+emailV+"";
        String exitValue= "a=\"ExitStatus>\"$?\"<\"\necho $a";
        /*
        WARNINGS ITS ONLY FOR ONE MODULE not if there is dependencies
        */
        String module   = "module load "+properties.get("ClusterModuleIs")+"";
        
        // Prepare lines
        List<String> lines = new ArrayList<String>();
        lines.add("#!/bin/bash");
        lines.add(walltime);
        lines.add(nodes);
        lines.add(qwork);
        if (!serverName.contains("mp2")){
            lines.add(r);
            lines.add(email);
        }
        lines.add(stdDoc);
        lines.add(stdDec);
        lines.add(module);
        lines.add(c);
        lines.add(exitValue);
        
        // Prepare bash file
        String fBash = Util.currentPath()+File.separator+"tmp"+File.separator+"clusterPbs.sh";
        Util.CreateFile(fBash);
        Path file = Paths.get(fBash);
        try {
            Files.write(file, lines);
        } catch (IOException ex) {
            Logger.getLogger(Docker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        String p2rsa = getP2Rsa(properties);
        String clusterDir = properties.get("ClusterDirPath");
        boolean b = runUploadFile(properties,p2rsa,fBash,clusterDir);
        if (!b){
            return false;
        }
        properties.put("ClusterCommandLineRunning", c);
        properties.put("ClusterPBSInfo", String.join("\n", lines));
        Util.deleteFile(fBash);
        String tasksNum = executePbsOnCluster(properties,p2rsa,clusterDir+"/clusterPbs.sh");
        if (tasksNum!=""){
            properties.put("ClusterTasksNumber",tasksNum);
            return true;
        }
        return false;
    }

    /////////////
    // Step 6
    /**
     * Wait output file
     * Added by JG 2017
     * @param 
     * @return true or false
     */
    public static boolean isStillRunning(workflow_properties properties) {
        Enumeration<Object> e = properties.keys();
        String taskId  = "";
        while(e.hasMoreElements()) {
            String key=(String)e.nextElement();
            if (key.contains("ClusterTasksNumber")){
                taskId  = properties.get(key);
            }
        }
        if (taskId!=""){
            Integer[] l = {60,60,60,60,60,60,120,240,480,960,1920};
            String p2rsa = getP2Rsa(properties);
            String command = "qstat -f "+taskId+"";
            boolean b = false;
            int i = 0;
            while(b == false && i<l.length) {
                ArrayList<String> tab = runSshCommand(properties,p2rsa,command);
                if (tab.size()==2 && tab.get(0).contains("qstat: Unknown Job Id")){
                    b = true;
                } else {
                    try {
                        TimeUnit.SECONDS.sleep(l[i]);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Cluster.class.getName()).log(Level.SEVERE, null, ex);
                        i = l.length;
                    }
                    i+=1;
                }
            }
            return b;
        }
        return false;
    }
    
    /////////////
    // Step 7
    /**
     * Test if the output is here path is a Unix path
     * Added by JG 2017
     * @param 
     * @return true or false
     */
    public static boolean downloadResults(workflow_properties properties) {
        String p2rsa = getP2Rsa(properties);
        Enumeration<Object> e = properties.keys();
        while(e.hasMoreElements()) {
            String key=(String)e.nextElement();
            if (key.contains("ClusterLocalOutput_")){
                String fLoc = "";
                if(isDocker(properties)){
                    fLoc = restoreLocalDocker(properties.get(key))[0];
                } else {
                    fLoc = properties.get(key);
                }
                String fDir = fLoc;
                if(Util.testIfFile(fLoc))
                    fDir = Util.getParentOfFile(fLoc);
                String clusterDir = properties.get("ClusterDirPath");
                String fClus = clusterDir+"/outputs/";
                ArrayList<String> tab = runSshCommand(properties,p2rsa,"ls "+fClus);
                if (tab.size()>=2 && !tab.get(0).contains("ls")){
                    tab.remove(tab.size()-1);
                    for (int i=0; i<tab.size(); i++){
                        tab.set(i,fClus+tab.get(i));
                    }
                    String[] tabTmp = tab.toArray(new String[tab.size()]);
                    boolean b2 = runDownloadDir(properties,p2rsa,fDir,tabTmp);
                    if(!b2){
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }
    
    /////////////
    // Step 8
    public static String getPgrmOutput(workflow_properties properties,String std){
        String p2rsa = getP2Rsa(properties);
        String fClus = getClusterFilePath(properties,std);
        ArrayList<String> tab = runSshCommand(properties,p2rsa,"cat "+fClus);
        if (tab.size()==1 && tab.get(0).contains("cat"))
            return "";
        else
            return Arrays.toString(tab.toArray());
    }
    
    /////////////
    // Step 9
    /**
     * Remove the cluster task number
     * Added by JG 2017
     * @param s path ex: ./path/to/file/file.f or \sdfkbgk\ls sldkf\
     * @return true or false
     */
    public static boolean removeClusterTasksNumber(workflow_properties properties) {
        if(properties.isSet("ClusterTasksNumber")){
            properties.remove("ClusterTasksNumber");
            return true;
        }
        return false;
    }
    
    public static boolean removeFilesFromCluster(workflow_properties properties){
        
        return true;
    }
    
    public static boolean savePathOfFilesOnCluster(workflow_properties properties){
        
        return true;
    }
    
    // OBSOLETE FUNCTIONS
    public static String[] call_Python_Process(String script, String commands)
            throws IOException, InterruptedException {
        //System.out.println("Commands>"+commands);
        String STDoutput = "";
        String STDError  = "";
        
        Process p = Runtime.getRuntime().exec("python "+script+" "+commands);
        BufferedReader bri = new BufferedReader
                    (new InputStreamReader(p.getInputStream()));
        BufferedReader bre = new BufferedReader
                    (new InputStreamReader(p.getErrorStream()));
        String line = "";

        // Check Output
        while ((line = bri.readLine()) != null) {
            STDoutput += line;
        }
        bri.close();
        while ((line = bre.readLine()) != null) {
            STDError += line;
        }
        bre.close();
        p.waitFor();
        
        String[] tab = {STDoutput,STDError};
        return tab;
    }

    //////////////////////////////////////////////////////////////////////////
    // USELESS functions
    public static String get_commands(workflow_properties properties) {
        String clusterAA = clusterAccessAddress(properties);
        String str =properties.getPropertiesToVarStringWithEOL();
        str = "\'"+str+"\'";
        str = str.replaceAll(" ", "_____");
        String commands = " -obj "+str+clusterAA;
        return commands;
    }

    /**
     * Useless?
     * @param properties
     * @param commandline
     * @return 
     */
    public static String macOSX_cmd_Modifications(workflow_properties properties, String[] commandline) {
        String cmdm = Util.toString(commandline);
        String execution_type=properties.get("RuntimeMacOSX");
        if (execution_type.startsWith("runtime")) {
            cmdm = Util.replaceMultiSpacesByOne(cmdm);
            cmdm = Util.removeTrailingSpace(cmdm);
        }
        return cmdm;
    }
    

}
