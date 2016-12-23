/*
 * AlgoritmoMateHilo.java
 *
 * Created on 17 de agosto de 2007, 03:28 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package algorithm.classification.mate;

import algorithm.classification.Value;
import algorithm.classification.c45_1.Attribute;
import algorithm.classification.c45_1.TreeCounter;
import gui.Icons.Clasification.ClasificationIcon;
import gui.Icons.DBConnection.ScrollableTableModel;
import gui.KnowledgeFlow.JackAnimation;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import gui.KnowledgeFlow.ChooserEscritorio;
import java.util.Stack;
import java.util.Vector;

/**
 *
 * 
 */
public class MateThread extends Thread{
    int num_columnas,num_registros,columnaClase,atributoGini;
    //long empieza,termina,tiempo;
    Object [][] conjuntoDatos;
    Object [][] auxDatos;
    String [] nomColumnas;
    ArrayList apilaDatos;
    public Attribute root;
    public ClasificationIcon ci;
    private JackAnimation animation;
    public TreeCounter c;
    Statement statement;
    ResultSet resulset;
    Vector []datosMate;
    String sqlMate;
    double minRow;
    
   /*    
    Stack pilaColumnas=new Stack();
    Stack pilaRows=new Stack();
    */
    boolean [] datosColumna;
    Vector datosRow= new Vector();
    
    
    /** Creates a new instance of AlgoritmoMateHilo */
    public MateThread(Object datos[][],String [] anomColumnas,ClasificationIcon ci)
    
    {
        int i,j; 
        this.ci = ci;
       
               
       
        /*     
        //muestro los datos
            for( i = 0; i <datos[0].length; i++){
                System.out.println("********** nomColumna: "+anomColumnas[i].toString());
                for( j = 0; j <datos.length; j++){
                    System.out.println(" - "+datos[j][i].toString()); 
                }
            }
        */
        //*************
        conjuntoDatos=datos;
        nomColumnas=anomColumnas;
         minRow= ci.minRows* conjuntoDatos.length/100;
         
       // System.out.println("sql Enviado a mate : "+ ci.sqlCreado+" minRows"+minRow); 
        
        String auxCad= ci.sqlCreado.substring(7,ci.sqlCreado.indexOf("FROM"));
        
        String postFrom = ci.sqlCreado.substring(ci.sqlCreado.indexOf("FROM"));
        
        String cadClase= auxCad.substring(auxCad.lastIndexOf(",")+1,auxCad.length()-1);
        auxCad=auxCad.substring(0,auxCad.lastIndexOf(","));
        /*System.out.println("columnas : "+ auxCad);
        System.out.println("clase: "+ cadClase); 
        System.out.println("select "+auxCad+","+cadClase+",count(*) "+postFrom+" MATE BY "+auxCad+" WITH "+cadClase+" GROUP BY "+auxCad+","+cadClase);*/
        sqlMate="select "+auxCad+","+cadClase+",count(*) "+postFrom+" MATE BY "+auxCad+" WITH "+cadClase+" GROUP BY "+auxCad+","+cadClase;
        
        datosMate=new Vector[nomColumnas.length+1];
        
        
        datosColumna=new boolean[nomColumnas.length];
        for(i=0;i<nomColumnas.length;i++){
              datosColumna[i]=true;
              datosMate[i]=new Vector();  
              for(j = 0; j < conjuntoDatos.length; j++){
                   if(i==0)datosRow.add(j);
                }
                
            }
        datosMate[i]=new Vector();  //la del count
        
           
        
    }
    
