package org.example.factories.movable;

import org.example.entities.Entity;
import org.example.entities.movable.Wolf;
import org.example.validators.FactoryValidator;
import org.example.coordinates.Coordinate;

import java.util.List;

public class WolfFactory extends MovableFactoryAncestor implements FactoryMovable {
    public WolfFactory(Class<? extends Entity> ... targets) {
        super(targets);
    }

    public WolfFactory(FactoryValidator validator, Class<? extends Entity>... targets) {
        super(validator, targets);
    }

    @Override
    public Entity get(Coordinate coordinate) {
        return new Wolf(coordinate, List.of(targets));
    }
}
