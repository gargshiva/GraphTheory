package com.learn.graphtheory.search;

import com.learn.graphtheory.representation.DirectedGraph;

import java.util.*;

/**
 * Breadth First Search
 * - Disconnected graphs
 * - Directed graph :
 * There is no root node , select any node as random node. What if you won't able to iterate all the elements in single pass ? Example select leaf node as rootnode.
 */
public class BFS {
    Set<Integer> visitedElements = new HashSet<Integer>();

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

        BFS search = new BFS();
        search.performBreadthFirstSearch(g);

    }

    /**
     * BFS - Level order traversal
     * Make use of Queue
     * Consider any node as the root node if not specified;
     */
    public void performBreadthFirstSearch(DirectedGraph graph) {
        // Get all the vertices and marked as false
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
                    performBFSWithNode(graph, entry.getKey(), visitedElements);
                    break;
                }
            }
        }

    }

    public void performBFSWithNode(DirectedGraph graph, int rootNode, Map<Integer, Boolean> visitedElements) {
        // Staging Queue
        Queue<Integer> stagingQueue = new LinkedList<Integer>();
        stagingQueue.add(rootNode);

        while (!stagingQueue.isEmpty()) {
            int element = stagingQueue.poll();
            System.out.print(element + " ");
            List<Integer> childs = graph.getEdges(element);
            for (Integer child : childs) {
                if (!visitedElements.get(child)) {
                    stagingQueue.add(child);
                    visitedElements.put(child, true);
                }
            }
        }
    }

     /* public void implementBFSRecursion(DirectedGraph graph, int rootNode) {
        // Root is already visited , check the child vertex
        // Perform operation only on unvisited nodes.
        List<Integer> edges = graph.getEdges(rootNode);
        List<Integer> unvisitedEdges = new ArrayList<Integer>();

        *//**
     * First print all the unvisited nodes at level +1
     * Then call recursive for each unvisited node;
     *//*

        // print all the child vertex at level_rootNode + 1
        for (Integer e : edges) {
            if (!visitedElements.contains(e)) {
                unvisitedEdges.add(e);
                System.out.print(e + " ");
                visitedElements.add(e);
            }
        }

        // Call recursive for unvisited vertex
        for (int edge : unvisitedEdges) {
            implementBFSRecursion(graph, edge);
        }
    }*/

}
