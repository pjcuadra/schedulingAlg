/**
 * @author Pedro Cuadra
 */
package obj;

import java.util.ArrayList;

import utils.Graph;


public abstract class Scheduling {
	
	/**
	 * Scheduling partition
	 */
	protected ArrayList<Partition> partition;
	/**
	 * Dependencies graph to be scheduled
	 */
	protected Graph graph;
	/**
	 * Not allocated runnables 
	 */
	protected ArrayList<Runnable> toAllocate;
	
	/**
	 * Constructor
	 * 
	 * @param graph dependencies graph to be scheduled
	 */
	public Scheduling(Graph graph){
		partition = new ArrayList<Partition>();
		this.graph = graph;
		
		toAllocate = new ArrayList<Runnable>();
		
		toAllocate.addAll(graph.getAllRunnables());
	}
	
	/**
	 * Schedule according to the selected algorithm
	 */
	public abstract void Schedule();
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		String ret = new String();
		for (Partition task: partition)
		{
			ret += task.toString() + "\n";
		}
		
		return ret;
	}
	

}
