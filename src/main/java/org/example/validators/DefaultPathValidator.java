package org.example.validators;

import org.example.coordinates.Coordinate;
import org.example.worlds.World;

public class DefaultPathValidator implements PathValidator {
    @Override
    public boolean isValidCoordinate(Coordinate coord, World world) {
        return coord.row() >= 1 && coord.row() <= world.getSizeRow() &&
                coord.column() >= 1 && coord.column() <= world.getSizeColumn();
    }

    @Override
    public boolean isPassable(Coordinate coord, World world) {
        return world.isEmptyKey(coord);
    }
}
