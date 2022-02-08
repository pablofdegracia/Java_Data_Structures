package upm.aed.graphs;

import java.util.ArrayList;
import java.util.Iterator;

import es.upm.aedlib.graph.DirectedGraph;
import es.upm.aedlib.graph.Edge;
import es.upm.aedlib.graph.UndirectedAdjacencyListGraph;
import es.upm.aedlib.graph.UndirectedGraph;
import es.upm.aedlib.graph.Vertex;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.set.HashTableMapSet;
import es.upm.aedlib.set.Set;

public class EjemplosUndirectedGraphsTODO {
	public static void main(String[] args) {
		UndirectedGraph<String, Integer> g = new UndirectedAdjacencyListGraph<>();

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


		g.insertUndirectedEdge(vs.get(0),vs.get(1), 1);
		g.insertUndirectedEdge(vs.get(1),vs.get(2), 1);
		g.insertUndirectedEdge(vs.get(2),vs.get(3), 1);

		g.insertUndirectedEdge(vs.get(0),vs.get(4), 1);
		g.insertUndirectedEdge(vs.get(0),vs.get(5), 1);
		g.insertUndirectedEdge(vs.get(1),vs.get(5), 1);
		g.insertUndirectedEdge(vs.get(2),vs.get(6), 1);
		g.insertUndirectedEdge(vs.get(3),vs.get(7), 1);
		g.insertUndirectedEdge(vs.get(3),vs.get(6), 1);

		g.insertUndirectedEdge(vs.get(4),vs.get(5), 1);

		g.insertUndirectedEdge(vs.get(4),vs.get(8), 1);
		g.insertUndirectedEdge(vs.get(5),vs.get(8), 1);
		g.insertUndirectedEdge(vs.get(6),vs.get(9), 1);
		g.insertUndirectedEdge(vs.get(6),vs.get(10), 1);
		g.insertUndirectedEdge(vs.get(6),vs.get(11), 1);
		g.insertUndirectedEdge(vs.get(7),vs.get(11), 1);

		g.insertUndirectedEdge(vs.get(8),vs.get(9), 1);
		g.insertUndirectedEdge(vs.get(9),vs.get(10), 1);

		g.insertUndirectedEdge(vs.get(8),vs.get(12), 1);
		g.insertUndirectedEdge(vs.get(8),vs.get(13), 1);
		g.insertUndirectedEdge(vs.get(10),vs.get(13), 1);
		g.insertUndirectedEdge(vs.get(10),vs.get(14), 1);
		g.insertUndirectedEdge(vs.get(11),vs.get(15), 1);

		g.insertUndirectedEdge(vs.get(12),vs.get(13), 1);
		g.insertUndirectedEdge(vs.get(14),vs.get(15), 1);


//		Vertex<String> solo = g.insertVertex("solito");
//
//		System.out.println(g.numVertices());
//		System.out.println(g.numEdges());

		//System.out.println(findReachableWithNJumps(g,vs.get(0),2));
		//		PlotGraphs.plotGraph(g);
		//		deepSearchNodes(g,vs.get(0));
		//		System.out.println(findReachableWithNJumps(g,vs.get(0),3));
		findAllSimplePaths(g, vs.get(0), vs.get(8));

	}

	public static <V,E> void deepSearchNodes (UndirectedGraph<V, E> g, 
			Vertex<V> n) {
		Set<Vertex<V>> visitados = new HashTableMapSet<Vertex<V>>(); 
		deepSearchNodes(g, n, visitados);
	}

	public static <V,E> void deepSearchNodes (UndirectedGraph<V, E> g, 
			Vertex<V> n, 
			Set<Vertex<V>> visitados) {
		//		if (visitados.contains(n)) {
		//			return; 
		//		}
		visitados.add(n); 
		for (Edge<E> edge: g.edges(n)) {
			if (!visitados.contains(g.opposite(n, edge))) {
				deepSearchNodes(g,g.opposite(n, edge),visitados);
			}
		}

	}
	public static <V,E> Set<Vertex<V>> findReachableWithNJumps (UndirectedGraph<V, E> g, 
			Vertex<V> from, 
			int nJumps) {

		Set<Vertex<V>> visitados = new HashTableMapSet<Vertex<V>>(); 

		findReachableWithNJumps(g,from,nJumps,visitados);
		return visitados; 
	}

