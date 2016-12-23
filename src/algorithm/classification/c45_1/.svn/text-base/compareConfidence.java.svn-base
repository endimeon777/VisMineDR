/*
 * compareConfidence.java
 *
 * Created on 26 de diciembre de 2006, 12:05
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package algorithm.classification.c45_1;
import algorithm.classification.Value;
import java.util.*;

/**
 *
 * @author Proyecto Taryi
 */
public class compareConfidence implements Comparator {
    
    public int compare(Object obj1, Object obj2) {
        int s1 = ((Attribute)((Leaf)obj1).getLeaf()).getFrecuenceFather();
        int s2 = ((Attribute)((Leaf)obj2).getLeaf()).getFrecuenceFather();
        
        return s2 - s1;
    }
}

