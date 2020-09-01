package com.learn.binarytree.algorithms;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeImpl {

    private TreeNode rootNode;

    public BinaryTreeImpl(TreeNode rootNode) {
        this.rootNode = rootNode;
    }

    public void addNode(TreeNode node) {
        Queue<TreeNode> stagingQueue = new LinkedList<TreeNode>();
        stagingQueue.add(rootNode);
        boolean isAdded = false;

        while (!isAdded) {
            TreeNode currentNode = stagingQueue.poll();
            if (currentNode.left == null) {
                currentNode.left = node;
                isAdded = true;
            } else if (currentNode.right == null) {
                currentNode.right = node;
                isAdded = true;
            } else {
                stagingQueue.add(currentNode.left);
                stagingQueue.add(currentNode.right);
            }
        }
    }

    // InOrder traversal
    public void printInOrderTraversal(TreeNode rootNode) {
        if (rootNode == null) {
            return;
        }

        printInOrderTraversal(rootNode.left);
        System.out.print(rootNode.data + " ");
        printInOrderTraversal(rootNode.right);
    }

    //PostOrder
    public void printPostOrderTraversal(TreeNode rootNode) {
        if (rootNode == null)
            return;

        printPostOrderTraversal(rootNode.left);
        printPostOrderTraversal(rootNode.right);
        System.out.print(rootNode.data + " ");
    }

    // PreOrder
    public void printPreOrderTraversal(TreeNode rootNode) {
        if (rootNode == null)
            return;

        System.out.print(rootNode.data + " ");
        printPreOrderTraversal(rootNode.left);
        printPreOrderTraversal(rootNode.right);
    }
}
