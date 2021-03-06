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

package biologic.seqclasses;


import configuration.Config;
import configuration.Util;
import database.ExplorerTreeMutableTreeNode;
import database.databaseFunction;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
 
/**
 * RenameComboBox for propertiesName
 * Note: return the propertiesName, it will be empty if cancelled
 *
 * @author Etienne Lord
 * @since July 2009
 */
public class StandardInputSequenceJDialog extends javax.swing.JDialog {

    ////////////////////////////////////////////////////////////////////////////
    /// Variables
    databaseFunction df = new databaseFunction();    //Access to database
    Config config=new Config();                      //Configuration file
    Frame frame;                                     //parent frame

    private String defaultGroupName="";      //Old group filename
    public String groupName="";             //New group filename
    ExplorerTreeMutableTreeNode current;    //the current displayed node (if not a new node)
    private int status=0;                   // Returned status code

    ////////////////////////////////////////////////////////////////////////////
    /// Constructor
   
    /**
     * Default constructor for new input
     * @param parent
     * @param defaultGroupName
     * @param RenameButtonText
     * @param type
     */
    public StandardInputSequenceJDialog(java.awt.Frame parent, String defaultGroupName,String RenameButtonText, String type) {
        super(parent, true);
        this.defaultGroupName=defaultGroupName;
        this.frame=parent;
        initComponents();
        this.ok_jButton.setText(RenameButtonText);        
        this.jComboBox1.setSelectedItem(type);
        if (type.equals("Sequence")||type.equals("MultipleSequences")||type.equals("Alignment")||type.isEmpty()) {
            this.DNAProteinjComboBox2.setEnabled(true);
        }
        //--Special case, enable other type for MultipleSequences
        if (type.isEmpty()) {
            this.jComboBox1.removeAllItems();
            this.jComboBox1.addItem("MultipleSequences");
            this.jComboBox1.addItem("Alignment");
            this.jComboBox1.setSelectedItem("MultipleSequences");
            this.jComboBox1.setEnabled(true);
        }
        this.setIconImage(Config.image);
        this.groupName_jTextField.setText(defaultGroupName);
        this.comments_jTextField.setText("Created on "+Util.returnCurrentDateAndTime()+".");
        Message("Note: Enter a new name and note for this file...","");
        // Set position
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension d = getSize();
        setLocation((screenSize.width-d.width)/2,
					(screenSize.height-d.height)/2);
    }

