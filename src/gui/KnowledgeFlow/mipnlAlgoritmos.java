/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * mipnlPreprocesamiento.java
 *
 * Created on 24-ene-2012, 14:44:23
 */
package gui.KnowledgeFlow;

import java.awt.Component;
import java.awt.datatransfer.StringSelection;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

/**
 *
 * @author Juan Carlos
 */

public class mipnlAlgoritmos extends javax.swing.JPanel implements DragSourceListener, DragGestureListener{

    
private DragSource dragSource;
private miContenedor container;

    /** Creates new form mipnlPreprocesamiento */
    public mipnlAlgoritmos(miContenedor container) {
        this.container = container;
        initComponents();
        // Step 1: Create a DragSource instance
        dragSource = new DragSource();
        // Step 2 and 3: Ask the DragSource to watch this JList
        //(first this) and to
        // notify this class when a drag gesture is recognized (second this)
        dragSource.createDefaultDragGestureRecognizer( this,DnDConstants.ACTION_COPY, this);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Algoritmos/EquipAsso.png"))); // NOI18N
        jLabel10.setText("EquipAsso");
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel10.setName("EquipAsso"); // NOI18N
        jLabel10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Algoritmos/FPGrowt.png"))); // NOI18N
        jLabel11.setText("FPGrowth");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel11.setName("FPGrowth"); // NOI18N
        jLabel11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Algoritmos/Apriori.png"))); // NOI18N
        jLabel12.setText("Apriori");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel12.setName("Apriori"); // NOI18N
        jLabel12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setName("otro"); // NOI18N

        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Algoritmos/C45.png"))); // NOI18N
        jLabel13.setText("  C45  ");
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel13.setName("c45"); // NOI18N
        jLabel13.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Algoritmos/Mate.png"))); // NOI18N
        jLabel14.setText("  Mate  ");
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel14.setName("mate"); // NOI18N
        jLabel14.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel1.setFont(new java.awt.Font("Dialog", 2, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CLASIFICATION");
        jLabel1.setName("otro"); // NOI18N

        jLabel2.setFont(new java.awt.Font("Dialog", 2, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ASSOCIATION");
        jLabel2.setName("otro"); // NOI18N

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator2.setName("otro"); // NOI18N

        jLabel3.setFont(new java.awt.Font("Dialog", 2, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 153));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("CLUSTER");
        jLabel3.setName("otro"); // NOI18N

        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Algoritmos/kmeans.png"))); // NOI18N
        jLabel15.setText("Kmeans");
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel15.setName("kmeans"); // NOI18N
        jLabel15.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Algoritmos/Clarans.png"))); // NOI18N
        jLabel16.setText("Clarans");
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel16.setName("Clarans"); // NOI18N
        jLabel16.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Algoritmos/Birch.png"))); // NOI18N
        jLabel17.setText("Birch");
        jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel17.setName("Birch"); // NOI18N
        jLabel17.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Algoritmos/Sliq.png"))); // NOI18N
        jLabel18.setText("Sliq");
        jLabel18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel18.setName("sliq"); // NOI18N
        jLabel18.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(39, 39, 39)))
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(jLabel11)
                        .addComponent(jLabel10))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables

    public void dragEnter(DragSourceDragEvent dsde) {
         System.out.println( "DS: dragEnter" );
    }

    public void dragOver(DragSourceDragEvent dsde) {
         System.out.println( "DS: dragOver" );
    }

    public void dropActionChanged(DragSourceDragEvent dsde) {
        System.out.println( "DS: dropActionChanged" );
    }

    public void dragExit(DragSourceEvent dse) {
         System.out.println( "DS: dragExit" );
    }

    public void dragDropEnd(DragSourceDropEvent dsde) {
        System.out.println( "DS: dragDropEnd" );
        if( dsde.getDropSuccess() ) {
            System.out.println( "Object successfully dropped" );
        } else {
            System.out.println( "Object not successfully dropped" );
        }
    }

    public void dragGestureRecognized(DragGestureEvent dge) {
        Component press = findComponentAt(dge.getDragOrigin());
        
        //Object selected = getSelectedValue();
        if( press instanceof  JLabel ) {
            JLabel icon = (JLabel)press;
            // Step 4: StringSelection implements Transferable
            // Build a StringSelection object that the Drag Source
            // can use to transport a string to the Drop Target
            StringSelection text = new StringSelection(icon.getText());
            JLabel pressed = new JLabel(icon.getText(), icon.getIcon(), JLabel.CENTER);
            pressed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            pressed.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            pressed.setName(icon.getName());
            pressed.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
            
            //Transferable dataIcon = new ImageSelection(icon);
            container.setDragged(pressed);
            // Step 5: As the DragSource to start the drag of our text
            dragSource.startDrag( dge, DragSource.DefaultMoveDrop,
                    text, this );
        } else {
            System.out.println( "nothing was selected");
        }
    }
    
    public String getToolTipText(MouseEvent event){
        Component press = findComponentAt(event.getPoint());
        if(press instanceof JLabel){
            JLabel iconPress = (JLabel)press;
            if(iconPress.getName().equals("otro")){
                return null;
            }
            String name = iconPress.getText();
            String tooltip = "<strong>" + name + "</strong><br>";
            if(name.trim().equals("Apriori")){
                tooltip += "The Apriori association algorithm.";
            } else if(name.trim().equals("FPGrowth")){
                tooltip += "The FPGrowth association algorithm.";
            } else if(name.trim().equals("EquipAsso")){
                tooltip += "The EquipAsso association algorithm.";
            } else if(name.trim().equals("C45")){
                tooltip += "The C4.5 classification algorithm.";
            } else if(name.trim().equals("Mate")){
                tooltip += "The Mate classification algorithm.";
            }
            return this.setIconInfo(tooltip);
        } else {
            return null;
        }
    }
    
    private String setIconInfo(String str){
        str = str.replaceAll("\n", "<p>");
        str = "<html>".concat(str).concat("</html>");
        return str;
    }
}
