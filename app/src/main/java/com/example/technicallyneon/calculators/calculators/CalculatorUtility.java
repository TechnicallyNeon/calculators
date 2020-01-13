package com.example.technicallyneon.calculators.calculators;

import android.util.Log;

import java.util.ArrayList;
import java.util.Scanner;

public class CalculatorUtility
{
    private static final String TAG = "CalculatorUtility";

    private CalculatorUtility()
    {
    }

    public static boolean isOp (String s)
    {
        int l = s.length();
        if (l > 1)
            return false;
        for (int i = 0; i < l; i++)
            switch (s.charAt(0))
            {
                case '+':
                case '-':
                case '*':
                case '/':
                case '%':
                    return true;
            }
        return false;
    }

    /**
     * Separates words in a given String into an array
     * @param s - String provided
     * @return an array of the words contained in s
     */
    public static String[] stringToArray(String s)
    {
        Scanner scan = new Scanner(s);
        Log.i(TAG, s);
        ArrayList<String> sList = new ArrayList<>();

        while (scan.hasNext())
            sList.add(scan.next());

        String[] expressionArr = new String[sList.size()];
        return sList.toArray(expressionArr);
    }
}
