package org.example.validators;

import org.example.entities.Entity;
import org.example.exceptions.CoordinateOccupiedException;
import org.example.exceptions.EntityNotFoundException;
import org.example.exceptions.InvalidCoordinateException;
import org.example.exceptions.WorldFullException;
import org.example.coordinates.Boundary;
import org.example.coordinates.Coordinate;

import java.util.Map;

public class DefaultWorldValidator implements WorldValidator {
    @Override
    public void validateCoordinate(Coordinate coordinate, Boundary boundary) {
        if (!boundary.contains(coordinate)) {
            throw new InvalidCoordinateException("Invalid coordinate: " + coordinate);
        }
    }

    @Override
    public void validateAddOperation(Coordinate coordinate, Map<Coordinate, Entity> entities, Boundary boundary) {
        validateCoordinate(coordinate, boundary);

        if (entities.size() >= boundary.capacity() && !entities.containsKey(coordinate)) {
            throw new WorldFullException("Cannot add entity to new coordinate - world is full");
        }
        if (entities.containsKey(coordinate)) {
            throw new CoordinateOccupiedException("Coordinate occupied: " + coordinate);
        }
    }

    @Override
    public void validateRemoveOperation(Coordinate coordinate, Map<Coordinate, Entity> entities, Boundary boundary) {
        validateCoordinate(coordinate, boundary);

        if (!entities.containsKey(coordinate)) {
            throw new EntityNotFoundException("No entity to remove at " + coordinate);
        }
    }
}
