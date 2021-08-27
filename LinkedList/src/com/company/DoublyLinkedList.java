package com.company;

public class DoublyLinkedList {
    Node head;

    static class Node {
        private int data;
        Node prev;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public static DoublyLinkedList insertAtEnd(DoublyLinkedList list, int data){
        Node newNode = new Node(data);

        if(list.head == null){
            list.head = newNode;
            newNode.next = null;
            newNode.prev = null;
        }
        else{
            Node temp = list.head;
            while (temp.next != null){
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.next = null;
            newNode.prev = temp;
        }
        return list;
    }
    public static DoublyLinkedList insertAtPosition(DoublyLinkedList list,int position, int data){
        Node traversalNode = list.head;
        Node newNode = new Node(data);
        if (list.head == null){
            System.out.println("No element in list");
            return null;
        }
        Node temp =  null;
//        for (int i = 1; i < position; i++){
//            traversalNode = traversalNode.next;
//        }
        int count = 1;
        while (count < position-1){
            traversalNode = traversalNode.next;
            count++;
        }
        // checks if the position is last or not, if position is last then it calls the insert at end function from above.
        if (traversalNode.next == null){
            insertAtEnd(list,data);
        }
        else{
            temp = traversalNode.next;
            temp.prev = traversalNode;

            traversalNode.next = newNode;
            newNode.prev = traversalNode;
            newNode.next = temp;
            temp.prev = newNode;
        }
        return list;
    }

    public static DoublyLinkedList deleteAtPosition(DoublyLinkedList list, int position){
        //if the node to be deleted is first node
        Node a = list.head;

        if (position == 1){
            list.head = list.head.next;
        }
        for (int i = 1; i < position; i++){
            a = a.next;
        }
        //if the node is the last node
        if (a.next == null){
            a.prev.next = null;
        }
        else{
            a.prev.next = a.next;
            a.next.prev = a.prev;
        }
        return list;
    }

    public static DoublyLinkedList deleteByValue(DoublyLinkedList list, int data){
        Node temp = list.head;
        if (temp.data == data){
            list.head = list.head.next;
        }
        else{
            while (temp != null){
                if (temp.data == data){
                    if (temp.next == null){
                        temp.prev.next = null;
                    }
                    else{
                        temp.prev.next = temp.next;
                        temp.next.prev = temp.prev;
                    }
//                    break;
                }
                temp = temp.next;
            }

        }
        return list;
    }

    public static void printReverse(DoublyLinkedList list){
        Node temp = list.head;
        while (temp.next != null){
            temp = temp.next;
        }
        while (temp != list.head){
            System.out.println(temp.data + " ");
            temp = temp.prev;
        }
        System.out.println(temp.data);
    }

//    public static DoublyLinkedList reverseList(DoublyLinkedList list){
//        Node temp = list.head;
//        while (temp.next != null){
//            temp = temp.next;
//        }
//
//    }



    public static void printList(DoublyLinkedList list){
        Node traversalNode = list.head;
        while (traversalNode != null){
            System.out.println(traversalNode.data + " ");
            traversalNode = traversalNode.next;
        }
        System.out.println();
    }
}
