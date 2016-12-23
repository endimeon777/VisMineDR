/*
 * RulesIcon.java
 *
 * Created on 26 de mayo de 2006, 10:17
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gui.Icons.Rules;

import Utils.AssocRules;
import Utils.DataSet;
import gui.KnowledgeFlow.Chooser;
import gui.KnowledgeFlow.Icon;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

public class RulesIcon extends Icon{
    private JMenuItem mnuConfigure;
    private JMenuItem mnuView;
    private JMenuItem mnuHelp;
    public configureConfidence cc;
    public double support;
    double confidence;
    public Vector trees;
    public DataSet dataset;
    public String title;
    
    /** Creates a new instance of DBConnectionIcon */
    public RulesIcon(JLabel s, int x, int y) {
        super(s, x, y);
        super.constrainsTo = new ArrayList(0);
        confidence = 50.0;
        setInfo("Confidence in " + confidence + "%");

        mnuConfigure = new javax.swing.JMenuItem();
        mnuConfigure.setText("Configure...");
        mnuConfigure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuConfigureActionPerformed(evt);
            }
        });
        super.pupMenu.add(mnuConfigure);
        
        mnuView = new javax.swing.JMenuItem();
        mnuView.setText("Run...");
        mnuView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuViewActionPerformed(evt);
            }
        });
        super.pupMenu.add(mnuView);
        
        mnuHelp = new javax.swing.JMenuItem();
        mnuHelp.setText("Help...");
        mnuHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuHelpActionPerformed(evt);
            }
        });
        super.pupMenu.add(mnuHelp);
    }
    
    private void mnuConfigureActionPerformed(java.awt.event.ActionEvent evt) {
        final RulesIcon ri = this;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                cc = new configureConfidence(ri);
                cc.setVisible(true);
            }
        });
    }
    
    private void mnuHelpActionPerformed(java.awt.event.ActionEvent evt) {
        final Icon icon = this;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Utils.Help(icon.getName().trim()).setVisible(true);
            }
        });
    }
    
    private void mnuViewActionPerformed(java.awt.event.ActionEvent evt) {
        AssocRules ar = new AssocRules(trees, dataset.getDictionary(), (int)confidence);
        ar.buildRules();
        new showRules(ar,dataset.getName() + " - " + title, Double.toString(support), Double.toString(confidence)).setVisible(true);
        this.setInfo(Chooser.getStatus());
    }
    
    /** Creates a new instance of AssociationIcon */
    
}
