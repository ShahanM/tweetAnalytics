package com.ishahanm.cluster;

import java.util.List;

/**
 * Created by shahanm on 11/21/16.
 *
 */
public final class SimilarityMatrix {

    public static float findCosineSimilarity(List<Float> vectorA, List<Float> vectorB){
        float dotProduct = dotProduct(vectorA, vectorB);
        float magnitudeA = magnitude(vectorA);
        float magnitudeB = magnitude(vectorB);
        float result = dotProduct / (magnitudeA * magnitudeB);

        return Float.isNaN(result) ? 0 : result;
    }

    public static float dotProduct(List<Float> vectorA, List<Float> vectorB){
        float product = 0;

        for(int i = 0; i < vectorA.size(); i++){
            product += vectorA.get(i) * vectorB.get(i);
        }

        return product;
    }

    public static float magnitude(List<Float> vector){
        return (float)Math.sqrt(dotProduct(vector, vector));
    }

    public static float findEucledianDistance(int[] vectorA, int[] vectorB){
        float euclidianDistance = 0;

        for(int i = 0; i < vectorA.length; i++){
            euclidianDistance += Math.pow((vectorA[i] - vectorB[i]), 2);
        }

        return euclidianDistance;
    }

    public static float findExtendedJaccard(List<Float> vectorA, List<Float> vectorB){
        float dotProduct = dotProduct(vectorA, vectorB);
        float magnitudeA = magnitude(vectorA);
        float magnitudeB = magnitude(vectorB);

        return dotProduct / (magnitudeA + magnitudeB - dotProduct);
    }


}
