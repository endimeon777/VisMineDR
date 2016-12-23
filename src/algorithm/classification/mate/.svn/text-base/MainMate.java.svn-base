/*
 * MainMate.java
 *
 * Created on 30 de diciembre de 2006, 1:03
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package algorithm.classification.mate;


import algorithm.classification.Value;
import algorithm.classification.c45_1.Attribute;
import algorithm.classification.c45_1.TreeCounter;
import algorithm.classification.c45_1.TreeViewer;
import gui.Icons.Clasification.ClasificationIcon;
import gui.Icons.Tree.ViewerAllTrees;
import gui.Icons.Tree.ViewerClasification;
import gui.KnowledgeFlow.AnimationLabel;
import gui.KnowledgeFlow.Chooser;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import Utils.ItemSet;

/**
 *
 * @author ivan
 */
public class MainMate extends Thread{
    
    /** Fuente de datos */
    private gui.Icons.Filters.TariyTableModel dataSrc;
    
    /** Diccionario de datos */
    private ArrayList dictionary;
    
    /** Mapa de atributos ganadores */
    private HashMap winners;
    
    /** Mapa de objetos Attribute */
    private HashMap attributes;
    
    /**
     * Almacena objetos tipo Describe. Estructura para construir el arbol de
     * decision.
     */
    private ArrayList desc;
    
    private float prune1; //threshold
    
    private int prune2; //number of occurrences of a value
    
    public TreeCounter c;
    
    public Attribute root;
    
    public ClasificationIcon ci;
    
    private AnimationLabel animation;
    
    /** Creates a new instance of MainMate */
    public MainMate(gui.Icons.Filters.TariyTableModel data, float prune1, int prune2) {
        dataSrc = data;
        dictionary = new ArrayList();
        winners = new HashMap();
        desc = new ArrayList();
        this.prune1 = prune1;
        this.prune2 = prune2;
    }
    
    public MainMate(gui.Icons.Filters.TariyTableModel data, float prune1, int prune2, ClasificationIcon ci) {
        this.ci = ci;
        dataSrc = data;
        dictionary = new ArrayList();
        winners = new HashMap();
        desc = new ArrayList();
        this.prune1 = prune1;
        this.prune2 = prune2;
    }
    /**
     * Construye el diccionario de datos en un arraylist.
     */
    public void buildDictionary() {
        // To concatenate the attribute name and the attribute value
        String aux;
        
        //->Dictionary building
        for(int i = 0; i < dataSrc.getColumnCount(); i++) {
            for(int j = 0; j < dataSrc.getRowCount(); j++) {
                aux = dataSrc.getColumnName(i);
                aux = aux + "=" + (String) dataSrc.getValueAt(j, i);
                if(dictionary.isEmpty()){
                    dictionary.add("?");
                }else{
                    if(!dictionary.contains(aux)) {
                        dictionary.add(aux);
                    }
                }
            }
        }
        // Ordena alfabeticamente el diccionario
        Collections.sort(dictionary);
    }
    
    public void dataCombination() {
        // Indice de la clase del conjunto de datos
        int classInd;
        // Conjunto de datos
        Object[][] datos = dataSrc.data;
        // Almacena las combinaciones atributo-ganadores
        Vector comb = new Vector();
        // Vector de ganadores
        Vector win;
        // To concatenate the attribute name and the attribute value
        String value;
        // Codifica a los elementos del conjunto de datos
        short cod;
        Entropy attrib;
        // Un nuevo HashMap por cada nivel del arbol
        attributes = new HashMap();
        for (int r=0; r<dataSrc.data.length; r++) {
            win = winnersIn(datos[r]);
            classInd = dataSrc.getColumnCount()-1;
            value = dataSrc.getColumnName(classInd);
            value = value + "=" + (String) datos[r][classInd];
            cod = code(value);
            if (win != null) {
                for (int c=0; c<classInd; c++) {
                    if (!winners.containsKey(c)) {
                        value = dataSrc.getColumnName(c);
                        if (!attributes.containsKey(c)) {
                            attrib = new Entropy(value);
                            attributes.put(c, attrib);
                        } else {
                            attrib = (Entropy) attributes.get(c);
                        }
                        value = value + "=" + (String) datos[r][c];
                        comb.addElement(code(value));
                        comb.addAll(win);
                        comb.addElement(cod);
                        attrib.addCombination(comb, c);
                        comb.clear();
                    }
                }
            }
        }
    }
    
