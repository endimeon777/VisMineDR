/*
 * frmPerformance.java
 *
 * Created on 9 de agosto de 2007, 10:05 AM
 */

package gui.performance;

import gui.KnowledgeFlow.performanceAttribute;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.event.MouseEvent;

/**
 *
 * 
 */
public class frmPerformance extends javax.swing.JFrame {
    DefaultTableModel modelo,mMate,mC45,mSliq;
    Pintar prueba;
    int [] mateX;
    int [] mateY;
    int [] c45X;
    int [] c45Y;
    int [] sliqX;
    int [] sliqY;
    String [] numHor;
    String [] numVer;
    boolean []  order=new boolean[]{true,true,true};
    Vector  auxV;
    public frmPerformance(Vector tiemposAlgoritmos) {
        initComponents();
        performanceAttribute aux;
        String [] fila;
        int contMate,contC45,contSliq;
        int i;
        long mayorTiempo=0,mayorRegistros=0;
        int topeRegistros,topeTiempo,vuTiempo=1,vuRegistros=1;
        auxV= tiemposAlgoritmos;
        if(tiemposAlgoritmos!=null ){
            
               String[] cabecera={"Algoritmo", "Num Registros", "Num Atributos", "Tiempo de Ejecución ms"};
		modelo=new DefaultTableModel(cabecera,0);
                mMate=new DefaultTableModel(cabecera,0);
                mC45=new DefaultTableModel(cabecera,0);
                mSliq=new DefaultTableModel(cabecera,0);
                
                fila=new String[4];
                
                contMate=0;contC45=0;contSliq=0;
                for(i=0;i<tiemposAlgoritmos.size();i++){
                    aux=(performanceAttribute) tiemposAlgoritmos.get(i);
                    modelo.addRow(aux.getValores());
                    if(mayorTiempo<aux.tiempoUtilizado)
                            mayorTiempo=aux.tiempoUtilizado;
                    if(mayorRegistros<aux.numInstancias)
                            mayorRegistros=aux.numInstancias;
                    
                    
                    if(aux.nomAlgoritmo.equals("Mate")){
                        contMate++;
                        mMate.addRow(aux.getValores());
                    }
                    else if (aux.nomAlgoritmo.equals("C4.5")){
                        contC45++;
                        mC45.addRow(aux.getValores());
                    } else{ contSliq++;
                       mSliq.addRow(aux.getValores());
                    }
                    
               }
               
                tblTiempos.setModel(modelo);
                
                tblTiempos.setEnabled(false);
                
                //tblTiempos.setRowSelectionInterval(0,1);
                //saco los topes para determinar los rangos de los planos
                topeRegistros=20; topeTiempo=20; 
                while(mayorRegistros>topeRegistros)
                    topeRegistros+=20;
                while(mayorTiempo>topeTiempo)
                    topeTiempo+=20;
                
                vuTiempo=topeTiempo/10;
                vuRegistros=topeRegistros/10;



                //puntos para graficar
                
                mateX=new int[contMate+1];
                mateY=new int[contMate+1];
                c45X=new int[contC45+1];
                c45Y=new int[contC45+1];
                sliqX=new int[contSliq+1];
                sliqY=new int[contSliq+1];
                
                //punto 0,0
                mateX[0]=50;
                mateY[0]=300;
                c45X[0]=50;
                c45Y[0]=300;
                sliqX[0]=50;
                sliqY[0]=300;
                
                
                contMate=1;contC45=1;contSliq=1;    
                for(i=0;i<tiemposAlgoritmos.size();i++){
                    aux = (performanceAttribute) tiemposAlgoritmos.get(i);
                                      
                    if(aux.nomAlgoritmo.equals("Mate")){
                        
                        mateX[contMate]=50 + (35*(aux.numInstancias/vuRegistros));
                        mateY[contMate]=300-(20*Integer.parseInt(String.valueOf(aux.tiempoUtilizado))/vuTiempo);
                        contMate++;
                        
                    }
                    else if (aux.nomAlgoritmo.equals("C4.5")){
                        c45X[contC45]= 50 + (35*(aux.numInstancias/vuRegistros));
                        c45Y[contC45]=300-(20*Integer.parseInt(String.valueOf(aux.tiempoUtilizado))/vuTiempo);
                        contC45++;
                    
                    } else{ 
                        sliqX[contSliq]=50 + (35*( aux.numInstancias/vuRegistros));
                        sliqY[contSliq]=300-(20*Integer.parseInt(String.valueOf(aux.tiempoUtilizado))/vuTiempo);
                        contSliq++;
                    }
               }
                
                int auxInt,j;
                //para ordenar mate
                for(i=0;i<mateY.length-1;i++)
                    for(j=i+1;j<mateY.length;j++)
                        if(mateY[i]>mateY[j]){
                            auxInt=mateY[i];
                            mateY[i]=mateY[j];
                            mateY[j]=auxInt;
                            
                            auxInt=mateX[i];
                            mateX[i]=mateX[j];
                            mateX[j]=auxInt;
                        }
                
                    //para ordenar c4.5
                for(i=0;i<c45Y.length-1;i++)
                    for(j=i+1;j<c45Y.length;j++)
                        if(c45Y[i]>c45Y[j]){
                            auxInt=c45Y[i];
                            c45Y[i]=c45Y[j];
                            c45Y[j]=auxInt;
                            
                            auxInt=c45X[i];
                            c45X[i]=c45X[j];
                            c45X[j]=auxInt;
                        }
                
                
                  //para ordenar sliq
                for(i=0;i<sliqY.length-1;i++)
                    for(j=i+1;j<sliqY.length;j++)
                        if(sliqY[i]>sliqY[j]){
                            auxInt=sliqY[i];
                            sliqY[i]=sliqY[j];
                            sliqY[j]=auxInt;
                            
                            auxInt=sliqX[i];
                            sliqX[i]=sliqX[j];
                            sliqX[j]=auxInt;
                        }
                
                System.out.println("Mayor de tiempo: "+mayorTiempo+" Unidad: "+vuTiempo+" Mayor de Registros: "+mayorRegistros+" Unidad: "+vuRegistros);
        
        
        }
              
       
        //numeros horizontales
        numHor=new String[11] ;
        for(i=0;i<10;i++){
            numHor[i]=String.valueOf((i+1)*vuRegistros); 
        }
                  
         //numeros Verticales
         numVer=new String[13]; 
         for(i=0;i<13;i++){
             numVer[i]=String.valueOf((i+1)*vuTiempo);
        }
              
     
        
        
        //prueba= new Pintar(tiemposAlgoritmos);
       prueba=new Pintar(mateX,mateY,c45X,c45Y,sliqX,sliqY,numHor,numVer);
        
      this.jScrollPane1.setViewportView(prueba);
      this.addJTableHeaderListener();
        
    }
    
