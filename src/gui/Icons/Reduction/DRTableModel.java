/*
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gui.Icons.Reduction;

import gui.Icons.Filters.*;
import Utils.DataSet;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Juan Carlos Alvarado
 */
public class DRTableModel extends AbstractTableModel {
      
    public double [][] data;
    
    public String[] columnName;  
    
    public Class[] columnClas;  


    
    public DRTableModel(double[][] d, String[] cn, Class[] ccl){
        this.setData(d, cn, ccl);
    }

    DRTableModel(double[][] dataIn, String[] atributos) {
        this.data = dataIn;
        this.columnName = atributos;
    }
    
    public void setData(double[][] d, String[] cn, Class[] ccl){
        data = d;
        columnName = cn;
        columnClas = ccl;
    }
    
    //creo que toca quitarla
    public void setData(double[][] d, String[] cn){
        data = d;
        columnName = cn;
    }
    
    public int getColumnCount() {
        return columnName.length;
    }
    
    public int getRowCount() {
        return data.length;
    }
    
    public String getColumnName(int col) {
        return columnName[col];
    }
    
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }
    
    public Class getColumnClass(int c) {
//        return getValueAt(0, c).getClass();
        return double.class;
    }
    
    public void setColumnClass(int c,Class cl) {
//        return getValueAt(0, c).getClass();
        columnClas[c] = cl;
    }
    
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col < 2) {
            return false;
        } else {
            return true;
        }
    }
    
    public void setValueAt(double value, int row, int col) {
        data[row][col] = value;
    }
    
    public int getColumnIndex(String attr) {
        int i;
        for (i=0; i<columnName.length; i++) {
            if (columnName[i].compareTo(attr) == 0) {
                break;
            }
        }
        return i;
    }
    
//    public DataSet loadDataSet(){
//
//    }
}
