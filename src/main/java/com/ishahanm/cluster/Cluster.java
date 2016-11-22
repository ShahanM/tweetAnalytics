package com.ishahanm.cluster;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by shahanm on 11/22/16.
 *
 */
public class Cluster {

    public List<Point> points;
    public Point centroid;
    public int id;
    public String label;

    public Cluster(int id) {
        this.id = id;
        this.points = Lists.newArrayList();
        this.centroid = null;
    }

    public Cluster(int id, List<Point> points){
        this(id);
        this.points = points;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public Point getCentroid() {
        return centroid;
    }

    public void setCentroid(Point centroid) {
        this.centroid = centroid;
    }

    public int getId() {
        return id;
    }

    public void clear() {
        points.clear();
    }

    public void plotCluster() {
        System.out.println("[Cluster: " + id + "]");
        System.out.println("[Centroid: " + centroid + "]");
        System.out.println("[Points: \n");
        points.forEach(System.out::println);
        System.out.println("]");
    }
}
