package org.example.renders;

import org.example.entities.Entity;
import org.example.worlds.World;

public interface Render {
    String rendering(World world);
    String getSprite(Entity entity);
}
