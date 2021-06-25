package com.advent.day11;

public class WaitingRoom {

    public static final char EMPTY = 'L';
    public static final char OCCUPIED = '#';
    public static final char FLOOR = '.';

    public char[][] seats = new char[98][93];

    public WaitingRoom() {
    }

    public int getOccupiedNeighbors(int line, int col) {
        int occupiedNeighbors = 0;
        for (int i = line - 1; i <= line + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < 98 && j >= 0 && j < 93 && (i != line || j != col)) {
                    if (this.seats[i][j] == OCCUPIED) {
                        occupiedNeighbors++;
                    }
                }
            }
        }
        return occupiedNeighbors;
    }

    public void printWaitingRoomPartial() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < Day11.COLUMNS; j++) {
                System.out.print(seats[i][j]);
            }
            System.out.println();
        }
        System.out.println(".....");
    }

    public void copyValuesFrom(WaitingRoom next) {
        for (int i = 0; i < Day11.ROWS; i++) {
            for (int j = 0; j < Day11.COLUMNS; j++) {
                this.seats[i][j] = next.seats[i][j];
            }
        }
    }

    public boolean isDifferentFrom(WaitingRoom otherWR) {
        for (int i = 0; i < Day11.ROWS; i++) {
            for (int j = 0; j < Day11.COLUMNS; j++) {
                if (this.seats[i][j] != otherWR.seats[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public int countOccupiedSeats() {
        int count = 0;
        for (int i = 0; i < Day11.ROWS; i++) {
            for (int j = 0; j < Day11.COLUMNS; j++) {
                if (this.seats[i][j] == OCCUPIED) {
                    count++;
                }
            }
        }
        return count;
    }

    public void evaluateNextRound(WaitingRoom next) {
        for (int i = 0; i < Day11.ROWS; i++) {
            for (int j = 0; j < Day11.COLUMNS; j++) {
                next.seats[i][j] = this.seats[i][j];
                if (this.seats[i][j] == WaitingRoom.EMPTY) {
                    if (this.getOccupiedNeighbors(i, j) == 0) {
                        next.seats[i][j] = WaitingRoom.OCCUPIED;
                    }
                } else if (this.seats[i][j] == WaitingRoom.OCCUPIED) {
                    if (this.getOccupiedNeighbors(i, j) >= 4) {
                        next.seats[i][j] = WaitingRoom.EMPTY;
                    }
                }
            }
        }
    }

}

