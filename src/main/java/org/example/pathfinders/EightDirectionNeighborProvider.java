package org.example.pathfinders;

import org.example.coordinates.Coordinate;

import java.util.Arrays;
import java.util.List;

public class EightDirectionNeighborProvider implements NeighborProvider {
    @Override
    public List<Coordinate> getNeighbors(Coordinate coordinate) {
        return Arrays.asList(
                new Coordinate(coordinate.row() - 1, coordinate.column()),
                new Coordinate(coordinate.row() + 1, coordinate.column()),
                new Coordinate(coordinate.row(), coordinate.column() - 1),
                new Coordinate(coordinate.row(), coordinate.column() + 1),
                new Coordinate(coordinate.row() - 1, coordinate.column() - 1),
                new Coordinate(coordinate.row() - 1, coordinate.column() + 1),
                new Coordinate(coordinate.row() + 1, coordinate.column() - 1),
                new Coordinate(coordinate.row() + 1, coordinate.column() + 1)
        );
    }
}
