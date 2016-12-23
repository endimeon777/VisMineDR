/*
 * RulesIcon.java
 *
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gui.Icons.Tree;

import algorithm.classification.c45_1.Attribute;
import algorithm.classification.c45_1.C45TreeGUI;
import algorithm.classification.c45_1.TreeCounter;
import algorithm.classification.c45_1.TreeViewer;
import gui.KnowledgeFlow.ChooserEscritorio;
import gui.KnowledgeFlow.Icon;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.AbstractTableModel;

public class TextTreeIcon extends Icon{
    
    private JMenuItem mnuRun;
    public JMenuItem mnuView;
    private JMenuItem mnuHelp;
    
    public StringBuffer RulesText;
    public AbstractTableModel dataTest;
    public Attribute root;
    public String texErrorM;
    
    public static JPanel TreePanel;
    private static javax.swing.JScrollPane scrollTree;
    private static miViewerClasification vc;
    
    /** Creates a new instance of DBConnectionIcon */
    public TextTreeIcon(JLabel s, int x, int y, int indiceIcon) {
        super(s, x, y, indiceIcon);
        super.constrainsTo = new ArrayList(0);
        
        mnuRun = new javax.swing.JMenuItem();
        mnuRun.setText("Run...");
        mnuRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRunActionPerformed(evt);
            }
        });
        //mnuRun.setEnabled(false);
        super.pupMenu.add(mnuRun);
        
        mnuView = new javax.swing.JMenuItem();
        mnuView.setText("View...");
        mnuView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuViewActionPerformed(evt);
            }
        });
        super.pupMenu.add(mnuView);
        mnuView.setEnabled(false);
//        vc = new miViewerClasification();
//        vc.setVisible(false);
        
        mnuHelp = new javax.swing.JMenuItem();
        mnuHelp.setText("Help...");
        mnuHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuHelpActionPerformed(evt);
            }
        });
        super.pupMenu.add(mnuHelp);
//        vc = null;
    }
    
    private void mnuHelpActionPerformed(java.awt.event.ActionEvent evt) {
        final Icon icon = this;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Utils.Help(icon.getName().trim()).setVisible(true);
            }
        });
    }
    
    private void mnuRunActionPerformed(java.awt.event.ActionEvent evt) {
        DecimalFormat confidenceFormat = new DecimalFormat();
        confidenceFormat.setMaximumFractionDigits(3);
        int f = 0, rows = 0, columns, colnode = 0;
        String cad,atri;
        Attribute auxiliar;
        float ErrorMissing = 0, datosWrong = 0;
        
        rows = dataTest.getRowCount();
        columns = dataTest.getColumnCount()-1;
        auxiliar = root.son;
        
        while(f < rows){
            
            StringTokenizer token = new StringTokenizer(auxiliar.name,"=");
            cad = token.nextToken().trim();
            
            if(auxiliar.son!=null){
                colnode = getColNode(cad);
            } else colnode = columns;
            
            cad = token.nextToken().trim();
            atri = dataTest.getValueAt(f,colnode).toString().trim();
            
            if(auxiliar.son == null){
                if(cad.equalsIgnoreCase(atri)){
                    datosWrong++;
                }
                f++;
                auxiliar = root.son;
            } else if(auxiliar.son != null && cad.equalsIgnoreCase(atri)){
                auxiliar = auxiliar.son;
            } else if(auxiliar.brother != null){
                auxiliar = auxiliar.brother;
            } else {
                f++;
                auxiliar = root.son;
            }
        }
        
        if(datosWrong == 0){
            ErrorMissing = 0;
        } else{
            ErrorMissing = ((datosWrong/rows)*100);
        }
        texErrorM = confidenceFormat.format(ErrorMissing);
        
        ChooserEscritorio.setStatus("Text Tree loaded, Confidence Tree : " + texErrorM + "%");
        this.setInfo("Confidence Tree : " + texErrorM + "%");
     
        mnuView.setEnabled(true);
    }
    
    private void mnuViewActionPerformed(java.awt.event.ActionEvent evt) {
        final TextTreeIcon tti = this;
        
        Thread view = new Thread(new Runnable() {
            miPaneltextTree pnlTree;
            public void run() {
                if(vc == null){
                    TreeCounter c =  new TreeCounter();
                    String tree = c.getTextTree(root);
                    scrollTree = new javax.swing.JScrollPane();
                    pnlTree = new miPaneltextTree(tree);
                    scrollTree.setViewportView(pnlTree); 
                    vc = new miViewerClasification(root, "Text Tree", scrollTree, texErrorM, tti);
                    vc.setVisible(true);
                } else if(!vc.getRoot().equals(root)){
                    TreeCounter c =  new TreeCounter();
                    String tree = c.getTextTree(root);
                    scrollTree = new javax.swing.JScrollPane();
                    pnlTree = new miPaneltextTree(tree);
                    scrollTree.setViewportView(pnlTree);
                    vc = new miViewerClasification(root, "Text Tree", scrollTree, texErrorM, tti);
                    vc.setVisible(true);
                }else {
                    vc.setVisible(true);
                }
                stopAnimation();
            }
        });
        this.startAnimation();
        view.start();
    }
    
    public int getColNode(String colt){
        int numcol = 0, columns = 0;
        
        boolean compatibilidad = false;
        columns = dataTest.getColumnCount();
        
        for(int i = 0; i < columns; i++ ){
            if(colt.equalsIgnoreCase(dataTest.getColumnName(i))){
                numcol = i;
                compatibilidad = true;
                break;
            }
        }
        return numcol;
    }
    
    public void updateIconViewerClasification(){
//        this.vc.updateIcon(this); // aqui le paso los valores a visualizador
        miPaneltextTree pnlTree;
        TreeCounter c =  new TreeCounter();
        String tree = c.getTextTree(root);
        scrollTree = new javax.swing.JScrollPane();
        pnlTree = new miPaneltextTree(tree);
        scrollTree.setViewportView(pnlTree); 
        vc = new miViewerClasification(root, "Text Tree", scrollTree, texErrorM, this);
//        vc.updateIcon(root, "Text Tree", scrollTree, texErrorM, this);
    }
    
    public JMenuItem getMnuView() {
        return mnuView;
    }
    
    /** Creates a new instance of AssociationIcon */
    
}
