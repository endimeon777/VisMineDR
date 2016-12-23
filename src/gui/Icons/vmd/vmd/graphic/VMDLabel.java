package gui.Icons.vmd.vmd.graphic;

import gui.Icons.vmd.guicomponents.G4P;
import gui.Icons.vmd.guicomponents.GCombo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import prefuse.util.ColorLib;
import processing.core.PApplet;
import processing.core.PFont;
import gui.Icons.vmd.guicomponents.GComponent;
import javax.swing.table.AbstractTableModel;

public class VMDLabel extends GComponent {
    // Attribute label

    VMDSector sector;
    PApplet parent;
    public VMDClassMenu classMenu;
    public GCombo menuCombo;
    String name;
    float xAnchor, yAnchor, angInit, textX, textY, xDrag, yDrag;
    int index;
    // Order list
    public Map<String, String[]> mapArray = new HashMap<String, String[]>();
    String[] tempOrderArray;
    String[] orderArray; // Order alphabetically the class values and construct a wheel list
    //String[] tempOrderArray;
    int[] tempOrderArrayWheel;
    // Render
    int attributeColor = 0;
    int attributeLabelSize = 9;
    String orientation; // right or left
    boolean horizontal;
    public boolean menuExpanded = false;
    boolean isCategorical = false;
    boolean isDragged = false;
    boolean isClassAttribute = false;
    // banner label
    int umbral = 10; // Character umbral for active banner
    String labelBanner = "";
    int charCount;
    boolean typeMenuCombo = false;
    AbstractTableModel dataIn;

    public VMDLabel(PApplet _parent, VMDSector _sector, String _attributeName, float _xAnchor, float _yAnchor, float _angInit, int _index) {
        super(_parent, (int) _xAnchor, (int) _yAnchor);
        sector = _sector;
        parent = _parent;
        name = _attributeName;
        xAnchor = _xAnchor;
        yAnchor = _yAnchor;
        angInit = _angInit;
        index = _index;

        dataIn = sector.sectors.workspace.data.dataInVmd;

        if ( dataIn.getColumnClass(dataIn.findColumn(name))== String.class) {
            isCategorical = true;
        }
    }

    public void setClassAttribute(boolean _value) {
        isClassAttribute = _value;
    }

    public void init() {
        // Define the orientation
        if (xAnchor > sector.sectors.workspace.x) {
            orientation = "RIGHT";
        } else {
            orientation = "LEFT";
        }
        // Storage in a hashmap the distinct values of the categoric attribute
        if (isCategorical) {
            mapArray = sector.sectors.workspace.data.distinctCategoricalValues;
            tempOrderArray = mapArray.get(name);
            tempOrderArrayWheel = new int[tempOrderArray.length];
            orderArray();
            if (tempOrderArray.length >= (this.getY() / (18))) {
                menuCombo = new GCombo(parent, tempOrderArray, 10, (int) this.getX(), (int) (this.getY() - 10), 80);
                menuCombo.setVisible(false);
                menuCombo.setFont(localFont.name, 9);
                typeMenuCombo = true;
            }
            classMenu = new VMDClassMenu(parent, this, xAnchor, yAnchor, orderArray, orientation, typeMenuCombo);
            classMenu.init();
        }
        createEventHandler(winApp, "handleLabelsEvents", new Class[]{VMDLabel.class});
        registerAutos_DMPK(true, true, false, false);
        sector.sectors.workspace.addToPanel(this);
    }

    void orderArray() {
        int median;
        orderArray = new String[tempOrderArray.length];
        if ((tempOrderArray.length % 2) == 0) {
            median = (tempOrderArray.length / 2);
        } else {
            median = Math.round(tempOrderArray.length / 2) - 1;
        }
        Arrays.sort(tempOrderArray); // Order alphabetically the strings of the class values
        if (tempOrderArray.length >= (this.getY() / (18))) { // 18 will be the item menu + the blank space betwwen the next
            // prepare the list in a kind of wheel
            for (int i = median; i < tempOrderArray.length; i++) {
                orderArray[i - median] = tempOrderArray[i];
                tempOrderArrayWheel[i - median] = i;
            }
            for (int i = 0; i < median; i++) {
                orderArray[i + median] = tempOrderArray[i];
                tempOrderArrayWheel[i + median] = i;
            }
        } else {
            orderArray = tempOrderArray;
        }
    }

    void reOrderArray(int _times) {
        String tempString = "", endPosition = "";
        for (int i = 0; i < _times; i++) {
            endPosition = orderArray[tempOrderArray.length];
            for (int r = tempOrderArray.length; r > 0; r--) {
                tempString = orderArray[r - 1];
                orderArray[r] = tempString;
            }
            orderArray[0] = endPosition;
        }
    }

