package com.learn.binarytree.algorithms;

public class LCA {

    public static void main(String[] args) {
        TreeNode rootNode = new TreeNode("10", null, null);
        BinaryTreeImpl originalTree = new BinaryTreeImpl(rootNode);
        originalTree.addNode(new TreeNode("20", null, null));
        originalTree.addNode(new TreeNode("30", null, null));
        originalTree.addNode(new TreeNode("40", null, null));
        originalTree.addNode(new TreeNode("50", null, null));
        originalTree.addNode(new TreeNode("60", null, null));
        originalTree.addNode(new TreeNode("70", null, null));
        originalTree.addNode(new TreeNode("80", null, null));
        originalTree.addNode(new TreeNode("90", null, null));


        LCA obj = new LCA();
        TreeNode res = obj.lca(rootNode, "30","90");
        System.out.println(res.data);
    }

    public TreeNode lca(TreeNode rootNode, String node1, String node2) {

        if (rootNode == null) {
            return null;
        }

        if (rootNode.data.equals(node1) || rootNode.data.equals(node2)) {
            return rootNode;
        }

        TreeNode n1 = lca(rootNode.left, node1, node2);
        TreeNode n2 = lca(rootNode.right, node1, node2);

        if (n1 != null && n2 != null) {
            return rootNode;
        } else if (n1 != null) {
            return n1;
        } else if (n2 != null) {
            return n2;
        } else
            return null;

    }
}
