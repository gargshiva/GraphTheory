package com.learn.graphtheory.undirected;

import java.util.*;

/**
 * Dijkstra Algorithm is to find the shortest distance and path from given node.
 * <p>
 * Drawback :  Dijkstra algorithm may or may not work , if weights are negative;
 * Consider Bellman-Ford algorithm
 */
public class    DijkstraAlgorithm {

    public static void main(String[] args) {
        UndirectedGraph undirectedGraph = new UndirectedGraph();

        undirectedGraph.addEdge("0", "1", 4);
        undirectedGraph.addEdge("0", "4", 8);
        undirectedGraph.addEdge("1", "2", 8);
        undirectedGraph.addEdge("1", "4", 11);
        undirectedGraph.addEdge("2", "3", 7);
        undirectedGraph.addEdge("2", "6", 4);
        undirectedGraph.addEdge("3", "7", 9);
        undirectedGraph.addEdge("3", "6", 14);
        undirectedGraph.addEdge("4", "8", 7);
        undirectedGraph.addEdge("4", "5", 1);
        undirectedGraph.addEdge("5", "8", 6);
        undirectedGraph.addEdge("5", "6", 2);
        undirectedGraph.addEdge("6", "7", 10);
        undirectedGraph.addEdge("8", "2", 2);


        undirectedGraph.printUndirectedGraph();

        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm();
        dijkstraAlgorithm.shortestDistance(undirectedGraph, "0");
    }

    public void shortestDistance(UndirectedGraph graph, String sourceNode) {
        // Update the vertices with max distance
        Map<String, Integer> distanceMap = new HashMap<String, Integer>();

        // Visited vertices
        Map<String, Boolean> visitedVertices = new HashMap<String, Boolean>();

        Map<String, String> prevNode = new HashMap<String, String>();


        Set<String> vertices = graph.getVertex();
        for (String vertex : vertices) {
            distanceMap.put(vertex, Integer.MAX_VALUE);
            visitedVertices.put(vertex, false);
        }

        // Update the source node with  distance 0
        distanceMap.put(sourceNode, 0);

        // Select the unvisited Node with shortest Distance.
        String currentNode = sourceNode;

        // Till all the vertices are not visited;
        while (visitedVertices.values().contains(false)) {

            int currentNodeDist = distanceMap.get(currentNode);
            // get all the outgoing edges of the current node;
            List<AdjVertex> edges = graph.getEdges(currentNode);

            //update the shortest distance of unvisited nodes;
            for (AdjVertex edge : edges) {
                if (!visitedVertices.get(edge.vertex)) {
                    int dist = currentNodeDist + edge.weight;
                    if (distanceMap.get(edge.vertex) > dist) {
                        distanceMap.put(edge.vertex, dist);
                        prevNode.put(edge.vertex, currentNode);
                    }
                }
            }

            visitedVertices.put(currentNode, true);

            int shortestDist = Integer.MAX_VALUE;

            // Find the shortest unvisited node
            // Can we use min heap ??
            for (Map.Entry<String, Integer> entrySet : distanceMap.entrySet()) {
                if (!visitedVertices.get(entrySet.getKey())) {
                    int dist = entrySet.getValue();
                    if (shortestDist > dist) {
                        shortestDist = dist;
                        currentNode = entrySet.getKey();
                    }
                }
            }
        }

        for (String vertex : vertices) {
            System.out.println("Minimum distance from 0 to " + vertex + " = " + distanceMap.get(vertex));
            List<String> path = new ArrayList<String>();
            path.add(vertex);
            String node = vertex;
            while (!node.equals("0")) {
                node = prevNode.get(node);
                path.add(node);
            }
            Collections.reverse(path);
            System.out.println("Minimum Path : " + path);
        }
    }
}
