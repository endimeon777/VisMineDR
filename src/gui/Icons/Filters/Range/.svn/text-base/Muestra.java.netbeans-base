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

package gui.Icons.Filters.Range;

import gui.Icons.Filters.TariyTableModel;
import java.util.Random;
import javax.swing.table.AbstractTableModel;

/**
 * This class is used for create a new Table whit Discretized data.<br>  
 * This in particular contains information about AbstractTableModel 
 * whit output data.
 *
 * @author Tariy
 */
public class Muestra extends AbstractTableModel{
    /** The input data that arrive from a connection. */
    AbstractTableModel datosEntrada;
    
    /** The output data, after applying the filter. */
    AbstractTableModel dataOut;
    
    /** Data after to have applied the filter. */
    Object[][] data;
    
    /** Names of the attributes. */
    String[] columnsName;
    int filmenos = 0;
    
    /** Selected Option. */
    private int opcmues;
    
    /** Value of the range. */
    private int valmues;
    
    /** 
   * This initializes and resizes the variables
   * and constructs the new table.
   *
   * @param dataIn input data that arrive from a connection.
   * @param r Value of Range.
   * @param o Selected Option.
   */
    public Muestra(AbstractTableModel dataIn, int r, int o){
        datosEntrada = dataIn;
        
        int columns = dataIn.getColumnCount();
        columnsName = new String[columns];
        for(int i = 0; i < columns; i++){
            columnsName[i] = dataIn.getColumnName(i);
        }
        
        opcmues = o;
        valmues = r;
        //nuevaTabla();
    }
    
  /**
   * This function constructs the new table  
   * whit filtered data
   */
    public void nuevaTabla() {
        int filn = 0, fp = 0, cfila = 0;
        if(opcmues==0) {     
            Random r = new Random();
            data = new Object[valmues][columnsName.length];
            for(int f = 0; f < valmues; f++) {
                cfila = r.nextInt(datosEntrada.getRowCount());
                for(int c = 0; c < datosEntrada.getColumnCount(); c++) {
                    data[f][c] = datosEntrada.getValueAt(cfila,c);
                }
            }
            filmenos = valmues;
        }
        
        else if(opcmues==1){ //  1 en n
            filn = 0;
            int nrows = (int)(datosEntrada.getRowCount() / valmues) + 1;
            data = new Object[nrows][columnsName.length];
            int rows = datosEntrada.getRowCount();
            for(int f = 0; f < rows; f++) {
                if(f==filn) {
                    for(int c = 0; c < datosEntrada.getColumnCount(); c++) {
                        data[fp][c] = datosEntrada.getValueAt(filn,c);
                    }
                    filn = filn + valmues;
                    fp++;
                }
            }
            filmenos = fp;
        }
        
        else if(opcmues==2){ // Primeros n
            data = new Object[valmues][columnsName.length];
            for(int f = 0; f < valmues; f++) {
                for(int c = 0; c < datosEntrada.getColumnCount(); c++) {
                    data[f][c] = datosEntrada.getValueAt(f,c);
                }
            }
            filmenos = valmues;
        }
        dataOut = new TariyTableModel(data, columnsName);
    }
    
    /**
     *  Returns the number of columns of the table. 
     */
    public int getColumnCount() {
        return dataOut.getColumnCount();
    }
    
    /**
     *  Returns the number of rows of the table 
     */
    public int getRowCount() {
        return filmenos;
    }
    
    /**
     *  Returns a default name for the column 
     *
     * @param column  the column being queried
     * @return a string containing the default name of <code>column</code>
     */
    public String getColumnName(int col) {
        return dataOut.getColumnName(col);
    }
    
    /**
     *  Returns the value of a cell queried, in a row and column of the table.
     *
     *  @param  row  the row being queried
     *  @param  col the column being queried
     *  @return datos value of a cell queried
     */
    public Object getValueAt(int row, int col) {
        return dataOut.getValueAt(row, col);
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
        if (col < 2) {
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
        dataOut.setValueAt(value, row, col);
    }
}
