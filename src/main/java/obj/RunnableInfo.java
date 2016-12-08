/**
 * @author Pedro Cuadra
 */
package obj;

import utils.Graph;

public abstract class RunnableInfo implements Comparable<RunnableInfo>{	
	/**
	 * Execution start time
	 */
	protected double startTime = 0;
	/**
	 * Allocated state
	 */
	protected boolean allocated = false;
	
	/**
	 * Check if a runnable can be run in the current time
	 * 
	 * @param currentTime current time
	 * @return true if can be run at current time
	 */
	public abstract boolean checkRunnability(double currentTime);
	
	/**
	 *  Update Algorithm specific information 
	 * 
	 * @param vertex runnable
	 * @param graph graph
	 */
	public abstract void updateInfo(Runnable vertex, Graph graph);

	/**
	 * Set the runnable to be already allocated
	 * 
	 * @param vertex runnable
	 * @param startTime execution start time
	 */
	public void allocate(Runnable vertex, double startTime)
	{
		this.allocated = true;		
		this.startTime = startTime;
	}
	
	/**
	 * Get the execution start time the runnable
	 * 
	 * @param vertex runnable
	 * @return execution start time
	 */
	public double getStartTime(Runnable vertex) {
		return startTime;
	}
}
