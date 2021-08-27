package com.company;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 1; i<10; i++){
            arrayList.add(i*100);
        }
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();

        arrayDeque.add(10);
        arrayDeque.add(20);
        arrayDeque.add(30);
        arrayDeque.add(40);
        arrayDeque.addAll(arrayList);

        System.out.println(arrayDeque);
    }
}
