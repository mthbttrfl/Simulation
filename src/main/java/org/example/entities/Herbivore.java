package org.example.entities;

import org.example.coordinates.Coordinate;
import org.example.worlds.World;

import java.util.List;

public abstract class Herbivore  extends Creature {

    public Herbivore(Coordinate coordinate, List<Class<? extends Entity>> targets) {
        super(coordinate, targets);
    }


    @Override
    protected void foodIteration(World world, Coordinate coordinate) {
        Entity entity = world.getByKey(coordinate);
        for (Class<? extends Entity> target : getTargets()) {
            if (target.isInstance(entity)) {
                world.remove(coordinate);
                resetHungryPoints();
                resetHP();
            }
        }
    }
}
