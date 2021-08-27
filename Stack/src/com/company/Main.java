package com.company;

public class Main {

    public static void main(String[] args) {
        Stack stack = new Stack();
        Stack.push(10);
        Stack.push(20);
        Stack.push(30);
        Stack.push(40);
        Stack.push(50);
        Stack.push(60);
        Stack.push(70);
        Stack.push(80);

        System.out.println(Stack.pop());
        System.out.println(Stack.pop());
        System.out.println(Stack.pop());
    }
}
