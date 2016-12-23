/*
 * clarans.java
 *
 * Created on 26 de mayo de 2009, 9:31
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package algorithm.cluster.clarans;

/**
 *
 * @author xp
 */
import algorithm.classification.TableModelImport;
import algorithm.cluster.Atributo;

import algorithm.cluster.similitud.DistanciaM;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author xp
 */

public class clarans {
       private String archivo="";
       String columnas[];

       private AbstractTableModel medoide;
       int DATO_GRADE=999999999;
       double DATO_GRANDE2=999999999.0;
       private int numcluster=2;
       private int numlocal=2;
       private int maxvecinos=4;
       int numIteraciones;
           private  Object[][] datosTemp;
       private AbstractTableModel dataInt;
       private AbstractTableModel dataSalida;
       private int numInterar;
       private int [] centroideCerca; 
       private int fil,col;
         private   String[] columnsName; 
       private   DistanciaM distancia; 
        private int [] centroideCercaF;
         private Object[] atColumna;
   private Atributo atri;
 public void ingresarArchivo(String ar){
        archivo=ar;

         } 
 public void setNumCluster(int num){
     numcluster=num;
 }
 public void setMaxVecinos(int num){
     maxvecinos=num;
 }
 public void setNumLocal(int num){
     numlocal=num;
 }
 public void setDataIn(AbstractTableModel dataI){
     this.dataInt=dataI;
 
 }

 public int[] getCentroidecerca(){
     return this.centroideCercaF;
 }
 public AbstractTableModel  getMedoide(){
     return medoide;
 }
 public void  setNumIterar(int numite){
     this.numInterar=numite;
 }
 public void setDistancia(DistanciaM distancia){
    this.distancia=distancia;
}

//ESTE MEDOTO ES EL PRICIPAL DE AQUI SE EJECUTAN LOS DEMAS METODOS
   public void ejecutar(){
  fil=dataInt.getRowCount();
  col=dataInt.getColumnCount();
    
    if (distancia.getAtriColumna()==null){
    atColumna=new Object[col];
     Atributo atributo;
           for(int i = 0; i < col; i++){
                    
                   atributo=new Atributo();
                     atColumna[i]=atributo;
              
                   
           } 
     distancia.setAtriColumna(atColumna);
       }else{
     
     atColumna=distancia.getAtriColumna();
     }
  
  
 
mostrar(dataInt);
  iniciamedoide();
  int [] centrocer;
 //int [] centrocer= realizacluster();
// nuevoMedoides(centrocer);

AbstractTableModel nue_medoide;
AbstractTableModel mecopia;
mecopia=medoide;

boolean continua=true;
int maximo=0;
int contador=0;
int numite=0;
while (continua){
    centrocer=realizacluster();
    nue_medoide=nuevoMedoides();
   System.out.println("-comparando ----------MEDOIDE ANTERIOR " );

   if (calcularIgualdad(medoide, nue_medoide)){
      continua=false;
      medoide=nue_medoide;
      System.out.println("----los medoides son iguales   -- " );
     }else{
            System.out.println("----los medoides han cambiado,se ejecuta nuevo ciclo   -- " );

     }
numite++;
   if (numite>=numIteraciones && numIteraciones != 0){
    continua=false;
    medoide=nue_medoide;
      System.out.println("----finalizo el numero de iteraciones   -- " );
   }


   mecopia=medoide;
  medoide = nue_medoide;

contador++;
 
  }

  System.out.println("----numero de iteraciones   -- "+ contador );
 

   }

private AbstractTableModel nuevoMedoides(){
  //calcular el nuevo medoide
  int t=0;


AbstractTableModel medoideTemp; 
Object[][] datosTemp = new Object[numcluster][col]; 

  //////////////
Object[] filaActu=new Object[col];
Object[][] clusterActual =new Object[fil][col];
  int contador,i,j;
  
    for (int clu=0;clu<numcluster;clu++){
           System.out.println("cluster "+clu);
                
             contador=0;
       for ( i=0;i<fil;i++){
             
               for ( j=0;j<col;j++){
                   if (centroideCercaF[i] == clu ){
                      clusterActual[contador][j]=dataInt.getValueAt(i,j);
                       
                    
                       
                   }
                                       
               }
               
             if (centroideCercaF[i] == clu )
                contador++;
       
          }
  
             
             Object[][] clusObtenido=new Object[contador][col];
             Object[] medFila=new Object[col];
             
             for (i=0;i<contador;i++){
                 for(j=0;j<col;j++)
                 clusObtenido[i][j] =  clusterActual[i][j];
                
             }
          
           medFila = MejorMedoide(clusObtenido,clu);
             
           mostrarfila(medFila);
           
           for(j=0;j<col;j++)
               datosTemp[clu][j]=medFila[j];
             
             
             
             
    }
  
  
  medoideTemp=new TableModelImport(datosTemp,columnsName); 
     System.out.println("nuevo medoide");
  mostrar(medoideTemp);     
  
  return medoideTemp;   
  
 }

private void mostrarfila(Object[] fi){
    for (int i=0;i<col;i++){
        System.out.print(" "+fi[i]);
    }
}

