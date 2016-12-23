/*
 * AvlNode.java
 *
 * Created on 8 de febrero de 2005, 11:14 AM
 */

package Utils;
import java.util.*;

public class AvlNode{
    ItemSet element;      // The data in the node
    AvlNode    left;      // Left child
    AvlNode    right;     // Right child
    boolean    check;
    int        height;    // Height
    // Constructors
    
    AvlNode(ItemSet theElements ){
        this(theElements, null, null );
    }
    
    AvlNode(ItemSet theElements, AvlNode lt, AvlNode rt ){
        element  = (ItemSet) theElements;
        left     = lt;
        right    = rt;
        height   = 0;
        check   = false;
    }
    
    public ItemSet getItemset(){
        return element;
    }
    
    public AvlNode getLeft(){
        return left;
    }

    public AvlNode getRight(){
        return right;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
    

    /** Representacion textual de un nodo AVL
     */
    public String toString() {
        return this.element.toString();
    }
}
