package gui.Icons.vmd.vmd.graphic;

////import vmd.graphic.VMDSectors;
import gui.Icons.vmd.guicomponents.GComponent;
import gui.Icons.vmd.guicomponents.G4P;
////import guicomponents.GCScheme;
////import java.awt.Point;
////import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import prefuse.util.ColorLib;
import gui.Icons.vmd.guicomponents.GWinData;
import processing.core.PApplet;
////import processing.core.PFont;
////import guicomponents.GButton;
import gui.Icons.vmd.guicomponents.GCheckbox;
import gui.Icons.vmd.guicomponents.GCombo;
import gui.Icons.vmd.guicomponents.GLabel;
import gui.Icons.vmd.guicomponents.GPanel;
////import processing.core.PImage;

// Classifier
import gui.KnowledgeFlow.Icon;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import prefuse.data.Table;

public class VMDWorkspace {

    // con VMDWorkspace construimos todos los elementos
    public PApplet parent;
    //public GPanel graphicPanel;
    public GWinData data;
    public GPanel graphicPanel;
    // Classifier data
    public Table classifier;
    public Table classifierFinal;
    // Data combo
    private GLabel filesLabel;
    public GCombo files;
    VMDFileList fileList;
    // Check box for view overlap points
    public GCheckbox viewOverlap;
    // Check box for view all polygons at once
    public GCheckbox viewPolygon;
    // Check box for view all correlations at once
    public GCheckbox viewCoorelationLines;
    // Check box for view all value distribution per attribute
    public GCheckbox viewValuesDistributionPerAttribute;
    // Check box for view all tendency measures
    public GCheckbox viewTendencyMeasures;
    // Zoom space
    VMDZoomWorkspace zoomWorkspace;
    // Stored data
    VMDPoint tempDP[]; // Points array
    VMDZoomPoints points[]; // Zoom Points
    // Sectors
    public VMDSectors baseSectors,  referenceSectors;
    // Applet size
    public float width,  height,  radius;
    // Representation
    float x, y;// Center of the representation
    Map<String, double[]> Attributes = new HashMap<String, double[]>();// Storing attributes values <name, {x,y}>
    String[] tempNames; // Storing attributes names
    int numAttributes; // Number of columns
    // Order of attributes in circunference
    int[] orderAttributes;
    // Max and min distances (center of mass): scale
    public float maxDistMinRadius = -999999;
    public float maxDistMaxRadius = 0;
    public float scaleRatioX = 0;
    public float scaleRatioY = 0;
    public float maxDistX = 0;
    public float maxDistY = 0;
    public float minDistX = 0;
    public float minDistY = 0;
    public float maxDistAng = 0;
    // Class render
    String classAttribute;
    // Drag Circle
    float xInitDrag, yInitDrag;
    // Layers
    int countLayers;
    Map<Integer, int[]> matrixLayer = new HashMap<Integer, int[]>(); 
    VMDLayerManager lagerManager;
    // Correlation Diagram
    VMDCorrelationRatio[] correlationRatio;
    // Classifier Diagram
    VMDSectorClassifier[] classifierSector;
    // Ellipse Menu
    VMDEllipseMenu ellipseMenu;

    AbstractTableModel dataInVmd;

    Icon auxVmdIcon;

    //////////////////////////////////////////////////
    public VMDWorkspace(PApplet _parent, int _width, int _height, float _x, float _y, int _radius, AbstractTableModel dataIn, Icon _auxVmdIcon) throws FileNotFoundException, Exception {
        this.auxVmdIcon = _auxVmdIcon;
        this.parent = _parent;
        this.width = _width;
        this.height = _height;
        x = _x;
        y = _y;
        this.radius = _radius / 2;

        this.dataInVmd = dataIn;

        // Cargando los datos
        ////////        String filePath = "gui/Icons/vmd/data/datos_banderas.txt";// ruta del archivo  //datos_banderas.txt  tennis.txt
        loadFile();
        if (this.graphicPanel != null) {
            this.graphicPanel = null;
        }
        pre();
    }

    public void pre() throws FileNotFoundException, Exception {
        data.structure(); // estructura de datos, valores estadisticos, printlog
        // Inicializate the order atributes array with the dimensions of the headers columns
        // assign correlating order to the array
        orderAttributes = new int[data.headers.size()];
        for (int i = 0; i < data.headers.size(); i++) {
            orderAttributes[i] = i;
         //  System.out.println("pos" + orderAttributes[i] + " indice: " + i);
        }
        // Inicializate the array of points
        tempDP = new VMDPoint[data.ROWS];
        points = new VMDZoomPoints[data.ROWS];
        createPanels();
        init();
        initCorrelation(); // Correlation Diagram
        auxVmdIcon.stopAnimation();
    }

    public void showCorrelationDiagram(boolean _value) {
        for (int i = 0; i < correlationRatio.length; i++) {
            correlationRatio[i].labels.setVisible(_value);
            correlationRatio[i].setVisible(_value);
        }
    }

    public void showClassifier(boolean _value) {
        for (int i = 0; i < this.classifierSector.length; i++) {
            classifierSector[i].setVisible(_value);
        }
    }

    public void loadFile() { //carga el archivo en forma de tabla prefuse con la clase GWinData
        data = new GWinData(dataInVmd);
    //data.loadFile(_fileName);
    }

