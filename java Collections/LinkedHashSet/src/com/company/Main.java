package com.company;

import java.util.LinkedHashSet;

public class Main {

    public static void main(String[] args) {
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();

        for (int i = 1; i<=10; i++){
            linkedHashSet.add(i*1000);
        }
        Integer[] array = new Integer[linkedHashSet.size()];
        array = linkedHashSet.toArray(array);
        for (int i:
             array) {
            System.out.println(i);
        }
    }
}
