package gui.Icons.vmd.vmd.graphic;

import java.awt.Point;
import processing.core.PApplet;
import gui.Icons.vmd.guicomponents.G4P;
import gui.Icons.vmd.guicomponents.GComponent;

public class VMDZoomWorkspace extends GComponent {

    PApplet parent;
    VMDWorkspace workspace;
    int radiusZoomWorkspace;
    float xZoomWorkspace, yZoomWorkspace;

    public VMDZoomWorkspace(PApplet _parent, VMDWorkspace _workspace, float _x, float _y, int _radius) {
        super(_parent, (int) _x, (int) _y);
        parent = _parent;
        workspace = _workspace;
        radiusZoomWorkspace = _radius;
        xZoomWorkspace = _x;
        yZoomWorkspace = _y;
    }

    public void init() {
        createEventHandler(winApp, "handleWorkspaceZoomEvents", new Class[]{VMDZoomWorkspace.class});
        registerAutos_DMPK(true, true, false, false);
        workspace.addToPanel(this);
    }

        @Override
    public boolean isOver(int ax, int ay) {
        Point p = new Point(0, 0);
        calcAbsPosition(p);
        float disX = p.x - ax;
        float disY = p.y - ay;
        if ((winApp.sqrt(winApp.sq(disX) + winApp.sq(disY)) < radiusZoomWorkspace) && !workspace.graphicPanel.isCollapsed() && visible) {
            return true;
        } else {
            return false;
        }
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
        winApp.rect(pos.x - (radiusZoomWorkspace * 2), pos.y - (radiusZoomWorkspace * 2), radiusZoomWorkspace * 4, radiusZoomWorkspace * 4);
        winApp.popStyle();
    }
}
