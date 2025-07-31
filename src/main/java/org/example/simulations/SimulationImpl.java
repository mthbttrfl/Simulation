package org.example.simulations;

import org.example.actions.Action;
import org.example.entities.Creature;
import org.example.renders.Render;
import org.example.worlds.World;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimulationImpl implements Simulation {

    private final World world;
    private final Action initActions;
    private final Action turnActions;
    private final Render render;
    private final InputHandler inputHandler;
    private final Lock stateLock = new ReentrantLock();
    private final Condition pauseCondition = stateLock.newCondition();

    private int counter;
    private boolean running = false;
    private boolean paused = true;
    private boolean singleStepRequested = false;
    private Thread simulationThread;

    public SimulationImpl(World world, Action initActions, Action turnActions, Render render) {
        this.world = world;
        this.initActions = initActions;
        this.turnActions = turnActions;
        this.render = render;
        this.inputHandler = new ConsoleInputHandler(this);

        initializationEntities();
        renderInitialState();
    }

    @Override
    public void nextTurn() {
        counter++;
        turnActions.execute(world);
        renderCurrentState();
    }

    @Override
    public void start() {
        if (running) return;

        running = true;
        paused = false;
        inputHandler.start();

        simulationThread = new Thread(this::runSimulationLoop);
        simulationThread.start();
    }

    private void runSimulationLoop() {
        while (running) {
            try {
                stateLock.lock();

                while (paused && !singleStepRequested) {
                    pauseCondition.await();
                }

                if (singleStepRequested) {
                    singleStepRequested = false;
                    paused = true;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            } finally {
                stateLock.unlock();
            }

            if (world.getAllByType(Creature.class).isEmpty()) {
                stop();
                return;
            }

            nextTurn();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void pause() {
        stateLock.lock();
        try {
            if (!paused) {
                paused = true;
                System.out.println("PAUSED");
            }
        } finally {
            stateLock.unlock();
        }
    }

    @Override
    public void resume() {
        stateLock.lock();
        try {
            if (paused) {
                paused = false;
                pauseCondition.signalAll();
                System.out.println("RESUMED");
            }
        } finally {
            stateLock.unlock();
        }
    }

    @Override
    public void requestSingleStep() {
        stateLock.lock();
        try {
            if (paused) {
                singleStepRequested = true;
                paused = false;
                pauseCondition.signalAll();
                System.out.println("STEP");
            }
        } finally {
            stateLock.unlock();
        }
    }

    @Override
    public void stop() {
        stateLock.lock();
        try {
            running = false;
            paused = false;
            pauseCondition.signalAll();
            inputHandler.stop();
            System.out.println("SIMULATION FINISHED");
        } finally {
            stateLock.unlock();
        }
    }

    @Override
    public boolean isPaused() {
        stateLock.lock();
        try {
            return paused;
        } finally {
            stateLock.unlock();
        }
    }

    private void initializationEntities() {
        initActions.execute(world);
    }

    private void renderInitialState() {
        System.out.println("Initial state:");
        System.out.println(render.rendering(world));
    }

    private void renderCurrentState() {
        System.out.println("\nTurn: " + counter);
        System.out.println("Creature: " + world.getAllByType(Creature.class).size());
        System.out.println(render.rendering(world));
    }
}