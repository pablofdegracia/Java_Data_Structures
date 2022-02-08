package upm.aed.graphs;

import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.map.Map;
import es.upm.aedlib.map.HashTableMap;
import es.upm.aedlib.set.Set;
import es.upm.aedlib.set.HashTableMapSet;
import es.upm.aedlib.graph.Edge;
import es.upm.aedlib.graph.Vertex;
import es.upm.aedlib.graph.Graph;
import es.upm.aedlib.graph.DirectedGraph;

import java.util.Iterator;

import es.upm.aedlib.graph.DirectedAdjacencyListGraph;
import es.upm.aedlib.lifo.LIFO;
import es.upm.aedlib.lifo.LIFOList;


public class EjemplosDirectedGraphs {

	public static <V,E> PositionList<Vertex<V>> reachableVertices
	(DirectedGraph<V,E> g,
			Vertex<V> startVertex) {
		PositionList<Vertex<V>> vertices = new NodePositionList<Vertex<V>>();

		Set<Vertex<V>> hasVisited = new HashTableMapSet<Vertex<V>>();
		visit(g, startVertex, vertices, hasVisited);
		return vertices;
	}

	private static <V,E> void visit(DirectedGraph<V,E> g,
			Vertex<V> vertex,
			PositionList<Vertex<V>> vertices,
			Set<Vertex<V>> hasVisited) {
		if (!hasVisited.contains(vertex)) {
			vertices.addLast(vertex);
			hasVisited.add(vertex);
			for (Edge<E> arco : g.outgoingEdges(vertex))
			{
				Vertex<V> fin = g.endVertex(arco);
				visit(g, fin, vertices, hasVisited);
			}
		}
	}

	public static <V,E> PositionList<Edge<E>> findOnePath(DirectedGraph<V,E> g,
			Vertex<V> from,
			Vertex<V> to) {
		PositionList<Edge<E>> path =
				new NodePositionList<Edge<E>>();

		return findOnePath(g, from, to, path);
	}

	

	
	public static <V,E> PositionList<Edge<E>> findOnePath(
			DirectedGraph<V,E> g,
			Vertex<V> from,
			Vertex<V> to,
			PositionList<Edge<E>> path) 
	{
		if (from == to) return path;
	
		for (Edge<E> e : g.outgoingEdges(from)) {
			Vertex<V> verticeAlQueTengoQueIr = g.endVertex(e);
			if (!verticeAlQueTengoQueIr.equals(g.startVertex(e))
					&& !pathContainsVertex(g,path,verticeAlQueTengoQueIr)) {
				path.addLast(e);
				PositionList<Edge<E>> result =
						findOnePath(g,verticeAlQueTengoQueIr,to,path);
				if (result != null) 
					return result;
				else 
					path.remove(path.last());
			}
		}
		return null;
	}

	public static <V,E> Set<PositionList<Edge<E>>> findAllPaths(DirectedGraph<V,E> g,
			Vertex<V> from,
			Vertex<V> to) {
		PositionList<Edge<E>> path =
				new NodePositionList<Edge<E>>();
		Set<PositionList<Edge<E>>> allPaths =
				new HashTableMapSet<PositionList<Edge<E>>>();

		findAllPaths(g, from, to, path, allPaths);
		return allPaths;
	}

	public static <V,E> void findAllPaths(DirectedGraph<V,E> g,
			Vertex<V> from,
			Vertex<V> to,
			PositionList<Edge<E>> path,
			Set<PositionList<Edge<E>>> allPaths) {
		
		if (from == to) {
			allPaths.add(new NodePositionList<Edge<E>>(path));
			return;
		}

		for (Edge<E> e : g.outgoingEdges(from)) {
			Vertex<V> endVertex = g.endVertex(e);
			if (!endVertex.equals(g.startVertex(e))
					&& !pathContainsVertex(g,path,endVertex)) {
				path.addLast(e);
				findAllPaths(g,endVertex,to,path,allPaths);
				path.remove(path.last());
			}
		}
		return;
	}

	public static <V,E> PositionList<Vertex<V>> topologicalOrder(DirectedGraph<V,E> g) {
		LIFO<Vertex<V>> s = new LIFOList<Vertex<V>>();
		Map<Vertex<V>,Integer> incounter = new HashTableMap<Vertex<V>,Integer>();
		PositionList<Vertex<V>> l = new NodePositionList<Vertex<V>>();

		for (Vertex<V> v : g.vertices()) {
			int indegree = g.inDegree(v);
			incounter.put(v, indegree);
			if (indegree == 0) s.push(v);
		}

		while (!s.isEmpty()) {
			Vertex<V> v = s.pop();
			l.addLast(v);
			for (Edge<E> e : g.outgoingEdges(v)) {
				Vertex<V> to = g.endVertex(e);
				int newCounter = incounter.get(to)-1;
				incounter.put(to,newCounter);
				if (newCounter == 0)
					s.push(to);
			}
		}

		return l;
	}

	private static <V,E> boolean pathContainsVertex(DirectedGraph<V,E> g,
			PositionList<Edge<E>> path, 
			Vertex<V> v) {
		for (Edge<E> edge : path) {
			if (v.equals(g.startVertex(edge))
					|| v.equals(g.endVertex(edge)))
				return true;
		}
		return false;
	}

	public static <V,E> Vertex<V> vertex(Graph<V,E> g, V v) {
		for (Vertex<V> vert : g.vertices()) {
			if (v.equals(vert.element())) return vert;
		}
		return null;
	}

	public static void main(String args[]) {
		DirectedGraph<String,Void> g = Despertar.defineDespertar();
//		PlotGraphs.plotGraph(g,"despertar2");

		System.out.println
		("\nThe vertices reachable from despertar are "+
				reachableVertices(g,vertex(g,"vestirse")));

//		System.out.println
//		("\nThe vertices reachable from salir casa are "+
//				reachableVertices(g,vertex(g,"salir casa")));
//
//		System.out.println
//		("\nThe vertices reachable from lavar dientes are "+
//				reachableVertices(g,vertex(g,"lavar dientes")));

//		System.out.println("\n----------------------------------------------------------------------");

		System.out.println
		("\nA path from despertar to salir casa is "+
				findOnePath(g,vertex(g,"despertar"),vertex(g,"lavar dientes")));

//		System.out.println("\n----------------------------------------------------------------------");
//
		System.out.println
		("\nAll paths from despertar to salir casa is "+
				findAllPaths(g,vertex(g,"despertar"),vertex(g,"salir casa")));

//		System.out.println("\n----------------------------------------------------------------------");
//
//		System.out.println("\nA topological order is "+topologicalOrder(g));
	}
}


