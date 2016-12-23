/*
 * AssociationIcon.java
 *
 */

package gui.Icons.Filters;

import Utils.DataSet;
import gui.Icons.DBConnection.DBConnectionIcon;
import gui.Icons.Filters.Codification.Codificacion;
import gui.Icons.Filters.Codification.HelpCodification;
import gui.Icons.Filters.Codification.VerCodificacion;
import gui.Icons.Filters.Codification.miVerCodificacion;
import gui.Icons.Filters.Discretize.AbrirDiscretizacion;
import gui.Icons.Filters.Discretize.Discretizacion;
import gui.Icons.Filters.Discretize.HelpDiscretize;
import gui.Icons.Filters.Discretize.VerDiscretizacion;
import gui.Icons.Filters.Discretize.miAbrirDiscretizacion;
import gui.Icons.Filters.Discretize.miVerDiscretizacion;
import gui.Icons.Filters.NumericRange.HelpNumericRange;
import gui.Icons.Filters.Range.HelpRange;
import gui.Icons.Filters.Reduction.HelpReduction;
import gui.Icons.Filters.RemoveMissing.EliminarMissing;
import gui.Icons.Filters.RemoveMissing.HelpRemoveM;
import gui.Icons.Filters.RemoveMissing.VerResElimMiss;
import gui.Icons.Filters.Range.AbrirMuestra;
import gui.Icons.Filters.Range.Muestra;
import gui.Icons.Filters.Range.VerMuestra;
import gui.Icons.Filters.NumericRange.AbrirRangNumer;
import gui.Icons.Filters.NumericRange.RangoNumerico;
import gui.Icons.Filters.NumericRange.VerRangoNumerico;
import gui.Icons.Filters.NumericRange.miAbrirRangNumer;
import gui.Icons.Filters.NumericRange.miVerRangoNumerico;
import gui.Icons.Filters.Range.miAbrirMuestra;
import gui.Icons.Filters.Range.miVerMuestra;
import gui.Icons.Filters.Reduction.AbrirReduccion;
import gui.Icons.Filters.Reduction.miAbrirReduccion;
import gui.Icons.Filters.Reduction.Reduccion;
import gui.Icons.Filters.Reduction.VerReduccion;
import gui.Icons.Filters.Reduction.miVerReduccion;
import gui.Icons.Filters.RemoveMissing.miVerResElimMiss;
import gui.Icons.Filters.ReplaceMissing.AbrirRemMissing;
import gui.Icons.Filters.ReplaceMissing.HelpUpdateM;
import gui.Icons.Filters.ReplaceMissing.RemplazarMissing;
import gui.Icons.Filters.ReplaceMissing.VerResRemMiss;
import gui.Icons.Filters.ReplaceMissing.miAbrirRemMissing;
import gui.Icons.Filters.ReplaceMissing.miVerResRemMiss;
import gui.Icons.Filters.ReplaceValue.AbrirRemVal;
import gui.Icons.Filters.ReplaceValue.HelpRemplaceValue;
import gui.Icons.Filters.ReplaceValue.RemplazarValor;
import gui.Icons.Filters.ReplaceValue.VerRemVal;
import gui.Icons.Filters.ReplaceValue.miAbrirRemVal;
import gui.Icons.Filters.ReplaceValue.miVerRemVal;
import gui.Icons.Filters.Selection.AbrirSeleccion;
import gui.Icons.Filters.Selection.HelpSelection;
import gui.Icons.Filters.Selection.Seleccion;
import gui.Icons.Filters.Selection.VerSeleccion;
import gui.Icons.Filters.Selection.miAbrirSeleccion;
import gui.Icons.Filters.Selection.miVerSeleccion;
import gui.KnowledgeFlow.ChooserEscritorio;
import gui.KnowledgeFlow.Icon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;

/**
 *
 */
public class FilterIcon extends Icon{
    private JMenuItem mnuConfigure;
    private JMenuItem mnuRun;
    private JMenuItem mnuView;
    private JMenuItem mnuHelp;
    
