package com.company;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
//        stack.push(10);
//        stack.push(20);
//        stack.push(30);
//        stack.push(40);
//        stack.push(50);
//        stack.push(60);
//        System.out.println("popped element : " + stack.pop());
//        System.out.println("Peek operation result : " + stack.peek());

        System.out.println("stack : " + stack);

    }
}

class StackDemo{
    public static void showPush(Stack<Integer> stack, int num){
        stack.push(num);
        System.out.println("push (" + num + ")");
        System.out.println("stack : " + stack);
    }

    public static void showPop(Stack<Integer> stack){
        System.out.println("pop --> ");
        int a = stack.pop();
        System.out.println(a);
        System.out.println("stack : " + stack);
    }
}
