/*
 * DBConnectionIcon.java
 *
 * Created on 25 de mayo de 2006, 05:28 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gui.Icons.DBConnection;

import Utils.DataSet;
import gui.Icons.Association.AssociationIcon;
import gui.Icons.DBConnection.ConnectionWizard;
import gui.KnowledgeFlow.Icon;
import gui.Icons.DBConnection.miSelectorTable;
import gui.KnowledgeFlow.ChooserEscritorio;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.table.AbstractTableModel;

/**
 *
 *
 */
public class DBConnectionIcon extends Icon{
    public JMenuItem mnuConfigure;
    private JMenuItem mnuHelp;
    private JMenuItem mnuSelector;
    private JMenuItem mnuLoad;
    
    public static Connection connection = null;
    public AbstractTableModel connectionTableModel;
    public DataSet dataset = null;
    public String sqlCreado = "";
    public String sqlColumnas = "";
    
    public int driver = 0;
    public String port = "";
    public String host = "";
    public String user = "";
    public String password = "";
    public String database = "";
    
    public static miConnectionWizard myConnectionWizard;
    public static miSelectorTable mySelectorTable;
    
    /** Creates a new instance of DBConnectionIcon */
    public DBConnectionIcon(JLabel s, int x, int y, int indiceIcon) {
        super(s, x, y, indiceIcon);
        super.constrainsTo = new ArrayList(2);
        super.constrainsTo.add("AssociationIcon");
        super.constrainsTo.add("FilterIcon");
        super.constrainsTo.add("PredictionIcon");
        super.constrainsTo.add("ClasificationIcon");
        super.constrainsTo.add("StandarizeIcon");
        
        mnuConfigure = new javax.swing.JMenuItem();
        mnuConfigure.setText("Configure...");
        mnuConfigure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuConfigureActionPerformed(evt);
            }
        });
        super.pupMenu.add(mnuConfigure);
////////myConnectionWizard = null;
        myConnectionWizard = new miConnectionWizard(this);
        myConnectionWizard.setVisible(false);
  
        
        mnuSelector = new javax.swing.JMenuItem();
        mnuSelector.setText("Attribute Selection...");
        mnuSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSelectorActionPerformed(evt);
            }
        });
        mnuSelector.setEnabled(false);
        super.pupMenu.add(mnuSelector);
        mySelectorTable = new miSelectorTable(this);
        mySelectorTable.setVisible(false);
        
        mnuLoad = new javax.swing.JMenuItem();
        mnuLoad.setText("Start Loading...");
        mnuLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLoadActionPerformed(evt);
            }
        });
        mnuLoad.setEnabled(false);
        super.pupMenu.add(mnuLoad);
        
        mnuHelp = new javax.swing.JMenuItem();
        mnuHelp.setText("Help...");
        mnuHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuHelpActionPerformed(evt);
            }
        });
        super.pupMenu.add(mnuHelp);
    }
    
    public AbstractTableModel getTable(){
        return mySelectorTable.tableModel;
    }
    
    private void mnuConfigureActionPerformed(java.awt.event.ActionEvent evt) {
        final DBConnectionIcon icon = this;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                  myConnectionWizard.updateIcon(icon);
                  myConnectionWizard.setVals();
                  myConnectionWizard.setVisible(true);   
            }
        });
    }
    
    private void mnuHelpActionPerformed(java.awt.event.ActionEvent evt) {
        final Icon icon = this;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new Utils.Help(icon.getName().trim()).setVisible(true);
                new Utils.miHelp("connectiondb.htm").setVisible(true);
            }
        });
    }
    
    private void mnuSelectorActionPerformed(java.awt.event.ActionEvent evt) {
        final DBConnectionIcon icon = this;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {           
                mySelectorTable.updateIcon(icon);
                mySelectorTable.setVisible(true);
            }
        });
    }
    
    private void mnuLoadActionPerformed(java.awt.event.ActionEvent evt) {
        Thread load = new Thread(new Runnable() {
            public void run() {
                mySelectorTable.dataset.reset();
                if(mySelectorTable.isMarketBasket){
                    dataset = mySelectorTable.loadDataSet();
                } else {
                    dataset = mySelectorTable.loadMultiValuedDataSet();
                }
                ChooserEscritorio.setStatus("Load " + dataset.getNtransactions()
                + " Instances");
                setInfo("Load " + dataset.getNtransactions() + " Instances");
                stopAnimation();
                Iterator it = tos.iterator();
                while(it.hasNext()){
                    Icon icon = (Icon)it.next();
                    if(icon instanceof AssociationIcon){
                        ((AssociationIcon)icon).dataset = dataset;
                    }
                }
            }
        });
        this.startAnimation();
        load.start();
    }
    
    public JMenuItem getMnuSelector() {
        return mnuSelector;
    }
    
    public JMenuItem getMnuLoad() {
        return mnuLoad;
    }
    
    public void updtateIconDB(){
        mySelectorTable.updateIcon(this);
    }
}
