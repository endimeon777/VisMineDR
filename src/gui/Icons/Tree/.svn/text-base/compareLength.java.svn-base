/*
 * compareLength.java
 *
 * Created on 25 de enero de 2007, 19:38
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gui.Icons.Tree;

import algorithm.classification.c45_1.Leaf;
import java.util.Comparator;

/**
 *
 * @author and
 */
public class compareLength implements Comparator {
    boolean ascendent = true;
    
    public compareLength(boolean ascendent){
        this.ascendent = ascendent;
    }
    
    public int compare(Object obj1, Object obj2) {
        int s1 = ((Leaf)obj1).getSizeRule();
        int s2 = ((Leaf)obj2).getSizeRule();
        
        if(ascendent){
            return s2 - s1;
        } else {
            return s1 - s2;
        }
    }
}
