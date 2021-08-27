package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        System.out.println("Initial size of arrayList : " + arrayList.size());
        for(int i = 1; i<=5; i++){
            arrayList.add(i*10);
        }
        // inserting elements at particular index.
        arrayList.add(0,60);
        System.out.println("after adding size : " + arrayList.size());
        //display the arrayList
        System.out.println("Contents of arrayList : " + arrayList);

        //removing array from the arrayList index
        arrayList.remove(0);
//        //1.removing all the occurances from arraylist (not using the index)
//        arrayList.removeAll(Arrays.asList(10));
//        //2.removing first occurance from arrayList :
//        arrayList.remove(Integer.valueOf(50));
        System.out.println("arraylist after removing an element : " + arrayList);


        //obtaining an array from an arrayList:
        Integer[] arr = new Integer[arrayList.size()];
        arr = arrayList.toArray(arr);

        System.out.print("after obtaining array from arrayList elements are : [ ");
        for (int i:
             arr) {
            System.out.print(i + " ");
        }
        System.out.print("]");
    }
}
