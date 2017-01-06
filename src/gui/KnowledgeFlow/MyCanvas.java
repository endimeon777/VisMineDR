/*
 * this.java
 *
 *  
 */    
package gui.KnowledgeFlow;

import gui.Icons.Association.AssociationIcon;
import gui.Icons.Clasification.ClasificationIcon;
import gui.Icons.DBConnection.DBConnectionIcon;
import gui.Icons.File.FileIcon;
import gui.Icons.Filters.FilterIcon;
import gui.Icons.Prediction.PredictionIcon;
import gui.Icons.Rules.RulesIcon;
import gui.Icons.Tree.HierarchicalTreeIcon;
import gui.Icons.Tree.TextTreeIcon;
import gui.Icons.Tree.WekaTreeIcon;
import gui.Icons.Filters.Standarize.StandarizeIcon;
import gui.Icons.Cluster.configureCluster.clusterIcon;
import gui.Icons.Cluster.viewsClus.IconoArbol;
import gui.Icons.Cluster.viewsClus.IconoGrafica;
import gui.Icons.Cluster.viewsClus.Iconopestana;
import gui.Icons.Reduction.DRIcon;
import gui.Icons.VisDR.ScatterIcon;
import gui.Icons.VisDR.VarianceIcon;
import gui.Icons.vmd.vmdIcon;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * 
 */
public class MyCanvas extends javax.swing.JPanel implements DropTargetListener {

    miContenedor container;
    Icon seleccionado = null;
    Conector conectorpressed = null;
    ArrayList conexiones = new ArrayList();  //Arreglo de conectores tipo Conexion()
    int ix, iy, fx, fy;
    Image offscreen;
    Dimension offscreensize;
    Graphics offgraphics;
    final Color colorEdge = new Color(124, 136, 135);
    final Color colorLine = new Color(148, 167, 179); //229,192,255

    private Component oldTipToolText;
    private Point oldPoint;
    DropTarget dropTarget = null;
    
    public int contIcons = 1;

    /** Creates new form this */
    public MyCanvas() {
        initComponents();
        this.setToolTipText("");
        oldTipToolText = this;
    }
    
    public MyCanvas(miContenedor container) {
        this.container = container;
        initComponents();
        this.setToolTipText("");
        oldTipToolText = this;
        dropTarget = new DropTarget(this, this);
    }

    public MyCanvas(Contenedor container) {   
    }

    public void addIcono(Icon icono) {
        add(icono);
        icono.setBounds(icono.getLocation().x, icono.getLocation().y,icono.getPreferredSize().width,icono.getPreferredSize().height);
    }
    
    //_________________ para guardar y abrir componentes serializados

    public Component[] getGrafo() {
        return this.getComponents();
    }
    
