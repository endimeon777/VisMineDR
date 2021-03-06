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
package algorithm.reduction.mds;

import Utils.MachineLearning.math.math.Math;
import Utils.MachineLearning.math.math.matrix.EigenValueDecomposition;

/**
 * Classical multidimensional scaling, also known as principal coordinates
 * analysis. Given a matrix of dissimilarities (e.g. pairwise distances), MDS
 * finds a set of points in low dimensional space that well-approximates the
 * dissimilarities in A. We are not restricted to using a Euclidean
 * distance metric. However, when Euclidean distances are used MDS is
 * equivalent to PCA.
 *
 * @see smile.projection.PCA
 * @see SammonMapping
 * 
 * @author Haifeng Li
 */
public class MDS {

    /**
     * Component scores.
     */
    private double[] eigenvalues;
    /**
     * Coordinate matrix.
     */
    private double[][] coordinates;
    /**
     * The proportion of variance contained in each principal component.
     */
    private double[] proportion;

    /**
     * Returns the component scores, ordered from largest to smallest.
     */
    public double[] getEigenValues() {
        return eigenvalues;
    }

    /**
     * Returns the proportion of variance contained in each eigenvectors,
     * ordered from largest to smallest.
     */
    public double[] getProportion() {
        return proportion;
    }

    /**
     * Returns the principal coordinates of projected data.
     */
    public double[][] getCoordinates() {
        return coordinates;
    }

    /**
     * Constructor. Learn the classical multidimensional scaling.
     * Map original data into 2-dimensional Euclidean space.
     * @param proximity the nonnegative proximity matrix of dissimilarities. The
     * diagonal should be zero and all other elements should be positive and
     * symmetric. For pairwise distances matrix, it should be just the plain
     * distance, not squared.
     */
    public MDS(double[][] proximity) {
        this(proximity, 2);
    }

    /**
     * Constructor. Learn the classical multidimensional scaling.
     * @param proximity the nonnegative proximity matrix of dissimilarities. The
     * diagonal should be zero and all other elements should be positive and
     * symmetric. For pairwise distances matrix, it should be just the plain
     * distance, not squared.
     * @param k the dimension of the projection.
     */
    public MDS(double[][] proximity, int k) {
        this(false, proximity, k, false);
    }
    
    /**
     *
     * @param codV es true si la matriz de covarianza la suministran directamente o false si hay que calcularla
     * @param proximity
     * @param k
     */
    public MDS(boolean codV, double[][] proximity, int k) {
        this(codV, proximity, k, false);
    }

    /**
     * Constructor. Learn the classical multidimensional scaling.
     * @param proxD: si la matriz de proximidad viene dada directa o se la debe contruir
     * @param proximity the nonnegative proximity matrix of dissimilarities. The
     * diagonal should be zero and all other elements should be positive and
     * symmetric. For pairwise distances matrix, it should be just the plain
     * distance, not squared.
     * @param k the dimension of the projection.
     * @param add true to estimate an appropriate constant to be added
     * to all the dissimilarities, apart from the self-dissimilarities, that
     * makes the learning matrix positive semi-definite. The other formulation of
     * the additive constant problem is as follows. If the proximity is
     * measured in an interval scale, where there is no natural origin, then there
     * is not a sympathy of the dissimilarities to the distances in the Euclidean
     * space used to represent the objects. In this case, we can estimate a constant c
     * such that proximity + c may be taken as ratio data, and also possibly
     * to minimize the dimensionality of the Euclidean space required for
     * representing the objects.
     */
    public MDS(boolean proxD, double[][] proximity, int k, boolean add) {
        
        int m = proximity.length;
        int n = proximity[0].length;

        if (k < 1 || k >= n) {
            throw new IllegalArgumentException("Invalid k = " + k);
        }
        
        if(!proxD){// si la matri de proximidad no viene dada directamente, es decir se debe calcular
            
//            CUANDO SE CONTRUYE LA MATRIZ DE PROXIMIDAD CXC(ATRIBUTOS POR ATRIBUTOS), SIMILAR A LA DE EURODIST
//            //// para calcular la matriz de proxmidad
//            proximity = Math.transpose(proximity);
//            double[][] pro = new double[n][n];
//            
//            for (int i = 0; i < n; i++) { 
//                for (int j = i; j < n; j++) { 
//                    if(i==j){
//                        pro[i][j] = 0.0;
//                    }else{
//                        pro[i][j] = Math.distance(proximity[i], proximity[j]);
//                        pro[j][i] = pro[i][j];
//                    }
//                }
//            }  
//            ///////
            

//          CUANDO SE CONTRUYE LA MATRIZ DE PROXIMIDAD FXF(REGISTRO POR REGISTRO)
            //// para calcular la matriz de proxmidad
//            proximity = Math.transpose(proximity);
            double[][] pro = new double[m][m];
            
            for (int i = 0; i < m; i++) { 
                for (int j = i; j < m; j++) { 
                    if(i==j){
                        pro[i][j] = 0.0;
                    }else{
                        pro[i][j] = Math.distance(proximity[i], proximity[j]);
                        pro[j][i] = pro[i][j];
                    }
                }
            }  
            ///////
            
            proximity = pro;
            m = proximity.length;
            n = proximity[0].length;
            
        }else{
            if (m != n) {
               throw new IllegalArgumentException("The proximity matrix is not square.");
            }
        }
        
        double[][] A = new double[n][n];
        double[][] B = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                A[i][j] = -0.5 * Math.sqr(proximity[i][j]);
                A[j][i] = A[i][j];
            }
        }

        double[] mean = Math.rowMean(A);
        double mu = Math.mean(mean);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                B[i][j] = A[i][j] - mean[i] - mean[j] + mu;
                B[j][i] = B[i][j];
            }
        }

        if (add) {
            double[][] Z = new double[2 * n][2 * n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    Z[i][n + j] = 2 * B[i][j];
                }
            }

            for (int i = 0; i < n; i++) {
                Z[n + i][i] = -1;
            }

            mean = Math.rowMean(proximity);
            mu = Math.mean(mean);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    Z[n + i][n + j] = 2 * (proximity[i][j] - mean[i] - mean[j] + mu);
                }
            }

            EigenValueDecomposition eigen = Math.eigen(Z, false, true);
            double c = Math.max(eigen.getEigenValues());

            for (int i = 0; i < n; i++) {
                B[i][i] = 0.0;
                for (int j = 0; j < i; j++) {
                    B[i][j] = -0.5 * Math.sqr(proximity[i][j] + c);
                    B[j][i] = B[i][j];
                }
            }
        }

        EigenValueDecomposition eigen = Math.eigen(B, k);
        
//        aqui cambie lo de cxc o fxf   cambie la n por m
        coordinates = new double[m][k];
        for (int j = 0; j < k; j++) {
            if (eigen.getEigenValues()[j] < 0) {
                throw new IllegalArgumentException(String.format("Some of the first %d eigenvalues are < 0.", k));
            }

            double scale = Math.sqrt(eigen.getEigenValues()[j]);
            for (int i = 0; i < m; i++) {
                coordinates[i][j] = eigen.getEigenVectors()[i][j] * scale;
            }
        }

        eigenvalues = eigen.getEigenValues();
        proportion = eigenvalues.clone();
        Math.unitize1(proportion);
    }
       
}
