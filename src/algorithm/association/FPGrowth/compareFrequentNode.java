/*
 * Comparer.java
 *
 */

package algorithm.association.FPGrowth;
import java.util.*;

/**
 *
 * @author Proyecto Taryi
 */
public class compareFrequentNode implements Comparator {
    
    public int compare(Object obj1, Object obj2) {
        short i1 = ((FrequentNode)obj1).getSupport();
        short i2 = ((FrequentNode)obj2).getSupport();

        return i2 - i1;
    }
}
