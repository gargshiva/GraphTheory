package com.learn.graphtheory.undirected;

import java.util.*;

// G(V,E)
public class UndirectedGraph {
    private Map<String, List<AdjVertex>> adjList;

    public UndirectedGraph() {
        adjList = new HashMap<String, List<AdjVertex>>();
    }

    // E(u,v,w)
    public void addEdge(String u, String v, int w) {
        addVertex(u);
        addVertex(v);

        // E : u -> v
        List<AdjVertex> uEdges = adjList.get(u);
        AdjVertex vEdge = new AdjVertex(v, w);
        uEdges.add(vEdge);

        // E : v -> u
        List<AdjVertex> vEdges = adjList.get(v);
        AdjVertex uEdge = new AdjVertex(u, w);
        vEdges.add(uEdge);

    }

    public void addVertex(String vertex) {
        if (adjList.get(vertex) == null) {
            adjList.put(vertex, new ArrayList<AdjVertex>());
        }
    }

    public Map<String, List<AdjVertex>> getAdjList() {
        return adjList;
    }

    public void printUndirectedGraph() {
        System.out.println("Graph representation : ");
        for (Map.Entry<String, List<AdjVertex>> entry : adjList.entrySet()) {
            System.out.println("\t" + entry);
        }
        System.out.println();
    }

    public List<AdjVertex> getEdges(String vertex) {
        return adjList.get(vertex);
    }

    public Set<String> getVertex() {
        return adjList.keySet();
    }
}

class AdjVertex {
    String vertex;
    int weight;

    public AdjVertex(String vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    public String getVertex() {
        return vertex;
    }

    public void setVertex(String vertex) {
        this.vertex = vertex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "(" + vertex + "," + weight + ")";
    }
}
