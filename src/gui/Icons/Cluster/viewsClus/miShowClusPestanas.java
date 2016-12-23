/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * miShowClusPestanas.java
 *
 * Created on 21-jun-2012, 15:00:39
 */
package gui.Icons.Cluster.viewsClus;

import java.util.LinkedList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Juan Carlos
 */
public class miShowClusPestanas extends javax.swing.JFrame {

 private JScrollPane jpanel[];
 private   JTable jtable1[];
 private    DefaultTableModel modelo[];
 private int numcluster=5;

 private  AbstractTableModel medoide; 
 private int [] centroideCerca;
 private   String[] columnsName; 
 private AbstractTableModel dataIn;
 private AbstractTableModel dataSalida;
 private boolean ingresado=false;
 int rows;
 int cols;
    /** Creates new form mostrarClukmeans */
    public miShowClusPestanas() {
        initComponents();

         }
    public void setNumcluster(int num){
        numcluster=numcluster;
    }
    public void setColumnas(String[] col){
        columnsName=col;
    }
    public void setMedoide(AbstractTableModel med){
        medoide = med;
        numcluster = med.getRowCount();
    }
    public void setcentroidecer(int [] cencer){
        centroideCerca=cencer;
    }
  
    public void setData(AbstractTableModel data,AbstractTableModel datasal){
        dataIn=data;
        this.dataSalida=datasal;
    }
    
    public void ejecutar(){
        mostrarCluster(dataIn);
       
    }
     public void mostrarCluster(AbstractTableModel dataMostrar){
       jtaresultados.removeAll();
         mostrarCentroide();
         rows =dataMostrar.getRowCount();
        cols =dataMostrar.getColumnCount();
         jpanel = new JScrollPane[numcluster];
        modelo = new DefaultTableModel[numcluster];
        jtable1 = new JTable[numcluster];   
      double porcentaje=0.0;
      double[] maximo= new double[cols];
      double[] minimo= new double[cols];
       
      
      int contador=0;
         Object[] fila=new Object[cols];
                
        jtabcluster.removeAll(); 
       for (int clu=0;clu<numcluster;clu++){
           System.out.println("cluster "+clu);
  
            jpanel[clu]=new JScrollPane();
            jtable1[clu]=new JTable();
            modelo[clu]=new DefaultTableModel();
            jtable1[clu].setModel(modelo[clu]);
            
              for (int ci=0;ci<cols;ci++)
               modelo[clu].addColumn(dataIn.getColumnName(ci));
            
        
              jpanel[clu].setViewportView(jtable1[clu]);
            jtabcluster.addTab("Cluster "+clu,jpanel[clu]);
           
            jtaresultados.append("--------------------------------------------------------------------- " +"\n");
            jtaresultados.append("Cluster "+clu +"\n");
            jtaresultados.append("--------------------------------------------------------------------- " +"\n");
         
            boolean bam=false;
            contador=0;
       for (int ini=0;ini<cols;ini++){
                maximo[ini]= -999999999999.0;
                minimo[ini]= 999999999999.0;
                
       }
            
       for (int i=0;i<rows;i++){
                
                 for (int j=0;j<cols;j++){
                   if (centroideCerca[i] == clu){
                       System.out.print("  "+dataMostrar.getValueAt(i,j) );
                         fila[j]=dataMostrar.getValueAt(i,j);
                         bam=true;
                             try  {
                                if (Double.parseDouble(fila[j].toString()) > maximo[j])
                                 maximo[j]=Double.parseDouble(fila[j].toString());
                         
                                   if (Double.parseDouble(fila[j].toString()) < minimo[j])
                                     minimo[j]=Double.parseDouble(fila[j].toString());
                             
                                  } catch (Exception e) {
                                       System.out.println("Dato no se puede convertir "+e);
                                  }      
                            }              
                            }
                     if (centroideCerca[i] == clu)
                     contador++;
                 
                    if (bam)
                modelo[clu].addRow(fila);
                 bam=false;
               }
         porcentaje= ((double)contador/rows) * 100; 
        jtaresultados.append("Numero de elementos "+contador+"\n");
        jtaresultados.append("Porcentaje "+ porcentaje +"\n\n");
                
          for (int ci=0;ci<cols;ci++) { //esto muestra maximos y minimos
             jtaresultados.append(dataIn.getColumnName(ci)+"\n");
          if (maximo[ci]!= -999999999999.0) {
          jtaresultados.append(" Maximo "+ maximo[ci] +"\n");
          jtaresultados.append(" Minimo "+ minimo[ci] +"\n");
                        }
                    }
          } 
    }
      
 
   public void mostrarCentroide(){
        String medo=""; 
        for (int i=0;i<medoide.getRowCount();i++){
            medo="";     
            for (int j=0;j<medoide.getColumnCount();j++){
         
                       System.out.print("  "+dataIn.getValueAt(i,j) );
                       medo=medo+ "[ "+ medoide.getValueAt(i,j) +" ]";
                      
               }
               jtaresultados.append("Centroide "+i+" "+medo+" \n"); 
              
            }
         
          }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtaresultados = new javax.swing.JTextArea();
        btnpreprocesa = new javax.swing.JButton();
        btnreales = new javax.swing.JButton();
        btncerrar = new javax.swing.JButton();
        jtabcluster = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jtaresultados.setColumns(20);
        jtaresultados.setRows(5);
        jScrollPane1.setViewportView(jtaresultados);

        btnpreprocesa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/16x16/filter.png"))); // NOI18N
        btnpreprocesa.setText("Standarize data");
        btnpreprocesa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnpreprocesa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnpreprocesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpreprocesaActionPerformed(evt);
            }
        });

        btnreales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/16x16/datain.png"))); // NOI18N
        btnreales.setText("Input data");
        btnreales.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnreales.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnreales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrealesActionPerformed(evt);
            }
        });

        btncerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit2.png"))); // NOI18N
        btncerrar.setText("Close");
        btncerrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btncerrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btncerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtabcluster, javax.swing.GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnreales, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnpreprocesa))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btncerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btncerrar)
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnreales)
                            .addComponent(btnpreprocesa))
                        .addGap(14, 14, 14)))
                .addGap(18, 18, 18)
                .addComponent(jtabcluster, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnpreprocesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpreprocesaActionPerformed
        // TODO: Agrege su codigo aqui:
        
        mostrarCluster(dataSalida);
}//GEN-LAST:event_btnpreprocesaActionPerformed

    private void btnrealesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrealesActionPerformed
        
        
        mostrarCluster(dataIn);
}//GEN-LAST:event_btnrealesActionPerformed

    private void btncerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncerrarActionPerformed
        // TODO: Agrege su codigo aqui:
        this.dispose();
}//GEN-LAST:event_btncerrarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new miShowClusPestanas().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncerrar;
    private javax.swing.JButton btnpreprocesa;
    private javax.swing.JButton btnreales;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jtabcluster;
    private javax.swing.JTextArea jtaresultados;
    // End of variables declaration//GEN-END:variables
}