    public void createPanels() { // pone los check boxs y titulos
        graphicPanel = new GPanel(parent, "Tariy Visualization Multi Dimensional", 0, 0, (int) (width * 2) - 200, (int) height - 20); //paneles internos internos
        G4P.setMouseOverEnabled(true); //Enable mouse over image changes
        graphicPanel.setCollapsed(false); // Colapse at start
        // combo box for select the data file
//        fileList = new VMDFileList("gui/Icons/vmd/data/data/");  //"gui/Icons/vmd/data/data"
//        files = new GCombo(parent, fileList.getFileList(), 30, 75, 10, 120);
        //files.setSelected(1);
        graphicPanel.add(files);
        // Files label
//        filesLabel = new GLabel(parent, "Seleccionar archivo", 0, 10, 78, 17);
//        filesLabel.setAlpha(0);
//        filesLabel.setBorder(0);
//        filesLabel.setOpaque(true);
//        graphicPanel.add(filesLabel);
        viewOverlap = new GCheckbox(parent, "Overlap", 5, 450, 20);
        viewOverlap.setAlpha(200);
        graphicPanel.add(viewOverlap);
        viewPolygon = new GCheckbox(parent, "Polygons", 5, 470, 20);
        viewPolygon.setAlpha(200);
        graphicPanel.add(viewPolygon);
        viewCoorelationLines = new GCheckbox(parent, "Correlation", 5, 490, 20);
        graphicPanel.add(viewCoorelationLines);
        viewValuesDistributionPerAttribute = new GCheckbox(parent, "Value Distribution", 5, 510, 20);
        graphicPanel.add(viewValuesDistributionPerAttribute);
        viewTendencyMeasures = new GCheckbox(parent, "Central Tendency Measures", 5, 530, 20);
        graphicPanel.add(viewTendencyMeasures);
        zoomWorkspace = new VMDZoomWorkspace(parent, this, (width / 4) + width, height / 4, (int) (height / 4) - 120);
        zoomWorkspace.init();
        zoomWorkspace.setVisible(false);
        graphicPanel.setAlpha(100); // color de titulo
        G4P.setMouseOverEnabled(true); //Enable mouse over image changes
    }

    float getRadius() {
        return radius;
    }

    float[] getCenter() {
        float[] center = {x, y};
        return center;
    }

    public void hideAll() {
        this.zoomWorkspace.setVisible(false);
        for (int r = 0; r < data.ROWS; r++) {
            tempDP[r].setVisible(false);
        }
        for (int c = 0; c < data.headers.size(); c++) {
            baseSectors.colapseMenues();
            boolean categorical = baseSectors.sector[c].label.isCategorical;
            baseSectors.sector[c].ratio.setScaleInVisible();
            if (!categorical) {
                baseSectors.sector[c].ratio.filterControlGreater.setVisible(false);
                baseSectors.sector[c].ratio.filterControlLess.setVisible(false);
            }
            baseSectors.sector[c].ratio.setVisible(false);
            baseSectors.sector[c].label.setVisible(false);
            baseSectors.sector[c].setVisible(false);
        }
    }

    public void showAll() {
        for (int c = 0; c < data.headers.size(); c++) {
            baseSectors.sector[c].ratio.setVisible(true);
            baseSectors.sector[c].label.setVisible(true);
            baseSectors.sector[c].setVisible(true);
        }
        // Repaint only the points that has the active layer
        int tmpLayers = countLayers + 1;
        for (int i = 1; i < tmpLayers; i++) {
            for (int r = 0; r < data.ROWS; r++) {
                if (tempDP[r].layer == i && this.lagerManager.layers[i - 1].hide == false) {
                    tempDP[r].setVisible(true);
                }
            }
        }
    }

    public void reMake() {
        for (int r = 0; r < data.ROWS; r++) {
            graphicPanel.remove(tempDP[r].polygon);
            graphicPanel.remove(tempDP[r]);
        }
        for (int c = 0; c < data.headers.size(); c++) {
            baseSectors.colapseMenues();
            boolean categorical = baseSectors.sector[c].label.isCategorical;
            if (categorical) {
                int items = baseSectors.sector[c].label.classMenu.itemMenuClass.length;
                for (int r = 0; r < items; r++) {
                    graphicPanel.remove(baseSectors.sector[c].label.classMenu.itemMenuClass[r]);
                }
                graphicPanel.remove(baseSectors.sector[c].label.classMenu);
            } else {
                graphicPanel.remove(baseSectors.sector[c].ratio.filterControlLess);
                graphicPanel.remove(baseSectors.sector[c].ratio.filterControlGreater);
            }
            graphicPanel.remove(baseSectors.sector[c].ratio);
            graphicPanel.remove(baseSectors.sector[c].label);
            baseSectors.sector[c].setVisible(false);
            baseSectors.zoom.setVisible(false);
            graphicPanel.remove(baseSectors.zoom);
            graphicPanel.remove(baseSectors.sector[c]);
            graphicPanel.remove(baseSectors);
        }
    }

