package org.example.spawners;

import org.example.entities.Entity;
import org.example.worlds.World;
import org.example.coordinates.Coordinate;

import java.util.List;
import java.util.Random;

public abstract class RandomSpawner implements Spawner {
    protected final Random random;

    public RandomSpawner() {
        this.random = new Random();
    }

    @Override
    public void spawning(World world){
        for (int i = 0; i < getRandomQuantity(world); i++) {
            List<Coordinate> coordinates = world.getEmptyKey();
            Coordinate coordinate = coordinates.get(random.nextInt(coordinates.size()));
            Entity entity = getRandomEntity(coordinate);
            world.put(coordinate,entity);
        }
    }

    protected abstract Entity getRandomEntity(Coordinate coordinate);

    public final int getRandomQuantity(World world){
        int count = world.getEmptyKey().size();
        return random.nextInt(count);
    }
}
