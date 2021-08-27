package com.company;

import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        priorityQueue.add(10);
        priorityQueue.add(20);
        priorityQueue.add(30);
        priorityQueue.add(40);
        priorityQueue.add(50);

//        priorityQueue.clear();
        System.out.println(priorityQueue.contains(50));
        System.out.println(priorityQueue);
    }
}
