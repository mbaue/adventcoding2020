package com.advent.day03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * --- Day 3: Toboggan Trajectory ---
 * --- Part Two ---
 * Time to check the rest of the slopes - you need to minimize
 * the probability of a sudden arboreal stop, after all.
 * <p>
 * Determine the number of trees you would encounter if, for each of the following slopes,
 * you start at the top-left corner and traverse the map all the way to the bottom:
 * <p>
 * Right 1, down 1.
 * Right 3, down 1. (This is the slope you already checked.)
 * Right 5, down 1.
 * Right 7, down 1.
 * Right 1, down 2.
 * In the above example, these slopes would find 2, 7, 3, 4, and 2 tree(s) respectively;
 * multiplied together, these produce the answer 336.
 * <p>
 * What do you get if you multiply together the number of trees encountered on each of the listed slopes?
 * <p>
 * Your puzzle answer was 3154761400.
 */

public class Part2 {

    public static void main(String[] args) {

        try {
            int down = 2;
            int right = 1;
            int patternLength = 31;

            File file = new File("resources/adv03.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            int lineNum = 0;
            int position;
            int treeCount = 0;
            while ((line = br.readLine()) != null) {
                lineNum++;
                position = (lineNum - 1) * right;
                System.out.println(lineNum + "  ----  " + position + " mod 31=" + (position % patternLength) + "   ----------   " + line + "  found: " + line.charAt(position % patternLength));
                if (line.charAt(position % patternLength) == '#') {
                    treeCount++;
                }
                for (int i = 1; i < down; i++) {
                    br.readLine();
                }
            }
            System.out.println("right=" + right + ", down=" + down + ", pattern length=" + patternLength);
            System.out.println("pocet stromu=" + treeCount);

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println();
            System.out.println("----------------------------------------------------------------");

            br.close();
            //TODO vytvorit metodu a volat ji pro ruzna zadani

            //System.out.println((long) (61L*265L*82L*70L*34L));


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
