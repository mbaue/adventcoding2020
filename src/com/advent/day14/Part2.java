package com.advent.day14;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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