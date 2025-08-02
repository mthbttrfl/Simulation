package org.example.factories.actions;

import org.example.actions.Action;
import org.example.factories.immovable.FactoryImmovable;
import org.example.factories.movable.FactoryMovable;

import java.util.List;

public interface BaseEntityActionFactory {
    public List<FactoryMovable> createMovableFactories();
    public List<FactoryImmovable> createImmovableFactories();
    public Action createSpawnAction();
    public Action createMoveAction();
}