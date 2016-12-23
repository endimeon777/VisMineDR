/*
 * TreeTableModel.java
 *
 * Created on 15 de enero de 2007, 16:19
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gui.Icons.Tree;

import algorithm.classification.c45_1.Leaf;
import java.text.DecimalFormat;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author and
 */
public class TreeTableModel extends AbstractTableModel{
    private LinkedList rules;
    String[] columnNames = new String[4];
    DecimalFormat df;
    
    /**
     * Crea una nueva instancia de la clase RulesTableModel.
     *
     * @param rules La estructura que sera utilizada para crear el modelo de
     *        la tabla de datos.
     */
    public TreeTableModel(LinkedList rules) {
        this.rules = rules;
        this.columnNames[0] = "#";
        this.columnNames[1] = "Rules";
        this.columnNames[2] = "Class";
        this.columnNames[3] = "Confidence";
        fireTableChanged(null);
        df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
    }
    
    public String getColumnName(int column) {
        if (columnNames[column] != null) {
            return columnNames[column];
        } else {
            return "";
        }
    }
    
    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    //||||||||||||||| AbstractTableModel implemented methods |||||||||||||||||
    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    
    /**
     * Retorna el número de reglas a través del tamaño del ArrayList que las
     * contiene.
     */
    public int getRowCount() {
        return rules.size();
    }
    
    public int getColumnCount() {
        return columnNames.length;
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object obj = rules.get(rowIndex);
        if(columnIndex == 0) {
            return (rowIndex + 1);
        } else if(columnIndex == 1) {
            return ((Leaf)obj).getPath();
        } else if(columnIndex == 2) {
            return ((Leaf)obj).getLeaf();
        } else {
            int a1 = ((Leaf)obj).getLeaf().getFrecuence();
            int a2 = ((Leaf)obj).getLeaf().getFrecuenceFather();
            Double c1 = new Double((double)a1/(double)a2);
            
            return df.format(c1 * 100) + "%";
        }
    }
}
