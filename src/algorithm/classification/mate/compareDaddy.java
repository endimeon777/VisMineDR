/*
 * compareConfidence.java
 *
 * Created on 26 de diciembre de 2006, 12:05
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package algorithm.classification.mate;
import java.util.*;

/**
 *
 * @author Proyecto Taryi
 */
public class compareDaddy implements Comparator {
    
    public int compare(Object obj1, Object obj2) {
        int s1 = ((Describe)obj1).getFather();
        int s2 = ((Describe)obj2).getFather();
        
        return s1 - s2;
    }
}
