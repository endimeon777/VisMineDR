/*
 * attribute.java
 *
 * Created on 31 de diciembre de 2006, 12:14
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package algorithm.classification.mate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import Utils.ItemSet;

/**
 * Almacena las combinaciones de cada valor de un atributo con la clase ganadora
 * y lleva su conteo.
 *
 * @author ivan
 */
public class Entropy {
    /** Nombre de un atributo */
    private String attribute;
    
    /**
     * Mapa que almacena las combinaciones de cada valor de un atributo con la
     * clase ganadora. Este mapa maneja la siguiente nomenclatura:
     *   key   = (short) Codigo del valor en el diccionario de datos
     *   value = (ArrayList) Combinaciones de un elemento del conjunto de datos
     *                       con los valores de la clase. La primera posicion
     *                       tiene el numero de veces que el valor de un
     *                       atributo se ha combinado.
     */
    private HashMap values;
    
    /** Numero de veces que los valores de un atributo se combinan */
    private short sup;
    
    /** Entropia de un atributo */
    private double entro;
    
    /** Creates a new instance of attribute */
    public Entropy(String attribute) {
        this.attribute = attribute;
        this.values = new HashMap();
        this.sup = 0;
        this.entro = 0.0;
    }
    
    /**
     * Añade una nueva combinacion al mapa de combinaciones, de acuerdo al
     * valor del atributo que se esta combinando. O sea, la clave del mapa
     * es el valor del atributo.
     */
    public void addCombination(Vector combination, int ncol) {
        ItemSet itemset = new ItemSet(combination, (short) 1);
        // Clave del mapa = primer elemento de la combinacion.
        short key = (Short) combination.get(0);
        itemset.sortItems();
        ArrayList combinations;
        // Si la clave no existe añadimos una nueva combinacion
        if (!values.containsKey(key)) {
            combinations = new ArrayList();
            combinations.add(itemset.getSupport());
            combinations.add(itemset);
            values.put(key, combinations);
        } else {
            boolean flag = true;
            combinations = (ArrayList) values.get(key);
            Iterator it = combinations.iterator();
            while (it.hasNext()) {
                Object elem = it.next();
                if (elem instanceof ItemSet) {
                    // Si la clave existe, verificar si la combinacion existia
                    if (itemset.compare(itemset, elem) == 0) {
                        ((ItemSet)elem).increaseSupport();
                        short s = (Short) combinations.get(0);
                        s++;
                        combinations.set(0, s);
                        flag = false;
                        break;
                    }
                }
            }
            // Si no existia se añade una nueva combinacion a ese atributo
            if (flag) {
                combinations.add(itemset);
                Integer i = (Short) combinations.get(0) + itemset.getSupport();
                Short a =  new Short(i.toString());
                combinations.set(0, a);
            }
        }
        this.sup++;
    }
    
    /**
     * Calcula la entropia de un atributo.
     */
    public void entropy() {
        double entro=0.0, entrop=0.0, num=0.0, den=0.0;
        Collection set = this.values.values();
        Iterator ith = set.iterator();
        while (ith.hasNext()) {
            ArrayList combs = (ArrayList) ith.next();
            Iterator ita = combs.iterator();
            entrop = 0.0;
            while (ita.hasNext()) {
                Object item = ita.next();
                if (item instanceof Short) {
                    den = (Short) item;
                } else {
                    num = ((ItemSet) item).getSupport();
                    entrop += ((num/den)*(Math.log(num/den)/Math.log(2)))*-1;
                }
            }
            entro = entro + (((den/this.sup) * entrop) * -1);
        }
        this.entro = entro;
    }
    
    /**
     * Muestra una representacion textual de las combinaciones de cada atributo
     * con los valores de la clase.
     */
    public void showAttributes() {
        System.out.println("\nAttribute = " + attribute + " Support = " + sup + " Entropy = " + entro);
        Collection set = this.values.values();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            ArrayList elem = (ArrayList) it.next();
            Iterator item = elem.iterator();
            while (item.hasNext()) {
                Object obj = item.next();
                if (obj instanceof Short) {
                    System.out.print( obj + "<-" );
                } else {
                    System.out.print( ((ItemSet) obj).toString() + " " );
                }
            }
            System.out.println("");
        }
    }

    public double getEntro() {
        return entro;
    }

    public String getAttribute() {
        return attribute;
    }

    public HashMap getValues() {
        return values;
    }

    public short getSup() {
        return sup;
    }

}
