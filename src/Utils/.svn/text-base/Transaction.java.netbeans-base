/*
 * Transaccion.java
 *
 * Created on 2 de febrero de 2005, 09:43 AM
 */
package Utils;


import algorithm.association.EquipAsso.EquipAsso;
import algorithm.association.FPGrowth.FPGrowth;
import java.util.*;
import java.lang.*;

class bySupport implements Comparator{
    
    /** Creates a new instance of Comparer */
    public int compare(Object o1, Object o2){
        ItemSet a = (ItemSet) o1;
        ItemSet b = (ItemSet) o2;
        return b.getSupport() - a.getSupport();
    }
}

class byItem implements Comparator{
    
    /** Creates a new instance of Comparer */
    public int compare(Object o1, Object o2){
        ItemSet a = (ItemSet) o1;
        ItemSet b = (ItemSet) o2;
        return a.getItems()[0] - b.getItems()[0];
    }
}

/**
 *
 * @author Proyecto Tariy
 */
public class Transaction {
    
    /** Creates a new instance of Transaccion */
    
    ArrayList articles = new ArrayList();
    private short item;
    
    public Transaction(){
    }

    public ArrayList getArticles() {
        articles.trimToSize();
        return articles;
    }

    public int getSize(){
        return articles.size();
    } 
    
    public ItemSet getItemset(int i){
        return (ItemSet) articles.get(i);
    }
    
    public void sortBySupport(){
        Collections.sort(articles, new byItem());
        Collections.sort(articles, new bySupport());
    }
    
    public void sortByItem(){
        Collections.sort(articles, new byItem());
    }
    
    public int loadItemsets(ItemSet[] frequentsOne){
        articles.clear();
        
        item = FPGrowth.dataset.getNode();
        //System.out.print(item + ", ");
        while(item >= 0){
            ItemSet i = new ItemSet(1);
            i.addItem(item);
            //i = frequentsOne.findItemset(i);
            int pos = Arrays.binarySearch(frequentsOne, i, new byItem());
            if( pos >= 0 ){
                i = frequentsOne[pos];
                articles.add(i);
            }
            item = FPGrowth.dataset.getNode();
            //System.out.print(item + ", ");
        }
        if(item == -1){
            return 0;
        } else {
            //System.out.println();
            return 1;
        }
    }
    
    public int loadItemsets(DataSet dataset, AvlTree frequentsOne){
        articles.clear();
        
        item = dataset.getNode();
        //System.out.print(item + ", ");
        while(item >= 0){
            ItemSet i = new ItemSet(1);
            i.addItem(item);
            i = frequentsOne.findItemset(i);
            //int pos = Arrays.binarySearch(frequentsOne, i, new byItem());
            if( i != null ){
                //i = frequentsOne[pos];
                articles.add(i.getItems()[0]);
            }
            item = dataset.getNode();
            //System.out.print(item + ", ");
        }
        if(item == -1){
            return 0;
        } else {
            //System.out.println();
            return 1;
        }
    }    
    
    public void clearTransaction(){
        this.articles.clear();
    }
    
    public void printTransaction(){
        System.out.println(articles.toString());
        //return articles.toString();
    }
    
}
