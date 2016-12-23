/*
 * AlgoritmoSliq.java
 *
 * Created on July 17, 2007, 8:58 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package algorithm.classification.Sliq;

import algorithm.classification.Value;
import algorithm.classification.c45_1.TreeCounter;
import gui.KnowledgeFlow.ChooserEscritorio;
import algorithm.classification.c45_1.Attribute;
import gui.Icons.Clasification.ClasificationIcon;
import gui.KnowledgeFlow.performanceAttribute;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Vector;
import gui.KnowledgeFlow.JackAnimation;
 

/**
 *
 * 
 */
public class AlgoritmoSliqHilo extends Thread{
    int num_columnas,num_registros,columnaClase,atributoGini;
    //long empieza,termina,tiempo;
    Object [][] conjuntoDatos;
    Object [][] auxDatos;
    String [] nomColumnas;
    ArrayList apilaDatos;
    public Attribute root= new Attribute("raiz");
    public ClasificationIcon ci;
    private JackAnimation animation;
    public TreeCounter c;
    private int totalNodes;
    double minRow=0;
    
    /** Creates a new instance of AlgoritmoSliq */
    public AlgoritmoSliqHilo(Object datos[][],String [] anomColumnas,int columClase, ClasificationIcon ci) {
        //obtengo el numero de columnas y registros
        int i,j; 
        this.ci = ci;
         num_columnas= datos[0].length;
         num_registros=datos.length;
         columnaClase=columClase;
        /*/muestro los datos
            for( i = 0; i <num_columnas; i++){
                System.out.println("********** nomColumna: "+anomColumnas[i].toString());
                for( j = 0; j <num_registros ; j++){
                    System.out.println("- "+datos[j][i].toString()); 
                }
            }
        
        /*/
         nomColumnas=anomColumnas;
        
         conjuntoDatos=datos;
          minRow= ci.minRows* conjuntoDatos.length/100;
         //this.ejecutarAlgoritmo();
         //javax.swing.JOptionPane.showMessageDialog(null,"Sliq ha Terminado con exito");  
    }
    
