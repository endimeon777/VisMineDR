/*
 * Rules.java
 *
 * Created on 14 de mayo de 2006, 12:09
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Utils;

import java.util.Comparator;

/**
 *
 * @author ivan
 */
class byConfidence implements Comparator {
    
    /** Creates a new instance of Comparer */
    public int compare(Object o1, Object o2){
        Rules a = (Rules) o1;
        Rules b = (Rules) o2;
        if(b.getConfidence() > a.getConfidence()) {
            return 1;
        } else if(b.getConfidence() == a.getConfidence()) {
            return 0;
        } else {
            return -1;
        }
    }
}

public class Rules {
    private String antecedent;
    private String concecuent;
    private float confidence;
    
    /** Creates a new instance of Rules */
    public Rules(String antecedent, String concecuent, float confidence) {
        this.antecedent = antecedent;
        this.concecuent = concecuent;
        this.confidence = confidence;
    }

    public String getAntecedent() {
        return antecedent;
    }

    public String getConcecuent() {
        return concecuent;
    }

    public float getConfidence() {
        return confidence;
    }
    
    /**
     * Representación textual de una regla.
     */
    public String toString() {
        return (String) this.getAntecedent() + " -> " + this.getConcecuent() 
                + " (" + this.getConfidence() + "%)";
    }    
}
