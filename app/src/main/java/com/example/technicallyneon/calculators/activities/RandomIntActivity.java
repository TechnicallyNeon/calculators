package com.example.technicallyneon.calculators.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.technicallyneon.calculators.R;

public class RandomIntActivity extends AppCompatActivity
{
    private static final String TAG = "RandomIntActivity";
    private EditText lower, upper;
    private TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_int);

        lower = findViewById(R.id.random_int_lower);
        upper = findViewById(R.id.random_int_upper);
        resultView = findViewById(R.id.random_int_result_view);
    }

    public void submit(View v)
    {
        int low, high;
        try
        {
            low = Integer.valueOf(lower.getText().toString());
            high = Integer.valueOf(upper.getText().toString());
        }
        catch (Exception e) { resultView.setText("Result: "); return; }
        int result = (int) Math.round(Math.random() * (high - low) + low);
        resultView.setText("Result: " + String.valueOf(result));
    }
}
