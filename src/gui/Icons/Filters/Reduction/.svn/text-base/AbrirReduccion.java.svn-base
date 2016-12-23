/*
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program; if not, write to the Free Software
 *    Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

package gui.Icons.Filters.Reduction;

import gui.Icons.Filters.ValAtributos;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author  Tariy
 */
public class AbrirReduccion extends javax.swing.JFrame {
    AbstractTableModel datosEntrada;
    ValAtributos valAtri;
    String atributos[];
    int rbtsel = 0, fini = 0, ffin = 0, menores = 0, colsel = 0, se = 0, nAtriSel = 0, rbtManRem = 0;
    ArrayList atris;
    
    public AbrirReduccion(AbstractTableModel dataIn) {
        datosEntrada = dataIn;
        atributos = new String[datosEntrada.getColumnCount()+1];
        atris = new ArrayList(1);
        atributos[0] = "select an Attribute";
        for(int i = 0; i < datosEntrada.getColumnCount(); i++) {
            atributos[i+1] = datosEntrada.getColumnName(i);
        }
        initComponents();
    }
    
    public int getSelRbtn() { // si fue por rango o por valor
        return rbtsel;
    }
    
    public int getValFilIni() { // fila inicial
        return fini;
    }
    
    public int getValFilFin() { // fila final
        return ffin;
    }
    
    public int getRbtnManRem() { // si se mantienen o se remueven los datos
        return rbtManRem;
    }
    
    public int getColSel() { // que columna se selecciono en CmbItem
        return colsel;
    }
    
    public ArrayList getAtriSel() { // los atributos que se seleccionaron pra mantener o remover
        return atris;
    }
    
    public int getSe() {// seal del CmbItem si selecciono un atributo cuya columna
        return se;        // es Integer retorna 0 si es String retorna 1
    }
    
