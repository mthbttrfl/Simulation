package org.example.entities.movable;

import org.example.entities.Entity;
import org.example.coordinates.Coordinate;
import org.example.entities.Herbivore;

import java.util.List;

public class Rabbit extends Herbivore {

    public Rabbit(Coordinate coordinate, List<Class<? extends Entity>> targets) {
        super(coordinate, targets);
    }
}
