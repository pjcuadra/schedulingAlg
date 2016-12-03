package examples;

import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import CPP.CPPScheduling;
import obj.Runnable;

public class CPPExample {
	public static void main(String[] args){	
		SimpleDirectedWeightedGraph<Runnable, DefaultWeightedEdge> graph = 
				new SimpleDirectedWeightedGraph<Runnable, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		DefaultWeightedEdge edge;
						
				
		
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
		graph.addVertex(A);
		graph.addVertex(B);
		graph.addVertex(C);
		graph.addVertex(D);
		graph.addVertex(E);
		graph.addVertex(F);
		graph.addVertex(G);
		graph.addVertex(H);
		graph.addVertex(I);
		graph.addVertex(J);
		
		// Add dependencies
		edge = graph.addEdge(A, E);
		graph.setEdgeWeight(edge, 1);
		edge = graph.addEdge(B, F);
		graph.setEdgeWeight(edge, 1);
		edge = graph.addEdge(B, G);
		graph.setEdgeWeight(edge, 1);
		edge = graph.addEdge(B, J);
		graph.setEdgeWeight(edge, 1);
		edge = graph.addEdge(C, H);
		graph.setEdgeWeight(edge, 1);
		edge = graph.addEdge(D, J);
		graph.setEdgeWeight(edge, 1);
		edge = graph.addEdge(E, H);
		graph.setEdgeWeight(edge, 1);
		edge = graph.addEdge(F, I);
		graph.setEdgeWeight(edge, 1);
		edge = graph.addEdge(G, I);
		graph.setEdgeWeight(edge, 1);
		edge = graph.addEdge(H, I);
		graph.setEdgeWeight(edge, 1);
		
		CPPScheduling myCPPScheduler = new CPPScheduling(graph);
		myCPPScheduler.Schedule();
		
		System.out.println(myCPPScheduler);	
		
		
	}
	

	
}
