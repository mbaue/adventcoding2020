package com.advent.day12;

/*
test of enum class CardinalDirections
 */

import java.util.Arrays;

public class DirTest {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(CardinalDirections.values()));

        CardinalDirections actualDirection = CardinalDirections.EAST;
        System.out.println(actualDirection);

        actualDirection = actualDirection.turnRight(270);
        System.out.println(actualDirection);

        actualDirection = actualDirection.turnLeft(270);
        System.out.println(actualDirection);


    }
}
