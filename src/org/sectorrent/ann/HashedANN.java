package org.sectorrent.ann;

import java.util.Random;

public class HashedANN {

    private double[][] weightsInputHidden;
    private double[][] weightsHiddenOutput;
    private double[] biasHidden;
    private double[] biasOutput;

    public HashedANN(int inputSize, int hiddenSize, int outputSize, int seed){
        Random random = new Random(seed);

        weightsInputHidden = initializeMatrix(inputSize, hiddenSize, random);
        weightsHiddenOutput = initializeMatrix(hiddenSize, outputSize, random);
        biasHidden = initializeVector(hiddenSize, random);
        biasOutput = initializeVector(outputSize, random);
    }

    public double[] forward(double[] input){
        double[] hiddenLayer = activateLeakyReLU(matrixVectorMultiply(weightsInputHidden, input, biasHidden));
        return activateLeakyReLU(matrixVectorMultiply(weightsHiddenOutput, hiddenLayer, biasOutput));
    }

    private double[] activateLeakyReLU(double[] x){
        double[] activated = new double[x.length];

        for(int i = 0; i < x.length; i++){
            activated[i] = x[i] > 0 ? x[i] : 0.01*x[i];
        }

        return activated;
    }

    /*
    private double[] activate(double[] x){
        double[] activated = new double[x.length];

        for(int i = 0; i < x.length; i++){
            activated[i] = Math.max(0, x[i]);
        }

        return activated;
    }
    */

    private double[] matrixVectorMultiply(double[][] matrix, double[] vector, double[] bias){
        double[] result = new double[matrix[0].length];

        for(int j = 0; j < matrix[0].length; j++){
            result[j] = bias[j];

            for(int i = 0; i < matrix.length; i++){
                result[j] += matrix[i][j] * vector[i];
            }
        }

        return result;
    }

    private double[][] initializeMatrix(int rows, int cols, Random random){
        double[][] matrix = new double[rows][cols];

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                matrix[i][j] = random.nextDouble()-0.5;
            }
        }

        return matrix;
    }

    private double[] initializeVector(int size, Random random){
        double[] vector = new double[size];

        for(int i = 0; i < size; i++){
            vector[i] = random.nextDouble()-0.5;
        }

        return vector;
    }
}
