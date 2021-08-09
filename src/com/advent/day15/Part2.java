package com.advent.day15;

import java.util.HashMap;
import java.util.Map;

/**
 * --- Day 15: Rambunctious Recitation ---
 * --- Part Two ---
 * Impressed, the Elves issue you a challenge:
 * determine the 30000000th number spoken. For example, given the same starting numbers as above:
 *
 * Given 0,3,6, the 30000000th number spoken is 175594.
 * Given 1,3,2, the 30000000th number spoken is 2578.
 * Given 2,1,3, the 30000000th number spoken is 3544142.
 * Given 1,2,3, the 30000000th number spoken is 261214.
 * Given 2,3,1, the 30000000th number spoken is 6895259.
 * Given 3,2,1, the 30000000th number spoken is 18.
 * Given 3,1,2, the 30000000th number spoken is 362.
 * Given your starting numbers, what will be the 30000000th number spoken?
 *
 * Your puzzle answer was 63644.
 */

public class Part2 {

    public static void main(String[] args) {

        Map<Long, Long> map = new HashMap<>();
        // zadani
        map.put(1L, 1L);
        map.put(20L, 2L);
        map.put(8L, 3L);
        map.put(12L, 4L);
        map.put(0L, 5L);
        map.put(14L, 6L);

        long actualTurn = 7L;
        long actualNumber = 0L;
        // konec zadani

        // priklad
//        map.put(0L, 1L);
//        map.put(3L, 2L);
//        map.put(6L, 3L);
//
//        long actualTurn = 4L;
//        long actualNumber = 0L;
        // konec prikladu

        long nextNumber;
        boolean isFirst;
        do {
            isFirst = !map.containsKey(actualNumber);
            if (!isFirst) {
                System.out.println(actualTurn + " uz byl: " + actualNumber);
                long x = map.get(actualNumber);
                nextNumber = actualTurn - x;
                map.replace(actualNumber, actualTurn);
            } else {
                System.out.println(actualTurn + " prvni:  " + actualNumber);
                nextNumber = 0L;
                map.put(actualNumber, actualTurn);
            }
            actualNumber = nextNumber;
            actualTurn++;
        } while (actualTurn <= 30000000L);
//175594
    }
}
