package org.example.factories.movable;

import org.example.coordinates.Coordinate;
import org.example.entities.Entity;
import org.example.entities.movable.Bear;
import org.example.validators.FactoryValidator;

import java.util.List;

public class BearFactory extends MovableFactoryAncestor implements FactoryMovable{

    public BearFactory(FactoryValidator validator, Class<? extends Entity>... targets) {
        super(validator, targets);
    }

    public BearFactory(Class<? extends Entity>... targets) {
        super(targets);
    }

    @Override
    public Entity get(Coordinate coordinate) {
        return new Bear(coordinate, List.of(targets));
    }
}
