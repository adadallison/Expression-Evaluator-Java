package edu.csc413.calculator.evaluator;

import edu.csc413.calculator.exceptions.InvalidTokenException;
import edu.csc413.calculator.operators.*;

import java.util.Stack;
import java.util.StringTokenizer;


public class Evaluator {

    private Stack<Operand> operandStack;
    private Stack<Operator> operatorStack;
    private StringTokenizer expressionTokenizer; // this is a (private) class in Java is used to break a string into tokens.
    private final String delimiters = " +/*-^()"; // our default delimiters

  /* Example of a StringTokenizer:

  StringTokenizer st = new StringTokenizer("this is a test");
       while (st.hasMoreTokens()) {
   }*/

    public Evaluator() { // constructor that creates the object
        operandStack = new Stack<>();
        operatorStack = new Stack<>();
    }

    // Evaluator Expression: where most of the work will be done
    public int evaluateExpression(String expression) throws InvalidTokenException {
        String expressionToken;

        // The 3rd argument is true to indicate that the delimiters should be used
        // as tokens, too. But, we'll need to remember to filter out spaces.
        this.expressionTokenizer = new StringTokenizer(expression, this.delimiters, true); // basically: "StringTokenizer expressionTokenizer = new StringTokenizer( expression, this.delimiters, true);
   /*
        while(expressionTokenizer.hasMoreTokens()) {
            System.out.println(expressionTokenizer.nextToken());
        }
        this.expressionTokenizer = new StringTokenizer(expression, this.delimiters, true); // basically: "StringTokenizer expressionTokenizer = new StringTokenizer( expression, this.delimiters, true);
*/
        // initialize operator stack - necessary with operator priority schema
        // the priority of any operator in the operator stack other than
        // the usual mathematical operators - "+-*/" - should be less than the priority
        // of the usual operators

        //System.out.println(expression);

        while (this.expressionTokenizer.hasMoreTokens()) {
            expressionToken = this.expressionTokenizer.nextToken();
            //System.out.println(expressionToken);
            // filter out spaces
            if (!expressionToken.equals(" ")) { // if theres spaces we are going to skip them
                // check if token is an operand
                if (Operand.check(expressionToken)) { // If an operand token is scanned..
                    operandStack.push(new Operand(expressionToken)); // an Operand object is created from the token, and pushed to the operand Stack
                } else { // If an operator token is scanned,.. ?????if (Operator.check(expressionToken))?????
                    if (!Operator.check(expressionToken)) { // else if NOT an operand, we check if its an operator, if YES then returns true AND jumps back to ELSE
                        throw new InvalidTokenException(expressionToken); // if NOT and operator, then exception is thrown
                    }

                    // we know an operator was now scanned


                    // TODO Operator is abstract - these two lines will need to be fixed:
                    // The Operator class should contain an instance of a HashMap,
                    // and values will be instances of the Operators.  See Operator class
                    // skeleton for an example. -DONE!
                    Operator newOperator = Operator.getOperator(expressionToken); // trying to create an instance of the operator class
                    //System.out.println(newOperator.getClass().getSimpleName());


                    // parenthesis
                    if (newOperator instanceof OpenOperator) {
                        operatorStack.push(newOperator);
                        continue;
                    }

                    if (newOperator instanceof ClosedOperator) {
                        //System.out.println("closed op");
                        while (!operatorStack.isEmpty() && !(operatorStack.peek() instanceof OpenOperator)){
                            Operator operatorFromStack = operatorStack.pop();
                            //System.out.println(operatorFromStack.getClass().getSimpleName());
                            Operand operandTwo = operandStack.pop();
                            Operand operandOne = operandStack.pop();
                            Operand result = operatorFromStack.execute(operandOne, operandTwo);
                            operandStack.push(result);
                        }
                        if (operatorStack.isEmpty()){
                            throw new RuntimeException("Mismatched Parenthesis");
                        }
                        operatorStack.pop();
                        continue;
                    }

                        // "Operator Process":
                        while (!operatorStack.isEmpty() && operatorStack.peek().priority() >= newOperator.priority()) {
                            // If an operator token is scanned, and the operator Stack is not empty, and the operator’s precedence is greater than the precedence of the Operator
                            // at the top of the Stack, then and Operator object is created from the token, and pushed to the operator Stack

                            // Processing an Operator:
                            // note that when we eval the expression 1 - 2 we will
                            // push the 1 then the 2 and then do the subtraction operation
                            // This means that the first number to be popped is the
                            // second operand, not the first operand - see the following code
                            Operator operatorFromStack = operatorStack.pop();
                            Operand operandTwo = operandStack.pop();
                            Operand operandOne = operandStack.pop();
                            Operand result = operatorFromStack.execute(operandOne, operandTwo);
                            operandStack.push(result);
                        }

                        operatorStack.push(newOperator); // does it always push after the while loop above?
                    }

                }
            }


            // Control gets here when we've picked up all of the tokens; you must add
            // code to complete the evaluation - consider how the code given here
            // will evaluate the expression 1+2*3
            // When we have no more tokens to scan, the operand stack will contain 1 2
            // and the operator stack will have + * with 2 and * on the top;
            // In order to complete the evaluation we must empty the stacks,
            // that is, we should keep evaluating the operator stack until it is empty;
            // Suggestion: create a method that processes the operator stack until empty.

            return evaluateHelper(); // DO NOT always return 0, RETURN the result, change this
        }

        private int evaluateHelper () {
            while (!operatorStack.isEmpty()) {
                Operator operatorFromStack = operatorStack.pop();
                Operand operandTwo = operandStack.pop();
                Operand operandOne = operandStack.pop();
                Operand result = operatorFromStack.execute(operandOne, operandTwo);
                operandStack.push(result);
            }
            int result = operandStack.pop().getValue();
            return result;

        }
    }

