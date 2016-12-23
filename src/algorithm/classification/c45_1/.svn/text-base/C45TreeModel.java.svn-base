/*
 * TariyNTreeModel.java
 *
 * Created on April 12, 2006, 2:51 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package algorithm.classification.c45_1;

import java.util.Vector;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author Tariy
 */

/*Clase que implementa la interfaz TreeModel y sus metodos 
 *
 */
public class C45TreeModel implements TreeModel {
    private Vector treeModelListeners = new Vector();
    private Attribute root;
    
    /** Creates a new instance of TariyNTreeModel */
    public C45TreeModel(Attribute root) {
        this.root = root;
    }
    
    // Implementacion de los metodos de la interfaz TreeModel  
    // aqui utilizamos los metodos que escribimos en la clase Attribute
    
    public void addTreeModelListener(TreeModelListener l) {
        treeModelListeners.addElement(l);
    }
    
    public Object getChild(Object parent, int index) {
        Attribute n = (Attribute) parent;
        return n.getChild(index);
    }
    
    public int getChildCount(Object parent) {
        Attribute n = (Attribute) parent;
        return n.getChildCount();
    }
    
    public int getIndexOfChild(Object parent, Object child) {
        Attribute n = (Attribute) parent;
        return n.getIndexOfChild(child);
    }
    
    public Object getRoot() {
        return this.root;
    }
    
    public boolean isLeaf(Object Attribute) {
        Attribute n = (Attribute) Attribute;
        return n.isLeaf() == null;
    }
    
    public void removeTreeModelListener(TreeModelListener l) {
        treeModelListeners.removeElement(l);
    }
    
    public void valueForPathChanged(TreePath path, Object newValue) {
//        System.out.println("*** valueForPathChanged : "
//                           + path + " --> " + newValue);
    }
}
