/*
 * Table.java
 *
 * Created on 30 de abril de 2006, 03:58 PM
 */

package gui.Icons.DBConnection;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

class Edge{
    Conector2 from;
    Conector2 to;
    boolean Default;
    
    Edge(Conector2 f, Conector2 t, boolean byDefault){
        this.Default = byDefault;
        from = f;
        from.increaseConection();
        to = t;
        to.increaseConection();
    }

    public boolean isDefault() {
        return Default;
    }
    
    public boolean isThisConector(Conector2 conector){
        return (conector.equals(from) || conector.equals(to));
    }
    
    public String getRelation(){
        return from.getCampo() + " = " + to.getCampo() ;
    }
    
    public String toString(){
        return getRelation();
    }
}

class Conector2 extends JComponent {
    boolean selected = false;
    Image imageConector = null;
    int key = 0;
    final int width = 20;
    final int height = 20;
    int conections = 0;
    final Color colorSeleccionado = new Color(210, 116, 226);
    final Color colorNormal = new Color(0, 0, 232);
    
    public Conector2(int key){
        this.setName("Conector");
        this.key = key;
        switch(key){
            case 1:
                imageConector = new ImageIcon(getClass().getResource("/images/primary")).getImage();
                break;
            case 2:
                imageConector = new ImageIcon(getClass().getResource("/images/foreing")).getImage();
                break;
            case 3:
                imageConector = new ImageIcon(getClass().getResource("/images/pri-for")).getImage();
                break;
        }
    }

    public int getKey() {
        return key;
    }
    
    public String getCampo(){
        JLabel l = (JLabel)(this.getParent().getComponent(1));
        return this.getParent().getParent().getName() + "." + l.getText() ;
    }
    
    public Table getTable(){
        return (Table)this.getParent().getParent();
    }
    
    public String getTableName(){
        return getTable().getName();
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }
    
    public int get_X(){
        return this.getParent().getParent().getX()
        + this.getParent().getX()
        + this.getX() + 10;
    }
    
    public int get_Y(){
        return this.getParent().getParent().getY()
        + this.getParent().getY()
        + this.getY() + 10;
    }
    
    public void increaseConection(){
        this.conections++;
    }
    
    public void decreaseConection(){
        this.conections--;
    }
    public synchronized void paint(Graphics g){
        if(key == 0){
            if(selected){
                g.setColor(colorSeleccionado);
                g.fillRect(7, 7, 6, 6);
            } else {
                g.setColor(colorNormal);
            }
            g.draw3DRect(5, 5, getPreferredSize().width - 11,
                    getPreferredSize().height - 11, selected);
        }else {
            g.drawImage(imageConector, 0, 0, this);
        }
    }
    
    public String toString(){
        return getCampo() + ": " + conections + " conections " + key + " key";
    }
}

class Campo extends JLabel{
    boolean seleccionado = false;
    boolean conectado = true;
    ImageIcon imageSelectorON = null;
    
    public Campo(String s){
        setName("Campo");
        setText(s);
        setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        imageSelectorON  = new ImageIcon(getClass().getResource("/images/ok"));
    }
    
    public Table getTable(){
        return (Table)this.getParent().getParent();
    }
}
/**
 *
 * @author  and
 */
public class Table extends javax.swing.JPanel {
    JLabel jLabel1;
    Conector2 c2;
    Campo campo;
    Color colorTitleTable = new Color(144, 201, 144);
    int n;  //numero de campos
    
    public Table(String name, int columns){
        initComponents();
        setLocation(50, 50);
        jLabel1 = new javax.swing.JLabel();
        n = columns + 1;
        setLayout(new java.awt.GridLayout(n, 1));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/table")));
        jLabel1.setText(name);
        setName(jLabel1.getText());
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel1.setOpaque(true);
        jLabel1.setBackground(colorTitleTable);
        jLabel1.setName("Table");
        add(jLabel1);
    }
    
    public void addColumn(String name, int key, boolean selected){
        JPanel row = new JPanel();
        c2 = new Conector2(key);
        campo = new Campo(name);
        row.setLayout(new BorderLayout(5, 0));
        row.add(c2, java.awt.BorderLayout.WEST);
        row.add(campo, java.awt.BorderLayout.CENTER);
        row.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        row.setName("Table");
        add(row);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(150, n * 24);
    }
    
//    /** This method is called from within the constructor to
//     * initialize the form.
//     * WARNING: Do NOT modify this code. The content of this method is
//     * always regenerated by the Form Editor.
//     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        PopupMenu = new javax.swing.JPopupMenu();
        mnuDelete = new javax.swing.JMenuItem();
        mnuSelectAll = new javax.swing.JMenuItem();
        mnuInvertSelection = new javax.swing.JMenuItem();

