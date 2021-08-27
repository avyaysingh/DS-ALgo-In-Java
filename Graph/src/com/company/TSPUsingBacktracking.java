package com.company;

// ref:- gfg.......https://www.geeksforgeeks.org/travelling-salesman-problem-implementation-using-backtracking/

public class TSPUsingBacktracking {

    //Function to find the minimum weight
    // Hamiltonian Cycle
    public static int tsp(int[][] graph, boolean[] v, int currPos, int n, int count, int cost, int ans) {

        /*
        * if last node is reached and it has a link
        * to the starting node i.e the source then
        * kee the min value out of the total cost
        * of traversal and "ans"
        * Finally return to check for more possible values
         */
        if (count == n && graph[currPos][0] > 0) {
            ans = Math.min(ans, cost + graph[currPos][0]);
            return ans;
        }

        //Backtracking step
        /*
        *loop to traverse the adjacency list
        * of currPos node and increasing the count
        * by 1 and cost by graph[currPos,i] vale
         */
        for (int i = 0; i < n; i++) {
            if (!v[i] && graph[currPos][i] > 0) {
                //mark as visited
                v[i] = true;
                ans = tsp(graph, v, i, n, count + 1, cost + graph[currPos][i], ans);

                //mark ith node as unvisited
                v[i] = false;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] graph = {{0, 10, 15, 20},
                {10, 0, 35, 25},
                {15, 35, 0, 30},
                {20, 25, 30, 0}};

        boolean[] v = new boolean[n];
        v[0] = true;
        int ans = Integer.MAX_VALUE;

        //find the minimum weight hamiltonian Cycle
        ans = tsp(graph, v, 0, n, 1, 0, ans);
        System.out.println(ans);
    }
}
