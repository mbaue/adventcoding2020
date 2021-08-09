package com.advent.day12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * --- Day 12: Rain Risk ---
 * --- Part Two ---
 * Before you can give the destination to the captain, you realize
 * that the actual action meanings were printed on the back of the instructions the whole time.
 *
 * Almost all of the actions indicate how to move a waypoint which is relative to the ship's position:
 *
 * Action N means to move the waypoint north by the given value.
 * Action S means to move the waypoint south by the given value.
 * Action E means to move the waypoint east by the given value.
 * Action W means to move the waypoint west by the given value.
 * Action L means to rotate the waypoint around the ship left (counter-clockwise) the given number of degrees.
 * Action R means to rotate the waypoint around the ship right (clockwise) the given number of degrees.
 * Action F means to move forward to the waypoint a number of times equal to the given value.
 * The waypoint starts 10 units east and 1 unit north relative to the ship.
 * The waypoint is relative to the ship; that is, if the ship moves, the waypoint moves with it.
 *
 * For example, using the same instructions as above:
 *
 * F10 moves the ship to the waypoint 10 times (a total of 100 units east and 10 units north),
 * leaving the ship at east 100, north 10. The waypoint stays 10 units east and 1 unit north of the ship.
 * N3 moves the waypoint 3 units north to 10 units east and 4 units north of the ship.
 * The ship remains at east 100, north 10.
 * F7 moves the ship to the waypoint 7 times (a total of 70 units east and 28 units north),
 * leaving the ship at east 170, north 38. The waypoint stays 10 units east and 4 units north of the ship.
 * R90 rotates the waypoint around the ship clockwise 90 degrees,
 * moving it to 4 units east and 10 units south of the ship. The ship remains at east 170, north 38.
 * F11 moves the ship to the waypoint 11 times (a total of 44 units east and 110 units south),
 * leaving the ship at east 214, south 72. The waypoint stays 4 units east and 10 units south of the ship.
 * After these operations, the ship's Manhattan distance from its starting position is 214 + 72 = 286.
 *
 * Figure out where the navigation instructions actually lead.
 * What is the Manhattan distance between that location and the ship's starting position?
 *
 * Your puzzle answer was 18107.
 *
 * hint to solution:
 * https://cs.wikipedia.org/wiki/Oto%C4%8Den%C3%AD
 */

public class Part2 {

    public static void main(String[] args) {
        try {
            File file = new File("resources/adv12.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            int[] shipPosition = {0, 0};
            int[] wayPoint = {10, 1}; // {N-S, E-W}
            System.out.println("ship [" + shipPosition[0] + ", " + shipPosition[1] + "], waypoint [" + wayPoint[0] + ", " + wayPoint[1] + "]");

            while ((line = br.readLine()) != null) {

                System.out.print(line);
                char c = line.charAt(0);
                switch (c) {
                    case 'N':
                        wayPoint[1] += Integer.parseInt(line.substring(1));
                        break;
                    case 'E':
                        wayPoint[0] += Integer.parseInt(line.substring(1));
                        break;
                    case 'S':
                        wayPoint[1] -= Integer.parseInt(line.substring(1));
                        break;
                    case 'W':
                        wayPoint[0] -= Integer.parseInt(line.substring(1));
                        break;
                    case 'L':
                        wayPoint = rotateWayPointLeft(wayPoint, Integer.parseInt(line.substring(1)));
                        break;
                    case 'R':
                        wayPoint = rotateWayPointRight(wayPoint, Integer.parseInt(line.substring(1)));
                        break;
                    case 'F':
                        shipPosition[0] += Integer.parseInt(line.substring(1)) * wayPoint[0];
                        shipPosition[1] += Integer.parseInt(line.substring(1)) * wayPoint[1];
                }
                System.out.println("   ship [" + shipPosition[0] + ", " + shipPosition[1] + "], waypoint [" + wayPoint[0] + ", " + wayPoint[1] + "]");
            }

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println(Math.abs(shipPosition[0]) + Math.abs(shipPosition[1]));
            System.out.println("----------------------------------------------------------------");

            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static int[] rotateWayPointLeft(int[] wp, int degrees) {
        int[] coord = new int[2];
        coord[0] = (int) Math.round(wp[0] * Math.cos(angleInRad(degrees)) - wp[1] * Math.sin(angleInRad(degrees)));
        coord[1] = (int) Math.round(wp[0] * Math.sin(angleInRad(degrees)) + wp[1] * Math.cos(angleInRad(degrees)));
        return coord;
    }

    public static int[] rotateWayPointRight(int[] wp, int degrees) {
        return rotateWayPointLeft(wp, 360 - degrees);
    }

    public static double angleInRad(int angleInDeg) {
        return (2 * Math.PI * angleInDeg / 360);
    }
}