    public void applyRendererInformation() {
        // Assign the color in function of the class attribute wich is the categorical one that the user click
        for (int r = 0; r < data.ROWS; r++) {
            if (classAttribute != null) {
                for (int m = 0; m < data.categoricalValuesRendererInformation.getRowCount(); m++) {
                    if ((data.categoricalValuesRendererInformation.getString(m, "Attribute").equals(classAttribute)) && (data.categoricalValuesRendererInformation.getString(m, "Value").equals(data.dataInVmd.getValueAt(r, data.dataInVmd.findColumn(classAttribute)).toString()))) {
                        tempDP[r].setColor(data.categoricalValuesRendererInformation.getInt(m, "Color"));
                    //tempDP[r].shapeRender = (data.categoricalValuesRendererInformation.getString(m, "Shape"));
                    }
                }
            }
        }
    }

    public void classColored() {
        for (int c = 0; c < data.headers.size(); c++) {
            if (baseSectors.sector[c].label.name.equals(classAttribute)) {
                baseSectors.sector[c].label.setClassAttribute(true);
            }
        }
    }

    // The zoom points are created at the begining, and if the zoom is active the particles are being visible or invisible
    public void createZoomPoints() {
        for (int i = 0; i < tempDP.length; i++) {
            float disX = ((tempDP[i].x - x) + zoomWorkspace.xZoomWorkspace);
            float disY = ((tempDP[i].y - y) + zoomWorkspace.yZoomWorkspace);
            points[i] = new VMDZoomPoints(parent, this, disX, disY, tempDP[i].color, i);
            points[i].init();
            points[i].setVisible(false);
        }
        baseSectors.zoom.identifyPoitsZoomed();
    }

    public void init() {  // posiciones de puntos angulo y nombre de variables categoricas
        double ang = 0, angAnt = 0;
        numAttributes = data.headers.size();
        // loading font
        //font = parent.loadFont("writing.vlw");
        // Distribute the angles for the sectors
        if (numAttributes > 0) {

            for (int i = 0; i < numAttributes; i++) {
                double attX = x;
                double attY = y;
                angAnt = ang;
                attX += radius * Math.cos(ang);
                attY += radius * Math.sin(ang);
                ang += (Math.PI * 2) / numAttributes;
                double[] tempVal = {attX, attY, angAnt, ang};
                Attributes.put(data.headers.get(orderAttributes[i]), tempVal);
            }
            // Capturing names
            tempNames = new String[Attributes.size()];
            // VERIFICAR SI ES NECESARIA ESTA INSTRUCCIÓN
            //Attributes.keySet().toArray(tempNames);
            for (int i = 0; i < numAttributes; i++) {
                tempNames[i] = data.headers.get(orderAttributes[i]);
            }
        }
        // Calculate Center of Mass
        calcOfMass(0);
        if (maxDistMinRadius > 10) {
            calcOfMass(maxDistMinRadius);
        }
        // Begin create the representation
        createSectors();
        createZoomPoints();
//        if (classAttribute == null) {
//            renderDefaultClass();
//        }
//        applyRendererInformation();
        createLayers();
        //lagerManager = new VMDLayerManager(parent, this, x - radius, height - 30);
        lagerManager = new VMDLayerManager(parent, this, x - radius, y);
        lagerManager.init();
        lagerManager.setVisible(false);
    }

    public void initCorrelation() {
        double ang = 0, angAnt = 0;
        int numAttributesCorr = data.correlationCoefficientt.getRowCount();
        Map<String, double[]> AttributesCorr = new HashMap<String, double[]>();
        float xCorrelationGraph = (width / 3) + width;
        float yCorrelationGraph = width / 2;
        float ratioCorrelationGraph = (height - 300) / 2;
        if (numAttributesCorr > 0) {
            for (int i = 0; i < numAttributesCorr; i++) {
                double attX = xCorrelationGraph;
                double attY = yCorrelationGraph;
                angAnt = ang;
                attX += ratioCorrelationGraph * Math.cos(ang);
                attY += ratioCorrelationGraph * Math.sin(ang);
                ang += (Math.PI * 2) / numAttributesCorr;
                double[] tempVal = {attX, attY, angAnt, ang};
                AttributesCorr.put(data.correlationCoefficientt.getString(i, "Attributes"), tempVal);
            }
        }
        // Begin create the representation
        correlationRatio = new VMDCorrelationRatio[numAttributesCorr];
        for (int i = 0; i < numAttributesCorr; i++) {
            String tmpAttrib = data.correlationCoefficientt.getString(i, "Attributes");
            double[] values = AttributesCorr.get(tmpAttrib);
            correlationRatio[i] = new VMDCorrelationRatio(parent, this, tmpAttrib, xCorrelationGraph, yCorrelationGraph, (float) values[2], (float) values[3], numAttributesCorr, ratioCorrelationGraph, (i + 1));
            correlationRatio[i].init();
        }
    }

    public void renderDefaultClass() {
        classAttribute = data.categoricalValuesRendererInformation.getString(0, "Attribute");
    }

    public void createSectors() {
        baseSectors = new VMDSectors(parent, this, Attributes, tempNames, x, y, radius);
        baseSectors.init();
        addToPanel(baseSectors);
    }

    public void pointsAlpha(boolean _value) {
        for (int i = 0; i < tempDP.length; i++) {
            tempDP[i].setAlpha(_value);
        }
    }

    public void addToPanel(GComponent component) {
        graphicPanel.add(component);
    }

