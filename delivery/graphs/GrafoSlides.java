package upm.aed.graphs;

import java.util.ArrayList;

import es.upm.aedlib.graph.DirectedAdjacencyListGraph;
import es.upm.aedlib.graph.DirectedGraph;
import es.upm.aedlib.graph.Vertex;
import es.upm.aedlib.positionlist.PositionList;

public class GrafoSlides {
	public static void main(String[] args) {
		DirectedGraph<String, Integer> g = new DirectedAdjacencyListGraph<>();

		ArrayList<Vertex<String>> vs = new ArrayList<>();		
		vs.add(g.insertVertex("A"));
		vs.add(g.insertVertex("B"));
		vs.add(g.insertVertex("C"));
		vs.add(g.insertVertex("D"));
		vs.add(g.insertVertex("E"));
		vs.add(g.insertVertex("F"));
		vs.add(g.insertVertex("G"));
		vs.add(g.insertVertex("H"));
		vs.add(g.insertVertex("I"));
		vs.add(g.insertVertex("J"));
		vs.add(g.insertVertex("K"));
		vs.add(g.insertVertex("L"));
		vs.add(g.insertVertex("M"));
		vs.add(g.insertVertex("N"));
		vs.add(g.insertVertex("O"));
		vs.add(g.insertVertex("P"));

		g.insertDirectedEdge(vs.get(0),vs.get(1), 1);
		g.insertDirectedEdge(vs.get(1),vs.get(2), 1);
		g.insertDirectedEdge(vs.get(2),vs.get(3), 1);

		g.insertDirectedEdge(vs.get(0),vs.get(4), 1);
		g.insertDirectedEdge(vs.get(0),vs.get(5), 1);
		g.insertDirectedEdge(vs.get(1),vs.get(5), 1);
		g.insertDirectedEdge(vs.get(2),vs.get(6), 1);
		g.insertDirectedEdge(vs.get(3),vs.get(7), 1);
		g.insertDirectedEdge(vs.get(3),vs.get(6), 1);

		g.insertDirectedEdge(vs.get(4),vs.get(5), 1);

		g.insertDirectedEdge(vs.get(4),vs.get(8), 1);
		g.insertDirectedEdge(vs.get(5),vs.get(8), 1);
		g.insertDirectedEdge(vs.get(6),vs.get(9), 1);
		g.insertDirectedEdge(vs.get(6),vs.get(10), 1);
		g.insertDirectedEdge(vs.get(6),vs.get(11), 1);
		g.insertDirectedEdge(vs.get(7),vs.get(11), 1);

		g.insertDirectedEdge(vs.get(8),vs.get(9), 1);
		g.insertDirectedEdge(vs.get(9),vs.get(10), 1);

		g.insertDirectedEdge(vs.get(8),vs.get(12), 1);
		g.insertDirectedEdge(vs.get(8),vs.get(13), 1);
		g.insertDirectedEdge(vs.get(10),vs.get(13), 1);
		g.insertDirectedEdge(vs.get(10),vs.get(14), 1);
		g.insertDirectedEdge(vs.get(11),vs.get(15), 1);

		g.insertDirectedEdge(vs.get(12),vs.get(13), 1);
		g.insertDirectedEdge(vs.get(14),vs.get(15), 1);
		
		PlotGraphs.plotGraph(g);
		

	}
}
