package obj;
/**
 * 
 */

import java.util.ArrayList;

// JGrapht
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

/**
 * @author pjcuadra
 *
 */
public abstract class Scheduling {
	
	protected ArrayList<Task> tasks;
	protected SimpleDirectedWeightedGraph<Runnable, DefaultWeightedEdge> graph;
	protected ArrayList<Runnable> toAllocate;
	
	public Scheduling(SimpleDirectedWeightedGraph<Runnable, DefaultWeightedEdge> graph){
		tasks = new ArrayList<Task>();
		this.graph = graph;
		
		toAllocate = new ArrayList<Runnable>();
		
		toAllocate.addAll(getGraph().vertexSet());
	}
	
	public abstract void Schedule();

	/**
	 * @return the graph
	 */
	public SimpleDirectedWeightedGraph<Runnable, DefaultWeightedEdge> getGraph() {
		return graph;
	}
	
	public String toString()
	{
		String ret = new String();
		for (Task task: tasks)
		{
			ret += task.toString() + "\n";
		}
		
		return ret;
	}
	

}
