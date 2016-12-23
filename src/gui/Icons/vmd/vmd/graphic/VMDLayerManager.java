package gui.Icons.vmd.vmd.graphic;

import gui.Icons.vmd.guicomponents.G4P;
import gui.Icons.vmd.guicomponents.GComponent;
import java.awt.Point;
import java.awt.event.MouseEvent;
import processing.core.PApplet;

public class VMDLayerManager extends GComponent {

    PApplet parent;
    VMDWorkspace workspace;
    VMDLayer layers[];
    float x, y;

    public VMDLayerManager(PApplet _parent, VMDWorkspace _workspace, float _x, float _y) {
        super(_parent, (int) _x, (int) _y);
        parent = _parent;
        x = _x;
        y = _y;
        workspace = _workspace;
    }

    public void init() {
        layers = new VMDLayer[workspace.countLayers];
        float tmpY = workspace.y - 25; // the layers start at y less 25, wich is an arbitrary valueo
        for (int i = 0; i < workspace.countLayers; i++) {
            int[] layerValues;
            layerValues = workspace.matrixLayer.get(i + 1);
            layers[i] = new VMDLayer(parent, this, this.x, tmpY, layerValues, i + 1);
            layers[i].init();
            layers[i].setVisible(false);
            tmpY = tmpY - 5;
        }
        createEventHandler(winApp, "handleLayerManagerEvents", new Class[]{VMDLayerManager.class});
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
        winApp.fill(144, 116, 100, 10);
        winApp.ellipse(pos.x + workspace.radius , workspace.y, workspace.radius * 2, 8);
        //winApp.line(pos.x + (workspace.radius * 2), pos.y + 5, pos.x + (workspace.radius * 2), 0);
        winApp.popStyle();
    }
}
