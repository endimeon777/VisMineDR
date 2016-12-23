/*
 * IconoArbol.java
 *
 * Created on 5 de febrero de 2010, 14:57
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gui.Icons.Cluster.viewsClus;

import algorithm.classification.c45_1.TableModelImportT;
import algorithm.classification.c45_1.TreeCounter;
//import algorithm.classification.c45_1.AlgoritmoC45Hilo;
import algorithm.classification.mate.MateThread;
import algorithm.classification.mate.MainMate;
import algorithm.cluster.Atributo;
import algorithm.cluster.clarans.clarans;
import algorithm.cluster.jerarquico.Nodo;
import algorithm.cluster.similitud.Distancia;
import gui.manual.Algoritmos;
import gui.manual.C45;
import gui.manual.Mate;
import gui.manual.Sliq;
import gui.KnowledgeFlow.help;
import gui.KnowledgeFlow.ChooserEscritorio;
//import gui.Icons.Filters.Selection.Seleccion;
//import gui.Icons.Prediction.Prediction;
import gui.KnowledgeFlow.Icon;
import gui.KnowledgeFlow.JackAnimation;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JOptionPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;

/**
 *
 * 
 */
public class IconoArbol extends Icon {
    
    private JMenuItem mnuConfigure;
    public JMenuItem mnuRun;
    public JMenuItem mnuHelp;
    
    public String algorithm;
    public AbstractTableModel dataIn;
    public AbstractTableModel dataSalidad;
    public TreeCounter c;  //en lugar de un TreeCounter deberia almacenarse un
    public Atributo root; //Atributo con la raiz del arbol de decision
  // public opcioneskmeas cp;
    public double minRows = 25.0;
    public double trainingSet = 100.0;
    AbstractTableModel dataOut1;
    public AbstractTableModel dataOut2; 
    public Connection conexion = null;
    public String sqlCreado="";
    public String sqlColumnas="";
    public double threshold = 100.0;
    public  LinkedList lista = new LinkedList(); //crea una estrutura de datos
    public  LinkedList liscluster = new LinkedList(); //crea una estrutura de datos
    public  AbstractTableModel medoide;  //crea una estrutura de datos
    public int [] centroideCerca;
    public int numcluster=2;
    public    Object[] atriColumnas;  
    public Nodo raiz;
    public Distancia distanciakm =new Distancia();
    
    private static miVerBirch view;
  
    public IconoArbol(JLabel s, int x, int y, int indiceIcon) {
       super(s, x, y, indiceIcon);
        super.constrainsTo = new ArrayList(1);
        super.constrainsTo.add("IconoArbolDinamico");//Restricciones de conexion (a que iconos se puede conectar un icono de clasificacion)
        super.constrainsTo.add("IconoArbolWeka");
        super.constrainsTo.add("IconoArbolTexto");
        
        algorithm = s.getText();
        
     /*   mnuConfigure = new javax.swing.JMenuItem();
        mnuConfigure.setText("Configurar...");
        mnuConfigure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuConfigureActionPerformed(evt);
            }
        });
        super.pupMenu.add(mnuConfigure);
        */
        mnuRun = new javax.swing.JMenuItem();
        mnuRun.setText("View...");
        mnuRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRunActionPerformed(evt);
            }
        });
     //    mnuRun.setEnabled(false);
        super.pupMenu.add(mnuRun);
        view = new miVerBirch();
        view.setVisible(false);
        
        mnuHelp = new javax.swing.JMenuItem();
        mnuHelp.setText("Help...");
        mnuHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuHelpActionPerformed(evt);
            }
        });
        super.pupMenu.add(mnuHelp);
        
        this.setInfo("Cofigurando Parametros...");
    }
    
  private void mnuConfigureActionPerformed(java.awt.event.ActionEvent evt) {
     
        String [] nomAtributos;
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 //if(!algorithm.trim().equals("Sliq")){
             /*       cp = new opcioneskmeas(ci);
                    cp.setLocation(200,200);
                    cp.setnumcluster(numcluster);
                    cp.setDistancia(distanciakm.getTipo());
                     cp.setVisible(true);*/
                // }
            }
        });     
               
        
    }
    
    private void mnuHelpActionPerformed(java.awt.event.ActionEvent evt) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            public void run() {
                                
                    if(ChooserEscritorio.ayuda==null){
                     ChooserEscritorio.ayuda = new help(new Algoritmos());
                     ChooserEscritorio.ayuda.setVisible(true);
                   } else
               { ChooserEscritorio.ayuda.dispose();
                 ChooserEscritorio.ayuda = new help(new Algoritmos());
                 ChooserEscritorio.ayuda.setVisible(true);
               }
                
            }
        });
    }
   
    private void mnuRunActionPerformed(java.awt.event.ActionEvent evt) {
       view.ejecutar(raiz);
       view.setLocationRelativeTo(null);
       view.setVisible(true);  
    }
    
    public void setNumcluster(int num){
        this.numcluster=num;
    }
    
    public TableModelImportT changeToTariyModel(){
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
        TableModelImportT tariyModel = new TableModelImportT();
        tariyModel.setDatos(data);
        tariyModel.setNomcol(columnsName);
        
        return tariyModel;
    }
}

