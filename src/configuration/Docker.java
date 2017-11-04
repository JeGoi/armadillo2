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

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import workflows.workflow_properties;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.*;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.CreateNetworkResponse;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.exception.ConflictException;
import com.github.dockerjava.api.exception.DockerException;
import com.github.dockerjava.api.model.Bind;
import com.github.dockerjava.api.model.ContainerNetwork;
import com.github.dockerjava.api.model.Device;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.Link;
import com.github.dockerjava.api.model.LogConfig;
import com.github.dockerjava.api.model.Network;
import com.github.dockerjava.api.model.Ports;
import com.github.dockerjava.api.model.RestartPolicy;
import com.github.dockerjava.api.model.Ulimit;
import com.github.dockerjava.api.model.Volume;
import com.github.dockerjava.api.model.VolumesFrom;
import com.github.dockerjava.api.model.Ports.Binding;
import java.security.SecureRandom;


import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.ExecCreateCmdResponse;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.command.ListContainersCmd;
import com.github.dockerjava.api.command.ListImagesCmd;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.api.model.Version;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.command.PullImageResultCallback;
import com.github.dockerjava.core.util.CompressArchiveUtil;
import com.github.dockerjava.core.command.ExecStartResultCallback;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Collection of util command
 * @author Jérémy Goimard
 */
public class Docker {
    public static int count=0; //--Internal variable for returnCount...
    public static final SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat dateyear = new SimpleDateFormat("yyyy");
    //--Logging function
    public PrintWriter log;
    public static String kword = "armadilloWF";
    
    public Docker() {}
    
    /***************************************************************************
     * DOCKER FUNCTIONS
     **************************************************************************/

    /**
     * Test if the program is using docker
     */
    public static boolean isProgramUseDocker (workflow_properties properties) {
        String dockerCommand = Util.getOSCommandLine(properties);
        if (dockerCommand.toLowerCase().contains("docker"))
            return true;
        return false;
    }

    /**
     * Functions to test if docker is present
     */
    public static boolean isDockerHere () {
        if (isDockerHereByRootDir() || isDockerHereByVersion())
            return true;
        return false;
    }
    
    public static boolean isDockerHereByRootDir () {
        String s = getRootDir();
        if (s.toLowerCase().contains("docker")) {
            return true;
        }
        return false;
    }

    public static boolean isDockerHereByVersion () {
        Version v = getVersion();
        if (v.getGoVersion().length() > 0)
            return true;
        if (v.getVersion().length() > 0)
            return true;
        return false;
    }
    
    /**
     * Functions to get a docker instance by default and to close it
     * COULD BE GOOD to create a similar function with specific config builder for advanced docker user
     */

    public static DockerClientConfig createDockerClientConfig () {
        return DefaultDockerClientConfig.createDefaultConfigBuilder()
                .build();
    }
    
    public static DockerClient getDockerClient () {
        return DockerClientBuilder.getInstance(createDockerClientConfig())
                .build();
    }    
    
    public static boolean closeDockerClient (DockerClient dockerClient) {
        try {
            dockerClient.close();
            return true;
        } catch (IOException ex) {
            System.out.println(Docker.class.getName());
            System.out.println(ex);
        }
        return false;
    }

    /*
     * Functions to get info from Docker
     */    

    public static Version getVersion (){
        DockerClient dockerClient = getDockerClient();
        Version version = dockerClient.versionCmd().exec();
        closeDockerClient(dockerClient);
        return version;
    }
    
    public static Info getDockerInfo () {
        DockerClient dockerClient = getDockerClient();
        Info info = dockerClient.infoCmd().exec();
        closeDockerClient(dockerClient);
        return info;
    }
    
    public static String getClusterAdvertise () {
        if (isDockerHere())
            return getDockerInfo().getClusterAdvertise();
        else
            return "";
    }
    
    public static String getHttpProxy () {
        if (isDockerHere())
            return getDockerInfo().getHttpProxy();
        else
            return "";
    }
    
    public static String getOperatingSystem () {
        if (isDockerHere())
            return getDockerInfo().getOperatingSystem();
        else
            return "";
    }
    
