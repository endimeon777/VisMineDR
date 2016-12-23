/*
 * pnlArbol.java
 *
 * Created on 27 de enero de 2010, 03:25 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gui.Icons.Cluster.viewsClus;

/**
 *
 * @author Administrador
 */
import algorithm.cluster.jerarquico.Birch;
import algorithm.cluster.jerarquico.Nodo;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Graphics2D;	 
import javax.swing.table.AbstractTableModel;
	

public class pnlArbol extends JPanel {
    BufferedImage ima;
    private Color colorfondo=Color.YELLOW;
    private Color colorHoja=Color.RED;
    private Color colorLinea=Color.RED;
    private Color colorDatos=Color.BLUE;
    int  alto=600;
    int ancho =800;
     private Nodo raiz;
    
	   // usar figura para dibujar un óvalo o rectángulo
	   public void paintComponent( Graphics g )
	   {
	      super.paintComponent( g );
	 
	         g.fillOval( 50, 10, 200, 160 );
	   }
	 
           
	   // establecer valor de figura y repintar PanelPersonalizado
	   public void dibujar(Nodo _ra )
	   {
               this.raiz=_ra;
               System.out.println("volver a mostrar arbol");
               mostrarData(raiz.dataNodo);
	       repaint();
               
	   }
           
         public void mostrarData(AbstractTableModel tabla){
         
        for (int i=0;i<tabla.getRowCount();i++){
            for (int j=0;j<tabla.getColumnCount();j++){
                System.out.print("   "+tabla.getValueAt(i,j));
            }
             System.out.println("");
        }
    }
   public BufferedImage crearPNG(int _ancho,int _alto ) { 
       this.ancho=_ancho;
       this.alto=_alto;
       
        BufferedImage bufferedImage = new BufferedImage(_ancho,_alto, BufferedImage.TYPE_INT_RGB);  
        Graphics2D g2 = bufferedImage.createGraphics();  
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  
              RenderingHints.VALUE_ANTIALIAS_ON);  
            g2.setColor(colorfondo);  
         
            
            g2.fillRect(0,0, _ancho, _alto);  
            g2.setColor(Color.BLUE);  
                  
             
             int an=_ancho/10;
             int al=_alto/15;
             
             g2.fillOval( getCentrox()-50, 10, an, al );
             g2.setColor(Color.RED);  
             
             g2.drawString("Olger",getCentrox()+5,al-25);
        // Aquí deberíamos introducir el código que queramos pintar.  
            g2.dispose();  
           return bufferedImage;  
            }  

  private int getCentrox(){
      int cex=ancho/2;
      return cex;
      
  } 
   //esto guarda la imagen



 }
        