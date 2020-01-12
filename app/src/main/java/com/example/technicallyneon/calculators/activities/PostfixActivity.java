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
import java.util.Scanner;

public class PostfixActivity extends AppCompatActivity
{
    private final String TAG = "Prefix Activity";
    private EditText rawExpression;
    private Button submitButton;
    private TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postfix);

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
                int right;
                try { right = Integer.valueOf(stack.pop()); }
                catch (Exception e) { throw new ImproperOperandCountException("Too few"); }
                int left;
            }
        }

        return 0;
    }
}
