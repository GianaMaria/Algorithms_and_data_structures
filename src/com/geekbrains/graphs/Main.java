package com.geekbrains.graphs;

public class Main {

    public static void main(String[] args) {
        MyGraph myGraph = new MyGraph(5);

        myGraph.addEdge(1, 2);
        myGraph.addEdge(0, 4);
        myGraph.addEdge(1, 4);
//        myGraph.addEdge(3, 4);

        System.out.println(myGraph.getAdjList(1));
//        System.out.println(myGraph.getAdjList(4));

//        DepthFirstPaths dfs = new DepthFirstPaths(myGraph, 2);
//        System.out.println(dfs.hasToPath(0));
//        System.out.println(dfs.pathTo(0));

        BreadthFirstPaths bfs = new BreadthFirstPaths(myGraph, 2);
        System.out.println(bfs.hasPathTo(0));
        System.out.println(bfs.pathTo(0));
        System.out.println(bfs.hasPathTo(3));

    }
}
