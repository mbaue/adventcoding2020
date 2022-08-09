package com.advent.day21;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Food {
    private Set<String> ingredients;
    private Set<String> alergens;

    public Food(String[] ingredients, String[] alergens) {
        this.ingredients = new HashSet<>(Arrays.asList(ingredients));
        this.alergens = new HashSet<>(Arrays.asList(alergens));
    }

    public boolean contains(String ingr) {
        return this.alergens.contains(ingr);
    }

    public Set<String> getIngredients() {
        return ingredients;
    }
}
