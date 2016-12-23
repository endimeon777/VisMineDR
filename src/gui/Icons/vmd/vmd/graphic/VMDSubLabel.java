package gui.Icons.vmd.vmd.graphic;

import gui.Icons.vmd.guicomponents.G4P;
import gui.Icons.vmd.guicomponents.GComponent;
import java.awt.Point;
import java.awt.event.MouseEvent;
import prefuse.util.ColorLib;
import processing.core.PApplet;

public class VMDSubLabel extends GComponent {

    PApplet parent;
    VMDRatio ratio;
    float x, y;
    String value;
    double color;
    float angle;
    double maxFrequency; // this variable is updated for known the max value of the color gradient to 255 in red
    boolean checkBox = false, isSelected = false;
    int w = 5, h = 5;
    double percentRound; // for general view of the distribution

    public VMDSubLabel(PApplet _parent, VMDRatio _ratio, float _x, float _y, String _value, double _color, float _angle, double _percentRound) {
        super(_parent, (int) _x, (int) _y);
        x = _x;
        y = _y;
        parent = _parent;
        value = _value;
        color = _color;
        angle = _angle;
        ratio = _ratio;
        percentRound = _percentRound;
    }

    public void init() {

        createEventHandler(winApp, "handleSubLabelEvents", new Class[]{VMDSubLabel.class});
        registerAutos_DMPK(true, true, false, false);
        ratio.sector.sectors.workspace.addToPanel(this);
    }

    public void setMaxFrequency(double _value) {
        this.maxFrequency = _value;
    }

    public void activeBox(boolean _value) {
        this.checkBox = _value;
    }

    public void selectBox(boolean _value) {
        this.isSelected = _value;
    }

    public void draw() {

        if (!visible) {
            return;
        }
        winApp.pushStyle();
        winApp.style(G4P.g4pStyle);
        Point pos = new Point(0, 0);
        calcAbsPosition(pos);
        if (checkBox) {
            winApp.stroke(2);
            if (isSelected) {
                winApp.fill(255, 0, 0);
            } else {
                winApp.noFill();
            }
            winApp.rect(pos.x, pos.y, w, h);
        }
        winApp.pushMatrix();
        float newAngle = angle + (float) (Math.PI / 2);
        winApp.textFont(localFont);
        winApp.textSize(12);
        double tmpRedColor = (color * 255) / maxFrequency;
        winApp.fill(ColorLib.rgb((int) tmpRedColor, 0, 0));
        winApp.translate(pos.x, pos.y);
        winApp.rotate(newAngle);
        winApp.text(value, -1, -1);
        winApp.popMatrix();
        winApp.popStyle();
    }

    @Override
    public boolean isOver(int ax, int ay) {
        Point p = new Point(0, 0);
        calcAbsPosition(p);
        if ((ax >= p.x - w / 2 && ax <= p.x + w / 2 && ay >= p.y - h / 2 && ay <= p.y + h / 2) && !ratio.sector.sectors.workspace.graphicPanel.isCollapsed() && visible) {
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
                if (focusIsWith != this && mouseOver) {
                    takeFocus();
                    if (isSelected) {
                        isSelected = false;
                    } else {
                        isSelected = true;
                    }
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