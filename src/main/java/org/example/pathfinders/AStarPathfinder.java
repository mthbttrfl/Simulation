package org.example.pathfinders;

import org.example.coordinates.Coordinate;
import org.example.entities.Entity;
import org.example.worlds.World;

import java.util.*;

public class AStarPathfinder {
    private final Heuristic heuristic;
    private final NeighborProvider neighborProvider;
    private final PathGenerator fallbackPathGenerator;

    public AStarPathfinder() {
        this(new ManhattanHeuristic(),
                new EightDirectionNeighborProvider(),
                new RandomPathGenerator(new EightDirectionNeighborProvider()));
    }

    public AStarPathfinder(Heuristic heuristic,
                           NeighborProvider neighborProvider,
                           PathGenerator fallbackPathGenerator) {
        this.heuristic = heuristic;
        this.neighborProvider = neighborProvider;
        this.fallbackPathGenerator = fallbackPathGenerator;
    }

    public List<Coordinate> findPath(Coordinate start, int speed,
                                     List<Class<? extends Entity>> targets,
                                     World world) {
        List<Coordinate> targetCoordinates = collectTargetCoordinates(world, targets);
        if (targetCoordinates.isEmpty()) {
            return fallbackPathGenerator.generatePath(start, speed, world);
        }

        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Map<Coordinate, Integer> gScore = new HashMap<>();
        Set<Coordinate> closedSet = new HashSet<>();
        int startH = heuristic.calculate(start, targetCoordinates);
        Node startNode = new Node(start, null, 0, startH);
        openSet.add(startNode);
        gScore.put(start, 0);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            Coordinate currentCoord = current.coordinate;

            if (targetCoordinates.contains(currentCoord)) {
                return reconstructPath(current);
            }

            closedSet.add(currentCoord);

            for (Coordinate neighbor : neighborProvider.getNeighbors(currentCoord)) {
                if (!isWithinBounds(neighbor, world) || closedSet.contains(neighbor)) {
                    continue;
                }

                if (!isPassable(neighbor, world, targets, start)) {
                    continue;
                }

                int tentativeG = current.g + 1;
                int currentG = gScore.getOrDefault(neighbor, Integer.MAX_VALUE);

                if (tentativeG < currentG) {
                    Node neighborNode = new Node(neighbor, current, tentativeG, heuristic.calculate(neighbor, targetCoordinates));
                    gScore.put(neighbor, tentativeG);
                    openSet.add(neighborNode);
                }
            }
        }
        return fallbackPathGenerator.generatePath(start, speed, world);
    }

    private boolean isWithinBounds(Coordinate coord, World world) {
        return coord.row() >= 1 && coord.row() <= world.getSizeRow() &&
                coord.column() >= 1 && coord.column() <= world.getSizeColumn();
    }

    private boolean isPassable(Coordinate coord, World world,
                               List<Class<? extends Entity>> targets, Coordinate start) {
        if (coord.equals(start)) {
            return true;
        }
        if (world.isEmptyKey(coord)) {
            return true;
        }
        Entity entity = world.getByKey(coord);
        for (Class<? extends Entity> target : targets) {
            if (target.isInstance(entity)) {
                return true;
            }
        }
        return false;
    }

    private List<Coordinate> collectTargetCoordinates(World world,
                                                      List<Class<? extends Entity>> targets) {
        List<Coordinate> targetCoords = new ArrayList<>();
        int rows = world.getSizeRow();
        int columns = world.getSizeColumn();

        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= columns; c++) {
                Coordinate coord = new Coordinate(r, c);
                if (!world.isEmptyKey(coord)) {
                    Entity entity = world.getByKey(coord);
                    for (Class<? extends Entity> target : targets) {
                        if (target.isInstance(entity)) {
                            targetCoords.add(coord);
                            break;
                        }
                    }
                }
            }
        }

        return targetCoords;
    }

    private List<Coordinate> reconstructPath(Node node) {
        LinkedList<Coordinate> path = new LinkedList<>();
        while (node != null) {
            path.addFirst(node.coordinate);
            node = node.parent;
        }
        return path;
    }

    static class Node implements Comparable<Node> {
        final Coordinate coordinate;
        final Node parent;
        final int g;
        final int h;

        Node(Coordinate coordinate, Node parent, int g, int h) {
            this.coordinate = coordinate;
            this.parent = parent;
            this.g = g;
            this.h = h;
        }

        int getF() {
            return g + h;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.getF(), other.getF());
        }
    }
}