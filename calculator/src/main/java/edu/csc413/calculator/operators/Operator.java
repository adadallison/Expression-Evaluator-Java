package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

import java.util.HashMap;
import java.util.Map;

public abstract class Operator {
    // The Operator class should contain an instance of a HashMap
    // This map will use keys as the tokens we're interested in,
    // and values will be instances of the Operators.
    // ALL subclasses of operator MUST be in their own file.
    // Example:
    // Where does this declaration go? What should its access level be?
    // Class or instance variable? Is this the right declaration?
    // HashMap operators = new HashMap();
    // operators.put( "+", new AdditionOperator() );
    // operators.put( "-", new SubtractionOperator() );

    private static HashMap<String, Operator> operators; // no one outside Operator class should know we have the hashmap bc PRIVATE

    static {
        operators = new HashMap<>();
        operators.put("+", new AddOperator()); // (key, value) ; keys=tokens & value=subclasses of operator
        operators.put("-", new SubtractOperator());
        operators.put("*", new MultiplyOperator());
        operators.put("/", new DivideOperator());
        operators.put("^", new PowerOperator());
        operators.put(")", new ClosedOperator());
        operators.put("(", new OpenOperator());

    }

    /**
     * retrieve the priority of an Operator
     *
     * @return priority of an Operator as an int
     */
    public abstract int priority(); // abstract class: all of our child classes must have this fcn

    /**
     * Abstract method to execute an operator given two operands.
     *
     * @param operandOne first operand of operator
     * @param operandTwo second operand of operator
     * @return an operand of the result of the operation.
     */
    public abstract Operand execute(Operand operandOne, Operand operandTwo); // all of our child classes must have this fcn

    /**
     * used to retrieve an operator from our HashMap.
     * This will act as out publicly facing function,
     * granting access to the Operator HashMap.
     *
     * @param token key of the operator we want to retrieve
     * @return reference to a Operator instance.
     */
    public static Operator getOperator(String token) {

        return operators.get(token); // returns hash map value

    } // returns operators from the hashmap


    /**
     * determines if a given token is a valid operator.
     * please do your best to avoid static checks
     * for example token.equals("+") and so on.
     * Think about what happens if we add more operators.
     */
    public static boolean check(String token) {
        //System.out.println(token);
        //System.out.println(operators.containsKey(token));
        return operators.containsKey(token); // determines if a given token is a valid operator by checking if hashmap contains the operator
    }
}
