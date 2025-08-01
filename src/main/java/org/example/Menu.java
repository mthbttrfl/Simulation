package org.example;

import org.example.factories.simulations.CustomFactorySimulation;
import org.example.factories.simulations.DefaultFactorySimulation;
import org.example.factories.simulations.FactorySimulation;
import org.example.renders.sprites.SpriteRegister;
import org.example.simulations.Simulation;

import java.util.Scanner;

public class Menu {
    private final static String INFO = """
            To pause the simulation press ENTER!
            To make one move in pause mode, press SPACE then ENTER!!!
            """;
    private final static String QUESTION = "Want to change the size of the map? (YES/NO)";
    private final static String ANSWER = "Answer: ";
    private final static String YES = "YES";
    private final static String NO = "NO";
    private final static String COLUMN = "Enter the number of columns: ";
    private final static String ROW = "Enter number of rows: ";
    private final static String INVALID_YES_NO = "Please enter YES or NO!";
    private final static String INVALID_NUMBER = "Invalid input. Please enter a positive integer.";

    private final SpriteRegister sprites;

    private Simulation simulation;
    private FactorySimulation factory;

    public Menu(SpriteRegister sprites) {
        this.sprites = sprites;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(INFO);

        while (true) {
            System.out.println("\n" + QUESTION);
            System.out.print(ANSWER);
            String answer = scanner.nextLine().trim();

            if (answer.equalsIgnoreCase(YES)) {
                int rows = getValidDimension(scanner, ROW);
                int columns = getValidDimension(scanner, COLUMN);
                factory = new CustomFactorySimulation(rows, columns, sprites);
                simulation = factory.get();
                simulation.start();
                break;
            } else if (answer.equalsIgnoreCase(NO)) {
                factory = new DefaultFactorySimulation(sprites);
                simulation = factory.get();
                simulation.start();
                break;
            } else {
                System.out.println(INVALID_YES_NO);
            }
        }
    }

    private int getValidDimension(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                int value = Integer.parseInt(input);

                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Value must be greater than 0");
                }
            } catch (NumberFormatException ex) {
                System.out.println(INVALID_NUMBER);
            }
        }
    }
}