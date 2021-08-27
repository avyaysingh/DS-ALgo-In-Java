package com.company;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        SinglyLinkedList.insertAtEnd(singlyLinkedList,10);
        SinglyLinkedList.insertAtEnd(singlyLinkedList,20);
        SinglyLinkedList.insertAtEnd(singlyLinkedList,30);
        SinglyLinkedList.insertAtEnd(singlyLinkedList,40);
        SinglyLinkedList.insertAtEnd(singlyLinkedList,50);
        SinglyLinkedList.insertAtEnd(singlyLinkedList,60);

        //SinglyLinkedList.insertAtPosition(singlyLinkedList,3,300);

        //SinglyLinkedList.deleteAtPosition(singlyLinkedList,4);
        //SinglyLinkedList.deleteByValue(singlyLinkedList,60);

        //SinglyLinkedList.reversePrint(singlyLinkedList);

        //SinglyLinkedList.reverse(singlyLinkedList);

        //SinglyLinkedList.printList(singlyLinkedList);

        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        DoublyLinkedList.insertAtEnd(doublyLinkedList,10);
        DoublyLinkedList.insertAtEnd(doublyLinkedList,20);
        DoublyLinkedList.insertAtEnd(doublyLinkedList,30);
        DoublyLinkedList.insertAtEnd(doublyLinkedList,40);

        //DoublyLinkedList.insertAtPosition(doublyLinkedList,5,300);

//        DoublyLinkedList.deleteAtPosition(doublyLinkedList,4);

        //DoublyLinkedList.deleteByValue(doublyLinkedList,30);

        DoublyLinkedList.printReverse(doublyLinkedList);

        //DoublyLinkedList.printList(doublyLinkedList);
    }
}
