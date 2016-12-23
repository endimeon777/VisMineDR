/*
 * StandarizeIcon.java
 *
 * Created on 2 de julio de 2009, 16:45
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gui.Icons.Filters.Standarize;

import algorithm.classification.c45_1.TableModelImportT;
import algorithm.classification.c45_1.TreeCounter;
//import algorithm.classification.c45_1.AlgoritmoC45Hilo;
//import algorithm.classification.mate.MateThread;
//import algorithm.classification.mate.MainMate;
import algorithm.cluster.Atributo;
//import algorithm.cluster.clarans.clarans;
import algorithm.cluster.similitud.DistanciaM;
//import gui.Icons.Cluster.kmeans.miAbrirStandarize;
//import gui.Icons.Cluster.kmeans.miShowClukmeans;
import gui.manual.Algoritmos;
//import gui.manual.C45;
//import gui.manual.Mate;
//import gui.manual.Sliq;
import gui.KnowledgeFlow.help;
import gui.KnowledgeFlow.ChooserEscritorio;
//import gui.Icons.Filters.Selection.Seleccion;
//import gui.Icons.Prediction.Prediction;
import gui.KnowledgeFlow.Icon;
//import gui.KnowledgeFlow.JackAnimation;
//import java.awt.BorderLayout;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedList;
//import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
//import javax.swing.JOptionPane;
//import javax.swing.JOptionPane;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author vismine
 */
public class StandarizeIcon extends Icon{
    private JMenuItem mnuConfigure;
    public JMenuItem mnuRun;
    public JMenuItem mnuView;
    public JMenuItem mnuHelp;
    public String algorithm;
    public AbstractTableModel dataIn;
    public TreeCounter c;  //en lugar de un TreeCounter deberia almacenarse un
    public Atributo root; //Atributo con la raiz del arbol de decision
    public double minRows = 25.0;
    public double trainingSet = 100.0;
    public AbstractTableModel dataOut1;
    public AbstractTableModel dataOut2; 
    public AbstractTableModel dataSalida; 
    public Connection conexion = null;
    public String sqlCreado="";
    public String sqlColumnas="";
    public double threshold = 100.0;
    public  LinkedList lista = new LinkedList(); //crea una estrutura de datos
    public int numcluster = 2;
    public DistanciaM distanciakm = new DistanciaM();
    public boolean colNumericalS[];
    public boolean colStandarizeS[];
     
    public static miAbrirStandarize open;
    public static miVerStandarize view;
    
  //   private    Object[] atriColumnas; 
     /**
     * Creates a new instance of IconoConexionBD
     */
   
    /** Creates a new instance of StandarizeIcon */
    
    
    public StandarizeIcon(JLabel s, int x, int y, int indiceIcon) {
        
        super(s, x, y, indiceIcon);
        super.constrainsTo = new ArrayList(1);
        super.constrainsTo.add("clusterIcon");//Restricciones de conexion (a que iconos se puede conectar un icono de clasificacion)
        super.constrainsTo.add("FilterIcon");
        
        algorithm = s.getText();
        
        mnuConfigure = new javax.swing.JMenuItem();
        mnuConfigure.setText("Configure...");
        mnuConfigure.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuConfigureActionPerformed(evt);
            }
        });
        super.pupMenu.add(mnuConfigure);
        open = new miAbrirStandarize();
        open.setVisible(false);
        
        mnuRun = new javax.swing.JMenuItem();
        mnuRun.setText("Run...");
        mnuRun.setEnabled(false);
        mnuRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRunActionPerformed(evt);
            }
        });
     //    mnuRun.setEnabled(false);
        super.pupMenu.add(mnuRun);
        
        
        mnuView = new javax.swing.JMenuItem();
        mnuView.setText("View...");
        mnuView.setEnabled(false);
        mnuView.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuViewActionPerformed(evt);
            }
        });
        super.pupMenu.add(mnuView);
        view = new miVerStandarize();
        view.setVisible(false);
        
        
        mnuHelp = new javax.swing.JMenuItem();
        mnuHelp.setText("Help...");
        mnuHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuHelpActionPerformed(evt);
            }
        });
        super.pupMenu.add(mnuHelp);
        
        this.setInfo("Configuring...");
    }
    
  private void mnuConfigureActionPerformed(java.awt.event.ActionEvent evt) {
        final StandarizeIcon ci = this;
//        String [] nomAtributos;
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {            
                    open.updateIcon(ci);
                    open.setLocation(200,200);
                    open.setVisible(true);      
            }
        });                   
    }
    
    private void mnuHelpActionPerformed(java.awt.event.ActionEvent evt) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            public void run() {
                new Utils.miHelp("Standarize.htm").setVisible(true); 
            }
        });
    }
    
   private void mnuRunActionPerformed(java.awt.event.ActionEvent evt) {        
        distanciakm.setAtriColumna(open.getAtriColumnas());
        setDatasalida(open.getData());
        this.colNumericalS = open.getColNumerical();
        mnuView.setEnabled(true);
    }
   
   public JMenuItem getMnuRun() {
        return mnuRun;
    }
   
   private void mnuViewActionPerformed(java.awt.event.ActionEvent evt) {    
//     System.out.println("algortimo sel "+ algorithm);
     miVerStandarize verdatos = new miVerStandarize();
     view.setDatos(dataIn,dataSalida);
     view.setLocationRelativeTo(null);
     view.setVisible(true);
     view.ejecutar();
    }
   
   public JMenuItem getMnuView() {
        return mnuView;
    }
   
   public void setNumcluster(int num){
        this.numcluster=num;
    }
    
   public void setDatasalida(AbstractTableModel dasal){
       this.dataSalida = dasal;
    }
   
   public void mostrar(){
        for (int i=0;i<dataSalida.getRowCount();i++){
            for (int j=0;j<dataSalida.getColumnCount();j++){
                System.out.print("   "+dataSalida.getValueAt(i,j));
            }
             System.out.println("");
        }
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
    
   public void updtateIconStandarize(){
        this.open.updateIcon(this); // aqui le paso los valores a visualizador
    }
    
}