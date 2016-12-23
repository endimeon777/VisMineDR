/*
 * Nodo.java
 *
 * Created on 3 de febrero de 2010, 10:16
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package algorithm.cluster.jerarquico;

import java.io.Serializable;
import javax.swing.table.AbstractTableModel;

/**
 *
 *
 */
  public class Nodo implements Serializable{
    private static final long serialVersionUID = 6701676767971317106L;
        
//        private static final long serialVersionUID = 1L; 
    
        public Nodo izquierda = null;
        public Nodo derecha = null;
        public String name;
        public AbstractTableModel dataNodo = null;
        public AbstractTableModel dataOri = null;
        
        public int cantObjetos;
        public int nivel;
        public int nivelPadre;
        public int kCluster;
        public int ind;
    
    /** Creates a new instance of Nodo */
         public Nodo() {
         }
      }