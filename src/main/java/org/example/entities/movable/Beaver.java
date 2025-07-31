package org.example.entities.movable;

import org.example.entities.Entity;
import org.example.coordinates.Coordinate;
import org.example.entities.Herbivore;

import java.util.List;

public class Beaver extends Herbivore {

    public Beaver(Coordinate coordinate, List<Class<? extends Entity>> targets) {
        super(coordinate, targets);
    }
}
