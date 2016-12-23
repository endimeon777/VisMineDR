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

package gui.Icons.Filters.Selection;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Tariy
 */
public class TipodVariablesSel extends AbstractTableModel{
    
    AbstractTableModel datosEntrada;
    final Object[][] datos;
    final String[] nomcol;
    int columns, rows;
    
    public TipodVariablesSel(AbstractTableModel dataIn){
        datosEntrada = dataIn;
        columns = dataIn.getColumnCount();
        rows = dataIn.getRowCount();
        datos = new Object[columns][4];
        nomcol  = new String[4];
        nuevaTabla();
    }
    
    public void nuevaTabla() {
        // Para isertar los datos
        for(int c = 0; c < columns; c++ ){
            datos[c][0] = datosEntrada.getColumnName(c);
            datos[c][1] = datosEntrada.getColumnClass(c).getSimpleName();
            datos[c][2] = Boolean.TRUE;
            datos[c][3] = "Attribute";  // tiene que ser un radio buton
        }
        // Para insertar el nombre de las columnas
        nomcol[0] = "ATRIBUTE";
        nomcol[1] = "TYPE";
        nomcol[2] = "SELECTION";
        nomcol[3] = "TARGET";
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
