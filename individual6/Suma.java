package aed.individual6;

import es.upm.aedlib.graph.Edge;
import es.upm.aedlib.graph.Vertex;
import java.util.Iterator;
import es.upm.aedlib.Entry;
import es.upm.aedlib.graph.DirectedGraph;
import es.upm.aedlib.map.Map;
import es.upm.aedlib.map.HashTableMap;


public class Suma {
	
	public static <E> Map<Vertex<Integer>, Integer> sumVertices(DirectedGraph<Integer, E> g) {
		Map<Vertex<Integer>, Integer> result = new HashTableMap<Vertex<Integer>, Integer>(); 
		//recorrer todos los vertices del grafo g
		for (Vertex<Integer> v : g.vertices()) {
			
			int suma = 0; Iterator<Entry<Vertex<Integer>, Integer>> it= VAlcanzables(g, v).entries().iterator(); 
			//it recorre las entradas de los v alcanzables
			while(it.hasNext()) {
				Entry<Vertex<Integer>, Integer> entri= it.next();
				suma =suma+ entri.getValue();
				}
			
			result.put(v, suma); //anade la suma al mapa
		}
		return result;
	}

	/* metodos auxiliares*/
	/*VAlcanzables -> metodo de recorrido en profundiad:
		* Se para si -> encontramos camino sin salida por un nodo no visitado
		*/
	private static <E> Map<Vertex<Integer>,Integer> VAlcanzables(DirectedGraph<Integer, E> g, Vertex<Integer> n) {
		Map<Vertex<Integer>, Integer> vMap = new HashTableMap<Vertex<Integer>, Integer>();
		//vMap -> mapa de nodos visitados
		VAlcanzablesRec(g, n, vMap);
		return vMap;
	}

	private static <E> void VAlcanzablesRec(DirectedGraph<Integer, E> g, Vertex<Integer> n, Map<Vertex<Integer>,Integer> vMap) {
		if (vMap.containsKey(n)) {
			//si el mapa  contiene el v inicial -> se acaba (condicion parada)
			return;
		}
		else
		vMap.put(n,n.element());
		//usamos un for-each para recorrer todas las e salientes de dicho vertice
		//y ademas si el vertice no tiene e salientes el metodo acaba. 
		for (Edge<E> e : g.outgoingEdges(n)) {
			//recursividad de los vertices que son los finales de las e 
			//para ver si son alcanzables y anadirlos en el mapa
			VAlcanzablesRec(g, g.endVertex(e), vMap);
		}
	}
}