    /**
     * Set the color of the attribute anchor and label
     * @param color
     */
    public void setAttributeColor(int color) {
        attributeColor = color;
    }

    /**
     * Set the color of the attribute anchor and label
     * @param color
     */
    public void setAttributeLabelSize(int size) {
        attributeLabelSize = size;
    }

    public void setHorizontal(boolean value) {
        horizontal = value;
    }

    @Override
    public boolean isOver(int ax, int ay) {
        Point p = new Point(0, 0);
        calcAbsPosition(p);
        boolean tempReturn = false;
        if (horizontal) {
            if (p.x > sector.sectors.workspace.x) {
                if ((ax >= p.x + 8 && ax <= p.x + (name.length() * attributeLabelSize) + 8 && ay >= p.y - 8 && ay <= p.y) && !sector.sectors.workspace.graphicPanel.isCollapsed() && visible) {
                    tempReturn = true;
                }
            } else {
                if ((ax >= p.x - (name.length() * attributeLabelSize) + 8 && ax <= p.x + 8 && ay >= p.y - 8 && ay <= p.y) && !sector.sectors.workspace.graphicPanel.isCollapsed() && visible) {
                    tempReturn = true;
                }
            }
        } else {
            double dragAngle = 0;
            // Follow the angle position of the mouse once a label attribute has been dragged
            dragAngle = Math.atan2((ay - p.y), (ax - p.x));
            if (dragAngle < 0.00) {
                dragAngle += 2 * Math.PI;
            } // Change the range from (PI/-PI) to (2PI)
            // the mouse most be not only between the angles, must be in the radious of the label
            float disX = p.x - ax;
            float disY = p.y - ay;
            if (p.x > sector.sectors.workspace.x) {
                if ((winApp.sqrt(winApp.sq(disX) + winApp.sq(disY)) < (sector.radius/3)) && (dragAngle >= angInit - 0.2 && dragAngle <= angInit + 0.2) && !sector.sectors.workspace.graphicPanel.isCollapsed() && visible) {
                    tempReturn = true;
                }
            } else {
                if ((winApp.sqrt(winApp.sq(disX) + winApp.sq(disY)) < (sector.radius/3)) && (dragAngle >= angInit - 0.2 && dragAngle <= angInit + 0.2) && !sector.sectors.workspace.graphicPanel.isCollapsed() && visible) {
                    tempReturn = true;
                }
            }
        }
        if (tempReturn) {
            return true;
        } else {
            return false;
        }
    }

