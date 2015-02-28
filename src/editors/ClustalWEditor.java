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

package editors;


import workflows.workflow_properties;
import configuration.Config;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import editor.EditorInterface;
import program.*;
import workflows.armadillo_workflow;
import workflows.workflow_properties_dictionnary;

/**
 * Editor of the object properties in the Main Workflow
 * Note: Only called if object doesnt have a Custum Editor
 * @author Etienne Lord
 * @since July 2009
 */
public class ClustalWEditor extends javax.swing.JDialog implements EditorInterface {

    ////////////////////////////////////////////////////////////////////////////
    /// VARIABLES

    Config config=new Config();
    //ConnectorInfoBox connectorinfobox;
    workflow_properties_dictionnary dict=new workflow_properties_dictionnary();
    String selected="";             // Selected properties
    Frame frame;
    workflow_properties properties;
    armadillo_workflow parent_workflow;
   
    ////////////////////////////////////////////////////////////////////////////
    /// CONSTANT

    public final String defaultNameString=" Name";

    /////////////////////////////////////////////////////////////////////////
    /// Default Options
  

    /** Creates new form propertiesJDialog */
    public ClustalWEditor(java.awt.Frame parent, armadillo_workflow parent_workflow) {
        super(parent, false);
        this.parent_workflow=parent_workflow;        
        frame=parent;
    }
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        NamejTextField = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        Gop_jComboBox = new javax.swing.JComboBox();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        Gep_jComboBox = new javax.swing.JComboBox();
        Tgpt_jComboBox = new javax.swing.JComboBox();
        jLabel22 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        kaling_MoltypejComboBox = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        ClosejButton = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        jLabel2.setText("jLabel2");

        setTitle("Properties");

        jTabbedPane1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jTabbedPane1ComponentShown(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Name");

        NamejTextField.setText(" ");

        jButton4.setText("Rename");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NamejTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(NamejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton4))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel7.setToolTipText("");

