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

import configuration.Config;
import biologic.Blast;
import biologic.BlastHit;
import biologic.seqclasses.blastParser;
import workflows.workflow_properties;
import configuration.Config;
import database.AbstractTreeModel;
import editor.BlastTableCellRenderer;
import editor.BlastTableModel;
import editor.BlastTableSorter;
import editor.BlastTreeNode;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import editor.EditorInterface;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.tree.TreeSelectionModel;
import workflows.armadillo_workflow;
import workflows.workflow_properties_dictionnary;

/**
 *
 * @author Etienne Lord
 * @since July 2009
 */
public class BlastViewEditor extends javax.swing.JDialog implements EditorInterface {

    ////////////////////////////////////////////////////////////////////////////
    /// VARIABLES

    Config config=new Config();
    //ConnectorInfoBox connectorinfobox;
    workflow_properties_dictionnary dict=new workflow_properties_dictionnary();    
    Frame frame;
    workflow_properties properties;
    armadillo_workflow parent_workflow;
    BlastTreeNode selected=null;
    blastParser blast=new blastParser();
    private boolean stateSelected=true;             //do we Select or Unselect

    ////////////////////////////////////////////////////////////////////////////
    /// CONSTANT

    public final String defaultNameString=" Name";
    final String lastSearch="";
    static final int MODE_ID=0;
    static final int MODE_ACCESSION=1;
    static final int MODE_DESC=2;
    static final int MODE_ALIASES=3;
    static final int MODE_ALL=4;
    static final int MODE_LENMORE=6;
    static final int MODE_LENLESS=7;

    /////////////////////////////////////////////////////////////////////////
    /// Default Options    

