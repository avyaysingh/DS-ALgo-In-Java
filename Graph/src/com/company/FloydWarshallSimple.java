package com.company;

//This is simple implementation of floyd warshall algorithm and it can't find the cycles present in the graph
// for advance implementation of floyd warshall algorithm check FloydWarshall.java class in this package


public class FloydWarshallSimple {
    final static int INF = 99999;
    final int nV = 4;

    void floydWarshall(int[][] graph) {
        int[][] matrix = new int[nV][nV];

        for (int i = 0; i < nV; i++) {
            for (int j = 0; j < nV; j++) {
                matrix[i][j] = graph[i][j];
            }
        }

        //adding vertices individually
        for (int k = 0; k < nV; k++) {
            for (int i = 0; i < nV; i++) {
                for (int j = 0; j < nV; j++) {
                    if (matrix[i][k] + matrix[k][j] < matrix[i][j]) {
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                    }
                }
            }
        }
        // printing the matrix with shortest paths
        for (int i = 0; i < nV; i++) {
            for (int j = 0; j < nV; j++) {
                if (matrix[i][j] == INF) {
                    System.out.print("INF");
                } else {
                    System.out.print(matrix[i][j] + " ");
                }
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        int[][] graph = {{0, 3, INF, 5}, {2, 0, INF, 4}, {INF, 1, 0, INF}, {INF, INF, 2, 0}};
        FloydWarshallSimple a = new FloydWarshallSimple();
        a.floydWarshall(graph);
    }
}
