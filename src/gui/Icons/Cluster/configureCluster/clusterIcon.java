/*
 * IconoConexionBD.java
 *
 * Created on 25 de mayo de 2007, 11:00 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gui.Icons.Cluster.configureCluster;

import algorithm.classification.c45_1.TableModelImportT;
import algorithm.classification.c45_1.TreeCounter;
import algorithm.classification.mate.MainMate;
import algorithm.cluster.Atributo;
import algorithm.cluster.clarans.clarans;
import algorithm.cluster.jerarquico.Birch;
import algorithm.cluster.jerarquico.Nodo;
import algorithm.cluster.kmeans.kmeansM;
import algorithm.cluster.similitud.DistanciaM;
import gui.Icons.Cluster.viewsClus.miVerBirch;
import gui.Icons.Filters.Standarize.miVerStandarize;
import gui.manual.Algoritmos;
import gui.KnowledgeFlow.help;
import gui.KnowledgeFlow.ChooserEscritorio;
//import gui.Icons.Filters.Selection.Seleccion;
//import gui.Icons.Prediction.Prediction;
import gui.KnowledgeFlow.Icon;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author vismine
 */
public class clusterIcon extends Icon{
    private JMenuItem mnuConfigure;
    public JMenuItem mnuRun;
    public JMenuItem mnuHelp;
    
    public String algorithm;
    public AbstractTableModel dataIn;
    public TreeCounter c;  //en lugar de un TreeCounter deberia almacenarse un
    public Atributo root; //Atributo con la raiz del arbol de decision
    public double minRows = 25.0;
    public double trainingSet = 100.0;
    AbstractTableModel dataOut1;
    public AbstractTableModel dataOut2; 
    public Connection conexion = null;
    public String sqlCreado="";
    public String sqlColumnas="";
    public double threshold = 100.0;
    public  LinkedList lista = new LinkedList(); //crea una estrutura de datos
    public  AbstractTableModel medoide; //crea una estrutura de datos
    public  AbstractTableModel dataOri; //crea una estrutura de datos

    public  LinkedList listacluster = new LinkedList(); //crea una estrutura de datos
   
    public int numcluster = 2;
    public int numInteraciones = 0;
   
    public int cantidadclu = 0;
    
    public    Object[] atriColumnas;  
    public    DistanciaM distanciakm = new DistanciaM();
    public int [] centroideCerca; 
    public int numlocal = 2;
    public int maxvecinos = 3;
    
    public static JFrame Open;
    
//    public static miConfigureKmeans ck; // ventana de configuracion kmenas
//    public static miConfigureClarans cc; // ventana de configuracion clarans
//    public static miConfigureBirch cb; // ventana de configuracion birch
     /**
     * Creates a new instance of IconoConexionBD
     */
 
  public Nodo raiz;
    public clusterIcon(JLabel s, int x, int y, int indiceIcon) {
       super(s, x, y, indiceIcon);
        super.constrainsTo = new ArrayList(1);
        super.constrainsTo.add("IconoArbolDinamico");//Restricciones de conexion (a que iconos se puede conectar un icono de clasificacion)
        super.constrainsTo.add("Iconopestana");
        super.constrainsTo.add("Iconolista");
        super.constrainsTo.add("IconoGrafica");
        super.constrainsTo.add("IconoArbol");
        
        algorithm = s.getText();
        
        mnuConfigure = new javax.swing.JMenuItem();
        mnuConfigure.setText("Configure...");
        mnuConfigure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuConfigureActionPerformed(evt);
            }
        });
        super.pupMenu.add(mnuConfigure);
        
        if(algorithm.trim().equals("Kmeans")){
           Open = new miConfigureKmeans();
           Open.setVisible(false);
        } else if(algorithm.trim().equals("Clarans")){
           Open = new miConfigureClarans(); 
           Open.setVisible(false);
        }else if(algorithm.trim().equals("Birch")){
           Open = new miConfigureBirch();  
           Open.setVisible(false);
        }
        
        
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
        
        mnuHelp = new javax.swing.JMenuItem();
        mnuHelp.setText("Help...");
        mnuHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuHelpActionPerformed(evt);
            }
        });
        super.pupMenu.add(mnuHelp);
        
        this.setInfo("Setting parameters...");
    }
    
  private void mnuConfigureActionPerformed(java.awt.event.ActionEvent evt) {
        final clusterIcon ci = this;
        String [] nomAtributos;
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
 
                  if(algorithm.trim().equals("Kmeans")){
                      ((miConfigureKmeans)Open).updateIcon(ci);
                      ((miConfigureKmeans)Open).setLocationRelativeTo(null);
                      ((miConfigureKmeans)Open).setnumcluster(numcluster);
                      ((miConfigureKmeans)Open).setDistancia(distanciakm.getTipo());
                      Open.setVisible(true); 
                  }else if(algorithm.trim().equals("Clarans")){
                      ((miConfigureClarans)Open).updateIcon(ci);
                      ((miConfigureClarans)Open).setLocationRelativeTo(null);
                      ((miConfigureClarans)Open).setNumlocal(numlocal);
                      ((miConfigureClarans)Open).setMaxVecinos(maxvecinos);
                      ((miConfigureClarans)Open).setnumcluster(numcluster);
                      ((miConfigureClarans)Open).setDistancia(distanciakm.getTipo());
                      Open.setVisible(true); 
                  }else if(algorithm.trim().equals("Birch")){
                      ((miConfigureBirch)Open).updateIcon(ci);
                      ((miConfigureBirch)Open).setLocationRelativeTo(null);
                      ((miConfigureBirch)Open).setnumcluster(numcluster);
                      ((miConfigureBirch)Open).setDistancia(distanciakm.getTipo());
                      Open.setVisible(true); 
                  }  
                   
                  mnuRun.setEnabled(true);
            }
        });     
               
        
    }
  
