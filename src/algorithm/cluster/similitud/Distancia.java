/*
 * Distancia.java
 *
 * Created on 13 de mayo de 2009, 14:55
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package algorithm.cluster.similitud;

import algorithm.cluster.Atributo;
import algorithm.cluster.Datos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 *
 *
 */
public class Distancia implements Serializable{
    private static final long serialVersionUID = 4233307322892096569L;
   
//   private static final long serialVersionUID = 1L; 
    
   private int tipo;
   private Object[] atColumna;
   private Atributo atri;
     
   public double euclidiana(Datos obj1,Datos obj2){
     Double distancia=0.0;
     double subtotal=0.0;
      int j=0;
      List<Double> l1=new ArrayList<Double>();
      List<Double> l2=new ArrayList<Double>();

      l1=obj1.obtenerLista();
      l2=obj2.obtenerLista();

            while (j < l1.size()){
       /*     atri=(Atributo) atColumna[j];
          if(atri.getDesabilitar()==false)*/
               subtotal    =subtotal+ Math.pow(Math.abs(l1.get(j)-l2.get(j)),2);
          
            j++;

            }
        distancia=Math.sqrt(subtotal);
        return distancia;



   }
     
    public double Manhattan(Datos obj1,Datos obj2){
     Double distancia=0.0;
     double subtotal=0.0;
      int j=0;
      List<Double> l1=new ArrayList<Double>();
      List<Double> l2=new ArrayList<Double>();

      l1=obj1.obtenerLista();
      l2=obj2.obtenerLista();

            while (j < l1.size()){
            atri=(Atributo) atColumna[j];
          if(atri.getDesabilitar()==false)
               subtotal    =subtotal+ Math.abs(l1.get(j)-l2.get(j));
          
            j++;

            }
      
        return subtotal;



   }  
     
     
public void setAtriColumna(Object[] at){
    this.atColumna=at;
}     
     
public void setTipo(int ti){
      this.tipo=ti;
    
 }
public int getTipo(){
    return tipo;
}
     public double getDistancia(Datos obj1,Datos obj2){
      
         switch(tipo) {
             case 0:
                 return euclidiana(obj1, obj2);
        
             case 1:
                 return Manhattan(obj1,obj2);
              
         }
         return 0.0;
     } 
      
}
