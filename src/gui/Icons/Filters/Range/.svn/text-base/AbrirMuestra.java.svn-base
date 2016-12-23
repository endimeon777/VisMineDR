
/*
 * AbrirMuestra.java
 *
 * Created on 27 de mayo de 2006, 04:34 PM
 */

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


package gui.Icons.Filters.Range;

import javax.swing.JOptionPane;
import gui.Icons.Filters.TariyTableModel;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author  Tariy
 */
public class AbrirMuestra extends javax.swing.JFrame {
    AbstractTableModel datosEntrada;
    int rbtsel = 0, valmuestra = 0;  // valmuestra es el valor de la muestra que puede ser el de la
    // semilla o el de los valores de n, rbtsel es cual rbtn fue seleccionado
    /** Creates new form AbrirMuestra */
    public AbrirMuestra(AbstractTableModel dataIn) {
        datosEntrada = dataIn;
        initComponents();
    }
    
    public int getSelRbtn() {
        return rbtsel;
    }
    
    public int getValMues() {
        return valmuestra;
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        RbtnAle = new javax.swing.JRadioButton();
        Rbtn1enn = new javax.swing.JRadioButton();
        RbtnPrim = new javax.swing.JRadioButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        TexSem = new javax.swing.JTextField();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jLabel2 = new javax.swing.JLabel();
        Tex1enn = new javax.swing.JTextField();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        jLabel3 = new javax.swing.JLabel();
        TexPrim = new javax.swing.JTextField();
        BtnAplicar = new javax.swing.JButton();
        BtnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configure Filter");
        setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBackground(new java.awt.Color(81, 81, 133));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        RbtnAle.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(RbtnAle);
        RbtnAle.setFont(new java.awt.Font("Tahoma", 1, 12));
        RbtnAle.setForeground(new java.awt.Color(0, 0, 204));
        RbtnAle.setSelected(true);
        RbtnAle.setText("Random");
        RbtnAle.setToolTipText("Random");
        RbtnAle.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        RbtnAle.setBorderPainted(true);
        RbtnAle.setMargin(new java.awt.Insets(0, 0, 0, 0));
        RbtnAle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbtnAleActionPerformed(evt);
            }
        });

        Rbtn1enn.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(Rbtn1enn);
        Rbtn1enn.setFont(new java.awt.Font("Tahoma", 1, 12));
        Rbtn1enn.setForeground(new java.awt.Color(0, 0, 204));
        Rbtn1enn.setText("1 in n");
        Rbtn1enn.setToolTipText("1 in n");
        Rbtn1enn.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        Rbtn1enn.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Rbtn1enn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rbtn1ennActionPerformed(evt);
            }
        });

        RbtnPrim.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(RbtnPrim);
        RbtnPrim.setFont(new java.awt.Font("Tahoma", 1, 12));
        RbtnPrim.setForeground(new java.awt.Color(0, 0, 204));
        RbtnPrim.setText("n First");
        RbtnPrim.setToolTipText("n First");
        RbtnPrim.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        RbtnPrim.setMargin(new java.awt.Insets(0, 0, 0, 0));
        RbtnPrim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbtnPrimActionPerformed(evt);
            }
        });

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Rows    :");
        jLabel1.setBounds(20, 20, 100, 20);
        jLayeredPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        TexSem.setFont(new java.awt.Font("Tahoma", 0, 14));
        TexSem.setForeground(new java.awt.Color(0, 0, 204));
        TexSem.setToolTipText("Numbers of rows selected by random");
        TexSem.setBounds(140, 10, 70, 40);
        jLayeredPane1.add(TexSem, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("n Value :");
        jLabel2.setEnabled(false);
        jLabel2.setBounds(20, 20, 110, 20);
        jLayeredPane2.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Tex1enn.setFont(new java.awt.Font("Tahoma", 0, 14));
        Tex1enn.setForeground(new java.awt.Color(0, 0, 204));
        Tex1enn.setToolTipText("This value is the jump of n ");
        Tex1enn.setEnabled(false);
        Tex1enn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Tex1ennActionPerformed(evt);
            }
        });

        Tex1enn.setBounds(140, 10, 70, 40);
        jLayeredPane2.add(Tex1enn, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("n Value :");
        jLabel3.setEnabled(false);
        jLabel3.setBounds(20, 20, 120, 20);
        jLayeredPane4.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        TexPrim.setFont(new java.awt.Font("Tahoma", 0, 14));
        TexPrim.setForeground(new java.awt.Color(0, 0, 204));
        TexPrim.setToolTipText("n is the number of rows from first");
        TexPrim.setEnabled(false);
        TexPrim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TexPrimActionPerformed(evt);
            }
        });

        TexPrim.setBounds(140, 10, 70, 40);
        jLayeredPane4.add(TexPrim, javax.swing.JLayeredPane.DEFAULT_LAYER);

        BtnAplicar.setText("Play");
        BtnAplicar.setToolTipText("execute the Configuration");
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

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jLayeredPane4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                            .add(RbtnAle)
                            .add(Rbtn1enn)
                            .add(jLayeredPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jLayeredPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                            .add(RbtnPrim)))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(43, 43, 43)
                        .add(BtnAplicar)
                        .add(44, 44, 44)
                        .add(BtnCerrar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(RbtnAle)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLayeredPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 65, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(26, 26, 26)
                .add(Rbtn1enn)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLayeredPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 63, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(27, 27, 27)
                .add(RbtnPrim)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLayeredPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 61, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(23, 23, 23)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(BtnCerrar)
                    .add(BtnAplicar))
                .add(46, 46, 46))
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
                .add(23, 23, 23)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 389, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void Tex1ennActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Tex1ennActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_Tex1ennActionPerformed
    
    private void TexPrimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TexPrimActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_TexPrimActionPerformed
    
    private void RbtnPrimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RbtnPrimActionPerformed
        jLabel1.setEnabled(false);
        TexSem.setEnabled(false);
        jLabel2.setEnabled(false);
        Tex1enn.setEnabled(false);
        jLabel3.setEnabled(true);
        TexPrim.setEnabled(true);
        rbtsel = 2;
    }//GEN-LAST:event_RbtnPrimActionPerformed
    
    private void Rbtn1ennActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rbtn1ennActionPerformed
        jLabel1.setEnabled(false);
        TexSem.setEnabled(false);
        jLabel2.setEnabled(true);
        Tex1enn.setEnabled(true);
        jLabel3.setEnabled(false);
        TexPrim.setEnabled(false);
        rbtsel = 1;
    }//GEN-LAST:event_Rbtn1ennActionPerformed
    
    private void BtnAplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAplicarActionPerformed
        String cad;
        char uc;
        int bdSem = 0, bdenn = 0, bdPrim = 0, x = 0, fils = 0, val = 0;
        String valmen = null;
        // -----------------------
        fils = datosEntrada.getRowCount();
        
        if(RbtnAle.isSelected()==true){
            cad = TexSem.getText();
            for(int y = 0; y < cad.length(); y++) {
                uc = cad.charAt(y);
                if(uc>47 && uc<58) x++;
            }
            if(x==cad.length()) bdSem = 1;
            else bdSem = 0;
            if(cad.equals("")) bdSem = 0;
            
            if(bdSem == 0) {
                JOptionPane.showMessageDialog(this, "Rows must contain numerical values.",
                        "Error in Configure Range.",JOptionPane.ERROR_MESSAGE);
            } else {
                val = Integer.parseInt(TexSem.getText());
                if(val < 0 || val > fils ) {
                    valmen = "The values of Rows must be included between 0 and " + Integer.toString(fils);
                    JOptionPane.showMessageDialog(this, valmen,
                            "Error in Configure Range.",JOptionPane.ERROR_MESSAGE);
                } else {
                    valmuestra = Integer.parseInt(cad);
                    BtnCerrar.setEnabled(true);
                }
            }
        }
        
        if(Rbtn1enn.isSelected()==true){
            x = 0;
            cad = Tex1enn.getText();
            for(int y = 0; y < cad.length(); y++) {
                uc = cad.charAt(y);
                if(uc>47 && uc<58) x++;
            }
            if(x==cad.length()) bdenn = 1;
            else bdenn = 0;
            if(cad.equals("")) bdenn = 0;
            
            if(bdenn == 0) {
                JOptionPane.showMessageDialog(this, "Value of n of 1 in n must contain numerical values",
                        "Error in Configure Range.",JOptionPane.ERROR_MESSAGE);
            } else {
                val = Integer.parseInt(Tex1enn.getText());
                if(val < 0 || val > fils ) {
                    valmen = "The values of 1 in n must be included between 0 and " + Integer.toString(fils);
                    JOptionPane.showMessageDialog(this, valmen,
                            "Error in Configure Range.",JOptionPane.ERROR_MESSAGE);
                } else {
                    valmuestra = Integer.parseInt(cad);
                    BtnCerrar.setEnabled(true);
                }
            }
        }
        
        if(RbtnPrim.isSelected()==true){
            x = 0;
            cad = TexPrim.getText();
            for(int y = 0; y < cad.length(); y++) {
                uc = cad.charAt(y);
                if(uc>47 && uc<58) x++;
            }
            if(x==cad.length()) bdPrim = 1;
            else bdPrim = 0;
            if(cad.equals("")) bdPrim = 0;
            
            if(bdPrim == 0) {
                JOptionPane.showMessageDialog(this, "Value of n of First n must contain numerical values",
                        "Error in Configure Range.",JOptionPane.ERROR_MESSAGE);
            } else {
                val = Integer.parseInt(TexPrim.getText());
                if(val < 0 || val > fils ) {
                    valmen = "The values of First n must be included between 0 and " + Integer.toString(fils);
                    JOptionPane.showMessageDialog(this, valmen,
                            "Error in Configure Range.",JOptionPane.ERROR_MESSAGE);
                } else {
                    valmuestra = Integer.parseInt(cad);
                    BtnCerrar.setEnabled(true);
                }
            }
        }
    }//GEN-LAST:event_BtnAplicarActionPerformed
    
    private void BtnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCerrarActionPerformed
        //System.exit( 0 );
        this.dispose();
    }//GEN-LAST:event_BtnCerrarActionPerformed
    
    private void RbtnAleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RbtnAleActionPerformed
        jLabel1.setEnabled(true);
        TexSem.setEnabled(true);
        jLabel2.setEnabled(false);
        Tex1enn.setEnabled(false);
        jLabel3.setEnabled(false);
        TexPrim.setEnabled(false);
        rbtsel = 0;
    }//GEN-LAST:event_RbtnAleActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new AbrirMuestra().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAplicar;
    private javax.swing.JButton BtnCerrar;
    private javax.swing.JRadioButton Rbtn1enn;
    private javax.swing.JRadioButton RbtnAle;
    private javax.swing.JRadioButton RbtnPrim;
    private javax.swing.JTextField Tex1enn;
    private javax.swing.JTextField TexPrim;
    private javax.swing.JTextField TexSem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
    
}
