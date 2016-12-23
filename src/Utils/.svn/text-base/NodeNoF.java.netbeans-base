/*
 * NodeNoF.java
 *
 * Created on January 28, 2006, 4:01 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package Utils;

import java.io.Serializable;

/**
 *
 * @author Tariy
 */

public class NodeNoF {
    
    public short item;
    public NodeNoF dad, son, bro;
    
    /** Creates a new instance of Node */
    public NodeNoF(short d) {
        this.item = d;
        this.son = null;
        this.dad = null;
        this.bro = null;
    }
    
    public NodeNoF() {
        this.item = -5;
        this.son = null;
        this.dad = null;
        this.bro = null;
    }
    
    public NodeNoF addSon(short arrived, int type) {
        NodeNoF node = null;
        if(type == -1 || type == -2){
            node = new NodeF(arrived);
        } else{
            node = new NodeNoF(arrived);          
        }
        this.son = node;
        node.dad = this;
        node.bro = null;
        return node;
    }
    
    public NodeNoF addSon(short arrived, short frec) {
        NodeF node = new NodeF(arrived);
        node.setFrec(frec);
        this.son = node;
        node.dad = this;
        node.bro = null;
        return node;
    }
    
    public NodeNoF addBro(short arrived, int type) {
        NodeNoF node = null;
        if(type == -1 || type == -2){
            node = new NodeF(arrived);
        } else{
            node = new NodeNoF(arrived);
        }
        this.bro = node;
        node.dad = this.dad;
        node.bro = null;
        node.son = null;
        return this.bro;
    }
    
    public NodeNoF addBro(short arrived, short frec) {
        NodeF node = new NodeF(arrived);
        node.setFrec(frec);
        this.bro = node;
        node.dad = this.dad;
        node.bro = null;
        node.son = null;
        return this.bro;
    }
    
    public NodeNoF findBro(short n, int type) {
        NodeNoF aux = this.son;
        NodeNoF ret = null;
        NodeF nf;
        while(aux != null) {
            if(aux.getItem() == n) {
                if(/*aux.getClass().getSimpleName().compareTo("NodeF") == 0*/
                        aux instanceof NodeF && 
                        (type == -1 || type == -2)) {
                    nf = (NodeF) aux;
                    nf.incFrec();
                    return aux;
                } else if(type == -1 || type == -2) {
                    nf = new NodeF(n);
                    aux.updateTree(nf);
                    return nf;
                }
                return aux;
            }
            ret = aux;
            aux = aux.bro;
        }
        return ret;
    }
    
    public void updateTree(NodeF leaf) {
        leaf.dad = this.dad;
        leaf.son = this.son;
        leaf.bro = this.bro;
        if(this.dad.son == this) {
            this.dad.son = leaf;
            NodeNoF aux = this.son;
            while(aux != null) {
                aux.dad = leaf;
                aux = aux.bro;
            }
        } else {
            NodeNoF aux = this.dad.son;
            while(true) {
                if(aux.bro == this) {
                    aux.bro = leaf;
                    break;
                }
                aux = aux.bro;
            }
            aux = this.son;
            while(aux != null) {
                aux.dad = leaf;
                aux = aux.bro;
            }
        }
    }
    
    public short getItem() {
        return this.item;
    }
}
