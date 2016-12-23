package gui.Icons.vmd.vmd.graphic;

import gui.Icons.vmd.guicomponents.G4P;
import gui.Icons.vmd.guicomponents.GComponent;
import java.awt.Point;
import java.awt.event.MouseEvent;
import processing.core.PApplet;

public class VMDCorrelationRatio extends GComponent {

    PApplet parent;
    public VMDWorkspace workspace;
    public VMDLabelCorrelation labels;
    public float x, y;
    float angleAct, angleNext, ratioCorrelationGraph;
    public int numAttributes, number;
    String name;

    public VMDCorrelationRatio(PApplet _parent, VMDWorkspace _workspace, String _name, float _x, float _y, float _angleAct, float _angleNext, int _numAttributes, float _ratioCorrelationGraph, int i) {
        super(_parent, (int) _x, (int) _y);
        parent = _parent;
        workspace = _workspace;
        name = _name;
        x = _x;
        y = _y;
        numAttributes = _numAttributes;
        angleAct = _angleAct;
        angleNext = _angleNext;
        ratioCorrelationGraph = _ratioCorrelationGraph;
        number = i;
    }

    public void init() {
        labels = new VMDLabelCorrelation(parent,this, x, y, name, angleAct, angleNext, ratioCorrelationGraph, number);
        labels.init();
        createEventHandler(winApp, "handleLabelsEvents", new Class[]{VMDCorrelationRatio.class});
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
        //winApp.fill(100);
        winApp.stroke(0);
        float xAnchorCorr = (float) (pos.x + (ratioCorrelationGraph * Math.cos(angleAct)));
        float yAnchorCorr = (float) (pos.y + (ratioCorrelationGraph * Math.sin(angleAct)));
        winApp.line(pos.x, pos.y, xAnchorCorr, yAnchorCorr);
        winApp.strokeWeight((float)0.1);
        winApp.stroke(230);
        winApp.ellipse(pos.x, pos.y, ratioCorrelationGraph * 2, ratioCorrelationGraph * 2);
        winApp.stroke(0);
        winApp.strokeWeight((float)0.5);
       
        for (int i = 0; i < numAttributes; i++) {
            System.out.println(angleAct + ":" + angleNext);
            double y = pos.y + (((i + 1) * (ratioCorrelationGraph / numAttributes)) * Math.sin(angleAct));
            double x = pos.x + (((i + 1) * (ratioCorrelationGraph / numAttributes)) * Math.cos(angleAct));
            double yy = pos.y + (((i + 1) * (ratioCorrelationGraph / numAttributes)) * Math.sin(angleNext));
            double xx = pos.x + (((i + 1) * (ratioCorrelationGraph / numAttributes)) * Math.cos(angleNext));
            float disX = (float) (x - xx);
            float disY = (float) (y - yy);
            float distance = winApp.sqrt(winApp.sq(disX) + winApp.sq(disY));
            float xPoint, yPoint;

            if ((!workspace.data.correlationCoefficientt.getString(i, "Attributes").equals(name))) {
                String tmpName = workspace.data.correlationCoefficientt.getString(i, "Attributes");
                double coefCorrelation = workspace.data.correlationCoefficientt.getDouble(i, name);
                double proportional = Math.abs(coefCorrelation * distance);
                float anglePoint = (float) (angleAct + (Math.PI / 2) + (Math.PI / 4));
                xPoint = (float) (x + (proportional * (Math.cos(anglePoint))));
                yPoint = (float) (y + (proportional * (Math.sin(anglePoint))));
               
                if (coefCorrelation > 0){
                    winApp.stroke(0);
                    winApp.fill(0, 255, 0, 30);
                } else {
                     winApp.stroke(0);
                     winApp.fill(255, 0, 0, 30);
                }
                winApp.ellipse((float) xPoint, (float) yPoint, 2, 2);
                winApp.beginShape();
                winApp.vertex(xPoint, yPoint, (float) x, (float) y);
                winApp.vertex((float) pos.x, (float) pos.y);
                winApp.vertex((float) x, (float) y);
                winApp.vertex((float) xPoint, (float) yPoint);
                winApp.vertex((float) pos.x, (float) pos.y);
                winApp.endShape(winApp.CLOSE);
            }
        }
       
        winApp.popStyle();
    }

//    @Override
//    public boolean isOver(int ax, int ay) {
//        Point p = new Point(0, 0);
//        calcAbsPosition(p);
//        if ((ax >= p.x - w / 2 && ax <= p.x + w / 2 && ay >= p.y - h && ay <= p.y) && !layerMan.workspace.graphicPanel.isCollapsed() && visible) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    @Override
//    public void mouseEvent(MouseEvent event) {
//        if (!visible) {
//            return;
//        }
//        boolean mouseOver = isOver(winApp.mouseX, winApp.mouseY);
//        if (mouseOver) {
//            cursorIsOver = this;
//        } else if (cursorIsOver == this) {
//            cursorIsOver = null;
//        }
//
//        switch (event.getID()) {
//            case MouseEvent.MOUSE_PRESSED:
//
//                break;
//            case MouseEvent.MOUSE_RELEASED:
//                if (focusIsWith != this) {
//                    looseFocus(null);
//                }
//                break;
//        }
//    }
}