    public AbstractTableModel dataIn = null;
    public AbstractTableModel dataOut = null;
    public TipodVariables typeData;
    public String filterName, filterText;
    public static JFrame Open;
    public static JFrame View;
    
    //
    public int indexColumn = 0; //indice de la columna para update missing
    public Object replaceWith = ""; //objeto con el que sera reemplazado en update missing
    public int rangeValue = 0; //Valora de la muestra en Muestras y numero de rangos en Discretizacion.
    public int optionRange = 0;//Opcion del rango selecciona en Muestras y opcion seleccionada en Discretizacion.
    public int columnSelected = 0; //columna escogida en reemplazarValor, rango numerico, Discretizacion y Reduccion.
    public ArrayList valuesAttribute; //valores del atributo seleccionado en reemplazarValor y Reducciones.
    public String replaceString = ""; //valor a reemplazar en reemplazar valor.
    int attributeCount = 0;//numero de atributos a reemplazar en reemplazar valor.
    public int minValue = 0;//minimo valor para Rango Numerico.
    public int maxValue = 0;//maximo valor para Rango Numerico.
    public int selRbtn = 0; //seleccion por valor o por rango en Reducciones.
    public int rManRem = 0; //seleccion para mantener o remover en Reducciones.
    public int filIni = 0, filFin = 0; // fila inicial y fila final en Reducciones.
    public int seal = 0; // opcion para identificar tipo de columna(Integer o String) en Reducciones.
    public double minor = 0; // valor a comparar en Reducciones.
    public int numberColumns = 0; // numero de columnas de Seleccion.
    public int columnsSelected[]; //arreglo de las columnas seleccionadas en Seleccion.
    
    private DataSet dataset = null;
    
