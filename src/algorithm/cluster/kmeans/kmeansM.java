/*
 * kmeans.java
 *
 * Created on 13 de mayo de 2009, 14:52
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package algorithm.cluster.kmeans;


import algorithm.classification.TableModelImport;
import algorithm.cluster.Atributo;
import algorithm.cluster.similitud.DistanciaM;
//////////import com.sun.org.apache.bcel.internal.generic.ASTORE;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.crypto.NullCipher;
import javax.swing.JTextArea;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author vismine
 */
public class kmeansM {
       String columnas[];
       private AbstractTableModel[] clusterkm;
       private AbstractTableModel medoide;
       private AbstractTableModel dataInt;
       private AbstractTableModel dataOri;
        
       private AbstractTableModel clu1;
       private AbstractTableModel clu2;
       private AbstractTableModel clu1Ori;
       private AbstractTableModel clu2Ori;
       
       int DATO_GRADE=999999999;
       double DATO_GRANDE2=999999999.0;
       int numcluster=2;
       int numIteraciones;
       private DistanciaM distancia; 
       int fil; 
       int col;
       private   String[] columnsName; 
       private  Object[][] datosTemp;
       private int [] centroideCercaF;
       private Object[] atColumna;
       private Atributo atri;
//JTextArea jta;

public void setDataint(AbstractTableModel datosInt){
    this.dataInt = datosInt;
}
public void setDataOri(AbstractTableModel datosOri){
    this.dataOri=datosOri;
}
public void setDistancia(DistanciaM distancia){
    this.distancia=distancia;
}
 public void setNumcluster(int num){
        this.numcluster=num;
    }
     public void setNumIterar(int num){
        this.numIteraciones=num;
    }
      
