package org.example.factories.immovable;

import org.example.entities.Entity;
import org.example.entities.immovable.Rock;

public class RockFactory implements FactoryImmovable {
    @Override
    public Entity get() {
        return new Rock();
    }
}
