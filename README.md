# Graph class

<b>Graph</b> represents an <b>immutable</b> collection of Vertices (also called nodes)
and edges. Each Edge connects two Vertices. The Edge in this graph is directed.
For example, an edge e = [A,B] indicates that B is directly reachable from A or
there is a one-way path from A to B.
The children of a Vertex v are all Edges directly coming out from v. The parents of
a Vertex v are all Edges directly coming into v.
A graph can have any number of edges (zero, one, or more) between a pair of nodes. An
Edge can have a label containing information of some sort. Labels are not unique:
multiple edges may have the same label. But there is no 2 edges with the same parent
and child nodes will have the same edge label.
<p>An example of Edge can be "A Edge labeled e is a one-way connection from the Vertex
named fromVertex to the Vertex named toVertex
  
# ShortestPath class
  
This class implements algorithm to find the shortest path in a graph by using Dijkstra algorithm.
@param <T> generic type
