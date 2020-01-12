package com.example.technicallyneon.calculators.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.technicallyneon.calculators.R;
import com.example.technicallyneon.calculators.calculators.CalculatorUtility;
import com.example.technicallyneon.calculators.calculators.ImproperOperandCountException;
import com.example.technicallyneon.calculators.calculators.StringStack;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Scanner;

public class PrefixActivity extends AppCompatActivity
{
    private final String TAG = "Prefix Activity";
    private EditText rawExpression;
    private Button submitButton;
    private TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefix);

        rawExpression = findViewById(R.id.prefix_raw_expression);
        submitButton = findViewById(R.id.prefix_submit_button);
        resultView = findViewById(R.id.prefix_result_view);
    }

    /**
     * Submit button's onclick method
     * @param v - View object used by the submit button
     */
    public void submit(View v)
    {
        String[] expressionArr = CalculatorUtility.stringToArray(rawExpression.getText().toString());
        for (int i = 0 ; i < expressionArr.length; i++)
            Log.i(TAG, expressionArr[i]);

        if (expressionArr.length == 0)
            return; // don't do anything if input is empty

        try
        {
            int result = calculate(expressionArr);
            resultView.setText("Result: " + result);
        } catch (ImproperOperandCountException e)
        {
            if (e.getMessage().equals("Too many"))
                resultView.setText("Too many operands provided");
            else
                resultView.setText("Too few operands provided");
        } catch (IllegalArgumentException e1)
        {
            resultView.setText("Error reading, please use spaces.");
        } catch (ArithmeticException e2)
        {
            resultView.setText("Undefined");
        }
    }

    private static int calculate(String[] input) throws ImproperOperandCountException,
            IllegalArgumentException, ArithmeticException
    {
        StringStack operands = new StringStack(); // new stack
        // read array backwards
        for (int i = input.length - 1; i >= 0; i--)
        {
            String curr = input[i];
            if (!CalculatorUtility.isOp(curr)) // if an operand, add to stack
                operands.push(curr);
            else if (CalculatorUtility.isOp(curr))// if an operator, pop and evaluate
            {
                int left;
                try
                {
                    left = Integer.valueOf(operands.pop());
                } catch (Exception e)
                {
                    throw new ImproperOperandCountException("Too few");
                }
                int right;
                try
                {
                    right = Integer.valueOf(operands.pop());
                } catch (Exception e) // if a value is missing, the left value is assumed to be zero
                {                   // i.e. - 5 = -5
                    right = left;
                    left = 0;
                }
                int result = 0;

                switch (curr.charAt(0))
                {
                    case '+':
                        result = left + right;
                        break;
                    case '-':
                        result = left - right;
                        break;
                    case '*':
                        result = left * right;
                        break;
                    case '/':
                        if (right == 0)
                            throw new ArithmeticException();
                        result = left / right;
                        break;
                    case '%':
                        if (right == 0)
                            throw new ArithmeticException();
                        result = left % right;
                        break;
                }

                operands.push(String.valueOf(result));
            } else
            {
                throw new IllegalArgumentException();
            }
        }

        int result = Integer.valueOf(operands.pop());
        // Check to see if any operands remaining
        try { operands.pop(); }
        catch (EmptyStackException e) { return result; } // if none remaining, return
        throw new ImproperOperandCountException("Too many"); // if some remaining throw
    }
}
