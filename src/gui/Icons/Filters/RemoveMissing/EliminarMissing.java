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

package gui.Icons.Filters.RemoveMissing;

import javax.swing.table.AbstractTableModel;

/**
 * This class is used for create a new Table whitout missing data.<br>  
 * This in particular contains information about AbstractTableModel 
 * whit output data.
 *
 * 
 */
public class EliminarMissing extends AbstractTableModel{
    /** The input data that arrive from a connection. */
    AbstractTableModel datosEntrada;
    
    /** Data after to have applied the filter. */
    final Object[][] datos;
    
    /** Number of Columns. */
    final String[] nomcol;
    
    /** Class of Columns. */
    Class colsClas[];
   
    
    public int fv = 0;
    
   /** 
   * This initializes and resizes the variables
   * and constructs the new table.
   *
   * @param dataIn input data that arrive from a connection.
   */
    public EliminarMissing(AbstractTableModel dataIn){
        datosEntrada = dataIn;
        datos = new Object[datosEntrada.getRowCount()][datosEntrada.getColumnCount()];
        nomcol = new String[datosEntrada.getColumnCount()];
        colsClas = new Class[datosEntrada.getColumnCount()];
        for (int i = 0; i < datosEntrada.getColumnCount(); i++) {
           colsClas[i] = Object.class;
        }
        nuevaTabla();
    }
    
   /**
   * This function constructs the new table  
   * whit filtered data
   */
    public void nuevaTabla() {
        int bfv = 0;
        for(int f = 0; f < datosEntrada.getRowCount(); f++ ){
            bfv = 0;
            for(int c = 0; c < datosEntrada.getColumnCount(); c++ ){
                if(datosEntrada.getValueAt(f,c) != null && datosEntrada.getValueAt(f,c).toString().trim().compareTo("")!=0 && datosEntrada.getValueAt(f,c).toString().trim().compareToIgnoreCase("null")!=0) { 
                    
                    datos[fv][c] = datosEntrada.getValueAt(f,c);   // fv = fila verificada solo incrementa cuando todos los atributos estuvieron llenos
                    
                    if(esEntero(datosEntrada.getValueAt(f,c).toString())){
                       if(!colsClas[c].equals(String.class) && !colsClas[c].equals(Double.class))  
                          colsClas[c] = Integer.class;  // El string predominta sobre el double y el double sobre el integer 
    //                      valNumInt = Integer.parseInt(valRem.toString());
    //                      dataOut.setValueAt(valNumInt, f, colRem);
                    }else if(esDouble(datosEntrada.getValueAt(f,c).toString())){
                       if(!colsClas[c].equals(String.class)) 
                          colsClas[c] = Double.class;  // es decir, si algun elemento fue string, deja a toda la columna como string
    //                      valNumDo = Double.parseDouble(valRem.toString());
    //                      dataOut.setValueAt(valNumDo, f, colRem);
                    }else {
                       colsClas[c] = String.class;
                    }
                
                } else {
                    if(f == datosEntrada.getRowCount()-1) {
                        for(int i=0; i < c; i++) {
                            datos[fv][i] = null;
                        }
                    }
                    c = datosEntrada.getColumnCount();  bfv=1;  // bfv = bandera de fila verificada si es 1 es porque tuvo algun atributo vacio
                }
            }
            if(bfv!=1) fv++;
        }
        // Para insertar el nombre de las columnas
        for(int i = 0; i < datosEntrada.getColumnCount(); i++ ){
            nomcol[i] = datosEntrada.getColumnName(i);
        }
    }
    
    private boolean esEntero(String cad){
        boolean bd = false;
        int valNum = 0;
                
        try{  // si lo puede transformar a numero lo transforma a numero
          valNum = Integer.parseInt(cad);
          bd = true;
        } catch (Exception e) {
          bd = false;
        }
        return bd;
    }
    
    private boolean esDouble(String cad){
        boolean bd = false;
        double valNum = 1.0;
                
        try{  // si lo puede transformar a numero lo transforma a numero
          valNum = Double.parseDouble(cad);
          bd = true;
        } catch (Exception e) {
          bd = false;
        }
        return bd;
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
        return fv;
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
//        return getValueAt(0, c).getClass();
        return colsClas[c];
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
        datos[row][col] = value;
    }
}
