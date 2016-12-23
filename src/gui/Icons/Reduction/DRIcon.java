/*
 * AssociationIcon.java
 *
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gui.Icons.Reduction;

import gui.Icons.Reduction.configureMDS;
import gui.Icons.VisDR.ScatterIcon;
import Utils.AvlTree;
import Utils.DataSet;
import Utils.MachineLearning.graph.Graph;
import Utils.MachineLearning.math.math.kernel.GaussianKernel;
import Utils.MachineLearning.plot.plot.Palette;
import Utils.MachineLearning.plot.plot.PlotCanvas;
import Utils.MachineLearning.plot.plot.ScatterPlot;
import algorithm.reduction.kernel.KMDS;
import algorithm.reduction.manifold.LLE;
import algorithm.reduction.manifold.LaplacianEigenmap;
import algorithm.reduction.mds.MDS;
import algorithm.reduction.projection.KPCA;
import algorithm.reduction.projection.PCA;
import gui.Icons.DBConnection.DBConnectionIcon;
import gui.Icons.Rules.RulesIcon;
import gui.KnowledgeFlow.ChooserEscritorio;
import gui.KnowledgeFlow.Icon;
import gui.KnowledgeFlow.JackAnimation;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
 
/**
 *
 */
public class DRIcon extends Icon{
    private JMenuItem mnuConfigure;
    private JMenuItem mnuRun;
    private JMenuItem mnuView;
    private JMenuItem mnuHelp;
    
    public String algorithm;
    
    public double[][] dataIn;
    public String[] atributos;
    public String[] etiquetas;
    public ArrayList etiquetasDif = new ArrayList(1);
    
    public Integer d = 2; // MDS y PCA, para seleccionar a que dimension se reduce
    public boolean cor; //PCA para seleccionar el metodo de scalig, correlacion o covarianza 
    public Integer k = 0; // Para LLE Y LE
    public Integer t = -1; // Para LE
    public double gamma = 0.0; // Para KPCA
    
    double[][] dataOut = null;
    
    public MDS mds;
    public PCA pca;
    public LLE lle;
    public LaplacianEigenmap le;
    public KPCA<double[]> kpca;
    
    public KMDS kmds;
     
//    public AbstractTableModel dataIn = null;
//    public AbstractTableModel dataOut = null;
    
    public static configureMDS cmds;
    public static configurePCA cpca;
    public static configureLLE clle;
    public static configureLE cle;
    public static configureKPCA ckpca;
    public static verDatosReducidos verY;
    public static verDatosReducidosKernel verYK;
    
