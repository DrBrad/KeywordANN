package org.sectorrent.ann;

import java.util.Arrays;

public class HashedInput {

    private static final int INPUT_SIZE = 128;

    private static int hash(String word){
        return Math.abs(word.hashCode()) % INPUT_SIZE;
    }

    public static double[] vectorize(String input){
        double[] vector = new double[INPUT_SIZE];
        Arrays.fill(vector, 0.0);

        String[] words = input.split(" ");
        for(String word : words){
            int index = hash(word);
            vector[index] += 1.0;
        }

        return normalize(vector);
    }

    private static double[] normalize(double[] vector){
        double magnitude = 0.0;

        for(double v : vector){
            magnitude += v*v;
        }

        magnitude = Math.sqrt(magnitude);

        if (magnitude == 0.0) return vector;

        double[] normalized = new double[vector.length];

        for(int i = 0; i < vector.length; i++){
            normalized[i] = vector[i]/magnitude;
        }

        return normalized;
    }
}

