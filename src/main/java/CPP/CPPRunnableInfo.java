/**
 * @author Pedro Cuadra
 */
package CPP;

import obj.Runnable;
import obj.RunnableInfo;
import utils.Graph;
import utils.Path;

public class CPPRunnableInfo extends RunnableInfo implements Comparable<RunnableInfo> {
	/**
	 * Earliest possible starting time of the runnable
	 */
	private double earliesInitialTime;
	/**
	 * Latest possible starting time of the runnable
	 */
	private double latestInitialTime;
	/**
	 * Critical Path execution time
	 */
	private double criticalPathTime;

	/**
	 * CPPRunnableInfo Constructor
	 * 
	 * @param criticalPath Critical Path of the graph
	 */
	public CPPRunnableInfo(Path criticalPath)
	{
		earliesInitialTime = 0;
		latestInitialTime = 0;
		criticalPathTime = criticalPath.getPathCost();
	}
		
	/* (non-Javadoc)
	 * @see obj.RunnableInfo#checkRunnability(double)
	 */
	public boolean checkRunnability(double currentTime)
	{
		return ((earliesInitialTime <= currentTime) && (latestInitialTime >= currentTime));
	}
	
	/**
	 * Calculate the maximum LSP
	 * 
	 * @param startVert start vertex of path
	 * @param endVert   end vertex of path
	 * @param graph     graph
	 * @return maximum LSP
	 */
	public double getMaxLSP(Runnable startVert, Runnable endVert, Graph graph)
	{
		double LSP = 0;
		double maxLSP = 0;
		
		for (Path path : graph.getAllPaths(startVert, endVert))
		{
			LSP = path.getPathCost();		
			if (maxLSP < LSP)
			{
				maxLSP = LSP;
			}
		}	
		
		return maxLSP;
	}
	
	/* (non-Javadoc)
	 * @see obj.RunnableInfo#updateInfo(obj.Runnable, org.jgrapht.graph.SimpleDirectedWeightedGraph)
	 */
	@Override
	public void updateInfo(Runnable vertex, Graph graph) {
		
		double currWeight = 0;
		double maxLSP = 0;
		
		earliesInitialTime = 0;
		latestInitialTime = 0;
		
		for (Runnable currVer: graph.getAllRunnables())
		{
			if (currVer.equals(vertex))
				continue;
			
			// Calculate the earliest initial time
			/* eit =  max(sum(PrevVertex)) 
			 * Iterate thru all the paths that end in the vertex 
			 * and get maximum of the paths' weights (taking into 
			 * account if a node in the path is already allocated. */
			for (Path path : graph.getAllPaths(currVer, vertex))
			{
				currWeight = path.getPathCostWithAllocatedRunnables();				
				if (earliesInitialTime < currWeight)
				{
					earliesInitialTime = currWeight - vertex.getExecutionCost(); 
				}
			}
			
			// Store the max LSP
			if (maxLSP < getMaxLSP(vertex, currVer, graph))
			{
				maxLSP = getMaxLSP(vertex, currVer, graph);
			}
			
		}
		
		// Calculate the latest initial time note 
		latestInitialTime = criticalPathTime - maxLSP;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(RunnableInfo o) {
		CPPRunnableInfo info = (CPPRunnableInfo) o;
		Double lt0 = (this.latestInitialTime - this.earliesInitialTime);
		Double lt1 = (info.latestInitialTime - info.earliesInitialTime);
		Double eit0 = this.earliesInitialTime;
		Double eit1 = info.earliesInitialTime;
		
		/* If both shifting potentials are equal compare
		 * the earliest starting time. 
		 */
		if (lt0.compareTo(lt1) == 0)
		{
			return eit0.compareTo(eit1);
		}
		
		
		return lt0.compareTo(lt1);
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String info = new String("");
		
		info += "eit: " + earliesInitialTime + ", lit: " + latestInitialTime;
		
		
		return info;
		
	}
}
