/*
 * Histograma.java
 *
 * Created on 3 de agosto de 2007, 04:59 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package algorithm.classification.Sliq;

import java.util.Vector;
/**
 *
 * 
 */
public class Histograma {
       Vector datoUnico;
       int [] cantidad;
       int [][] cantClase;
       Vector [] subConjuntos;
       float cantTotal;
       public float mejorGini;
       public Vector [] mejorDivision;
               
       public Histograma(int cantDatos,int numValClases,int acantTotal ){
           datoUnico=new Vector();//Object[cantDatos];
           cantidad=new int [cantDatos];
           cantClase=new int[cantDatos][numValClases];
           cantTotal=acantTotal+1;
       }
       
       public void printHistograma(){
           int i,j;    
            
          // System.out.println("******************* HISTOGRAMA -- total Registros"+cantTotal);
           
           System.out.println(" Dato  -  cant - CantClase");
           
           //for(i=0;i<datoUnico.length ;i++){
            for(i=0;i<datoUnico.size() ;i++){
               //System.out.print(" * "+datoUnico[i].toString());
               System.out.print(" * "+datoUnico.get(i).toString());
               System.out.print(" # "+ cantidad[i]);
               for(j=0;j<cantClase[i].length ;j++){
                 System.out.print(" / "+cantClase[i][j]);
               }
             
           System.out.println();   
           
           }
           System.out.println("*******************");
       }
       
