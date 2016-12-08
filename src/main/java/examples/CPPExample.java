/**
 * This is an example of a runnables graph scheduled using Critical Path Partitioning algorithm
 *  
 * @author Pedro Cuadra
 * @version 0.1
 */
package examples;

import CPP.CPPScheduling;
import obj.Runnable;
import utils.Graph;

public class CPPExample {
	public static void main(String[] args){	
		Graph graph = new Graph();
						
		// Initialize graphs nodes (Runnables)
		Runnable A = new Runnable("A", 1);
		Runnable B = new Runnable("B", 3);
		Runnable C = new Runnable("C", 4);
		Runnable D = new Runnable("D", 9);
		Runnable E = new Runnable("E", 1);
		Runnable F = new Runnable("F", 2);
		Runnable G = new Runnable("G", 5);
		Runnable H = new Runnable("H", 3);
		Runnable I = new Runnable("I", 2);
		Runnable J = new Runnable("J", 4);
		
		// Add nodes to graph
		graph.addRunnable(A);
		graph.addRunnable(B);
		graph.addRunnable(C);
		graph.addRunnable(D);
		graph.addRunnable(E);
		graph.addRunnable(F);
		graph.addRunnable(G);
		graph.addRunnable(H);
		graph.addRunnable(I);
		graph.addRunnable(J);
		
		// Add dependencies with no communication cost
		graph.addDependency(A, E);
		graph.addDependency(B, F);
		graph.addDependency(B, G);
		graph.addDependency(B, J);
		graph.addDependency(C, H);
		graph.addDependency(D, J);
		graph.addDependency(E, H);
		graph.addDependency(F, I);
		graph.addDependency(G, I);
		graph.addDependency(H, I);
		
		// Create the CPP scheduling class based on the graph
		CPPScheduling myCPPScheduler = new CPPScheduling(graph);
		
		// Schedule the graph
		myCPPScheduler.Schedule();
		
		// Print out the results
		System.out.println(myCPPScheduler);	
	}
}