  private Object[] MejorMedoide(Object[][] cluActual,int cluac){
   int i,j,n;
      Object[] posMedoide=new Object[col];
      Object[] fila=new Object[col];
      Object[] mejorFila=new Object[col];
      
   int nfil=cluActual.length;
   System.out.println("Numero de elementos"+nfil);
   int veme,pome;

   int maxv=maxvecinos;
   if(maxv > nfil-1)
       maxv=nfil-1;
   
   int[] meve=new int[maxv];
   int[] mepo=new int[numlocal];
   
   meve=randomicos(maxv,0,nfil);
   mepo=randomicos(numlocal,0,nfil);
   
   double sumDistancia=0.0;
   
   
  double sumamejornodo=DATO_GRANDE2;
   //obtener sumatoria de distancia de medoide anterior  
  Object[] meAnt=new Object[col]; 
             
             for(j=0;j<col;j++)
                 meAnt[j]=medoide.getValueAt(cluac,j);
            
               System.out.println("Medoide ant de este cluster " + cluac);
                mostrarfila(meAnt);
    
             for (i=0;i<maxv;i++){
                    veme=meve[i];
                 for(j=0;j<col;j++)
                     fila[j]=cluActual[veme][j];
                 mostrarfila(fila);
                 
                sumDistancia=sumDistancia + distancia.getDistancia(meAnt,fila);
                System.out.println("distancia "+sumDistancia);
             }
   
  sumamejornodo=sumDistancia;
  mejorFila=meAnt;
  /////////////////////////////////////

   Random rando= new Random();       
 for (n=0;n<numlocal;n++){
       pome=mepo[n];
     
       for(j=0;j<col;j++)
        posMedoide[j]=cluActual[pome][j];
        
       sumDistancia=0.0;
        for (i=0;i<maxv;i++){ //cmabiar aqui para realizar sumatoria solo de maxivecinos
            veme=meve[i];
                 for(j=0;j<col;j++)
                   fila[j]=cluActual[veme][j]; 
          
          sumDistancia=sumDistancia + distancia.getDistancia(posMedoide,fila);
        
      }
      
       if(sumDistancia < sumamejornodo){
           sumamejornodo=sumDistancia;
           mejorFila=fila;
       }
      
   } 
      return mejorFila;
  } 



     //ESTE METODO RETORNA UN OBJETO  QUE CONTINE LOS CLUSTER OBTENIDOS PARA CADA MEDOIDE
    private int[] realizacluster(){
   Double distancia1=0.0;
  Double distancia2=DATO_GRANDE2;

  AbstractTableModel[]  clustemp;
  int [] centroidecerca =new  int[fil];


  Object[] filaActu = new Object[col]; 
     Object[] filaMedoide = new Object[col]; 
   
  
       int t=0;
   

      for (int i=0;i<fil;i++){
               for (int j=0;j<col;j++){
                   
                  filaActu[j]=dataInt.getValueAt(i,j);      
               }
         
                  distancia2=DATO_GRANDE2;
                  distancia1=0.0;
                             
               for (int m=0;m<numcluster;m++){
                    for (int y=0;y<col;y++){
                    filaMedoide[y]= medoide.getValueAt(m,y);
               
                    }
                  distancia1=distancia.getDistancia(filaMedoide,filaActu); 
            //     System.out.println("Centroide "+ m +" objeto "+ i +"distancia "+distancia1 + "distancia2 " + distancia2+" ");
                  
             
             
                     if (distancia1 < distancia2){
                      centroidecerca[i]=m;
                     distancia2=distancia1;
                }
               }
                 
                         
                 
          }
            
         
     int cuentaObje=0;
       for (int clu=0;clu<numcluster;clu++){
           System.out.println("cluster "+clu);
  cuentaObje=0;
       for (int i=0;i<fil;i++){
               for (int j=0;j<col;j++){
                   if (centroidecerca[i] == clu){
                       System.out.print("  "+dataInt.getValueAt(i,j) );
                       cuentaObje++;
                         }
                      
               }
               
            }
            System.out.println("");
            if(cuentaObje==0){
               System.out.println("El cluster esta vacio, debe eliminar repetidos "+clu); 
                    
            }
          }
       centroideCercaF=centroidecerca;
       return centroidecerca;
     }


