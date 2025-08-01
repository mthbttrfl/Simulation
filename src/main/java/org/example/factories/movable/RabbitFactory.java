package org.example.factories.movable;

import org.example.coordinates.Coordinate;
import org.example.entities.Entity;
import org.example.entities.movable.Rabbit;
import org.example.validators.FactoryValidator;

import java.util.List;

public class RabbitFactory extends MovableFactoryAncestor implements FactoryMovable {

    public RabbitFactory(Class<? extends Entity> ... targets) {
        super(targets);
    }

    public RabbitFactory(FactoryValidator validator, Class<? extends Entity>... targets) {
        super(validator, targets);
    }

    @Override
    public Entity get(Coordinate coordinate) {
        return new Rabbit(coordinate, List.of(targets));
    }
}