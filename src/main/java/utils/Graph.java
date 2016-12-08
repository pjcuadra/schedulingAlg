/**
 * @author Pedro Cuadra
 */

package utils;

// Scheduling Alg
import obj.Runnable;

// Java
import java.util.ArrayList;
import java.util.Set;

// JGrapht
import org.jgrapht.DirectedGraph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.AllDirectedPaths;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

/**
 * @author Pedro Cuadra
 *
 * This class is wrapper of JGraphT library graphs providing only the necessary functionalities
 */
public class Graph {
	/**
	 * JGraphT graph
	 */
	protected SimpleDirectedWeightedGraph<Runnable, DefaultWeightedEdge> graph;
	
	/**
	 * Constructor
	 */
	public Graph()
	{
		graph = new SimpleDirectedWeightedGraph<Runnable, DefaultWeightedEdge>(DefaultWeightedEdge.class);
	}
	
	/**
	 * Add runnable to graph
	 * 
	 * @param run
	 */
	public void addRunnable(Runnable run)
	{
		graph.addVertex(run);
	}
	
	/**
	 * Add dependency between runnables with custom communication cost
	 * 
	 * @param independent independent runnable
	 * @param dependent dependent runnable
	 * @param cost communication cost
	 */
	public void addDependency(Runnable independent, Runnable dependent, double cost)
	{
		DefaultWeightedEdge edge;
		
		edge = graph.addEdge(independent, dependent);
		graph.setEdgeWeight(edge, cost);
	}
	
	/**
	 * Add dependency between runnables
	 * 
	 * @param independent independent runnable
	 * @param dependent dependent runnable
	 */
	public void addDependency(Runnable independent, Runnable dependent)
	{
		DefaultWeightedEdge edge;
		
		edge = graph.addEdge(independent, dependent);
		graph.setEdgeWeight(edge, 0);
	}

	
	/**
	 * Get all possible paths between two runnables
	 * 
	 * @param srcRun source runnable
	 * @param dstRun destination runnable
	 * @return
	 */
	public ArrayList<Path> getAllPaths(Runnable srcRun, Runnable dstRun)
	{
		ArrayList<Path> allPahtsList = new ArrayList<Path>();
		AllDirectedPaths<Runnable, DefaultWeightedEdge> allPaths = new AllDirectedPaths<Runnable, DefaultWeightedEdge>((DirectedGraph<Runnable, DefaultWeightedEdge>) graph);
		
		for (GraphPath<Runnable, DefaultWeightedEdge> currPath: allPaths.getAllPaths(srcRun, dstRun, true, 100))
		{
			allPahtsList.add(new Path(currPath));
		}
		
		return allPahtsList;
	}
	
	/**
	 * Get all runnables within the graph
	 * 
	 * @return set of runnables
	 */
	public Set<Runnable> getAllRunnables()
	{
		return graph.vertexSet();
	}
	
	
	// TODO: Use communication costs
	/**
	 * Get the critical path of a graph
	 * 
	 * @return critical path
	 */
	public Path getCriticalPath(){
		Path criticalPath = null;
		
		// All vertex
		Set<Runnable> vertex = getAllRunnables();
				
		// Try all combinations
		for (Runnable verSrc: vertex)
		{
			for (Runnable verDes: vertex)
			{
				for (Path path: getAllPaths(verSrc, verDes))
				{
					// For the first time let's use the first path we got
					if (criticalPath == null)
					{
						criticalPath = path;
						continue;
					}
					
					// For other times get the path with higher weights
					if (criticalPath.getPathCost() < path.getPathCost())
					{
						criticalPath = path;
					}
					
				}
			}
		}
		
		return criticalPath;
	}

}
