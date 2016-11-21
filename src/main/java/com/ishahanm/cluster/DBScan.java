package com.ishahanm.cluster;

import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * Created by shahanm on 11/21/16.
 *
 */
public class DBScan {

    private Vector<Point> neighbors;
    private Vector<Point> visitedList = new Vector<>();

    private Vector<Point> vectorSpace;
    private int minSize;
    private double eps;

    public DBScan(Vector<Point> vectorSpace, Double eps, Integer minSize) {
        this.vectorSpace = vectorSpace;
        this.minSize = minSize;
        this.eps = eps;
    }

    public DBScan(Vector<Point> vectorSpace) {
        this(vectorSpace, 0.5, 5);
    }

    public double getDistance(Point p, Point q) {
        double dx = p.getX() - q.getX();
        double dy = p.getY() - q.getY();

        return Math.sqrt(dx * dx + dy * dy);
    }

    private void visit(Point d) {
        visitedList.add(d);
    }

    private boolean isVisited(Point c) {
        return visitedList.contains(c);
    }

    private Vector<Point> merge(Vector<Point> vectorA, Vector<Point> vectorB) {
        vectorB.stream().filter(t -> !vectorA.contains(t)).forEach(vectorA::add);
        return vectorA;
    }

    private static Boolean equalPoints(Point m, Point n) {
        return (m.getX() == n.getX()) && (m.getY() == n.getY());
    }

    public Vector<List> execute() {
        Vector<List> result = new Vector<>();
        vectorSpace.stream().filter(p -> !isVisited(p)).forEach(p -> {
            visit(p);
            neighbors = getNeighbours(p);
            if (neighbors.size() > minSize) {
                neighbors.stream().filter(r -> !isVisited(r)).forEach(r -> {
                    visit(r);
                    Vector<Point> nextNeighbor = getNeighbours(r);
                    if (nextNeighbor.size() >= minSize) {
                        neighbors = merge(neighbors, nextNeighbor);
                    }
                });
            }
            result.add(neighbors);
        });
        return result;
    }

    public Vector<Point> getNeighbours(Point p) {
        return vectorSpace.stream().filter(q -> getDistance(p, q) <= eps)
            .collect(Collectors.toCollection(Vector::new));
    }

}
