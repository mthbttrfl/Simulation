package org.example.coordinates;

public record Boundary(int rows, int columns) {
    public Boundary {
        if (rows <= 0 || columns <= 0) {
            throw new IllegalArgumentException("Invalid boundary dimensions");
        }
    }

    public boolean contains(Coordinate coord) {
        return coord.row() > 0 &&
                coord.row() <= rows &&
                coord.column() > 0 &&
                coord.column() <= columns;
    }

    public int capacity() {
        return rows * columns;
    }
}
