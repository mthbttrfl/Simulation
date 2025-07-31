package org.example.renders.sprites;

import org.example.entities.Entity;

public interface SpriteRegister {
    void add(Class<? extends Entity> clazz, String sprite);
    String get(Class<? extends Entity> clazz);
}
