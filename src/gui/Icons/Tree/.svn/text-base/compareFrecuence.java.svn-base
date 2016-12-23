/*
 * compareFrecuence.java
 *
 * Created on 15 de enero de 2007, 17:00
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
 * @author and
 */
public class compareFrecuence implements Comparator {
    boolean ascendent = true;
    
    public compareFrecuence(boolean ascendent){
        this.ascendent = ascendent;
    }
    
    public int compare(Object obj1, Object obj2) {
        int s1 = ((Attribute)((Leaf)obj1).getLeaf()).getFrecuence();
        int s2 = ((Attribute)((Leaf)obj2).getLeaf()).getFrecuence();
        
        if(ascendent){
            return s2 - s1;
        } else {
            return s1 - s2;
        }
    }
}

