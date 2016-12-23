package gui.Icons.vmd.vmd.graphic;

import gui.Icons.vmd.guicomponents.G4P;
import gui.Icons.vmd.guicomponents.GComponent;
import java.awt.Point;
import java.awt.event.MouseEvent;
import processing.core.PApplet;

public class VMDLayer extends GComponent {

    PApplet parent;
    VMDLayerManager layerMan;
    float x, y;
    int[] layerValues;
    int w = 4, h = 4, layer;
    boolean hide;

    public VMDLayer(PApplet _parent, VMDLayerManager _layerMan, float _x, float _y, int[] _layerValues, int _layer) {
        super(_parent, (int) _x, (int) _y);
        parent = _parent;
        x = _x;
        y = _y;
        layerMan = _layerMan;
        layerValues = _layerValues;
        layer = _layer;
    }

    public void init() {
        createEventHandler(winApp, "handleLayerEvents", new Class[]{VMDLayer.class});
        registerAutos_DMPK(true, true, false, false);
        layerMan.workspace.addToPanel(this);
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
        winApp.stroke(0, 20);
        winApp.line(pos.x, pos.y - h / 2, pos.x + (layerMan.workspace.radius * 2), pos.y);
        winApp.stroke(0);
        if (hide) {
            winApp.fill(255, 255, 255);
        } else {
            winApp.fill(255, 0, 0);
        }

        winApp.rect(pos.x, pos.y - h, w, h);
        winApp.noStroke();
        for (int i = 0; i < layerValues.length; i++) {
            if (layerValues[i] > 0) {
                if (!hide) {
                    winApp.fill(255, 0, 0);
                } else {
                    winApp.fill(0);
                }
                winApp.ellipse(pos.x + i, pos.y, 2, 2);
//              winApp.text(layerValues[i], pos.x + i, pos.y);
            }
        }
        winApp.popStyle();
    }

    @Override
    public boolean isOver(int ax, int ay) {
        Point p = new Point(0, 0);
        calcAbsPosition(p);
        if ((ax >= p.x - w / 2 && ax <= p.x + w / 2 && ay >= p.y - h && ay <= p.y) && !layerMan.workspace.graphicPanel.isCollapsed() && visible) {
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
                if (focusIsWith != this && mouseOver && hide == true && layerMan.workspace.baseSectors.horizontalViewEnd == true) {
                    takeFocus();
                    for (int i = 0; i < layerMan.workspace.tempDP.length; i++) {
                        if (layerMan.workspace.tempDP[i].layer == layer) {
                            layerMan.workspace.tempDP[i].setVisible(true);
                        }
                    }
                    hide = false;
                }

                if (focusIsWith != this && mouseOver && hide == false && layerMan.workspace.baseSectors.horizontalViewEnd == true) {
                    takeFocus();
                    for (int i = 0; i < layerMan.workspace.tempDP.length; i++) {
                        if (layerMan.workspace.tempDP[i].layer == layer) {
                            layerMan.workspace.tempDP[i].setVisible(false);
                        }
                    }
                    hide = true;
                }
                break;
            case MouseEvent.MOUSE_RELEASED:
                if (focusIsWith != this) {
                    looseFocus(null);
                }
                break;
        }
    }
}
