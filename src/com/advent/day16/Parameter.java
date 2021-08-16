package com.advent.day16;

import java.util.*;

public class Parameter {

    private String paramName;
    private List<Interval> intervals;
    public ArrayList<Integer> possiblePositions;
    private boolean finalPosition = false;

    public Parameter(String paramName) {
        this.paramName = paramName;
        this.intervals = new ArrayList<>();
        this.possiblePositions = new ArrayList<>() ;
    }

    public void addInterval(Interval interval) {
        this.intervals.add(interval);
    }

    public boolean isValid(int number) {
        for (Interval i : intervals) {
            if (i.contains(number)) {
                return true;
            }
        }
        return false;
    }

    public String getParamName() {
        return paramName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(paramName);
        sb.append(", ");
        for (Interval inter : intervals) {
            sb.append(inter.toString()).append(", ");
        }
        return sb.toString();
    }

    public void setFinalPosition(boolean finalPosition) {
        this.finalPosition = finalPosition;
    }

    public boolean isFinalPosition() {
        return finalPosition;
    }
}
