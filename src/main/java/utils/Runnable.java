package utils;

public class Runnable {
	public String name;
	public double weight;
	
	// Constraints here...
	
	public Runnable()
	{
		name = new String("");
		weight = 0;
	}
	
	public Runnable(String name, double weight)
	{
		this.name = new String(name);
		this.weight = weight;
	}
	
	public String toString()
	{
		return this.name + this.weight;
	}
}
