package org.example.spawners;

import org.example.entities.Entity;
import org.example.factories.movable.FactoryMovable;
import org.example.coordinates.Coordinate;

import java.util.List;

public class MovableRandomSpawner extends RandomSpawner{

    private final List <? extends FactoryMovable> factories;

    public MovableRandomSpawner(List<? extends FactoryMovable> factories) {
        this.factories = factories;
    }

    @Override
    protected Entity getRandomEntity(Coordinate coordinate) {
        FactoryMovable factory = factories.get(random.nextInt(factories.size()));
        return factory.get(coordinate);
    }
}