    /** Creates a new instance of DBConnectionIcon */
    public FilterIcon(JLabel s, int x, int y, int indiceIcon) {
        super(s, x, y, indiceIcon);
        super.constrainsTo = new ArrayList(3);
        super.constrainsTo.add("FilterIcon");
        super.constrainsTo.add("StandarizeIcon");
        super.constrainsTo.add("AssociationIcon");
        super.constrainsTo.add("ClasificationIcon");
        super.constrainsTo.add("vmdIcon");
        super.constrainsTo.add("DRIcon");
        super.constrainsTo.add("ScatterIcon");
        
        filterName = s.getName();
        filterText = s.getText();
            
        if(!filterName.equals("removem") && !filterName.equals("codification")){
            mnuConfigure = new javax.swing.JMenuItem();
            mnuConfigure.setText("Configure...");
            mnuConfigure.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    mnuConfigureActionPerformed(evt);
                }
            });
            super.pupMenu.add(mnuConfigure);
            
           createOpens();
        }
        
        
        mnuRun = new javax.swing.JMenuItem();
        mnuRun.setText("Run...");
        mnuRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRunActionPerformed(evt);
            }
        });
        super.pupMenu.add(mnuRun);
        if(filterName.equals("removem") || filterName.equals("codification")){
            mnuRun.setEnabled(true);    
        } else {
            mnuRun.setEnabled(false);
        }
        
        
        mnuView = new javax.swing.JMenuItem();
        mnuView.setText("View...");
        mnuView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuViewActionPerformed(evt);
            }
        });
        super.pupMenu.add(mnuView);
        createViews();
        mnuView.setEnabled(false);
       
        
        mnuHelp = new javax.swing.JMenuItem();
        mnuHelp.setText("Help...");
        mnuHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuHelpActionPerformed(evt);
            }
        });
        super.pupMenu.add(mnuHelp);
    }  
    
    
    private void createOpens(){
        if(filterName.equals("updatem")){
            Open = new miAbrirRemMissing();
            Open.setVisible(false);
        } else if(filterName.equals("muestra")){
            Open = new miAbrirMuestra();  // hay que quitar los datain
            Open.setVisible(false);
        } else if(filterName.equals("remvalor")){
            Open = new miAbrirRemVal();
            Open.setVisible(false);
        } else if(filterName.equals("rangenum")){
            Open = new miAbrirRangNumer();
            Open.setVisible(false);
        } else if(filterName.equals("discretize")){
            Open = new miAbrirDiscretizacion();
            Open.setVisible(false);
        } else if(filterName.equals("reduction")){
            Open = new miAbrirReduccion();
            Open.setVisible(false);
        } else if(filterName.equals("selection")){
            Open = new miAbrirSeleccion();
            Open.setVisible(false);
        }
    }
    
     private void createViews(){
       if(filterName.equals("updatem")){   
            View = new miVerResRemMiss();
            View.setVisible(false);
        } else if(filterName.equals("codification")){
            View = new miVerCodificacion();
            View.setVisible(false);
        } else if(filterName.equals("removem")){
            View = new miVerResElimMiss();
            View.setVisible(false);
        } else if(filterName.equals("muestra")){
            View = new miVerMuestra();
            View.setVisible(false);
        } else if(filterName.equals("remvalor")){  // hay que quitar los parametros
            View = new miVerRemVal();
            View.setVisible(false);
        } else if(filterName.equals("rangenum")){
            View = new miVerRangoNumerico();
            View.setVisible(false);
        } else if(filterName.equals("discretize")){
            View = new miVerDiscretizacion();
            View.setVisible(false);
        } else if(filterName.equals("reduction")){
            View = new miVerReduccion();
            View.setVisible(false);
        } else if(filterName.equals("selection")){
            View = new miVerSeleccion();
            View.setVisible(false);
        }
    }
     
    public void setValuesByDefault(){
//        Open = null;
//        View = null;
        if(!filterName.equals("removem") && !filterName.equals("codification")){
            mnuRun.setEnabled(false);
        }
        mnuView.setEnabled(false);
    }
    
    public void setDataIn(AbstractTableModel dataIn) {
        this.dataIn = dataIn;
    }
    
    private void mnuHelpActionPerformed(java.awt.event.ActionEvent evt) {
        final Icon icon = this;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new Utils.Help(icon.getName().trim()).setVisible(true);

                
                if(filterName.equals("removem")){
                    
                   new Utils.miHelp("Remove Missing.htm").setVisible(true); 
                    
                }else if(filterName.equals("codification")){
                    
                   new Utils.miHelp("Codification.htm").setVisible(true); 
                    
                }else if(filterName.equals("updatem")){
                    
                   new Utils.miHelp("udatemissing.htm").setVisible(true); 
                    
                } else if(filterName.equals("muestra")){
                    
                   new Utils.miHelp("Muestra.htm").setVisible(true); 
                    
                } else if(filterName.equals("remvalor")){
                    
                   new Utils.miHelp("replacevalue.htm").setVisible(true); 
                    
                } else if(filterName.equals("rangenum")){
                    
                   new Utils.miHelp("numericrange.htm").setVisible(true);
                    
                } else if(filterName.equals("discretize")){
                    
                   new Utils.miHelp("Discretize.htm").setVisible(true); 
                    
                } else if(filterName.equals("reduction")){
                    
                   new Utils.miHelp("Reducción.htm").setVisible(true); 
                    
                } else if(filterName.equals("selection")){
                    
                   new Utils.miHelp("Selection.htm").setVisible(true);
                   
                }
                
            }
        });
    }
    

    private void mnuConfigureActionPerformed(java.awt.event.ActionEvent evt) {
//        final AbstractTableModel di = this.dataIn;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                if(filterName.equals("updatem")){
                    
                    if(!Open.getClass().getSimpleName().equals("miAbrirRemMissing")){
                            createOpens();
                    }
                    ((miAbrirRemMissing)Open).setDatas(dataIn, indexColumn, replaceWith);
                    Open.setVisible(true);
                    
                } else if(filterName.equals("muestra")){
                    
                    if(!Open.getClass().getSimpleName().equals("miAbrirMuestra")){
                            createOpens();
                    }
                    ((miAbrirMuestra)Open).setDatas(dataIn, rangeValue, optionRange);         
                    Open.setVisible(true);
                    
                } else if(filterName.equals("remvalor")){
                    
                    if(!Open.getClass().getSimpleName().equals("miAbrirRemVal")){
                            createOpens();
                    }
                    ((miAbrirRemVal)Open).setDatas(dataIn, columnSelected, valuesAttribute, replaceString);
                    Open.setVisible(true); 
                    
                } else if(filterName.equals("rangenum")){
                    
                    if(!Open.getClass().getSimpleName().equals("miAbrirRangNumer")){
                            createOpens();
                    }
                    ((miAbrirRangNumer)Open).setDatas(dataIn, columnSelected, minValue, maxValue);
                    Open.setVisible(true);
                    
                } else if(filterName.equals("discretize")){
                    
                    if(!Open.getClass().getSimpleName().equals("miAbrirDiscretizacion")){
                            createOpens();
                    }
                    ((miAbrirDiscretizacion)Open).setDatas(dataIn, columnSelected, rangeValue, optionRange);
                    Open.setVisible(true);
                    
                } else if(filterName.equals("reduction")){
                    
                    if(!Open.getClass().getSimpleName().equals("miAbrirReduccion")){
                            createOpens();
                    }
                    ((miAbrirReduccion)Open).setDatas(dataIn, selRbtn, rManRem, filIni,filFin, minor, columnSelected, valuesAttribute);
                    Open.setVisible(true);
                    
                } else if(filterName.equals("selection")){
                    
                    if(!Open.getClass().getSimpleName().equals("miAbrirSeleccion")){
                            createOpens();
                    }
                    ((miAbrirSeleccion)Open).setDatas(dataIn, numberColumns,columnsSelected, columnSelected);
                    Open.setVisible(true);
                }
                
                mnuRun.setEnabled(true);
            }
        });
    }
    
    private void mnuRunActionPerformed(java.awt.event.ActionEvent evt) {
        if(filterName.equals("updatem")){
            indexColumn = ((miAbrirRemMissing)Open).getColSelec();
            replaceWith = ((miAbrirRemMissing)Open).getValRem();
            RemplazarMissing replace = new RemplazarMissing();
            if(dataOut == null){
                replace.setDatosEntrada(dataIn);
            } else {
                replace.setDatosEntrada(dataOut);
            }
            replace.setColRem(indexColumn);
            replace.setValRem(replaceWith);
            replace.nuevaTabla();
            dataOut = replace;
            this.setInfo("Column for Remplace: " + (indexColumn+1) +"\nValue for Remplace: " + replaceWith);
        } else if(filterName.equals("codification")){
            Codificacion codification = new Codificacion(dataIn);
            dataOut = codification;
            this.setInfo(dataIn.getRowCount() + " Records Loaded");
        } else if(filterName.equals("removem")){
            EliminarMissing remove = new EliminarMissing(dataIn);
            this.setInfo(dataIn.getRowCount() - remove.fv + " Records Deleted" + "\n" + remove.fv + " Records Currents");
            dataOut = remove;
        } else if(filterName.equals("muestra")){
            rangeValue = ((miAbrirMuestra)Open).getValMues();
            optionRange = ((miAbrirMuestra)Open).getSelRbtn();
            Muestra range = new Muestra(dataIn, rangeValue, optionRange);
            range.nuevaTabla();
            dataOut = range;
            this.setInfo("Range Value: " + rangeValue + "\nOption Range: " + (optionRange+1));
        } else if(filterName.equals("remvalor")){
            columnSelected = ((miAbrirRemVal)Open).getColSel();
            valuesAttribute = ((miAbrirRemVal)Open).getAtriSel();
            replaceString = ((miAbrirRemVal)Open).getRemCon();
            attributeCount = ((miAbrirRemVal)Open).getNumAtri();
            RemplazarValor replace = new RemplazarValor(dataIn, columnSelected,valuesAttribute, replaceString);
            dataOut = replace;
            this.setInfo("Column Selected: " + (columnSelected+1) + "\nValues Attribute: " + valuesAttribute + "\nValue for Remplace: " + replaceString + "\nNumbers of Atributes: " + attributeCount);
        } else if(filterName.equals("rangenum")){
            columnSelected = ((miAbrirRangNumer)Open).getColSelec();
            minValue = ((miAbrirRangNumer)Open).getMinVal();
            maxValue = ((miAbrirRangNumer)Open).getMaxVal();
            RangoNumerico range = new RangoNumerico(dataIn, columnSelected,minValue, maxValue);
            dataOut = range;
            this.setInfo("Column Selected: " + (columnSelected+1) + "\nMin Value: " + minValue + "\nMax Value: " + maxValue);
        } else if(filterName.equals("discretize")){
            columnSelected = ((miAbrirDiscretizacion)Open).getColSelec();
            rangeValue = ((miAbrirDiscretizacion)Open).getValor();
            optionRange = ((miAbrirDiscretizacion)Open).getSelRbtn();
            
            Discretizacion discretize = new Discretizacion(dataIn, columnSelected,rangeValue, optionRange);
            dataOut = discretize;
            this.setInfo("Column Selected: " + (columnSelected+1) + "\nRange Value: " + rangeValue + "\nOption Range: " + optionRange);
        } else if(filterName.equals("reduction")){
            selRbtn = ((miAbrirReduccion)Open).getSelRbtn();
            rManRem = ((miAbrirReduccion)Open).getRbtnManRem();
            filIni = ((miAbrirReduccion)Open).getValFilIni();
            filFin = ((miAbrirReduccion)Open).getValFilFin();
            seal = ((miAbrirReduccion)Open).getSe();
            minor = ((miAbrirReduccion)Open).getMenoresQue();
            columnSelected = ((miAbrirReduccion)Open).getColSel();
            valuesAttribute = ((miAbrirReduccion)Open).getAtriSel();
            
            Reduccion reduction = new Reduccion(dataIn, selRbtn, rManRem, filIni,filFin, seal, minor, columnSelected, valuesAttribute);
            dataOut = reduction;
            this.setInfo("Option Selected: " + (selRbtn+1) + "\nFirst Row: " + filIni + "\nLast Row: " + filFin + "\nColumn Selected: " + (columnSelected+1)+ "\nValues Attributes: " + valuesAttribute);
        } else if(filterName.equals("selection")){
            numberColumns = ((miAbrirSeleccion)Open).getNumColsSel();
            columnsSelected = ((miAbrirSeleccion)Open).getColsSel();
            columnSelected = ((miAbrirSeleccion)Open).getColObj();
            
            Seleccion selection = new Seleccion(dataIn, numberColumns,columnsSelected, columnSelected);
            dataOut = selection;
            this.setInfo("Number of Columns: " + (numberColumns+1) + "\nColumn Target: " + (columnSelected+1));
        }
        
        mnuView.setEnabled(true);
        ChooserEscritorio.setStatus("Filter " + filterText + " loaded");
    }
    
    public JMenuItem getMnuRun() {
        return mnuRun;
    }
    
    private void mnuViewActionPerformed(java.awt.event.ActionEvent evt) {
//        final FilterIcon ai = this;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                    if(filterName.equals("updatem")){
                        
                        if(!View.getClass().getSimpleName().equals("miVerResRemMiss")){
                            createViews();
                        }
                        ((miVerResRemMiss)View).setDatas(dataIn, dataOut);
                        View.setVisible(true);
                        
                    } else if(filterName.equals("codification")){
                        
                        if(!View.getClass().getSimpleName().equals("miVerCodificacion")){
                            createViews();
                        }
                        ((miVerCodificacion)View).setDatas(dataIn, dataOut);
                        View.setVisible(true);

                    } else if(filterName.equals("removem")){
                        
                        if(!View.getClass().getSimpleName().equals("miVerResElimMiss")){
                            createViews();
                        }
                        ((miVerResElimMiss)View).setDatas(dataIn, dataOut);
                        View.setVisible(true);
                        
                    } else if(filterName.equals("muestra")){
                        
                        if(!View.getClass().getSimpleName().equals("miVerMuestra")){
                            createViews();
                        }
                        ((miVerMuestra)View).setDatas(dataIn, dataOut);
                        View.setVisible(true);
                        
                    } else if(filterName.equals("remvalor")){
                        
                        if(!View.getClass().getSimpleName().equals("miVerRemVal")){
                            createViews();
                        }
                        ((miVerRemVal)View).setDatas(dataIn, dataOut);
                        View.setVisible(true);
                        
                    } else if(filterName.equals("rangenum")){
                        
                        if(!View.getClass().getSimpleName().equals("miVerRangoNumerico")){
                            createViews();
                        }     
                        ((miVerRangoNumerico)View).setDatas(dataIn, dataOut);                        
                        View.setVisible(true);
                        
                    } else if(filterName.equals("discretize")){
                        
                        if(!View.getClass().getSimpleName().equals("miVerDiscretizacion")){
                            createViews();
                        } 
                        ((miVerDiscretizacion)View).setDatas(dataIn, dataOut);    
                        View.setVisible(true);
                    } else if(filterName.equals("reduction")){
                        
                        if(!View.getClass().getSimpleName().equals("miVerReduccion")){
                            createViews();
                        }
                        ((miVerReduccion)View).setDatas(dataIn, dataOut); 
                        View.setVisible(true);
                        
                    } else if(filterName.equals("selection")){
                        
                        if(!View.getClass().getSimpleName().equals("miVerSeleccion")){
                            createViews();
                        }
                        ((miVerSeleccion)View).setDatas(dataIn, dataOut);
                        View.setVisible(true);
                    }

