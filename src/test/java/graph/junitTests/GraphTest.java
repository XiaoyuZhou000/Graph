package graph.junitTests;

import graph.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public final class GraphTest {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds max per method tested

    /**
     * Item tests globally
     */
    private Map<Graph.Vertex<String>, List<Graph.Edge<String, String>>> adjacencyList = new HashMap<>();
    private Graph.Vertex<String> v1WithEdges;
    private Graph.Vertex<String> v2WithEdges;
    private Graph.Edge<String, String> e1WithVertices;
    private Graph.Edge<String, String> e2WithVertices;
    private List<Graph.Edge<String, String>> v1List = new ArrayList<>();
    private List<Graph.Edge<String, String>> v2List = new ArrayList<>();
    private List<Graph.Edge<String, String>> e1List = new ArrayList<>();
    private List<Graph.Edge<String, String>> e2List = new ArrayList<>();
    private Graph<String, String> g1;

    //g2
    private Graph<String, String> g2;
    private Graph.Vertex<String> start;
    private Graph.Vertex<String> A;
    private Graph.Vertex<String> B;

    //g3
    private Graph<String, String> g3;
    private Graph.Vertex<String> S;
    private Graph.Vertex<String> I;
    private Graph.Vertex<String> J;
    private Graph.Vertex<String> N;
    private Graph.Vertex<String> M;
    private Graph.Vertex<String> O;
    private Graph.Vertex<String> P;
    private Graph.Edge<String, String> a;
    private Graph.Edge<String, String> c;
    private Graph.Edge<String, String> b;

    /**
     * Set up method depends on Vertex and Edge
     */
    @Before
    public void setUp() {

        v1WithEdges = new Graph.Vertex<String>("v1");
        v2WithEdges = new Graph.Vertex<String>("v2");
        e1WithVertices = new Graph.Edge<String, String>("e1", v2WithEdges, v1WithEdges);
        e2WithVertices = new Graph.Edge<String, String>("e2", v1WithEdges, v2WithEdges);
        v1List.add(e1WithVertices);
        v1List.add(e2WithVertices);
        v2List.add(e2WithVertices);
        v2List.add(e1WithVertices);
        adjacencyList.put(v1WithEdges, v1List);
        adjacencyList.put(v2WithEdges, v2List);
        g1 = new Graph<String, String>("g1", adjacencyList);
        e1WithVertices = new Graph.Edge<String, String>("e1", v2WithEdges, v1WithEdges);
        e2WithVertices = new Graph.Edge<String, String>("e2", v1WithEdges, v2WithEdges);
        e1List.add(e1WithVertices);
        e1List.add(e2WithVertices);
        e2List.add(e2WithVertices);
        e2List.add(e1WithVertices);

        // g2
        g2 = new Graph<String, String>("g2");
        start = new Graph.Vertex<String>("start");
        A = new Graph.Vertex<String>("A");
        B = new Graph.Vertex<String>("B");
        g2.addEdge(start, A, new Graph.Edge<String, String>("a", start, A));
        g2.addEdge(A, B, new Graph.Edge<String, String>("c", A, B));
        g2.addEdge(start, B, new Graph.Edge<String, String>("b", start, B));

        //g3
        g3 = new Graph<String, String>("g2");
        S = new Graph.Vertex<String>("start");
        I = new Graph.Vertex<String>("A");
        J = new Graph.Vertex<String>("B");
        a = new Graph.Edge<String, String>("a", S, I);
        c = new Graph.Edge<String, String>("c", I, J);
        b = new Graph.Edge<String, String>("b", S, J);
        g3.addEdge(S, I, a);
        g3.addEdge(I, J, c);
        g3.addEdge(S, J, b);
        g3.addEdge(I, S, new Graph.Edge<String, String>("d", I, S));
        g3.addEdge(J, I, new Graph.Edge<String, String>("e", J, I));
        g3.addEdge(J, S, new Graph.Edge<String, String>("f", J, S));
        N = new Graph.Vertex<String>("N");
        M = new Graph.Vertex<String>("M");
        O = new Graph.Vertex<String>("O");
        P = new Graph.Vertex<String>("P");
    }

    /**
     * Tests getting parents Edge from a Vertex
     */
    @Test
    public void testGetEdgesFrom() {
        List<Graph.Edge<String, String>> gList1 = g1.getEdgesFrom(v2WithEdges);
        List<Graph.Edge<String, String>> gList2 = g1.getEdgesFrom(v1WithEdges);
        assertEquals(gList1, e2List);
        assertEquals(gList2, e1List);
    }

    /**
     * Tests getting children Edge from a Vertex
     */
    @Test
    public void testGetEdgesTo() {
//        List<Graph.Edge> gList1 = g1.getEdgesTo(v1WithEdges);
//        List<Graph.Edge> gList2 = g1.getEdgesTo(v2WithEdges);
//        assertEquals(gList1, e2List);
//        assertEquals(gList2, e1List);
        List<Graph.Edge<String, String>> g2EdgesToB = g2.getEdgesTo(B);
        List<Graph.Edge<String, String>> g2EdgesToBExpected = new ArrayList<>();
        g2EdgesToBExpected.add(new Graph.Edge<String, String>("c", A, B));
        g2EdgesToBExpected.add(new Graph.Edge<String, String>("b", start, B));
        assertEquals(g2EdgesToB, g2EdgesToBExpected);
    }

    /**
     * Tests adding a new Edge between two existed Vertices
     */
    @Test
    public void testAddEdgeBetweenExistedVertices() {
        Graph.Edge<String, String> e3 = new Graph.Edge<String, String>("e3", v1WithEdges, v2WithEdges);
        g1.addEdge(v1WithEdges, v2WithEdges, e3);
        assertTrue(g1.getEdgesFrom(v1WithEdges).contains(e3));
        assertTrue(g1.getEdgesTo(v2WithEdges).contains(e3));
    }

    /**
     * Tests adding a existed Edge in the graph.
     */
    @Test
    public void testAddExistedEdge() {
        int preCode = g1.hashCode();
        g1.addEdge(v1WithEdges, new Graph.Vertex<String>(""), e2WithVertices);
        assertEquals(preCode, g1.hashCode());
    }

    /**
     * Tests containsVertex() method for the Graph.
     */
    @Test
    public void testContainsVertex() {
        assertTrue(g2.containsVertex(start));
        assertTrue(g2.containsVertex(A));
        assertTrue(g2.containsVertex(B));
    }

    /**
     * Tests containsVertex() method for the Graph with loops.
     */
    @Test
    public void testContainsVertexWithLoop() {
        assertTrue(g3.containsVertex(S));
        assertTrue(g3.containsVertex(I));
        assertTrue(g3.containsVertex(J));
    }

    /**
     * Tests add Edge which goes to a current non-existed Vertex in the graph.
     */
    @Test
    public void testAddEdgeToNonExistedVertex() {
        g3.addEdge(I, N, new Graph.Edge<String, String>("n", I, N));
        assertTrue(g3.containsEdge("n", I, N));
    }

    /**
     * Tests add Edge which come from a current non-existed Vertex in the graph.
     */
    @Test
    public void testAddEdgeFromNonExistedVertex() {
        g3.addEdge(M, N, new Graph.Edge<String, String>("m", M, N));
        assertTrue(g3.containsEdge("m", M, N));
    }

    /**
     * Tests add Edge which come from a current non-existed and goes to a current non-existed Vertex in the graph.
     */
    @Test
    public void testAddEdgeFromNonExistedVertexToNonExistedVertex() {
        g3.addEdge(O, P, new Graph.Edge<String, String>("o", O, P));
        assertTrue(g3.containsEdge("o", O, P));
    }

    @Test
    public void testContainsVertexName() {
        assertTrue(g3.containsVertexName("start"));
        assertTrue(g3.containsVertexName("A"));
    }

    @Test
    public void testAddVertex() {
        g3.addVertex("X");
        g3.addVertex(new Graph.Vertex<String>("Y"));
        assertTrue(g3.containsVertexName("X"));
        assertTrue(g3.containsVertexName("Y"));
    }

    @Test
    public void testGetVertex() {
        assertEquals(g3.getVertex("start"), S);
        assertEquals(g3.getVertex("A"), A);
    }

    @Test
    public void testContainsEdge() {
        assertTrue(g3.containsEdge("a", S, A));
        assertTrue(g3.containsEdge("c", A, B));
        assertTrue(g3.containsEdge("b", S, B));

    }

}
