/*
 * FPGrowth.java
 *
 * Proyecto Tariy
 */

package algorithm.association.FPGrowth;

import Utils.*;
import gui.Icons.Association.AssociationIcon;
import gui.KnowledgeFlow.AnimationLabel;
import gui.KnowledgeFlow.Chooser;
import java.util.*;

public class FPGrowth extends Thread{
    /**
     * Al crear el árbol N-Ario del algoritmo FPGrowth, controla si un nuevo
     * nodo se inserta como hermano o si este ya se encontraba en el árbol se
     * incrementa su soporte.
     */
    int sen;
    
    /**
     * Sirve como indice de acuerdo al número de los itemsets frecuentes tipo 1.
     */
    int n;
    
    /**
     * Referencias a Nodos FPGrowth {@link FPGrowthNode}. A traves de estas
     * referencias se construye el árbol N-Ario para el algoritmo FPGrowth.
     */
    FPGrowthNode cab;
    FPGrowthNode nue, aux, auxh, ant, show;
    
    /** Los datos a minar contenidos en un arbol N-Ario. */
    public static DataSet dataset;
    
    /** Soporte con el que se van a evaluar los datos.*/
    private static short support;
    
    /**
     * Estructura que tiene objetos de tipo {@link FrequentNode}, contiene a los
     * itemsets frecuentes tipo 1 y además una referencia al árbol N-Ario del
     * algoritmo FPGrowth.
     */
    private FrequentNode[] frequentsOne;
    
    private AnimationLabel animation;
    private AssociationIcon ai;
    /**
     * Vector de arboles AvlTree donde se almacenan los distintos itemsets
     * frecuentes.
     */
    Vector Trees;
    
    /**
     * Construye una instancia de la clase FPGrowth.
     *
     *  @param dataset Conjunto de datos contenidos en un arbol N-Ario.
     *  @param support Soporte con el que los datos seran minados.
     */
    public FPGrowth(DataSet dataset, short support, AssociationIcon ai) {
        this.ai = ai;
        n = 0;
        cab = new FPGrowthNode();
        nue = new FPGrowthNode();
        aux = new FPGrowthNode();
        auxh= new FPGrowthNode();
        ant = new FPGrowthNode();
        show = new FPGrowthNode();
        this.dataset = dataset;
        this.support = support;
        
        AvlTree frequents = new AvlTree();
        dataset.pruneCandidatesOne(support, frequents);
        frequentsOne = new FrequentNode[frequents.howMany()];
        this.buildFrequentsNodes(frequents.getRoot());
        
        Trees = new Vector(2, 1);
        Trees.addElement(frequents);
    }
    
    /**
     * Retorna el soporte minimo del sistema.
     *
     * @return El soporte minimo del sistema.
     */
    public static short getSupport(){
        return support;
    }
    
    /**
     * Construye una estructura que tiene objetos de tipo {@link FrequentNode},
     * contiene a los itemsets frecuentes tipo 1 y además una referencia al
     * árbol N-Ario del algoritmo FPGrowth.
     *
     * @param t El nodo del árbol Avl donde se encuentran los itemsets
     * frecuentes tipo 1.
     */
    public void buildFrequentsNodes(AvlNode t) {
        if(t != null) {
            buildFrequentsNodes(t.getLeft());
            frequentsOne[n++] = new FrequentNode(t.getItemset().getItems()[0],
                    t.getItemset().getSupport());
            buildFrequentsNodes(t.getRight());
        }
    }
    
    /**
     * Retorna el nodo asociado al item que se encuentra en la estructura de
     * nodos frecuentes {@link FrequentNode}.
     *
     * @param item El item a buscar dentro de la estructura {@link FrequentNode}.
     * @return La referencia al nodo frecuente, si y solo si se encuentra en la
     *         estructura {@link FrequentNode}; <code>null</code> en caso
     *         contrario.
     */
    public FrequentNode findNode(short item) {
        int low = 0;
        int high = frequentsOne.length - 1;
        FrequentNode aux;
        
        while (high >= low) {
            int middle = (low + high) / 2;
            aux = (FrequentNode) frequentsOne[middle];
            if (aux.getItem() == item)
                return aux;
            else if (aux.getItem() > item)
                high = middle-1;
            else
                low = middle+1;
        }
        return null;
    }
    
