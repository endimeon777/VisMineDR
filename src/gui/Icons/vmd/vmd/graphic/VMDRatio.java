package gui.Icons.vmd.vmd.graphic;

import java.awt.Point;
import java.awt.event.MouseEvent;
import processing.core.PApplet;
import gui.Icons.vmd.guicomponents.G4P;
import gui.Icons.vmd.guicomponents.GComponent;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.AbstractTableModel;

public class VMDRatio extends GComponent {
    // Attribute axis. Thinking about slice and other addons.

    public VMDSector sector;
    public PApplet parent;
    public VMDFilter filterControlLess,  filterControlGreater;
    public VMDSubLabel scale[];
    float xAnchor, yAnchor, angInit, radius;
    float x, y;
    float angle;
    int w = 10, h = 10, numberIntervals = 13; // numberIntervals is the number of intervals in which the scale is divided
    boolean activeFilter = false;
    String scaleLabels, scalePercent;
    boolean numericScale, categoricalScale, hasCategoricalScale; // Activate, to show the sacale
    // Corelation coeficient
    Map<String, double[]> attributes = new HashMap<String, double[]>();
    boolean activeCorrelationDiagram;
    boolean showCoefCorrelationLabel;
    int posCorrTable;
    String[] distinctValues;
    // If is categorical
    String moda = ""; // central tendency measure
    float modaProportion;

    AbstractTableModel dataIn;
    int indexCol;

    public VMDRatio(PApplet _parent, VMDSector _sector, float _xCenter, float _yCenter, float _angInit, float _radius, Map _attributes) {
        super(_parent, (int) _xCenter, (int) _yCenter);
        parent = _parent;
        sector = _sector;
        this.x = _xCenter;
        this.y = _yCenter;
        angInit = _angInit;
        radius = _radius;
        attributes = _attributes;
        dataIn = sector.sectors.workspace.data.dataInVmd;
        indexCol = dataIn.findColumn(sector.name);
    }

    public void init() {
        xAnchor = (float) (this.x + (radius * Math.cos(angInit)));
        yAnchor = (float) (this.y + (radius * Math.sin(angInit)));
        filterControlLess = new VMDFilter(parent, this, xAnchor, yAnchor, angInit, radius, 0);
        filterControlLess.init();
        filterControlLess.setVisible(false);
        filterControlGreater = new VMDFilter(parent, this, sector.sectors.workspace.x, sector.sectors.workspace.y, angInit, radius, 1);
        filterControlGreater.init();
        filterControlGreater.setVisible(false);
        createEventHandler(winApp, "handleRatioEvents", new Class[]{VMDRatio.class});
        registerAutos_DMPK(true, true, false, false);
        sector.sectors.workspace.addToPanel(this);
    }

