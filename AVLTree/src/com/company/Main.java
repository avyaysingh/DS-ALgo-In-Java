package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        AVLTree tree = new AVLTree();
        tree.root = tree.insertNode(tree.root,43);
        tree.root = tree.insertNode(tree.root,33);
        tree.root = tree.insertNode(tree.root,73);
        tree.root = tree.insertNode(tree.root,19);
        tree.printTree(tree.root, "", true);
    }
}
