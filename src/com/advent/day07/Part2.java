package com.advent.day07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * --- Day 7: Handy Haversacks ---
 * --- Part Two ---
 * It's getting pretty expensive to fly these days - not because of ticket prices,
 * but because of the ridiculous number of bags you need to buy!
 *
 * Consider again your shiny gold bag and the rules from the above example:
 *
 * faded blue bags contain 0 other bags.
 * dotted black bags contain 0 other bags.
 * vibrant plum bags contain 11 other bags: 5 faded blue bags and 6 dotted black bags.
 * dark olive bags contain 7 other bags: 3 faded blue bags and 4 dotted black bags.
 * So, a single shiny gold bag must contain 1 dark olive bag (and the 7 bags within it)
 * plus 2 vibrant plum bags (and the 11 bags within each of those): 1 + 1*7 + 2 + 2*11 = 32 bags!
 *
 * Of course, the actual rules have a small chance of going several levels deeper than this example;
 * be sure to count all of the bags, even if the nesting becomes topologically impractical!
 *
 * Here's another example:
 *
 * shiny gold bags contain 2 dark red bags.
 * dark red bags contain 2 dark orange bags.
 * dark orange bags contain 2 dark yellow bags.
 * dark yellow bags contain 2 dark green bags.
 * dark green bags contain 2 dark blue bags.
 * dark blue bags contain 2 dark violet bags.
 * dark violet bags contain no other bags.
 * In this example, a single shiny gold bag must contain 126 other bags.
 *
 * How many individual bags are required inside your single shiny gold bag?
 *
 * Your puzzle answer was 13264.
 */

public class Part2 {

    private static HashMap<String, List<String>> colors = new HashMap<>();
    private static int numberOfInnerBags = 0;
    private static int numberOfRecursion = 0;

    public static void main(String[] args) {
        try {
            File file = new File("resources/adv07.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            // rozparsovani vstupu do hashmap
            while ((line = br.readLine()) != null) {
                String outerBag = line.split(" contain ")[0];
                String[] innerBags = line.split(" contain ")[1].split(",");
                for (int j = 0; j < innerBags.length; j++) {
                    innerBags[j] = innerBags[j]
                            .replace(".", "")
                            .replaceAll("bag[s]?", "")
                            .replace("no other", "")
                            .trim();
                }
                colors.put(outerBag.replace("bags", "").trim(), Arrays.stream(innerBags).toList());
            }

            String mostOuterBag = "shiny gold";
            processInner(1, mostOuterBag);

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("number of bags = " + numberOfInnerBags);
            System.out.println("----------------------------------------------------------------");

            br.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void processInner(int numOuter, String bag) {
        numberOfRecursion++;
        System.out.println(numberOfRecursion + " ----------------------------------");

        System.out.println("in " + numOuter + " " + bag + " can be " + numOuter + "x " + colors.get(bag));
        for (String str : colors.get(bag)) {
            System.out.println("checking " + str);
            if (!str.equals("")) {
                Pattern cislo = Pattern.compile("(\\d+)");
                Matcher matcher = cislo.matcher(str);
                if (matcher.find()) {
                    String x = matcher.group();
                    int numInner = Integer.parseInt(x) * numOuter;
                    System.out.println(" adding " + numInner);
                    numberOfInnerBags += numInner;
                    System.out.println(" total bags for now = " + numberOfInnerBags);
                    processInner(numInner, str.substring(str.indexOf(' ') + 1));
                }
            } else {
                System.out.println("       leaving empty bag");
                numberOfRecursion--;
            }
        }

    }
}
