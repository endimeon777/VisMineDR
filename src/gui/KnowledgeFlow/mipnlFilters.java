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

public class mipnlFilters extends javax.swing.JPanel implements DragSourceListener, DragGestureListener{

    
private DragSource dragSource;
private miContenedor container;

    /** Creates new form mipnlPreprocesamiento */
    public mipnlFilters(miContenedor container) {
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
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/filters/Selection.png"))); // NOI18N
        jLabel10.setText("Selection");
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel10.setName("selection"); // NOI18N
        jLabel10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/filters/UpdateM.png"))); // NOI18N
        jLabel13.setText("Update Missing");
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel13.setName("updatem"); // NOI18N
        jLabel13.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/filters/removeM.png"))); // NOI18N
        jLabel14.setText("Remove Missing ");
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel14.setName("removem"); // NOI18N
        jLabel14.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/filters/Range.png"))); // NOI18N
        jLabel15.setText(" Range ");
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel15.setName("muestra"); // NOI18N
        jLabel15.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/filters/Reduction.png"))); // NOI18N
        jLabel16.setText("Reduction");
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel16.setName("reduction"); // NOI18N
        jLabel16.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/filters/Codification.png"))); // NOI18N
        jLabel17.setText("Codification");
        jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel17.setName("codification"); // NOI18N
        jLabel17.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/filters/ReplaceV.png"))); // NOI18N
        jLabel18.setText("Replace Value");
        jLabel18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel18.setName("remvalor"); // NOI18N
        jLabel18.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/filters/Numeric.png"))); // NOI18N
        jLabel19.setText("Numeric Range");
        jLabel19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel19.setName("rangenum"); // NOI18N
        jLabel19.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/filters/Discretize.png"))); // NOI18N
        jLabel20.setText("Discretize");
        jLabel20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel20.setName("discretize"); // NOI18N
        jLabel20.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/filters/Standarize.png"))); // NOI18N
        jLabel21.setText("Standardize");
        jLabel21.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel21.setName("Estandar"); // NOI18N
        jLabel21.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel1.setFont(new java.awt.Font("Dialog", 2, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LABELS");
        jLabel1.setName("otro"); // NOI18N

        jLabel2.setFont(new java.awt.Font("Dialog", 2, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("CLUSTER");
        jLabel2.setName("otro"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel2)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15))
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel14)))))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
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
            String name = iconPress.getText();
            String tooltip = "<strong>" + name + "</strong><br>";
            if(name.trim().equals("Remove Missing")){
                tooltip += "Remove missing records";
            } else if(name.trim().equals("Update Missing")){
                tooltip += "Update missing records";
            } else if(name.trim().equals("Selection")){
                tooltip += "Select attributes and a target";
            } else if(name.trim().equals("Range")){
                tooltip += "Select a data range";
            } else if(name.trim().equals("Reduction")){
                tooltip += "Reduce the data";
            } else if(name.trim().equals("Codification")){
                tooltip += "Codify attributes";
            } else if(name.trim().equals("Replace Value")){
                tooltip += "Change a value";
            } else if(name.trim().equals("Numeric Range")){
                tooltip += "Select a numeric range";
            } else if(name.trim().equals("Discretize")){
                tooltip += "Discretize a numeric column";
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