    public AbstractTableModel getMedoide(){
       return medoide; 
    }
    public int [] getCentroideCerca(){
        return centroideCercaF;
    }
    public void mostrar(AbstractTableModel tabla){
        for (int i=0;i<tabla.getRowCount();i++){
            for (int j=0;j<tabla.getColumnCount();j++){
                System.out.print("   "+tabla.getValueAt(i,j));
            }
             System.out.println("");
        }
    }
 public void ejecutar(){

     fil = dataInt.getRowCount();
     col = dataInt.getColumnCount();
     
    if (distancia.getAtriColumna() == null){
        atColumna=new Object[col];
        Atributo atributo;
               for(int i = 0; i < col; i++){
                   atributo=new Atributo();
                   atColumna[i]=atributo;
               } 
           distancia.setAtriColumna(atColumna);
       }else{
           atColumna = distancia.getAtriColumna();
     }

//mostrar(dataInt);

  iniciamedoide();
  if(dataInt==null){
      System.out.println("Dataint esta null");
  }
  
  if (dataInt.getRowCount()==2){ //si solo hay dos objetos los dividimos en dos
       centroideCercaF[0]=0;
       centroideCercaF[1]=1;
         
     }else{         

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
    nue_medoide=nuevoMedoides(centrocer);
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
   }
 
 
 
//calcula la igualda entre dos objetos lista, se lo utiliza para ver si los medoides han cambiado
 public boolean calcularIgualdad(AbstractTableModel tabla1,AbstractTableModel tabla2){
      boolean op=true;
     for (int i=0;i<tabla1.getRowCount();i++){
               for (int j=0;j<tabla1.getColumnCount();j++){
                   if (tabla1.getValueAt(i,j).equals(tabla2.getValueAt(i,j))){
                                            
                         }else{
                         
                    //     System.out.println("Datos diferentes "+ tabla1.getValueAt(i,j) +" con "+tabla2.getValueAt(i,j));
                         op=false;
                         }
                      
               }
            }
     
    
     return op;
 }

private AbstractTableModel nuevoMedoides(int [] centroidecer){
     //calcular el nuevo medoide
 
    AbstractTableModel medotemp;
      datosTemp = new Object[numcluster][col]; 
      columnsName = new String[col];
      for(int t = 0; t < col; t++)
       columnsName[t] = dataInt.getColumnName(t);
    
    double sumatoria=0.0;
    double totalsuma=0.0;
    int contador=0;
    for (int clu=0;clu<numcluster;clu++){
    //       System.out.println("cluster "+clu);
  
       for (int i=0;i<col;i++){
                 atri=(Atributo) atColumna[i];
              
               if(atri.getClase().equalsIgnoreCase("Integer") || atri.getClase().equalsIgnoreCase("Double")){  //atri.getDesabilitar()==false

                   sumatoria=0.0;
                   contador=0;
                   for (int j=0;j<fil;j++){
                       if (centroidecer[j] == clu ){

                          sumatoria=sumatoria + Double.parseDouble(dataInt.getValueAt(j,i).toString());
                           contador++;

                       }

                   }
                    if (contador==0){
                       // datosTemp[clu][i] = medoide.getValueAt(clu,i);  
                        datosTemp[clu][i] = 0;  

                      //   System.out.println("0 datos cluster ante"+medoide.getValueAt(clu,i));
                     }else{

                    totalsuma=sumatoria / contador;
                  //   System.out.println("suma de estos datos"+totalsuma);

                      datosTemp[clu][i]=totalsuma;  

                     }
                }else{
                     datosTemp[clu][i]="no aplica";        
                     } 
            }
            System.out.println("");
          }
          
          
      medotemp=new TableModelImport(datosTemp,columnsName); 
     System.out.println("nuevo centroide");
  mostrar(medotemp);     
  
  return medotemp;   
  ////////////
}

   //ESTE METODO RETORNA UN OBJETO  QUE CONTINE LOS CLUSTER OBTENIDOS PARA CADA MEDOIDE
   private int[] realizacluster(){
   Double distancia1 = 0.0;
   Double distancia2 = DATO_GRANDE2;

   AbstractTableModel[]  clustemp;
   int [] centroidecerca = new  int[fil];


   Object[] filaActu = new Object[col]; 
   Object[] filaMedoide = new Object[col]; 
   
   int t = 0;
   
      for (int i=0;i<fil;i++){
               for (int j=0;j<col;j++){                   
                  filaActu[j] = dataInt.getValueAt(i,j);      
               }
         
                  distancia2 = DATO_GRANDE2;
                  distancia1=0.0;
                             
               for (int m=0;m<numcluster;m++){
                   
                    for (int y=0;y<col;y++){
                          filaMedoide[y] = medoide.getValueAt(m,y);
                     }
                    
                    distancia1 = distancia.getDistancia(filaMedoide,filaActu); 
            //     System.out.println("Centroide "+ m +" objeto "+ i +"distancia "+distancia1 + "distancia2 " + distancia2+" ");

                   if (distancia1 < distancia2){
                      centroidecerca[i] = m;
                      distancia2 = distancia1;
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
            if(cuentaObje == 0){
               System.out.println("El cluster esta vacio, debe eliminar repetidos "+clu);        
            }
          }
       centroideCercaF=centroidecerca;
       return centroidecerca;
       }
  
 


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
    
    
    
    
    //ESTE METODO CREA LOS MEDOIDES INICIALES ALEATORIAMENTE DEBE RECIBIR EL NUMERO DE MEDOIDES A TOMAR
    private void iniciamedoideAl(){
    
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

 private int[] randomicos(int tam,int min,int max){ // retorna un arreglo de numeros aleatoriamente sin repetidos
        int conta=0;                                // tam= numero de numeros que tendra el arrelgo   
        Random rand= new Random();                  // min - max = rango en el cual se busca los nuemero aleatorios
       int[] arre=new int[tam];
        
       for (int i=0;i<tam;i++){
         int randomico=rand.nextInt(max);
          if (siexiste(arre,randomico) || randomico < min){
             i--;
          } else
          {
             arre[conta]=randomico;
             conta++;
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
    
    
    
    public void divCluster(){
        int con1,con2;
        con1=0;
        con2=0;
          for(int i=0;i<fil;i++){
                 if(centroideCercaF[i]==0)
                     con1++;
                 if(centroideCercaF[i]==1)
                     con2++;
            }
                
          
        Object[][] data1=new Object[con1][col];
        Object[][] data2=new Object[con2][col];
        Object[][] data1Ori=new Object[con1][col];
        Object[][] data2Ori=new Object[con2][col];
        
         con1=0;
        con2=0;
          for(int i=0;i<fil;i++){
               for(int j=0;j<col;j++){  
                   System.out.print("datos ori  "+ dataOri.getValueAt(i,j));
                   if(centroideCercaF[i]==0){
                       
                       data1[con1][j]=dataInt.getValueAt(i,j);
                       data1Ori[con1][j]=dataOri.getValueAt(i,j);
                   }else{
                      if(centroideCercaF[i]==1){
                       data2[con2][j]=dataInt.getValueAt(i,j);
                       data2Ori[con2][j]=dataOri.getValueAt(i,j);
                      }  
                   }
               }
               
               if(centroideCercaF[i]==0)
                     con1++;
                if(centroideCercaF[i]==1)
                     con2++;
            }
  
        if(con1==0){
                Object[][] da1=new Object[1][col];  
                 for(int j=0;j<col;j++)
                   da1[0][j]=0;; 
               
                   clu1=new TableModelImport(da1,columnsName); 
                   clu1Ori=new TableModelImport(da1,columnsName);
                System.out.println("mostrar cluster 1");
                   mostrar(clu1);           
            }
        else{
          System.out.println("mostrar cluster 1");
            clu1=new TableModelImport(data1,columnsName); 
            clu1Ori=new TableModelImport(data1Ori,columnsName); 
            
           mostrar(clu1);  
        }
           if(con2==0){
                Object[][] da2=new Object[1][col];  
                for(int j=0;j<col;j++)
                   da2[0][j]=0;; 
               
                   clu2=new TableModelImport(da2,columnsName); 
                   clu2Ori=new TableModelImport(da2,columnsName); 
                   
                System.out.println("mostrar cluster 1");
                   mostrar(clu2);           
            }
        else{
          System.out.println("mostrar cluster 1");
            clu2=new TableModelImport(data2,columnsName); 
            clu2Ori=new TableModelImport(data2Ori,columnsName); 
            
           mostrar(clu2);  
        }
           
        System.out.println("----------------------------------------------------------------------");
    }
    
    public AbstractTableModel getClu1(){
        return clu1;
    }
    
    public AbstractTableModel getClu2(){
        return clu2;
    }
     public AbstractTableModel getClu1ori(){
        return clu1Ori;
    }
    
    public AbstractTableModel getClu2ori(){
        return clu2Ori;
    }
}