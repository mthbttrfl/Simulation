package org.example.pathfinders;

import org.example.worlds.World;
import org.example.coordinates.Coordinate;

import java.util.List;

public interface PathGenerator {
    List<Coordinate> generatePath(Coordinate start, int maxSteps, World world);
}
