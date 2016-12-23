package gui.Icons.vmd;


////import java.awt.event.MouseEvent;
import gui.Icons.vmd.guicomponents.GButton;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import processing.core.PApplet;   // libreria grafica
////import processing.core.PImage;
////import vmd.graphic.VMDColorItem;
////import vmd.graphic.VMDItemMenu;
////import vmd.graphic.VMDLabel;
import gui.Icons.vmd.vmd.graphic.VMDPoint;
import gui.Icons.vmd.vmd.graphic.VMDWorkspace;
import gui.Icons.vmd.guicomponents.GCScheme;
import gui.Icons.vmd.guicomponents.GCombo;
import gui.Icons.vmd.guicomponents.GComponent;
import gui.Icons.vmd.guicomponents.GFont;
import gui.Icons.vmd.guicomponents.GWindow;
import gui.KnowledgeFlow.Icon;
import javax.swing.table.AbstractTableModel;


//Visualizacion de datos multivariantes.

public class VMD extends PApplet {  //extiende a la libreria del core.jar processing

    // Workspace dimensiones de espacio interno interno donde aparecen los graficos
    public int wsWidth = 700; 
    public int appletWidth = 1200;
    public int wsHeight = 560;
    VMDWorkspace mvws, corrWorkspace;
    VMDPoint point_2;
    GButton btnControl;
    GWindow windControl;
    AbstractTableModel dataInVmd;
    Icon auxVmdIcon;

    // Zoom
    int[] color = new int[500];
    // Tool keyboard menu
    int[] tool = {0, 1, 2, 3, 4, 5};
    int toolIndex;


    public static void main(String args[]) {
        PApplet.main(new String[]{"--present", "VMD"});
    }

    public VMD(AbstractTableModel dataIn, Icon _vmdIcon) {
        this.dataInVmd = dataIn;
        this.auxVmdIcon = _vmdIcon;
    }

    public void setup() {
        size(appletWidth, wsHeight, JAVA2D); // Tamaño de la ventana interna de graficos
        background(255);
        smooth();

        GComponent.globalColor = GCScheme.getColor(this, GCScheme.BLUE_SCHEME); // color claro del fongo interno

        GComponent.globalFont = GFont.getFont(this, "Georgia", 11);
        hint(ENABLE_NATIVE_FONTS);

        try {
            // !!!!! Constructor del workspace que dibuja los sectores y los puntos   (this, width, height, x, y, diametro, dataIn)
            mvws = new VMDWorkspace((PApplet) this, wsWidth, wsHeight, wsWidth / 2, 270, wsHeight - 100, dataInVmd, auxVmdIcon);  // wsHeight - 200 controla el tamaño de los objetos graficos
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VMD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VMD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // all the handle events blocks most be here, in the main class
    public void handleComboEvents(GCombo combo) throws FileNotFoundException, Exception { // esto toca ver

//        if (mvws.files == combo) {
            mvws.loadFile();
            mvws.pre();
//        }
        // Procedure to handle the event the combo box of the class menu label that has many values and cant be showed at ince
        for (int i = 0; i < mvws.baseSectors.sector.length; i++) {
            if (mvws.baseSectors.sector[i].label.menuCombo == combo) {
                for (int j = 0; j < mvws.baseSectors.sector[i].label.classMenu.itemMenuClass.length; j++) {
                    if (mvws.baseSectors.sector[i].label.classMenu.itemMenuClass[j].nameValue == combo.selectedText()) {
                        // position alone the value item over the label not in type class menu
                        float tmpX = mvws.baseSectors.sector[i].label.getX();
                        float tmpY = mvws.baseSectors.sector[i].label.getY() - 10;
                        mvws.baseSectors.sector[i].label.classMenu.itemMenuClass[j].setPosition(tmpX, tmpY);
                        mvws.baseSectors.sector[i].label.classMenu.itemMenuClass[j].setVisible(true);
                        mvws.baseSectors.sector[i].label.menuExpanded = true;
                    } else {
                        mvws.baseSectors.sector[i].label.classMenu.itemMenuClass[j].setVisible(false);
                    }
                }
                mvws.baseSectors.sector[i].label.menuCombo.setVisible(false);
            }
        }
    }

    public void draw() {
        background(255); //255 blanco color de fondo interno
        mvws.classColored();
    }

    @Override
    public void keyPressed() {
        // if key pressed
        if (key == CODED) {
            if (keyCode == LEFT) {
                setIndexTool(-1);
            } else if (keyCode == RIGHT) {
                setIndexTool(1);
            }
        }
    }

    public void setIndexTool(int _value) {
        toolIndex = toolIndex + _value;
        if (toolIndex > tool.length) {
            toolIndex = 0;
        }
        if (toolIndex < 0) {
            toolIndex = tool.length;
        }
        switch (toolIndex) {
            case 0:
                mvws.viewOverlap.setSelected(false);
                mvws.viewPolygon.setSelected(false);
                mvws.viewCoorelationLines.setSelected(false);
                mvws.viewValuesDistributionPerAttribute.setSelected(false);
                mvws.viewTendencyMeasures.setSelected(false);
                mvws.pointsAlpha(false);
                break;
            case 1:
                mvws.viewOverlap.setSelected(true);
                mvws.viewPolygon.setSelected(false);
                mvws.viewCoorelationLines.setSelected(false);
                mvws.viewValuesDistributionPerAttribute.setSelected(false);
                mvws.viewTendencyMeasures.setSelected(false);
                mvws.pointsAlpha(false);
                mvws.overLap();
                break;
            case 2:
                mvws.viewOverlap.setSelected(false);
                mvws.viewPolygon.setSelected(true);
                mvws.viewCoorelationLines.setSelected(false);
                mvws.viewValuesDistributionPerAttribute.setSelected(false);
                mvws.viewTendencyMeasures.setSelected(false);
                mvws.pointsAlpha(false);
                break;
            case 3:
                mvws.viewOverlap.setSelected(false);
                mvws.viewPolygon.setSelected(false);
                mvws.viewCoorelationLines.setSelected(true);
                mvws.viewValuesDistributionPerAttribute.setSelected(false);
                mvws.viewTendencyMeasures.setSelected(false);
                mvws.pointsAlpha(false);
                break;
            case 4:
                mvws.viewOverlap.setSelected(false);
                mvws.viewPolygon.setSelected(false);
                mvws.viewCoorelationLines.setSelected(false);
                mvws.viewValuesDistributionPerAttribute.setSelected(true);
                mvws.viewTendencyMeasures.setSelected(false);
                mvws.pointsAlpha(true);
                break;
            case 5:
                mvws.viewOverlap.setSelected(false);
                mvws.viewPolygon.setSelected(false);
                mvws.viewCoorelationLines.setSelected(false);
                mvws.viewValuesDistributionPerAttribute.setSelected(false);
                mvws.viewTendencyMeasures.setSelected(true);
                mvws.pointsAlpha(true);
                break;
        }
    }
}