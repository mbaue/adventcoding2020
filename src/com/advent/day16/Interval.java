package com.advent.day16;

public class Interval {

    private int lowerLimit;
    private int upperLimit;

    public Interval(int lowerLimit, int upperLimit) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    public int getLowerLimit() {
        return lowerLimit;
    }

    public int getUpperLimit() {
        return upperLimit;
    }

    public boolean contains(int number) {
        return number >= this.lowerLimit && number <= this.upperLimit;
    }
}