    public void draw() {

        if (!visible) {
            return;
        }
        winApp.pushStyle();
        winApp.style(G4P.g4pStyle);
        Point pos = new Point(0, 0);
        calcAbsPosition(pos);
        xAnchor = (float) (pos.x + (radius * Math.cos(angInit)));
        yAnchor = (float) (pos.y + (radius * Math.sin(angInit)));
        winApp.stroke(0, 20);
        winApp.line(pos.x, pos.y, xAnchor, yAnchor);
        if (activeFilter) {
            winApp.fill(255, 51, 0);
        } else {
            winApp.fill(0, 102, 0);
        }
        winApp.noStroke();
        winApp.rectMode(parent.CENTER);
        winApp.rect(xAnchor, yAnchor, w, h);
        winApp.fill(240, 240, 240);
        winApp.ellipse(xAnchor, yAnchor, 4, 4);
        //////////////////////////////////////////
        // Draw the Cofiecient Correlation Diagram
        if (sector.sectors.workspace.viewCoorelationLines.isSelected() && this.activeCorrelationDiagram) {
            for (int i = 0; i < sector.sectors.workspace.data.correlationCoefficientt.getRowCount(); i++) {
                if ((!sector.sectors.workspace.data.correlationCoefficientt.getString(i, "Attributes").equals(sector.name))) {
                    String tmpName = sector.sectors.workspace.data.correlationCoefficientt.getString(i, "Attributes");
                    double[] tmpCoords = attributes.get(tmpName);
                    float tmpxAnchor = (float) (pos.x + (radius * Math.cos(tmpCoords[2])));
                    float tmpyAnchor = (float) (pos.y + (radius * Math.sin(tmpCoords[2])));
                    double coefCorrelation = sector.sectors.workspace.data.correlationCoefficientt.getDouble(i, sector.name);
                    int colorCorrelation = 120 + (int) (130 * coefCorrelation);
                    float lineWeight = (float) (1.5 * (Math.abs(coefCorrelation)));
                    if (coefCorrelation != 0) {
                        winApp.strokeWeight(lineWeight); // the weight proportional to the coeficient correlation
                    } else {
                        winApp.strokeWeight(0); // if there is no correlation, there is no line segment connecting
                    }
                    // the base color will be 120 for R and G, for distinct clear the sign of the correlation
                    if (coefCorrelation < 0) {
                        winApp.stroke(colorCorrelation, 0, 0);
                    } else {
                        winApp.stroke(0, colorCorrelation, 0);
                    }
                    winApp.line(xAnchor, yAnchor, tmpxAnchor, (float) tmpyAnchor);
                    // Show labels coeficient
                    if (showCoefCorrelationLabel) {
                        String tmpCorrelationLabel = String.valueOf(roundTwoDecimals(coefCorrelation));
                        winApp.textFont(localFont);
                        winApp.stroke(0);
                        if (coefCorrelation < 0) {
                            winApp.fill(255, 0, 0);
                        } else {
                            winApp.fill(0, 255, 0);
                        }
                        winApp.text(tmpCorrelationLabel, tmpxAnchor + 12, tmpyAnchor + 12);
                    }
                }
            }
        }
        /////////////////////////
        // Draw the distribution
        if (sector.sectors.workspace.viewValuesDistributionPerAttribute.isSelected()) {
            int intervals = numberIntervals - 2;
            // Draw the general view of the distribution for each variable
            if (scale != null && scale.length != 0 && this.numericScale == true) {
                for (int i = 1; i < intervals; i++) {
                    float lineWeightScale = (float) (0.2 * (Math.abs(scale[i].percentRound)));
                    winApp.strokeWeight(lineWeightScale); // line weight is proportional to the percent of distribution
                    winApp.stroke(255, 0, 0, 80);
                    float tmpScaleXStart = pos.x + (float) ((i * (radius / (numberIntervals - 2))) * Math.cos(angInit));
                    float tmpScaleYStart = pos.y + (float) ((i * (radius / (numberIntervals - 2))) * Math.sin(angInit));
                    float tmpScaleXEnd = pos.x + (float) (((i + 1) * (radius / (numberIntervals - 2))) * Math.cos(angInit));
                    float tmpScaleYEnd = pos.y + (float) (((i + 1) * (radius / (numberIntervals - 2))) * Math.sin(angInit));
                    winApp.line(tmpScaleXStart, tmpScaleYStart, tmpScaleXEnd, tmpScaleYEnd);
                }
            }
            if (this.hasCategoricalScale) {
                for (int i = 0; i < this.distinctValues.length; i++) {
                    float lineWeightScale = (float) (0.2 * (Math.abs(scale[i].percentRound)));
                    winApp.strokeWeight(lineWeightScale); // line weight is proportional to the percent of distribution
                    winApp.fill(255, 0, 0, 80);
                    float tmpScaleXStart = pos.x + (float) (((i + 1) * (radius / distinctValues.length)) * Math.cos(angInit));
                    float tmpScaleYStart = pos.y + (float) (((i + 1) * (radius / distinctValues.length)) * Math.sin(angInit));
//                    float tmpScaleXEnd = pos.x + (float) (((i + 1) * (radius / distinctValues.length)) * Math.cos(angInit));
//                    float tmpScaleYEnd = pos.y + (float) (((i + 1) * (radius / distinctValues.length)) * Math.sin(angInit));
                    float width = (float) (50 * (Math.abs(scale[i].percentRound) / 100));
                    float height = (float) (50 * (Math.abs(scale[i].percentRound) / 100));
                    winApp.ellipse(tmpScaleXStart, tmpScaleYStart, width, height);
                }
            }
        }
        /////////////////////////////////////
        // Draw the central tendency measures
        // the categorical attributes with ellipses, the numerical with rect line all marks at the center of the ratio
        // if is numeric transform the value respect to the ratio for real ubication of the mean
        if (sector.sectors.workspace.viewTendencyMeasures.isSelected()) {
            float tmpCentralTendencyMeasureX = 0;
            float tmpCentralTendencyMeasureY = 0;
            tmpCentralTendencyMeasureX = pos.x + (float) ((radius / 2) * Math.cos(angInit));
            tmpCentralTendencyMeasureY = pos.y + (float) ((radius / 2) * Math.sin(angInit));
            float tmpCentralTendencyMeasureXNumeric = 0;
            float tmpCentralTendencyMeasureYNumeric = 0;
            String textMeasure = "";
            winApp.textSize(11);
            if (this.numericScale) {
                // firs obtain the min max values for scale the position of the real value of the mean respect the ratio
                double[] minMax = sector.sectors.workspace.data.realMinMax.get(sector.name);
                double mean = roundTwoDecimals(sector.sectors.workspace.data.centralTendencyMeasures.get(sector.name));
                double distanceReal = (((mean - minMax[0]) * radius) / (minMax[1] - minMax[0]));
                tmpCentralTendencyMeasureXNumeric = pos.x + (float) ((distanceReal) * Math.cos(angInit));
                tmpCentralTendencyMeasureYNumeric = pos.y + (float) ((distanceReal) * Math.sin(angInit));
                textMeasure = String.valueOf(mean);
                // draw a reference point in the real mean ubication
                winApp.fill(255, 0, 0);
                winApp.ellipse(tmpCentralTendencyMeasureXNumeric, tmpCentralTendencyMeasureYNumeric, 4, 4);
                winApp.strokeWeight(1);
                winApp.stroke(255, 0, 0);
                winApp.line(tmpCentralTendencyMeasureXNumeric, tmpCentralTendencyMeasureYNumeric, tmpCentralTendencyMeasureX, tmpCentralTendencyMeasureY);
            } else {
                textMeasure = moda;
            }
            // dimensions of the ellipse of moda
            float widthModa = (float) (50 * modaProportion);
            float heightModa = (float) (50 * modaProportion);
            //
            winApp.pushMatrix();
            if (xAnchor > sector.sectors.workspace.x) {
                winApp.translate(tmpCentralTendencyMeasureX, tmpCentralTendencyMeasureY);
                winApp.textAlign(winApp.LEFT);
                winApp.rotate(angInit);
                winApp.fill(0);
                winApp.text(textMeasure, 2, 2);
            } else {
                winApp.translate(tmpCentralTendencyMeasureX, tmpCentralTendencyMeasureY);
                winApp.textAlign(winApp.RIGHT);
                winApp.rotate((float) (Math.PI) + angInit);
                winApp.fill(0);
                winApp.text(textMeasure, -1, -1);
            }
            winApp.popMatrix();
            winApp.pushMatrix();
            if (this.numericScale) {
                if (xAnchor > sector.sectors.workspace.x) {
                    winApp.translate(tmpCentralTendencyMeasureX, tmpCentralTendencyMeasureY);
                    winApp.textAlign(winApp.LEFT);
                    winApp.rotate((float) (angInit + (Math.PI / 2)));
                    winApp.fill(255, 0, 0);
                    winApp.rect(2, 2, 15, 4);
                } else {
                    winApp.translate(tmpCentralTendencyMeasureX, tmpCentralTendencyMeasureY);
                    winApp.textAlign(winApp.RIGHT);
                    winApp.rotate((float) (Math.PI + (Math.PI / 2)) + angInit);
                    winApp.fill(255, 0, 0);
                    winApp.rect(-1, -1, 15, 4);
                }
            } else {
                if (xAnchor > sector.sectors.workspace.x) {
                    winApp.translate(tmpCentralTendencyMeasureX, tmpCentralTendencyMeasureY);
                    winApp.textAlign(winApp.LEFT);
                    winApp.rotate((float) (angInit + (Math.PI / 2)));
                    winApp.fill(255, 0, 0, 60);
                    winApp.ellipse(2, 2, widthModa, heightModa);
                } else {
                    winApp.translate(tmpCentralTendencyMeasureX, tmpCentralTendencyMeasureY);
                    winApp.textAlign(winApp.RIGHT);
                    winApp.rotate((float) (Math.PI + (Math.PI / 2)) + angInit);
                    winApp.fill(255, 0, 0, 60);
                    winApp.ellipse(-1, -1, widthModa, heightModa);
                }
            }
            winApp.popMatrix();
        }
        winApp.popStyle();
    }

