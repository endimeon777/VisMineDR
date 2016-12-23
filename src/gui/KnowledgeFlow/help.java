/*
 * Ayuda.java
 *
 * Created on 27 de agosto de 2007, 02:49 PM
 */

package gui.KnowledgeFlow;

import gui.manual.*;
import java.awt.ScrollPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;

/**
 *
 * @author  TNRsoft
 */
public class help extends javax.swing.JFrame {
    DefaultMutableTreeNode raiz,ramaSel,ramaCla,ramaVi,ramaRen,hojaRen,hojaAbout,ramaAso;
    DefaultMutableTreeNode hojaSel,hojaMate,hojaC45,hojaSliq, hojaAlgoritmo;
    DefaultMutableTreeNode hojaDinamo,hojaWeka,hojaTexto,hojaFunciona,hojaAlgo;
    JTree arbol;
    AcercaDe acerca=null;
    C45 c45=null;
    gui.manual.ConexionMn conexion=null;
    Dinamico dinamico=null;
    Mate mate=null;
    Sliq sliq=null;
    Texto texto=null;
    Weka weka=null;
    Algoritmos algo=null;
    
    ComoFunciona funciona=null;
    RendimientoMn rendimiento=null;

    // ----------- modificacion asociacion --------
    //Algoasociacion algoasociacion=null;
    // ----------- fin modificacion asociacion --------
    
    boolean empezar=false;
    /** Creates new form Ayuda */
    public help() {
    
    initComponents();
   
       raiz = new DefaultMutableTreeNode(".: Ayuda Yachay v1.0 :.");
        //hojaAbout
       raiz.add(hojaAbout=new DefaultMutableTreeNode("Acerca de..."));
       raiz.add(hojaFunciona=new DefaultMutableTreeNode("Como Funciona?"));
       
       raiz.add(ramaSel=new DefaultMutableTreeNode("Selección"));
       raiz.add(ramaCla=new DefaultMutableTreeNode("Clasificación"));
       raiz.add(ramaVi=new DefaultMutableTreeNode("Visualización"));
       raiz.add(ramaRen=new DefaultMutableTreeNode("Rendimiento"));
       
       //rama seleccion
       ramaSel.add(hojaSel=new DefaultMutableTreeNode("Conexión BD"));
       
       //rama Clasificacion
       ramaCla.add(hojaAlgo=new DefaultMutableTreeNode("Algoritmos"));
       /*
       ramaCla.add(hojaMate=new DefaultMutableTreeNode("Mate Tree"));
       ramaCla.add(hojaC45=new DefaultMutableTreeNode("C45"));
       ramaCla.add(hojaSliq=new DefaultMutableTreeNode("Sliq"));      
       */
       //rama visualizacion
       
       ramaVi.add(hojaDinamo=new DefaultMutableTreeNode("Arbol Dinamico"));
       ramaVi.add(hojaWeka=new DefaultMutableTreeNode("Arbol Weka"));
       ramaVi.add(hojaTexto=new DefaultMutableTreeNode("Arbol Texto"));
       
       // rama Rendimineto
       ramaRen.add(hojaRen=new DefaultMutableTreeNode("Rendimiento"));

       // ----------- modificacion asociacion --------
       //rama asociacion
       ramaAso.add(hojaAlgoritmo=new DefaultMutableTreeNode("Asociación"));
       // ----------- fin modificacion asociacion --------

       arbol=new JTree(raiz);
        
       jTree1.setModel(arbol.getModel());
      
        acerca=new AcercaDe();
        panelEstado.setViewportView(acerca);
    } 
    
    public help(JPanel aux){
    
    initComponents();
   
              panelPpal=acerca;  

       raiz = new DefaultMutableTreeNode(".: Ayuda Yachay v1.0 :.");
      
        //hojaAbout
       raiz.add(hojaAbout=new DefaultMutableTreeNode("Acerca de..."));
       raiz.add(hojaFunciona=new DefaultMutableTreeNode("Como Funciona?"));
       
       raiz.add(ramaSel=new DefaultMutableTreeNode("Selección"));
       raiz.add(ramaCla=new DefaultMutableTreeNode("Clasificación"));
       raiz.add(ramaVi=new DefaultMutableTreeNode("Visualización"));
       raiz.add(ramaAso=new DefaultMutableTreeNode("Asociación"));

       
       //rama seleccion
       ramaSel.add(hojaSel=new DefaultMutableTreeNode("Conexión BD"));
       
       
       //rama Clasificacion
       ramaCla.add(hojaAlgo=new DefaultMutableTreeNode("Algoritmos"));
       /*
       ramaCla.add(hojaMate=new DefaultMutableTreeNode("Mate Tree"));
       ramaCla.add(hojaC45=new DefaultMutableTreeNode("C45"));
       ramaCla.add(hojaSliq=new DefaultMutableTreeNode("Sliq"));      
       */
       //rama visualizacion
       
       ramaVi.add(hojaDinamo=new DefaultMutableTreeNode("Arbol Dinamico"));
       ramaVi.add(hojaWeka=new DefaultMutableTreeNode("Arbol Weka"));
       ramaVi.add(hojaTexto=new DefaultMutableTreeNode("Arbol Texto"));
       
       // rama Rendimineto
       ramaRen.add(hojaRen=new DefaultMutableTreeNode("Rendimiento"));

       //rama asociacion
       //ramaAso.add(hojaAlgoritmo=new DefaultMutableTreeNode("Algoritmos"));

       /*//hojaAbout
       raiz.add(hojaAbout=new DefaultMutableTreeNode("Acerca de..."));
       raiz.add(hojaFunciona=new DefaultMutableTreeNode("Como Funciona?"));
        */
       arbol=new JTree(raiz);     
        
       jTree1.setModel(arbol.getModel());
      
        
       panelEstado.setViewportView(aux);     
    }   
    
