/*
 * Apriori.java
 *
 * Created on 23 de marzo de 2005, 11:50 AM
 *
 * Proyecto Tariy.  prueba para Alejita.
 */

package algorithm.association.Apriori;

import gui.Icons.Association.AssociationIcon;
import gui.KnowledgeFlow.AnimationLabel;
import gui.KnowledgeFlow.Chooser;
import java.util.*;
import Utils.*;

public class Apriori extends Thread{
    /** Soporte con el que se van a evaluar los datos.*/
    public static short support;
    
    /**
     * Vector de arboles AvlTree donde se almacenan los distintos itemsets
     * frecuentes.
     */
    private static Vector Trees;
    
    /** Los datos a minar contenidos en un arbol N-Ario. */
    public static DataSet dataset;
    
    /**
     * Guarda una copia del itemset al momento de realizar sus combinaciones.
     */
    private short[] auxiliar;
    
    /**
     * Arbol AvlTree para guardar la referencia al ultimo conjunto de itemsets
     * frecuentes generados. La primera referencia seran los itemsets frecuentes
     * tipo 1.
     */
    private AvlTree auxTree;
    
    /**
     *
     */
    private AnimationLabel animation;
    private AssociationIcon ai;
    /**
     * Construye una instancia de la clase Apriori.
     *
     *  @param dataset Conjunto de datos contenidos en un arbol N-Ario.
     *  @param support Soporte con el que los datos seran minados.
     */
    public Apriori(DataSet dataset, short support, AssociationIcon ai){
        this.ai = ai;
        this.support = support;
        this.dataset = dataset;
        this.Trees = new Vector(2,1);
        AvlTree frequentsOne = new AvlTree();
        dataset.pruneCandidatesOne(support, frequentsOne);
        Trees.addElement(frequentsOne);
        auxTree = frequentsOne;
    }
    
    /**
     * Determina el número de apariciones de un itemset candidato dentro del
     * dataset.
     *
     * @param candidate El itemset candidato cuyo numero de apariciones va a
     *                  ser contado dentro del dataset.
     */
    public static void increaseSupport(ItemSet candidate){
        Transaction t = new Transaction();
        int i = 1;
        
        dataset.setPointers();
        while(i != 0){
            i = t.loadItemsets(dataset, (AvlTree)Trees.elementAt(0));
            ArrayList articles = t.getArticles();
            Collections.sort(articles);
            short[] items = candidate.getItems();
            Arrays.sort(items);
            if(contains(articles, items)){
                candidate.increaseSupport();
            }
            t.clearTransaction();
        }
    }
    
    /**
     * Retorna el vector de arboles AvlTree donde se almacenan los distintos
     * itemsets frecuentes.
     *
     * @return El vector de arboles AvlTree.
     */
    public Vector getFrequents(){
        return Trees;
    }
    
    /**
     * Representación tipo cadena de los itemsets frecuentes.
     */
    public int showFrequents(){
        int count = 0;
        
        for(int i = 0; i < Trees.size(); i++){
            AvlTree arbol = (AvlTree)Trees.elementAt(i);
            System.out.println("Type " + (i+1));
            arbol.printTree();
            count += arbol.howMany();
        }
        return count;
    }
    
    /**
     * Recorre cada nodo del arbol AvlTree de itemsets frecuentes tipo n para a
     * partir de las combinaciones de sus elementos generar los itemsets
     * candidatos tipo n+1.
     * El nuevo arbol de itemsets frecuentes es añadido al vector que contiene
     * todos los frecuentes.
     *
     * @return  <code>true<code> si el nuevo arbol de itemsets frecuentes no se
     *                           encuentra vacio. O sea existen itemsets
     *                           frecuentes.
     *          <code>false</code> si ya no existen más itemsets frecuentes.
     */
    public boolean makeCandidates(){
        if( auxTree.isEmpty( ) ){
            System.out.println( "Tree Empty" );
            return false;
        } else {
            AvlTree frequents = new AvlTree();
            for(int i = 0; i < auxTree.howMany() - 1; i++){
                auxiliar = null;
                Combinations(auxTree.getRoot(), frequents);
            }
            if(!frequents.isEmpty()){
                auxTree = frequents;
                Trees.addElement(frequents);
                return true;
            }else{
                return false;
            }
        }
    }
    
