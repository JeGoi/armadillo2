/*
 *  Armadillo Workflow Platform v2.0
 *  A simple pipeline system for phylogenetic analysis
 *  
 *  Copyright (C) 2009-2014  Etienne Lord, Mickael Leclercq
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

package editors;


import biologic.seqclasses.InformationJDialog;
import biologic.*;
import configuration.BlastDBFilter;
import javax.swing.SwingWorker;
import workflows.workflow_properties;
import configuration.Config;
import configuration.Docker;
import configuration.Util;
import database.databaseFunction;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import editor.EditorInterface;
import editor.EditorInterface;
import editors.HelpEditor;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import tools.Toolbox;
import workflows.armadillo_workflow;
import workflows.workflow_properties_dictionnary;

/**
 * Docker Editor
 * Note: Called when it's necessary
 * @author JG
 * @since Jan 2016
 */
public class dockerEditor extends javax.swing.JDialog implements EditorInterface {

    ////////////////////////////////////////////////////////////////////////////
    /// VARIABLES

    Config config=new Config();
    //ConnectorInfoBox connectorinfobox;
    workflow_properties_dictionnary dict=new workflow_properties_dictionnary();
    String selected="";             // Selected properties
    Frame frame;
    workflow_properties properties;
    armadillo_workflow parent_workflow;
    InformationJDialog loading;                        //Loading sequence JDialog
    databaseFunction df=new databaseFunction();
    
    ////////////////////////////////////////////////////////////////////////////
    /// CONSTANT

    public final String defaultNameString="Docker";
    public boolean changed=false;
    
