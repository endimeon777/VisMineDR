/*
 * RulesIcon.java
 *
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gui.Icons.Rules;

import Utils.AssocRules;
import Utils.DataSet;
import gui.KnowledgeFlow.ChooserEscritorio;
import gui.KnowledgeFlow.Icon;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

public class RulesIcon extends Icon{
    private JMenuItem mnuConfigure;
    private JMenuItem mnuView;
    private JMenuItem mnuHelp;
    
    public double support;
    public double confidence;
    public Vector trees;
    public DataSet dataset;
    public String title;
    
    public static miconfigureConfidence cc;
    
    /** Creates a new instance of DBConnectionIcon */
    public RulesIcon(JLabel s, int x, int y, int indiceIcon) {
        super(s, x, y, indiceIcon);
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
        cc = new miconfigureConfidence(this);
        cc.setVisible(false);
        
        mnuView = new javax.swing.JMenuItem();
        mnuView.setText("View...");
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
                cc.updateIcon(ri);
                cc.setVisible(true);
            }
        });
    }
    
    private void mnuHelpActionPerformed(java.awt.event.ActionEvent evt) {
        final Icon icon = this;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new Utils.Help(icon.getName().trim()).setVisible(true);
                new Utils.miHelp("asociacion.htm").setVisible(true);
            }
        });
    }
    
    private void mnuViewActionPerformed(java.awt.event.ActionEvent evt) {
        AssocRules ar = new AssocRules(trees, dataset.getDictionary(), (int)confidence);
        ar.buildRules();
        new mishowRules(ar,dataset.getName() + " - " + title, Double.toString(support), Double.toString(confidence)).setVisible(true);
        this.setInfo(ChooserEscritorio.getStatus());
    }
    
    public void updateIconConfigureConfidence(){
        this.cc.updateIcon(this); // aqui le paso los valores a visualizador
    }
    
    public JMenuItem getMnuView() {
        return mnuView;
    }
    
    /** Creates a new instance of AssociationIcon */
    
}
