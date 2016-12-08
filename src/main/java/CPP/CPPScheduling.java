/**
 * @author Pedro Cuadra
 */
package CPP;

// SchedulingAlg
import obj.Runnable;
import obj.Scheduling;
import obj.Partition;
import utils.Graph;
import utils.Path;

// Java
import java.util.ArrayList;
import java.util.Collections;

public class CPPScheduling extends Scheduling{
	/**
	 * Critical Path
	 */
	private Path criticalPath;
	
	/**
	 * Constructor
	 * 
	 * @param graph graph to be schedule
	 */
	public CPPScheduling(Graph graph) {
		super(graph);
		
		// Calculate Critical Path 
		criticalPath = graph.getCriticalPath();
		
		// Assign CPP info to the runnables
		for (Runnable currRun: toAllocate)
		{
			currRun.setInfo(new CPPRunnableInfo(criticalPath));
		}
	}

	/**
	 * Push a runnable into the current allocating task
	 * 
	 * @param currTask current allocating task
	 * @param vertex runnable to be pushed
	 * @param startTime execution start time
	 * @return weight of the allocated runnable
	 */
	private double allocate(Partition currTask, Runnable vertex, double startTime)
	{
		currTask.addRunnable(vertex);
		vertex.allocate(startTime);
		toAllocate.remove(vertex);
		
		return vertex.getExecutionCost();
	}	
	
	/* (non-Javadoc)
	 * @see obj.Scheduling#Schedule()
	 */
	@Override
	public void Schedule() {
		double currentTime = 0, criticalPathTime = criticalPath.getPathCost();
	    ArrayList<Runnable> allocable;
		Partition currTask = null;
		
		// Add critical path to first task
		currTask = new Partition();
		partition.add(currTask);
		
		// Allocated all vertexes
		for (Runnable vertex: criticalPath.getAllRunnables())
		{
			currentTime += allocate(currTask, vertex, currentTime);
		}
		
		// Rest of algorithm goes here
		while (!toAllocate.isEmpty())
		{
			currentTime = 0;
			currTask =  new Partition();
			partition.add(currTask);
			
			// Within Critical Path execution time 
			while (currentTime < criticalPathTime)
			{
				// List of runnables that can be allocated
				allocable = new ArrayList<Runnable>();
				
				// Populate the list of allocable runnables
				for (Runnable currVer: toAllocate)
				{
					// Recalculate EIT and LIT
					currVer.updateInfo(graph);
					
					// Check if the task is allocable and add it to the list
					if (currVer.checkRunnability(currentTime))
					{
						allocable.add(currVer);
					}
				}
								
				if (allocable.size() == 0)
				{
					// This can be improved
					currentTime++;
					continue;
				}
				
				// Sort the runnables list
				Collections.sort(allocable);
				
				/* 
				 * If allocating first of the list will cause to exceed
				 * the critical path execution time then break and create
				 * a new task.
				 */
				
				if (currentTime + allocable.get(0).getExecutionCost() > criticalPathTime)
				{
			    	break;
				}
				
				// Allocate first of the list
				currentTime += allocate(currTask, allocable.get(0), currentTime);
			}
		}
	}
}
