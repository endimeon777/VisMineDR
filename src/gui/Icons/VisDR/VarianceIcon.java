/*
 * AssociationIcon.java
 *
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gui.Icons.VisDR;

import Utils.AvlTree;
import Utils.DataSet;
import Utils.MachineLearning.plot.plot.Palette;
import Utils.MachineLearning.plot.plot.PlotCanvas;
import Utils.MachineLearning.plot.plot.ScatterPlot;
import algorithm.reduction.mds.MDS;
import algorithm.reduction.projection.PCA;
import gui.Icons.DBConnection.DBConnectionIcon;
import gui.Icons.Rules.RulesIcon;
import gui.KnowledgeFlow.ChooserEscritorio;
import gui.KnowledgeFlow.Icon;
import gui.KnowledgeFlow.JackAnimation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 */
public class VarianceIcon extends Icon{
    
    private JMenuItem mnuRun;
    private JMenuItem mnuView;
    private JMenuItem mnuHelp;
    
    private JPanel pane;
    private String title;
     
    public PCA pca;

    /** Creates a new instance of DBConnectionIcon */
    public VarianceIcon(JLabel s, int x, int y, int indiceIcon) {
        super(s, x, y, indiceIcon);

        setInfo("Variance in process");
             
        mnuRun = new javax.swing.JMenuItem();
        mnuRun.setText("Run...");
        mnuRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRunActionPerformed(evt);
            }
        });
        super.pupMenu.add(mnuRun);
        mnuRun.setEnabled(false);
        
        mnuView = new javax.swing.JMenuItem();
        mnuView.setText("View...");
        mnuView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuViewActionPerformed(evt);
            }
        });
        super.pupMenu.add(mnuView);
        mnuView.setEnabled(false);
        
        mnuHelp = new javax.swing.JMenuItem();
        mnuHelp.setText("Help...");
        mnuHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuHelpActionPerformed(evt);
            }
        });
        super.pupMenu.add(mnuHelp);
    }
    
    public JMenuItem getMnuView() {
        return mnuView;
    }
    
    public JMenuItem getMnuRun() {
        return mnuRun;
    }
     
    private void mnuRunActionPerformed(java.awt.event.ActionEvent evt) {

        pane = new JPanel(new GridLayout(1, 2));
        
        JackAnimation jack = new JackAnimation();
        this.add(jack);
        this.setComponentZOrder(jack, 0);
        jack.setBounds(this.animation.getX(), this.animation.getY(), 36, 36);
        this.setAnimation(jack);
        this.startAnimation();
         
//      este pone la curva de las componentes principales
        PlotCanvas scree = PlotCanvas.screeplot(pca);
        scree.setTitle("Variance");
        pane.add(scree);
        
        title = "Principal Component Analysis";
        this.stopAnimation();
        mnuView.setEnabled(true);
    }
    
    private void mnuViewActionPerformed(java.awt.event.ActionEvent evt) {
        final VarianceIcon icon = this;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame f = new JFrame(title);
                f.setSize(new Dimension(800, 700));
                f.setLocationRelativeTo(null);
        //                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.getContentPane().add(pane);
                f.setVisible(true);
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
}
