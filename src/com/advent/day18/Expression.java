package com.advent.day18;

public class Expression {

    public String expression;
    public int nextChar = 0;

    public Expression(String expression) {
        this.expression = expression;
        this.nextChar = 0;
    }

    public long processExpression() {
        long firstNumber = -1;
        long secondNumber = -1;
        char operator = 'x';
        while (nextChar < expression.length()) {
            char myChar = getNextChar();
            if (myChar >= '0' && myChar <= '9') {
                if (firstNumber < 0) {
                    firstNumber = Integer.parseInt(String.valueOf(myChar));
                } else {
                    if (operator != 'x') {
                        secondNumber = Integer.parseInt(String.valueOf(myChar));
                        if (operator == '+') {
                            firstNumber = firstNumber + secondNumber;
                        } else if (operator == '*') {
                            firstNumber = firstNumber * secondNumber;
                        }
                        secondNumber = -1;
                        operator = 'x';
                    } else {
                        System.out.println("chyba - mela bych druhy operand, ale nemam operator");
                    }
                }
            } else if (myChar == '+' || myChar == '*') {
                operator = myChar;
            } else if (myChar == '(') {
                long obsahZavorky = processExpression();
                if (firstNumber < 0) {
                    firstNumber = obsahZavorky;
                } else {
                    secondNumber = obsahZavorky;
                    if (operator == '+') {
                        firstNumber = firstNumber + secondNumber;
                    } else if (operator == '*') {
                        firstNumber = firstNumber * secondNumber;
                    }
                    secondNumber = -1;
                    operator = 'x';
                }
            } else if (myChar == ')') {
                return firstNumber;
            }
        }
        return firstNumber;
    }

    private char getNextChar() {
        char ch;
        do {
            ch = expression.charAt(nextChar);
            nextChar++;
        } while (ch == ' ');
        return ch;
    }
}
