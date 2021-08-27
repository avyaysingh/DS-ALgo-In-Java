package com.company;

import java.util.*;

public class GraphGeneric<T> {
    private Map<T, List<T> > map;

    public GraphGeneric() {
        this.map = new HashMap<>();
    }

    //function to add vertex to graph
    public void addVertex(T t){
        map.put(t, new LinkedList<T>());
    }

    //function to remove edge
    public void removeEdge(T source, T destination){
        map.remove(source);
        map.remove(destination);

    }


    //function to add edge bw source to destination
    public void addEdge(T source, T destination, boolean bidirectional){
        if (!map.containsKey(source)){
            addVertex(source);
        }
        if (!map.containsKey(destination)) {
            addVertex(destination);
        }
        map.get(source).add(destination);
        if (bidirectional){
            map.get(destination).add(source);
        }
    }

    // function to count the number of vertices:
    public void getVertexCount(){
        System.out.println("Number of vertices : " + map.keySet().size());
    }

    //function to count the edges
    public void getEdgeCount(boolean bidirectional){
        int count = 0;
        for (T t : map.keySet()){
            count += map.get(t).size();
        }
        if (bidirectional){
            count /= 2;
        }
        System.out.println("The graph has " + count +" edges.");
    }

    //function to show if a vertex is present or not
    public void hasVertex(T t){
        if (map.containsKey(t)){
            System.out.println("The graph containd " + t + " vertex");
        }
        else {
            System.out.println("The graph doesn't contain " + t + " vertex");
        }
    }

    //function to show if there is edge bw source and destination
    public void hasEdge(T source, T destination){
        if (map.get(source).contains(destination)){
            System.out.println("Yup! edge is present bw " + source + " and " + destination);
        }
        else {
            System.out.println("Nah! no edge bw " + source + " and " + destination);
        }
    }
    public String printGraph(){
        StringBuilder builder = new StringBuilder();
        for (T key : map.keySet()){
            builder.append(key.toString() + " -> ");
            for (T value : map.get(key)){
                builder.append(value.toString() + " ");
            }
            builder.append("\n");
        }
        return (builder.toString());
    }

    public static void main(String[] args) {
        GraphGeneric<String> graphGeneric = new GraphGeneric<>();

        graphGeneric.addEdge("a", "b", true);
        graphGeneric.addEdge("a", "c", true);
        graphGeneric.addEdge("a", "d", true);
        graphGeneric.addEdge("b", "e", true);
        graphGeneric.addEdge("b", "f", true);
        graphGeneric.addEdge("c", "g", true);
        graphGeneric.addEdge("d", "h", true);
        graphGeneric.addEdge("e", "h", true);
        graphGeneric.addEdge("f", "h", true);
        graphGeneric.addEdge("g", "h", true);

        System.out.println("Graph:\n " + graphGeneric.printGraph());
        graphGeneric.getVertexCount();
        graphGeneric.getEdgeCount(true);
        graphGeneric.hasVertex("g");
        graphGeneric.hasEdge("e" , "h");

        graphGeneric.removeEdge("a", "b");

        System.out.println("Graph:\n " + graphGeneric.printGraph());
    }
}