        PopupMenu.setInvoker(this);
        mnuDelete.setText("Delete");
        mnuDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuDeleteActionPerformed(evt);
            }
        });

        PopupMenu.add(mnuDelete);

        mnuSelectAll.setText("Select All");
        mnuSelectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSelectAllActionPerformed(evt);
            }
        });

        PopupMenu.add(mnuSelectAll);

        mnuInvertSelection.setText("Invert Selection");
        mnuInvertSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuInvertSelectionActionPerformed(evt);
            }
        });

        PopupMenu.add(mnuInvertSelection);

        setLayout(new java.awt.BorderLayout());

        setComponentPopupMenu(PopupMenu);
    }// </editor-fold>//GEN-END:initComponents
    
    private void mnuInvertSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuInvertSelectionActionPerformed
// TODO add your handling code here:
        MyCanvasTable parent = (MyCanvasTable)this.getParent();
        Vector columns = this.getColumns();
        int size = columns.size();
        for(int i = 0; i < size; i++){
            Campo camp = (Campo)columns.elementAt(i);
            if( camp.seleccionado ){
                parent.select.removeElement(this.getName() + "." + camp.getText());
                camp.seleccionado = false;
                camp.setIcon(null);
            } else {
                parent.select.addElement(this.getName() + "." + camp.getText());
                camp.seleccionado = true;
                camp.setIcon(camp.imageSelectorON);
                
            }
        }
        parent.mySelectorTable.setQuery(parent.selectToString());
        parent.repaint();
    }//GEN-LAST:event_mnuInvertSelectionActionPerformed
    
    private void mnuSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSelectAllActionPerformed
// TODO add your handling code here:
        MyCanvasTable parent = (MyCanvasTable)this.getParent();
        Vector columns = this.getColumns();
        int size = columns.size();
        for(int i = 0; i < size; i++){
            Campo camp = (Campo)columns.elementAt(i);
            if( !camp.seleccionado ){
                parent.select.addElement(this.getName() + "." + camp.getText());
                camp.seleccionado = true;
                camp.setIcon(camp.imageSelectorON);
            }
        }
        parent.mySelectorTable.setQuery(parent.selectToString());
        parent.repaint();
    }//GEN-LAST:event_mnuSelectAllActionPerformed
    
    public JPopupMenu getPopupMenu(){
        return PopupMenu;
    }
    
    public Vector getColumns(){
        Vector columns = new Vector(n - 1);
        
        for(int i = 1; i < n; i++){
            columns.addElement(
                    ((JPanel)this.getComponents()[i]).getComponents()[1] );
        }
        return columns;
    }
    
    public Vector getConectors(){
        Vector conectors = new Vector(n - 1);
        
        for(int i = 1; i < n; i++){
            conectors.addElement(
                    ((JPanel)this.getComponents()[i]).getComponents()[0] );
        }
        return conectors;
    }
    
    public Conector2 getConector(String column){
        for(int i = 1; i < n; i++){
            JLabel label = (JLabel)((JPanel)this.getComponents()[i])
            .getComponents()[1];
            if(label.getText().equals(column)){
                return (Conector2)((JPanel)this.getComponents()[i])
                .getComponents()[0];
            }
        }
        return null;
    }
    
    private void mnuDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuDeleteActionPerformed
// TODO add your handling code here:
        Vector conectors = this.getConectors();
        MyCanvasTable parent = (MyCanvasTable)this.getParent();
        Iterator it = parent.edges.iterator();
        while(it.hasNext()){
            Edge e = (Edge)it.next();
            int size = conectors.size();
            for(int i = 0; i < size; i++){
                if(e.from.equals(conectors.elementAt(i))){
                    e.to.selected = false;
                    e.to.decreaseConection();
                    parent.tableSelected = null;
                    it.remove();
                    break;
                } else if(e.to.equals(conectors.elementAt(i))){
                    e.from.selected = false;
                    e.from.decreaseConection();
                    parent.tableSelected = null;
                    it.remove();
                    break;
                }
            }
        }
        Vector columns = this.getColumns();
        int size = columns.size();
        for(int i = 0; i < size; i++){
            if( ((Campo)columns.elementAt(i)).seleccionado ){
                parent.select.removeElement(new String(this.getName() + "." +
                        ((Campo)columns.elementAt(i)).getText()));
            }
        }
        parent.mySelectorTable.setQuery(parent.selectToString());
        parent.remove(this);
        parent.repaint();
    }//GEN-LAST:event_mnuDeleteActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu PopupMenu;
    private javax.swing.JMenuItem mnuDelete;
    private javax.swing.JMenuItem mnuInvertSelection;
    private javax.swing.JMenuItem mnuSelectAll;
    // End of variables declaration//GEN-END:variables
    
}
