package graph.junitTests;

import graph.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EdgeTest {
    @Rule
    public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds max per method tested

    /**
     * Item tests globally
     */
    String e1Label = "e1";
    String e2Label = "e2";
    private Graph.Vertex<String> v1 = new Graph.Vertex<String>("v1");
    private Graph.Vertex<String> v2 = new Graph.Vertex<String>("v2");
    private Graph.Edge<String, String> e1WithVertex = new Graph.Edge<String, String>("e1", v2, v1);
    private Graph.Edge<String, String> e2WithVertex = new Graph.Edge<String, String>("e2", v1, v2);

    /**
     * Tests getting label for a Edge
     */
    @Test
    public void testGetLabel() {
        assertEquals(e1WithVertex.getLabel(), e1Label);
        assertEquals(e2WithVertex.getLabel(), e2Label);
    }

    /**
     * Tests the origin Vertex for an Edge
     */
    @Test
    public void testGetFromVertex() {
        assertEquals(e1WithVertex.getFromVertex(), v2);
        assertEquals(e2WithVertex.getFromVertex(), v1);
    }

    /**
     * Tests the destination Vertex for an Edge
     */
    @Test
    public void testGetToVertex() {
        assertEquals(e1WithVertex.getToVertex(), v1);
        assertEquals(e2WithVertex.getToVertex(), v2);
    }

    /**
     * Tests override hashCode() properly
     */
    @Test
    public void testHashCode() {
        assertEquals(e1WithVertex.hashCode(), new Graph.Edge<String, String>("e1", v2, v1).hashCode());
        assertEquals(e2WithVertex.hashCode(), new Graph.Edge<String, String>("e2", v1, v2).hashCode());
    }

    /**
     * Tests override equals() properly
     */
    @Test
    public void testEquals() {
        assertTrue(e1WithVertex.equals(new Graph.Edge<String, String>("e1", v2, v1)));
        assertTrue(e2WithVertex.equals(new Graph.Edge<String, String>("e2", v1, v2)));
    }
}
