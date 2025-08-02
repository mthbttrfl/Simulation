package org.example.factories.simulations;

import org.example.actions.Action;
import org.example.renders.Render;
import org.example.renders.RenderImpl;
import org.example.renders.sprites.SpriteRegister;
import org.example.simulations.Simulation;
import org.example.simulations.SimulationImpl;
import org.example.worlds.World;

public abstract class BaseSimulationFactory implements FactorySimulation {
    protected final SpriteRegister spriteRegister;

    protected BaseSimulationFactory(SpriteRegister spriteRegister) {
        this.spriteRegister = spriteRegister;
    }

    protected abstract World createWorld();
    protected abstract Action createInitAction();
    protected abstract Action createTurnAction();

    protected Render createRender() {
        return new RenderImpl(spriteRegister);
    }

    @Override
    public Simulation get() {
        return new SimulationImpl(
                createWorld(),
                createInitAction(),
                createTurnAction(),
                createRender()
        );
    }
}