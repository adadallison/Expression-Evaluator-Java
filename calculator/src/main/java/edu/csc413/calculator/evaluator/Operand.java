package edu.csc413.calculator.evaluator;

/**
 * Operand class used to represent an operand
 * in a valid mathematical expression.
 */

// purpose: store numbers as objects
    // we are going to store the operand tokens from our expressions as operand objects

public class Operand {

    private int operandValue; // operand will be stored as an int value


    /**
     * construct operand from string token.
     */
    public Operand(String token) { // CONSTRUCTOR that passes a String
        operandValue = Integer.parseInt(token); // converts Strings to Integers
    }

    /**
     * construct operand from integer - constructor that takes a string
     */
    public Operand(int value) { // CONSTRUCTOR that passes an Int we have now converted from a String value
        operandValue = value;
    }

    /**
     * return value of operand - constructor that takes an int
     */
    public int getValue() { // has to be an int
        return operandValue;
    }

    /**
     * Check to see if given token is a valid
     * operand.
     */
    public static boolean check(String token) {  // checks tokens to make sure strings are actually numbers

        try {
            Integer.parseInt(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
