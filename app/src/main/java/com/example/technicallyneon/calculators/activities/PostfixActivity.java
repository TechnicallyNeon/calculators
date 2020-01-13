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

import java.util.EmptyStackException;

public class PostfixActivity extends AppCompatActivity
{
    private static final String TAG = "Prefix Activity";
    private EditText rawExpression;
    private Button submitButton;
    private TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postfix);

        rawExpression = findViewById(R.id.postfix_raw_expression);
        submitButton = findViewById(R.id.postfix_submit_button);
        resultView = findViewById(R.id.postfix_result_view);
    }

    /**
     * Submit button's onclick method
     * @param v - View object used by the submit button
     */
    public void submit(View v)
    {
        Log.i(TAG, String.valueOf(rawExpression == null));
        String[] expressionArr = CalculatorUtility.stringToArray(rawExpression.getText().toString());

        if (expressionArr.length == 0)
            return;

        try
        {
            int result = calculate(expressionArr);
            resultView.setText("Result: " + result);
        } catch (ImproperOperandCountException e)
        {
            resultView.setText(e.getMessage() + " " +
                    " operands provided");
        } catch (IllegalArgumentException e1)
        {
            resultView.setText("Error reading, please use spaces.");
        } catch (ArithmeticException e2)
        {
            resultView.setText("Undefined");
        }
    }

    private static int calculate(String[] input)
    {
        StringStack stack = new StringStack();



        for (int i = 0; i < input.length; i++)
        {
            if (!CalculatorUtility.isOp(input[i]))
                stack.push(input[i]);
            else if (CalculatorUtility.isOp(input[i]))
            {
                int right, left, result = 0;
                try { right = Integer.valueOf(stack.pop()); }
                catch (Exception e) { throw new ImproperOperandCountException("Too few"); }
                try { left = Integer.valueOf(stack.pop()); }
                catch (Exception e) { left = 0; } // defaults left operand to zero

                switch (input[i].charAt(0))
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

                stack.push(String.valueOf(result));
            }
            else
                throw new IllegalArgumentException();
        }

        int result = Integer.valueOf(stack.pop());
        // Check to see if any operands remaining
        try { stack.pop(); }
        catch (EmptyStackException e) { return result; } // if none remaining, return
        throw new ImproperOperandCountException("Too many"); // if some remaining throw
    }
}
