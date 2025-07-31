package org.example.actions;

import org.example.spawners.Spawner;
import org.example.worlds.World;

import java.util.List;


public class SpawnAction implements Action{

    private final List<Spawner> spawners;

    public SpawnAction(List<Spawner> spawners) {
        this.spawners = spawners;
    }

    @Override
    public void execute(World world) {
        spawners.forEach(spawner -> spawner.spawning(world));
    }
}