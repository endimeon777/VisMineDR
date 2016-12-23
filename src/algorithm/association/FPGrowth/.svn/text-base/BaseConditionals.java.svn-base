/*
 * Principal.java
 *
 * Created on 14 de febrero de 2005, 10:18 AM
 */

package algorithm.association.FPGrowth;
import Utils.ItemSet;
import java.util.*;

/**
 *
 * @author Proyecto Taryi
 */
public class BaseConditionals {
    private Vector baseConditionals;
    private short frequent;
    private short support;
    
    /** Creates a new instance of Principal */
    public BaseConditionals(short frequent, short support) {
        this.baseConditionals = new Vector();
        this.frequent = frequent;
        this.support = support;
    }
    
    public void addBaseConditionals(BaseConditional c){
        baseConditionals.add(c);
    }
    
    public void sortByElement(){
        Collections.sort(baseConditionals, new compareBaseConditional());
    }
    
    private short findItem(Short item){
        BaseConditional aux;
        short n = 0;
                
        for(int i = 0; i < baseConditionals.size(); i++){
            aux = (BaseConditional) baseConditionals.elementAt(i);
            if(aux.getPath().contains(item)){
                n+=aux.getSoporte();
            }
        }
        return n;
    }
    
    public Vector buildConditionals(Vector Trees){
        BaseConditional aux1;
        Conditionals conditionals = new Conditionals();
        Short item;
        Combinations combine = new Combinations(Trees);
        
        for(int i = 0; i < baseConditionals.size(); i++){
            aux1 = (BaseConditional) baseConditionals.elementAt(i);
            for(int j = 0; j < aux1.getPath().size(); j++){
                item = (Short)aux1.getPath().elementAt(j);
                if(this.findItem(item) < support){
                    aux1.getPath().remove(j);
                }
            }
        }
        for(int i = 0; i < baseConditionals.size(); i++){
            aux1 = (BaseConditional) baseConditionals.elementAt(i);
            if(aux1.getPath().isEmpty() == false){
                for(int j = 0; j < aux1.getPath().size(); j++){
                    conditionals.addConditional(
                            new ItemSet((Short)aux1.getPath().elementAt(j),
                                          aux1.getSoporte()));
                }
                combine.setFrequent(frequent);
                combine.combine(conditionals.getConditionals(), 
                        conditionals.getConditionals().size()-1);
                conditionals.clearConditionals();            
            }
        }
        combine.pruneCandidates();
        return Combinations.getFrequents();
    }
}
