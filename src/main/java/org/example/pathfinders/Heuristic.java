package org.example.pathfinders;

import org.example.coordinates.Coordinate;

import java.util.List;

public interface Heuristic {
    int calculate(Coordinate from, List<Coordinate> targets);
}