    public void activeCorrelation(boolean _value) {
        activeCorrelationDiagram = _value;
    }

    public void activateNumericScale(boolean value) {
        numericScale = value;
    }

    public void activateCategoricalScale(boolean value) {
        categoricalScale = value;
    }

    double roundTwoDecimals(double d) {
//        DecimalFormat twoDForm = new DecimalFormat("##.##");
//        return Double.valueOf(twoDForm.format(d)); essssssssss pooooooooorrrrrrrrr aaaaaaqqquuuuuuiiiiiiii
        return Double.valueOf(d);
    }

    public void attributeScale() {
        scale = new VMDSubLabel[numberIntervals];
        double[] minMax = sector.sectors.workspace.data.realMinMax.get(sector.name);
        double step = (minMax[1] - minMax[0]) / (numberIntervals - 2);
        double tempObj = 0, tmpLabelValue;
        float textX;
        float textY;
        int rows = sector.sectors.workspace.data.ROWS;
        float newAngle;
        double inf = 0;
        double sup = 0;
        double percentLast = 0;
        double maxFrequency = 0;  

        // define a number of intervals and make an subLabel for each
        for (int i = 1; i < (numberIntervals - 2); i++) {
            tmpLabelValue = roundTwoDecimals(minMax[0] + (step * i));
            inf = minMax[0] + (step * (i - 1));
            sup = minMax[0] + (step * i);
            System.out.println(minMax[0] + "::" + minMax[1] + "::" + inf + "::" + sup + "::" + step);
            double tmpTotal = 0;
            double percent = 0;
            double colorDegree = 0;       

            for (int r = 0; r < sector.sectors.workspace.data.ROWS; r++) {
                if (dataIn.getColumnClass(indexCol).equals(int.class)) {
                    tempObj = Integer.parseInt(dataIn.getValueAt(r, indexCol).toString());
                }
                if (dataIn.getColumnClass(indexCol).equals(float.class)) {
                    tempObj = Float.parseFloat(dataIn.getValueAt(r, indexCol).toString());
                }
                if (dataIn.getColumnClass(indexCol).equals(double.class)) {
                    tempObj = Double.parseDouble(dataIn.getValueAt(r, indexCol).toString());
                }
                if (dataIn.getColumnClass(indexCol).equals(long.class)) {
                    tempObj = Long.parseLong(dataIn.getValueAt(r, indexCol).toString());
                }
                if (dataIn.getColumnClass(indexCol).equals(short.class)) {
                    tempObj = Short.parseShort(dataIn.getValueAt(r, indexCol).toString());
                }
                if (tempObj >= inf && tempObj < sup) {
                    tmpTotal++;
                }
            }
            if (tmpTotal > maxFrequency) {
                maxFrequency = tmpTotal;
            }
            percent = (tmpTotal / rows) * 100;
            colorDegree = (tmpTotal / rows);
            double percentRound = roundTwoDecimals(percent);
            scalePercent = String.valueOf(roundTwoDecimals(percent));
            if (String.valueOf(Math.round(percent)).length() == 1) {
                scalePercent = "0" + String.valueOf(roundTwoDecimals(percent));
            }
            if (scalePercent.length() == 4) {
                scalePercent = scalePercent + "0";
            }
            newAngle = angInit + (float) (Math.PI / 2);

            scaleLabels = scalePercent + " % -->" + String.valueOf(tmpLabelValue);
            textX = x + (float) ((i * (radius / (numberIntervals - 2))) * Math.cos(angInit)) - (float) ((scaleLabels.length() * 6) * Math.cos(newAngle));
            textY = y + (float) ((i * (radius / (numberIntervals - 2))) * Math.sin(angInit)) - (float) ((scaleLabels.length() * 6) * Math.sin(newAngle));
            scale[i] = new VMDSubLabel(parent, this, textX, textY, scaleLabels, colorDegree, angInit, percentRound);
            scale[i].init();
            scale[i].setVisible(false);
        }
        // Put the labels for the max and min value
        newAngle = angInit + (float) (Math.PI / 2);
        scaleLabels = " 00.00 % -->" + String.valueOf(minMax[0]);
        textX = x - (float) ((scaleLabels.length() * 6) * Math.cos(newAngle));
        textY = y - (float) ((scaleLabels.length() * 6) * Math.sin(newAngle));
        scale[numberIntervals - 1] = new VMDSubLabel(parent, this, textX, textY, scaleLabels, 0, angInit, 0);
        scale[numberIntervals - 1].init();
        scale[numberIntervals - 1].setVisible(false);

        // Put the labels for the max limit
        double totalMaxInterval = 0;
        inf = minMax[1] - step;
        sup = minMax[1];

        for (int r = 0; r < sector.sectors.workspace.data.ROWS; r++) {
            if (dataIn.getColumnClass(indexCol).equals(int.class)) {
                tempObj = Integer.parseInt(dataIn.getValueAt(r, indexCol).toString());
            }
            if (dataIn.getColumnClass(indexCol).equals(float.class)) {
                tempObj = Float.parseFloat(dataIn.getValueAt(r, indexCol).toString());
            }
            if (dataIn.getColumnClass(indexCol).equals(double.class)) {
                tempObj = Double.parseDouble(dataIn.getValueAt(r, indexCol).toString());
            }
            if (dataIn.getColumnClass(indexCol).equals(long.class)) {
                tempObj = Long.parseLong(dataIn.getValueAt(r, indexCol).toString());
            }
            if (dataIn.getColumnClass(indexCol).equals(short.class)) {
                tempObj = Short.parseShort(dataIn.getValueAt(r, indexCol).toString());
            }
            if (tempObj >= inf && tempObj <= sup) {
                totalMaxInterval++;
            }
        }
        if (totalMaxInterval > maxFrequency) {
            maxFrequency = totalMaxInterval;
        }
        percentLast = ((totalMaxInterval / rows) * 100);
        double colorDegree = (totalMaxInterval / rows);
        double percentRound = roundTwoDecimals(percentLast);
        String scalePercentLast = String.valueOf(roundTwoDecimals(percentLast));
        System.out.println(percentLast + ":" + totalMaxInterval);
        scaleLabels = scalePercentLast + " % -->" + String.valueOf(minMax[1]);
        newAngle = angInit + (float) (Math.PI / 2);
        textX = x + (float) ((radius) * Math.cos(angInit)) - (float) ((scaleLabels.length() * 6) * Math.cos(newAngle));
        textY = y + (float) ((radius) * Math.sin(angInit)) - (float) ((scaleLabels.length() * 6) * Math.sin(newAngle));
        scale[numberIntervals - 2] = new VMDSubLabel(parent, this, textX, textY, scaleLabels, colorDegree, angInit, percentRound);
        scale[numberIntervals - 2].init();
        scale[numberIntervals - 2].setVisible(false);

        // Define the color gradient
        for (int i = 1; i < numberIntervals; i++) {
            scale[i].setMaxFrequency(maxFrequency / rows);
        }
    }

