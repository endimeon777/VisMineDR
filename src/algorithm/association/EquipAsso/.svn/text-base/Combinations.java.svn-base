/*
 * Combinations.java
 *
 * Created on 22 de marzo de 2005, 09:05 AM
 *
 * Proyecto Tariy
 */

package algorithm.association.EquipAsso;

import java.util.ArrayList;
import Utils.*;

public class Combinations {
    /**
     * Indica el tipo o tamaño de los itemsets frecuentes que se desean obtener.
     */
    private int type;
    
    /**
     * Almacena temporalmente el itemset del cuál se calculán sus diferentes 
     * combinaciones de itemsets candidatos.
     */
    private ArrayList itemset;
    
    /**
     * Construye una instancia de la clase Combinations.
     *
     *  @param type     Indica el tipo o tamaño de los itemsets frecuentes que se 
     *                  desean obtener.
     *  @param itemset  Almacena temporalmente el itemset del cuál se calculán 
     *                  sus diferentes combinaciones de itemsets candidatos.
     */
    public Combinations(int type, ArrayList itemset) {
        this.type = type;
        this.itemset = itemset;
    }
    
    /**
     * Calcula todas las posibles combinaciones tipo o tamaño 2 de un itemset y
     * se almacenan en un árbol Avl de itemsets candidatos.
     *
     * @param newTree Almacena los itemsets candidatos tipo o tamaño 2.
     */
    public void combine2(AvlTree newTree){
        int a, b;
        int length = itemset.size();
        
        for(a = 0; a < length - 1; a++){
            for(b = a + 1; b < length; b++){
                ItemSet i = new ItemSet(type);
                i.addItem((Short) itemset.get(a));
                i.addItem((Short) itemset.get(b));
                newTree.insertItemsetOne(i);
            }
        }
    }
    
    /**
     * Calcula todas las posibles combinaciones tipo o tamaño 3 de un itemset y
     * se almacenan en un árbol Avl de itemsets candidatos.
     *
     * @param newTree Almacena los itemsets candidatos tipo o tamaño 3.
     */
    public void combine3(AvlTree newTree){
        int a, b, c;
        int length = itemset.size();
        
        for(a = 0; a < length - 2; a++){
            for(b = a + 1; b < length - 1; b++){
                for(c = b + 1; c < length; c++){
                    ItemSet i = new ItemSet(type);
                    i.addItem((Short) itemset.get(a));
                    i.addItem((Short) itemset.get(b));
                    i.addItem((Short) itemset.get(c));
                    newTree.insertItemsetOne(i);
                }
            }
        }
    }
    
    /**
     * Calcula todas las posibles combinaciones tipo o tamaño 4 de un itemset y
     * se almacenan en un árbol Avl de itemsets candidatos.
     *
     * @param newTree Almacena los itemsets candidatos tipo o tamaño 4.
     */
    public void combine4(AvlTree newTree){
        int a, b, c, d;
        int length = itemset.size();
        
        for(a = 0; a < length - 3; a++){
            for(b = a + 1; b < length - 2; b++){
                for(c = b + 1; c < length - 1; c++){
                    for(d = c + 1; d < length; d++){
                        ItemSet i = new ItemSet(type);
                        i.addItem((Short) itemset.get(a));
                        i.addItem((Short) itemset.get(b));
                        i.addItem((Short) itemset.get(c));
                        i.addItem((Short) itemset.get(d));
                        newTree.insertItemsetOne(i);
                    }
                }
            }
        }
    }
    
    /**
     * Calcula todas las posibles combinaciones tipo o tamaño 5 de un itemset y
     * se almacenan en un árbol Avl de itemsets candidatos.
     *
     * @param newTree Almacena los itemsets candidatos tipo o tamaño 5.
     */
    public void combine5(AvlTree newTree){
        int a, b, c, d, e;
        int length = itemset.size();
        
        for(a = 0; a < length - 4; a++){
            for(b = a + 1; b < length - 3; b++){
                for(c = b + 1; c < length - 2; c++){
                    for(d = c + 1; d < length - 1; d++){
                        for(e = d + 1; e < length; e++){
                            ItemSet i = new ItemSet(type);
                            i.addItem((Short) itemset.get(a));
                            i.addItem((Short) itemset.get(b));
                            i.addItem((Short) itemset.get(c));
                            i.addItem((Short) itemset.get(d));
                            i.addItem((Short) itemset.get(e));
                            newTree.insertItemsetOne(i);
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Calcula todas las posibles combinaciones tipo o tamaño 6 de un itemset y
     * se almacenan en un árbol Avl de itemsets candidatos.
     *
     * @param newTree Almacena los itemsets candidatos tipo o tamaño 6.
     */
    public void combine6(AvlTree newTree){
        int a, b, c, d, e, f;
        int length = itemset.size();
        
        for(a = 0; a < length - 5; a++){
            for(b = a + 1; b < length - 4; b++){
                for(c = b + 1; c < length - 3; c++){
                    for(d = c + 1; d < length - 2; d++){
                        for(e = d + 1; e < length - 1; e++){
                            for(f = e + 1; f < length; f++){
                                ItemSet i = new ItemSet(type);
                                i.addItem((Short) itemset.get(a));
                                i.addItem((Short) itemset.get(b));
                                i.addItem((Short) itemset.get(c));
                                i.addItem((Short) itemset.get(d));
                                i.addItem((Short) itemset.get(e));
                                i.addItem((Short) itemset.get(f));
                                newTree.insertItemsetOne(i);
                            }
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Calcula todas las posibles combinaciones tipo o tamaño 7 de un itemset y
     * se almacenan en un árbol Avl de itemsets candidatos.
     *
     * @param newTree Almacena los itemsets candidatos tipo o tamaño 7.
     */
    public void combine7(AvlTree newTree){
        int a, b, c, d, e, f, g;
        int length = itemset.size();
        
        for(a = 0; a < length - 6; a++){
            for(b = a + 1; b < length - 5; b++){
                for(c = b + 1; c < length - 4; c++){
                    for(d = c + 1; d < length - 3; d++){
                        for(e = d + 1; e < length - 2; e++){
                            for(f = e + 1; f < length - 1; f++){
                                for(g = f + 1; g < length; g++){
                                    ItemSet i = new ItemSet(type);
                                    i.addItem((Short) itemset.get(a));
                                    i.addItem((Short) itemset.get(b));
                                    i.addItem((Short) itemset.get(c));
                                    i.addItem((Short) itemset.get(d));
                                    i.addItem((Short) itemset.get(e));
                                    i.addItem((Short) itemset.get(f));
                                    i.addItem((Short) itemset.get(g));
                                    newTree.insertItemsetOne(i);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Determina de acuerdo al tipo asignado a type el tamaño de los itemsets 
     * candidatos a encontrar a partir las combinaciones de los elementos de un 
     * itemset.
     *
     * @param newTree Almacena los itemsets candidatos.
     */
    public void letsCombine(AvlTree newTree){
        switch(type){
            case 2:
                combine2(newTree);
                break;
            case 3:
                combine3(newTree);
                break;
            case 4:
                combine4(newTree);
                break;
            case 5:
                combine5(newTree);
                break;
            case 6:
                combine6(newTree);
                break;
            case 7:
                combine7(newTree);
                break;
            default:
                System.err.println("Out of Range...");
        }
    }
}
