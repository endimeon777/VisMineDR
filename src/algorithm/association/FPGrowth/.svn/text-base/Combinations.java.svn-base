/*
 * Combinations.java
 *
 * Created on 23 de febrero de 2005, 11:12 AM
 */

package algorithm.association.FPGrowth;
import Utils.*;
import java.util.*;

/**
 *
 * @author Proyecto Tariy
 */
public class Combinations {
    private static Vector Trees;
    private short frequent;
    /** Array que tiene itemsets candidatos.*/
    private static Vector candidates;
    
    public Combinations(Vector Trees) {
        this.Trees = Trees;
        this.candidates = new Vector();
    }
    
    public void setFrequent(short f) {
        this.frequent = f;
    }
    
    public int combine(Vector conditional, int x){
        if(conditional.size() > 1){
            for(int i = x; i >= 0; i--){
                Vector aux = (Vector) conditional.clone();
                aux.remove(i);
                combine(aux, i - 1);
            }
        }
        this.addCandidates(conditional);
        return 0;
    }
    
    public void addCandidates(Vector conditional){
        Vector candidate = new Vector(20);
        ItemSet it = (ItemSet) conditional.firstElement();
        short lowerSuport = it.getSupport();
        
        for(int k = 0; k < conditional.size(); k++){
            it = (ItemSet) conditional.elementAt(k);
            candidate.addElement(it.getItems()[0]);
            if(it.getSupport() < lowerSuport){
                lowerSuport = it.getSupport();
            }
        }
        candidate.addElement(frequent);
        candidate.trimToSize();
        
        ItemSet itCandidate = new ItemSet(candidate, lowerSuport);
        
        int i;
        for(i=0; i<candidates.size(); i++) {
            if(compareItemsets(candidates.elementAt(i), itCandidate) == 0) {
                ((ItemSet) candidates.get(i)).increaseSupport( itCandidate.getSupport() );
                break;
            }
        }
        if(i == candidates.size())
            candidates.addElement(itCandidate);
    }
    
    public void pruneCandidates() {
        ItemSet itemset;
        AvlTree aux;
        short sup = FPGrowth.getSupport();
        int type;
        int l;
        
        for(int i=0; i<candidates.size(); i++) {
            itemset = (ItemSet) candidates.elementAt(i);
            if(itemset.getSupport() >= sup) {
                itemset.sortItems();
                type = itemset.getType();
                for(l=0; l<Trees.size(); l++) {
                    aux = (AvlTree) Trees.elementAt(l);
                    if(aux.getTipo() == type) {
                        aux.insert(itemset);
                        break;
                    }
                }
                // Si no esta, se adiciona un arbol al final del vector
                if(l == Trees.size()) {
                    aux = new AvlTree();
                    aux.insert(itemset);
                    Trees.addElement(aux);
                }
            }
        }
    }
    
    public static Vector getFrequents() {
        return Trees;
    }
    
    public int compareItemsets(Object o1, Object o2){
        Utils.ItemSet i1 = (Utils.ItemSet) o1;
        Utils.ItemSet i2 = (Utils.ItemSet) o2;
        short[] v1;
        short[] v2;
        
        v1 = i1.getItems();
        v2 = i2.getItems();
        short a, b;
        for (int i = 0; i < v1.length; i++){
            b = v2[i];
            a = v1[i];
            if (a > b)
                return 1;
            else if (a < b)
                return -1;
        }
        return 0;
    }
}
