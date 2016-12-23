/*
 * Leaf.java
 *
 * Created on 26 de diciembre de 2006, 11:59
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package algorithm.classification.c45_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author and
 */
public class Leaf {
    private String path;
    private Attribute leaf;
    private int sizeRule;
    /** Creates a new instance of Leaf */
    public Leaf(Attribute leaf, LinkedList path) {
        this.leaf = leaf;
        this.sizeRule = path.size();
        this.path = this.buildStringPath(path);
    }

    private String buildStringPath(LinkedList path){
        StringBuffer str = new StringBuffer();
        int size = path.size();
        
        for(int i = 0; i < size; i++){
            if(i != size - 1){
                str.append(path.get(i) + " and ");
            } else {
                str.append(path.get(i));
            }
        }
        return str.toString();
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setLeaf(Attribute leaf) {
        this.leaf = leaf;
    }

    public String getPath() {
        return path;
    }

    public Attribute getLeaf() {
        return leaf;
    }

    public void setSizeRule(int sizeRule) {
        this.sizeRule = sizeRule;
    }

    public int getSizeRule() {
        return sizeRule;
    }

    public String toString() {
        return path + " --> " + leaf;
    }
}