    public static String getOsType () {
        if (isDockerHere())
            return getDockerInfo().getOsType();
        else
            return "";
    }
    
    public static String getServerVersion () {
        if (isDockerHere())
            return getDockerInfo().getServerVersion();
        else
            return "";
    }
    
    public static String getNCPU () {
        if (isDockerHere())
            return Integer.toString(getDockerInfo().getNCPU());
        else
            return "";
    }
    
    public static String getNFd () {
        if (isDockerHere())
            return Integer.toString(getDockerInfo().getNFd());
        else
            return "";
    }
    
    public static String getInitPath () {
        if (isDockerHere())
            return getDockerInfo().getInitPath();
        else
            return "";
    }
    
    public static String getRootDir () {
        return getDockerInfo().getDockerRootDir();
    }

    public static String getRootDirIfDocker () {
        if (isDockerHere())
            return getDockerInfo().getDockerRootDir();
        else
            return "";
    }

    public static String getDriver () {
        if (isDockerHere())
            return getDockerInfo().getDriver();
        else
            return "";
    }

    public static String getArchitecture () {
        if (isDockerHere())
            return getDockerInfo().getArchitecture();
        else
            return "";
    }
    
    public static String getClusterStore () {
        if (isDockerHere())
            return getDockerInfo().getClusterStore();
        else
            return "";
    }

    public static String getExecutionDriver () {
        if (isDockerHere())
            return getDockerInfo().getExecutionDriver();
        else
            return "";
    }

    public static String getSockets () {
        if (isDockerHere())
            return Arrays.toString(getDockerInfo().getSockets());
        else
            return "";
    }

    /***************************************************************************
     * Functions for Images
     **************************************************************************/    

    /**
     * Lists of images
     */
    public static List<String> getAllImagesID () {
        DockerClient dockerClient = getDockerClient();
        List<Image> images = dockerClient.listImagesCmd().exec();
        List<String> list = new ArrayList<String>();
        for(int i=0; i < images.size(); i++){
            //System.out.println(images.get(i).getId());
            list.add(images.get(i).getId());
        }
        return list;
    }
    
    public static List<String> getAllImagesName () {
        DockerClient dockerClient = getDockerClient();
        List<Image> images = dockerClient.listImagesCmd().exec();
        List<String> list = new ArrayList<String>();
        for(int i=0; i < images.size(); i++){
            //System.out.println(images.get(i).getId());
            list.add(images.get(i).getRepoTags()[0]);
        }
        return list;
    }
    
    public static String[] getAllImagesIDTab () {
        List<String> list = getAllImagesID();
        String[] tab = list.toArray(new String[list.size()]);
        return tab;
    }
    
    public static String[] getAllImagesNameTab () {
        List<String> list = getAllImagesName();
        String[] tab = list.toArray(new String[list.size()]);
        return tab;
    }
    
    /**
     * Add a new image if it's not here
     */
    public static boolean addImage (String s) {
        List<String> list = getAllImagesName();
        for (String im : list){
            if(im.toLowerCase().contains(s.toLowerCase()))
                return true;
        }
        return pullImage(s);
    }
    
    /**
     * Pull an image
     */
    public static boolean pullImage (String dockerImage) {
        DockerClient dockerClient = getDockerClient();
        try {
            dockerClient.pullImageCmd(dockerImage)
                    .exec(new PullImageResultCallback())
                    .awaitCompletion();
        } catch (InterruptedException ex) {
            closeDockerClient(dockerClient);
            System.out.println(Docker.class.getName());
            System.out.println(ex);
            return false;
        }
        return true;
    }
    
    /**
     * Remove functions for images
     */
    public static boolean removeImage (String dockerImage) {
        DockerClient dockerClient = getDockerClient();
        List<Container> list = getAllArmadilloContainersListFromImage(dockerImage);
        for (Container c : list){
            if (c.getStatus().equals("paused"))
                killContainer(c.getId());
            if (c.getStatus().equals("running"))
                stopContainer(c.getId());
            if (c.getStatus().equals("exited"))
                removeContainer(c.getId());
        }
        list = getAllArmadilloContainersListFromImage(dockerImage);
        if (list.size()==0) {
            dockerClient.removeImageCmd(dockerImage).exec();
            return true;
        }
        return false;
    }
    
