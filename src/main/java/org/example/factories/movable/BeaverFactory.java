package org.example.factories.movable;

import org.example.entities.Entity;
import org.example.entities.movable.Beaver;
import org.example.validators.FactoryValidator;
import org.example.coordinates.Coordinate;

import java.util.List;

public class BeaverFactory extends MovableFactoryAncestor implements FactoryMovable {

    public BeaverFactory(Class<? extends Entity> ... targets) {
        super(targets);
    }

    public BeaverFactory(FactoryValidator validator, Class<? extends Entity>... targets) {
        super(validator, targets);
    }

    @Override
    public Entity get(Coordinate coordinate) {
        return new Beaver(coordinate, List.of(targets));
    }
}
