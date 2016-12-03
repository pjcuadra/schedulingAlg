package obj;
/**
 * 
 */

import java.util.ArrayList;
/**
 * @author Pedro Cuadra
 *
 */
public class Task {
	
	static int id = 0;
	String name;
	ArrayList<Runnable> runnables;
	
	public Task() {
		runnables = new ArrayList<Runnable>();
		this.name =  "t" + id;
		id++;
	}
	
	public Task(String name) {
		runnables = new ArrayList<Runnable>();
		this.name = name;
		id++;
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
		String ret0 = new String();
		String ret1 = new String();
		int emptySpace = 0;
		int nameLength = this.name.length();
		
		ret0 = "+-";
		
		for (int i = 0; i < nameLength; i++)
		{
			ret0 += "-";
		}
		
		ret0 += "-+";
		ret1 += "| " + name + " |";
				
		for (Runnable run: runnables)
		{
			nameLength = run.name.length();
			
			for (; emptySpace < run.getStartTime(); emptySpace++)
			{
				
				for (int i = 0; i < nameLength + 1; i++)
				{
					ret0 += "-";
					ret1 += " ";
				}
				
				ret0 += "-+";
				ret1 += " |";
				
			}
			
			for (int o = 0; o < run.weight; o++)
			{
				ret1 += " " + run.name + " |";
				
				for (int i = 0; i < nameLength + 1; i++)
				{
					ret0 += "-";
				}
				
				ret0 += "-+";
			}
						
			emptySpace += run.weight;
		}
				
		return ret0 + "\n" + ret1 + "\n" + ret0;
	}
	

}
