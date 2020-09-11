package com.learn.binarytree.algorithms;

public class InvertBinaryTree {

    public static void main(String[] args) {


        TreeNode originalRootNode = new TreeNode("10", null, null);
        BinaryTreeImpl originalTree = new BinaryTreeImpl(originalRootNode);
        originalTree.addNode(new TreeNode("20", null, null));
        originalTree.addNode(new TreeNode("30", null, null));
        originalTree.addNode(new TreeNode("40", null, null));
        originalTree.addNode(new TreeNode("50", null, null));
        originalTree.addNode(new TreeNode("60", null, null));
        originalTree.addNode(new TreeNode("70", null, null));
        originalTree.addNode(new TreeNode("80", null, null));
        originalTree.addNode(new TreeNode("90", null, null));
        System.out.print("Original Binary Tree : ");
        originalTree.printPreOrderTraversal(originalRootNode);
        System.out.println();

        TreeNode invertedRootNode = new TreeNode("10", null, null);
        BinaryTreeImpl invertedBinaryTree = new BinaryTreeImpl(invertedRootNode);
        invertedBinaryTree.addNode(new TreeNode("20", null, null));
        invertedBinaryTree.addNode(new TreeNode("30", null, null));
        invertedBinaryTree.addNode(new TreeNode("40", null, null));
        invertedBinaryTree.addNode(new TreeNode("50", null, null));
        invertedBinaryTree.addNode(new TreeNode("60", null, null));
        invertedBinaryTree.addNode(new TreeNode("70", null, null));
        invertedBinaryTree.addNode(new TreeNode("80", null, null));
        invertedBinaryTree.addNode(new TreeNode("90", null, null));

        InvertBinaryTree invertBinaryTree = new InvertBinaryTree();
        invertBinaryTree.invertBinaryTree(invertedRootNode);
        System.out.print("Inverted Binary Tree : ");
        invertedBinaryTree.printPreOrderTraversal(invertedRootNode);
        System.out.println();

        boolean result = invertBinaryTree.isInvertTree(originalRootNode, invertedRootNode);
        System.out.println(result);
    }

    public void invertBinaryTree(TreeNode rootNode) {
        if (rootNode == null) {
            return;
        }

        invertBinaryTree(rootNode.left);
        invertBinaryTree(rootNode.right);
        swap(rootNode);

    }

    public void swap(TreeNode rootNode) {
        TreeNode temp = rootNode.left;
        rootNode.left = rootNode.right;
        rootNode.right = temp;
    }


    public boolean isInvertTree(TreeNode rootNode1, TreeNode rootNode2) {
        if (rootNode1 == null && rootNode2 == null) {
            return true;
        } else if (rootNode1 == null && rootNode2 != null) {
            return false;
        } else if (rootNode1 != null && rootNode2 == null) {
            return false;
        }

        boolean compareLeftSide = isInvertTree(rootNode1.left, rootNode2.right);
        boolean compareRightSide = isInvertTree(rootNode1.right, rootNode2.left);
        return compareLeftSide && compareRightSide && rootNode1.data.equals(rootNode2.data);

    }
}