    public void setGrafo(Object[] iconosS) {
       Icon iconoSeri;
       Icon icon = null;

       removeAll();
       repaint();

       for(int i=0; i<iconosS.length; i++){

            iconoSeri = (Icon)iconosS[i];

            String nameIcon = iconoSeri.getName();

            // Conection
            if(nameIcon.equals(" Plain Text ")){               
                icon = new FileIcon(iconoSeri.getLabel(), iconoSeri.getX(), iconoSeri.getY(), iconoSeri.getIndiceIcon());
                icon.mapaConexionTo = iconoSeri.mapaConexionTo;
                icon.mapaConexionFrom = iconoSeri.mapaConexionFrom;
                ((FileIcon)icon).filePath = (( FileIcon)iconoSeri).filePath;
                ((FileIcon)icon).dataset = (( FileIcon)iconoSeri).dataset;
                ((FileIcon)icon).xcon = (( FileIcon)iconoSeri).xcon;
                ((FileIcon)icon).isMarketBasket = (( FileIcon)iconoSeri).isMarketBasket;
                ((FileIcon)icon).data = (( FileIcon)iconoSeri).data;
                ((FileIcon)icon).updtateIconOpenFile(); // envia los valores al visualizador
            }else if(nameIcon.equals(" Connection DB ")){               
                icon = new DBConnectionIcon(iconoSeri.getLabel(), iconoSeri.getX(), iconoSeri.getY(), iconoSeri.getIndiceIcon());
                icon.mapaConexionTo = iconoSeri.mapaConexionTo;
                icon.mapaConexionFrom = iconoSeri.mapaConexionFrom;
//                ((DBConnectionIcon)icon).connection = (( DBConnectionIcon)iconoSeri).connection;
                ((DBConnectionIcon)icon).connectionTableModel = (( DBConnectionIcon)iconoSeri).connectionTableModel;
                ((DBConnectionIcon)icon).dataset = (( DBConnectionIcon)iconoSeri).dataset;
                ((DBConnectionIcon)icon).sqlCreado = (( DBConnectionIcon)iconoSeri).sqlCreado;
                ((DBConnectionIcon)icon).sqlColumnas = (( DBConnectionIcon)iconoSeri).sqlColumnas;
                ((DBConnectionIcon)icon).driver = (( DBConnectionIcon)iconoSeri).driver;
                ((DBConnectionIcon)icon).port = (( DBConnectionIcon)iconoSeri).port;
                ((DBConnectionIcon)icon).host = (( DBConnectionIcon)iconoSeri).host;
                ((DBConnectionIcon)icon).user = (( DBConnectionIcon)iconoSeri).user;
                ((DBConnectionIcon)icon).password = (( DBConnectionIcon)iconoSeri).password;
                ((DBConnectionIcon)icon).database = (( DBConnectionIcon)iconoSeri).database;
                ((DBConnectionIcon)icon).updtateIconDB(); // envia los valores al visualizador
                ((DBConnectionIcon)icon).mnuConfigure.setEnabled(true);
            } // FALTA SERIALIZAR EL EXAMPLE(DATOS EJEMPLO)
            else if(nameIcon.equals("Apriori")){
                icon = new AssociationIcon(iconoSeri.getLabel(), iconoSeri.getX(), iconoSeri.getY(), iconoSeri.getIndiceIcon());
                icon.mapaConexionTo = iconoSeri.mapaConexionTo;
                icon.mapaConexionFrom = iconoSeri.mapaConexionFrom;
                ((AssociationIcon)icon).algorithm = (( AssociationIcon)iconoSeri).algorithm;
                ((AssociationIcon)icon).dataset = (( AssociationIcon)iconoSeri).dataset;
                ((AssociationIcon)icon).cs = (( AssociationIcon)iconoSeri).cs;
                ((AssociationIcon)icon).support = (( AssociationIcon)iconoSeri).support;
                ((AssociationIcon)icon).trees = (( AssociationIcon)iconoSeri).trees;
                ((AssociationIcon)icon).updateIconConfigureSupport(); // envia los valores al configurador que es grafico y no se puede serializar
                ((AssociationIcon)icon).getMnuRun().setEnabled(true);
            }else if(nameIcon.equals("EquipAsso")){
                icon = new AssociationIcon(iconoSeri.getLabel(), iconoSeri.getX(), iconoSeri.getY(), iconoSeri.getIndiceIcon());
                icon.mapaConexionTo = iconoSeri.mapaConexionTo;
                icon.mapaConexionFrom = iconoSeri.mapaConexionFrom;
                ((AssociationIcon)icon).algorithm = (( AssociationIcon)iconoSeri).algorithm;
                ((AssociationIcon)icon).dataset = (( AssociationIcon)iconoSeri).dataset;
                ((AssociationIcon)icon).cs = (( AssociationIcon)iconoSeri).cs;
                ((AssociationIcon)icon).support = (( AssociationIcon)iconoSeri).support;
                ((AssociationIcon)icon).trees = (( AssociationIcon)iconoSeri).trees;
                ((AssociationIcon)icon).updateIconConfigureSupport();
                ((AssociationIcon)icon).getMnuRun().setEnabled(true);
            }else if(nameIcon.equals("FPGrowth")){
                icon = new AssociationIcon(iconoSeri.getLabel(), iconoSeri.getX(), iconoSeri.getY(), iconoSeri.getIndiceIcon());
                icon.mapaConexionTo = iconoSeri.mapaConexionTo;
                icon.mapaConexionFrom = iconoSeri.mapaConexionFrom;
                ((AssociationIcon)icon).algorithm = (( AssociationIcon)iconoSeri).algorithm;
                ((AssociationIcon)icon).dataset = (( AssociationIcon)iconoSeri).dataset;
                ((AssociationIcon)icon).cs = (( AssociationIcon)iconoSeri).cs;
                ((AssociationIcon)icon).support = (( AssociationIcon)iconoSeri).support;
                ((AssociationIcon)icon).trees = (( AssociationIcon)iconoSeri).trees;
                ((AssociationIcon)icon).updateIconConfigureSupport();
                ((AssociationIcon)icon).getMnuRun().setEnabled(true);
            }else if(nameIcon.equals("  C45  ")){
                icon = new ClasificationIcon(iconoSeri.getLabel(), iconoSeri.getX(), iconoSeri.getY(), iconoSeri.getIndiceIcon());
                icon.mapaConexionTo = iconoSeri.mapaConexionTo;
                icon.mapaConexionFrom = iconoSeri.mapaConexionFrom;
                ((ClasificationIcon)icon).algorithm = (( ClasificationIcon)iconoSeri).algorithm;
                ((ClasificationIcon)icon).dataIn = (( ClasificationIcon)iconoSeri).dataIn;
                ((ClasificationIcon)icon).c = (( ClasificationIcon)iconoSeri).c;
                ((ClasificationIcon)icon).root = (( ClasificationIcon)iconoSeri).root;
                ((ClasificationIcon)icon).minRows = (( ClasificationIcon)iconoSeri).minRows;
                ((ClasificationIcon)icon).trainingSet = (( ClasificationIcon)iconoSeri).trainingSet;
                ((ClasificationIcon)icon).dataOut2 = (( ClasificationIcon)iconoSeri).dataOut2;
                ((ClasificationIcon)icon).threshold = (( ClasificationIcon)iconoSeri).threshold;
                ((ClasificationIcon)icon).updateIconConfigureParameters();
                ((ClasificationIcon)icon).getMnuRun().setEnabled(true);
            }else if(nameIcon.equals("  Mate  ")){
                icon = new ClasificationIcon(iconoSeri.getLabel(), iconoSeri.getX(), iconoSeri.getY(), iconoSeri.getIndiceIcon());
                icon.mapaConexionTo = iconoSeri.mapaConexionTo;
                icon.mapaConexionFrom = iconoSeri.mapaConexionFrom;
                ((ClasificationIcon)icon).algorithm = (( ClasificationIcon)iconoSeri).algorithm;
                ((ClasificationIcon)icon).dataIn = (( ClasificationIcon)iconoSeri).dataIn;
                ((ClasificationIcon)icon).c = (( ClasificationIcon)iconoSeri).c;
                ((ClasificationIcon)icon).root = (( ClasificationIcon)iconoSeri).root;
                ((ClasificationIcon)icon).minRows = (( ClasificationIcon)iconoSeri).minRows;
                ((ClasificationIcon)icon).trainingSet = (( ClasificationIcon)iconoSeri).trainingSet;
                ((ClasificationIcon)icon).dataOut2 = (( ClasificationIcon)iconoSeri).dataOut2;
                ((ClasificationIcon)icon).threshold = (( ClasificationIcon)iconoSeri).threshold;
                ((ClasificationIcon)icon).updateIconConfigureParameters();
                ((ClasificationIcon)icon).getMnuRun().setEnabled(true);
            }else if(nameIcon.equals("Sliq")){
                icon = new ClasificationIcon(iconoSeri.getLabel(), iconoSeri.getX(), iconoSeri.getY(), iconoSeri.getIndiceIcon());
                icon.mapaConexionTo = iconoSeri.mapaConexionTo;
                icon.mapaConexionFrom = iconoSeri.mapaConexionFrom;
                ((ClasificationIcon)icon).algorithm = (( ClasificationIcon)iconoSeri).algorithm;
                ((ClasificationIcon)icon).dataIn = (( ClasificationIcon)iconoSeri).dataIn;
                ((ClasificationIcon)icon).c = (( ClasificationIcon)iconoSeri).c;
                ((ClasificationIcon)icon).root = (( ClasificationIcon)iconoSeri).root;
                ((ClasificationIcon)icon).minRows = (( ClasificationIcon)iconoSeri).minRows;
                ((ClasificationIcon)icon).trainingSet = (( ClasificationIcon)iconoSeri).trainingSet;
                ((ClasificationIcon)icon).dataOut2 = (( ClasificationIcon)iconoSeri).dataOut2;
                ((ClasificationIcon)icon).threshold = (( ClasificationIcon)iconoSeri).threshold;
                ((ClasificationIcon)icon).updateIconConfigureParameters();
                ((ClasificationIcon)icon).getMnuRun().setEnabled(true);
            }else if(nameIcon.equals("Kmeans")){
                icon = new clusterIcon(iconoSeri.getLabel(), iconoSeri.getX(), iconoSeri.getY(), iconoSeri.getIndiceIcon());
                icon.mapaConexionTo = iconoSeri.mapaConexionTo;
                icon.mapaConexionFrom = iconoSeri.mapaConexionFrom;
                ((clusterIcon)icon).algorithm = (( clusterIcon)iconoSeri).algorithm;
                ((clusterIcon)icon).dataIn = (( clusterIcon)iconoSeri).dataIn;
                ((clusterIcon)icon).c = (( clusterIcon)iconoSeri).c;
                ((clusterIcon)icon).root = (( clusterIcon)iconoSeri).root;
                ((clusterIcon)icon).minRows = (( clusterIcon)iconoSeri).minRows;
                ((clusterIcon)icon).trainingSet = (( clusterIcon)iconoSeri).trainingSet;
                ((clusterIcon)icon).dataOut2 = (( clusterIcon)iconoSeri).dataOut2;
                ((clusterIcon)icon).threshold = (( clusterIcon)iconoSeri).threshold;
                ((clusterIcon)icon).lista = (( clusterIcon)iconoSeri).lista;
                ((clusterIcon)icon).medoide = (( clusterIcon)iconoSeri).medoide;
                ((clusterIcon)icon).dataOri = (( clusterIcon)iconoSeri).dataOri;
                ((clusterIcon)icon).listacluster = (( clusterIcon)iconoSeri).listacluster;
                ((clusterIcon)icon).numcluster = (( clusterIcon)iconoSeri).numcluster;  
                ((clusterIcon)icon).numInteraciones = (( clusterIcon)iconoSeri).numInteraciones;
                ((clusterIcon)icon).cantidadclu = (( clusterIcon)iconoSeri).cantidadclu;
                ((clusterIcon)icon).atriColumnas = (( clusterIcon)iconoSeri).atriColumnas;
                ((clusterIcon)icon).distanciakm = (( clusterIcon)iconoSeri).distanciakm;
                ((clusterIcon)icon).centroideCerca = (( clusterIcon)iconoSeri).centroideCerca;
//                ((clusterIcon)icon).numlocal = (( clusterIcon)iconoSeri).numlocal;
//                ((clusterIcon)icon).maxvecinos = (( clusterIcon)iconoSeri).maxvecinos;
                ((clusterIcon)icon).updateIconConfigureKmeans();
                ((clusterIcon)icon).getMnuRun().setEnabled(true);
            }else if(nameIcon.equals("Clarans")){
                icon = new clusterIcon(iconoSeri.getLabel(), iconoSeri.getX(), iconoSeri.getY(), iconoSeri.getIndiceIcon());
                icon.mapaConexionTo = iconoSeri.mapaConexionTo;
                icon.mapaConexionFrom = iconoSeri.mapaConexionFrom;
                ((clusterIcon)icon).algorithm = (( clusterIcon)iconoSeri).algorithm;
                ((clusterIcon)icon).dataIn = (( clusterIcon)iconoSeri).dataIn;
                ((clusterIcon)icon).c = (( clusterIcon)iconoSeri).c;
                ((clusterIcon)icon).root = (( clusterIcon)iconoSeri).root;
                ((clusterIcon)icon).minRows = (( clusterIcon)iconoSeri).minRows;
                ((clusterIcon)icon).trainingSet = (( clusterIcon)iconoSeri).trainingSet;
                ((clusterIcon)icon).dataOut2 = (( clusterIcon)iconoSeri).dataOut2;
                ((clusterIcon)icon).threshold = (( clusterIcon)iconoSeri).threshold;
                ((clusterIcon)icon).lista = (( clusterIcon)iconoSeri).lista;
                ((clusterIcon)icon).medoide = (( clusterIcon)iconoSeri).medoide;
                ((clusterIcon)icon).dataOri = (( clusterIcon)iconoSeri).dataOri;
                ((clusterIcon)icon).listacluster = (( clusterIcon)iconoSeri).listacluster;
                ((clusterIcon)icon).numcluster = (( clusterIcon)iconoSeri).numcluster;  
                ((clusterIcon)icon).numInteraciones = (( clusterIcon)iconoSeri).numInteraciones;
                ((clusterIcon)icon).cantidadclu = (( clusterIcon)iconoSeri).cantidadclu;
                ((clusterIcon)icon).atriColumnas = (( clusterIcon)iconoSeri).atriColumnas;
                ((clusterIcon)icon).distanciakm = (( clusterIcon)iconoSeri).distanciakm;
                ((clusterIcon)icon).centroideCerca = (( clusterIcon)iconoSeri).centroideCerca;
                ((clusterIcon)icon).numlocal = (( clusterIcon)iconoSeri).numlocal;
                ((clusterIcon)icon).maxvecinos = (( clusterIcon)iconoSeri).maxvecinos;
                ((clusterIcon)icon).updateIconConfigureClarans();
                ((clusterIcon)icon).getMnuRun().setEnabled(true);
            }else if(nameIcon.equals("Birch")){
                icon = new clusterIcon(iconoSeri.getLabel(), iconoSeri.getX(), iconoSeri.getY(), iconoSeri.getIndiceIcon());
                icon.mapaConexionTo = iconoSeri.mapaConexionTo;
                icon.mapaConexionFrom = iconoSeri.mapaConexionFrom;
                ((clusterIcon)icon).algorithm = (( clusterIcon)iconoSeri).algorithm;
                ((clusterIcon)icon).dataIn = (( clusterIcon)iconoSeri).dataIn;
                ((clusterIcon)icon).c = (( clusterIcon)iconoSeri).c;
                ((clusterIcon)icon).root = (( clusterIcon)iconoSeri).root;
                ((clusterIcon)icon).minRows = (( clusterIcon)iconoSeri).minRows;
                ((clusterIcon)icon).trainingSet = (( clusterIcon)iconoSeri).trainingSet;
                ((clusterIcon)icon).dataOut2 = (( clusterIcon)iconoSeri).dataOut2;
                ((clusterIcon)icon).threshold = (( clusterIcon)iconoSeri).threshold;
                ((clusterIcon)icon).lista = (( clusterIcon)iconoSeri).lista;
                ((clusterIcon)icon).medoide = (( clusterIcon)iconoSeri).medoide;
                ((clusterIcon)icon).dataOri = (( clusterIcon)iconoSeri).dataOri;
                ((clusterIcon)icon).listacluster = (( clusterIcon)iconoSeri).listacluster;
                ((clusterIcon)icon).numcluster = (( clusterIcon)iconoSeri).numcluster;  
                ((clusterIcon)icon).numInteraciones = (( clusterIcon)iconoSeri).numInteraciones;
                ((clusterIcon)icon).cantidadclu = (( clusterIcon)iconoSeri).cantidadclu;
                ((clusterIcon)icon).atriColumnas = (( clusterIcon)iconoSeri).atriColumnas;
                ((clusterIcon)icon).distanciakm = (( clusterIcon)iconoSeri).distanciakm;
                ((clusterIcon)icon).centroideCerca = (( clusterIcon)iconoSeri).centroideCerca;
//                ((clusterIcon)icon).numlocal = (( clusterIcon)iconoSeri).numlocal;
//                ((clusterIcon)icon).maxvecinos = (( clusterIcon)iconoSeri).maxvecinos;
                ((clusterIcon)icon).updateIconConfigureBirch();
                ((clusterIcon)icon).getMnuRun().setEnabled(true);
            } // Dimensionality Reduction
            else if(nameIcon.equals("MDS")){
                icon = new DRIcon(iconoSeri.getLabel(), iconoSeri.getX(), iconoSeri.getY(), iconoSeri.getIndiceIcon());
                icon.mapaConexionTo = iconoSeri.mapaConexionTo;
                icon.mapaConexionFrom = iconoSeri.mapaConexionFrom;
                ((DRIcon)icon).algorithm = ((DRIcon)iconoSeri).algorithm;
                ((ScatterIcon)icon).getMnuRun().setEnabled(true);
            } // CREO QUE FALTA SERIALIZAR LOS DEMAS METODOS RD (PCA, LE, LLE)
             // Filters
            else if(nameIcon.equals("Update Missing")){
                icon = new FilterIcon(iconoSeri.getLabel(), iconoSeri.getX(), iconoSeri.getY(), iconoSeri.getIndiceIcon());
                icon.mapaConexionTo = iconoSeri.mapaConexionTo;
                icon.mapaConexionFrom = iconoSeri.mapaConexionFrom;
                ((FilterIcon)icon).dataIn = (( FilterIcon)iconoSeri).dataIn;
                ((FilterIcon)icon).dataOut = (( FilterIcon)iconoSeri).dataOut;
                ((FilterIcon)icon).typeData = (( FilterIcon)iconoSeri).typeData;
                ((FilterIcon)icon).filterName = (( FilterIcon)iconoSeri).filterName;
                ((FilterIcon)icon).filterText = (( FilterIcon)iconoSeri).filterText;
                ((FilterIcon)icon).indexColumn = (( FilterIcon)iconoSeri).indexColumn; // solo lo que les compete a cada filtro
                ((FilterIcon)icon).replaceWith = (( FilterIcon)iconoSeri).replaceWith; // solo lo que les compete a cada filtro
                ((FilterIcon)icon).getMnuRun().setEnabled(true);
                ((FilterIcon)icon).getMnuView().setEnabled(true);
            }else if(nameIcon.equals("Codification")){
                icon = new FilterIcon(iconoSeri.getLabel(), iconoSeri.getX(), iconoSeri.getY(), iconoSeri.getIndiceIcon());
                icon.mapaConexionTo = iconoSeri.mapaConexionTo;
                icon.mapaConexionFrom = iconoSeri.mapaConexionFrom;
                ((FilterIcon)icon).dataIn = (( FilterIcon)iconoSeri).dataIn;
                ((FilterIcon)icon).dataOut = (( FilterIcon)iconoSeri).dataOut;
                ((FilterIcon)icon).typeData = (( FilterIcon)iconoSeri).typeData;
                ((FilterIcon)icon).filterName = (( FilterIcon)iconoSeri).filterName;
                ((FilterIcon)icon).filterText = (( FilterIcon)iconoSeri).filterText;
                ((FilterIcon)icon).getMnuView().setEnabled(true);
            }else if(nameIcon.equals("Remove Missing ")){
                icon = new FilterIcon(iconoSeri.getLabel(), iconoSeri.getX(), iconoSeri.getY(), iconoSeri.getIndiceIcon());
                icon.mapaConexionTo = iconoSeri.mapaConexionTo;
                icon.mapaConexionFrom = iconoSeri.mapaConexionFrom;
                ((FilterIcon)icon).dataIn = (( FilterIcon)iconoSeri).dataIn;
                ((FilterIcon)icon).dataOut = (( FilterIcon)iconoSeri).dataOut;
                ((FilterIcon)icon).typeData = (( FilterIcon)iconoSeri).typeData;
                ((FilterIcon)icon).filterName = (( FilterIcon)iconoSeri).filterName;
                ((FilterIcon)icon).filterText = (( FilterIcon)iconoSeri).filterText;
                ((FilterIcon)icon).getMnuView().setEnabled(true);
            }else if(nameIcon.equals(" Range ")){
                icon = new FilterIcon(iconoSeri.getLabel(), iconoSeri.getX(), iconoSeri.getY(), iconoSeri.getIndiceIcon());
                icon.mapaConexionTo = iconoSeri.mapaConexionTo;
                icon.mapaConexionFrom = iconoSeri.mapaConexionFrom;
                ((FilterIcon)icon).dataIn = (( FilterIcon)iconoSeri).dataIn;
                ((FilterIcon)icon).dataOut = (( FilterIcon)iconoSeri).dataOut;
                ((FilterIcon)icon).typeData = (( FilterIcon)iconoSeri).typeData;
                ((FilterIcon)icon).filterName = (( FilterIcon)iconoSeri).filterName;
                ((FilterIcon)icon).filterText = (( FilterIcon)iconoSeri).filterText;
                ((FilterIcon)icon).rangeValue = (( FilterIcon)iconoSeri).rangeValue; // solo lo que les compete a cada filtro
                ((FilterIcon)icon).optionRange = (( FilterIcon)iconoSeri).optionRange; // solo lo que les compete a cada filtro
                ((FilterIcon)icon).getMnuRun().setEnabled(true);
                ((FilterIcon)icon).getMnuView().setEnabled(true);
            }else if(nameIcon.equals("Replace Value")){
                icon = new FilterIcon(iconoSeri.getLabel(), iconoSeri.getX(), iconoSeri.getY(), iconoSeri.getIndiceIcon());
                icon.mapaConexionTo = iconoSeri.mapaConexionTo;
                icon.mapaConexionFrom = iconoSeri.mapaConexionFrom;
                ((FilterIcon)icon).dataIn = (( FilterIcon)iconoSeri).dataIn;
                ((FilterIcon)icon).dataOut = (( FilterIcon)iconoSeri).dataOut;
                ((FilterIcon)icon).typeData = (( FilterIcon)iconoSeri).typeData;
                ((FilterIcon)icon).filterName = (( FilterIcon)iconoSeri).filterName;
                ((FilterIcon)icon).filterText = (( FilterIcon)iconoSeri).filterText;
                ((FilterIcon)icon).columnSelected = (( FilterIcon)iconoSeri).columnSelected; // solo lo que les compete a cada filtro
                ((FilterIcon)icon).valuesAttribute = (( FilterIcon)iconoSeri).valuesAttribute; // solo lo que les compete a cada filtro
                ((FilterIcon)icon).replaceString = (( FilterIcon)iconoSeri).replaceString; // solo lo que les compete a cada filtro
//                ((FilterIcon)icon).attributeCount = (( FilterIcon)iconoSeri).attributeCount; // solo lo que les compete a cada filtro
                ((FilterIcon)icon).getMnuRun().setEnabled(true);
                ((FilterIcon)icon).getMnuView().setEnabled(true);
            }else if(nameIcon.equals("Numeric Range")){
                icon = new FilterIcon(iconoSeri.getLabel(), iconoSeri.getX(), iconoSeri.getY(), iconoSeri.getIndiceIcon());
                icon.mapaConexionTo = iconoSeri.mapaConexionTo;
                icon.mapaConexionFrom = iconoSeri.mapaConexionFrom;
                ((FilterIcon)icon).dataIn = (( FilterIcon)iconoSeri).dataIn;
                ((FilterIcon)icon).dataOut = (( FilterIcon)iconoSeri).dataOut;
                ((FilterIcon)icon).typeData = (( FilterIcon)iconoSeri).typeData;
                ((FilterIcon)icon).filterName = (( FilterIcon)iconoSeri).filterName;
                ((FilterIcon)icon).filterText = (( FilterIcon)iconoSeri).filterText;
                ((FilterIcon)icon).columnSelected = (( FilterIcon)iconoSeri).columnSelected; // solo lo que les compete a cada filtro
                ((FilterIcon)icon).minValue = (( FilterIcon)iconoSeri).minValue; // solo lo que les compete a cada filtro
                ((FilterIcon)icon).maxValue = (( FilterIcon)iconoSeri).maxValue; // solo lo que les compete a cada filtro
                ((FilterIcon)icon).getMnuRun().setEnabled(true);
                ((FilterIcon)icon).getMnuView().setEnabled(true);
            }else if(nameIcon.equals("Discretize")){
                icon = new FilterIcon(iconoSeri.getLabel(), iconoSeri.getX(), iconoSeri.getY(), iconoSeri.getIndiceIcon());
                icon.mapaConexionTo = iconoSeri.mapaConexionTo;
                icon.mapaConexionFrom = iconoSeri.mapaConexionFrom;
                ((FilterIcon)icon).dataIn = (( FilterIcon)iconoSeri).dataIn;
                ((FilterIcon)icon).dataOut = (( FilterIcon)iconoSeri).dataOut;
                ((FilterIcon)icon).typeData = (( FilterIcon)iconoSeri).typeData;
                ((FilterIcon)icon).filterName = (( FilterIcon)iconoSeri).filterName;
                ((FilterIcon)icon).filterText = (( FilterIcon)iconoSeri).filterText;                
                ((FilterIcon)icon).rangeValue = (( FilterIcon)iconoSeri).rangeValue;// solo lo que les compete a cada filtro
                ((FilterIcon)icon).optionRange = (( FilterIcon)iconoSeri).optionRange;// solo lo que les compete a cada filtro
                ((FilterIcon)icon).columnSelected = (( FilterIcon)iconoSeri).columnSelected;// solo lo que les compete a cada filtro
                ((FilterIcon)icon).getMnuRun().setEnabled(true);
                ((FilterIcon)icon).getMnuView().setEnabled(true);
            }else if(nameIcon.equals("Reduction")){
                icon = new FilterIcon(iconoSeri.getLabel(), iconoSeri.getX(), iconoSeri.getY(), iconoSeri.getIndiceIcon());
                icon.mapaConexionTo = iconoSeri.mapaConexionTo;
                icon.mapaConexionFrom = iconoSeri.mapaConexionFrom;
                ((FilterIcon)icon).dataIn = (( FilterIcon)iconoSeri).dataIn;
                ((FilterIcon)icon).dataOut = (( FilterIcon)iconoSeri).dataOut;
                ((FilterIcon)icon).typeData = (( FilterIcon)iconoSeri).typeData;
                ((FilterIcon)icon).filterName = (( FilterIcon)iconoSeri).filterName;
                ((FilterIcon)icon).filterText = (( FilterIcon)iconoSeri).filterText;                
                ((FilterIcon)icon).selRbtn = (( FilterIcon)iconoSeri).selRbtn; // solo lo que les compete a cada filtro
                ((FilterIcon)icon).rManRem = (( FilterIcon)iconoSeri).rManRem;// solo lo que les compete a cada filtro
                ((FilterIcon)icon).filIni = (( FilterIcon)iconoSeri).filIni;// solo lo que les compete a cada filtro
                ((FilterIcon)icon).filFin = (( FilterIcon)iconoSeri).filFin;// solo lo que les compete a cada filtro
                ((FilterIcon)icon).seal = (( FilterIcon)iconoSeri).seal;// solo lo que les compete a cada filtro
                ((FilterIcon)icon).minor = (( FilterIcon)iconoSeri).minor;// solo lo que les compete a cada filtro
                ((FilterIcon)icon).columnSelected = (( FilterIcon)iconoSeri).columnSelected;// solo lo que les compete a cada filtro
                ((FilterIcon)icon).valuesAttribute = (( FilterIcon)iconoSeri).valuesAttribute;// solo lo que les compete a cada filtro
                ((FilterIcon)icon).getMnuRun().setEnabled(true);
                ((FilterIcon)icon).getMnuView().setEnabled(true);
            } else if(nameIcon.equals("Selection")){
                icon = new FilterIcon(iconoSeri.getLabel(), iconoSeri.getX(), iconoSeri.getY(), iconoSeri.getIndiceIcon());
                icon.mapaConexionTo = iconoSeri.mapaConexionTo;
                icon.mapaConexionFrom = iconoSeri.mapaConexionFrom;
                ((FilterIcon)icon).dataIn = (( FilterIcon)iconoSeri).dataIn;
                ((FilterIcon)icon).dataOut = (( FilterIcon)iconoSeri).dataOut;
                ((FilterIcon)icon).typeData = (( FilterIcon)iconoSeri).typeData;
                ((FilterIcon)icon).filterName = (( FilterIcon)iconoSeri).filterName;
                ((FilterIcon)icon).filterText = (( FilterIcon)iconoSeri).filterText;                
                ((FilterIcon)icon).numberColumns = (( FilterIcon)iconoSeri).numberColumns; // solo lo que les compete a cada filtro
                ((FilterIcon)icon).columnsSelected = (( FilterIcon)iconoSeri).columnsSelected;// solo lo que les compete a cada filtro
                ((FilterIcon)icon).columnSelected = (( FilterIcon)iconoSeri).columnSelected;// solo lo que les compete a cada filtro
                ((FilterIcon)icon).getMnuRun().setEnabled(true);
                ((FilterIcon)icon).getMnuView().setEnabled(true);
            }else if(nameIcon.equals("Standardize")){
                icon = new StandarizeIcon(iconoSeri.getLabel(), iconoSeri.getX(), iconoSeri.getY(), iconoSeri.getIndiceIcon());
                icon.mapaConexionTo = iconoSeri.mapaConexionTo;
                icon.mapaConexionFrom = iconoSeri.mapaConexionFrom;
                ((StandarizeIcon)icon).algorithm = (( StandarizeIcon)iconoSeri).algorithm;
                ((StandarizeIcon)icon).dataIn = (( StandarizeIcon)iconoSeri).dataIn;
                ((StandarizeIcon)icon).c = (( StandarizeIcon)iconoSeri).c;
                ((StandarizeIcon)icon).root = (( StandarizeIcon)iconoSeri).root;
                ((StandarizeIcon)icon).minRows = (( StandarizeIcon)iconoSeri).minRows;
                ((StandarizeIcon)icon).trainingSet = (( StandarizeIcon)iconoSeri).trainingSet;
                ((StandarizeIcon)icon).dataOut1 = (( StandarizeIcon)iconoSeri).dataOut1;
                ((StandarizeIcon)icon).dataOut2 = (( StandarizeIcon)iconoSeri).dataOut2;
                ((StandarizeIcon)icon).dataSalida = (( StandarizeIcon)iconoSeri).dataSalida;
                
                ((StandarizeIcon)icon).threshold = (( StandarizeIcon)iconoSeri).threshold;
                ((StandarizeIcon)icon).lista = (( StandarizeIcon)iconoSeri).lista;
                ((StandarizeIcon)icon).numcluster = (( StandarizeIcon)iconoSeri).numcluster;
//                ((StandarizeIcon)icon).sqlCreado = (( StandarizeIcon)iconoSeri).sqlCreado;
//                ((StandarizeIcon)icon).sqlColumnas = (( StandarizeIcon)iconoSeri).sqlColumnas;
                ((StandarizeIcon)icon).distanciakm = (( StandarizeIcon)iconoSeri).distanciakm;
                ((StandarizeIcon)icon).colNumericalS = (( StandarizeIcon)iconoSeri).colNumericalS;
                ((StandarizeIcon)icon).colStandarizeS = (( StandarizeIcon)iconoSeri).colStandarizeS;               
                ((StandarizeIcon)icon).updtateIconStandarize();
                ((StandarizeIcon)icon).getMnuRun().setEnabled(true);
                ((StandarizeIcon)icon).getMnuView().setEnabled(true);
            }// Visualization
            else if(nameIcon.equals("Generator")){
                icon = new RulesIcon(iconoSeri.getLabel(), iconoSeri.getX(), iconoSeri.getY(), iconoSeri.getIndiceIcon());
                icon.mapaConexionTo = iconoSeri.mapaConexionTo;
                icon.mapaConexionFrom = iconoSeri.mapaConexionFrom;
                ((RulesIcon)icon).title = (( RulesIcon)iconoSeri).title;
                ((RulesIcon)icon).dataset = (( RulesIcon)iconoSeri).dataset;
                ((RulesIcon)icon).confidence = (( RulesIcon)iconoSeri).confidence;
                ((RulesIcon)icon).support = (( RulesIcon)iconoSeri).support;
                ((RulesIcon)icon).trees = (( RulesIcon)iconoSeri).trees;
                ((RulesIcon)icon).updateIconConfigureConfidence();
            }else if(nameIcon.equals("Text Tree")){
                icon = new TextTreeIcon(iconoSeri.getLabel(), iconoSeri.getX(), iconoSeri.getY(), iconoSeri.getIndiceIcon());
                icon.mapaConexionTo = iconoSeri.mapaConexionTo;
                icon.mapaConexionFrom = iconoSeri.mapaConexionFrom;
                ((TextTreeIcon)icon).RulesText = (( TextTreeIcon)iconoSeri).RulesText;
                ((TextTreeIcon)icon).dataTest = (( TextTreeIcon)iconoSeri).dataTest;
                ((TextTreeIcon)icon).root = (( TextTreeIcon)iconoSeri).root;
                ((TextTreeIcon)icon).texErrorM = (( TextTreeIcon)iconoSeri).texErrorM;
                ((TextTreeIcon)icon).updateIconViewerClasification();
                ((TextTreeIcon)icon).mnuView.setEnabled(true);
            }else if(nameIcon.equals("Hierarchical Tree")){
                icon = new HierarchicalTreeIcon(iconoSeri.getLabel(), iconoSeri.getX(), iconoSeri.getY(), iconoSeri.getIndiceIcon());
                icon.mapaConexionTo = iconoSeri.mapaConexionTo;
                icon.mapaConexionFrom = iconoSeri.mapaConexionFrom;
                ((HierarchicalTreeIcon)icon).RulesText = (( HierarchicalTreeIcon)iconoSeri).RulesText;
                ((HierarchicalTreeIcon)icon).dataTest = (( HierarchicalTreeIcon)iconoSeri).dataTest;
                ((HierarchicalTreeIcon)icon).root = (( HierarchicalTreeIcon)iconoSeri).root;
                ((HierarchicalTreeIcon)icon).texErrorM = (( HierarchicalTreeIcon)iconoSeri).texErrorM;
                ((HierarchicalTreeIcon)icon).updateIconViewerClasification();
                ((HierarchicalTreeIcon)icon).mnuView.setEnabled(true);
            }else if(nameIcon.equals(" Weka Tree ")){
                icon = new WekaTreeIcon(iconoSeri.getLabel(), iconoSeri.getX(), iconoSeri.getY(), iconoSeri.getIndiceIcon());
                icon.mapaConexionTo = iconoSeri.mapaConexionTo;
                icon.mapaConexionFrom = iconoSeri.mapaConexionFrom;
                ((WekaTreeIcon)icon).RulesText = (( WekaTreeIcon)iconoSeri).RulesText;
                ((WekaTreeIcon)icon).dataTest = (( WekaTreeIcon)iconoSeri).dataTest;
                ((WekaTreeIcon)icon).root = (( WekaTreeIcon)iconoSeri).root;
                ((WekaTreeIcon)icon).texErrorM = (( WekaTreeIcon)iconoSeri).texErrorM;
                ((WekaTreeIcon)icon).updateIconViewerClasification();
                ((WekaTreeIcon)icon).mnuView.setEnabled(true);
            }else if(nameIcon.equals("Prediction")){
                icon = new PredictionIcon(iconoSeri.getLabel(), iconoSeri.getX(), iconoSeri.getY(), iconoSeri.getIndiceIcon());
                icon.mapaConexionTo = iconoSeri.mapaConexionTo;
                icon.mapaConexionFrom = iconoSeri.mapaConexionFrom;
                ((PredictionIcon)icon).root = (( PredictionIcon)iconoSeri).root;
                ((PredictionIcon)icon).dataIn = (( PredictionIcon)iconoSeri).dataIn;
                ((PredictionIcon)icon).p = (( PredictionIcon)iconoSeri).p;
                ((PredictionIcon)icon).updateIconViewPrediction();
                ((PredictionIcon)icon).mnuView.setEnabled(true);
            }else if(nameIcon.equals("Tabs")){
                icon = new Iconopestana(iconoSeri.getLabel(), iconoSeri.getX(), iconoSeri.getY(), iconoSeri.getIndiceIcon());
                icon.mapaConexionTo = iconoSeri.mapaConexionTo;
                icon.mapaConexionFrom = iconoSeri.mapaConexionFrom;
                ((Iconopestana)icon).algorithm = (( Iconopestana)iconoSeri).algorithm;
                ((Iconopestana)icon).dataIn = (( Iconopestana)iconoSeri).dataIn;
                ((Iconopestana)icon).dataSalidad = (( Iconopestana)iconoSeri).dataSalidad;
                ((Iconopestana)icon).c = (( Iconopestana)iconoSeri).c;
                ((Iconopestana)icon).root = (( Iconopestana)iconoSeri).root;
                ((Iconopestana)icon).minRows = (( Iconopestana)iconoSeri).minRows;
                ((Iconopestana)icon).trainingSet = (( Iconopestana)iconoSeri).trainingSet;
                ((Iconopestana)icon).dataOut2 = (( Iconopestana)iconoSeri).dataOut2;
                ((Iconopestana)icon).threshold = (( Iconopestana)iconoSeri).threshold;
                ((Iconopestana)icon).lista = (( Iconopestana)iconoSeri).lista;
                ((Iconopestana)icon).liscluster = (( Iconopestana)iconoSeri).liscluster;
                ((Iconopestana)icon).medoide = (( Iconopestana)iconoSeri).medoide;
                ((Iconopestana)icon).centroideCerca = (( Iconopestana)iconoSeri).centroideCerca;
                ((Iconopestana)icon).numcluster = (( Iconopestana)iconoSeri).numcluster;
                ((Iconopestana)icon).atriColumnas = (( Iconopestana)iconoSeri).atriColumnas;
                ((Iconopestana)icon).distanciakm = (( Iconopestana)iconoSeri).distanciakm;
            }else if(nameIcon.equals("Graph Cluster")){
                icon = new IconoGrafica(iconoSeri.getLabel(), iconoSeri.getX(), iconoSeri.getY(), iconoSeri.getIndiceIcon());
                icon.mapaConexionTo = iconoSeri.mapaConexionTo;
                icon.mapaConexionFrom = iconoSeri.mapaConexionFrom;
                ((IconoGrafica)icon).algorithm = (( IconoGrafica)iconoSeri).algorithm;
                ((IconoGrafica)icon).dataIn = (( IconoGrafica)iconoSeri).dataIn;
                ((IconoGrafica)icon).dataSalidad = (( IconoGrafica)iconoSeri).dataSalidad;
                ((IconoGrafica)icon).c = (( IconoGrafica)iconoSeri).c;
                ((IconoGrafica)icon).root = (( IconoGrafica)iconoSeri).root;
                ((IconoGrafica)icon).minRows = (( IconoGrafica)iconoSeri).minRows;
                ((IconoGrafica)icon).trainingSet = (( IconoGrafica)iconoSeri).trainingSet;
                ((IconoGrafica)icon).dataOut2 = (( IconoGrafica)iconoSeri).dataOut2;
                ((IconoGrafica)icon).threshold = (( IconoGrafica)iconoSeri).threshold;
                ((IconoGrafica)icon).lista = (( IconoGrafica)iconoSeri).lista;
                ((IconoGrafica)icon).liscluster = (( IconoGrafica)iconoSeri).liscluster;
                ((IconoGrafica)icon).medoide = (( IconoGrafica)iconoSeri).medoide;
                ((IconoGrafica)icon).centroideCerca = (( IconoGrafica)iconoSeri).centroideCerca;
                ((IconoGrafica)icon).numcluster = (( IconoGrafica)iconoSeri).numcluster;
                ((IconoGrafica)icon).atriColumnas = (( IconoGrafica)iconoSeri).atriColumnas;
                ((IconoGrafica)icon).distanciakm = (( IconoGrafica)iconoSeri).distanciakm;
            }else if(nameIcon.equals("Birch Tree")){
                icon = new IconoArbol(iconoSeri.getLabel(), iconoSeri.getX(), iconoSeri.getY(), iconoSeri.getIndiceIcon());
                icon.mapaConexionTo = iconoSeri.mapaConexionTo;
                icon.mapaConexionFrom = iconoSeri.mapaConexionFrom;
                ((IconoArbol)icon).algorithm = (( IconoArbol)iconoSeri).algorithm;
                ((IconoArbol)icon).dataIn = (( IconoArbol)iconoSeri).dataIn;
                ((IconoArbol)icon).dataSalidad = (( IconoArbol)iconoSeri).dataSalidad;
                ((IconoArbol)icon).c = (( IconoArbol)iconoSeri).c;
                ((IconoArbol)icon).root = (( IconoArbol)iconoSeri).root;
                ((IconoArbol)icon).minRows = (( IconoArbol)iconoSeri).minRows;
                ((IconoArbol)icon).trainingSet = (( IconoArbol)iconoSeri).trainingSet;
                ((IconoArbol)icon).dataOut2 = (( IconoArbol)iconoSeri).dataOut2;
                ((IconoArbol)icon).threshold = (( IconoArbol)iconoSeri).threshold;
                ((IconoArbol)icon).lista = (( IconoArbol)iconoSeri).lista;
                ((IconoArbol)icon).liscluster = (( IconoArbol)iconoSeri).liscluster;
                ((IconoArbol)icon).medoide = (( IconoArbol)iconoSeri).medoide;
                ((IconoArbol)icon).centroideCerca = (( IconoArbol)iconoSeri).centroideCerca;
                ((IconoArbol)icon).numcluster = (( IconoArbol)iconoSeri).numcluster;
                ((IconoArbol)icon).atriColumnas = (( IconoArbol)iconoSeri).atriColumnas;
                ((IconoArbol)icon).raiz = (( IconoArbol)iconoSeri).raiz;
                ((IconoArbol)icon).distanciakm = (( IconoArbol)iconoSeri).distanciakm;
            }
            
            add(icon);
            icon.setBounds(icon.getLocation().x, icon.getLocation().y,icon.getPreferredSize().width,icon.getPreferredSize().height);
            icon.setBackground(new Color(0, 0, 0, 0)); //transparencia en el icono.
        }
       
        putConexions();
        contIcons = iconosS.length + 1;
        repaint();
    }
        
