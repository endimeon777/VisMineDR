package gui.Icons.vmd.vmd.graphic;

import gui.Icons.vmd.guicomponents.G4P;
import java.awt.Point;
import java.awt.event.MouseEvent;
import prefuse.util.ColorLib;
import processing.core.PApplet;
import gui.Icons.vmd.guicomponents.GComponent;

public class VMDColorItem extends GComponent {

    PApplet parent;
    VMDColorPallete pallete;
    int color;
    float xColorItem, yColorItem;

    public VMDColorItem(PApplet _parent, VMDColorPallete _pallete, int _color, float _x, float _y) {
        super(_parent, (int) _x, (int) _y);
        parent = _parent;
        pallete = _pallete;
        color = _color;
        xColorItem = _x;
        yColorItem = _y;
    }

    public void init() {
        createEventHandler(winApp, "handleColorItemEvents", new Class[]{VMDColorItem.class});
        registerAutos_DMPK(true, true, false, false);
        pallete.itemMenu.classMenu.label.sector.sectors.workspace.addToPanel(this);
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
        winApp.fill(color);
        winApp.rect(pos.x, pos.y, 7, 7);
        winApp.popStyle();
    }

    @Override
    public boolean isOver(int ax, int ay) {
        Point p = new Point(0, 0);
        calcAbsPosition(p);
        if ((ax >= p.x && ax <= p.x + 7 && ay >= p.y && ay <= p.y + 7) && ! pallete.itemMenu.classMenu.label.sector.sectors.workspace.graphicPanel.isCollapsed() && visible) {
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
                }
                if (focusIsWith == this) {
                    for (int i = 0; i < pallete.itemMenu.classMenu.label.sector.sectors.workspace.data.categoricalValuesRendererInformation.getRowCount(); i++) {
                        if (pallete.itemMenu.classMenu.label.sector.sectors.workspace.data.categoricalValuesRendererInformation.getString(i, "Attribute").equals(pallete.itemMenu.classMenu.label.name) && pallete.itemMenu.classMenu.label.sector.sectors.workspace.data.categoricalValuesRendererInformation.getString(i, "Value").equals(pallete.itemMenu.nameValue)) {
                            pallete.itemMenu.classMenu.label.sector.sectors.workspace.data.categoricalValuesRendererInformation.setInt(i, "Color", color);
                            pallete.itemMenu.setColor(color);
                        }
                    }
                }
                pallete.itemMenu.classMenu.label.sector.sectors.workspace.applyRendererInformation();
                break;
            case MouseEvent.MOUSE_RELEASED:
                // if the mouse has moved then release focus otherwise
                // MOUSE_CLICKED will handle it
                if (focusIsWith == this) { // && mouseHasMoved(winApp.mouseX, winApp.mouseY)){
                    //looseFocus(null);
                }
                break;

        }
    }
}