    public void ejecutarAlgoritmo(){
        
         
         Attribute auxAtributo = null;
         Attribute primerHijo = null;
         ArrayList pila= new ArrayList();
         Vector pilaDatos=new Vector();
         Object [][] auxDatos2;
         Vector unicosClase,unicosCol;
         ArrayList cantClase;
         float giniMenor;
         int  i,j,k,auxNumRegistros;
         int columnaMenor=-1;
         int giniMenorColumna=1;
         int punDatos;
         int numNodo=1;
         int [] datosEscojidos;
         RegistroIndexado [] aDatos;
         MiColumna [] particionCol;
         String valorNodo;
         Vector pilaHijos=new Vector();
         Vector pilaNomColumnas=new Vector();
         unicosClase =new Vector(); 
         cantClase=new ArrayList();
         Vector columnGiniMenor =new Vector();
          String [] auxNomColumns;  
          String [] auxNomColumns2;  
         apilaDatos=new ArrayList();
         
         root.nomColumna = "clase";
         root.name = nomColumnas[nomColumnas.length-1];
         root.frecuence = conjuntoDatos.length;
         //root.valuesClass=new ArrayList(); 
         
         
        // empieza=System.currentTimeMillis();
 //****************** comienza a ejecutarse el algoritmo   
         
         //root.name="raiz";
         
         i=0;
         pilaDatos.add(conjuntoDatos);
         pilaNomColumnas.add(nomColumnas);
         //auxNomColumns=nomColumnas;
         columnGiniMenor.add(-1);
         //apilaDatos.add(conjuntoDatos);
         do{
                 auxDatos=(Object[][]) pilaDatos.lastElement();
                 columnaMenor=(Integer)columnGiniMenor.lastElement();
                 auxNomColumns=(String[]) pilaNomColumnas.lastElement();
                 
                 if( auxAtributo!=null){
                     //se hace la particion de los datos teniendo en cuenta la condicion del nodo
                     //System.out.println("xxxxxxxxxxxxxxxxxxxx tengo el atributo:"+auxAtributo.name+" de la columna: "+auxNomColumns[columnaMenor]+" por Mayor:"+auxAtributo.mayor);
                     datosEscojidos=null;
                     datosEscojidos=new int[auxDatos.length];
                     auxNumRegistros=0; 
                     
                     //encuentro los indices de los registros a evaluar
                     //para nodo categorico
                     if(!auxAtributo.numerico){
                         
                          if(auxAtributo.name.contains("+")){
                           // javax.swing.JOptionPane.showMessageDialog(null,"llego");
                            String auxSliq="Nada";
                            int indI=0,indF=0;
                            indF=auxAtributo.name.indexOf("+");
                            // lleno el o
                            while(indF!=-1){
                            auxSliq=auxAtributo.name.substring(indI,indF-1);
                            javax.swing.JOptionPane.showMessageDialog(null,"Tengo: "+auxSliq);
                            for(i=0;i<auxDatos.length;i++){
                                    if(auxDatos[i][columnaMenor].equals(auxSliq) ){
                                      datosEscojidos[auxNumRegistros]=i;
                                      auxNumRegistros++;

                                  }                            
                               } 
                            indI=indF+1;
                            indF=auxAtributo.name.indexOf("+",indF+1);
                            }
                            
                            auxSliq=auxAtributo.name.substring(indI);
                            //javax.swing.JOptionPane.showMessageDialog(null,"Tengo: "+auxSliq);
                            for(i=0;i<auxDatos.length;i++){
                                    if(auxDatos[i][columnaMenor].equals(auxSliq) ){
                                      datosEscojidos[auxNumRegistros]=i;
                                      auxNumRegistros++;

                                  }                            
                               } 
                          
                          }
                          else{
                               for(i=0;i<auxDatos.length;i++){
                                  if(auxDatos[i][columnaMenor].equals(auxAtributo.name) ){
                                      datosEscojidos[auxNumRegistros]=i;
                                      auxNumRegistros++;

                                  }                            
                               } 
                          }
                     }else{ // para nodo numerico
                         
                         BigDecimal dato,valor;
                         valor=new BigDecimal(auxAtributo.name);
                         
                         if(!auxAtributo.mayor){
                             //si se evalua por <=
                             for(i=0;i<auxDatos.length;i++){
                               dato=new BigDecimal(auxDatos[i][columnaMenor].toString());   
                               if(valor.compareTo(dato)==0 || valor.compareTo(dato)==1){
                                  
                                   datosEscojidos[auxNumRegistros]=i;
                                  auxNumRegistros++;

                               }                            
                            } 
                             
                            
                             //adicionando nodo <
                            // System.out.println("cantidad de registros: "+auxDatos.length+" escojidos"+auxNumRegistros);
                             if( i!=auxNumRegistros ){
                                                                         
                               auxAtributo.brother = new Attribute(auxAtributo.name);
                               auxAtributo.brother.frecuenceFather=auxAtributo.frecuence;
                               auxAtributo.brother.numerico=true;
                               auxAtributo.brother.mayor=true;
                               auxAtributo.brother.setId(numNodo);
                               numNodo++;
                             }
                         
                         }else{
                             //si se evalua por mayor
                             for(i=0;i<auxDatos.length;i++){
                               dato=new BigDecimal(auxDatos[i][columnaMenor].toString());   
                               if(valor.compareTo(dato)==-1){
                                  datosEscojidos[auxNumRegistros]=i;
                                  auxNumRegistros++;

                               }                            
                            } 
                            //System.out.println("cantidad de registros Mayor: "+auxDatos.length+" escojidos"+auxNumRegistros); 
                         }
                     }
                      
                     auxDatos2=new Object[auxNumRegistros][auxDatos[0].length-1];
                     
                     
                     for(i=0;i<auxNumRegistros;i++){
                         punDatos=0;
                         for(j=0;j< auxDatos[0].length;j++ ){
                             if(j!=columnaMenor){
                                 //System.out.println("dato: "+auxDatos[datosEscojidos[i]][j]);
                                 auxDatos2[i][punDatos]=auxDatos[datosEscojidos[i]][j];
                                 punDatos++;
                             }
                         }
                     }
                     punDatos=0;
                     auxNomColumns2=new String [auxDatos[0].length-1];
                     for(j=0;j< auxDatos[0].length;j++ ){
                             if(j!=columnaMenor){
                                 //System.out.println("dato: "+auxDatos[datosEscojidos[i]][j]);
                                 auxNomColumns2[punDatos]=auxNomColumns[j];
                                 punDatos++;
                             }
                         }
                    
                     //coloco el nombre del atributo
                     auxAtributo.nomColumna=auxNomColumns[columnaMenor];
                      
                     auxDatos=null;
                     auxNomColumns=null;
                     
                     auxNomColumns=auxNomColumns2;
                     auxDatos=auxDatos2;
                     //coloco la frecuencia del atributo, que es la cantidad de registros a evaluar
                     auxAtributo.frecuence=auxDatos.length;
                        
                 } 
                else{
                     auxAtributo=root;
                     
                } 
                  
                
                    //  if(auxDatos[0].length>1){
                    /*System.out.println("@@@@@@************* datos AuxDatos ***********************************************");
                   
                    for( i = 0; i <auxDatos.length; i++){
                            for( j = 0; j <auxDatos[0].length; j++){
                                System.out.print("- "+auxDatos[i][j].toString()); 
                            }
                            System.out.println();
                        }*/
                     aDatos=null;
                     particionCol=null;
                    //creo una las lista del atributo clase 
                     aDatos=new RegistroIndexado[auxDatos.length];
                     particionCol=new MiColumna[auxDatos[0].length];
                     
                     for(j=0;j<auxDatos.length;j++){
                         // obtengo el dato de la tabla y asignmo el indice
                          aDatos[j] = new RegistroIndexado(auxDatos[j][auxDatos[0].length-1],j );
                     }
                                          
                     particionCol[auxDatos[0].length-1]=new MiColumna(aDatos,auxDatos[0][auxDatos[0].length-1].getClass().toString() ,"probar");
                     
                     unicosClase = particionCol[auxDatos[0].length-1].datosUnicos();
                     cantClase=particionCol[auxDatos[0].length-1].frecuencia;
//                     System.out.println("*********** Clase *************");
                     auxAtributo.valuesClass=new ArrayList(); 
                     for(int m=0;m<unicosClase.size() ;m++){
                           //      System.out.println(m+"  - "+ unicosClase.get(m).toString()+" cant: "+ (Integer)cantClase.get(m) ); 
                                    
                               auxAtributo.valuesClass.add(new Value(unicosClase.get(m).toString(),(Integer) cantClase.get(m))); 
                     }
                      if(auxDatos[0].length>1 && minRow <auxDatos.length){
                    
                     //auxAtributo.
                       //para verificar si ya termino, es decir que tiene un solo valor de clase
                     if(unicosClase.size()==1){
                         //System.out.println("termino");
                         auxAtributo.son=new Attribute(unicosClase.get(0).toString());
                         auxAtributo.son.frecuence=auxDatos.length;
                         auxAtributo.son.frecuenceFather=auxDatos.length; 
                         auxAtributo.son.nomColumna=auxNomColumns[auxNomColumns.length-1];
                         auxAtributo.son.setId(numNodo);
                         numNodo++;
                         /*
                         auxAtributo.son.valuesClass=new ArrayList();
                         auxAtributo.son.valuesClass.add(new Value(unicosClase.get(0).toString(),(Integer)cantClase.get(0)));
                         */
                          if(auxAtributo.brother==null){ 
                            
                             while(auxAtributo.brother==null && pilaHijos.size()>1 ){//aumente
                                auxAtributo=(Attribute) pilaHijos.lastElement();
                                
                                if(auxAtributo.brother==null){
                                                               
                                 // System.out.println(" Atributo: "+auxAtributo.name+" pilaHijos: "+pilaHijos.size()+" pilaDatos: "+pilaDatos.size() );
                                  pilaHijos.remove(pilaHijos.size()-1);
                                  pilaDatos.remove(pilaDatos.size()-1);
                                  pilaNomColumnas.remove(pilaNomColumnas.size()-1);
                                  columnGiniMenor.remove(columnGiniMenor.size()-1 );
                                
                               }
                             }//fin while
                             auxAtributo=auxAtributo.brother;
                             //aumente
                             pilaHijos.remove(pilaHijos.size()-1);
                             pilaDatos.remove(pilaDatos.size()-1);
                             pilaNomColumnas.remove(pilaNomColumnas.size()-1);
                             columnGiniMenor.remove(columnGiniMenor.size()-1 );
                             
                             
                          }//fin if
                          else  
                            auxAtributo=auxAtributo.brother;
                         
                            
                             
                     }    
                     else{// mas de una clase
                         
                         
                          pilaDatos.add(auxDatos);
                          pilaHijos.add(auxAtributo);
                          pilaNomColumnas.add(auxNomColumns);
                      
                       //si hay mas de un valor CLase entonces creamos las listas de los demas atributos y
                      // encontramos el atributo con el menor gini
                         
                         for(i=0;i<auxDatos[0].length-1;i++){
                             
                             for(k=0;k<auxDatos.length;k++){
                                  aDatos[k]=new RegistroIndexado(auxDatos[k][i],k);
                             }
                             // creo la lista indexada  
                             particionCol[i]=new MiColumna(aDatos,auxDatos[0][i].getClass().toString(),"hay que ver" );
                             
                             
                             if( particionCol[i].tipoDato.equals("class java.lang.String") ){
                             //calculo el index para atributos categoricos    
                                 //****
                               unicosCol = particionCol[i].datosUnicos();
                               //System.out.println("*********** Valores Unicos columna - "+particionCol[i].nomColumna +"**********");
//                               for(int m=0;m<unicosCol.size() ;m++)
//                                 System.out.println(m+"  - "+ unicosCol.get(m).toString());
                            
                            
                            particionCol[i].histograma=new Histograma(unicosCol.size(),unicosClase.size(),auxDatos.length);   


                              int acantVal=0,inc;
                              int [] acantClase;

                              inc=0;  
                             // para formar tabla(Histograma)
                             for(j=0;j< unicosCol.size();j++){
                                 //particionCol[i].histograma.datoUnico[j]=unicosCol.get(j).toString();
                                particionCol[i].histograma.datoUnico.add(unicosCol.get(j).toString());
                                acantVal=0;
                                acantClase=null;
                                acantClase= new int[unicosClase.size()];

                                //formo el histograma, es decir hago el conteo con todos los registros 
                                for(k=0;k<auxDatos.length;k++){
                                    //System.out.println("comparo: "+particionCol[i].histograma.datoUnico.get(j).toString()+" con: "+ particionCol[i].datos[k].valor_dato);
                                    if(particionCol[i].histograma.datoUnico.get(j).equals(particionCol[i].datos[k].valor_dato )){   
                                       acantVal++;
                                       inc=unicosClase.indexOf( auxDatos[k][auxDatos[0].length-1]);
                                       acantClase[inc]++;
                                       }

                                 }
                                 //coloco en histograma las cant encontradas
                                 particionCol[i].histograma.cantidad[j]=acantVal;
                                 particionCol[i].histograma.cantClase[j]=acantClase;
                                 //****
                              }
                             particionCol[i].histograma.printHistograma(); 
                             //encontrar SubConjuntos
                             particionCol[i].histograma.giniCategorico();
                                                       
                             
                         }//fin atributos categoricos
                             else{
                                 //*****
                                 // tratamiento para Numericos
                                  particionCol[i].ordenarDatos();
                                  particionCol[i].histograma=new Histograma(auxDatos.length-1,unicosClase.size(),auxDatos.length); 
                                     int acantVal=0,inc;
                                 int [] acantClase;

                                 // para formar tabla
                                 for(j=0;j<auxDatos.length-1;j++){

                                     Double auxD=(Double.valueOf(particionCol[i].datos[j].valor_dato.toString()) + Double.valueOf(particionCol[i].datos[j+1].valor_dato.toString()))/2;
                                 //System.out.println("Si sale: "+ auxD);
                                     //particionCol[i].histograma.datoUnico[j]=auxD ;
                                    particionCol[i].histograma.datoUnico.add(auxD);
                                    acantVal=0;
                                    acantClase=null;
                                    acantClase= new int[unicosClase.size()];
                                     for(k=0;k<auxDatos.length;k++){

                                        //System.out.println("comparo: "+particionCol[i].histograma.datoUnico[j]+" con: "+particionCol[i].datos[k].valor_dato+" tengo "+particionCol[0].datos[k].valor_dato); 
                                        if( Double.valueOf(particionCol[i].datos[k].valor_dato.toString()) < Double.valueOf(particionCol[i].histograma.datoUnico.get(j).toString()) ){      
                                        acantVal++;
                                              inc=unicosClase.indexOf(auxDatos[particionCol[i].datos[k].indice ][auxDatos[0].length-1] );
                                              acantClase[inc]++;
                                             //System.out.println("val: "+particionCol[i].histograma.datoUnico[j]+" cant:"+acantVal++);
                                          }


                                     }
                                     //coloco en histograma las cant encontradas
                                     particionCol[i].histograma.cantidad[j]=acantVal;
                                     particionCol[i].histograma.cantClase[j]=acantClase;
                                     //particionCol[i].histograma.giniNumerico();

                                 }

                                 particionCol[i].histograma.printHistograma(); 
                                 particionCol[i].histograma.giniNumerico();
                                 
                                 //****
                             }//fin atributos numericos  
                     }//fin for de analisis de columnas
                        //miro cual es el menor gini
                         giniMenor=1;atributoGini=-1;
                         for(i=0;i<auxDatos[0].length-1;i++){
                            if(particionCol[i].histograma.mejorGini<giniMenor){
                                giniMenor=particionCol[i].histograma.mejorGini;
                                atributoGini=i;
                            }
                         }
                         
                        // System.out.println("¡¡¡¡¡¡ El Mejor Atributo: "+atributoGini+" - "+auxNomColumns[atributoGini]+" Con Gini: "+giniMenor+"de tipo: "+auxDatos[0][atributoGini].getClass().toString());
                         columnGiniMenor.add(atributoGini);
                         
                       // creo los nuevos nodos del arbol
                         //System.out.println("tamaño subconjuntos del vector: "+particionCol[conjuntoGini].histograma.mejorDivision.length);
                         if(auxDatos[0][atributoGini].getClass().toString().equals("class java.lang.String")){
                         //coloco el mejor gini en el arbol
                                                       
                            
                             for(i=0;i < particionCol[atributoGini].histograma.mejorDivision.length ;i++ ){
                                 //en valorNodo almaceno los valores de los subconjuntos encontrados se dividen con el signo +   
                                 valorNodo=particionCol[atributoGini].histograma.mejorDivision[i].get(0).toString();
                                 for(j=1; j<particionCol[atributoGini].histograma.mejorDivision[i].size();j++){
                                     valorNodo+="+"+particionCol[atributoGini].histograma.mejorDivision[i].get(j).toString();
                                     //javax.swing.JOptionPane.showMessageDialog(null,"conjunto");
                                 }

                                 if(i==0){
                                     //adiciono el nuevo nodo
                                     auxAtributo.son=new Attribute(valorNodo);
                                   //auxAtributo.son.valuesClass
                                     
                                   auxAtributo.son.frecuenceFather=auxAtributo.frecuence;
                                   auxAtributo=auxAtributo.son;

                                   primerHijo=auxAtributo;

                                 }
                                 else{ 
                                     auxAtributo.brother=new Attribute(valorNodo);
                                     auxAtributo.brother.frecuenceFather=auxAtributo.frecuenceFather;
                                     auxAtributo=auxAtributo.brother;    
                                 }
                                 auxAtributo.setId(numNodo);
                                 numNodo++;

                             }
                              auxAtributo=primerHijo;
                         }
                         else{//adicionar nodo numerico
                             //
                             //adicionando nodo <=
                             auxAtributo.son=new Attribute(particionCol[atributoGini].histograma.mejorDivision[0].get(0).toString());
                             auxAtributo.son.frecuenceFather=auxAtributo.frecuence;
                             auxAtributo=auxAtributo.son;
                             auxAtributo.numerico=true;
                             auxAtributo.setId(numNodo);
                             numNodo++;                          
                             
                          }
                  }//para verificar que tenga mas de un valor clase
                 
               // }//auxAtributo
             //}//columna clase
             /*else
                 i++;*/
                                 
             
               }else{
                        //como hay mas de un valor clase y solo queda la columna clase 
                        //escojo el de mayor frecuencia 
                         int mayorFrecuencia,indiceMayor;
                               mayorFrecuencia=(Integer) cantClase.get(0);
                                indiceMayor=0;
                                for(int r=0;r<cantClase.size();r++){
                                    if( mayorFrecuencia <(Integer)cantClase.get(r)){
                                        indiceMayor=r;
                                        mayorFrecuencia=(Integer)cantClase.get(r);
                                    }
                                }
                          
                                
                         //System.out.println("termino Imcompleto");
                         auxAtributo.son=new Attribute((String) unicosClase.get(indiceMayor));
                         auxAtributo.son.frecuence=mayorFrecuencia;
                         auxAtributo.son.frecuenceFather=auxAtributo.frecuence;
                         auxAtributo.son.nomColumna=auxNomColumns[auxNomColumns.length-1];
                         auxAtributo.son.setId(numNodo);
                         numNodo++;
                         
                         if(auxAtributo.brother==null && pilaHijos.size()>0 ){
                             
                             //aumente
                             while(auxAtributo.brother==null && pilaHijos.size()>1){
                             
                                auxAtributo=(Attribute) pilaHijos.lastElement();
                               
                             
                             if(auxAtributo!=null){
                             // System.out.println(" 2 Atributo: "+auxAtributo.name+" pilaHijos: "+pilaHijos.size()+" pilaDatos: "+pilaDatos.size() );
                              pilaHijos.remove(pilaHijos.size()-1);
                              pilaDatos.remove(pilaDatos.size()-1);
                              pilaNomColumnas.remove(pilaNomColumnas.size()-1);
                              columnGiniMenor.remove(columnGiniMenor.size()-1 );
                             }
                             else{
                                // System.out.println("Se quedo por aqui");
                                }
                             
                             }//fin while
                              auxAtributo=auxAtributo.brother;    
                           }
                          else  
                          {auxAtributo=auxAtributo.brother;                                   
                            
                          }
                             
                         
               }
                   
                    
                    
         }while(auxAtributo!=null);
         
          
                  
//***********************         finaliza el algoritmo
      /*   termina=System.currentTimeMillis();   
         tiempo=termina-empieza;
         System.out.println(" Tiempo transcurrido: "+tiempo);
         
        
// mostrar arbol
     System.out.println("--------- ARBOL GENERADO --------------- ");    
     
       
        */
        
    }
  
