package com.advent.day13;

public class Bus {
    private int delay;
    private int interval;

    public Bus(int delay, int interval) {
        this.delay = delay;
        this.interval = interval;
    }

    public int getDelay() {
        return delay;
    }

    public int getInterval() {
        return interval;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "delay=" + delay +
                ", interval=" + interval +
                '}';
    }
}
