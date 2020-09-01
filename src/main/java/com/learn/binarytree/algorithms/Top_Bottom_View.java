package com.learn.binarytree.algorithms;


import java.util.*;

/**
 * Print top view of binary tree
 */
public class Top_Bottom_View {

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

        System.out.print("Pre Order traversal : ");
        binaryTree.printPreOrderTraversal(rootNode);
        System.out.println();

        Top_Bottom_View view = new Top_Bottom_View();
        view.printTopAndBottomView(rootNode);

    }

    public void printTopAndBottomView(TreeNode rootNode) {
        // Staging Queue for level order traversal
        Queue<Element> queue = new LinkedList<Element>();
        queue.add(new Element(rootNode, 0));

        // HashMap for vertical distance
        Map<Integer, List<String>> verticalDistanceMap = new TreeMap<Integer, List<String>>();

        while (!queue.isEmpty()) {
            Element currentElement = queue.poll();

            if (verticalDistanceMap.containsKey(currentElement.vd)) {
                List<String> nodes = verticalDistanceMap.get(currentElement.vd);
                nodes.add(currentElement.treeNode.data);
            } else {
                List<String> nodes = new ArrayList<String>();
                nodes.add(currentElement.treeNode.data);
                verticalDistanceMap.put(currentElement.vd, nodes);
            }

            if (currentElement.treeNode.left != null) {
                queue.add(new Element(currentElement.treeNode.left, currentElement.vd - 1));
            }

            if (currentElement.treeNode.right != null) {
                queue.add(new Element(currentElement.treeNode.right, currentElement.vd + 1));
            }

        }

        System.out.println("Vertical Distance Map : ");
        for (Map.Entry<Integer, List<String>> entry : verticalDistanceMap.entrySet()) {
            System.out.println("\t" + entry);
        }

        System.out.print("Top View : ");
        for (Map.Entry<Integer, List<String>> entry : verticalDistanceMap.entrySet()) {
            List<String> nodes = entry.getValue();
            System.out.print(nodes.get(0) + " ");
        }
        System.out.println();
        System.out.print("Bottom View : ");
        for (Map.Entry<Integer, List<String>> entry : verticalDistanceMap.entrySet()) {
            List<String> nodes = entry.getValue();

            System.out.print(nodes.get(nodes.size() - 1) + " ");
        }

    }
}

class Element {
    TreeNode treeNode;
    int vd;

    public Element(TreeNode treeNode, int vd) {
        this.treeNode = treeNode;
        this.vd = vd;
    }
}