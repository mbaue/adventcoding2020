package com.advent.day21;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * --- Day 21: Allergen Assessment ---
 * --- Part Two ---
 * Now that you've isolated the inert ingredients, you should have enough information to figure out
 * which ingredient contains which allergen.
 *
 * In the above example:
 *
 * mxmxvkd contains dairy.
 * sqjhc contains fish.
 * fvjkl contains soy.
 * Arrange the ingredients alphabetically by their allergen and separate them by commas to produce your canonical
 * dangerous ingredient list. (There should not be any spaces in your canonical dangerous ingredient list.)
 * In the above example, this would be mxmxvkd,sqjhc,fvjkl.
 *
 * Time to stock your raft with supplies. What is your canonical dangerous ingredient list?
 *
 * Your puzzle answer was qhvz,kbcpn,fzsl,mjzrj,bmj,mksmf,gptv,kgkrhg.
 */

public class Part2 {

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

            Map<String, Set<String>> codeTable = new HashMap<>();
            // for each alergen filter those foods thak may contain it and by intersection get possible ingredient
            Set<String> ingrContainingAlergen = new HashSet<>();
            System.out.println("allergens and ingredients that may possibly contain it:");
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
                codeTable.put(a, intersection);
                System.out.println("possible ingredients: " + intersection);
                System.out.println();
                ingrContainingAlergen.addAll(intersection);
            }
            System.out.println(" ----------------------------------------------------------------- ");

            List<String> fin = new ArrayList<>();
            // iterate through allergens
            do {
                Iterator i = codeTable.keySet().iterator();
                while (i.hasNext()) {
                    String klic = (String) i.next();
                    String hodnota = "";
                    // if there is only one possible ingredient
                    if (codeTable.get(klic).size() == 1) {
                        hodnota = codeTable.get(klic).toString();
                        System.out.println(hodnota);
                        // put it into final
                        fin.add(klic + hodnota);
                        // remove it from codetable
                        i.remove();
                        // its value remove from remaining values in codetable
                        for (Set<String> set : codeTable.values()) {
                            set.remove(hodnota.replace("[", "").replace("]", ""));
                        }
                    }
                }
            } while (!codeTable.isEmpty());

            // sort final kombinations alphabetically
            fin.sort((s1, s2) -> (s1.compareTo(s2)));

            // retrieve values according task
            StringBuilder sb = new StringBuilder();
            for (String s : fin) {
                String pom = s.substring(s.indexOf("[") + 1, s.indexOf("]"));
                if (!sb.isEmpty()) {
                    sb.append(",");
                }
                sb.append(pom);
            }

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("solution = " + sb.toString());
            System.out.println("----------------------------------------------------------------");

            br.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
