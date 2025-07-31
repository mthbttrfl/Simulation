package org.example;

import org.example.entities.movable.Beaver;
import org.example.entities.movable.Rabbit;
import org.example.entities.movable.Wolf;
import org.example.entities.immovable.Grass;
import org.example.entities.immovable.Rock;
import org.example.entities.immovable.Tree;
import org.example.factories.simulations.DefaultFactorySimulation;
import org.example.factories.simulations.FactorySimulation;
import org.example.renders.sprites.SpriteRegister;
import org.example.renders.sprites.SpriteRegisterImpl;
import org.example.simulations.Simulation;


public class Main {
    public static void main(String[] args) {
        SpriteRegister spriteRegister = new SpriteRegisterImpl();
        spriteRegister.add(Grass.class, "\uD83C\uDF31 ");
        spriteRegister.add(Rock.class, "\uD83E\uDEA8 ");
        spriteRegister.add(Tree.class, "🌳 ");
        spriteRegister.add(Wolf.class, "\uD83D\uDC3A ");
        spriteRegister.add(Beaver.class, "\uD83E\uDDAB ");
        spriteRegister.add(Rabbit.class, "\uD83D\uDC30 ");

        FactorySimulation factory = new DefaultFactorySimulation(spriteRegister);

        Simulation simulation = factory.get();
        simulation.start();
    }
}

