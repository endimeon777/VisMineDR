/*
 * NodeF.java
 *
 * Created on January 28, 2006, 4:08 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package Utils;

/**
 *
 * @author Tariy
 */

public class NodeF extends NodeNoF {
    
    private short frec;
    NodeF next;
    
    /** Creates a new instance of Leaf */
    public NodeF(short item) {
        super(item);
        this.frec = 1;
        this.next = null;
    }
    
    public short getSupport() {
        return this.frec;
    }
    
    public short getItemF() {
        return this.getItem();
    }
    
    public String getPath() {
        NodeNoF aux = this;
        String path = new String();
        path = "[";
        while(aux.dad != null) {
            path = path + aux.getItem() + ",";
            aux = aux.dad;
        }
        path = path.substring(0, path.length()-1);
        path = path + "] +" + this.getSupport();
        return path;
    }
    
    public void incFrec() {
        this.frec += 1;
    }
    
    public void setFrec(short frec) {
        this.frec = frec;
    }
}
