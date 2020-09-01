package com.learn.graphtheory.undirected;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Find bridges/cut edges in the undirected graph
 * Assumption : Graph in connected , there are no disconnected components.
 */
public class FindBridges {

    public static void main(String[] args) {
        UndirectedGraph undirectedGraph = new UndirectedGraph();
        undirectedGraph.addEdge("a", "b", 0);
        undirectedGraph.addEdge("b", "c", 0);
        undirectedGraph.addEdge("c", "d", 0);
        undirectedGraph.addEdge("d", "a", 0);

        undirectedGraph.addEdge("d", "x", 0);

        undirectedGraph.addEdge("x", "y", 0);
        undirectedGraph.addEdge("y", "z", 0);
        undirectedGraph.addEdge("z", "x", 0);

        undirectedGraph.addEdge("z", "k", 0);

        undirectedGraph.addEdge("k", "m", 0);

        undirectedGraph.printUndirectedGraph();

        FindBridges findBridges = new FindBridges();
        findBridges.findBridgesInGraph(undirectedGraph);

    }

    public void findBridgesInGraph(UndirectedGraph graph) {
        // Staging Stack for backtrack
        Stack<String> stack = new Stack<String>();

        // Root node - Take any node as starting point.
        String rootNode = graph.getVertex().iterator().next();

        // Visited nodes
        Map<String, Boolean> visitedVertices = new HashMap<String, Boolean>();
        for (String vertex : graph.getVertex()) {
            visitedVertices.put(vertex, false);
        }
        //Time
        Map<String, Integer> vertexTime = new HashMap<String, Integer>();
        int counter = -1;

        stack.push(rootNode);
        visitedVertices.put(rootNode, true);
        vertexTime.put(rootNode, ++counter);

        while (!stack.isEmpty()) {
            String current = stack.peek();
            List<AdjVertex> edges = graph.getEdges(current);
            if (!edges.isEmpty()) {
                boolean isUnvisitedVertexFound = false;
                for (AdjVertex edge : edges) {
                    if (!visitedVertices.get(edge.vertex)) {
                        visitedVertices.put(edge.vertex, true);
                        stack.push(edge.vertex);
                        vertexTime.put(edge.vertex, ++counter);
                        isUnvisitedVertexFound = true;
                        break;
                    }
                }
                if (!isUnvisitedVertexFound) {
                    // All the edges has been visited / exhausted.
                    // Particular node has been explored fully

                    // Get vertexTime for all the edges of that particular node except from parent (backtrack node);
                    // Compare the vertex time of min edge node with parent time;
                    // if time(minEdgeNode) > time(parentNode) then it is break edge;

                    String poppedVertex = stack.pop();

                    if (!stack.isEmpty()) {
                        // Parent Edge time
                        String parentVertex = stack.peek();
                        int parentVertextime = vertexTime.get(parentVertex);

                        // Find the edge with Min time except parent ;
                        List<AdjVertex> poppedVertexEdges = graph.getEdges(poppedVertex);
                        int minTime = Integer.MAX_VALUE;
                        for (AdjVertex edge : poppedVertexEdges) {
                            if (!edge.vertex.equals(parentVertex)) {
                                int vTime = vertexTime.get(edge.vertex);
                                if (vTime < minTime) {
                                    minTime = vTime;
                                }
                            }
                        }

                        vertexTime.put(poppedVertex, minTime);

                        if (minTime > parentVertextime) {
                            System.out.println("Break Edge : " + parentVertex + "-" + poppedVertex);
                        }
                    }
                }
            } else {
                String poppedVertex = stack.pop();

                if (!stack.isEmpty()) {
                    // Parent Edge time
                    String parentVertex = stack.peek();
                    int parentVertextime = vertexTime.get(parentVertex);

                    // Find the edge with Min time except parent ;
                    List<AdjVertex> poppedVertexEdges = graph.getEdges(poppedVertex);
                    int minTime = Integer.MAX_VALUE;
                    for (AdjVertex edge : poppedVertexEdges) {
                        if (!edge.vertex.equals(parentVertex)) {
                            int vTime = vertexTime.get(parentVertex);
                            if (vTime < minTime) {
                                minTime = vTime;
                            }
                        }
                    }

                    vertexTime.put(poppedVertex, minTime);

                    if (minTime > parentVertextime) {
                        System.out.println("Break Edge : " + parentVertex + "-" + poppedVertex);
                    }
                }
            }
        }

        System.out.println(vertexTime);
    }
}
