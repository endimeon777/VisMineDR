/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * miVerStandarize.java
 *
 * Created on 23-jun-2012, 17:18:00
 */
package gui.Icons.Filters.Standarize;

import Utils.ExampleFileFilter;
import Utils.FileManager;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;

/**
 *
 * @author juank
 */
public class miVerStandarize extends javax.swing.JFrame {
private AbstractTableModel dataIn;
     private AbstractTableModel dataSalida;
     int fil,col;
     private String[] columnsName;
     private double maximo;
     private double minimo;
     BufferedImage grafica = null;
     private XYSeries series;
    /** Creates new form verDatos */
    
    public miVerStandarize() {
        initComponents(); 
    }
   
    public void setDatos(AbstractTableModel dain,AbstractTableModel dasa){
        this.dataIn=dain;
        this.dataSalida=dasa;
    }
    public void ejecutar(){
        fil=dataIn.getRowCount();
        col=dataIn.getColumnCount();
        columnsName = new String[col];    
        for(int i = 0; i < col; i++)
              columnsName[i] = dataIn.getColumnName(i);
              
        mostrarDatos(dataIn);
        mostrarDatosFiltered(dataSalida); 
        ltotal1.setText("" + fil);
        
        /////
        getMetadatos(dataSalida);
//        llenaSerie(dataIn);
    }
    
     public void mostrarDatos(AbstractTableModel datos){

    Object [] fila = new Object[col];  
    DefaultTableModel modeloDa = new DefaultTableModel();
    jtadatos.setModel(modeloDa); 
    for(int i = 0; i < col; i++)
        modeloDa.addColumn(columnsName[i]);
        
               for(int i = 0; i < fil; i++){
              
                for(int j = 0; j < col; j++){
                    fila[j] =datos.getValueAt(i,j); //de lo contraio los dejamos iguales 
                                                 
                }
             modeloDa.addRow(fila);   
           } 
              
 }  
     
 public void mostrarDatosFiltered(AbstractTableModel datos){

    Object [] fila = new Object[col];  
    DefaultTableModel modeloDaFil = new DefaultTableModel();
    jtableFiltered.setModel(modeloDaFil); 
    for(int i = 0; i < col; i++)
        modeloDaFil.addColumn(columnsName[i]);
        
               for(int i = 0; i < fil; i++){
              
                for(int j = 0; j < col; j++){
                    fila[j] =datos.getValueAt(i,j); //de lo contraio los dejamos iguales                           
                }
             modeloDaFil.addRow(fila);   
           }      
 }  
    
  private double calmedia(int columna,AbstractTableModel datos){
        double suma=0.0;
        double media,valor;
        maximo= -999999999.0;
        minimo= 999999999.0;
        for(int i=0;i<fil;i++){
            valor=Double.parseDouble(datos.getValueAt(i,columna).toString());
            suma=suma + valor;
            
            if (valor > maximo)
                maximo=valor;
            if (valor < minimo)
                minimo=valor;
            
        }
        media=suma/fil;
        return media;
    }
  
