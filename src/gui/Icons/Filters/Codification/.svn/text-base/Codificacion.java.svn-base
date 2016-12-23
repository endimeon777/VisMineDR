/*
 * FiltroStandard.java
 *
 * Created on 18 de mayo de 2006, 03:18 PM
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

package gui.Icons.Filters.Codification;

import java.util.Collections;
import javax.swing.table.AbstractTableModel;

/**
 * This class is used for create a new Table whit codified data.<br>  
 * This in particular contains information about AbstractTableModel 
 * whit output data.
 *
 * @author Tariy
 */
public class Codificacion extends AbstractTableModel{
    /** The input data that arrive from a connection. */
    AbstractTableModel datosEntrada;
    
    /** The Data dictionary. */
    public ValAtributosCod valatricod;
    
    /** Data after to have applied the filter. */
    final Object[][] datos;
    
    /** Names of the attributes. */
    final String[] nomcol;
    
   /** 
   * This initializes and resizes the variables
   * and constructs the new table.
   *
   * @param dataIn input data that arrive from a connection.
   */
    public Codificacion(AbstractTableModel dataIn){
        datosEntrada = dataIn;
        valatricod = new ValAtributosCod(dataIn);
        datos = new Object[datosEntrada.getRowCount()][datosEntrada.getColumnCount()];
        nomcol = new String[datosEntrada.getColumnCount()];
        nuevaTabla();
    }
    
   /**
   * This function constructs the new table  
   * whit filtered data
   */
    public void nuevaTabla() {
        for(int f = 0; f < datosEntrada.getRowCount(); f++) {
            for(int c = 0; c < datosEntrada.getColumnCount(); c++) {
                String element = datosEntrada.getColumnName(c) + "=" +
                        datosEntrada.getValueAt(f,c);
                int index = Collections.binarySearch(valatricod.datos, element);
                datos[f][c] = new Short((short)index);
            }
        }
        for(int c = 0; c < datosEntrada.getColumnCount(); c++) {
            nomcol[c] = datosEntrada.getColumnName(c);
        }
    }
    
    /**
     *  Returns the number of columns of the table. 
     */
    public int getColumnCount() {
        return nomcol.length;
    }
    
    /**
     *  Returns the number of rows of the table 
     */
    public int getRowCount() {
        return datosEntrada.getRowCount();
    }
       
    /**
     *  Returns a default name for the column 
     *
     * @param column  the column being queried
     * @return a string containing the default name of <code>column</code>
     */
    public String getColumnName(int col) {
        return nomcol[col];
    }
    
     /**
     *  Returns the value of a cell queried, in a row and column of the table.
     *
     *  @param  row  the row being queried
     *  @param  col the column being queried
     *  @return datos value of a cell queried
     */
    public Object getValueAt(int row, int col) {
        return datos[row][col];
    }
    
     /**
     *  Returns <code>Object.class</code> regardless of <code>columnIndex</code>.
     *
     *  @param c  the column being queried
     *  @return the Object.class
     */
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
    
     /**
     *  Returns if the cell is editable.  This is the default implementation for all cells.
     *
     *  @param  row  the row being queried
     *  @param  col the column being queried
     *  @return boolean value that depends if it is editable
     */
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col < 1) {
            return false;
        } else {
            return true;
        }
    }
    
     /**
     *  this method assigns a value to a cell.
     *
     *  @param  value   value to assign to cell
     *  @param  row   row of cell
     *  @param  col  column of cell
     */
    public void setValueAt(Object value, int row, int col) {
        datos[row][col] = value;
    }
    
}
