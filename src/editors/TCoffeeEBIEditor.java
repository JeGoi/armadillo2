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
import java.io.File;
import editor.EditorInterface;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import program.*;
import workflows.armadillo_workflow;
import workflows.workflow_properties_dictionnary;

/**
 * Editor of the object properties in the Main Workflow
 * Note: Only called if object doesnt have a Custum Editor
 * @author Etienne Lord
 * @since July 2009
 */
public class TCoffeeEBIEditor extends javax.swing.JDialog implements EditorInterface {

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
    
   
    

    /** Creates new form propertiesJDialog */
    public TCoffeeEBIEditor(java.awt.Frame parent, armadillo_workflow parent_workflow) {
        super(parent, false);
        this.parent_workflow=parent_workflow;
        //--Set variables and init
        frame=parent;
        //connectorinfobox=new ConnectorInfoBox(parent); //--Used to display Connector info
        //--Initialize component
       
    }

    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        NamejTextField = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        clustalMultiAln_Button = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        matrix_jComboBox = new javax.swing.JComboBox();
        JobIdjTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ClosejButton = new javax.swing.JButton();
        run_jButton = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        Notice = new javax.swing.JTextArea();

        jTextField1.setText("jTextField1");

        jTextField2.setText("jTextField2");

        setTitle("Properties");
        setResizable(false);

        jTabbedPane1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jTabbedPane1ComponentShown(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Name");

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
                .addComponent(NamejTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
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

        buttonGroup1.add(clustalMultiAln_Button);
        clustalMultiAln_Button.setSelected(true);
        clustalMultiAln_Button.setText("Complete Multiple Alignement (Slow and accurate)");
        clustalMultiAln_Button.setToolTipText("<html>\nAll sequences are compared <br/>\nto each other\n</html>");
        clustalMultiAln_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clustalMultiAln_ButtonActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Specific options"));

        jLabel5.setText("Matrix");

        matrix_jComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "default", "blosum", "pam" }));
        matrix_jComboBox.setToolTipText("Nucleic Acid: DNA Identity matrix, Protein: Gonnet 250. (PAM350 or BLOSUM30)");
        matrix_jComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matrix_jComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(14, 14, 14)
                .addComponent(matrix_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel5)
                .addComponent(matrix_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        JobIdjTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JobIdjTextFieldFocusLost(evt);
            }
        });

        jLabel4.setText("Download a previous Job (JobID) :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(clustalMultiAln_Button)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE))
                    .addComponent(jLabel4)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(JobIdjTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(clustalMultiAln_Button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JobIdjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        ClosejButton.setText("<html><b>Close<b></html>");
        ClosejButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClosejButtonActionPerformed(evt);
            }
        });

        run_jButton.setText("Run");
        run_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                run_jButtonActionPerformed(evt);
            }
        });

        jButton8.setText("Stop");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton6.setText("Reset default values");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(run_jButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ClosejButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ClosejButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(run_jButton)
                    .addComponent(jButton8)
                    .addComponent(jButton6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("T-Coffee Web EBI", jPanel9);

        jButton7.setText("?");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        Notice.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        Notice.setColumns(20);
        Notice.setEditable(false);
        Notice.setFont(new java.awt.Font("Tahoma", 0, 10));
        Notice.setLineWrap(true);
        Notice.setRows(5);
        Notice.setText("No information to display for this progam at the moment.");
        Notice.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Notice, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(Notice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ClosejButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClosejButtonActionPerformed
        //-- 2. Close dialog
        this.setVisible(false);
}//GEN-LAST:event_ClosejButtonActionPerformed

    private void jTabbedPane1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTabbedPane1ComponentShown
      
}//GEN-LAST:event_jTabbedPane1ComponentShown

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       properties.put("Name", this.NamejTextField.getText());

       parent_workflow.updateCurrentWorkflow(properties);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void clustalMultiAln_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clustalMultiAln_ButtonActionPerformed
       
}//GEN-LAST:event_clustalMultiAln_ButtonActionPerformed

    private void run_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_run_jButtonActionPerformed
        if(properties.isSet("ClassName")) {
           this.parent_workflow.workflow.updateDependance();
           programs prog=new programs(parent_workflow.workbox.getCurrentWorkflows());
           prog.Run(properties);
        }
}//GEN-LAST:event_run_jButtonActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        properties.put("Status", Config.status_nothing);
        properties.killThread();
}//GEN-LAST:event_jButton8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        properties.load();             //--reload current properties from file
        this.setProperties(properties);//--Update current field
}//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        HelpEditor help = new HelpEditor(this.frame, false, properties.get("Name"), properties.get("help"));
        help.setVisible(true);
}//GEN-LAST:event_jButton7ActionPerformed

    private void JobIdjTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JobIdjTextFieldFocusLost
        String JobId=this.JobIdjTextField.getText();
        if (JobId.isEmpty()) {
            properties.remove("JobId");
        } else properties.put("JobId",JobId);
}//GEN-LAST:event_JobIdjTextFieldFocusLost

    private void matrix_jComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matrix_jComboBoxActionPerformed
        String item=(String)this.matrix_jComboBox.getSelectedItem();
       if (item.equals("default")) {
           properties.remove("matrix");
       } else properties.put("matrix",item);
}//GEN-LAST:event_matrix_jComboBoxActionPerformed

  
    /**
     * This set the Properties
     * @param properties
     */
    public void setProperties(workflow_properties properties) {
        this.properties=properties;
        setTitle(properties.getName());
         if (properties.isSet("Description")) this.Notice.setText(properties.get("Description"));
        this.NamejTextField.setText(properties.getName());

        if (properties.isSet("matrix")) {
            this.matrix_jComboBox.setSelectedItem(properties.get("matrix"));
        }

        if (properties.isSet("JobId")) {
            this.JobIdjTextField.setText(properties.get("JobId"));
        } else this.JobIdjTextField.setText("");

    }

      

  

    ///////////////////////////////////////////////////////////////////////////
    /// DISPLAY MAIN FUNCTION

    public void display(workflow_properties properties) {
        initComponents();
        setIconImage(Config.image);
        setProperties(properties);
        // Set position 
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension d = getSize();
        setLocation((screenSize.width-d.width)/2,
					(screenSize.height-d.height)/2);
        this.setAlwaysOnTop(true);
        this.setProperties(properties);
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
    private javax.swing.JTextField JobIdjTextField;
    private javax.swing.JTextField NamejTextField;
    private javax.swing.JTextArea Notice;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JRadioButton clustalMultiAln_Button;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JComboBox matrix_jComboBox;
    private javax.swing.JButton run_jButton;
    // End of variables declaration//GEN-END:variables



}
