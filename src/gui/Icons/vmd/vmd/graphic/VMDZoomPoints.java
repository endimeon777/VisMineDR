package gui.Icons.vmd.vmd.graphic;

import java.awt.Point;
import java.awt.event.MouseEvent;
import prefuse.util.ColorLib;
import processing.core.PApplet;
import gui.Icons.vmd.guicomponents.G4P;
import gui.Icons.vmd.guicomponents.GComponent;


// The zoomPoints apears by default but the user can erase it with a RIGHT click over them
// and can make apear again clicking the label without ratio sign
public class VMDZoomPoints extends GComponent {

    PApplet parent;
    VMDWorkspace workspace;
    VMDZoomPointInformation labels;
    float xx, yy;
    boolean mouseOver;
    // Dimensions of the particle
    float w = (float) 4;
    float h = (float) 4;
    int color, index, zoomFactor = 3;
    boolean selected = false;

    public VMDZoomPoints(PApplet _parent, VMDWorkspace _workspace, float _x, float _y, int _color, int _index) {
        super(_parent, (int) _x, (int) _y);
        parent = _parent;
        workspace = _workspace;
        xx = _x;
        yy = _y;
        index = _index;
        color = _color;
    }

    public void init() {
        createEventHandler(winApp, "handlezoomPointsEvents", new Class[]{VMDZoomPoints.class});
        registerAutos_DMPK(true, true, false, false);
        workspace.addToPanel(this);
        this.labels = new VMDZoomPointInformation(parent, this, xx, yy, index);
        this.labels.init();
        this.labels.setVisible(false);
    }

    public void setPosition(float _x, float _y) {
        super.x = (int) _x;
        super.y = (int) _y;
    }

    public void setColor(int _color) {
        color = _color;
    }

    public void draw() {
        if (!visible) {
            return;
        }
        winApp.pushStyle();
        winApp.style(G4P.g4pStyle);
        Point pos = new Point(0, 0);
        calcAbsPosition(pos);
        if (selected) {
            winApp.strokeWeight(5);
            winApp.stroke(2);
        } else {
            winApp.strokeWeight(1);
            winApp.stroke(0);
        }
        if (color != 0) {
            winApp.fill(color);
        } else {
            winApp.noFill();
        }
        winApp.rect(((pos.x) - (w * zoomFactor) / 2), ((pos.y) - (h * zoomFactor) / 2), w * zoomFactor, h * zoomFactor);
        winApp.popStyle();
    }

    @Override
    public boolean isOver(int ax, int ay) {
        Point p = new Point(0, 0);
        calcAbsPosition(p);
        if ((ax >= p.x - (w * zoomFactor) / 2 && ax <= p.x + (w * zoomFactor) / 2 && ay >= p.y - (h * zoomFactor) / 2 && ay <= p.y + (h * zoomFactor) / 2) && !workspace.graphicPanel.isCollapsed() && visible) {
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
                if (mouseOver && parent.mouseButton == parent.RIGHT) {
                    takeFocus();
                    setVisible(false);
                    labels.activeSegment(false);
                    labels.setColor(ColorLib.rgb(255, 51, 0));
                    selected = true;
                }
                if (mouseOver && parent.mouseButton == parent.LEFT) {
                    takeFocus();
                    workspace.tempDP[index].polygon.setSelected(true);
                    for (int i = 0; i < workspace.tempDP.length; i++) {
                        if (i != index) {
                            workspace.tempDP[i].setAlpha(true);
                        }
                    }
                    selected = true;
                }
                break;
            case MouseEvent.MOUSE_RELEASED:
                // if the mouse has moved then release focus otherwise
                // MOUSE_CLICKED will handle it
                if (focusIsWith == this) { // && mouseHasMoved(winApp.mouseX, winApp.mouseY)){
                    looseFocus(null);
                    selected = false;
                    workspace.tempDP[index].polygon.setSelected(false);
                    for (int i = 0; i < workspace.tempDP.length; i++) {
                        workspace.tempDP[i].setAlpha(false);
                    }
                }
                break;
        }
    }
}