 //MUESTRA LOS CLUSTER OBTENIDOS DEL METOD REALIZAR CLUSTER, RECIBE EL OBJETO LINKEDLIST clusterpam
   public void mostrar(AbstractTableModel tabla){
        for (int i=0;i<tabla.getRowCount();i++){
            for (int j=0;j<tabla.getColumnCount();j++){
                System.out.print("   "+tabla.getValueAt(i,j));
            }
             System.out.println("");
        }
    }




/*
  //ESTE METODO CREA LOS MEDOIDES INICIALES ALEATORIAMENTE DEBE RECIBIR EL NUMERO DE MEDOIDES A TOMAR
    private void iniciamedoide(){
    
   Object[][]   datosTemp = new Object[numcluster][col]; 
    
    columnsName = new String[col];
      for(int t = 0; t < col; t++){
          columnsName[t] = dataInt.getColumnName(t);
      }
   int[] arre=randomicos(numcluster,0,fil);
          int fi;
       for (int k=0;k<numcluster;k++)
       {   System.out.println(arre[k]);
         fi=arre[k];
               for (int j=0;j<col;j++){
                   datosTemp[k][j]=dataInt.getValueAt(fi,j);
                   
               }
          
       }
     
   
     medoide=new TableModelImport(datosTemp,columnsName); 
     System.out.println("medoides iniciales -------");
    mostrar(medoide);


    }
 **/
 private int[] randomicos(int tam,int min,int max){ // retorna un arreglo de numeros aleatoriamente sin repetidos
        int conta=0;                                // tam= numero de numeros que tendra el arrelgo   
        Random rand= new Random();                  // min - max = rango en el cual se busca los nuemero aleatorios
       int[] arre=new int[tam];
        int numiteraciones=0;
      
              
        for (int i=0;i<tam;i++){
            numiteraciones++;
         int randomico=rand.nextInt(max);
          if (siexiste(arre,randomico) || randomico < min){
             i--;
          } else
          {
             arre[conta]=randomico;
             conta++;
          }
         
           if (numiteraciones>=30){
             for (int x=i+1;x<tam;x++){
         
             int rando=rand.nextInt(max); 
              arre[conta]=rando;
             conta++;
             }
             return arre;
           }
         
         } 
       return arre;
    }
    
    
    private boolean siexiste(int[] arreglo,int com){ //verifica si ya existe el numero randomico en el arreglo
        for (int i=0;i<arreglo.length;i++){
            if (arreglo[i] == com){
                return true;
            }           
            
        }
        return false;
    }


   
   //////////
   
   //ESTE METODO CREA LOS MEDOIDES INICIALES  DEBE RECIBIR EL NUMERO DE MEDOIDES A TOMAR
    private void iniciamedoide(){
      
   datosTemp = new Object[numcluster][col]; 
  Object [] nuefila=new Object[col];
        columnsName = new String[col];
      for(int t = 0; t < col; t++){
          columnsName[t] = dataInt.getColumnName(t);
      }
        int con=0;
        int nc=0;
  while(con <numcluster && nc < fil){
      
          for (int j=0;j<col;j++){
              nuefila[j]=dataInt.getValueAt(nc,j);
             
              }
       
          if(verExiste(nuefila,datosTemp)){
             nc++;
          }else{
               for (int j=0;j<col;j++){
             datosTemp[con][j]=dataInt.getValueAt(nc,j);
          
              }
            con++;
            nc++;
          }
          
          
          
    }
   if (con < numcluster){
            for(int i=con;i<numcluster;i++){
                for(int j=0;j<dataInt.getColumnCount();j++)
               datosTemp[i][j]=0;  
            }
   }     
  medoide=new TableModelImport(datosTemp,columnsName); 
     System.out.println("medoides iniciales");
  mostrar(medoide);
    }
    
    private boolean verExiste(Object fila[],Object datos[][]){
       boolean ret=false;
        Object [] nuefila=new Object[col];
        int con=0;
      String da1;
      String da2;
   
        for (int i=0; i< numcluster;i++){
          con=0;
             for (int j=0;j<col;j++){
                 atri=(Atributo) atColumna[j];
              if(atri.getDesabilitar()==true){
              con++;
              }
              nuefila[j]=datos[i][j];
            //  System.out.println("nue fila ="+datos[i][j] +" filaant = "+ fila[j]);
              da1=""+datos[i][j];
                da2=""+fila[j];
                
                    
                    if(da1.equals(da2)){
                        con++;
                   
                    }
                
            }
             System.out.println("-------------");
          if (con==col){
              ret=true;
               System.out.println("datos iguales");
              return ret;
          
          }    
       
    }
        
        return ret;
    }
   
   //////////
     public boolean calcularIgualdad(AbstractTableModel tabla1,AbstractTableModel tabla2){
      boolean op=true;
     for (int i=0;i<tabla1.getRowCount();i++){
               for (int j=0;j<tabla1.getColumnCount();j++){
                   if (tabla1.getValueAt(i,j).equals(tabla2.getValueAt(i,j))){
                                            
                         }else{
                         
                         System.out.println("Datos diferentes "+ tabla1.getValueAt(i,j) +" con "+tabla2.getValueAt(i,j));
                         op=false;
                         }
                      
               }
            }
     
    
     return op;
 }
 
}