    /** Creates a new instance of DBConnectionIcon */
    public DRIcon(JLabel s, int x, int y, int indiceIcon) {
        super(s, x, y, indiceIcon);
        super.constrainsTo = new ArrayList(1);
        super.constrainsTo.add("ScatterIcon");
        super.constrainsTo.add("VarianceIcon");
        algorithm = s.getText();

        setInfo("Dimensionality Reduction in process");
        System.out.println("Dimensionality Reduction in process");
        
        if(!algorithm.equals("KMDS")){ // El KMDS es el unico metodo que no se configura
        
            mnuConfigure = new javax.swing.JMenuItem();
            mnuConfigure.setText("Configure...");
            mnuConfigure.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    mnuConfigureActionPerformed(evt);
                }
            });
            super.pupMenu.add(mnuConfigure);
            mnuConfigure.setEnabled(false);
             if(algorithm.equals("MDS")){
                cmds = new configureMDS(this);
                cmds.setVisible(false);
             }else if(algorithm.equals("PCA")){
                cpca = new configurePCA(this);
                cpca.setVisible(false);
             }else if(algorithm.equals("LLE")){
                clle = new configureLLE(this);
                clle.setVisible(false);
             }else if(algorithm.equals("LE")){
                cle = new configureLE(this);
                cle.setVisible(false);
             }else if(algorithm.equals("KPCA")){
                ckpca = new configureKPCA(this);
                ckpca.setVisible(false);
             }
        }
         
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
        if(algorithm.equals("KMDS")){
            verYK = new verDatosReducidosKernel(); //el view de los metodos kernel tiene mas pestañas que corresponden a los datos kernel
        }else{
            verY = new verDatosReducidos();
        }
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

    public JMenuItem getMnuRun() {
        return mnuRun;
    }
    
    public JMenuItem getMnuConfigure() {
        return mnuConfigure;
    }
    
    private void mnuConfigureActionPerformed(java.awt.event.ActionEvent evt) {
        final DRIcon icon = this;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if(algorithm.equals("MDS")){
                    cmds.updateIcon(icon);
                    cmds.setVisible(true);
                }else if(algorithm.equals("PCA")){
                    cpca.updateIcon(icon);
                    cpca.setVisible(true);
                }else if(algorithm.equals("LLE")){
                    clle.updateIcon(icon);
                    clle.setVisible(true);
                }else if(algorithm.equals("LE")){
                    cle.updateIcon(icon);
                    cle.setVisible(true);
                }else if(algorithm.equals("KPCA")){
                    ckpca.updateIcon(icon);
                    ckpca.setVisible(true);
                }
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
    
    private void mnuRunActionPerformed(java.awt.event.ActionEvent evt) {

        JackAnimation jack = new JackAnimation();
        this.add(jack);
        this.setComponentZOrder(jack, 0);
        jack.setBounds(this.animation.getX(), this.animation.getY(), 36, 36);
        this.setAnimation(jack);

        if(algorithm.equals("MDS")){
            
            if(dataIn.length==dataIn[0].length){// para saber si la matriz es cuadrada como debe ser una matriz de afinidad
                this.startAnimation();
                long clock = System.currentTimeMillis();  
                mds = new MDS(dataIn, d);
                System.out.format("Learn MDS from %d samples in %dms\n", dataIn.length, System.currentTimeMillis()-clock);
                this.stopAnimation();
            } else{
                JOptionPane.showMessageDialog(this, "Matrix is not square", "VisMineDR", JOptionPane.ERROR_MESSAGE);
            }  
            
        }else if(algorithm.equals("PCA")){
            this.startAnimation();
            long clock = System.currentTimeMillis();
            
            if(d > dataIn[0].length){
                JOptionPane.showMessageDialog(this, "The reduction dimension can not be greater than the original dimension", "VisMineDR", JOptionPane.ERROR_MESSAGE);
            }else{
                pca = new PCA(dataIn, cor);
                System.out.format("Learn PCA from %d samples in %dms\n", dataIn.length, System.currentTimeMillis()-clock);      
            }
            this.stopAnimation();
            
        }else if(algorithm.equals("LLE")){
             
             this.startAnimation();
             
//             // provicional por rendimiento computacional y costo de tiempo
//             if (dataIn.length > 1000) {
//                double[][] x = new double[1000][];
//                for (int i = 0; i < 1000; i++)
//                    x[i] = dataIn[i];
//                dataIn = x;
//            }
             
             long clock = System.currentTimeMillis();
             lle = new LLE(dataIn, d, k);
             
             System.out.format("Learn LLE from %d samples in %dms\n", dataIn.length, System.currentTimeMillis()-clock);
             this.stopAnimation();    
             
        }else if(algorithm.equals("LE")){
             
             this.startAnimation();
             
//             // provicional por rendimiento computacional y costo de tiempo
//             if (dataIn.length > 1000) {
//                double[][] x = new double[1000][];
//                for (int i = 0; i < 1000; i++)
//                    x[i] = dataIn[i];
//                dataIn = x;
//            }
             
             long clock = System.currentTimeMillis();
             le = new LaplacianEigenmap(dataIn, d, k, -1); ///sigma - 1
             
             System.out.format("Learn LE from %d samples in %dms\n", dataIn.length, System.currentTimeMillis()-clock);
             this.stopAnimation();  
             
        }else if(algorithm.equals("KPCA")){
            
            if (gamma == 0.0) {
                int n = 0;
                for (int i = 0; i < dataIn.length; i++) {
                    for (int j = 0; j < i; j++, n++) {
                        gamma += Utils.MachineLearning.math.math.Math.squaredDistance(dataIn[i], dataIn[j]);
                    }
                }

                gamma = Utils.MachineLearning.math.math.Math.sqrt(gamma / n) / 4;
            } 
            
            this.startAnimation();
            long clock = System.currentTimeMillis();
            kpca = new KPCA<double[]>(dataIn, new GaussianKernel(gamma), d);
            System.out.format("Learn KPCA from %d samples in %dms\n", dataIn.length, System.currentTimeMillis()-clock);
            this.stopAnimation();  
            
        }else if(algorithm.equals("KMDS")){
            this.startAnimation();
            long clock = System.currentTimeMillis();
            
            try {
                kmds = new KMDS(dataIn, etiquetas);
            } catch (Exception ex) {
                Logger.getLogger(DRIcon.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.format("Learn KMDS from %d samples in %dms\n", dataIn.length, System.currentTimeMillis()-clock);
            this.stopAnimation();    
        }
        mnuView.setEnabled(true);
    }
    
        private void mnuViewActionPerformed(java.awt.event.ActionEvent evt) {
//        final FilterIcon ai = this;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

        if(algorithm.equals("MDS")){
            dataOut = mds.getCoordinates();

        } else if(algorithm.equals("PCA")){
            pca.setProjection(d);
            dataOut = pca.project(dataIn);
    
        }else if(algorithm.equals("LLE")){
             dataOut = lle.getCoordinates();
                    
        }else if(algorithm.equals("LE")){
             dataOut = le.getCoordinates();
            
        }else if(algorithm.equals("KPCA")){
            dataOut = kpca.getCoordinates();
        
        }else if(algorithm.equals("KMDS")){
            dataOut = kmds.getKernelMDS();
        }
          
        llenarDatosTablas();
        
        }
        });
    }

        
    public void llenarDatosTablas(){
          
      DRTableModel dataI = new DRTableModel(dataIn, atributos);
        
      if(algorithm.equals("KMDS")){ //El view de los metodos kernel tiene mas pestañas por que se visualizan los eigenvectore y eigenvalores
          String[] ds = new String[dataOut.length];
          for(int i=0; i<dataOut.length; i++){
              ds[i] = "d"+(i+1); 
          }
          DRTableModel dataO = new DRTableModel(dataOut, ds);  
          
          DRTableModel eigenVectores = new DRTableModel(kmds.getEigValores(), ds);
          DRTableModel eigenValores = new DRTableModel(kmds.getEigVectores(), ds);

          verYK.setDatas(dataI, dataO, eigenVectores, eigenValores); 
          verYK.setVisible(true);

      }else{
          String[] ds = new String[d];
          for(int i=0; i<d; i++){
              ds[i] = "d"+i+1; 
          }
          DRTableModel dataO = new DRTableModel(dataOut, ds);    

          verY.setDatas(dataI, dataO); 
          verY.setVisible(true);
      }
    }
    
    public void updateIconConfigureMDS(){
        this.cmds.updateIcon(this); // aqui le paso los valores a visualizador
    }
}
