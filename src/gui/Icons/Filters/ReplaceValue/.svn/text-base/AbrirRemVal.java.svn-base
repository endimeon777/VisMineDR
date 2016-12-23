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

package gui.Icons.Filters.ReplaceValue;

import gui.Icons.Filters.ValAtributos;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author  Tariy
 */
public class AbrirRemVal extends javax.swing.JFrame {
    AbstractTableModel datosEntrada;
    String[] atributos;
    ArrayList atris;
    String texval;
    ValAtributos valAtri;
    int colsel, nAtriSel;
    
    public AbrirRemVal(AbstractTableModel dataIn) {
        datosEntrada = dataIn;
        atributos  = new String[datosEntrada.getColumnCount()+1];
        atris = new ArrayList(1);
        int pa;
        //____________
        
        atributos[0] = "Select an Attribute";
        pa = 1;
        for(int i = 0; i < datosEntrada.getColumnCount(); i++) {
            if(datosEntrada.getColumnClass(i).getSimpleName().equals("String")) {
                atributos[pa] = datosEntrada.getColumnName(i);
                pa ++;
            }
        }
        initComponents();
    }
    
    
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        BtnAplicar = new javax.swing.JButton();
        BtnCerrar = new javax.swing.JButton();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        CmbItem = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLayeredPane5 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableAtrib = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        TexRemCon = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configure Filter");
        setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBackground(new java.awt.Color(81, 81, 133));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        CmbItem.setFont(new java.awt.Font("Tahoma", 0, 16));
        CmbItem.setModel(new javax.swing.DefaultComboBoxModel(atributos));
        CmbItem.setToolTipText("Select an Attribute");
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
        jLabel2.setBounds(10, 20, 90, 20);
        jLayeredPane2.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Alphabetics", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255)));
        jScrollPane1.setToolTipText("Alphabetical Values");
        TableAtrib.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(TableAtrib);

        jScrollPane1.setBounds(10, 20, 270, 160);
        jLayeredPane5.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane5.setBounds(10, 60, 290, 190);
        jLayeredPane2.add(jLayeredPane5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Replace by :");
        jLabel3.setBounds(30, 270, 140, 20);
        jLayeredPane2.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        TexRemCon.setFont(new java.awt.Font("Tahoma", 0, 14));
        TexRemCon.setToolTipText("Value to Replace");
        TexRemCon.setBounds(150, 270, 150, 30);
        jLayeredPane2.add(TexRemCon, javax.swing.JLayeredPane.DEFAULT_LAYER);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(54, 54, 54)
                .add(BtnAplicar)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 96, Short.MAX_VALUE)
                .add(BtnCerrar)
                .add(74, 74, 74))
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLayeredPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 309, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLayeredPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
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
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void CmbItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CmbItemActionPerformed
        
        colsel = datosEntrada.findColumn(CmbItem.getModel().getSelectedItem().toString());
        if(colsel == -1){
            JOptionPane.showMessageDialog(this, "You must select some Attribute","Error in Configure Remplace Value",JOptionPane.ERROR_MESSAGE);
        } else {
            valAtri = new ValAtributos(colsel, datosEntrada);
            TableAtrib.setModel(valAtri);
        }
    }//GEN-LAST:event_CmbItemActionPerformed
    
    private void BtnAplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAplicarActionPerformed
        String cad;
        char uc;
        int x = 0;
        //____________
        
        cad = TexRemCon.getText();
        for(int y = 0; y < cad.length(); y++) {
            uc = cad.charAt(y);
            if(uc>47 && uc<58) x++;
        }
        if(x==cad.length()) {
            JOptionPane.showMessageDialog(this, "Replace by must contain alphabetical values ",
                    "Error in Configure Remplace Value.",JOptionPane.ERROR_MESSAGE);
        } else {
            texval = TexRemCon.getText();
            BtnCerrar.setEnabled(true);
        }
        nAtriSel = 0;
        for(int f = 0; f < valAtri.getRowCount(); f++) {  // talves esta parte del for deberia ser en el boton aplicar anction performed
            if(valAtri.getValueAt(f,1).toString().equals("true")) {
                atris.add(valAtri.getValueAt(f,0).toString());
                nAtriSel ++;
            }
        }
    }//GEN-LAST:event_BtnAplicarActionPerformed
    
    private void BtnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCerrarActionPerformed
        //System.exit( 0 );
        dispose();
    }//GEN-LAST:event_BtnCerrarActionPerformed
    
    public ArrayList getAtriSel() {
        return atris;
    }
    
    public String getRemCon() {
        return texval;
    }
    
    public int getColSel() {
        return colsel;
    }
    
    public int getNumAtri() {
        return nAtriSel;
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAplicar;
    private javax.swing.JButton BtnCerrar;
    private javax.swing.JComboBox CmbItem;
    private javax.swing.JTable TableAtrib;
    private javax.swing.JTextField TexRemCon;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    
}