        public void putConexions() {

        Icon ic1 = null;
        Icon ic2 = null;
        Iterator it;
        Iterator it2;
        Conector conector1 = null;
        Conector conector2 = null;

        conexiones.clear();

        for(int i=0; i<getComponentCount(); i++){

           ic1 =  (Icon) this.getComponent(i);

           it = ic1.mapaConexionTo.entrySet().iterator();

           while (it.hasNext()) {
               Map.Entry e = (Map.Entry)it.next();
               System.out.println("To: " + e.getKey() + " bolita: " + e.getValue());


               conector1 = (Conector) ic1.conectores[Integer.parseInt(e.getValue().toString())];
               conector1.seleccionado = true;

//               toca buscar con el e.getKey el nodo al que se conecta y una vez encontrado asignar a conector2 su respectivo conector
                       
                       
               for(int j=0; j<getComponentCount(); j++){

                  ic2 =  (Icon) this.getComponent(j);

                  if(e.getKey().equals(ic2.indiceIcon)){

                      it2 = ic2.mapaConexionFrom.entrySet().iterator();

                      while (it2.hasNext()) {
                           Map.Entry e2 = (Map.Entry)it2.next();
                           if(e2.getKey().equals(ic1.indiceIcon)){
                              System.out.println("From: " + e2.getKey() + " bolita: " + e2.getValue());
                              conector2 = (Conector) ic2.conectores[Integer.parseInt(e2.getValue().toString())];
                              conector2.seleccionado = true;

                              conexiones.add(new Conexion(conector1, conector2));
                          }
                      }
                   }
               }
            }
        }
    }
        
