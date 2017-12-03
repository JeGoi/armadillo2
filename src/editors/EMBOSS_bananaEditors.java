/**
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package editors;

import editor.dockerEditor;
import configuration.Config;
import configuration.Util;
import editor.EditorInterface;
import editor.clusterEditorProgram;
import editor.dockerEditorProgram;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import program.*;
import workflows.armadillo_workflow;
import workflows.workflow_properties;
import workflows.workflow_properties_dictionnary;

/**
 *
 * @author : JG
 * @Date   : Feb 2016
 */

public class EMBOSS_bananaEditors extends javax.swing.JDialog implements EditorInterface  {

    /**
     * Creates new form EMBOSS_bananaEditors
     */
    Config config=new Config();
    //ConnectorInfoBox connectorinfobox;
    workflow_properties_dictionnary dict=new workflow_properties_dictionnary();
    String selected = "";             // Selected properties
    Frame frame;
    workflow_properties properties;
    armadillo_workflow  parent_workflow;
    public static HashMap<JCheckBox,JSpinner> DictBoxSpinner = new HashMap<JCheckBox,JSpinner>();
    public static HashMap<JCheckBox,JTextField> DictBoxTextField = new HashMap<JCheckBox,JTextField>();
    public static HashMap<JCheckBox,JComboBox> DictBoxComboBox = new HashMap<JCheckBox,JComboBox>();
    public static HashMap<JRadioButton,JSpinner> DictRadioButtonSpinner = new HashMap<JRadioButton,JSpinner>();
    public static HashMap<JRadioButton,JTextField> DictRadioButtonTextField = new HashMap<JRadioButton,JTextField>();
    public static ArrayList<HashMap> listDicts = new ArrayList<HashMap>();
    public final String defaultNameString="Name";
    static final boolean default_map=true;
    public EMBOSS_bananaEditors(java.awt.Frame parent, armadillo_workflow parent_workflow) {
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

        Menu_Buttons = new javax.swing.ButtonGroup();
        EMBOSS_bananaEditors2 = new javax.swing.JTabbedPane();
        general_jPanel1 = new javax.swing.JPanel();
        name_jLabel = new javax.swing.JLabel();
        name_jTextField = new javax.swing.JTextField();
        default_RButton = new javax.swing.JRadioButton();
        Advanced_Options_RButton = new javax.swing.JRadioButton();
        Sq_panel = new javax.swing.JPanel();
        Sq_graph_Box = new javax.swing.JCheckBox();
        Sq_graph_Box_List = new javax.swing.JComboBox();
        Sq_graph_Box_Label = new javax.swing.JLabel();
        Sq_residuesperline_Box = new javax.swing.JCheckBox();
        Sq_residuesperline_Box_IntValue = new javax.swing.JSpinner();
        run_jButton = new javax.swing.JButton();
        stop_jButton = new javax.swing.JButton();
        reset_jButton = new javax.swing.JButton();
        close_jButton = new javax.swing.JButton();
        how_jButton = new javax.swing.JButton();
        docker_jButton1 = new javax.swing.JButton();
        ClusterProgramButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        EMBOSS_bananaEditors2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                EMBOSS_bananaEditors2ComponentShown(evt);
            }
        });

        general_jPanel1.setName("general_jPanel1"); // NOI18N
        general_jPanel1.setPreferredSize(new java.awt.Dimension(459, 140));

        name_jLabel.setFont(new java.awt.Font("Ubuntu", 3, 15)); // NOI18N
        name_jLabel.setText("Name");
        name_jLabel.setName("name_jLabel"); // NOI18N

        name_jTextField.setText("EMBOSS_banana");
        name_jTextField.setName("name_jTextField"); // NOI18N
        name_jTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                name_jTextFieldFocusLost(evt);
            }
        });
        name_jTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                name_jTextField_ActionPerformed(evt);
            }
        });

        Menu_Buttons.add(default_RButton);
        default_RButton.setText("default");
        default_RButton.setName("default_RButton"); // NOI18N
        default_RButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                default_RButton_ActionPerformed(evt);
            }
        });

        Menu_Buttons.add(Advanced_Options_RButton);
        Advanced_Options_RButton.setText("Advanced Options");
        Advanced_Options_RButton.setName("Advanced_Options_RButton"); // NOI18N
        Advanced_Options_RButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Advanced_Options_RButton_ActionPerformed(evt);
            }
        });

        Sq_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Standard_qualifiers"));

        Sq_graph_Box.setText("graph");
        Sq_graph_Box.setName("Sq_graph_Box"); // NOI18N
        Sq_graph_Box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sq_graph_Box_ActionPerformed(evt);
            }
        });

        Sq_graph_Box_List.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ps", "hpgl", "hp7470", "hp7580", "meta", "cps", "x11", "tek", "tekt", "none", "data", "xterm", "png", "gif", "pdf", "svg" }));
        Sq_graph_Box_List.setName("Sq_graph_Box_List"); // NOI18N
        Sq_graph_Box_List.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sq_graph_Box_List_ActionPerformed(evt);
            }
        });

        Sq_graph_Box_Label.setFont(new java.awt.Font("Ubuntu", 3, 15)); // NOI18N
        Sq_graph_Box_Label.setText("Choose Graph type");
        Sq_graph_Box_Label.setName("Sq_graph_Box_Label"); // NOI18N

        Sq_residuesperline_Box.setText("residuesperline");
        Sq_residuesperline_Box.setName("Sq_residuesperline_Box"); // NOI18N
        Sq_residuesperline_Box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sq_residuesperline_Box_ActionPerformed(evt);
            }
        });

        Sq_residuesperline_Box_IntValue.setModel(new javax.swing.SpinnerNumberModel(50, 0, 2147483647, 1));
        Sq_residuesperline_Box_IntValue.setName("Sq_residuesperline_Box_IntValue"); // NOI18N
        Sq_residuesperline_Box_IntValue.setPreferredSize(new java.awt.Dimension(115, 28));
        Sq_residuesperline_Box_IntValue.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                Sq_residuesperline_Box_IntValue_StateChanged(evt);
            }
        });

        javax.swing.GroupLayout Sq_panelLayout = new javax.swing.GroupLayout(Sq_panel);
        Sq_panel.setLayout(Sq_panelLayout);
        Sq_panelLayout.setHorizontalGroup(
            Sq_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Sq_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Sq_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Sq_panelLayout.createSequentialGroup()
                        .addComponent(Sq_residuesperline_Box)
                        .addGap(18, 18, 18)
                        .addComponent(Sq_residuesperline_Box_IntValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(Sq_panelLayout.createSequentialGroup()
                        .addComponent(Sq_graph_Box)
                        .addGap(18, 18, 18)
                        .addComponent(Sq_graph_Box_List, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Sq_graph_Box_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6))))
        );
        Sq_panelLayout.setVerticalGroup(
            Sq_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Sq_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Sq_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Sq_graph_Box)
                    .addComponent(Sq_graph_Box_List, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sq_graph_Box_Label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Sq_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Sq_residuesperline_Box)
                    .addComponent(Sq_residuesperline_Box_IntValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        Sq_graph_Box.getAccessibleContext().setAccessibleDescription("EMBOSS_GRAPHICS value");
        Sq_graph_Box_List.getAccessibleContext().setAccessibleDescription("EMBOSS_GRAPHICS value");
        Sq_residuesperline_Box.getAccessibleContext().setAccessibleDescription("Number of residues to be displayed on each line");
        Sq_residuesperline_Box_IntValue.getAccessibleContext().setAccessibleDescription("Number of residues to be displayed on each line");

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

        reset_jButton.setForeground(new java.awt.Color(255, 116, 0));
        reset_jButton.setText("Reset");
        reset_jButton.setName("reset_jButton"); // NOI18N
        reset_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_jButton_ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout general_jPanel1Layout = new javax.swing.GroupLayout(general_jPanel1);
        general_jPanel1.setLayout(general_jPanel1Layout);
        general_jPanel1Layout.setHorizontalGroup(
            general_jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(general_jPanel1Layout.createSequentialGroup()
                .addGroup(general_jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(general_jPanel1Layout.createSequentialGroup()
                        .addComponent(reset_jButton)
                        .addGap(35, 35, 35)
                        .addComponent(stop_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addComponent(run_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(general_jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(general_jPanel1Layout.createSequentialGroup()
                            .addComponent(default_RButton)
                            .addGap(18, 18, 18)
                            .addComponent(Advanced_Options_RButton))
                        .addGroup(general_jPanel1Layout.createSequentialGroup()
                            .addComponent(name_jLabel)
                            .addGap(18, 18, 18)
                            .addComponent(name_jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(general_jPanel1Layout.createSequentialGroup()
                .addComponent(Sq_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        general_jPanel1Layout.setVerticalGroup(
            general_jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(general_jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(general_jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reset_jButton)
                    .addComponent(stop_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(run_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(general_jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(name_jLabel)
                    .addComponent(name_jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(general_jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(default_RButton)
                    .addComponent(Advanced_Options_RButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Sq_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        name_jTextField.getAccessibleContext().setAccessibleDescription("");
        default_RButton.getAccessibleContext().setAccessibleDescription("");
        Advanced_Options_RButton.getAccessibleContext().setAccessibleDescription("");

        EMBOSS_bananaEditors2.addTab("EMBOSS_bananaEditors", general_jPanel1);

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

        docker_jButton1.setText("Docker");
        docker_jButton1.setMaximumSize(new java.awt.Dimension(91, 29));
        docker_jButton1.setMinimumSize(new java.awt.Dimension(91, 29));
        docker_jButton1.setName("docker_jButton"); // NOI18N
        docker_jButton1.setPreferredSize(new java.awt.Dimension(91, 29));
        docker_jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                docker_jButton1_ActionPerformed(evt);
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
                .addComponent(docker_jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ClusterProgramButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(how_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(EMBOSS_bananaEditors2, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(close_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(how_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ClusterProgramButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(docker_jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(EMBOSS_bananaEditors2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        EMBOSS_bananaEditors2.getAccessibleContext().setAccessibleName("EMBOSS_bananaEditors");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EMBOSS_bananaEditors2ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_EMBOSS_bananaEditors2ComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_EMBOSS_bananaEditors2ComponentShown
        
    private void name_jTextField_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name_jTextField_ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_name_jTextField_ActionPerformed
    

    private void default_RButton_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_default_RButton_ActionPerformed
        // TODO add your handling code here:
        if (properties.isSet(Advanced_Options_RButton.getName())){
            properties.remove(Advanced_Options_RButton.getName());
        }
        Util.buttonEventSpinner(properties,default_RButton,null);
        menuFields(properties);
    }//GEN-LAST:event_default_RButton_ActionPerformed

    private void Advanced_Options_RButton_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Advanced_Options_RButton_ActionPerformed
        // TODO add your handling code here:
        if (properties.isSet(default_RButton.getName())){
            properties.remove(default_RButton.getName());
        }
        Util.buttonEventSpinner(properties,Advanced_Options_RButton,null);
        menuFields(properties);
    }//GEN-LAST:event_Advanced_Options_RButton_ActionPerformed

    private void Sq_graph_Box_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sq_graph_Box_ActionPerformed
        // TODO add your handling code here:
        Util.boxEventComboBox(properties,Sq_graph_Box,Sq_graph_Box_List);
    }//GEN-LAST:event_Sq_graph_Box_ActionPerformed

    private void Sq_graph_Box_List_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sq_graph_Box_List_ActionPerformed
        // TODO add your handling code here:
        Util.boxEventComboBox(properties,Sq_graph_Box,Sq_graph_Box_List);
    }//GEN-LAST:event_Sq_graph_Box_List_ActionPerformed

    private void Sq_residuesperline_Box_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sq_residuesperline_Box_ActionPerformed
        // TODO add your handling code here:
        Util.boxEventSpinner(properties,Sq_residuesperline_Box,Sq_residuesperline_Box_IntValue);
    }//GEN-LAST:event_Sq_residuesperline_Box_ActionPerformed

    private void Sq_residuesperline_Box_IntValue_StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_Sq_residuesperline_Box_IntValue_StateChanged
        // TODO add your handling code here:
        Util.boxEventSpinner(properties,Sq_residuesperline_Box,Sq_residuesperline_Box_IntValue);
    }//GEN-LAST:event_Sq_residuesperline_Box_IntValue_StateChanged

    private void name_jTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_name_jTextFieldFocusLost
        // TODO add your handling code here:
        properties.put("Name", name_jTextField.getText());
    }//GEN-LAST:event_name_jTextFieldFocusLost

    private void how_jButton_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_how_jButton_ActionPerformed
        // TODO add your handling code here:
        HelpEditor help = new HelpEditor(this.frame, false, properties);
        help.setVisible(true);
    }//GEN-LAST:event_how_jButton_ActionPerformed

    private void run_jButton_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_run_jButton_ActionPerformed
        // TODO add your handling code here:
        if (this.properties.isSet("ClassName")) {
            this.parent_workflow.workflow.updateDependance();
            programs prog=new programs(parent_workflow.workbox.getCurrentWorkflows());
            prog.Run(properties);
        }
    }//GEN-LAST:event_run_jButton_ActionPerformed

    private void stop_jButton_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stop_jButton_ActionPerformed
        // TODO add your handling code here:
        properties.put("Status", Config.status_nothing);
        properties.killThread();
    }//GEN-LAST:event_stop_jButton_ActionPerformed

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

    private void docker_jButton1_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_docker_jButton1_ActionPerformed
        // TODO add your handling code here:
        dockerEditorProgram dock = new dockerEditorProgram(this.frame, false, properties);
        dock.setVisible(true);
    }//GEN-LAST:event_docker_jButton1_ActionPerformed

    private void ClusterProgramButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClusterProgramButtonActionPerformed
        // TODO add your handling code here:
        clusterEditorProgram clus = new clusterEditorProgram(this.frame, false, properties);
        clus.setVisible(true);
    }//GEN-LAST:event_ClusterProgramButtonActionPerformed

    /*******************************************************************
     * Set the configuration properties for this object
     ******************************************************************/

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
        
        // Set the program properties
        this.setProperties(properties);
        
        this.setAlwaysOnTop(true);
        this.setVisible(true);
        
        
    }

    /*******************************************************************
     * Sets for Properties
     ******************************************************************/

    /**
     * Set Properties
     * @param properties
     */

    public void setProperties(workflow_properties properties) {
        this.properties=properties;
        setTitle(properties.getName());
        //if (this.properties.isSet("Description")) this.Notice.setText(properties.get("Description"));
        
        // Dicts prepare
        Util.dictsReset(listDicts,DictBoxSpinner,DictBoxTextField,DictBoxComboBox,DictRadioButtonSpinner,DictRadioButtonTextField);
        DictBoxComboBox.put(Sq_graph_Box,Sq_graph_Box_List);
        DictBoxSpinner.put(Sq_residuesperline_Box,Sq_residuesperline_Box_IntValue);
        // Properties Default Options
        this.defaultPgrmValues(properties);
        // Update Saved Properties => usp
        Util.updateSavedProperties(properties,listDicts,name_jTextField);
        // Set the menu
        this.menuFields(properties);

    }

    public void setProperties(String filename, String path) {
        workflow_properties tmp=new workflow_properties();
        tmp.load(filename, path);
        this.properties=tmp;
        setTitle(properties.getName());
    }

    /*******************************************************************
     * Set With default program values present in properties file
     ******************************************************************/
    private void defaultPgrmValues(workflow_properties properties) {
        boolean b = true;
        if (!(properties.isSet(default_RButton.getName()))
        && !(properties.isSet(Advanced_Options_RButton.getName()))
        ) {
            b = false;
        }
        
        Util.getDefaultPgrmValues(properties,b);
    }
    
    /*******************************************************************
     * Set Menu fields
     ******************************************************************/

    private void menuFields(workflow_properties properties) {
        if (properties.isSet(default_RButton.getName())) {
            default_RButton.setSelected(true);
            Util.enabled_Advanced_Options(properties,false,listDicts);
        }
        else if (properties.isSet(Advanced_Options_RButton.getName())) {
            Advanced_Options_RButton.setSelected(true);
            Util.enabled_Advanced_Options(properties,true,listDicts);
        }
    }

    /*******************************************************************
     * Save Image
     ******************************************************************/

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
    private javax.swing.JRadioButton Advanced_Options_RButton;
    private javax.swing.JButton ClusterProgramButton;
    private javax.swing.JTabbedPane EMBOSS_bananaEditors2;
    private javax.swing.ButtonGroup Menu_Buttons;
    private javax.swing.JCheckBox Sq_graph_Box;
    private javax.swing.JLabel Sq_graph_Box_Label;
    private javax.swing.JComboBox Sq_graph_Box_List;
    private javax.swing.JPanel Sq_panel;
    private javax.swing.JCheckBox Sq_residuesperline_Box;
    private javax.swing.JSpinner Sq_residuesperline_Box_IntValue;
    private javax.swing.JButton close_jButton;
    private javax.swing.JRadioButton default_RButton;
    private javax.swing.JButton docker_jButton1;
    private javax.swing.JPanel general_jPanel1;
    private javax.swing.JButton how_jButton;
    private javax.swing.JLabel name_jLabel;
    private javax.swing.JTextField name_jTextField;
    private javax.swing.JButton reset_jButton;
    private javax.swing.JButton run_jButton;
    private javax.swing.JButton stop_jButton;
    // End of variables declaration//GEN-END:variables
    }

