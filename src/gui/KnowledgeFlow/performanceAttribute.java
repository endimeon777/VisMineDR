/*
 * atributoRendimiento.java
 *
 * Created on 9 de agosto de 2007, 12:35 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gui.KnowledgeFlow;

import java.util.Vector;

/**
 *
 * 
 */
public class performanceAttribute {
    public String nomAlgoritmo;
    public int numInstancias;
    public int numColumnas;
    public long tiempoUtilizado;
    
    /** Creates a new instance of atributoRendimiento */
    public performanceAttribute(String auxNom,int auxNumIns,int auxNumCols,long auxTiempo) {
        nomAlgoritmo=auxNom;
        numInstancias=auxNumIns;
        tiempoUtilizado=auxTiempo;
        numColumnas=auxNumCols;
    }

    public Vector getValores() {
        Vector aux=new Vector();
        aux.add(nomAlgoritmo);
        aux.add(numInstancias);
        aux.add(numColumnas);
        aux.add(tiempoUtilizado);
        
        return aux;
    }
}