    public int getMenoresQue() { // umbral de los valores numericos menores que este
        return menores;
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        RbtnPorRango = new javax.swing.JRadioButton();
        RbtnPorValor = new javax.swing.JRadioButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        TexFini = new javax.swing.JTextField();
        TexFfin = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        BtnAplicar = new javax.swing.JButton();
        BtnCerrar = new javax.swing.JButton();
        RbtnMantener = new javax.swing.JRadioButton();
        RbtnRemover = new javax.swing.JRadioButton();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        TexMenores = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        CmbItem = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLayeredPane5 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableAtrib = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configure Filter");
        setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBackground(new java.awt.Color(81, 81, 133));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        RbtnPorRango.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(RbtnPorRango);
        RbtnPorRango.setFont(new java.awt.Font("Tahoma", 1, 12));
        RbtnPorRango.setForeground(new java.awt.Color(0, 0, 204));
        RbtnPorRango.setSelected(true);
        RbtnPorRango.setText("By Range");
        RbtnPorRango.setToolTipText("Reduce by range");
        RbtnPorRango.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        RbtnPorRango.setBorderPainted(true);
        RbtnPorRango.setMargin(new java.awt.Insets(0, 0, 0, 0));
        RbtnPorRango.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbtnPorRangoActionPerformed(evt);
            }
        });

        RbtnPorValor.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(RbtnPorValor);
        RbtnPorValor.setFont(new java.awt.Font("Tahoma", 1, 12));
        RbtnPorValor.setForeground(new java.awt.Color(0, 0, 204));
        RbtnPorValor.setText("By Value");
        RbtnPorValor.setToolTipText("Reduce by Value");
        RbtnPorValor.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        RbtnPorValor.setMargin(new java.awt.Insets(0, 0, 0, 0));
        RbtnPorValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbtnPorValorActionPerformed(evt);
            }
        });

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("First Row");
        jLabel1.setBounds(50, 10, 80, 20);
        jLayeredPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        TexFini.setFont(new java.awt.Font("Tahoma", 0, 14));
        TexFini.setForeground(new java.awt.Color(0, 0, 204));
        TexFini.setToolTipText("First Row");
        TexFini.setBounds(50, 40, 70, 40);
        jLayeredPane1.add(TexFini, javax.swing.JLayeredPane.DEFAULT_LAYER);

        TexFfin.setFont(new java.awt.Font("Tahoma", 0, 14));
        TexFfin.setForeground(new java.awt.Color(0, 0, 204));
        TexFfin.setToolTipText("Last Row");
        TexFfin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TexFfinActionPerformed(evt);
            }
        });

        TexFfin.setBounds(180, 40, 70, 40);
        jLayeredPane1.add(TexFfin, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Last Row");
        jLabel3.setBounds(180, 10, 80, 20);
        jLayeredPane1.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        BtnAplicar.setText("Play");
        BtnAplicar.setToolTipText("Execute the Configuration");
        BtnAplicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAplicarActionPerformed(evt);
            }
        });

        BtnCerrar.setText("Close");
        BtnCerrar.setToolTipText("Close the Configuration");
        BtnCerrar.setEnabled(false);
        BtnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCerrarActionPerformed(evt);
            }
        });

        RbtnMantener.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(RbtnMantener);
        RbtnMantener.setFont(new java.awt.Font("Tahoma", 1, 12));
        RbtnMantener.setForeground(new java.awt.Color(0, 0, 204));
        RbtnMantener.setSelected(true);
        RbtnMantener.setText("Keep");
        RbtnMantener.setToolTipText("Keep the set");
        RbtnMantener.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        RbtnMantener.setMargin(new java.awt.Insets(0, 0, 0, 0));
        RbtnMantener.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbtnMantenerActionPerformed(evt);
            }
        });

        RbtnRemover.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(RbtnRemover);
        RbtnRemover.setFont(new java.awt.Font("Tahoma", 1, 12));
        RbtnRemover.setForeground(new java.awt.Color(0, 0, 204));
        RbtnRemover.setText("Remove");
        RbtnRemover.setToolTipText("Remove the set");
        RbtnRemover.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        RbtnRemover.setMargin(new java.awt.Insets(0, 0, 0, 0));
        RbtnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbtnRemoverActionPerformed(evt);
            }
        });

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jLayeredPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Numerics", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255)));
        jLayeredPane4.setToolTipText("Reduce by Numeric Value ");
        TexMenores.setFont(new java.awt.Font("Tahoma", 0, 14));
        TexMenores.setForeground(new java.awt.Color(0, 0, 204));
        TexMenores.setToolTipText("Upper Limit ");
        TexMenores.setEnabled(false);
        TexMenores.setBounds(170, 20, 70, 40);
        jLayeredPane4.add(TexMenores, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Smaller than:");
        jLabel4.setEnabled(false);
        jLabel4.setBounds(40, 30, 120, 20);
        jLayeredPane4.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane4.setBounds(10, 60, 290, 70);
        jLayeredPane2.add(jLayeredPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        CmbItem.setFont(new java.awt.Font("Tahoma", 0, 16));
        CmbItem.setModel(new javax.swing.DefaultComboBoxModel(atributos));
        CmbItem.setToolTipText("Select an Attribute");
        CmbItem.setEnabled(false);
        CmbItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CmbItemActionPerformed(evt);
            }
        });

        CmbItem.setBounds(110, 10, 190, 40);
        jLayeredPane2.add(CmbItem, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Attribute :");
        jLabel2.setEnabled(false);
        jLabel2.setBounds(10, 20, 100, 20);
        jLayeredPane2.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Alphabetics", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255)));
        jLayeredPane5.setToolTipText("Reduce by Alphabetical Value");
        jScrollPane1.setToolTipText("Alphabetical Values");
        TableAtrib.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(TableAtrib);

        jScrollPane1.setBounds(10, 20, 270, 150);
        jLayeredPane5.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane5.setBounds(10, 140, 290, 180);
        jLayeredPane2.add(jLayeredPane5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(43, 43, 43)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(BtnAplicar)
                            .add(RbtnMantener))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 124, Short.MAX_VALUE)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(RbtnRemover)
                            .add(BtnCerrar))
                        .add(28, 28, 28))
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLayeredPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(RbtnPorRango)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 237, Short.MAX_VALUE))))
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(RbtnPorValor))
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLayeredPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 309, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(RbtnPorRango)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLayeredPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 97, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(RbtnPorValor)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLayeredPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(RbtnRemover)
                    .add(RbtnMantener))
                .add(15, 15, 15)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(BtnAplicar)
                    .add(BtnCerrar))
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void CmbItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CmbItemActionPerformed
        final Object[][] datos = new Object[datosEntrada.getRowCount()][2];
        final String[] nomcol = new String[2];
        Object[] valores = new Object[datosEntrada.getRowCount()];
        int catri = 1, con = 0;
        
        String valmen = null;
        colsel = datosEntrada.findColumn(CmbItem.getModel().getSelectedItem().toString());
        if(colsel == -1){
            JOptionPane.showMessageDialog(this, "You must select some Attribute","Error in Configure Reduction.",JOptionPane.ERROR_MESSAGE);
        } else {
            se = 0;
            if(datosEntrada.getColumnClass(colsel).getSimpleName().equals("Integer")) {  //************
                TexMenores.setEnabled(true);
                jLabel4.setEnabled(true);
                TableAtrib.setEnabled(false);
                se = 0;
            } else if(datosEntrada.getColumnClass(colsel).getSimpleName().equals("String")) {
                TexMenores.setEnabled(false);
                jLabel4.setEnabled(false);
                TableAtrib.setEnabled(true);
                valAtri = new ValAtributos(colsel, datosEntrada);
                TableAtrib.setModel(valAtri);
                
                se = 1;
            }
        }
    }//GEN-LAST:event_CmbItemActionPerformed
    
    private void RbtnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RbtnRemoverActionPerformed
        rbtManRem = 1;
    }//GEN-LAST:event_RbtnRemoverActionPerformed
    
    private void RbtnMantenerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RbtnMantenerActionPerformed
        rbtManRem = 0;
    }//GEN-LAST:event_RbtnMantenerActionPerformed
    
    private void TexFfinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TexFfinActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_TexFfinActionPerformed
    
    private void RbtnPorValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RbtnPorValorActionPerformed
        jLabel1.setEnabled(false);
        jLabel3.setEnabled(false);
        TexFini.setEnabled(false);
        TexFfin.setEnabled(false);
        
        jLabel2.setEnabled(true);
        CmbItem.setEnabled(true);
        TexMenores.setEnabled(false);
        jLabel4.setEnabled(false);
        
        rbtsel = 1;
    }//GEN-LAST:event_RbtnPorValorActionPerformed
    
    private void BtnAplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAplicarActionPerformed
        String cad;
        char uc;
        int bdSem = 0, x = 0, fils = 0, val = 0;
        String valmen = null;
        // -----------------------
        fils = datosEntrada.getRowCount();
        
        
        if(RbtnPorRango.isSelected() == true){
            fini = 0; ffin = 0;
            cad = TexFini.getText();
            for(int y = 0; y < cad.length(); y++) {
                uc = cad.charAt(y);
                if(uc>47 && uc<58) x++;
            }
            if(x==cad.length()) bdSem = 1;
            else bdSem = 0;
            if(cad.equals("")) bdSem = 0;
            
            if(bdSem == 0) {
                JOptionPane.showMessageDialog(this, "First row must contain numerical values",
                        "Error in Configure Reduction.",JOptionPane.ERROR_MESSAGE);
            } else {
                val = Integer.parseInt(TexFini.getText());
                if(val < 0 || val > fils ) {
                    valmen = "The values of First Row must be included between 0 and " + Integer.toString(fils);
                    JOptionPane.showMessageDialog(this, valmen,"Error in Configure Reduction.",JOptionPane.ERROR_MESSAGE);
                } else {
                    fini = Integer.parseInt(cad);
                }
            }
            
            x = 0;
            cad = TexFfin.getText();
            for(int y = 0; y < cad.length(); y++) {
                uc = cad.charAt(y);
                if(uc>47 && uc<58) x++;
            }
            if(x==cad.length()) bdSem = 1;
            else bdSem = 0;
            if(cad.equals("")) bdSem = 0;
            
            if(bdSem == 0) {
                JOptionPane.showMessageDialog(this, "Last row must contain numerical values",
                        "Error in Configure Reduction.",JOptionPane.ERROR_MESSAGE);
            } else {
                val = Integer.parseInt(TexFfin.getText());
                if(val < 0 || val > fils ) {
                    valmen = "The values of Last Row must be included between 0 and  " + Integer.toString(fils);
                    JOptionPane.showMessageDialog(this, valmen,"Error in Configure Reduction.",JOptionPane.ERROR_MESSAGE);
                } else {
                    ffin = Integer.parseInt(cad);
                }
            }
            
            if(fini != 0 && ffin != 0 && fini > ffin) {
                valmen = "The First row must be greater than the Last row";
                JOptionPane.showMessageDialog(this, valmen,"Error in Configure Reduction.",JOptionPane.ERROR_MESSAGE);
            } else if(fini != 0 && ffin != 0) {
                BtnCerrar.setEnabled(true);
            }
        }
        
        if(RbtnPorValor.isSelected() == true){
            if(se == 0) {
                cad = TexMenores.getText();
                for(int y = 0; y < cad.length(); y++) {
                    uc = cad.charAt(y);
                    if(uc>47 && uc<58) x++;
                }
                if(x==cad.length()) bdSem = 1;
                else bdSem = 0;
                if(cad.equals("")) bdSem = 0;
                
                if(bdSem == 0) {
                    JOptionPane.showMessageDialog(this, "Smaller than must contain numerical values",
                            "Error in Configure Reduction.",JOptionPane.ERROR_MESSAGE);
                } else {
                    menores = Integer.parseInt(cad);
                    BtnCerrar.setEnabled(true);
                }
            } else if(se == 1) {
                for(int f = 0; f < valAtri.getRowCount(); f++) {  
                    if(valAtri.getValueAt(f,1).toString().equals("true")) {
                        atris.add(valAtri.getValueAt(f,0).toString());
                    }
                }
                BtnCerrar.setEnabled(true);
            }
        }
    }//GEN-LAST:event_BtnAplicarActionPerformed
    
    private void BtnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCerrarActionPerformed
        dispose();
        //System.exit( 0 );
    }//GEN-LAST:event_BtnCerrarActionPerformed
    
    private void RbtnPorRangoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RbtnPorRangoActionPerformed
        jLabel1.setEnabled(true);
        jLabel3.setEnabled(true);
        TexFini.setEnabled(true);
        TexFfin.setEnabled(true);
        
        jLabel2.setEnabled(false);
        CmbItem.setEnabled(false);
        TexMenores.setEnabled(false);
        jLabel4.setEnabled(false);
        
        rbtsel = 0;
    }//GEN-LAST:event_RbtnPorRangoActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAplicar;
    private javax.swing.JButton BtnCerrar;
    private javax.swing.JComboBox CmbItem;
    private javax.swing.JRadioButton RbtnMantener;
    private javax.swing.JRadioButton RbtnPorRango;
    private javax.swing.JRadioButton RbtnPorValor;
    private javax.swing.JRadioButton RbtnRemover;
    private javax.swing.JTable TableAtrib;
    private javax.swing.JTextField TexFfin;
    private javax.swing.JTextField TexFini;
    private javax.swing.JTextField TexMenores;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JLayeredPane jLayeredPane5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    
}
