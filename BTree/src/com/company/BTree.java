package com.company;

public class BTree {
    Node root;

    static class Node{
        int t;          //min degree of btree
        int[] keys;     //array of keys
        Node[] c;       //array of child pointers
        int n;          //current number of keys
        boolean leaf;   //true when node is leaf, otherwise false

        public Node(int t, boolean leaf) {
            this.t = t;
            this.keys = new int[2 * t - 1];
            this.c = new Node[2 * t];
            this.n = 0;
            this.leaf = leaf;
        }
    }
}
