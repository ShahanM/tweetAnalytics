package com.ishahanm.cluster;

/**
 * Created by shahanm on 11/21/16.
 */
public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public static double getDistance(Point p, Point q) {
        double dx = p.getX() - q.getX();
        double dy = p.getY() - q.getY();

        return Math.sqrt(dx * dx + dy * dy);
    }

    public void setY(double y) {
        this.y = y;
    }
}
