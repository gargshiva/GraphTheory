package com.learn.binarytree.algorithms;

public class Left_Right_View {
    int currentLevel = 0;

    public static void main(String[] args) {
        TreeNode rootNode = new TreeNode("10", null, null);
        BinaryTreeImpl binaryTree = new BinaryTreeImpl(rootNode);
        binaryTree.addNode(new TreeNode("20", null, null));
        binaryTree.addNode(new TreeNode("30", null, null));
        binaryTree.addNode(new TreeNode("40", null, null));
        binaryTree.addNode(new TreeNode("50", null, null));
        binaryTree.addNode(new TreeNode("60", null, null));
        binaryTree.addNode(new TreeNode("70", null, null));
        binaryTree.addNode(new TreeNode("80", null, null));
        binaryTree.addNode(new TreeNode("90", null, null));
        binaryTree.addNode(new TreeNode("100", null, null));
        binaryTree.addNode(new TreeNode("110", null, null));

        Left_Right_View leftRightView = new Left_Right_View();

        System.out.print("Left View : ");
        leftRightView.printLeftView(rootNode, 0);
        System.out.println();

        leftRightView.currentLevel = 0;

        System.out.print("Right View : ");
        leftRightView.printRightView(rootNode, 0);
        System.out.println();


    }

    public void printLeftView(TreeNode rootNode, int level) {
        if (rootNode == null) {
            return;
        }

        if (level == currentLevel) {
            System.out.print(rootNode.data + " ");
            ++currentLevel;
        }
        printLeftView(rootNode.left, level + 1);
        printLeftView(rootNode.right, level + 1);

    }

    public void printRightView(TreeNode rootNode, int level) {
        if (rootNode == null) {
            return;
        }

        if (level == currentLevel) {
            System.out.print(rootNode.data + " ");
            ++currentLevel;
        }

        printRightView(rootNode.right, level + 1);
        printRightView(rootNode.left, level + 1);
    }
}
