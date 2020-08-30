package com.learn.graphtheory.problems;

import java.util.*;

public class TopologicalSortDFS {

    Stack<String> opStack = new Stack<String>();

    public static void main(String[] args) {
        Map<String, List<String>> input = new HashMap<String, List<String>>();

        List<String> tableADepends = new ArrayList<String>();
        tableADepends.add("B");
        tableADepends.add("C");
        input.put("A", tableADepends);

        List<String> tableCDepends = new ArrayList<String>();
        tableCDepends.add("D");
        input.put("C", tableCDepends);

        List<String> tableDDepends = new ArrayList<String>();
        tableDDepends.add("B");
        input.put("D", tableDDepends);

        TopologicalSortDFS topologicalSortDFS = new TopologicalSortDFS();
        List<String> order = topologicalSortDFS.getOrder(input);
        System.out.println(order);

    }


    public List<String> getOrder(Map<String, List<String>> input) {
        Map<String, List<String>> adjList = createDAG(input);

        Map<String, Boolean> visitedNodes = new HashMap<String, Boolean>();
        for (String vertex : adjList.keySet()) {
            visitedNodes.put(vertex, false);
        }

        Collection<Boolean> vertexStatus = visitedNodes.values();

        // Till all the nodes are not visited
        while (vertexStatus.contains(false)) {
            //select first non-visited node
            for (Map.Entry<String, Boolean> entry : visitedNodes.entrySet()) {
                if (!entry.getValue()) {
                    visitedNodes.put(entry.getKey(), true);
                    implementTopologicalSort(adjList, visitedNodes, entry.getKey());
                    break;
                }
            }
        }

        List<String> finalOrder = new ArrayList<String>();

        while (!opStack.isEmpty()) {
            finalOrder.add(opStack.pop());
        }

        return finalOrder;
    }

    public void implementTopologicalSort(Map<String, List<String>> adjList,
                                         Map<String, Boolean> visitedNodes,
                                         String rootNode) {
        //Output list
        List<String> order = new ArrayList<String>();

        // Stack for backtrack
        Stack<String> stack = new Stack<String>();
        stack.push(rootNode);
        visitedNodes.put(rootNode, true);

        while (!stack.isEmpty()) {
            String current = stack.peek();
            List<String> edgeList = adjList.get(current);
            if (!edgeList.isEmpty()) {
                boolean hasElementAdded = false;
                for (String edge : edgeList) {
                    if (!visitedNodes.get(edge)) {
                        stack.push(edge);
                        visitedNodes.put(edge, true);
                        //   System.out.print(edge + " ");
                        hasElementAdded = true;
                        break;
                    }
                }
                if (!hasElementAdded) {
                    opStack.push(stack.pop());
                }
            } else {
                opStack.push(stack.pop());
            }
        }
    }

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
}
