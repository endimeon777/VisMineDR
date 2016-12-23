/*
 * FPGrowthNode.java
 *
 * Proyecto Tariy
 */

package algorithm.association.FPGrowth;

/**
 * La clase <code>FPGrowthNode</code> es la estructura de todos los nodos del
 * árbol N-Ario propio del algoritmo FPGrowth. Cada nodo tiene una referencia
 * a un nodo; padre (<tt>fat</tt>), hijo (<tt>son</tt>), hermano (<tt>bro</tt>)
 * y siguiente (<tt>next</tt> que apunta a un item identico de otra rama).
 */
public class FPGrowthNode {
    /** 
     * El valor del item que se va a añadir al árbol N-Ario propio del
     * algoritmo FPGrowth.
     */
    private short item;
    
    /** 
     * El soporte del item que se va a añadir al árbol N-Ario propio del
     * algoritmo FPGrowth.
     */
    private short support;

    /** La referencia al nodo padre del actual nodo. */
    FPGrowthNode fat; 

    /** La referencia al nodo hijo del actual nodo. */
    FPGrowthNode son;

    /** La referencia al hermano del actual nodo. */
    FPGrowthNode bro;

    /** La referencia al siguiente del actual nodo. */
    FPGrowthNode next;
    
    /**
     * Crea una nueva instancia de un nodo FPGrowthNode.
     */
    public FPGrowthNode(short item, short support) {
        this.item = item;
        this.support = support;
        son = null;
        bro = null;
        fat = null;
        next = null;
    }
    
    public FPGrowthNode() {
        this.item = 0;
        this.support = 0;
        son = null;
        bro = null;
        fat = null;
        next = null;
    }
    
    public short getItem() {
        return this.item;
    }
    
    public short getSupport() {
        return this.support;
    }
    
    public void increaseSupport() {
        this.support++;
    }
}
