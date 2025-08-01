package org.example.worlds;

import org.example.coordinates.Boundary;
import org.example.coordinates.Coordinate;
import org.example.entities.Creature;
import org.example.entities.Entity;
import org.example.exceptions.EntityNotFoundException;
import org.example.validators.DefaultWorldValidator;
import org.example.validators.WorldValidator;

import java.util.*;
import java.util.stream.Collectors;

public class WorldMap implements World {
    private static final int DEFAULT_ROW = 25;
    private static final int DEFAULT_COLUMN = 25;

    private final Map<Coordinate, Entity> entities = new HashMap<>();
    private final Boundary boundary;
    private final WorldValidator validator;

    public WorldMap() {
        this(new Boundary(DEFAULT_ROW, DEFAULT_COLUMN), new DefaultWorldValidator());
    }

    public WorldMap(int row, int column) {
        this(new Boundary(row, column), new DefaultWorldValidator());
    }

    public WorldMap(Boundary boundary, WorldValidator validator) {
        this.boundary = boundary;
        this.validator = validator;
    }

    @Override
    public int getSizeRow() {
        return boundary.rows();
    }

    @Override
    public int getSizeColumn() {
        return boundary.columns();
    }

    @Override
    public void put(Coordinate coordinate, Entity entity) {
        validator.validateAddOperation(coordinate, entities, boundary);
        entities.put(coordinate, entity);
    }

    @Override
    public void put(int row, int column, Entity entity) {
        put(new Coordinate(row, column), entity);
    }

    @Override
    public Entity getByKey(Coordinate coordinate) {
        validator.validateCoordinate(coordinate, boundary);
        return Optional.ofNullable(entities.get(coordinate))
                .orElseThrow(() -> new EntityNotFoundException("Entity not found at " + coordinate.toString()));
    }

    @Override
    public Entity getByKey(int row, int column) {
        return getByKey(new Coordinate(row, column));
    }

    @Override
    public List<? extends Entity> getAllByType(Class<? extends Entity> entityType) {
        return entities.values().stream()
                .filter(entityType::isInstance)
                .collect(Collectors.toList());
    }

    @Override
    public void remove(Coordinate coordinate) {
        validator.validateRemoveOperation(coordinate, entities, boundary);
        entities.remove(coordinate);
    }

    @Override
    public void remove(int row, int column) {
        remove(new Coordinate(row, column));
    }

    @Override
    public void update(int row, int column, Creature creature) {
        update(new Coordinate(row,column), creature);
    }

    @Override
    public void update(Coordinate coordinate, Creature creature) {
        Coordinate oldCoord = creature.getCoordinate();
        put(coordinate,creature);
        remove(oldCoord);
    }

    @Override
    public boolean isEmptyKey(Coordinate coordinate) {
        validator.validateCoordinate(coordinate, boundary);
        return !entities.containsKey(coordinate);
    }

    @Override
    public boolean isEmptyKey(int row, int column) {
        return isEmptyKey(new Coordinate(row, column));
    }

    @Override
    public List<Coordinate> getEmptyKey() {
        List<Coordinate> coordinates = new ArrayList<>();
        for (int i = 1; i <= getSizeRow(); i++) {
            for (int j = 1; j <= getSizeColumn(); j++) {
                if (isEmptyKey(i,j)){
                    coordinates.add(new Coordinate(i,j));
                }
            }
        }
        return coordinates;
    }

    @Override
    public boolean isFull() {
        return entities.size() >= boundary.capacity();
    }
}