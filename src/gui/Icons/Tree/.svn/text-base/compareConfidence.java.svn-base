/*
 * compareConfidence.java
 *
 * Created on 26 de diciembre de 2006, 12:05
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gui.Icons.Tree;
import algorithm.classification.c45_1.Attribute;
import algorithm.classification.c45_1.Leaf;
import java.util.*;

/**
 *
 * @author Proyecto Taryi
 */
public class compareConfidence implements Comparator {
    boolean ascendent = true;
    
    public compareConfidence(boolean ascendent){
        this.ascendent = ascendent;
    }
    
    public int compare(Object obj1, Object obj2) {
        int a1 = ((Attribute)((Leaf)obj1).getLeaf()).getFrecuence();
        int a2 = ((Attribute)((Leaf)obj1).getLeaf()).getFrecuenceFather();
        int b1 = ((Attribute)((Leaf)obj2).getLeaf()).getFrecuence();
        int b2 = ((Attribute)((Leaf)obj2).getLeaf()).getFrecuenceFather();
        Double c1 = new Double(a1/a2);
        Double c2 = new Double(b1/b2);
        
        if(ascendent){
            return c2.compareTo(c1);
        } else {
            return c1.compareTo(c2);
        }
    }
}