    public void generateOrderArray(int _posAct, int _posNew) {
        int tempValue = 0, tempPosAct = 0, overflow = orderAttributes[numAttributes - 1], kposAct = 0, kposNew = 0, direction = 0, f = 0, g = 0, n = 0; // direction 1: rigth, 2: left
        kposAct = _posAct;
        kposNew = _posNew;
        tempValue = orderAttributes[_posAct];

        if (kposAct < kposNew) {
            direction = 1;
        } else {
            direction = 2;
        }

        if (((orderAttributes[0] == _posNew) && (orderAttributes[numAttributes - 1] == _posAct))) {
            tempPosAct = orderAttributes[0];
            orderAttributes[0] = orderAttributes[numAttributes - 1];
            orderAttributes[numAttributes - 1] = tempPosAct;
        } else if ((orderAttributes[numAttributes - 1] == _posNew) && (orderAttributes[0] == _posAct)) {
            tempPosAct = orderAttributes[numAttributes - 1];
            orderAttributes[numAttributes - 1] = orderAttributes[0];
            orderAttributes[0] = tempPosAct;
        } else {
            if (direction == 1) {
                for (g = numAttributes - 1; g > kposNew; g--) {
                    orderAttributes[g] = orderAttributes[g - 1];
                }

                for (f = kposAct; f > 0; f--) {
                    orderAttributes[f] = orderAttributes[f - 1];
                }

                orderAttributes[kposNew] = tempValue;
                orderAttributes[0] = overflow;
            }

            if (direction == 2) {
                for (n = kposAct; n > kposNew; n--) {
                    orderAttributes[n] = orderAttributes[n - 1];
                }

                orderAttributes[kposNew] = tempValue;
            }
        }
    }
    // Compute the center of mass of each particle and set the value to the private variable "xCenterMass" and
    // "yCenterMass" of each point representation.

    public void calcOfMass(float _maxDistX) { // calcula el centro y distancias minimas y maximas
        // Calculating each center of mass, min distance and max distance.
        for (int r = 0; r < data.ROWS; r++) {
            float sumValues = 0;
            float tmpX = 0;
            float tmpY = 0;
            float comx;
            float comy;
            float ang = 0;
            Map<String, double[]> individualPoligon = new HashMap<String, double[]>();

            // First column avoided: center of mass. Processing attributes
            for (int c = 0; c < data.headers.size(); c++) {

                float xAtt = x;
                float yAtt = y;
                float xAtt2 = x;
                float yAtt2 = y;
                float attValue = 0;
                float attValue2 = 0;
                if (_maxDistX == 0) {
                    // Always divided by 8 (spaces). Added 4 in normalization:
                    // gNorm inside DataSet) => -4 [1] -3 [2] -2 [3] -1 [4] 0
                    // [5] 1 [6] 2 [7] 3 [8] 4
                    attValue = (float) (data.normalizedt.getDouble(r, tempNames[c]) * radius) / 8;
                } else {

                    attValue = (float) (data.normalizedt.getDouble(r, tempNames[c]) * (radius * scaleRatioX) / 8); //(workspace.gpanel.radius - _distRef);
                }
                attValue2 = (float) (data.normalizedt.getDouble(r, tempNames[c]) * radius) / data.maxValues.get(tempNames[c]).floatValue();
                // Moving attribute, moving angle, moving coordinates
                xAtt += attValue * Math.cos(ang);
                yAtt += attValue * Math.sin(ang);
                xAtt2 += attValue2 * Math.cos(ang);
                yAtt2 += attValue2 * Math.sin(ang);
                //  Get the sign of the attribite value
                double sign = 0;
                if (data.dataInVmd.getColumnClass(c).equals(String.class) || data.dataInVmd.getColumnClass(c).equals(Object.class)) {
                    sign = 0;
                } else {
                    double valueSign = Double.parseDouble(data.dataInVmd.getValueAt(r, data.dataInVmd.findColumn(tempNames[c])).toString());
                    if (valueSign > 0) {
                        sign = 1;
                    } else {
                        sign = -1;
                    }

                }
                // Storage components of the values for each variable, for perform the poligon
                double[] tmpValues = {attValue2, ang, sign};
                individualPoligon.put(tempNames[c], tmpValues);
                ang += (Math.PI * 2) / numAttributes;
                maxDistAng = ang;
                // Storing values for Center of Mass
                tmpX += xAtt * attValue; // x coord * mass
                tmpY += yAtt * attValue; // y coord * mass

                sumValues += attValue; // sum of all masses
            }

            // Center of Mass calculus
            comx = tmpX / sumValues;
            comy = tmpY / sumValues;
            // Create Points
            if (_maxDistX != 0) {
                tempDP[r] = new VMDPoint(parent, this, comx, comy, individualPoligon, r);
                tempDP[r].init();
            }
            if (_maxDistX == 0) {
                // Operations to calculate maxDist and minDist
                float tempDistToCenter = calcDistance(x, y, comx, comy);
                float tempDistRad = radius - tempDistToCenter;
                if (maxDistMinRadius < tempDistToCenter && maxDistMinRadius < radius) {
                    System.out.println("Max distance changed at row [" + r + "]");
                    maxDistMinRadius = Math.max(maxDistMinRadius, tempDistToCenter);
                }

                maxDistMaxRadius = radius - 10;
                maxDistX = (float) Math.cos(maxDistAng) * maxDistMaxRadius;
                maxDistY = (float) Math.sin(maxDistAng) * maxDistMaxRadius;
                minDistX = (float) Math.cos(maxDistAng) * maxDistMinRadius;
                minDistY = (float) Math.sin(maxDistAng) * maxDistMinRadius;
            }

        }
        System.out.println("Max comass distance = " + maxDistMinRadius + " :: Radius = " + maxDistMaxRadius + " :: scale factor: " + scaleRatioX);
    }

