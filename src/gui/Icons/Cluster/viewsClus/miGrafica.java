/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * miGrafica.java
 *
 * Created on 23-jun-2012, 16:43:26
 */
package gui.Icons.Cluster.viewsClus;

import java.awt.Color;
import java.awt.image.*;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;

/**
 *
 * @author juank
 */
public class miGrafica extends javax.swing.JFrame {
 BufferedImage grafica = null;

 
 private int numcluster=5;
 private AbstractTableModel medoide; 
 private String[] columnsName; 
 private AbstractTableModel dataIn;
 private AbstractTableModel dataSalida;
 private boolean ingresado=false;
 private int [] centroideCerca;
 int fil;
 int col;
//  private  BufferedImage grafica = null;
 private XYSeries[] series;
 
 
    /** Creates new form Grafica */
    public miGrafica() {
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
    
    public void iniciar(){
       col = dataIn.getColumnCount();
       fil = dataIn.getRowCount();
       series = new XYSeries[numcluster]; 
       columnsName = new String[col]; 
       
       for(int i = 0; i < col; i++){               
           if(dataIn.getColumnClass(i).equals(Integer.class) || dataIn.getColumnClass(i).equals(Double.class)){
              jcbx.addItem(dataIn.getColumnName(i));
              jcby.addItem(dataIn.getColumnName(i));  
           }
       }
       jcbx.setSelectedIndex(0);
       jcby.setSelectedIndex(0);
        
        mostrarGrafica(dataIn);
    }
    
     private boolean isNumerico(Object dato){
        try  {
            double da=Double.parseDouble(dato.toString());
            return true;
        }catch (NumberFormatException ex) {
            return false;
        }          
  }
      
    public void mostrarGrafica(AbstractTableModel data){

      if (jcbtipo.getSelectedIndex() == 0){
          
//        int ejex = jcbx.getSelectedIndex();
//        int ejey = jcby.getSelectedIndex(); 
        
        int ejex = data.findColumn(jcbx.getSelectedItem().toString());
        int ejey = data.findColumn(jcby.getSelectedItem().toString());
        int nclu;
        
        if (isNumerico(data.getValueAt(1,ejex)) == true && isNumerico(data.getValueAt(1,ejey))){ 
            
            for(int clu = 0; clu<numcluster; clu++) series[clu] = new XYSeries("Cluster "+clu);
           
            for (int i = 0; i< fil; i++ ){
                nclu = centroideCerca[i];
                series[nclu].add(Double.parseDouble(data.getValueAt(i,ejex).toString()),Double.parseDouble(data.getValueAt(i,ejey).toString()));
            }

            jlabelgrafica.removeAll();
            jlabelgrafica.setText("");

	    XYSeriesCollection xyDataset = new XYSeriesCollection();

            for(int j=0; j<numcluster;j++) xyDataset.addSeries(series[j]);
              
            String dax = jcbx.getSelectedItem().toString();
            String day = jcby.getSelectedItem().toString();
        
            JFreeChart chart = ChartFactory.createScatterPlot("Dispercion",  // Title
            dax, // X-Axis label
            day, // Y-Axis label
            xyDataset, // Dataset
            PlotOrientation.HORIZONTAL,
            true, false, false);
            chart.setBackgroundPaint(Color.WHITE);
      
            // consigue una referencia para el plot en futuras personalizaciones…
            XYPlot plot = (XYPlot) chart.getPlot();

            plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
            plot.setDomainGridlinePaint(Color.BLUE);
            plot.setRangeGridlinePaint(Color.BLUE);

	    BufferedImage image = chart.createBufferedImage(500,500);
	    jlabelgrafica.setIcon(new ImageIcon(image));
	    pack();
	    repaint();
          
        }else{
            jlabelgrafica.setText("Categorical Data");
          }       
      }else{ // por si es la grafica de pastel
         int contador=0;
           DefaultPieDataset piedata = new DefaultPieDataset();
	     
          for(int clu=0;clu<numcluster;clu++){
             contador=0; 
             for(int i=0;i<fil;i++){
                  if (clu==centroideCerca[i])
                  contador++;   
                  
              }
              piedata.setValue("Cluster"+clu,contador);
          }

              JFreeChart chart = ChartFactory.createPieChart(
	                           "Numero de elemetos por cluster", //Títrulo del gráfico
	                           piedata,
	                           true,//Leyenda
	                           true,//ToolTips
	                           true);
	        //Creamos una especie de frame y mostramos el JFreeChart en él
	        //Este constructor nos pide el título del Chart y el chart creado
	    BufferedImage image = chart.createBufferedImage(500,500);
            jlabelgrafica.setIcon(new ImageIcon(image));
            pack();
            repaint();
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

        jLabel2 = new javax.swing.JLabel();
        jcbx = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jcby = new javax.swing.JComboBox();
        btnGraficaStd = new javax.swing.JButton();
        btnGraficaInput = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jcbtipo = new javax.swing.JComboBox();
        jlabelgrafica = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Attribute X");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Attribute Y");

        btnGraficaStd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/16x16/filter.png"))); // NOI18N
        btnGraficaStd.setText("Standardized data");
        btnGraficaStd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGraficaStd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGraficaStd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGraficaStdActionPerformed(evt);
            }
        });

        btnGraficaInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/16x16/datain.png"))); // NOI18N
        btnGraficaInput.setText("Data input");
        btnGraficaInput.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGraficaInput.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGraficaInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGraficaInputActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit2.png"))); // NOI18N
        jButton2.setText("Close");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jcbtipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dispersion", "cake" }));
        jcbtipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbtipoActionPerformed(evt);
            }
        });

        jlabelgrafica.setBackground(new java.awt.Color(255, 255, 255));
        jlabelgrafica.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbtipo, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlabelgrafica, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnGraficaInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGraficaStd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbx, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(97, 97, 97)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcby, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jcbtipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGraficaInput)
                        .addGap(18, 18, 18)
                        .addComponent(btnGraficaStd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 350, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(jlabelgrafica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcby, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGraficaStdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGraficaStdActionPerformed
        mostrarGrafica(dataSalida);
        // TODO: Agrege su codigo aqui:
}//GEN-LAST:event_btnGraficaStdActionPerformed

    private void btnGraficaInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGraficaInputActionPerformed
        // TODO: Agrege su codigo aqui:
        mostrarGrafica(dataIn);
}//GEN-LAST:event_btnGraficaInputActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO: Agrege su codigo aqui:
        this.dispose();
}//GEN-LAST:event_jButton2ActionPerformed

    private void jcbtipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbtipoActionPerformed
         mostrarGrafica(dataIn);
    }//GEN-LAST:event_jcbtipoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new miGrafica().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGraficaInput;
    private javax.swing.JButton btnGraficaStd;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JComboBox jcbtipo;
    private javax.swing.JComboBox jcbx;
    private javax.swing.JComboBox jcby;
    private javax.swing.JLabel jlabelgrafica;
    // End of variables declaration//GEN-END:variables
}