     /**
      * Constructor to change informations
      * @param parent
      * @param node
      * @param RenameButtonText
      */
       public StandardInputSequenceJDialog(java.awt.Frame parent, ExplorerTreeMutableTreeNode node, String RenameButtonText) {
        super(parent, true);
        current=node;       
        this.frame=parent;
        initComponents();
        //this.RenamejButton.setText(RenameButtonText);
        this.setTitle("Add Comments to "+node.getName());
        this.comments_jTextField.setText(node.getNote());
        Message("Add comments...","");
        // Set position
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension d = getSize();
        setLocation((screenSize.width-d.width)/2,
					(screenSize.height-d.height)/2);
        this.setVisible(true);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        groupName_jTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        CanceljButton = new javax.swing.JButton();
        ok_jButton = new javax.swing.JButton();
        jStatusMessage = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        comments_jTextField = new javax.swing.JTextArea();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        DNAProteinjComboBox2 = new javax.swing.JComboBox();

        setTitle("Choose a Collection Name");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        groupName_jTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                groupName_jTextFieldKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Group (prefix)");
        jLabel1.setToolTipText("This will be added before the filename to identify the sequences");

        CanceljButton.setText("Cancel");
        CanceljButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CanceljButtonActionPerformed(evt);
            }
        });

        ok_jButton.setText("Create");
        ok_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ok_jButtonActionPerformed(evt);
            }
        });

        jStatusMessage.setForeground(new java.awt.Color(51, 51, 255));
        jStatusMessage.setText("Info");

        jLabel2.setText("Note");

        comments_jTextField.setColumns(20);
        comments_jTextField.setRows(5);
        jScrollPane1.setViewportView(comments_jTextField);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sequence", "MultipleSequences", "Alignment", "Tree", "MultipleTrees", "Matrix", "Unknown", "Text", "Results" }));
        jComboBox1.setEnabled(false);
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Type");

        DNAProteinjComboBox2.setEditable(true);
        DNAProteinjComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DNA", "RNA", "AA" }));
        DNAProteinjComboBox2.setEnabled(false);
        DNAProteinjComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DNAProteinjComboBox2ItemStateChanged(evt);
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
                        .addComponent(jStatusMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ok_jButton)
                        .addGap(8, 8, 8)
                        .addComponent(CanceljButton))
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(groupName_jTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DNAProteinjComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DNAProteinjComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(groupName_jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jStatusMessage)
                    .addComponent(ok_jButton)
                    .addComponent(CanceljButton)))
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

    private void CanceljButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CanceljButtonActionPerformed
        groupName="";
        this.status=Config.status_nothing;
        this.setVisible(false);
    }//GEN-LAST:event_CanceljButtonActionPerformed

    private void ok_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ok_jButtonActionPerformed
        this.status=Config.status_done;
        this.setVisible(false);
}//GEN-LAST:event_ok_jButtonActionPerformed

    private void groupName_jTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_groupName_jTextFieldKeyPressed
        char c=evt.getKeyChar(); //Hack to catch V_ENTER key;
       if (c==KeyEvent.VK_ENTER) {
           this.status=Config.status_done;
           this.setVisible(false);
        }
}//GEN-LAST:event_groupName_jTextFieldKeyPressed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged

}//GEN-LAST:event_jComboBox1ItemStateChanged

    private void DNAProteinjComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DNAProteinjComboBox2ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_DNAProteinjComboBox2ItemStateChanged

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        String type=(String)this.jComboBox1.getSelectedItem();
        if (type==null) return;
        if (type.equals("Sequence")||type.equals("MultipleSequences")||type.equals("Alignment")) {
            this.DNAProteinjComboBox2.setEnabled(true);
        } else {
            this.DNAProteinjComboBox2.setEnabled(false);
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    ////////////////////////////////////////////////////////////////////////////
    /// Rename

    private void createAddSequence() {
        groupName=groupName_jTextField.getText();
        //--Note: we exit only if everything is fine
        

//        if (df.checkMultipleSequencesGroupName(groupName)) {
//            Object[] options = {"Rename group name","Update Sequences"};
//            int n = JOptionPane.showOptionDialog(this,"Rename multiple sequence group or" +
//                    "update sequences?\n Warning, if you update sequences, all sequences\n" +
//                    "of the group name already in database will be replaced by the new \n" +
//                    "sequences.","Sequence group name found in Database!",
//                    JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options, options[0]);
//            if (n==0){
//                groupName_jTextField.setSelectedTextColor(null);
//                MessageErreur("You chose to rename, rename the group name","");
//            }
//        } else {
//            this.setVisible(false); //--End dialog
//        }
    }

    ///////////////////////////////////////////////////////////////////////////
    /// MESSAGE FONCTION

    /**
     * Affiche un message dans la status bar
     * La provenance peut être mise dans un tooltip
     * @param text Le texte
     * @param tooltip Le tooltip texte
     */
    void Message(String text, String tooltip) {
        this.jStatusMessage.setEnabled(true);
        this.jStatusMessage.setForeground(new java.awt.Color(0, 51, 153));
        this.jStatusMessage.setBackground(Color.WHITE);
        this.jStatusMessage.setToolTipText(tooltip);
        this.jStatusMessage.setText(text);
    }

    /**
     * Affiche un message d'erreur en rouge dans la status bar
     * La provenance peut être mise dans un tooltip
     * @param text Le texte
     * @param tooltip Le tooltip texte
     */
    void MessageErreur(String text, String tooltip) {
        this.jStatusMessage.setEnabled(true);
        this.jStatusMessage.setForeground(Color.RED);
        this.jStatusMessage.setBackground(Color.WHITE);
        this.jStatusMessage.setToolTipText(tooltip);
        this.jStatusMessage.setText(text);
    }

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CanceljButton;
    private javax.swing.JComboBox DNAProteinjComboBox2;
    private javax.swing.JTextArea comments_jTextField;
    private javax.swing.JTextField groupName_jTextField;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jStatusMessage;
    private javax.swing.JButton ok_jButton;
    // End of variables declaration//GEN-END:variables

    public String getComments() {
        return this.comments_jTextField.getText();
    }

    public String getCollectionName() {
        return groupName_jTextField.getText();
    }

     public String getBiologicType() {
        return (String)this.jComboBox1.getSelectedItem();
    }

    public String getSequenceType() {
        return (String)this.DNAProteinjComboBox2.getSelectedItem();
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }
}