    public void calcEntropy() {
        Entropy winatt = null;
        double entro=-100000.0;
        String winner = new String();
        Collection set = attributes.values();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Entropy elem = (Entropy) it.next();
            elem.entropy();
            if (elem.getEntro() > entro) {
                entro = elem.getEntro();
                winner = elem.getAttribute();
                winatt = elem;
            }
        }
        HashMap values = winatt.getValues();
        set = values.keySet();
        it = set.iterator();
        ArrayList combinations;
        String classe = dataSrc.getColumnName(dataSrc.getColumnCount()-1);
        while (it.hasNext()) {
            short key = (Short) it.next();
            combinations = (ArrayList) values.get(key);
            combinations = prune2(combinations,classe);
            combinations = prune1(combinations,classe);
        }
        buildTree(winner, values,prune1,prune2);
    }
    
    public void buildTree(String winner, HashMap values, float prune1,int prune2) {
        String classe = dataSrc.getColumnName(dataSrc.getColumnCount()-1);
        ArrayList and = new ArrayList(1);
        if (desc.isEmpty()) {
            and = singleCounterClass(values,classe);
            Describe d = new Describe(0,-1, winner, "-1", "-1",and,getScore(values));
            desc.add(d);
        }
        int index = desc.size()-1;
        Set set = values.keySet();
        Iterator it = set.iterator();
        ArrayList combinations;
        while (it.hasNext()) {
            short key = (Short) it.next();
            combinations = (ArrayList) values.get(key);
            if (combinations.size() == 2) {
                boolean flag = false;
                Iterator i = combinations.iterator();
                while (i.hasNext()) {
                    Object elem = (Object) i.next();
                    if (elem instanceof ItemSet) {
                        short[] s = ((ItemSet) elem).getItems();
                        Describe d = null;
                        int cadNull = 0;
                        for (int h=0; h<s.length; h++) {
                            String cad = decode(s[h]);
                            StringTokenizer st = new StringTokenizer(cad, "=");
                            String attr = st.nextToken();
                            String value = st.nextToken();
                            int a = dataSrc.getColumnIndex(attr);
                            String ca = (String) winners.get(a);
                            if(ca == null)
                                cadNull = 0;
                            else
                                cadNull = (int) value.compareTo(ca);
                            if ( cadNull != 0 || !(winners.containsValue(value)&& winners.containsKey(a))) {
                                if (attr.compareTo(classe) == 0) {
                                    int l = desc.size()-1;
                                    and = counterClass(values,key,classe);
                                    value = fixCounter(and);
                                    Object o = (Short) combinations.get(0);
                                    int score = (Short)o;
                                    d = new Describe(l+1,l, attr, value, "-1", and,score);
                                    flag = false;
                                } else {
                                    and = counterClass(values,key,classe);
                                    Object o = (Short) combinations.get(0);
                                    int score = (Short)o;
                                    d = new Describe(desc.size(),index,attr, value, classe,and,score);
                                    flag = true;
                                }
                                desc.add(d);
                            }
                        }
                        if(flag){
                            int l = desc.size()-2;
                            Describe a = (Describe) desc.get(l);
                            a.setFather(l+1);
                            desc.set(l,a);
                        }
                    }
                }
            } else {
                String value = decode((short) key);
                StringTokenizer st = new StringTokenizer(value, "=");
                st.nextToken();
                value = st.nextToken();
                int k = dataSrc.getColumnIndex(winner);
                if (winners.containsKey(k)) {
                    winners.remove(k);
                }
                winners.put(k, value);
                and = counterClass(values,key,classe);
                int score = getScore(values);
                Describe d = new Describe(desc.size(),index, winner, value, "-1",and,score);
                desc.add(d);
                dataCombination();
                if (!attributes.isEmpty()) {
                    calcEntropy();
                }else{
                    int l = desc.size()-1;
                    and = counterClass(values,key,classe);
                    ArrayList and2 = (ArrayList) and.clone();
                    and2.remove(and2.size()-1);
                    value = fixCounter(and);
                    String str2 = ((Value)and.get(0)).getName();
                    for(int i = 0; i < and2.size(); i++){
                        String st1 = ((Value)and2.get(i)).getName();
                        if(str2.equals(st1)){
                            and2.remove(i);
                            break;
                        }
                    }
                    and2.add(and.get(0));
                    Object o = (Short) combinations.get(0);
                    score = (Short)o;
                    d = new Describe(l+1,l, classe, value, "-1", and2,score);
                    desc.add(d);
                }
            }
        }
        int k = dataSrc.getColumnIndex(winner);
        if (winners.containsKey(k)) {
            winners.remove(k);
        }
    }
    
    public int getF(ArrayList a){
        if(a != null){
            Value v = (Value) a.get(a.size()-1);
            return v.getFrecuence();
        }
        return 0;
    }
    
    /**
     * Verifica que los valores de los atributos ganadores se encuentren en un
     * determinado registro del conjunto de datos.
     */
    public Vector winnersIn(Object[] data) {
        Set set = winners.keySet();
        Iterator it = set.iterator();
        int key;
        String value;
        Vector aux = new Vector();
        while (it.hasNext()) {
            key = (Integer) it.next();
            value = (String) winners.get(key);
            String var = (String) data[key];
            if (value.compareTo(var) != 0) {
                return null;
            } else {
                value = dataSrc.getColumnName(key);
                value = value + "=" + (String) data[key];
                aux.addElement(code(value));
            }
        }
        return aux;
    }
    
    /**
     * Codifica un elemento de la fuente de datos por su valor en el diccionario.
     */
    public short code(String attr) {
        return (short) Collections.binarySearch(dictionary, attr);
    }
    
    /** Frecuence for root
     */
    public ArrayList singleCounterClass(HashMap values,String classe){
        ArrayList<Value> counter = new ArrayList<Value>();
        ArrayList combinations = null;
        int frec = 0;
        Set set = values.keySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            short key = (Short) it.next();
            combinations = (ArrayList) values.get(key);
            Iterator i = combinations.iterator();
            while (i.hasNext()) {
                Object elem = (Object) i.next();
                if (elem instanceof ItemSet) {
                    short[] elements = ((ItemSet) elem).getItems();
                    for (int j=0; j<elements.length; j++) {
                        String str1 = decode(elements[j]);
                        StringTokenizer st = new StringTokenizer(str1,"=");
                        String attr = st.nextToken();
                        String valu = st.nextToken();
                        if(classe.compareTo(attr)==0){
                            if(counter.isEmpty()){
                                Value v = new Value(valu,((ItemSet)elem).getSupport());
                                counter.add(v);
                            }else{
                                int k;
                                for(k = 0; k < counter.size(); k++){
                                    if(valu.compareTo(counter.get(k).getName()) == 0){
                                        counter.get(k).incFrecuence(((ItemSet)elem).getSupport());
                                        break;
                                    }
                                }
                                if(k == counter.size()){
                                    Value v = new Value(valu,((ItemSet)elem).getSupport());
                                    counter.add(v);
                                }
                            }
                            frec += ((ItemSet)elem).getSupport();
                            break;
                        }
                    }
                }
            }
        }
        Value v = new Value("",frec);
        counter.add(v);
        return counter;
    }
    
    /**This method returns the number of accurrences of each value of the class
     *for every node
     */
    
    public ArrayList counterClass(HashMap values, short key,String classe){
        ArrayList<Value> counter = new ArrayList<Value>();
        ArrayList combinations;
        combinations = (ArrayList) values.get(key);
        Iterator i = combinations.iterator();
        while (i.hasNext()) {
            Object elem = (Object) i.next();
            if (elem instanceof ItemSet) {
                short[] elements = ((ItemSet) elem).getItems();
                for (int j=0; j<elements.length; j++) {
                    String str1 = decode(elements[j]);
                    StringTokenizer st = new StringTokenizer(str1,"=");
                    String attr = st.nextToken();
                    String valu = st.nextToken();
                    if(classe.compareTo(attr)==0){
                        Value v = new Value(valu,((ItemSet)elem).getSupport());
                        counter.add(v);
                        break;
                    }
                }
            }
        }
        Object elem = combinations.get(0);
        int frec = (Short)elem;
        Value v = new Value("",frec);
        counter.add(v);
        return counter;
    }
    
    public ArrayList prune1(ArrayList a,String classe) {
        ArrayList b = new ArrayList(1);
        float big = 0F;
        int c = 0;
        Object aux = null;
        Iterator i = a.iterator();
        Object elem = (Object) i.next();
        float x = (Short)elem;
        float y = 0F;
        float z = 0F;
        while (i.hasNext()) {
            elem = (Object) i.next();
            y = ((ItemSet)elem).getSupport();
            if(y > big){
                big = y;
                aux = elem;
            }
            z = ((y/x)*100);
            if(z > this.prune1){
                c++;
                b.clear();
                b.add((short)x);
                b.add((ItemSet) elem);
            }
        }
        if(c == 1){//Only one is good enough
            a.clear();
            a.addAll(b);
            return a;
        }else if(c == 0){//None is good but nothing happens
            return null;
        }
        a.clear();//Both of them are good enough but just the bigger is returned
        a.add((short)x);
        a.add((ItemSet) aux);
        return a;
    }
    
    public ArrayList prune2(ArrayList a,String classe) {
        ArrayList b = new ArrayList(1);
        int counter = 0;
        float big = 0F;
        int c = 0;
        Object aux = null;
        Iterator i = a.iterator();
        Object elem = (Object) i.next();
        float x = (Short)elem;
        float y = 0F;
        while (i.hasNext()) {
            elem = (Object) i.next();
            y = ((ItemSet)elem).getSupport();
            if(y > big){
                big = y;
                aux = elem;
            }
            counter += y;
        }
        if(counter > this.prune2){ //if the sum of class values is bigger than
            return a;              //prune2 a is returned the same
        }else{
            a.clear();             //if not only the biggest one is returned
            a.add((short)x);
            a.add((ItemSet) aux);
            return a;
        }
    }
    
    public String fixCounter(ArrayList a){
        int b = ((Value)a.get(0)).getFrecuence();
        String str1 = ((Value)a.get(0)).getName();
        for(int i = 1; i<a.size()-1; i++){
            int c = ((Value)a.get(i)).getFrecuence();
            if(b < c){
                b = c;
                str1 = ((Value)a.get(i)).getName();
            }
        }
        a.clear();
        Value d = new Value(str1,b);
        a.add(d);
        return str1;
    }
    
    /**
     * Decodifica un dato.
     */
    public String decode(short i) {
        return (String) dictionary.get(i);
    }
    
    private short getScore(HashMap values) {
        short s = 0;
        Set set = values.keySet();
        Iterator it = set.iterator();
        ArrayList combinations;
        //String classe = dataSrc.getColumnName(dataSrc.getColumnCount()-1);
        while (it.hasNext()) {
            short key = (Short) it.next();
            combinations = (ArrayList) values.get(key);
            s += (Short)combinations.get(0);
        }
        return s;
    }
    /**
     * Adiciona un valor al mapa de atributos ganadores.
     */
    public void addWinner(int key, String winner) {
        this.winners.put(key, winner);
    }
    
    /**
     * Muestra el diccionario.
     */
    public String showDictionary() {
        return dictionary.toString();
    }
    
    /**
     * Muestra una representacion textual de las combinaciones de cada atributo
     * con los valores de la clase.
     */
    public void showAttributes() {
        Collection set = attributes.values();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Entropy elem = (Entropy) it.next();
            elem.showAttributes();
        }
    }
    
    public void showDesc(){
        Collections.sort(desc, new compareDaddy());
//        for (int i = 0; i < desc.size(); i++){
//            Describe a = (Describe) desc.get(i);
//            System.out.println("Nod: "+a.getNode()+" Dad: "+a.getFather()+" Attr: "+
//                    a.getAttribute() +" Value: "+a.getValue()+" Classe: "+a.getClasse()+
//                    " MyFrec: "+ getF(a.getCounter())+" DadFrec: "+a.getDadScore());
//        }
    }
    
    public TreeCounter erectTree(){
        Describe a1 = (Describe) desc.get(0);
        int fe = getF(a1.getCounter());
        if(fe != 0 && a1.getCounter().size() == 3)
            a1.getCounter().remove(a1.getCounter().size()-1);
        root = new Attribute(a1.getAttribute(),fe,a1.getDadScore(), a1.getCounter());
        c = new TreeCounter();
        Attribute aux2;
        for(int i = 1; i < desc.size(); i++){
            a1 = (Describe) desc.get(i);
            String str = a1.getAttribute();
            str = str + "=" + a1.getValue();
            aux2 = (Attribute) c.searchTreeDesc(root,a1.getFather());
            fe = getF(a1.getCounter());
            if(fe != 0 && a1.getCounter().size() == 3)
                a1.getCounter().remove(a1.getCounter().size()-1);
            Attribute a2 = new Attribute(str,fe,a1.getDadScore(), a1.getCounter());
            a2.setId(a1.getNode());
            if(aux2.getSon() != null){
                aux2 = aux2.getSon();
                aux2 = aux2.getBro(aux2);
                aux2.setBro(a2);
            }else{
                aux2.setSon(a2);
            }
        }
        c.root = root;
        c.pruneLeafs();
        return c;
    }
    
    public void setAnimation(AnimationLabel animation){
        this.animation = animation;
    }
    
    public void run() {
        this.buildDictionary();
        long time = System.currentTimeMillis();
        this.dataCombination();
        this.calcEntropy();
        this.showDesc();
        long executionTime = System.currentTimeMillis() - time;
        System.out.println("MateBy execution time: "+executionTime+"ms");
        this.erectTree();
        ci.setInfo("Builted Model in " + executionTime + "ms");
        Chooser.setStatus("Mate: Builted Model in " + executionTime + "ms");
        this.ci.root = this.root;
        animation.stop();
    }
    
    public static void main(String args[]) {
        MainMate mm = new MainMate(new gui.Icons.Filters.TariyTableModel(),100,0);
        mm.buildDictionary();
        long time = System.currentTimeMillis();
        mm.dataCombination();
        mm.calcEntropy();
        mm.showDesc();
        long executionTime = System.currentTimeMillis() - time;
        System.out.println("MateBy execution time: "+executionTime+"ms");
        mm.erectTree();
        new ViewerAllTrees(mm.root).setVisible(true);
    }
}