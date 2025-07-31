package org.example.entities;

import org.example.coordinates.Coordinate;
import org.example.pathfinders.AStarPathfinder;
import org.example.worlds.World;

import java.util.List;

public abstract class Creature extends Entity{
    private final static int DEFAULT_SPEED = 1;
    private final static int DEFAULT_HP = 10;
    private final static int DEFAULT_HUNGER_POINTS = 10;

    private final List<Class<? extends Entity>> targets;
    private final AStarPathfinder pathfinder;

    private Coordinate coordinate;
    private int hp;
    private int hungerPoints;

    public Creature(Coordinate coordinate, List<Class<? extends Entity>> targets) {
        this.coordinate = coordinate;
        this.hp = DEFAULT_HP;
        this.hungerPoints = DEFAULT_HUNGER_POINTS;
        this.targets = targets;
        this.pathfinder = new AStarPathfinder();
    }

    public final void makeMove(World world){
        if (isDead()){
            return;
        }

        List<Coordinate> path = pathfinder.findPath(
                getCoordinate(),
                getDefaultSpeed(),
                getTargets(),
                world
        );

        Coordinate nextCoord;

        if(path.size() > 1) {
           nextCoord = path.get(1);
        }else{
            nextCoord = getCoordinate();
        }

        if (!nextCoord.equals(getCoordinate())) {
            if (world.isEmptyKey(nextCoord)) {
                world.update(nextCoord, this);
                setCoordinate(nextCoord);
            }
            foodIteration(world, nextCoord);
        }

        setHungerPoints(getHungerPoints() - 1);
        if (isHungry()){
            takeDamage(1);
        }
        if (isDead()) {
            world.remove(getCoordinate());
        }
    }

    protected abstract void foodIteration(World world, Coordinate coordinate);

    public final void takeDamage(int damage){
        hp-=damage;
    }

    public final void resetHungryPoints(){
        hungerPoints = DEFAULT_HUNGER_POINTS;
    }

    public final void setHungerPoints(int hungerPoints) {
        this.hungerPoints = hungerPoints;
    }

    public final void resetHP(){
        hp = DEFAULT_HP;
    }

    public final boolean isHungry(){
        return hungerPoints <= 0;
    }

    public final boolean isDead(){
        return hp <= 0;
    }

    public final int getDefaultSpeed(){
        return DEFAULT_SPEED;
    }

    public final int getHungerPoints(){
        return hungerPoints;
    }

    public final Coordinate getCoordinate() {
        return coordinate;
    }

    public final List<Class<? extends Entity>> getTargets(){
        return targets;
    }

    public final void setCoordinate(Coordinate coordinate){
        this.coordinate = coordinate;
    }
}