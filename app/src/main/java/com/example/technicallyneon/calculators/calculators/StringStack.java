package com.example.technicallyneon.calculators.calculators;

public class StringStack
{
    /**
     * Node that references the top of the stack
     */
    private Node top;

    public StringStack()
    {

    }

    public void add(String item)
    {
        if (top == null)
            top = new Node(item, null);
        else
        {
            Node newNode = new Node(item, top);
            top = newNode;
        }
    }

    public String pop()
    {
        if (top == null)
            return null;
        String result = top.val;
        top = top.next;
        return result;
    }

    private class Node
    {
        private String val;
        private Node next;

        private Node(String val, Node next)
        {
            this.val = val;
            this.next = next;
        }
    }
}