        Gop_jComboBox.setEditable(true);
        Gop_jComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "default", "100", "50", "25", "5", "2", "1" }));
        Gop_jComboBox.setToolTipText("Default Nucleic Acid: 80.0, Protein: 11.0");
        Gop_jComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Gop_jComboBoxActionPerformed(evt);
            }
        });

        jLabel20.setText("Gap Opening Penalty (0.0-100.0) : ");

        jLabel21.setText("Gap Extension Penalty (0.0-10.0) :");

        Gep_jComboBox.setEditable(true);
        Gep_jComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "default", "0.05", "0.1", "0.5", "1.0", "2.0", "5.0", "7.5", "10.0" }));
        Gep_jComboBox.setToolTipText("Default Nucleic Acid: 3.0, Protein: 0.85");
        Gep_jComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Gep_jComboBoxActionPerformed(evt);
            }
        });

        Tgpt_jComboBox.setEditable(true);
        Tgpt_jComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "default", "1.0", "0.95", "0.90", "0.85", "0.80", "0.75", "0.70", "0.65", "0.60", "0.55", "0.50", "0.45", "0.40", "0.35", "0.30", "0.25", "0.20", "0.15", "0.10", " " }));
        Tgpt_jComboBox.setToolTipText("Default: Nucleic Acid: 3.0, Protein: 0.45");
        Tgpt_jComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Tgpt_jComboBoxActionPerformed(evt);
            }
        });

        jLabel22.setText("DNA Transition Weight (0.0-1.0) :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(Tgpt_jComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Gep_jComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Gop_jComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(131, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(Gop_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(Gep_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Tgpt_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(71, 71, 71))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        kaling_MoltypejComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nucleic Acid (RNA/DNA)", "Protein" }));
        kaling_MoltypejComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kaling_MoltypejComboBoxActionPerformed(evt);
            }
        });

        jLabel3.setText("Type :");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(kaling_MoltypejComboBox, 0, 391, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel3)
                .addComponent(kaling_MoltypejComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        ClosejButton.setText("<html><b>Close</b></html>");
        ClosejButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClosejButtonActionPerformed(evt);
            }
        });

        jButton5.setText("Run");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton8.setText("Stop");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton7.setText("Reset default values");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ClosejButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ClosejButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5)
                    .addComponent(jButton8)
                    .addComponent(jButton7))
                .addContainerGap())
        );

        jTabbedPane1.addTab("ClustalW", jPanel9);

        jButton6.setText("?");
        jButton6.setToolTipText("Help / Informations");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1)
                    .addComponent(jButton6))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ClosejButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClosejButtonActionPerformed
        this.setVisible(false);
}//GEN-LAST:event_ClosejButtonActionPerformed

    private void jTabbedPane1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTabbedPane1ComponentShown
      
}//GEN-LAST:event_jTabbedPane1ComponentShown

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       properties.put("Name", this.NamejTextField.getText());
       parent_workflow.updateCurrentWorkflow(properties);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
         if(properties.isSet("ClassName")) {
           this.parent_workflow.workflow.updateDependance();
           programs prog=new programs(parent_workflow.workbox.getCurrentWorkflows());
           prog.Run(properties);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        properties.put("Status", Config.status_nothing);
        properties.killThread();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       HelpEditor help = new HelpEditor(this.frame, false, properties);
        help.setVisible(true);
}//GEN-LAST:event_jButton6ActionPerformed

    private void Gop_jComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Gop_jComboBoxActionPerformed
        String item=(String)this.Gop_jComboBox.getSelectedItem();
        if (item.equals("default")) {
            properties.remove("gop");
        } else properties.put("gop",item);
}//GEN-LAST:event_Gop_jComboBoxActionPerformed

    private void Gep_jComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Gep_jComboBoxActionPerformed
        String item=(String)this.Gep_jComboBox.getSelectedItem();
        if (item.equals("default")) {
            properties.remove("gep");
        } else properties.put("gep",item);
}//GEN-LAST:event_Gep_jComboBoxActionPerformed

    private void Tgpt_jComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Tgpt_jComboBoxActionPerformed
        String item=(String)this.Tgpt_jComboBox.getSelectedItem();
        if (item.equals("default")) {
            properties.remove("transition_weight");
        } else properties.put("transition_weight",item);
}//GEN-LAST:event_Tgpt_jComboBoxActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        properties.load();             //--reload current properties from file
        this.setProperties(properties);//--Update current field
}//GEN-LAST:event_jButton7ActionPerformed

    private void kaling_MoltypejComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kaling_MoltypejComboBoxActionPerformed
        String type=(String)this.kaling_MoltypejComboBox.getSelectedItem();
        type=type.substring(0,1); //--Get the first letter
        properties.put("moltype",type);
}//GEN-LAST:event_kaling_MoltypejComboBoxActionPerformed

   
     
    /**
     * This set the Properties
     * @param properties
     */
    public void setProperties(workflow_properties properties) {
        this.properties=properties;
        this.properties=properties;
        setTitle(properties.getName());       
        if (properties.isSet("gop")) {
            this.Gop_jComboBox.setSelectedItem(properties.get("gop"));
       
        }
         if (properties.isSet("gep")) {
            this.Gep_jComboBox.setSelectedItem(properties.get("gep"));
        }

          if (properties.isSet("transition_weight")) {
            this.Tgpt_jComboBox.setSelectedItem(properties.get("transition_weight"));
        }

         if (properties.isSet("moltype")) {
            String moltype=properties.get("moltype");
            if (moltype.equals("P")) this.kaling_MoltypejComboBox.setSelectedIndex(1);
            if (moltype.equals("N")) this.kaling_MoltypejComboBox.setSelectedIndex(0);
        }
      
        setTitle(properties.getName());
        //if (properties.isSet("Description")) this.Notice.setText(properties.get("Description"));
        this.NamejTextField.setText(properties.getName());
    }

    ///////////////////////////////////////////////////////////////////////////
    /// DISPLAY MAIN FUNCTION

    public void display(workflow_properties properties) {
        this.properties=properties;
        initComponents();
        setIconImage(Config.image);

        // Set position 
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension d = getSize();
        setLocation((screenSize.width-d.width)/2,
					(screenSize.height-d.height)/2);
        this.setProperties(properties);
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
    private javax.swing.JComboBox Gep_jComboBox;
    private javax.swing.JComboBox Gop_jComboBox;
    private javax.swing.JTextField NamejTextField;
    private javax.swing.JComboBox Tgpt_jComboBox;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox kaling_MoltypejComboBox;
    // End of variables declaration//GEN-END:variables



}
