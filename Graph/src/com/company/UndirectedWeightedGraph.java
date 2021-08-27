package com.company;

import java.util.*;


public class UndirectedWeightedGraph {
    private int vertices;
    private LinkedList<Edge>[] edgeLinkedList;
    ArrayList<Edge> allEdges;

    private class Edge {
        private int source;
        private int destination;
        private double weight;

        public Edge(int source, int destination, double weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    public UndirectedWeightedGraph(int vertices) {
        this.vertices = vertices;
        edgeLinkedList = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++) {
            edgeLinkedList[i] = new LinkedList<>();
        }
        allEdges = new ArrayList<>();
    }

//    public void addEdge(Edge e){
//        int source = e.either();
//        int destination = e.other(source);
//        edgeLinkedList[source].add(e);
//        edgeLinkedList[destination].add(e);
//    }

    public void addEdge(int source, int destination, double weight) {
        Edge edge = new Edge(source, destination, weight);
        edgeLinkedList[source].add(edge);
//        // the graph is undirected
        edgeLinkedList[destination].add(new Edge(destination, source, weight));
//
        // for kruskals minimum spanning tree algorithm
//        allEdges.add(edge);
    }

    public void printGraph() {
        for (int i = 0; i < vertices; i++) {
            LinkedList<Edge> linkedList = edgeLinkedList[i];
            for (int j = 0; j < linkedList.size(); j++) {
                System.out.println(i + " -> " + linkedList.get(j).destination + " & weight -> " + linkedList.get(j).weight);
            }
        }
    }

    private List<Edge> outEdges(int i) {
        return edgeLinkedList[i];
    }

    //*************************************** Minimum spanning tree algorithms ****************************************//
    //Prim's algorithm


    //Helper function ............->>>>>>>>>>>  Not working properly .....................................//

    private void visit(boolean marked[], ArrayList<Edge> mst, PriorityQueue<Edge> pq, int v) {
        marked[v] = true;
        for (Edge e : edgeLinkedList[v]) {
            if (!marked[e.destination]) {
                pq.add(e);
            }
        }
    }

    public void PrimsMST() {
        ArrayList<Edge> mst = new ArrayList<>();
        boolean[] marked = new boolean[vertices];
        Arrays.fill(marked, false);
        PriorityQueue<Edge> pq = new PriorityQueue<>((Object o1, Object o2) -> {
            Edge first = (Edge) o1;
            Edge second = (Edge) o2;
            if (first.weight < second.weight) {
                return -1;
            } else if (first.weight > second.weight) {
                return 1;
            } else {
                return 0;
            }
        });

        // xxxxxxxxxxxxxxxxx Recursion method is finally working properly xxxxxxxxxxxxxxxxxxx//

        visit(marked, mst, pq, 0);
        while (!pq.isEmpty()) {
            Edge e = pq.remove();
            if (marked[e.source] && marked[e.destination]) {
                continue;
            }
            mst.add(e);
            if (!marked[e.source]) {
                visit(marked, mst, pq, e.source);
            }
            if (!marked[e.destination]) {
                visit(marked, mst, pq, e.destination);
            }
        }
        for (Edge e : mst) {
            System.out.println(e.source + " -> " + e.destination + " & weight->" + e.weight);
        }

        //*********** using loops without the use of recursion **********************//
//        for (Edge e : outEdges(0)) {
//            pq.add(e);
//        }
//        marked[0] = true;
//        while (!pq.isEmpty()) {
//            Edge e = pq.remove();
//            if (marked[e.source] && marked[e.destination]) {
//                continue;
//            }
//            marked[e.source] = true;
//            for (Edge edge : outEdges(e.destination)) {
//                if (!marked[edge.destination]) {
//                    pq.add(edge);
//                }
//            }
//            marked[e.destination] = true;
//            mst.add(e);
//        }
//

//        for (Edge e : mst) {
//            System.out.println(e.source + " -> " + e.destination + " & weight->" + e.weight);
//        }
    }


