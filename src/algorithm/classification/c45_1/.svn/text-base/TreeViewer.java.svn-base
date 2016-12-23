/*
 * TreeViewer.java
 *
 * Created on 26 de diciembre de 2006, 9:55
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package algorithm.classification.c45_1;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author and
 */
public class TreeViewer extends JTree{
    Attribute root;
    int t = 0;
//Esta es la clase del arbol final que ya extiende a JTree
    public TreeViewer(Attribute root){
        //y con este declaro una instancia de C45TreeModel y se la mando al contructor
        //superior de JTree con la instruccion super
        super(new C45TreeModel(root));
        this.root = root;
        //estas lineas son necesarias para setear las propiedades iniciales del JTree
        getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        Icon personIcon = null;
        Icon leafIcon = new ImageIcon(getClass().getResource("/images/leaf.gif"));
        renderer.setLeafIcon(leafIcon);
        renderer.setClosedIcon(personIcon);
        renderer.setOpenIcon(personIcon);
        setCellRenderer(renderer);
    }
    
    public void seeTree(Attribute auxiliar){
        tabs(t);
        System.out.println(auxiliar.name);
        if(auxiliar.son != null){
            t++;
            seeTree(auxiliar.son);
            t--;
        }
        if(auxiliar.brother != null){
            seeTree(auxiliar.brother);
        }
    }
    
    private void tabs(int t){
        for(int i = 0; i < t; i++){
            System.out.print("|  ");
        }
    }
}



