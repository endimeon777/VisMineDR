/*
 * TreeCounter.java
 *
 * Created on 22 de junio de 2006, 11:24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package algorithm.classification.c45_1;

import gui.Icons.Clasification.ClasificationIcon;
import gui.Icons.Tree.ViewerAllTrees;
import gui.KnowledgeFlow.AnimationLabel;
import gui.KnowledgeFlow.Chooser;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author and
 */
public class TreeCounter extends Thread{
    int rows;
    int columns;
    static final double LOG2 = Math.log(2);
    public Attribute root;
    Attribute aux;
    AbstractTableModel dataIn;
    public int count;
    private int t;
    public String theClass;
    private AnimationLabel animation = null;
    public int MIN_ROWS;
    public double THRESHOLD;
    public TariyTableModel data = null;
    public JPanel TreePanel;
    private Attribute backer;
    private int totalNodes;
    public ClasificationIcon ci;
    public String textTree = "";
    
    /** Creates a new instance of TreeCounter */
    public TreeCounter() {
        root = new Attribute("I'm the root", null, null);
        totalNodes = 0;
    }
    
    public TreeCounter(TariyTableModel data, int min_rows, double threshold) {
        this.data = data;
        this.MIN_ROWS = min_rows;
        this.THRESHOLD = threshold;
        theClass = data.getColumnName(data.getColumnCount() - 1);
        root = new Attribute(theClass, null, null);
        root.frecuence = data.getRowCount();
        root.valuesClass = data.getAttributeDistribution(data.getColumnCount() - 1);
        totalNodes = 0;
    }
    
    public TreeCounter(TariyTableModel data, int min_rows, double threshold, ClasificationIcon ci) {
        this.ci = ci;
        this.data = data;
        this.MIN_ROWS = min_rows;
        this.THRESHOLD = threshold;
        theClass = data.getColumnName(data.getColumnCount() - 1);
        root = new Attribute(theClass, null, null);
        root.frecuence = data.getRowCount();
        root.valuesClass = data.getAttributeDistribution(data.getColumnCount() - 1);
        totalNodes = 0;
    }
    
    public int getTotalNodes() {
        return totalNodes;
    }
    
    public void setAnimation(AnimationLabel animation){
        this.animation = animation;
    }
    
    public void setData(AbstractTableModel dataIn){
        this.dataIn = dataIn;
        rows = dataIn.getRowCount();
        columns = dataIn.getColumnCount();
        root = new Attribute("", null, null);
    }
    
    public void countTree(int column){
        Attribute auxValue;
        Attribute oldValue = null;
        Attribute auxTarjet;
        String value;
        String tarjet;
        boolean flag;
        
        root.son = null;
        root.frecuence = column;
        root.name = dataIn.getColumnName(column);
        for(int r = 0; r < rows ; r++ ) {
            Class thisClass = dataIn.getColumnClass(column);
            if(!thisClass.getCanonicalName().equals("String")){
                value = (String)dataIn.getValueAt(r, column).toString();
            } else {
                value = (String)dataIn.getValueAt(r, column);
            }
            if(!thisClass.getCanonicalName().equals("String")){
                tarjet = (String)dataIn.getValueAt(r, columns - 1).toString();
            } else {
                tarjet = (String)dataIn.getValueAt(r, columns - 1);
            }
            if(root.son == null){
                root.son = new Attribute(value, null, null);
                root.son.son = new Attribute(tarjet, null, null);
            } else {
                auxValue = root.son;
                while(auxValue != null){
                    flag = true;
                    if(auxValue.name.equals(value)){
                        auxValue.incrementFrecuence();
                        auxTarjet = auxValue.son;
                        while(auxTarjet != null){
                            if(auxTarjet.name.equals(tarjet)){
                                auxTarjet.incrementFrecuence();
                                flag = false;
                                break;
                            }
                            auxTarjet = auxTarjet.brother;
                        }
                        if(auxTarjet == null){
                            auxTarjet = new Attribute(tarjet, null, null);
                            auxTarjet.brother = auxValue.son;
                            auxValue.son = auxTarjet;
                            flag = false;
                        }
                    }
                    if(!flag){
                        break;
                    }
                    oldValue = auxValue;
                    auxValue = auxValue.brother;
                }
                if(auxValue == null){
                    auxValue = new Attribute(value, null, null);
                    auxValue.son = new Attribute(tarjet, null, null);
                    oldValue.brother = auxValue;
                }
            }
        }
    }
    
