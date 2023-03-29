package graph;

import java.util.*;

/**
 * <b>Graph</b> represents an <b>immutable</b> collection of Vertices (also called nodes)
 * and edges. Each Edge connects two Vertices. The Edge in this graph is directed.
 * For example, an edge e = [A,B] indicates that B is directly reachable from A or
 * there is a one-way path from A to B.
 *
 * The children of a Vertex v are all Edges directly coming out from v. The parents of
 * a Vertex v are all Edges directly coming into v.
 *
 * A graph can have any number of edges (zero, one, or more) between a pair of nodes. An
 * Edge can have a label containing information of some sort. Labels are not unique:
 * multiple edges may have the same label. But there is no 2 edges with the same parent
 * and child nodes will have the same edge label.
 *
 * <p>An example of Edge can be "A Edge labeled e is a one-way connection from the Vertex
 * named fromVertex to the Vertex named toVertex."
 */
public class Graph<V, E> {

    //RI:
    // this != null,
    // this.adjacencyList != null,
    // every Vertex in adjacencyList != null,
    // every Edge in adjacencyList != null
    //
    //AF(this):
    // vertex of this graph = this.adjacencyList.keySet(),
    // children edge for a vertex v = this.adjacencyList.get(v)

    private final boolean DEBUG = false;
    
    // A collection of Vertex and its outgoing Edges.
    private final Map<Vertex<V>, List<Edge<V, E>>> adjacencyList;

    private final String name;

    private final Set<V> vertexNameCollection;

    /**
     * Constructs a empty new Graph with a empty Vertex.
     *
     * @param name the given name for this graph.
     * @spec.effects Constructs a new Graph with given starter.
     */
    public Graph(String name) {
        this.adjacencyList = new HashMap<>();
//        this.adjacencyList.put(new Vertex(V), new ArrayList<>());
        this.name = name;
        this.vertexNameCollection = new HashSet<V>();
    }

    /**
     * Constructs a new Graph with given starting Vertex.
     *
     * @param name the given name for this graph.
     * @param starter the given Vertex to start this Graph with.
     * @spec.effects Constructs a new Graph with given starter.
     */
    public Graph(String name, Vertex<V> starter) {
        this.adjacencyList = new HashMap<>();
        this.adjacencyList.put(starter, new ArrayList<>());
        this.name = name;
        this.vertexNameCollection = new HashSet<V>();
        vertexNameCollection.add(starter.name);
    }

    /**
     * Constructs a new Graph with given dictionary for Vertices and lists of edges.
     *
     * @param name the given name for this graph.
     * @param adjacencyList the given dictionary that stores Vertex and Edges and their direction
     *                      relationship with each others.
     * @spec.effects Constructs a new Graph with the given dictionary.
     */
    public Graph(String name, Map<Vertex<V>, List<Edge<V, E>>> adjacencyList) {
        this.name = name;
        this.adjacencyList = adjacencyList;
        this.vertexNameCollection = new HashSet<V>();
        for (Vertex<V> v :
                adjacencyList.keySet()) {
            vertexNameCollection.add(v.name);
        }
    }

    /**
     * Throws an exception if the representation invariant is violated.
     */
    private void checkRep() {
        assert this != null : "this is null";
        assert this.adjacencyList != null : "adjacencyList is null";
        if (DEBUG) {
            for (Vertex<V> v :
                    this.adjacencyList.keySet()) {
                assert v != null : "Vertex is null";
                for (Edge<V, E> e :
                        this.adjacencyList.get(v)) {
                    assert e != null : "Edge is null";
                }
            }
        }
    }

    /**
     * Add a new Vertex v into this graph.
     * @spec.requires v != null, this != null, name != every Vertex name in this graph.
     * @param name the given String that want to name the new Vertex with.
     */
    public void addVertex(V name) {
        checkRep();
        this.adjacencyList.put(new Vertex<V>(name), new ArrayList<>());
        this.vertexNameCollection.add(name);
        checkRep();
    }

    /**
     * Add a new Vertex v into this graph.
     * @spec.requires v != null, this != null, name != every Vertex name in this graph.
     * @param v the given Vertex that want to add into this graph.
     */
    public void addVertex(Vertex<V> v) {
        checkRep();
        this.adjacencyList.put(v, new ArrayList<>());
        this.vertexNameCollection.add(v.name);
        checkRep();
    }

    /**
     * Get the Vertex with the given name from this graph.
     * @param name the given String of the name of the Vertex.
     * @return return the found Vertex with the given name. If there is no Vertex with the given name, return null.
     */
    public Vertex<V> getVertex(V name) {
        checkRep();
        for (Vertex<V> v :
                this.adjacencyList.keySet()) {
            if (v.getName().equals(name)) {
                return new Vertex<V>(v.getName());
            }
        }
        return null;
    }

    /**
     * Checks whether the given Vertex is in the graph.
     *
     * @param v the given vertex that will be checked its existence.
     * @return return true if the given vertex exists in the graph, else return false.
     */
    public boolean containsVertex(Vertex<V> v) {
        checkRep();
        return this.adjacencyList.containsKey(v);
    }

