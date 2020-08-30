package com.learn.graphtheory.search;

import com.learn.graphtheory.representation.DirectedGraph;
import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.util.*;

public class PostAndPreDFSTime {

    Map<String, EdgeClassifier> edgeClassifierMap = new HashMap<String, EdgeClassifier>();

    public static void main(String[] args) {
        DirectedGraph g = new DirectedGraph();
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);

        // disconnected graph
        //g.addEdge(4, 5);

        PostAndPreDFSTime postAndPreDFSTime = new PostAndPreDFSTime();
        Map<Integer, PreAndPostTime> prePostTime = postAndPreDFSTime.calculatePreAndPostTime(g);
        postAndPreDFSTime.edgeClassifier(g, prePostTime);
        System.out.println();
        System.out.println(prePostTime);

        System.out.println(postAndPreDFSTime.edgeClassifierMap);

    }


    public void edgeClassifier(DirectedGraph graph, Map<Integer, PreAndPostTime> preAndPostTime) {
        Map<Integer, List<Integer>> adjList = graph.getAdjList();
        for (Map.Entry<Integer, List<Integer>> node : adjList.entrySet()) {
            int parentNode = node.getKey();
            PreAndPostTime preAndPostTimeParentNode = preAndPostTime.get(parentNode);
            for (int childNode : node.getValue()) {
                PreAndPostTime preAndPostTimeChildNode = preAndPostTime.get(childNode);
                if (!edgeClassifierMap.containsKey(parentNode + "," + childNode)
                        || edgeClassifierMap.get(parentNode + "," + childNode) != EdgeClassifier.TREE_EDGE) {
                    int parentPreTime = preAndPostTimeParentNode.preTime;
                    int parentPostTime = preAndPostTimeParentNode.postTime;

                    int childPostTime = preAndPostTimeChildNode.postTime;
                    int childPreTime = preAndPostTimeChildNode.preTime;

                    if (parentPreTime < childPreTime && childPostTime < parentPostTime) {
                        edgeClassifierMap.put(parentNode + "," + childNode, EdgeClassifier.FWD_EDGE);
                    } else if (parentPreTime > childPreTime && parentPostTime < childPostTime) {
                        edgeClassifierMap.put(parentNode + "," + childNode, EdgeClassifier.BACK_EDGE);
                    } else if (parentPostTime < childPreTime || childPostTime < parentPreTime) {
                        edgeClassifierMap.put(parentNode + "," + childNode, EdgeClassifier.CROSS_EDGE);
                    }
                }
            }
        }
    }

    public Map<Integer, PreAndPostTime> calculatePreAndPostTime(DirectedGraph graph) {
        Set<Integer> vertices = graph.getVertices();

        Map<Integer, Boolean> visitedElements = new HashMap<Integer, Boolean>();
        for (int vertex : vertices) {
            visitedElements.put(vertex, false);
        }

        int counter = -1;

        Map<Integer, PreAndPostTime> preAndPostTimeMap = new HashMap<Integer, PreAndPostTime>();

        Collection<Boolean> vertexStatus = visitedElements.values();

        // Till all the nodes are not visited
        while (vertexStatus.contains(false)) {
            //select first non-visited node
            for (Map.Entry<Integer, Boolean> entry : visitedElements.entrySet()) {
                if (!entry.getValue()) {
                    visitedElements.put(entry.getKey(), true);
                    counter = implDFS(graph, visitedElements, preAndPostTimeMap, entry.getKey(), counter);
                    break;
                }
            }
        }
        return preAndPostTimeMap;
    }

    // Implement the DFS from particular vertex in the graph
    public int implDFS(
            DirectedGraph graph,
            Map<Integer, Boolean> visitedNodes,
            Map<Integer, PreAndPostTime> preAndPostTimeMap,
            int rootNode,
            int counter) {

        // Staging Stack to backtrack
        Stack<Integer> stagingStack = new Stack<Integer>();
        // Push the root node ; Mark it as visited and calculate the pre-time
        stagingStack.push(rootNode);
        visitedNodes.put(rootNode, true);
        preAndPostTimeMap.put(rootNode, new PreAndPostTime(++counter, -1));
        System.out.print(rootNode + " ");
        while (!stagingStack.isEmpty()) {
            int currentNode = stagingStack.peek();
            List<Integer> childVertices = graph.getEdges(currentNode);
            if (!childVertices.isEmpty()) {
                boolean isChildUnvisited = false;
                for (int vertex : childVertices) {
                    // Child is unvisited
                    if (!visitedNodes.get(vertex)) {
                        stagingStack.push(vertex);
                        visitedNodes.put(vertex, true);
                        preAndPostTimeMap.put(vertex, new PreAndPostTime(++counter, -1));
                        System.out.print(vertex + " ");
                        edgeClassifierMap.put(currentNode + "," + vertex, EdgeClassifier.TREE_EDGE);
                        isChildUnvisited = true;
                        break;
                    }
                }

                if (!isChildUnvisited) {
                    PreAndPostTime preAndPostTime = preAndPostTimeMap.get(currentNode);
                    preAndPostTime.setPostTime(++counter);
                    //  preAndPostTimeMap.put(rootNode, preAndPostTime);
                    stagingStack.pop();
                }
            } else {
                // Leaf Node - no vertices
                PreAndPostTime preAndPostTime = preAndPostTimeMap.get(currentNode);
                preAndPostTime.setPostTime(++counter);
                //preAndPostTimeMap.put(rootNode, preAndPostTime);

                stagingStack.pop();
            }
        }

        return counter;
    }
}

class PreAndPostTime {
    int preTime;
    int postTime;

    public PreAndPostTime(int preTime, int postTime) {
        this.preTime = preTime;
        this.postTime = postTime;
    }

    public void setPostTime(int postTime) {
        this.postTime = postTime;
    }

    public void setPreTime(int preTime) {
        this.preTime = preTime;
    }

    @Override
    public String toString() {
        return "(" + preTime + "," + postTime + ")";
    }
}

enum EdgeClassifier {
    TREE_EDGE, BACK_EDGE, FWD_EDGE, CROSS_EDGE
}