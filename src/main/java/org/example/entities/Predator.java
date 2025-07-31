package org.example.entities;

import org.example.coordinates.Coordinate;
import org.example.worlds.World;

import java.util.List;

public abstract class Predator extends Creature{
    private final static int DEFAULT_DAMAGE = 2;

    private int damage;

    public Predator(Coordinate coordinate, List<Class<? extends Entity>> targets) {
        super(coordinate, targets);
        this.damage = DEFAULT_DAMAGE;
    }

    @Override
    protected void foodIteration(World world, Coordinate coordinate) {
        Entity entity = world.getByKey(coordinate);
        for (Class<? extends Entity> target : getTargets()) {
            if (target.isInstance(entity) && entity instanceof Creature) {
                Creature prey = (Creature) entity;
                prey.takeDamage(getDamage());
                if (prey.isDead()) {
                    world.remove(coordinate);
                    resetHungryPoints();
                    resetHP();
                }
            }
        }
    }

    public int getDamage() {
        return damage;
    }
}
