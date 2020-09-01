package com.learn.graphtheory.undirected;

import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.util.*;

/**
 * In undirected graph , find how many connected components are there ?
 * <p>
 * How many disconnected components ?
 */
public class ConnectedComponents {

    public static void main(String[] args) {
        UndirectedGraph undirectedGraph = new UndirectedGraph();
        undirectedGraph.addEdge("a", "b", 0);
        undirectedGraph.addEdge("b", "c", 0);
        undirectedGraph.addEdge("c", "d", 0);
        undirectedGraph.addEdge("d", "a", 0);

        undirectedGraph.addEdge("x", "y", 0);
        undirectedGraph.addEdge("y", "z", 0);
        undirectedGraph.addEdge("z", "x", 0);

        undirectedGraph.addEdge("k", "m", 0);

        undirectedGraph.printUndirectedGraph();

        ConnectedComponents connectedComponents = new ConnectedComponents();
        int count = connectedComponents.getConnectedComponentsCount(undirectedGraph);
        System.out.println("Total Connected components = " + count);
    }

    public int getConnectedComponentsCount(UndirectedGraph graph) {
        int count = 0;

        Map<String, Boolean> visitedVertices = new HashMap<String, Boolean>();
        Set<String> vertices = graph.getVertex();
        for (String vertex : vertices) {
            visitedVertices.put(vertex, false);
        }

        for (Map.Entry<String, Boolean> vertex : visitedVertices.entrySet()) {
            if (!vertex.getValue()) {
                ++count;
                implementDFS(graph, visitedVertices, vertex.getKey());
                System.out.println();
            }
        }

        return count;
    }

    public void implementDFS(UndirectedGraph graph, Map<String, Boolean> visitedVertices, String rootNode) {
        // Stack for backtrack
        Stack<String> st = new Stack<String>();
        st.push(rootNode);
        visitedVertices.put(rootNode, true);
        System.out.print(rootNode + " ");

        while (!st.isEmpty()) {
            String currentVertex = st.peek();
            List<AdjVertex> edges = graph.getEdges(currentVertex);
            if (!edges.isEmpty()) {
                boolean isUnvisitedEdgeFound = false;
                for (AdjVertex edge : edges) {
                    if (!visitedVertices.get(edge.vertex)) {
                        System.out.print(edge.vertex + " ");
                        st.push(edge.vertex);
                        visitedVertices.put(edge.vertex, true);
                        isUnvisitedEdgeFound = true;
                        break;
                    }
                }

                if (!isUnvisitedEdgeFound) {
                    // This node has no unvisited edge
                    st.pop();
                }
            } else {
                // Leaf Node
                st.pop();
            }
        }

    }
}
