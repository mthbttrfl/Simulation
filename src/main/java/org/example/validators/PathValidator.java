package org.example.validators;

import org.example.worlds.World;
import org.example.coordinates.Coordinate;

public interface PathValidator {
    boolean isValidCoordinate(Coordinate coord, World world);
    boolean isPassable(Coordinate coord, World world);
}
