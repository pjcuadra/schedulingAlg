package utils;
/**
 * 
 */

import java.util.ArrayList;

// JGrapht
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import utils.Runnable;

/**
 * @author pjcuadra
 *
 */
public abstract class Scheduling {
	
	protected ArrayList<Task> tasks;
	private SimpleDirectedWeightedGraph<Runnable, DefaultWeightedEdge> graph;
	
	public Scheduling(){
		tasks = new ArrayList<Task>();
	}
	
	public abstract void Schedule();

	/**
	 * @return the graph
	 */
	public SimpleDirectedWeightedGraph<Runnable, DefaultWeightedEdge> getGraph() {
		return graph;
	}

	/**
	 * @param graph the graph to set
	 */
	public void setGraph(SimpleDirectedWeightedGraph<Runnable, DefaultWeightedEdge> graph) {
		this.graph = graph;
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
