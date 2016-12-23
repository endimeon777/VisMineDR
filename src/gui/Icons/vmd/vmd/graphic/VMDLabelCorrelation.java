package gui.Icons.vmd.vmd.graphic;

import gui.Icons.vmd.guicomponents.G4P;
import gui.Icons.vmd.guicomponents.GComponent;
import java.awt.Point;
import java.awt.event.MouseEvent;
import processing.core.PApplet;
import gui.Icons.vmd.vmd.graphic.VMDCorrelationRatio;

public class VMDLabelCorrelation extends GComponent {

    PApplet parent;
    VMDCorrelationRatio ratioCorr;
    float x, y, radiusCorrelation, angle, angleNext;
    String name, label;
    int numAttribute;
    boolean over;
    boolean show;

    public VMDLabelCorrelation(PApplet _parent, VMDCorrelationRatio _ratioCorr, float _x, float _y, String _name, float _angle, float _angleNext, float _radiusCorrelation, int _numAttribute) {
        super(_parent, (int) _x, (int) _y);
        parent = _parent;
        ratioCorr = _ratioCorr;
        x = _x;
        y = _y;
        name = _name;
        angle = _angle;
        angleNext = _angleNext;
        radiusCorrelation = _radiusCorrelation;
        numAttribute = _numAttribute;
    }

    public void init() {
        createEventHandler(winApp, "handleLabelsEvents", new Class[]{VMDLabelCorrelation.class});
        registerAutos_DMPK(true, true, false, false);
        ratioCorr.workspace.addToPanel(this);
    }

    public void setShow(boolean value) {
        show = value;
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
            winApp.fill(0);
        } else {
            winApp.fill(210);
        }
        winApp.textSize(10);
        float angleNew = ((angle - angleNext)/2) + angleNext;
        float anchorX = pos.x + ((float) (radiusCorrelation * Math.cos(angleNew))) + (float) (10 * Math.cos(angleNew));
        float anchorY = pos.y + ((float) (radiusCorrelation * Math.sin(angleNew))) + (float) (10 * Math.sin(angleNew));
        winApp.pushMatrix();
        if (anchorX > ratioCorr.x) {
            winApp.translate(anchorX, anchorY);
            winApp.textAlign(winApp.LEFT);
            winApp.rotate(angleNew);
            winApp.text(name, 2, 2);
        } else {
            winApp.translate(anchorX, anchorY);
            winApp.textAlign(winApp.RIGHT);
            winApp.rotate((float) (Math.PI) + (angleNew));
            winApp.text(name, -1, -1);
        }
        winApp.popMatrix();
        // Show number of the attribute
        winApp.textSize(30);
        if (over) {
            winApp.stroke(0, 0, 0, 255);
            winApp.fill(0, 0, 0, 120);
        } else {
            winApp.stroke(0, 0, 0, 255);
            winApp.fill(0, 0, 0, 10);
        }
        float xx = pos.x + ((float) (radiusCorrelation / 2 * Math.cos(angle + ((Math.PI * 2) / (ratioCorr.numAttributes * 2)))));
        float yy = pos.y + ((float) (radiusCorrelation / 2 * Math.sin(angle + ((Math.PI * 2) / (ratioCorr.numAttributes * 2)))));
        winApp.text(numAttribute, xx, yy);
        // show the scale
        if (over) {
            winApp.fill(0);
            winApp.textSize(17);
            for (int i = 0; i < ratioCorr.numAttributes; i++) {
                float tmpX = pos.x + ((float) ((i + 1) * (radiusCorrelation / ratioCorr.numAttributes) * Math.cos(angle)));
                float tmpY = pos.y + ((float) ((i + 1) * (radiusCorrelation / ratioCorr.numAttributes) * Math.sin(angle)));
                winApp.text((i + 1), tmpX, tmpY);
            }
        }
        winApp.popStyle();
    }

    @Override
    public boolean isOver(int ax, int ay) {
        Point p = new Point(0, 0);
        calcAbsPosition(p);
        boolean tempReturn = false;
        double dragAngle = 0;
        // Follow the angle position of the mouse once a label attribute has been dragge
        float angleNew = ((angle - angleNext)/2) + angleNext;
        dragAngle = Math.atan2((ay - p.y), (ax - p.x));
        if (dragAngle < 0.00) {
            dragAngle += 2 * Math.PI;
        } // Change the range from (PI/-PI) to (2PI)
        // the mouse most be not only between the angles, must be in the radious of the label
        float anchorX = p.x + ((float) (radiusCorrelation * Math.cos(angleNew)));
        float anchorY = p.y + ((float) (radiusCorrelation * Math.sin(angleNew)));
        float disX = anchorX - ax;
        float disY = anchorY - ay;
        
        if (anchorX > ratioCorr.x) {
            if ((winApp.sqrt(winApp.sq(disX) + winApp.sq(disY)) < (radiusCorrelation / 5)) && (dragAngle >= angleNew - 0.2 && dragAngle <= angleNew + 0.2) && !ratioCorr.workspace.graphicPanel.isCollapsed() && visible) {
                tempReturn = true;
            }
        } else {
            if ((winApp.sqrt(winApp.sq(disX) + winApp.sq(disY)) < (radiusCorrelation / 5)) && (dragAngle >= angleNew - 0.2 && dragAngle <= angleNew + 0.2) && !ratioCorr.workspace.graphicPanel.isCollapsed() && visible) {
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
            over = true;
        } else if (cursorIsOver == this) {
            cursorIsOver = null;
            over = false;
        }
    }
}
