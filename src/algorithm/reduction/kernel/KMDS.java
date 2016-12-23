/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm.reduction.kernel;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;
import javax.swing.table.AbstractTableModel;
 
/**
 *
 * @author MacArthur
 */
public class KMDS {

    double[][] X; // Datos de entrada en forma de matriz de objeto
    String[] etiquetas; 
    
    private double[][] K; //Matriz kernel, datos de salida
    
    public KMDS(double[][] dataIn, String[] etiquetas) throws Exception {
        this.X = dataIn;
        this.etiquetas = etiquetas;
        KernelMDS();
    }

    public void KernelMDS() throws Exception {
        int N;
        
        double[][] D;
        double[][] N1;
        double[][] I;
        double[][] Itmp;

        N = X.length;

        D = Matriz.Potencia(Matriz.Dist(X), 2);

        N1 = Matriz.ProductoEscalar(Matriz.Unos(N, 1), Math.pow(N, -0.5));

        I = Matriz.Identidad(N);

        //Itmp = (I-N1*N1t);
        Itmp = Matriz.Resta(I, Matriz.Multiplicar(N1, Matriz.Transpuesta(N1)));

        K = Matriz.Multiplicar(Matriz.Multiplicar(Matriz.ProductoEscalar(Itmp, -0.5), D), Itmp);

        System.out.println("Matriz KERNEL");
//        Matriz.Imprimir(getKernelMDS());

//        System.out.println("Matriz KERNEL");
//        Matriz.Imprimir(kmds.getKernelMDS());
//        System.out.println("Matriz Vectores");
//        Matriz.Imprimir(kmds.EigVectores());
//        System.out.println("Matriz Valores");
//        Matriz.Imprimir(kmds.EigValores());
    }

    public double[][] getEigValores() {

        //Matrix Libreria JAMA
        Matrix A = Matrix.constructWithCopy(K);

        // compute the spectral decomposition
        EigenvalueDecomposition e = A.eig();

        Matrix D = e.getD();
        return D.getArray();
    }

    public double[][] getEigVectores() {
        //Matrix Libreria JAMA
        Matrix A = Matrix.constructWithCopy(K);

        // compute the spectral decomposition
        EigenvalueDecomposition e = A.eig();

        Matrix V = e.getV();

        return V.getArray();
    }

    public double[][] getKernelMDS(){
        return K;
    }

}
