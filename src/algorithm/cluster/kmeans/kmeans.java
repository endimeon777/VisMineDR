/*
 * kmeans.java
 *
 * Created on 13 de mayo de 2009, 14:52
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package algorithm.cluster.kmeans;


import algorithm.cluster.Datos;
import algorithm.cluster.similitud.Distancia;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JTextArea;

/**
 *
 * @author xp
 */
public class kmeans {
       String columnas[];
       private  LinkedList lista = new LinkedList(); //crea una estrutura de datos
       private LinkedList clusterkm=new LinkedList();
       private LinkedList medoide=new LinkedList();
       int DATO_GRADE=999999999;
       double DATO_GRANDE2=999999999.0;
       int numcluster=3;
       private   Distancia distancia; 

//JTextArea jta;

public void setLista(LinkedList lis){
    this.lista = lis;
}
public void setDistancia(Distancia distancia){
    this.distancia = distancia;
}
 public void ejecutar(){
//this.jta=jta;
  iniciamedoide();

LinkedList nue_medoide=new LinkedList();
LinkedList mecopia=new LinkedList();
mecopia=medoide;

boolean continua=true;
int maximo=0;
int contador=0;
while (continua){
    clusterkm=realizacluster();
   mostrarCluster(clusterkm);
 
   nue_medoide=nuevoMedoides();
   System.out.println("-comparando ----------MEDOIDE ANTERIOR " );
mostrarlista(medoide);
 System.out.println("--------------------  -- NUEVOMEDOIDE " );
mostrarlista(nue_medoide);
   if (calcularIgualdad(medoide, nue_medoide)){
      continua=false;
      medoide=nue_medoide;
      System.out.println("----los medoides son iguales   -- " );
     }else{
            System.out.println("----los medoides han cambiado,se ejecuta nuevo ciclo   -- " );

     }

   if (calcularIgualdad(nue_medoide,mecopia)){
      continua=false;
      medoide=nue_medoide;
      System.out.println("----los medoides son iguales   -- " );
     }else{
            System.out.println("----los medoides han cambiado,se ejecuta nuevo ciclo   -- " );

     }

   mecopia=medoide;
  medoide = nue_medoide;

contador++;
}

  System.out.println("----numero de iteracxiones   -- "+ contador );

   }
 
 public LinkedList getCluster(){
     return clusterkm;
 }
 
//calcula la igualda entre dos objetos lista, se lo utiliza para ver si los medoides han cambiado
 public boolean calcularIgualdad(LinkedList lk1,LinkedList lk2){
      boolean op=true;
     int i=0;
            Datos datos1 = new Datos();
            Datos datos2 = new Datos();

            while ( i < lk1.size()) {
			datos1 = (Datos) lk1.get(i);
            datos2 = (Datos) lk2.get(i);

            int j=0;
            while (j < datos1.obtenerLista().size()){

                if (datos1.obtenerLista().get(j).equals(datos2.obtenerLista().get(j))) {
                }else{
                    op=false;
                       System.out.println("datos distintos " + datos1.obtenerLista().get(j) + " y " + datos2.obtenerLista().get(j));
                    return false;

                     } 
                  j++;
              }

		i++;
	   }

     return op;
 }

private LinkedList nuevoMedoides(){
     //calcular el nuevo medoide
  int t=0;
  LinkedList listaclu=new LinkedList();
  LinkedList nuevosMedoides=new LinkedList();

  Datos datolista=new Datos(); //obtener un dato de la lista de cada cluster



       while(t< clusterkm.size()){ //recorrer los cluster
           listaclu=(LinkedList) clusterkm.get(t); //contien un cluster con varios objetos tipo datos
          //   System.out.println("----calcular nuevo medoide cluster    " + t);
            // mostrarlista(listaclu);

             Datos nuevomedoide=new Datos();
           ///////// calcular medoide por cada cluster

                int m=0;
                   List<Double> l2=new ArrayList<Double>();
                     List<Double> l1=new ArrayList<Double>();
                 
                  int ini=0;
                  datolista =  (Datos) listaclu.get(0);
                  while(ini < datolista.size()){
                   l2.add(0.0);
                  ini++;
                   }
                double sdato;
                while(m < listaclu.size()){
                  datolista =  (Datos) listaclu.get(m);
                    int j=0;

                   l1=datolista.obtenerLista();
               
                    while (j < l1.size()){
             //  System.out.println("----------  "+l2.get(j) +" l1= "+ l1.get(j));
                    sdato=l2.get(j) + l1.get(j);
                    l2.set(j, sdato);
                      j++;
                     }

           /* if (sumatoria <= sumatoriaant){
                    nuevomedoide=posiblemedoide;
             }
            sumatoriaant=sumatoria;*/
                  m++;
             }
             int to=0;
    
            while(to < l2.size()){
                sdato=l2.get(to) /listaclu.size();
               
                l2.set(to, sdato);
                        to++;
            }
             nuevomedoide.setLista(l2);
             
           nuevosMedoides.addLast(nuevomedoide);
        
           /////////////////////////////////

           t++;
       }

   System.out.println("nuevos medoides");
// jta.append("\n Nuevo medoides \n");
  mostrarlista(nuevosMedoides);
  return nuevosMedoides;
  ////////////
}

