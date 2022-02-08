package upm.aed.graphs;

import es.upm.aedlib.graph.UndirectedAdjacencyListGraph;
import es.upm.aedlib.graph.UndirectedGraph;
import es.upm.aedlib.graph.Vertex;

public class Airlines {

	public static UndirectedGraph<String,String> defineFlightGraph() {
		UndirectedGraph<String,String> g =
				new UndirectedAdjacencyListGraph<String,String>();

		Vertex<String> madrid = g.insertVertex("madrid");
		Vertex<String> estocolmo = g.insertVertex("estocolmo");
		Vertex<String> frankfurt = g.insertVertex("frankfurt");
		Vertex<String> munich = g.insertVertex("munich");
		Vertex<String> zurich = g.insertVertex("zurich");
		Vertex<String> paris = g.insertVertex("paris");
		Vertex<String> amsterdam = g.insertVertex("amsterdam");
		Vertex<String> castellon = g.insertVertex("castellon");
		Vertex<String> ciudadReal = g.insertVertex("ciudad real");
		Vertex<String> newYork = g.insertVertex("new york");

		g.insertUndirectedEdge(madrid,munich,"lufthansa");
		g.insertUndirectedEdge(madrid,zurich,"swissair");
		g.insertUndirectedEdge(amsterdam,munich,"klm");
		g.insertUndirectedEdge(madrid,frankfurt,"lufthansa");
		g.insertUndirectedEdge(madrid,paris,"airfrance");
		g.insertUndirectedEdge(frankfurt,estocolmo,"lufthansa");
		g.insertUndirectedEdge(munich,estocolmo,"lufthansa");
		g.insertUndirectedEdge(zurich,estocolmo,"swissair");
		g.insertUndirectedEdge(amsterdam,estocolmo,"klm");
		g.insertUndirectedEdge(amsterdam,zurich,"klm");
		g.insertUndirectedEdge(paris,zurich,"airfrance");
		g.insertUndirectedEdge(madrid,estocolmo,"iberia");
		g.insertUndirectedEdge(madrid,estocolmo,"sas");
		g.insertUndirectedEdge(amsterdam,newYork,"us-airlines");
		
		g.insertUndirectedEdge(ciudadReal,castellon,"ryanair");

		return g;
	}

	public static UndirectedGraph<String,Integer> defineWeightedFlightGraph() {
		UndirectedGraph<String,Integer> g =
				new UndirectedAdjacencyListGraph<String,Integer>();

		Vertex<String> madrid = g.insertVertex("madrid");
		Vertex<String> estocolmo = g.insertVertex("estocolmo");
		Vertex<String> frankfurt = g.insertVertex("frankfurt");
		Vertex<String> munich = g.insertVertex("munich");
		Vertex<String> zurich = g.insertVertex("zurich");
		Vertex<String> paris = g.insertVertex("paris");
		Vertex<String> amsterdam = g.insertVertex("amsterdam");
		Vertex<String> castellon = g.insertVertex("castellon");
		Vertex<String> ciudadReal = g.insertVertex("ciudad real");
		Vertex<String> newYork = g.insertVertex("New York");

		g.insertUndirectedEdge(madrid,munich,100);
		g.insertUndirectedEdge(madrid,zurich,120);
		g.insertUndirectedEdge(madrid,frankfurt,80);
		g.insertUndirectedEdge(madrid,paris,200);
		g.insertUndirectedEdge(frankfurt,estocolmo,150);
		g.insertUndirectedEdge(amsterdam,munich,100);
		g.insertUndirectedEdge(munich,estocolmo,120);
		g.insertUndirectedEdge(zurich,estocolmo,90);
		g.insertUndirectedEdge(amsterdam,estocolmo,100);
		g.insertUndirectedEdge(amsterdam,zurich,100);
		g.insertUndirectedEdge(paris,zurich,200);
		g.insertUndirectedEdge(madrid,estocolmo,1000);
		g.insertUndirectedEdge(amsterdam,newYork,600);
		
		g.insertUndirectedEdge(ciudadReal,castellon,10);

		return g;
	}

	public static <V,E> Vertex<V> vertex(UndirectedGraph<V,E> g, V v) {
		for (Vertex<V> vert : g.vertices()) {
			if (v.equals(vert.element())) return vert;
		}
		return null;
	}

	public static void main(String args[]) {
		UndirectedGraph<String,String> f = defineFlightGraph();
//		PlotGraphs.plotGraph(f,"aviones");

//		UndirectedGraph<String,Integer> fp = defineWeightedFlightGraph();
//		PlotGraphs.plotGraph(fp,"airlinesWithPrice");

		System.out.println
		("\nreachable new york desde madrid? "+
				EjemplosUndirectedGraphs.isReachable
				(f,vertex(f,"madrid"),vertex(f,"new york")));

		System.out.println
		("\nreachable castellon desde madrid? "+
				EjemplosUndirectedGraphs.isReachable
				(f,vertex(f,"madrid"),vertex(f,"castellon")));

		System.out.println
		("\na path from madrid to estocolmo: "+
				EjemplosUndirectedGraphs.findOneSimplePath
				(f,vertex(f,"madrid"),vertex(f,"new york")));

		System.out.println("all paths from madrid to estocolmo:");
		EjemplosUndirectedGraphs.findAllSimplePaths
		(f,vertex(f,"madrid"),vertex(f,"estocolmo"));

//		System.out.println("where to go from madrid with 2 flights:" + 
//				EjemplosUndirectedGraphs.findReachableWithNJumps(f, vertex(f,"madrid"), 3));
		System.out.println("all paths from madrid to estocolmo:");
		EjemplosUndirectedGraphs.findAllSimplePaths2
		(f,vertex(f,"madrid"),vertex(f,"estocolmo"));
		
	}
	
}

