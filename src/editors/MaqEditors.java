/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package editors;

import configuration.Config;
import editor.EditorInterface;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import program.*;
import workflows.armadillo_workflow;
import workflows.workflow_properties;
import workflows.workflow_properties_dictionnary;

/**
 *
 * @author Brisée-pas-morte
 */
public class MaqEditors extends javax.swing.JDialog implements EditorInterface  {

    /**
     * Creates new form MaqEditors
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
     
    public MaqEditors(java.awt.Frame parent, armadillo_workflow parent_workflow) {
       super(parent, false);
        this.parent_workflow=parent_workflow;
        //--Set variables and init
        frame=parent;
    }
     public void setDefaultValues(){
     map_jRadioButton1.setSelected(default_map);
 
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
        jButton1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        name_jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        map_jRadioButton1 = new javax.swing.JRadioButton();
        mapcheck_jRadioButton2 = new javax.swing.JRadioButton();
        mapview_jRadioButton3 = new javax.swing.JRadioButton();
        convert_jRadioButton4 = new javax.swing.JRadioButton();
        assemble_jRadioButton4 = new javax.swing.JRadioButton();
        cns2fq_jRadioButton6 = new javax.swing.JRadioButton();
        reset_jButton3 = new javax.swing.JButton();
        stop_jButton4 = new javax.swing.JButton();
        run_jButton5 = new javax.swing.JButton();
        ClosejButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton1.setText("?");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTabbedPane1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jTabbedPane1ComponentShown(evt);
            }
        });

        jButton2.setText("Rename");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        name_jTextField1.setText("jTextField1");

        jLabel1.setText("Name");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 7, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(name_jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(name_jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        buttonGroup1.add(map_jRadioButton1);
        map_jRadioButton1.setSelected(true);
        map_jRadioButton1.setText("Map (map reads to a reference genome)");
        map_jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                map_jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(mapcheck_jRadioButton2);
        mapcheck_jRadioButton2.setText("Mapcheck");
        mapcheck_jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mapcheck_jRadioButton2ActionPerformed(evt);
            }
        });

        buttonGroup1.add(mapview_jRadioButton3);
        mapview_jRadioButton3.setText("Mapview (extract sequences information)");
        mapview_jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mapview_jRadioButton3ActionPerformed(evt);
            }
        });

        buttonGroup1.add(convert_jRadioButton4);
        convert_jRadioButton4.setText("Convert fasta to reference genome");
        convert_jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                convert_jRadioButton4ActionPerformed(evt);
            }
        });

        buttonGroup1.add(assemble_jRadioButton4);
        assemble_jRadioButton4.setText("Assemble (assemble reads to the reference)");
        assemble_jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assemble_jRadioButton4ActionPerformed(evt);
            }
        });

        buttonGroup1.add(cns2fq_jRadioButton6);
        cns2fq_jRadioButton6.setText("Consensus (extract consensus sequences)");
        cns2fq_jRadioButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cns2fq_jRadioButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mapview_jRadioButton3)
                    .addComponent(mapcheck_jRadioButton2)
                    .addComponent(map_jRadioButton1)
                    .addComponent(convert_jRadioButton4)
                    .addComponent(assemble_jRadioButton4)
                    .addComponent(cns2fq_jRadioButton6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(map_jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(convert_jRadioButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mapcheck_jRadioButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mapview_jRadioButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(assemble_jRadioButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cns2fq_jRadioButton6)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        reset_jButton3.setText("Reset default value");
        reset_jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_jButton3ActionPerformed(evt);
            }
        });

        stop_jButton4.setText("Stop");
        stop_jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stop_jButton4ActionPerformed(evt);
            }
        });

        run_jButton5.setText("Run");
        run_jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                run_jButton5ActionPerformed(evt);
            }
        });

        ClosejButton6.setText("Close");
        ClosejButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClosejButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(reset_jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stop_jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(run_jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ClosejButton6)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reset_jButton3)
                    .addComponent(stop_jButton4)
                    .addComponent(run_jButton5)
                    .addComponent(ClosejButton6))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("MaqEditor", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("MaqEditor");

        pack();
    }// </editor-fold>//GEN-END:initComponents

 
    private void jTabbedPane1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTabbedPane1ComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1ComponentShown

    private void ClosejButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClosejButton6ActionPerformed
        // TODO add your handling code here:
         this.setVisible(false);
    }//GEN-LAST:event_ClosejButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
          HelpEditor help = new HelpEditor(this.frame, false, properties);
        help.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void reset_jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_jButton3ActionPerformed
        // TODO add your handling code here:
         properties.load();             //--reload current properties from file
        this.setProperties(properties);//--Update current field
    }//GEN-LAST:event_reset_jButton3ActionPerformed

    private void stop_jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stop_jButton4ActionPerformed
        // TODO add your handling code here:
        properties.put("Status", Config.status_nothing);
        properties.killThread();
    }//GEN-LAST:event_stop_jButton4ActionPerformed

    private void run_jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_run_jButton5ActionPerformed
        // TODO add your handling code here:
           if(properties.isSet("ClassName")) {
           this.parent_workflow.workflow.updateDependance();
           programs prog=new programs(parent_workflow.workbox.getCurrentWorkflows());
           prog.Run(properties);
    }//GEN-LAST:event_run_jButton5ActionPerformed
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        properties.put("Name", this.name_jTextField1.getText());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void map_jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_map_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        properties.put("map", map_jRadioButton1.isSelected());
    }//GEN-LAST:event_map_jRadioButton1ActionPerformed

    private void mapcheck_jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mapcheck_jRadioButton2ActionPerformed
        // TODO add your handling code here:
         properties.put("mapcheck", mapcheck_jRadioButton2.isSelected());
    }//GEN-LAST:event_mapcheck_jRadioButton2ActionPerformed

    private void mapview_jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mapview_jRadioButton3ActionPerformed
        // TODO add your handling code here:
         properties.put("mapview", mapview_jRadioButton3.isSelected());
    }//GEN-LAST:event_mapview_jRadioButton3ActionPerformed

    private void convert_jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_convert_jRadioButton4ActionPerformed
        properties.put("convert",convert_jRadioButton4.isSelected());
    }//GEN-LAST:event_convert_jRadioButton4ActionPerformed

    private void assemble_jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assemble_jRadioButton4ActionPerformed
       properties.put("assemble",assemble_jRadioButton4.isSelected());
    }//GEN-LAST:event_assemble_jRadioButton4ActionPerformed

    private void cns2fq_jRadioButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cns2fq_jRadioButton6ActionPerformed
      properties.put("cns2fq", this.cns2fq_jRadioButton6.isSelected());
    }//GEN-LAST:event_cns2fq_jRadioButton6ActionPerformed

    
    
    
       public void setProperties(workflow_properties properties) {
        this.properties=properties;
        setTitle(properties.getName());      
        //if (properties.isSet("Description")) this.Notice.setText(properties.get("Description"));
        setSettingForProperties();
        
    }
     public void setProperties(String filename, String path) {
        workflow_properties tmp=new workflow_properties();
        tmp.load(filename, path);
        this.properties=tmp;
        setTitle(properties.getName());
       setSettingForProperties();
     
    }
     /**
      * Set the configuration properties for this object
      */
    public void setSettingForProperties() {
           this.name_jTextField1.setText(properties.getName());
           if (properties.getBoolean("map")) this.map_jRadioButton1.setSelected(true);
           if (properties.getBoolean("mapview")) this.mapview_jRadioButton3.setSelected(true);
           if (properties.getBoolean("mapcheck")) this.mapcheck_jRadioButton2.setSelected(true);
           if (properties.getBoolean("convert")) this.convert_jRadioButton4.setSelected(true);
           if (properties.getBoolean("assemble")) this.assemble_jRadioButton4.setSelected(true);
           if (properties.getBoolean("cns2fq")) this.cns2fq_jRadioButton6.setSelected(true);
     }
    
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
    private javax.swing.JButton ClosejButton6;
    private javax.swing.JRadioButton assemble_jRadioButton4;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton cns2fq_jRadioButton6;
    private javax.swing.JRadioButton convert_jRadioButton4;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton map_jRadioButton1;
    private javax.swing.JRadioButton mapcheck_jRadioButton2;
    private javax.swing.JRadioButton mapview_jRadioButton3;
    private javax.swing.JTextField name_jTextField1;
    private javax.swing.JButton reset_jButton3;
    private javax.swing.JButton run_jButton5;
    private javax.swing.JButton stop_jButton4;
    // End of variables declaration//GEN-END:variables
}
