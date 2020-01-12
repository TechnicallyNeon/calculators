package com.example.technicallyneon.calculators.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.technicallyneon.calculators.R;
import com.example.technicallyneon.calculators.calculators.ImproperOperandCountException;

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

    public void submit(View v)
    {
        Scanner scan = new Scanner(rawExpression.getText().toString());
        ArrayList<String> expression = new ArrayList<>();

        while (scan.hasNext())
            expression.add(scan.next());

        String[] expressionArr = new String[expression.size()];
        expression.toArray(expressionArr);
        for (int i = 0; i < expression.size(); i++)
            Log.i(TAG, expressionArr[i]);


    }
}
