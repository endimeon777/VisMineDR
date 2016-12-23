/*
 * FileTableModel.java
 *
 * Created on 11 de junio de 2006, 17:23
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gui.Icons.File;

import Utils.FileManager;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ivan
 */
public class FileTableModel extends AbstractTableModel {
    /** La ruta del archivo de acceso aleatorio de tipo .arff. */
    private String filePath;
    
    /** Los nombres de las columnas. */
    Object[] columnNames;
    
    /** Los datos provenientes de un archivo de acceso aleatorio .arff. */
    Object [][] data;
    
    /** Creates a new instance of FileTableModel */
    public FileTableModel(String file) {
        filePath = file;
        
        FileManager fileMngt = new FileManager(filePath);
        fileMngt.readCsv();
        //fileMngt.dataAndAttributes(true);
        
        int size = fileMngt.getAttributes().length;
        columnNames = new Object[size-1];
        columnNames = fileMngt.getAttributes();
        
        int rows = fileMngt.getData().length;
        int cols = fileMngt.getData()[0].length;
        data = new Object[rows][cols-1];
        data = fileMngt.getData();
    }
    
    /** Creates a new instance of FileTableModel */
    public FileTableModel(String file, String ext) {
        filePath = file;
        
        FileManager fileMngt = new FileManager(filePath);
        if(ext.compareTo("arff")==0){
            fileMngt.dataAndAttributes(true);
        }else{
            fileMngt.readCsv();
        }
        
        int size = fileMngt.getAttributes().length;
        columnNames = new Object[size-1];
        columnNames = fileMngt.getAttributes();
        
        int rows = fileMngt.getData().length;
        int cols = fileMngt.getData()[0].length;
        data = new Object[rows][cols-1];
        data = fileMngt.getData();
    }
    
    public String getColumnName(int column) {
        return (String) columnNames[column];
//        if (column==0) {
//            return "#";
//        } else {
//            if (columnNames[column-1] != null) {
//                return (String) columnNames[column-1];
//            } else {
//                return "";
//            }
//        }
    }
    
    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    //||||||||||||||| AbstractTableModel implemented methods |||||||||||||||||
    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    
    public int getRowCount() {
        return data.length;
    }
    
    public int getColumnCount() {
        return columnNames.length;
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
//        if (columnIndex==0) {
//            return rowIndex+1;
//        } else {
//            return data[rowIndex][columnIndex-1];
//        }
    }
}
