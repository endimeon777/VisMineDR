
package gui.Icons.vmd.guicomponents;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
//import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import javax.swing.table.AbstractTableModel;
import prefuse.data.Table;
//import prefuse.data.io.DelimitedTextTableReader;
import prefuse.util.ColorLib;


public class GWinData {
    // Shared data (tables). Depending on data file (combo).

    public GWindow owner;
    // Prefuse tables
//    public Table t;
    public Table normalizedt;
    // Calculate correlation coefficient
    public Table correlationCoefficientt;
    public int[] headersNumericAttributes;
    private int counterNumericAttributes = 0,  pointerNumericAttributes = 0;
    // Categorical Values renderer information
    public Table categoricalValuesRendererInformation;
    int maxCantValues; // Identify the maximun number of distinct values for a categorical attrib
    public Map<String, String[]> distinctCategoricalValues = new HashMap<String, String[]>();
    // Store Minimum and Maximum real values for each variable
    public Map<String, double[]> realMinMax = new HashMap<String, double[]>();
    // Store the central tendency measures (mean) // the moda is obteined in ratio
    public Map<String, Double> centralTendencyMeasures = new HashMap<String, Double>();
    // Stored references
    public int ROWS;
    public int COLS;
    // Store structure by column names in original tables: DataPieces structure might be changed
    public List<String> headers = new ArrayList<String>();
    public Map<String, Double> maxValues = new HashMap<String, Double>();
    // Graphic normalization value (0 becomes -gNorm, then almost all positives)
    public int gNorm = 4;

    public AbstractTableModel dataInVmd;

    public GWinData(AbstractTableModel dataIn) {
        dataInVmd = dataIn;
    }

//    public GWinData(String _fileName) { // aqui carga los datos
//        if (_fileName != null) {
//
//            try {
//                t = new DelimitedTextTableReader().readTable(_fileName);
//            } catch (Exception e) {
//                System.out.println("DATA loader => Error occurred: " + e);
//            }
//        }
//    }

    public void structure() {// este bloque tambien mira si las columnas son de tipo entero decimal o doble
//        ROWS = t.getRowCount();
//        COLS = t.getColumnCount();

        ROWS = dataInVmd.getRowCount();
        COLS = dataInVmd.getColumnCount();

        normalizedt = new Table(ROWS, COLS);
        
        // debug data
        // Correlation coefficient  
        headersNumericAttributes = new int[COLS];
        for (int c = 0; c < COLS; c++) {
//            System.out.println(t.getColumn(c).getClass().getName());
            if (dataInVmd.getColumnClass(c).equals(Integer.class) || dataInVmd.getColumnClass(c).equals(int.class) || dataInVmd.getColumnClass(c).equals(float.class) || dataInVmd.getColumnClass(c).equals(double.class)) {
                headersNumericAttributes[counterNumericAttributes] = c;
                pointerNumericAttributes = c; // The process stop in the n-1 value because there will be a next
                counterNumericAttributes++;
            }
        }
        correlationCoefficientt = new Table(counterNumericAttributes, counterNumericAttributes + 1);
        // TODO all the data need to be printed (change to another class �DataPieces?)
        this.normalizeTypes(); // saca algunos valores estadisticos y arma una tabla con caracteristicas graficas como el color que llevara un dato
        this.createHeaders();
        this.printLogData();
    }

    // Types and normalizations
    public void normalizeTypes() { //calcula varianza, media y otras cosas por cada columna categorica

        // Define the first column : attributes name
        correlationCoefficientt.addColumn("Attributes", String.class);
        for (int c = 0; c < COLS; c++) {
            normalizedt.addColumn(dataInVmd.getColumnName(c), double.class);
            // delimited case (fisher iris): int and string
//            System.out.println("COLUMN: " + t.getColumnName(c) + " :: TYPE: " + t.getColumnType(c));
            if (dataInVmd.getColumnClass(c)== String.class || dataInVmd.getColumnClass(c)== Object.class) {
                normalizeStringAttribute(c);
            } else {
                normalizeNumericalAttribute(c);
            }
        }
        // Correlation coefficient
        for (int c = 0; c < counterNumericAttributes; c++) {
            // Define the rest of columns
            correlationCoefficientt.addColumn(dataInVmd.getColumnName(headersNumericAttributes[c]), double.class);
            correlationCoefficientt.setString(c, "Attributes", dataInVmd.getColumnName(headersNumericAttributes[c]));
//            if (c != counterNumericAttributes - 1) {
                calcCoefCorelation(headersNumericAttributes[c]);
//            }
        }
        // Construct Renderer Information Table
        rendererInformationTable();
    }