    public void attributeScaleCategorical() {

        distinctValues = sector.sectors.workspace.data.distinctCategoricalValues.get(sector.name);
        if (distinctValues.length <= 20) { // the filter apply only for short class values lists
            hasCategoricalScale = true;
            scale = new VMDSubLabel[distinctValues.length];
            double newAngle = angInit + (float) (Math.PI / 2);
            String tempObj;
            double maxFrequency = 0;
            for (int i = 0; i < distinctValues.length; i++) {
                double percent = 0;
                double colorDegree = 0;
                double totalCount = 0;
                for (int r = 0; r < sector.sectors.workspace.data.ROWS; r++) {
                    tempObj = dataIn.getValueAt(r, indexCol).toString();
                    if (tempObj.equals(distinctValues[i])) {
                        totalCount++;
                    }
                }
                if (totalCount > maxFrequency) {
                    maxFrequency = totalCount;
                    moda = distinctValues[i];
                    modaProportion = (float)(totalCount / sector.sectors.workspace.data.ROWS);
                }
//                // identify the moda, only is there one value that is the most repeated
//                if (totalCount == maxFrequency) {
//                    moda = "";
//                }
                percent = (totalCount / sector.sectors.workspace.data.ROWS) * 100;

//               jjjjjjjjjjjjjjjjjjjjjjjjjjjjjj    aqui esta el error
                colorDegree = (totalCount / sector.sectors.workspace.data.ROWS);
                scalePercent = String.valueOf(roundTwoDecimals(percent));
                double percentRound = roundTwoDecimals(percent);
                String scaleLabels = " " + distinctValues[i] + " --> " + scalePercent + "%";
                float textX = x + (float) (((i + 1) * (radius / (distinctValues.length))) * Math.cos(angInit)) + (float) (5 * Math.cos(newAngle));
                float textY = y + (float) (((i + 1) * (radius / (distinctValues.length))) * Math.sin(angInit)) + (float) (5 * Math.sin(newAngle));
                scale[i] = new VMDSubLabel(parent, this, textX, textY, scaleLabels, colorDegree, angInit, percentRound);
                scale[i].init();
                scale[i].activeBox(true);
                scale[i].selectBox(true);
                scale[i].setVisible(false);
            }
            for (int i = 1; i < distinctValues.length; i++) {
                scale[i].setMaxFrequency(maxFrequency / sector.sectors.workspace.data.ROWS);
            }
        }
    }

