/*
 * compareClass.java
 *
 * Created on 15 de enero de 2007, 17:14
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
public class compareClass implements Comparator {
    boolean ascendent = true;
    
    public compareClass(boolean ascendent){
        this.ascendent = ascendent;
    }
    
    public int compare(Object obj1, Object obj2) {
        String s1 = ((Attribute)((Leaf)obj1).getLeaf()).name;
        String s2 = ((Attribute)((Leaf)obj2).getLeaf()).name;
        
        if(ascendent){
            return s2.compareTo(s1);
        } else {
            return s1.compareTo(s2);
        }
    }
}