        public ArrayList getConexionSeri() {
        System.out.println("conexiones antes: " + conexiones.toString());
        return this.conexiones;
    }

    public void showComponentes() {
        System.out.println("Componentes en el container");
        for(int i=0; i<this.getComponentCount(); i++){
            System.out.println(this.getComponent(i));
        }
    }        

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setName("miCanvas"); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 500));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        setLayout(null);
    }// </editor-fold>//GEN-END:initComponents
    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        //aqui es cuando hace clic en el puro canvas
        int x = evt.getXOnScreen() - this.getLocationOnScreen().x;
        int y = evt.getYOnScreen() - this.getLocationOnScreen().y;
        Component press = this.findComponentAt(x, y);
//        Component press = this.getComponentAt(x, y);
        
//        System.out.println(press.getName());
        if(press instanceof MyIcon){
            seleccionado = (Icon)press.getParent();
            if(evt.getButton() == evt.BUTTON2 || evt.getButton() == evt.BUTTON3){
                seleccionado.getPupMenu().show(evt.getComponent(), evt.getX(), evt.getY());
            }
            seleccionado = null;
        } else if(press instanceof Conector){
            Conector conectorPress = (Conector)press;
            if(conectorPress.seleccionado){
                this.removeConector(conectorPress);
            }
        }
    }//GEN-LAST:event_formMouseClicked

    public void getMouseReleased(java.awt.event.MouseEvent evt) {
        this.formMouseReleased(evt);
    }

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
// TODO add your handling code here:
        //Creo que toca reconocer el elemento presionado en el formmouseclc
        
        int x = evt.getXOnScreen() - this.getLocationOnScreen().x;
        int y = evt.getYOnScreen() - this.getLocationOnScreen().y;
        Component pressed = this.findComponentAt(x, y);
        
        try {
            if (pressed instanceof Conector && conectorpressed != null) {
                Conector nuevopressed = (Conector) pressed;
                if (!conectorpressed.getParent().equals(nuevopressed.getParent())) {
                    
                    Icon from = ((Icon) conectorpressed.getParent());
                    Icon to = ((Icon) nuevopressed.getParent());
                    
                    // se mapea las conexiones con el indice del icono con el que se conecta y el nombre de su conector
                    from.mapaConexionTo.put(to.getIndiceIcon(),conectorpressed.getName()); //(conquien_se_conecta,de_cual_conector)
                    to.mapaConexionFrom.put(from.getIndiceIcon(),nuevopressed.getName());
//                    System.out.println("bolitaaaaaaaaa" + nuevopressed.getName());
                    
                    if (from.constrainsTo.contains(to.getIconType())) {
                        to.froms.add(from);
                        from.tos.add(to);
                        if (from instanceof DBConnectionIcon && to instanceof AssociationIcon) {
                            if (((DBConnectionIcon) from).dataset == null) {
                                ChooserEscritorio.setStatus("There are not loaded data in " + from.getIconType() + "...");
                            } else {
                                ((AssociationIcon) to).dataset = ((DBConnectionIcon) from).dataset;
                                ((AssociationIcon) to).getMnuRun().setEnabled(true);
                                nuevopressed.seleccionado = true;
                                conexiones.add(new Conexion(conectorpressed, nuevopressed));
                                conectorpressed = null;
                            }
                        } else if (from instanceof FilterIcon && to instanceof AssociationIcon) {
                            if (((FilterIcon) from).dataOut == null) {
                                ChooserEscritorio.setStatus("There are not loaded data in " + from.getIconType() + "...");
                            } else {
                                ((AssociationIcon) to).dataset = ((FilterIcon) from).buildDataSet();
                                ((AssociationIcon) to).getMnuRun().setEnabled(true);
                                nuevopressed.seleccionado = true;
                                conexiones.add(new Conexion(conectorpressed, nuevopressed));
                                conectorpressed = null;
                            }
                        } else if (from instanceof AssociationIcon && to instanceof RulesIcon) {
                            if (((AssociationIcon) from).trees == null || ((AssociationIcon) from).dataset == null) {
                                ChooserEscritorio.setStatus("There are not loaded data in " + from.getIconType() + "...");
                            } else {
                                ((RulesIcon) to).trees = ((AssociationIcon) from).trees;
                                ((RulesIcon) to).dataset = ((AssociationIcon) from).dataset;
                                ((RulesIcon) to).support = ((AssociationIcon) from).support;
                                ((RulesIcon) to).title = ((AssociationIcon) from).icono.getText();
                                nuevopressed.seleccionado = true;
                                conexiones.add(new Conexion(conectorpressed, nuevopressed));
                                conectorpressed = null;
                            }
                        } else if (from instanceof DBConnectionIcon && to instanceof ClasificationIcon) {
                            if (((DBConnectionIcon) from).connectionTableModel == null) {
                                ChooserEscritorio.setStatus("There are not loaded data in " + from.getIconType() + "...");
                            } else {
                                ((ClasificationIcon) to).dataIn = ((DBConnectionIcon) from).connectionTableModel;
                                nuevopressed.seleccionado = true;
                                conexiones.add(new Conexion(conectorpressed, nuevopressed));
                                conectorpressed = null;
                            }
                        } else if (from instanceof DBConnectionIcon && to instanceof FilterIcon) {
                            if (((DBConnectionIcon) from).connectionTableModel == null) {
                                ChooserEscritorio.setStatus("There are not loaded data in " + from.getIconType() + "...");
                            } else {
                                ((FilterIcon) to).dataIn = ((DBConnectionIcon) from).connectionTableModel;
                                ((FilterIcon) to).setValuesByDefault();
                                nuevopressed.seleccionado = true;
                                conexiones.add(new Conexion(conectorpressed, nuevopressed));
                                conectorpressed = null;
                            }
                        } else if (from instanceof DBConnectionIcon && to instanceof DRIcon) {
                            
                            if(!((DRIcon)to).getName().equals("MDS")){ // A MDS se le debe pasar una matriz cuadrada de distancias
                                
                                // para validar que solo ingresen datos cuantitativos
                                boolean bdCuanti = true;
                                for(int j = 0; j < ((DBConnectionIcon) from).connectionTableModel.getColumnCount(); j++){
                                   if(((DBConnectionIcon) from).connectionTableModel.getColumnClass(j).toString().equals("class java.lang.String")){
                                       bdCuanti = false;
                                   }
                                }

                               if(bdCuanti){
                                
                                    double[][] data = null;
                                    if (((DBConnectionIcon) from).connectionTableModel == null) {
                                        ChooserEscritorio.setStatus("There are not loaded data in " + from.getIconType() + "...");
                                    }else {
                                        data = new double[((DBConnectionIcon) from).connectionTableModel.getRowCount()][((DBConnectionIcon) from).connectionTableModel.getColumnCount()];

                                        // datos                                                     
                                        for(int i = 0; i < ((DBConnectionIcon) from).connectionTableModel.getRowCount(); i++){
                                            for(int j = 0; j < ((DBConnectionIcon) from).connectionTableModel.getColumnCount(); j++){
                                                 data[i][j] = Double.parseDouble(((DBConnectionIcon) from).connectionTableModel.getValueAt(i, j).toString()) ;              
                                            }   
                                        }
                                    }
                                    
                                    ((DRIcon)to).dataIn = data;                                   
                                    
                                    if(((DRIcon)to).getName().equals("KMDS")){ //KMDS no se configura, se ejecuta directamente
                                        ((DRIcon) to).getMnuRun().setEnabled(true);
                                    }else{
                                        ((DRIcon) to).getMnuConfigure().setEnabled(true);  
                                        
                                    }

                                     nuevopressed.seleccionado = true;
                                     conexiones.add(new Conexion(conectorpressed, nuevopressed));
                                     conectorpressed = null;
                                     
                                }else{
                                    JOptionPane.showMessageDialog(this, "Only quantitative attributes are allowed", "VisMineDR", JOptionPane.ERROR_MESSAGE);
                                }
                                 
                            }else{
                                    JOptionPane.showMessageDialog(this, "You can not connect directly, use the filter selection", "VisMineDR", JOptionPane.ERROR_MESSAGE);
                            }
                                
                        }else if (from instanceof FilterIcon && to instanceof FilterIcon) {
                            if (((FilterIcon) from).dataOut == null) {
                                ChooserEscritorio.setStatus("There are not loaded data in " + from.getIconType() + "...");
                            } else {
                                ((FilterIcon) to).dataIn = ((FilterIcon) from).dataOut;
                                ((FilterIcon) to).setValuesByDefault();
                                nuevopressed.seleccionado = true;
                                conexiones.add(new Conexion(conectorpressed, nuevopressed));
                                conectorpressed = null;
                            }
                        } else if (from instanceof FilterIcon && to instanceof ClasificationIcon) {
                            if (((FilterIcon) from).dataOut == null) {
                                ChooserEscritorio.setStatus("There are not loaded data in " + from.getIconType() + "...");
                            } else {
                                ((ClasificationIcon) to).dataIn = ((FilterIcon) from).dataOut;
                                nuevopressed.seleccionado = true;
                                conexiones.add(new Conexion(conectorpressed, nuevopressed));
                                conectorpressed = null;
                            }
                        } else if (from instanceof FileIcon && to instanceof AssociationIcon) {
                            if (((FileIcon) from).dataset == null) {
                                ChooserEscritorio.setStatus("There are not loaded data in " + from.getIconType() + "...");
                            } else {
                                ((AssociationIcon) to).dataset = ((FileIcon) from).dataset;
//                                ((AssociationIcon) to).getMnuRun().setEnabled(true);
                                nuevopressed.seleccionado = true;
                                conexiones.add(new Conexion(conectorpressed, nuevopressed));
                                conectorpressed = null;
                            }
                        } else if (from instanceof FileIcon && to instanceof FilterIcon) {
                            if (((FileIcon) from).data == null) {
                                ChooserEscritorio.setStatus("There are not loaded data in " + from.getIconType() + "...");
                            } else {
                                ((FilterIcon) to).dataIn = ((FileIcon) from).data;
                                ((FilterIcon) to).setValuesByDefault();
                                nuevopressed.seleccionado = true;
                                conexiones.add(new Conexion(conectorpressed, nuevopressed));
                                conectorpressed = null;
                            }
                        } else if (from instanceof FileIcon && to instanceof PredictionIcon) {
                            if (((FileIcon) from).data == null) {
                                ChooserEscritorio.setStatus("There are not loaded data in " + from.getIconType() + "...");
                            } else {
                                ((PredictionIcon) to).dataIn = ((FileIcon) from).data;
                                nuevopressed.seleccionado = true;
                                conexiones.add(new Conexion(conectorpressed, nuevopressed));
                                conectorpressed = null;
                            }
                        } else if (from instanceof ClasificationIcon && to instanceof HierarchicalTreeIcon) {
                            if (((ClasificationIcon) from).root == null ||
                                    ((ClasificationIcon) from).dataOut2 == null) {
                                ChooserEscritorio.setStatus("There are not loaded data in " + from.getIconType() + "...");
                            } else {
                                ((HierarchicalTreeIcon) to).root = ((ClasificationIcon) from).root;
                                ((HierarchicalTreeIcon) to).dataTest = ((ClasificationIcon) from).dataOut2;
                                nuevopressed.seleccionado = true;
                                conexiones.add(new Conexion(conectorpressed, nuevopressed));
                                conectorpressed = null;
                            }
                        } else if (from instanceof ClasificationIcon && to instanceof WekaTreeIcon) {
                            if (((ClasificationIcon) from).root == null ||
                                    ((ClasificationIcon) from).dataOut2 == null) {
                                ChooserEscritorio.setStatus("There are not loaded data in " + from.getIconType() + "...");
                            } else {
                                ((WekaTreeIcon) to).root = ((ClasificationIcon) from).root;
                                ((WekaTreeIcon) to).dataTest = ((ClasificationIcon) from).dataOut2;
                                nuevopressed.seleccionado = true;
                                conexiones.add(new Conexion(conectorpressed, nuevopressed));
                                conectorpressed = null;
                            }
                        } else if (from instanceof ClasificationIcon && to instanceof TextTreeIcon) {
                            if (((ClasificationIcon) from).root == null ||
                                    ((ClasificationIcon) from).dataOut2 == null) {
                                ChooserEscritorio.setStatus("There are not loaded data in " + from.getIconType() + "...");
                            } else {
                                ((TextTreeIcon) to).root = ((ClasificationIcon) from).root;
                                ((TextTreeIcon) to).dataTest = ((ClasificationIcon) from).dataOut2;
                                nuevopressed.seleccionado = true;
                                conexiones.add(new Conexion(conectorpressed, nuevopressed));
                                conectorpressed = null;
                            }
                        } else if (from instanceof ClasificationIcon && to instanceof PredictionIcon) {
                            if (((ClasificationIcon) from).root == null) {
                                ChooserEscritorio.setStatus("There are not loaded data in " + from.getIconType() + "...");
                            } else {
                                ((PredictionIcon) to).root = ((ClasificationIcon) from).root;
                                nuevopressed.seleccionado = true;
                                conexiones.add(new Conexion(conectorpressed, nuevopressed));
                                conectorpressed = null;
                            }
                        } else if (from instanceof DBConnectionIcon && to instanceof PredictionIcon) {
                            if (((DBConnectionIcon) from).connectionTableModel == null) {
                                ChooserEscritorio.setStatus("There are not loaded data in " + from.getIconType() + "...");
                            } else {
                                ((PredictionIcon) to).dataIn = ((DBConnectionIcon) from).connectionTableModel;
                                nuevopressed.seleccionado = true;
                                conexiones.add(new Conexion(conectorpressed, nuevopressed));
                                conectorpressed = null;
                            }
                        }else if(from instanceof DBConnectionIcon && to instanceof vmdIcon){
                            if(((DBConnectionIcon)from).connectionTableModel == null){
                                ChooserEscritorio.setStatus("There are not loaded data in " + from.getIconType() + "...");
                            } else {
                                (( vmdIcon)to).dataIn = ((DBConnectionIcon)from).connectionTableModel;
                                nuevopressed.seleccionado = true;
                                conexiones.add(new Conexion(conectorpressed, nuevopressed));
                                conectorpressed = null;
                            }
                        }else if(from instanceof FileIcon && to instanceof vmdIcon){
                            if(((FileIcon)from).data == null){
                                ChooserEscritorio.setStatus("There are not loaded data in " + from.getIconType() + "...");
                            } else {
                                (( vmdIcon)to).dataIn = ((FileIcon)from).data;
                                nuevopressed.seleccionado = true;
                                conexiones.add(new Conexion(conectorpressed, nuevopressed));
                                conectorpressed = null;
                            }
                        }else if(from instanceof FilterIcon && to instanceof vmdIcon){
                            if(((FilterIcon)from).dataOut == null){
                                ChooserEscritorio.setStatus("There are not loaded data in " + from.getIconType() + "...");
                            } else {
                                (( vmdIcon)to).dataIn = ((FilterIcon)from).dataOut;
                                nuevopressed.seleccionado = true;
                                conexiones.add(new Conexion(conectorpressed, nuevopressed));
                                conectorpressed = null;
                             }
                         }//lo nuevo
                          else if(from instanceof DBConnectionIcon && to instanceof clusterIcon){
                            if(((DBConnectionIcon) from).connectionTableModel == null){
                                ChooserEscritorio.setStatus("Los Datos no han sido Cargados en " + from.getIconType() + "...");
                            } else {
                                ((clusterIcon) to).dataIn = ((DBConnectionIcon) from).connectionTableModel;
                                 ((clusterIcon) to).conexion = ((DBConnectionIcon) from).connection;
                                  ((clusterIcon) to).sqlCreado= ((DBConnectionIcon) from).sqlCreado;
                                  ((clusterIcon) to).sqlColumnas= ((DBConnectionIcon) from).sqlColumnas;
                                nuevopressed.seleccionado = true;
                                conexiones.add(new Conexion(conectorpressed, nuevopressed));
                                conectorpressed = null;                               
                            }
                          }
                          else if(from instanceof FileIcon && to instanceof clusterIcon){
                            if(((FileIcon) from).data == null){
                                ChooserEscritorio.setStatus("Los Datos no han sido Cargados en " + from.getIconType() + "...");
                            } else {
                                ((clusterIcon) to).dataIn = ((FileIcon) from).data;
//                                 ((clusterIcon) to).conexion = ((FileIcon) from).connection;
//                                  ((clusterIcon) to).sqlCreado= ((FileIcon) from).sqlCreado;
//                                  ((clusterIcon) to).sqlColumnas= ((FileIcon) from).sqlColumnas;
                                nuevopressed.seleccionado = true;
                                conexiones.add(new Conexion(conectorpressed, nuevopressed));
                                conectorpressed = null;                               
                            }
                        }else if(from instanceof clusterIcon && to instanceof Iconopestana){
                            if(((clusterIcon)from).getAlgorithm().equalsIgnoreCase("Birch")){
                                ChooserEscritorio.setStatus("Cannot Connect to" + to.getIconType());
                            }else if(((clusterIcon) from).dataIn == null){
                                ChooserEscritorio.setStatus("lista No han sido cargados los datos en " + from.getIconType() + "...");
                            }else {
                                ((Iconopestana) to).dataIn = ((clusterIcon) from).dataOri;
                                ((Iconopestana) to).numcluster = ((clusterIcon) from).numcluster;
                             //   ((Iconopestana) to).distanciakm = ((Iconokmeans) from).distanciakm;
                                ((Iconopestana) to).liscluster = ((clusterIcon) from).listacluster;
                                ((Iconopestana) to).medoide = ((clusterIcon) from).medoide;
                                ((Iconopestana) to).centroideCerca = ((clusterIcon) from).centroideCerca;
                                ((Iconopestana) to).dataSalidad = ((clusterIcon) from).dataIn;
                                
                                nuevopressed.seleccionado = true;
                                conexiones.add(new Conexion(conectorpressed, nuevopressed));
                                conectorpressed = null;
                            }
                        }else if(from instanceof clusterIcon && to instanceof IconoGrafica){
                            if(((clusterIcon)from).getAlgorithm().equalsIgnoreCase("Birch")){
                                ChooserEscritorio.setStatus("Cannot Connect to" + to.getIconType());
                            }else if(((clusterIcon) from).dataIn == null){
                                ChooserEscritorio.setStatus("lista No han sido cargados los datos en " + from.getIconType() + "...");
                            }else {
                                ((IconoGrafica) to).dataIn = ((clusterIcon) from).dataOri;
                                ((IconoGrafica) to).numcluster = ((clusterIcon) from).numcluster;
                             //   ((Iconopestana) to).distanciakm = ((Iconokmeans) from).distanciakm;
                                ((IconoGrafica) to).liscluster = ((clusterIcon) from).listacluster;
                                ((IconoGrafica) to).medoide = ((clusterIcon) from).medoide;
                                ((IconoGrafica) to).centroideCerca = ((clusterIcon) from).centroideCerca;
                                ((IconoGrafica) to).dataSalidad = ((clusterIcon) from).dataIn;
                                
                                nuevopressed.seleccionado = true;
                                conexiones.add(new Conexion(conectorpressed, nuevopressed));
                                conectorpressed = null;
                            }
                        }else if(from instanceof clusterIcon && to instanceof IconoArbol){
                            if(!((clusterIcon)from).getAlgorithm().equalsIgnoreCase("Birch")){
                                ChooserEscritorio.setStatus("Cannot Connect to" + to.getIconType());
                            }else if(((clusterIcon) from).raiz == null){
                                ChooserEscritorio.setStatus("No han sido cargados los datos en " + from.getIconType() + "...");
                            }else {       
                                ((IconoArbol) to).numcluster =  ((clusterIcon) from).numcluster;
                                //((IconoArbol) to).distanciakm = ((Iconokmeans) from).distanciakm;
                                ((IconoArbol) to).raiz = ((clusterIcon) from).raiz;
                       
                                nuevopressed.seleccionado = true;
                                conexiones.add(new Conexion(conectorpressed, nuevopressed));
                                conectorpressed = null;
                            }
                            
                        }else if(from instanceof FileIcon && to instanceof StandarizeIcon){
                            if(((FileIcon) from).data == null){
                                ChooserEscritorio.setStatus("Los Datos no han sido Cargados en " + from.getIconType() + "...");
                            } else {
                                ((StandarizeIcon) to).dataIn = ((FileIcon) from).data;
//                                 ((StandarizeIcon) to).conexion = ((FileIcon) from).connection;
//                                  ((StandarizeIcon) to).sqlCreado= ((FileIcon) from).sqlCreado;
//                                  ((StandarizeIcon) to).sqlColumnas= ((FileIcon) from).sqlColumnas;
                                nuevopressed.seleccionado = true;
                                conexiones.add(new Conexion(conectorpressed, nuevopressed));
                                conectorpressed = null;    
                            }
                        }else if(from instanceof DBConnectionIcon && to instanceof StandarizeIcon){
                            if(((DBConnectionIcon) from).connectionTableModel == null){
                                ChooserEscritorio.setStatus("Los Datos no han sido Cargados en " + from.getIconType() + "...");
                            } else {
                                ((StandarizeIcon) to).dataIn = ((DBConnectionIcon) from).connectionTableModel;
                                ((StandarizeIcon) to).conexion = ((DBConnectionIcon) from).connection;
                                ((StandarizeIcon) to).sqlCreado= ((DBConnectionIcon) from).sqlCreado;
                                ((StandarizeIcon) to).sqlColumnas= ((DBConnectionIcon) from).sqlColumnas;
                                nuevopressed.seleccionado = true;
                                conexiones.add(new Conexion(conectorpressed, nuevopressed));
                                conectorpressed = null; 
                            }
                        }else if (from instanceof FilterIcon && to instanceof StandarizeIcon) {
                            if (((FilterIcon) from).dataOut == null) {
                                ChooserEscritorio.setStatus("There are not loaded data in " + from.getIconType() + "...");
                            } else { 
                                ((StandarizeIcon) to).dataIn = ((FilterIcon) from).dataOut;
                                nuevopressed.seleccionado = true;
                                conexiones.add(new Conexion(conectorpressed, nuevopressed));
                                conectorpressed = null; 
                            }
                        }else if (from instanceof StandarizeIcon && to instanceof FilterIcon) {
                            if (((StandarizeIcon) from).dataIn== null) {
                                ChooserEscritorio.setStatus("There are not loaded data in " + from.getIconType() + "...");
                            } else { 
                                ((FilterIcon) to).dataIn = ((StandarizeIcon) from).dataIn;
                                nuevopressed.seleccionado = true;
                                conexiones.add(new Conexion(conectorpressed, nuevopressed));
                                conectorpressed = null; 
                            }
                        }else if(from instanceof StandarizeIcon && to instanceof clusterIcon){
                              
                            if(((StandarizeIcon) from).dataIn == null){
                                ChooserEscritorio.setStatus("No han sido cargados los datos en " + from.getIconType() + "...");
                            } else {
                                ((clusterIcon) to).root = ((StandarizeIcon) from).root;
                                ((clusterIcon) to).lista = ((StandarizeIcon)from).lista;
                                ((clusterIcon) to).distanciakm = ((StandarizeIcon)from).distanciakm;
                                ((clusterIcon) to).dataIn = ((StandarizeIcon)from).dataSalida;
                                ((clusterIcon) to).dataOri = ((StandarizeIcon)from).dataIn;
                                
                                nuevopressed.seleccionado = true;
                                conexiones.add(new Conexion(conectorpressed, nuevopressed));
                                conectorpressed = null;
                            }
                        }else if (from instanceof FileIcon && to instanceof DRIcon) {
                            
//                            if(!((DRIcon)to).getName().equals("MDS")){ // A MDS se le debe pasar una matriz cuadrada de distancias
                                
                                // para validar que solo ingresen datos cuantitativos
                                boolean bdCuanti = true;
                                for(int j = 0; j < ((FileIcon) from).data.getColumnCount(); j++){
                                   if(((FileIcon) from).data.getColumnClass(j).toString().equals("class java.lang.String")){
                                       bdCuanti = false;
                                   }
                                }

                               if(bdCuanti){
                             
                                    double[][] data = null;
                                    String[] atributos = null;
                                    if (((FileIcon) from).data == null) {
                                        ChooserEscritorio.setStatus("There are not loaded data in " + from.getIconType() + "...");
                                    }else {   
                                        data = new double[((FileIcon) from).data.getRowCount()][((FileIcon) from).data.getColumnCount()];
                                        
                                        // datos                                                     
                                        for(int i = 0; i < ((FileIcon) from).data.getRowCount(); i++){
                                            for(int j = 0; j < ((FileIcon) from).data.getColumnCount(); j++){
                                                 data[i][j] = Double.parseDouble(((FileIcon)from).data.getValueAt(i, j).toString()) ;              
                                            }   
                                        }
                                        
                                        // atributos   
                                        atributos = new String[((FileIcon) from).data.getColumnCount()];
                                        for(int j = 0; j < ((FileIcon) from).data.getColumnCount(); j++){      
                                              atributos[j] = ((FileIcon) from).data.getColumnName(j);
                                        }
                                    }
                                    
                                    ((DRIcon)to).dataIn = data;   
                                    ((DRIcon) to).atributos = atributos;
                                    
                                    if(((DRIcon)to).getName().equals("KMDS")){ //KMDS no se configura, se ejecuta directamente
                                        ((DRIcon) to).getMnuRun().setEnabled(true);
                                    }else{
                                        ((DRIcon) to).getMnuConfigure().setEnabled(true);  
                                        
                                    }

                                     nuevopressed.seleccionado = true;
                                     conexiones.add(new Conexion(conectorpressed, nuevopressed));
                                     conectorpressed = null;
                                     
                                }else{
                                    JOptionPane.showMessageDialog(this, "Only quantitative attributes are allowed", "VisMineDR", JOptionPane.ERROR_MESSAGE);
                                }
                                 
//                            }else{
//                                    JOptionPane.showMessageDialog(this, "You can not connect directly, use the filter selection", "VisMineDR", JOptionPane.ERROR_MESSAGE);
//                            }
                                
                        }else if (from instanceof FileIcon && to instanceof ScatterIcon) {
                            
                            // para validar que solo ingresen datos cuantitativos
                            boolean bdCuanti = true;
                            for(int j = 0; j < ((FileIcon) from).data.getColumnCount(); j++){
                               if(((FileIcon) from).data.getColumnClass(j).toString().equals("class java.lang.String")){
                                   bdCuanti = false;
                               }
                            }
                           
                           if(bdCuanti){
 
                               double[][] data = null;
                               String[] atributos = null;
                                if (((FileIcon) from).data == null) {
                                    ChooserEscritorio.setStatus("There are not loaded data in " + from.getIconType() + "...");
                                } else {
                                    data = new double[((FileIcon) from).data.getRowCount()][((FileIcon) from).data.getColumnCount()];
                                    atributos = new String[((FileIcon) from).data.getColumnCount()];
                                    // datos                                                     
                                    for(int i = 0; i < ((FileIcon) from).data.getRowCount(); i++){
                                        for(int j = 0; j < ((FileIcon) from).data.getColumnCount(); j++){

                                             data[i][j] = Double.parseDouble(((FileIcon)from).data.getValueAt(i, j).toString()) ;              
                                        }   
                                    }
                                    // atributos                              
                                     for(int j = 0; j < ((FileIcon) from).data.getColumnCount(); j++){      
                                          atributos[j] = ((FileIcon) from).data.getColumnName(j);
                                     }
                                }
                                ((ScatterIcon)to).dataIn = data;
                                ((ScatterIcon) to).atributos = atributos;
                                ((ScatterIcon) to).d = ((FileIcon) from).data.getColumnCount();
                                ((ScatterIcon) to).algorithm = "sinEtiquetas";
                                ((ScatterIcon) to).getMnuRun().setEnabled(true);
                                 nuevopressed.seleccionado = true;
                                 conexiones.add(new Conexion(conectorpressed, nuevopressed));
                                 conectorpressed = null;
                             
                           }else{
                             JOptionPane.showMessageDialog(this, "Only quantitative attributes are allowed", "VisMineDR", JOptionPane.ERROR_MESSAGE);
                           }
    
                        }else if (from instanceof FilterIcon && to instanceof DRIcon) {

                          if(((FilterIcon)from).filterName.equals("selection")){
                                   
                            // para validar que solo ingresen datos cuantitativos
                            boolean bdCuanti = true;
                            for(int j = 0; j < ((FilterIcon) from).dataOut.getColumnCount()-1; j++){
                               if(((FilterIcon) from).dataOut.getColumnClass(j).toString().equals("class java.lang.String")){
                                   bdCuanti = false;
                               }
                            }
                           
                           if(bdCuanti){
                                if (((FilterIcon) from).dataOut == null) {
                                    ChooserEscritorio.setStatus("There are not loaded data in " + from.getIconType() + "...");
                                } else {
                                    
                                    double[][] data = new double[((FilterIcon) from).dataOut.getRowCount()][((FilterIcon) from).dataOut.getColumnCount()-1];
                                    String[] atributos = new String[((FilterIcon) from).dataOut.getColumnCount()-1];
                                    String[] etiquetas = new String[((FilterIcon) from).dataOut.getRowCount()];
                                    ArrayList etiquetasDif = new ArrayList(1);

                                    // datos ***** realiza una transformación del dataset para que quede en terminos de matriz como lo requiere RD                                                    
                                    for(int i = 0; i < ((FilterIcon) from).dataOut.getRowCount(); i++){
                                        for(int j = 0; j < ((FilterIcon) from).dataOut.getColumnCount(); j++){
                                            if(j==((FilterIcon) from).dataOut.getColumnCount()-1){                   
                                                // columna de etiquetas
                                                etiquetas[i] = ((FilterIcon) from).dataOut.getValueAt(i, j).toString();
                                                 // obtiene los distintos valores de la columna de etiquetas
                                                if(!etiquetasDif.contains(etiquetas[i])){
                                                    etiquetasDif.add(etiquetas[i]);
                                                 }
                                            }else{
                                                if(((FilterIcon) from).dataOut.getValueAt(i, j) != null){                 
                                                    data[i][j] = Double.parseDouble(((FilterIcon) from).dataOut.getValueAt(i, j).toString());
                                                }
                                            }
                                        }
                                    }
                                        // atributos                              
                                         for(int j = 0; j < ((FilterIcon) from).dataOut.getColumnCount()-1; j++){      
                                              atributos[j] = ((FilterIcon) from).dataOut.getColumnName(j);
                                         }
                                        // *************                              

                                        ((DRIcon) to).dataIn = data;
                                        ((DRIcon) to).atributos = atributos;
                                        ((DRIcon) to).etiquetas = etiquetas;
                                        ((DRIcon) to).etiquetasDif = etiquetasDif;
                                        
                                        if(((DRIcon)to).getName().equals("KMDS")){ //KMDS no se configura, se ejecuta directamente
                                          ((DRIcon) to).getMnuRun().setEnabled(true);
                                        }else{
                                          ((DRIcon) to).getMnuConfigure().setEnabled(true);  
                                        }

                                        nuevopressed.seleccionado = true;
                                        conexiones.add(new Conexion(conectorpressed, nuevopressed));
                                        conectorpressed = null;
                                    }
                               }else{
                                   JOptionPane.showMessageDialog(this, "Only quantitative attributes are allowed", "VisMineDR", JOptionPane.ERROR_MESSAGE);
                               }
                           
                            }else { // si no es selection
                                
                                if(((DRIcon)to).getName().equals("MDS") ){
                                     JOptionPane.showMessageDialog(this, ((DRIcon)to).algorithm +" can only connect filter Selection", "VisMineDR", JOptionPane.ERROR_MESSAGE);
                                }else{
                                    
                                    double[][] data = null;
                                    String[] atributos = null;
                                    
                                    if (((FilterIcon) from).dataOut == null) {
                                        ChooserEscritorio.setStatus("There are not loaded data in " + from.getIconType() + "...");
                                    } else {
                                        
                                       // para validar que solo ingresen datos cuantitativos
                                        boolean bdCuanti = true;
                                        for(int j = 0; j < ((FilterIcon) from).dataOut.getColumnCount(); j++){
                                           if(((FilterIcon) from).dataOut.getColumnClass(j).toString().equals("class java.lang.String")){
                                               bdCuanti = false;
                                           }
                                        }

                                       if(bdCuanti){ 

                                            data = new double[((FilterIcon) from).dataOut.getRowCount()][((FilterIcon) from).dataOut.getColumnCount()];

                                            // datos                                                     
                                            for(int i = 0; i < ((FilterIcon) from).dataOut.getRowCount(); i++){
                                                for(int j = 0; j < ((FilterIcon) from).dataOut.getColumnCount(); j++){
                                                     data[i][j] = Double.parseDouble(((FilterIcon)from).dataOut.getValueAt(i, j).toString()) ;              
                                                }   
                                            }

                                            atributos = new String[((FilterIcon) from).dataOut.getColumnCount()];
                                            // atributos                              
                                             for(int j = 0; j < ((FilterIcon) from).dataOut.getColumnCount(); j++){      
                                                  atributos[j] = ((FilterIcon) from).dataOut.getColumnName(j);
                                             }

                                            ((DRIcon)to).dataIn = data;
                                            ((DRIcon) to).atributos = atributos;
                                            
                                            if(((DRIcon)to).getName().equals("KMDS")){ //KMDS no se configura, se ejecuta directamente
                                              ((DRIcon) to).getMnuRun().setEnabled(true);
                                            }else{
                                              ((DRIcon) to).getMnuConfigure().setEnabled(true);  
                                            }
                                            
                                            nuevopressed.seleccionado = true;
                                            conexiones.add(new Conexion(conectorpressed, nuevopressed));
                                            conectorpressed = null;
                                    
                                    }else{
                                       JOptionPane.showMessageDialog(this, "Only quantitative attributes are allowed", "VisMineDR", JOptionPane.ERROR_MESSAGE);
                                    }
                                  }
                                }
                            }
                            
                        }else if (from instanceof FilterIcon && to instanceof ScatterIcon) {
 
                          if(((FilterIcon)from).filterName.equals("selection")){
                                
                            // para validar que solo ingresen datos cuantitativos
                            boolean bdCuanti = true;
                            for(int j = 0; j < ((FilterIcon) from).dataOut.getColumnCount()-1; j++){
                               if(((FilterIcon) from).dataOut.getColumnClass(j).toString().equals("class java.lang.String")){
                                   bdCuanti = false;
                               }
                            }
                           
                           if(bdCuanti){

                                if (((FilterIcon) from).dataOut == null) {
                                    ChooserEscritorio.setStatus("There are not loaded data in " + from.getIconType() + "...");
                                } else {
                                    double[][] data = new double[((FilterIcon) from).dataOut.getRowCount()][((FilterIcon) from).dataOut.getColumnCount()-1];
                                    String[] atributos = new String[((FilterIcon) from).dataOut.getColumnCount()-1];
                                    String[] etiquetas = new String[((FilterIcon) from).dataOut.getRowCount()];
                                    ArrayList etiquetasDif = new ArrayList(1);

                                    // datos ***** realiza una transformación del dataset para que quede en terminos de matriz como lo requiere RD                                                    
                                    for(int i = 0; i < ((FilterIcon) from).dataOut.getRowCount(); i++){
                                        for(int j = 0; j < ((FilterIcon) from).dataOut.getColumnCount(); j++){
                                            if(j==((FilterIcon) from).dataOut.getColumnCount()-1){                   
                                                // columna de etiquetas
                                                etiquetas[i] = ((FilterIcon) from).dataOut.getValueAt(i, j).toString();
                                                 // obtiene los distintos valores de la columna de etiquetas
                                                if(!etiquetasDif.contains(etiquetas[i])){
                                                    etiquetasDif.add(etiquetas[i]);
                                                 }
                                            }else{
                                                if(((FilterIcon) from).dataOut.getValueAt(i, j) != null){                 
                                                    data[i][j] = Double.parseDouble(((FilterIcon) from).dataOut.getValueAt(i, j).toString());
                                                }
                                            }
                                        }
                                    }
                                        // atributos                              
                                         for(int j = 0; j < ((FilterIcon) from).dataOut.getColumnCount()-1; j++){      
                                              atributos[j] = ((FilterIcon) from).dataOut.getColumnName(j);
                                         }
                                        // *************                              
                                        ((ScatterIcon) to).d = ((FilterIcon) from).dataOut.getColumnCount()-1;
                                        ((ScatterIcon) to).dataIn = data;
                                        ((ScatterIcon) to).atributos = atributos;
                                        ((ScatterIcon) to).etiquetas = etiquetas;
                                        ((ScatterIcon) to).etiquetasDif = etiquetasDif;
                                        ((ScatterIcon) to).algorithm = "conEtiquetas";
                                        ((ScatterIcon) to).getMnuConfigure().setVisible(true);
                                        ((ScatterIcon) to).getMnuConfigure().setEnabled(true);
                                        nuevopressed.seleccionado = true;
                                        conexiones.add(new Conexion(conectorpressed, nuevopressed));
                                        conectorpressed = null;
                                    }
                                }else{
                                   JOptionPane.showMessageDialog(this, "Only quantitative attributes are allowed", "VisMineDR", JOptionPane.ERROR_MESSAGE);
                                 }
                           
                            }else{                                
                                double[][] data = null;
                                String[] atributos = new String[((FilterIcon) from).dataOut.getColumnCount()];
                                if (((FilterIcon) from).dataOut == null) {
                                    ChooserEscritorio.setStatus("There are not loaded data in " + from.getIconType() + "...");
                                } else {
                                    
                                // para validar que solo ingresen datos cuantitativos
                                boolean bdCuanti = true;
                                for(int j = 0; j < ((FilterIcon) from).dataOut.getColumnCount(); j++){
                                   if(((FilterIcon) from).dataOut.getColumnClass(j).toString().equals("class java.lang.String")){
                                       bdCuanti = false;
                                   }
                                }

                               if(bdCuanti){
 
                                    data = new double[((FilterIcon) from).dataOut.getRowCount()][((FilterIcon) from).dataOut.getColumnCount()];
                                    
                                    // datos                                                     
                                    for(int i = 0; i < ((FilterIcon) from).dataOut.getRowCount(); i++){
                                        for(int j = 0; j < ((FilterIcon) from).dataOut.getColumnCount(); j++){
                                             data[i][j] = Double.parseDouble(((FilterIcon)from).dataOut.getValueAt(i, j).toString()) ;              
                                        }   
                                    }
                                    
                                    // atributos                              
                                     for(int j = 0; j < ((FilterIcon) from).dataOut.getColumnCount(); j++){      
                                          atributos[j] = ((FilterIcon) from).dataOut.getColumnName(j);
                                     }
                                
                                ((ScatterIcon)to).dataIn = data;
                                ((ScatterIcon)to).atributos = atributos;
                                ((ScatterIcon) to).d = ((FilterIcon) from).dataOut.getColumnCount();
                                ((ScatterIcon) to).algorithm = "sinEtiquetas";
                                ((ScatterIcon) to).getMnuRun().setEnabled(true);
                                 nuevopressed.seleccionado = true;
                                 conexiones.add(new Conexion(conectorpressed, nuevopressed));
                                 conectorpressed = null;
                                
                                }else{
                                   JOptionPane.showMessageDialog(this, "Only quantitative attributes are allowed", "VisMineDR", JOptionPane.ERROR_MESSAGE);
                                 }
                              }
                            }
                            
                        }else if(from instanceof DRIcon && to instanceof ScatterIcon){
      
                            ((ScatterIcon) to).algorithm =  ((DRIcon)from).algorithm;
                            ((ScatterIcon) to).mds = ((DRIcon)from).mds;
                            ((ScatterIcon) to).pca = ((DRIcon)from).pca;
                            ((ScatterIcon) to).lle = ((DRIcon)from).lle;
                            ((ScatterIcon) to).le = ((DRIcon)from).le;
                            ((ScatterIcon) to).kpca = ((DRIcon)from).kpca;
                            ((ScatterIcon) to).dataIn = ((DRIcon)from).dataIn;
                            ((ScatterIcon) to).atributos = ((DRIcon)from).atributos;
                            ((ScatterIcon) to).etiquetas =  ((DRIcon)from).etiquetas;
                            ((ScatterIcon) to).etiquetasDif = ((DRIcon)from).etiquetasDif;
                            ((ScatterIcon) to).d = ((DRIcon)from).d;

                            if(((ScatterIcon) to).etiquetas == null && ((DRIcon)from).algorithm.equals("KPCA")){ 
                                ((ScatterIcon) to).getMnuRun().setEnabled(true);
//                            }
//                            else if(((DRIcon)from).algorithm.equals("MDS")){ 
//                                ((ScatterIcon) to).getMnuConfigure().setEnabled(true);
                            }else{
                                ((ScatterIcon) to).getMnuConfigure().setVisible(true);
                                ((ScatterIcon) to).getMnuConfigure().setEnabled(true); 
                            }

                            nuevopressed.seleccionado = true;
                            conexiones.add(new Conexion(conectorpressed, nuevopressed));
                            conectorpressed = null;
                        
                        }else if(from instanceof DRIcon && to instanceof VarianceIcon){
                                if(((DRIcon)from).algorithm.equals("PCA")){
                                    
                                    ((VarianceIcon) to).pca = ((DRIcon)from).pca;
                                    ((VarianceIcon) to).getMnuRun().setEnabled(true);
                                    
                                    nuevopressed.seleccionado = true;
                                    conexiones.add(new Conexion(conectorpressed, nuevopressed));
                                    conectorpressed = null;
                                }else{
                                    JOptionPane.showMessageDialog(this, "You can only connect to PCA", "VisMineDR", JOptionPane.ERROR_MESSAGE);
                                }
                        } 
                    }else {
                        ChooserEscritorio.setStatus("Cannot connect a " + from.getIconType() + " to a " + to.getIconType());
                    }
                }
            }
            if (conectorpressed != null) {
                conectorpressed.seleccionado = false;
                conectorpressed = null;
            }
            seleccionado = null;
            repaint();
        } catch (NullPointerException npe) {
        }
    }//GEN-LAST:event_formMouseReleased

    private void setConnection() {
    }

    public void getMousePressed(java.awt.event.MouseEvent evt) {
        this.formMousePressed(evt);
    }

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
// TODO add your handling code here:
        
        int x = evt.getXOnScreen() - this.getLocationOnScreen().x;
        int y = evt.getYOnScreen() - this.getLocationOnScreen().y; 
        
        Component press = this.findComponentAt(x, y);
        
        if (press instanceof MyIcon || press instanceof JackAnimation || press instanceof AnimationLabel) {
            
            conectorpressed = null; //parece que esta fue la solucion
            
            System.out.println(press.getClass().getSimpleName());
            seleccionado = (Icon) (press.getParent());
        } else if (press instanceof Conector) {
            
            seleccionado = null; //parece que esta fue la solucion
            
            System.out.println(press.getClass().getSimpleName() + " - " + press.getParent().getName());
            conectorpressed = (Conector) press;
            if (conectorpressed.seleccionado) {
                if (conectorpressed.conections >= 1) {// Mas de una conexion asociada
                    this.moveConector(conectorpressed);
                } else {
                    conectorpressed = null;
                }
            } else {
                conectorpressed.seleccionado = true;
                fx = ix = conectorpressed.get_X();
                fy = iy = conectorpressed.get_Y();
            }
        }
        repaint();
    }//GEN-LAST:event_formMousePressed

    public void getMouseDragged(java.awt.event.MouseEvent evt) {
        this.formMouseDragged(evt);
    }

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
// TODO add your handling code here:
        if (seleccionado != null) {
            int x = evt.getXOnScreen() - this.getLocationOnScreen().x;
            int y = evt.getYOnScreen() - this.getLocationOnScreen().y;
            if (x < seleccionado.getWidth() / 2) {
                x = seleccionado.getWidth() / 2;
            }
            if (x > this.getWidth() - seleccionado.getWidth() / 2) {
                x = this.getWidth() - seleccionado.getWidth() / 2;
            }
            if (y < seleccionado.getHeight() / 2) {
                y = seleccionado.getHeight() / 2;
            }
            if (y > this.getHeight() - seleccionado.getHeight()) {
                y = this.getHeight() - seleccionado.getHeight();
            }

            seleccionado.setLocation(x - seleccionado.getWidth() / 2, y - seleccionado.getHeight() / 2);
        } else if (conectorpressed != null) {
            fx = evt.getXOnScreen() - this.getLocationOnScreen().x;
            fy = evt.getYOnScreen() - this.getLocationOnScreen().y;
            //fx = evt.getX();
            //fy = evt.getY();
        }
        repaint();
    }//GEN-LAST:event_formMouseDragged

    public synchronized void paint(Graphics g) {
        int xd, yd, xh, yh;
        Dimension d = getSize();
        if ((offscreen == null) || (d.width != offscreensize.width) || (d.height != offscreensize.height)) {
            offscreen = createImage(d.width, d.height);
            offscreensize = d;
            if (offgraphics != null) {
                offgraphics.dispose();
            }
            offgraphics = offscreen.getGraphics();
        }
        Iterator it = conexiones.iterator();

        super.paint(offgraphics);
        while (it.hasNext()) {
            Conexion aux = (Conexion) (it.next());
            xd = aux.de.get_X();
            yd = aux.de.get_Y();
            xh = aux.hacia.get_X();
            yh = aux.hacia.get_Y();
            offgraphics.setColor(colorEdge.BLUE);  //color de la linea conectora
            offgraphics.drawLine(xd, yd, xh, yh);
        }
        if (conectorpressed != null && 0 < fx && fx < this.getWidth() && 0 < fy && fy < this.getHeight()) {
            offgraphics.setColor(colorLine);
            offgraphics.drawLine(ix, iy, fx, fy);
        }
        g.drawImage(offscreen, 0, 0, null);
    }

    public String getToolTipText(MouseEvent event) {
        Component press = findComponentAt(event.getPoint());
        if (press.getParent() instanceof Icon) {
            Icon iconPress = (Icon) press.getParent();
            return this.setIconInfo("<strong>" + iconPress.getName() + "</strong><br>" + iconPress.getInfo());
        } else {
            return null;
        }
    }

    private String setIconInfo(String str) {
        str = str.replaceAll("\n", "<p>");
        str = "<html>".concat(str).concat("</html>");
        return str;
    }

    private void moveConector(Conector conector) {
        Iterator it = conexiones.iterator();
        boolean bfrom, bto;
        while (it.hasNext()) {
            Conexion e = (Conexion) it.next();
            bfrom = e.de.equals(conector);
            bto = e.hacia.equals(conector);
            if (bfrom || bto) {
                if (bfrom) {
                    if (e.de.conections == 1) {
                        e.de.seleccionado = false;
                    }
                    ix = e.hacia.get_X();
                    iy = e.hacia.get_Y();
                    fx = e.de.get_X();
                    fy = e.de.get_Y();
                } else {
                    if (e.hacia.conections == 1) {
                        e.hacia.seleccionado = false;
                    }
                    ix = e.de.get_X();
                    iy = e.de.get_Y();
                    fx = e.hacia.get_X();
                    fy = e.hacia.get_Y();
                }
                conectorpressed = (Conector) this.findComponentAt(ix, iy);
                e.de.decreaseConection();
                e.hacia.decreaseConection();
                it.remove();
                break;
            }
        }
    }

    private void removeConector(Conector conector) {
        Iterator it = conexiones.iterator();
        while (it.hasNext()) {
            Conexion e = (Conexion) it.next();
            if (e.de.equals(conector) ||
                    e.hacia.equals(conector)) {
                if (e.de.conections == 1) {
                    e.de.seleccionado = false;
                }
                e.de.decreaseConection();
                if (e.hacia.conections == 1) {
                    e.hacia.seleccionado = false;
                }
                e.hacia.decreaseConection();
                it.remove();
                break;
            }
        }
    }
    // Step 4: Handle dragged object notifications
    public void dragExit(DropTargetEvent dte) {
        System.out.println("DT: dragExit");
    }

    public void dragEnter(DropTargetDragEvent dtde) {
        System.out.println("DT: dragEnter");
    }

    public void dragOver(DropTargetDragEvent dtde) {
        System.out.println("DT: dragOver");
    }

    public void dropActionChanged(DropTargetDragEvent dtde) {
        System.out.println("DT: dropActionChanged");
    }

    // Step 5: Handle the drop: aqui es donde copia el icono al canvas
    // Se instancian los objetos una vez arrastrados al canvas
    public void drop(DropTargetDropEvent dtde) {
        System.out.println("DT: drop");
        try {
            Transferable transferable = dtde.getTransferable();

            if (transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                // Step 5: Accept Strings
                dtde.acceptDrop(DnDConstants.ACTION_COPY);

                // Step 6: Extract the Transferable String
                String s = (String) transferable.getTransferData(DataFlavor.stringFlavor);
//                Image image = (Image) transferable.getTransferData(DataFlavor.imageFlavor);
//                ImageIcon icon1 = new ImageIcon(image);
                //label.setIcon(icon);

                System.out.println(s + " - " + dtde.getLocation().x + "," + dtde.getLocation().y);
                //JLabel icon = new JLabel(label);
                JLabel pressed = container.getDragged(); // aqui tres el icon(que es un jlabel) del contenedor al cual se lo paso alguno de los paneles
                if (pressed.getName().equals("otro")) {
                    return;
                }
                Point p = dtde.getLocation();
                String nameIcon = pressed.getName();
                Icon icon = null;
                if (nameIcon.equals("Conexion BD")) {
                    icon = new DBConnectionIcon((JLabel) pressed, p.x, p.y, contIcons);
                } else if (nameIcon.equals("EquipAsso")) {
                    icon = new AssociationIcon((JLabel) pressed, p.x, p.y, contIcons);
                } else if (nameIcon.equals("FPGrowth")) {
                    icon = new AssociationIcon((JLabel) pressed, p.x, p.y, contIcons);
                } else if (nameIcon.equals("Apriori")) {
                    icon = new AssociationIcon((JLabel) pressed, p.x, p.y, contIcons);
                } else if (nameIcon.equals("plaintext")) {
                    icon = new FileIcon ((JLabel) pressed, p.x, p.y, contIcons);
                } else if (nameIcon.equals("Example")) {
                    icon = new FileIcon ((JLabel) pressed, p.x, p.y, contIcons);
                } else if (nameIcon.equals("Generador")) {
                    icon = new RulesIcon((JLabel) pressed, p.x, p.y, contIcons);
                } else if (nameIcon.equals("updatem")) {
                    icon = new FilterIcon((JLabel) pressed, p.x, p.y, contIcons);
                } else if (nameIcon.equals("codification")) {
                    icon = new FilterIcon((JLabel) pressed, p.x, p.y, contIcons);
                } else if (nameIcon.equals("removem")) {
                    icon = new FilterIcon((JLabel) pressed, p.x, p.y, contIcons);
                } else if (nameIcon.equals("muestra")) {
                    icon = new FilterIcon((JLabel) pressed, p.x, p.y, contIcons);
                } else if (nameIcon.equals("remvalor")) {
                    icon = new FilterIcon((JLabel) pressed, p.x, p.y, contIcons);
                } else if (nameIcon.equals("rangenum")) {
                    icon = new FilterIcon((JLabel) pressed, p.x, p.y, contIcons);
                } else if (nameIcon.equals("discretize")) {
                    icon = new FilterIcon((JLabel) pressed, p.x, p.y, contIcons);
                } else if (nameIcon.equals("reduction")) {
                    icon = new FilterIcon((JLabel) pressed, p.x, p.y, contIcons);
                } else if (nameIcon.equals("selection")) {
                    icon = new FilterIcon((JLabel) pressed, p.x, p.y, contIcons);
                } else if (nameIcon.equals("c45")) {
                    icon = new ClasificationIcon((JLabel) pressed, p.x, p.y, contIcons);
                } else if (nameIcon.equals("mate")) {
                    icon = new ClasificationIcon((JLabel) pressed, p.x, p.y, contIcons);
                }else if (nameIcon.equals("sliq")) {
                    icon = new ClasificationIcon((JLabel) pressed, p.x, p.y, contIcons);
                }else if (nameIcon.equals("Hierarchical_Tree")) {
                    icon = new HierarchicalTreeIcon((JLabel) pressed, p.x, p.y, contIcons);
                } else if (nameIcon.equals("Weka_Tree")) {
                    icon = new WekaTreeIcon((JLabel) pressed, p.x, p.y, contIcons);
                } else if (nameIcon.equals("Text_Tree")) {
                    icon = new TextTreeIcon((JLabel) pressed, p.x, p.y, contIcons);
                } else if(nameIcon.equals("VMD")){
                    icon = new vmdIcon((JLabel)pressed, p.x, p.y, contIcons);
                } else if (nameIcon.equals("Prediction")) {
                    icon = new PredictionIcon((JLabel) pressed, p.x, p.y, contIcons);
                } else if (nameIcon.equals("kmeans")) {  // tanto kmeans como clarans y Birch llaman a kmeanicon en el cual se reconoce mediante el nombre del algoritmo con cual se trabajara
                    icon = new clusterIcon((JLabel) pressed, p.x, p.y, contIcons);
                }else if (nameIcon.equals("Clarans")) {
                    icon = new clusterIcon((JLabel) pressed, p.x, p.y, contIcons);
                }else if (nameIcon.equals("Birch")) {
                    icon = new clusterIcon((JLabel) pressed, p.x, p.y, contIcons);
                }else if (nameIcon.equals("Tabs")) {
                    icon = new Iconopestana((JLabel) pressed, p.x, p.y, contIcons);
                }else if (nameIcon.equals("Birchtree")) {
                    icon = new IconoArbol((JLabel) pressed, p.x, p.y, contIcons);
                }else if (nameIcon.equals("Estandar")) {
                    icon = new StandarizeIcon((JLabel) pressed, p.x, p.y, contIcons);
                }else if (nameIcon.equals("Graphluster")) {
                    icon = new IconoGrafica((JLabel) pressed, p.x, p.y, contIcons);
                }else if (nameIcon.equals("LLE")) {
                    icon = new DRIcon((JLabel) pressed, p.x, p.y, contIcons);
                }else if (nameIcon.equals("LE")) {
                    icon = new DRIcon((JLabel) pressed, p.x, p.y, contIcons);
                }else if (nameIcon.equals("PCA")) {
                    icon = new DRIcon((JLabel) pressed, p.x, p.y, contIcons);
                }else if (nameIcon.equals("MDS")) {
                    icon = new DRIcon((JLabel) pressed, p.x, p.y, contIcons);
                }else if (nameIcon.equals("KLLE")) {
                    icon = new DRIcon((JLabel) pressed, p.x, p.y, contIcons);
                }else if (nameIcon.equals("KLE")) {
                    icon = new DRIcon((JLabel) pressed, p.x, p.y, contIcons);
                }else if (nameIcon.equals("KPCA")) {
                    icon = new DRIcon((JLabel) pressed, p.x, p.y, contIcons);
                }else if (nameIcon.equals("KMDS")) {
                    icon = new DRIcon((JLabel) pressed, p.x, p.y, contIcons);
                }else if (nameIcon.equals("DDLLE")) {
                    icon = new DRIcon((JLabel) pressed, p.x, p.y, contIcons);
                }else if (nameIcon.equals("DDLE")) {
                    icon = new DRIcon((JLabel) pressed, p.x, p.y, contIcons);
                }else if (nameIcon.equals("DDMDS")) {
                    icon = new DRIcon((JLabel) pressed, p.x, p.y, contIcons);
                }else if (nameIcon.equals("Scatter")) {
                    icon = new ScatterIcon((JLabel) pressed, p.x, p.y, contIcons);
                }else if (nameIcon.equals("Variance")) {
                    icon = new VarianceIcon((JLabel) pressed, p.x, p.y, contIcons);
                }else {
                    icon = new Icon((JLabel) pressed, p.x, p.y,0);
                }

                if (p.x + icon.getPreferredSize().width > this.getWidth()) {
                    this.setPreferredSize(new Dimension(p.x + icon.getPreferredSize().width,this.getHeight()));
                    container.getScrollPanel().setViewportView(this);
                }
                if (p.y + icon.getPreferredSize().height > this.getHeight()) {
                    this.setPreferredSize(new Dimension(this.getWidth(),p.y + icon.getPreferredSize().height));
                    container.getScrollPanel().setViewportView(this);
                }
                
                contIcons++;
                this.addIcono(icon);
                icon.setBackground(new Color(0, 0, 0, 0)); //transparencia en el icono.

                dtde.getDropTargetContext().dropComplete(true);
                repaint();
            } else {
                // Step 5: Reject dropped objects that are not Strings
                dtde.rejectDrop();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
            System.err.println("Exception" + exception.getMessage());
            dtde.rejectDrop();
        } catch (UnsupportedFlavorException ufException) {
            ufException.printStackTrace();
            System.err.println("Exception" +
                    ufException.getMessage());
            dtde.rejectDrop();
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
