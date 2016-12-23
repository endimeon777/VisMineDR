package gui.Icons.vmd.vmd.graphic;

import gui.Icons.vmd.guicomponents.G4P;
import gui.Icons.vmd.guicomponents.GComponent;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import processing.core.PApplet;

public class VMDPoint extends GComponent {
    // TODO Defines each Center of Mass point, storing attribute values. Store a polygon.

    PApplet parent;
    VMDWorkspace workspace;
    VMDPolygon polygon;
    // Center
    public float x,  y;
    Map<String, double[]> individualPoligon = new HashMap<String, double[]>();
    // Dimensions of the particle
    float w = (float) 4;
    float h = (float) 4;
    int color = 0;
    int row;
    boolean mouseOver, alpha;
    // Overlap variables
    int colorOverlap, overlapCounter;
    boolean hasOverlap; // this flag is true if this point is overlapped with another who take the control count
    String overlapLabels = ""; // all the labels overlapped
    int layer;

    public VMDPoint(PApplet _parent, VMDWorkspace _workspace, float _x, float _y, Map _individualPoligon, int _row) {
        super(_parent, (int) _x, (int) _y);
        parent = _parent;
        workspace = _workspace;
        x = _x;
        y = _y;
        individualPoligon = _individualPoligon;
        row = _row;
    }

    public void init() {
        createEventHandler(winApp, "handlePointsEvents", new Class[]{VMDPoint.class});
        registerAutos_DMPK(true, true, false, false);
        polygon = new VMDPolygon(parent, this, individualPoligon, workspace.x, workspace.y);
        polygon.init();
        workspace.addToPanel(this);
    }

    public void setLayer(int _value) {
        layer = _value;
    }

    public void setColor(int _color) {
        color = _color;
    }

    public void setAlpha(boolean _value) {
        alpha = _value;
    }

    public void draw() {
        if (!visible) {
            return;
        }
        winApp.pushStyle();
        winApp.style(G4P.g4pStyle);
        Point pos = new Point(0, 0);
        calcAbsPosition(pos);
        winApp.strokeWeight(1);
        winApp.stroke(0);
        if (color != 0) {
            winApp.fill(color);
        } else {
            winApp.noFill();
        }
        if (alpha) {
            winApp.stroke(0, 0, 0, 20);
            winApp.fill(0, 0, 0, 20);
        }
        winApp.rect((pos.x - w / 2), (pos.y - w / 2), w, h);

        // if has overlap, the labels must be not overlapped
        if (mouseOver && workspace.viewOverlap.isSelected() && this.hasOverlap && this.colorOverlap != 0) {
            winApp.textFont(localFont);
            winApp.fill(0);
            winApp.textSize(13);
            winApp.text(this.overlapLabels, pos.x + 10, pos.y + 12);
        }
        if (mouseOver && !workspace.viewOverlap.isSelected()) {
            winApp.textFont(localFont);
            winApp.fill(255, 0, 0);
            winApp.textSize(20);
            String text = "No." + row;
            winApp.text(text, pos.x, pos.y);
        }
        // Overlap
        if (workspace.viewOverlap.isSelected()) {
            // Render the overlap gradient
            if (this.colorOverlap != 0) {
                winApp.noStroke();
                winApp.fill(this.colorOverlap);
                winApp.rect((pos.x - w / 4), (pos.y - w / 4), w - 1, h - 1);
            }
            if (this.hasOverlap && this.colorOverlap != 0) {
                winApp.textFont(localFont);
                winApp.fill(0);
                winApp.textSize(10);
                winApp.text(this.overlapCounter, pos.x - 2, pos.y - 3);
            }
        }
        winApp.popStyle();
    }

    @Override
    public boolean isOver(int ax, int ay) {
        Point p = new Point(0, 0);
        calcAbsPosition(p);
        if ((ax >= p.x && ax <= p.x + w && ay >= p.y && ay <= p.y + h) && !workspace.graphicPanel.isCollapsed() && visible) {
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

        mouseOver = isOver(winApp.mouseX, winApp.mouseY);
        if (mouseOver) {
            cursorIsOver = this;
        } else if (cursorIsOver == this) {
            cursorIsOver = null;
        }

        switch (event.getID()) {
            case MouseEvent.MOUSE_PRESSED:
                if (focusIsWith != this && mouseOver) {
                    takeFocus();
                    polygon.setSelected(true);
                }
                break;
            case MouseEvent.MOUSE_CLICKED:
                // No need to test for isOver() since if the component has focus
                // the mouse has not moved since MOUSE_PRESSED
                if (focusIsWith == this) {
                    polygon.setSelected(true);
                }
                break;
            case MouseEvent.MOUSE_RELEASED:
                // if the mouse has moved then release focus otherwise
                // MOUSE_CLICKED will handle it
                if (focusIsWith == this) { // && mouseHasMoved(winApp.mouseX, winApp.mouseY)){
                    looseFocus(null);
                    polygon.setSelected(false);
                    // Colapse all polygons
                    workspace.colapsePolygons();
                }
                break;
        }
    }
}
