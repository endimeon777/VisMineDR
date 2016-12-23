package gui.Icons.vmd.vmd.graphic;

import java.awt.Point;
import gui.Icons.vmd.vmd.*;
import prefuse.util.ColorLib;
import processing.core.PApplet;
import gui.Icons.vmd.guicomponents.G4P;
import gui.Icons.vmd.guicomponents.GComponent;

public class VMDColorPallete extends GComponent {

    PApplet parent;
    public VMDItemMenu itemMenu;
    public VMDColorItem colors[];
    float xColorPallete, yColorPallete, x_inicial;
    public String attributeName;
    public boolean palleteVisible = false;
    String orientation;

    // This class recieve the position
    public VMDColorPallete(PApplet _parent, VMDItemMenu _itemMenu, float _x, float _y, String _attributeName, String _orientation) {
        super(_parent, (int) _x, (int) _y);
        parent = _parent;
        itemMenu = _itemMenu;
        attributeName = _attributeName;
        if (_orientation == "RIGHT") { // if the label is oriented to the right
            xColorPallete = _x + (15 * 6); // arbitrary values for position the pallete
            if ((xColorPallete + 70) < itemMenu.classMenu.label.sector.sectors.workspace.width) {
                xColorPallete = _x + (15 * 7);
                x_inicial = _x + (15 * 7);
                yColorPallete = _y - 8;
            } else {
                xColorPallete = _x;
                x_inicial = _x;
                yColorPallete = itemMenu.classMenu.label.yAnchor + 8;
            }
        }
        //_x >= _itemMenu.classMenu.label.sector.sectors.workspace.x
        if (_orientation == "LEFT") {
            // Pallete apears below the label
            xColorPallete = _x - 78;
            x_inicial = _x - 78;
            yColorPallete = itemMenu.classMenu.label.yAnchor + 8;
        }
    }

    public void setColorPalletteVisible() {
        for (int i = 0; i < 60; i++) {
            colors[i].setVisible(true);
        }
    }

    public void setColorPalletteInVisible() {
        for (int i = 0; i < 60; i++) {
            colors[i].setVisible(false);
        }
    }

    public void init() {

        int acumulate = 0;
        int segs = 10;
        int steps = 6;
        colors = new VMDColorItem[225];
        for (int j = 0; j < steps; j++) {
            int[] cols = {
                ColorLib.rgb(255 - (255 / steps) * j, 255 - (255 / steps) * j, 0),
                ColorLib.rgb(255 - (255 / steps) * j, (int) ((255 / 1.5) - ((255 / 1.5) / steps) * j), 0),
                ColorLib.rgb(255 - (255 / steps) * j, (255 / 2) - ((255 / 2) / steps) * j, 0),
                ColorLib.rgb(255 - (255 / steps) * j, (int) ((255 / 2.5) - ((255 / 2.5) / steps) * j), 0),
                ColorLib.rgb(255 - (255 / steps) * j, 0, 0),
                ColorLib.rgb(255 - (255 / steps) * j, 0, (255 / 2) - ((255 / 2) / steps) * j),
                ColorLib.rgb(255 - (255 / steps) * j, 0, 255 - (255 / steps) * j),
                ColorLib.rgb((255 / 2) - ((255 / 2) / steps) * j, 0, 255 - (255 / steps) * j),
                ColorLib.rgb(0, 0, 255 - (255 / steps) * j),
                ColorLib.rgb(0, 255 - (255 / steps) * j, (int) ((255 / 2.5) - ((255 / 2.5) / steps) * j)),
                ColorLib.rgb(0, 255 - (255 / steps) * j, 0),
                ColorLib.rgb((255 / 2) - ((255 / 2) / steps) * j, 255 - (255 / steps) * j, 0)};

            for (int i = 0; i < segs; i++) {
                acumulate = i + (j * 10);
                colors[acumulate] = new VMDColorItem(parent, this, cols[i], xColorPallete, yColorPallete);
                colors[acumulate].init();
                colors[acumulate].setVisible(false);
                xColorPallete += 7;
            }
            xColorPallete = x_inicial;
            yColorPallete += 7;
        }
        createEventHandler(winApp, "handlePalleteEvents", new Class[]{VMDColorPallete.class});
        registerAutos_DMPK(true, true, false, false);
        itemMenu.classMenu.label.sector.sectors.workspace.addToPanel(this);
    }
}
