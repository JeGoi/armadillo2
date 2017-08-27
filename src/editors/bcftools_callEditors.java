/**
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package editors;

import configuration.Config;
import configuration.Util;
import editor.EditorInterface;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import java.util.ArrayList;
import java.util.HashMap;
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
 * @author : J-G
 * @Date   : 26-08-2017
 */

public class bcftools_callEditors extends javax.swing.JDialog implements EditorInterface {

    /**
     * Creates new form bcftools_callEditors
     */
    Config config=new Config();
    //ConnectorInfoBox connectorinfobox;
    workflow_properties_dictionnary dict=new workflow_properties_dictionnary();
    String selected = "";             // Selected properties
    Frame frame;
    workflow_properties properties;
    armadillo_workflow  parent_workflow;

    public final String defaultNameString="Name";
    static final boolean default_map=true;
    public static HashMap<JCheckBox,JSpinner> DictMenuCBS0 = new HashMap<JCheckBox,JSpinner>();
    public static HashMap<JCheckBox,JTextField> DictMenuCBT0 = new HashMap<JCheckBox,JTextField>();
    public static HashMap<JCheckBox,JComboBox> DictMenuCBC0 = new HashMap<JCheckBox,JComboBox>();
    public static HashMap<JRadioButton,JSpinner> DictMenuRBS0 = new HashMap<JRadioButton,JSpinner>();
    public static HashMap<JRadioButton,JTextField> DictMenuRBT0 = new HashMap<JRadioButton,JTextField>();
    public static ArrayList<HashMap> listDictsMenu0 = new ArrayList<HashMap>();

