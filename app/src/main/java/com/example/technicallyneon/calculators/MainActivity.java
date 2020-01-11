package com.example.technicallyneon.calculators;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity
{
    private Button prefix, infix, postfix;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Construct objects
        prefix = findViewById(R.id.prefix);
        infix = findViewById(R.id.infix);
        postfix = findViewById(R.id.postfix);
    }

    public void runPrefix(View v)
    {
        Log.i("Main Activity","Running prefix calculator...");

        Intent intent = new Intent(this, PrefixActivity.class);
        startActivity(intent);
    }

    public void runInfix(View v)
    {
        Log.i("Main Activity","Running infix calculator...");
        // TODO move to another activity
    }

    public void runPostfix(View v)
    {
        Log.i("Main Activity","Running postfix calculator...");
        // TODO move to another activity
    }
}
