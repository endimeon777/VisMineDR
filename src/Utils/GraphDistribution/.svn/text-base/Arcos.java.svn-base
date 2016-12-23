package Utils.GraphDistribution;
import algorithm.classification.Value;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import javax.swing.*;

public class Arcos extends JPanel {
    private double ini;
    private Color color;
    ArrayList values;
    double total;
    int n;
    
    public Arcos(ArrayList values, int total) {
        setBackground(Color.WHITE);
        this.values = values;
        this.total = (double)total;
        n = values.size();
    }
    
    public void porValores(Graphics2D g2) {
        double grados;
        int ancho = 185;
        int alto = 105;
        int x = 10;
        int y = 50;
        ini = 90.0;
        
        for(int i = 0; i < 25; i++){
            for(int j = 0; j < n; j++){
                double value = (double)((Value)values.get(j)).getFrecuence();
                grados = (value * 360) / total;
                color = Grafico.getColor(((Value)values.get(j)).getName());
                g2.setPaint(color);
                g2.fill(new Arc2D.Double(x, y - i, ancho, alto, ini, grados, 2));
                ini += grados;
            }
        }
        color = new Color(245,245,245);
        color.brighter();
        g2.setPaint(color);
        g2.drawArc(x, y - 24, ancho, alto, 180, 180);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        porValores(g2);
    }
}
