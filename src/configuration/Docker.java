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
import static program.RunProgram.config;
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
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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
    public static String kword = "armadillo_WF_";
    
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
        boolean b = false;
        try{
            b = isDockerHereByVersion();
        } catch (Exception e) { 
            System.out.println(Util.BRDockerNotFound());
            b = false;
        } 
        return b;
    }
    
    public static boolean isDockerHereByRootDir () {
        String s = getRootDir();
        if (s.toLowerCase().contains("docker")) {
            return true;
        }
        return false;
    }

    public static boolean isDockerHereByVersion () {
        Version v;
        try {
            v = getVersion();
        } catch (Exception e) { 
            v = null;
        }
        if (v!=null){
            if (v.getGoVersion().length() > 0)
                return true;
            if (v.getVersion().length() > 0)
                return true;
        }
        return false;
    }
    
    /**
     * Functions to get a docker instance by default and to close it
     * COULD BE GOOD to create a similar function with specific config builder for advanced docker user
     */

    public static boolean isDockerNeededInfoInConfig() {
        List<String> lines = Arrays.asList("DockerHost","DockerTlsVerify","DockerCertPath","DockerConfig");
        return Util.isListInProperties(config.properties,lines);
    }
    
    public static DockerClientConfig getDockerClientConfigWindowsSimple () {
        DockerClientConfig dcc = null;
        if (isDockerNeededInfoInConfig()){
            return null;
        } else {
            try { 
                dcc = DefaultDockerClientConfig.createDefaultConfigBuilder()
                    .withDockerHost("tcp://"+config.get("DockerHost"))
                    .withDockerTlsVerify(config.get("DockerTlsVerify"))
                    .withDockerCertPath(config.get("DockerCertPath"))
                    .withDockerConfig(config.get("DockerConfig"))
                    .build();
            } catch (Exception e) { 
                System.out.println(Util.BRDockerAPINotAccessible());
                dcc = null;
            } 
        }
        return dcc;
    }
    
    public static DockerClientConfig getDockerClientConfigUnix () {
        DockerClientConfig dcc = null;
        try {
            dcc = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .build();
        } catch (Exception e) { 
            System.out.println(Util.BRDockerAPINotAccessible());
            dcc = null;
        } 
        return dcc;
    }
    
    public static DockerClientConfig getDockerClientConfig(){
        DockerClientConfig dcc = null;
        if (dcc == null)
            if (config.getBoolean("Windows"))
                return getDockerClientConfigWindowsSimple();
            else
                return getDockerClientConfigUnix();
        return dcc;
    }

    
    public static DockerClient getDockerClient () {
        DockerClient dc;
        try {
            DockerClientConfig dcc = getDockerClientConfig();
            if (dcc!=null){
                dc = DockerClientBuilder.getInstance(dcc).build();
            }else{
                dc = null;
            }
        } catch (Exception e) { 
            System.out.println(Util.BRDockerUnableToCreateClient());
            dc = null;
        } 
        return dc;
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
    
    public static DockerClientConfig createDockerClientAdvancedConfig (workflow_properties properties) {
        String DockerHost = properties.get("DockerHost_Text");
        String DockerTlsVerify = properties.get("DockerTlsVerify_Text");
        String DockerCertPath = properties.get("DockerCertPath");
        String DockerRegistryUserName = properties.get("DockerRegistryUserName_Text");
        String DockerRegistryPassword = properties.get("DockerRegistryPassword_Text");
        String DockerRegistryEmail = properties.get("DockerRegistryEmail_Text");
        String DockerRegistryUrl = properties.get("DockerRegistryUrl_Text");
         
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
            .withDockerHost(DockerHost)
            .withDockerTlsVerify(DockerTlsVerify)
            .withDockerCertPath(DockerCertPath)
            .withRegistryUsername(DockerRegistryUserName)
            .withRegistryPassword(DockerRegistryPassword)
            .withRegistryEmail(DockerRegistryEmail)
            .withRegistryUrl(DockerRegistryUrl)
            .build();
        return config;
    }
    
    /*
     * Functions to get info from Docker
     */    

    public static Version getVersion (){
        DockerClient dockerClient = getDockerClient();
        Version version = null;
        if (dockerClient!=null){
            version = dockerClient.versionCmd().exec();
            closeDockerClient(dockerClient);
        }
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
                if (name.toLowerCase().contains(kword)){
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
                if (name.toLowerCase().contains(kword)){
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
                if (name.toLowerCase().contains(kword)){
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
                if (name.toLowerCase().contains(kword)){
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
     * Check if requiered fields are in default properties
     */
    public static boolean areDockerVariablesNotInProperties (workflow_properties properties) {
        List<String> lines = Arrays.asList("DockerImage","ExecutableDocker","DockerInputs","DockerOutputs");
        return Util.isListInProperties(properties,lines);
    }
    
    /**
     * Prepare the container
     */
    public static long prepareContainer(workflow_properties properties, HashMap<String,String> sharedFolders) {
        long startTime = System.nanoTime();
        String dockerImage = properties.get("DockerImage");
        if(addImage(dockerImage)){
            String alea = Integer.toString(new SecureRandom().nextInt());
            alea = alea.replaceAll("-","");
            String containerName = kword+alea;
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
                // ADD SHARED FOLDERS TO BE SEEN IN dockerEditorProgram
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
        long startTime = System.nanoTime();
        if (properties.isSet("DockerContainerID")){
            String DockerContainerID = properties.get("DockerContainerID");
            //removeContainer(DockerContainerID);
        }
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
        String[] cmd = {"/bin/sh","-c","/data/dockerBash.sh"};
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        ByteArrayOutputStream stderr = new ByteArrayOutputStream();
        String DockerContainerID = properties.get("DockerContainerID");
        startContainer(properties);
        DockerClient dockerClient = getDockerClient();
        ExecCreateCmdResponse execCreateCmdResponse = dockerClient.execCreateCmd(DockerContainerID)
                .withAttachStdout(true)
                .withAttachStderr(true)
                .withCmd(cmd)
                .withUser("root")
                .exec();
        try {
            dockerClient.execStartCmd(execCreateCmdResponse.getId())
                    .exec(new ExecStartResultCallback(stdout, stderr))
                    .awaitCompletion();
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
        if (Docker.isDockerHere()){
            String userName = "";
            String grpName = "";
            String dockerAddUser = "";
            String dockerAddGrp = "";
            String dockerChangeOwner = "";
            String dockerBash = "";
            String exitValue = "echo exit is -$?-";
            String timeStart= "";
            String timeEnd  = "";
            String timeDiff = "";
            String timeEcho = "";
            if (!config.getBoolean("Windows")){
                userName = Util.getOwnerJar();
                grpName = Util.getGroupJar();
                dockerAddGrp = "groupadd "+grpName+"";
                dockerAddUser = "useradd "+userName+"";
                dockerChangeOwner = "chown -R "+userName+":"+grpName+" /data/outputs/";
                dockerBash = "#!/bin/bash";
                exitValue = "echo exit is -$?-";
                timeStart= "START=$(date +%s)";
                timeEnd  = "END=$(date +%s)";
                timeDiff = "DIFF=$(( $END - $START ))";
                timeEcho = "echo \"It took $DIFF seconds\"";
            } else {
                dockerBash = "#/bin/bash";
            }

            String eol = "\n";
            String cli = dockerBash+eol+timeStart+eol+dockerCli+eol+exitValue+eol+timeEnd+eol+timeDiff+eol+timeEcho+eol+dockerAddUser+eol+dockerAddGrp+eol+dockerChangeOwner;

            String s = Util.currentPath()+File.separator+"tmp"+File.separator+"dockerBash.sh";
            Util.CreateFile(s);
            Path path = Paths.get(s);
            try {
                path.toFile().setExecutable(true, false);
            }  catch(Exception ex) {
                System.out.println("Execute permission on bash file is not set!");
                System.out.println(ex);
            }
            try {
                File file = path.toFile();
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF-8"));
                out.write(cli);
                out.close();
            } catch (IOException e) {
                System.out.println("Exception "+e);
            }
            String DockerContainerID = properties.get("DockerContainerID");
            properties.put("DockerCommandLine",dockerCli);
            copyFileToContainer(DockerContainerID,s,"/data/");
            Util.deleteFile(s);
        }
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
                sharedFolders = addInSharedFolder(sharedFolders,tabPath[i],doInputs+tabID[i]+"/");
            }
        }
        return sharedFolders;
    }
    
    public static HashMap<String,String> addInSharedFolder(HashMap<String,String> sf, String outputPath, String doOutputs) {
        String dir = Util.getCanonicalPath(outputPath);
        dir = Util.getParentOfFile(dir);
        dir = Util.getCanonicalPath(dir);
        dir = cleanWindowsPath4DockerVolume(dir);
        sf.put(dir, doOutputs);
        return sf;
    }
    
    public static String cleanWindowsPath4DockerVolume(String path){
        if (config.getBoolean("Windows")){
            path = path.substring(0, 1).toLowerCase() + path.substring(1);
            path = path.replaceAll("\\\\", "/");
            path = path.replaceFirst("(\\w):","/$1".toLowerCase());
        }
        return path;
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
