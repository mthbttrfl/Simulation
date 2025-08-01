package org.example.factories.movable;

import org.example.coordinates.Coordinate;
import org.example.entities.Entity;

public interface FactoryMovable {
    Entity get (Coordinate coordinate);
}
