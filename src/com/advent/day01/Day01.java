package com.advent.day01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * https://adventofcode.com/2020/day/1
 * <p>
 * --- Day 1: Report Repair ---
 * After saving Christmas five years in a row, you've decided
 * to take a vacation at a nice resort on a tropical island.
 * Surely, Christmas will go on without you.
 * The tropical island has its own currency and is entirely cash-only.
 * The gold coins used there have a little picture of a starfish;
 * the locals just call them stars.
 * None of the currency exchanges seem to have heard of them, but somehow,
 * you'll need to find fifty of these coins by the time
 * you arrive so you can pay the deposit on your room.
 * <p>
 * To save your vacation, you need to get all fifty stars by December 25th.
 * <p>
 * Collect stars by solving puzzles. Two puzzles will be made
 * available on each day in the Advent calendar;
 * the second puzzle is unlocked when you complete the first.
 * Each puzzle grants one star. Good luck!
 * <p>
 * Before you leave, the Elves in accounting just need you
 * to fix your expense report (your puzzle input);
 * apparently, something isn't quite adding up.
 * <p>
 * Specifically, they need you to find the two entries that sum to 2020
 * and then multiply those two numbers together.
 * <p>
 * For example, suppose your expense report contained the following:
 * <p>
 * 1721
 * 979
 * 366
 * 299
 * 675
 * 1456
 * In this list, the two entries that sum to 2020 are 1721 and 299.
 * Multiplying them together produces 1721 * 299 = 514579, so the correct answer is 514579.
 * <p>
 * Of course, your expense report is much larger. Find the two entries that sum to 2020; what do you get if you multiply them together?
 * <p>
 * Your puzzle answer was 100419.
 */

public class Day01 {
    public static final int TARGET_VALUE = 2020;

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv01.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            int solution = 0;

            int[] list = new int[200];
            int i = 0;
            while ((line = br.readLine()) != null) {
                list[i] = Integer.parseInt(line);
                i++;
            }

            int lowerIndex = 0;
            int upperIndex = list.length - 1;
            Arrays.sort(list);

            do {
                if (list[lowerIndex] + list[upperIndex] > TARGET_VALUE) {
                    upperIndex--;
                } else if (list[lowerIndex] + list[upperIndex] == TARGET_VALUE) {
                    System.out.println(list[lowerIndex] + "  * " + list[upperIndex] + " = " + (list[lowerIndex] * list[upperIndex]));
                    solution = list[lowerIndex] * list[upperIndex];
                    upperIndex--;
                    lowerIndex++;
                } else {
                    lowerIndex++;
                }
            }
            while (lowerIndex < upperIndex);

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("two numbers multiplied give = " + solution);
            System.out.println("----------------------------------------------------------------");

            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
