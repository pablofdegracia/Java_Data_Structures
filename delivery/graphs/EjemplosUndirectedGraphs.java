package upm.aed.graphs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.function.Predicate;

import es.upm.aedlib.Entry;
import es.upm.aedlib.Position;
import es.upm.aedlib.fifo.FIFO;
import es.upm.aedlib.fifo.FIFOList;
import es.upm.aedlib.graph.DirectedGraph;
import es.upm.aedlib.graph.Edge;
import es.upm.aedlib.graph.UndirectedAdjacencyListGraph;
import es.upm.aedlib.graph.UndirectedGraph;
import es.upm.aedlib.graph.Vertex;
import es.upm.aedlib.map.HashTableMap;
import es.upm.aedlib.map.Map;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.priorityqueue.HeapPriorityQueue;
import es.upm.aedlib.priorityqueue.PriorityQueue;
import es.upm.aedlib.set.HashTableMapSet;
import es.upm.aedlib.set.Set;
import upm.aed.tads.Pair;


public class EjemplosUndirectedGraphs {
	public static void main(String[] args) {
		UndirectedGraph<String, Integer> g = new UndirectedAdjacencyListGraph<>();


		ArrayList<Vertex<String>> vs = new ArrayList<>();		
		vs.add(g.insertVertex("A")); // 0
		vs.add(g.insertVertex("B")); // 1
		vs.add(g.insertVertex("C")); // 2
		vs.add(g.insertVertex("D")); // 3
		vs.add(g.insertVertex("E")); // 4
		vs.add(g.insertVertex("F")); // 5
		vs.add(g.insertVertex("G")); // 6
		vs.add(g.insertVertex("H")); // 7
		vs.add(g.insertVertex("I")); // 8
		vs.add(g.insertVertex("J")); // 9
		vs.add(g.insertVertex("K")); // 10
		vs.add(g.insertVertex("L")); // 11
		vs.add(g.insertVertex("M")); // 12
		vs.add(g.insertVertex("N")); // 13
		vs.add(g.insertVertex("O")); // 14
		vs.add(g.insertVertex("P")); // 15

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

		Vertex<String> solo = g.insertVertex("solito");

		System.out.println(g);
		System.out.println(g.numVertices());
		System.out.println(g.numEdges());
		PlotGraphs.plotGraph(g,"letras");
		System.out.println(isReachable(g,vs.get(0),solo));
		System.out.println(isReachable(g,vs.get(0),vs.get(10)));
		//		System.out.println(findOneSimplePath(g,vs.get(0),solo));
		//		System.out.println(findOneSimplePath(g,vs.get(0),vs.get(10)));
		//	//	findAllSimplePaths(g,vs.get(0),vs.get(10));

		PositionList<PositionList<Vertex<String>>> paths = findAllSimplePaths2(g,vs.get(0),vs.get(10));
		System.out.println("-------------");

		System.out.println(paths);
		//		
		System.out.println(findReachableWithNJumps(g, vs.get(10), 3));
		System.out.println(findReachablePred(g, vs.get(10), v -> esVocal(v.element().charAt(0))));

		//		


		//		//
		//		//		deepSearchNodes(g,vs.get(0));
		//		//
		//		//		breadthFirstSearch(g,vs.get(0));
		//
		//		findAllSimplePaths2(g, vs.get(0), vs.get(8));
		//		System.out.println(isReachableInSteps(g, vs.get(0), vs.get(11), 4));
		//		//System.out.println(findReachableWithNJumps(g, vs.get(0), 3));


		/*UndirectedGraph<String,Integer> wFl =
				Airlines.defineWeightedFlightGraph();
		System.out.println
		("The shortest paths from madrid to all destinations are:\n"+
				shortestPaths(wFl,Airlines.vertex(wFl,"madrid")));*/
	}


	public static boolean esVocal (char a) {

		switch (a) {
		case 'a':
		case 'A':
		case 'e':
		case 'E':
		case 'i':
		case 'I':
		case 'o':
		case 'O':
		case 'u':
		case 'U':return true;
		default: return false;
		}

	}
	public static <V,E> void deepSearchNodes (UndirectedGraph<V, E> g, 
			Vertex<V> n) {

		Set<Position<?>> visited = new HashTableMapSet<>();
		deepSearchNodes(g,n,visited);

	}

