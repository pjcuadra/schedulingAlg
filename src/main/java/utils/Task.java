package utils;
/**
 * 
 */

import java.util.ArrayList;
/**
 * @author Pedro Cuadra
 *
 */
public class Task {
	
	String name;
	ArrayList<Runnable> runnables;
	
	public Task() {
		runnables = new ArrayList<Runnable>();
	}
	
	public Task(String name) {
		runnables = new ArrayList<Runnable>();
		this.name = name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}	
	
	public void addRunnable(Runnable runnable)
	{
		runnables.add(runnable);
	}
	
	public String toString()
	{
		String ret = new String();
		
		ret += name + ": o->";
		for (Runnable run: runnables)
		{
			ret += run.toString() + "->";
		}
		
		ret += "*";
		
		return ret;
	}
	

}
