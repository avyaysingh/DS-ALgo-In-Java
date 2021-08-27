package com.company;
class Node{
    int key;
    int height;
    Node left;
    Node right;

    public Node(int key) {
        this.key = key;
        this.height = 1;
    }
}
public class AVLTree {
    Node root;

    private int height(Node node){
        if (node == null){
            return 0;
        }
        return node.height;
    }
    private int max(int a, int b){
        return (a > b) ? a : b;
    }

    private Node rightRotate(Node node){
        Node x = node.left;
        Node y = x.right;
        x.right = node;
        node.left = y;
        node.height = max(height(node.left), height(node.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;
        return x;
    }

    private Node leftRotate(Node node){
        Node y = node.right;
        Node x = y.left;
        y.left = node;
        node.right = x;
        node.height = max(height(node.left), height(node.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
        return y;
    }

    // Getting balance factor of node
    private int getBalanceFacor(Node node){
        if (node == null){
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    //inserting a node
    public Node insertNode(Node node, int key){
        //find the position and insert the node
        if (node == null){
            return (new Node(key));
        }
        if (key < node.key){
            node.left = insertNode(node.left, key);
        }
        else if (key > node.key){
            node.right = insertNode(node.right, key);
        }
        else {
            return node;
        }
        //updating the balance factor and balancing the tree
        node.height = 1 + max(height(node.left), height(node.right));
        int balanceFactor = getBalanceFacor(node);
        if (balanceFactor > 1){
            if (key < node.left.key){
                return rightRotate(node);
            }
            else if (key > node.left.key){
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }
        if(balanceFactor < -1){
            if (key > node.right.key){
                return leftRotate(node);
            }
            else if (key < node.right.key){
                node.left = rightRotate(node.left);
//                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }
        return node;
    }

    private Node nodeWithMinimumValue(Node node){
        Node current = node;
        while (current != null){
            current = current.left;
        }
        return current;
    }

    //Deleting a node:::
    public Node deleteNode(Node root, int key){

        //find the node to be deleted and remove it
        if (root == null){
            return root;
        }
        if (key < root.key){
            root.left = deleteNode(root.left, key);
        }
        else if (key > root.key){
            root.right = deleteNode(root.right, key);
        }
        else {
            if ((root.left == null) || (root.right == null)){
                Node temp = null;
                if (temp == root.left){
                    temp = root.right;
                }
                else {
                    temp = root.left;
                }
                if (temp  == null){
                    temp = root;
                    root = null;
                }
                else {
                    root = temp;
                }
            }
            else {
                Node temp = nodeWithMinimumValue(root.right);
                root.key = temp.key;
                root.right = deleteNode(root.right, temp.key);
            }
        }
        if (root == null){
            return root;
        }
        //updating the balance factor of each node and balaning the tree
        root.height = max(height(root.left), height(root.right)) + 1;
        int balanceFactor = getBalanceFacor(root);
        if (balanceFactor > 1){
            if (getBalanceFacor(root.left) >= 0){
                return rightRotate(root);
            }
            else {
                root.left = leftRotate(root.left);
                return rightRotate(root);
            }
        }
        if (balanceFactor < -1){
            if (getBalanceFacor(root.right) <= 0){
                return leftRotate(root);
            }
            else {
                root.right = rightRotate(root.right);
                return leftRotate(root);
            }
        }
        return root;
    }
    private void preOrder(Node node){
        if (node != null){
            System.out.println(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }
    public void printTree(Node currPtr, String indent, boolean last) {
        if (currPtr != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }
            System.out.println(currPtr.key);
            printTree(currPtr.left, indent, false);
            printTree(currPtr.right, indent, true);
        }
    }
}
