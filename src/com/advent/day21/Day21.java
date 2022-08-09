package com.advent.day21;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * https://adventofcode.com/2020/day/21
 *
 * --- Day 21: Allergen Assessment ---
 * You reach the train's last stop and the closest you can get to your vacation island without getting wet.
 * There aren't even any boats here, but nothing can stop you now: you build a raft. You just need a few days'
 * worth of food for your journey.
 *
 * You don't speak the local language, so you can't read any ingredients lists. However, sometimes, allergens are
 * listed in a language you do understand. You should be able to use this information to determine
 * which ingredient contains which allergen and work out which foods are safe to take with you on your trip.
 *
 * You start by compiling a list of foods (your puzzle input), one food per line. Each line includes that food's
 * ingredients list followed by some or all of the allergens the food contains.
 *
 * Each allergen is found in exactly one ingredient. Each ingredient contains zero or one allergen. Allergens aren't
 * always marked; when they're listed (as in (contains nuts, shellfish) after an ingredients list),
 * the ingredient that contains each listed allergen will be somewhere in the corresponding ingredients list.
 * However, even if an allergen isn't listed, the ingredient that contains that allergen could still be present:
 * maybe they forgot to label it, or maybe it was labeled in a language you don't know.
 *
 * For example, consider the following list of foods:
 *
 * mxmxvkd kfcds sqjhc nhms (contains dairy, fish)
 * trh fvjkl sbzzf mxmxvkd (contains dairy)
 * sqjhc fvjkl (contains soy)
 * sqjhc mxmxvkd sbzzf (contains fish)
 * The first food in the list has four ingredients (written in a language you don't understand):
 * mxmxvkd, kfcds, sqjhc, and nhms. While the food might contain other allergens,
 * a few allergens the food definitely contains are listed afterward: dairy and fish.
 *
 * The first step is to determine which ingredients can't possibly contain any of the allergens
 * in any food in your list. In the above example, none of the ingredients kfcds, nhms, sbzzf,
 * or trh can contain an allergen. Counting the number of times any of these ingredients appear
 * in any ingredients list produces 5: they all appear once each except sbzzf, which appears twice.
 *
 * Determine which ingredients cannot possibly contain any of the allergens in your list.
 * How many times do any of those ingredients appear?
 *
 * Your puzzle answer was 2614.
 */

public class Day21 {

    private static final Set<String> alergens = new HashSet<>();
    private static final Set<Food> foods = new HashSet<>();

    public static void main(String[] args) {
        try {
            File file = new File("resources/adv21.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                int otviraci = line.indexOf('(');
                int zaviraci = line.indexOf(')');
                String[] alerg = line.substring(otviraci + 1, zaviraci).replace("contains ", "").split(", ");
                String[] ingred = line.substring(0, line.indexOf('(') - 1).split(" ");
                foods.add(new Food(ingred, alerg));
                alergens.addAll(Arrays.asList(alerg));
            }

            // for each alergen filter those foods thak may contain it and by intersection get possible ingredient
            Set<String> ingrContainingAlergen = new HashSet<>();
            for (String a : alergens) {
                System.out.println("processing +++ " + a + " +++");
                Set<String> intersection = new HashSet<>();
                boolean init = true;
                List<Food> myListOfFoods = foods.stream().filter(food -> (food.contains(a))).toList();
                for (Food f : myListOfFoods) {
                    Set<String> fIngrediens = f.getIngredients();
                    if (init) {
                        intersection.addAll(fIngrediens);
                        init = false;
                    } else {
                        intersection.retainAll(fIngrediens);
                    }
                }
                System.out.println("possible ingredients: " + intersection);
                System.out.println();
                ingrContainingAlergen.addAll(intersection);
            }

            // remove set of allergic ingredients from each food record and count its content
            int solution = 0;
            for (Food f : foods) {
                Set<String> nonAlergicIngr = f.getIngredients();
                nonAlergicIngr.removeAll(ingrContainingAlergen);
                solution += nonAlergicIngr.size();
            }
            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("solution = " + solution);
            System.out.println("----------------------------------------------------------------");

            br.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
