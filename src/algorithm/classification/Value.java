/*
 * Value.java
 *
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package algorithm.classification;

import java.io.Serializable;

/**
 *
 *
 */
public class Value implements Serializable{
    private static final long serialVersionUID = -7403011539001200099L;
    
//    private static final long serialVersionUID = 1L; 
    
    private String name;
    private int frecuence;
    
    /** Creates a new instance of Value */
    public Value(String name, int frecuence) {
        this.name = name;
        this.frecuence = frecuence;
    }

    public int getFrecuence() {
        return frecuence;
    }

    public String getName() {
        return name;
    }

    public void setFrecuence(int frecuence) {
        this.frecuence = frecuence;
    }
    
    public void incFrecuence(int frecuence) {
        this.frecuence += frecuence;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String toString(){
        return name + " (" + frecuence + ")";
    }
}