    public void setAyuda(JPanel aux){
       panelEstado.setViewportView(aux); 
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        div = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        panelEstado = new javax.swing.JScrollPane();
        panelPpal = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        ChooserEscritorio.ayuda=null;
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        ChooserEscritorio.ayuda=null;
        setTitle("Manual Clasificador Yachay v1.0");

        jTree1.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jTree1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jTree1);

        div.setLeftComponent(jScrollPane1);

        panelPpal.setBackground(new java.awt.Color(255, 255, 255));

        org.jdesktop.layout.GroupLayout panelPpalLayout = new org.jdesktop.layout.GroupLayout(panelPpal);
        panelPpal.setLayout(panelPpalLayout);
        panelPpalLayout.setHorizontalGroup(
            panelPpalLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 928, Short.MAX_VALUE)
        );
        panelPpalLayout.setVerticalGroup(
            panelPpalLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 467, Short.MAX_VALUE)
        );

        panelEstado.setViewportView(panelPpal);

        div.setRightComponent(panelEstado);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit2.png"))); // NOI18N
        jButton1.setText("Salir");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton1.setSelected(true);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/salir1"))); // NOI18N
        jButton2.setText("Empezar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(344, 344, 344)
                        .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 102, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(45, 45, 45)
                        .add(jButton2))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .add(div, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 960, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(div, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                .add(41, 41, 41)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .add(jButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 39, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
// TODO add your handling code here:
       ChooserEscritorio uno = new ChooserEscritorio();
       uno.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
        ChooserEscritorio.ayuda=null;
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTree1ValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jTree1ValueChanged
// TODO add your handling code here:
      String valSel="";
      
     if(jTree1.getLastSelectedPathComponent()!=null)
        valSel=jTree1.getLastSelectedPathComponent().toString();
      //javax.swing.JOptionPane.showMessageDialog(null,"Selecciono: "+jTree1.getLastSelectedPathComponent().toString() );
        
        if(valSel.equals("Acerca de...") ){
            if(acerca==null)
                acerca=new AcercaDe();
           panelEstado.setViewportView(acerca);
        }/*else if(valSel.equals("C45") ){
           if(c45==null)
                c45=new C45();
            panelEstado.setViewportView(c45);
        }*/else if(valSel.equals("Conexión BD") ){
            if(conexion==null)
                conexion=new ConexionMn();
            panelEstado.setViewportView(conexion);
        }else if(valSel.equals("Algoritmos") ){
           if(c45==null)
                algo=new Algoritmos();
            panelEstado.setViewportView(algo);
        }else if(valSel.equals("Arbol Dinamico") ){
            if(dinamico==null)
                dinamico=new Dinamico();
            panelEstado.setViewportView(dinamico);
        }/*else if(valSel.equals("Mate Tree") ){
            if(mate==null)
                mate=new Mate();
            panelEstado.setViewportView(mate);
        }else if(valSel.equals("Sliq") ){
            if(sliq==null)
                sliq=new Sliq();
            panelEstado.setViewportView(sliq);
        }*/else if(valSel.equals("Arbol Texto") ){
            if(texto==null)
                texto=new Texto();
           panelEstado.setViewportView(texto);
        }else if(valSel.equals("Arbol Weka") ){
            if(weka==null)
                weka=new Weka();
           panelEstado.setViewportView(weka);
        }else if(valSel.equals("Rendimiento") ){
            if(rendimiento==null)
                rendimiento=new RendimientoMn();
            panelEstado.setViewportView(rendimiento);
        }else if(valSel.equals("Como Funciona?") ){
            if(rendimiento==null)
                funciona=new ComoFunciona();
            panelEstado.setViewportView(funciona);
        }else if(valSel.equals("Asociacion") ){
            //if(algoasociacion==null)
                //algoasociacion=new Algoasociacion();
            panelEstado.setViewportView(sliq);
        }else{
          if(acerca==null)
           acerca=new AcercaDe();  
             panelEstado.setViewportView(acerca);  
        }
            
        
    }//GEN-LAST:event_jTree1ValueChanged
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new help().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSplitPane div;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree1;
    private javax.swing.JScrollPane panelEstado;
    private javax.swing.JPanel panelPpal;
    // End of variables declaration//GEN-END:variables
   

 
}
 


