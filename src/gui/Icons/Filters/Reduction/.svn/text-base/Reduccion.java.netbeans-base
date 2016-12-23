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

package gui.Icons.Filters.Reduction;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.table.AbstractTableModel;

/**
 * This class is used for create a new Table after applying the filter Reduction.<br>  
 * This in particular contains information about AbstractTableModel 
 * whit output data.
 *
 * @author Tariy
 */
public class Reduccion extends AbstractTableModel{
    /** The input data that arrive from a connection. */
    AbstractTableModel datosEntrada;    
    
    /** Data after to have applied the filter. */
    ArrayList data;
    
    /** Names of the attributes. */
    String[] columnsName;
    
    /** Number of Columns. */
    int columns; 
            
    /** Number of Rows. */
    int rows;
    
    /** Selected Option. */
    int selRbtn; 
     
    /** Option keep o remove. */
    int rManRem;
    
    /** Initial row. */
    int filIni;
            
    /** Final row. */       
    int filFin;
    
    /** Number of Selected Rows. */
    int numfil;    
     
    int seal;
    
    /** Minimum limits. */
    int menores;
    
    /** Column Selected*/
    int colsel;
    
    /** Number of selected attributes */
    int numatrisel;
    ArrayList valsAtr;
    
   /** 
   * This initializes and resizes the variables
   * and constructs the new table.
   *
   * @param dataIn input data that arrive from a connection.
   * @param s signal of option.
   * @param r Option keep or remove.
   * @param fi Initial row. 
   * @param ff Final row.
   * @param se signal.
   * @param men Minimum limit.
   * @param cs Selected Column .
   * @param va Selected Atributes.
   */
    public Reduccion(AbstractTableModel dataIn, int s, int r, int fi, int ff,
            int se, int men, int cs, ArrayList va){
        datosEntrada = dataIn;
        columns = dataIn.getColumnCount();
        rows = dataIn.getRowCount();
        columnsName = new String[columns];
        for(int i = 0; i < columns; i++){
            columnsName[i] = dataIn.getColumnName(i);
        }
        selRbtn = s;
        rManRem = r;
        filIni = fi - 1;
        filFin = ff - 1;
        seal = se;
        menores = men;
        colsel = cs;
        valsAtr = va;
        
        nuevaTabla();
    }
    
    /**
   * This function constructs the new table  
   * whit filtered data
   */
    public void nuevaTabla() {
        if(selRbtn == 0) { // for range
            if(rManRem == 0) { // keep
                numfil = (filFin - filIni) + 1;
                data = new ArrayList(numfil);
                for(int f = filIni; f <= filFin; f++) {
                    Object[] row = new Object[columns];
                    for(int c = 0; c < columns; c++) {
                        row[c] = datosEntrada.getValueAt(f,c);
                    }
                    data.add(row);
                }
            } else if(rManRem == 1) { // remove
                numfil = rows - ((filFin - filIni) + 1);
                data = new ArrayList(numfil);
                for(int f = 0; f < rows; f++) {
                    if(f < filIni || f > filFin){
                        Object[] row = new Object[columns];
                        for(int c = 0; c < columns; c++) {
                            row[c] = datosEntrada.getValueAt(f,c);
                        }
                        data.add(row);
                    }
                }
            }
        } else if(selRbtn == 1) { // for Value
            data = new ArrayList();
            if(seal == 0) {  // for Integer
                numfil = 0;
                for(int f = 0; f < rows; f++) {
                    if(rManRem == 0) { // for keep
                        if((Integer)datosEntrada.getValueAt(f,colsel) < menores) {
                            Object[] row = new Object[columns];
                            for(int c = 0; c < columns; c++) {
                                row[c] = datosEntrada.getValueAt(f,c);
                            }
                            data.add(row);
                            numfil ++;
                        }
                    } else if(rManRem == 1) { // for remove
                        if((Integer)datosEntrada.getValueAt(f,colsel) >= menores) {
                            Object[] row = new Object[columns];
                            for(int c = 0; c < columns; c++) {
                                row[c] = datosEntrada.getValueAt(f,c);
                            }
                            data.add(row);
                            numfil ++;
                        }
                    }
                }
            } else if(seal == 1) { //for String
                numfil = 0;
                //________________
                if(rManRem == 0) { 
                    for(int f = 0; f < rows; f++) {
                        if(valsAtr.contains(datosEntrada.getValueAt(f,colsel))){
                            Object[] row = new Object[columns];
                            for(int c = 0; c < columns; c++) {
                                row[c] = datosEntrada.getValueAt(f,c);
                            }
                            data.add(row);
                            numfil++;
                        }
                    }
                } else if(rManRem == 1) { // for remove
                    for(int f = 0; f < rows; f++) {
                        if(!valsAtr.contains(datosEntrada.getValueAt(f,colsel))){
                            Object[] row = new Object[columns];
                            for(int c = 0; c < columns; c++) {
                                row[c] = datosEntrada.getValueAt(f,c);
                            }
                            data.add(row);
                            numfil++;
                        }
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
        return numfil;
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
        Object[] r = (Object[])data.get(row);
        return r[col];
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
