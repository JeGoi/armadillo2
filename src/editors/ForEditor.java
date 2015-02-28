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

import biologic.Biologic;
import biologic.Output;
import configuration.Config;
import configuration.excelAdapterTable;
import database.AbstractTreeModel;
import database.databaseFunction;
import editor.EditorInterface;
import editor.ForMutableTreeNode;
import editor.ForTableModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.tree.TreeSelectionModel;
import workflows.workflow_properties;
import workflows.armadillo_workflow;
import workflows.armadillo_workflow.workflow_object;
import workflows.workflow_properties_dictionnary;

public class ForEditor extends javax.swing.JDialog implements EditorInterface {

    databaseFunction df=new databaseFunction();
    Frame frame;
    workflow_properties properties;
    armadillo_workflow parent_workflow;
    workflow_properties_dictionnary dic=new workflow_properties_dictionnary();
    ////////////////////////////////////////////////////////////////////////////
    /// SELECTED VARIABLE

    ForMutableTreeNode selected=null;
     Vector<ForMutableTreeNode>dataTree=new Vector<ForMutableTreeNode>();

    ////////////////////////////////////////////////////////////////////////////
    // Constante
    // Search
    String lastSearch="";
    static final int MODE_ID=0;
    static final int MODE_ACCESSION=1;
    static final int MODE_DESC=2;
    static final int MODE_ALIASES=3;
    static final int MODE_ALL=4;
    static final int MODE_LENMORE=6;
    static final int MODE_LENLESS=7;

   /////////////////////////////////////////////////////////////////////////////
   /// Constructor

