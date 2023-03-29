package graph.junitTests;

import graph.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class VertexTest {
    @Rule
    public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds max per method tested

    /**
     * Item tests globally
     */
    String v1Name = "v1";
    String v2Name = "v2";
    String v3Name = "v3";

    private Graph.Vertex<String> v1 = new Graph.Vertex<String>("v1");

    private Graph.Vertex<String> v2 = new Graph.Vertex<String>("v2");

    private Graph.Vertex<String> v3 = new Graph.Vertex<String>("v3");

    private List<Graph.Edge<String, String>> e1List = new ArrayList<>();
    private List<Graph.Edge<String, String>> e2List = new ArrayList<>();
    private Graph.Vertex<String> v1WithEdges;
    private Graph.Vertex<String> v2WithEdges;

    /**
     * Set up method depends on Vertex and Edge
     */
    @Before
    public void setUp() {
        v1WithEdges = new Graph.Vertex<String>("v1");
        v2WithEdges = new Graph.Vertex<String>("v2");
    }

    /**
     * Tests getting the name of the vertices
     */
    @Test
    public void testGetName() {
        assertEquals(v1.getName(), v1Name);
        assertEquals(v2.getName(), v2Name);
        assertEquals(v3.getName(), v3Name);
    }

    /**
     * Tests adding parents Edges and children Edges for a Vertex
     */
    @Test
    public void testAddInOutEdges() {
        assertEquals(v1, v1WithEdges);
        assertEquals(v2, v2WithEdges);

    }

    /**
     * Tests override hashCode() properly
     */
    @Test
    public void testHashCode() {
        assertEquals(v1.hashCode(), v1WithEdges.hashCode());
        assertEquals(new Graph.Vertex<String>("v1").hashCode(), new Graph.Vertex<String>(v1Name).hashCode());
        assertEquals(v2.hashCode(), v2WithEdges.hashCode());
        assertEquals(new Graph.Vertex<String>("v2").hashCode(), new Graph.Vertex<String>(v2Name).hashCode());
    }

    /**
     * Tests override equals() properly
     */
    @Test
    public void testEquals() {
        assertTrue(v1.equals(v1WithEdges));
        assertTrue(new Graph.Vertex<String>("v1").equals(new Graph.Vertex<String>(v1Name)));
        assertTrue(v2.equals(v2WithEdges));
        assertTrue(new Graph.Vertex<String>("v2").equals(new Graph.Vertex<String>(v2Name)));
    }
}
