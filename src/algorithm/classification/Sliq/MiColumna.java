/*
 * MiColumna.java
 *
 * Created on 3 de agosto de 2007, 04:55 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package algorithm.classification.Sliq;

import java.util.ArrayList;
import java.util.Vector;
/**
 *
  */
public  class MiColumna{
 
     public RegistroIndexado[] datos;
     public String tipoDato;
     public String nomColumna;
     public boolean tipoClase;
     public Histograma histograma;
     public ArrayList frecuencia;
     
     public MiColumna(RegistroIndexado[] adatos,String atipoDato,String aNom ){
         datos=adatos;
         tipoDato=atipoDato;
         nomColumna=aNom;
         tipoClase=false;
                 
     }
      public MiColumna(){
      
     }
      
      public void ordenarDatos(){
         int i,j;
         RegistroIndexado aux;
         
         for(i=0;i<datos.length-1;i++){
            
             for(j=i+1;j<datos.length;j++){
                 if( Double.valueOf(datos[j].valor_dato.toString()) < Double.valueOf(datos[i].valor_dato.toString()) ){
                    
                    aux=datos[i];
                    datos[i]=datos[j];
                    datos[j]=aux;
                }
            } 
         }
         
     }
      
    public Vector datosUnicos(){
        //Object [] valDiferentes;
        Vector encontrados;
        int i,j,cantUnicos,indice;
        
        encontrados=new Vector();
         frecuencia=new ArrayList();
        // adiciono el valor al vector
        encontrados.add(datos[0].valor_dato);          
        frecuencia.add(1); 
          
          for(i=1;i<datos.length;i++ ){
            //miro si el valor ya existe en el vector si es -1 no esta
            indice = encontrados.indexOf(datos[i].valor_dato);
            if(indice ==-1 ){
              //adiciono el nuevo valor
                encontrados.add(datos[i].valor_dato);
                frecuencia.add(1);
            }else{
                    
                frecuencia.set( indice, (((Integer)frecuencia.get(indice))+1));
            }
            
          } 
         return encontrados;
    }
     
         
     
  }