    public void draw() {
        winApp.frameRate(2); // Control the animation speed
        if (!visible) {
            return;
        }
        winApp.pushStyle();
        winApp.style(G4P.g4pStyle);
        Point pos = new Point(0, 0);
        calcAbsPosition(pos);

        // Lablel
        winApp.textFont(localFont);
        winApp.textSize(attributeLabelSize);
        if (isClassAttribute) {
            winApp.fill(ColorLib.rgb(255, 51, 0));
        } else {
            winApp.fill(attributeColor);
        }
        // is Banner Label if the lenght is over x amount
        if (name.length() > umbral) {
            charCount += 1;
            if (charCount == name.length() - (umbral)) {
                charCount = pos.x + 8;
                labelBanner = name.substring(0, umbral);
                charCount = 0;
            }
            labelBanner = name.substring(charCount, charCount + umbral);
        } else {
            labelBanner = name;
        }
        // Position and Orientation
        if (!horizontal) {
            if (isDragged) {
                winApp.text(labelBanner, xDrag, yDrag);
            } else {
                textX = pos.x + (float) (8 * Math.cos(angInit));
                textY = pos.y + (float) (8 * Math.sin(angInit));
                winApp.pushMatrix();
                if (pos.x > sector.sectors.workspace.x) {
                    winApp.translate(textX, textY);
                    winApp.textAlign(winApp.LEFT);
                    winApp.rotate(angInit);
                    winApp.text(labelBanner, 2, 2);
                } else {
                    winApp.translate(textX, textY);
                    winApp.textAlign(winApp.RIGHT);
                    winApp.rotate((float) (Math.PI) + angInit);
                    winApp.text(labelBanner, -1, -1);
                }
                winApp.popMatrix();
            }
        } else {
            if (pos.x > sector.sectors.workspace.x) {
                if (isDragged) {
                    winApp.text(labelBanner, xDrag, yDrag);
                } else {
                    winApp.textAlign(winApp.LEFT);
                    winApp.text(labelBanner, pos.x + 8, pos.y);
                }
                if (isClassAttribute) {
                    winApp.strokeWeight(1);
                    winApp.stroke(1);
                    winApp.noFill();
                    winApp.line(pos.x + 6, pos.y + 2, pos.x + 6 + labelBanner.length() * 6, pos.y + 2);
                    winApp.line(pos.x + 6, pos.y + 4, pos.x + 6 + labelBanner.length() * 6, pos.y + 4);
                }
            } else {
                if (isDragged) {
                    winApp.text(labelBanner, xDrag, yDrag);
                } else {
                    winApp.textAlign(winApp.RIGHT);
                    winApp.text(labelBanner, pos.x - (name.length()), pos.y);
                }
                if (isClassAttribute) {
                    winApp.strokeWeight(1);
                    winApp.stroke(1);
                    winApp.noFill();
                    winApp.line(pos.x - (name.length() * 6) - 2, pos.y + 2, pos.x - 6, pos.y + 2);
                    winApp.line(pos.x - (name.length() * 6) - 2, pos.y + 4, pos.x - 6, pos.y + 4);
                }
            }
        }
        winApp.popStyle();
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
                    sector.sectors.setFilterScaleInVisible();
                    sector.sectors.zoom.setVisible(false);
                    sector.sectors.workspace.zoomWorkspace.setVisible(false);
                    // hide all the zoom points
                    for (int i = 0; i < sector.sectors.workspace.points.length; i++) {
                        sector.sectors.workspace.points[i].setVisible(false);
                        sector.sectors.workspace.points[i].labels.setVisible(false);
                    }
                    for (int i = 0; i < sector.sectors.sector.length; i++) {
                        if (sector.sectors.sector[i].label.menuCombo != null) {
                            if (sector.sectors.sector[i].label.typeMenuCombo == true && sector.sectors.sector[i].label.menuCombo.isVisible()) {
                                sector.sectors.sector[i].label.menuCombo.setVisible(false);
                            }
                        }
                    }

                } else if (focusIsWith == this) {
                    looseFocus(null);
                }
                break;
            case MouseEvent.MOUSE_CLICKED:
                if (mouseOver && isCategorical) {
//                    if (menuExpanded) {
//                        classMenu.setMenuInVisible();
//                        menuExpanded = false;
//                    }
                    for (int i = 0; i < sector.sectors.sector.length; i++) {
                        if (isCategorical && sector.sectors.sector[i].label.menuExpanded == true) {
                            sector.sectors.sector[i].label.classMenu.setMenuInVisible();
                            sector.sectors.sector[i].label.menuExpanded = false;
                        }
                    }
                }

                if (focusIsWith == this && mouseOver && !menuExpanded && isCategorical && typeMenuCombo == false) {
                    menuExpanded = true;
                    classMenu.setMenuVisible();
                    if (menuCombo != null) {
                        menuCombo.setVisible(false);
                    }
                    // seactive the current class attribute
                    for (int i = 0; i < sector.sectors.sector.length; i++) {
                        if (sector.sectors.sector[i].label.name == sector.sectors.workspace.classAttribute) {
                            sector.sectors.sector[i].label.isClassAttribute = false;
                        }
                    }
                    sector.sectors.workspace.classAttribute = name; // If is clicked is class attribute
                    sector.sectors.workspace.classColored();
                }
                if (focusIsWith == this && mouseOver && !menuExpanded && isCategorical && typeMenuCombo == true) {
                    if (menuExpanded) {
                        classMenu.setMenuInVisible();
                        menuExpanded = false;
                    }
                    menuCombo.setVisible(true);
                    // seactive the current class attribute
                    for (int i = 0; i < sector.sectors.sector.length; i++) {
                        if (sector.sectors.sector[i].label.name == sector.sectors.workspace.classAttribute) {
                            sector.sectors.sector[i].label.isClassAttribute = false;
                        }
                    }
                    sector.sectors.workspace.classAttribute = name; // If is clicked is class attribute
                    sector.sectors.workspace.classColored();
                }

                break;
            case MouseEvent.MOUSE_DRAGGED:
                if (focusIsWith == this && mouseOver) {
                    isDragged = true;
                    if (menuCombo != null) {
                        menuCombo.setVisible(false);
                    }
                }
                if (isDragged == true) {
                    sector.sectors.isDraggedLabel = true;
                    xDrag = winApp.mouseX;
                    yDrag = winApp.mouseY;
                }
                break;
            case MouseEvent.MOUSE_RELEASED:
                // if the mouse has moved then release focus otherwise
                // MOUSE_CLICKED will handle it
                if (focusIsWith == this && isDragged) { // && mouseHasMoved(winApp.mouseX, winApp.mouseY)){
                    looseFocus(null);
                    isDragged = false;
                    sector.sectors.isDraggedLabel = false;
                    sector.sectors.workspace.reMake();
                    sector.sectors.workspace.generateOrderArray(index, sector.sectors.indexNew);
                    sector.sectors.workspace.init();
                }
                break;
        }
    }
}
