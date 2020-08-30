package com.learn.graphtheory.problems;

import java.util.*;

/**
 * How to detect cycle using kahn algorithm ?
 *
 * Total nodes not equal to nodes popped out from queue
 */

/**
 * Table A -> Table B , Table C
 * Table C -> Table D,
 * Table D -> Table B
 * <p>
 * Table A depends on Table B and Table C that means Table B and C need to be created first before Table A.
 */
public class TopologicalSortKahnAlgo {

    public static void main(String[] args) {
        Map<String, List<String>> input = new HashMap<String, List<String>>();

        List<String> tableADepends = new ArrayList<String>();
        tableADepends.add("Table B");
        tableADepends.add("Table C");
        input.put("Table A", tableADepends);

        List<String> tableCDepends = new ArrayList<String>();
        tableCDepends.add("Table D");
        input.put("Table C", tableCDepends);

        List<String> tableDDepends = new ArrayList<String>();
        tableDDepends.add("Table B");
        input.put("Table D", tableDDepends);

        TopologicalSortKahnAlgo sort = new TopologicalSortKahnAlgo();
        List<String> order = sort.implementKahnAlgo(input);
        System.out.println(order);



    }

    public List<String> implementKahnAlgo(Map<String, List<String>> input) {
        Map<String, List<String>> adjList = createDAG(input);
        Map<String, Integer> inDegreeGraph = calculateInDegree(adjList);

        // Output
        List<String> topologicalOrder = new ArrayList<String>();

        // Staging Queue
        Queue<String> stagingQueue = new LinkedList<String>();


        //push all the elements in queue with indegree = 0
        for (Map.Entry<String, Integer> entry : inDegreeGraph.entrySet()) {
            if (entry.getValue() == 0) {
                stagingQueue.add(entry.getKey());
            }
        }

        while (!stagingQueue.isEmpty()) {
            String element = stagingQueue.poll();
            topologicalOrder.add(element);
            // Decremnet indegree of all edges
            // If indgree == 0 then add to queue

            List<String> edges = adjList.get(element);
            for (String edge : edges) {
                int indegree = inDegreeGraph.get(edge) - 1;
                inDegreeGraph.put(edge, indegree);
                if (indegree == 0) {
                    stagingQueue.add(edge);
                }
            }
        }

        return topologicalOrder;

    }

    /**
     * Create Adjacency List  E(u,v) : u must occur before v
     * <p>
     * B -> [A , D]
     * C -> [A]
     * D -> [C]
     * <p>
     */
    public Map<String, List<String>> createDAG(Map<String, List<String>> input) {
        Map<String, List<String>> adjList = new HashMap<String, List<String>>();

        for (Map.Entry<String, List<String>> entry : input.entrySet()) {
            if (adjList.get(entry.getKey()) == null) {
                adjList.put(entry.getKey(), new ArrayList<String>());
            }

            for (String dependent : entry.getValue()) {
                List<String> edges = adjList.get(dependent);
                if (edges != null) {
                    edges.add(entry.getKey());
                } else {
                    List<String> al = new ArrayList<String>();
                    al.add(entry.getKey());
                    adjList.put(dependent, al);
                }
            }
        }

        return adjList;
    }

    public Map<String, Integer> calculateInDegree(Map<String, List<String>> adjList) {
        Map<String, Integer> inDegreeMapping = new HashMap<String, Integer>();

        for (Map.Entry<String, List<String>> vertex : adjList.entrySet()) {
            // Key
            if (!inDegreeMapping.containsKey(vertex.getKey())) {
                inDegreeMapping.put(vertex.getKey(), 0);
            }
            // Value
            for (String edge : vertex.getValue()) {
                if (!inDegreeMapping.containsKey(edge)) {
                    inDegreeMapping.put(edge, 1);
                } else {
                    int v = inDegreeMapping.get(edge);
                    inDegreeMapping.put(edge, v + 1);
                }
            }
        }

        return inDegreeMapping;
    }


}