  private boolean isNumerico(Object dato){
        try  {
           
            double da=Double.parseDouble(dato.toString());
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
      
     
              
  }
  
  
   private double getVarianza(int columna,double media,AbstractTableModel datos){
          double varianza=0.0;
        double valor,desviacion;
        
        for(int i=0;i<fil;i++){
            valor=Double.parseDouble(datos.getValueAt(i,columna).toString());
            varianza=varianza + Math.pow(valor-media,2);
        }
        varianza=varianza/fil;
        return varianza; 
   }
  
    private double getDesviacionEst(double _variaza){
   
        double desviacion;
            
        desviacion=Math.sqrt(_variaza);
        return desviacion; 
    }

    
    private void getMetadatos(AbstractTableModel data){
        jtabla.removeAll();           
         ///////////////////esto muestra las caracteristicas de cada columna en la tabla
   DefaultTableModel modelo = new DefaultTableModel();
   /* DefaultTableModel modelo = (new DefaultTableModel(){
    public Class getColumnClass(int columnIndex){  //esto se utiliza para redefinir el metodo de defaultablemodel
                        if(columnIndex < 2){        //getcolumnclass para que tome el campo deseado en la tabla
                                return String.class; // en este caso queremos booleanos tipo checkbox
                        } else if(columnIndex >= 2){
                                return Boolean.class;
                        }
                        return Object.class;
                }
        }); 
*/
       jtabla.setModel(modelo);  
     
    modelo.addColumn("FIELD");  
    modelo.addColumn("TYPE");  
    modelo.addColumn("MAXIMUM");  
    modelo.addColumn("MINIMUN");  
    modelo.addColumn("MEAN");  
    modelo.addColumn("VARIANCE"); 
    modelo.addColumn("STANDARD DEVIATION"); 

    Object [] fila = new Object[7];// Se crea un array que sera una de las filas de la tabla.
    Object da=new Object();                          // Hay  columnas en la tabla 
    double media,varianza,desviacion;
        for(int i = 0; i < col; i++){
                 
               fila[0]= columnsName[i];
               da=data.getValueAt(1,i);
               if (isNumerico(da)){
                  media=calmedia(i,data);
                  varianza=getVarianza(i,media,data);
                  desviacion=getDesviacionEst(varianza);

                  fila[1]="Numerical";
                  fila[2]=maximo;
                  fila[3]=minimo;
                  fila[4]=media;
                  fila[5]=varianza;
                  fila[6]=desviacion;
               }else
               {
                   
              fila[1]="Categorical";
              fila[2]="";
              fila[3]="";
              fila[4]="";
              fila[5]="";
              fila[6]="";
               }
                      
             modelo.addRow(fila);
            
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

        Save = new javax.swing.JFileChooser();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jscropane = new javax.swing.JScrollPane();
        jtabla = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtadatos = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtableFiltered = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        ltotal1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jscropane.setBackground(new java.awt.Color(204, 204, 204));

        jtabla.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Título 1", "Título 2", "Título 3", "Título 4", "Título 5"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jtabla.setSelectionBackground(new java.awt.Color(0, 0, 204));
        jtabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtablaMouseClicked(evt);
            }
        });
        jscropane.setViewportView(jtabla);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jscropane, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jscropane, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Attributes", new javax.swing.ImageIcon(getClass().getResource("/images/16x16/atributes.png")), jPanel1); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jtadatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Título 1", "Título 2", "Título 3", "Título 4"
            }
        ));
        jScrollPane1.setViewportView(jtadatos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Input Data", new javax.swing.ImageIcon(getClass().getResource("/images/16x16/datain.png")), jPanel2); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jtableFiltered.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Título 1", "Título 2", "Título 3", "Título 4"
            }
        ));
        jScrollPane2.setViewportView(jtableFiltered);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Filtered Data", new javax.swing.ImageIcon(getClass().getResource("/images/16x16/filter.png")), jPanel3); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18));
        jLabel2.setText("Samples ");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel1.setText("Current: ");

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/filesave.png"))); // NOI18N
        jButton4.setText("Filtered");
        jButton4.setToolTipText("Save the Filtered Data");
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        ltotal1.setFont(new java.awt.Font("Tahoma", 0, 12));
        ltotal1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ltotal1.setText("0");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton4)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(ltotal1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ltotal1)
                            .addComponent(jLabel1))
                        .addGap(176, 176, 176)
                        .addComponent(jButton4)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtablaMouseClicked
        boolean opc2= Boolean.parseBoolean(jtabla.getValueAt(jtabla.getSelectedRow(),2).toString()); //Tomar de la tabla opcion
        boolean opc3= Boolean.parseBoolean(jtabla.getValueAt(jtabla.getSelectedRow(),3).toString()); //Tomar de la tabla opcion
        boolean opc4= Boolean.parseBoolean(jtabla.getValueAt(jtabla.getSelectedRow(),4).toString()); //Tomar de la tabla opcion
        
        int fila=jtabla.getSelectedRow();
        // int col=jtabla.getSelectedColumn();
        
        
        //   at=(Atributo) atriColumnas[fila];
    }//GEN-LAST:event_jtablaMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
  
        ExampleFileFilter ext = new ExampleFileFilter("csv", "Filtered Data");
        String path;
        FileManager fm;
        
        Save.addChoosableFileFilter(ext);
        int saveOK = Save.showSaveDialog(this);
        if(saveOK == Save.APPROVE_OPTION) {
            path = Save.getSelectedFile().getAbsolutePath();
            path += ".csv";
            fm = new FileManager(path);
            int rows = dataSalida.getRowCount();
            int columns = dataSalida.getColumnCount()-1;
            StringBuffer textFilter = new StringBuffer();
            
            for(int c = 0; c < columns; c++){
                textFilter.append(dataSalida.getColumnName(c) + ",");
            }
            textFilter.append(dataSalida.getColumnName(columns));
            textFilter.append("\n");
            
            for(int f = 0; f < rows; f++){
                for(int c = 0; c < columns; c++){
                    textFilter.append(dataSalida.getValueAt(f,c) + ",");
                }
                textFilter.append(dataSalida.getValueAt(f,columns));
                textFilter.append("\n");
            }
            fm.writeString(textFilter.toString());
            JOptionPane.showMessageDialog(this, "Filtered data successfully saved", "VisMineKDD", JOptionPane.INFORMATION_MESSAGE);
        }
}//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new miVerStandarize().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser Save;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JScrollPane jscropane;
    private javax.swing.JTable jtabla;
    private javax.swing.JTable jtableFiltered;
    private javax.swing.JTable jtadatos;
    private javax.swing.JLabel ltotal1;
    // End of variables declaration//GEN-END:variables
}
