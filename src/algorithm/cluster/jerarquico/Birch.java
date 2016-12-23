/*
 * Birch.java
 *
 * Created on 26 de enero de 2010, 09:54 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package algorithm.cluster.jerarquico;

import Utils.TreeViewer.Node;
import algorithm.cluster.kmeans.kmeansM;
import algorithm.cluster.Atributo;
import algorithm.cluster.similitud.DistanciaM;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author vismine
 */
public class Birch  {
      String columnas[];
        private   DistanciaM distancia; 
       private AbstractTableModel[] clusterkm;
       private AbstractTableModel medoide;
       private AbstractTableModel dataInt;
       private AbstractTableModel dataOri;
       
       int DATO_GRADE=999999999;
       double DATO_GRANDE2=999999999.0;
       int numcluster=2;
       int numIteraciones;
       int cantidadclu;
      int fil; 
      int col;
       private   String[] columnsName; 
       private  Object[][] datosTemp;
       private int [] centroideCercaF;
       private Object[] atColumna;
      private Atributo atri;
      private AbstractTableModel c1,c2,c1ori,c2ori;
       kmeansM kme;
         Nodo nueNodo=null;
              
        protected  Nodo raiz=null;
            
        public Birch() {
            raiz=new Nodo();
            kme=new kmeansM();
        
           
    } 
        

    
     ///////////
    /** Creates a new instance of Birch */
   
    public void setDataint(AbstractTableModel datosInt){
      this.dataInt=datosInt;
    }
    public void setDataori(AbstractTableModel datosori){
      this.dataOri=datosori;
    }
public void setDistancia(DistanciaM distancia){
    this.distancia=distancia;
}
public void setCantidad(int can){
    this.cantidadclu=can;
}
 public void setNumcluster(int num){
        this.numcluster=num;
    }
     public void setNumIterar(int num){
        this.numIteraciones=num;
    }
     
     public Nodo getNodo(){
         return this.raiz;
     }
    
    public AbstractTableModel getMedoide(){
       return medoide; 
    }
    public int [] getCentroideCerca(){
        return centroideCercaF;
    }
     public void   mostrarInorden(Nodo r,boolean NodoRaiz){
          Nodo actu=null;
          
          if (NodoRaiz)
              actu=raiz;
          else
              actu=r;
          
          if(actu != null){
              mostrarInorden(actu.izquierda,false);
              System.out.println("nivel "+actu.nivel);
              mostrar(actu.dataNodo);
             mostrarInorden(actu.derecha,false);
          }
       
      
    }
    public void llenarDatos(Nodo r,boolean NodoRaiz,int _nivel){
        Nodo actual=null;
         if (NodoRaiz){
            actual=raiz;
            }
        else{
            actual=r;
                      
        }
        if (actual.cantObjetos > cantidadclu && actual !=null){ //si tienemas de un objeto lo dividimos
          
              
         kme.setDataint(actual.dataNodo);
         kme.setDataOri(actual.dataOri);
         kme.ejecutar();
         kme.divCluster();
         c1=kme.getClu1();
         c2=kme.getClu2();
         c1ori=kme.getClu1ori();
         c2ori=kme.getClu2ori();
          
         if (c1.getRowCount()==actual.cantObjetos || c2.getRowCount()==actual.cantObjetos){
             System.out.println("cluster no cambio");
         }else{
         
           nueNodo =new Nodo();
           nueNodo.dataNodo=c1;
           nueNodo.dataOri=c1ori;
           nueNodo.nivel=_nivel;
           nueNodo.cantObjetos=c1.getRowCount();
           nueNodo.izquierda=null;
           nueNodo.derecha=null;
           actual.izquierda=nueNodo;
       
           nueNodo =new Nodo();
           nueNodo.dataNodo=c2;
           nueNodo.dataOri=c2ori;
           nueNodo.nivel=_nivel;
           nueNodo.cantObjetos=c2.getRowCount();
           nueNodo.izquierda=null;
           nueNodo.derecha=null;
           actual.derecha=nueNodo;
          
           
           
           
          llenarDatos(actual.izquierda,false,_nivel+1);
          llenarDatos(actual.derecha,false,_nivel+1);
         }
        }
    }
    
    public void ejecutar(){
        kme.setNumcluster(2);
         kme.setDistancia(distancia);
         
     Nodo actual=new Nodo();
            
           
         nueNodo =new Nodo();
         nueNodo.dataNodo=dataInt;
         nueNodo.dataOri=dataOri;
         nueNodo.nivel=0;
         nueNodo.cantObjetos=dataInt.getRowCount();
         nueNodo.izquierda=null;
         nueNodo.derecha=null;
         raiz=nueNodo;
         
                    
            
         mostrar(raiz.dataNodo);    
         llenarDatos(raiz,true,1);
       /*  
         actual=raiz; 
    
       
       //llenando el arbol manualmente ..............
       nueNodo =new Nodo();
        
        
         kme.setDataint(dataInt);
         kme.ejecutar();
         kme.divCluster();
         c1=kme.getClu1();
         c2=kme.getClu2();
         
        nueNodo =new Nodo();
        nueNodo.dataNodo=c1;
        nueNodo.izquierda=null;
        nueNodo.derecha=null;
         actual.izquierda=nueNodo;
       
       
         
          nueNodo =new Nodo();
          nueNodo.dataNodo=c2;
          nueNodo.izquierda=null;
          nueNodo.derecha=null;
          actual.derecha=nueNodo;
        
        
         
        System.out.println("segunda divicion ..... clu 1");
        kme.setDataint(c1);
        kme.ejecutar();
        kme.divCluster();
          c1=kme.getClu1();
         c2=kme.getClu2();
         
        actual=actual.izquierda;
        mostrar(raiz.dataNodo);
        
         nueNodo =new Nodo();
         nueNodo.dataNodo=c1;
         nueNodo.izquierda=null;
         nueNodo.derecha=null;
         actual.izquierda=nueNodo;
         mostrar(raiz.dataNodo); 
         nueNodo =new Nodo();
         nueNodo.dataNodo=c2;
         nueNodo.izquierda=null;
         nueNodo.derecha=null;
         actual.derecha=nueNodo;
        
        
          System.out.println("segunda divicion ..... clu 2");
          kme.setDataint(c2);
          kme.ejecutar();
          kme.divCluster();  
        
         c1=kme.getClu1();
         c2=kme.getClu2();
         
         actual=actual.derecha;
         nueNodo =new Nodo();
         nueNodo.dataNodo=c1;
         nueNodo.izquierda=null;
         nueNodo.derecha=null;
         actual.izquierda=nueNodo;
        
         nueNodo =new Nodo();
         nueNodo.dataNodo=c2;
         nueNodo.izquierda=null;
         nueNodo.derecha=null;
         actual.derecha=nueNodo;
       
        
      System.out.println("mostrar inorden... cl");  */
         
      mostrarInorden(raiz,true);
    }
    
    

    
      public void mostrar(AbstractTableModel tabla){
            System.out.println("mostrando datos recursivamente inorden hoy");
        for (int i=0;i<tabla.getRowCount();i++){
            for (int j=0;j<tabla.getColumnCount();j++){
                System.out.print("   "+tabla.getValueAt(i,j));
            }
             System.out.println("");
        }
    }
      
}      
    
      
