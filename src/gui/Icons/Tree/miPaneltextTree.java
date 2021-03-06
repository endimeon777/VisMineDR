/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * miPaneltextTree.java
 *
 * Created on 31-may-2012, 14:33:16
 */
package gui.Icons.Tree;

import Utils.ExampleFileFilter;
import Utils.FileManager;
import Utils.GraphDistribution.Grafico;
import Utils.TableOptimalWidth;
import algorithm.classification.Value;
import algorithm.classification.c45_1.Attribute;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Juan Carlos
 */
public class miPaneltextTree extends javax.swing.JPanel {
    
    LinkedList rules;
    TreeTableModel tblModel;
    String error, textTree;
    boolean orderConfidence = true;
    boolean orderFrecuence = true;
    boolean orderClass = true;
    boolean orderLength = true;
    private Attribute leaf;
    private ArrayList colors;
    
    /** Creates new form PanelTableRules */
    public miPaneltextTree(String tree) {
      initComponents();  
      textTree = tree;  
      jTextArea1.setText(tree);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Save = new javax.swing.JFileChooser();
        pupGraph = new javax.swing.JPopupMenu();
        mnuGraph = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        mnuGraph.setText("View Distribution");
        mnuGraph.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuGraphActionPerformed(evt);
            }
        });
        pupGraph.add(mnuGraph);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/filesave.png"))); // NOI18N
        jButton1.setText("Save Tree");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ExampleFileFilter ext = new ExampleFileFilter("tree", "Structure Tree");
        String path;
        FileManager fm;
        
        Save.addChoosableFileFilter(ext);
        int saveOK = Save.showSaveDialog(this);
        if(saveOK == Save.APPROVE_OPTION) {
            path = Save.getSelectedFile().getAbsolutePath();
            path += ".tree";
            fm = new FileManager(path);
            fm.writeString("Structure Tree \n\n");
            fm.writeString(textTree);
        }
}//GEN-LAST:event_jButton1ActionPerformed

    private void mnuGraphActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuGraphActionPerformed
        // TODO add your handling code here:
        new Grafico(leaf, colors).setVisible(true);
}//GEN-LAST:event_mnuGraphActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser Save;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JMenuItem mnuGraph;
    private javax.swing.JPopupMenu pupGraph;
    // End of variables declaration//GEN-END:variables
}
