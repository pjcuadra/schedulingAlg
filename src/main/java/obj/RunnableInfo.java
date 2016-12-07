/**
 * 
 */
package obj;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

/**
 * @author Pedro Cuadra
 *
 */
public abstract class RunnableInfo implements Comparable<RunnableInfo>{	
	protected double startTime = 0;
	protected boolean allocated = false;
	
	public abstract boolean checkRunnability(double currentTime);
	public abstract void updateInfo(Runnable vertex, SimpleDirectedWeightedGraph<Runnable, DefaultWeightedEdge> graph);

	public void allocate(Runnable vertex, double startTime)
	{
		this.allocated = true;		
		this.startTime = startTime;
	}
	
	public double getStartTime(Runnable vertex) {
		return startTime;
	}

	
}
