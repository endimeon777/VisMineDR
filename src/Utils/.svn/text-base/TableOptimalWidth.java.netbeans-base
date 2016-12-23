/*
 * TableOptimalWidth.java
 *
 * Created on 20 de enero de 2007, 12:11
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Utils;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 *
 * @author and
 */
public class TableOptimalWidth {
    
    /** Creates a new instance of TableOptimalWidth */
    public TableOptimalWidth() {
    }
    
    public static void setOptimalColumnWidth(JTable jtable) {
        int columns = jtable.getColumnModel().getColumnCount();
        jtable.setAutoResizeMode(jtable.AUTO_RESIZE_OFF);
        for (int i = 0; i < columns; i++){
            setOptimalColumnWidthByColumn(jtable, i);
        }
    }

    public static void setOptimalColumnWidth(JTable jtable, int minColumns) {
        int columns = jtable.getColumnModel().getColumnCount();
        if(columns < minColumns){
            jtable.setAutoResizeMode(jtable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
            return;
        }
        jtable.setAutoResizeMode(jtable.AUTO_RESIZE_OFF);
        for (int i = 0; i < columns; i++){
            setOptimalColumnWidthByColumn(jtable, i);
        }
    }
    
    public static void setOptimalColumnWidthByColumn(JTable jtable, int col) {
        int            width;
        TableColumn    column;
        JTableHeader   header;
        
        if ( (col >= 0) && (col < jtable.getColumnModel().getColumnCount()) ) {
            width = calcColumnWidth(jtable, col);
            
            if (width >= 0) {
                header = jtable.getTableHeader();
                column = jtable.getColumnModel().getColumn(col);
                column.setPreferredWidth(width);
                jtable.sizeColumnsToFit(-1);
                header.repaint();
            }
        }
    }
    
    public static int calcColumnWidth(JTable table, int col) {
        int width = calcHeaderWidth(table, col);
        if (width == -1)
            return width;
        
        TableColumnModel columns = table.getColumnModel();
        TableModel data = table.getModel();
        int rowCount = data.getRowCount();
        TableColumn column = columns.getColumn(col);
        try {
            for (int row = rowCount - 1; row >= 0; --row) {
                Component c = table.prepareRenderer(
                        table.getCellRenderer(row, col),
                        row, col);
                width = Math.max(width, c.getPreferredSize().width + 10);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return width;
    }
    public static int calcHeaderWidth(JTable table, int col) {
        if (table == null)
            return -1;
        
        if (col < 0 || col > table.getColumnCount()) {
            System.out.println("invalid col " + col);
            return -1;
        }
        
        JTableHeader header = table.getTableHeader();
        TableCellRenderer defaultHeaderRenderer = null;
        if (header != null) defaultHeaderRenderer = header.getDefaultRenderer();
        TableColumnModel columns = table.getColumnModel();
        TableModel data = table.getModel();
        TableColumn column = columns.getColumn(col);
        int width = -1;
        TableCellRenderer h = column.getHeaderRenderer();
        if (h == null) h = defaultHeaderRenderer;
        if (h != null) {
            // Not explicitly impossible
            Component c = h.getTableCellRendererComponent(
                    table,
                    column.getHeaderValue(),
                    false, false, -1, col);
            width = c.getPreferredSize().width + 5;
        }
        
        return width;
    }
    
    
}