    // This function detect the number of particles that has the same C.M, in order to have a kind of gradient distribution
    public void overLap() {
        int maxOverlap = 0;
        Map<Integer, Integer> colorScale = new HashMap<Integer, Integer>();
        // iterate the points array and if has overlap increase the counter, store the maximum quantity of
        // overlap in one point, for discretize the scale of colors.
        for (int i = 0; i < data.ROWS; i++) {
            for (int j = 0; j < data.ROWS; j++) {
                if ((this.tempDP[j].y == this.tempDP[i].y) && (this.tempDP[j].x == this.tempDP[i].x) && (this.tempDP[j].hasOverlap == false)) {
                    this.tempDP[i].overlapCounter++;
                    this.tempDP[j].hasOverlap = true;
                    if (this.tempDP[i].overlapCounter == 1) {
                        this.tempDP[i].overlapLabels += " " + j;
                    } else {
                        this.tempDP[i].overlapLabels += " - " + j;
                    }
                }
            }
            if (this.tempDP[i].overlapCounter > maxOverlap) {
                maxOverlap = this.tempDP[i].overlapCounter;
            }
        }
        int step = 155 / (maxOverlap);
        // Crate the color array scale in function of the max quantity of overlap cases
        for (int w = 1; w <= maxOverlap; w++) {
            double r = 255;
            double g = 100 + (155 - (step * w));
            double b = 0;
            int colors = ColorLib.rgb((int) r, (int) g, (int) b);
            colorScale.put(w, colors);
        }

        for (int i = 0; i < data.ROWS; i++) {
            if (this.tempDP[i].overlapCounter > 1) {
                int tmpColorOverlap = colorScale.get(this.tempDP[i].overlapCounter);
                this.tempDP[i].colorOverlap = tmpColorOverlap;
            } else {
                this.tempDP[i].setAlpha(true);
            }
        }
    }

    float calcDistance(float x1, float y1, float x2, float y2) {
        scaleRatioX = maxDistMaxRadius / maxDistMinRadius; //(maxDistX - minDistX) / workspace.gpanel.radius;
        scaleRatioY = (maxDistY - minDistY) / radius;
        return PApplet.sqrt(PApplet.sq(x2 - x1) + PApplet.sq(y2 - y1));
    }

    public void colapsePolygons() {
        for (int i = 0; i < data.ROWS; i++) {
            tempDP[i].polygon.setSelected(false);
        }
    }

    public void createLayers() {
        tempDP[0].setLayer(1);
        countLayers = 1;
        for (int i = 1; i < this.tempDP.length; i++) {
            int max = 0;
            int countOver = 0, countFirstLayer = 0;
            for (int j = i - 1; j >= 0; j--) {
                boolean over = false;
                // if any vertice is in the area of other particle
                if ((tempDP[i].x >= tempDP[j].x && tempDP[i].x <= tempDP[j].x + 4 && tempDP[i].y >= tempDP[j].y && tempDP[i].y <= tempDP[j].y + 4) || (tempDP[i].x + 4 >= tempDP[j].x && tempDP[i].x + 4 <= tempDP[j].x + 4 && tempDP[i].y + 4 >= tempDP[j].y && tempDP[i].y + 4 <= tempDP[j].y + 4) || (tempDP[i].x + 4 >= tempDP[j].x && tempDP[i].x + 4 <= tempDP[j].x + 4 && tempDP[i].y >= tempDP[j].y && tempDP[i].y <= tempDP[j].y + 4) || (tempDP[i].x >= tempDP[j].x && tempDP[i].x <= tempDP[j].x + 4 && tempDP[i].y + 4 >= tempDP[j].y && tempDP[i].y + 4 <= tempDP[j].y + 4) || (tempDP[i].x + 2 >= tempDP[j].x && tempDP[i].x + 2 <= tempDP[j].x + 2 && tempDP[i].y + 2 >= tempDP[j].y && tempDP[i].y + 2 <= tempDP[j].y + 2) || (tempDP[i].x + 2 >= tempDP[j].x && tempDP[i].x + 2 <= tempDP[j].x + 2 && tempDP[i].y >= tempDP[j].y && tempDP[i].y <= tempDP[j].y + 2) || (tempDP[i].x >= tempDP[j].x && tempDP[i].x <= tempDP[j].x + 4 && tempDP[i].y + 2 >= tempDP[j].y && tempDP[i].y + 2 <= tempDP[j].y + 4)) {
                    over = true;
                }
                // if over mor than one particle, the layer selected is the largest
                if (over) {
                    if (tempDP[j].layer > max) {
                        max = tempDP[j].layer;
                        countOver++;
                    }
                    if (tempDP[j].layer == 1) {
                        countFirstLayer++;
                    }
                }
            }
            if ((countOver > 1 && countFirstLayer != 0) || countFirstLayer != 0) {
                tempDP[i].layer = max + 1;
                if (tempDP[i].layer > countLayers) {
                    countLayers = tempDP[i].layer;
                }
            } else {
                tempDP[i].layer = 1;
            }
        }
        // Create the map of layers
        int initEllipse = (int) (x - radius);
        int endEllipse = (int) (x + radius);
        int[] tmpLayerValues;
        for (int i = 1; i <= countLayers; i++) {
            tmpLayerValues = new int[(int) radius * 20];
            for (int j = initEllipse; j < endEllipse; j++) {
                int count = 0;
                for (int m = 0; m < this.tempDP.length; m++) {
                    if (this.tempDP[m].layer == i) {
                        if (Math.round(this.tempDP[m].x) == j) {
                            count++;
                        }
                    }
                }
                if (count > 0) {
                    tmpLayerValues[j - initEllipse] = count;
                }
            }
            matrixLayer.put(i, tmpLayerValues);
        }
    }

