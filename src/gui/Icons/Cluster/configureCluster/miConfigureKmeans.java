/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * miConfigureKmeans.java
 *
 * Created on 19-jun-2012, 10:07:32
 */
package gui.Icons.Cluster.configureCluster;

import javax.swing.JLabel;

/**
 *
 * @author Juan Carlos
 */


public class miConfigureKmeans extends javax.swing.JFrame {
    private clusterIcon ci;

    /** Creates new form miConfigureKmeans */
    public miConfigureKmeans(clusterIcon ci) {
        initComponents();
        this.ci = ci;
    }
    
    public miConfigureKmeans() {
        initComponents();
    }
    
    public void updateIcon(clusterIcon icon){
        this.ci = icon;
        jspnumcluster.setValue(this.ci.numcluster);
        jcbdistancia.setSelectedIndex(this.ci.distanciakm.getTipo());
   
        if (this.ci.numInteraciones == 0){
            rb1.setSelected(true);
            rb2.setSelected(false);
        }else{
            rb1.setSelected(false);
            rb2.setSelected(true);
            jspNumIterar.setEnabled(true);
            jspNumIterar.setValue(this.ci.numInteraciones);
        }
//        spnSupport.setValue(ai.support);
//        ai.getMnuRun().setEnabled(true);
    }
    
    public void  iniciaVentana(){
}

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jspnumcluster = new javax.swing.JSpinner();
        jcbdistancia = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        rb1 = new javax.swing.JRadioButton();
        rb2 = new javax.swing.JRadioButton();
        jspNumIterar = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Number of cluster");

        jLabel2.setText("Distance");

        jspnumcluster.setToolTipText("Numero de cluster");
        jspnumcluster.setName("jsnumcluster"); // NOI18N

        jcbdistancia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Euclidiana", "Manhattan", "Minkowski" }));
        jcbdistancia.setName("jcbdistancia"); // NOI18N
        jcbdistancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbdistanciaActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/play.png"))); // NOI18N
        jButton2.setText("Play");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        buttonGroup1.add(rb1);
        rb1.setSelected(true);
        rb1.setText("Converge");
        rb1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rb1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(rb2);
        rb2.setText("Number of iterations");
        rb2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rb2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb2ActionPerformed(evt);
            }
        });

        jspNumIterar.setToolTipText("Numero de cluster");
        jspNumIterar.setEnabled(false);
        jspNumIterar.setName("jsnumcluster"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jspnumcluster, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 131, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(rb1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rb2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jspNumIterar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jcbdistancia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 58, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(75, 75, 75))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(jspnumcluster, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jcbdistancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rb1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rb2)
                    .addComponent(jspNumIterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jButton2)
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO: Agrege su codigo aqui:
        int nc = Integer.parseInt(""+jspnumcluster.getValue());
        ci.setNumcluster(nc);
        
        if (rb1.isSelected()){
            ci.setNumIterar(0);
        }else{
            int ni = Integer.parseInt(""+jspNumIterar.getValue());
            ci.setNumIterar(ni);
        }
        
        ci.distanciakm.setTipo(jcbdistancia.getSelectedIndex());
        //  ci.distanciakm.setAtriColumna(this.atriColumnas);
        ci.setInfo("Numero de cluster obtener: " + jspnumcluster + "");// +
        ci.mnuRun.setEnabled(true);
        
        this.dispose();
}//GEN-LAST:event_jButton2ActionPerformed

    private void jcbdistanciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbdistanciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbdistanciaActionPerformed

    private void rb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb2ActionPerformed
        if (rb1.isSelected()){
            jspNumIterar.setEnabled(false);
        }else if(rb2.isSelected()){
            jspNumIterar.setEnabled(true);
        }
    }//GEN-LAST:event_rb2ActionPerformed

    private void rb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb1ActionPerformed
      if (rb1.isSelected()){
            jspNumIterar.setEnabled(false);
        }else if(rb2.isSelected()){
            jspNumIterar.setEnabled(true);
        }
    }//GEN-LAST:event_rb1ActionPerformed

    public void setnumcluster(Object valor) {
      jspnumcluster.setValue(valor);
    }
      public void setDistancia(int valor) {
      jcbdistancia.setSelectedIndex(valor);
    }
      
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                miConfigureKmeans cp = new miConfigureKmeans(new clusterIcon(new JLabel(), 0 , 0, 0));
                cp.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox jcbdistancia;
    private javax.swing.JSpinner jspNumIterar;
    private javax.swing.JSpinner jspnumcluster;
    private javax.swing.JRadioButton rb1;
    private javax.swing.JRadioButton rb2;
    // End of variables declaration//GEN-END:variables
}