   //ESTE METODO RETORNA UN OBJETO LINKEDLIST QUE CONTINE LOS CLUSTER OBTENIDOS PARA CADA MEDOIDE
   private LinkedList realizacluster(){
   Double distancia1=0.0;
  Double distancia2=DATO_GRANDE2;
  Datos datolista=new Datos();
  Datos medoideact = new Datos();
  int medoidecerca=DATO_GRADE;
  LinkedList cluspam=new LinkedList();
  LinkedList listatemporal=new LinkedList();

      int j=0;
      int t=0;
       while(t < medoide.size()){
             LinkedList listan = new LinkedList();
             cluspam.addLast(listan);
             t++;
                }

       while (j<lista.size()){
         datolista=(Datos) lista.get(j);

             int m=0;
             distancia2=DATO_GRANDE2;
             distancia1=0.0;
              medoidecerca=DATO_GRADE;
             while(m < medoide.size()){
            medoideact =  (Datos) medoide.get(m);
            distancia1=distancia.getDistancia(medoideact, datolista);
              if (distancia1 < distancia2){
              medoidecerca=m;
              distancia2=distancia1;
                }

         
                  m++;
             }
              System.out.println("medoide cerca "+medoidecerca);
            listatemporal=(LinkedList) cluspam.get(medoidecerca);
            listatemporal.add(datolista);
            cluspam.set(medoidecerca, listatemporal);
           mostrarlista(listatemporal);
                  j++;
       }
return cluspam;
   }

 //MUESTRA LOS CLUSTER OBTENIDOS DEL METOD REALIZAR CLUSTER, RECIBE EL OBJETO LINKEDLIST clusterpam
   public void mostrarCluster(LinkedList lclu){
       int t=0;
       LinkedList listaclu=new LinkedList();

       while(t< lclu.size()){
           listaclu=(LinkedList) lclu.get(t);
             System.out.println("----CLUSTER   -- " + t);
            //  jta.append("----CLUSTER----- " + t + "\n");
           mostrarlista(listaclu);
           t++;
       }
   }



  //
   public void  mostrarlista(LinkedList lis){
       //// esto es par amostrar los datos guardados en la estructura---
        int i=0;
            Datos datos = new Datos();
               String res;
            while ( i < lis.size()) {
			datos = (Datos) lis.get(i);
            int j=0;
         res="";
            while (j < datos.obtenerLista().size()){
                System.out.print("   [  "+ datos.obtenerLista().get(j) +" ]");
          res=res+"[ "+ datos.obtenerLista().get(j)+" ]";
            j++;
            }
         //   jta.append(res+"\n");
                 System.out.println("");
			i++;
		     }
         //----------------------------------
   }


  //ESTE METODO CREA LOS MEDOIDES INICIALES ALEATORIAMENTE DEBE RECIBIR EL NUMERO DE MEDOIDES A TOMAR
    private void iniciamedoide(){

        for (int i=0; i<numcluster;i++){
         Datos datos = new Datos();
       	 datos = (Datos) lista.get(i);
         medoide.addLast(datos);
        }
         mostrarlista(medoide);
    }
    public void setNumcluster(int num){
        this.numcluster=num;
    }
    
    public LinkedList getMedoide(){
       return medoide; 
    }
}
