package CPP;

import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import obj.Runnable;
import obj.RunnableInfo;
import utils.GraphUtils;

public class CPPRunnableInfo extends RunnableInfo implements Comparable<RunnableInfo> {
	public double earliesInitialTime;
	public double latestInitialTime;
	public double criticalPathTime;
	

	public CPPRunnableInfo(GraphPath<Runnable, DefaultWeightedEdge> criticalPath)
	{
		earliesInitialTime = 0;
		latestInitialTime = 0;
		criticalPathTime = GraphUtils.getPathWeigth(criticalPath);
	}
		
	public boolean checkRunnability(double currentTime)
	{
		return ((earliesInitialTime <= currentTime) && (latestInitialTime >= currentTime));
	}

	@Override
	public void updateInfo(Runnable vertex, SimpleDirectedWeightedGraph<Runnable, DefaultWeightedEdge> graph) {
		
		double currWeight = 0;
		
		earliesInitialTime = 0;
		latestInitialTime = criticalPathTime;
		
		for (Runnable currVer: graph.vertexSet())
		{
			// Calculate the earliest initial time
			/* eit =  max(sum(PrevVertex)) 
			 * Iterate thru all the paths that end in the vertex 
			 * and get maximum of the paths' weights (taking into 
			 * account if a node in the path is already allocated. */
			for (GraphPath<Runnable, DefaultWeightedEdge> path : GraphUtils.getAllPaths(currVer, vertex, graph))
			{
				currWeight = GraphUtils.getPathWeigthWithAllocations(path);				
				if (earliesInitialTime < currWeight)
				{
					earliesInitialTime = currWeight - vertex.weight; 
				}
			}
			
			// Calculate the latest initial time
			for (GraphPath<Runnable, DefaultWeightedEdge> path : GraphUtils.getAllPaths(vertex, currVer, graph))
			{
				currWeight = GraphUtils.getPathWeigthWithAllocations(path);				
				if (latestInitialTime < (criticalPathTime - currWeight))
				{
					latestInitialTime = criticalPathTime - currWeight; 
				}
			}
		}
	}

	@Override
	public int compareTo(RunnableInfo o) {
		CPPRunnableInfo info = (CPPRunnableInfo) o;
		Double lt0 = (this.latestInitialTime - this.earliesInitialTime);
		Double lt1 = (info.latestInitialTime - info.earliesInitialTime);
		Double eit0 = this.earliesInitialTime;
		Double eit1 = info.earliesInitialTime;
		
		if (lt0.compareTo(lt1) == 0)
		{
			return eit0.compareTo(eit1);
		}
		
		
		return lt0.compareTo(lt1);
	}
}