	public static <V,E> void deepSearchNodes (UndirectedGraph<V, E> g, 
			Vertex<V> n, 
			Set<Position<?>> visited ) {

		if  (visited.contains(n)) {
			return;
		}
		visited.add(n);
		n.put("dotFillcolor","lightblue");
		PlotGraphs.plotGraph(g,"depthFirst");
		waitConsole();

		for (Edge<E> e: g.edges(n)) {
			deepSearchNodes(g, g.opposite(n, e), visited);
		}
	}

	public static <V,E> void breadthFirstSearch (UndirectedGraph<V, E> g, 
			Vertex<V> n) {
		Set<Vertex<V>> visited = new HashTableMapSet<>();
		FIFO<Vertex<V>> pending = new FIFOList<>();

		pending.enqueue(n);

		while (!pending.isEmpty()) {
			FIFO<Vertex<V>> existing = pending;
			pending = new FIFOList<Vertex<V>>();

			while (!existing.isEmpty()) {
				Vertex<V> v = existing.dequeue();
				if (!visited.contains(v)) {
					visited.add(v);
					v.put("dotFillcolor","lightblue");
					for (Edge<E> e: g.edges(v)) {
						pending.enqueue(g.opposite(v, e));
					}
				}
			}
			PlotGraphs.plotGraph(g,"breadthFirst");
			System.console().readLine("\nnext round> ");
		}
	}


	public static <V,E> void deepSearchEdges (UndirectedGraph<V, E> g, 
			Vertex<V> n) {
		Set<Edge<E>> visited = new HashTableMapSet<>();
		deepSearchEdges (g,n,visited);
		System.out.println(visited);
	}

	public static <V,E> void deepSearchEdges (UndirectedGraph<V, E> g, 
			Vertex<V> n, 
			Set<Edge<E>> visited ) {

		for (Edge<E> e: g.edges(n)) {
			if (!visited.contains(e)) {
				visited.add(e);
				deepSearchEdges(g, g.opposite(n, e), visited);
			}
		}
	}

	public static <V,E> boolean isReachable (UndirectedGraph<V, E> g, 
			Vertex<V> from,
			Vertex<V> to) {
		Set<Position<?>> visited = new HashTableMapSet<>(); 
		return isReachable(g, from, to, visited);
	}
	public static <V,E> boolean isReachable (UndirectedGraph<V, E> g, 
			Vertex<V> from,
			Vertex<V> to,
			Set<Position<?>> visited ) {

		if (from == to) { // el mismo vertice (objeto !!!)
			return true;
		}
		if  (visited.contains(from)) {  // he pasado por aquí !
			return false;
		}

		visited.add(from);   // marco como visitado en el que estoy 
		boolean reachable = false;
		Iterator<Edge<E>> aristas = g.edges(from).iterator();
		while (aristas.hasNext() && !reachable) {
			Vertex<V> opuesto = g.opposite(from, aristas.next());
			reachable = isReachable(g, opuesto, to, visited);
		}
		return reachable;
	}


	public static <V,E> PositionList<Vertex<V>> findOneSimplePath (
			UndirectedGraph<V, E> g,
			Vertex<V> from,
			Vertex<V> to) {

		Set<Vertex<V>> visited = new HashTableMapSet<>(); 
		PositionList<Vertex<V>> path = new NodePositionList<>();

		findOneSimplePath(g, from, to, visited, path);
		return path;
	}

	// It can be done by returning path
	public static <V,E> boolean findOneSimplePath (
			UndirectedGraph<V, E> g, 
			Vertex<V> from,
			Vertex<V> to,
			Set<Vertex<V>> visited, 
			PositionList<Vertex<V>> path) {


		if  (visited.contains(from)) {
			return false;
		}
		path.addLast(from);
		// solucion basica he llegado al final
		// porque from se va moviendo y ha llegado al objetivo !!!
		if (from == to) {
			return true;
		}  //solucion basica con exito

		visited.add(from);
		boolean found = false;
		Iterator<Edge<E>> arcos = g.edges(from).iterator();
		while (arcos.hasNext() && !found) {
			found = findOneSimplePath(g, g.opposite(
					from, arcos.next()), to, visited, path);
		}

		if (!found) { // no he encontrado el objetivo
			path.remove(path.last());  // elimino el vertice que había elegido
		}

		return found;
	}

