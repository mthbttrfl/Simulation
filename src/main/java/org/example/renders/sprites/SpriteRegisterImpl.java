package org.example.renders.sprites;

import org.example.entities.Entity;

import java.util.HashMap;
import java.util.Map;

public class SpriteRegisterImpl implements SpriteRegister {

    private final Map<Class<? extends Entity>, String> SPRITES = new HashMap<>();

    @Override
    public void add(Class<? extends Entity> clazz, String sprite) {
        SPRITES.put(clazz,sprite);
    }

    @Override
    public String get(Class<? extends Entity> clazz) {
        return SPRITES.get(clazz);
    }
}