     private void ejecutarAlgoritmo() {
        int i,j;
        int indice;
        int numNodo;
        int mayorAtributo=-1;
        int colActivas=0;
                
        Double logaritmo2=Math.log10(2) ;
        Double auxLogaritmo;
        Double entroGral=0.0;
        Double mayorGanancia=0.0;
        Double entroParcial;
        
        Attribute auxAtributo;
        Attribute primerHijo = null;
                
        Vector datosClase=new Vector();
        Vector cantUnicos=new Vector(); 
        Vector datosUnicos=new Vector();
        Vector datosGanancia = new Vector();
        Vector datosAtributo=new Vector();
        Vector datosAtributoMayor = null;
        Vector auxVector;
        
        
        Stack pilaHijos=new Stack();
        Stack pilaDatos=new Stack();
        Stack pilaColumnas=new Stack();
        Stack pilaGanador=new Stack();
        boolean [] auxDatosColumna;
        
        String []valorAtributo;
        

        //nodo Raiz
        root=new Attribute(nomColumnas[nomColumnas.length-1]);
        root.nomColumna="raiz";
        root.frecuence=conjuntoDatos.length;
        root.setId(0);
             
        ScrollableTableModel stm = new ScrollableTableModel(ci.conexion,sqlMate);
        
        //cargando resultado operador mate
        for(i=0;i<stm.getColumnCount();i++){
            //System.out.println("* ");
            for(j=0;j<stm.getRowCount() ;j++ ){
                System.out.print(" - "+stm.getValueAt(j,i)); ///aqui modificado------------------------
                datosMate[i].add(stm.getValueAt(j,i));
            }
        }
        auxAtributo=root;
        numNodo=1;
        auxDatosColumna=datosColumna;
        
        valorAtributo=new String[datosRow.size()];
        
        
        
        do{
             //si es root
             if(!auxAtributo.equals(root)){
                                
                  /*
                       for(j=0;j<auxAtributo.datosAtributo.size();j++){
                           System.out.println(auxAtributo.datosAtributo.get(j).toString());
                       }*/
                       
                      //
                 //System.out.println("******************************** pilahijos*********************"); 
                /* for(j=0;j<pilaHijos.size();j++){
                           System.out.println(pilaHijos.get(j).toString());
                       }*/
                 auxDatosColumna= (boolean[]) pilaColumnas.peek();
                 mayorAtributo=(Integer) pilaGanador.peek(); 
                 //auxDatosColumna= (boolean[]) pilaColumnas.pop();
                 
                 
                // System.out.println("tamaño de la pila hijos: "+pilaColumnas.size()+" pila ganador"+pilaGanador.size());
                 auxDatosColumna[mayorAtributo]=false;
                 /*
                 for(i=0;i<auxDatosColumna.length-1 ;i++ )
                     System.out.print("--"+auxDatosColumna[i]);
                 */
                // System.out.println("Columnas Validas para: "+auxAtributo.name+" de la columna: "+nomColumnas[mayorAtributo]);    
                 
                 
                 //mayorAtributo=(Integer) pilaGanador.pop(); 
                 
                 //System.out.println("********** muestro los datos de "+auxAtributo.name);
                       datosRow = auxAtributo.datosAtributo;
                       
                      /* for(j=0;j<datosRow.size();j++){
                           System.out.println(datosRow.get(j).toString());
                       }*/
                 
                       
                       auxAtributo.frecuence=datosRow.size();
                       valorAtributo[mayorAtributo]=auxAtributo.name;
                       
             }
             
             datosClase.removeAllElements();
             cantUnicos.removeAllElements();
             
            //hago el conteo de los valores de los atributos clase
             for(i=0;i<datosRow.size();i++){
                  
                 indice=datosClase.indexOf(conjuntoDatos[ (Integer) datosRow.get(i)][conjuntoDatos[0].length-1]);
                 if(-1==indice){
                      datosClase.add(conjuntoDatos[ (Integer) datosRow.get(i)][conjuntoDatos[0].length-1]);
                      cantUnicos.add(1);
                 } else{
                     
                     cantUnicos.setElementAt(((Integer) cantUnicos.get(indice))+1,indice );
                     
                 }  
                 
             }
              //muestro   valores clase
            /* for(j=0;j<datosClase.size();j++ ){
                 
                 System.out.println("clase: "+(j+1)+" valor: "+datosClase.get(j).toString()+" cant: "+cantUnicos.get(j).toString());
             }*/
             
             

                //lleno el array de valores
                 auxAtributo.valuesClass=new ArrayList(); 
                     for(int m=0;m<datosClase.size() ;m++){
                             //System.out.println(m+"  - "+ datosClase.get(m).toString()+" cant: "+ (Integer)cantUnicos.get(m) ); 
                                    
                              auxAtributo.valuesClass.add(new Value(datosClase.get(m).toString(),(Integer) cantUnicos.get(m))); 
                 }

            
                //miro si solo hay un valor de clase, si es 1 es una hoja
             
             if(datosClase.size()==1){
                  
                         //System.out.println(" entro por el hijo unico");
                         auxAtributo.son=new Attribute(datosClase.get(0).toString());
                         auxAtributo.son.frecuence=(Integer) cantUnicos.get(0);
                         auxAtributo.son.frecuenceFather=auxAtributo.frecuence ; 
                         auxAtributo.son.nomColumna=nomColumnas[nomColumnas.length-1];
                         auxAtributo.son.setId(numNodo);
                         numNodo++;
                        
                         if(auxAtributo.brother==null){
                           
                            while(auxAtributo.brother==null && pilaHijos.size()>1 ){//aumente
                             auxAtributo=(Attribute) pilaHijos.peek();
                             //
                             if(auxAtributo.brother==null){
                             // System.out.println(" Atributo: "+auxAtributo.name+" pilaHijos: "+pilaHijos.size()+" pilaDatos: "+pilaDatos.size() );
                              pilaHijos.pop();
                              //pilaDatos.pop();
                              pilaColumnas.pop();
                              //pilaGanador.pop();
                              valorAtributo[(Integer) pilaGanador.pop()]=null;
                              valorAtributo[mayorAtributo]=null;
                             }
                                 
                           }//fin while
                           auxAtributo=auxAtributo.brother;
                           
                            // aumente
                           pilaHijos.pop();
                           //pilaDatos.pop();
                           pilaColumnas.pop();
                           //pilaGanador.pop();
                           valorAtributo[(Integer) pilaGanador.pop()]=null;
                           valorAtributo[mayorAtributo]=null;
                         }
                          else{  
                            
                             auxAtributo=auxAtributo.brother;
                              
                          }
                        
             }else{//hay mas de un valor de clase
                 
                 mayorAtributo=-1;
                //calculamos Entropia General
                 entroGral=0.0;
                 mayorGanancia=-2.0;
                 colActivas=0;
                 if(minRow <datosRow.size()){
                for( i=0;i<datosClase.size();i++){
                    
                    entroGral-=(((Double.valueOf(cantUnicos.get(i).toString()))/datosRow.size()) * ((Math.log10( Double.valueOf(cantUnicos.get(i).toString())/ datosRow.size()) )/logaritmo2));
                    //System.out.println("frac: "+ (Double.valueOf(cantUnicos.get(i).toString())/datosRow.size() )+" log: "+(Math.log10( Double.valueOf(cantUnicos.get(i).toString())/ datosRow.size()) )+" temp: "+i+" - "+entroGral);
                }   
                 
                 
                 for(i=0;i<auxDatosColumna.length-1;i++){
                     if(auxDatosColumna[i]){
                         //System.out.println("Si entro a calcular");
                         colActivas++;
                         cantUnicos.removeAllElements();
                         datosUnicos.removeAllElements();
                         datosAtributo=new Vector();
                         entroParcial=0.0;
                         //hago el conteo de los valores de cada Atributo
                         for(j=0;j<datosRow.size();j++){

                             indice=datosUnicos.indexOf(conjuntoDatos[ (Integer) datosRow.get(j)] [i]);
                             if(-1==indice){
                                  datosUnicos.add(conjuntoDatos[ (Integer) datosRow.get(j)][i]);
                                  cantUnicos.add(1);
                                  datosAtributo.add(new Vector());
                                  auxVector=(Vector) datosAtributo.lastElement();  
                                  auxVector.add((Integer) datosRow.get(j));
                                  
                             } else{

                                 cantUnicos.setElementAt(((Integer) cantUnicos.get(indice))+1,indice );
                                 auxVector=(Vector) datosAtributo.get(indice);  
                                 auxVector.add((Integer) datosRow.get(j));
                                 
                             }  //fin else

                         }//fin for
                         
                         //buscamos en la matriz datosMate
                         int valor=0;boolean bandera;
                         
                         for(j=0;j<datosUnicos.size();j++){
                            //System.out.println("*******conteo atributo: "+nomColumnas[i]+" * "+ (j+1)+" valor: "+datosUnicos.get(j).toString()+" cant: "+cantUnicos.get(j).toString());
                            valor=0;
                            
                            while(valor!=-1){
                               
                                valor=datosMate[i].indexOf(datosUnicos.get(j),valor);
                                if(valor!=-1){
                                //System.out.println("valor: "+valor+" j: "+j+" i: "+i);
                                bandera=true;
                                  for(int k =i+1;k<datosMate.length-2 && bandera==true;k++){
                                    //System.out.println("en for: "+datosMate[k].get(valor)+" con: "+valorAtributo[k]);  
                                    
                                    if(valorAtributo[k]!=null){ 
                                         if(!valorAtributo[k].equals((String) datosMate[k].get(valor)))
                                             bandera=false;
                                        
                                    }else
                                        if(datosMate[k].get(valor)!=null){
                                         bandera=false;
                                       }
                                    }
                                    
                                  for(int k=i-1;-1 < k && bandera==true;k--){
                                    //System.out.println("en for: "+datosMate[k].get(valor)+" con: "+valorAtributo[k]);  
                                    if(valorAtributo[k]!=null){ 
                                         if(!valorAtributo[k].equals((String) datosMate[k].get(valor)))
                                             bandera=false;
                                        
                                    }else
                                        if(datosMate[k].get(valor)!=valorAtributo[k]){
                                            bandera=false;
                                         }
                                    }
                                
                                
                                if(bandera==true){
                                     //System.out.println("count: "+datosMate[datosMate.length-1].get(valor).toString());
                                    entroParcial-=((Double.valueOf(cantUnicos.get(j).toString()))/datosRow.size()) * (((Double.valueOf(datosMate[datosMate.length-1].get(valor).toString()   ))/(Integer)cantUnicos.get(j)) * ((Math.log10( Double.valueOf(datosMate[datosMate.length-1].get(valor).toString())/ (Integer)cantUnicos.get(j)) )/logaritmo2));
                                }
                                  valor++;
                                }
                            }
                             
                             
                         }
                         
                         //System.out.println("entro Parcial: "+entroParcial);
                         entroParcial=entroGral-entroParcial;
                         //miramos si tiene la mayor ganacia
                         if(mayorGanancia<entroParcial){
                             mayorGanancia=entroParcial;
                             mayorAtributo=i;
                             datosAtributoMayor=datosAtributo;
                             datosGanancia.removeAllElements(); 
                             for(int k=0; k <datosUnicos.size();k++)
                                datosGanancia.add(datosUnicos.get(k));
                              
                         }
                             
                         
                         
                         
                                               
                     }//fin if columna
                         
                     
                     
                 }//fin for
                 }//fin minRow
                if(colActivas!=0){ 
                 //System.out.println("la mayor ganacia la tiene: "+nomColumnas[mayorAtributo]+" con: "+mayorGanancia);
                 
                //guardamos en las pilas: los datos, el atributo mayor,
                 
                                      
                  boolean [] auxBool=null;
                  auxBool=new boolean[auxDatosColumna.length];
                  for(int k=0;k<auxDatosColumna.length-1 ;k++ )
                        auxBool[k]=auxDatosColumna[k];
                   
                   pilaHijos.push(auxAtributo);
                   //pilaDatos.push(datosRow);
                   pilaColumnas.push(auxBool);
                   pilaGanador.push(mayorAtributo);
                                     
                                    
                   auxDatosColumna=null;
                   //auxDatosColumna=auxBool;
                  
                   
                // adicionamos la nueva rama al arbol
                 
                  for(i=0;i < datosGanancia.size();i++ ){
                             
                       //System.out.println("Nodo "+i+": "+datosGanancia.get(i).toString());
                       
                       /*
                       for(j=0;j<auxVector.size();j++){
                           System.out.println(auxVector.get(j).toString());
                       }*/
                       
                     if(i==0){
                         //adiciono el nuevo nodo
                       auxAtributo.son=new Attribute(datosGanancia.get(i).toString() );
                       auxAtributo.son.frecuenceFather=auxAtributo.frecuence;
                       auxAtributo=auxAtributo.son;
                        primerHijo=auxAtributo;

                     }
                     else{ 
                         auxAtributo.brother=new Attribute(datosGanancia.get(i).toString());
                         auxAtributo.brother.frecuenceFather=auxAtributo.frecuenceFather;
                         auxAtributo=auxAtributo.brother;    
                     }
                     auxAtributo.nomColumna=nomColumnas[mayorAtributo];
                     auxAtributo.setId(numNodo);
                     auxAtributo.datosAtributo=(Vector) datosAtributoMayor.get(i);
                     numNodo++;

                 }
                 auxAtributo=primerHijo;
                
                } else{
                       
                     //System.out.println("entro a encontrar el mayor");
                        //como hay mas de un valor clase y solo queda la columna clase 
                        //escojo el de mayor frecuencia 
                         int mayorFrecuencia,indiceMayor;
                                mayorFrecuencia=(Integer) cantUnicos.get(0);
                                indiceMayor=0;
                                for(int r=0;r<cantUnicos.size();r++){
                                    if( mayorFrecuencia <(Integer)cantUnicos.get(r)){
                                        indiceMayor=r;
                                        mayorFrecuencia=(Integer)cantUnicos.get(r);
                                    }
                                }
                          
                                
                         //System.out.println("termino Imcompleto");
                         auxAtributo.son=new Attribute((String) datosClase.get(indiceMayor));
                         auxAtributo.son.frecuence=mayorFrecuencia;
                         auxAtributo.son.frecuenceFather=auxAtributo.frecuence;
                         auxAtributo.son.nomColumna=nomColumnas[nomColumnas.length-1];
                         auxAtributo.son.setId(numNodo);
                         numNodo++;
                         
                         if(auxAtributo.brother==null && pilaHijos.size()>0 ){
                           //  System.out.println(" entro por el ciclo");
                             
                             //aumente
                             while(auxAtributo.brother==null && pilaHijos.size()>1){
                             
                              auxAtributo=(Attribute) pilaHijos.peek();
                               
                             
                                 //if(auxAtributo!=null){
                                 if(auxAtributo.brother==null){
                                    // System.out.println(" 2 Atributo: "+auxAtributo.name+" pilaHijos: "+pilaHijos.size()+" pilaDatos: "+pilaDatos.size() );

                                  pilaHijos.pop();
                                  //pilaDatos.pop();
                                  pilaColumnas.pop();
                                  //pilaGanador.pop();
                                  valorAtributo[(Integer) pilaGanador.pop()]=null;
                                     
                                  /*pilaDatos.remove(pilaDatos.size()-1);
                                  pilaNomColumnas.remove(pilaNomColumnas.size()-1);
                                  columnGiniMenor.remove(columnGiniMenor.size()-1 );*/

                                 }
                                                          
                             }//fin while
                            auxAtributo=auxAtributo.brother; 
                            
                            //aumente
                            pilaHijos.pop();
                            //pilaDatos.pop();
                            pilaColumnas.pop();
                            //pilaGanador.pop();
                            valorAtributo[(Integer) pilaGanador.pop()]=null;
                            
                            
                           }
                          else  
                          { //System.out.println(" entro por el Hermano en mayor valor");
                              auxAtributo=auxAtributo.brother;                                   
                              
                          }
                             
                         
                   }
                 
                   //
                 
                 
                 
                 
                 
             }//fin else de 1 en clase
             
            // auxAtributo=null;
            
            
        }while(auxAtributo!=null);
            
        
        
        
        
        
        
        
        
             
        
        
        
        
         
    }
    
