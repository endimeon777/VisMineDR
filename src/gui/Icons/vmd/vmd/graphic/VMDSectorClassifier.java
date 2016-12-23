package gui.Icons.vmd.vmd.graphic;

import gui.Icons.vmd.guicomponents.G4P;
import gui.Icons.vmd.guicomponents.GComponent;
import java.awt.Point;
import java.awt.event.MouseEvent;
import processing.core.PApplet;

public class VMDSectorClassifier extends GComponent {

    PApplet parent;
    VMDWorkspace workspace;
    float x;
    float y;
    float angleInit;
    float angleEnd;
    String name, className;
    String color;
    int level;
    float radius;
    float anchorX, anchorY, totalRadius;
    float nextRadius, portion;
    boolean over;
    String nextValue, operator, value;

    public VMDSectorClassifier(PApplet _parent, VMDWorkspace _workspace, float _x, float _y, float _angleInit, float _angleEnd, String _name, String _color, int _level, float _radius, String _className, float _totalRadius, float _nextRadius, String _operator, String _value, String _nextValue, float _portion) {
        super(_parent, (int) _x, (int) _y);
        parent = _parent;
        workspace = _workspace;
        x = _x;
        y = _y;
        angleInit = _angleInit;
        angleEnd = _angleEnd;
        name = _name;
        color = _color;
        level = _level;
        radius = _radius;
        className = _className;
        totalRadius = _totalRadius;
        nextRadius = _nextRadius;
        operator = _operator;
        value = _value;
        nextValue = _nextValue;
        portion = _portion;
    }

    public void init() {
        createEventHandler(winApp, "handleLabelsEvents", new Class[]{VMDSectorClassifier.class});
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
        anchorX = pos.x + (float) (totalRadius * Math.cos(angleInit));
        anchorY = pos.y + (float) (totalRadius * Math.sin(angleInit));

        // Split the color and the alpha
        String[] rgb = color.split(",");
        winApp.stroke(Integer.valueOf(rgb[0]), Integer.valueOf(rgb[1]), Integer.valueOf(rgb[2]), Integer.valueOf(rgb[3]));
//        winApp.fill(Integer.valueOf(rgb[0]), Integer.valueOf(rgb[1]), Integer.valueOf(rgb[2]), 150);
        // Paint the perimeter
        winApp.strokeWeight(2);
        winApp.arc(pos.x, pos.y, radius, radius, (float) angleInit, (float) angleEnd);
        // fill the sector with the quantity of sectors between the actual and previous arc
        winApp.noFill();
        float distanceRadius = radius - nextRadius;
        for (int i = 0; i < distanceRadius; i++) {
            winApp.strokeWeight(1);
            winApp.arc(pos.x, pos.y, nextRadius + i, nextRadius + i, (float) angleInit, (float) angleEnd);
        }

        // over behavior show the label
        if (over) {
            winApp.textFont(localFont);
            winApp.textSize(15);
            winApp.stroke(0, 0, 0, 255);
            winApp.fill(0, 0, 0, 80);
            // Position of the label
            winApp.text(className, pos.x - (totalRadius / 2), pos.y - totalRadius - 50);
            winApp.textSize(10);
            winApp.stroke(0, 0, 0, 255);
            winApp.fill(0, 0, 0, 60);
            String text = name + " " + operator + " " + value;
            winApp.text(text, pos.x - (totalRadius / 2), pos.y - totalRadius - 30);
        }
        if (level == 1) {
            winApp.stroke(230);
            winApp.strokeWeight(1);
            winApp.line(pos.x, pos.y, anchorX, anchorY);
            // permanent label
            winApp.textFont(localFont);
            winApp.textSize(10);
            winApp.stroke(0);
            winApp.fill(0);
            // Position of the label
            float newAngle = ((angleEnd - angleInit) / 2) + angleInit;
            float anchorXLabel = pos.x + (float) (totalRadius * Math.cos(newAngle)) + (float) (8 * Math.cos(newAngle));
            float anchorYLabel = pos.y + (float) (totalRadius * Math.sin(newAngle)) + (float) (8 * Math.sin(newAngle));
            float anchorXLabelPortion = pos.x + (float) ((totalRadius / 2) * Math.cos(newAngle)) + (float) (8 * Math.cos(newAngle));
            float anchorYLabelPortion = pos.y + (float) ((totalRadius / 2) * Math.sin(newAngle)) + (float) (8 * Math.sin(newAngle));

            winApp.pushMatrix();
            if (anchorX < pos.x) {
                winApp.translate(anchorXLabel, anchorYLabel);
                winApp.textAlign(winApp.LEFT);
                winApp.rotate(newAngle);
                winApp.text(name, 2, 2);
            } else {
                winApp.translate(anchorXLabel, anchorYLabel);
                winApp.textAlign(winApp.RIGHT);
                winApp.rotate((float) (Math.PI) + newAngle);
                winApp.text(name, -1, -1);
            }
            winApp.popMatrix();

            winApp.pushMatrix();
            if (anchorX < pos.x) {
                winApp.translate(anchorXLabelPortion, anchorYLabelPortion);
                winApp.rotate(newAngle);
                winApp.textSize(20);
                winApp.stroke(0, 0, 0, 255);
                winApp.fill(0, 0, 0, 120);
                winApp.text(String.valueOf(portion * 100) + "%", 2, 2);
            } else {
                winApp.translate(anchorXLabelPortion, anchorYLabelPortion);
                winApp.rotate((float) (Math.PI) + newAngle);
                winApp.textSize(20);
                winApp.stroke(0, 0, 0, 255);
                winApp.fill(0, 0, 0, 120);
                winApp.text(String.valueOf(portion * 100) + "%", -1, -1);
            }
            winApp.popMatrix();
        }
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
        if ((dragAngle >= angleInit && dragAngle <= angleEnd) && (winApp.sqrt(winApp.sq(disX) + winApp.sq(disY)) < radius / 2) && (winApp.sqrt(winApp.sq(disX) + winApp.sq(disY)) > nextRadius / 2) && !workspace.graphicPanel.isCollapsed() && visible) {
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
        if (!mouseOver) {
            over = false;
        }
    }
}
