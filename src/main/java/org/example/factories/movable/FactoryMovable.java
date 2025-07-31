package org.example.factories.movable;

import org.example.entities.Entity;
import org.example.coordinates.Coordinate;

public interface FactoryMovable {
    Entity get (Coordinate coordinate);
}