    /**
     * Checks whether the given string is the name of one of the Vertex in the graph.
     * @param s the given name to search in the graph.
     * @return return true if the given name is the name of one of the Vertex in the graph,
     *          else return false.
     */
    public boolean containsVertexName(String s) {
        checkRep();
//        for (Vertex v :
//                adjacencyList.keySet()) {
//            if (v.getName().equals(s)) {
//                return true;
//            }
//        }
//        return false;

        return this.vertexNameCollection.contains(s);
    }

    /**
     * Get a list of Edges which comes out of the given Vertex, aka child Edges of v.
     * @spec.requires v != null and vertex v exists in the graph.
     * @param v the given Vertex used to find its corresponding children.
     * @return return a list of Edges which comes out of the given Vertex if the given vertex exists
     *          in the graph, else return null.
     */
    public List<Edge<V, E>> getEdgesFrom(Vertex<V> v) {
        checkRep();
        List<Edge<V, E>> copy = new ArrayList<>();
        for (Edge<V, E> e : this.adjacencyList.get(v)) {
            copy.add(e);
        }
        return copy;
    }

    /**
     * Get a list of Edges which goes to the given Vertex, aka parent Edges of v.
     * @spec.requires v != null and vertex v exists in the graph.
     * @param v the given Vertex used to find its corresponding parents.
     * @return a list of Edges which goes to the given Vertex.
     */
    public List<Edge<V, E>> getEdgesTo(Vertex<V> v) {
        checkRep();
        List<Edge<V, E>> copy = new ArrayList<>();
        for (Vertex<V> vtx :
                this.adjacencyList.keySet()) {
            for (Edge<V, E> e :
                    this.adjacencyList.get(vtx)) {
                if (e.toVertex.equals(v)) {
                    copy.add(e);
                }
            }
        }
        return copy;
    }

    /**
     * Get a relationship dictionary for all the Vertices and Edges in this graph.
     * @return a relationship dictionary for all the Vertices and Edges in this graph.
     */
    public Map<Vertex<V>, List<Edge<V, E>>> getAdjacencyList() {
        checkRep();
        Map<Vertex<V>, List<Edge<V, E>>> copy = new HashMap<>();
        for (Vertex<V> v :
                this.adjacencyList.keySet()) {
            copy.put(v, this.adjacencyList.get(v));
        }
        return copy;
    }

    /**
     * Add the given Edge e to the children Edges list of v1 and the parent Edges list of v2.
     * @spec.requires v1 != null and v2 != null and e != null and
     *                  e.fromVertex != null and e.toVertex != null and Edge e does not exist in this graph.
     * @param v1 the given Vertex which the Edge comes from.
     * @param v2 the given Vertex which the Edge goes to.
     * @param e the given Edge which try to be the connection from v1 to v2.
     * @spec.modifies this.
     * @spec.effects this.
     */
    public void addEdge(Vertex<V> v1, Vertex<V> v2, Edge<V, E> e) {
        checkRep();
        if (!this.adjacencyList.containsKey(v2)) {
            this.adjacencyList.put(v2, new ArrayList<>());
            this.vertexNameCollection.add(v2.name);
        }
        if (!this.adjacencyList.containsKey(v1)) {
            this.adjacencyList.put(v1, new ArrayList<>());
            this.vertexNameCollection.add(v1.name);
        }
        addEdge(v1, e);
        checkRep();
    }

    // add edge from v1 into adjacencyList
    private void addEdge(Vertex<V> v1, Edge<V, E> e) {
        List<Edge<V, E>> edgeList;
        edgeList = this.adjacencyList.get(v1);
        edgeList.add(e);
        this.adjacencyList.put(v1, edgeList);

    }