    /** Creates new form propertiesJDialog */
    public dockerEditor(java.awt.Frame parent, boolean modal,workflow_properties properties) {
        super(parent, modal);
        this.frame=parent;
        this.properties=properties;
        display(properties);
        updateLists();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        D_AC_StopSelectedCont = new javax.swing.JButton();
        DAC_StopAllCont_button = new javax.swing.JButton();
        D_UI_deleteSelectedImages = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        D_UI_list = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        D_AC_list = new javax.swing.JList();
        jButton2 = new javax.swing.JButton();
        ClosejButton = new javax.swing.JButton();
        Update_docker_button = new javax.swing.JButton();
        HelpjButton = new javax.swing.JButton();

        setTitle("Properties");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jTabbedPane1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jTabbedPane1ComponentShown(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel3.setText("Docker uploaded images (Name,Tag,ImageID)");

        jLabel8.setText("Docker active containers (ContainerID,Name,Image)");

        D_AC_StopSelectedCont.setText("Stop selected container(s)");
        D_AC_StopSelectedCont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D_AC_StopSelectedContActionPerformed(evt);
            }
        });

        DAC_StopAllCont_button.setText("Stop all containers");
        DAC_StopAllCont_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DAC_StopAllCont_buttonActionPerformed(evt);
            }
        });

        D_UI_deleteSelectedImages.setText("Delete Selected image(s)");
        D_UI_deleteSelectedImages.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D_UI_deleteSelectedImagesActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(D_UI_list);

        jScrollPane2.setViewportView(D_AC_list);

        jButton2.setText("Install Docker (need SU)");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(D_UI_deleteSelectedImages)
                            .addComponent(jButton2)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(D_AC_StopSelectedCont)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(DAC_StopAllCont_button)))
                                    .addGap(8, 8, 8))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(D_UI_deleteSelectedImages)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(D_AC_StopSelectedCont)
                    .addComponent(DAC_StopAllCont_button))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ClosejButton.setText("Close");
        ClosejButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClosejButtonActionPerformed(evt);
            }
        });

        Update_docker_button.setText("Update docker");
        Update_docker_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Update_docker_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Update_docker_button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ClosejButton)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ClosejButton)
                    .addComponent(Update_docker_button))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Docker Infos", jPanel9);

        HelpjButton.setText("?");
        HelpjButton.setToolTipText("Help / Informations");
        HelpjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HelpjButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(HelpjButton))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(HelpjButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTabbedPane1ComponentShown
      
}//GEN-LAST:event_jTabbedPane1ComponentShown

    private void HelpjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HelpjButtonActionPerformed
        HelpEditor help = new HelpEditor(this.frame, false, properties);
        help.setVisible(true);
}//GEN-LAST:event_HelpjButtonActionPerformed

    private void ClosejButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClosejButtonActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_ClosejButtonActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        this.setVisible(false);
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.setVisible(false);
    }//GEN-LAST:event_formWindowClosing

    private void D_AC_StopSelectedContActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D_AC_StopSelectedContActionPerformed
        // TODO add your handling code here:
        int[] tabInt = this.D_AC_list.getSelectedIndices();
        if (tabInt.length > 0) {
            ArrayList<String> cont = new ArrayList<String>();
            for (int i:tabInt){
                String s = D_AC_list.getModel().getElementAt(i).toString();
                s = s.replaceAll("^(\\w+), \\w+, .*","$1");
                cont.add(s);
            }
            if (Docker.cleanContainers(cont))
                System.out.print("None or Few containers hadn't been stopped. Docker Editor will be updated.");
            updateLists();
        }
    }//GEN-LAST:event_D_AC_StopSelectedContActionPerformed

    private void DAC_StopAllCont_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DAC_StopAllCont_buttonActionPerformed
        // TODO add your handling code here:
        ArrayList<String> cont = Docker.getAllContainersID();
        if (!Docker.cleanContainers(cont))
            System.out.print("None or Few containers hadn't been stopped. Docker Editor will be updated.");
        updateLists();
    }//GEN-LAST:event_DAC_StopAllCont_buttonActionPerformed

    private void D_UI_deleteSelectedImagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D_UI_deleteSelectedImagesActionPerformed
        // TODO add your handling code here:
        int[] tabInt = this.D_UI_list.getSelectedIndices();
        if (tabInt.length > 0) {
            ArrayList<String> l = new ArrayList<String>();
            for (int i:tabInt){
                String s = this.D_UI_list.getModel().getElementAt(i).toString();
                String[] tp = s.split(", ");
                l.add(tp[0]);
            }
            Docker.removeImages(l);
            updateLists();
        }
    }//GEN-LAST:event_D_UI_deleteSelectedImagesActionPerformed
    
    private void Update_docker_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Update_docker_buttonActionPerformed
        // TODO add your handling code here:
        updateLists();
    }//GEN-LAST:event_Update_docker_buttonActionPerformed

    /**
     * Set Lists for D_UI_list or D_AC_list and clean inactives containers
     */
    private void updateLists() {
        D_UI_list.setListData(getDockerLists(D_UI_list));
        D_AC_list.setListData(getDockerLists(D_AC_list));
    }
    
    /**
     * Set Lists for D_UI_list or D_AC_list and clean inactives containers
     */
    private String[] getDockerLists(javax.swing.JList list) {
        ArrayList<String> tplist = new ArrayList<String>(); 
        if (Docker.isDockerHere()) {
            list.removeAll();
            
            ArrayList<String> str = new ArrayList<String>();
            if (list.equals(D_UI_list))      str = Docker.getImages();
            else if (list.equals(D_AC_list)) {
                Docker.cleanInactiveContainers();
                str = Docker.getActivesContainers();
            }
            
            if (str == null || str.isEmpty()) {
                if (list.equals(D_UI_list))      tplist.add("No repositry (images) founded");
                else if (list.equals(D_AC_list)) tplist.add("No Active Container founded");
            } else {
                for (int i=0;i<str.size();i++) {
                    if (list.equals(D_UI_list)) {
                        String[] temp = str.get(i).split("\\s+");
                        tplist.add(temp[0]+", "+temp[1]+", "+temp[2]);
                    } else {
                        tplist.add(str.get(i));
                    }
                }
            }
        } else {
            tplist.add("Docker not found, Please Install it.");
        }
        String[] rep = new String[tplist.size()];
        rep = tplist.toArray(rep);
        return rep;
    }

    ///////////////////////////////////////////////////////////////////////////
    /// DISPLAY MAIN FUNCTION

    public void display(workflow_properties properties) {
        this.properties=properties;
        initComponents();
        setIconImage(Config.image);
        setTitle(properties.getName());
        changed=false; //--For the serialization if needed
        
        // Set position 
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension d = getSize();
        setLocation((screenSize.width-d.width),
					(screenSize.height-d.height));
        this.setAlwaysOnTop(true);
        this.setVisible(true);
    }
    
    public void saveImage(String filename) {
        BufferedImage bi;
        try {
            bi = new Robot().createScreenCapture(this.getBounds()); 
            ImageIO.write(bi, "png", new File(filename));
            this.setVisible(false);
        } catch (Exception ex) {
           Config.log("Unable to save "+filename+" dialog image");
        }            
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ClosejButton;
    private javax.swing.JButton DAC_StopAllCont_button;
    private javax.swing.JButton D_AC_StopSelectedCont;
    private javax.swing.JList D_AC_list;
    private javax.swing.JButton D_UI_deleteSelectedImages;
    private javax.swing.JList D_UI_list;
    private javax.swing.JButton HelpjButton;
    private javax.swing.JButton Update_docker_button;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables



}