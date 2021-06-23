package com.advent.day07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

/**
 * https://adventofcode.com/2020/day/7
 *
 * --- Day 7: Handy Haversacks ---
 * You land at the regional airport in time for your next flight. In fact, it looks like you'll
 * even have time to grab some food: all flights are currently delayed due to issues in luggage processing.
 *
 * Due to recent aviation regulations, many rules (your puzzle input) are being enforced about bags and their contents;
 * bags must be color-coded and must contain specific quantities of other color-coded bags.
 * Apparently, nobody responsible for these regulations considered how long they would take to enforce!
 *
 * For example, consider the following rules:
 *
 * light red bags contain 1 bright white bag, 2 muted yellow bags.
 * dark orange bags contain 3 bright white bags, 4 muted yellow bags.
 * bright white bags contain 1 shiny gold bag.
 * muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
 * shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
 * dark olive bags contain 3 faded blue bags, 4 dotted black bags.
 * vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
 * faded blue bags contain no other bags.
 * dotted black bags contain no other bags.
 * These rules specify the required contents for 9 bag types. In this example,
 * every faded blue bag is empty, every vibrant plum bag contains 11 bags
 * (5 faded blue and 6 dotted black), and so on.
 *
 * You have a shiny gold bag. If you wanted to carry it in at least one other bag,
 * how many different bag colors would be valid for the outermost bag?
 * (In other words: how many colors can, eventually, contain at least one shiny gold bag?)
 *
 * In the above rules, the following options would be available to you:
 *
 * A bright white bag, which can hold your shiny gold bag directly.
 * A muted yellow bag, which can hold your shiny gold bag directly, plus some other bags.
 * A dark orange bag, which can hold bright white and muted yellow bags, either of which could then hold your shiny gold bag.
 * A light red bag, which can hold bright white and muted yellow bags, either of which could then hold your shiny gold bag.
 * So, in this example, the number of bag colors that can eventually contain at least one shiny gold bag is 4.
 *
 * How many bag colors can eventually contain at least one shiny gold bag?
 * (The list of rules is quite long; make sure you get all of it.)
 *
 * Your puzzle answer was 222.
 */

public class Day07 {

    public static void main(String[] args) {
        try {
            File file = new File("resources/adv07.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            int itemCount = 0;
            HashMap<String, List<String>> colors = new HashMap<>();

            // rozparsovani vstupu do hashmap
            while ((line = br.readLine()) != null) {
                itemCount++;
                String outerBag = line.split(" contain ")[0];
                String[] innerBags = line.split(" contain ")[1].split(",");
                for (int j = 0; j < innerBags.length; j++) {
                    innerBags[j] = innerBags[j]
                            .replace(".", "")
                            .replaceAll("bag[s]?", "")
                            .replaceAll("\\d+", "")
                            .trim();
                }
                colors.put(outerBag.replace("bags", "").trim(), Arrays.stream(innerBags).toList());
            }
            System.out.println("zaznamu " + itemCount + ", barev " + colors.size());
            System.out.println(colors);

            Set<String> bagColorsAllowed = new HashSet<>();
            Set<String> searchBags = new HashSet<>();
            searchBags.add("shiny gold");

            int round = 0;
            Set<String> bagsFromRound = new HashSet<>();
            do {
                round++;
                System.out.println(round + ". kolo --------------------------------------------------------------------");
                bagsFromRound.clear();
                for (String item : searchBags) {
                    System.out.println("   searching for " + item );
                    for (HashMap.Entry<String, List<String>> entry : colors.entrySet()) {
                        if (entry.getValue().contains(item)) {
                            System.out.println("      found in " + entry.getKey());
                            if (!bagColorsAllowed.contains(entry.getKey())) {
                                bagsFromRound.add(entry.getKey());
                                System.out.println("         " + entry.getKey() + " added"         );
                            } else {
                                System.out.println("         " + entry.getKey() + " already in set");
                            }
                        }
                    }
                }
                searchBags.clear();
                searchBags.addAll(bagsFromRound);
                bagColorsAllowed.addAll(bagsFromRound);
                System.out.println("found from this round " + bagsFromRound.size());
                System.out.println("total " + bagColorsAllowed.size());
            } while (bagsFromRound.size() > 0);

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("number of bags = " + bagColorsAllowed.size());
            System.out.println("----------------------------------------------------------------");

            br.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
