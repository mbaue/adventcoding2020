package com.advent.day11;

public class WaitingRoom {

    public static final char EMPTY = 'L';
    public static final char OCCUPIED = '#';

    public static final int ROWS = 90;
    public static final int COLUMNS = 93;

    public char[][] seats = new char[ROWS][COLUMNS];

    public WaitingRoom() {
    }

    /*
    for part 1
     */
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

    /*
    for part 2
     */
    public int getOccupiedVisibleNeighbors(int row, int col) {
        int occupiedVisibleNeighbors = 0;
        // up
        for (int i = row - 1; i >= 0; i--) {
            if (this.seats[i][col] == OCCUPIED) {
                occupiedVisibleNeighbors++;
                break;
            } else if (this.seats[i][col] == EMPTY) {
                break;
            }
        }
        // down
        for (int i = row + 1; i < ROWS; i++) {
            if (this.seats[i][col] == OCCUPIED) {
                occupiedVisibleNeighbors++;
                break;
            } else if (this.seats[i][col] == EMPTY) {
                break;
            }
        }
        // left
        for (int i = col - 1; i >= 0; i--) {
            if (this.seats[row][i] == OCCUPIED) {
                occupiedVisibleNeighbors++;
                break;
            } else if (this.seats[row][i] == EMPTY) {
                break;
            }
        }
        // right
        for (int i = col + 1; i < COLUMNS; i++) {
            if (this.seats[row][i] == OCCUPIED) {
                occupiedVisibleNeighbors++;
                break;
            } else if (this.seats[row][i] == EMPTY) {
                break;
            }
        }
        // up-left
        for (int i = col - 1, j = row - 1; i >= 0 && j >= 0; i--, j--) {
            if (this.seats[j][i] == OCCUPIED) {
                occupiedVisibleNeighbors++;
                break;
            } else if (this.seats[j][i] == EMPTY) {
                break;
            }
        }
        // up-right
        for (int i = col + 1, j = row - 1; i < COLUMNS && j >= 0; i++, j--) {
            if (this.seats[j][i] == OCCUPIED) {
                occupiedVisibleNeighbors++;
                break;
            } else if (this.seats[j][i] == EMPTY) {
                break;
            }
        }
        // down-left
        for (int i = col - 1, j = row + 1; i >= 0 && j < ROWS; i--, j++) {
            if (this.seats[j][i] == OCCUPIED) {
                occupiedVisibleNeighbors++;
                break;
            } else if (this.seats[j][i] == EMPTY) {
                break;
            }
        }
        // down-right
        for (int i = col + 1, j = row + 1; i < COLUMNS && j < ROWS; i++, j++) {
            if (this.seats[j][i] == OCCUPIED) {
                occupiedVisibleNeighbors++;
                break;
            } else if (this.seats[j][i] == EMPTY) {
                break;
            }
        }
        return occupiedVisibleNeighbors;
    }

    public void printWaitingRoom() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print(seats[i][j]);
            }
            System.out.println();
        }
        System.out.println(".....");
    }

    public void printWaitingRoomPartial() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print(seats[i][j]);
            }
            System.out.println();
        }
        System.out.println(".....");
    }

    public void copyValuesFrom(WaitingRoom next) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                this.seats[i][j] = next.seats[i][j];
            }
        }
    }

    public boolean isDifferentFrom(WaitingRoom otherWR) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (this.seats[i][j] != otherWR.seats[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public int countOccupiedSeats() {
        int count = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (this.seats[i][j] == OCCUPIED) {
                    count++;
                }
            }
        }
        return count;
    }

    /*
    for part 1
    */
    public void evaluateNextRound(WaitingRoom next) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
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

    /*
    for part 2
     */
    public void evaluateNextRoundEnhanced(WaitingRoom next) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                next.seats[i][j] = this.seats[i][j];
                if (this.seats[i][j] == WaitingRoom.EMPTY) {
                    if (this.getOccupiedVisibleNeighbors(i, j) == 0) {
                        next.seats[i][j] = WaitingRoom.OCCUPIED;
                    }
                } else if (this.seats[i][j] == WaitingRoom.OCCUPIED) {
                    if (this.getOccupiedVisibleNeighbors(i, j) >= 5) {
                        next.seats[i][j] = WaitingRoom.EMPTY;
                    }
                }
            }
        }
    }

}

