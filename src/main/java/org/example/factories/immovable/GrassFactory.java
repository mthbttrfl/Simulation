package org.example.factories.immovable;

import org.example.entities.Entity;
import org.example.entities.immovable.Grass;

public class GrassFactory implements FactoryImmovable {
    @Override
    public Entity get() {
        return new Grass();
    }
}
