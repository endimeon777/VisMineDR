/*
 * Stack.java
 */

package algorithm.association.FPGrowth;

/**
 *
 * @author Proyecto Tariy
 */
class StackNode {
    FPGrowthNode FPNode;
    StackNode sigp;
    
    StackNode(FPGrowthNode node) {
        this.FPNode = node;
        sigp = null;
    }
}

public class Stack {
    StackNode cabp;
    
    public Stack() {
        cabp = null;
    }
    
    public void push(StackNode FPNode) {
        if( cabp==null ) {
            cabp = FPNode;
            cabp.sigp = null;
        } else {
            FPNode.sigp = cabp;
            cabp = FPNode;
        }
    }
    
    public FPGrowthNode pop() {
        StackNode nuep;
        nuep = cabp;
        cabp = cabp.sigp;
        return nuep.FPNode;
    }
}
