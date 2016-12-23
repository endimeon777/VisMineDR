package gui.Icons.vmd.vmd.graphic;

import gui.Icons.vmd.guicomponents.G4P;
import gui.Icons.vmd.guicomponents.GComponent;
import java.awt.Point;
import java.awt.event.MouseEvent;
import processing.core.PApplet;
import processing.core.PImage;
import gui.Icons.vmd.guicomponents.GWinApplet;
import gui.Icons.vmd.guicomponents.GWinData;
import gui.Icons.vmd.guicomponents.GWindow;

public class VMDWindowZoom extends GComponent {

    PApplet parent;
    VMDSectors sectors;
    GWindow window;
    float xStart, yStart;
    int radiusZoom = 80;
    boolean isDragged;
    float xDrag, yDrag;

    public VMDWindowZoom(PApplet _parent, VMDSectors _sectors, float _xStart, float _yStart) {
        super(_parent, (int) _xStart, (int) _yStart);
        parent = _parent;
        sectors = _sectors;
        xStart = _xStart;
        yStart = _yStart;
    }

    public void init() {
        createEventHandler(winApp, "handleWindowZoomEvents", new Class[]{VMDWindowZoom.class});
        registerAutos_DMPK(true, true, false, false);
        sectors.workspace.addToPanel(this);
    }

    public void setWindowPosition(float _x, float _y) {
        this.xStart = _x;
        this.yStart = _y;
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
        winApp.fill(localColor.setAlpha(0, 20));
//        if (isDragged) {
//            //winApp.rect(xDrag - w / 2, yDrag - h / 2, this.w, this.h);
//            winApp.ellipse(xDrag, yDrag, radiusZoom, radiusZoom);
//        } else {
//            //winApp.rect(pos.x - w / 2, pos.y - h / 2, this.w, this.h);
//            winApp.ellipse(pos.x, pos.y, radiusZoom, radiusZoom);
//        }
        // Window like rectangle
        if (isDragged) {
            //winApp.rect(xDrag - w / 2, yDrag - h / 2, this.w, this.h);
            winApp.rect(xDrag - radiusZoom / 2, yDrag - radiusZoom / 2, radiusZoom, radiusZoom);
        } else {
            //winApp.rect(pos.x - w / 2, pos.y - h / 2, this.w, this.h);
            winApp.rect(pos.x - radiusZoom / 2, pos.y - radiusZoom / 2, radiusZoom, radiusZoom);
        }
    }

    @Override
    public boolean isOver(int ax, int ay) {
        Point p = new Point(0, 0);
        calcAbsPosition(p);
        if ((ax >= p.x - radiusZoom / 2 && ax <= p.x + radiusZoom / 2 && ay >= p.y - radiusZoom / 2 && ay <= p.y + radiusZoom / 2) && !sectors.workspace.graphicPanel.isCollapsed() && visible) {
            return true;
        } else {
            return false;
        }
    // Over a ellipse
//        Point p = new Point(0, 0);
//        calcAbsPosition(p);
//        float disX = p.x - ax;
//        float disY = p.y - ay;
//        if ((winApp.sqrt(winApp.sq(disX) + winApp.sq(disY)) < radiusZoom / 2) && !sectors.workspace.graphicPanel.isCollapsed() && visible) {
//            return true;
//        } else {
//            return false;
//        }
    }

    // Identify the points in the zoomWindow area for displayed zoomed in the zoom panel
    public void identifyPoitsZoomed() {
        int count = 0;
        int[] tmpPointAttay = new int[sectors.workspace.tempDP.length];
        for (int i = 0; i < sectors.workspace.tempDP.length; i++) {
            Point p = new Point(0, 0);
            calcAbsPosition(p);
            float disX = ((sectors.workspace.tempDP[i].x - p.x) * 2) + sectors.workspace.zoomWorkspace.xZoomWorkspace;
            float disY = ((sectors.workspace.tempDP[i].y - p.y) * 2) + sectors.workspace.zoomWorkspace.yZoomWorkspace;
            if (isOver((int) sectors.workspace.tempDP[i].x, (int) sectors.workspace.tempDP[i].y + 12)) {
                // 12, is the fator to fix the error between the "y" center of the workspace and the "y" center of the zoom window
                if (sectors.workspace.tempDP[i].isVisible()) {
                    sectors.workspace.points[i].setPosition(disX, disY);
                    sectors.workspace.points[i].setColor(sectors.workspace.tempDP[i].color);
                    sectors.workspace.points[i].setVisible(true);
                    tmpPointAttay[count] = i;
                    count++;
                }
            } else {
                sectors.workspace.points[i].setVisible(false);
                sectors.workspace.points[i].labels.setVisible(false);
            }
        }
        float ang = 0;
        for (int i = 0; i < count; i++) {
            float attX = sectors.workspace.zoomWorkspace.xZoomWorkspace;
            float attY = sectors.workspace.zoomWorkspace.yZoomWorkspace;
            attX += (sectors.workspace.zoomWorkspace.radiusZoomWorkspace * 3) * Math.cos(ang);
            attY += (sectors.workspace.zoomWorkspace.radiusZoomWorkspace * 3) * Math.sin(ang);
            ang += (Math.PI * 2) / count;
            sectors.workspace.points[tmpPointAttay[i]].labels.setAnchors(attX, attY, ang);
            sectors.workspace.points[tmpPointAttay[i]].labels.setVisible(true);
            sectors.workspace.points[tmpPointAttay[i]].labels.activeSegment(true);
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
                if (focusIsWith != this && mouseOver) {
                    takeFocus();
                }
                if (mouseOver && parent.mouseButton == parent.RIGHT) {
                    setVisible(false);
                    sectors.workspace.zoomWorkspace.setVisible(false);
                    // hide all the points
                    for (int i = 0; i < sectors.workspace.points.length; i++) {
                        sectors.workspace.points[i].setVisible(false);
                        sectors.workspace.points[i].labels.setVisible(false);
                    }
                }
                break;
            case MouseEvent.MOUSE_DRAGGED:
                if (focusIsWith == this && mouseOver) {
                    isDragged = true;
                }
                if (isDragged == true) {
                    xDrag = winApp.mouseX;
                    yDrag = winApp.mouseY;
                }
                break;
            case MouseEvent.MOUSE_RELEASED:
                // if the mouse has moved then release focus otherwise
                // MOUSE_CLICKED will handle it
                if (focusIsWith == this && isDragged) { // && mouseHasMoved(winApp.mouseX, winApp.mouseY)){
                    looseFocus(null);
                    isDragged = false;
                    super.x = (int) xDrag;
                    super.y = (int) yDrag;
                    identifyPoitsZoomed();
                }
                break;
        }
    }
}





