package com.company;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println(timeConversion("12:34:00AM"));


//        int[] myArray = getInteger(5);
//        //insertAtEnd(myArray,5);
//        //insertionSort(myArray);
//        //bubbleSort(myArray);
//        //selectionSort(myArray);
//        insertAtPosition(myArray,5);
//        displayArray(myArray);

    }

    public static int[] getInteger(int number){
        int[] myArray = new int[7];
        System.out.println("Enter " + number + " integers");
        for (int i=0; i<number; i++){
            myArray[i] = scanner.nextInt();
        }
        return myArray;
    }
    public static int[] insertAtEnd(int[] array,int numberOfItemsInitallyAdded){
        int number;
        System.out.println("Enter value to insert at the end : ");
        number = scanner.nextInt();
        array[numberOfItemsInitallyAdded] = number;
        System.out.println(number + " added at the end of the array");
        return array;
    }

    // problems in the insert at postion program needs dubbing
    public static int[] insertAtPosition(int[] array, int numberOfItemsInitallyAdded){
        System.out.println("Enter position : ");
        int position = scanner.nextInt();
        System.out.println("Enter number to insert : ");
        int number = scanner.nextInt();
        for (int i=numberOfItemsInitallyAdded; i>position; i--){
            array[i+1] = array[i];
        }
        array[position-1] = number;
        return array;
    }
    public static int[] deletebyValue(int[] array){
        System.out.println("Enter initial number of items in element");
        int numberOfItemsInitallyAdded = scanner.nextInt();
        System.out.println("Enter value to delete : ");
        int value = scanner.nextInt();
        int i;
        for (i=0; i<numberOfItemsInitallyAdded; i++){
            if (array[i] == value){
                break;
            }
        }
        if (i < numberOfItemsInitallyAdded){
            numberOfItemsInitallyAdded -= 1;
            for (int j=i; j<numberOfItemsInitallyAdded; j++){
                array[j] = array[j+1];
            }
        }
        return array;
    }
    public static int[] deleteByPosition(int[] array, int position){
        for (int i=array.length; i>position-1; i--){
            array[i] = array[i+1];
        }
        return array;
    }

    public static void linearSsearch(int[] array, int numberOfItemsInitallyAdded, int searchValue){
        int i;
        for (i=0; i<numberOfItemsInitallyAdded; i++){
            if (array[i] == searchValue){
                break;
            }
        }
        System.out.println("Element " + searchValue + " found at " + i +"th position");
    }

    public static void binarySearchUsingLoopMethod(int[] array, int searchValue, int numberOfItemsInitallyAdded){
        System.out.println("Assuming that the array is sorted");
        int low = 0;
        int mid;
        int high = numberOfItemsInitallyAdded-1;
        for (int i=0; i<numberOfItemsInitallyAdded;i++){
            if (high > low){
                mid = (low + high) / 2;
                if (array[mid] == searchValue){
                    System.out.println("value " + searchValue + " found at " + array[mid] + "position");
                    break;
                }
                else if(searchValue > array[mid]){
                    low = mid + 1;
                }
                else {
                    high = mid - 1;
                }
            }
            else {
                System.out.println("Element not found");
            }
        }
    }

    public static int binarySearchRecursiveMethod(int[] array, int numberOfItemsInitallyAdded,int searchValue){
        int low = 0;
        int high = numberOfItemsInitallyAdded - 1;
        int mid = (low + high) / 2;
        if (searchValue == array[mid]){
            return mid;
        }
        else if (searchValue > array[mid]){
            return binarySearchRecursiveMethod(array, mid + 1, searchValue);
        }
        else if (searchValue < array[mid]){
            return binarySearchRecursiveMethod(array, mid - 1, searchValue);
        }
        return -1;
    }

    public static int[] selectionSort(int[] array){
        int loc;
        for (int i=0; i<array.length-1; i++){
            loc = i;
            for(int j=i+1;j<array.length; i++){
                if (array[j] < array[loc]){
                    loc = j;
                }
            }
            int temp = array[loc];
            array[loc] = array[i];
            array[i] = temp;
        }
        return array;
    }

    public static int[] bubbleSort(int[] array){
        for (int i=0; i<array.length-1; i++){
            for (int j=0; j<array.length-i-1; j++){
                if (array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
        return array;
    }

    public static int[] insertionSort(int[] array){
        for(int i=1; i<array.length; i++){
            int key = array[i];
            int j = i - 1;
            while (j>=0 && array[j] > key){
                array[j+1] = array[j];
                j = j - 1;
            }
            array[j+1] = key;
        }
        return array;
    }


    public static void displayArray(int[] array) {
        System.out.println("Array elements are : ");
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    //solution of Time conversion from hackerrank:

    public static String timeConversion(String s){
        String[] arrayOfTime = s.split(":");
        String arrayOfSeconds = arrayOfTime[2];
        String[] part = arrayOfSeconds.split("(?<=\\d)(?=\\D)");
        int hour = Integer.parseInt(arrayOfTime[0]);
        int minutes = Integer.parseInt(arrayOfTime[1]);
        int seconds = Integer.parseInt(part[0]);
        System.out.println(part[1]);
        String twentyFourHourFormat = "";

        if (part[1].equals("PM") && hour < 12){
            twentyFourHourFormat = String.format("%02d:%02d:%02d",hour+12,minutes,seconds);
        }
        if (part[1].equals("PM") && hour == 12){
            twentyFourHourFormat = String.format("%02d:%02d:%02d",hour,minutes,seconds);
        }
        if (hour == 12 && part[1].equals("AM")){
            hour -= 12;
            twentyFourHourFormat = String.format("%02d:%02d",hour,minutes,seconds);
        }
        if (part[1].equals("AM")){
            twentyFourHourFormat = String.format("%02d:%02d:%02d",hour,minutes,seconds);
        }
        return twentyFourHourFormat;
    }



}




//
//    boolean quit = false;
//        while (!quit){
//                System.out.println("press :");
//                System.out.println("0 - to quit" +
//                "1 - to create array add elemets\n" +
//                "2 - to insert element at the end\n" +
//                "3 - to insert element at position\n" +
//                "4 - to delete an element from position\n" +
//                "5 - to search using linear search\n" +
//                "6 - to search using binary search\n" +
//                "7 - to sort using Bubble sort\n" +
//                "8 - to sort using Quick sort(don't know yet)\n" +
//                "9 - to sort using selection sort\n" +
//                "10 - to sort using insertion sort\n" +
//                "11 - to show array\n");
//                System.out.println("Enter your choice : ");
//                int choice = scanner.nextInt();
//                switch (choice){
//                case 0:
//                quit = true;
//                break;
//                case 1:
//                int[] myArray = new int[20];
//                System.out.println("Enter number of elements to insert: ");
//                int number = scanner.nextInt();
//                for (int i = 0; i<number; i++){
//        myArray[i] = scanner.nextInt();
//        }
//        break;
//        case 2:
//        System.out.println("Enter number to add to the array : ");
//        }
//        }







