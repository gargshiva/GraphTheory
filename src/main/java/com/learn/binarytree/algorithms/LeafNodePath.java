package com.learn.binarytree.algorithms;

import sun.jvm.hotspot.opto.RootNode;

import java.util.*;

public class LeafNodePath {

    List<String> path = new ArrayList<String>();

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

        originalTree.printPreOrderTraversal(originalRootNode);
        System.out.println();

        LeafNodePath leafNodePath = new LeafNodePath();
        leafNodePath.printPath(originalRootNode, "70");
        System.out.println(leafNodePath.path);


        leafNodePath.findPath(originalRootNode, "70");
    }

    public boolean printPath(TreeNode rootNode, String node) {
        if (rootNode == null) {
            return false;
        }

        if (rootNode.data.equals(node)) {
            path.add(node);
            return true;
        }

        boolean isFoundInLeftTree = printPath(rootNode.left, node);
        if (isFoundInLeftTree) {
            path.add(rootNode.data);
            return true;
        } else {
            boolean isFoundInRightTree = printPath(rootNode.right, node);
            if (isFoundInRightTree) {
                path.add(rootNode.data);
                return true;
            }
            return false;
        }
    }


    public void findPath(TreeNode rootNode, String node) {
        Stack<TreeNode> stagingStack = new Stack<TreeNode>();
        Map<String, Boolean> visitedNodes = new HashMap<String, Boolean>();

        stagingStack.push(rootNode);
        visitedNodes.put(rootNode.data, true);
        //System.out.print(rootNode.data + " ");

        while (!stagingStack.isEmpty()) {
            TreeNode current = stagingStack.peek();
            if (current.data.equals(node)) {
                while (!stagingStack.isEmpty()) {
                    System.out.print(stagingStack.pop().data + " ");
                }
            } else if (current.left != null && visitedNodes.get(current.left.data) == null) {
                //System.out.print(current.left.data + " ");
                stagingStack.push(current.left);
                visitedNodes.put(current.left.data, true);
            } else if (current.right != null && visitedNodes.get(current.right.data) == null) {
               // System.out.print(current.right.data + " ");
                stagingStack.push(current.right);
                visitedNodes.put(current.right.data, true);
            } else if (current.left == null && current.right == null) {
                stagingStack.pop();
            } else {
                stagingStack.pop();
            }

        }

    }
}
