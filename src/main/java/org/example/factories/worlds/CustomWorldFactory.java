package org.example.factories.worlds;

import org.example.worlds.World;
import org.example.worlds.WorldMap;

public class CustomWorldFactory implements FactoryWorld {
    private final int rows;
    private final int cols;

    public CustomWorldFactory(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    @Override
    public World create() {
        return new WorldMap(rows, cols);
    }
}