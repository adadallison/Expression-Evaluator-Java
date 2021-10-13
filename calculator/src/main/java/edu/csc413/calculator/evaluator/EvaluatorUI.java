package edu.csc413.calculator.evaluator;

import edu.csc413.calculator.exceptions.InvalidTokenException;
import edu.csc413.calculator.operators.AddOperator;
import edu.csc413.calculator.operators.Operator;
import jdk.jshell.EvalException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EvaluatorUI extends JFrame implements ActionListener {

    private TextField expressionTextField = new TextField(); // creating a Textfield OBJ called 'expressionTextField'
    private Panel buttonPanel = new Panel();

    // total of 20 buttons on the calculator,
    // numbered from left to right, top to bottom
    // bText[] array contains the text for corresponding buttons
    private static final String[] buttonText = {
            "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3",
            "*", "0", "^", "=", "/", "(", ")", "C", "CE"
    };

    /**
     * C  is for clear, clears entire expression
     * CE is for clear expression, clears last entry up until the last operator.
     */
    private Button[] buttons = new Button[buttonText.length];

    public static void main(String argv[]) {
        new EvaluatorUI();
    }

    public EvaluatorUI() {
        setLayout(new BorderLayout());
        this.expressionTextField.setPreferredSize(new Dimension(600, 50));
        this.expressionTextField.setFont(new Font("Courier", Font.BOLD, 28));

        add(expressionTextField, BorderLayout.NORTH);
        expressionTextField.setEditable(false);

        add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setLayout(new GridLayout(5, 4));

        //create 20 buttons with corresponding text in bText[] array
        Button tempButtonReference;
        for (int i = 0; i < EvaluatorUI.buttonText.length; i++) {
            tempButtonReference = new Button(buttonText[i]);
            tempButtonReference.setFont(new Font("Courier", Font.BOLD, 28));
            buttons[i] = tempButtonReference;
        }

        //add buttons to button panel
        for (int i = 0; i < EvaluatorUI.buttonText.length; i++) {
            buttonPanel.add(buttons[i]);
        }

        //set up buttons to listen for mouse input
        for (int i = 0; i < EvaluatorUI.buttonText.length; i++) {
            buttons[i].addActionListener(this);
        }

        setTitle("Calculator");
        setSize(400, 400);
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * This function is triggered anytime a button is pressed
     * on our Calculator GUI.
     *
     * @param actionEventObject Event object generated when a
     *                          button is pressed.
     */
    public void actionPerformed(ActionEvent actionEventObject) {

        String buttonPressed = actionEventObject.getActionCommand(); // prints button pressed

        String oldText = this.expressionTextField.getText();
        try { // placed all if statements in this TRY CATCH so that if any invalid entries such as '++-2324+' were entered it would explain why entry is wrong
            if ("7".equals(buttonPressed)) {
                this.expressionTextField.setText(oldText + "7"); // makes text on calculator = buttonPressed
            } else if ("8".equals(buttonPressed)) {
                this.expressionTextField.setText(oldText + "8");
            } else if ("9".equals(buttonPressed)) {
                this.expressionTextField.setText(oldText + "9");
            } else if ("+".equals(buttonPressed)) {
                this.expressionTextField.setText(oldText + "+");
            } else if ("-".equals(buttonPressed)) {
                this.expressionTextField.setText(oldText + "-");
            } else if ("4".equals(buttonPressed)) {
                this.expressionTextField.setText(oldText + "4");
            } else if ("5".equals(buttonPressed)) {
                this.expressionTextField.setText(oldText + "5");
            } else if ("6".equals(buttonPressed)) {
                this.expressionTextField.setText(oldText + "6");
            } else if ("1".equals(buttonPressed)) {
                this.expressionTextField.setText(oldText + "1");
            } else if ("2".equals(buttonPressed)) {
                this.expressionTextField.setText(oldText + "2");
            } else if ("3".equals(buttonPressed)) {
                this.expressionTextField.setText(oldText + "3");
            } else if ("*".equals(buttonPressed)) {
                this.expressionTextField.setText(oldText + "*");
            } else if ("0".equals(buttonPressed)) {
                this.expressionTextField.setText(oldText + "0");
            } else if ("^".equals(buttonPressed)) {
                this.expressionTextField.setText(oldText + "^");
            } else if ("=".equals(buttonPressed)) {
                try {
                    int result = (new Evaluator()).evaluateExpression(this.expressionTextField.getText()); // result = evaluateExpression in the Evaluating class evaluating the Text we input
                    //System.out.println(String.valueOf(result));
                    this.expressionTextField.setText(oldText + "=" + String.valueOf(result)); // converts Int result which Evaluator class produced back into a String so the calculatorUI can print it
                    System.out.println(result); // prints result on console as well
                } catch (InvalidTokenException e) {
                    System.out.println("InvalidTokenException");
                }

            } else if ("/".equals(buttonPressed)) {
                this.expressionTextField.setText(oldText + "/");
            } else if ("(".equals(buttonPressed)) {
                this.expressionTextField.setText(oldText + "(");
            } else if (")".equals(buttonPressed)) {
                this.expressionTextField.setText(oldText + ")");
            } else if ("C".equals(buttonPressed)) {

                String CText = oldText.substring(0, oldText.length() - 1); // pressing C will keep removing the last string until Exception is thrown
                this.expressionTextField.setText(CText);

            } else if ("CE".equals(buttonPressed)) {
                this.expressionTextField.setText(""); // clears everything previously typed
            }

        } catch (Exception e) {
            System.out.println("Invalid Entry, Press CE And Try Again!");
        }

    }
}




