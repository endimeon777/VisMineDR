/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * miOpenFile.java
 *
 * Created on 24-ene-2012, 16:33:54
 */
package gui.Icons.Filters.Reduction;

import gui.Icons.File.*;
import Utils.ExampleFileFilter;
import Utils.TableOptimalWidth;
import gui.Icons.Filters.ValAtributos;
import gui.KnowledgeFlow.ChooserEscritorio;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Juan Carlos
 */
public class miAbrirReduccion extends javax.swing.JFrame {
    AbstractTableModel datosEntrada;
    ValAtributos valAtri;
    String atributos[];
    int rbtsel = 0, fini = 0, ffin = 0, colsel = 0, se = 0, nAtriSel = 0, rbtManRem = 0;
    double menores = 0;
    ArrayList atris;
    
    
    public miAbrirReduccion(AbstractTableModel dataIn) {       
        datosEntrada = dataIn;
        atributos = new String[datosEntrada.getColumnCount()+1];
        atris = new ArrayList(1);
        atributos[0] = "select an Attribute";
        for(int i = 0; i < datosEntrada.getColumnCount(); i++) {
            atributos[i+1] = datosEntrada.getColumnName(i);
        }
        initComponents();
    }
    
    public miAbrirReduccion() {       
        initComponents();
    }
    
