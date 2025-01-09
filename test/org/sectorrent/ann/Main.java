package org.sectorrent.ann;

public class Main {

    public static void main(String[] args){
        HashedANN ann = new HashedANN(128, 16, 8, 12345);

        double[] outputVector1 = ann.forward(HashedInput.vectorize("avengers end game"));
        double[] outputVector2 = ann.forward(HashedInput.vectorize("end game avengers"));
        double[] outputVector3 = ann.forward(HashedInput.vectorize("game end avengers"));

        System.out.println("Output Vector 1:");
        printVector(outputVector1);

        System.out.println("Output Vector 2:");
        printVector(outputVector2);

        System.out.println("Output Vector 3:");
        printVector(outputVector3);
    }

    private static void printVector(double[] vector){
        for(double v : vector){
            System.out.printf("%.4f ", v);
        }
        System.out.println();
    }
}
