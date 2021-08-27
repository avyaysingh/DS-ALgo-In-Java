package com.company;


public class SinglyLinkedList {
    Node head;

    static class Node {
        private int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    //  function to create a new node and insert at end
    public static SinglyLinkedList insertAtEnd(SinglyLinkedList list, int data){

        //creating a new node with given data
        Node newNode = new Node(data);
        newNode.next = null;

        // if list is empty
        //then make the new node as head
        if (list.head == null){
            list.head = newNode;
        }
        else{
            //traverse till the last node
            //insert new node at the end
            Node last = list.head;
            while (last.next != null){
                last = last.next;
            }
            //inserting at the end of the node
            last.next = newNode;
        }
        return list;
    }

    // function to add at a particular position
    public static SinglyLinkedList insertAtPosition(SinglyLinkedList list, int position, int data){
        Node newNode = new Node(data);
        if(position < 1){
            System.out.println("Invalid Position");
        }

        //if position is 1st then we'll have to make this node as head node
        if (position == 1){
            //Node newNode = new Node(data);
            newNode.next = list.head;
            list.head = newNode;
        }
//        else {
//            //Node temp will point to head
//            Node temp = list.head;
//            Node current = null;
//            for (int i = 1; i <= position-1; i++){
//                //Node current will point to temp
//                current = temp;
//                //Node temp will point to node next to it
//                temp = temp.next;
//
//            }
//            //here current will point to new node
//            current.next = newNode;
//            //new node will point to temp because temp is having the address to the next node
//            newNode.next = temp;
//        }
        // my HackerRank solution
        else{
            Node traversalNode = list.head;
            Node temp = null;
            for(int i = 1; i <= position - 1; i++){
                temp = traversalNode;
                traversalNode = traversalNode.next;
//                newNode.next = temp.next;
            }
            newNode.next = temp.next;
            temp.next = newNode;
        }
        return list;
    }

    public static SinglyLinkedList deleteAtPosition(SinglyLinkedList list, int position){
        Node q = list.head;
        Node t = null;

        //if linked list is empty
        if (list.head == null){
            System.out.println("List is empty");
            return null;
        }
        // if node to be deleted is first node
        else if (position == 1){
            list.head = list.head.next;
        }
        else{
            for (int i = 1; i <= position-1; i++){
                t = q;
                q = q.next;
            }
            // if exception arise because t is earlier declared as null in this method.
            if (t != null) {
                t.next = q.next;
            }
        }
        return list;
    }

    public static void deleteByValue(SinglyLinkedList list, int value){
        Node q;
        Node t;
        q = list.head;
        t = null;
        if (q == null){
            System.out.println("List is empty");
            return;
        }
        //if node to be removed is first node
        if (q.data == value){
            list.head = q.next;
            System.out.println(value + " is removed !");
            return;
        }
        q = q.next;
        while (q != null){
            if (q.data == value){
                t.next = q.next;
                System.out.println(value + " removed from linked list");
                return;
            }
            t = q;
            q = q.next;
        }
        System.out.println(value + " not found");
    }

    public static void printList(SinglyLinkedList list){
        Node currentNode = list.head;
        System.out.println("Linked List : ");
        while (currentNode != null){
            System.out.println(currentNode.data + " ");
            currentNode = currentNode.next;
        }
        System.out.println();
    }

    //hacekerrank reverse list solution
    //recursive solution : directly printing without reversing
//    static void reversePrint(Node head) {
//        if(head == null){
//            return;
//        }
//        reversePrint(head.next);
//        System.out.println(head.data + " ");
//    }

    //reversing a list then return

    public static SinglyLinkedList reverse(SinglyLinkedList list){
        Node traversalNode = list.head;
        Node firstTemp = null;
        Node secondTemp = null;

        if (list.head == null){
            return null;
        }
        while(traversalNode != null){
            firstTemp = traversalNode.next;
            traversalNode.next = secondTemp;

            secondTemp = traversalNode;
            traversalNode = firstTemp;
        }
        list.head = secondTemp;
        return list;
    }



    public static boolean compareLists(SinglyLinkedList list1, SinglyLinkedList list2) {
        Node traversalNodeOne = list1.head;
        Node traversalNodeSecond = list2.head;
        while (traversalNodeOne != null && traversalNodeSecond != null){
            if (traversalNodeOne.data != traversalNodeSecond.data){
                return false;
            }
            traversalNodeOne = traversalNodeOne.next;
            traversalNodeSecond = traversalNodeSecond.next;
        }
        return(traversalNodeOne == null && traversalNodeSecond == null);
    }

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        list.insertAtEnd(list, 10);
        list.insertAtEnd(list, 16);
        list.printList(list);
    }
}
