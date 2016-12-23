/*
 * FiltroStandard.java
 *
 * Created on 18 de mayo de 2006, 03:18 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gui.Icons.Filters;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Tariy
 */
public class ValAtributos extends AbstractTableModel{
    
    AbstractTableModel datosEntrada;
    
    Object[][] datos;
    final String[] nomcol;
    
    int catri = 1;
    
    public ValAtributos(int colsel, AbstractTableModel dataIn){
        datosEntrada = dataIn;
        nomcol = new String[2];
        nuevaTabla(colsel);
    }
    
    public void nuevaTabla(int colsel) {
        ArrayList valores = new ArrayList(1);
        int con = 0;
        
        valores.add(datosEntrada.getValueAt(0,colsel));
        int rows = datosEntrada.getRowCount();
        for(int f = 0; f < rows; f++) {
            con = 0;
            if(!valores.contains(datosEntrada.getValueAt(f,colsel))){
                valores.add(datosEntrada.getValueAt(f,colsel));
            }
        }
        catri = valores.size();
        datos = new Object[catri][2];
        for(int i = 0; i < catri; i++) {
            datos[i][0] = valores.get(i);
            datos[i][1] = Boolean.FALSE;
        }
        nomcol[0] = "ATRIBUTO";
        nomcol[1] = "SELECCION";
    }
    
    public int getColumnCount() {
        return nomcol.length;
    }
    
    public int getRowCount() {
        return catri;
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
        if (col < 1) {
            return false;
        } else {
            return true;
        }
    }
    
    public void setValueAt(Object value, int row, int col) {
        datos[row][col] = value;
    }
    
}
