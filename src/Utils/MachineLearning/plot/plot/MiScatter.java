/*******************************************************************************
 * Copyright (c) 2010 Haifeng Li
 *   
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/

package Utils.MachineLearning.plot.plot;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Utils.MachineLearning.data.AttributeDataset;
import Utils.MachineLearning.data.NominalAttribute;
import Utils.MachineLearning.data.parser.DelimitedTextParser;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Haifeng Li
 */
@SuppressWarnings("serial")
public class MiScatter extends JPanel {
    
    private static String[] datasetName = {
        "Toy",
        "Big Toy",
    };

    private static String[] datasource = {
//        ruta + "/toy-train.txt",
//        ruta + "/toy-test.txt"

//       "D:/datos/data/classification/toy/toy-train.txt",
//       "D:/datos/data/classification/toy/toy-test.txt"
            
       "D:/ProyectoDR/datos/clasicos/Sphere/DBsphereOriginal.txt",
       "D:/ProyectoDR/datos/clasicos/Sphere/DBsphereOriginal.txt"
    };

    static AttributeDataset[] dataset = null;
    static int datasetIndex = 0; 
    
    public MiScatter() {
        super(new GridLayout(1,2));
        
//        double[][] data = new double[100][3];
//        for (int j = 0; j < data.length; j++) {
//            data[j][0] = Math.random();
//            data[j][1] = Math.random();
//            data[j][2] = Math.random();
//        }

if (dataset == null) {
            dataset = new AttributeDataset[datasetName.length];
            DelimitedTextParser parser = new DelimitedTextParser();
            parser.setDelimiter("[\t ]+");
            parser.setResponseIndex(new NominalAttribute("class"), 0);
        
            try {
                dataset[datasetIndex] = parser.parse(datasetName[datasetIndex], new java.io.File(datasource[datasetIndex]));
//                smile.data.parser.IOUtils.getTestDataFile(datasource[datasetIndex]));
            } catch (IOException ex) {
                Logger.getLogger(MiScatter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(MiScatter.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
 
        double[][] data = dataset[datasetIndex].toArray(new double[dataset[datasetIndex].size()][]);
//        int[] label = dataset[datasetIndex].toArray(new int[dataset[datasetIndex].size()]);
        
        PlotCanvas canvas3d = ScatterPlot.plot(data);
        canvas3d.setTitle("3D Scatter Plot");
        add(canvas3d);
    }

    @Override
    public String toString() {
        return "Scatter Plot";
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Staircase Plot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(new MiScatter());
        frame.setVisible(true);
    }
}
