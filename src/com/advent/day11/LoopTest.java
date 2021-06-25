package com.advent.day11;

/*
loop with two parameters
 */

public class LoopTest {
    public static void main(String[] args) {
        for (int i = 0,  j = 0; i <15 && j >-5; i++, j--) {
            System.out.println("i=" + i + ", j=" + j);
        }
    }
}
