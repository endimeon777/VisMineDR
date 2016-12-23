/*
 * compareValues.java
 *
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package algorithm.classification;

import java.util.Comparator;

/**
 *
 * @author Proyecto Taryi
 */
public class compareValues implements Comparator {
    
    public int compare(Object obj1, Object obj2) {
        String s1 = ((Value)obj1).getName();
        String s2 = ((Value)obj2).getName();
        
        return s1.compareTo(s2);
    }
}
