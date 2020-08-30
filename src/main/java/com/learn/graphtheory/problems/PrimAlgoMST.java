package com.learn.graphtheory.problems;

import java.util.*;

/**
 * Find minimum Spanning Tree in Graph (Undirected) using Prim Algo.
 *
 * Start with a node
 * Find the edge with minimum weight. Consider the unvisited edges of all the previous nodes to find min edge.
 * Add the node-edge relation in mst.
 */
public class PrimAlgoMST {
    Map<String, List<AdjNode>> minimumSpanningTree = new HashMap<String, List<AdjNode>>();
    Map<String, List<AdjNode>> possibleEdges = new HashMap<String, List<AdjNode>>();
    Set<String> visitedEdges = new HashSet<String>();

    public static void main(String[] args) {
        Map<String, List<AdjNode>> input = new HashMap<String, List<AdjNode>>();

        List<AdjNode> aDepends = new ArrayList<AdjNode>();
        aDepends.add(new AdjNode("b", 7));
        aDepends.add(new AdjNode("c", 8));
        input.put("a", aDepends);

        List<AdjNode> BDepends = new ArrayList<AdjNode>();
        BDepends.add(new AdjNode("a", 7));
        BDepends.add(new AdjNode("c", 3));
        BDepends.add(new AdjNode("d", 6));
        input.put("b", BDepends);

        List<AdjNode> CDepends = new ArrayList<AdjNode>();
        CDepends.add(new AdjNode("a", 8));
        CDepends.add(new AdjNode("b", 3));
        CDepends.add(new AdjNode("d", 4));
        CDepends.add(new AdjNode("e", 3));
        input.put("c", CDepends);

        List<AdjNode> DDepends = new ArrayList<AdjNode>();
        DDepends.add(new AdjNode("b", 6));
        DDepends.add(new AdjNode("c", 4));
        DDepends.add(new AdjNode("e", 2));
        DDepends.add(new AdjNode("f", 5));
        input.put("d", DDepends);


        List<AdjNode> EDepends = new ArrayList<AdjNode>();
        EDepends.add(new AdjNode("d", 2));
        EDepends.add(new AdjNode("c", 3));
        EDepends.add(new AdjNode("f", 2));
        input.put("e", EDepends);

        List<AdjNode> FDepends = new ArrayList<AdjNode>();
        FDepends.add(new AdjNode("d", 5));
        FDepends.add(new AdjNode("e", 2));
        input.put("f", FDepends);

        PrimAlgoMST primAlgoMST = new PrimAlgoMST();
        primAlgoMST.primAlgo(input, "a");

        System.out.println(primAlgoMST.minimumSpanningTree);

    }

    public void primAlgo(Map<String, List<AdjNode>> graph, String rootNode) {
        int vertexCount = graph.size();
        int edges = vertexCount - 1;
        int counter = 0;
        List<String> nodesVisited = new ArrayList<String>();
        String currentNode = rootNode;
        while (counter < edges) {
            nodesVisited.add(currentNode);
            addEdges(currentNode, graph.get(currentNode));

            // Get unvisited edge with min weight ;
            Map<String, AdjNode> minAdjNode = getMinEdge(nodesVisited);
            String parent = minAdjNode.keySet().iterator().next();
            AdjNode child = minAdjNode.values().iterator().next();

            if (minimumSpanningTree.get(parent) == null) {
                List<AdjNode> mstNodeList = new ArrayList<AdjNode>();
                mstNodeList.add(child);
                minimumSpanningTree.put(parent, mstNodeList);
            } else {
                minimumSpanningTree.get(parent).add(child);
            }

            currentNode = child.node;
            ++counter;
        }
    }

    public void addEdges(String node, List<AdjNode> adjNodes) {
        possibleEdges.put(node, adjNodes);
    }

    public Map<String, AdjNode> getMinEdge(List<String> nodes) {
        AdjNode minEdgeNode = null;
        String parentNode = null;
        for (String node : nodes) {
            AdjNode adjNode = getMinEdgeForNode(node);
            System.out.println("Parent Node : " + node);
            System.out.println("Min Node : " + adjNode);
            if (adjNode != null && (minEdgeNode == null || minEdgeNode.weight > adjNode.weight)) {
                minEdgeNode = adjNode;
                parentNode = node;
            }
        }
        System.out.println("minEdgeNode " + minEdgeNode);

        visitedEdges.add(parentNode + "-" + minEdgeNode.node);
        visitedEdges.add(minEdgeNode.node + "-" + parentNode);
        System.out.println(visitedEdges);
        System.out.println();

        Map<String, AdjNode> map = new HashMap<String, AdjNode>();
        map.put(parentNode, minEdgeNode);
        return map;
    }

    // Get minimum unvisited edge for particular node
    // Edge considered to be visited : A->B and B->A
    public AdjNode getMinEdgeForNode(String node) {
        List<AdjNode> edges = possibleEdges.get(node);
        AdjNode selectedNode = null;
        for (AdjNode adjNode : edges) {
            // if not considered previously
            if (!(visitedEdges.contains(node + "-" + adjNode.node) || visitedEdges.contains(adjNode.node + "-" + node))) {
                if (selectedNode == null || adjNode.weight < selectedNode.weight) {
                    selectedNode = adjNode;
                }
            }
        }
        return selectedNode;
    }
}


class AdjNode {
    String node;
    int weight;

    public AdjNode(String node, int weight) {
        this.node = node;
        this.weight = weight;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return node + "," + weight;
    }
}