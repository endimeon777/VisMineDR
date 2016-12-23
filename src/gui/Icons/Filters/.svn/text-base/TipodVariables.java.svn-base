/*
 * FiltroStandard.java
 *
 * Created on 18 de mayo de 2006, 03:18 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gui.Icons.Filters;

import gui.Icons.DBConnection.ScrollableTableModel;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Tariy
 */
public class TipodVariables extends AbstractTableModel{
    
    AbstractTableModel datosEntrada;
    
    final Object[][] datos;
    final String[] nomcol = new String[2];
    
    public TipodVariables(AbstractTableModel dataIn){
        datosEntrada = dataIn;
        datos = new Object[datosEntrada.getColumnCount()][2];
        nuevaTabla();
    }
    
    public void nuevaTabla() {
        // Para isertar los datos
        for(int c = 0; c < datosEntrada.getColumnCount(); c++ ){
            datos[c][0] = datosEntrada.getColumnName(c);
            datos[c][1] = datosEntrada.getColumnClass(c).getSimpleName();
        }
        // Para insertar el nombre de las columnas
        nomcol[0] = "ATRIBUTO";
        nomcol[1] = "TIPO";
        
    }
    
    public int getColumnCount() {
        return nomcol.length;
    }
    
    public int getRowCount() {
        return datos.length;
    }
    
    public String getColumnName(int col) {
        return nomcol[col];
    }
    
    public Object getValueAt(int row, int col) {
        return datos[row][col];
    }
    
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
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
    
    public void setValueAt(Object value, int row, int col) {
        datos[row][col] = value;
    }
    
}