    public static void removeImagesList (ArrayList<String> l) {
        for (String imgName : l) {
            removeImage(imgName);
        }
    }
    
    /***************************************************************************
     * Functions for Containers
     **************************************************************************/    

    /**
     * Lists for containers
     */
    public static List<Container> getAllContainersList () {
        DockerClient dockerClient = getDockerClient();
        List<Container> containers = dockerClient.listContainersCmd()
                .withShowAll(true)
                .exec();
        List<Container> list = new ArrayList<Container>();
        for(int i=0; i < containers.size(); i++){
            String   id = containers.get(i).getId();
            if (id.matches("\\w*")){
                list.add(containers.get(i));
            }
        }
        return list;
    }
    
    public static List<Container> getAllArmadilloContainersList () {
        List<Container> containers = getAllContainersList ();
        List<Container> list = new ArrayList<Container>();
        for(int i=0; i < containers.size(); i++){
            String   id = containers.get(i).getId();
            String[] names = containers.get(i).getNames();
            for (String name : names){
                if (name.toLowerCase().contains("armadillo_wf_")){
                    list.add(containers.get(i));
                }
            }
        }
        return list;
    }
    
    public static List<String> getAllArmadilloContainersIDList () {
        List<Container> containers = getAllArmadilloContainersList ();
        List<String> list = new ArrayList<String>();
        for(int i=0; i < containers.size(); i++){
            list.add(containers.get(i).getId());
        }
        return list;
    }
    
    public static List<String> getAllArmadilloContainersNameList () {
        List<Container> containers = getAllArmadilloContainersList ();
        List<String> list = new ArrayList<String>();
        for(int i=0; i < containers.size(); i++){
            String[] names = containers.get(i).getNames();
            for (String name : names){
                if (name.toLowerCase().contains("armadillo_wf_")){
                    list.add(name);
                }
            }
        }
        return list;
    }
    
    public static List<Container> getAllArmadilloContainersListFromImage (String image) {
        List<Container> containers = getAllArmadilloContainersList ();
        List<Container> list = new ArrayList<Container>();
        for(int i=0; i < containers.size(); i++){
            String   id = containers.get(i).getId();
            String name = containers.get(i).getImage();
            if (name.toLowerCase() == null ? image.toLowerCase() == null : name.toLowerCase().equals(image.toLowerCase())){
                list.add(containers.get(i));
            }
        }
        return list;
    }
    
    public static String[] getAllContainersIDListTab() {
        List<String> list = getAllArmadilloContainersIDList();
        String[] tab = list.toArray(new String[list.size()]);
        return tab;
    }
    
    public static String[] getAllContainersNameListTab() {
        List<String> list = getAllArmadilloContainersNameList();
        String[] tab = list.toArray(new String[list.size()]);
        return tab;
    }
    
    public static List<String> getRunningContainersList () {
        DockerClient dockerClient = getDockerClient();
        List<Container> containers = dockerClient.listContainersCmd()
                .withShowAll(true)
                .withStatusFilter("running")
                .exec();
        List<String> list = new ArrayList<String>();
        for(int i=0; i < containers.size(); i++){
            String   id = containers.get(i).getId();
            String[] names = containers.get(i).getNames();
            for (String name : names){
                if (name.toLowerCase().contains("armadillo_wf_")){
                    list.add(id);
                }
            }
        }
        return list;
    }
    
    public static List<String> getExitedContainersList () {
        DockerClient dockerClient = getDockerClient();
        List<Container> containers = dockerClient.listContainersCmd()
                .withShowAll(true)
                .withStatusFilter("exited")
                .exec();
        List<String> list = new ArrayList<String>();
        for(int i=0; i < containers.size(); i++){
            String   id = containers.get(i).getId();
            String[] names = containers.get(i).getNames();
            for (String name : names){
                if (name.toLowerCase().contains("armadillo_wf_")){
                    list.add(id);
                }
            }
        }
        return list;
    }
    
