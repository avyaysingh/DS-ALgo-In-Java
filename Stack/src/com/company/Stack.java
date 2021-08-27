package com.company;

public class Stack {
    private static int tos;
    private static int[] stack = new int[10];

    public static void push(int num){
        if (tos == 9){
            System.out.println("stack is full");
        }
        else {
            stack[++tos] = num;
        }
    }

    public static int pop(){
        if (tos < 0){
            System.out.println("stack underflow.");
            return 0;
        }
        else {
            return stack[tos--];
        }
    }
    
}
