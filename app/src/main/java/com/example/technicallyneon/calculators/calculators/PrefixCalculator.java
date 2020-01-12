package com.example.technicallyneon.calculators.calculators;

public class PrefixCalculator
{
    private PrefixCalculator()
    {
    }

    public static int calculate(String[] input) throws ImproperOperandCountException
    {
        StringStack operands = new StringStack();
        for (int i = input.length - 1; i >= 0; i--)
        {
            String curr = input[i];
            if (!isOp(curr)) // if an operand
                operands.add(curr);
            else // if an operator
            {
                int left = Integer.valueOf(operands.pop());
                int right = 0;
                try { right = Integer.valueOf(operands.pop()); }
                catch (Exception e) // if a value is missing, the left value is assumed to be zero
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
                        result = left / right;
                        break;
                    case '%':
                        result = left % right;
                        break;
                }

                operands.add(String.valueOf(result));
            }
        }

        int result = Integer.valueOf(operands.pop());
        // Check to see if any operands remaining
        try { String test = operands.pop(); }
        catch (Exception e) { throw new ImproperOperandCountException("Too many operands"); }
        return result;
    }

    private static boolean isOp (String s)
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
}
