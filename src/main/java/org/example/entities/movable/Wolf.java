package org.example.entities.movable;

import org.example.coordinates.Coordinate;
import org.example.entities.Entity;
import org.example.entities.Predator;

import java.util.List;

public class Wolf extends Predator {

    public Wolf(Coordinate coordinate, List<Class<? extends Entity>> targets) {
        super(coordinate, targets);
    }
}