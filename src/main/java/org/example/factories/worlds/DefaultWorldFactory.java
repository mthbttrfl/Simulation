package org.example.factories.worlds;

import org.example.worlds.World;
import org.example.worlds.WorldMap;

public class DefaultWorldFactory implements FactoryWorld {
    @Override
    public World create() {
        return new WorldMap();
    }
}