/*
 * CondicionalFPtree.java
 *
 * Created on 14 de febrero de 2005, 11:15 AM
 */

package algorithm.association.FPGrowth;
import Utils.ItemSet;
import java.util.*;

/**
 *
 * @author Proyecto Taryi
 */
public class Conditionals {
    private Vector conditionals;
    
    public Conditionals() {
        this.conditionals = new Vector();
    }
    
    public void addConditional(ItemSet c){
        conditionals.addElement(c);
    }
    
    public void clearConditionals(){
        conditionals.clear();
    }
    
    public Vector getConditionals(){
        return conditionals;
    }
    
    public String toString(){
        String str = "{ ";
        for(int i = 0; i < conditionals.size() - 1; i++){
            ItemSet aux = (ItemSet) conditionals.elementAt(i);
            str +=  aux.toString() + ",";
        }
        ItemSet aux = (ItemSet) conditionals.lastElement();
        str +=  aux.toString() + " }\n";
        return str;
    }
}
