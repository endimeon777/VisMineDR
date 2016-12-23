/*
 * Datos.java
 *
 * Created on 13 de mayo de 2009, 14:51
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package algorithm.cluster;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author xp
 */
public class Datos {

private List<Double> l;
    private String da="";
    public Datos(){}
    public Datos(String ar)
     {
           l =new ArrayList<Double>();
           Double tnum;
    
     
        StringTokenizer st= new StringTokenizer(ar,", ");
        while(st.hasMoreTokens()){
            da=st.nextToken();
            try {
          
                     tnum=Double.parseDouble(da);
                     
               
            } catch (NumberFormatException ex) {
              tnum=0.0;
                System.out.println("Error en la conversio de datos, el valor se ha pasado a 0.0");
            }
            
            l.add(tnum);
        }

     }
  
      public List<Double> obtenerLista()
    {
    	return l;
    }
  public int size(){
    return  l.size();
  }

  public void setLista(List<Double> lis){
          this.l=lis;
  }
  public boolean esigual(Datos dato){
     
      for (int i=0;i<dato.obtenerLista().size();i++){
          if (dato.obtenerLista().get(i).equals(l.get(i))==false){
              return false;
          }
      }
      return true;
  }

}
