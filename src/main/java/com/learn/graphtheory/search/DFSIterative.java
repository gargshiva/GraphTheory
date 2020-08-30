package com.learn.graphtheory.search;

import com.learn.graphtheory.representation.DirectedGraph;

import java.util.*;

public class DFSIterative {

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

        DFSIterative dfsIterative = new DFSIterative();
        dfsIterative.startDFS(g);
    }

    public void startDFS(DirectedGraph graph) {
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
                    implementDFS2(graph, visitedElements, entry.getKey());
                    break;
                }
            }
        }
    }

    /**
     * DFS - Stack
     * <p>
     * - Select rootNode
     * - Push rootNode to stack
     * - Mark rootNode as visited(print)
     * - While stack is not empty :
     * - Take anyone unvisited vertices of st.peek()
     * - Push to stack
     * - Mark it visited.(print)
     * - * no child vertex or no unvisited child vertex , then pop();
     *
     * @param graph
     * @param visitedNodes
     * @param rootNode
     */
    public void implementDFS(DirectedGraph graph, Map<Integer, Boolean> visitedNodes, int rootNode) {
        Stack<Integer> stagingStack = new Stack<Integer>();

        // Push to Stack and mark the node as visited
        stagingStack.push(rootNode);
        visitedNodes.put(rootNode, true);
        System.out.print(rootNode + " ");

        while (!stagingStack.isEmpty()) {
            int currentNode = stagingStack.peek();
            // all child vertices (visited + unvisited)
            List<Integer> childVertex = graph.getEdges(currentNode);
            if (!childVertex.isEmpty()) {
                boolean isVertexAdded = false;
                for (int vertex : childVertex) {
                    if (!visitedNodes.get(vertex)) {
                        stagingStack.push(vertex);
                        visitedNodes.put(vertex, true);
                        System.out.print(vertex + " ");
                        isVertexAdded = true;
                        break;
                    }
                }
                // All childs has been visited
                if (!isVertexAdded) {
                    stagingStack.pop();
                }
            } else {
                // Leaf Node
                stagingStack.pop();
            }
        }
    }

    public void implementDFS2(DirectedGraph graph, Map<Integer, Boolean> visitedNodes, int rootNode) {
        Stack<Integer> stagingStack = new Stack<Integer>();

        // Push to Stack and mark the node as visited
        stagingStack.push(rootNode);
        visitedNodes.put(rootNode, true);

        while (!stagingStack.isEmpty()) {
            int currentNode = stagingStack.pop();
            System.out.print(currentNode + " ");
            List<Integer> childVertices = graph.getEdges(currentNode);
            for (int vertex : childVertices) {
                if (!visitedNodes.get(vertex)) {
                    stagingStack.push(vertex);
                    visitedNodes.put(vertex, true);
                }
            }
        }
    }
}
