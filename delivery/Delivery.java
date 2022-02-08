package aed.delivery;

import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.graph.DirectedGraph;
import es.upm.aedlib.graph.DirectedAdjacencyListGraph;
import es.upm.aedlib.graph.Vertex;
import es.upm.aedlib.graph.Edge;
import es.upm.aedlib.map.HashTableMap;
import es.upm.aedlib.set.HashTableMapSet;
import es.upm.aedlib.set.Set;
import java.util.Iterator;

public class Delivery<V> 
{
	private DirectedGraph<V, Integer> grafo;
	private V[] places;
	private Integer[][] gmat;


	// Construct a graph out of a series of vertices and an adjacency matrix.
	// There are 'len' vertices. A negative number means no connection. A non-negative
	// number represents distance between nodes.

	public Delivery(V[] places, Integer[][] gmat) 
	{
		this.places = places;
		this.gmat = gmat;
		this.grafo = new DirectedAdjacencyListGraph<V, Integer>(); 
		int k = 0; 
		int tam = places.length;
		while(k<tam) 
		{
			grafo.insertVertex(places[k]); 
			k++;
		}

		for(int i=0; i<tam; i++) 
		{
			for(int j=0; j<tam; j++) 
			{
				if(gmat[i][j]!=null) 
				{
					Vertex<V> start=null; Vertex<V> end=null; 
					for(Vertex<V> vertices: grafo.vertices()) 
					{
						if(vertices.element()==places[i])
							start=vertices;
						if(vertices.element()==places[j]) 
							end=vertices;
						if(start!=null&&end!=null) 
							break;
					}
					grafo.insertDirectedEdge(start, end, gmat[i][j]);
				}
			}  
		}
	}

	// Just return the graph that was constructed
	public DirectedGraph<V, Integer> getGraph() 
	{
		return this.grafo;
	}

	// Return a Hamiltonian path for the stored graph, or null if there is noe.
	// The list containts a series of vertices, with no repetitions (even if the path
	// can be expanded to a cycle).
	//	public PositionList <Vertex<V>> tour() 
	//	{
	//		PositionList<Vertex<V>> res = new NodePositionList<Vertex<V>> ();
	//		return null;
	//	}
	public PositionList <Vertex<V>> tour() { 
		PositionList<Vertex<V>> path = null;
		boolean found=false;
		Iterator<Vertex<V>> it = grafo.vertices().iterator();
		while(it.hasNext() && found==false) 
		{
			path = new NodePositionList<Vertex<V>>();
			Vertex<V> startVert = it.next();
			try {
				if (grafo.outDegree(startVert) != 0) 
				{
					path.addLast(startVert);
					path = tourRec(path, this.getGraph(), startVert);
					if(path.size()==places.length) 
						found=true;
				}
			}
			catch(java.lang.NullPointerException e) 
			{

			}
		}
		if(!found) 
			return null;
		return path;
	}

	private PositionList<Vertex<V>> tourRec(PositionList<Vertex<V>> path, DirectedGraph<V, Integer> graph, Vertex<V> vert) 
	{
		if(path.size() == places.length) 
			return path;
		for(Edge<Integer> edge: graph.outgoingEdges(vert)) 
		{
			if(edge != null) 
			{
				Vertex<V> connectedVert = graph.endVertex(edge); 
				if(!isInPath(path, connectedVert)) 
				{
					path.addLast(connectedVert);
					PositionList<Vertex<V>> res = tourRec(path, graph, connectedVert);
					if (res != null) 
						return res;
					else 
						path.remove(path.last());
				}
			}
		}
		return null;
	}


	public int length(PositionList<Vertex<V>> path) 
	{
		return lengthRec(path, path.first(), 0); 
	}

	private int lengthRec(PositionList<Vertex<V>> path, 
			Position<Vertex<V>> vert, 
			int suma) 
	{
		Position<Vertex<V>> sig = path.next(vert);
		if(vert.equals(path.last())) 
			return suma;
		 for(Edge<Integer> arista :grafo.outgoingEdges(vert.element())) { 
			if(grafo.endVertex(arista).equals(sig.element())) 
			{
				int res = arista.element() + lengthRec(path, path.next(vert), suma);  
				  return res; 
			}
		}
		return 0;
	}


	public String toString() 
	{
		return "Delivery";
	}

	/*
	 ********METODOS AUXILIARES********
	 */

	/*
	 * Devuelve true si el vertice dado esta en el path y false e.o.c
	 */

	private boolean isInPath (PositionList<Vertex<V>> path, Vertex<V> vertice)
	{
		boolean esta = false;
		Position<Vertex<V>> cursor = path.first();
		while (cursor != null && !esta)
		{
			if (cursor.element().equals(vertice))
			{
				esta = true;
			}
			cursor = path.next(cursor);
		}
		return esta;
	}



}