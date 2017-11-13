/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package editors;

import editor.dockerEditor;
import configuration.Config;
import editor.EditorInterface;
import configuration.Util;
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
public class miRcheck_fold_inverted_repeats_Editors extends javax.swing.JDialog implements EditorInterface  {
    
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
    
    public miRcheck_fold_inverted_repeats_Editors(java.awt.Frame parent, armadillo_workflow parent_workflow) {
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
        jLabel1 = new javax.swing.JLabel();
        name_jTextField1 = new javax.swing.JTextField();
        reset_jButton = new javax.swing.JButton();
        stop_jButton = new javax.swing.JButton();
        run_jButton = new javax.swing.JButton();
        default_jbutton = new javax.swing.JRadioButton();
        advanced_options_jbutton = new javax.swing.JRadioButton();
        Options_panel = new javax.swing.JTabbedPane();
        Patscan_panel = new javax.swing.JPanel();
        C_flankingNt_value = new javax.swing.JSpinner();
        C_flankingNt_box = new javax.swing.JCheckBox();
        close_jButton = new javax.swing.JButton();
        how_jButton = new javax.swing.JButton();
        docker_jButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        miRcheck.setPreferredSize(new java.awt.Dimension(405, 604));
        miRcheck.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                miRcheckComponentShown(evt);
            }
        });

        jLabel1.setText("(re)Name");

        name_jTextField1.setText("miRcheck - fold inverted repeats");
        name_jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                name_jTextField1FocusLost(evt);
            }
        });
        name_jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                name_jTextField1ActionPerformed(evt);
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

        buttonGroup1.add(default_jbutton);
        default_jbutton.setText("default options");
        default_jbutton.setName("default_jbutton"); // NOI18N
        default_jbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                default_jbuttonActionPerformed(evt);
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

        Options_panel.setPreferredSize(new java.awt.Dimension(341, 413));

        C_flankingNt_value.setModel(new javax.swing.SpinnerNumberModel(-1, -1, null, 1));
        C_flankingNt_value.setName("C_flankingNt_value"); // NOI18N
        C_flankingNt_value.setPreferredSize(new java.awt.Dimension(118, 28));
        C_flankingNt_value.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                C_flankingNt_valueStateChanged(evt);
            }
        });

        C_flankingNt_box.setText("flanking nt");
        C_flankingNt_box.setName("C_flankingNt_box"); // NOI18N
        C_flankingNt_box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C_flankingNt_boxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Patscan_panelLayout = new javax.swing.GroupLayout(Patscan_panel);
        Patscan_panel.setLayout(Patscan_panelLayout);
        Patscan_panelLayout.setHorizontalGroup(
            Patscan_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Patscan_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(C_flankingNt_box)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addComponent(C_flankingNt_value, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        Patscan_panelLayout.setVerticalGroup(
            Patscan_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Patscan_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Patscan_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(C_flankingNt_value, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C_flankingNt_box))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Options_panel.addTab("adcanced options", Patscan_panel);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(reset_jButton)
                                .addGap(33, 33, 33)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(stop_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(run_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(name_jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(default_jbutton)
                        .addGap(18, 18, 18)
                        .addComponent(advanced_options_jbutton))
                    .addComponent(Options_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reset_jButton)
                    .addComponent(stop_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(run_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(name_jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(default_jbutton)
                    .addComponent(advanced_options_jbutton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Options_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        miRcheck.addTab("miRcheck - fold inverted repeats", jPanel1);

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
        docker_jButton.setName("docker_jButton"); // NOI18N
        docker_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                docker_jButton_ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(close_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(docker_jButton)
                        .addGap(18, 18, 18)
                        .addComponent(how_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(miRcheck, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(close_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(how_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(docker_jButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(miRcheck, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    private void miRcheckComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_miRcheckComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_miRcheckComponentShown
    
    private void C_flankingNt_valueStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_C_flankingNt_valueStateChanged
        // TODO add your handling code here:
        Util.boxEventSpinner(properties,C_flankingNt_box,C_flankingNt_value);
    }//GEN-LAST:event_C_flankingNt_valueStateChanged
 
    private void advanced_options_jbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_advanced_options_jbuttonActionPerformed
        // TODO add your handling code here:
        Util.buttonEventSpinner(properties,advanced_options_jbutton,null);
        if (properties.isSet(default_jbutton.getName())) {
            properties.remove(default_jbutton.getName());
        }
        menuFields();
    }//GEN-LAST:event_advanced_options_jbuttonActionPerformed

    private void default_jbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_default_jbuttonActionPerformed
        // TODO add your handling code here:
        if (properties.isSet(advanced_options_jbutton.getName())) {
            properties.remove(advanced_options_jbutton.getName());
        }
        Util.buttonEventSpinner(properties,default_jbutton,null);
        menuFields();
    }//GEN-LAST:event_default_jbuttonActionPerformed

    private void C_flankingNt_boxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C_flankingNt_boxActionPerformed
        // TODO add your handling code here:
        Util.boxEventSpinner(properties,C_flankingNt_box,C_flankingNt_value);
    }//GEN-LAST:event_C_flankingNt_boxActionPerformed

    private void name_jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_name_jTextField1ActionPerformed

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

    private void docker_jButton_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_docker_jButton_ActionPerformed
        // TODO add your handling code here:
        dockerEditorProgram dock = new dockerEditorProgram(this.frame, false, properties);
        dock.setVisible(true);
    }//GEN-LAST:event_docker_jButton_ActionPerformed

    private void name_jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_name_jTextField1FocusLost
        // TODO add your handling code here:
        String s = name_jTextField1.getText();
        if (!s.equals("")){
            properties.put("Name", name_jTextField1.getText());
        }
    }//GEN-LAST:event_name_jTextField1FocusLost
    
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
        if (properties.isSet(C_flankingNt_value.getName())){
            this.C_flankingNt_value.setValue(Integer.parseInt(properties.get(C_flankingNt_value.getName())));
            this.C_flankingNt_value.setEnabled(false);
        }
    }
    
    private void usp_boxANDbutton() {
        if (properties.isSet(C_flankingNt_box.getName())){
            this.C_flankingNt_box.setSelected(true);
            this.C_flankingNt_value.setEnabled(true);
        }
        
    }
    
    /*******************************************************************
     * Set Menu fields
     ******************************************************************/
    
    private void menuFields() {
        if (properties.isSet(default_jbutton.getName())) {
            this.default_jbutton.setSelected(true);
            enabled_Advanced_Options(false);
        }
        else if (properties.isSet(advanced_options_jbutton.getName())) {
            this.advanced_options_jbutton.setSelected(true);
            enabled_Advanced_Options(true);
        }
    }
        

    /*******************************************************************
    * Enabled Function
    *******************************************************************/
    
    private void enabled_Advanced_Options (boolean e) {
        this.C_flankingNt_box.setEnabled(e);
        if (properties.isSet(C_flankingNt_box.getName()) && e==true) {
            this.C_flankingNt_value.setEnabled(true);
        } else {
            this.C_flankingNt_value.setEnabled(false);
        }
    }

    /*******************************************************************
     * Set With default program values present in properties file
     ******************************************************************/
    private void defaultPgrmValues() {
        boolean b = true;
        if (!(properties.isSet(default_jbutton.getName()))
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
    private javax.swing.JCheckBox C_flankingNt_box;
    private javax.swing.JSpinner C_flankingNt_value;
    private javax.swing.JTabbedPane Options_panel;
    private javax.swing.JPanel Patscan_panel;
    private javax.swing.JRadioButton advanced_options_jbutton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton close_jButton;
    private javax.swing.JRadioButton default_jbutton;
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
