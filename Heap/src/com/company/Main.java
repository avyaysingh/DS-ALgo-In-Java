package com.company;

public class Main {

    public static void main(String[] args) {
//        MinHeap minHeap = new MinHeap(10);
//        minHeap.add(10);
//        minHeap.add(20);
//        minHeap.add(500);
//        minHeap.add(200);
//        minHeap.add(30);
//        minHeap.add(1);
//        minHeap.add(5);
//        System.out.println(minHeap.peek());
//        minHeap.poll();
//        System.out.println(minHeap.peek());

        MaxHeap maxHeap = new MaxHeap(10);
        maxHeap.add(10);
        maxHeap.add(100);
        maxHeap.add(233);
        maxHeap.add(14);
        maxHeap.add(15);
        maxHeap.add(1334);
        maxHeap.add(1);
        System.out.println(maxHeap.peek());
        maxHeap.poll();
        System.out.println(maxHeap.peek());



    }
}
