package com.company;


import java.util.*;

public class DirectedWeightedGraph {
    private int vertices;   //number of vertices
    private LinkedList<Edge>[] adjacencyList;

    static class Edge {
        private int source;
        private int destination;
        private double weight;

        public Edge(int source, int destination, double weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    public DirectedWeightedGraph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int source, int destination, double weight) {
        adjacencyList[source].add(new Edge(source, destination, weight));   //for directed graph
    }

    private List<Edge> outEdges(int i) {
        return adjacencyList[i];
    }

    public void printGraph() {
        for (int i = 0; i < vertices; i++) {
            LinkedList<Edge> list = adjacencyList[i];
            for (int j = 0; j < list.size(); j++) {
                System.out.println(i + " -> " + list.get(j).destination + " & Weight ->  " + list.get(j).weight);
            }
        }
    }

    // DijkastraSP implementation using priority queue

    //  ************below code not working.. tried using priority queue only .**************//


//    public void dijkstraSPT(int sourceVertex) {
//        boolean[] visited = new boolean[vertices];
//        int[] distance = new int[vertices];
//        int[] prev = new int[vertices];
//        Arrays.fill(distance, Integer.MAX_VALUE);
//        Arrays.fill(visited, false);
//        PriorityQueue<Edge> pq = new PriorityQueue<>((Edge first, Edge second) -> {
//            if (first.weight < second.weight) {
//                return -1;
//            } else if (first.weight > second.weight) {
//                return 1;
//            } else {
//                return 0;
//            }
//        });
//        distance[0] = 0;
//        for (Edge e : outEdges(0)) {
//            pq.add(e);
//        }
//        while (!pq.isEmpty()) {
//            Edge e = pq.poll();
//            visited[e.source] = true;
//            if(distance[e.source] < e.weight){
//                continue;
//            }
//            int extractedVertex = e.source;
//            for (Edge edge : outEdges(extractedVertex)) {
//                if (visited[extractedVertex]){
//                    continue;
//                }
//                int newWeight = distance[extractedVertex] + edge.weight;
//                int destination = edge.destination;
//                if (newWeight < distance[destination]){
//                    distance[destination] = newWeight;
//                    pq.add(edge);
//                }
////                if (!visited[destination]) {
////
////                    int currentWeight = distance[destination];
////                    if (newWeight < currentWeight) {
////                        distance[destination] = newWeight;
////                        pq.add(edge);
////                    }
////                }
//            }
//
//        }
//        System.out.println("Dijkstra Algorithm: (Adjacency List + Priority Queue)");
//        for (int i = 0; i < vertices; i++) {
//            System.out.println("Source Vertex: " + sourceVertex + " to vertex " + i +
//                    " distance: " + distance[i]);
//        }
//    }

    // ********************************* Shortest path in edge-weighted DAGs *****************//
    //***********  using topological sort  ****************************//
    private void topologicalSortUtil(int vertex, boolean[] visited, Stack<Integer> stack) {
        visited[vertex] = true;
        for (Edge i : adjacencyList[vertex]) {
            if (!visited[i.destination]) {
                topologicalSortUtil(i.destination, visited, stack);
            }
        }
        stack.push(vertex);
    }

    private void relax(double[] distTo, int v) {
        for (Edge e : outEdges(v)) {
            int w = e.destination;
            if (distTo[w] > distTo[v] + e.weight) {
                distTo[w] = distTo[v] + e.weight;
            }
        }
    }

    private void topologicalSort(int s) {
        boolean[] visited = new boolean[vertices];
        double[] distTo = new double[vertices];
        Arrays.fill(visited, false);
        Arrays.fill(distTo, Integer.MAX_VALUE);
        Stack<Integer> stack = new Stack<>();
        distTo[s] = 0;
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }

        while (!stack.isEmpty()) {
            relax(distTo, stack.pop());
        }

        for (int i = 0; i < vertices; i++) {
            System.out.println("Source Vertex: " + s + " to vertex " + i + " distance: " + distTo[i]);
        }
    }

