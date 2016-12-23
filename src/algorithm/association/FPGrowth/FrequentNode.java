/*
 * FrequentNode.java
 *
 */

package algorithm.association.FPGrowth;

import Utils.ItemSet;

/**
 *
 * @author Proyecto Taryi
 */
public class FrequentNode extends ItemSet{
    FPGrowthNode pcab;
    FPGrowthNode pult;
    
    public FrequentNode(short i, short s) {
        super(1);
        super.addItem(i);
        super.setSupport(s);
        pcab = null;
        pult = null;
    }
    
    public short getItem(){
        return super.getItems()[0];
    }
}
