package gui.Icons.vmd.vmd.graphic;

import gui.Icons.vmd.guicomponents.G4P;
import gui.Icons.vmd.guicomponents.GComponent;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import javax.swing.table.AbstractTableModel;
import prefuse.util.ColorLib;
import processing.core.PApplet;

public class VMDFilter extends GComponent {

    PApplet parent;
    VMDRatio ratio;
    float x, y, angle, radius, initialX, initialY, xDrag, yDrag;
    int h = 5, w = 5;
    boolean isFilterActive = false;
    int type; // 0: Less, 1: Greater
    boolean active = false;
    String less = "Less than", greater = "Greater than", lessValue, greaterValue;
    public double totalDistance;
    String realValue;
    double[] maxminValues;
    double tmpRealValue;

    public VMDFilter(PApplet _parent, VMDRatio _ratio, float _x, float _y, float _angle, float _radius, int _type) {
        super(_parent, (int) _x, (int) _y);
        parent = _parent;
        ratio = _ratio;
        x = _x;
        y = _y;
        initialX = _x;
        initialY = _y;
        angle = _angle;
        radius = _radius;
        type = _type;
    }

    public void init() {
        maxminValues = ratio.sector.sectors.workspace.data.realMinMax.get(ratio.sector.name);
        createEventHandler(winApp, "handleFilterEvents", new Class[]{VMDFilter.class});
        registerAutos_DMPK(true, true, false, false);
        ratio.sector.sectors.workspace.addToPanel(this);
    }