    public void setAnimation(JackAnimation animation){
        this.animation = animation;
     }
    
    public void run() { 
        long time = System.currentTimeMillis();
        this.ejecutarAlgoritmo();
        long executionTime = System.currentTimeMillis() - time;
        System.out.println("Mate Ejecutado en un tiempo de: "+executionTime+"ms");
        this.erectTree();
        ci.setInfo("Mate: Arbol Generado en " + executionTime + "ms");
        ChooserEscritorio.setStatus("Mate: Arbol Generado en " + executionTime + "ms");
        //aqui va a ser
       root.showSons();
       ChooserEscritorio.addTimes( "Mate",conjuntoDatos.length,conjuntoDatos[0].length, executionTime);
        
        this.ci.root = this.root;
        animation.stop();
    }
    
     public TreeCounter erectTree(){
        
        c = new TreeCounter();
        c.root = root;
        c.pruneLeafs();
        return c;
    }

   //ejecutamos el operador Mate con stament 
        /*
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        try {
            
            statement = ci.conexion.createStatement();
            
            resulset = statement.executeQuery(sqlMate);
            
            while(resulset.next()){
                
                for( i = 0; i <nomColumnas.length+1; i++){
                    //System.out.print("" + resulset.getString(i+1));
                    //datosMate[i].add(""+resulset.getString(i+1));
                    
                }
                //System.out.println();
                                
               }
         } catch (SQLException ex) {
            ex.printStackTrace();
        }
       */
         //************
        
        
}