//    private void mnuHelpActionPerformed(java.awt.event.ActionEvent evt) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            
//            public void run() {
//                 if(ChooserEscritorio.ayuda==null){
//                     ChooserEscritorio.ayuda = new help(new Algoritmos());
//                     ChooserEscritorio.ayuda.setVisible(true);
//                 }else{ 
//                    ChooserEscritorio.ayuda.dispose();
//                    ChooserEscritorio.ayuda = new help(new Algoritmos());
//                    ChooserEscritorio.ayuda.setVisible(true);
//                 }             
//            }
//        });
//    }
    
    private void mnuHelpActionPerformed(java.awt.event.ActionEvent evt) {
        final Icon icon = this;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new Utils.Help(icon.getName().trim()).setVisible(true);
                new Utils.miHelp("cluster.htm").setVisible(true);
            }
        });
    }
    
 
   
    private void mnuRunActionPerformed(java.awt.event.ActionEvent evt) {

      if (algorithm.trim().equals("Kmeans")){
       /*      animacion ani=new animacion();
            ani.setLocationRelativeTo(null);
            ani.setVisible(true);*/

            kmeansM clukm = new kmeansM();
            clukm.setNumcluster(numcluster);
            clukm.setDistancia(this.distanciakm);
            clukm.setDataint(dataIn);
            clukm.setNumIterar(this.numInteraciones);
            Calendar ahora1 = Calendar.getInstance();
            long t1 = ahora1.getTimeInMillis();
            clukm.ejecutar();
            Calendar ahora2 = Calendar.getInstance();

            long   t2 = ahora2.getTimeInMillis();  

            long df=t2-t1;
         
            System.out.println("tiempo de proceso "+df);

//////////            miExecuteTime tp = new miExecuteTime();
//////////            tp.ltiempo1.setText(""+df);
//////////            tp.ltiempo2.setText(""+df/1000);
//////////            tp.setLocationRelativeTo(null);
//////////            tp.setVisible(true);
      
                 
            centroideCerca = clukm.getCentroideCerca();
           
            medoide = clukm.getMedoide();
       
       
       
     }else if (algorithm.trim().equals("Clarans")){       
        clarans clukm = new clarans();
        clukm.setNumCluster(numcluster);
        clukm.setDistancia(this.distanciakm);
        clukm.setDataIn(dataIn);
        clukm.setNumIterar(this.numInteraciones);
        clukm.setMaxVecinos(this.maxvecinos);
        clukm.setNumLocal(this.numlocal);
        Calendar ahora1 = Calendar.getInstance();
        long t1 = ahora1.getTimeInMillis();
        clukm.ejecutar();
        Calendar ahora2 = Calendar.getInstance();
        long   t2 = ahora2.getTimeInMillis();  
     
        long df=t2-t1;
        System.out.println("tiempo de proceso "+df);

//////        miExecuteTime tp = new miExecuteTime();
//////        tp.ltiempo1.setText(""+df);
//////        tp.ltiempo2.setText(""+df/1000);
//////        tp.setLocationRelativeTo(null);
//////        tp.setVisible(true);
             
        centroideCerca = clukm.getCentroidecerca();
        medoide = clukm.getMedoide();
       
     //  medoide=clucla.getMedoide();   
             
     }else if (algorithm.trim().equals("Birch")){
        Birch clukm= new Birch ();
        clukm.setNumcluster(numcluster);
        clukm.setDistancia(this.distanciakm);
        clukm.setDataint(dataIn);
        clukm.setDataori(dataOri);
        clukm.setCantidad(cantidadclu);
        clukm.setNumIterar(this.numInteraciones);
       
        Calendar ahora1 = Calendar.getInstance();
        long t1 = ahora1.getTimeInMillis();
        clukm.ejecutar();
        Calendar ahora2 = Calendar.getInstance();
        long t2 = ahora2.getTimeInMillis();  
     
        long df = t2-t1;
        System.out.println("tiempo de proceso "+df);
    
//////        miExecuteTime tp = new miExecuteTime();
//////        tp.ltiempo1.setText(""+df);
//////        tp.ltiempo2.setText(""+df/1000);
//////        tp.setLocationRelativeTo(null);
//////        tp.setVisible(true);
      
                 
   //    centroideCerca = clukm.getCentroidecerca();
         raiz = clukm.getNodo();
 
       // ejecutar mostrar nodo
    /*   VerBirch verb=new VerBirch();
       verb.ejecutar(raiz);
       verb.setLocationRelativeTo(null);
       verb.setVisible(true);*/
       
     }
                
    }
    
    public JMenuItem getMnuRun() {
        return mnuRun;
    }
   
    public void setNumcluster(int num){
        this.numcluster=num;
    }
    
    public void setNumIterar(int num){
        this.numInteraciones = num;
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
    
    public String getAlgorithm(){
        return algorithm;
    }
    
    public void updateIconConfigureKmeans(){
        ((miConfigureKmeans)Open).updateIcon(this); // aqui le paso los valores a visualizador
    }
    
    public void updateIconConfigureClarans(){
        ((miConfigureClarans)Open).updateIcon(this); // aqui le paso los valores a visualizador
    }
    
    public void updateIconConfigureBirch(){
        ((miConfigureBirch)Open).updateIcon(this); // aqui le paso los valores a visualizador
    }
}
