package com.company;

import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 1;i<=10; i++){
            hashSet.add(i*1000);
        }
        //to clear the hashset
        //hashSet.clear();

        //to return if the value is present in hashset or not. true if value id present.
        //System.out.println(hashSet.contains(2000));

        //to determine the size of hashset
        //System.out.println(hashSet.size());


        System.out.println(hashSet);
    }
}
