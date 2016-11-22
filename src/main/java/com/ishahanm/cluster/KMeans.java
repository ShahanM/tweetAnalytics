package com.ishahanm.cluster;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;

/**
 * Created by shahanm on 11/21/16.
 *
 */
public class KMeans {

    private static final int CLUSTER_NUM = 3;

    private List<Point> points;
    private List<Cluster> clusters;

    public KMeans(List<Point> points) {
        this.points = points;
        this.clusters = Lists.newArrayList();
    }

    private Point getMaxPoint(){
        Point maxPoint = new Point(0, 0);
        points.stream().forEach(p -> {
            if(p.getX() > maxPoint.getX()){
                maxPoint.setX(p.getX());
            }
            if(p.getY() > maxPoint.getY()){
                maxPoint.setY(p.getY());
            }
        });
        return maxPoint;
    }

    public void generateRandomClusters() {
        Point maxPoint = getMaxPoint();
        for (int i = 0; i < CLUSTER_NUM; i++) {
            Cluster cluster = new Cluster(i);
            Point centroid = createRandomPoint(maxPoint.getX(), maxPoint.getY());
            cluster.setCentroid(centroid);
            clusters.add(cluster);
        }
    }

    private Point createRandomPoint(double min, double max) {
        Random r = new Random();
        double x = min + (max - min) * r.nextDouble();
        double y = min + (max - min) * r.nextDouble();
        return new Point(x,y);
    }

    public List<Cluster> execute() {
        generateRandomClusters();
        boolean finish = false;

        while(!finish) {
            List<Point> lastCentroids = getCentroids();
            assignPointsToCluster();
            calculateCentroids();

            List<Point> currentCentroids = getCentroids();
            double distance = 0;
            for(int i = 0; i < lastCentroids.size(); i++) {
                distance += Point.getDistance(lastCentroids.get(i), currentCentroids.get(i));
            }
            finish = distance == 0;
        }
        return clusters;
    }

    private List<Point> getCentroids() {
        List<Point> centroids = Lists.newArrayList();
        for(Cluster cluster : clusters) {
            Point aux = cluster.getCentroid();
            Point point = new Point(aux.getX(),aux.getY());
            centroids.add(point);
        }
        return centroids;
    }

    private void assignPointsToCluster() {
        double max = Double.MAX_VALUE;
        double min;
        int cluster = 0;
        double distance;

        for(Point point : points) {
            min = max;
            for(int i = 0; i < CLUSTER_NUM; i++) {
                Cluster c = clusters.get(i);
                distance = Point.getDistance(point, c.getCentroid());
                if(distance < min){
                    min = distance;
                    cluster = i;
                }
            }
            clusters.get(cluster).addPoint(point);
        }
    }

    private void calculateCentroids() {
        for(Cluster cluster : clusters) {
            double sumX = 0;
            double sumY = 0;
            List<Point> clusterPoints = cluster.getPoints();
            int nPoints = clusterPoints.size();

            for(Point point : clusterPoints) {
                sumX += point.getX();
                sumY += point.getY();
            }
            Point centroid = cluster.getCentroid();
            if(nPoints < 0) {
                centroid.setX(sumX / nPoints);
                centroid.setY(sumY / nPoints);
            }
        }
    }
}
