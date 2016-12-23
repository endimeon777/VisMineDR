package gui.performance;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.util.Vector;
import javax.swing.JPanel;
/*
 * Pintar.java
 *
 * Created on 13 de agosto de 2007, 11:34 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * 
 */
public class Pintar extends JPanel{
    Vector tiempos;
    int [] mateX;
    int [] mateY;
    int [] c45X;
    int [] c45Y;
    int [] sliqX;
    int [] sliqY;
    int pintar=0;
    String [] numHor;
    String [] numVer;
    /** Creates a new instance of Pintar */
    public Pintar() {
     
    }
    
    public Pintar(Vector tiemposAlgoritmos){
     
        tiempos=tiemposAlgoritmos;
    }
    
    public Pintar(int [] amateX,int [] amateY,int [] ac45X,int [] ac45Y,int [] asliqX,int [] asliqY,String [] anumHor,String [] anumVer){
    mateX=amateX;
    mateY=amateY;
    c45X=ac45X;
    c45Y=ac45Y;
    sliqX=asliqX;
    sliqY=asliqY;
    numHor=anumHor;
    numVer=anumVer;
        
    }
    
    public void paint(Graphics g) {
        int i;
        Graphics2D g2 = (Graphics2D) g;
            
         //lineas plano 
         g2.setColor(Color.BLACK);
         g2.drawLine(50,20,50,310);//vertical
         g2.drawLine(49,20,49,310);//vertical
         g2.drawLine(40,300,430,300);//horizontal
         g2.drawLine(40,301,430,301);//horizontal
         
        //numeros horizontales
         g2.drawString("0",50,320);
         for(i=0;i<10;i++){
            g2.drawString(" |",70+(i*35),305); 
            g2.drawString(numHor[i],70+(i*35),320); 
           
         }
         
         //numeros Verticales
         for(i=0;i<13;i++){
            g2.drawString("_",50,280-(i*20)); 
            g2.drawString(numVer[i],3,285-(i*20)); 
           
         }
         
         
         //etiquetas
         g2.setColor(Color.gray);
         g2.drawString("Num. Registros",360,340);
         g2.drawString("Tiempo (ms)",2,15);
       
        //punto (0,0) es 50,300 
         
         // Mate Azul
        if(mateX.length>1 && (pintar==0 || pintar==1) ){
          g2.setColor(Color.BLUE);
          g2.drawPolyline(mateX,mateY,mateX.length);
                   
          for(i=0;i<mateX.length-1;i++){
           g2.drawLine(mateX[i]-1,mateY[i]-1,mateX[i+1]-1,mateY[i+1]-1);
           //g2.drawLine(mateX[i]-2,mateY[i]-2,mateX[i+1]-2,mateY[i+1]-2);    
          }
          
        }
        // C4.5 Verde 
         if(c45X.length>1 && (pintar==0 || pintar==2)){
          g2.setColor(Color.GREEN);
          g2.drawPolyline(c45X,c45Y,c45X.length);    
          
          for(i=0;i<c45X.length-1;i++){
           //g2.drawLine(c45X[i]-2,c45Y[i]-2,c45X[i+1]-2,c45Y[i+1]-2);
           g2.drawLine(c45X[i]-1,c45Y[i]-1,c45X[i+1]-1,c45Y[i+1]-1);    
          }
         
         }
        // Sliq Rojo 
         if(sliqX.length>1 && (pintar==0 || pintar==3)){
           g2.setColor(Color.RED);
           g2.drawPolyline(sliqX,sliqY,sliqX.length); 
           
           for(i=0;i<sliqX.length-1;i++){
           //g2.drawLine(sliqX[i]-2,sliqY[i]-2,sliqX[i+1]-2,sliqY[i+1]-2);
           g2.drawLine(sliqX[i]-1,sliqY[i]-1,sliqX[i+1]-1,sliqY[i+1]-1);    
          }
         }
        
    }//fin paint

    
}
