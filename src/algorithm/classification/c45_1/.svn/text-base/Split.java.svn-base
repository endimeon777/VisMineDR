/*
 * Split.java
 *
 * Created on 22 de diciembre de 2006, 10:44
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package algorithm.classification.c45_1;

/**
 *
 * @author and
 */
public class Split {
    public String name;
    public String[] names;
    public Object[][] data; 
    int index;

    /** Creates a new instance of Split */
    public Split(String name, int rows) {
        this.name = name;
        data = new Object[rows][];
        index = 0;
    }
    
    public void addRow(Object[] row){
        data[index++] = row;
    }

    public Object[][] getData() {
        return data;
    }

    public String getName() {
        return name;
    }
    
    public String toString(){
        StringBuffer str = new StringBuffer();

        str.append(name + ":\n");
        for(int i = 0; i < data.length; i++){
            for(int j = 0; j < data[i].length; j++){
                str.append(data[i][j].toString() + ", ");
            }
            str.append("\n");
        }
        return str.toString();
    }
}
