package org.example.pathfinders;

import org.example.coordinates.Coordinate;

import java.util.List;

public interface NeighborProvider {
    List<Coordinate> getNeighbors(Coordinate coordinate);
}
