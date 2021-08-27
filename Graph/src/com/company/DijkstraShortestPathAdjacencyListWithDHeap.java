package com.company;


import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class DijkstraShortestPathAdjacencyListWithDHeap {

    public class Edge {
        int to;
        double cost;

        public Edge(int to, double cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    private final int n;

    private int edgeCount;
    private double[] dist;
    private Integer[] prev;
    private List<List<Edge>> graph;


    private void createEmptyGraph() {
        graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
    }

    public DijkstraShortestPathAdjacencyListWithDHeap(int n) {
        this.n = n;
        createEmptyGraph();
    }

    private static class MinIndexedDHeap<T extends Comparable<T>> {

        //current number of elements in the heap.
        private int sz;

        //maximum number of elements in the heap
        private final int N;

        // the degree of every node in the heap
        private final int D;

        //lookup array to track the child/parent indexes of each node
        private final int[] child;
        private final int[] parent;

        //the position map (pm) maps key indexes (ki) to where the positio of that
        //key iis represented in the priority queue in the domain[0, sz]
        private final int[] pm;

        // The inverse map (im) stores the indexes of the keys in the range
        //[0, sz] which make up the priority queue. it should be noted that
        //'im' and 'pm' are inverses of each other, so: pm[im[i]] == im[pm[i]]
        private final int[] im;

        //the values associated with the keys. It is very importent to note
        //that this array is indexed by the key indexes (aks 'ki')
        private final Object[] values;

        //Initiallizes a D array heap with a maximum capacity of maxSize.
        public MinIndexedDHeap(int degree, int maxSize) {
            if (maxSize <= 0) throw new IllegalArgumentException("maxSize <= 0");

            D = max(2, degree);
            N = max(D + 1, maxSize);

            im = new int[N];
            pm = new int[N];
            child = new int[N];
            parent = new int[N];
            values = new Object[N];

            for (int i = 0; i < N; i++) {
                parent[i] = (i - 1) / D;
                child[i] = i * D + 1;
                pm[i] = im[i] = -1;
            }
        }

        public int size() {
            return sz;
        }

        public boolean isEmpty() {
            return sz == 0;
        }

        public boolean contains(int ki) {
            keyInBoundsOrThrow(ki);
            return pm[ki] != -1;
        }

        public int peekMinKeyIndex() {
            isNotEmptyOrThrow();
            return im[0];
        }

        public int pollMinKeyIndex() {
            int minki = peekMinKeyIndex();
            delete(minki);
            return minki;
        }

        @SuppressWarnings("unchecked")
        public T peekMinValue() {
            isNotEmptyOrThrow();
            return (T) values[im[0]];
        }

        public T pollMinValue() {
            T minValue = peekMinValue();
            delete(peekMinKeyIndex());
            return minValue;
        }

        public void insert(int ki, T value) {
            if (contains(ki)) throw new IllegalArgumentException("Index already exists; received: " + ki);
            valueNotNullOrThrow(value);
            pm[ki] = sz;
            im[sz] = ki;
            values[ki] = value;
            swim(sz++);
        }

        @SuppressWarnings("unchecked")
        public T valueOf(int ki) {
            keyExistsOrThrow(ki);
            return (T) values[ki];
        }

        @SuppressWarnings("unchecked")
        public T delete(int ki) {
            keyExistsOrThrow(ki);
            final int i = pm[ki];
            swap(i, --sz);
            sink(i);
            swim(i);
            T value = (T) values[ki];
            values[ki] = null;
            pm[ki] = -1;
            im[sz] = -1;
            return value;
        }

        @SuppressWarnings("unchecked")
        public T update(int ki, T value) {
            keyExistsAndValueNotNullOrThrow(ki, value);
            final int i = pm[ki];
            T oldValue = (T) values[ki];
            values[ki] = value;
            sink(i);
            swim(i);
            return oldValue;
        }

        // strictly decreases the values associated with 'ki' to 'value'
        public void decrease(int ki, T value) {
            keyExistsAndValueNotNullOrThrow(ki, value);
            if (less(value, values[ki])) {
                values[ki] = value;
                swim(pm[ki]);
            }
        }

        //strictly increases the value associaed with 'ki' to 'value'
        public void increase(int ki, T value) {
            keyExistsAndValueNotNullOrThrow(ki, value);
            if (less(values[ki], value)) {
                values[ki] = value;
                sink(pm[ki]);
            }
        }

        //Helper functions

        private void sink(int i) {
            for (int j = minChild(i); j != -1; ) {
                swap(i, j);
                i = j;
                j = minChild(i);

            }
        }

        private void swim(int i) {
            while (less(i, parent[i])) {
                swap(i, parent[i]);
                i = parent[i];
            }
        }

        //from the parent node at index i find the minimum child below it
        private int minChild(int i) {
            int index = -1;
            int from = child[i];
            int to = min(sz, from + D);
            for (int j = from; j < to; j++) {
                if (less(j, i)) {
                    index = i = j;
                }
            }
            return index;
        }

        private void swap(int i, int j) {
            pm[im[j]] = i;
            pm[im[i]] = j;
            int tmp = im[i];
            im[i] = im[j];
            im[j] = tmp;
        }

        //Tests if the value of node i < node j
        @SuppressWarnings("unchecked")
        private boolean less(int i, int j) {
            return ((Comparable<? super T>) values[im[i]]).compareTo((T) values[im[j]]) < 0;
        }

        @SuppressWarnings("unchecked")
        private boolean less(Object obj1, Object obj2) {
            return ((Comparable<? super T>) obj1).compareTo((T) obj2) < 0;
        }

        @Override
        public String toString() {
            List<Integer> lst = new ArrayList<>(sz);
            for (int i = 0; i < sz; i++) {
                lst.add(im[i]);
            }
            return lst.toString();
        }

        //Helper function to make the code more readable.

        private void isNotEmptyOrThrow() {
            if (isEmpty()) throw new NoSuchElementException("priority queue underflow");
        }

        private void keyExistsAndValueNotNullOrThrow(int ki, Object value) {
            keyExistsOrThrow(ki);
            valueNotNullOrThrow(value);
        }

        private void keyExistsOrThrow(int ki) {
            if (!contains(ki)) throw new NoSuchElementException("Index does not exist; recived: " + ki);
        }

        private void valueNotNullOrThrow(Object value) {
            if (value == null) throw new IllegalArgumentException("value can not be null");
        }

        private void keyInBoundsOrThrow(int ki) {
            if (ki < 0 || ki >= N) {
                throw new IllegalArgumentException("key index out of bounds, recieved: " + ki);
            }
        }
    }

    public void addEdge(int from, int to, int cost) {
        edgeCount++;
        graph.get(from).add(new Edge(to, cost));
    }

    public List<List<Edge>> getGraph() {
        return graph;
    }

    public double dijkstra(int start, int end) {
        int degree = edgeCount / n;
        MinIndexedDHeap<Double> ipq = new MinIndexedDHeap<>(degree, n);
        ipq.insert(start, 0.0);

        //maintain an array of the minimum distance to each node.
        dist = new double[n];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        dist[start] = 0.0;

        boolean[] visited = new boolean[n];
        prev = new Integer[n];

        while (!ipq.isEmpty()) {
            int nodeId = ipq.peekMinKeyIndex();
            visited[nodeId] = true;
            double minValue = ipq.pollMinValue();

            //we already found a better path before we got to
            //processing this node so we can ignore it.
            if (minValue > dist[nodeId]) {
                continue;
            }

            for (Edge edge : graph.get(nodeId)) {

                // we cannot get a shorter path by revisiting
                // a node we have already visited before
                if (visited[edge.to]) {
                    continue;
                }

                //relax edge by updating minumum cost if applicable.
                double newDist = dist[nodeId] + edge.cost;

                if (newDist < dist[edge.to]) {
                    prev[edge.to] = nodeId;
                    dist[edge.to] = newDist;

                    //Insert the cost of going to a node for the first time in the PQ,
                    //or try and update it to a better value by calling decrease.
                    if (!ipq.contains(edge.to)) {
                        ipq.insert(edge.to, newDist);
                    } else {
                        ipq.decrease(edge.to, newDist);
                    }
                }
            }
            //once we've processed the end node we can return early (without
            // necessarily visiting the whole graph) because we know we cannot get a
            // shorter path by routing through any other nodes since Dijkstra's is
            //greedy and there are no negative edge weights.
            if (nodeId == end) {
                return dist[end];
            }
        }
        return Double.POSITIVE_INFINITY;
    }

    //Reconstruct the shortest path(of nodes) from start to end inclusive
    // return an array of node indexes of the shortest path from 'start' to 'end'. If 'start' and
    // 'end' are not connected then an empty array is returned.
    public List<Integer> reconstructPath(int start, int end) {
        if (end < 0 || end >= n) {
            throw new IllegalArgumentException("Invalid node index");
        }
        if (start < 0 || start >= n) {
            throw new IllegalArgumentException("Invalid node index");
        }
        List<Integer> path = new ArrayList<>();
        double dist = dijkstra(start, end);
        if (dist == Double.POSITIVE_INFINITY) {
            return path;
        }
        for (Integer at = end; at != null; at = prev[at]) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        DijkstraShortestPathAdjacencyListWithDHeap graph = new DijkstraShortestPathAdjacencyListWithDHeap(8);
//        graph.addEdge(0, 1, 4);
//        graph.addEdge(0, 2, 3);
//        graph.addEdge(1, 2, 1);
//        graph.addEdge(1, 3, 2);
//        graph.addEdge(2, 3, 4);
//        graph.addEdge(3, 4, 2);
//        graph.addEdge(4, 5, 6);
        graph.addEdge(0,1,5);
        graph.addEdge(0,7,8);
        graph.addEdge(0,4,9);
        graph.addEdge(1,3,15);
        graph.addEdge(1,7,4);
        graph.addEdge(1,2,12);
        graph.addEdge(7,5,6);
        graph.addEdge(7,2,7);
        graph.addEdge(4,7,5);
        graph.addEdge(4,5,4);
        graph.addEdge(4,6,20);
        graph.addEdge(5,2,1);
        graph.addEdge(5,6,13);
        graph.addEdge(2,3,3);
        graph.addEdge(2,6,11);
        graph.addEdge(3,6,9);

        for (int i = 0; i < 8; i++) {
            System.out.println(graph.dijkstra(0, i));
        }

//        System.out.println(graph.reconstructPath(0,5));


    }
}
