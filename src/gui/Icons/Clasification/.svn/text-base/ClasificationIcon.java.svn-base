/*
 * AssociationIcon.java
 *
 * Created on 26 de mayo de 2006, 10:17
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gui.Icons.Clasification;


import algorithm.classification.c45_1.Attribute;
//import algorithm.classification.c45_1.Prediction;
import algorithm.classification.c45_1.TariyTableModel;
import algorithm.classification.c45_1.TreeCounter;
import algorithm.classification.c45_1.TreeViewer;
import algorithm.classification.mate.MainMate;
import gui.Icons.Filters.Selection.Seleccion;
import gui.Icons.Prediction.Prediction;
import gui.KnowledgeFlow.Icon;
import gui.KnowledgeFlow.JackAnimation;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ivan
 */
public class ClasificationIcon extends Icon{
    private JMenuItem mnuConfigure;
    public JMenuItem mnuRun;
    public JMenuItem mnuHelp;
    private String algorithm;
    public AbstractTableModel dataIn;
    public TreeCounter c;  //en lugar de un TreeCounter deberia almacenarse un
    public Attribute root; //Attribute con la raiz del arbol de decision
    public configureParameters cp;
    public double minRows = 25.0;
    public double trainingSet = 100.0;
    AbstractTableModel dataOut1;
    public AbstractTableModel dataOut2; 
    
    public double threshold = 100.0;
    /** Creates a new instance of DBConnectionIcon */
    public ClasificationIcon(JLabel s, int x, int y) {
        super(s, x, y);
        super.constrainsTo = new ArrayList(1);
        super.constrainsTo.add("HierarchicalTreeIcon");//Restricciones de conexion (a que iconos se puede conectar un icono de clasificacion)
        super.constrainsTo.add("WekaTreeIcon");
        super.constrainsTo.add("TextTreeIcon");
        super.constrainsTo.add("PredictionIcon");//Restricciones de conexion (a que iconos se puede conectar un icono de clasificacion)
        algorithm = s.getText();
        
        mnuConfigure = new javax.swing.JMenuItem();
        mnuConfigure.setText("Configure...");
        mnuConfigure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuConfigureActionPerformed(evt);
            }
        });
        super.pupMenu.add(mnuConfigure);
        
        mnuRun = new javax.swing.JMenuItem();
        mnuRun.setText("Run...");
        mnuRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRunActionPerformed(evt);
            }
        });
        mnuRun.setEnabled(false);
        super.pupMenu.add(mnuRun);
        
        mnuHelp = new javax.swing.JMenuItem();
        mnuHelp.setText("Help...");
        mnuHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuHelpActionPerformed(evt);
            }
        });
        super.pupMenu.add(mnuHelp);
        
        this.setInfo("Without\nParameters...");
    }
    
    private void mnuConfigureActionPerformed(java.awt.event.ActionEvent evt) {
        final ClasificationIcon ci = this;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                cp = new configureParameters(ci);
                cp.setVisible(true);
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
    
    private void mnuRunActionPerformed(java.awt.event.ActionEvent evt) {
        JackAnimation jack = new JackAnimation();
        this.add(jack);
        this.setComponentZOrder(jack, 0);
        jack.setBounds(this.animation.getX(), this.animation.getY(), 36, 36);
        this.setAnimation(jack);
        System.out.println(algorithm);
        if(algorithm.trim().equals("C45")){
            //this.startAnimation();
            TariyTableModel tariyData = this.changeToTariyModel();
            //System.out.println(cp.minRows);
            int minIntegerRows = (int)(minRows*tariyData.getRowCount()/100);
            c = new TreeCounter(tariyData, minIntegerRows, threshold, this);
            this.startAnimation();
            c.setAnimation(jack);
            c.start();
        } else if(algorithm.trim().equals("Mate")){
            int rows = dataOut1.getRowCount();
            int columns = dataOut1.getColumnCount();
            Object[][] data = new Object[rows][columns];
            String[] columnsName = new String[columns];
            for(int i = 0; i < columns; i++){
                for(int j = 0; j < rows; j++){
                    data[j][i] = dataOut1.getValueAt(j ,i);
                }
                columnsName[i] = dataOut1.getColumnName(i);
            }
            gui.Icons.Filters.TariyTableModel tariyData
                    = new gui.Icons.Filters.TariyTableModel(data, columnsName);
            int minIntegerRows = (int)(minRows*tariyData.getRowCount()/100);
            MainMate mm = new MainMate(tariyData,(int)threshold, minIntegerRows, this);
            this.startAnimation();
            mm.setAnimation(jack);
            mm.start();
        }
    }
    
    public TariyTableModel changeToTariyModel(){
        int rows = dataOut1.getRowCount();
        int columns = dataOut1.getColumnCount();
        Object[][] data = new Object[rows][columns];
        String[] columnsName = new String[columns];
        for(int i = 0; i < columns; i++){
            for(int j = 0; j < rows; j++){
                data[j][i] = dataOut1.getValueAt(j ,i);
            }
            columnsName[i] = dataOut1.getColumnName(i);
        }
        TariyTableModel tariyModel = new TariyTableModel();
        tariyModel.setDatos(data);
        tariyModel.setNomcol(columnsName);
        
        return tariyModel;
    }
}
