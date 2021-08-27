package com.company;

import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(10);
        treeSet.add(100);
        treeSet.add(1);
        treeSet.add(5);

        System.out.println(treeSet.contains(1));

        System.out.println(treeSet);

    }
}
