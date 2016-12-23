package gui.Icons.vmd.vmd.graphic;

import gui.Icons.vmd.guicomponents.G4P;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import processing.core.PApplet;
import gui.Icons.vmd.vmd.graphic.VMDSector;
import gui.Icons.vmd.vmd.graphic.VMDWorkspace;
import gui.Icons.vmd.guicomponents.GComponent;

public class VMDSectors extends GComponent {

    public VMDWorkspace workspace;
    public VMDSector[] sector;
    public VMDWindowZoom zoom;
    PApplet parent;
    Map<String, double[]> Attributes = new HashMap<String, double[]>();
    String[] tempNames;
    int referenceType; // 0:referenceSectors 1: baseSectors
    float radius; // Center of the sectors
    // Drag Behavior
    public boolean isDraggedLabel = false;
    public int indexNew;
    // Rotate behavior
    public float rotateY,  rotateDistance;
    public boolean horizontalViewOn = false,  horizontalViewEnd = false;
    // tools keyboard menu
    int colorArrow = 3;

    public VMDSectors(PApplet _parent, VMDWorkspace _workspace, Map _attributes, String[] _tempNames, float _x, float _y, float _radius) {
        super(_parent, (int) _x, (int) _y);
        parent = _parent;
        workspace = _workspace;
        Attributes = _attributes;
        tempNames = _tempNames;
        radius = _radius;
        sector = new VMDSector[tempNames.length];
    }

    public void colapseMenues() {
        for (int i = 0; i < tempNames.length; i++) {
            if (sector[i].label.isCategorical && sector[i].label.menuExpanded) {
                sector[i].label.classMenu.setMenuInVisible();
            }
        }
    }

    public void init() {
        for (int i = 0; i < tempNames.length; i++) {
            String nameAttrib = tempNames[i];
            double[] tempVal = Attributes.get(nameAttrib);
            sector[i] = new VMDSector(parent, this, x, y, nameAttrib, (float) tempVal[0], (float) tempVal[1], (float) tempVal[2], (float) tempVal[3], radius, i, Attributes);
            sector[i].init();
            createEventHandler(winApp, "handleSectorsEvents", new Class[]{VMDSector.class});
            registerAutos_DMPK(true, true, false, false);
            workspace.addToPanel(this);
        }
        zoom = new VMDWindowZoom(parent, this, workspace.x, workspace.y);
        zoom.init();
        zoom.setVisible(false);
        zoom.setAlpha(20);
    }

    @Override
    public boolean isOver(int ax, int ay) {
        Point p = new Point(0, 0);
        calcAbsPosition(p);
        float disX = p.x - ax;
        float disY = p.y - ay;
        // plus 10 to the radious for include the anchor of the ratio and can hide all the scales when
        // the user click out of the ellipse
        if ((winApp.sqrt(winApp.sq(disX) + winApp.sq(disY)) < radius + 10) && !workspace.graphicPanel.isCollapsed() && visible) {
            return true;
        } else {
            return false;
        }
    }

    public void setFilterScaleVisible() {
        for (int i = 0; i < sector.length; i++) {
            if (sector[i].ratio.numericScale) {
                sector[i].ratio.filterControlGreater.setVisible(true);
                sector[i].ratio.filterControlLess.setVisible(true);
                sector[i].ratio.setScaleVisible();
            }
        }
    }

