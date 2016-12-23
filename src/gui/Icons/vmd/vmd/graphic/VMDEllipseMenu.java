package gui.Icons.vmd.vmd.graphic;

import gui.Icons.vmd.guicomponents.G4P;
import gui.Icons.vmd.guicomponents.GComponent;
import java.awt.Point;
import java.awt.event.MouseEvent;
import processing.core.PApplet;

public class VMDEllipseMenu extends GComponent {

    PApplet parent;
    VMDWorkspace workspace;
    float x, y;
    float radiusMenu;
    boolean over;
    int itemsIndex; // Classifier or Correlation diagram

    public VMDEllipseMenu(PApplet _parent, VMDWorkspace _workspace, float _x, float _y) {
        super(_parent, (int) _x, (int) _y);
        parent = _parent;
        workspace = _workspace;
        x = _x;
        y = _y;
        radiusMenu = (workspace.height - 300);
    }

    public void init() {
        createEventHandler(winApp, "handleLabelsEvents", new Class[]{VMDEllipseMenu.class});
        registerAutos_DMPK(true, true, false, false);
        workspace.addToPanel(this);
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
        winApp.stroke(0);
        winApp.textFont(localFont);
        if (over) {
            winApp.fill(0, 0, 255, 40);
            winApp.ellipse(pos.x, pos.y - 50, radiusMenu, radiusMenu);
            winApp.fill(0);
            if (itemsIndex == 0){
                winApp.text("View Classifier J48 Diagram", pos.x - 75, 675);
            } else {
                winApp.text("View Correlation Diagram", pos.x - 75, 675);
            }

        } else {
            winApp.fill(0, 0, 255, 10);
            winApp.ellipse(pos.x, pos.y, radiusMenu, radiusMenu);
        }
        
        winApp.popStyle();
    }

    @Override
    public boolean isOver(int ax, int ay) {
        Point p = new Point(0, 0);
        calcAbsPosition(p);
        float disX = p.x - ax;
        float disY = p.y - ay;
        // Change the range from (PI/-PI) to (2PI)
        if ((winApp.sqrt(winApp.sq(disX) + winApp.sq(disY)) < radiusMenu / 2) && !workspace.graphicPanel.isCollapsed() && visible) {
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
            over = true;
        } else if (cursorIsOver == this) {
            cursorIsOver = null;
            over = false;
        }
          switch (event.getID()) {
            case MouseEvent.MOUSE_PRESSED:
                if (mouseOver) {
                    if (itemsIndex == 0){ // 0 : correlation diagram
                        itemsIndex = 1;
                        workspace.showClassifier(true);
                        workspace.showCorrelationDiagram(false);
                    } else {
                        itemsIndex = 0; // 1 : classifier
                        workspace.showClassifier(false);
                        workspace.showCorrelationDiagram(true);
                    }
                    workspace.baseSectors.zoom.setVisible(false);
                    workspace.zoomWorkspace.setVisible(false);
                    // hide all the points
                    for (int i = 0; i <  workspace.points.length; i++) {
                         workspace.points[i].setVisible(false);
                         workspace.points[i].labels.setVisible(false);
                    }
                }
                break;
        }
    }
}