    @Override
    public boolean isOver(int ax, int ay) {
        Point p = new Point(0, 0);
        calcAbsPosition(p);
        float xAnchorTmp = (float) (p.x + (radius * Math.cos(angInit)));
        float yAnchorTmp = (float) (p.y + (radius * Math.sin(angInit)));
        if ((ax >= xAnchorTmp - w / 2 && ax <= xAnchorTmp + w / 2 && ay >= yAnchorTmp - h / 2 && ay <= yAnchorTmp + h / 2) && !sector.sectors.workspace.graphicPanel.isCollapsed() && visible) {
            return true;
        } else {
            return false;
        }
    }

    public void setScaleVisible() {
        if (numericScale) {
            for (int i = 1; i < scale.length; i++) {
                scale[i].setVisible(true);
            }
        } else {
            if (hasCategoricalScale) {
                for (int i = 0; i < scale.length; i++) {
                    scale[i].setVisible(true);
                }
            }
        }
    }

    public void setScaleInVisible() {
        if (numericScale) {
            for (int i = 1; i < scale.length; i++) {
                scale[i].setVisible(false);
            }
        } else {
            if (hasCategoricalScale) {
                for (int i = 0; i < scale.length; i++) {
                    scale[i].setVisible(false);
                }
            }
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
                // Cant show the scale bars if the correlation is enabled
                if (focusIsWith != this && mouseOver && !filterControlGreater.isVisible() && !filterControlLess.isVisible() && !sector.sectors.workspace.viewCoorelationLines.isSelected()) {
                    takeFocus();
                    if (numericScale) {
                        sector.sectors.setFilterScaleInVisible();
                        // Active this code for disapear the particles when the filter
//                        for (int i = 0; i < sector.sectors.workspace.tempDP.length; i++) {
//                            sector.sectors.workspace.tempDP[i].setVisible(false);
//                        }
                        filterControlGreater.setVisible(true);
                        filterControlLess.setVisible(true);
                    }

                    if (categoricalScale) {
                        sector.sectors.setFilterScaleInVisible();
                        // Active this code for disapear the particles when the filter
//                        for (int i = 0; i < sector.sectors.workspace.tempDP.length; i++) {
//                            sector.sectors.workspace.tempDP[i].setVisible(false);
//                        }
                        filterControlGreater.setVisible(false);
                        filterControlLess.setVisible(false);
                    }
                    setScaleVisible();
                    sector.sectors.workspace.pointsAlpha(true);
                }
                // While is pressed show coeficient correlation value label
                if (focusIsWith != this && showCoefCorrelationLabel == false && mouseOver && !filterControlGreater.isVisible() && !filterControlLess.isVisible() && sector.sectors.workspace.viewCoorelationLines.isSelected()) {
                    showCoefCorrelationLabel = true;
                    takeFocus();
                    // hide temporaly the others correlations
                    for (int i = 0; i < sector.sectors.sector.length; i++) {
                        if (!sector.sectors.sector[i].ratio.showCoefCorrelationLabel) {
                            sector.sectors.sector[i].ratio.activeCorrelation(false);
                        }
                    }
                }
                break;
            case MouseEvent.MOUSE_RELEASED:
                if (focusIsWith == this && showCoefCorrelationLabel == true && mouseOver && !filterControlGreater.isVisible() && !filterControlLess.isVisible() && sector.sectors.workspace.viewCoorelationLines.isSelected()) {
                    looseFocus(null);
                    // show all the correlations
                    for (int i = 0; i < sector.sectors.sector.length; i++) {
                        if (sector.sectors.sector[i].ratio.numericScale) {
                            sector.sectors.sector[i].ratio.activeCorrelation(true);
                        }
                    }
                    showCoefCorrelationLabel = false;
                }
                break;
        }
    }
}
