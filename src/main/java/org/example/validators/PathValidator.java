package org.example.validators;

import org.example.coordinates.Coordinate;
import org.example.worlds.World;

public interface PathValidator {
    boolean isValidCoordinate(Coordinate coord, World world);
    boolean isPassable(Coordinate coord, World world);
}
