/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * miVerCodificacion.java
 *
 * Created on 25-may-2012, 11:25:39
 */
package gui.Icons.Filters.Codification;

import Utils.ExampleFileFilter;
import Utils.FileManager;
import gui.Icons.Filters.ReplaceMissing.RemplazarMissing;
import gui.Icons.Filters.TipodVariables;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Juan Carlos
 */
public class miVerCodificacion extends javax.swing.JFrame {
    
    AbstractTableModel datosEntrada;
    AbstractTableModel tipoVariables;
    ValAtributosCod diccionario;
    Codificacion datosFiltros;

    /** Creates new form miVerCodificacion */
    public miVerCodificacion(AbstractTableModel dataIn, AbstractTableModel dataOut) {
        datosEntrada = dataIn;
        tipoVariables = new TipodVariables(dataIn);
        diccionario = ((Codificacion)dataOut).valatricod;
        datosFiltros = (Codificacion)dataOut;
        initComponents();
        LblRegAct.setText(Integer.toString(datosFiltros.getRowCount()));
        LblDictionary.setText(Integer.toString(diccionario.getRowCount()));
    }
    
    public miVerCodificacion() {
        initComponents();
    }
    
    public void setDatas(AbstractTableModel din, AbstractTableModel dot) {
        datosEntrada = din;
        tipoVariables = new TipodVariables(din);
        diccionario = ((Codificacion)dot).valatricod;
        datosFiltros = (Codificacion)dot;
        LblRegAct.setText(Integer.toString(datosFiltros.getRowCount()));
        LblDictionary.setText(Integer.toString(diccionario.getRowCount()));

        TableTVariables.setModel(tipoVariables);
        TableDatosEntrada.setModel(datosEntrada);
        TableDatosFiltro.setModel(datosFiltros);
        TableDiccionario.setModel(diccionario);
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
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableTVariables = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableDatosEntrada = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableDatosFiltro = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        TableDiccionario = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        LblRegAct = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        LblDictionary = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setToolTipText("Structure Data");

        TableTVariables.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(TableTVariables);

        jTabbedPane1.addTab("Attributes", new javax.swing.ImageIcon(getClass().getResource("/images/16x16/atributes.png")), jScrollPane3); // NOI18N

        jScrollPane1.setToolTipText("Input Data");

        TableDatosEntrada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(TableDatosEntrada);

        jTabbedPane1.addTab("Input Data", new javax.swing.ImageIcon(getClass().getResource("/images/16x16/datain.png")), jScrollPane1); // NOI18N

        jScrollPane2.setToolTipText("Filtered Data");

        TableDatosFiltro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(TableDatosFiltro);

        jTabbedPane1.addTab("Filtered Data", new javax.swing.ImageIcon(getClass().getResource("/images/16x16/filter.png")), jScrollPane2); // NOI18N

        jScrollPane4.setToolTipText("Dictionary of Data");

        TableDiccionario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(TableDiccionario);

        jTabbedPane1.addTab("Dictionary", new javax.swing.ImageIcon(getClass().getResource("/images/16x16/dictionary.png")), jScrollPane4); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setText("Samples ");

        LblRegAct.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        LblRegAct.setText("0");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Dictionary:");

        LblDictionary.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        LblDictionary.setText("0");

        jButton1.setForeground(new java.awt.Color(51, 51, 51));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/filesave.png"))); // NOI18N
        jButton1.setText("Filtered");
        jButton1.setToolTipText("Save the Filtered Data");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setForeground(new java.awt.Color(51, 51, 51));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/filesave.png"))); // NOI18N
        jButton2.setText("Dictionary");
        jButton2.setToolTipText("Save the Dictionary of Data");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Current : ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 678, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(LblDictionary, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(LblRegAct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(LblRegAct, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGap(2, 2, 2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(LblDictionary, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addComponent(jButton1)
                        .addGap(90, 90, 90)
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ExampleFileFilter ext = new ExampleFileFilter("csv", "Filtered Data");
        String path;
        FileManager fm;
        
        Save.addChoosableFileFilter(ext);
        int saveOK = Save.showSaveDialog(this);
        if(saveOK == Save.APPROVE_OPTION) {
            path = Save.getSelectedFile().getAbsolutePath();
            path += ".csv";
            fm = new FileManager(path);
            int rows = datosFiltros.getRowCount();
            int columns = datosFiltros.getColumnCount()-1;
            StringBuffer textFilter = new StringBuffer();
            
            for(int c = 0; c < columns; c++){
                textFilter.append(datosFiltros.getColumnName(c) + ",");
            }
            textFilter.append(datosFiltros.getColumnName(columns));
            textFilter.append("\n");
            
            for(int f = 0; f < rows; f++){
                for(int c = 0; c < columns; c++){
                    textFilter.append(datosFiltros.getValueAt(f,c) + ",");
                }
                textFilter.append(datosFiltros.getValueAt(f,columns));
                textFilter.append("\n");
            }
            fm.writeString(textFilter.toString());
            JOptionPane.showMessageDialog(this, "Filtered data successfully saved", "VisMineKDD", JOptionPane.INFORMATION_MESSAGE);
        }
}//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ExampleFileFilter ext = new ExampleFileFilter("csv", "Dictionary");
        String path;
        FileManager fm;
        
        Save.addChoosableFileFilter(ext);
        int saveOK = Save.showSaveDialog(this);
        if(saveOK == Save.APPROVE_OPTION) {
            path = Save.getSelectedFile().getAbsolutePath();
            path += ".csv";
            fm = new FileManager(path);
            fm.writeString("Dictionary \n\n");
            int rows = diccionario.getRowCount();
            int columns = diccionario.getColumnCount()-1;
            StringBuffer textFilter = new StringBuffer();
            
            for(int c = 0; c < columns; c++){
                textFilter.append(diccionario.getColumnName(c) + ",");
            }
            textFilter.append(diccionario.getColumnName(columns));
            textFilter.append("\n");
            
            for(int f = 0; f < rows; f++){
                for(int c = 0; c < columns; c++){
                    textFilter.append(diccionario.getValueAt(f,c) + ",");
                }
                textFilter.append(diccionario.getValueAt(f,columns));
                textFilter.append("\n");
            }
            fm.writeString(textFilter.toString());
            JOptionPane.showMessageDialog(this, "Dictionary successfully saved", "VisMineKDD", JOptionPane.INFORMATION_MESSAGE);
        }
}//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
//                new miVerCodificacion().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LblDictionary;
    private javax.swing.JLabel LblRegAct;
    private javax.swing.JFileChooser Save;
    private javax.swing.JTable TableDatosEntrada;
    private javax.swing.JTable TableDatosFiltro;
    private javax.swing.JTable TableDiccionario;
    private javax.swing.JTable TableTVariables;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
