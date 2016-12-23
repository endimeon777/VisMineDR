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

import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import javax.swing.table.AbstractTableModel;

/**
 * This class is used for create a Data dictionary.<br>  
 * This in particular contains information about AbstractTableModel 
 * whit Data dictionary.
 *
 * @author Tariy
 */
public class ValAtributosCod extends AbstractTableModel{
    /** The input data that arrive of the class Codification */
    AbstractTableModel datosEntrada;
    
    /** Data whit values of dictionary. */
    public final ArrayList datos = new ArrayList();   
    
    /** Names of the attributes. */
    final String[] nomcol = new String[3];
    
    int catri = 1, limc = 0,  auxc = 0;
    
   /** 
   * This initializes and resizes the variables
   * and constructs the new table.
   *
   * @param dataIn input data that arrive from a connection whit Codification.
   */
    public ValAtributosCod(AbstractTableModel dataIn){
        datosEntrada = dataIn;
        nuevaTabla();
    }
    
   /**
   * This function constructs the new table  
   * whit data dictionary.
   */
    public void nuevaTabla() {
        Object[] valores = new Object[datosEntrada.getRowCount()];
        int con = 0, pv;
        
        for(int c = 0; c < datosEntrada.getColumnCount(); c++) {
            catri = 0;  //1
            for(int f = 0; f < datosEntrada.getRowCount(); f++) {
                con = 0;
                for(int i = 0; i < catri; i++) {
                    if(datosEntrada.getValueAt(f,c).equals(valores[i])){
                        con ++;
                        break;
                    }
                }
                if(con == 0) {
                    valores[catri] = datosEntrada.getValueAt(f,c);
                    catri++;
                }
            }
            pv = 0;
            limc = limc + catri;
            for(int i = auxc; i < limc; i++) {
                //ArrayList row = new ArrayList(3);
                //row.add(new Short((short)i));
                datos.add(datosEntrada.getColumnName(c) + "=" + valores[pv]);
                pv ++;
                //datos.add(row);
            }
            auxc = auxc + catri;
        }
        nomcol[0] = "INDICE";
        nomcol[1] = "ATRIBUTO";
        nomcol[2] = "VALOR";
        datos.trimToSize();
        Collections.sort(datos);
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
        return auxc;
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
        StringTokenizer token = new StringTokenizer((String)datos.get(row), "=");
        if(col == 0){
            return new Short((short)row);
        } else if(col == 1){
            return token.nextToken();
        } else if(col == 2){
            token.nextToken();
            return token.nextToken();
        }
        return null;
    }
    
     /**
     *  Returns <code>Object.class</code> regardless of <code>columnIndex</code>.
     *
     *  @param c  the column being queried
     *  @return the Object.class
     */
    public Class getColumnClass(int c) {
        if(c == 0){
            return Short.class;
        } else {
            return String.class;
        }
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
}