    /**
     * Construye un árbol N-Ario propio del algoritmo FPGrowth a partir de un
     * dataset previamente construido.
     *
     * @exception NullPointerException
     */
    public void buildTree() throws NullPointerException{
        short d = 0, c = 0;
        /** Variable to control the end of DataSet */
        int x = 0;
        boolean start = true;
        FPGrowthNode newNode;
        FrequentNode ind;
        
        try{
            dataset.setPointers();
            Transaction t = new Transaction();
            aux= null;
            do{
                do{
                    x = t.loadItemsets(frequentsOne);
                    t.sortBySupport();
                    c = 0;
                }while(t.getSize() < 1 && x == 1);
                if(start){
                    // t.getItemset(c++).getItems()[0] devuelve el item de Itemset1
                    // La transaccion t esta formada solo de Itemset frecuentes tipo 1
                    newNode = new FPGrowthNode( (short) t.getItemset(c++).getItems()[0],
                            (short) 1 );
                    ind = this.findNode(newNode.getItem());
                    ind.pcab = newNode;
                    ind.pult = newNode;
                    cab.son = newNode;
                    newNode.fat = null;
                    ant = newNode;
                    start = false;
                }
                while(c<t.getSize()) {
                    d = t.getItemset(c++).getItems()[0];
                    newNode = new FPGrowthNode((short)d, (short)1);
                    ind = this.findNode(newNode.getItem());
                    if(aux == null){
                        ant.son = newNode;
                        newNode.fat = ant;
                        ant = newNode;
                        aux = newNode.son;
                        if( ind.pcab == null){
                            ind.pcab = newNode;
                            ind.pult = newNode;
                        } else {
                            ind.pult.next = newNode;
                            ind.pult = newNode;
                        }
                        
                    } else {
                        if(newNode.getItem() == aux.getItem()){
                            aux.increaseSupport();
                            ant = aux;
                            aux = aux.son;
                        } else {
                            ant = aux;
                            auxh = aux.bro;
                            sen = 0;
                            while(auxh != null){
                                if(auxh.getItem() == newNode.getItem()){
                                    auxh.increaseSupport();
                                    ant = auxh;
                                    sen = 1;
                                    aux = auxh.son;
                                    auxh = null;
                                } else {
                                    ant = auxh;
                                    auxh = auxh.bro;
                                }
                            }
                            if(sen==0){
                                ant.bro = newNode;
                                newNode.fat = ant.fat;
                                ant = newNode;
                                if( ind.pcab == null){
                                    ind.pcab = newNode;
                                    ind.pult = newNode;
                                } else {
                                    ind.pult.next = newNode;
                                    ind.pult = newNode;
                                }
                                aux = null;
                            }
                        }
                    }
                }
                ant = cab.son;
                aux = cab.son;
            }while(x == 1);
        } // end of try
        catch(NullPointerException e2){
            System.out.println(e2.getMessage());
        }
    }
    
    /**
     * Representación tipo cadena en in-order del árbol N-Ario propio del
     * algoritmo FPGrowth.
     *
     * @param stack La pila de referencias al árbol N-Ario que previamente se
     *              debio de haber construido.
     */
    public void showTree(Stack stack) {
        while(aux != null){
            stack.push(new StackNode(aux));
            aux = aux.son;
        }
        show = stack.pop();
        System.out.println("Item: " + show.getItem() + " support: " + show.getSupport());
        if(show.bro == null){
            show = stack.pop();
            System.out.println("Item: " + show.getItem() + " support: " + show.getSupport());
        }
        aux = show.bro;
        if( (stack.cabp != null) || (aux != null) ){
            showTree(stack);
        }
    }
    
    
//    public void goToRoot(){
//        aux = cab.son;
//    }
    
    /**
     * Retorna el vector de arboles AvlTree donde se almacenan los distintos
     * itemsets frecuentes.
     *
     * @return El vector de arboles AvlTree.
     */
    public Vector getFrequents() {
        return Trees;
    }
    
    /**
     * Construye el vector de árboles Avl de itemsets frecuentes a partir de
     * cada rama o path de los nodos frecuentes {@link FrequentNode}.
     *
     * @exception ArrayIndexOutOfBoundsException
     */
    public void buildFrequents() throws ArrayIndexOutOfBoundsException{
        short frequent;
        BaseConditional baseconditional;
        BaseConditionals bcs;
        Vector path = new Vector(1,1);
        
        try{
            aux = cab.son;
            FPGrowthNode pcb, pcab;
            
            Arrays.sort(frequentsOne, new compareFrequentNode());
            
            for(int i = frequentsOne.length - 1; i >= 0; i--){
                FrequentNode aux = (FrequentNode) frequentsOne[i];
                pcb = aux.pcab;
                pcab = aux.pcab;
                frequent = pcb.getItem();
                bcs = new BaseConditionals(frequent, support);
                while(pcab != null){
                    path.clear();
                    pcb = pcb.fat;
                    while(pcb != null){
                        path.add((short) pcb.getItem());
                        pcb = pcb.fat;
                    }
                    if(path.size() != 0){
                        baseconditional = new BaseConditional(path, pcab.getSupport());
                        bcs.addBaseConditionals(baseconditional);
                    }
                    pcab = pcab.next;
                    pcb = pcab;
                }
                bcs.sortByElement();
                Trees = bcs.buildConditionals(Trees);
            }
        } catch(ArrayIndexOutOfBoundsException e) {
            Chooser.setStatus(e.getMessage());
        }
    }
    
    /**
     * Representación tipo cadena de los itemsets frecuentes.
     */
    public int showFrequents(){
        AvlTree arbol;
        int countItemset = 0;
        for(int j = 0; j < Trees.size(); j++){
            arbol = (AvlTree) Trees.elementAt(j);
            System.out.println("Type " + arbol.getTipo());
            if(arbol.isEmpty() != true){
                arbol.printTree();
                countItemset += arbol.howMany();
            }
        }
        return countItemset;
    }
    
    /**
     * Borra todos los arboles contenidos en el vector de itemsets frecuentes.
     */
    public void clearFrequents(){
        this.Trees.clear();
    }
    
    public void run() {
        long time = System.currentTimeMillis();
        int count = 0;
        
        this.buildTree();
        this.buildFrequents();
        
        long executionTime = System.currentTimeMillis() - time;
        count = this.showFrequents();
        Chooser.setStatus("FPGrowth: " + count + " large itemsets " +
                "with support " + ai.support + "% " + " in " +
                executionTime + "ms");
        ai.setInfo(count + " large itemsets found\nin " + executionTime + "ms "
                + "with support " + ai.support + "%");
        animation.stop();
    }
    
    public void setAnimation(AnimationLabel animation){
        this.animation = animation;
    }
}