       public void giniCategorico(){
           int i=0,numSub=0,puntero=0,j=0,k=0,m,x=0,y=0,indClase;
           int cant=1;
           float frecuencia;
           float aVal,menorGini;
           int [] totalFre=new int[cantClase[0].length]; 
           int cantPar=0;
           float [] gini;
           int [] subGiniMenor;
           
           //for(i=0;i<datoUnico.length;i++){
           for(i=0;i<datoUnico.size();i++){
               cant=cant*2;
           }
           subConjuntos = new Vector[cant];
           gini=new float[cant];
           numSub=0;
           //hago la union de los valores unicos para formar los subconjuntos
           for(i=0;i<datoUnico.size();i++){
               frecuencia=0;
               puntero=numSub;
               subConjuntos[numSub]=new Vector();
               subConjuntos[numSub].add(datoUnico.get(i));
               //calculamos el gini para un subconjunto con un elemento
               for(x=0;x<cantClase[i].length;x++){
                   aVal=  ((float)cantClase[i][x])/((float)cantidad[i]);
                   frecuencia+=(aVal*aVal);
               }
               gini[numSub]= (((float)cantidad[i])/(cantTotal) )*( 1 - frecuencia); 
               
               numSub++;
               
               for(j=i;j<datoUnico.size()-1;j++ ){
                   for(k=j+1;k<datoUnico.size();k++){
                       subConjuntos[numSub]=new Vector();
                       
                        // coloco en 0 el arreglo totalFre
                       for(y=0;y<cantClase[0].length;y++)totalFre[y]=0;
                       cantPar=0;
                       // meto cada valor del Subconjunto compuesto    
                       for(m=0;m<subConjuntos[puntero].size();m++){
                           //encuentro el valor unico del subgrupo en el histograma
                           indClase=datoUnico.indexOf(subConjuntos[puntero].get(m));
                           //sumo la cantidad de registros de este subconjunto
                           cantPar+=cantidad[indClase];
                           //sumo la cantidad de registros de este subconjunto segun la clase
                           for(y=0;y<cantClase[0].length;y++){
                               totalFre[y]+=cantClase[indClase][y];
                           }
                                                     
                           subConjuntos[numSub].add(subConjuntos[puntero].get(m));
                       
                       }
                       //adiciono el elemento nuevo
                       subConjuntos[numSub].add(datoUnico.get(k));
                       //adicono la cantidad de es elemento a adicionar
                       cantPar+=cantidad[k];
                       for(y=0;y<cantClase[0].length;y++){
                               totalFre[y]+=cantClase[k][y];
                           }
                       //encuento los valores 
                       frecuencia=0;
                       for(y=0;y<cantClase[i].length;y++){
                        aVal=  ((float)totalFre[y] )/((float)cantPar);
                        frecuencia+=(aVal*aVal);
                       
                      }
                       gini[numSub]= (((float)cantPar)/(cantTotal) )*( 1 - frecuencia);
                      
                       numSub++;
                   }
                   puntero++;
               }
           }
          
          // System.out.println("*************** subconjuntos Encontrados");
           // System.out.println("@");
           
           /* for(i=0;i<numSub;i++){
               for(j=0; j<subConjuntos[i].size();j++)
                // System.out.print(subConjuntos[i].get(j).toString()+" * ");
               
               System.out.println(" -> gini: "+gini[i]);
           }
            */
            
            //union de los subconjuntos
            
            frecuencia=0;
            
            //calculando el valor del gini de la union de todos los valores unicos
            for(i=0;i<cantClase[0].length;i++ ){
            aVal=0;
                for(j=0;j<datoUnico.size();j++) {
                    aVal+= cantClase[j][i];
                    
                }
               //System.out.println("aval= "+aVal); 
               aVal =(aVal/cantTotal);
                aVal=aVal*aVal;
                frecuencia+=aVal;
            }
            menorGini=1-frecuencia;
            // System.out.println("primer MenorGIni= "+menorGini);           
            
            //para el grupo de menor Gini             
             int [] grupoMenor=new int[datoUnico.size()];
             int pMenor=-1;
            
             
            // union de subGrupos*********************
            int [] grupo=new int[datoUnico.size()];
            int p=0,agrupado;
            boolean ban=false; 
            
            for(i=0;i<numSub;i++ ){
                p=0;
                grupo[p]=i;
                agrupado=subConjuntos[i].size();
                p++;
                for(j=i+1;j<numSub ;j++){
                    if(estaElemento(subConjuntos[i],subConjuntos[j])==false){
                       //System.out.println("valor de p:"+p+" valor de j: "+j+" i: "+i);
                        grupo[p]=j;
                        agrupado+=subConjuntos[j].size();
                        p++;
                        //hacia abajo
                        for(k=j+1;k<numSub && agrupado<datoUnico.size();k++ ){
                            ban=false;
                            for(m=0;m<p && ban==false;m++){
                                if(estaElemento(subConjuntos[grupo[m]],subConjuntos[k]))
                                   ban=true;                                   
                            }
                            
                            if(ban==false){
                                grupo[p]=k;
                                agrupado+=subConjuntos[k].size();
                                p++;
                            }
                        }
                        
                         //hacia arriba
                        for(k=j-1;k>-1 && agrupado<datoUnico.size();k-- ){
                            ban=false;
                            for(m=0;m<p && ban==false;m++){
                                if(estaElemento(subConjuntos[grupo[m]],subConjuntos[k]))
                                   ban=true;                                   
                            }
                            
                            if(ban==false){
                                grupo[p]=k;
                                agrupado+=subConjuntos[k].size();
                                p++;
                            }
                        }
                            
                    }
                    
                   if(agrupado==datoUnico.size()){
                        frecuencia=0;
                        for(m=0;m<p;m++){
                           // System.out.print(" _U_ "+grupo[m] );
                            //sumo los ginis de los subgrupos encontrados
                            frecuencia+=gini[grupo[m]];
                            
                        }
                     
                        //System.out.println("  ###################"+frecuencia);
                        if(frecuencia<menorGini){
                            pMenor=p;
                            menorGini=frecuencia;
                            for(m=0;m<p;m++)
                                grupoMenor[m]=grupo[m];
                          //System.out.println("cambio *******");      
                        
                        }

                    }
                    //limpiar las variables utilizadas
                    for(m=1;m<p;m++) 
                        grupo[m]=-1;
                    p=1;
                    agrupado=subConjuntos[i].size();
               
                }
                
               
                
                
            }
           
            //System.out.println(" EL MEJOR GINI CATEGORICO ES: " );
            String nodoEncontrado;
            
            
            if(pMenor!=-1){
               mejorDivision=new Vector[pMenor];
                
               for(m=0;m<pMenor;m++){
                  //System.out.print(" _U_ "+grupoMenor[m] );
                   mejorDivision[m]=subConjuntos[grupoMenor[m]];
                   nodoEncontrado="[";
                   nodoEncontrado+=(""+subConjuntos[grupoMenor[m]].get(0).toString());
                 
                  for(k=1;k<subConjuntos[grupoMenor[m]].size();k++ ){
                      
                      nodoEncontrado+=(","+subConjuntos[grupoMenor[m]].get(k).toString());
                      //mejorDivision[m].add(subConjuntos[grupoMenor[m]].get(k));
                  }
                  nodoEncontrado+="]";
                 // System.out.print(nodoEncontrado+" U ");                  
                }
                
                  //System.out.println(" Menor Gini -> "+ menorGini);
            }
            else{
                 mejorDivision=new Vector[1];
                 mejorDivision[0]=subConjuntos[0];
                
                 
                 //System.out.println(" Menor Gini@@@: "+ menorGini);
                 nodoEncontrado="[";
                  nodoEncontrado+=(""+datoUnico.get(0).toString());
                  for(k=1;k<datoUnico.size();k++ ){
                      nodoEncontrado+=(","+datoUnico.get(k).toString());
                  }
                  nodoEncontrado+="]";
            }
            //coloco el menor gini y sus subgrupos   
           
            mejorGini=menorGini;
            
            
           
       }
       
       
       public boolean estaElemento( Vector uno,Vector dos){
           boolean esta=false;
           int i,j;
           esta=false;
           for(i=0;i<uno.size()&& esta==false ;i++ ){
               if( dos.indexOf(uno.get(i))!=-1)
                esta=true;
           }
           return esta;
       }

        public void giniNumerico() {
            int i,j,pMenor;
            float giniMenor,auxGini,frecuencia,aVal;
            
            
            
            pMenor=-1;giniMenor=1;
            
            for(i=0;i<datoUnico.size() ;i++){
               //System.out.print(" * "+datoUnico.get(i).toString());
               //System.out.print(" # "+ cantidad[i]);
                frecuencia=0;aVal=0;
               for(j=0;j<cantClase[i].length ;j++){
                     aVal=((float)cantClase[i][j])/((float)cantidad[i]);
                     
                     aVal=aVal*aVal; 
                    
                     frecuencia+=aVal;
               }
                auxGini=1-frecuencia;
                //System.out.println(i+" gini "+auxGini);
                if(auxGini<giniMenor && cantidad[i]>1){
                    pMenor=i;
                    giniMenor=auxGini;
                }
            }
           
            //System.out.println("Menor Gini Numerico es: "+pMenor+" gini:"+giniMenor);
           mejorGini=giniMenor;
           mejorDivision=new Vector[1];
           mejorDivision[0]=new Vector();
           mejorDivision[0].add(datoUnico.get(pMenor));
            
        }
       
       
   }
