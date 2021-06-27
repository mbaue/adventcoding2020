package com.advent.day12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * https://adventofcode.com/2020/day/12
 *
 * --- Day 12: Rain Risk ---
 * Your ferry made decent progress toward the island,
 * but the storm came in faster than anyone expected.
 * The ferry needs to take evasive actions!
 *
 * Unfortunately, the ship's navigation computer seems to be malfunctioning;
 * rather than giving a route directly to safety, it produced extremely circuitous instructions.
 * When the captain uses the PA system to ask if anyone can help, you quickly volunteer.
 *
 * The navigation instructions (your puzzle input) consists of
 * a sequence of single-character actions paired with integer input values.
 * After staring at them for a few minutes, you work out what they probably mean:
 *
 * Action N means to move north by the given value.
 * Action S means to move south by the given value.
 * Action E means to move east by the given value.
 * Action W means to move west by the given value.
 * Action L means to turn left the given number of degrees.
 * Action R means to turn right the given number of degrees.
 * Action F means to move forward by the given value in the direction the ship is currently facing.
 * The ship starts by facing east. Only the L and R actions change the direction the ship is facing.
 * (That is, if the ship is facing east and the next instruction is N10,
 * the ship would move north 10 units, but would still move east if the following action were F.)
 *
 * For example:
 *
 * F10
 * N3
 * F7
 * R90
 * F11
 * These instructions would be handled as follows:
 *
 * F10 would move the ship 10 units east (because the ship starts by facing east) to east 10, north 0.
 * N3 would move the ship 3 units north to east 10, north 3.
 * F7 would move the ship another 7 units east (because the ship is still facing east) to east 17, north 3.
 * R90 would cause the ship to turn right by 90 degrees and face south; it remains at east 17, north 3.
 * F11 would move the ship 11 units south to east 17, south 8.
 * At the end of these instructions, the ship's Manhattan distance
 * (sum of the absolute values of its east/west position and its north/south position)
 * from its starting position is 17 + 8 = 25.
 *
 * Figure out where the navigation instructions lead. What is the Manhattan distance between
 * that location and the ship's starting position?
 *
 * Your puzzle answer was 879.
 */


public class Day12 {

    public static void main(String[] args) {
        try {
            File file = new File("resources/adv12.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            int north = 0;
            int south = 0;
            int east = 0;
            int west = 0;

            CardinalDirections actualDirection = CardinalDirections.EAST;
            while ((line = br.readLine()) != null) {

                System.out.println(line);
                char c = line.charAt(0);
                if (c == 'F') {
                    c = actualDirection.getCode();
                }
                switch (c) {
                    case 'N':
                        north = north + Integer.parseInt(line.substring(1));
                        System.out.println("incr NORTH by " + line.substring(1));
                        break;
                    case 'E':
                        east = east + Integer.parseInt(line.substring(1));
                        System.out.println("incr EAST by " + line.substring(1));
                        break;
                    case 'S':
                        south = south + Integer.parseInt(line.substring(1));
                        System.out.println("incr SOUTH by " + line.substring(1));
                        break;
                    case 'W':
                        west = west + Integer.parseInt(line.substring(1));
                        System.out.println("incr WEST by " + line.substring(1));
                        break;
                    case 'L':
                        actualDirection = actualDirection.turnLeft(Integer.parseInt(line.substring(1)));
                        System.out.println("changing direction to " + actualDirection);
                        break;
                    case 'R':
                        actualDirection = actualDirection.turnRight(Integer.parseInt(line.substring(1)));
                        System.out.println("changing direction to " + actualDirection);
                        break;
                }
            }
            System.out.println("-------------------------------------------");
            System.out.println("north = " + north);
            System.out.println("south = " + south);
            System.out.println("east = " + east);
            System.out.println("west = " + west);

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println(Math.abs(north-south) + Math.abs(west-east));
            System.out.println("----------------------------------------------------------------");

            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}