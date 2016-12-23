package gui.Icons.vmd.vmd.graphic;

import gui.Icons.vmd.guicomponents.G4P;
import gui.Icons.vmd.guicomponents.GComponent;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.AbstractTableModel;
import processing.core.PApplet;

public class VMDPolygon extends GComponent {
    // Polygon (glyph). If there are any empty value, it might be an open polygon.

    PApplet parent;
    VMDPoint point;
    Map<String, double[]> individualPoligon = new HashMap<String, double[]>();
    Map<String, double[]> realValuesMap = new HashMap<String, double[]>();
    float x, y;
    int color;
    public boolean isSelected;

    AbstractTableModel dataIn;

    public VMDPolygon(PApplet _parent, VMDPoint _point, Map _polygon, float _x, float _y) {
        super(_parent, (int) _x, (int) _y);
        parent = _parent;
        point = _point;
        individualPoligon = _polygon;
        x = _x;
        y = _y;
    }

    public void init() {
        createEventHandler(winApp, "handlePolygonEvents", new Class[]{VMDPolygon.class});
        registerAutos_DMPK(true, true, false, false);
        point.workspace.addToPanel(this);
    }

    public void setColor(int _color) {
        color = _color;
    }

    public void setSelected(boolean value) {
        isSelected = value;
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
        winApp.strokeWeight(1);
        if (isSelected == true) {
            winApp.stroke(0, 0, 0, 255);
            winApp.fill(0, 0, 0, 30);
        } else {
            winApp.strokeWeight(0);
            winApp.stroke(point.color);
        }
        if (point.workspace.viewPolygon.isSelected() || isSelected) {
            // Fill and stroke values for polygon
            winApp.textFont(localFont);

            winApp.beginShape();
            for (int i = 0; i < individualPoligon.size(); i++) {
                double[] tempValues = individualPoligon.get(point.workspace.tempNames[i]);
                double yy = pos.y + (tempValues[0] * Math.sin(tempValues[1]));
                double xx = pos.x + (tempValues[0] * Math.cos(tempValues[1]));
                double[] realValues = {xx, yy};
                realValuesMap.put(point.workspace.tempNames[i], realValues);
                winApp.vertex((float) xx, (float) yy);
            }
            winApp.endShape(winApp.CLOSE);
            for (int i = 0; i < individualPoligon.size(); i++) {
                double[] tempValues = individualPoligon.get(point.workspace.tempNames[i]);
                double yy = pos.y + (tempValues[0] * Math.sin(tempValues[1]));
                double xx = pos.x + (tempValues[0] * Math.cos(tempValues[1]));
                if (tempValues[2]<0){
                    winApp.fill(255,0,0);
                    winApp.ellipse((float)xx, (float)yy, 2, 2);
                } 
            }
        }
        // Draw real values if is selected the particle
        if (isSelected == true) {
            dataIn = point.workspace.data.dataInVmd;
            for (int i = 0; i < individualPoligon.size(); i++) {
                // Render real values
                String tempAttRVal = dataIn.getValueAt(point.row, dataIn.findColumn(point.workspace.tempNames[i])).toString();
                double[] tmpRealValues = realValuesMap.get(point.workspace.tempNames[i]);
                double xReal = tmpRealValues[0];
                double yReal = tmpRealValues[1];
                winApp.textFont(localFont);
                winApp.textSize(12);
                winApp.fill(255, 51, 0);
                winApp.textAlign(winApp.RIGHT);
                if (xReal <= pos.x && yReal < pos.y) {
                    winApp.text("[ " + tempAttRVal.toUpperCase() + " ]", (float) (xReal + 8), (float) (yReal + 8));
                }
                if (xReal < pos.x && yReal >= pos.y) {

                    winApp.text("[ " + tempAttRVal.toUpperCase() + " ]", (float) (xReal + 8), (float) (yReal - 8));
                }
                if (xReal >= pos.x && yReal < pos.y) {

                    winApp.text("[ " + tempAttRVal.toUpperCase() + " ]", (float) (xReal - 8), (float) (yReal + 8));
                }
                if (xReal > pos.x && yReal >= pos.y) {

                    winApp.text("[ " + tempAttRVal.toUpperCase() + " ]", (float) (xReal - 8), (float) (yReal - 8));
                }
                winApp.textAlign(winApp.LEFT);
            }
        }
        winApp.popStyle();
    }

//    @Override
//    public boolean isOver(int ax, int ay) {
//        Point p = new Point(0, 0);
//        calcAbsPosition(p);
//        if (!point.workspace.graphicPanel.isCollapsed() && visible) {
//            return true;
//        } else {
//            return false;
//        }
//    }
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

        switch (event.getID()) {
            case MouseEvent.MOUSE_PRESSED:
                if (focusIsWith != this && mouseOver) {
                }
                break;
            case MouseEvent.MOUSE_CLICKED:
                // No need to test for isOver() since if the component has focus
                // the mouse has not moved since MOUSE_PRESSED
                if (focusIsWith == this) {
                }
                break;
            case MouseEvent.MOUSE_RELEASED:
                // if the mouse has moved then release focus otherwise
                // MOUSE_CLICKED will handle it
                if (focusIsWith == this) {
                }
                break;
        }
    }
}
