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

package gui.Icons.Filters.Discretize;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.table.AbstractTableModel;

/**
 * This class is used for create a new Table whit Discretized data.<br>  
 * This in particular contains information about AbstractTableModel 
 * whit output data.
 *
 * @author Tariy
 */
public class Discretizacion extends AbstractTableModel{
    /** The input data that arrive from a connection. */
    AbstractTableModel datosEntrada;
    
    /** Selected Colmn. */
    int colSel;
    
    /** Selected Option. */
    int rbtnsel;
    
    /** Number of Rows. */
    private int rows;
    
    /** Number of Columns. */
    private int columns;
    int val;
    
    /** Data after to have applied the filter. */
    Object[][] data;
    
    /** Names of the attributes. */
    String[] columnsName;
    
   /** 
   * This initializes and resizes the variables
   * and constructs the new table.
   *
   * @param dataIn input data that arrive from a connection.
   * @param cs Selected Column.
   * @param v Value of Discretize.
   * @param r Selected Option.
   */
    public Discretizacion(AbstractTableModel dataIn, int cs, int v, int r){
        datosEntrada = dataIn;
        rows = dataIn.getRowCount();
        columns = dataIn.getColumnCount();
        data = new Object[rows][columns];
        columnsName = new String[columns];
        colSel = cs;
        val = v;
        rbtnsel = r;
        nuevaTabla();
    }
    
   /**
   * This function constructs the new table  
   * whit filtered data
   */
    public void nuevaTabla() {
        int min, max;
        float aux, incre;
        float rangos[];
        String rangString = "";

        min = (Integer)datosEntrada.getValueAt(0,colSel);
        max = (Integer)datosEntrada.getValueAt(0,colSel);
        for(int c = 0; c < columns; c++){
            for(int f = 0; f < rows; f++ ){
                if(c == colSel){
                    if((Integer)datosEntrada.getValueAt(f,colSel) < min) {
                        min = (Integer)datosEntrada.getValueAt(f,colSel);
                    }
                    if((Integer)datosEntrada.getValueAt(f,colSel) > max) {
                        max = (Integer)datosEntrada.getValueAt(f,colSel);
                    }
                }
                data[f][c] = datosEntrada.getValueAt(f, c);
            }
            columnsName[c] = datosEntrada.getColumnName(c);
        }
        
        if(rbtnsel == 0) {
            rangos =  new float[val - 1];
            //val = val - 2;
            
            aux = max - min;
            aux = aux / val;
            incre = min + aux;
            
            int con = 0;
            while( con < rangos.length) {
                rangos[con] = incre;
                incre = incre + aux;
                con ++;
            }
            //rangos[con] = max - 1;
            
            for(int f = 0; f < rows; f++ ){
                for(int i = 0; i < val; i++ ){
                    if((Integer)datosEntrada.getValueAt(f,colSel) <= (rangos[0])) {
                        rangString = "(- Infinity : " + rangos[0] + " ]";
                        data[f][colSel] = rangString;
                        break;
                    }else if((Integer)datosEntrada.getValueAt(f,colSel) > (rangos[rangos.length - 1])) {
                        rangString = "( " + rangos[rangos.length - 1] + " : + Infinity )";
                        data[f][colSel] = rangString;
                        break;
                    }else if((Integer)datosEntrada.getValueAt(f,colSel) > rangos[i] && (Integer)datosEntrada.getValueAt(f,colSel) <= rangos[i + 1]) {
                        rangString = "( " + rangos[i] + " : " + rangos[i + 1] + " ]";
                        data[f][colSel] = rangString;
                        break;
                    }
                }
            }
        } else if(rbtnsel == 1) {
            int r = ((max - min) / val) + 1;
            rangos = new float[r + 2];
            int pr = 0;
            incre = min;
            while(incre < max){
                rangos[pr] = incre;
                incre = incre + val;
                pr ++;
            }
            rangos[pr] = max - 1;
            
            for(int f = 0; f < rows; f++ ){
                for(int i = 0; i < pr; i++ ){
                    if((Integer)datosEntrada.getValueAt(f,colSel) <= (rangos[0])) {
                        rangString = "(- Infinity : " + rangos[0] + " ]";
                        data[f][colSel] = rangString;
                        break;
                    }else if((Integer)datosEntrada.getValueAt(f,colSel) > (rangos[pr])) {
                        rangString = "( " + rangos[pr] + " : + Infinity )";
                        data[f][colSel] = rangString;
                        break;
                    }else if((Integer)datosEntrada.getValueAt(f,colSel) > rangos[i] && (Integer)datosEntrada.getValueAt(f,colSel) <= rangos[i + 1]) {
                        rangString = "( " + rangos[i] + " : " + rangos[i + 1] + " ]";
                        data[f][colSel] = rangString;
                        break;
                    }
                }
            }
            
        }
    }
    
     /**
     *  Returns the number of columns of the table. 
     */
    public int getColumnCount() {
        return columns;
    }
    
    /**
     *  Returns the number of rows of the table 
     */
    public int getRowCount() {
        return rows;
    }
    
     /**
     *  Returns a default name for the column 
     *
     * @param column  the column being queried
     * @return a string containing the default name of <code>column</code>
     */
    public String getColumnName(int col) {
        return columnsName[col];
    }
    
    /**
     *  Returns the value of a cell queried, in a row and column of the table.
     *
     *  @param  row  the row being queried
     *  @param  col the column being queried
     *  @return datos value of a cell queried
     */
    public Object getValueAt(int row, int col) {
        return data[row][col];
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
}
