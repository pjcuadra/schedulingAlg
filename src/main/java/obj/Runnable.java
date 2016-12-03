package obj;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class Runnable implements Comparable<Runnable> {
	public String name;
	public double weight;
	public RunnableInfo info;
	
	// Constraints here...
	
	public Runnable()
	{
		name = new String("");
		weight = 0;
		info = null;
	}
	
	public Runnable(String name, double weight)
	{
		this.name = new String(name);
		this.weight = weight;
		this.info = null;
	}
	
	public String toString()
	{
		return this.name + this.weight;
	}
	
	public void setInfo(RunnableInfo info)
	{
		this.info = info;
	}
	
	public void updateInfo(SimpleDirectedWeightedGraph<Runnable, DefaultWeightedEdge> graph)
	{
		if (info == null)
			return;
		
		info.updateInfo(this, graph);
	}
	
	public void allocate(double startTime)
	{
		if (info == null)
			return;
		
		info.allocate(this, startTime);
	}
	
	public boolean getAllocate()
	{
		if (info == null)
			return false;
		
		return info.allocated;
	}

	public double getStartTime() {
		if (info == null)
			return 0;
		
		return info.getStartTime(this);
	}

	public boolean checkRunnability(double currentTime) {
		if (info == null)
			return false;
		
		return info.checkRunnability(currentTime);
	}

	@Override
	public int compareTo(Runnable arg0) {
		// TODO Auto-generated method stub
		return this.info.compareTo(arg0.info);
	}
}
