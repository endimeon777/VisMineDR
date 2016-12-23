/*
 * FileManager.java
 *
 * Created on 3 de abril de 2005, 08:54 AM
 *
 * Proyecto Tariy
 */

package Utils;

import java.io.*;
import java.util.*;

/**
 * La clase <code>FileManager</code> gestiona todo lo relacionado con flujos de
 * archivos.
 *
 * @author Proyecto Tariy
 */
public class FileManager {
    /**
     * El archivo de acceso aleatorio del cual se quiere leer o al cual se va a
     * escribir.
     */
    File out;
    
    /**
     * Puntero para ubicarse en el archivo de acceso aleatorio. Proporciona el
     * flujo hacia el archivo.
     */
    RandomAccessFile outChannel;
    
    /** Matriz con los datos de un archivo de acceso aleatorio .arff. */
    private Object[][] data;
    
    /**
     * Arreglo con los nombres de los atributos de un archivo de acceso
     * aleatorio .arff.
     */
    private Object[] attributes;
    
    /** Lista con el diccionario de datos de un archivo .arff */
    ArrayList dictionary;
    
    /**
     * Crea una nueva instancia de la clase <code>FileManager</code>.
     *
     * @param file Archivo de acceso aleatorio.
     */
    public FileManager(String archivo) {
        try{
            out = new File( archivo );
            outChannel = new RandomAccessFile( out, "rw" );
        } catch( IOException e ) {
            e.printStackTrace();
        }
    }
    
    /**
     * Escribe un item de tipo <code>short</code> (2 bytes) en el archivo de
     * acceso aleatorio.
     *
     * @param s El item a escribir en el archivo de acceso aleatorio.
     */
    public void writeItem(short s) {
        try{
            outChannel.seek( out.length() );
            outChannel.writeShort(s);
        } catch( IOException e ) {
            e.printStackTrace();
        }
    }
    
    /**
     * Escribe una cadena en el archivo de acceso aleatorio.
     *
     * @param s La cadena a escribir en el archivo de acceso aleatorio.
     */
    public void writeString(String s){
        byte b[];
        try{
            b = s.getBytes();
            outChannel.seek( out.length() );
            outChannel.write(b);
        } catch( IOException e ) {
            e.printStackTrace();
        }
    }
    
    /**
     * Retorna el tamaÃ±o en bytes del archivo de acceso aleatorio.
     */
    public long getFileSize() {
        return out.length();
    }
    
    /**
     * Retorna el nombre del archivo de acceso aleatorio.
     */
    public String getFileName() {
        return out.getName();
    }
    
