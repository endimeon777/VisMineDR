
package gui.Icons.vmd;

import algorithm.classification.c45_1.Attribute;
import algorithm.classification.c45_1.TariyTableModel;
import gui.KnowledgeFlow.Icon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.table.AbstractTableModel;
import processing.core.PApplet;

/**
 *
 * @author Juan Carlos Alvarado
 */
public class vmdIcon extends Icon{
//    private JMenuItem mnuConfigure;
    private JMenuItem mnuRun;
    private JMenuItem mnuView;
    private JMenuItem mnuHelp;
    public Attribute root;
    public AbstractTableModel dataIn;
    PApplet vmdTariy;

    /** Creates a new instance of vmdIcon */
    public vmdIcon(JLabel s, int x, int y, int indiceIcon) {
        super(s, x, y, indiceIcon);
        
        mnuRun = new javax.swing.JMenuItem();
        mnuRun.setText("Run...");
        mnuRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRunActionPerformed(evt);
            }
        });
        //mnuRun.setEnabled(false);
        super.pupMenu.add(mnuRun);
        
        mnuView = new javax.swing.JMenuItem();
        mnuView.setText("View...");
        mnuView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuViewActionPerformed(evt);
            }
        });
        super.pupMenu.add(mnuView);
        
        mnuHelp = new javax.swing.JMenuItem();
        mnuHelp.setText("Help...");
        mnuHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuHelpActionPerformed(evt);
            }
        });
        super.pupMenu.add(mnuHelp);
    }
    
    private void mnuHelpActionPerformed(java.awt.event.ActionEvent evt) {
        final Icon icon = this;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Utils.Help(icon.getName().trim()).setVisible(true);
            }
        });
    }
    
    private void mnuRunActionPerformed(java.awt.event.ActionEvent evt) {
         this.startAnimation();
         vmdTariy = new VMD(dataIn, this);
         vmdTariy.init();
    }
    
    private void mnuViewActionPerformed(java.awt.event.ActionEvent evt) {
        ViewVmd view = new ViewVmd(dataIn, vmdTariy);
        view.setVisible(true);
    }
    
//    public TariyTableModel changeToTariyModel(){
//        int rows = dataIn.getRowCount();
//        int columns = dataIn.getColumnCount();
//        Object[][] data = new Object[rows][columns];
//        String[] columnsName = new String[columns];
//        for(int i = 0; i < columns; i++){
//            for(int j = 0; j < rows; j++){
//                data[j][i] = dataIn.getValueAt(j ,i);
//            }
//            columnsName[i] = dataIn.getColumnName(i);
//        }
//        TariyTableModel tariyModel = new TariyTableModel();
//        tariyModel.setDatos(data);
//        tariyModel.setNomcol(columnsName);
//
//        return tariyModel;
//    }
}
