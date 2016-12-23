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
////////import com.sun.org.apache.bcel.internal.generic.ConversionInstruction;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author vismine
 */
public class DistanciaM implements Serializable{
    private static final long serialVersionUID = 6021984112597698679L;
   
//   private static final long serialVersionUID = 1L; 
    
   private int tipo;
   private Object[] atColumna = null;
   private Atributo atri;
   
   public double euclidiana(Object[] obj1,Object[] obj2){
   double distancia = 0.0;
   double subtotal = 0.0;
   double da1;
   double da2;
     
    int j=0;
    while (j < obj1.length){
    //   System.out.println("objeto da1 "+obj1[j]);
        atri = (Atributo) atColumna[j];
        
//        aqui tocaria buscar algun modo que mirando si es entero haga las cosas
        if(atri.getClase().equalsIgnoreCase("Integer") || atri.getClase().equalsIgnoreCase("Double")){ //atri.getDesabilitar()==false
            da1 = Double.parseDouble((obj1[j]).toString());
            da2 = Double.parseDouble((obj2[j]).toString());
            subtotal = subtotal + Math.pow(Math.abs(da1 - da2),2);
        }
        j++;
    }
    distancia = Math.sqrt(subtotal);
    return distancia;

   }
     
    public double Manhattan(Object[] obj1,Object[] obj2){
     Double distancia=0.0;
     double subtotal=0.0;
     int j=0;
     double da1;
     double da2;

            while (j <obj1.length){
                atri = (Atributo) atColumna[j];
                atri.getTipo();

                //aqui tocaria buscar algun modo que mirando si es entero haga las cosas
               if(atri.getClase().equalsIgnoreCase("Integer") || atri.getClase().equalsIgnoreCase("Double")){  // if(atri.getDesabilitar()==false){
                  da1 = Double.parseDouble((obj1[j]).toString());
                  da2 = Double.parseDouble((obj2[j]).toString());
                  subtotal = subtotal + Math.abs(da1-da2);
                }
                
                j++;
            }
      
        return subtotal;
   }  
    
 public double hamming(Object[] obj1,Object[] obj2){
     double distancia=0.0;
     double subtotal=0.0;
     double da1;
     double da2;
     
      int j=0;
           
            while (j < obj1.length){
        //   System.out.println("objeto da1 "+obj1[j]);
             atri = (Atributo) atColumna[j];
             
             if(atri.getDesabilitar() == false){
                 if(obj1[j].equals(obj2[j])){
                     
                 }else{
                    subtotal++ ;
                 }
              }  
            j++;
            }
     
        return distancia;
   }    
     
public void setAtriColumna(Object[] at){
   this.atColumna = at;
}  

public Object[] getAtriColumna(){
   return this.atColumna;
} 
    
public void setTipo(int ti){
   this.tipo = ti;
 }

public int getTipo(){
    return tipo;
}

public double getDistancia(Object[] obj1, Object[] obj2){
      
         switch(tipo) {
             case 0:
                 return euclidiana(obj1, obj2);
             case 1:
                 return Manhattan(obj1,obj2);
             case 2:
                 return hamming(obj1,obj2);
         }
         return 0.0;
     } 
      
}
