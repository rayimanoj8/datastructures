package Trees.BinaryTrees;
/* 
 * This Is Simple Implementation of 
 * AVL 🌲(Adelskon-Velskey-landis)
 */
public class AVLTree extends BinarySearchTree {
    public Node root;

    public void insert(int data) {
        root = insert(root, data);
    }
    
    Node insert(Node node, int data) {
        node = super.insert(node, data);
        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);
        /* 
        1. X > Y AND Y > Z 
            X                                   Y
           /                                   / \
          Y       --> RIGHT ROTATE AT X-->    Z   X
         /
        Z   
         */
        if (balance > 1 && data < node.left.data)
            return rightRotate(node);
        /*
        2. X > Y AND Y > Z
         X                         Y
          \                       / \
           Y    --> INTO -->     X   Z
            \
             Z
         */
        if (balance < -1 && data > node.right.data)
            return leftRotate(node);
        /*
        3. X>Y AND Y<Z
            X                                X                                 Z 
           /                                /                                 / \ 
          Y     --> LEFT ROTATE AT Y -->   Z    ---> RIGHT ROTATE AT X -->   Y   X
           \                              / 
            Z                            Y   
         */
        if (balance > 1 && data > node.left.data) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        /*
        4. X<Y AND Y>Z
            X                               X                                 Z
             \                               \                               / \
              Y --> RIGHT ROTATE AT Y -->     Z  --> LEFT ROATATE AT X -->  X   Y
             /                                 \         
            Z                                   Y   
         */
        if (balance < -1 && data < node.right.data) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
/*
 * ACCORDING TO OUR CODE DATA IS AT NODE Z AND
 * X.LEFT OR X.RIGHT IS Y
 */
        return node;
    }
    
    public void delete(int data) {
        root = delete(root, data);
    }

    Node delete(Node node, int data) {
        node = super.delete(node, data);
        if (node == null)
            return node;
        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);
        if (balance > 1 && getBalance(node.left) >= 0)
            return rightRotate(node);
        if (balance < -1 && getBalance(node.right) <= 0)
            return leftRotate(node);
        if (balance > 1 && getBalance(node.left) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && getBalance(node.right) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    public Node successor(Node curr) {
        while (curr.left != null)
            curr = curr.left;
        return curr;
    }

    private Node leftRotate(Node node) {
        Node x = node.right;
        Node t2 = x.left;
        x.left = node;
        node.right = t2;
        node.height = 1 + Math.max(height(node.left), height(node.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return x;
    }

    private Node rightRotate(Node node) {
        Node x = node.left;
        Node t2 = x.right;
        x.right = node;
        node.left = t2;
        node.height = 1 + Math.max(height(node.left), height(node.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return x;
    }

    private int getBalance(Node node) {
        if (node != null) {
            return height(node.left) - height(node.right);
        }
        return 0;
    }

    private int height(Node curr) {
        if (curr != null)
            return curr.height;
        return 0;
    }

    public void inOrder() {
        inOrder(root);
    }

    void inOrder(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            inOrder(node.left);
            inOrder(node.right);
        }
    }
}