package com.advent.day14;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * https://adventofcode.com/2020/day/14
 *
 * --- Day 14: Docking Data ---
 * As your ferry approaches the sea port, the captain asks for your help again. The computer system
 * that runs this port isn't compatible with the docking program on the ferry, so the docking parameters
 * aren't being correctly initialized in the docking program's memory.
 *
 * After a brief inspection, you discover that the sea port's computer system uses a strange bitmask system
 * in its initialization program. Although you don't have the correct decoder chip handy, you can emulate it in software!
 *
 * The initialization program (your puzzle input) can either update the bitmask or write a value to memory.
 * Values and memory addresses are both 36-bit unsigned integers. For example,
 * ignoring bitmasks for a moment, a line like mem[8] = 11 would write the value 11 to memory address 8.
 *
 * The bitmask is always given as a string of 36 bits, written with the most significant bit (representing 2^35)
 * on the left and the least significant bit (2^0, that is, the 1s bit) on the right.
 * The current bitmask is applied to values immediately before they are written to memory:
 * a 0 or 1 overwrites the corresponding bit in the value, while an X leaves the bit in the value unchanged.
 *
 * For example, consider the following program:
 *
 * mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
 * mem[8] = 11
 * mem[7] = 101
 * mem[8] = 0
 * This program starts by specifying a bitmask (mask = ....). The mask it specifies will
 * overwrite two bits in every written value: the 2s bit is overwritten with 0, and the 64s bit is overwritten with 1.
 *
 * The program then attempts to write the value 11 to memory address 8.
 * By expanding everything out to individual bits, the mask is applied as follows:
 *
 * value:  000000000000000000000000000000001011  (decimal 11)
 * mask:   XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
 * result: 000000000000000000000000000001001001  (decimal 73)
 * So, because of the mask, the value 73 is written to memory address 8 instead.
 * Then, the program tries to write 101 to address 7:
 *
 * value:  000000000000000000000000000001100101  (decimal 101)
 * mask:   XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
 * result: 000000000000000000000000000001100101  (decimal 101)
 * This time, the mask has no effect, as the bits it overwrote were already the values the mask tried to set.
 * Finally, the program tries to write 0 to address 8:
 *
 * value:  000000000000000000000000000000000000  (decimal 0)
 * mask:   XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
 * result: 000000000000000000000000000001000000  (decimal 64)
 * 64 is written to address 8 instead, overwriting the value that was there previously.
 *
 * To initialize your ferry's docking program, you need the sum of all values left in memory
 * after the initialization program completes. (The entire 36-bit address space begins initialized
 * to the value 0 at every address.) In the above example, only two values in memory
 * are not zero - 101 (at address 7) and 64 (at address 8) - producing a sum of 165.
 *
 * Execute the initialization program. What is the sum of all values left in memory after it completes?
 * (Do not truncate the sum to 36 bits.)
 *
 * Your puzzle answer was 9967721333886.
 */

public class Day14 {

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv14.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            String mask = "";
            int address;
            String numberDecimal;
            String numberBinary;
            String maskedNumber;
            long decimalNumber;

            Map<Integer, Long> memory = new HashMap<>();
            long solution = 0L;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("mem")) {
                    address = Integer.parseInt(line.substring(line.indexOf("[") + 1, line.indexOf("]")));
                    numberDecimal = line.substring(line.indexOf(" = ") + 3);
                    numberBinary = convertToBinary(numberDecimal);
                    maskedNumber = maskedNumber(numberBinary, mask);
                    System.out.println("-------------------------------------------------------");
                    System.out.println("adresa  " + address);
                    System.out.println("maska   " + mask);
                    System.out.println("cislo   " + numberBinary);

                    decimalNumber = convertToDecimalNumber(maskedNumber);
                    System.out.println("vysled  " + maskedNumber + ", decimal " + decimalNumber) ;
                    if (memory.putIfAbsent(address, decimalNumber) != null) {
                        memory.replace(address, decimalNumber);
                    }
                } else {
                    mask = line.substring(7);
                }
            }
            for (Map.Entry<Integer, Long> entry : memory.entrySet()) {
                solution += entry.getValue();
            }

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println(solution);
            System.out.println("----------------------------------------------------------------");

            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static String convertToBinary(String numberDecimal) {
        StringBuilder sb = new StringBuilder();
        int numDec = Integer.parseInt(numberDecimal);
        int digit;
        do {
            digit = numDec % 2;
            sb.append(digit);
            numDec /= 2;
        } while (numDec > 0);
        while (sb.length() < 36) {
            sb.append('0');
        }
        sb.reverse();
        return sb.toString();
    }

    public static String maskedNumber(String number, String mask) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mask.length(); i++) {
            if (mask.charAt(i) != 'X') {
                sb.append(mask.charAt(i));
            } else {
                sb.append(number.charAt(i));
            }
        }
        return sb.toString();
    }

    public static long convertToDecimalNumber(String binaryNumber) {
        long x = 0L;
        for (int i = 0; i < binaryNumber.length(); i++) {
            if (binaryNumber.charAt(binaryNumber.length() - i - 1) == '1') {
                x += Math.pow(2, i);
            }
        }
        return x;
    }

}