    // Kruskal's Minimum spanning tree algorithm
    // helper functions of union find(disjoint set for kruskals algorithm)
//    private void makeSet(int[] parent) {
//        for (int i = 0; i < vertices; i++) {
//            parent[i] = i;
//        }
//    }
//
//    private int find(int[] parent, int vertex) {
//        if (parent[vertex] != vertex) {
//            return find(parent, parent[vertex]);
//        }
//        return vertex;
//    }
//
//    public void union(int[] parent, int p, int q) {
//        int i = find(parent, p);
//        int j = find(parent, q);
//
//        parent[j] = i;
//    }
//
//    public ArrayList<Edge> kruskalMST() {
//        PriorityQueue<Edge> pq = new PriorityQueue<>((Object o1, Object o2) -> {
//            Edge first = (Edge) o1;
//            Edge second = (Edge) o2;
//            if (first.weight < second.weight) {
//                return -1;
//            } else if (first.weight > second.weight) {
//                return 1;
//            } else {
//                return 0;
//            }
//        });
////        PriorityQueue<Edge> pq = new PriorityQueue<Edge>(allEdges.size(), Comparator.comparingInt(o -> (int) o.weight));
//        for (int i = 0; i < allEdges.size(); i++) {
//            pq.add(allEdges.get(i));
//        }
//        int[] parent = new int[vertices];
//        makeSet(parent);
//        int index = 0;
//        while (index < vertices - 1) {
//            Edge edge = pq.remove();
//            int x_set = find(parent, edge.source);
//            int y_set = find(parent, edge.destination);
//
//            if (x_set == y_set) {
//                continue;
//            } else {
//                mst.add(edge);
//                index++;
//                union(parent, x_set, y_set);
//            }
//        }
//        return mst;
//    }
// **********************************my implementation of kruskals algorithm*************************//
    private void makeSet(int[] parent) {
        for (int i = 0; i < vertices; i++) {
            parent[i] = i;
        }
    }

    private int find(int[] parent, int p) {
        return parent[p];
    }

    private void union(int[] parent, int p, int q) {
        int pID = find(parent, p);
        int qID = find(parent, q);
        if (pID == qID) {
            return;
        }
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == pID) {
                parent[i] = qID;
            }
        }
    }

    private boolean connected(int[] parent, int p, int q) {
        return find(parent, p) == find(parent, q);
    }

    public void kruskalsMST() {
        ArrayList<Edge> mst = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>((Edge first, Edge second) -> {
            if (first.weight < second.weight) {
                return -1;
            } else if (first.weight > second.weight) {
                return 1;
            } else {
                return 0;
            }
        });
        for (int i = 0; i < allEdges.size(); i++) {
            pq.add(allEdges.get(i));
        }
        int[] parent = new int[vertices];
        makeSet(parent);
        while (!pq.isEmpty() && mst.size() < vertices - 1) {
            Edge e = pq.remove();
            int v = e.source;
            int w = e.destination;
            if (connected(parent, v, w)) {
                continue;
            }
            union(parent, v, w);
            mst.add(e);
        }

        for (Edge e : mst) {
            System.out.println(e.source + " -> " + e.destination + " & weight->" + e.weight);
        }
    }

    public static void main(String[] args) {
        UndirectedWeightedGraph graph = new UndirectedWeightedGraph(7);
//        Edge edge = new Edge(1,2,3);
//        graph.addEdge(edge);
//        graph.addEdge(1,2,3);
//        graph.addEdge(1,3,8);
//        graph.addEdge(1,7,7);
//        graph.addEdge(2,4,4);
//        graph.addEdge(2,5,6);
//        graph.addEdge(3,6,9);
//        graph.addEdge(4,5,5);

//        graph.printGraph();

        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 8);
        graph.addEdge(1, 2, 9);
        graph.addEdge(1, 3, 8);
        graph.addEdge(1, 4, 10);
        graph.addEdge(2, 3, 2);
        graph.addEdge(2, 5, 1);
        graph.addEdge(3, 4, 7);
        graph.addEdge(3, 5, 9);
        graph.addEdge(4, 5, 5);
        graph.addEdge(  4, 6, 6);
        graph.addEdge(5, 6, 2);

//        graph.addEdge(0, 7, 0.16);
//        graph.addEdge(2, 3, 0.17);
//        graph.addEdge(1, 7, 0.19);
//        graph.addEdge(0, 2, 0.26);
//        graph.addEdge(5, 7, 0.28);
//        graph.addEdge(1, 3, 0.29);
//        graph.addEdge(1, 5, 0.32);
//        graph.addEdge(2, 7, 0.34);
//        graph.addEdge(4, 5, 0.35);
//        graph.addEdge(1, 2, 0.36);
//        graph.addEdge(4, 7, 0.37);
//        graph.addEdge(0, 4, 0.38);
//        graph.addEdge(6, 2, 0.40);
//        graph.addEdge(3, 6, 0.52);
//        graph.addEdge(6, 0, 0.58);
//        graph.addEdge(6, 4, 0.93);


        graph.PrimsMST();
//        graph.printPrimsMST();
//        graph.kruskalsMST();

    }
}
