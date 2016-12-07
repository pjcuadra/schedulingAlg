package utils;

import java.util.List;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.AllDirectedPaths;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import obj.Runnable;

public class GraphUtils {
	
	public static List<GraphPath<Runnable, DefaultWeightedEdge>> getAllPaths(Runnable verSrc, Runnable verDst, SimpleDirectedWeightedGraph<Runnable, DefaultWeightedEdge> graph)
	{
		AllDirectedPaths<Runnable, DefaultWeightedEdge> allPaths = new AllDirectedPaths<Runnable, DefaultWeightedEdge>((DirectedGraph<Runnable, DefaultWeightedEdge>) graph);
		
		return allPaths.getAllPaths(verSrc, verDst, true, 100);
	}
	
	public static boolean checkVertexInPath(Runnable vertex, GraphPath<Runnable, DefaultWeightedEdge> path)
	{	
		
		return path.getVertexList().contains(vertex);
	}
	
	// TODO: Use communication costs
	public static double getPathWeigth(GraphPath<Runnable, DefaultWeightedEdge> path)
	{
		double weight = 0;
		
		for (Runnable vertex: path.getVertexList())
		{
			weight += vertex.weight;
		}
		
		return weight;
	}
	
	// TODO: Use communication costs
	public static double getPathWeigthWithAllocations(GraphPath<Runnable, DefaultWeightedEdge> path)
	{
		double weight = 0;
		
		for (Runnable vertex: path.getVertexList())
		{
			// If a runnable is already allocated
			if (vertex.getAllocate())
			{
				/* Start time of the allocated runnable
				 * is be the new origin.*/
				weight = vertex.getStartTime();
			}
			
			weight += vertex.weight;
		}
		
		return weight;
	}
	
	// TODO: Use communication costs
	public static GraphPath<Runnable, DefaultWeightedEdge> getCriticalPath(SimpleDirectedWeightedGraph<Runnable, DefaultWeightedEdge> graph){
		GraphPath<Runnable, DefaultWeightedEdge> criticalPath = null;
		
		// All vertex
		Set<Runnable> vertex = graph.vertexSet();
				
		// Try all combinations
		for (Runnable verSrc: vertex)
		{
			for (Runnable verDes: vertex)
			{
				for (GraphPath<Runnable, DefaultWeightedEdge> path: getAllPaths(verSrc, verDes, graph))
				{
					// For the first time let's use the first path we got
					if (criticalPath == null)
					{
						criticalPath = path;
						continue;
					}
					
					// For other times get the path with higher weights
					if (getPathWeigth(criticalPath) < getPathWeigth(path))
					{
						criticalPath = path;
					}
					
				}
			}
		}
		
		return criticalPath;
	}

}
