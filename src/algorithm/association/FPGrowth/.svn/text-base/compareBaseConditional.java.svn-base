/*
 * compareBaseConditional.java
 *
 * Created on 14 de febrero de 2005, 10:47 AM
 */

package algorithm.association.FPGrowth;
import java.util.*;
/**
 *
 * @author Proyecto Taryi
 */
public class compareBaseConditional implements Comparator{
    
    /**
     * Creates a new instance of compareBaseConditional
     */
    public int compare(Object o1, Object o2){
        BaseConditional a = (BaseConditional) o1;
        BaseConditional b = (BaseConditional) o2;
        short l1 = (Short) a.getPath().lastElement();
        short l2 = (Short) b.getPath().lastElement();
        return l1 - l2;
    }
}
