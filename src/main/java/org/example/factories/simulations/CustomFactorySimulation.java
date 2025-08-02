package org.example.factories.simulations;

import org.example.actions.Action;
import org.example.factories.actions.BaseEntityActionFactory;
import org.example.factories.actions.DefaultEntityActionFactory;
import org.example.factories.worlds.CustomWorldFactory;
import org.example.factories.worlds.FactoryWorld;
import org.example.renders.sprites.SpriteRegister;
import org.example.worlds.World;

public class CustomFactorySimulation extends BaseSimulationFactory {
    private final FactoryWorld factoryWorld;
    private final BaseEntityActionFactory actionFactory;

    public CustomFactorySimulation(int rows, int cols, SpriteRegister spriteRegister) {
        super(spriteRegister);
        this.factoryWorld = new CustomWorldFactory(rows, cols);
        this.actionFactory = new DefaultEntityActionFactory();
    }

    @Override
    protected World createWorld() {
        return factoryWorld.create();
    }

    @Override
    protected Action createInitAction() {
        return actionFactory.createSpawnAction();
    }

    @Override
    protected Action createTurnAction() {
        return actionFactory.createMoveAction();
    }
}