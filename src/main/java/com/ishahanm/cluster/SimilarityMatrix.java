package com.ishahanm.cluster;

import java.util.List;

/**
 * Created by shahanm on 11/21/16.
 *
 */
public final class SimilarityMatrix {

    public static Double findCosineSimilarity(List<Double> vectorA, List<Double> vectorB){
        Double dotProduct = dotProduct(vectorA, vectorB);
        Double magnitudeA = magnitude(vectorA);
        Double magnitudeB = magnitude(vectorB);
        Double result = dotProduct / (magnitudeA * magnitudeB);

        return Double.isNaN(result) ? 0 : result;
    }

    public static Double dotProduct(List<Double> vectorA, List<Double> vectorB){
        Double product = 0.0;

        for(int i = 0; i < vectorA.size(); i++){
            product += vectorA.get(i) * vectorB.get(i);
        }

        return product;
    }

    public static Double magnitude(List<Double> vector){
        return Math.sqrt(dotProduct(vector, vector));
    }

    public static Double findEucledianDistance(int[] vectorA, int[] vectorB){
        Double euclidianDistance = 0.0;

        for(int i = 0; i < vectorA.length; i++){
            euclidianDistance += Math.pow((vectorA[i] - vectorB[i]), 2);
        }

        return euclidianDistance;
    }

    public static Double findExtendedJaccard(List<Double> vectorA, List<Double> vectorB){
        Double dotProduct = dotProduct(vectorA, vectorB);
        Double magnitudeA = magnitude(vectorA);
        Double magnitudeB = magnitude(vectorB);

        return dotProduct / (magnitudeA + magnitudeB - dotProduct);
    }


}
