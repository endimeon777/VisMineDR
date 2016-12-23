/*
 * Utils.java
 *
 * Created on 22 de marzo de 2005, 05:26 PM
 */

package Utils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Vector;

/**
 * Clase que almacena los valores de los itemsets.
 * @author Proyecto Tariy
 */
public class ItemSet implements Comparator {
    /** Array que almacena los items que forman el itemset. */
    private short[] items;
    /** support asociado a este itemset*/
    private short support;
    /** Tamaño del itemset */
    private short n;
    
    public ItemSet(int type) {
        this.items = new short[type];
        this.support = 1;
        this.n = 0;
    }
    
    public ItemSet(Vector items, short support) {
        this.items = new short[items.size()];
        for(this.n=0; n<items.size(); n++)
            this.items[n] = (Short) items.elementAt(n);
        this.support = support;
    }
    
    public ItemSet(short item, short support) {
        this.items = new short[1];
        this.items[0] = item;
        this.support = support;
    }    
    
    /**
     * Adiciona un item al itemset.
     * @param item Item a adicionar al itemset.
     */
    public void addItem(short item) {
        if(n < this.getType()) {
            this.items[n++] = item;
        }
    }
    
    /**
     * Devuelve el itemset asociado
     * @return Un arreglo con los items de este itemset
     */
    public short[] getItems(){
        return this.items;
    }
    
    /**
     * Devuelve el support de este itemset
     * @return El support del itemset
     */
    public short getSupport(){
        return this.support;
    }
    
    /**
     * Devuelve el tamaño de un itemset.
     * @return El Tamaño del itemset.
     */
    public int getType(){
        return this.items.length;
    }
    
    /**
     * Establece el soporte de este itemset.
     * @param support El soporte del itemset.
     */
    public void setSupport(short support){
        this.support = support;
    }
    
    /**
     * Incrementa el support de este itemset en uno.
     */
    public void increaseSupport(){
        this.support++;
    }
    
    public void increaseSupport(int plus){
        this.support += plus;
    }
    
    /**
     * Representacion textual del itemset
     * @return Cadena de texto con el contenido del itemset
     */
    public String toString(){
        String str = "[";
        int i;
        for(i = 0; i < items.length - 1; i++){
            str += items[i] + ", ";
        }
        str += items[i] + "] : " + support;
        return str;
    }
    
    public int compare(Object o1, Object o2){
        ItemSet i1 = (ItemSet) o1;
        ItemSet i2 = (ItemSet) o2;
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
    
    public void sortItems(){
        Arrays.sort(items);
    }
    
    /*public ItemSet clone(){
        ItemSet i = new ItemSet(this.itemset, this.support);
        return i;
    }*/
    
    public boolean equals(Object o) {
        return this == o;
    }
}