    protected void generateClassifier() throws FileNotFoundException, Exception {
        // load data
        BufferedReader reader = new BufferedReader(new FileReader("gui/Icons/vmd/data/data/iris.arff"));
        Instances train = new Instances(reader);
        reader.close();
        train.setClassIndex(train.numAttributes() - 1);
        BufferedReader reader_2 = new BufferedReader(new FileReader("gui/Icons/vmd/data/data/iris.arff"));
        Instances test = new Instances(reader_2);
        reader_2.close();
        test.setClassIndex(test.numAttributes() - 1);
        // train classifier
        J48 cls = new J48();
        cls.buildClassifier(train);

        // The output tree saved in a text file
        File outfile = new java.io.File("gui/Icons/vmd/data/data/filename.txt");
        PrintWriter writer = new java.io.PrintWriter(outfile);
        writer.println(cls.toString());
        writer.close();
        // Clean the tree
        int countValidate = 0;
        try {
            String DataCount = null;
            // TODO Auto-generated method stub
            BufferedReader br1 = new BufferedReader(new FileReader(new File("gui/Icons/vmd/data/data/filename.txt")));
            while ((DataCount = br1.readLine()) != null) {
                int validate = DataCount.indexOf(":");
                if (validate > 0) {
                    countValidate++;
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // five columns for: attribute, operator, value, class, instances
        classifier = new Table(countValidate - 2, 10);
        classifier.addColumn("Attribute", String.class);
        classifier.addColumn("Operator", String.class);
        classifier.addColumn("Value", String.class);
        classifier.addColumn("Class", String.class);
        classifier.addColumn("Instances", String.class);
        classifier.addColumn("MaxInstances", String.class);
        classifier.addColumn("Color", String.class); // Red, Green, Blue, Cyan, Magenta, Yellow, Gray
        classifier.addColumn("RenderColor", String.class); // color to be used to fill sectors
        classifier.addColumn("Order", String.class);
        classifier.addColumn("ValueSuperior", String.class);
        // Read the file in order to extract the tree leaves
        try {
            String data = null;
            int count = 0, countSize = 0;
            // TODO Auto-generated method stub
            BufferedReader br = new BufferedReader(new FileReader(new File("gui/Icons/vmd/data/data/filename.txt")));
            while ((data = br.readLine()) != null) {
                String result = "";
                int validate = data.indexOf(":");
                if (validate > 0 && countSize < (countValidate - 2)) {
                    String resultPoints = data.replace(":", "");
                    String resultParenthesisLeft = resultPoints.replace("(", "");
                    String resultParenthesisRight = resultParenthesisLeft.replace(")", "");
                    String resultPipe = resultParenthesisRight.replace("|", "");
                    result = resultPipe.trim();
                    String[] parts = result.split(" ");
                    for (int i = 0; i < parts.length; i++) {
                        if (parts[i].indexOf("/") > 0) {
                            parts[i] = parts[i].split("/")[0];
                        }
                    }
//                    System.out.println(parts[0] + ":" + parts[1] + ":" + parts[2] + ":" + parts[3] + ":" + parts[4]);
                    classifier.setString(countSize, "Attribute", parts[0]);
                    classifier.setString(countSize, "Operator", parts[1]);
                    classifier.setString(countSize, "Value", parts[2]);
                    classifier.setString(countSize, "Class", parts[3]);
                    classifier.setString(countSize, "Instances", parts[4]);
                    countSize++;
                }
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Define color array // Red, Green, Blue, Cyan, Magenta, Yellow, Gray
        Map<String, int[]> colorArray = new HashMap<String, int[]>();
        int[] red = {255, 0, 0, 120};
        int[] green = {0, 255, 0, 120};
        int[] blue = {0, 0, 255, 120};
        int[] yellow = {255, 255, 0, 120};
        int[] cyan = {0, 255, 255, 120};
        int[] magenta = {255, 0, 255, 120};
        int[] grey = {150, 0, 75, 209};
        colorArray.put("red", red);
        colorArray.put("green", green);
        colorArray.put("blue", blue);
        colorArray.put("yellow", yellow);
        colorArray.put("cyan", cyan);
        colorArray.put("magenta", magenta);
        colorArray.put("grey", grey);
        // Order colors in priority
        String[] colorOrder = {"red", "green", "blue", "yellow", "cyan", "magenta", "grey"};
        // Assign Color to each class value
        String[] distinctClassValues = data.distinctCategoricalValues.get("Species Name");
        for (int i = 0; i < distinctClassValues.length; i++) {
            for (int m = 0; m < classifier.getRowCount(); m++) {
                if (classifier.getString(m, "Class").equals(distinctClassValues[i])) {
                    classifier.setString(m, "Color", colorOrder[i]);
                }
            }
        }
        // Search the max value of instances for proportionaly assign color
        for (int i = 0; i < classifier.getRowCount(); i++) {
            String tmpClass = classifier.getString(i, "Class");
            String tmpAttribute = classifier.getString(i, "Attribute");
            float maxValue = 0;
            for (int j = 0; j < classifier.getRowCount(); j++) {
                if (classifier.getString(i, "Class").equals(tmpClass) && classifier.getString(i, "Attribute").equals(tmpAttribute)) {
                    Float tmpInstances = Float.valueOf(classifier.getString(j, "Instances"));
                    if (tmpInstances > maxValue) {
                        maxValue = tmpInstances;
                    }
                }
            }
            classifier.setString(i, "MaxInstances", String.valueOf(maxValue));
        }
        // Assign percent color to each class value of classifier
        // and identify the distinct number of attributes involved in classifier tree
        List<String> distinctClassesTreeList = new ArrayList<String>();
        Map<String, Float> distinctClassesTree = new HashMap<String, Float>();
        for (int i = 0; i < classifier.getRowCount(); i++) {
            int[] tmpRGB = colorArray.get(classifier.getString(i, "Color"));
            float tmpInstances = Float.valueOf(classifier.getString(i, "Instances"));
            float tmpMaxInstances = Float.valueOf(classifier.getString(i, "MaxInstances"));
            double percent = tmpInstances / tmpMaxInstances;
//            int r = (int) (tmpRGB[0] * percent);
//            if (tmpRGB[0] != 0) {
//                tmpRGB[0] += tmpRGB[3];
//            }
//            int g = (int) (tmpRGB[1] * percent);
//            if (tmpRGB[1] != 0) {
//                tmpRGB[0] += tmpRGB[3];
//            }
//            int b = (int) (tmpRGB[2] * percent);
//            if (tmpRGB[2] != 0) {
//                tmpRGB[0] += tmpRGB[3];
//            }
            int r = (int) (tmpRGB[0]);
            int g = (int) (tmpRGB[1]);
            int b = (int) (tmpRGB[2]);
            // percent of alpha proportionaly to the amount of instances classified
            int alpha = (int) (50 + (150 * percent));
            classifier.setString(i, "RenderColor", String.valueOf(r + "," + g + "," + b + "," + alpha));
            // identify distinct values
            if (!distinctClassesTreeList.contains(classifier.getString(i, "Attribute"))) {
                distinctClassesTreeList.add(classifier.getString(i, "Attribute"));
            }
            if (distinctClassesTree.containsKey(classifier.getString(i, "Attribute"))) {
                float tmpValueInstances = tmpInstances + distinctClassesTree.get(classifier.getString(i, "Attribute"));
                distinctClassesTree.put(classifier.getString(i, "Attribute"), tmpValueInstances);
            }
            if (!distinctClassesTree.containsKey(classifier.getString(i, "Attribute"))) {
                distinctClassesTree.put(classifier.getString(i, "Attribute"), tmpInstances);
            }

        }
        // Generate order of the sectors layers
        for (int n = 0; n < distinctClassesTreeList.size(); n++) {
            String valueListAttribute = distinctClassesTreeList.get(n);
            int count = 0;
            for (int i = 0; i < classifier.getRowCount(); i++) {
                if (classifier.getString(i, "Attribute").equals(valueListAttribute)) {
                    float tmpMaxValue = 0;
                    int order = 0;
                    for (int j = 0; j < classifier.getRowCount(); j++) {
                        if (classifier.getString(j, "Order") == null) {
                            if (classifier.getString(j, "Attribute").equals(valueListAttribute)) {
                                if (Float.valueOf(classifier.getString(j, "Value")) > tmpMaxValue) {
                                    order = j;
                                    tmpMaxValue = Float.valueOf(classifier.getString(j, "Value"));
                                }
                                if (Float.valueOf(classifier.getString(j, "Value")) == tmpMaxValue) {
                                    if (classifier.getString(j, "Operator").equals(">")) {
                                        order = j;
                                        tmpMaxValue = Float.valueOf(classifier.getString(j, "Value"));
                                    }
                                }
                            }
                        }
                    }
                    count++;
                    classifier.setString(order, "Order", String.valueOf(count));
                }
            }
        }
        // Update the superior value level
        for (int n = 0; n < distinctClassesTreeList.size(); n++) {
            String valueListAttribute = distinctClassesTreeList.get(n);
            for (int i = 0; i < classifier.getRowCount(); i++) {
                if (classifier.getString(i, "Attribute").equals(valueListAttribute)) {
                    if (classifier.getString(i, "Operator").equals("<=")) {
                        classifier.setString(i, "ValueSuperior", classifier.getString(i, "Value"));
                    }
                    if (classifier.getString(i, "Operator").equals(">") && (classifier.getString(i, "Order").equals("1"))) {
                        double[] maxminValues = data.realMinMax.get(valueListAttribute);
                        classifier.setString(i, "ValueSuperior", String.valueOf(maxminValues[1]));
                    }
                    if (classifier.getString(i, "Operator").equals(">") && !(classifier.getString(i, "Order").equals("1"))) {
                        boolean flag = false;
                        for (int k = 0; k < classifier.getRowCount(); k++) {
                            if (classifier.getString(k, "Attribute").equals(valueListAttribute)) {
                                String order1 = "";
                                if (flag == false) {
                                    order1 = String.valueOf(Integer.valueOf(classifier.getString(i, "Order")) - 1);
                                } else {
                                    order1 = String.valueOf(Integer.valueOf(classifier.getString(i, "Order")) - 2);
                                }
                                if (classifier.getString(k, "Order").equals(order1)) {
                                    if (!classifier.getString(k, "Value").equals(classifier.getString(i, "Value"))) {
                                        classifier.setString(i, "ValueSuperior", classifier.getString(k, "Value"));
                                    } else {
                                        flag = true;
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
        // Identify the max order, store the order
        int maxOrder = 0;
        int[] orderList = new int[classifier.getRowCount()];
        for (int i = 0; i < classifier.getRowCount(); i++) {
            if (Integer.valueOf(classifier.getString(i, "Order")) > maxOrder) {
                maxOrder = Integer.valueOf(classifier.getString(i, "Order"));
            }
            orderList[i] = Integer.valueOf(classifier.getString(i, "Order"));
        }
//        // depure the order
//        List<Integer> distinctOrder = new ArrayList<Integer>();
//        for (int k = 0; k < orderList.length; k++) {
//            if (!distinctOrder.contains(orderList[k])) {
//                distinctOrder.add(orderList[k]);
//                System.out.println(orderList[k]);
//            }
//        }
        // init graph
        float xClassifierGraph = (width / 3) + width;
        float yClassifierGraph = width / 2;
        float ratioClassifierGraph = (height - 100) / 2;
        // divide the circunference between the number of attributes involved in the classifier tree J48
        // and create the sectors
        classifierSector = new VMDSectorClassifier[classifier.getRowCount()];
        Map<String, float[]> classAngles = new HashMap<String, float[]>();
        float angleAnt = 0;
        for (int i = 0; i < distinctClassesTreeList.size(); i++) {
            float tmpTotalInstances = distinctClassesTree.get(distinctClassesTreeList.get(i));
            float portion = tmpTotalInstances / data.ROWS;
            float angleClassifier = (float) (portion * (Math.PI * 2)) + angleAnt;
            float[] valuesAngle = {angleAnt, angleClassifier, portion};
            classAngles.put(distinctClassesTreeList.get(i), valuesAngle);
            angleAnt = angleClassifier;
        }
//        // Print table
//        for (int i = 0; i < classifier.getColumnCount(); i++) {
//            for (int j = 0; j < classifier.getRowCount(); j++) {
//                System.out.print(classifier.getString(j, i) + ": ");
//            }
//            System.out.println("");
//        }
        // Crate the graph
        for (int i = 0; i < distinctClassesTreeList.size(); i++) {
            int countFlag = 1;
            String tmpAttrib = distinctClassesTreeList.get(i);
            for (int j = 0; j < classifier.getRowCount(); j++) {
                if (classifier.getString(j, "Attribute").equals(tmpAttrib)) {
                    int flag = 0;
                    for (int l = 0; l < classifier.getRowCount(); l++) {
                        if (classifier.getString(l, "Order").equals(String.valueOf(countFlag)) && classifier.getString(l, "Attribute").equals(tmpAttrib)) {
                            flag = l;
                        }
                    }
                    // Start conversion scale 0 to radius --> min - max attribute
                    double[] maxMinAttribute = data.realMinMax.get(classifier.getString(flag, "Attribute"));
                    double differenceMaxMin = maxMinAttribute[1] - maxMinAttribute[0];
                    float newRadius = (float) ((Float.valueOf(classifier.getString(flag, "ValueSuperior")) * ratioClassifierGraph) / differenceMaxMin);
                    // found the next sector radius for tooltip
                    int nextFlag = 0;
                    String nextValue = "";
                    for (int a = 0; a < classifier.getRowCount(); a++) {
                        if (classifier.getString(a, "Order").equals(String.valueOf(countFlag + 1)) && classifier.getString(a, "Attribute").equals(tmpAttrib)) {
                            nextFlag = a;
                        }
                    }
                    float nextRadius = 0;
                    nextValue = "0";
                    if (nextFlag != 0) {
                        nextRadius = (float) ((Float.valueOf(classifier.getString(nextFlag, "ValueSuperior")) * ratioClassifierGraph) / differenceMaxMin);
                        nextValue = classifier.getString(nextFlag, "ValueSuperior");
                    }
                    // Construct the sector
                    float[] angles = classAngles.get(classifier.getString(flag, "Attribute"));
                    classifierSector[j] = new VMDSectorClassifier(parent, this, xClassifierGraph, yClassifierGraph, angles[0], angles[1], classifier.getString(flag, "Attribute"), classifier.getString(flag, "RenderColor"), Integer.valueOf(classifier.getString(flag, "Order")), newRadius, classifier.getString(flag, "Class"), ratioClassifierGraph / 2, nextRadius, classifier.getString(flag, "Operator"), classifier.getString(flag, "Value"), nextValue, angles[2]);
                    classifierSector[j].init();
                    classifierSector[j].setVisible(false);
                    countFlag++;
                }
            }
        }
    }
}
