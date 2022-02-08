package upm.aed.graphs;

import java.util.ArrayList;
import java.util.Iterator;

import es.upm.aedlib.Position;
import es.upm.aedlib.fifo.FIFO;
import es.upm.aedlib.fifo.FIFOList;
import es.upm.aedlib.graph.Edge;
import es.upm.aedlib.graph.DirectedAdjacencyListGraph;
import es.upm.aedlib.graph.DirectedGraph;
import es.upm.aedlib.graph.Vertex;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.set.HashTableMapSet;
import es.upm.aedlib.set.Set;

public class Despertar {
  public static DirectedGraph<String,Void> defineDespertar() {
    DirectedGraph<String,Void> g =
      new DirectedAdjacencyListGraph<String,Void>();
    Vertex<String> despertar = g.insertVertex("despertar");
    Vertex<String> comidaMascota = g.insertVertex("comida a la mascota");
    Vertex<String> ducharse = g.insertVertex("ducharse");
    Vertex<String> salir = g.insertVertex("salir casa");
    Vertex<String> lavarDientes = g.insertVertex("lavar dientes");
    Vertex<String> desayunar = g.insertVertex("desayunar");
    Vertex<String> vestirse = g.insertVertex("vestirse");

    g.insertDirectedEdge(despertar,comidaMascota,null);
    g.insertDirectedEdge(despertar,ducharse,null);
    g.insertDirectedEdge(despertar,desayunar,null);
    g.insertDirectedEdge(desayunar,lavarDientes,null);
    g.insertDirectedEdge(ducharse,vestirse,null);
    g.insertDirectedEdge(comidaMascota,salir,null);
    g.insertDirectedEdge(vestirse,salir,null);
    g.insertDirectedEdge(lavarDientes,salir,null);
    g.insertDirectedEdge(desayunar,salir,null);
    return g;
  }

  public static void main(String args[]) {
    PlotGraphs.plotGraph(defineDespertar());
  }
}

