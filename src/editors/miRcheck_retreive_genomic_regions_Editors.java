/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package editors;

import configuration.Config;
import editor.EditorInterface;
import configuration.Util;
import editor.clusterEditorProgram;
import editor.dockerEditorProgram;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import program.*;
import workflows.armadillo_workflow;
import workflows.workflow_properties;
import workflows.workflow_properties_dictionnary;

/**
 *
 * @author Jérémy Goimard
 * 
 */
public class miRcheck_retreive_genomic_regions_Editors extends javax.swing.JDialog implements EditorInterface  {
    
    /**
     * Creates new form miRchekEditors
     */
    Config config=new Config();
    //ConnectorInfoBox connectorinfobox;
    workflow_properties_dictionnary dict=new workflow_properties_dictionnary();
    String selected="";             // Selected properties
    Frame frame;
    workflow_properties properties;
    armadillo_workflow parent_workflow;
    
    public final String defaultNameString=" Name";
    static final boolean default_map=true;
    
    public miRcheck_retreive_genomic_regions_Editors(java.awt.Frame parent, armadillo_workflow parent_workflow) {
        super(parent, false);
        this.parent_workflow=parent_workflow;
        //--Set variables and init
        frame=parent;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        miRcheck = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        Options_panel = new javax.swing.JTabbedPane();
        Mircheck_panel = new javax.swing.JPanel();
        C_win_box = new javax.swing.JCheckBox();
        C_win_value = new javax.swing.JSpinner();
        default_options_jbutton = new javax.swing.JRadioButton();
        advanced_options_jbutton = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        name_jTextField1 = new javax.swing.JTextField();
        reset_jButton = new javax.swing.JButton();
        stop_jButton = new javax.swing.JButton();
        run_jButton = new javax.swing.JButton();
        how_jButton = new javax.swing.JButton();
        close_jButton = new javax.swing.JButton();
        docker_jButton = new javax.swing.JButton();
        ClusterProgramButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        miRcheck.setPreferredSize(new java.awt.Dimension(328, 239));
        miRcheck.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                miRcheckComponentShown(evt);
            }
        });

        Options_panel.setPreferredSize(new java.awt.Dimension(341, 413));

        C_win_box.setText("win");
        C_win_box.setName("C_win_box"); // NOI18N
        C_win_box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C_win_boxActionPerformed(evt);
            }
        });

        C_win_value.setModel(new javax.swing.SpinnerNumberModel(-1, -1, null, 1));
        C_win_value.setMaximumSize(new java.awt.Dimension(118, 28));
        C_win_value.setMinimumSize(new java.awt.Dimension(118, 28));
        C_win_value.setName("C_win_value"); // NOI18N
        C_win_value.setPreferredSize(new java.awt.Dimension(118, 28));
        C_win_value.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                C_win_valueStateChanged(evt);
            }
        });

        javax.swing.GroupLayout Mircheck_panelLayout = new javax.swing.GroupLayout(Mircheck_panel);
        Mircheck_panel.setLayout(Mircheck_panelLayout);
        Mircheck_panelLayout.setHorizontalGroup(
            Mircheck_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Mircheck_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(C_win_box)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(C_win_value, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
        );
        Mircheck_panelLayout.setVerticalGroup(
            Mircheck_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Mircheck_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Mircheck_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(C_win_value, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C_win_box))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Options_panel.addTab("advanced options", Mircheck_panel);

        buttonGroup1.add(default_options_jbutton);
        default_options_jbutton.setText("default options");
        default_options_jbutton.setName("default_options_jbutton"); // NOI18N
        default_options_jbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                default_options_jbuttonActionPerformed(evt);
            }
        });

        buttonGroup1.add(advanced_options_jbutton);
        advanced_options_jbutton.setText("advanced options");
        advanced_options_jbutton.setName("advanced_options_jbutton"); // NOI18N
        advanced_options_jbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                advanced_options_jbuttonActionPerformed(evt);
            }
        });

        jLabel1.setText("(re)Name");

        name_jTextField1.setText("miRcheck retreive genomic regions");
        name_jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                name_jTextField1FocusLost(evt);
            }
        });

        reset_jButton.setForeground(new java.awt.Color(255, 116, 0));
        reset_jButton.setText("Reset");
        reset_jButton.setName("reset_jButton"); // NOI18N
        reset_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_jButton_ActionPerformed(evt);
            }
        });

        stop_jButton.setForeground(new java.awt.Color(255, 0, 0));
        stop_jButton.setText("Stop");
        stop_jButton.setMaximumSize(new java.awt.Dimension(71, 29));
        stop_jButton.setMinimumSize(new java.awt.Dimension(71, 29));
        stop_jButton.setName("stop_jButton"); // NOI18N
        stop_jButton.setPreferredSize(new java.awt.Dimension(71, 29));
        stop_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stop_jButton_ActionPerformed(evt);
            }
        });

        run_jButton.setForeground(new java.awt.Color(0, 255, 3));
        run_jButton.setText("Run");
        run_jButton.setMaximumSize(new java.awt.Dimension(71, 29));
        run_jButton.setMinimumSize(new java.awt.Dimension(71, 29));
        run_jButton.setName("run_jButton"); // NOI18N
        run_jButton.setPreferredSize(new java.awt.Dimension(71, 29));
        run_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                run_jButton_ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Options_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(default_options_jbutton)
                                .addGap(18, 18, 18)
                                .addComponent(advanced_options_jbutton))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(reset_jButton)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(stop_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(run_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(name_jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))))
                        .addGap(0, 12, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stop_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reset_jButton)
                    .addComponent(run_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(name_jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(default_options_jbutton)
                    .addComponent(advanced_options_jbutton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Options_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        miRcheck.addTab("miRcheck retreive genomic regions", jPanel1);

        how_jButton.setForeground(new java.awt.Color(255, 0, 255));
        how_jButton.setText("?");
        how_jButton.setMaximumSize(new java.awt.Dimension(51, 29));
        how_jButton.setMinimumSize(new java.awt.Dimension(51, 29));
        how_jButton.setName("how_jButton"); // NOI18N
        how_jButton.setPreferredSize(new java.awt.Dimension(51, 29));
        how_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                how_jButton_ActionPerformed(evt);
            }
        });

        close_jButton.setForeground(new java.awt.Color(0, 0, 255));
        close_jButton.setText("Close");
        close_jButton.setMaximumSize(new java.awt.Dimension(91, 29));
        close_jButton.setMinimumSize(new java.awt.Dimension(91, 29));
        close_jButton.setName("close_jButton"); // NOI18N
        close_jButton.setPreferredSize(new java.awt.Dimension(91, 29));
        close_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                close_jButton_ActionPerformed(evt);
            }
        });

        docker_jButton.setText("Docker");
        docker_jButton.setMaximumSize(new java.awt.Dimension(91, 29));
        docker_jButton.setMinimumSize(new java.awt.Dimension(91, 29));
        docker_jButton.setName("docker_jButton"); // NOI18N
        docker_jButton.setPreferredSize(new java.awt.Dimension(91, 29));
        docker_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                docker_jButton_ActionPerformed(evt);
            }
        });

        ClusterProgramButton.setText("Cluster");
        ClusterProgramButton.setMaximumSize(new java.awt.Dimension(91, 29));
        ClusterProgramButton.setMinimumSize(new java.awt.Dimension(91, 29));
        ClusterProgramButton.setPreferredSize(new java.awt.Dimension(91, 29));
        ClusterProgramButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClusterProgramButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(close_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(docker_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ClusterProgramButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(how_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(miRcheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ClusterProgramButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(docker_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(how_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(close_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(miRcheck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    private void miRcheckComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_miRcheckComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_miRcheckComponentShown

    private void advanced_options_jbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_advanced_options_jbuttonActionPerformed
        // TODO add your handling code here:
        Util.buttonEventSpinner(properties,advanced_options_jbutton,null);
        if (properties.isSet(default_options_jbutton.getName())) {
            properties.remove(default_options_jbutton.getName());
            default_options_jbutton.setSelected(false);
        }
        menuFields();
    }//GEN-LAST:event_advanced_options_jbuttonActionPerformed

    private void default_options_jbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_default_options_jbuttonActionPerformed
        // TODO add your handling code here:
        Util.buttonEventSpinner(properties,default_options_jbutton,null);
        if (properties.isSet(advanced_options_jbutton.getName())) {
            properties.remove(advanced_options_jbutton.getName());
            advanced_options_jbutton.setSelected(false);
        }
        menuFields();
    }//GEN-LAST:event_default_options_jbuttonActionPerformed

    private void C_win_boxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C_win_boxActionPerformed
        // TODO add your handling code here:
        Util.boxEventSpinner(properties,C_win_box,C_win_value);
    }//GEN-LAST:event_C_win_boxActionPerformed

    private void C_win_valueStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_C_win_valueStateChanged
        // TODO add your handling code here:
        Util.boxEventSpinner(properties,C_win_box,C_win_value);
    }//GEN-LAST:event_C_win_valueStateChanged

    private void name_jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_name_jTextField1FocusLost
        // TODO add your handling code here:
        String s = name_jTextField1.getText();
        if (!s.equals("")){
            properties.put("Name", name_jTextField1.getText());
        }
    }//GEN-LAST:event_name_jTextField1FocusLost

    private void reset_jButton_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_jButton_ActionPerformed
        // TODO add your handling code here:
        properties.load();             //--reload current properties from file
        this.setProperties(properties);//--Update current field
        this.setVisible(false);
    }//GEN-LAST:event_reset_jButton_ActionPerformed

    private void close_jButton_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_close_jButton_ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_close_jButton_ActionPerformed

    private void stop_jButton_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stop_jButton_ActionPerformed
        // TODO add your handling code here:
        properties.put("Status", Config.status_nothing);
        properties.killThread();
    }//GEN-LAST:event_stop_jButton_ActionPerformed

    private void run_jButton_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_run_jButton_ActionPerformed
        // TODO add your handling code here:
        if (this.properties.isSet("ClassName")) {
            this.parent_workflow.workflow.updateDependance();
            programs prog=new programs(parent_workflow.workbox.getCurrentWorkflows());
            prog.Run(properties);
        }
    }//GEN-LAST:event_run_jButton_ActionPerformed

    private void how_jButton_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_how_jButton_ActionPerformed
        // TODO add your handling code here:
        HelpEditor help = new HelpEditor(this.frame, false, properties);
        help.setVisible(true);
    }//GEN-LAST:event_how_jButton_ActionPerformed

    private void docker_jButton_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_docker_jButton_ActionPerformed
        // TODO add your handling code here:
        dockerEditorProgram dock = new dockerEditorProgram(this.frame, false, properties);
        dock.setVisible(true);
    }//GEN-LAST:event_docker_jButton_ActionPerformed

    private void ClusterProgramButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClusterProgramButtonActionPerformed
        // TODO add your handling code here:
        clusterEditorProgram clus = new clusterEditorProgram(this.frame, false, properties);
        clus.setVisible(true);
    }//GEN-LAST:event_ClusterProgramButtonActionPerformed
         
    /**
    ***************************************************************************
    * Set Properties
    ***************************************************************************
    */
    public void setProperties(workflow_properties properties) {
        this.properties=properties;
        setTitle(properties.getName());
        //if (properties.isSet("Description")) this.Notice.setText(properties.get("Description"));
        
        // Properties Default Options
        this.defaultPgrmValues();
        // Update Saved Properties => usp
        this.updateSavedProperties();
        // Set the menu
        this.menuFields();
    }
    
    public void setProperties(String filename, String path) {
        workflow_properties tmp=new workflow_properties();
        tmp.load(filename, path);
        this.properties=tmp;
        setTitle(properties.getName());
    }
    
    /*******************************************************************
    * Update Saved Properties => usp_functions
    *******************************************************************/
    
    private void updateSavedProperties() {
        usp_valueANDtext();
        usp_boxANDbutton();
        name_jTextField1.setText(properties.getName());
    }
    
    private void usp_valueANDtext() {
        if (properties.isSet(C_win_value.getName())){
            this.C_win_value.setValue(Integer.parseInt(properties.get(C_win_value.getName())));
            this.C_win_value.setEnabled(false);
        }
    }
    
    private void usp_boxANDbutton() {
        if (properties.isSet(C_win_box.getName())){
            this.C_win_box.setSelected(true);
            this.C_win_value.setEnabled(true);
        }
    }
    
    /*******************************************************************
     * Set Menu fields
     ******************************************************************/
    
    private void menuFields() {
        if (properties.isSet(default_options_jbutton.getName())) {
            this.default_options_jbutton.setSelected(true);
            enabled_advanced_Options (false);
        }
        else if (properties.isSet(advanced_options_jbutton.getName())) {
            this.advanced_options_jbutton.setSelected(true);
            enabled_advanced_Options (true);
        }
    }

    /*******************************************************************
    * Enabled Function
    *******************************************************************/
    
    private void enabled_advanced_Options (boolean e) {
        this.C_win_box.setEnabled(e);
        if (properties.isSet(C_win_box.getName()) && e==true) {
            this.C_win_value.setEnabled(true);
        } else {
            this.C_win_value.setEnabled(false);
        }
    }
        
    /*******************************************************************
     * Set With default program values present in properties file
     ******************************************************************/
    private void defaultPgrmValues() {
        boolean b = true;
        if (!(properties.isSet(default_options_jbutton.getName()))
        && !(properties.isSet(advanced_options_jbutton.getName()))
        ) {
            b = false;
        }
        
        Util.getDefaultPgrmValues(properties,b);
    }
    
    /**
     * Set the configuration properties for this object
     */
    
    
    
    @Override
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
    
    @Override
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
    private javax.swing.JCheckBox C_win_box;
    private javax.swing.JSpinner C_win_value;
    private javax.swing.JButton ClusterProgramButton;
    private javax.swing.JPanel Mircheck_panel;
    private javax.swing.JTabbedPane Options_panel;
    private javax.swing.JRadioButton advanced_options_jbutton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton close_jButton;
    private javax.swing.JRadioButton default_options_jbutton;
    private javax.swing.JButton docker_jButton;
    private javax.swing.JButton how_jButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane miRcheck;
    private javax.swing.JTextField name_jTextField1;
    private javax.swing.JButton reset_jButton;
    private javax.swing.JButton run_jButton;
    private javax.swing.JButton stop_jButton;
    // End of variables declaration//GEN-END:variables
}