    public double getEntropy(double frecuence){
        Attribute attribute;
        Attribute tarjet;
        double entropy = 0.0;
        
        attribute = root.son;
        while(attribute != null){
            entropy += ((double)(attribute.frecuence)/frecuence)*attribute.setEntropia();
            attribute = attribute.brother;
        }
        return entropy * (-1.0);
    }
    
    public void seeTree(){
        seeTree(root.son);
    }
    
    public void seeTree(Attribute auxiliar){
//        tabs(t);
//        System.out.println(auxiliar);
        textTree =  textTree + "\n" + tabu(t);
        textTree = textTree + auxiliar;
        
        if(auxiliar.son != null){
            t++;
            seeTree(auxiliar.son);
            t--;
        }
        if(auxiliar.brother != null){
            seeTree(auxiliar.brother);
        }
    }
    
    public String getTextTree(Attribute auxiliar){
        seeTree(auxiliar.son);
        return textTree;
    }
    
//    private void tabs(int t){
//        for(int i = 0; i < t; i++){
//            System.out.print("|  ");
//        }
//    }
    
    private String tabu(int t){
        String tab = "";
        for(int i = 0; i < t; i++){
            tab = tab + "|        ";
        }
        return tab;
    }
    
    public StringBuffer seeLeafs(Attribute root){
        StringBuffer orderLeafs = new StringBuffer();
        ArrayList leafs = new ArrayList();
        LinkedList path = new LinkedList();
        seeLeafs(root.son, path, leafs);
        Collections.sort(leafs, new compareConfidence());
        Iterator it = leafs.iterator();
        int order = 1;
        while(it.hasNext()){
            Leaf oneLeaf = (Leaf)it.next();
            orderLeafs.append(order + ") " + oneLeaf + "\n");
            order++;
        }
        return orderLeafs;
    }
    
    private void seeLeafs(Attribute auxiliar, LinkedList path, ArrayList leafs){
        if(auxiliar.son != null){
            path.add(auxiliar);
            seeLeafs(auxiliar.son, path, leafs);
            path.removeLast();
        } else {
            leafs.add(new Leaf(auxiliar, path));
        }
        if(auxiliar.brother != null){
            seeLeafs(auxiliar.brother, path, leafs);
        }
    }
    
    private double log2(double value){
        if(value == 0.0) return 0.0;
        return Math.log(value)/LOG2;
    }
    
    public void pruneLeafs(){
        Attribute auxiliar = this.root;
        Stack stack = new Stack();
        crossTree(auxiliar, stack);
        totalNodes = 0;
        while(!stack.isEmpty()){
            pruneSameBranch((Attribute)stack.pop());
        }
    }
    
    public void crossTree(Attribute auxiliar, Stack stack){
        if(auxiliar.brother != null){
            crossTree(auxiliar.brother, stack);
        }
        if(auxiliar.son.isLeaf() != null){
            stack.push(auxiliar);
            crossTree(auxiliar.son, stack);
        }
    }
    
    public void pruneSameBranch(Attribute node) {
        if(node.son.son.isLeaf() != null){
            totalNodes++;
            return;
        }
        String grandSon = node.son.son.name;
        int frecuences = node.son.son.frecuence;
        int frecuencesFather = node.son.son.frecuenceFather;
        Attribute childrens = node.son.brother;
        boolean isEquals = true;
        while(childrens != null){
            if(!childrens.son.name.equals(grandSon)){
                isEquals = false;
                break;
            } else {
                frecuences += childrens.son.frecuence;
                frecuencesFather += childrens.son.frecuenceFather;
            }
            childrens = childrens.brother;
        }
        if(isEquals){
            node.son.frecuence = frecuences;
            node.son.frecuenceFather = frecuencesFather;
            node.son.name = node.son.son.name;
            node.son.valuesClass = node.valuesClass;
            node.son.son = null;
            node.son.brother = null;
        }
    }
    
