/*
 * BaseConditional.java
 *
 */

package algorithm.association.FPGrowth;
import java.util.*;
/**
 *
 * @author Proyecto Taryi
 */
public class BaseConditional{
    private Vector path = new Vector();
    private short support;
    
    public BaseConditional(Vector path, short support) {
        this.path = (Vector) path.clone();
        this.support = support;
    }
    
    public Vector getPath(){
        return this.path;
    }
    
    public short getSoporte(){
        return this.support;
    }
    
    public String toString(){
        return "{ " + path.toString() + " : " + support + "}";
    }
}
