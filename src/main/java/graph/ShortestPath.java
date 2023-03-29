package pathfinder;

import graph.Graph;
import pathfinder.datastructures.Path;

import java.io.IOException;
import java.util.*;

/**
 * This class implements algorithm to find the shortest path in a graph by using Dijkstra algorithm.
 * @param <T> generic type
 */
public class ShortestPath<T> {

    private static final boolean DEBUG = true;

    private static <T> void checkRep(Graph<T, Double> g, Graph.Vertex<T> start, Graph.Vertex<T> end) throws IOException {
        if (g == null || start == null || end == null) {
            throw new IOException("Input has null value");
        }
        if (DEBUG) {
            Map<Graph.Vertex<T>, List<Graph.Edge<T, Double>>> adjacencyList = g.getAdjacencyList();
            for (Graph.Vertex<T> v :
                    adjacencyList.keySet()) {
                if (v == null || g.getEdgesFrom(v) == null) {
                    throw new IOException("Input has null value");
                }
                for (Graph.Edge<T, Double> e :
                        g.getEdgesFrom(v)) {
                    if (e == null || e.getLabel() == null) {
                        throw new IOException("Input has null value");
                    }
                }

            }
        }
    }

    /**
     * Find the shortest path for the given graph from the given start point to the given end point.
     * @param g the graph to run Dijkstra algorithm
     * @param start the start point for the shortest path
     * @param end the end point for the shortest path
     * @spec.requires g != null; start != null; end != null
     * @return return the shortest path from <b>start</b> to <b>end</b>.
     *          If there is no path between <b>start</b> and <b>end</b>, return null.
     * @param <T> generic type
     */
    public static <T> Path<T> dijkstra(Graph<T, Double> g, Graph.Vertex<T> start, Graph.Vertex<T> end) {
//        checkRep(g, start, end);
        PriorityQueue<Path<T>> active = new PriorityQueue<>(new Path.PathComparator<T>());
        Set<Graph.Vertex<T>> finished = new HashSet<>();
        active.add(new Path<T>(start.getName()));
        while (!active.isEmpty()) {
            Path<T> minPath = active.poll();
            Graph.Vertex<T> minDest = g.getVertex(minPath.getEnd());
            if (minDest.equals(end)) {
                return minPath;
            }
            for (Graph.Edge<T, Double> e :
                    g.getEdgesFrom(minDest)) {
                if (!finished.contains(e.getToVertex())) {
                    if (e.getLabel() >= 0) {
                        Path<T> newPath = minPath.extend(e.getToVertex().getName(), e.getLabel());
                        active.add(newPath);
                    }
                }
            }
            finished.add(minDest);
        }
//        checkRep(g, start, end);
        return null;
    }



}
