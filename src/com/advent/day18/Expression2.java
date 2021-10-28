package com.advent.day18;

import java.util.Stack;

public class Expression2 {
    public String expression;
    public int nextChar = 0;

    public Expression2(String expression) {
        this.expression = expression;
        this.nextChar = 0;
    }

    public long processExpression() {
        char operator = 'x';
        Stack<Long> st = new Stack();

        while (nextChar < expression.length()) {
            char myChar = getNextChar();
            if (myChar >= '0' && myChar <= '9') {
                st.push(Long.parseLong(String.valueOf(myChar)));
                if (operator == '*') {
                    operator = 'x';
                } else if (operator == '+') {
                    long first = st.pop();
                    long second = st.pop();
                    long result = first + second;
                    st.push(result);
                    operator = 'x';
                }
            } else if (myChar == '+' || myChar == '*') {
                operator = myChar;
            } else if (myChar == '(') {
                long obsahZavorky = processExpression();

                st.push(obsahZavorky);
                if (operator == '*') {
                    operator = 'x';
                } else if (operator == '+') {
                    long first = st.pop();
                    long second = st.pop();
                    long result = first + second;
                    st.push(result);
                    operator = 'x';
                }
            } else if (myChar == ')') {
                long product = 1L;
                while (!st.isEmpty()) {
                    product = product * st.pop();
                }
                return product;
            }
        }

        long product = 1L;
        while (!st.isEmpty()) {
            product = product * st.pop();
        }
        return product;
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