    public StringBuffer getStringTree(){
        StringBuffer tree = new StringBuffer();
        
        return tree;
    }
    public Attribute searchTreeDesc(Attribute node, int aux){
        if(node.id == aux){
            backer = node;
            return backer;
        }
        if(node.son != null){
            searchTreeDesc(node.son,aux);
        }
        if(node.brother != null){
            searchTreeDesc(node.brother,aux);
        }
        return backer;
    }
    
    public Attribute chooseBestAttribute(AbstractTableModel data){
        TreeCounter finalTree = null;
        int treeFinal = 0;
        int columns = data.getColumnCount();
        double minEntropy = Double.MAX_VALUE;
        double entropy;
        LinkedList trees = new LinkedList();
        
        for(int i = 0; i < columns - 1; i++){
            TreeCounter c = new TreeCounter();
            c.setData(data);
            c.countTree(i);
            entropy = c.getEntropy(c.rows);
            if(entropy < minEntropy){
                minEntropy = entropy;
                finalTree = c;
            }
        }
        return finalTree.root;
    }
    
    public Attribute decisionTree(){
        Attribute attribute = new Attribute("", null, null);
        attribute.entropia = 1.0;
        this.root.son = decisionTree(data, attribute);
        return this.root;
    }
    
    private Attribute decisionTree(TariyTableModel data, Attribute byDefault){
        if(byDefault.entropia == 0.0){
            byDefault.son.name = theClass + "=" + byDefault.son.name;
            byDefault.son.frecuenceFather = byDefault.son.frecuence;
            byDefault.son.id = totalNodes++;
            byDefault.son.valuesClass = byDefault.valuesClass;
            return byDefault.son;
        } else if(data.getColumnCount() == 1){
            return byDefault.bestChild(theClass, totalNodes++);
        } else if(data.getRowCount() < MIN_ROWS){
            return byDefault.bestChild(theClass, totalNodes++);
        } else if(byDefault.hasOneChildOverThreshold(THRESHOLD) ){
            return byDefault.bestChild(theClass, totalNodes++);
        } else {
            Attribute attribute = null;
            attribute = this.chooseBestAttribute(data);
            LinkedList values = data.splitData(attribute);
            String[] names = data.newNames(attribute.name);
            Iterator it = values.iterator();
            
            Attribute auxiliar = attribute.son;
            while(it.hasNext()){
                auxiliar.name = attribute.name + "=" + auxiliar.name;
                auxiliar.id = totalNodes++;
                Object[][] newData = ((Split)it.next()).data;
                data.setDatos(newData);
                data.setNomcol(names);
                auxiliar.setValuesClass();
                auxiliar.son = decisionTree(data, auxiliar);
                auxiliar = auxiliar.brother;
            }
            
            return attribute.son;
        }
    }
    
    public void run(){
        long time = System.currentTimeMillis();
        this.decisionTree();
        this.pruneLeafs();
        long executionTime = System.currentTimeMillis() - time;
        System.out.println("decisionTree : " + executionTime + "ms ");
        ci.setInfo("Builted Model in " + executionTime + "ms");
        Chooser.setStatus("C4.5: Builted Model in " + executionTime + "ms");
        ci.root = root;
        animation.stop();
    }
    
    static public void main(String arg[]){
        TreeCounter c = new TreeCounter(new TariyTableModel(), 0, 100.0);
        long time = System.currentTimeMillis();
        Attribute root = c.decisionTree();
        long executionTime = System.currentTimeMillis() - time;
        c.pruneLeafs();
        System.out.println("decisionTree : " + executionTime + "ms ");
        new ViewerAllTrees(root).setVisible(true);
    }
}