	public static int sumarValoresVertices (UndirectedGraph<Integer, Integer> g)
	{
		int suma= 0;
		for (Vertex<Integer> v : g.vertices())
			suma += v.element();
		return suma;
	}

	public static int sumarValoresArcos (UndirectedGraph<Integer, Integer> g)
	{
		int suma= 0;

		for (Edge<Integer> v : g.edges())
			suma += v.element();
		return suma;
	}
	public static <V,E> void findAllSimplePaths (UndirectedGraph<V, E> g, 
			Vertex<V> from,
			Vertex<V> to) {
		Set<Vertex<V>> visited = new HashTableMapSet<>(); 
		PositionList<Vertex<V>> path = new NodePositionList<>();

		findAllSimplePaths(g, from, to, visited, path);

	}

	public static <V,E> void findAllSimplePaths (UndirectedGraph<V, E> g, 
			Vertex<V> from,
			Vertex<V> to,
			Set<Vertex<V>> visited, 
			PositionList<Vertex<V>> path) {

		if (visited.contains(from)) {
			return; 
		}

		path.addLast(from);
		visited.add(from);

		if (from == to) {
			System.out.println(path);
		}
		else {
			for (Edge<E> e: g.edges(from)) {
				findAllSimplePaths(g, g.opposite(from, e), to, visited, path);
			}
		}

		visited.remove(from);
		path.remove(path.last()); // path.last() == from
	}


	public static <V,E> PositionList<PositionList<Vertex<V>>> findAllSimplePaths2 (UndirectedGraph<V, E> g, 
			Vertex<V> from,
			Vertex<V> to) {
		Set<Vertex<V>> visited = new HashTableMapSet<>(); 
		PositionList<Vertex<V>> path = new NodePositionList<>();
		PositionList<PositionList<Vertex<V>>> resultado = new NodePositionList<PositionList<Vertex<V>>>();

		findAllSimplePaths2 (g, from, to, visited, path, resultado);
		return resultado;
	}

	public static <E> PositionList<E> copy (PositionList<E> l)
	{
		PositionList<E> copy = new NodePositionList();
		Position<E> cursor = l.first();
		while (cursor != null) {
			copy.addLast(cursor.element());
			cursor = l.next(cursor);
		}
		return copy;	
	}
	public static <V,E> void findAllSimplePaths2 (UndirectedGraph<V, E> g, 
			Vertex<V> from,
			Vertex<V> to,
			Set<Vertex<V>> visited, 
			PositionList<Vertex<V>> path,
			PositionList<PositionList<Vertex<V>>> resultado) {

		if (visited.contains(from)) {
			return; 
		}

		path.addLast(from);
		visited.add(from);

		if (from == to) {
			resultado.addLast(copy(path));
		}
		else {
			for (Edge<E> e: g.edges(from)) {
				findAllSimplePaths2(g, g.opposite(from, e), to, visited, path, resultado);
			}
		}

		visited.remove(from);
		path.remove(path.last()); // path.last() == from
	}
	// An alternative with just the path -- less efficient but clean
	public static <V,E> void findAllSimplePathsP (UndirectedGraph<V, E> g, 
			Vertex<V> from,
			Vertex<V> to) {
		PositionList<Vertex<V>> path = new NodePositionList<>();

		findAllSimplePathsP(g, from, to, path);

	}

	public static <V,E> void findAllSimplePathsP (UndirectedGraph<V, E> g, 
			Vertex<V> from,
			Vertex<V> to,
			PositionList<Vertex<V>> path) {

		path.addLast(from);

		if (from == to) {
			System.out.println(path);
			return;
		} 
		else {
			for (Edge<E> e: g.edges(from)) {
				if (!pathContainsVertex(path,g.opposite(from, e))) {
					findAllSimplePathsP(g, g.opposite(from, e), to, path);
				}
			}
		}
		path.remove(path.last()); 
	}

