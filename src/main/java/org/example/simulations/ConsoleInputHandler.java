package org.example.simulations;

import java.util.Scanner;

public class ConsoleInputHandler implements InputHandler {
    private final Simulation simulation;
    private volatile boolean running;
    private Thread inputThread;

    public ConsoleInputHandler(Simulation simulation) {
        this.simulation = simulation;
    }

    @Override
    public void start() {
        running = true;
        inputThread = new Thread(this::handleInput);
        inputThread.setDaemon(true);
        inputThread.start();
    }

    private void handleInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (running) {
                String input = scanner.nextLine();
                processInput(input);
            }
        }
    }

    private void processInput(String input) {
        if (input.isEmpty()) { // Enter
            if (simulation.isPaused()) {
                simulation.resume();
            } else {
                simulation.pause();
            }
        } else if (input.equals(" ")) { // Space
            simulation.requestSingleStep();
        }
    }

    @Override
    public void stop() {
        running = false;
        if (inputThread != null) {
            inputThread.interrupt();
        }
    }
}