package org.example.spawners;

import org.example.coordinates.Coordinate;
import org.example.entities.Entity;
import org.example.factories.immovable.FactoryImmovable;

import java.util.List;

public class ImmovableRandomSpawner extends RandomSpawner{

    private final List <? extends FactoryImmovable> factories;

    public ImmovableRandomSpawner(List<? extends FactoryImmovable> factories) {
        this.factories = factories;
    }

    @Override
    protected Entity getRandomEntity(Coordinate coordinate) {
        FactoryImmovable factory = factories.get(random.nextInt(factories.size()));
        return factory.get();
    }
}
