/**
 * @author Pedro Cuadra
 */

package utils;

// Java
import java.util.List;

// JGrapht
import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultWeightedEdge;

// Scheduling Alg
import obj.Runnable;

/**
 * @author Pedro Cuadra
 *
 * This class is wrapper of JGraphT library paths providing only the necessary functionalities
 */
public class Path {
	/**
	 * JGraphT path
	 */
	private GraphPath<Runnable, DefaultWeightedEdge> path;
	
	/**
	 * Constructor
	 * 
	 * @param path JGraphT path
	 */
	public Path(GraphPath<Runnable, DefaultWeightedEdge> path)
	{
		this.path = path;
	}
	
	// TODO: Use communication costs
    /**
     * Get the cost of a path
     * 
     * @return path's cost
     */
    public double getPathCost()
	{
		double weight = 0;
			
		for (Runnable vertex: path.getVertexList())
		{
			weight += vertex.getExecutionCost();
		}
		
		return weight;
	}
	
	// TODO: Use communication costs
	/**
	 * Get the cost of a path taking already allocated runnables
	 * into account
	 * 
	 * @return path's cost
	 */
	public double getPathCostWithAllocatedRunnables()
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
			
			weight += vertex.getExecutionCost();
		}
		
		return weight;
	}
	
	/**
	 * Get list of all runnables in the path
	 * 
	 * @return list of runnables
	 */
	public List<Runnable> getAllRunnables()
	{
		return path.getVertexList();
	}

}
