package com.advent.day14;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * --- Day 14: Docking Data ---
 * --- Part Two ---
 * For some reason, the sea port's computer system still can't communicate
 * with your ferry's docking program. It must be using version 2 of the decoder chip!
 *
 * A version 2 decoder chip doesn't modify the values being written at all.
 * Instead, it acts as a memory address decoder. Immediately before a value is
 * written to memory, each bit in the bitmask modifies the corresponding bit of
 * the destination memory address in the following way:
 *
 * If the bitmask bit is 0, the corresponding memory address bit is unchanged.
 * If the bitmask bit is 1, the corresponding memory address bit is overwritten with 1.
 * If the bitmask bit is X, the corresponding memory address bit is floating.
 * A floating bit is not connected to anything and instead fluctuates unpredictably.
 * In practice, this means the floating bits will take on all possible values,
 * potentially causing many memory addresses to be written all at once!
 *
 * For example, consider the following program:
 *
 * mask = 000000000000000000000000000000X1001X
 * mem[42] = 100
 * mask = 00000000000000000000000000000000X0XX
 * mem[26] = 1
 * When this program goes to write to memory address 42, it first applies the bitmask:
 *
 * address: 000000000000000000000000000000101010  (decimal 42)
 * mask:    000000000000000000000000000000X1001X
 * result:  000000000000000000000000000000X1101X
 * After applying the mask, four bits are overwritten, three of which are different,
 * and two of which are floating. Floating bits take on every possible combination of values;
 * with two floating bits, four actual memory addresses are written:
 *
 * 000000000000000000000000000000011010  (decimal 26)
 * 000000000000000000000000000000011011  (decimal 27)
 * 000000000000000000000000000000111010  (decimal 58)
 * 000000000000000000000000000000111011  (decimal 59)
 * Next, the program is about to write to memory address 26 with a different bitmask:
 *
 * address: 000000000000000000000000000000011010  (decimal 26)
 * mask:    00000000000000000000000000000000X0XX
 * result:  00000000000000000000000000000001X0XX
 * This results in an address with three floating bits, causing writes to eight memory addresses:
 *
 * 000000000000000000000000000000010000  (decimal 16)
 * 000000000000000000000000000000010001  (decimal 17)
 * 000000000000000000000000000000010010  (decimal 18)
 * 000000000000000000000000000000010011  (decimal 19)
 * 000000000000000000000000000000011000  (decimal 24)
 * 000000000000000000000000000000011001  (decimal 25)
 * 000000000000000000000000000000011010  (decimal 26)
 * 000000000000000000000000000000011011  (decimal 27)
 * The entire 36-bit address space still begins initialized to the value 0 at every address, and you still need the sum of all values left in memory at the end of the program. In this example, the sum is 208.
 *
 * Execute the initialization program using an emulator for a version 2 decoder chip. What is the sum of all values left in memory after it completes?
 *
 * Your puzzle answer was 4355897790573.
 */

public class Part2 {

    public static final int ADDR_LENGTH = 36;

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv14.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            String mask = "";
            String address;
            Long value;

            Map<Long, Long> memory = new HashMap<>();
            long solution = 0L;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("mem")) {
                    address = fillWithZeros(Integer.toBinaryString(Integer.parseInt(line.substring(line.indexOf("[") + 1, line.indexOf("]")))), ADDR_LENGTH);
                    value = Long.parseLong(line.substring(line.indexOf(" = ") + 3));
                    ArrayList<Long> addreses = getAddresses(address, mask);
                    for (Long addr : addreses) {
                        if (memory.putIfAbsent(addr, value) != null) {
                            memory.replace(addr, value);
                        }
                    }
                } else {
                    mask = line.substring(7);
                }

            }
            for (Map.Entry<Long, Long> entry : memory.entrySet()) {
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

    public static long convertToDecimalNumber(String binaryNumber) {
        long x = 0L;
        for (int i = 0; i < binaryNumber.length(); i++) {
            if (binaryNumber.charAt(binaryNumber.length() - i - 1) == '1') {
                x += Math.pow(2, i);
            }
        }
        return x;
    }

    public static int countXs(String mask) {
        int count = 0;
        for (int i = 0; i < mask.length(); i++) {
            if (mask.charAt(i) == 'X') {
                count++;
            }
        }
        return count;
    }

    public static ArrayList<Long> getAddresses(String address, String mask) {
        ArrayList<Long> addreses = new ArrayList<>();

        System.out.println("--------------------------------------");
        System.out.println("add  " + address);
        System.out.println("mask " + mask);
        int pocetX = countXs(mask);
        System.out.println("Xs   " + pocetX);
        int combinationsCount = (int) (Math.pow(2, countXs(mask)) - 1);
        System.out.println("combi " + combinationsCount);

        for (int i = 0; i <= combinationsCount; i++) {
            StringBuilder sb = new StringBuilder();
            String strMask = fillWithZeros(Integer.toBinaryString(i), pocetX);
            int positionInMask = 0;
            for (int j = 0; j < mask.length(); j++) {
                if (mask.charAt(j) == '0') {
                    sb.append(address.charAt(j));
                } else if (mask.charAt(j) == '1') {
                    sb.append('1');

                } else if (mask.charAt(j) == 'X') {
                    sb.append(strMask.charAt(positionInMask));
                    positionInMask++;
                }
            }
            addreses.add(convertToDecimalNumber(sb.toString()));
        }
        return addreses;
    }

    public static String fillWithZeros(String binNumber, int binNumberLength) {
        StringBuilder sb = new StringBuilder();
        int zeroes = binNumberLength - binNumber.length();
        sb.append("0".repeat(Math.max(0, zeroes)));
        sb.append(binNumber);
        return sb.toString();
    }
}