    /**
     * Checks whether the edge from v1 to v2 exists in this graph.
     * @param label the label of the given edge
     * @param v1 the start vertex of the given edge
     * @param v2 the end vertex of the given edge
     * @return true if this graph contains the given edge, else return false.
     */
    public boolean containsEdge(E label, Vertex<V> v1, Vertex<V> v2) {
        checkRep();
        if (!this.adjacencyList.containsKey(v2)) {
            return false;
        }
        if (!this.adjacencyList.containsKey(v1)) {
            return false;
        }
        for (Edge<V, E> e :
                this.adjacencyList.get(v1)) {
            if (e.toVertex.equals(v2) && e.getLabel().equals(label)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <b>Edge</b> represents an <b>immutable</b> edge in a graph. A edge is a one-way
     * connection between two Vertex, which is a node, in a graph. For example, an edge
     * e = [A,B] indicates that B is directly reachable from A or there is a one-way path
     * from A to B.
     *
     * An Edge can have a label containing information of some sort. Labels are not unique:
     * multiple edges may have the same label. But there is no 2 edges with the same parent
     * and child nodes will have the same edge label.
     *
     * <p>An example of Edge can be "A Edge labeled e is a one-way connection from the Vertex
     * named fromVertex to the Vertex named toVertex.
     */
    public static final class Edge<V, ELabel> {

        // RI: this != null and fromVertex != null and toVertex != null
        // AF(this):
        // label of this Edge is this.label,
        // origin Vertex = this.fromVertex,
        // destination Vertex = this.toVertex.

        /**
         * Holds the Vertex this Edge comes from.
         */
        private final Vertex<V> fromVertex;

        /**
         * Holds the Vertex this Edge goes to.
         */
        private final Vertex<V> toVertex;

        /**
         * Holds the label of this Edge.
         */
        private final ELabel label;

        /**
         * Constructs a new Edge.
         *
         * @spec.requires fromVertex != null and toVertex != null
         * @param label the given label for this Edge.
         * @param fromVertex the Vertex where this Edge comes from.
         * @param toVertex the Vertex where this Edge goes to.
         * @spec.effects Constructs a new Edge with given parameter.
         */
        public Edge(ELabel label, Vertex<V> fromVertex, Vertex<V> toVertex) {
            this.label = label;
            this.fromVertex = fromVertex;
            this.toVertex = toVertex;
        }

        /**
         * Throws an exception if the representation invariant is violated.
         */
        private void checkRep() {
            assert this != null : "this is null";
            assert this.toVertex != null : "toVertex is null";
            assert this.fromVertex != null : "fromVertex is null";
        }

        /**
         * Return the label of this Edge.
         *
         * @return the label of this Vertex.
         * @spec.requires this != null
         */
        public ELabel getLabel() {
            checkRep();
            return this.label;
        }

        /**
         * Return the Vertex where this Edge comes from
         *
         * @return the Vertex where this Edge comes from
         * @spec.requires this != null
         */
        public Vertex<V> getFromVertex() {
            checkRep();
            return new Vertex<V>(this.fromVertex.name);
        }

        /**
         * Return the Vertex where this Edge goes to
         *
         * @return the Vertex where this Edge goes to
         * @spec.requires this != null
         */
        public Vertex<V> getToVertex() {
            checkRep();
            return new Vertex<V>(this.toVertex.name);
        }

        /**
         * Standard equality operation.
         *
         * @param obj the object to be compared for equality
         * @return true if and only if 'obj' is an instance of a Edge and 'this' and 'obj' represent
         * the same graph edge.
         */
        @Override
        public boolean equals(Object obj) {
            checkRep();
            if (obj instanceof Edge<?, ?>) {
                return this.label.equals(((Edge<?, ?>) obj).label) &&
                        this.toVertex.equals(((Edge<?, ?>) obj).toVertex) &&
                        this.fromVertex.equals(((Edge<?, ?>) obj).fromVertex);
            }
            return false;
        }

        /**
         * Standard hashCode function.
         *
         * @return an int that all objects equal to this will also return.
         */
        @Override
        public int hashCode() {
            checkRep();
            return 7 * this.label.hashCode() +
                    11 * this.toVertex.hashCode() +
                    13 * this.fromVertex.hashCode();
        }
    }



    /**
     * <b>Vertex</b> represents an <b>immutable</b> vertex in a graph. A vertex is a node between
     * two edges in a graph.
     *
     * The children of a Vertex v are all Edges directly coming out from v, which are outEdges.
     * The parents of a Vertex v are all Edges directly coming into v, which are inEdges.
     *
     * <p>An example of Vertex can be "A Vertex named v has an Edge list named inEdges coming in and an
     * Edge list named outEdges coming out.
     */
    public static final class Vertex<VName> {

        // RI: this != null
        // AF(this): name of this Vertex is this.name;

        /**
         * Holds the name of this Vertex.
         */
        private final VName name;

        /**
         * Constructs a new Vertex.
         *
         * @param name the name given to this Vertex.
         * @spec.effects Constructs a new Vertex with given name. If there is no Edges provided,
         * then construct a Vertex with given name and empty list of inEdges and outEdges.
         */
        public Vertex(VName name) {
            this.name = name;
        }

        /**
         * Throws an exception if the representation invariant is violated.
         */
        private void checkRep() {
            assert this != null : "this is null";
        }

        /**
         * Return the name of this Vertex.
         *
         * @return the name of this Vertex.
         * @spec.requires this != null
         */
        public VName getName() {
            checkRep();
            return this.name;
        }

        /**
         * Standard equality operation.
         *
         * @param obj the object to be compared for equality
         * @return true if and only if 'obj' is an instance of a Vertex and 'this' and 'obj' represent
         * the same graph vertex.
         */
        @Override
        public boolean equals(Object obj) {
            checkRep();
            if (obj instanceof Vertex<?>) {
                return this.name.equals(((Vertex<?>) obj).getName());
            }
            return false;
        }

        /**
         * Standard hashCode function.
         *
         * @return an int that all objects equal to this will also return.
         */
        @Override
        public int hashCode() {
            checkRep();
            return this.name.hashCode();
        }
    }
}
