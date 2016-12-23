/*
 * DataSet.java
 *
 * Created on January 28, 2006, 3:51 PM
 *
 * Proyecto Tariy
 */

package Utils;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * La clase <code>DataSet</code> es la estructura que los algoritmos de
 * asociaciÃ³n utilizan para almacenar los datos de tal forma que ocupan menos
 * espacio en memoria. La estructura que utiliza <code>DataSet</code> es un
 * Ã¡rbol N-Ario con los datos a minar codificados como datos tipo short, en esta
 * estructura se aprovecha en una sola rama los items de distintas transacciones
 * con datos repetidos controlando su nÃºmero de apariciones o soporte.
 *
 * @author Proyecto Tariy
 */
public class DataSet {
    /** El nombre del DataSet. */
    private String name;
    
    /** La raiz del DataSet. */
    protected  NodeNoF root;
    
    /** El Ãºltimo nodo insertado en el DataSet. */
    protected  NodeNoF current;
    
    /**
     * La raÃ­z de una lista que referencia a todos los nodos del DataSet que
     * tienen soporte.
     */
    public NodeF lroot;
    
    /** El Ãºltimo nodo del DataSet referenciado en la lista. */
    public NodeF lcurrent;
    
    /** Un nuevo nodo en la lista. */
    public NodeNoF lnode;
    
    /** */
    public short frecuency;
    
    /** Ãrbol Avl que contiene los itemsets candidatos tipo 1. */
    private AvlTree candidatesOne;
    
    /** El nÃºmero de transacciones en el DataSet. */
    protected int ntransactions;
    
    /** Diccionario de los datos con su respectiva codificaciÃ³n. */
    private ArrayList dictionary;
    
    /** Lista de los nombres de los atributos del conjunto de datos. */
    private ArrayList attrNames;
    
    /**
     * Crea una nueva instancia de la clase DataSet.
     *
     * @param n El nombre del DataSet.
     */
    public DataSet(String n) {
        this.name = n;
        this.root = new NodeNoF();
        this.current = root;
        this.lroot = null;
        this.candidatesOne = new AvlTree();
        this.ntransactions = 0;
        this.attrNames = new ArrayList();
    }
    
