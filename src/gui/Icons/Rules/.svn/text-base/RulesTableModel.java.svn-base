/*
 * RulesTableModel.java
 *
 * Created on 1 de junio de 2006, 20:23
 *
 * Proyecto Tariy
 */

package gui.Icons.Rules;

import Utils.Rules;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Proyecto Tariy
 */
public class RulesTableModel extends AbstractTableModel {
    /** Las reglas de asociación. */
    private ArrayList rules;
    /** Decimal format to confidence */
    private DecimalFormat df;
    /** Los nombres de las columnas. */
    String[] columnNames = new String[3];
    
    /** */
    protected TableModel model;
    
    /**
     * Crea una nueva instancia de la clase RulesTableModel.
     *
     * @param rules La estructura que sera utilizada para crear el modelo de
     *        la tabla de datos.
     */
    public RulesTableModel(ArrayList rules) {
        this.rules = rules;
        this.columnNames[0] = "#";
        this.columnNames[1] = "Rule";
        this.columnNames[2] = "Confidence";
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
            return rowIndex+1;
        } else if(columnIndex == 1) {
            String rule = new String();
            rule = "If " + ((Rules) obj).getAntecedent().replaceAll("~", "and") + " Then " +
                    ((Rules) obj).getConcecuent().replaceAll("~", "and");
            return rule;
        } else {
            float confidence = 0;
            confidence = ((Rules) obj).getConfidence();
            return df.format(confidence) + "%";
        }
    }
}
