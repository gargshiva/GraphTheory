package com.learn.graphtheory.search;

import com.learn.graphtheory.representation.DirectedGraph;

import java.util.*;

public class DFSRecursive {
    public static void main(String[] args) {
        DirectedGraph g = new DirectedGraph();
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);

        // disconnected graph
        g.addEdge(4, 5);

        g.printGraph();

        DFSRecursive dfsRecursive = new DFSRecursive();
        dfsRecursive.search(g);

    }

    /**
     * Consider disconnected graph
     * What if the root node is leaf node.
     *
     * @param graph
     */
    public void search(DirectedGraph graph) {
        Set<Integer> vertices = graph.getVertices();
        Map<Integer, Boolean> visitedElements = new HashMap<Integer, Boolean>();
        for (int vertex : vertices) {
            visitedElements.put(vertex, false);
        }

        Collection<Boolean> vertexStatus = visitedElements.values();

        // Till all the nodes are not visited
        while (vertexStatus.contains(false)) {
            //select first non-visited node
            for (Map.Entry<Integer, Boolean> entry : visitedElements.entrySet()) {
                if (!entry.getValue()) {
                    visitedElements.put(entry.getKey(), true);
                    depthFirstSearch(graph, visitedElements, entry.getKey());
                    break;
                }
            }
        }
    }

    public void depthFirstSearch(DirectedGraph graph, Map<Integer, Boolean> visitedElements, int rootNode) {
        visitedElements.put(rootNode, true);
        System.out.print(rootNode + " ");

        List<Integer> childVertices = graph.getEdges(rootNode);
        for (Integer vertex : childVertices) {
            if (!visitedElements.get(vertex)) {
                depthFirstSearch(graph, visitedElements, vertex);
            }
        }
    }
}