    public void draw() {
        if (!visible) {
            return;
        }
        winApp.pushStyle();
        winApp.style(G4P.g4pStyle);
        Point pos = new Point(0, 0);
        calcAbsPosition(pos);
        winApp.fill(ColorLib.rgb(255, 51, 0));
        winApp.ellipse(pos.x, pos.y, h, w);
        winApp.pushMatrix();
        float newAngle = angle + (float) (Math.PI / 2);
        float textX = pos.x + (float) (8 * Math.cos(newAngle));
        float textY = pos.y + (float) (8 * Math.sin(newAngle));
        winApp.translate(textX, textY);
        winApp.rotate(newAngle);
//        winApp.rect(-1, 1, h, w);
        winApp.textFont(localFont);
        winApp.textSize(8);
        if (type == 0) {
            if (lessValue != null) {
                winApp.text(less + lessValue, -1, -1);
            } else {
                winApp.text(less, -1, -1);
            }
        } else {
            if (greaterValue != null) {
                winApp.text(greater + greaterValue, -1, -1);
            } else {
                winApp.text(greater, -1, -1);
            }
        }

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

    double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("##.##");
        return Double.valueOf(twoDForm.format(d));
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
                if (focusIsWith != this && mouseOver && parent.mouseButton == parent.LEFT) {
                    takeFocus();
                }
                if (parent.mouseButton == parent.LEFT && (initialX != super.x) && (initialY != super.y) && (winApp.mouseX >= initialX - (w * 2) && winApp.mouseX <= initialX && winApp.mouseY >= initialY && winApp.mouseY <= initialY + (h * 4))) {
                    super.x = (int) initialX;
                    super.y = (int) initialY;
                    if (type == 0) {
                        realValue = String.valueOf(roundTwoDecimals(maxminValues[1]));
                        lessValue = "-->  " + realValue;
                    } else {
                        realValue = String.valueOf(roundTwoDecimals(maxminValues[0]));
                        greaterValue = "-->  " + realValue;
                    }
                } else {
                    if (type == 0 && parent.mouseButton == parent.LEFT && !ratio.filterControlGreater.isOver(winApp.mouseX, winApp.mouseY)) {

                        // Obtain the angle where the user click
                        looseFocus(null);
                        double dragAngle = 0;
                        // Follow the angle position of the mouse once a label attribute has been dragged
                        dragAngle = Math.atan2((winApp.mouseY - ratio.sector.sectors.workspace.y), (winApp.mouseX - ratio.sector.sectors.workspace.x));
                        if (dragAngle < 0.00) {
                            dragAngle += 2 * Math.PI;
                        } // Change the range from (PI/-PI) to (2PI)
                        float disX = 0;
                        float disY = 0;
                        disX = x - winApp.mouseX;
                        disY = y - winApp.mouseY;
                        // if the click is inside the ellipse validates with the workspace center
                        float disXInside = ratio.sector.sectors.workspace.x - winApp.mouseX;
                        float disYInside = ratio.sector.sectors.workspace.y - winApp.mouseY;
                        if ((winApp.sqrt(winApp.sq(disXInside) + winApp.sq(disYInside)) < radius + 5))  {
                            double filterControlDistance = winApp.sqrt(winApp.sq(disX) + winApp.sq(disY));
                            double tmpDragAngle = Math.round(dragAngle);
                            double tmpAngle = Math.round(angle);
                            if (x == ratio.sector.sectors.workspace.x && y == ratio.sector.sectors.workspace.y) {
                                tmpDragAngle = 0;
                                tmpAngle = 0;
                            }
                            if ((filterControlDistance < radius) && (tmpDragAngle >= tmpAngle - 1 && tmpDragAngle <= tmpAngle + 1)) {
                                float disXLimit = ratio.sector.sectors.workspace.x - (int) (ratio.sector.sectors.workspace.x + ((radius - (winApp.sqrt(winApp.sq(disX) + winApp.sq(disY)))) * Math.cos(angle)));
                                float disYLimit = ratio.sector.sectors.workspace.y - (int) (ratio.sector.sectors.workspace.y + ((radius - (winApp.sqrt(winApp.sq(disX) + winApp.sq(disY)))) * Math.sin(angle)));

                                if ((filterControlDistance <= radius)) {
                                    if (type == 0) {
                                        totalDistance = (radius - (winApp.sqrt(winApp.sq(disX) + winApp.sq(disY))));
                                    }
                                    if (type == 0) {
                                        if (this.totalDistance > ratio.filterControlGreater.totalDistance) {
                                            super.x = (int) (ratio.sector.sectors.workspace.x + (totalDistance * Math.cos(angle)));
                                            super.y = (int) (ratio.sector.sectors.workspace.y + (totalDistance * Math.sin(angle)));
                                            // Determine the real value in the position of the filter control
                                            tmpRealValue = ((totalDistance * (maxminValues[1] - maxminValues[0])) / radius) + maxminValues[0];
                                            realValue = String.valueOf(roundTwoDecimals(tmpRealValue));
                                            lessValue = "-->  " + realValue;
                                            query(ratio.filterControlGreater.tmpRealValue, ratio.filterControlLess.tmpRealValue);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            case MouseEvent.MOUSE_DRAGGED:
                // The user drag the control and the system catch the new coordinates
                if (focusIsWith == this && mouseOver && parent.mouseButton == parent.LEFT && type == 1) {
                    xDrag = winApp.mouseX;
                    yDrag = winApp.mouseY;
                }
                break;
            case MouseEvent.MOUSE_RELEASED:
                // if the mouse has moved then release focus otherwise
                // MOUSE_CLICKED will handle it
                if (focusIsWith == this && type == 1) {
                    looseFocus(null);
                    // Obtain the angle where the user click
                    double dragAngle = 0;

                    // Follow the angle position of the mouse once a label attribute has been dragged
                    dragAngle = Math.atan2((yDrag - ratio.sector.sectors.workspace.y), (xDrag - ratio.sector.sectors.workspace.x));
                    if (dragAngle < 0.00) {
                        dragAngle += 2 * Math.PI;
                    } // Change the range from (PI/-PI) to (2PI)
                    float disX = 0;
                    float disY = 0;
                    disX = x - winApp.mouseX;
                    disY = y - winApp.mouseY;
                    double filterControlDistance = winApp.sqrt(winApp.sq(disX) + winApp.sq(disY));
                    double tmpDragAngle = Math.round(dragAngle);
                    double tmpAngle = Math.round(angle);
                    if (x == ratio.sector.sectors.workspace.x && y == ratio.sector.sectors.workspace.y) {
                        tmpDragAngle = 0;
                        tmpAngle = 0;
                    }
                    // The mouse coordinates must have the same angle as the ratio (+/- 1) and must be inside the ellipse
                    if ((filterControlDistance < radius) && (tmpDragAngle >= tmpAngle - 1 && tmpDragAngle <= tmpAngle + 1)) {
                        float disXLimit = ratio.sector.sectors.workspace.x - (int) (ratio.sector.sectors.workspace.x + ((radius - (winApp.sqrt(winApp.sq(disX) + winApp.sq(disY)))) * Math.cos(angle)));
                        float disYLimit = ratio.sector.sectors.workspace.y - (int) (ratio.sector.sectors.workspace.y + ((radius - (winApp.sqrt(winApp.sq(disX) + winApp.sq(disY)))) * Math.sin(angle)));
                        // If the user click in the anchor extreme, the filter control moves to that position
                        if ((filterControlDistance <= radius)) {
                            if (type == 0) {
                                totalDistance = (radius - (winApp.sqrt(winApp.sq(disX) + winApp.sq(disY))));
                                System.out.println("0:" + totalDistance);
                            } else {
                                totalDistance = (winApp.sqrt(winApp.sq(disX) + winApp.sq(disY)));
                                System.out.println("1:" + totalDistance);
                            }
                            if (type == 1) {
//                                if ((this.totalDistance - ratio.filterControlLess.totalDistance) < 0) {
                                super.x = (int) (ratio.sector.sectors.workspace.x + (totalDistance * Math.cos(angle)));
                                super.y = (int) (ratio.sector.sectors.workspace.y + (totalDistance * Math.sin(angle)));
//                                }
                                // Determine the real value in the position of the filter control
                                tmpRealValue = ((totalDistance * (maxminValues[1] - maxminValues[0])) / radius) + maxminValues[0];
                                realValue = String.valueOf(roundTwoDecimals(tmpRealValue));
                                greaterValue = "-->  " + realValue;
                                query(ratio.filterControlGreater.tmpRealValue, ratio.filterControlLess.tmpRealValue);
                            }
                        }
                    }
                }
//                // Determine the real value in the position of the filter control
//                double tmpRealValue = ((totalDistance * (maxminValues[1] - maxminValues[0])) / radius) + maxminValues[0];
//                realValue = String.valueOf(roundTwoDecimals(tmpRealValue));
//                if (type == 0) {
//                    lessValue = "-->  " + realValue;
//                } else {
//                    greaterValue = "-->  " + realValue;
//                }
                break;

        }
    }

    public void query(double _greater, double _less) {
        AbstractTableModel dataIn;
        dataIn = ratio.sector.sectors.workspace.data.dataInVmd;
        for (int i = 0; i < ratio.sector.sectors.workspace.tempDP.length; i++) {
            if (( Double.parseDouble(dataIn.getValueAt(i, dataIn.findColumn(ratio.sector.name)).toString()) >= _greater && Double.parseDouble(dataIn.getValueAt(i, dataIn.findColumn(ratio.sector.name)).toString()) <= _less)) {
                ratio.sector.sectors.workspace.tempDP[i].setVisible(true);
            } else {
                ratio.sector.sectors.workspace.tempDP[i].setVisible(false);
            }
        }
    }
}