    public void normalizeNumericalAttribute(int col) {

        // Stored row value
        double tempObj = 0.0000;
        // Stored row values for max and min
        double tempObjMax = -999999;
        double tempObjMin = 999999;
        // Total sum for mean
        double totalSum = 0.0000;
        // Mean
        double mean = 0.0000;
        // Variance
        double variance = 0.0000;
        // Total sum of squares for Variance
        double sumForVar = 0.0000;
        // Array of stored values
        List<Double> resume = new ArrayList<Double>();
        // Store the min and max value
        double min = 1000000, max = 0;
        // Begin normalization: media and temp array
        for (int r = 0; r < ROWS; r++) {
            // Avoiding exception in  prefuse.data.DataTypeException: Type double not supported.
            // Change commas to points (decimals)
            if (dataInVmd.getColumnClass(col).equals(int.class) || dataInVmd.getColumnClass(col).equals(Integer.class)) {
                tempObj = Integer.parseInt(dataInVmd.getValueAt(r, col).toString());
            }
            if (dataInVmd.getColumnClass(col).equals(float.class)) {
                tempObj = Float.parseFloat(dataInVmd.getValueAt(r, col).toString());
            }
            if (dataInVmd.getColumnClass(col).equals(double.class)) {
                tempObj = Double.parseDouble(dataInVmd.getValueAt(r, col).toString());
            }
            if (dataInVmd.getColumnClass(col).equals(long.class)) {
                tempObj = Long.parseLong(dataInVmd.getValueAt(r, col).toString());
            }
            if (dataInVmd.getColumnClass(col).equals(short.class)) {
               tempObj = Float.parseFloat(dataInVmd.getValueAt(r, col).toString());
            }
            if (dataInVmd.getColumnClass(col).equals(short.class)) { //// creo que este no va
                tempObj = Float.parseFloat(dataInVmd.getValueAt(r, col).toString());
            }
            // The minimum real value
            if (tempObj < min) {
                min = tempObj;
            }
            // The maximum real value
            if (tempObj > max) {
                max = tempObj;
            }
            totalSum += tempObj;
            sumForVar += tempObj * tempObj;
            resume.add(tempObj);
        }
        double[] minmaxVals = {min, max};
        realMinMax.put(dataInVmd.getColumnName(col), minmaxVals);

        System.out.println(min + ":" + max);
        // Define Mean
        mean = totalSum / ROWS;

        // Store mean
        centralTendencyMeasures.put(dataInVmd.getColumnName(col), mean);
        // Define Variance
        variance = (sumForVar / ROWS) - (mean * mean);
        //System.out.println(t.getColumnName(col) + ":" + mean + ":" + variance);
        // Fill table (normalizedt) rows with normalized values
        double tempNormValues = 0.0000;
        for (int r = 0; r < ROWS; r++) {
            tempNormValues = (resume.get(r) - mean) / Math.sqrt(variance);
            // Adding gNorm for Graphic normalization
            tempObjMax = (float) Math.max(tempObjMax, (tempNormValues + gNorm));
            tempObjMin = (float) Math.min(tempObjMin, (tempNormValues + gNorm));
            normalizedt.setDouble(r, col, tempNormValues + gNorm);
            //System.out.println(t.getColumnName(col) + ":" + tempNormValues + gNorm);
        }
        // Storing max value
        
        maxValues.put(dataInVmd.getColumnName(col), tempObjMax);
    }