	private static <V> boolean pathContainsVertex(PositionList<Vertex<V>> l, 
			Vertex<V> v) {
		for (Vertex<V> pathVertex : l) {
			if (pathVertex.equals(v)) return true;
		}
		return false;
	}


	public static <V,E> Set<Vertex<V>> findReachableWithNJumps (UndirectedGraph<V, E> g, 
			Vertex<V> from, 
			int nJumps) {
		Set<Vertex<V>> solution = new HashTableMapSet<>(); 

		findReachableWithNJumps(g, from, nJumps, solution);
		return solution;

	}
	public static <V,E> Set<Vertex<V>> findReachablePred (
			UndirectedGraph<V, E> g, 
			Vertex<V> from, 
			Predicate<Vertex<V>> pred) {
		Set<Vertex<V>> solution = new HashTableMapSet<>(); 
		Set<Position<?>> visited = new HashTableMapSet<>(); 
		findReachablePred(g, from,  pred, solution, visited);
		return solution;

	}

	//	public static <V,E> Set<Vertex<V>> findReachablePred (
	//			UndirectedGraph<V, E> g, 
	//			Vertex<V> from, 
	//			Predicate<Pair<Vertex<V>,UndirectedGraph<V, E>>> pred) {
	//		Set<Vertex<V>> solution = new HashTableMapSet<>(); 
	//		Set<Position<?>> visited = new HashTableMapSet<>(); 
	////		findReachablePred(g, from,  pred, solution, visited);
	//		return solution;
	//
	//	}

	public static <V,E> void findReachableWithNJumps (UndirectedGraph<V, E> g, 
			Vertex<V> from,
			int jumps, 
			Set<Vertex<V>> solution) {

		if (jumps == 0) {
			solution.add(from);
			return;
		}

		for (Edge<E> e: g.edges(from)) {
			findReachableWithNJumps(
					g, g.opposite(from, e), jumps-1, solution);
		}
	}


	public static <V,E> void findReachablePred (UndirectedGraph<V, E> g, 
			Vertex<V> from,
			Predicate<Vertex<V>> pred, 
			Set<Vertex<V>> solution,
			Set<Position<?>> visited) {

		if(visited.contains(from))
			return;

		visited.add(from);

		if (pred.test(from)) {
			solution.add(from);
			return;
		}

		for (Edge<E> e: g.edges(from)) {
			findReachablePred(g, g.opposite(from, e), pred, solution, visited);
		}
	}

	public static <V> Map<Vertex<V>,Integer> shortestPaths(UndirectedGraph<V,Integer> g, Vertex<V> v) {
		Map<Vertex<V>,Integer> pathCosts = new HashTableMap<Vertex<V>,Integer>();
		for (Vertex<V> vertex : g.vertices()) {
			if (vertex == v)
				pathCosts.put(vertex,0);
			else
				pathCosts.put(vertex,1000000);
		}

		PriorityQueue<Integer,Vertex<V>> pq =
				new HeapPriorityQueue<Integer,Vertex<V>>();
		//new SortedListPriorityQueue<Integer,Vertex<V>>();
		Map<Vertex<V>,Entry<Integer,Vertex<V>>> notInCloud =
				new HashTableMap<Vertex<V>,Entry<Integer,Vertex<V>>>();
		Map<Vertex<V>,Edge<Integer>> froms =
				new HashTableMap<Vertex<V>,Edge<Integer>>();

		for (Vertex<V> vertex : g.vertices()) {
			System.out.println("enqueueing "+vertex);
			notInCloud.put(vertex, pq.enqueue(pathCosts.get(vertex),vertex));
		}

		while (!pq.isEmpty()) {
			Entry<Integer,Vertex<V>> entry = pq.dequeue();
			Vertex<V> vertex = entry.getValue();
			vertex.put("dotFillcolor","lightblue");
			PlotGraphs.plotGraph(g);
			System.console().readLine("\nselected "+vertex.element()+" of cost "+pathCosts.get(vertex)+"> ");

			notInCloud.remove(vertex);
			for (Edge<Integer> e : g.edges(vertex)) {
				Vertex<V> otherVertex = g.opposite(vertex,e);
				Entry<Integer,Vertex<V>> otherEntry = notInCloud.get(otherVertex);
				if (otherEntry != null) {
					checkEntry(otherEntry,pq);
					int edgeCost = e.element();
					int costVertex = pathCosts.get(vertex);
					int costOther = pathCosts.get(otherVertex);
					int newCostOther = costVertex + edgeCost;
					if (newCostOther < costOther) {
						pathCosts.put(otherVertex,newCostOther);
						pq.replaceKey(otherEntry,newCostOther);
						froms.put(otherVertex,e);
					}
				}
			}
		}
		System.out.println("froms are "+froms);
		return pathCosts;
	}