    public void setDatas(AbstractTableModel dataIn, int selRbtn, int rManRem, int filIni, int filFin, double minor, int columnSelected, ArrayList valuesAttribute) {       
        datosEntrada = dataIn;
//        rbtsel = selRbtn;
//        fini = filIni;
//        ffin = filFin;
//        menores = minor; 
//        colsel = columnSelected; 
//        rbtManRem = rManRem;
        
        if(atributos == null){
            atributos = new String[datosEntrada.getColumnCount()+1];
            atris = new ArrayList(1);
            atributos[0] = "select an Attribute";
            CmbItem.addItem(atributos[0]);
            for(int i = 1; i < datosEntrada.getColumnCount()+1; i++) {
                atributos[i] = datosEntrada.getColumnName(i-1);
                CmbItem.addItem(atributos[i]);
            }
        }
        
        TexFini.setText(String.valueOf(filIni));
        TexFfin.setText(String.valueOf(filFin));

        if(columnSelected != -1){

            CmbItem.setSelectedIndex(columnSelected+1);
            TexMenores.setText(String.valueOf(minor));
            
            if(datosEntrada.getColumnClass(columnSelected).getSimpleName().equals("Integer")) {  //************
                TexMenores.setEnabled(true);
                jLabel4.setEnabled(true);
                TableAtrib.setEnabled(false);
                TableAtrib.setModel(new DefaultTableModel());          
            } else if(datosEntrada.getColumnClass(columnSelected).getSimpleName().equals("String")) {
                TexMenores.setEnabled(false);
                jLabel4.setEnabled(false);
                TableAtrib.setEnabled(true);

                valAtri = new ValAtributos(columnSelected, datosEntrada);
                if(valuesAttribute != null){ 
                    for(int i = 0; i < valuesAttribute.size(); i++){
                        valAtri.setSelection(valuesAttribute.get(i));
                    }
                }
                TableAtrib.setModel(valAtri);
           }
        }
        
        if(rManRem == 0){
            RbtnMantener.setSelected(true);
            RbtnRemover.setSelected(false);
        }else if(rManRem == 1){
            RbtnMantener.setSelected(false);
            RbtnRemover.setSelected(true);
        }
        
        tabbPanelReduction.setSelectedIndex(selRbtn);
        
//        if(selRbtn == 0){
//            panelByRange.setVisible(true);
//        }else if(selRbtn == 1){
//            panelByValue.setVisible(true);
//        }   
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
    
    public Double getMenoresQue() { // umbral de los valores numericos menores que este
        return menores;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        BtnAplicar = new javax.swing.JButton();
        BtnCerrar = new javax.swing.JButton();
        RbtnMantener = new javax.swing.JRadioButton();
        RbtnRemover = new javax.swing.JRadioButton();
        BtnReset = new javax.swing.JButton();
        tabbPanelReduction = new javax.swing.JTabbedPane();
        panelByRange = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        TexFini = new javax.swing.JTextField();
        TexFfin = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        panelByValue = new javax.swing.JPanel();
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

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        BtnAplicar.setForeground(new java.awt.Color(51, 51, 51));
        BtnAplicar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/play.png"))); // NOI18N
        BtnAplicar.setText("Play");
        BtnAplicar.setToolTipText("Execute the Configuration");
        BtnAplicar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnAplicar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnAplicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAplicarActionPerformed(evt);
            }
        });

        BtnCerrar.setForeground(new java.awt.Color(51, 51, 51));
        BtnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit2.png"))); // NOI18N
        BtnCerrar.setText("Close");
        BtnCerrar.setToolTipText("Close the Configuration");
        BtnCerrar.setEnabled(false);
        BtnCerrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnCerrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCerrarActionPerformed(evt);
            }
        });

        RbtnMantener.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(RbtnMantener);
        RbtnMantener.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
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
        RbtnRemover.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        RbtnRemover.setText("Remove");
        RbtnRemover.setToolTipText("Remove the set");
        RbtnRemover.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        RbtnRemover.setMargin(new java.awt.Insets(0, 0, 0, 0));
        RbtnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbtnRemoverActionPerformed(evt);
            }
        });

        BtnReset.setForeground(new java.awt.Color(51, 51, 51));
        BtnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/undo.png"))); // NOI18N
        BtnReset.setText("Reset");
        BtnReset.setToolTipText("Reset the Parameters");
        BtnReset.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnReset.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnResetMouseClicked(evt);
            }
        });
        BtnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(RbtnMantener)
                            .addComponent(RbtnRemover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BtnAplicar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(BtnCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RbtnMantener)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RbtnRemover)
                .addGap(18, 18, 18)
                .addComponent(BtnAplicar)
                .addGap(51, 51, 51)
                .addComponent(BtnCerrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnReset)
                .addGap(32, 32, 32))
        );

        tabbPanelReduction.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabbPanelReductionMouseClicked(evt);
            }
        });

        panelByRange.setBackground(new java.awt.Color(255, 255, 255));

        jLayeredPane1.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("First Row");
        jLabel1.setBounds(60, 20, 50, 20);
        jLayeredPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        TexFini.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TexFini.setForeground(new java.awt.Color(0, 0, 204));
        TexFini.setToolTipText("First Row");
        TexFini.setBounds(50, 50, 70, 40);
        jLayeredPane1.add(TexFini, javax.swing.JLayeredPane.DEFAULT_LAYER);

        TexFfin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TexFfin.setForeground(new java.awt.Color(0, 0, 204));
        TexFfin.setToolTipText("Last Row");
        TexFfin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TexFfinActionPerformed(evt);
            }
        });
        TexFfin.setBounds(50, 180, 70, 40);
        jLayeredPane1.add(TexFfin, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Last Row");
        jLabel3.setBounds(60, 150, 60, 20);
        jLayeredPane1.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout panelByRangeLayout = new javax.swing.GroupLayout(panelByRange);
        panelByRange.setLayout(panelByRangeLayout);
        panelByRangeLayout.setHorizontalGroup(
            panelByRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelByRangeLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );
        panelByRangeLayout.setVerticalGroup(
            panelByRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelByRangeLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        tabbPanelReduction.addTab("By Range", panelByRange);

        panelByValue.setBackground(new java.awt.Color(255, 255, 255));

        jLayeredPane2.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLayeredPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Numerics", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jLayeredPane4.setToolTipText("Reduce by Numeric Value ");

        TexMenores.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TexMenores.setForeground(new java.awt.Color(0, 0, 204));
        TexMenores.setToolTipText("Upper Limit ");
        TexMenores.setEnabled(false);
        TexMenores.setBounds(130, 20, 70, 40);
        jLayeredPane4.add(TexMenores, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Smaller than:");
        jLabel4.setEnabled(false);
        jLabel4.setBounds(20, 30, 80, 20);
        jLayeredPane4.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane4.setBounds(10, 60, 240, 70);
        jLayeredPane2.add(jLayeredPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        CmbItem.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        CmbItem.setToolTipText("Select an Attribute");
        CmbItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CmbItemActionPerformed(evt);
            }
        });
        CmbItem.setBounds(100, 10, 150, 40);
        jLayeredPane2.add(CmbItem, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Attribute :");
        jLabel2.setBounds(20, 20, 80, 20);
        jLayeredPane2.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Alphabetics", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jLayeredPane5.setToolTipText("Reduce by Alphabetical Value");

        jScrollPane1.setToolTipText("Alphabetical Values");

        TableAtrib.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TableAtrib.setEnabled(false);
        jScrollPane1.setViewportView(TableAtrib);

        jScrollPane1.setBounds(10, 20, 220, 150);
        jLayeredPane5.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane5.setBounds(10, 140, 240, 180);
        jLayeredPane2.add(jLayeredPane5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout panelByValueLayout = new javax.swing.GroupLayout(panelByValue);
        panelByValue.setLayout(panelByValueLayout);
        panelByValueLayout.setHorizontalGroup(
            panelByValueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelByValueLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        panelByValueLayout.setVerticalGroup(
            panelByValueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelByValueLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        tabbPanelReduction.addTab("By Value", panelByValue);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbPanelReduction, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tabbPanelReduction, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TexFfinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TexFfinActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_TexFfinActionPerformed

    private void BtnAplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAplicarActionPerformed
        String cad;
        char uc;
        int bdSem = 0, x = 0, fils = 0, val = 0;
        String valmen = null;
        // -----------------------
        fils = datosEntrada.getRowCount();
        
        if(panelByRange.isShowing() == true){
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
                JOptionPane.showMessageDialog(this, "First row must contain numerical values","Error in Configure Reduction.",JOptionPane.ERROR_MESSAGE);
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
        
        else if(panelByValue.isShowing() == true){
            // para valores numericos
            if(se == 0) {
                cad = TexMenores.getText();
//                for(int y = 0; y < cad.length(); y++) {
//                    uc = cad.charAt(y);
//                    if(uc>47 && uc<58) x++;
//                }
//                if(x==cad.length()) bdSem = 1;
//                else bdSem = 0;
//                
//                if(cad.equals("")) bdSem = 0;
                
                if(esEntero(cad) || esDouble(cad)) { 
                    menores = Double.parseDouble(cad);
                    BtnCerrar.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Smaller than must contain numerical values","Error in Configure Reduction.",JOptionPane.ERROR_MESSAGE);
                }
            // para valores alfabeticos
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

         private boolean esEntero(String cad){
        boolean bd = false;
        int valNum = 0;
                
        try{  // si lo puede transformar a numero lo transforma a numero
          valNum = Integer.parseInt(cad);
          bd = true;
        } catch (Exception e) {
          bd = false;
        }
        return bd;
    }
    
    private boolean esDouble(String cad){
        boolean bd = false;
        double valNum = 1.0;
                
        try{  // si lo puede transformar a numero lo transforma a numero
          valNum = Double.parseDouble(cad);
          bd = true;
        } catch (Exception e) {
          bd = false;
        }
        return bd;
    }
    
    private void BtnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCerrarActionPerformed
        this.dispose();
        //System.exit( 0 );
}//GEN-LAST:event_BtnCerrarActionPerformed

    private void RbtnMantenerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RbtnMantenerActionPerformed
        rbtManRem = 0;
}//GEN-LAST:event_RbtnMantenerActionPerformed

    private void RbtnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RbtnRemoverActionPerformed
        rbtManRem = 1;
}//GEN-LAST:event_RbtnRemoverActionPerformed

    private void CmbItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CmbItemActionPerformed
        final Object[][] datos = new Object[datosEntrada.getRowCount()][2];
        final String[] nomcol = new String[2];
        Object[] valores = new Object[datosEntrada.getRowCount()];
        int catri = 1, con = 0;
        
        String valmen = null;
        colsel = datosEntrada.findColumn(CmbItem.getModel().getSelectedItem().toString());
        
        if(colsel != - 1){
            se = 0;
            if(datosEntrada.getColumnClass(colsel).getSimpleName().equals("Integer")) {  //************
                TexMenores.setEnabled(true);
                jLabel4.setEnabled(true);
                TableAtrib.setEnabled(false);
                TableAtrib.setModel(new DefaultTableModel());
                se = 0;   
            }else if(datosEntrada.getColumnClass(colsel).getSimpleName().equals("Double")) {  //************
                TexMenores.setEnabled(true);
                jLabel4.setEnabled(true);
                TableAtrib.setEnabled(false);
                TableAtrib.setModel(new DefaultTableModel());
                se = 0;   
            }else if(datosEntrada.getColumnClass(colsel).getSimpleName().equals("String")) {
                TexMenores.setEnabled(false);
                jLabel4.setEnabled(false);
                TableAtrib.setEnabled(true);
                valAtri = new ValAtributos(colsel, datosEntrada);
                TableAtrib.setModel(valAtri);
                se = 1;
            }
        }
}//GEN-LAST:event_CmbItemActionPerformed

    private void tabbPanelReductionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabbPanelReductionMouseClicked
        if(panelByRange.isShowing() == true){
            rbtsel = 0;
        }else if(panelByValue.isShowing() == true){
            rbtsel = 1;
        }
    }//GEN-LAST:event_tabbPanelReductionMouseClicked

    private void BtnResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnResetMouseClicked
        
}//GEN-LAST:event_BtnResetMouseClicked

    private void BtnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResetActionPerformed
        TableAtrib.setModel(new DefaultTableModel());
        TexFini.setText("");
        TexFfin.setText("");
        RbtnMantener.setSelected(true);
        RbtnRemover.setSelected(false);
        CmbItem.setSelectedIndex(0);
        TexMenores.setText("");
        TexMenores.setEnabled(false);
        jLabel4.setEnabled(false);
}//GEN-LAST:event_BtnResetActionPerformed

     
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            public void run() {
//                new miOpenFile(fi).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAplicar;
    private javax.swing.JButton BtnCerrar;
    private javax.swing.JButton BtnReset;
    private javax.swing.JComboBox CmbItem;
    private javax.swing.JRadioButton RbtnMantener;
    private javax.swing.JRadioButton RbtnRemover;
    private javax.swing.JTable TableAtrib;
    private javax.swing.JTextField TexFfin;
    private javax.swing.JTextField TexFini;
    private javax.swing.JTextField TexMenores;
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
    private javax.swing.JPanel panelByRange;
    private javax.swing.JPanel panelByValue;
    private javax.swing.JTabbedPane tabbPanelReduction;
    // End of variables declaration//GEN-END:variables
}
