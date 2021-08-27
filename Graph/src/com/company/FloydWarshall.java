package com.company;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Double.*;

public class FloydWarshall {
    private int n;                                    // number of nodes
    private boolean solved;                         // tracks whether we've solved all pairs of shortest path or not
    private double[][] dp;
    private int[][] next;                       // used to reconstruct the path

    private static final int REACHES_NEGATIVE_CYCLE = -1;              // constant to identify when we've reached -ve cycle

    public FloydWarshall(double[][] matrix) {
        this.n = matrix.length;
        this.dp = new double[n][n];
        this.next = new int[n][n];

        //copy input matrix and setup 'next' matrix for path reconstruction.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // for path reconstruction
                if (matrix[i][j] != POSITIVE_INFINITY) {
                    next[i][j] = j;
                }
                dp[i][j] = matrix[i][j];
            }
        }
    }

    /**
     * Runs Floyd-Warshall to coompute te shortest distance between every pair of nodes.
     *
     * @return the solved All Pairs Shortest Path(APSP) matrix.
     */

    public double[][] getApspMatrix() {
        solve();
        return dp;
    }

    // Executes the Floyd-Warshall algorithm.
    public void solve() {
        if (solved) return;

        //compute all pairs shortest paths.
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dp[i][k] + dp[k][j] < dp[i][j]) {
                        dp[i][j] = dp[i][k] + dp[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }

        //Identify negative cycles by propagating the value 'NEGATIVE_INFINITY'
        // To every edge that is part of or reaches into a negative cycle.

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dp[i][k] + dp[k][j] < dp[i][j]) {
                        dp[i][j] = NEGATIVE_INFINITY;
                        next[i][j] = REACHES_NEGATIVE_CYCLE;
                    }
                }
            }
        }
        solved = true;
    }

    /**
     * Reconstructs the shortest path(of nodes) from 'start' to 'end' incusive.
     * <p>
     * return An array of nodex indexes of the shortest path from 'start' to 'end'. If 'start' and
     * 'end' are not connected return an empty array. If the shortest path'form' start' to 'end'
     * are reachable by a negative cycle return -1.
     */

    public List<Integer> reconstructShortestPath(int start, int end) {
        solve();
        List<Integer> path = new ArrayList<>();
        if (dp[start][end] == POSITIVE_INFINITY) {
            return path;
        }
        int at = start;
        for (; at != end; at = next[at][end]) {
            //Return null since there are infinite number of shortest paths.
            if (at == REACHES_NEGATIVE_CYCLE) {
                return null;
            }
            path.add(at);
        }
        // Return null since there are an infinite number of shortest paths.
        if (next[at][end] == REACHES_NEGATIVE_CYCLE) {
            return null;
        }
        path.add(end);
        return path;
    }

    /*Example usage */

    //Creates a graph with n nodes. The adjacency matrix is constructed
    //such that the value of going from a node to itself is 0.

    public static double[][] createGraph(int n) {
        double[][] matrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(matrix[i], POSITIVE_INFINITY);
            matrix[i][i] = 0;
        }
        return matrix;
    }

    public static void main(String[] args) {
        int n = 5;
        double[][] m = createGraph(n);



        //add some edge values.
//        m[0][1] = 2;
//        m[0][2] = 5;
//        m[0][6] = 10;
//        m[1][2] = 2;
//        m[1][4] = 11;
//        m[2][6] = 2;
//        m[6][5] = 11;
//        m[4][5] = 1;
//        m[5][4] = -2;


        m[1][2] = 3;
        m[1][4] = 7;
        m[2][1] = 8;
        m[2][3] = 2;
        m[3][1] = 5;
        m[3][4] = 1;
        m[4][1] = 2;

        FloydWarshall floydWarshall = new FloydWarshall(m);
        double[][] dist = floydWarshall.getApspMatrix();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
//                System.out.printf("This shortest path from node %d to node %d is %.3f\n", i, j, dist[i][j]);
                System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("******************************************************************************");

        // Reconstructs the shortest paths from all nodes to every other nodes.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                List<Integer> path = floydWarshall.reconstructShortestPath(i, j);
                String str;
                if (path == null) {
                    str = "HAS AN ∞ NUMBER OF SOLUTIONS! (negative cycle case)";
                } else if (path.size() == 0) {
                    str = String.format("DOES NOT EXIST (node %d doesn't reach node %d)", i, j);
                } else {
                    str = String.join(" -> ", path.stream().map(Object::toString).collect(java.util.stream.Collectors.toList()));
                    str = "is: [" + str + "]";
                }
                System.out.printf("The shortest path from node %d to node %d %s\n", i, j, str);
            }
        }
    }
}