    /** Creates new form propertiesJDialog */
    public BlastViewEditor(java.awt.Frame parent, armadillo_workflow parent_workflow) {
        super(parent, false);
        this.parent_workflow=parent_workflow;
        this.parent_workflow.workflow.updateDependance();
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        Filter_ComboBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        SelectUnselectSequence_jButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        ClosejButton = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setTitle("Properties");

        jTabbedPane1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jTabbedPane1ComponentShown(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Select BlastHit"));

        BlastTableModel bm=new BlastTableModel();
        jTable3.setModel(bm);
        jTable3.setRowSorter(new BlastTableSorter(bm));
        jTable3.setDefaultRenderer(Double.class, new BlastTableCellRenderer());
        jScrollPane4.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        Filter_ComboBox.setEditable(true);
        Filter_ComboBox.setModel(new javax.swing.DefaultComboBoxModel(Config.ClusteringOption));
        Filter_ComboBox.setToolTipText("Filter your results. Enter a search string");
        Filter_ComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Filter_ComboBoxActionPerformed(evt);
            }
        });

        jLabel1.setText("Filter Blast Results");

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("No blast results loaded.");
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTree1MouseClicked(evt);
            }
        });
        jTree1.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jTree1ValueChanged(evt);
            }
        });
        jTree1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTree1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTree1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Filter_ComboBox, 0, 574, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Filter_ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
        );

        SelectUnselectSequence_jButton.setText("Select / Unselect");
        SelectUnselectSequence_jButton.setToolTipText("<html>Select or Unselect the current results selection. <br>If nothing is selected, select or unselect all the results</html>");
        SelectUnselectSequence_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectUnselectSequence_jButtonActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Download options"));

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setText("Download Genbank File (files will be in tmp directory)");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jCheckBox3.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox3.setText("Download \"BEST HIT\" Only");
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox3)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jCheckBox1)
                .addComponent(jCheckBox3))
        );

        ClosejButton.setText("<html><b>Close</b></html>");
        ClosejButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClosejButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(SelectUnselectSequence_jButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ClosejButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SelectUnselectSequence_jButton)
                    .addComponent(ClosejButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Blast View Editor", jPanel9);

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
                .addContainerGap(606, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ClosejButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClosejButtonActionPerformed
        //--Clear for
        Vector<String>keys=new Vector<String>();
           for (Object k:properties.keySet()) keys.add((String)k);
           for(String key:keys) {
               if (key.startsWith("For_")) properties.remove(key);
           }
         for (Blast b:blast.getList()) {
                   
                    for (BlastHit bh:b.getBlasthit_list()) {
                    //--Match?
                         if (bh.isSelected()) {
                           properties.put("For_"+bh.getSubject_accession(), true);
                         }
                    }
                }

        this.setVisible(false);
}//GEN-LAST:event_ClosejButtonActionPerformed

    private void jTabbedPane1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTabbedPane1ComponentShown
      
}//GEN-LAST:event_jTabbedPane1ComponentShown

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        HelpEditor help = new HelpEditor(this.frame, false, properties);
        help.setVisible(true);
}//GEN-LAST:event_jButton6ActionPerformed

    private void jTree1ValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jTree1ValueChanged
     //updateTable();
    }//GEN-LAST:event_jTree1ValueChanged

    private void jTree1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree1MouseClicked
        updateTable();
    }//GEN-LAST:event_jTree1MouseClicked

    private void jTree1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTree1KeyPressed
        updateTable();
    }//GEN-LAST:event_jTree1KeyPressed

    private void Filter_ComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Filter_ComboBoxActionPerformed
        //BlastTableModel tm=(BlastTableModel)this.jTable3.getModel();
        int mode=this.Filter_ComboBox.getSelectedIndex();

        String searchString=(String)this.Filter_ComboBox.getSelectedItem();
        if (mode==0) {
            //--Reset the table
            this.createTree();
            //Message("Found "+blast.getTotalHits()+" element(s)","");
        } else {
            //--Normal search
            this.createTreeSearch(searchString);
//            Vector<Integer> resultIndex=search(searchString, this.MODE_ALL);
//            tm.data.clear();
//            for (Integer index:resultIndex) {
//                tm.addData(dataTree.get(index));
//            }
//            Message("Found "+resultIndex.size()+" element(s)","");
        }
       
}//GEN-LAST:event_Filter_ComboBoxActionPerformed

    private void SelectUnselectSequence_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectUnselectSequence_jButtonActionPerformed
        BlastTableModel model=(BlastTableModel)this.jTable3.getModel();
         int[] index=this.jTable3.getSelectedRows();
        if (index.length==0) {
             for (BlastHit b:model.data) {
                b.setSelected(stateSelected);
             }   
            stateSelected=!stateSelected;
        } else {
            //CAS 2. Inversion de la sélection
            for (int i:index) {
                i=this.jTable3.convertRowIndexToModel(i);
                model.data.get(i).setSelected(!model.data.get(i).isSelected());
            }
        }

        model.fireTableDataChanged();
        this.jTable3.setModel(model);
     
}//GEN-LAST:event_SelectUnselectSequence_jButtonActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
       properties.put("download_genbank", this.jCheckBox1.isSelected());
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
       properties.put("download_best", this.jCheckBox3.isSelected());
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    public void updateTable() {
        selected = (BlastTreeNode) this.jTree1.getLastSelectedPathComponent();
        BlastTableModel model=(BlastTableModel)this.jTable3.getModel();
         model.data.clear();
        if (selected!=null&&selected.blast!=null) {
        for (BlastHit bh:selected.blast.getBlasthit_list()) {
            model.data.add(bh);
        }
        model.fireTableDataChanged();
        this.jTable3.setModel(model);
        }
    }


     /**
     * 1. Create the tree model from the database table.
     * 2. Set the properties
     * tree1 is the first parent, tree2 the first child, etc..
     */
    public void createTree(){

        //--Set the tree and selection model

        BlastTreeNode tree1 = new BlastTreeNode("Blast Report");
        tree1.setLeaf(false);                                      
                for (Blast b:blast.getList()) {
                    BlastTreeNode query=new BlastTreeNode(b, "Query="+b.queryname+" ("+b.getBlasthit_list().size()+" hits)");
                    query.setLeaf(true);
                    tree1.add(query);
//                    for (BlastHit bh:b.getBlasthit_list()) {
//                    //--Add if 1. Unusual type and 2. Not default properties
//                           //BlastTreeNode n=new BlastTreeNode(bh, bh.getSubject_name()+" (Evalue="+bh.getEvalue()+")");
//                           //n.setLeaf(true);
//                           //query.add(n);
//                    }
                }
        AbstractTreeModel treeModel = new AbstractTreeModel(tree1);
        treeModel.setRoot(tree1);
        treeModel.reload();
        jTree1.setModel(treeModel);
        jTree1.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

    }
   
     /**
     * 1. Create the tree model from the database table.
     * 2. Set the properties
     * tree1 is the first parent, tree2 the first child, etc..
     */
    public void createTreeSearch(String searchString){

        //--Set the tree and selection model
       
        BlastTableModel model=(BlastTableModel)this.jTable3.getModel();
        //--
        BlastTreeNode tree1 = new BlastTreeNode("Blast Search");
        tree1.setLeaf(false);                                      
                for (Blast b:blast.getList()) {
                    //BlastTreeNode query=new BlastTreeNode(b, "Query="+b.queryname+" ("+b.getBlasthit_list().size()+" hits)");
                    //query.setLeaf(true);
                    //tree1.add(query);
                    for (BlastHit bh:b.getBlasthit_list()) {
                    //--Match?
                         if (bh.toString().indexOf(searchString)>-1) {
                           BlastTreeNode n=new BlastTreeNode(bh, bh.getSubject_name()+" (Evalue="+bh.getEvalue()+")");
                           n.setLeaf(true);
                           //tree1.add(n);
                           model.data.add(bh);
                         }
                    }
                }
        model.fireTableDataChanged();
        this.jTable3.setModel(model);
        //Message("Found "+tree1.getChildCount()+" element(s).","");
        AbstractTreeModel treeModel = new AbstractTreeModel(tree1);
        treeModel.setRoot(tree1);
        treeModel.reload();
        jTree1.setModel(treeModel);
        jTree1.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

    }

    /**
     * This set the Properties
     * @param properties
     */
    public void setProperties(workflow_properties properties) {
        this.properties=properties;
        setTitle(properties.getName());             
        Pattern key_value=Pattern.compile("For_(.*)", Pattern.CASE_INSENSITIVE);
        int blast_id=properties.getOutputID("blast");
        if (blast_id==0) blast_id=properties.getInputID("blast");
        Config.log(String.valueOf(blast_id));
        if (blast_id>0) {
            this.jTree1.setEnabled(true);
            this.Filter_ComboBox.setEnabled(true);
            Blast bh=new Blast(blast_id);
            blast.loadText(bh.getText());
            //--For found
              for (Object k:properties.keySet()) {
                 Matcher m=key_value.matcher((String)k);
                 if (m.find()) {                    
                    String value=m.group(1);
                    //--Set selected in blastList
                     for (Blast b:blast.getList()) {                   
                        for (BlastHit bh2:b.getBlasthit_list()) {
                        if (bh2.getSubject_accession().equals(value)) bh2.setSelected(true);                         
                    }
                }
                    
                 }
             }
            this.createTree();
        } else {
            //Don't allow selection and filter
            this.jTree1.setEnabled(false);
            this.Filter_ComboBox.setEnabled(false);
        }

        if (properties.isSet("download_genbank")) this.jCheckBox1.setSelected(properties.getBoolean("download_genbank"));
        //if (properties.isSet("force_download")) this.jCheckBox2.setSelected(properties.getBoolean("force_download"));
        if (properties.isSet("download_best")) this.jCheckBox3.setSelected(properties.getBoolean("download_best"));

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



      ////////////////////////////////////////////////////////////////////////////
    /// Search and Message

    boolean search (String regex, int mode) {
        Pattern p;
        try {
            p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        } catch(java.util.regex.PatternSyntaxException e) {return false;}
        int search_len=0;
        try {
            search_len=Integer.valueOf(regex);
        } catch (Exception e) {search_len=0;}
//        switch (mode) {
//            case MODE_ID:       lastSearch="Id with: "+regex;
//                                for (int i=0; i<dataTree.size();i++) {
//                                      ForMutableTreeNode data=dataTree.get(i);
//                                      Matcher m = p.matcher(data.getGi());
//                                      Matcher m2 = p.matcher(data.getAccession());
//                                      if (m.find()||m2.find()) returnArray.add(i);
//                                }
//                                break;
//            case MODE_ACCESSION:lastSearch="Accession with: "+regex;
//                                for (int i=0; i<MultipleInfoSequence.size();i++) {
//                                     InfoSequence data=MultipleInfoSequence.get(i);
//                                      Matcher m = p.matcher(data.getAccession());
//                                      if (m.find()) returnArray.add(i);
//                                }
//                                break;
//            case MODE_DESC:  lastSearch="Description with: "+regex;
//                                for (int i=0; i<MultipleInfoSequence.size();i++) {
//                                      InfoSequence data=MultipleInfoSequence.get(i);
//                                       Matcher m = p.matcher(data.getName());
//                                      if (m.find()) returnArray.add(i);
//                                }
//                                break;
//            case MODE_LENMORE: lastSearch="Len(bp) greater: "+regex;
//                                for (int i=0; i<MultipleInfoSequence.size();i++) {
//                                    InfoSequence data=MultipleInfoSequence.get(i);
//                                    try {
//                                        int len=data.getLen();
//                                        if (len>=search_len) returnArray.add(i);
//                                    } catch(Exception e) {}
//                                }
//                                break;
//             case MODE_LENLESS: lastSearch="Len(bp) greater: "+regex;
//                                for (int i=0; i<MultipleInfoSequence.size();i++) {
//                                    InfoSequence data=MultipleInfoSequence.get(i);
//                                    try {
//                                        int len=Integer.valueOf(data.getLen());
//                                        if (len<=search_len) returnArray.add(i);
//                                    } catch(Exception e) {}
//                                }
//                                break;
//            case MODE_ALL:      lastSearch="All with: "+regex;
//                                for (int i=0; i<dataTree.size();i++) {
//                                    ForMutableTreeNode data=dataTree.get(i);
//                                    Matcher m1 = p.matcher(data.toString());
//
//                                    if (m1.find()) returnArray.add(i);
//                                }
//        } //end switch
//        Config.log("Searching for "+lastSearch);
//        System.out.printf(" found %d result(s)\n", returnArray.size());
//        return returnArray;
           return false;
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
    private javax.swing.JComboBox Filter_ComboBox;
    private javax.swing.JButton SelectUnselectSequence_jButton;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable3;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables



}