    public ForEditor(java.awt.Frame parent, armadillo_workflow parent_workflow) {
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

        jComboBox2 = new javax.swing.JComboBox();
        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        NamejTextField = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Filter_ComboBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        AddjButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        NTimejComboBox = new javax.swing.JComboBox();
        RepeatjCheckBox1 = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        ClosejButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Repeat");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setText("Name");

        jButton4.setText("Rename");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NamejTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel2)
                .addComponent(NamejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton4))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Possible variables", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), new java.awt.Color(255, 153, 51))); // NOI18N

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTree1MouseClicked(evt);
            }
        });
        jTree1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTree1FocusGained(evt);
            }
        });
        jTree1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTree1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTree1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Selected value for each loop"));

        jTable1.setModel(new ForTableModel());
        jScrollPane2.setViewportView(jTable1);

        Filter_ComboBox.setEditable(true);
        Filter_ComboBox.setModel(new javax.swing.DefaultComboBoxModel(Config.ClusteringOption));
        Filter_ComboBox.setToolTipText("Filter your results. Enter a search string");
        Filter_ComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Filter_ComboBoxActionPerformed(evt);
            }
        });

        jLabel1.setText("Filter Value");

        AddjButton.setText("Add Value");
        AddjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddjButtonActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("Note: Unselected values will be lost...");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Filter_ComboBox, 0, 493, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addContainerGap())
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                .addComponent(AddjButton))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Filter_ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(AddjButton))
                .addGap(10, 10, 10))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Repeat"));

        NTimejComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100" }));
        NTimejComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NTimejComboBoxActionPerformed(evt);
            }
        });
        NTimejComboBox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                NTimejComboBoxFocusLost(evt);
            }
        });

        RepeatjCheckBox1.setText("Repeat program (N=)");
        RepeatjCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RepeatjCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(RepeatjCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NTimejComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(260, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(RepeatjCheckBox1)
                .addComponent(NTimejComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        jScrollPane3.setViewportView(jPanel3);

        jButton1.setText("<html><b>Ok</b></html>");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        ClosejButton.setText("Close");
        ClosejButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClosejButtonActionPerformed(evt);
            }
        });

        jButton2.setText("Remove Repeat");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ClosejButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ClosejButton)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Repeat Editor", jPanel5);

        jButton7.setText("?");
        jButton7.setToolTipText("Help / Informations");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton7)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                        .addGap(17, 17, 17))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ClosejButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClosejButtonActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_ClosejButtonActionPerformed

    private void jTree1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree1MouseClicked
       //--Display the selected variable in the Variable JTextField
       selected = (ForMutableTreeNode) this.jTree1.getLastSelectedPathComponent();      
        this.updateUI();
       //--TO DO HERE..Test variables
       
    }//GEN-LAST:event_jTree1MouseClicked

    private void jTree1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTree1KeyReleased
        //--Display the selected variable in the Variable JTextField
       selected = (ForMutableTreeNode) this.jTree1.getLastSelectedPathComponent();
       this.updateUI();
    }//GEN-LAST:event_jTree1KeyReleased

    private void Filter_ComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Filter_ComboBoxActionPerformed
        ForTableModel tm=(ForTableModel)this.jTable1.getModel();
        int mode=this.Filter_ComboBox.getSelectedIndex();
        
        String searchString=(String)this.Filter_ComboBox.getSelectedItem();
        if (mode==0) {
            //--Reset the table
            tm.data.clear();
            tm.setData(dataTree);
            //Message("Found "+dataTree.size()+" element(s)","");
        } else {
                //--Normal search
                Vector<Integer> resultIndex=search(searchString, this.MODE_ALL);               
                tm.data.clear();
                for (Integer index:resultIndex) {
                    tm.addData(dataTree.get(index));
                }
              //  Message("Found "+resultIndex.size()+" element(s)","");
        }
        tm.fireTableDataChanged();
        this.jTable1.setModel(tm);
}//GEN-LAST:event_Filter_ComboBoxActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        if (this.RepeatjCheckBox1.isSelected()) {
            this.Repeat_Object();
        } else {
            this.ForLoop_Object();
        }
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void NTimejComboBoxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NTimejComboBoxFocusLost
       this.Repeat_Object();
    }//GEN-LAST:event_NTimejComboBoxFocusLost

    private void RepeatjCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RepeatjCheckBox1ActionPerformed
        boolean repeat=this.RepeatjCheckBox1.isSelected();        
        properties.put("Repeat", this.RepeatjCheckBox1.isSelected());
        this.Repeat_Object();

    }//GEN-LAST:event_RepeatjCheckBox1ActionPerformed

    private void NTimejComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NTimejComboBoxActionPerformed
       this.Repeat_Object();
    }//GEN-LAST:event_NTimejComboBoxActionPerformed

    private void AddjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddjButtonActionPerformed
        ForTableModel model=(ForTableModel)this.jTable1.getModel();
        if (selected!=null) {
            ForMutableTreeNode n=new ForMutableTreeNode(selected.getProperties(),selected.getName(),selected.getValue());
            n.setSelected(true);
             dataTree.add(n);
             //--Save to object
             properties.put("For_"+selected.getValue(),selected.getName());
            model.setData(dataTree);
            model.fireTableDataChanged();
            model.fireTableStructureChanged();
            this.jTable1.setModel(model);
        }

    }//GEN-LAST:event_AddjButtonActionPerformed

    private void jTree1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTree1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTree1FocusGained

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        properties.put("Name", this.NamejTextField.getText());
        parent_workflow.updateCurrentWorkflow(properties);
}//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        properties.remove("ForObjectID");
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        HelpEditor help = new HelpEditor(this.frame, false, properties);
        help.setVisible(true);
}//GEN-LAST:event_jButton7ActionPerformed

    void updateUI() {
        if (!properties.getBoolean("Repeat")) {
            //--UI
            this.jTree1.setEnabled(true);
            this.jTable1.setEnabled(true);
            this.AddjButton.setEnabled(true);
            this.Filter_ComboBox.setEnabled(true);
            //this.SelectUnselectSequence_jButton.setEnabled(true);
            //--Logic             
        } else {
           //--UI
           this.jTree1.setEnabled(false);
           this.jTable1.setEnabled(false);
           this.AddjButton.setEnabled(false);
           this.Filter_ComboBox.setEnabled(false);
           //this.SelectUnselectSequence_jButton.setEnabled(false);
        }
    }

    /**
     * Called when we start the program...
     * @param properties
     */
    public void setValueTable(workflow_properties properties) {
         //--Value Table
        dataTree.clear();
        ForTableModel model=(ForTableModel)this.jTable1.getModel();
        Pattern key_value=Pattern.compile("For_(.*)", Pattern.CASE_INSENSITIVE);
         //--Duplicate list and add new properties
         for (Object k:properties.keySet()) {
                 Matcher m=key_value.matcher((String)k);
                 if (m.find()) {
                    String key=properties.get(k);
                    String value=(m.group(1));
                    ForMutableTreeNode n=new ForMutableTreeNode(properties,key, value);
                    n.setSelected(true);
                    dataTree.add(n);
                 }
        }
        if (dataTree.size()==0&&selected!=null) {
            ForMutableTreeNode n=new ForMutableTreeNode(selected.getName(),selected.getValue());
            n.setSelected(true);
            dataTree.add(n);
            //--Save to object
            properties.put("For_"+selected.getValue(),selected.getName());
        }
        model.setData(dataTree);
        model.fireTableDataChanged();
        model.fireTableStructureChanged();
        this.jTable1.setModel(model);
    }


    public void Repeat_Object() {
        boolean repeat=properties.getBoolean(("Repeat"));
        //--Clear previous repeat
        Vector<String>keys=new Vector<String>();
           for (Object k:properties.keySet()) keys.add((String)k);
           for(String key:keys) {
               if (key.startsWith("For_")) properties.remove(key);
           }        
        if (repeat) {
                try {
                    int ntime=Integer.valueOf((String)this.NTimejComboBox.getSelectedItem());
                    for (int i=0; i<ntime;i++) {
                        properties.put("For_"+i,"");
                    }
                    properties.put("Ntime", ntime);
                } catch(Exception e) {
            }        
        } 
        updateUI();
        parent_workflow.updateCurrentWorkflow(properties);
    }

    public void ForLoop_Object() {
       if (selected!=null) {
           Vector<String>keys=new Vector<String>();
           for (Object k:properties.keySet()) keys.add((String)k);
           for(String key:keys) {
               if (key.startsWith("For_")) properties.remove(key);
           }
           properties.put("ForObjectID", selected.getProperties().getID());
           ForTableModel model=(ForTableModel)this.jTable1.getModel();
           for (ForMutableTreeNode n:model.data) {
               if (n.isSelected()) {
                   properties.put("For_"+n.getValue(), n.getName());
               }
           }
       }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddjButton;
    private javax.swing.JButton ClosejButton;
    private javax.swing.JComboBox Filter_ComboBox;
    private javax.swing.JComboBox NTimejComboBox;
    private javax.swing.JTextField NamejTextField;
    private javax.swing.JCheckBox RepeatjCheckBox1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables

    /**
     * 1. Create the tree model from the database table.
     * 2. Set the properties
     * tree1 is the first parent, tree2 the first child, etc..
     */
    public void createTree(){
        //--Set the tree and selection model
        ForMutableTreeNode tree1 = new ForMutableTreeNode("Possible variables","");
        tree1.setLeaf(false); 
             ForMutableTreeNode parent=new ForMutableTreeNode(properties,properties.getName(),"");
             tree1.add(parent);
                ForMutableTreeNode tree2 = (ForMutableTreeNode) tree1.getLastLeaf();
                parent.setLeaf(false);
                for (Object k:properties.keySet()) {
                    String key=(String)k;
                    //--Add if 1. Unusual type and 2. Not default properties
                    if (!dic.isValidValue(key, properties.get(key))&&!key.startsWith("default")&&!key.startsWith("output_")&&!key.startsWith("input_")&&!key.startsWith("For_")) {
                        ForMutableTreeNode n=new ForMutableTreeNode(properties,key, properties.get(key));
                        String name=properties.getName()+"."+key;
                        tree2.add(n);
                    }
                }
       
        AbstractTreeModel treeModel = new AbstractTreeModel(tree1);
        treeModel.setRoot(tree1);
        treeModel.reload();
        jTree1.setModel(treeModel);
        jTree1.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        //--Set some properties


        this.setValueTable(properties);       
        this.RepeatjCheckBox1.setSelected(properties.getBoolean("Repeat"));
        if(properties.isSet("Ntime")) this.NTimejComboBox.setSelectedItem(properties.get("Ntime"));

    }
    
   /////////////////////////////////////////////////////////////////////////////
   /// Main display
    
    public void display(workflow_properties properties) {
       this.properties=properties;
        initComponents();
        this.NamejTextField.setText(properties.getName());
        //excelAdapterTable myAd = new excelAdapterTable(jTable1);
        this.createTree();
        //Message("Note: Some variables might not be available if set to \"Default\".","");
        setIconImage(Config.image);
        //updateUI();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension d = getSize();
        setLocation((screenSize.width-d.width)/2,
					(screenSize.height-d.height)/2);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
    }

    ////////////////////////////////////////////////////////////////////////////
    /// Search and Message

    Vector<Integer> search (String regex, int mode) {
        Vector<Integer> returnArray = new Vector<Integer>();
        Pattern p;
        try {
            p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        } catch(java.util.regex.PatternSyntaxException e) {return returnArray;}
        int search_len=0;
        try {
            search_len=Integer.valueOf(regex);
        } catch (Exception e) {search_len=0;}
        switch (mode) {
            case MODE_ID:       lastSearch="Id with: "+regex;
                                for (int i=0; i<dataTree.size();i++) {
                                      ForMutableTreeNode data=dataTree.get(i);
//                                      Matcher m = p.matcher(data.getGi());
//                                      Matcher m2 = p.matcher(data.getAccession());
//                                      if (m.find()||m2.find()) returnArray.add(i);
                                }
                                break;
            case MODE_ACCESSION:lastSearch="Accession with: "+regex;
//                                for (int i=0; i<MultipleInfoSequence.size();i++) {
//                                     InfoSequence data=MultipleInfoSequence.get(i);
//                                      Matcher m = p.matcher(data.getAccession());
//                                      if (m.find()) returnArray.add(i);
//                                }
                                break;
            case MODE_DESC:  lastSearch="Description with: "+regex;
//                                for (int i=0; i<MultipleInfoSequence.size();i++) {
//                                      InfoSequence data=MultipleInfoSequence.get(i);
//                                       Matcher m = p.matcher(data.getName());
//                                      if (m.find()) returnArray.add(i);
//                                }
                                break;
            case MODE_LENMORE: lastSearch="Len(bp) greater: "+regex;
//                                for (int i=0; i<MultipleInfoSequence.size();i++) {
//                                    InfoSequence data=MultipleInfoSequence.get(i);
//                                    try {
//                                        int len=data.getLen();
//                                        if (len>=search_len) returnArray.add(i);
//                                    } catch(Exception e) {}
//                                }
                                break;
             case MODE_LENLESS: lastSearch="Len(bp) greater: "+regex;
//                                for (int i=0; i<MultipleInfoSequence.size();i++) {
//                                    InfoSequence data=MultipleInfoSequence.get(i);
//                                    try {
//                                        int len=Integer.valueOf(data.getLen());
//                                        if (len<=search_len) returnArray.add(i);
//                                    } catch(Exception e) {}
//                                }
                                break;
            case MODE_ALL:      lastSearch="All with: "+regex;
                                for (int i=0; i<dataTree.size();i++) {
                                    ForMutableTreeNode data=dataTree.get(i);
                                    Matcher m1 = p.matcher(data.toString());
                                    
                                    if (m1.find()) returnArray.add(i);
                                }
        } //end switch
        Config.log("Searching for "+lastSearch);
        System.out.printf(" found %d result(s)\n", returnArray.size());
        return returnArray;
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
    
}
