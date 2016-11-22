package com.ishahanm.cluster;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by shahanm on 11/21/16.
 *
 */
public class DBScan {

    private List<Point> neighbors;
    private List<Point> visitedList = Lists.newArrayList();

    private List<Point> vectorSpace;
    private int minSize;
    private double eps;

    public DBScan(List<Point> vectorSpace, Double eps, Integer minSize) {
        this.vectorSpace = vectorSpace;
        this.minSize = minSize;
        this.eps = eps;
    }

    public DBScan(List<Point> vectorSpace) {
        this(vectorSpace, 0.5, 5);
    }

    private void visit(Point d) {
        visitedList.add(d);
    }

    private boolean isVisited(Point c) {
        return visitedList.contains(c);
    }

    private List<Point> merge(List<Point> vectorA, List<Point> vectorB) {
        vectorB.stream().filter(t -> !vectorA.contains(t)).forEach(vectorA::add);
        return vectorA;
    }

    private static Boolean equalPoints(Point m, Point n) {
        return (m.getX() == n.getX()) && (m.getY() == n.getY());
    }

    public List<Cluster> execute() {
        System.out.println(vectorSpace.size());

        List<Cluster> result = Lists.newArrayList();
        vectorSpace.stream().filter(p -> !isVisited(p)).forEach(p -> {
            visit(p);
            neighbors = getNeighbours(p);
            if (neighbors.size() > minSize) {
                neighbors.stream().filter(r -> !isVisited(r)).forEach(r -> {
                    visit(r);
                    List<Point> nextNeighbor = getNeighbours(r);
                    if (nextNeighbor.size() >= minSize) {
                        neighbors = merge(neighbors, nextNeighbor);
                    }
                });
            }
            result.add(new Cluster(vectorSpace.indexOf(p), neighbors));
        });

        return result;
    }

    public List<Point> getNeighbours(Point p) {
        return vectorSpace.stream().filter(q -> Point.getDistance(p, q) <= eps)
            .collect(Collectors.toList());
    }

}
