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
import gui.KnowledgeFlow.Chooser;
import gui.KnowledgeFlow.Icon;
import gui.Icons.DBConnection.SelectorTable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author and
 */
public class DBConnectionIcon extends Icon{
    private JMenuItem mnuConfigure;
    private JMenuItem mnuHelp;
    private JMenuItem mnuSelector;
    private JMenuItem mnuLoad;
    public ConnectionWizard myConnectionWizard;
    public Connection connection = null;
    public SelectorTable mySelectorTable = null;
    public AbstractTableModel connectionTableModel;
    public DataSet dataset = null;
    
    /** Creates a new instance of DBConnectionIcon */
    public DBConnectionIcon(JLabel s, int x, int y) {
        super(s, x, y);
        super.constrainsTo = new ArrayList(2);
        super.constrainsTo.add("AssociationIcon");
        super.constrainsTo.add("FilterIcon");
        super.constrainsTo.add("PredictionIcon");
        super.constrainsTo.add("ClasificationIcon");
        
        myConnectionWizard = null;
        
        mnuConfigure = new javax.swing.JMenuItem();
        mnuConfigure.setText("Configure...");
        mnuConfigure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuConfigureActionPerformed(evt);
            }
        });
        super.pupMenu.add(mnuConfigure);
        
        mnuSelector = new javax.swing.JMenuItem();
        mnuSelector.setText("Attribute Selection...");
        mnuSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSelectorActionPerformed(evt);
            }
        });
        mnuSelector.setEnabled(false);
        super.pupMenu.add(mnuSelector);
        
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
                if(myConnectionWizard == null){
                    myConnectionWizard =  new ConnectionWizard(icon);
                    myConnectionWizard.setVisible(true);
                    mySelectorTable = null;
                } else {
                    myConnectionWizard.setVisible(true);
                    mySelectorTable = null;
                }
            }
        });
    }
    
    private void mnuHelpActionPerformed(java.awt.event.ActionEvent evt) {
        final Icon icon = this;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Utils.Help(icon.getName().trim()).setVisible(true);
            }
        });
    }
    
    private void mnuSelectorActionPerformed(java.awt.event.ActionEvent evt) {
        final DBConnectionIcon icon = this;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if(mySelectorTable == null){
                    mySelectorTable =  new SelectorTable(icon);
                    mySelectorTable.setVisible(true);
                } else {
                    mySelectorTable.setVisible(true);
                }
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
                Chooser.setStatus("Load " + dataset.getNtransactions()
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
}
