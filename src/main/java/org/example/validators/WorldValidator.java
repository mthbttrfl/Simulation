package org.example.validators;

import org.example.coordinates.Boundary;
import org.example.coordinates.Coordinate;
import org.example.entities.Entity;

import java.util.Map;

public interface WorldValidator {
    void validateAddOperation(Coordinate coordinate, Map<Coordinate, Entity> entities, Boundary boundary);
    void validateRemoveOperation(Coordinate coordinate, Map<Coordinate, Entity> entities, Boundary boundary);
    void validateCoordinate(Coordinate coordinate, Boundary boundary);
}

