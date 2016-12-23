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
public class compareFrecuence implements Comparator {
    
    public int compare(Object obj1, Object obj2) {
        int s1 = ((Value)obj1).getFrecuence();
        int s2 = ((Value)obj2).getFrecuence();
        
        return s1 - s2;
    }
}