package org.example.simulations;

public interface Simulation {
    void nextTurn();
    void start();
    void pause();
    void resume();
    void requestSingleStep();
    void stop();
    boolean isPaused();
}