    // Bellman Ford algorithm for shortest paths
    //-------------->>>>>>>>>>>>>>>>>>>>> works for both negative cycle detection and
    // finding shortest path in graph containing negative cycles

    public double[] bellmanFord(int start) {
        double[] dist = new double[vertices];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        dist[start] = 0;

        // Only in the worst case does it take v-1 iterations for the Bellmen-Ford
        // algorithm to complete. Another stopping condition is when we're unableto
        // relax and edge, tis means we've reached the optional solution early.
        //For each vertex, apply relaxation for all edges


        for (int v = 0; v < vertices - 1; v++) {
            for (List<Edge> edges : adjacencyList) {
                for (Edge edge : edges) {
                    if (dist[edge.source] + edge.weight < dist[edge.destination]) {
                        dist[edge.destination] = dist[edge.source] + edge.weight;
                    }
                }
            }
        }

        // Run algorithm a second time to detect which nodes are part of
        // a negative cycle. A negative cycle has occured if we
        // can find a bette path beyond the optimal solution.

        for (int v = 0; v < vertices - 1; v++) {
            for (Edge edge : adjacencyList[v]) {
                if (dist[edge.source] + edge.weight < dist[edge.destination]) {
                    dist[edge.destination] = Double.NEGATIVE_INFINITY;
                }
            }
        }

        return dist;
    }


    public static void main(String[] args) {
        DirectedWeightedGraph graph = new DirectedWeightedGraph(8);
//        graph.addEdge(0, 1, 4);
//        graph.addEdge(0, 2, 3);
//        graph.addEdge(1, 3, 2);
//        graph.addEdge(1, 2, 5);
//        graph.addEdge(2, 3, 7);
//        graph.addEdge(3, 4, 2);
//        graph.addEdge(4, 0, 4);
//        graph.addEdge(4, 1, 4);
//        graph.addEdge(4, 5, 6);


//        graph.printGraph();

        //dijkstra algorithm test

//        graph.addEdge(0, 1, 4);
//        graph.addEdge(0, 2, 3);
//        graph.addEdge(1, 2, 1);
//        graph.addEdge(1, 3, 2);
//        graph.addEdge(2, 3, 4);
//        graph.addEdge(3, 4, 2);
//        graph.addEdge(4, 5, 6);

//        graph.dijkstraSPT(0);

        // shortest path using topological sort testss

//        graph.addEdge(0, 1, 5);
//        graph.addEdge(0, 7, 8);
//        graph.addEdge(0, 4, 9);
//        graph.addEdge(1, 3, 15);
//        graph.addEdge(1, 7, 4);
//        graph.addEdge(1, 2, 12);
//        graph.addEdge(7, 5, 6);
//        graph.addEdge(7, 2, 7);
//        graph.addEdge(4, 7, 5);
//        graph.addEdge(4, 5, 4);
//        graph.addEdge(4, 6, 20);
//        graph.addEdge(5, 2, 1);
//        graph.addEdge(5, 6, 13);
//        graph.addEdge(2, 3, 3);
//        graph.addEdge(2, 6, 11);
//        graph.addEdge(3, 6, 9);
//
//        graph.topologicalSort(0);

        // ************************** bellmenford test **********************//
        graph.addEdge(4, 5, 0.35);
        graph.addEdge(5, 4, 0.35);
        graph.addEdge(4, 7, 0.37);
        graph.addEdge(5, 7, 0.28);
        graph.addEdge(7, 5, 0.28);
        graph.addEdge(5, 1, 0.32);
        graph.addEdge(0, 4, 0.38);
        graph.addEdge(0, 2, 0.26);
        graph.addEdge(7, 3, 0.39);
        graph.addEdge(1, 3, 0.29);
        graph.addEdge(2, 7, 0.34);
        graph.addEdge(6, 2, -1.20);
        graph.addEdge(3, 6, 0.52);
        graph.addEdge(6, 0, -1.40);
        graph.addEdge(6, 4, -1.25);

        double[] d = graph.bellmanFord(0);


        for (int i = 0; i < 8; i++) {
            System.out.printf("The cost to get from node %d to %d is %.2f\n", 0, i, d[i]);
        }


    }

}