    public void normalizeStringAttribute(int col) {

        // Stored row value
        String tempStr = "";
        // Stored row value for max
        double tempObjMax = -999999;
        double tempObjMin = 999999;
        // Total sum for mean
        double totalSum = 0.0000;
        // Mean
        double mean = 0.0000;
        // Variance
        double variance = 0.0000;
        // Total sum of squares for Variance
        double sumForVar = 0.0000;
        // Arrays
        List<String> resume = new ArrayList<String>();
        List<Integer> resumeToInt = new ArrayList<Integer>();
        // List (how many values are repeated)
        int distinctCount = 0;
        for (int r = 0; r < ROWS; r++) { //este bloque busca los distintos valores de una columna
            tempStr = dataInVmd.getValueAt(r, col).toString();
            if (!resume.contains(tempStr)) {
                resume.add(tempStr); //agregae en este arraylist los valores distientos de una columna
                distinctCount++;
                maxCantValues++;
            }
        }
        String[] tmpDistinct = new String[distinctCount];
        for (int r = 0; r < distinctCount; r++) {
            tmpDistinct[r] = resume.get(r);
        }
        // llena el hashmap con los distientos valores por cada categoria
        distinctCategoricalValues.put(dataInVmd.getColumnName(col), tmpDistinct);
        // Make a temp list to convert values
        // Begin normalization: total sum for mean
        for (int r = 0; r < ROWS; ++r) {
            for (int i = 0; i < resume.size(); i++) {
                if (resume.get(i).equals(dataInVmd.getValueAt(r, col).toString())) {
                    resumeToInt.add(i);
                    totalSum += i;
                    sumForVar += i * i;
                }
            }
        }

        // calcula la media
        mean = totalSum / ROWS;

        // calcula la varianza
        variance = (sumForVar / ROWS) - (mean * mean);
        //System.out.println(t.getColumnName(col) + ":" + mean + ":" + variance);
        // Fill normalizedt rows with normalized values
        double tempNormValues = 0.0000;
        for (int r = 0; r < ROWS; r++) {
            tempNormValues = (resumeToInt.get(r) - mean) / Math.sqrt(variance);
            // Adding gNorm to Graphic normalization (0 value becomes -gNorm, then almost all positives)
            tempObjMax = (float) Math.max(tempObjMax, (tempNormValues + gNorm));
            tempObjMin = (float) Math.min(tempObjMin, (tempNormValues + gNorm));
            normalizedt.setDouble(r, col, tempNormValues + gNorm);
            //System.out.println(r + ":" + tempNormValues + gNorm);
        }
        // Storing max value

        maxValues.put(dataInVmd.getColumnName(col), tempObjMax);

    }

    void calcCoefCorelation(int c) {
        for (int next = 0; next < counterNumericAttributes; next++) {
            // Stored row value
            double tempObj = 0.0000;
            double tempObjNext = 0.0000;
            // Total sum for mean
            double totalSum = 0.0000, totalSumNext = 0.0000;
            // Correlation Coefficient
            double totalSumCorCoef = 0.0000, correlationCoefficient = 0.00000, coVarianza = 0.00000;
            // Mean
            double mean = 0.0000, meanNext = 0.0000;
            // Standard deviation
            double stdDev = 0.0000, stdDevNext = 0.0000;
            // Total sum for standard deviation
            double sumForStdDevNum = 0.0000, sumForStdDevNumNext = 0.0000;
            // Array of stored values
            List<Double> resume = new ArrayList<Double>();
            List<Double> resumeNext = new ArrayList<Double>();
            int nullCounter = 0;
            // Null Counter
            for (int r = 0; r < ROWS; ++r) {
                if (dataInVmd.getColumnClass(c).equals(int.class) || dataInVmd.getColumnClass(c).equals(Integer.class)) {
                    tempObj = Integer.parseInt(dataInVmd.getValueAt(r,c).toString());
//                    if (c < pointerNumericAttributes) {
                        tempObjNext =  Integer.parseInt(dataInVmd.getValueAt(r,headersNumericAttributes[next]).toString());
//                    } // store the row value of the next attribute
                }
                if (dataInVmd.getColumnClass(c).equals(float.class)) {
                    tempObj = Float.parseFloat(dataInVmd.getValueAt(r, c).toString());
//                    if (c < pointerNumericAttributes) {
                        tempObjNext = tempObj = Float.parseFloat(dataInVmd.getValueAt(r, headersNumericAttributes[next]).toString());
//                    }
                }
                if (dataInVmd.getColumnClass(c).equals(double.class)) {
                    tempObj = Double.parseDouble(dataInVmd.getValueAt(r, c).toString());
//                    if (c < pointerNumericAttributes) {
                        tempObjNext = Double.parseDouble(dataInVmd.getValueAt(r, headersNumericAttributes[next]).toString());
//                    }
                }
                if (!(dataInVmd.getValueAt(r, c)== null)) {
                    totalSumCorCoef += (tempObj * tempObjNext); // step 1. calc. coef. cor.
                    totalSum += tempObj;
                    totalSumNext += tempObjNext;
                } else {
                    nullCounter++;
                }
                resume.add(tempObj);
                resumeNext.add(tempObjNext);
            }

            // Define Mean
            mean = totalSum / (ROWS - nullCounter);
            meanNext = totalSumNext / (ROWS - nullCounter);
            // Define standard deviation numerator
            for (int r = 0; r < ROWS; ++r) {
                if (!(resume.get(r) == null)) {
                    double Xval = resume.get(r);
                    sumForStdDevNum += Math.pow(Xval - mean, 2);
                }
                if (!(resumeNext.get(r) == null)) {
                    double XvalNext = resumeNext.get(r);
                    sumForStdDevNumNext += Math.pow(XvalNext - meanNext, 2);
                }
            }
            // Define standard deviation
            stdDev = Math.sqrt(sumForStdDevNum / (ROWS - (1 + nullCounter)));
            stdDevNext = Math.sqrt(sumForStdDevNumNext / (ROWS - (1 + nullCounter)));
            coVarianza = (totalSumCorCoef / (ROWS - (1 + nullCounter))) - (mean * meanNext);
            correlationCoefficient = coVarianza / (stdDev * stdDevNext);
            System.out.println("Col : " + c + "coef: " + correlationCoefficient);
            correlationCoefficientt.setDouble(next, dataInVmd.getColumnName(c), correlationCoefficient);
        }
    }

