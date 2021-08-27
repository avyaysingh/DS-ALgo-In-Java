package com.company;

import java.util.LinkedList;

public class DWGraph {
    private int vertices;   //number of vertices
    private LinkedList<Edge>[] adjacencyList;

    private class Edge {
        private int source;
        private int destination;
        private int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    public DWGraph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        adjacencyList[source].addFirst(edge);
    }

    public void printGraph() {
        for (int i = 0; i < vertices; i++) {
            LinkedList<Edge> linkedList = adjacencyList[i];
            for (int j = 0; j < linkedList.size(); j++) {
                System.out.println(i + " -> " + linkedList.get(j).destination + " & Weight ->  " + linkedList.get(j).weight);
            }
        }
    }

    public static void main(String[] args) {
        DWGraph graph = new DWGraph(6);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 3, 2);
        graph.addEdge(1, 2, 5);
        graph.addEdge(2, 3, 7);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 0, 4);
        graph.addEdge(4, 1, 4);
        graph.addEdge(4, 5, 6);

        graph.printGraph();
    }
}