   public void setAnimation(JackAnimation animation){
        this.animation = animation;
        }
    
   public void pruneLeafs(){
        Attribute auxiliar = this.root;
        Stack stack = new Stack();
        crossTree(auxiliar, stack);
        totalNodes = 0;
        while(!stack.isEmpty()){
            pruneSameBranch((Attribute)stack.pop());
        }
    }
    
    public void pruneSameBranch(Attribute node) {
        if(node.son.son.isLeaf() != null){
            totalNodes++;
            return;
        }
        String grandSon = node.son.son.name;
        int frecuences = node.son.son.frecuence;
        int frecuencesFather = node.son.son.frecuenceFather;
        Attribute childrens = node.son.brother;
        boolean isEquals = true;
        while(childrens != null){
            if(!childrens.son.name.equals(grandSon)){
                isEquals = false;
                break;
            } else {
                frecuences += childrens.son.frecuence;
                frecuencesFather += childrens.son.frecuenceFather;
            }
            childrens = childrens.brother;
        }
        if(isEquals){
            node.son.frecuence = frecuences;
            node.son.frecuenceFather = frecuencesFather;
            node.son.name = node.son.son.name;
            node.son.valuesClass = node.valuesClass;
            node.son.son = null;
            node.son.brother = null;
        }
    }
   
    public void crossTree(Attribute auxiliar, Stack stack){
        if(auxiliar.brother != null){
            crossTree(auxiliar.brother, stack);
        }
        if(auxiliar.son.isLeaf() != null){
            stack.push(auxiliar);
            crossTree(auxiliar.son, stack);
        }
    }
   
   
   public void run() {
        
        long time = System.currentTimeMillis();
        this.ejecutarAlgoritmo();
       // this.pruneLeafs();
        long executionTime = System.currentTimeMillis() - time;
        System.out.println("Sliq Ejecutado en un tiempo de: "+executionTime+"ms");
        this.erectTree();
        ci.setInfo("Sliq: Arbol Generado en " + executionTime + "ms");
        ChooserEscritorio.setStatus("Sliq: Arbol Generado en " + executionTime + "ms");
        
       root.showSons();
//////////// ChooserEscritorio.addTimes( "Sliq",conjuntoDatos.length,conjuntoDatos[0].length, executionTime);
        
       this.ci.root = this.root;
       animation.stop();
    }
        
    public TreeCounter erectTree(){
        
        c = new TreeCounter();
        c.root = root;
        c.pruneLeafs();
        return c;
    }
    
}
