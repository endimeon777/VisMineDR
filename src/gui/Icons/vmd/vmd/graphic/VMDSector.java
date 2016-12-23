package gui.Icons.vmd.vmd.graphic;

import gui.Icons.vmd.vmd.graphic.VMDSectors;
import java.awt.Point;
import java.awt.event.MouseEvent;
import prefuse.util.ColorLib;
import gui.Icons.vmd.guicomponents.G4P;
import gui.Icons.vmd.guicomponents.GComponent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.AbstractTableModel;
import processing.core.PApplet;

public class VMDSector extends GComponent {
    // Includes VMDRatio and VMDLabel.

    public VMDSectors sectors;
    public VMDRatio ratio;
    public VMDLabel label;
    public PApplet parent;
    float xAnchor, yAnchor, angInit, angEnd, radius; // x, y : Center
    String name;
    int index;
    public boolean draggedOver = false;
    Map<String, double[]> attributes = new HashMap<String, double[]>();

    public VMDSector(PApplet _parent, VMDSectors _sectors, float _x, float _y, String _name, float _xAnchor, float _yAnchor, float _angInit, float _angEnd, float _radius, int _i, Map _Attributes) {
        super(_parent, (int) _x, (int) _y);
        parent = _parent;
        sectors = _sectors;
        name = _name;
        xAnchor = _xAnchor;
        yAnchor = _yAnchor;
        angInit = _angInit;
        angEnd = _angEnd;
        radius = _radius;
        index = _i;
        attributes = _Attributes;
    }

    public void init() {

        AbstractTableModel dataIn = sectors.workspace.data.dataInVmd;
        int indexCol = dataIn.findColumn(name);

        ratio = new VMDRatio(parent, this, x, y, angInit, radius, attributes);
        ratio.init();
        if ( dataIn.getColumnClass(indexCol)!= String.class && dataIn.getColumnClass(indexCol)!= Object.class){
            ratio.attributeScale();
            ratio.activateNumericScale(true);
            ratio.activateCategoricalScale(false);
            ratio.activeCorrelation(true);
        } else{
            ratio.attributeScaleCategorical();
            ratio.activateCategoricalScale(true);
            ratio.activateNumericScale(false);
            ratio.activeCorrelation(false);
        }
        label = new VMDLabel(parent, this, name, xAnchor, yAnchor, angInit, index);
        label.init();
        if ( dataIn.getColumnClass(indexCol) == String.class) {
            label.setHorizontal(true);
            label.setAttributeColor(ColorLib.rgb(255, 200, 0));
        }
        createEventHandler(winApp, "handleSectorEvents", new Class[]{VMDSector.class});
        registerAutos_DMPK(true, true, false, false);
        sectors.workspace.addToPanel(this);
    }

    @Override
    public void draw() {
        if (!visible) {
            return;
        }
        winApp.pushStyle();
        winApp.style(G4P.g4pStyle);
        Point pos = new Point(0, 0);
        calcAbsPosition(pos);
//        winApp.strokeWeight(1);
//        winApp.stroke(localColor.btnBorder);
//        parent.fill(localColor.lblBack);
        winApp.stroke(1);
        if (draggedOver) {
            winApp.fill(174, 221, 60, 160);
        } else {
            winApp.fill(144, 116, 100, 10);
        }
        winApp.arc(pos.x, pos.y, radius * 2, radius * 2, (float) angInit, (float) angEnd);
        winApp.popStyle();

    }

    public boolean isOver(int ax, int ay) {
        Point p = new Point(0, 0);
        calcAbsPosition(p);
        double dragAngle = 0;
        float disX = p.x - ax;
        float disY = p.y - ay;
        // Follow the angle position of the mouse once a label attribute has been dragged
        dragAngle = Math.atan2((ay - p.y), (ax - p.x));
        if (dragAngle < 0.00) {
            dragAngle += 2 * Math.PI;
        } // Change the range from (PI/-PI) to (2PI)
        if ((dragAngle >= angInit && dragAngle <= angEnd) && (winApp.sqrt(winApp.sq(disX) + winApp.sq(disY)) < radius) && !sectors.workspace.graphicPanel.isCollapsed() && visible) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void mouseEvent(MouseEvent event) {
        if (!visible) {
            return;
        }
        boolean mouseOver = isOver(winApp.mouseX, winApp.mouseY);
        if (mouseOver) {
            cursorIsOver = this;
        } else if (cursorIsOver == this) {
            cursorIsOver = null;
        }
        // If is there are a label moving, assign the index of the new position to move
        if (mouseOver && sectors.isDraggedLabel) {
            draggedOver = true;
            sectors.indexNew = index;
        } else {
            draggedOver = false;
        }
//        //For drag Behavior
//        switch (event.getID()) {
//            case MouseEvent.MOUSE_PRESSED:
//                if (mouseOver) {
//                    takeFocus();
//                }
//                sectors.workspace.xInitDrag = winApp.mouseX;
//                sectors.workspace.yInitDrag = winApp.mouseY;
//                break;
//            case MouseEvent.MOUSE_RELEASED:
//                // if the mouse has moved then release focus otherwise
//                if (focusIsWith == this) { // && mouseHasMoved(winApp.mouseX, winApp.mouseY)){
//                    looseFocus(null);
//                }
//                break;
//            case MouseEvent.MOUSE_DRAGGED:
//                if (focusIsWith == this) { // && mouseHasMoved(winApp.mouseX, winApp.mouseY)){
//                    // For drag space, the center of the representation moves to the mose position less the 10th distance between the center point and the current position
//                    float distance = winApp.sqrt(winApp.sq(winApp.mouseX - sectors.workspace.x) + winApp.sq(winApp.mouseY - sectors.workspace.y));
//                    sectors.workspace.x = winApp.mouseX - distance / 80;
//                    sectors.workspace.y = winApp.mouseY - distance / 80;
//                    sectors.workspace.reMake();
//                    sectors.workspace.init();
//                }
//
//                break;

//        }
    }
}
