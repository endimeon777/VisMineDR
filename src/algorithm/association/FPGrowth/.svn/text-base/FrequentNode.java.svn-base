/*
 * FrequentNode.java
 *
 * Created on 8 de febrero de 2005, 08:56 AM
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
