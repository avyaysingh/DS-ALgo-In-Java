package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Dequeue dequeue = new Dequeue(10);
        dequeue.addFront(10);
        dequeue.addFront(20);
        dequeue.addFront(30);
        dequeue.addFront(40);
        dequeue.addFront(50);
        dequeue.addFront(60);
        dequeue.addFront(70);
        dequeue.addFront(80);
        dequeue.addFront(90);
        dequeue.addFront(100);
        //dequeue.addFront(500);

        //System.out.println(dequeue.printFrontValue());
        dequeue.printDeQueue();
    }
}
