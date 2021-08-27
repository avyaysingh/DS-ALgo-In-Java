package com.company;

import java.util.*;

//ref:- opendatastructures
public class AdjList {
    private int n;
    private List<Integer>[] adj;
    private int[] edgeTo;
    private boolean[] visited;
    private Stack<Integer> cycle;
    private boolean[] onStack;

    public AdjList(int n) {
        this.n = n;
        this.adj = (List<Integer>[]) new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        this.edgeTo = new int[n];
        this.visited = new boolean[n];
        this.onStack = new boolean[n];
    }

    public void addEdge(int i, int j, boolean bidirectional) {
        adj[i].add(j);
        //if graph is bidirectional
        if (bidirectional) {
            adj[j].add(i);
        }
    }

    public void removeEdge(int i, int j) {
        Iterator<Integer> iterator = adj[i].iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == j) {
                iterator.remove();
                return;
            }
        }
    }

    public boolean hasEdge(int i, int j) {
        return adj[i].contains(j);
    }

    public List<Integer> outEdges(int i) {
        return adj[i];
    }

    public List<Integer> inEdges(int i) {
        List<Integer> edges = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            if (adj[j].contains(i)) {
                edges.add(j);
            }
        }
        return edges;
    }

    //Traversal algorithms
    public void breadthFirstSearch(int source) {
//        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        while (!queue.isEmpty()) {
            int i = queue.remove();
            System.out.println(i + " ");
            for (int j :
                    outEdges(i)) {
                if (!visited[j]) {
                    queue.add(j);
                    visited[j] = true;
                }

            }
        }
    }

    public void depthFirstSearch(AdjList g, int source) {
//        below variables and arrays are already initialized in the class AdjList(because  dfs is using recursion and this may cause problem)
//        boolean[] visited = new boolean[n];
//        int[] edgeTo = new int[n];
        visited[source] = true;
        System.out.println(source + " ");
        for (int i :
                outEdges(source)) {
            if (!visited[i]) {
                edgeTo[i] = source;
                depthFirstSearch(g, i);
            }
        }
    }

    //programiz solution for dfs algorithm
    public void dFS(int vertex) {
        visited[vertex] = true;
        System.out.println(vertex + " ");
//        Iterator<Integer> iterator = adj[vertex].iterator();
//        while (iterator.hasNext()){
//            int j = iterator.next();
//            if (!visited[j]){
//                dFS(j);
//            }
//        }
        for (int j :
                outEdges(vertex)) {
            if (!visited[j]) {
                dFS(j);
            }
        }
    }

    //****************************************Cycle detection section *************************************************//

    // cycle detection in undirected graph using  DFS
    private Boolean isCycleUtil(int v, Boolean[] visited, int parent) {
        visited[v] = true;
        Integer i;
        Iterator<Integer> iterator = adj[v].iterator();
        while (iterator.hasNext()) {
            i = iterator.next();
            if (!visited[i]) {
                if (isCycleUtil(i, visited, v)) {
                    return true;
                }
            } else if (i != parent) {
                return true;
            }
        }
        return false;
    }

    public Boolean isCyclic() {
        Boolean[] visited = new Boolean[n];
        for (int i = 0; i < n; i++) {
            visited[i] = false;
        }
        for (int u = 0; u < n; u++) {
            if (!visited[u]) {
                if (isCycleUtil(u, visited, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    //cycle detection in undirected graph using bfs .................   Not working
//    private Boolean isCycleUtilBFS(AdjList g, int s, int v, boolean visited[]){
//        int[] parent = new int[n];
//        Arrays.fill(parent, -1);
//        Queue<Integer> q = new LinkedList<>();
//        visited[s] = true;
//        q.add(s);
//        while (!q.isEmpty()){
//            int u = q.poll();
//            for (int i = 0; i < n; i++){
//                int x = g.get(i);
//                if (!visited[x]){
//                    visited[x] = true;
//                    q.add(x);
//                    parent[x] = u;
//                }
//                else if(parent[u] != x){
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    public Boolean isCyclicBFS(AdjList g, int v){
//        boolean[] visited = new boolean[v];
//        Arrays.fill(visited, false);
//        for (int i = 0; i < n; i++){
//            if (!visited[i] && isCycleUtilBFS(g, i, v, visited)){
//                return true;
//            }
//        }
//        return false;
//    }

    // cycle detection in directed graph using dfs

    private boolean cycleInDirectedDfsUtil(int vertex, boolean[] visited, boolean[] recStack) {
        visited[vertex] = true;
        recStack[vertex] = true;

        //recursive call to all the adjacent vertices
        for (int i = 0; i < n; i++) {
            //if not already visited
            int adjVertex = adj[vertex].get(i);
            if (!visited[adjVertex] && cycleInDirectedDfsUtil(adjVertex, visited, recStack)) {
                return true;
            } else if (recStack[adjVertex]) {
                return true;
            }
        }
        //if reached here means cycle has not found in DFS from this vertex
        //reset
        recStack[vertex] = false;
        return false;
    }

    public boolean hasCycleInDirectedGraphDfs() {
        boolean[] visited = new boolean[n];
        boolean[] recStack = new boolean[n];
        Arrays.fill(visited, false);
        Arrays.fill(recStack, false);

        //do DFS from each node
        for (int i = 0; i < n; i++) {
            if (cycleInDirectedDfsUtil(i, visited, recStack)) {
                return true;
            }
        }
        return false;
    }


    // **************************************** DFS to find connected components of a graph ***********************//
    private void dfsCCUtil(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");
        for (int i : outEdges(v)) {
            if (!visited[i]) {
                dfsCCUtil(i, visited);
            }
        }
    }

    public void connectedComponents() {
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                //printing all reachable vertices from v
                dfsCCUtil(i, visited);
                System.out.println();
            }
        }
    }

    //**************************************** Shortest path algorithms *******************************************//

    //Shortest path in Unweighted graph  ------------------->>>>>>>>>>>>>>>>         using bfs

    public int[] shortestReachBFS(AdjList graph, int start) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(start);

        //boolean[] visited = new boolean[n];
        int[] distances = new int[n];
        Arrays.fill(distances, -1);
        distances[start] = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbour : graph.outEdges(node)) {
                if (distances[neighbour] == -1) {
                    distances[neighbour] = distances[node] + 1;
                    queue.add(neighbour);
                }
            }
        }
        return distances;
    }

    //************************************ DFS vertex ordering in graph ****************************************//
    private void dfsVertexOrderingUtil(int v, boolean[] marked, Queue<Integer> pre,
                                       Queue<Integer> post, Stack<Integer> reversePost) {
        pre.add(v);
        marked[v] = true;
        for (int w : outEdges(v)) {
            if (!marked[w]) {
                dfsVertexOrderingUtil(w, marked, pre, post, reversePost);
            }
        }
        post.add(v);
        reversePost.push(v);
    }

    public void dfsOrder() {
        boolean[] marked = new boolean[n];
        Queue<Integer> pre = new LinkedList<>();
        Queue<Integer> post = new LinkedList<>();
        Stack<Integer> reversePOst = new Stack<>();
        Arrays.fill(marked, false);
        for (int i = 0; i < n; i++) {
            if (!marked[i]) {
                dfsVertexOrderingUtil(i, marked, pre, post, reversePOst);
            }
        }
        // can add print function to print the ordering of the graph in pre, post and reversePost
    }

//************************************************** Topological sorting in graph *******************************//

    private void topologicalSortUtil(int vertex, boolean[] visited, Stack<Integer> stack) {
        visited[vertex] = true;
        for (int i : outEdges(vertex)) {
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }
        stack.push(vertex);
    }

    public void topologicalSort() {
        boolean[] visited = new boolean[n];
        Arrays.fill(visited, false);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }
        while (!stack.empty()) {
            System.out.print(stack.pop() + " ");
        }
    }


    public static void main(String[] args) {
        AdjList list = new AdjList(13);
//        list.addEdge(0, 1, true);
//        list.addEdge(0, 2, true);
//        list.addEdge(0, 3, true);
//        list.addEdge(1, 2, true);
//        list.addEdge(2, 3, true);
//        list.addEdge(3, 4, true);
//        list.addEdge(2, 4, true);
//
//        System.out.println(list.outEdges(0));
//        System.out.println(list.outEdges(1));
//        System.out.println(list.outEdges(2));
//        System.out.println(list.outEdges(3));
//        System.out.println(list.inEdges(4));

//        list.addEdge(0,5, false);
//        list.addEdge(3,5, false);
//        list.addEdge(5,4, false);
//        list.addEdge(4,3, false);
//
//        System.out.println(list.hasCycleInDirectedGraphDfs());


//        //list.breadthFirstSearch(0);
////        list.depthFirstSearch(list,0);
//
//        //list.depthFirstSearch(list, 0);
//        System.out.println("----------------------------");
//
//        int[] arr = list.shortestReachBFS(list,0);
//        for (int i = 0; i < arr.length; i++){
//            System.out.print(arr[i] + " ");
//        }
//        System.out.println();
//
//        System.out.println("----------------------------");
//        list.connectedComponents();


//        System.out.println(list.isCyclic());

//        System.out.println(list.isCyclicBFS(list,5));
        //list.dFS(0);


//        System.out.println(list.hasEdge(0, 2));
//
//        list.removeEdge(0,2);
//        list.removeEdge(2,0);
//        System.out.println(list.outEdges(0));
//        System.out.println(list.outEdges(1));
//        System.out.println(list.outEdges(2));
//        System.out.println(list.outEdges(3));


        //*********************dfs vertex ordering *******************************//
        list.addEdge(0,1, false);
        list.addEdge(0,5, false);
        list.addEdge(0,6, false);
        list.addEdge(5,4, false);
        list.addEdge(2,0, false);
        list.addEdge(2,3, false);
        list.addEdge(3,5, false);
        list.addEdge(6,4, false);
        list.addEdge(6,9, false);
        list.addEdge(9,10, false);
        list.addEdge(9,11, false);
        list.addEdge(9,12, false);
        list.addEdge(11,12, false);
        list.addEdge(7,6, false);
        list.addEdge(8,7, false);
//
//        System.out.println(list.outEdges(0));
//        System.out.println(list.outEdges(5));
//        System.out.println(list.outEdges(6));
//
//        System.out.println("************************************************");
//
////        list.dfsOrder();
//
        list.topologicalSort();

    }
}
