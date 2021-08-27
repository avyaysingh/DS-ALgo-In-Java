package com.company;

public class Dequeue {
    static final int maximum = 30;
    private int[] array;
    private int front;
    private int rear;
    private int capacity;

    public Dequeue(int capacity) {
        this.array = new int[maximum];
        this.front = -1;
        this.rear = 0;
        this.capacity = capacity;
    }

    public void addFront(int value){
        if ((front == 0 && rear == capacity - 1) || front == rear + 1){
            System.out.println("full");
        }
        if (front == -1){
            front = 0;
            rear = 0;
        }
        else if (front == 0){
            front = capacity - 1;
        }
        else {
            front--;
        }
        array[front] = value;
    }

    public int printFrontValue()
    {
        // check whether Deque is empty or not
        if (front == -1)
        {
            System.out.println(" Underflow");
            return -1 ;
        }
        return array[front];
    }

    public void printDeQueue(){
        for (int i = 0; i < capacity; i++){
            System.out.print(array[i] + " ");
        }
    }
}