	private static <V> void checkEntry(Entry<Integer,Vertex<V>> entry,
			PriorityQueue<Integer,Vertex<V>> pq) {
		boolean hasError = true;

		if (entry instanceof es.upm.aedlib.LocationAwareEntryImpl<?,?>) {
			es.upm.aedlib.LocationAwareEntryImpl<?,?> locEntry =
					(es.upm.aedlib.LocationAwareEntryImpl<?,?>)entry;
			if (locEntry.getLocation() == null) {
				System.out.println
				("\n*** Error: location of "+entry+" points to "+locEntry.getLocation());
			} else {
				Object obj = locEntry.getLocation().element();
				if (obj == null)
					System.out.println("\n*** Error: location of "+entry+" points to a null element");
				else if (!obj.equals(entry))
					System.out.println
					("\n*** Error: location of "+entry+" points to "+locEntry.getLocation().element());
				else hasError = false;
			}
		} else {
			System.out.println("Entry "+entry+" is not an location aware entry");
		}

		if (hasError) {
			System.out.println("\n Priority queue: "+pq);
			throw new RuntimeException();
		}
	}

	private static void waitConsole() {
		System.out.println("Escribe algo para seguir: ");
		Scanner in= new Scanner(System.in);

		String s = in.nextLine();

	}

	public static <V,E> void deepSearchNodes (DirectedGraph<V, E> g, 
			Vertex<V> n, 
			Set<Position<?>> visited ) {

		if  (visited.contains(n)) {
			return;
		}
		visited.add(n);
		//		n.put("dotFillcolor","lightblue");
		//		PlotGraphs.plotGraph(g,"depthFirst");
		//		waitConsole();

		for (Edge<E> e: g.outgoingEdges(n)) {
			deepSearchNodes(g, g.endVertex(e), visited);
		}
	}


	public static <V,E> boolean isReachableInSteps (UndirectedGraph<V, E> g, 
			Vertex<V> from,
			Vertex<V> to, 
			int n) {
		Set<Position<?>> visited = new HashTableMapSet<>(); 
		return isReachableInSteps(g, from, to, n, visited);
	}
	public static <V,E> boolean isReachableInSteps (UndirectedGraph<V, E> g, 
			Vertex<V> from,
			Vertex<V> to,
			int n, 
			Set<Position<?>> visited ) {

		if (from == to) {
			return true;
		}
		if  (n == 0 || visited.contains(from)) {
			return false;
		}

		visited.add(from);
		boolean reachable = false;
		Iterator<Edge<E>> it = g.edges(from).iterator();
		while (it.hasNext() && !reachable) {
			reachable = isReachableInSteps(g, g.opposite(from, it.next()), to, n-1, visited);
		}
		return reachable;
	}

	public static <V,E> boolean isReachableInSteps (DirectedGraph<V, E> g, 
			Vertex<V> from,
			Vertex<V> to, 
			int n) {
		Set<Position<?>> visited = new HashTableMapSet<>(); 
		return isReachableInSteps(g, from, to, n, visited);
	}
	public static <V,E> boolean isReachableInSteps (DirectedGraph<V, E> g, 
			Vertex<V> from,
			Vertex<V> to,
			int n, 
			Set<Position<?>> visited ) {

		if (from == to) {
			return true;
		}
		if  (n == 0 || visited.contains(from)) {
			return false;
		}

		visited.add(from);
		boolean reachable = false;
		Iterator<Edge<E>> it = g.outgoingEdges(from).iterator();
		while (it.hasNext() && !reachable) {
			reachable = isReachableInSteps(g, g.endVertex(it.next()), to, n,visited);
		}
		return reachable;
	}



}
