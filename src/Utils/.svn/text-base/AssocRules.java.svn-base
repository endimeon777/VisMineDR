/*
 * AssocRules.java
 *
 * Created on 13 de mayo de 2006, 16:25
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * La clase <code>AssocRules</code> es la encargada de recorrer los Ã¡rboles de
 * ItemSets frecuentes, calcular sus combinaciones y generar las reglas.
 *
 * @author Proyecto Tariy
 */
public class AssocRules {
    /** Vector de Ã¡rboles Avl con los ItemSets frecuentes. */
    private Vector frequents;
    
    /** Diccionario de datos con los nombres de los ItemSets frecuentes. */
    private ArrayList dictionary;
    
    /** Confianza con la que se va a evaluar las reglas de asociaciÃ³n. */
    private int confidence;
    
    /** Reglas que clasifican como fuertes. */
    private ArrayList rules;
    
    /**
     * Crea una nueva instancia de la clase AssocRules.
     *
     * @param frequents  Ãrboles Avl de ItemSets frecuentes.
     * @param dictionary Diccionario de datos con los nombres de los ItemSets
     *                   frecuentes.
     * @param confidence Confianza con la que se va a evaluar las reglas de
     *                   asociaciÃ³n.
     */
    public AssocRules(Vector frequents, ArrayList dictionary, int confidence) {
        this.frequents = frequents;
        this.dictionary = dictionary;
        this.confidence = confidence;
        this.rules = new ArrayList();
    }
    
    /**
     * Recorre el Vector de Ã¡rboles de ItemSets frecuentes para entrar a evaluar
     * cada una de las combinaciones y obtener las reglas de asociaciÃ³n.
     */
    public void buildRules() {
        int Fsize = frequents.size();
        AvlTree tree;
        
        for(int i=1; i<Fsize; i++) {
            tree = (AvlTree) frequents.elementAt(i);
            walkTree(tree.getRoot());
        }
    }
    
//    public void buildRules2() {
//        int Fsize = frequents.size();
//        int sizeTree;
//        AvlTree tree;
//        AvlNode node;
//
//        for(int i=1; i<Fsize; i++) {
//            tree = (AvlTree) frequents.elementAt(i);
//            sizeTree = tree.howMany();
//            for(int j=0; j<sizeTree; j++) {
//                node = tree.walkTree();
//
//                int size = node.getItemset().getItems().length;
//                Vector items = new Vector(size);
//                Vector post = new Vector(size);
//                for(int k=0; i<size; k++) {
//                    items.addElement( node.getItemset().getItems()[k] );
//                }
//                Combinations(items, size-1, post, node.getItemset().getSupport());
//            }
//        }
//    }
    
    /**
     * MÃ©todo interno para recorrer un Ã¡rbol Avl de ItemSets frecuentes.
     *
     * @param node Un nodo del Ã¡rbol Avl de ItemSets frecuentes.
     */
    private void walkTree(AvlNode node) {
        if(node != null) {
            walkTree(node.getLeft());
            
            int size = node.getItemset().getItems().length;
            Vector items = new Vector(size);
            Vector post = new Vector(size);
            for(int i=0; i<size; i++) {
                items.addElement( node.getItemset().getItems()[i] );
            }
            Combinations(items, size-1, post, node.getItemset().getSupport());
            
            walkTree(node.getRight());
        }
    }
    
    /**
     * MÃ©todo interno que calcula todas las combinaciones posibles de un ItemSet
     * a partir de sus elementos, construye la regla de asociaciÃ³n y evalua su
     * confianza para determinar que reglas son fuertes.
     *
     * @param items  Los elementos del ItemSet con los cuales se van a construir
     *               las reglas.
     * @param con    Vector que contiene los elementos del consecuente de la
     *               regla.
     * @param supFre Soporte de todos los elementos del ItemSet.
     */
    private void Combinations(Vector items, int x, Vector con, float supFre){
        if(items.size() > 1) {
            for(int i = x; i >= 0; i--) {
                Vector aux = (Vector) items.clone();
                Vector post = (Vector) con.clone();
                aux.remove(i);
                post.addElement(items.elementAt(i));
                Combinations(aux, i - 1, post, supFre);
                post.remove(post.size()-1);
            }
        }
        // Si existe consecuente...
        if(con.size() > 0) {
            // Instancio un ItemSet a partir de los elementos del antecendente.
            ItemSet ant = new ItemSet(items, (short) 0);
            // Nos ubicamos en el Ã¡rbol de frecuentes del tipo correspondiente
            // al del ItemSet antecedente.
            AvlTree prevTree = (AvlTree) frequents.elementAt(ant.getType()-1);
            // Buscamos el antecedente en el Ã¡rbol de frecuentes.
            ant = prevTree.findItemset(ant);
            // Y obtenemos su soporte.
            float supAnt = ant.getSupport();
            // Calculamos la confianza de la regla.
            float conf = (supFre / supAnt) * 100;
            if(conf >= this.confidence) {
                // Decodificamos los items de la regla. Obtenemos los nombres
                // de los datos a partir del diccionario.
                decodeFrequents(items, con, conf);
            }
        }
    }
    
