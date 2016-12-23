/*
 * AvlTree.java
 *
 * Created on 5 de Marzode 2005, 11:30 AM
 */

package Utils;

import java.lang.*;
import java.util.*;

public class AvlTree {
    /** La raiz del arbol. */
    private AvlNode root;
    /** El numero de elementos en el arbol. */
    private int n;
    /** Nodo para recorrer el arbol. */
    private AvlNode node;
    /** Pila para retornar el ultimo AvlNode que ha sido insertado. */
    private Stack stack;

    private ItemSet auxiliar;
    
    public AvlTree() {
        root = null;
        n = 0;
    }
    
    public void insertItemsetOne( ItemSet x ) {
        root = insertItemsetOne( x, root );
    }
    
    private AvlNode insertItemsetOne( ItemSet x, AvlNode t ){
        if( t == null ){
            t = new AvlNode( x, null, null );
            n++;
        }else if( compareItemsets((ItemSet) x,(ItemSet) t.element) < 0){
            t.left = insertItemsetOne( x, t.left );
            if( height( t.left ) - height( t.right ) == 2 )
                if( compareItemsets((ItemSet ) x,(ItemSet ) t.left.element) < 0)
                    t = rotateWithLeftChild( t );
                else
                    t = doubleWithLeftChild( t );
        }else if( compareItemsets((ItemSet) x,(ItemSet) t.element) > 0){
            t.right = insertItemsetOne( x, t.right );
            if( height( t.right ) - height( t.left ) == 2 )
                if( compareItemsets((ItemSet) x,(ItemSet) t.right.element) > 0)
                    t = rotateWithRightChild( t );
                else
                    t = doubleWithRightChild( t );
        }else{
            // Incrementa el soporte
            t.element.increaseSupport();
        }
        t.height = max( height( t.left ), height( t.right ) ) + 1;
        return t;
    }
    
    public void insert( ItemSet x ) {
        root = insert( x, root );
    }
    
    private AvlNode insert( ItemSet x, AvlNode t ){
        if( t == null ){
            t = new AvlNode( x, null, null );
            n++;
        }else if( compareItemsets((ItemSet) x,(ItemSet) t.element) < 0){
            t.left = insert( x, t.left );
            if( height( t.left ) - height( t.right ) == 2 )
                if( compareItemsets((ItemSet ) x,(ItemSet ) t.left.element) < 0)
                    t = rotateWithLeftChild( t );
                else
                    t = doubleWithLeftChild( t );
        }else if( compareItemsets((ItemSet) x,(ItemSet) t.element) > 0){
            t.right = insert( x, t.right );
            if( height( t.right ) - height( t.left ) == 2 )
                if( compareItemsets((ItemSet) x,(ItemSet) t.right.element) > 0)
                    t = rotateWithRightChild( t );
                else
                    t = doubleWithRightChild( t );
        }else{
            //t.element.incrementarSoporte();
        }
        t.height = max( height( t.left ), height( t.right ) ) + 1;
        return t;
    }
    
    public int getTipo(){
        return root.element.getItems().length;
    }
    
    /**
     * Make the tree logically empty.
     */
    public void makeEmpty(){
        root = null;
    }
    
    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty(){
        return root == null;
    }
    
