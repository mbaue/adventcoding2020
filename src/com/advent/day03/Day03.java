package com.advent.day03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * https://adventofcode.com/2020/day/3
 * <p>
 * --- Day 3: Toboggan Trajectory ---
 * With the toboggan login problems resolved, you set off toward the airport.
 * While travel by toboggan might be easy, it's certainly not safe:
 * there's very minimal steering and the area is covered in trees.
 * You'll need to see which angles will take you near the fewest trees.
 * <p>
 * Due to the local geology, trees in this area only grow on exact integer coordinates in a grid.
 * You make a map (your puzzle input) of the open squares (.) and trees (#) you can see. For example:
 * <p>
 * ..##.......
 * #...#...#..
 * .#....#..#.
 * ..#.#...#.#
 * .#...##..#.
 * ..#.##.....
 * .#.#.#....#
 * .#........#
 * #.##...#...
 * #...##....#
 * .#..#...#.#
 * These aren't the only trees, though;
 * due to something you read about once involving arboreal genetics and biome stability,
 * the same pattern repeats to the right many times:
 * <p>
 * ..##.........##.........##.........##.........##.........##.......  --->
 * #...#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..
 * .#....#..#..#....#..#..#....#..#..#....#..#..#....#..#..#....#..#.
 * ..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#
 * .#...##..#..#...##..#..#...##..#..#...##..#..#...##..#..#...##..#.
 * ..#.##.......#.##.......#.##.......#.##.......#.##.......#.##.....  --->
 * .#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#
 * .#........#.#........#.#........#.#........#.#........#.#........#
 * #.##...#...#.##...#...#.##...#...#.##...#...#.##...#...#.##...#...
 * #...##....##...##....##...##....##...##....##...##....##...##....#
 * .#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#  --->
 * You start on the open square (.) in the top-left corner
 * and need to reach the bottom (below the bottom-most row on your map).
 * <p>
 * The toboggan can only follow a few specific slopes (you opted for a cheaper model
 * that prefers rational numbers); start by counting all the trees you would encounter for the slope right 3, down 1:
 * <p>
 * From your starting position at the top-left, check the position that is right 3 and down 1.
 * Then, check the position that is right 3 and down 1 from there, and so on until you go past the bottom of the map.
 * <p>
 * The locations you'd check in the above example are marked here with O where there was an open square and X where there was a tree:
 * <p>
 * ..##.........##.........##.........##.........##.........##.......  --->
 * #..O#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..
 * .#....X..#..#....#..#..#....#..#..#....#..#..#....#..#..#....#..#.
 * ..#.#...#O#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#
 * .#...##..#..X...##..#..#...##..#..#...##..#..#...##..#..#...##..#.
 * ..#.##.......#.X#.......#.##.......#.##.......#.##.......#.##.....  --->
 * .#.#.#....#.#.#.#.O..#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#
 * .#........#.#........X.#........#.#........#.#........#.#........#
 * #.##...#...#.##...#...#.X#...#...#.##...#...#.##...#...#.##...#...
 * #...##....##...##....##...#X....##...##....##...##....##...##....#
 * .#..#...#.#.#..#...#.#.#..#...X.#.#..#...#.#.#..#...#.#.#..#...#.#  --->
 * In this example, traversing the map using this slope would cause you to encounter 7 trees.
 * <p>
 * Starting at the top-left corner of your map and following a slope of right 3 and down 1,
 * how many trees would you encounter?
 * <p>
 * Your puzzle answer was 265.
 */

public class Day03 {

    public static void main(String[] args) {
        try {
            File file = new File("resources/adv03.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            int lineNum = 0;
            int position;
            int treeCount = 0;
            while ((line = br.readLine()) != null) {
                lineNum++;
                position = (lineNum - 1) * 3;
                System.out.println(lineNum + "  ----  " + position + " mod 31=" + (position % 31) + "   ----------   " + line + "  found: " + line.charAt(position % 31));
                if (line.charAt(position % 31) == '#') {
                    treeCount++;
                }
            }

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("pocet stromu = " + treeCount);
            System.out.println("----------------------------------------------------------------");

            br.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