    //**********
     public void addJTableHeaderListener() {
        MouseAdapter mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                TableColumnModel columnModel = tblTiempos.getColumnModel();
                int viewColumn = columnModel.getColumnIndexAtX(e.getX());
                int column = tblTiempos.convertColumnIndexToModel(viewColumn);
                if(e.getClickCount() == 1 && column != -1) {
                    if(column == 1) {
                       if(order[0]) 
                        orderColumnasMenor(1);
                       else 
                           orderColumnasMayor(1);
                       order[0]=!order[0];
                        
                       tblTiempos.updateUI();
                    } else if(column == 2) {
                       
                        if(order[1]) 
                        orderColumnasMenor(2);
                        else 
                           orderColumnasMayor(2);
                       order[1]=!order[1];
                        tblTiempos.updateUI();
                    } else if(column == 3) {
                       
                        if(order[2]) 
                        orderColumnasMenor(3);
                       else 
                           orderColumnasMayor(3);
                       order[2]=!order[2];
                       tblTiempos.updateUI();
                    }else if(column == 0) {
                        javax.swing.JOptionPane.showMessageDialog(null,"Toma un: 0");
                        tblTiempos.updateUI();
                    } 
                }
            }
        };
        JTableHeader header = tblTiempos.getTableHeader();
        header.addMouseListener(mouseListener);
    }
    
     public void orderColumnasMenor(int numCol){
         
         long menor=0,aux=0;
         for(int i=0;i<tblTiempos.getRowCount()-1;i++ ){
             menor=Long.parseLong(modelo.getValueAt(i,numCol).toString());
             for(int j=i+1;j<tblTiempos.getRowCount();j++ ){
                 aux= Long.parseLong(modelo.getValueAt(j,numCol).toString());
                 if( aux< menor){
                      modelo.moveRow(i,i,j-1);
                     modelo.moveRow(j,j,i);
                   menor=aux;
                 }
                 
             }
         }
         
     }
     
      public void orderColumnasMayor(int numCol){
         
         long mayor=0,aux=0;
         for(int i=0;i<tblTiempos.getRowCount()-1;i++ ){
             mayor=Long.parseLong(modelo.getValueAt(i,numCol).toString());
             for(int j=i+1;j<tblTiempos.getRowCount();j++ ){
                 aux= Long.parseLong(modelo.getValueAt(j,numCol).toString());
                 if( mayor<aux){
                      modelo.moveRow(i,i,j-1);
                     modelo.moveRow(j,j,i);
                   mayor=aux;
                 }
                 
             }
         }
         
     }
    //************
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTiempos = new javax.swing.JTable();
        btnMate = new javax.swing.JButton();
        btnC45 = new javax.swing.JButton();
        btnSliq = new javax.swing.JButton();
        btnTodos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Grafica de Rendimiento de Algoritmos");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(212, 233, 255));

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB", 1, 12));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Grafica de Rendimiento:   Tiempo vs Num Registros");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit2.png"))); // NOI18N
        jButton1.setText("Retornar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane2.setBackground(new java.awt.Color(212, 233, 255));
        jScrollPane2.setOpaque(false);

        tblTiempos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblTiempos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTiemposMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblTiempos);

        btnMate.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 12));
        btnMate.setForeground(new java.awt.Color(39, 39, 238));
        btnMate.setText("Mate");
        btnMate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMateActionPerformed(evt);
            }
        });

        btnC45.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 12));
        btnC45.setForeground(new java.awt.Color(0, 153, 0));
        btnC45.setText("C45");
        btnC45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnC45ActionPerformed(evt);
            }
        });

        btnSliq.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 12));
        btnSliq.setForeground(new java.awt.Color(255, 0, 51));
        btnSliq.setText("Sliq");
        btnSliq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSliqActionPerformed(evt);
            }
        });

        btnTodos.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 12));
        btnTodos.setText("Todos");
        btnTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTodosActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(148, 148, 148)
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 480, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(340, 340, 340)
                        .add(jButton1))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(212, 212, 212)
                        .add(btnMate)
                        .add(40, 40, 40)
                        .add(btnC45)
                        .add(49, 49, 49)
                        .add(btnSliq)
                        .add(46, 46, 46)
                        .add(btnTodos))
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 802, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 350, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(15, 15, 15)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnTodos)
                    .add(btnC45)
                    .add(btnSliq)
                    .add(btnMate))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 133, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTodosActionPerformed
