package gui.Icons.vmd.vmd.graphic;

import java.awt.Point;
import java.awt.event.MouseEvent;
import processing.core.PApplet;
import gui.Icons.vmd.guicomponents.G4P;
import gui.Icons.vmd.guicomponents.GComponent;

public class VMDZoomPointInformation extends GComponent {

    PApplet parent;
    VMDZoomPoints point;
    float xAnchor, yAnchor, angle;
    int index, colorLabel = 0;
    boolean segment;
    String information;

    public VMDZoomPointInformation(PApplet _parent, VMDZoomPoints _point, float _x, float _y, int _index) {
        super(_parent, (int) _x, (int) _y);
        parent = _parent;
        point = _point;
        this.xAnchor = _x;
        this.yAnchor = _y;
        index = _index;
        information = "" + index;
    }

    public void setAnchors(float _x, float _y, float ang) {
        super.x = (int) _x;
        super.y = (int) _y;
        angle = ang;
    }

    public void init() {
        createEventHandler(winApp, "handlePoints2Events", new Class[]{VMDZoomPointInformation.class});
        registerAutos_DMPK(true, true, false, false);
        point.workspace.addToPanel(this);
    }

    public void draw() {
        if (!visible) {
            return;
        }
        winApp.pushStyle();
        winApp.style(G4P.g4pStyle);
        Point pos = new Point(0, 0);
        calcAbsPosition(pos);
        if (segment) {
            if (point.selected) {
                winApp.stroke(0, 0, 0, 255);
            } else {
                winApp.stroke(0, 20);
            }
            winApp.ellipse((point.getX()), (point.getY() + (point.h * point.zoomFactor)), 1, 1);
            winApp.textFont(localFont);
            winApp.line((point.getX()), (point.getY() + (point.h * point.zoomFactor)), pos.x, pos.y);
        } else {
            winApp.noFill();
            winApp.stroke(0);
            if (pos.x > point.workspace.zoomWorkspace.xZoomWorkspace){
                winApp.rect(pos.x - 8, pos.y - 6, 24, 10);
            } else {
                winApp.rect(pos.x - 24, pos.y - 6, 24, 10);
            }
        }
        winApp.fill(colorLabel);
        winApp.textSize(7);
        if (pos.x > point.workspace.zoomWorkspace.xZoomWorkspace) {
            winApp.textAlign(winApp.LEFT);
            winApp.text(information, pos.x + 2, pos.y);
        } else {
            winApp.textAlign(winApp.RIGHT);
            winApp.text(information, pos.x - (information.length()), pos.y);
        }
        winApp.popStyle();
    }

    public void activeSegment(boolean value) {
        segment = value;
    }

    public void setColor(int color) {
        colorLabel = color;
    }

    @Override
    public boolean isOver(int ax, int ay) {
        Point p = new Point(0, 0);
        calcAbsPosition(p);
        boolean tempReturn = false;
        if (p.x > point.workspace.zoomWorkspace.xZoomWorkspace) {
            if ((ax >= p.x + 2 && ax <= p.x + (information.length() * 5) + 2 && ay >= p.y - 5 && ay <= p.y) && !point.workspace.graphicPanel.isCollapsed() && visible) {
                tempReturn = true;
            }
        } else {
            if ((ax >= p.x - (information.length() * 5) && ax <= p.x && ay >= p.y - 5 && ay <= p.y) && !point.workspace.graphicPanel.isCollapsed() && visible) {
                tempReturn = true;
            }
        }
        if (tempReturn) {
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

        switch (event.getID()) {
            case MouseEvent.MOUSE_PRESSED:
                if (focusIsWith != this && mouseOver && parent.mouseButton == parent.RIGHT) {
                    takeFocus();
                    segment = true;
                    colorLabel = 0;
                    point.setVisible(true);
                    point.selected = true;
                }
                if (focusIsWith != this && mouseOver && parent.mouseButton == parent.LEFT) {
                    takeFocus();
                    point.workspace.tempDP[index].polygon.setSelected(true);
                    for (int i = 0; i < point.workspace.tempDP.length; i++) {
                        if (i != index) {
                            point.workspace.tempDP[i].setAlpha(true);
                        }
                    }
                    point.selected = true;
                }
                break;
//            case MouseEvent.MOUSE_CLICKED:
//                // No need to test for isOver() since if the component has focus
//                // the mouse has not moved since MOUSE_PRESSED
//                if (focusIsWith == this) {
//                    polygon.setSelected(true);
//                }
//                break;
            case MouseEvent.MOUSE_RELEASED:
                // if the mouse has moved then release focus otherwise
                // MOUSE_CLICKED will handle it
                if (focusIsWith == this) { // && mouseHasMoved(winApp.mouseX, winApp.mouseY)){
                    looseFocus(null);
                    point.workspace.tempDP[index].polygon.setSelected(false);
                    for (int i = 0; i < point.workspace.tempDP.length; i++) {
                        point.workspace.tempDP[i].setAlpha(false);
                    }
                    point.selected = false;
                }
                break;
        }
    }
}