    public int compareItemsets(ItemSet i1, ItemSet i2 ){
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
    
    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( ){
        if( !isEmpty( ) )
            //System.out.println( "¡¡¡Arbol Vacio!!!" );
            //else
            printTree( root );
    }
    
    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the tree.
     */
    private void printTree( AvlNode t ){
        if( t != null ){
            printTree( t.left );
            System.out.println(t.element.toString() );
            printTree( t.right );
        }
    }
    
    /**
     * Internal method to get element field.
     * @param t the node.
     * @return the element field or null if t is null.
     */
    
    private ItemSet elementAt( AvlNode t ){
        return /*t == null ? null : */t.element;
    }
    
    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the tree.
     * @return the new root.
     */
    public int howMany(){
        return n;
    }
    
    public AvlNode getRoot(){
        return root;
    }
    
    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return the matching item or null if not found.
     */
    public int find( ItemSet x ){
        return find( x, root );
    }
    
    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the tree.
     * @return node containing the matched item.
     */
    private int find( ItemSet x, AvlNode t ){
        while( t != null ){
            int y = compareItemsets(x,t.element);
            if(y < 0){
                t = t.left;
            } else if(y > 0){
                t = t.right;
            } else {
                return 1;    // Item Encontrado
            }
        }
        return 0;   // Item NO Encontrado
    }
    
    public ItemSet findItemset( ItemSet x ){
        return findItemset( x, root );
    }
    
    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the tree.
     * @return node containing the matched item.
     */
    private ItemSet findItemset( ItemSet x, AvlNode t ){
        while( t != null ){
            int y = compareItemsets(x,t.element);
            if(y < 0){
                t = t.left;
            } else if(y > 0){
                t = t.right;
            } else {
                return t.element;    // ItemSet Encontrado
            }
        }
        return null;   // ItemSet NO Encontrado
    }
    
    /*public void recorrer(){
        if( isEmpty( ) )
            System.out.println( "¡¡¡Arbol Vacio!!!" );
        else
            recorrer( root );
    }
    
    private void recorrer(AvlNode t){
        if( t != null ){
            recorrer( t.left);
            if(auxiliar == null && t.check == false){
                auxiliar = t.element;
                t.check = true;
            }
            if(!t.check){
                System.out.println(auxiliar.toString() + " - " + t.element.toString());
                int tipo = auxiliar.getItems().length;
                short[] candidato = new short[tipo + 1];
                for(int k = 0; k < auxiliar.getItems().length; k++){
                    candidato[k] = auxiliar.getItems()[k];
                }
                candidato[tipo] = t.element.getItems()[tipo-1];
                for(int k = 0; k < candidato.length; k++)
                    System.out.println(candidato[k]);
            }
            recorrer(t.right);
        }
    }*/
    
    /**
     * Return the height of node t, or -1, if null.
     */
    private static int height( AvlNode t ){
        return t == null ? -1 : t.height;
    }
    
    /**
     * Return maximum of lhs and rhs.
     */
    private static int max( int lhs, int rhs ){
        return lhs > rhs ? lhs : rhs;
    }
    
    public void setNode() {
        node = root;
    }
    
    public AvlNode walkTree() {
        if(node != null && !node.isCheck()) {
            node.setCheck(true);
            stack.push(node);
            node = node.left;
            walkTree();
        }
        node = (AvlNode) stack.peek();
        if(node.right != null) {
            node = node.right;
        }
        return (AvlNode) stack.pop();
    }
    
    /**
     * Rotate binary tree node with left child.
     * For AVL trees, this is a single rotation for case 1.
     * Update heights, then return new root.
     */
    private static AvlNode rotateWithLeftChild( AvlNode k2 ){
        AvlNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = max( height( k2.left ), height( k2.right ) ) + 1;
        k1.height = max( height( k1.left ), k2.height ) + 1;
        return k1;
    }
    
    /**
     * Rotate binary tree node with right child.
     * For AVL trees, this is a single rotation for case 4.
     * Update heights, then return new root.
     */
    private static AvlNode rotateWithRightChild( AvlNode k1 ){
        AvlNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = max( height( k1.left ), height( k1.right ) ) + 1;
        k2.height = max( height( k2.right ), k1.height ) + 1;
        return k2;
    }
    
    /**
     * Double rotate binary tree node: first left child
     * with its right child; then node k3 with new left child.
     * For AVL trees, this is a double rotation for case 2.
     * Update heights, then return new root.
     */
    private static AvlNode doubleWithLeftChild( AvlNode k3 ){
        k3.left = rotateWithRightChild( k3.left );
        return rotateWithLeftChild( k3 );
    }
    
    /**
     * Double rotate binary tree node: first right child
     * with its left child; then node k1 with new right child.
     * For AVL trees, this is a double rotation for case 3.
     * Update heights, then return new root.
     */
    private static AvlNode doubleWithRightChild( AvlNode k1 ){
        k1.right = rotateWithLeftChild( k1.right );
        return rotateWithRightChild( k1 );
    }
}
