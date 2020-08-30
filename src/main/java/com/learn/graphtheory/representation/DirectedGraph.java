package com.learn.graphtheory.representation;

import java.util.*;

public class DirectedGraph {

    public static void main(String[] args) {
        DirectedGraph g = new DirectedGraph();
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        g.printGraph();

    }

    // Adjacency List
    private Map<Integer, List<Integer>> adjList;

    public DirectedGraph() {
        adjList = new HashMap<Integer, List<Integer>>();
    }


    // Add Undirected Edge
    public void addEdge(int v, int w) {
        addVertice(v);
        addVertice(w);
        List<Integer> childs = getEdges(v);
        childs.add(w);
    }

    // Add vertex
    public void addVertice(int v) {
        if (adjList.get(v) == null)
            adjList.put(v, new LinkedList<Integer>());
    }

    // Get Child
    public List<Integer> getEdges(int v) {
        if (adjList.get(v) != null)
            return adjList.get(v);
        else
            throw new RuntimeException(v + " - Vertice not present !");
    }

    public Set<Integer> getVertices() {
        return adjList.keySet();
    }

    public Map<Integer, List<Integer>> getAdjList() {
        return adjList;
    }

    public void printGraph() {
        System.out.println(adjList);
    }

}