    /**
     * MÃ©todo interno para decodificar los nombres de los items de la regla.
     *
     * @param ant  Items del antecedente.
     * @param con  Items del consecuente.
     * @param conf Confianza de la regla.
     */
    private void decodeFrequents(Vector ant, Vector con, float conf) {
        String antecedent = "", concecuent = "";
        int size;
        short element;
        
        // Decodificamos los items del antecedente.
        size = ant.size();
        for(int i=0; i<size; i++) {
            element = (Short) ant.elementAt(i);
            antecedent = antecedent + (String) dictionary.get(element) + " ~ ";
        }
        antecedent = antecedent.substring(0, antecedent.length() - 3);
        
        // Decodificamos los items del consecuente.
        size = con.size();
        for(int i=0; i<size; i++) {
            element = (Short) con.elementAt(i);
            concecuent = concecuent + (String) dictionary.get(element) + " ~ ";
        }
        
        // Instanciamos una nueva regla en el Vector de las reglas.
        concecuent = concecuent.substring(0, concecuent.length() - 3);
        rules.add( new Rules(antecedent, concecuent, conf) );
    }
    
    /**
     * Retorna el Vector que contiene las reglas.
     */
    public ArrayList getRules() {
        //Collections.sort(rules, new byConfidence());
        return rules;
    }
    
    /**
     * RepresentaciÃ³n textual de las reglas ordenadas descendentemente por
     * soporte.
     */
    public void showRules() {
        Rules ru = null;
        
        int size = rules.size();
        Collections.sort(rules, new byConfidence());
        for(int i=0; i<size; i++) {
            ru = (Rules) rules.get(i);
            System.out.print("If " + ru.getAntecedent() + " Then " + ru.getConcecuent()
            + " (" + ru.getConfidence() + " %)" + "\n");
        }
    }
    
    /**
     * RepresentaciÃ³n textual de las reglas ordenadas descendentemente por
     * soporte.
     */
    public String toString() {
        String ruls = new String("");
        int size = this.rules.size();
        
        Collections.sort(rules, new byConfidence());
        for(int i=0; i<size; i++) {
            ruls = ruls + (i+1) + ". " + ((Rules) rules.get(i)).toString() + "\n";
        }
        return ruls;
    }
    
    public ArrayList sortByConfidence() {
        Collections.sort(rules, new byConfidence());
        return rules;
    }
    
    /**
     * Desplaza las reglas desde i o 0 hasta j. Al final el elemento i se mueve
     * a la posiciÃ³n 0.
     */
    private void insert(int i, int j) {
        Rules aux = (Rules) rules.get(i);
        for (int k=i; k>j; k--) {
            rules.set(k, rules.get(k-1));
        }
        rules.set(j, aux);
    }
    