    /**
     * Cierra el flujo hacia el archivo de acceso aleatorio.
     */
    public void closeFile() {
        try{
            outChannel.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Borra fisicamente el archivo de acceso aleatorio.
     */
    public void deleteFile() {
        out.deleteOnExit();
    }
    
    
    /**
     * Ubica el flujo en una posiciÃ³n determinada del archivo de acceso
     * aleatorio.
     */
    public void setOutChannel(int pos) throws IOException {
        outChannel.seek(pos);
    }
    
    /**
     * Lee y muestra el contenido de un archivo de acceso aleatorio. Esta
     * funciÃ³n es Ãºtil para el antiguo formato de archivos Tariy, lee un flujo
     * de tipo <code>Short</code> y muestra cada transacciÃ³n separada por el
     * cero.
     *
     * @param readposition La posiciÃ³n a partir de la cual se va a leer el
     *                     archivo.
     */
    public void ReadTransaction(int readposition) {
        try{
            outChannel.seek(readposition);
            short s;
            while( true ) {
                s = outChannel.readShort();
                if(s != 0)
                    System.out.print(s + " ");
                else
                    System.out.println();
            }
        } catch(EOFException e1) {
            this.closeFile();
        } catch(IOException e2) {
            e2.printStackTrace();
        }
    }
    
    /**
     * Lee y muestra el contenido del antiguo formato de archivos Tariy sin
     * hacer distinciÃ³n de el separador de transacciones, el cero.
     *
     * @param initiposition La posiciÃ³n a partir de la cual se va a leer el
     *                      archivo.
     */
    public void readFile(int initposition) {
        try{
            outChannel.seek(initposition);
            short s;
            while( true ) {
                s = outChannel.readShort();
                System.out.print(s + " ");
            }
        } catch(EOFException e1) {
            this.closeFile();
        } catch(IOException e2) {
            e2.printStackTrace();
        }
    }
    
    /**
     * Retorna el arreglo que tiene los nombres los atributos del archivo de
     * acceso aleatorio .arff.
     */
    public Object[] getAttributes() {
        return attributes;
    }
    
    /**
     * Retorna la matriz que almacena los datos del archivo de acceso aleatorio
     * .arff.
     */
    public Object[][] getData() {
        return data;
    }
    
    /**
     * Retorna el diccionario de datos construido a partir del archivo de acceso
     * aleatorio .arff.
     */
    public ArrayList getDictionary() {
        return this.dictionary;
    }
    
    /**
     * A partir de un Comma Separated Value File (csv), almacena los nombres de
     * los atributos en un arreglo, los datos en una matriz y el diccionario de
     * datos en un ArrayList.
     */
    public void readCsv() {
        ArrayList datos = new ArrayList();
        dictionary = new ArrayList();
        int rows = 0;
        int cols = 0;
        try {
            outChannel.seek(0);
            long fileSize = outChannel.length();
            long pos = 0;
            String read;
            while (pos < fileSize) {
                read = outChannel.readLine();
                pos = outChannel.getFilePointer();
                StringTokenizer st = new StringTokenizer(read, ",");
                if (rows == 0) {
                    attributes = new Object[st.countTokens()];
                }
                while(st.hasMoreTokens()) {
                    if (rows == 0) {
                        attributes[cols] = st.nextToken().trim();
                        cols++;
                    } else {
                        String valor = st.nextToken().trim();
                        datos.add(valor);
                        if (!dictionary.contains(valor)) {
                            dictionary.add(valor);
                            Collections.sort(dictionary);
                        }
                    }
                }
                if (read.length() > 0) {
                    datos.add("<");
                    rows++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            int c = 0;
            int r = 0;
            rows--;
            data = new Object[rows][cols];
            Iterator it = datos.iterator();
            it.next();
            while (it.hasNext()) {
                String cad = (String) it.next();
                if (cad.compareTo("<") == 0) {
                    r++;
                    c = 0;
                } else {
                    data[r][c] = cad;
                    c++;
                }
            }
        }
    }
    
    /**
     * A partir de un archivo de acceso aleatorio .arff, almacena los nombres de
     * los atributos en un arreglo, los datos en una matriz y el diccionario de
     * datos en un ArrayList.
     */
    public void dataAndAttributes(boolean noAll) {
        String attrs = "";
        String dat = "";
        int cols = 0;
        int rows = 0;
        DataSet dataset = new DataSet("Dictionary");
        
        try {
            outChannel.seek(0);
            long fileSize = outChannel.length();
            long pos = 0;
            String read = "";
            boolean buildDictionary = true;
            
            while (pos < fileSize) {
                if (!read.trim().equalsIgnoreCase("@data")) {
                    read = outChannel.readLine();
                    pos = outChannel.getFilePointer();
                    
                    // Mientras la cadena leida sea igual a @attribute almacenela
                    // en una cadena para los atributos.
                    if (read != null && read.length() > 10 && read.substring(0, 10).equalsIgnoreCase("@attribute")) {
                        attrs = attrs + read;
                        cols++;
                    }
                } else {
                    if (buildDictionary) {
                        dataset.buildMultivaluedDictionary(attrs);
                        dataset.sortDictionary();
                        dictionary = dataset.getDictionary();
                        buildDictionary = false;
                    }
                    
                    // Se lee el primer byte para saber si se trata de un
                    // comentario (%) o un salto de linea.
                    Byte b = outChannel.readByte();
                    
                    // Se decrementa en uno el flujo de la cadena para despues
                    // poder leer la linea completa.
                    outChannel.seek(pos--);
                    
                    // Despues de haber leido @data cada linea se almacena en
                    // una cadena para los datos.
                    read = outChannel.readLine();
                    pos = outChannel.getFilePointer();
                    
                    // Si la linea leida es un comentario (37 - %) o un salto de
                    // linea (10) entonces se ignora.
                    if (b != 37 && b != 10) {
                        
                        // Las lineas son separadas a traves del salto de linea.
                        dat = dat + read + ",/n,";
                        rows++;
                        /*if (rows == 100 && noAll) {
                            break;
                        }*/
                    }
                    read = "@data";
                }
            }
            
            // Se elimina la coma sobrante al final de leer los datos.
            dat = dat.substring(0, dat.length()-3);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Los nombres de los atributos se almacenan en un arreglo.
            if (cols==1) {
                cols = 2;
                attributes = new Object[cols];
                attributes[0] = "TID";
                attributes[1] = "ITEM";
            } else {
                attributes = new Object[cols];
                String colName;
                for (int i=0; i<cols; i++) {
                    int beg = attrs.indexOf("@attribute");
                    if (beg<0) {
                        beg = attrs.indexOf("@ATTRIBUTE");
                        if (beg<0) {
                            break;
                        }
                    }
                    int end = beg + 10;
                    colName = attrs.substring(end, attrs.indexOf('{'));
                    colName = colName.trim().toUpperCase();
                    attributes[i] = colName;
                    attrs = attrs.substring(attrs.indexOf('}')+1, attrs.length());
                }
            }
            // Los nombres de los datos se almacenan en una matriz.
            data = new Object[rows][cols];
            StringTokenizer token = new StringTokenizer(dat, ",");
            String s = "";
            int c = 0;
            int r = 0;
            
            while (token.hasMoreTokens()) {
                s = token.nextToken().trim();
                if (s.equals("/n")) {
                    r++;
                    if (r==rows) {
                        break;
                    }
                    c=0;
                } else {
                    if (!noAll) {
                        String attr = (String) dataset.getAttrNames().get(c) +
                                "=" + s;
                        short node = dataset.codeAttribute(attr);
                        data[r][c] = node;
                    } else {
                        data[r][c] = s;
                    }
                    c++;
                }
            }
            System.out.println("");
        }
    }
    
    /**
     * Retorna la representacion textual del archivo plano relacionado con esta
     * clase.
     */
    public String toString() {
        String file = "";
        try {
            long size = outChannel.length();
            outChannel.seek(0);
            long curPointer = outChannel.getFilePointer();
            while (curPointer < size) {
                file = file + outChannel.readLine() + "\n";
                curPointer = outChannel.getFilePointer();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return file;
        }
    }
    
    /**
     * Retorna el <code>DataSet</code> que se construye a partir de un archivo
     * de acceso aleatorio de formato .arff de tipo multivaluado o multicolumna.
     *
     * @param  isMarketB Determina si el conjunto de datos del archivo de acceso
     *         aleatorio .arff es del tipo para Market Basket.
     * @return Un <code>DataSet</code> con los datos comprimidos en un Ã¡rbol
     *         N-Ario.
     */
    public DataSet buildMultivaluedDataset() {
        DataSet tree = new DataSet("contact");
        
        try{
            short node;
            String attr = "";
            String cad = "start";
            StringTokenizer data;
            int tokenSize;
            boolean flagSortDictionary = true;
            
            long pos=0;
            outChannel.seek(0);
            long fileSize = outChannel.length();
            
            while (pos<fileSize) {
                if(!cad.equalsIgnoreCase("@data")) {
                    cad = outChannel.readLine();
                    pos = outChannel.getFilePointer();
                    if(cad != null && cad.length()>10 && cad.substring(0, 10).equalsIgnoreCase("@attribute")) {
                        attr = attr + cad;
                    }
                } else {
                    if(flagSortDictionary){
                        tree.buildMultivaluedDictionary(attr);
                        tree.sortDictionary();
                        flagSortDictionary = false;
                    }
                    
                    attr = outChannel.readLine();
                    pos = outChannel.getFilePointer();
                    
                    // La linea en blanco no se tiene en cuenta.
                    if (attr != null) {
                        data = new StringTokenizer(attr, ",");
                        tokenSize = data.countTokens();
                        for(int index=0; index<tokenSize-1; index++) {
                            attr = data.nextToken().trim();
                            if ( !attr.equals("?") ) {
                                // Si el conjunto es de Market Basket el arreglo
                                // de los nombres de los atributos, solo tiene
                                // 1 atributo, el stock de productos.
                                /*if (isMarketB) {
                                    attr = (String) tree.getAttrNames().get(0) +
                                            "." + attr;
                                } else {*/
                                attr = (String) tree.getAttrNames().get(index) +
                                        "=" + attr;
                                //}
                            }
                            node = tree.codeAttribute(attr.trim());
                            if ( node >= 0 ) {
                                tree.buildNTree(node, index);
                            }
                        }
                        attr = data.nextToken().trim();
                        if ( !attr.equals("?") ) {
                            // Si el conjunto es de Market Basket el arreglo
                            // de los nombres de los atributos, solo tiene
                            // 1 atributo, el stock de productos.
                            /*if (isMarketB) {
                                attr = (String) tree.getAttrNames().get(0) +
                                        "." + attr;
                            } else {*/
                            attr = (String) tree.getAttrNames().get(tokenSize-1) +
                                    "=" + attr;
                            //}
                        }
                        node = tree.codeAttribute(attr.trim());
                        if(node >= 0){
                            tree.buildNTree(node, -1);
                        }
                    }
                }
            }
        } catch(EOFException e1) {
            this.closeFile();
        } catch(IOException e2) {
            e2.printStackTrace();
        } finally {
            //tree.showNTree();
            return tree;
            //tree.saveTree("/home/and/Reportes/ntree.tariy");
            //tree.rollBackTree("/home/and/Reportes/ntree.tariy");
        }
    }
    
    /**
     * Retorna el <code>DataSet</code> que se construye a partir de un archivo
     * de acceso aleatorio de formato .arff de tipo univaluado o binario.
     *
     * @return Un <code>DataSet</code> con los datos comprimidos en un Ã¡rbol
     *         N-Ario.
     */
    public DataSet buildUnivaluedDataSet() {
        DataSet tree = new DataSet("contact");
        
        try{
            String cad = "start";
            String attr = "";
            long fileSize;
            String item1 = "";
            String item2 = "";
            String cab = "";
            String cab2;
            StringTokenizer data;
            short node1 = 0;
            short node2 = 0;
            long filePos = 0;
            int id;
            boolean flagSortDictionary = true;
            boolean start = true;
            boolean end = false;
            
            outChannel.seek(0);
            fileSize = outChannel.length();
            long i = 0;
            while (i<fileSize) {
                if(!cad.equalsIgnoreCase("@data")) {
                    cad = outChannel.readLine();
                    i = outChannel.getFilePointer();
                    if(cad.length()>10 &&
                            cad.substring(0, 10).equalsIgnoreCase("@attribute")) {
                        attr = attr + cad;
                    }
                } else {
                    if(flagSortDictionary){
                        tree.buildUnivaluedDictionary(attr);
                        tree.sortDictionary();
                        flagSortDictionary = false;
                        
                        attr = outChannel.readLine();
                        data = new StringTokenizer(attr, ",");
                        cab2 = data.nextToken();
                        cab = cab2;
                        item1 = data.nextToken().trim();
                        node1 = tree.codeAttribute(item1);
                    }
                    
                    id = 1;
                    attr = outChannel.readLine();
                    data = new StringTokenizer(attr, ",");
                    cab2 = data.nextToken().trim();
                    item2 = data.nextToken().trim();
                    node2 = tree.codeAttribute(item2);
                    
                    if (!cab.equals(cab2)) {
                        end = true;
                        cab = cab2;
                    }
                    if (start) {
                        id = 0;
                        start = false;
                    }
                    if (end) {
                        if (id == 0 && end) {
                            id = -2;
                        } else {
                            id = -1;
                        }
                        end = false;
                        start = true;
                    }
                    tree.buildNTree(node1, id);
                    node1 = node2;
                }
                if (start) {
                    id = -2;
                } else {
                    id = -1;
                }
                tree.buildNTree(node1, id);
            }
        } catch (EOFException e1) {
            this.closeFile();
        } catch (IOException e2) {
            e2.printStackTrace();
        } finally {
            //tree.showNTree();
            return tree;
            //tree.saveTree("/home/and/Reportes/ntree.tariy");
            //tree.rollBackTree("/home/and/Reportes/ntree.tariy");
        }
    }
    
    /**
     * Retorna una matriz que representa un archivo de acceso aleatorio .arff con
     * los nombres de sus atributos y sus datos.
     */
    public Object[][] buildDataMatrix() {
        dataAndAttributes(false);
        
        int rows = getData().length + 1;
        int cols = getAttributes().length;
        Object[][] matrix = new Object[rows][cols];
        
        for (int c=0; c<cols; c++) {
            matrix[0][c] = attributes[c];
        }
        
        for (int r=1; r<rows; r++) {
            for (int c=0; c<cols; c++) {
                matrix[r][c] = data[r-1][c];
            }
        }
        
// Imprime los atributos y datos de un archivo .arff.
//        for (int j=0; j<rows; j++) {
//            for (int h=0; h<cols; h++) {
//                System.out.print(matrix[j][h] + " * ");
//            }
//            System.out.print("\n" + (j+1) + ". ");
//        }
        
        return matrix;
    }
    
    public static void main(String[] arg) {
        FileManager ar = new FileManager("/home/ivan/tariy/cDatos/arff/nominales/bd2_5k.arff");
        ar.dataAndAttributes(false);
    }
}
