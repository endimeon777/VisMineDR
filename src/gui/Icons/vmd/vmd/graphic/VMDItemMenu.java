package gui.Icons.vmd.vmd.graphic;

import gui.Icons.vmd.guicomponents.G4P;
import java.awt.Point;
import java.awt.event.MouseEvent;
import processing.core.PApplet;
import processing.core.PFont;
import gui.Icons.vmd.guicomponents.GComponent;

public class VMDItemMenu extends GComponent {

    PApplet parent;
    public VMDClassMenu classMenu;
    public VMDColorPallete colorPallete;
    int color;
    float xItem, yItem;
    public String nameValue;
    String orientation;
    int itemMenuSize = 10;
    boolean isPalleteVisible = false;

    public VMDItemMenu(PApplet _parent, VMDClassMenu _classMenu, float _x, float _y, String _nameValue, String _orientation, int _size) {
        super(_parent, (int) _x, (int) _y);
        parent = _parent;
        classMenu = _classMenu;
        xItem = _x;
        yItem = _y;
        nameValue = _nameValue;
        orientation = _orientation;
        itemMenuSize = _size;
    }

    public void init() {
        colorPallete = new VMDColorPallete(parent, this, x, y, nameValue, orientation);
        colorPallete.init();
        for (int i = 0; i < classMenu.label.sector.sectors.workspace.data.categoricalValuesRendererInformation.getRowCount(); i++) {
            if ((classMenu.label.sector.sectors.workspace.data.categoricalValuesRendererInformation.getString(i, "Attribute") == classMenu.label.name) && (classMenu.label.sector.sectors.workspace.data.categoricalValuesRendererInformation.getString(i, "Value") == nameValue)) {
                color = classMenu.label.sector.sectors.workspace.data.categoricalValuesRendererInformation.getInt(i, "Color");
            }
        }
        createEventHandler(winApp, "handleItemMenuEvents", new Class[]{VMDItemMenu.class});
        registerAutos_DMPK(true, true, false, false);
        classMenu.label.sector.sectors.workspace.addToPanel(this);
    }

    public void setColor(int _value) {
        color = _value;
    }

    public void setPosition(float _x, float _y) {
        super.x = (int) _x;
        super.y = (int) _y;
    }

    public void draw() {
        if (!visible) {
            return;
        }
        winApp.pushStyle();
        winApp.style(G4P.g4pStyle);
        Point pos = new Point(0, 0);
        calcAbsPosition(pos);
        // draw background item menu white
        if (orientation == "RIGHT") {
            winApp.fill(255);
            winApp.rect(pos.x + 8, pos.y - 8, 87, 9);
        } else {
            winApp.fill(255);
            winApp.rect(pos.x - 80, pos.y - 8, 87, 9);
        }
        // draw item
        winApp.fill(color);
        winApp.textFont(localFont);
        winApp.textAlign(winApp.LEFT);
        winApp.textSize(itemMenuSize);
        if (orientation == "RIGHT") {
            winApp.text(nameValue, pos.x + 8, pos.y);
        } else {
            winApp.text(nameValue, pos.x - (80), pos.y);
        }

        winApp.popStyle();
    }

    @Override
    public boolean isOver(int ax, int ay) {
        Point p = new Point(0, 0);
        calcAbsPosition(p);
        boolean tempReturn = false;
        if (orientation == "RIGHT") {
            if ((ax >= p.x + 8 && ax <= p.x + (nameValue.length() * 8) && ay >= p.y - 8 && ay <= p.y) && !classMenu.label.sector.sectors.workspace.graphicPanel.isCollapsed() && visible) {
                tempReturn = true;
            }
        } else {
            if ((ax >= p.x - 80 && ax <= p.x - 8 && ay >= p.y - 8 && ay <= p.y) && !classMenu.label.sector.sectors.workspace.graphicPanel.isCollapsed() && visible) {
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
        } else if (cursorIsOver == this) {
            cursorIsOver = null;
        }

        switch (event.getID()) {
            case MouseEvent.MOUSE_PRESSED:
                if (focusIsWith != this && mouseOver) {
                    takeFocus();
                }
                if (focusIsWith == this && mouseOver) {
                    looseFocus(null);
                    isPalleteVisible = true;
                    colorPallete.setColorPalletteVisible();
                } else {
                    isPalleteVisible = false;
                    colorPallete.setColorPalletteInVisible();
                }
                break;
        }
    }
}
