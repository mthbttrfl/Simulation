package org.example.factories.simulations;

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
import org.example.renders.Render;
import org.example.renders.RenderImpl;
import org.example.renders.sprites.SpriteRegister;
import org.example.simulations.Simulation;
import org.example.simulations.SimulationImpl;
import org.example.spawners.ImmovableRandomSpawner;
import org.example.spawners.MovableRandomSpawner;
import org.example.worlds.World;
import org.example.worlds.WorldMap;

import java.util.List;

public class DefaultFactorySimulation implements FactorySimulation {

    private SpriteRegister spriteRegister;

    public DefaultFactorySimulation(SpriteRegister spriteRegister) {
        this.spriteRegister = spriteRegister;
    }

    @Override
    public Simulation get() {
        Render render = new RenderImpl(spriteRegister);

        World world = new WorldMap();

        List<FactoryMovable> factoryMovable = List.of(
                new WolfFactory(Rabbit.class, Beaver.class),
                new BeaverFactory(Tree.class),
                new RabbitFactory(Grass.class));

        List<FactoryImmovable> factoryImmovable = List.of(
                new GrassFactory(),
                new TreeFactory(),
                new RockFactory());

        Action initActions = new SpawnAction(List.of(new MovableRandomSpawner(factoryMovable), new ImmovableRandomSpawner(factoryImmovable)));

        Action turnActions = new MoveAction();

        return new SimulationImpl(world, initActions, turnActions, render);
    }
}