    /**
     * A partir de cada itemset del último AvlTree de frecuentes generado,
     * construye los itemsets candidatos, evalua su soporte, adicionalmente a
     * partir de los candidatos tipo 3 se realiza el paso de poda. Finalmente
     * los candidatos cuyo soporte sea mayor al soporte minimo se adicionan al
     * final del vector de arboles AvlTree frecuentes.
     *
     * @param t         El nodo del árbol cuyos items serán combinados para
     *                  generar los itemsets cadidatos.
     * @param frequents El nuevo conjunto de itemsets frecuentes tipo n+1.
     */
    private void Combinations(AvlNode t, AvlTree frequents){
        if( t != null ){
            Combinations( t.getLeft(), frequents);
            if(auxiliar == null && !t.isCheck()){
                auxiliar = t.getItemset().getItems();
                t.setCheck(true);
            }
            if(!t.isCheck()){
                if(compareItemset(auxiliar, t.getItemset().getItems())){
                    int type = auxiliar.length;
                    ItemSet candidate = new ItemSet(type + 1);
                    
                    for(int k = 0; k < type; k++){
                        candidate.addItem(auxiliar[k]);
                    }
                    candidate.addItem(t.getItemset().getItems()[type-1]);
                    candidate.setSupport((short)0);
                    
                    if (type > 2){
                        if(this.pruneCandidate(candidate)){
                            this.increaseSupport(candidate);
                            if(candidate.getSupport() >= Apriori.support){
                                frequents.insert(candidate);
                            }
                        }
                    } else {
                        this.increaseSupport(candidate);
                        if(candidate.getSupport() >= Apriori.support){
                            frequents.insert(candidate);
                        }
                    }
                    
                }
            }
            Combinations(t.getRight(), frequents);
        }
    }
    
    /**
     * Compara la igualdad de dos itemsets.
     *
     * @param a Primer itemset a comparar.
     * @param b Segundo itemset a comparar.
     * @return <code>true</code> si los dos itemsets tienen exactamente los
     *                           mismos elementos.
     */
    private boolean compareItemset(short[] a, short[] b){
        for(int i = 0; i < a.length - 1; i++){
            if(a[i] != b[i]){
                return false;
            }
        }
        return true;
    }
    
    /**
     * Determina si ciertas combinaciones de tamaño n de los items del candidato
     * se encuentran presentes dentro del último conjunto de itemsets frecuentes.
     * <pre>
     *     <String>Sea el itemset candidato 1,2,3.
     *     La combinación 2,3 es buscada dentro de los itemsets frecuentes tipo
     *     2.
     * </pre>
     *
     * @param  candidate El itemset candidato cuyos elementos se verificarán que
     *                   pasen la poda.
     * @return <code>true</code> si todas las combinaciones del itemset se
     *                           encuentrán en el último árbol de itemsets
     *                           frecuentes.
     */
    private boolean pruneCandidate(ItemSet candidate){
        int size = candidate.getItems().length;
        ItemSet aux;
        for(int i = 0; i < size - 2; i++){
            aux = new ItemSet(size - 1);
            int k = 0;
            for(int j = 0; j < size; j++){
                if(j != i){
                    aux.addItem(candidate.getItems()[j]);
                }
            }
            if(auxTree.findItemset(aux) == null){
                return false;
            }
        }
        return true;
    }
    
    /**
     * Determina si un itemset candidato se encuentra dentro de una transacción
     * del dataset.
     *
     * @param  articles  Una transacción del dataset.
     * @param  items     Un itemset candidato.
     * @return <code>true</code>  si todos los elementos del itemset candidato
     *                            se encuentran en la transacción;
     *         <code>false</code> en otro caso.
     */
    private static boolean contains(ArrayList articles, short[] items) {
        for(int i = 0; i < items.length; i++){
            if(!articles.contains(items[i])){
                return false;
            }
        }
        return true;
    }
    
    public void setAnimation(AnimationLabel animation){
        this.animation = animation;
    }
    
    public void run() {
        long time = System.currentTimeMillis();
        int count = 0;
        
        while(this.makeCandidates());
        
        long executionTime = System.currentTimeMillis() - time;
        count = this.showFrequents();
        Chooser.setStatus("Apriori: " + count + " large itemsets " +
                "with support " + ai.support + "% " + " in " +
                executionTime + "ms");
        ai.setInfo(count + " large itemsets found\nin " + executionTime + "ms "
                + "with support " + ai.support + "%");
        animation.stop();
    }
}
