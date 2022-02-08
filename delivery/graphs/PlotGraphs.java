// Graph output which works in Linux

package upm.aed.graphs;

import es.upm.aedlib.Pair;
import es.upm.aedlib.Position;
import es.upm.aedlib.graph.Edge;
import es.upm.aedlib.graph.Vertex;
import es.upm.aedlib.graph.Graph;

import java.io.IOException;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;

// Plots graphs using the graphviz tool

public class PlotGraphs {
  public static <V,E> void toDot(Graph<V,E> g, String filePrefix) {
    try {
      File dotFile = new File(filePrefix + ".dot");
      BufferedWriter file = new BufferedWriter(new FileWriter(dotFile.getAbsolutePath()));
      file.write(g.toDot());
      file.close();
    } catch (IOException exc) { throw new RuntimeException(); }
  }

  public static <V,E> void toJPG(Graph<V,E> g, String filePrefix) {
    toDot(g,filePrefix);
    File dotFile = new File(filePrefix + ".dot");
    File jpgFile = new File(filePrefix + ".jpg");
    String command =
      "(dot -Tjpg "+dotFile.getAbsolutePath()+" > "+jpgFile.getAbsolutePath()+";)";
    try {
      Process process =
	new ProcessBuilder(new String[] {"bash", "-c",command}).start();
    } catch (IOException exc) { throw new RuntimeException(); }
  }

  public static <V,E> void visualizeGraph(Graph<V,E> g, String filePrefix) {
    toJPG(g,filePrefix);
    File jpgFile = new File(filePrefix + ".jpg");
        
    String command =
      "(eog "+jpgFile.getAbsolutePath()+")";
    try {
    Process process =
      new ProcessBuilder(new String[] {"bash", "-c",command}).start();
    } catch (IOException exc) { throw new RuntimeException(); }
  }

  public static <V,E> void plotGraph(Graph<V,E> g, String filePrefix) {
    visualizeGraph(g,filePrefix);
  }

  public static <V,E> void plotGraph(Graph<V,E> g) {
    plotGraph(g,"graph");
  }

}