    public bcftools_callEditors(java.awt.Frame parent, armadillo_workflow parent_workflow){
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
        close_jButton = new javax.swing.JButton();
        how_jButton = new javax.swing.JButton();
        docker_jButton = new javax.swing.JButton();
        bcftools_call_tab = new javax.swing.JTabbedPane();
        general_jPanel1 = new javax.swing.JPanel();
        stop_jButton = new javax.swing.JButton();
        reset_jButton = new javax.swing.JButton();
        run_jButton = new javax.swing.JButton();
        name_jLabel = new javax.swing.JLabel();
        name_jTextField = new javax.swing.JTextField();
        Default_Options = new javax.swing.JRadioButton();
        Advanced_Options = new javax.swing.JRadioButton();
        main_jScroll = new javax.swing.JScrollPane();
        options_tab_panel = new javax.swing.JTabbedPane();
        AO_jPanel = new javax.swing.JPanel();
        AO_IOO_JLabel = new javax.swing.JLabel();
        AO_IOO__variantsHYPHENSYMBOLonly_box = new javax.swing.JCheckBox();
        AO_FFO_JLabel = new javax.swing.JLabel();
        AO_FFO__outputHYPHENSYMBOLtype_box = new javax.swing.JCheckBox();
        AO_FFO__outputHYPHENSYMBOLtype_JComboBoxValue = new javax.swing.JComboBox();
        AO_CVCO_JLabel = new javax.swing.JLabel();
        AO_CVCO__multiallelicHYPHENSYMBOLcaller_box = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        docker_jButton.setText("Docker Editor");
        docker_jButton.setMaximumSize(new java.awt.Dimension(91, 29));
        docker_jButton.setMinimumSize(new java.awt.Dimension(91, 29));
        docker_jButton.setName("docker_jButton"); // NOI18N
        docker_jButton.setPreferredSize(new java.awt.Dimension(91, 29));
        docker_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                docker_jButton_ActionPerformed(evt);
            }
        });

        bcftools_call_tab.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                bcftools_call_tab_ComponentShown(evt);
            }
        });

        general_jPanel1.setName("general_jPanel1"); // NOI18N
        general_jPanel1.setPreferredSize(new java.awt.Dimension(459, 400));

        stop_jButton.setForeground(new java.awt.Color(0, 0, 255));
        stop_jButton.setText("Stop");
        stop_jButton.setMaximumSize(new java.awt.Dimension(91, 29));
        stop_jButton.setMinimumSize(new java.awt.Dimension(91, 29));
        stop_jButton.setName("stop_jButton"); // NOI18N
        stop_jButton.setPreferredSize(new java.awt.Dimension(91, 29));
        stop_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stop_jButton_ActionPerformed(evt);
            }
        });

        reset_jButton.setForeground(new java.awt.Color(255, 116, 0));
        reset_jButton.setText("Reset");
        reset_jButton.setMaximumSize(new java.awt.Dimension(91, 29));
        reset_jButton.setMinimumSize(new java.awt.Dimension(91, 29));
        reset_jButton.setName("reset_jButton"); // NOI18N
        reset_jButton.setPreferredSize(new java.awt.Dimension(91, 29));
        reset_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_jButton_ActionPerformed(evt);
            }
        });

        run_jButton.setForeground(new java.awt.Color(255, 116, 0));
        run_jButton.setText("RUN");
        run_jButton.setMaximumSize(new java.awt.Dimension(91, 29));
        run_jButton.setMinimumSize(new java.awt.Dimension(91, 29));
        run_jButton.setName("run_jButton"); // NOI18N
        run_jButton.setPreferredSize(new java.awt.Dimension(91, 29));
        run_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                run_jButton_ActionPerformed(evt);
            }
        });

        name_jLabel.setFont(new java.awt.Font("Ubuntu", 3, 15)); // NOI18N
        name_jLabel.setText("(re)Name");
        name_jLabel.setName("name_jLabel"); // NOI18N

        name_jTextField.setFont(new java.awt.Font("Ubuntu", 3, 15)); // NOI18N
        name_jTextField.setText("Name");
        name_jTextField.setName("name_jTextField"); // NOI18N
        name_jTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                name_jTextField_ActionPerformed(evt);
            }
        });
        name_jTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                name_jTextField_FocusLost(evt);
            }
        });

        Menu_Buttons.add(Default_Options);
        Default_Options.setText("Default Options");
        Default_Options.setName("Default_Options"); // NOI18N
        Default_Options.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Default_Options_ActionPerformed(evt);
            }
        });

        Menu_Buttons.add(Advanced_Options);
        Advanced_Options.setText("Advanced Options");
        Advanced_Options.setName("Advanced_Options"); // NOI18N
        Advanced_Options.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Advanced_Options_ActionPerformed(evt);
            }
        });

        AO_IOO_JLabel.setFont(new java.awt.Font("Ubuntu", 3, 15)); // NOI18N
        AO_IOO_JLabel.setText("Input Output options");
        AO_IOO_JLabel.setName("AO_IOO_JLabel"); // NOI18N

        AO_IOO__variantsHYPHENSYMBOLonly_box.setName("AO_IOO__variantsHYPHENSYMBOLonly_box"); // NOI18N
        AO_IOO__variantsHYPHENSYMBOLonly_box.setText("--variants-only");
        AO_IOO__variantsHYPHENSYMBOLonly_box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AO_IOO__variantsHYPHENSYMBOLonly_box_ActionPerformed(evt);
            }
        });

        AO_FFO_JLabel.setFont(new java.awt.Font("Ubuntu", 3, 15)); // NOI18N
        AO_FFO_JLabel.setText("File format options");
        AO_FFO_JLabel.setName("AO_FFO_JLabel"); // NOI18N

        AO_FFO__outputHYPHENSYMBOLtype_box.setName("AO_FFO__outputHYPHENSYMBOLtype_box"); // NOI18N
        AO_FFO__outputHYPHENSYMBOLtype_box.setText("--output-type");
        AO_FFO__outputHYPHENSYMBOLtype_box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AO_FFO__outputHYPHENSYMBOLtype_box_ActionPerformed(evt);
            }
        });

        AO_FFO__outputHYPHENSYMBOLtype_JComboBoxValue.setName("AO_FFO__outputHYPHENSYMBOLtype_JComboBoxValue"); // NOI18N
        AO_FFO__outputHYPHENSYMBOLtype_JComboBoxValue.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "u", "v" }));
        AO_FFO__outputHYPHENSYMBOLtype_JComboBoxValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AO_FFO__outputHYPHENSYMBOLtype_JComboBoxValue_ActionPerformed(evt);
            }
        });

        AO_CVCO_JLabel.setFont(new java.awt.Font("Ubuntu", 3, 15)); // NOI18N
        AO_CVCO_JLabel.setText("Consensus Variant calling options");
        AO_CVCO_JLabel.setName("AO_CVCO_JLabel"); // NOI18N

        AO_CVCO__multiallelicHYPHENSYMBOLcaller_box.setName("AO_CVCO__multiallelicHYPHENSYMBOLcaller_box"); // NOI18N
        AO_CVCO__multiallelicHYPHENSYMBOLcaller_box.setText("--multiallelic-caller");
        AO_CVCO__multiallelicHYPHENSYMBOLcaller_box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AO_CVCO__multiallelicHYPHENSYMBOLcaller_box_ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AO_jPanelLayout = new javax.swing.GroupLayout(AO_jPanel);
        AO_jPanel.setLayout(AO_jPanelLayout);
        AO_jPanelLayout.setHorizontalGroup(
            AO_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AO_jPanelLayout.createSequentialGroup()
                .addGroup(AO_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AO_IOO_JLabel)
                    .addComponent(AO_IOO__variantsHYPHENSYMBOLonly_box)
                    .addComponent(AO_FFO_JLabel)
                    .addComponent(AO_FFO__outputHYPHENSYMBOLtype_box)
                    .addComponent(AO_CVCO_JLabel)
                    .addComponent(AO_CVCO__multiallelicHYPHENSYMBOLcaller_box))
                .addGap(18, 18, 18)
                .addComponent(AO_FFO__outputHYPHENSYMBOLtype_JComboBoxValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        AO_jPanelLayout.setVerticalGroup(
            AO_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AO_jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AO_IOO_JLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AO_IOO__variantsHYPHENSYMBOLonly_box)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AO_FFO_JLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AO_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AO_FFO__outputHYPHENSYMBOLtype_box)
                    .addComponent(AO_FFO__outputHYPHENSYMBOLtype_JComboBoxValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AO_CVCO_JLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AO_CVCO__multiallelicHYPHENSYMBOLcaller_box)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        AO_IOO_JLabel.getAccessibleContext().setAccessibleDescription("Sub Items");
        AO_FFO_JLabel.getAccessibleContext().setAccessibleDescription("Sub Items");
        AO_CVCO_JLabel.getAccessibleContext().setAccessibleDescription("Sub Items");

        options_tab_panel.addTab("Advanced Options", AO_jPanel);

        main_jScroll.setViewportView(options_tab_panel);

        javax.swing.GroupLayout general_jPanel1Layout = new javax.swing.GroupLayout(general_jPanel1);
        general_jPanel1.setLayout(general_jPanel1Layout);
        general_jPanel1Layout.setHorizontalGroup(
            general_jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(general_jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(reset_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(stop_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(run_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(general_jPanel1Layout.createSequentialGroup()
                .addComponent(name_jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(name_jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(general_jPanel1Layout.createSequentialGroup()
                .addComponent(Default_Options)
                .addGap(18, 18, 18)
                .addComponent(Advanced_Options))
            .addGroup(general_jPanel1Layout.createSequentialGroup()
                .addComponent(main_jScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                .addContainerGap())
        );
        general_jPanel1Layout.setVerticalGroup(
            general_jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(general_jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(general_jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stop_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reset_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(run_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(general_jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(name_jLabel)
                    .addComponent(name_jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(general_jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Default_Options)
                    .addComponent(Advanced_Options))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(main_jScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        stop_jButton.getAccessibleContext().setAccessibleDescription("Stop this box");
        reset_jButton.getAccessibleContext().setAccessibleDescription("Reset to default values");
        run_jButton.getAccessibleContext().setAccessibleDescription("Run this box");
        name_jLabel.getAccessibleContext().setAccessibleDescription("Name Box");
        name_jTextField.getAccessibleContext().setAccessibleDescription("Rename the box here");
        Default_Options.getAccessibleContext().setAccessibleDescription("Default Options");
        Advanced_Options.getAccessibleContext().setAccessibleDescription("Advanced Options");

        bcftools_call_tab.addTab("bcftools_call", general_jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(close_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(docker_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(how_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(bcftools_call_tab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(close_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(how_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(docker_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bcftools_call_tab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        close_jButton.getAccessibleContext().setAccessibleDescription("Close this box");
        how_jButton.getAccessibleContext().setAccessibleDescription("About this box");
        docker_jButton.getAccessibleContext().setAccessibleDescription("Access to the docker editor");
        bcftools_call_tab.getAccessibleContext().setAccessibleName("bcftools_call");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bcftools_call_tab_ComponentShown(java.awt.event.ComponentEvent evt){//GEN-FIRST:event_bcftools_call_tab_ComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_bcftools_call_tab_ComponentShown
    
    private void how_jButton_ActionPerformed(java.awt.event.ActionEvent evt){//GEN-FIRST:event_how_jButton_ActionPerformed
        // TODO add your handling code here:
        HelpEditor help = new HelpEditor(this.frame, false, properties);
        help.setVisible(true);
    }//GEN-LAST:event_how_jButton_ActionPerformed

    private void close_jButton_ActionPerformed(java.awt.event.ActionEvent evt){//GEN-FIRST:event_close_jButton_ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_close_jButton_ActionPerformed

    private void run_jButton_ActionPerformed(java.awt.event.ActionEvent evt){//GEN-FIRST:event_run_jButton_ActionPerformed
        // TODO add your handling code here:
        if (this.properties.isSet("ClassName")){
            this.parent_workflow.workflow.updateDependance();
            programs prog=new programs(parent_workflow.workbox.getCurrentWorkflows());
            prog.Run(properties);
        }
    }//GEN-LAST:event_run_jButton_ActionPerformed

    private void stop_jButton_ActionPerformed(java.awt.event.ActionEvent evt){//GEN-FIRST:event_stop_jButton_ActionPerformed
        // TODO add your handling code here:
        properties.put("Status", Config.status_nothing);
        properties.killThread();
    }//GEN-LAST:event_stop_jButton_ActionPerformed

    private void reset_jButton_ActionPerformed(java.awt.event.ActionEvent evt){//GEN-FIRST:event_reset_jButton_ActionPerformed
        // TODO add your handling code here:
        properties.load();             //--reload current properties from file
        this.setProperties(properties);//--Update current field
        //this.display(properties);
        this.setVisible(false);
    }//GEN-LAST:event_reset_jButton_ActionPerformed

    private void name_jTextField_ActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        // TODO add your handling code here:
        properties.put("Name", name_jTextField.getText());
    }                                                

    private void name_jTextField_FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_name_jTextField_ActionPerformed
        // TODO add your handling code here:
        properties.put("Name", name_jTextField.getText());
    }//GEN-LAST:event_name_jTextField_ActionPerformed

    private void docker_jButton_ActionPerformed(java.awt.event.ActionEvent evt){//GEN-FIRST:event_docker_jButton_ActionPerformed
        // TODO add your handling code here:
        dockerEditor dock = new dockerEditor(this.frame, false, properties);
        dock.setVisible(true);
    }//GEN-LAST:event_docker_jButton_ActionPerformed
    
    private void Default_Options_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Default_Options_ActionPerformed
        // TODO add your handling code here:
        if (properties.isSet(Advanced_Options.getName())){
            properties.remove(Advanced_Options.getName());
        }
        Util.buttonEventSpinner(properties,Default_Options,null);
        menuFields(properties);
    }//GEN-LAST:event_Default_Options_ActionPerformed
    
    private void Advanced_Options_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Advanced_Options_ActionPerformed
        // TODO add your handling code here:
        if (properties.isSet(Default_Options.getName())){
            properties.remove(Default_Options.getName());
        }
        Util.buttonEventSpinner(properties,Advanced_Options,null);
        menuFields(properties);
    }//GEN-LAST:event_Advanced_Options_ActionPerformed
    
    private void AO_IOO__variantsHYPHENSYMBOLonly_box_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AO_IOO__variantsHYPHENSYMBOLonly_box_ActionPerformed
        // TODO add your handling code here:
        Util.boxEventSpinner(properties,AO_IOO__variantsHYPHENSYMBOLonly_box,null);
    }//GEN-LAST:event_AO_IOO__variantsHYPHENSYMBOLonly_box_ActionPerformed
    private void AO_FFO__outputHYPHENSYMBOLtype_JComboBoxValue_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AO_FFO__outputHYPHENSYMBOLtype_JComboBoxValue_actionPerformed
        // TODO add your handling code here:
        Util.boxEventComboBox(properties,AO_FFO__outputHYPHENSYMBOLtype_box,AO_FFO__outputHYPHENSYMBOLtype_JComboBoxValue);
    }//GEN-LAST:event_AO_FFO__outputHYPHENSYMBOLtype_JComboBoxValue_actionPerformed

    private void AO_FFO__outputHYPHENSYMBOLtype_box_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AO_FFO__outputHYPHENSYMBOLtype_box_ActionPerformed
        // TODO add your handling code here:
        Util.boxEventComboBox(properties,AO_FFO__outputHYPHENSYMBOLtype_box,AO_FFO__outputHYPHENSYMBOLtype_JComboBoxValue);
    }//GEN-LAST:event_AO_FFO__outputHYPHENSYMBOLtype_box_ActionPerformed
    private void AO_CVCO__multiallelicHYPHENSYMBOLcaller_box_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AO_CVCO__multiallelicHYPHENSYMBOLcaller_box_ActionPerformed
        // TODO add your handling code here:
        Util.boxEventSpinner(properties,AO_CVCO__multiallelicHYPHENSYMBOLcaller_box,null);
    }//GEN-LAST:event_AO_CVCO__multiallelicHYPHENSYMBOLcaller_box_ActionPerformed
    /*******************************************************************
     * Perpare List Dictionaries
     ******************************************************************/

    /**
     * Perpare List of Dictionaries by a general reset
     * @param properties
     */

    public void resetDictionaries(workflow_properties properties){
        Util.dictsReset(listDictsMenu0,DictMenuCBS0,DictMenuCBT0,DictMenuCBC0,DictMenuRBS0,DictMenuRBT0);
    }

    /*******************************************************************
     * Perpare Dictionaries
     ******************************************************************/

    /**
     * Perpare Dictionaries by adding commands
     * @param properties
     */

    public void perpareDictionaries(workflow_properties properties){
        DictMenuCBS0.put(AO_IOO__variantsHYPHENSYMBOLonly_box,null);
        DictMenuCBC0.put(AO_FFO__outputHYPHENSYMBOLtype_box,AO_FFO__outputHYPHENSYMBOLtype_JComboBoxValue);
        DictMenuCBS0.put(AO_CVCO__multiallelicHYPHENSYMBOLcaller_box,null);
    }


    /*******************************************************************
     * Set the configuration properties for this object
     ******************************************************************/

    @Override
    public void display(workflow_properties properties){
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

    public void setProperties(workflow_properties properties){
        this.properties=properties;
        setTitle(properties.getName());
        //if (this.properties.isSet("Description")) this.Notice.setText(properties.get("Description"));
        
        // Prepare dictionaries
        this.resetDictionaries(properties);
        this.perpareDictionaries(properties);
        // Properties Default Options
        this.defaultPgrmValues(properties);
        // Update Saved Properties => usp
        Util.updateSavedProperties(properties,listDictsMenu0,name_jTextField);
        // Set the menu
        this.menuFields(properties);
    }

    public void setProperties(String filename, String path){
        workflow_properties tmp=new workflow_properties();
        tmp.load(filename, path);
        this.properties=tmp;
        setTitle(properties.getName());
    }

    /*******************************************************************
     * Set With default program values present in properties file
     ******************************************************************/
    private void defaultPgrmValues(workflow_properties properties){
        boolean b = true;
        if (!(properties.isSet(Default_Options.getName()))
        && !(properties.isSet(Advanced_Options.getName()))
        ){
            b = false;
        }
        
        Util.getDefaultPgrmValues(properties,b);
    }
    
    /*******************************************************************
     * Set Menu fields
     ******************************************************************/

    private void menuFields(workflow_properties properties){
        if (properties.isSet(Default_Options.getName())){
            Default_Options.setSelected(true);
            Util.enabled_Advanced_Options(properties,false,listDictsMenu0);
        }
        else if (properties.isSet(Advanced_Options.getName())){
            Advanced_Options.setSelected(true);
            Util.enabled_Advanced_Options(properties,true,listDictsMenu0);
        }
        // update parents and children relation
        parentsChildrenUpdate(properties);
    }

    /*******************************************************************
     * Update parents children relation
     ******************************************************************/

    private void parentsChildrenUpdate(workflow_properties properties){
    }


    /*******************************************************************
     * Save Image
     ******************************************************************/

    public void saveImage(String filename){
        BufferedImage bi;
        try{
            bi = new Robot().createScreenCapture(this.getBounds());
            ImageIO.write(bi, "png", new File(filename));
            this.setVisible(false);
         } catch (Exception ex) {
            Config.log("Unable to save "+filename+" dialog image");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AO_CVCO_JLabel;
    private javax.swing.JCheckBox AO_CVCO__multiallelicHYPHENSYMBOLcaller_box;
    private javax.swing.JLabel AO_FFO_JLabel;
    private javax.swing.JComboBox AO_FFO__outputHYPHENSYMBOLtype_JComboBoxValue;
    private javax.swing.JCheckBox AO_FFO__outputHYPHENSYMBOLtype_box;
    private javax.swing.JLabel AO_IOO_JLabel;
    private javax.swing.JCheckBox AO_IOO__variantsHYPHENSYMBOLonly_box;
    private javax.swing.JPanel AO_jPanel;
    private javax.swing.JRadioButton Advanced_Options;
    private javax.swing.JRadioButton Default_Options;
    private javax.swing.ButtonGroup Menu_Buttons;
    private javax.swing.JTabbedPane bcftools_call_tab;
    private javax.swing.JButton close_jButton;
    private javax.swing.JButton docker_jButton;
    private javax.swing.JPanel general_jPanel1;
    private javax.swing.JButton how_jButton;
    private javax.swing.JScrollPane main_jScroll;
    private javax.swing.JLabel name_jLabel;
    private javax.swing.JTextField name_jTextField;
    private javax.swing.JTabbedPane options_tab_panel;
    private javax.swing.JButton reset_jButton;
    private javax.swing.JButton run_jButton;
    private javax.swing.JButton stop_jButton;
    // End of variables declaration//GEN-END:variables
    }

