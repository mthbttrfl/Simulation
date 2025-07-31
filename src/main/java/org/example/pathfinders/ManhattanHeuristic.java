package org.example.pathfinders;

import org.example.coordinates.Coordinate;

import java.util.List;

public class ManhattanHeuristic implements Heuristic {
    @Override
    public int calculate(Coordinate from, List<Coordinate> targets) {
        int minDistance = Integer.MAX_VALUE;
        for (Coordinate target : targets) {
            int distance = Math.abs(from.row() - target.row()) +
                    Math.abs(from.column() - target.column());
            if (distance < minDistance) {
                minDistance = distance;
            }
        }
        return minDistance;
    }
}