	public static <V,E> void findReachableWithNJumps (
			UndirectedGraph<V, E> g, 
			Vertex<V> n, 
			int nJumps, 
			Set<Vertex<V>> visitados) {
		if (nJumps == 0) {
			visitados.add(n);
			return; 
		}

		if (visitados.contains(n)) {
			return; 
		}
		for (Edge<E> edge: g.edges(n)) {
			findReachableWithNJumps(g,g.opposite(n, edge),nJumps-1,visitados);
		}
	}

	public static <V,E> boolean isReachable (DirectedGraph<V, E> g, 
			Vertex<V> from,
			Vertex<V> to) {

		Set<Vertex<V>> visitados = new HashTableMapSet<Vertex<V>>(); 
		return isReachable(g,from,to,visitados); 
	}

	private static <V,E> boolean isReachable (DirectedGraph<V, E> g, 
			Vertex<V> n,
			Vertex<V> to, 
			Set<Vertex<V>> visitados) {
		if (n == to) {
			return true;
		}
		if (visitados.contains(n)) {
			return false; 
		}

		visitados.add(n); 
		Iterator<Edge<E>> it = g.outgoingEdges(n).iterator();
		boolean found = false; 
		while (it.hasNext() && !found) { 
			found = isReachable(g,g.endVertex(it.next()),to,visitados);
		}
		return found;
	}


	public static <V,E> PositionList<Vertex<V>> findOneSimplePath (UndirectedGraph<V, E> g,
			Vertex<V> from,
			Vertex<V> to) {
		Set<Vertex<V>> visitados = new HashTableMapSet<Vertex<V>>(); 
		PositionList<Vertex<V>> res = new NodePositionList<>(); 
		findOneSimplePath(g,from,to,visitados, res);
		return res; 

	}

	private static <V,E> boolean findOneSimplePath (UndirectedGraph<V, E> g, 
			Vertex<V> n,
			Vertex<V> to, 
			Set<Vertex<V>> visitados, 
			PositionList<Vertex<V>> path) {

		if (n == to) {
			path.addFirst(n);
			return true;
		}
		if (visitados.contains(n)) {
			return false; 
		}

		//		path.addLast(n);
		visitados.add(n); 

		Iterator<Edge<E>> it = g.edges(n).iterator();
		boolean found = false; 
		while (it.hasNext() && !found) { 
			found = findOneSimplePath(g,g.opposite(n,it.next()),to,visitados, path);
		}

		if (found) {
			path.addFirst(n); 
		}
		return found;
	}

	public static <V,E> void findAllSimplePaths (UndirectedGraph<V, E> g, 
			Vertex<V> from,
			Vertex<V> to) {
		Set<Vertex<V>> visitados = new HashTableMapSet<Vertex<V>>(); 
		PositionList<Vertex<V>> path = new NodePositionList<>(); 
		findAllSimplePaths(g,from,to,visitados, path); 

	}

	public static <V,E> void findAllSimplePaths (UndirectedGraph<V, E> g, 
			Vertex<V> n,
			Vertex<V> to, 
			Set<Vertex<V>> visitados, 
			PositionList<Vertex<V>> path) {

		if (visitados.contains(n)) {
			return; 
		}
		path.addLast(n);
		visitados.add(n); 
		if (n== to ) {
			System.out.println(path);
		}
		else {
			for (Edge<E> edge: g.edges(n)) {
				findAllSimplePaths(g,g.opposite(n, edge),to,visitados, path);
			}
		}
		path.remove(path.last());
		visitados.remove(n); 
	}


}
