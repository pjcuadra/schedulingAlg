/**
 * @author Pedro Cuadra
 */
package obj;

import java.util.ArrayList;

public class Partition {
	
	/**
	 * Partition's ID
	 */
	static int id = 0;
	/**
	 * Partition's name
	 */
	String name;
	/**
	 * Runnables allocated to partition
	 */
	ArrayList<Runnable> runnables;
	
	/**
	 * Constructor
	 */
	public Partition() {
		runnables = new ArrayList<Runnable>();
		this.name =  "p" + id;
		id++;
	}
	
	/**
	 * Constructor
	 * @param name custom name of the partition
	 */
	public Partition(String name) {
		runnables = new ArrayList<Runnable>();
		this.name = name;
		id++;
	}
	
	/**
	 * Set partition's name
	 * 
	 * @param name custom name of the partition
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Get partition's name
	 * 
	 * @return partition's name
	 */
	public String getName()
	{
		return name;
	}	
	
	/**
	 * Add runnable to partition
	 * 
	 * @param runnable runnable to be added
	 */
	public void addRunnable(Runnable runnable)
	{
		runnables.add(runnable);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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
			
			for (int o = 0; o < run.getExecutionCost(); o++)
			{
				ret1 += " " + run.name + " |";
				
				for (int i = 0; i < nameLength + 1; i++)
				{
					ret0 += "-";
				}
				
				ret0 += "-+";
			}
						
			emptySpace += run.getExecutionCost();
		}
				
		return ret0 + "\n" + ret1 + "\n" + ret0;
	}
	

}