    /**
     * Get container id from container name
     * If name is not here
     *      return empty string
     */
    public static String getContainerIdFromName (String name) {
        String cId = "";
        List<Container> conts = getAllArmadilloContainersList ();
        for (Container cont : conts){
            String[] names = cont.getNames();
            for (String n : names){
                if (n.toLowerCase().contains(name)){
                    return cont.getId();
                }
            }
        }
        return cId;
    }
    
    /**
     * Create a list of volumes (inside the container) from shared folders list
     */
    public static Volume[] createListVolumes (HashMap<String,String> sharedFolders) {
        Volume[] list_v = new Volume[sharedFolders.size()];
        int i =0;
        for (String k :sharedFolders.values()){
            list_v[i] = new Volume(k);
            i++;
        }
        return list_v;
    }
    
    /**
     * Create a list of binds (path in host, volumes inside the container) from shared folders list and list of volumes
     */
    public static Bind[] createListBinds (HashMap<String,String> sharedFolders, Volume[] list_v) {
        Bind[] list_bd = new Bind[sharedFolders.size()];
        int i =0;
        for (String k : sharedFolders.keySet()){
            list_bd[i] = new Bind(k,list_v[i]);
            i++;
        }
        return list_bd;
    }
    
    /**
     * Prepare the container
     */
    public static long prepareContainer(workflow_properties properties, String dockerImage, HashMap<String,String> sharedFolders) {
        long startTime = System.nanoTime();
        if (addImage(dockerImage)){
            String alea = Integer.toString(new SecureRandom().nextInt());
            alea = alea.replaceAll("-","");
            String containerName = "armadillo_wf_"+alea;
            DockerClient dockerClient = getDockerClient();
            Volume[] list_v = createListVolumes(sharedFolders);
            Bind[] list_bd = createListBinds(sharedFolders,list_v);
            CreateContainerResponse container = dockerClient.createContainerCmd(dockerImage)
                    .withVolumes(list_v)
                    .withBinds(list_bd)
                    .withTty(true)
                    .withCmd("/bin/bash")
                    .withName(containerName)
                    .exec();
            String DockerContainerID = container.getId();
            closeDockerClient(dockerClient);
            if (DockerContainerID.matches("\\w*")){
                properties.put("DockerContainerID",DockerContainerID);
            } else {
                properties.remove("DockerContainerID");
            }
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        return TimeUnit.SECONDS.convert(duration, TimeUnit.NANOSECONDS);
    }
    
    public static boolean isDockerContainerIDPresentIn(workflow_properties properties) {
        if (properties.isSet("DockerContainerID")){
            String DockerContainerID = properties.get("DockerContainerID");
            if (DockerContainerID.matches("\\w*")){
                return true;
            }            
        }
        return false;
    }
    
    
    /**
     * Start containers functions
     */
    public static void startContainer(workflow_properties properties) {
        String DockerContainerID = properties.get("DockerContainerID");
        startContainer(DockerContainerID);
    }

    public static void startContainer(String s) {
        DockerClient dockerClient = getDockerClient();
        dockerClient.startContainerCmd(s).exec();
        closeDockerClient(dockerClient);
    }

    public static void startAllArmadilloContainer(){
        List<String> l = getExitedContainersList();
        for (String s : l){
            startContainer(s);
        }
    }
    
    /**
     * Stop containers functions
     */
    public static void stopContainer(workflow_properties properties) {
        String DockerContainerID = properties.get("DockerContainerID");
        stopContainer(DockerContainerID);
    }
    
    public static void stopContainer(String s) {
        DockerClient dockerClient = getDockerClient();
        dockerClient.stopContainerCmd(s).exec();
        closeDockerClient(dockerClient);
    }

    public static void stopAllArmadilloContainer(){
        List<String> l = getRunningContainersList();
        for (String s : l){
            stopContainer(s);
        }
    }
    
    /**
     * Remove containers functions
     */
    public static long removeContainer(workflow_properties properties) {
        String DockerContainerID = properties.get("DockerContainerID");
        long startTime = System.nanoTime();
        removeContainer(DockerContainerID);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        return TimeUnit.SECONDS.convert(duration, TimeUnit.NANOSECONDS);
    }

    public static void removeContainer(String s) {
        DockerClient dockerClient = getDockerClient();
        List<Container> list = getAllArmadilloContainersList();
        for (Container c : list){
            String cId = c.getId();
            if (cId.contains(s)){
                if (c.getStatus().equals("paused")){
                    killContainer(c.getId());
                }
                    
                if (c.getStatus().equals("running") ||
                        c.getStatus().toLowerCase().contains("up")){
                    stopContainer(c.getId());
                }
                    
            }
        }
        dockerClient.removeContainerCmd(s).exec();
        closeDockerClient(dockerClient);
    }

    public static void removeAllArmadilloContainer(){
        List<String> l = getExitedContainersList();
        for (String s : l){
            removeContainer(s);
        }
    }

    public static void removeContainersListFromName (ArrayList<String> l) {
        for (String name : l) {
            String cId = getContainerIdFromName(name);
            if (!"".equals(cId))
                removeContainer(cId);
        }
    }
    
    public static void removeContainersListFromId (ArrayList<String> l) {
        for (String cId : l) {
            removeContainer(cId);
        }
    }
    
    /**
     * Kill containers functions
     */
    public static void killContainer(workflow_properties properties) {
        String DockerContainerID = properties.get("DockerContainerID");
        killContainer(DockerContainerID);
    }

    public static void killContainer(String s) {
        DockerClient dockerClient = getDockerClient();
        dockerClient.killContainerCmd(s).exec();
        closeDockerClient(dockerClient);
    }
    
    public static void killAllArmadilloContainer(){
        List<String> l = getRunningContainersList();
        for (String s : l){
            killContainer(s);
        }
    }
    
    /**
     * Copy file from host to container directory
     */
    public static void copyFileToContainer(String id, String hostFile, String containerDir){
        DockerClient dockerClient = getDockerClient();
        dockerClient.copyArchiveToContainerCmd(id)
                .withRemotePath(containerDir)
                .withHostResource(hostFile)
                .exec();
    }

    /**
     * Execute the bash file
     */
    public static void executeBashFile(workflow_properties properties) {
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        ByteArrayOutputStream stderr = new ByteArrayOutputStream();
        String DockerContainerID = properties.get("DockerContainerID");
        startContainer(properties);
        DockerClient dockerClient = getDockerClient();
        ExecCreateCmdResponse execCreateCmdResponse = dockerClient.execCreateCmd(DockerContainerID)
                .withAttachStdout(true)
                .withAttachStderr(true)
                .withCmd("/data/dockerBash.sh", "/data/dockerBash.log")
                .withUser("root")
                .exec();
        try {
            dockerClient.execStartCmd(execCreateCmdResponse.getId()).exec(
                    new ExecStartResultCallback(stdout, stderr)).awaitCompletion();
            String dockerSTD = stdout.toString()+stderr.toString();
            dockerSTD = dockerSTD.replaceAll("groupadd: group '\\w*' already exists", "");
            properties.put("DockerSTD",dockerSTD);
        } catch (InterruptedException ex) {
            System.out.println("Unable to execute the bash file in the container");
            System.out.println(ex);
        }
    }
    
    /**
     * Prepare the docker bash file
     */
    public static long prepareDockerBashFile(workflow_properties properties, String dockerCli) {
        long startTime = System.nanoTime();
        String s = Util.currentPath()+File.separator+"tmp"+File.separator+"dockerBash.sh";
        Util.CreateFile(s);
        Path file = Paths.get(s);
        
        try {
            file.toFile().setExecutable(true, false);
        }  catch(Exception ex) {
            System.out.println("Execute permission on bash file is not set!");
            System.out.println(ex);
        }
        
        String userName = Util.getOwnerJar();
        String grpName = Util.getGroupJar();
        String dockerAddUser = "useradd "+userName+"";
        String dockerAddGrp = "groupadd "+grpName+"";
        String dockerChangeOwner = "chown -R "+userName+":"+grpName+" /data/outputs/";
        String exitValue = "echo exit is -$?-";
        String timeStart= "START=$(date +%s)";
        String timeEnd  = "END=$(date +%s)";
        String timeDiff = "DIFF=$(( $END - $START ))";
        String timeEcho = "echo \"It took $DIFF seconds\"";
        
        List<String> lines = Arrays.asList("#!/bin/bash", timeStart, dockerCli, exitValue, timeEnd, timeDiff, timeEcho,dockerAddUser, dockerAddGrp, dockerChangeOwner);
        try {
            Files.write(file, lines);
        } catch (IOException ex) {
            System.out.println("Unable to create the bash file in the container");
            System.out.println(ex);
        }
        
        String DockerContainerID = properties.get("DockerContainerID");
        copyFileToContainer(DockerContainerID,s,"/data/");
        Util.deleteFile(s);
        
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        return TimeUnit.SECONDS.convert(duration, TimeUnit.NANOSECONDS);
    }

    /**
     * It's the way to share files from local to docker
     * Added by JG 2017
     * @param tab it's a tab of local path to files
     * @param doInputs it's the path of inputs in docker
     * @return a map of local folders and their mirror in docker
     */
    public static HashMap<String,String> createSharedFolders(String[] tab, String doInputs) {
        HashMap<String,String> sharedFolders = new HashMap<String,String>();
        for(int i = 0; i < tab.length; i++) {
            if (tab[i]!="") {
                String dir = Util.getParentOfFile(tab[i]);
                dir = Util.getCanonicalPath(dir);
                sharedFolders.put(dir, doInputs+i+"/");
            }
        }
        return sharedFolders;
    }
    
    public static HashMap<String,String> createSharedFolders(String[] tabPath, String[] tabID, String doInputs) {
        HashMap<String,String> sharedFolders = new HashMap<String,String>();
        for(int i = 0; i < tabPath.length; i++) {
            if (tabPath[i]!="") {
                String dir = Util.getParentOfFile(tabPath[i]);
                dir = Util.getCanonicalPath(dir);
                sharedFolders.put(dir, doInputs+tabID[i]+"/");
            }
        }
        return sharedFolders;
    }
    
    /**
     * It's the way to add all inputs and their arguments in a single string
     * Added by JG 2017
     * @param pathAndArg it's a map of paths and arg local to docker
     * @param tab it's a tab of local path to files
     * 
     * @return CanonicalPath ex: /home/user/path/to/file/
     */
    public static String createAllDockerInputs(HashMap<String,String> pathAndArg,String[] tab, String doInputs) {
        String allDockerInputs = "";
        for(int i = 0; i < tab.length; i++) {
            if (tab[i]!="") {
                String v = pathAndArg.get(tab[i]);
                String c = Util.getCanonicalPath(tab[i]);
                String name = Util.getFileNameAndExt(c);
                allDockerInputs += " "+v+" "+doInputs+i+"/"+name+" ";
            }
        }
        return allDockerInputs;
    }
    public static String createAllDockerInputs(HashMap<String,String> pathAndArg,String[] tabPath, String[] tabId, String doInputs) {
        String allDockerInputs = "";
        for(int i = 0; i < tabPath.length; i++) {
            if (tabPath[i]!="") {
                String v = pathAndArg.get(tabPath[i]);
                String c = Util.getCanonicalPath(tabPath[i]);
                String name = Util.getFileNameAndExt(c);
                allDockerInputs += " "+v+" "+doInputs+tabId[i]+"/"+name+" ";
            }
        }
        return allDockerInputs;
    }
    
    /**
     * Used in docker command line creation
     * It's the way to add all inputs and their arguments in a single string
     * Added by JG 2017
     * @param pathAndArg it's a map of paths and arg local to docker
     * @param tab it's a tab of local path to files
     * 
     * @return CanonicalPath ex: /home/user/path/to/file/
     */
    public static String createAllDockerInputs(HashMap<String,String> pathAndArg,HashMap<String,String> sharedFolders) {
        String allDockerInputs = "";
        for (String s : pathAndArg.keySet()){
            String dir   = Util.getParentOfFile(s);
            if (sharedFolders.containsKey(dir)){
                String name  = Util.getFileNameAndExt(s);
                String doDir = sharedFolders.get(dir);
                String arg   = pathAndArg.get(s);
                allDockerInputs += " "+arg+" "+doDir+""+name+" ";
            }
        }
        return allDockerInputs;
    }
}
