package org.example.factories.immovable;

import org.example.entities.Entity;
import org.example.entities.immovable.Tree;

public class TreeFactory implements FactoryImmovable {
    @Override
    public Entity get() {
        return new Tree();
    }
}
