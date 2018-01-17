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


package tools;

import configuration.Util;
import database.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import program.RunProgram;
import workflows.workflow_properties;

/**
 *
 * @author Etienne Lord, Mickael Leclercq
 */
public class ToolboxMutableTreeNode extends DefaultMutableTreeNode implements Comparable {

    workflow_properties properties;
    private boolean programFound=false; //--Flag to indicate if the program is found

    // Final leaf
    public ToolboxMutableTreeNode(workflow_properties properties) {
        super(properties.getName());//useless?
        this.properties=properties;
        programFound   =getProgramPresence(properties);
    }
    
    // Node parent
    public ToolboxMutableTreeNode(String categorie) {
        workflow_properties newnode_properties=new workflow_properties();
        newnode_properties.put("Type",categorie);
        newnode_properties.setName(categorie);
        this.properties=newnode_properties;
        programFound=getProgramPresence(newnode_properties);
    }
    
    private boolean getProgramPresence(workflow_properties properties){
        if (!properties.isSet("ObjectType")) {
            return true;
        } else {    
            return RunProgram.isExecutableFound(properties);
        }
    }
    
    @Override
    public String toString() {
        return properties.toString();
    }
   
    public int compareTo(Object o) {
        try {
            ToolboxMutableTreeNode N=(ToolboxMutableTreeNode)o;
            return this.properties.get("Type").compareTo(N.properties.get("Type"));
        } catch(Exception e) {e.printStackTrace();return 0;}
    }
    
    public int subTreeDepth(String categorie){
        String findStr = "<>";
        int lastIndex = 0;
        int count = 0;
        while(lastIndex != -1){
            lastIndex = categorie.indexOf(findStr,lastIndex);
            if(lastIndex != -1){
                count ++;
                lastIndex += findStr.length();
            }
        }
        return count;
    }
    
    public ToolboxMutableTreeNode add(ToolboxMutableTreeNode newChild) {
        ToolboxMutableTreeNode tmtn = this.inChildren(newChild);
        if(tmtn==null){
            this.insert(newChild, getChildCount());
            return newChild;
        } else
            return tmtn;
    }
    
    public ToolboxMutableTreeNode inChildren(ToolboxMutableTreeNode newNode) {
        Enumeration<ToolboxMutableTreeNode> en=this.children();
        while (en.hasMoreElements()){
            ToolboxMutableTreeNode j = en.nextElement();
            if (j.getName().equals(newNode.getName()))
                return j;
        }
        return null;
    }
    
    public void reOrderChildren() {
        int n = this.getChildCount();
        List<String> childrenName = new ArrayList<String>();
        Hashtable<String,ToolboxMutableTreeNode> childrenTable = new Hashtable<String,ToolboxMutableTreeNode>();
        for (int i = 0; i < n; i++) {
            ToolboxMutableTreeNode newNode = cloneNode((ToolboxMutableTreeNode) this.getChildAt(i));
            if (newNode.getChildCount()>0){
                newNode.reOrderChildren();
            }
            childrenName.add(newNode.getName());
            childrenTable.put(newNode.getName(),newNode);
        }
        Collections.sort(childrenName);
        this.removeAllChildren();
        for (String s: childrenName) {
            this.add(childrenTable.get(s));
        }
    }
    
    public ToolboxMutableTreeNode cloneNode(ToolboxMutableTreeNode node) {
        ToolboxMutableTreeNode newNode = new ToolboxMutableTreeNode(node.getProperties());
        for(int iChildren=node.getChildCount(), i=0; i<iChildren; i++) {
            newNode.add((MutableTreeNode)cloneNode((ToolboxMutableTreeNode)node.getChildAt(i) ) );
        }
        return newNode;
    }
    
    @Override
    public boolean equals(Object obj) {
        try {
            if (obj instanceof ToolboxMutableTreeNode) {
                ToolboxMutableTreeNode N=(ToolboxMutableTreeNode)obj;
                //Config.log(this.properties.get("Type")+" "+N.properties.get("Type")+"->"+(this.properties.get("Type").equals(N.properties.get("Type"))));
                return this.properties.get("Type").equals(N.properties.get("Type"));
            } else {
                return false;
            }
        }catch(Exception e) {return false;}
    }

    /**
     * @return the programFound
     */
    public boolean isProgramFound() {
        return programFound;
    }

    /**
     * @param programFound the programFound to set
     */
    public void setProgramFound(boolean programFound) {
        this.programFound = programFound;
    }

    /**
     * @return the programFound
     */
    public String getName(){
        return this.properties.getName();
    }

    /**
     * @param programFound the programFound to set
     */
    public void setName(String nodeName) {
        this.properties.setName(nodeName);
    }

    
    /**
     * Get the tooltip associated with this program
     * @return
     */
    public String getTooltip() {
        //--Note(if the program is not found, change the tooltip
        if (!isProgramFound()) {
            return "<html>"+
                    this.properties.getTooltip()+"<br>"+
                    "<b>Warning. Executable not found for this tool.</b>"+
                    "</html>";
            
        } else
            //--default
            return properties.getTooltip();
    }

    /**
     * Return this properties
     * @return 
     */
    public workflow_properties getProperties() {
        return this.properties;
    }
    
}