    /**
     * Compara dos reglas de acuerdo al parametro que indique el valor de <code>
     * op</code>. Si <code>op</code> es 0 compara los concecuentes de las reglas.
     * Y si <code>op</code> es 1 compara las confianzas de reglas similares.
     *
     * @return En el caso de que <code>op</code> valga 0 <code>true</code> si el
     *         concecuente de r1 es mayor que el de r2 o cuando son iguales.
     *         <code>false</code> si el concecuente de r1 es menor que el de r2.
     *         En el caso de que <code>op</code> valga 1 <code>true</code> si la
     *         confianza de r1 es mayor que la de r2 o cuando son iguales.
     *         <code>false</code> si la confianza de r1 es menor que la de r2.
     * @param i  La posiciÃ³n de la primera regla.
     * @param j  La posiciÃ³n de la segunda regla.
     * @param op El indicador que determina el parametro a comparar de una regla.
     */
    private boolean moi(int i, int j, int op) {
        if (op == 0) {
            if (j >= rules.size()) {
                return true;
            }
            
            Rules r1 = (Rules) rules.get(i);
            Rules r2 = (Rules) rules.get(j);
            int ant1 = (new StringTokenizer(r1.getAntecedent(), "~")).countTokens();
            int con1 = (new StringTokenizer(r1.getConcecuent(), "~")).countTokens();
            int ant2 = (new StringTokenizer(r2.getAntecedent(), "~")).countTokens();
            int con2 = (new StringTokenizer(r2.getConcecuent(), "~")).countTokens();
            
            // Compara 2 reglas si el concecuente de la primera es mayor que el de
            // la segunda retorne true no haga nada. Pero si es menor que el
            // concecuente de la segunda y el antecedente de la segunda es menor o
            // igual al de la primera regla, retorne false, debe hacerse un cambio.
            if( con1>con2 ) {
                return true;
            } else if( (con2>con1 && ant2<=ant1) || (con2==con1 && ant2<=ant1) ) {
                return false;
            } else {
                return true;
            }
        } else if (op == 1) {
            if (j >= rules.size()) {
                return true;
            }
            
            Rules r1 = (Rules) rules.get(i);
            Rules r2 = (Rules) rules.get(j);
            int ant1 = (new StringTokenizer(r1.getAntecedent(), "~")).countTokens();
            int con1 = (new StringTokenizer(r1.getConcecuent(), "~")).countTokens();
            int ant2 = (new StringTokenizer(r2.getAntecedent(), "~")).countTokens();
            int con2 = (new StringTokenizer(r2.getConcecuent(), "~")).countTokens();
            
            // Compara 2 reglas de acuerdo a su confianza unicamente cuando dos
            // reglas tienen el mismo tamaÃ±o, es decir cuando sus concecuentes
            // y antecedentes tienen el mismo tamaÃ±o respectivamente. Si la
            // confianza de la primera es mayor que la de la segunda retorne true
            // no haga nada. Pero si es menor que la confianza de la segunda
            // retorne false, debe hacerse un cambio.
            if( con1!=con2 || ant1!=ant2 ) {
                return true;
            } else if( con1==con2 && ant2==ant1 ) {
                if( r1.getConfidence() > r2.getConfidence() ) {
                    return true;
                } else if( r2.getConfidence() > r1.getConfidence() ) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return true;
    }
    
    /**
     * Metodo de ordenamiento binario. Ordena las reglas de mayor a menor de
     * acuerdo al parÃ¡metro <code>op</code>. Si <code>op</code> es 0 ordena
     * las reglas de acuerdo al tamaÃ±o de su concecuente. Y si <code>op</code>
     * es 1 las ordena de acuerdo a la confianza de reglas similares.
     *
     * @param op Determina el parametro por el que se deben ordenar las reglas.
     */
    public void sortByGoldStone(int op) {
        // mÃ©todo de ordenamiento binario.
        int klo=0, khi=0, k=0, j=1;
        int size = rules.size();
        while (true) {
            if (j >= size) break;
            // Hay cambio en los elementos, el segundo (j) es menor que el
            // primero (j-1).
            if (!moi(j-1, j, op)) {
                // Elemento j menor que el elemento 0. Entonces insertar j en 0.
                if (!moi(0, j, op)) {
                    insert(j, 0);
                    // Si no buscar la posiciÃ³n donde haya que insertar a j.
                } else {
                    // klo lower, khi higher
                    klo=0; khi=j-1;
                    while (true) {
                        // Hasta comprobar todas las posiciones o sea cuando
                        // lower alcanza a higher.
                        if (klo > khi) {
                            k = klo; break;
                        }
                        // Calculo la mitad entre lower y higher.
                        k = (klo+khi)/2;
                        // Si el elemento en la mitad es menor que el que intento
                        // ubicar, el nuevo lower es la mitad+1, higher es el
                        // mismo.
                        if (moi(k, j, op)) {
                            klo = k+1;
                            // Si no el elemento que intento ubicar va en lower.
                        } else {
                            khi = k-1;
                        }
                    }
                    // Inserto el elemento j en la posiciÃ³n lower o k.
                    insert(j, k);
                }
            }
            j++;
        }
    }
}
