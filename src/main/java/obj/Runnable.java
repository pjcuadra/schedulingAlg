/**
 * @author Pedro Cuadra
 */
package obj;

import utils.Graph;

public class Runnable implements Comparable<Runnable> {
	/**
	 * Name of the runnable
	 */
	public String name;
	/**
	 * Execution Cost
	 */
	private double executionCost;
	/**
	 * Info used for algorithm specific purpose
	 */
	private RunnableInfo info;
	
	public Runnable()
	{
		name = new String("");
		setExecutionCost(0);
		info = null;
	}
	
	/**
	 * Constructor
	 * 
	 * @param name name of the runnable
	 * @param executionCost execution cost of the runnable
	 */
	public Runnable(String name, double executionCost)
	{
		this.name = new String(name);
		this.setExecutionCost(executionCost);
		this.info = null;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		if (info != null)
			return this.name + (int) this.getExecutionCost() + " {" + info.toString() + "}";
		
		return this.name + this.getExecutionCost();
	}
	
	/**
	 * Set Algorithm specific information
	 * 
	 * @param info algorithm specific information
	 */
	public void setInfo(RunnableInfo info)
	{
		this.info = info;
	}
	
	
	/**
	 * Update Algorithm specific information 
	 * 
	 * @param graph dependencies graph
	 */
	public void updateInfo(Graph graph)
	{
		if (info == null)
			return;
		
		info.updateInfo(this, graph);
	}
	
	/**
	 * Set the runnable to be already allocated
	 * 
	 * @param startTime execution start time of the runnable
	 */
	public void allocate(double startTime)
	{
		if (info == null)
			return;
		
		info.allocate(this, startTime);
	}
	
	/**
	 * Get the allocated state of the runnable
	 * 
	 * @return allocated state
	 */
	public boolean getAllocate()
	{
		if (info == null)
			return false;
		
		return info.allocated;
	}

	/**
	 * Get the execution start time the runnable
	 * 
	 * @return execution start time
	 */
	public double getStartTime() {
		if (info == null)
			return 0;
		
		return info.getStartTime(this);
	}

	/**
	 * Check if a runnable can be run in the current time
	 * 
	 * @param currentTime current time
	 * @return true if can be run at current time
	 */
	public boolean checkRunnability(double currentTime) {
		if (info == null)
			return false;
		
		return info.checkRunnability(currentTime);
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Runnable arg0) {
		// TODO Auto-generated method stub
		return this.info.compareTo(arg0.info);
	}

	/**
	 * Get the execution cost of a runnable
	 * 
	 * @return
	 */
	public double getExecutionCost() {
		return executionCost;
	}

	/**
	 * Set the execution cost of a task
	 * @param executionCost
	 */
	public void setExecutionCost(double executionCost) {
		this.executionCost = executionCost;
	}
}
