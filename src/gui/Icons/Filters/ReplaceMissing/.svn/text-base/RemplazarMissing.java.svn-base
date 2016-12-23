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

package gui.Icons.Filters.ReplaceMissing;

import gui.Icons.DBConnection.ScrollableTableModel;
import gui.Icons.Filters.TariyTableModel;
import javax.swing.table.AbstractTableModel;

/**
 * This class is used for create a new Table after replacing a value.<br>  
 * This in particular contains information about AbstractTableModel 
 * whit output data.
 *
 * @author Tariy
 */
public class RemplazarMissing extends AbstractTableModel{
    
    public AbstractTableModel dataIn;
    public AbstractTableModel dataOut;
    int colRem, conm = 0;
    Object valRem;
    
    public RemplazarMissing(){
        //nuevaTabla();
    }
    
   /** 
   * Constructs the new table.
   *
   * @param di input data that arrive from a connection.
   */
    public void setDatosEntrada(AbstractTableModel di) {
        this.dataIn = di;
        int rows = dataIn.getRowCount();
        int columns = dataIn.getColumnCount();

        Object[][] data = new Object[rows][columns];
        String[] columnsName = new String[columns];
        for(int i = 0; i < columns; i++){
            for(int j = 0; j < rows; j++){
                data[j][i] = dataIn.getValueAt(j ,i);
            }
            columnsName[i] = dataIn.getColumnName(i);
        }
        dataOut = new TariyTableModel(data, columnsName);
    }
    
    public void setValRem(Object valRem) {
        this.valRem = valRem;
    }
    
    public void setColRem(int colRem) {
        this.colRem = colRem;
    }
    
    
   /**
   * This function clean the table   
   */  	
    public void nuevaTabla() {
        for(int f = 0; f < dataOut.getRowCount(); f++ ){
            if(dataOut.getValueAt(f,colRem) == null) {
                dataOut.setValueAt(valRem,f,colRem);
                conm++;
            }
        }
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
        return dataOut.getRowCount();
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
    
    /**
     * This return the number of missing data.
     *
     * @return the number of missing data.
     */
    public int getNMissing() {
        return conm;
    }
    
}
