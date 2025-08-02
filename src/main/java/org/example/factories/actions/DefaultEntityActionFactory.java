package org.example.factories.actions;

import org.example.actions.Action;
import org.example.actions.MoveAction;
import org.example.actions.SpawnAction;
import org.example.entities.immovable.Grass;
import org.example.entities.immovable.Tree;
import org.example.entities.movable.Beaver;
import org.example.entities.movable.Rabbit;
import org.example.factories.immovable.FactoryImmovable;
import org.example.factories.immovable.GrassFactory;
import org.example.factories.immovable.RockFactory;
import org.example.factories.immovable.TreeFactory;
import org.example.factories.movable.BeaverFactory;
import org.example.factories.movable.FactoryMovable;
import org.example.factories.movable.RabbitFactory;
import org.example.factories.movable.WolfFactory;
import org.example.spawners.ImmovableRandomSpawner;
import org.example.spawners.MovableRandomSpawner;

import java.util.List;

public class DefaultEntityActionFactory implements BaseEntityActionFactory {
    public List<FactoryMovable> createMovableFactories() {
        return List.of(
                new WolfFactory(Rabbit.class, Beaver.class),
                new BeaverFactory(Tree.class),
                new RabbitFactory(Grass.class)
        );
    }

    public List<FactoryImmovable> createImmovableFactories() {
        return List.of(
                new GrassFactory(),
                new TreeFactory(),
                new RockFactory()
        );
    }

    public Action createSpawnAction() {
        return new SpawnAction(List.of(
                new MovableRandomSpawner(createMovableFactories()),
                new ImmovableRandomSpawner(createImmovableFactories())
        ));
    }

    public Action createMoveAction() {
        return new MoveAction();
    }
}