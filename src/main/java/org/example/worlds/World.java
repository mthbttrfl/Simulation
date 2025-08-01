package org.example.worlds;

import org.example.coordinates.Coordinate;
import org.example.entities.Creature;
import org.example.entities.Entity;

import java.util.List;

public interface World {
    int getSizeRow();
    int getSizeColumn();

    void put(int row, int column, Entity entity);
    void put(Coordinate coordinate, Entity entity);

    Entity getByKey(int row, int column);
    Entity getByKey(Coordinate coordinate);

    List<? extends Entity> getAllByType(Class <? extends Entity> entity);

    void remove (int row, int column);
    void remove (Coordinate coordinate);

    void update (int row, int column, Creature creature);
    void update (Coordinate coordinate, Creature creature);

    boolean isEmptyKey(int row, int column);
    boolean isEmptyKey(Coordinate coordinate);

    List<Coordinate> getEmptyKey();

    boolean isFull ();
}