    public void setFilterScaleInVisible() {
        for (int i = 0; i < sector.length; i++) {
            sector[i].ratio.filterControlGreater.setVisible(false);
            sector[i].ratio.filterControlLess.setVisible(false);
            sector[i].ratio.setScaleInVisible();
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

        if (horizontalViewOn) {
            winApp.stroke(230);
            winApp.ellipse(workspace.x, workspace.y, workspace.radius * 2, workspace.radius * 2);
            winApp.stroke(0);
            if (((workspace.radius * 2 - rotateDistance) < workspace.radius * 2)) {
                winApp.ellipse(workspace.x, workspace.y, workspace.radius * 2, workspace.radius * 2 - rotateDistance);
            }
            winApp.textFont(localFont);
            winApp.fill(0);
            winApp.textSize(11);
            winApp.pushMatrix();
            winApp.rotate(-PI / 8);
            String message = "Rotating VMD Tariy";
            winApp.text(message, workspace.x - workspace.radius / 2, workspace.y + workspace.radius / 2);
            winApp.popMatrix();
        }
        // This lines mark the reference system of coordinates, that persist when the ellipse is rotated and
        // when end rotating.
        if (horizontalViewOn || horizontalViewEnd) {
            winApp.stroke(0);
            winApp.line(workspace.x, workspace.y, workspace.x, workspace.y - workspace.radius);
            winApp.triangle(workspace.x, workspace.y - workspace.radius - 12, workspace.x - 4, workspace.y - workspace.radius, workspace.x + 4, workspace.y - workspace.radius);
            winApp.line(workspace.x, workspace.y, workspace.x, workspace.y + workspace.radius);
            winApp.triangle(workspace.x, workspace.y + workspace.radius + 12, workspace.x - 4, workspace.y + workspace.radius, workspace.x + 4, workspace.y + workspace.radius);
            winApp.line(workspace.x - workspace.radius, workspace.y, workspace.x + workspace.radius, workspace.y);

        }

        // Draw the arrows arround the circle
        winApp.fill(0, 0, 0, colorArrow);
        winApp.stroke(0, 0, 0, 0);
        // Left
        winApp.triangle(pos.x - workspace.radius - 30, pos.y, pos.x - workspace.radius + 10, pos.y + 30, pos.x - workspace.radius + 10, pos.y - 30);
        // Right
        winApp.triangle(pos.x, pos.y - workspace.radius - 30, pos.x + 30, pos.y - workspace.radius + 10, pos.x - 30, pos.y - workspace.radius + 10);
        // Up
        winApp.triangle(pos.x, pos.y + workspace.radius + 30, pos.x - 30, pos.y + workspace.radius - 10, pos.x + 30, pos.y + workspace.radius - 10);
        // Down
        winApp.triangle(pos.x + workspace.radius + 30, pos.y, pos.x + workspace.radius - 10, pos.y - 30, pos.x + workspace.radius - 10, pos.y + 30);
        // Remark the labels of workspace check boxes
        winApp.textFont(localFont);
        winApp.fill(255, 0, 0);
        if (workspace.viewOverlap.isSelected()) {
            winApp.text("Overlap", 23, 475);
        }
        if (workspace.viewPolygon.isSelected()) {
            winApp.text("Polygons", 23, 495);
        }
        if (workspace.viewTendencyMeasures.isSelected()) {
            winApp.text("Central Tendency Measures", 23, 555);
        }
        if (workspace.viewCoorelationLines.isSelected()) {
            winApp.text("Correlation", 23, 515);
        }
        if (workspace.viewValuesDistributionPerAttribute.isSelected()) {
            winApp.text("Value Distribution", 23, 535);
        }
        // Display classifier color scale and the titles of both diagrams
        float xClassifierScale = workspace.width - 50;
        float yClassifierScale = 45;
//        if (workspace.ellipseMenu.itemsIndex == 1 && !workspace.zoomWorkspace.isVisible()) {
//            winApp.fill(0);
//            winApp.textFont(localFont);
//            winApp.textSize(10);
//            winApp.text(" C l a s s  V a l u e s ", xClassifierScale, yClassifierScale - 15);
//            winApp.textSize(20);
//            winApp.fill(0,0,0,30);
//            winApp.text("W e k a  C l a s s i f i e r  J48", xClassifierScale + 150, yClassifierScale);
//            winApp.stroke(0);
//            winApp.line(xClassifierScale, yClassifierScale - 11, xClassifierScale + 100, yClassifierScale - 11);
//            winApp.line(xClassifierScale, yClassifierScale - 14, xClassifierScale + 100, yClassifierScale - 14);
//            int step = 8; // heigth of the character
//            for (int i = 0; i < workspace.classifier.getRowCount(); i++) {
//                String classValue = workspace.classifier.getString(i, "Class");
//                String classColor = workspace.classifier.getString(i, "RenderColor");
//                String[] rgb = classColor.split(",");
//                winApp.stroke(0);
//                winApp.fill(Integer.valueOf(rgb[0]), Integer.valueOf(rgb[1]), Integer.valueOf(rgb[2]), Integer.valueOf(rgb[3]));
//                winApp.textFont(localFont);
//                winApp.textSize(10);
//                winApp.text(classValue, xClassifierScale, yClassifierScale + (i * step));
//            }
//        }
//        if (workspace.ellipseMenu.itemsIndex == 0 && !workspace.zoomWorkspace.isVisible()) {
//            winApp.textSize(20);
//            winApp.fill(0,0,0,30);
//            winApp.text("C o r r e l a t i o n  D i a g r a m", xClassifierScale + 150, yClassifierScale);
//        }

        winApp.popStyle();
    }

    public void setColorArrow(int _value) {
        colorArrow = _value;
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

        switch (event.getID()) {  // de aqui se llaman los graficos #########
            case MouseEvent.MOUSE_PRESSED:
                if (mouseOver && parent.mouseButton == parent.RIGHT) {
                    zoom.setVisible(true);
                    workspace.zoomWorkspace.setVisible(true);
                    workspace.showClassifier(false);
                    workspace.showCorrelationDiagram(false);
                    zoom.identifyPoitsZoomed();
                }
                if (mouseOver && parent.mouseButton == parent.LEFT && !zoom.isVisible()) {
                    rotateY = winApp.mouseY;
                }
                // hide the scales
                if (!mouseOver) {
                    for (int i = 0; i < sector.length; i++) {
                        sector[i].ratio.setScaleInVisible();
                        sector[i].ratio.filterControlGreater.setVisible(false);
                        sector[i].ratio.filterControlLess.setVisible(false);
                        workspace.pointsAlpha(false);
                    }
                }
                break;
            case MouseEvent.MOUSE_DRAGGED:
                if (mouseOver && parent.mouseButton == parent.LEFT && rotateY != 0 && Math.abs(winApp.mouseY - rotateY) > 6) {
                    zoom.setVisible(false);
                    workspace.zoomWorkspace.setVisible(false);
                    workspace.hideAll();
                    horizontalViewOn = true;
                    horizontalViewEnd = true;
                    rotateDistance = (winApp.mouseY - rotateY) + 160;
                    // Horizontal view, when the mouse is dragged down the ellipse rotate proportionaly to the move
                    if (rotateDistance > 500) {
                        workspace.lagerManager.setVisible(true);
                        for (int i = 0; i < workspace.countLayers; i++) {
                            workspace.lagerManager.layers[i].setVisible(true);
                        }
                        horizontalViewEnd = true;
                        horizontalViewOn = false;
                    }
                }
                break;
            case MouseEvent.MOUSE_RELEASED:
                if (horizontalViewOn) {
                    workspace.showAll();
                    horizontalViewOn = false;
                    horizontalViewEnd = false;
                    rotateDistance = 0;
                    rotateY = 0;
                    workspace.lagerManager.setVisible(false);
                    for (int i = 0; i < workspace.countLayers; i++) {
                        workspace.lagerManager.layers[i].setVisible(false);
                    }
                }
                break;
        }
    }
}