// TODO add your handling code here:
        tblTiempos.setModel(modelo);
        prueba.pintar=0;                
    }//GEN-LAST:event_btnTodosActionPerformed

    private void btnC45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnC45ActionPerformed
// TODO add your handling code here:
          tblTiempos.setModel(mC45);
          prueba.pintar=2;
    }//GEN-LAST:event_btnC45ActionPerformed

    private void btnSliqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSliqActionPerformed
// TODO add your handling code here:
       tblTiempos.setModel(mSliq);
        
    }//GEN-LAST:event_btnSliqActionPerformed

    private void btnMateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMateActionPerformed
// TODO add your handling code here:
         tblTiempos.setModel(mMate);
         prueba.pintar=1;
        
        
    }//GEN-LAST:event_btnMateActionPerformed

    private void tblTiemposMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTiemposMouseClicked
// TODO add your handling code here:
   //javax.swing.JOptionPane.showMessageDialog(null,"llego: "+tblTiempos.getSelectedColumn());
        
    }//GEN-LAST:event_tblTiemposMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPerformance(null).setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnC45;
    private javax.swing.JButton btnMate;
    private javax.swing.JButton btnSliq;
    private javax.swing.JButton btnTodos;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblTiempos;
    // End of variables declaration//GEN-END:variables
    
}
   //punto (0,0) es 50,300 
         /*        
        //lineas de prueba; 
         int x2Points[] = new int[5];
         int y2Points[] = new int[5];
         
               
       // Mate Azul
        for(i=0;i<x2Points.length;i++){
             x2Points[i]=50+(30*i);
             y2Points[i]=300-(30*i);
        }
         
         g2.setColor(Color.BLUE);
         g2.drawPolyline(x2Points,y2Points,x2Points.length);
         
        // C4.5 Verde 
        for(i=0;i<x2Points.length;i++){
             x2Points[i]=50+(30*i);
             y2Points[i]=300-(20*i);
        }
         
         g2.setColor(Color.GREEN);
         g2.drawPolyline(x2Points,y2Points,x2Points.length);    
        
        // Sliq Rojo 
        for(i=0;i<x2Points.length;i++){
             x2Points[i]=50+(30*i);
             y2Points[i]=300-(50*i);
        }
        */
