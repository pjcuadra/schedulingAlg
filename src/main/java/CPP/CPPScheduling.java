package CPP;


// Java imports
import java.util.List;
import java.util.Set;

// JGraphT imports
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.DirectedGraph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.AllDirectedPaths;

// My imports
import utils.Runnable;
import utils.Scheduling;
import utils.Task;

public class CPPScheduling extends Scheduling{
	
	public GraphPath<Runnable, DefaultWeightedEdge> getCriticalPath(){
		GraphPath<Runnable, DefaultWeightedEdge> criticalPath = null;
		
		// All vertex
		Set<Runnable> vertex = getGraph().vertexSet();
				
		// Try all combinations
		for (Runnable verSrc: vertex)
		{
			for (Runnable verDes: vertex)
			{
				for (GraphPath<Runnable, DefaultWeightedEdge> path: getAllPaths(verSrc, verDes))
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
	
	private double getPathWeigth(GraphPath<Runnable, DefaultWeightedEdge> path)
	{
		double weight = 0;
		
		for (Runnable vertex: path.getVertexList())
		{
			weight += vertex.weight;
		}
		
		return weight;
	}
	
	private List<GraphPath<Runnable, DefaultWeightedEdge>> getAllPaths(Runnable verSrc, Runnable verDst)
	{
		AllDirectedPaths<Runnable, DefaultWeightedEdge> allPaths = new AllDirectedPaths<Runnable, DefaultWeightedEdge>((DirectedGraph<Runnable, DefaultWeightedEdge>) getGraph());
		
		return allPaths.getAllPaths(verSrc, verDst, true, 100);
	}

	@Override
	public void Schedule() {
		// TODO Auto-generated method stub
		GraphPath<Runnable, DefaultWeightedEdge> criticalPath = getCriticalPath();
		Task currTask = null;
		
		// Add critical path to first task
		currTask = new Task("t0");
		
		for (Runnable vertex: criticalPath.getVertexList())
		{
			currTask.addRunnable(vertex);
		}
		
		tasks.add(currTask);
		
		// Rest of algorithm goes here
	
		
		
	}

}
