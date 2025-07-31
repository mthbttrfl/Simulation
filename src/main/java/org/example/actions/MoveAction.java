package org.example.actions;

import org.example.entities.Creature;
import org.example.worlds.World;

import java.util.List;

public class MoveAction implements Action{
    @Override
    public void execute(World world) {
        List<Creature> creatures = (List<Creature>) world.getAllByType(Creature.class);
        creatures.forEach(creature -> creature.makeMove(world));
    }
}
