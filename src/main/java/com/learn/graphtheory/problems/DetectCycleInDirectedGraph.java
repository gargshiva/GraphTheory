package com.learn.graphtheory.problems;

import com.learn.graphtheory.representation.DirectedGraph;
import com.learn.graphtheory.search.DFSIterative;
import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.util.*;

/**
 * Maven Libraries which are dependent on each other;
 * Spark library depends on Java library.
 * Check whether there is a cyclic dependency or not ?
 * <p>
 * Example : LibraryA depends on LibraryB & LibraryB also depends on LibraryA . It may cause infinite loop.
 * <p>
 * if there is cyclic dependency return true else return false;
 */
public class DetectCycleInDirectedGraph {

    public static void main(String[] args) {
        DirectedGraph g = new DirectedGraph();
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);

        DetectCycleInDirectedGraph detectCycleInDirectedGraph = new DetectCycleInDirectedGraph();
        Map<Integer, TraversalState> state = new HashMap<Integer, TraversalState>();
        Set<Integer> vertices = g.getVertices();
        for (int vertex : vertices) {
            state.put(vertex, TraversalState.UNVISITED);
        }
        boolean res = detectCycleInDirectedGraph.isCycleExist_DFS(g, state, 0);
        System.out.println(res);
    }

    public boolean isCycleExist_DFS(DirectedGraph graph, Map<Integer, TraversalState> state, int rootNode) {
        //Staging Queue
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(rootNode);
        state.put(rootNode, TraversalState.EXPLORE);
        boolean result = false;

        while (!stack.isEmpty()) {
            int currentNode = stack.peek();
            List<Integer> childNodes = graph.getEdges(currentNode);
            if (!childNodes.isEmpty()) {
                boolean isElementAdded = false;
                for (int vertex : childNodes) {
                    TraversalState st = state.get(vertex);
                    if (st == TraversalState.EXPLORE) {
                        result = true;
                        // Empty the stack to break the outer loop;
                        stack.clear();
                        break;
                    } else if (st == TraversalState.UNVISITED) {
                        stack.push(vertex);
                        state.put(vertex, TraversalState.EXPLORE);
                        isElementAdded = true;
                        break;
                    }
                }
                if (!result && !isElementAdded) {
                    stack.pop();
                    state.put(currentNode, TraversalState.COMPLETED);
                }
            } else {
                stack.pop();
                state.put(currentNode, TraversalState.COMPLETED);
            }
        }

        return result;
    }
}

enum TraversalState {
    UNVISITED, EXPLORE, COMPLETED
}
