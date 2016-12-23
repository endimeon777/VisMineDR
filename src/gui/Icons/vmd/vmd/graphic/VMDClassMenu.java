package gui.Icons.vmd.vmd.graphic;

import gui.Icons.vmd.guicomponents.G4P;
import gui.Icons.vmd.guicomponents.GComponent;
import java.awt.Point;
import processing.core.PApplet;
import processing.core.PFont;

public class VMDClassMenu extends GComponent {

    PApplet parent;
    VMDLabel label;
    public VMDItemMenu[] itemMenuClass;
    float xMenuClass, yMenuClass;
    String[] values;
    String orientation;
    int itemMenuSize = 10;
    boolean typeMenuCombo;

    // This method has to create the Menu of the class values
    public VMDClassMenu(PApplet _parent, VMDLabel _label, float _x, float _y, String[] _values, String _orientation, boolean _type) {
        super(_parent, (int) _x, (int) _y);
        parent = _parent;
        label = _label;
        xMenuClass = _x;
        yMenuClass = _y;
        values = _values;
        orientation = _orientation;
        typeMenuCombo = _type;
        createEventHandler(winApp, "handleMenuEvents", new Class[]{VMDClassMenu.class});
        registerAutos_DMPK(true, true, false, false);
    }

    public void setSize(int _value) {
        itemMenuSize = _value;
    }

    public void setMenuVisible() {
        for (int i = 0; i < values.length; i++) {
            if (label.isCategorical && typeMenuCombo == false) {
                itemMenuClass[i].setVisible(true);
            }
        }
    }

    public void setMenuInVisible() {
        for (int i = 0; i < values.length; i++) {
            if (label.isCategorical && itemMenuClass[i].isVisible()) {
                itemMenuClass[i].setVisible(false);
            }
        }
    }

    public void init() {
        itemMenuClass = new VMDItemMenu[values.length];
        int count = values.length;
//        int limitNumberValues = (int) (yMenuClass / (10 + 8)); // This variable is for know the maximum number of class values labels
        // must be shown, goes from the screen y=0 coordinate to the y coordinate of the class attribute.
        // 10, is the render space between labels, 8 the size of the font
        float distBetweenValues = 0, attribX = 0, attribY = 0;
//        if (count < limitNumberValues) {
        for (int i = 0; i < values.length; i++) {
            distBetweenValues += 10;
            attribX = xMenuClass;
            attribY = yMenuClass - distBetweenValues;
            itemMenuClass[i] = new VMDItemMenu(parent, this, attribX, attribY, values[i], orientation, itemMenuSize);
            itemMenuClass[i].init();
            itemMenuClass[i].setVisible(false);
        }
//        } else {
//            int exclude = (int) (count - limitNumberValues) / 2; // the quantity of elements that
//            // hide from the both ends of the array
//            for (int i = exclude + 1; i < values.length - exclude; i++) {
//                int colorValue = label.sector.sectors.workspace.data.categoricalValuesRendererInformation.getInt(i, "Color");
//                distBetweenValues += 10;
//                attribX = xMenuClass;
//                attribY = yMenuClass - distBetweenValues;
//                itemMenuClass[i] = new VMDItemMenu(parent, this, attribX, attribY, values[i], orientation, itemMenuSize);
//                itemMenuClass[i].init();
//                itemMenuClass[i].setVisible(false);
//            }
//        }
    }
}
