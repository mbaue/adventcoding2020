package com.advent.day17;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * https://adventofcode.com/2020/day/17
 *
 * --- Day 17: Conway Cubes ---
 * As your flight slowly drifts through the sky, the Elves at the Mythical Information Bureau
 * at the North Pole contact you. They'd like some help debugging a malfunctioning experimental energy
 * source aboard one of their super-secret imaging satellites.
 *
 * The experimental energy source is based on cutting-edge technology: a set of Conway Cubes contained
 * in a pocket dimension! When you hear it's having problems, you can't help but agree to take a look.
 *
 * The pocket dimension contains an infinite 3-dimensional grid. At every integer 3-dimensional coordinate (x,y,z),
 * there exists a single cube which is either active or inactive.
 *
 * In the initial state of the pocket dimension, almost all cubes start inactive. The only exception to this is
 * a small flat region of cubes (your puzzle input); the cubes in this region start in the specified
 * active (#) or inactive (.) state.
 *
 * The energy source then proceeds to boot up by executing six cycles.
 *
 * Each cube only ever considers its neighbors: any of the 26 other cubes where any of their coordinates
 * differ by at most 1. For example, given the cube at x=1,y=2,z=3, its neighbors include the cube at x=2,y=2,z=2,
 * the cube at x=0,y=2,z=3, and so on.
 *
 * During a cycle, all cubes simultaneously change their state according to the following rules:
 *
 * If a cube is active and exactly 2 or 3 of its neighbors are also active, the cube remains active.
 * Otherwise, the cube becomes inactive.
 * If a cube is inactive but exactly 3 of its neighbors are active, the cube becomes active.
 * Otherwise, the cube remains inactive.
 * The engineers responsible for this experimental energy source would like you to simulate the pocket
 * dimension and determine what the configuration of cubes should be at the end of the six-cycle boot process.
 *
 * For example, consider the following initial state:
 *
 * .#.
 * ..#
 * ###
 * Even though the pocket dimension is 3-dimensional, this initial state represents a small 2-dimensional slice of it.
 * (In particular, this initial state defines a 3x3x1 region of the 3-dimensional space.)
 *
 * Simulating a few cycles from this initial state produces the following configurations,
 * where the result of each cycle is shown layer-by-layer at each given z coordinate
 * (and the frame of view follows the active cells in each cycle):
 *
 * Before any cycles:
 *
 * z=0
 * .#.
 * ..#
 * ###
 *
 *
 * After 1 cycle:
 *
 * z=-1
 * #..
 * ..#
 * .#.
 *
 * z=0
 * #.#
 * .##
 * .#.
 *
 * z=1
 * #..
 * ..#
 * .#.
 *
 *
 * After 2 cycles:
 *
 * z=-2
 * .....
 * .....
 * ..#..
 * .....
 * .....
 *
 * z=-1
 * ..#..
 * .#..#
 * ....#
 * .#...
 * .....
 *
 * z=0
 * ##...
 * ##...
 * #....
 * ....#
 * .###.
 *
 * z=1
 * ..#..
 * .#..#
 * ....#
 * .#...
 * .....
 *
 * z=2
 * .....
 * .....
 * ..#..
 * .....
 * .....
 *
 *
 * After 3 cycles:
 *
 * z=-2
 * .......
 * .......
 * ..##...
 * ..###..
 * .......
 * .......
 * .......
 *
 * z=-1
 * ..#....
 * ...#...
 * #......
 * .....##
 * .#...#.
 * ..#.#..
 * ...#...
 *
 * z=0
 * ...#...
 * .......
 * #......
 * .......
 * .....##
 * .##.#..
 * ...#...
 *
 * z=1
 * ..#....
 * ...#...
 * #......
 * .....##
 * .#...#.
 * ..#.#..
 * ...#...
 *
 * z=2
 * .......
 * .......
 * ..##...
 * ..###..
 * .......
 * .......
 * .......
 * After the full six-cycle boot process completes, 112 cubes are left in the active state.
 *
 * Starting with your given initial configuration, simulate six cycles.
 * How many cubes are left in the active state after the sixth cycle?
 *
 * Your puzzle answer was 213.
 */

public class Day17 {

    public static void main(String[] args) {
        try {
            File file = new File("resources/adv17.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            String[] inputLines = new String[8];
            int dimZ = 1; // slice
            int dimY = 0; // line
            int dimX = 0; // chars in line

            while ((line = br.readLine()) != null) {
                dimY++;
                dimX = line.length();
                inputLines[dimY - 1] = line;
            }

            System.out.println("--------vstupni kostka-------------------");
            Cube cube = new Cube(dimX, dimY, dimZ);
            cube.fillInitial(inputLines);
            cube.printStatus();
            Cube newCube = new Cube(0,0,0);
            Cube prevCube = cube;

            for (int i = 1; i <= 6; i++) {
                System.out.println("----- " + i + ". kolo ------");
                prevCube = prevCube.createOneLevelBigger();
                //System.out.println(" ------ prev cube ----");
                //prevCube.printStatus();
                newCube = new Cube(prevCube.getDimX(), prevCube.getDimY(), prevCube.getDimZ());
                newCube.countValuesFromPrevious(prevCube);
                //System.out.println("------- new cube ----");
                //newCube.printStatus();
                System.out.println("pocet aktivnich po " + i + "-tem kole = " + newCube.countActive());
                prevCube = newCube;

            }

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("solution = " + newCube.countActive());
            System.out.println("----------------------------------------------------------------");

            br.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