//                    View.setVisible(true);
            }
        });
    }
    
    public JMenuItem getMnuView() {
        return mnuView;
    }
    
    public DataSet buildDataSet(){
        dataset = new DataSet("");
        ArrayList dictionary;
        
        if(filterName.equals("codification")){
            dictionary = ((Codificacion)dataOut).valatricod.datos;
        } else {
            dictionary = this.getDictionaryFromTableModel();
        }
        dataset.buildMultiValuedDictionary(dictionary);
        
        for(int i = 0; i < dataOut.getRowCount(); i++){
            for(int j = 0; j < dataOut.getColumnCount(); j++){
                if(dataOut.getValueAt(i, j) != null){
                    String value = dataOut.getColumnName(j) + "=" + (String)dataOut.getValueAt(i, j);
                    short item = dataset.codeAttribute(value);
                    int id = (j == dataOut.getColumnCount() - 1) ? -1 : j;
                    dataset.buildNTree(item, id);
                }
            }
        }
        
        return dataset;
    }
    
    private ArrayList getDictionaryFromTableModel() {
        ArrayList dictionary = new ArrayList();
        int rows = dataOut.getRowCount();
        int columns = dataOut.getColumnCount();
        for(int column = 0; column < columns; column++){
            String columnName = dataOut.getColumnName(column);
            ArrayList attributes = this.getAttributes(rows, column);
            Iterator it = attributes.iterator();
            while(it.hasNext()){
                String attribute = (String)it.next();
                dictionary.add(columnName + "=" + attribute);
            }
        }
        
        return dictionary;
    }
    
    private ArrayList getAttributes(int rows, int column){
        ArrayList values = new ArrayList();
        for(int row = 0; row < rows; row++){
            String key = (String) dataOut.getValueAt(row, column);
            int index = Collections.binarySearch(values, key);
            if(index < 0){
                values.add((-(index) - 1), key);
            }
        }
        
        return values;
    }
}