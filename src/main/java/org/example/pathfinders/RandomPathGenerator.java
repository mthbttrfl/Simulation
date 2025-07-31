package org.example.pathfinders;

import org.example.validators.DefaultPathValidator;
import org.example.validators.PathValidator;
import org.example.worlds.World;
import org.example.coordinates.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomPathGenerator implements PathGenerator {
    private final Random random = new Random();
    private final NeighborProvider neighborProvider;
    private final PathValidator pathValidator;

    public RandomPathGenerator(NeighborProvider neighborProvider) {
        this(neighborProvider, new DefaultPathValidator());
    }

    public RandomPathGenerator(NeighborProvider neighborProvider, PathValidator pathValidator) {
        this.neighborProvider = neighborProvider;
        this.pathValidator = pathValidator;
    }

    @Override
    public List<Coordinate> generatePath(Coordinate start, int maxSteps, World world) {
        List<Coordinate> path = new ArrayList<>();
        path.add(start);

        Coordinate current = start;
        int steps = 0;

        while (steps < maxSteps) {
            List<Coordinate> neighbors = getValidNeighbors(current, world);
            if (neighbors.isEmpty()) break;

            Coordinate next = neighbors.get(random.nextInt(neighbors.size()));
            path.add(next);
            current = next;
            steps++;
        }

        return path;
    }

    private List<Coordinate> getValidNeighbors(Coordinate coord, World world) {
        return neighborProvider.getNeighbors(coord).stream()
                .filter(c -> pathValidator.isValidCoordinate(c, world))
                .filter(c -> pathValidator.isPassable(c, world))
                .toList();
    }
}