    public void createHeaders() {
        for (int c = 0; c < COLS; c++) {
            headers.add(normalizedt.getColumnName(c));
        }
    }

    void rendererInformationTable() { // cosntruye una tabla asigando a los valores un color forma y otros parametros para ser dibujados
        // Define the first column : attributes name
        categoricalValuesRendererInformation = new Table(maxCantValues, 3);
        categoricalValuesRendererInformation.addColumn("Attribute", String.class);
        categoricalValuesRendererInformation.addColumn("Value", String.class);
        categoricalValuesRendererInformation.addColumn("Shape", String.class);
        categoricalValuesRendererInformation.addColumn("Color", int.class);
        String tempStr = "";
        int p = 0;
        for (int c = 0; c < COLS; c++) {
            List<String> resume = new ArrayList<String>();
            if (dataInVmd.getColumnClass(c)== String.class || dataInVmd.getColumnClass(c)== Object.class) {
                for (int r = 0; r < ROWS; r++) {
                    if (!(dataInVmd.getValueAt(r, c) == null)) {
                        tempStr = dataInVmd.getValueAt(r, c).toString();
                        if (!resume.contains(tempStr)) {
                            resume.add(tempStr);
                        }
                    }
                }
                // Default class attribute is the first categorical variable "0"
                if (p == 0) {
                    for (int z = 0; z < resume.size(); z++) {
                        categoricalValuesRendererInformation.setString(p, "Attribute", dataInVmd.getColumnName(c));
                        categoricalValuesRendererInformation.setString(p, "Value", resume.get(z));
                        categoricalValuesRendererInformation.setString(p, "Shape", "ellipse");
                        // assign random color to each attribite class value
                        int color = ColorLib.rgb((int) (255 * Math.random()), (int) (255 * Math.random()), (int) (255 * Math.random()));
                        categoricalValuesRendererInformation.setInt(p, "Color", color);
                        p++;
                    }
                } else {
                    for (int z = 0; z < resume.size(); z++) {
                        categoricalValuesRendererInformation.setString(p, "Attribute", dataInVmd.getColumnName(c));
                        categoricalValuesRendererInformation.setString(p, "Value", resume.get(z));
                        categoricalValuesRendererInformation.setString(p, "Shape", "ellipse");
                        categoricalValuesRendererInformation.setInt(p, "Color", 0);
                        p++;
                    }
                }
            }
        }
    }

    // TODO all the data need to be printed (change to another class �DataPieces?)
    public void printLogData() {
        PrintWriter saveLog;
        try {
            saveLog = new PrintWriter("resume.txt");
            saveLog.println("List of attributes values from DataSet.");
            saveLog.println('\t');
            for (int r = 0; r < ROWS; r++) {
                saveLog.println("Table row: " + (r + 1));
                for (int i = 0; i < COLS; i++) {
                    saveLog.println("Attribute: [" + normalizedt.getColumnName(i) + " ]  -  Real value: [ " + dataInVmd.getValueAt(r, dataInVmd.findColumn(normalizedt.getColumnName(i))) + " ]  -  Normalized value: [ " + normalizedt.get(r, normalizedt.getColumnName(i)) + " ]");

//                    dataInVmd.getValueAt(r, normalizedt.getColumnName(i))
                }
                saveLog.println('\t');
            }
            saveLog.flush();
            saveLog.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
