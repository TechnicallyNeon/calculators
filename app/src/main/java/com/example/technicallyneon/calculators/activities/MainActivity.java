package com.example.technicallyneon.calculators.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.technicallyneon.calculators.R;


public class MainActivity extends AppCompatActivity
{
    private Button prefix, postfix;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Construct objects
        prefix = findViewById(R.id.prefix);
        postfix = findViewById(R.id.postfix);
    }

    public void runPrefix(View v)
    {
        Log.i("Main Activity","Running prefix calculator...");

        Intent intent = new Intent(this, PrefixActivity.class);
        startActivity(intent);
    }

    public void runPostfix(View v)
    {
        Log.i("Main Activity","Running postfix calculator...");

        Intent intent = new Intent(this, PostfixActivity.class);
        startActivity(intent);
    }
}
