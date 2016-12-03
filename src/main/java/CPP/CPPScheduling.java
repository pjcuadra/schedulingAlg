package CPP;


// Java imports

// JGraphT imports
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import obj.Runnable;
import obj.Scheduling;
import obj.Task;

import java.util.ArrayList;
import java.util.Collections;

import org.jgrapht.GraphPath;

import utils.GraphUtils;

public class CPPScheduling extends Scheduling{
	
	private GraphPath<Runnable, DefaultWeightedEdge> criticalPath;
	
	public CPPScheduling(SimpleDirectedWeightedGraph<Runnable, DefaultWeightedEdge> graph) {
		super(graph);
		
		criticalPath = GraphUtils.getCriticalPath(graph);
		
		for (Runnable currRun: toAllocate)
		{
			currRun.setInfo(new CPPRunnableInfo(criticalPath));
		}
	}		
	
	private double allocate(Task currTask, Runnable vertex, double startTime)
	{
		currTask.addRunnable(vertex);
		vertex.allocate(startTime);
		toAllocate.remove(vertex);
		
		return vertex.weight;
	}	
	
	@Override
	public void Schedule() {
		double currentTime = 0, criticalPathTime = GraphUtils.getPathWeigth(criticalPath);
	    ArrayList<Runnable> allocable;
		Task currTask = null;
		
		// Add critical path to first task
		currTask = new Task();
		tasks.add(currTask);
		
		// Allocated all vertexes
		for (Runnable vertex: criticalPath.getVertexList())
		{
			currentTime += allocate(currTask, vertex, currentTime);
		}
		
		
		// Rest of algorithm goes here
		while (!toAllocate.isEmpty())
		{
			currentTime = 0;
			currTask =  new Task();
			tasks.add(currTask);
			
			while (currentTime < criticalPathTime)
			{
				allocable = new ArrayList<Runnable>();
				
				for (Runnable currVer: toAllocate)
				{
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
				
				
				Collections.sort(allocable);
				
				if (currentTime + allocable.get(0).weight > criticalPathTime)
				{
					break;
				}
				
				currentTime += allocate(currTask, allocable.get(0), currentTime);
				
			}
			
			
		}
		
		
		
	}

}
