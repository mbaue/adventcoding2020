package com.advent.day05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * --- Day 5: Binary Boarding ---
 * --- Part Two ---
 * Ding! The "fasten seat belt" signs have turned on. Time to find your seat.
 *
 * It's a completely full flight, so your seat should be the only
 * missing boarding pass in your list. However, there's a catch: some of the seats
 * at the very front and back of the plane don't exist on this aircraft,
 * so they'll be missing from your list as well.
 *
 * Your seat wasn't at the very front or back, though;
 * the seats with IDs +1 and -1 from yours will be in your list.
 *
 * What is the ID of your seat?
 *
 * Your puzzle answer was 532.
 */

public class Part2 {

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv05.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            int solution = 0;

            int[] idList = new int[883];
            int highestID = 0;
            int index = 0;
            while ((line = br.readLine()) != null) {
                int seatID = 0;
                char[] num = line.toCharArray();
                for (int i = 0; i < num.length; i++) {
                    seatID = seatID * 2;
                    if (num[i] == 'B' || num[i] == 'R') {
                        seatID = seatID + 1;
                    }
                }
                if (seatID > highestID) {
                    highestID = seatID;
                }
                idList[index] = seatID;
                index++;
            }

            Arrays.sort(idList);
            for (int j = 0; j < idList.length - 1; j++) {
                if (Math.abs(idList[j] - idList[j+1]) > 1) {
                    System.out.println(idList[j] +", "+idList[j+1]);
                    if (Math.abs(idList[j] - idList[j+1]) == 2) {
                        solution = idList[j] + 1;
                    }
                }
            }

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("number of your seat = " + solution);
            System.out.println("----------------------------------------------------------------");

            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