    public DataSet() {
        this.name = "";
        this.root = new NodeNoF();
        this.current = root;
        this.lroot = null;
        //this.candidatesOne = new AvlTree();
        this.ntransactions = 0;
        this.attrNames = new ArrayList();
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Retorna el diccionario de datos con su codificaciÃ³n dentro del DataSet.
     */
    public ArrayList getDictionary() {
        return dictionary;
    }
    
    public void setDictionary(ArrayList dictionary) {
        this.dictionary = dictionary;
    }
    
    public void buildMultiValuedDictionary(ArrayList array){
        dictionary = array;
        Collections.sort(dictionary);
    }
    
    /**
     * Construye el diccionario a partir de una conexiÃ³n a base de datos.
     *
     * @param rs Flujo de datos proveniente de un ResultSet o consulta sql.
     */
    public void buildDictionary(ResultSet rs){
        try {
            rs.last();
            dictionary = new ArrayList(rs.getRow());
            rs.first();
            
            String typeColumn = rs.getMetaData().getColumnClassName(1);
            System.out.println(typeColumn);
            if(typeColumn.equals("java.lang.Short") ){
                buildShortDictionary(rs);
            } else if(typeColumn.equals("java.lang.String")){
                buildStringDictionary(rs);
            } else {
                buildShortDictionary(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        Collections.sort(dictionary);
    }
    
    private void buildShortDictionary(ResultSet rs){
        try {
            dictionary.add(rs.getObject(1).toString());
            //dictionary.add(rs.getShort(1));
            while(rs.next()){
                dictionary.add(rs.getObject(1).toString());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void buildStringDictionary(ResultSet rs){
        try {
            dictionary.add(rs.getString(1));
            while(rs.next()){
                dictionary.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Construye el diccionario a partir de un archivo de acceso aleatorio tipo
     * .arff.
     *
     * @param cad Flujo de datos con todas las etiquetas attribute proveniente
     *            de un archivo de acceso aleatorio tipo .arff.
     */
    public void buildMultivaluedDictionary(String cad) {
        StringTokenizer attributes;
        String colName = "";
        String attr = "";
        int beg;
        int end;
        do {
            // Se calcula el tamaÃ±o de la cadena attribute y se recorta para
            // almacenar el nombre del atributo en attrNames.
            beg = cad.indexOf("attribute");
            if (beg < 0) {
                beg = cad.indexOf("ATTRIBUTE");
                if (beg < 0) {
                    break;
                }
            }
            end = beg + 10;
            colName = cad.substring(end, cad.indexOf('{'));
            // attrNames es un diccionario con los nombres de los atributos.
            attrNames.add(colName.trim());
            // El nombre de cada atributo se almacena en la cadena que contiene
            // los items de un atributo. Para usarlo como separador de atributos
            attr = attr + colName.trim();
            attr = attr + ",";
            beg = cad.indexOf('{');
            end = cad.indexOf('}');
            // attr almacena todos los nombres de los items de un atributo
            // separandolos por una coma.
            attr = attr + cad.substring(beg+1, end);
            
            attr = attr + ",";
            // La cadena de atributos leida se recorta del flujo original de
            // etiquetas attribute, hasta que el tamaÃ±o del flujo original es 0.
            cad = cad.substring(end+1, cad.length());
            cad.trim();
        }while(cad.length() != 0);
        
        // Se elimina la coma sobrante al final de attr.
        attr = attr.substring(0, attr.length()-1);
        cad = "";
        
        attributes = new StringTokenizer(attr, ",");
        
        // El tamaÃ±o inicial del diccionario es el numero de Tokens o productos.
        dictionary = new ArrayList(attributes.countTokens());
        
        // El primer elemento del diccionario es el elemento missing.
        dictionary.add("?");
        // Cada uno de los Tokens se almacenan en el diccionario.
        while(attributes.hasMoreTokens()) {
            cad = attributes.nextToken();
            int f = findAttrName(cad);
            if (f >= 0) {
                colName = cad;
            } else {
                cad = colName + "=" + cad.trim();
                dictionary.add(cad.trim());
            }
        }
    }
    
    /**
     * Construye el diccionario de datos para un conjunto de datos univaluado
     * o binario, a partir de un archivo de acceso aleatorio .arff.
     *
     * @param attrs Cadena que tiene la linea @attribute, junto con los nombres
     *              de los datos del atributo correspondiente.
     */
    public void buildUnivaluedDictionary(String attrs) {
        int beg = attrs.indexOf('{');
        int end = attrs.indexOf('}');
        String data = attrs.substring(beg+1, end);
        StringTokenizer attributes = new StringTokenizer(data, ",");
        
        dictionary = new ArrayList(attributes.countTokens());
        dictionary.add("?");
        
        while (attributes.hasMoreTokens()) {
            dictionary.add(attributes.nextToken().trim());
        }
    }
    
    /**
     * Ordena alfabeticamente el diccionario de datos. ***
     */
    public void sortDictionary() {
        Collections.sort(dictionary);
    }
    
    /**
     * Retorna la lista con los nombres de todos los atributos del conjunto de
     * datos.
     */
    public ArrayList getAttrNames() {
        return attrNames;
    }
    
    /**
     * Busca un atributo en la lista de nombres de los atributos.
     *
     * @param attr El atributo a buscar en la lista de nombres de atributos.
     * @return El indice del atributo en la lista en caso de encontrarlo; de lo
     *         contrario retorna -1.
     */
    public int findAttrName(String attr) {
        Iterator it = attrNames.iterator();
        String at = "";
        int index = -1;
        int count = 0;
        while (it.hasNext()) {
            at = (String) it.next();
            if(at.equals(attr)) {
                index = count;
                break;
            }
            count++;
        }
        return index;
    }
    
    /**
     * Retorna la codificaciÃ³n de un atributo.
     *
     * @return El cÃ³digo que a un atributo le pertenece. El cÃ³digo es obtenido
     *         a partir del diccionario de datos.
     */
    public short codeAttribute(String attr){
        return (short)Collections.binarySearch(dictionary, attr);
    }
    
    /**
     * Retorna el nombre del atributo en la posiciÃ³n indicada.
     *
     * @param i El indice en el diccionario.
     */
    public String decodeAttribute(short i) {
        return (String) dictionary.get(i);
    }
    
    /**
     * RepresentaciÃ³n textual del diccionario de datos.
     */
    public void showDictionary(){
        Iterator it = dictionary.iterator();
        int i = 0;
        while(it.hasNext()){
            System.out.println((i++) + " " + (String)it.next());
        }
    }
    
    /**
     * Estabelece el nÃºmero de transacciones del <code>DataSet</code>.
     *
     * @param ntransactions El nÃºmero de transacciones del DataSet.
     */
    public void setNtransactions(int ntransactions) {
        this.ntransactions = ntransactions;
    }
    
    /**
     * Retorna el nÃºmero de transacciones del DataSet.
     */
    public int getNtransactions() {
        return ntransactions;
    }
    
    /**
     * Construye un nodo del <code>DataSet</code>.
     *
     * @param n  El valor codificado del dato a insertar en el <code>DataSet
     *           </code>.
     * @param id  0 Identifica el inicio de una transacciÃ³n.
     *           -1 Identifica el final de una transacciÃ³n.
     *           -2 Identifica una transacciÃ³n con un solo item.
     */
    public void buildNTree(short n, int id) {
        ItemSet itemset1 = new ItemSet(1);
        itemset1.addItem(n);
        candidatesOne.insertItemsetOne(itemset1);
        NodeF leaf = null;
        if((id == 0 || id == -2) && current != root ) {
            current = root;
            ntransactions++;
        }  else if(current == root){
            ntransactions++;
        }
        if(current.son == null) {
            current = current.addSon(n, id);
            if(id == -1 || id == -2) {
                linkLeaf((NodeF) current);
            }
        } else {
            NodeNoF aux = current.findBro(n, id);
            
            if(aux.getItem() == n) {
                current = aux;
                if((id == -1 || id == -2) && ((NodeF)current).getSupport() == 1){
                    linkLeaf((NodeF) current);
                }
            } else {
                current = aux.addBro(n, id);
                if(id == -1 || id == -2){
                    linkLeaf((NodeF) current);
                }
            }
        }
    }
    
    /**
     * Retorna el Ã¡rbol de itemsets frecuentes tipo 1.
     */
    public AvlTree getCandidatesOne() {
        return candidatesOne;
    }
    
    /**
     * Poda los itemsets candidatos tipo 1, construye los itemsets frecuentes
     * tipo 1 y los almacena en un Ã¡rbol Avl.
     */
    public void pruneCandidatesOne(int minSupport, AvlTree frequentsOne ){
        pruneCandidatesOne( candidatesOne.getRoot(), minSupport, frequentsOne);
    }
    
    /**
     * MÃ©todo interno poda los itemsets candidatos tipo 1, construye los
     * itemsets frecuentes tipo 1 y los almacena en un Ã¡rbol Avl.
     */
    private void pruneCandidatesOne( AvlNode t, int minSupport, AvlTree frequentsOne){
        if( t != null ){
            pruneCandidatesOne( t.left, minSupport, frequentsOne);
            if(t.element.getSupport() >= minSupport){
                frequentsOne.insert(t.element);
            }
            pruneCandidatesOne( t.right, minSupport, frequentsOne);
        }
    }
    
    /**
     * Enlaza un nodo con soporte a lista alterna. La lista alterna tiene
     * referencias a los nodos del DataSet que tienen soporte.
     *
     * @param leaf El nodo hoja o con soporte que se va insertar en la lista.
     */
    public void linkLeaf(NodeF leaf) {
        if(lroot == null) {
            lroot = leaf;
            lcurrent = lroot;
        } else if(leaf != current.son || leaf != current.bro) {
            lcurrent.next = leaf;
            lcurrent = leaf;
        }
    }
    
    /**
     * Retorna la raÃ­z del DataSet.
     */
    public NodeNoF getRoot() {
        return this.root;
    }
    
    /**
     * Establece una referencia a la raÃ­z de la lista de nodos hoja o con
     * soporte. MÃ©todo Ãºtil para recorrer la lista alterna.
     */
    public void setPointers(){
        lcurrent = this.lroot;
        lnode = this.lroot;
        frecuency = lcurrent.getSupport();
    }
    
    /**
     * Retorna el valor de un item del DataSet.
     */
    public short getNode() {
        short item = -1;
        if(lnode.dad != null){
            item = lnode.getItem();
            lnode = (NodeNoF)lnode.dad;
        }else if(lcurrent.next != null){
            item = lnode.getItem();
            if(--frecuency == 0){
                lcurrent = lcurrent.next;
                frecuency = lcurrent.getSupport();
            }
            lnode = lcurrent;
        }
        return item;
    }
    
    /**
     * RepresentaciÃ³n textual del DataSet.
     */
    public void showNTree() {
        int i = 0;
        NodeF aux = this.lroot;
        while(aux != null) {
            System.out.println(++i + " " +aux.getPath());
            aux = aux.next;
        }
    }
    
    /**
     * Guarda el DataSet en disco.
     *
     * @param file El archivo de acceso aleatorio en el cual se va a guardar
     *             el DataSet.
     */
    public void saveTree(String path) {
        FileManager aa = new FileManager(path);
        NodeNoF aux = getRoot().son;
        NodeF nf = null;
        while(aux.bro != null || aux.dad != null) {
            if(aux.son != null) {
                aa.writeItem((short) aux.getItem());
                if(aux.getClass().getSimpleName().compareTo("NodeF") == 0) {
                    aa.writeItem((short) -3);
                    nf = (NodeF) aux;
                    aa.writeItem(nf.getSupport());
                }
                aa.writeItem((short) -1);
                aux = aux.son;
            } else {
                aa.writeItem(aux.getItem());
                aa.writeItem((short) -3);
                nf = (NodeF) aux;
                aa.writeItem(nf.getSupport());
                if(aux.bro != null)
                    aux = aux.bro;
                else {
                    // si no entra en tiene hijo? no escriba cierra parentesis
                    aa.writeItem((short) -2);
                    aux = aux.dad;
                    while(aux.bro == null && aux.dad.getItem() != -5) {
                        aa.writeItem((short) -2);
                        aux = aux.dad;
                    }
                    if(aux.bro != null)
                        aux = aux.bro;
                    else if(aux.dad.getItem() == -5)
                        aux = getRoot();
                }
            }
        }
        //aa.readFile(0);
        aa.closeFile();
        System.out.println();
    }
    
    /**
     * Recupera el DataSet de disco y lo vuelve a cargar en memoria.
     *
     * @param file El archivo en el cual se guardo el DataSet.
     */
//    public void rollBackTree(String path) {
//        DataSet rTree = new DataSet("rollBackTree");
//        FileManager aa = new FileManager(path);
//        short s = 0, aux = 0;
//        int pos = 0;
//        try {
//            aa.setOutChannel(pos);
//            s = aa.outChannel.readShort();
//            pos += 2;
//            rTree.current = rTree.current.addSon(s, 1);
//            while(true) {
//                s = aa.outChannel.readShort();
//                pos += 2;
//                while(s == -1) {
//                    s = aa.outChannel.readShort();
//                    pos += 2;
//                    aux = s;
//                    s = aa.outChannel.readShort();
//                    pos += 2;
//                    if(s == -1)
//                        rTree.current = rTree.current.addSon(aux, 1);
//                }
//                if(s == -3) {
//                    s = aa.outChannel.readShort();
//                    pos += 2;
//                    rTree.current = rTree.current.addSon(aux, s);
//                    rTree.linkLeaf((NodeF) rTree.current);
//                    /**********/
//                    s = aa.outChannel.readShort();
//                    pos += 2;
//                    while(s >= 0) {
//                        aux = s;
//                        s = aa.outChannel.readShort();
//                        pos += 2;
//                        if(s == -3) {
//                            s = aa.outChannel.readShort();
//                            pos += 2;
//                            rTree.current = rTree.current.addBro(aux, s);
//                            rTree.linkLeaf((NodeF) rTree.current);
//                            s = aa.outChannel.readShort();
//                            pos += 2;
//                        } else if(s == -1 || s == -2) {
//                            pos -= 2;
//                            aa.setOutChannel(pos);
//                        }
//                    }
//                    if(s == -1 || s == -2) {
//                        pos -= 2;
//                        aa.setOutChannel(pos);
//                    }
//                    /**********/
//                } else {
//                    while(s == -2) {
//                        rTree.current = rTree.current.dad;
//                        s = aa.outChannel.readShort();
//                        pos += 2;
//                        if(s >= 0) {
//                            aux = s;
//                            s = aa.outChannel.readShort();
//                            pos += 2;
//                        }
//                    }
//                    if(s == -3) {
//                        s = aa.outChannel.readShort();
//                        pos += 2;
//                        rTree.current = rTree.current.addBro(aux, s); // hermano con frecuencia
//                        rTree.linkLeaf((NodeF) rTree.current);
//                        /**********/
//                        s = aa.outChannel.readShort();
//                        pos += 2;
//                        while(s >= 0) {
//                            aux = s;
//                            s = aa.outChannel.readShort();
//                            pos += 2;
//                            if(s == -3) {
//                                s = aa.outChannel.readShort();
//                                pos += 2;
//                                rTree.current = rTree.current.addBro(aux, s);
//                                rTree.linkLeaf((NodeF) rTree.current);
//                                s = aa.outChannel.readShort();
//                                pos += 2;
//                            } else if(s == -1 || s == -2) {
//                                pos -= 2;
//                                aa.setOutChannel(pos);
//                            }
//                        }
//                        if(s == -1 || s == -2) {
//                            pos -= 2;
//                            aa.setOutChannel(pos);
//                        }
//                        /**********/
//                    } else if(s == -1) {
//                        rTree.current = rTree.current.addBro(aux, 1); // hermano sin frecuencia
//                        pos -= 2;
//                        aa.setOutChannel(pos);
//                    }
//                }
//            }
//        } catch(EOFException e1) {
//            rTree.showNTree();
//            aa.closeFile();
//            aa.deleteFile();
//        } catch(IOException e2) {
//            e2.printStackTrace();
//        }
//    }
    
    public NodeF getLRoot() {
        return this.lroot;
    }
    
    public void reset() {
        this.root = new NodeNoF();
        this.current = root;
        this.lroot = null;
        this.candidatesOne = new AvlTree();
        this.ntransactions = 0;
        this.attrNames = new ArrayList();
    }
    
//    public void passDictio() {
//        root.setDictionary(dictionary);
//    }
}
