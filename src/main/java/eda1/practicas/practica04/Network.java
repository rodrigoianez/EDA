package eda1.practicas.practica04;

import eda1.practicas.auxiliar.Graph;
import eda1.practicas.auxiliar.Par;

import java.util.TreeMap;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Iterator;

public class Network<Vertex extends Comparable<Vertex>> implements Graph<Vertex>, Iterable<Vertex> {

	private boolean directed; 	
	
	protected TreeMap<Vertex, TreeMap<Vertex, Double>> adjacencyList;
	
	public Network(){
		this.directed = true;
		this.adjacencyList = new TreeMap<>();
	}

  	public void setDirected(boolean esDirected) {
  		this.directed = esDirected;
  	}

  	public boolean isDirected() {
  		return this.directed;
  	}

  	@Override
  	public boolean isEmpty() {
    	return this.adjacencyList.isEmpty();
  	} 

  	@Override
  	public void clear() {
		this.adjacencyList.clear();
	}

  	@Override
  	public int numberOfVertices() {
    	return this.adjacencyList.size();
  	} 

  	@Override
  	public int numberOfEdges() {
  		int count = 0;
  		for (TreeMap<Vertex, Double> mapValues : this.adjacencyList.values())
  			count += mapValues.size();
  		return count;
  	} 

  	@Override
  	public boolean containsVertex(Vertex vertex) {
    	return this.adjacencyList.containsKey(vertex);
  	} 
  	
  	@Override
  	public boolean containsEdge(Vertex v1, Vertex v2) {
  		TreeMap<Vertex, Double> neighbors = this.adjacencyList.get(v1);
  		if (neighbors == null) return false;
    	return neighbors.containsKey(v2);
  	} 

  	@Override
  	public double getWeight (Vertex v1, Vertex v2) {
  		TreeMap<Vertex, Double> neighbors = this.adjacencyList.get(v1);
  		if (neighbors == null) return -1;
  		Double d = neighbors.get(v2);
  		return d == null ? -1 : d; 
   	} 

  	@Override
  	public Double setWeight (Vertex v1, Vertex v2, double w) {
  		TreeMap<Vertex, Double> neighbors = this.adjacencyList.get(v1);
  		if (neighbors == null) return -1.;
		return neighbors.put(v2, w);
	}

  	public boolean isAdjacent (Vertex v1, Vertex v2) {
  		TreeMap<Vertex, Double> neighbors = this.adjacencyList.get(v1);
  		return neighbors != null && neighbors.containsKey(v2);
	}

  	public boolean addVertex(Vertex vertex) {
        if (this.adjacencyList.containsKey(vertex)) return false;
        this.adjacencyList.put(vertex, new TreeMap<>());
        return true;
  	} 

  	public boolean addEdge(Vertex v1, Vertex v2, double w) {
  		if (!containsVertex(v1) || !containsVertex(v2)) return false;
  		this.adjacencyList.get(v1).put(v2, w);
       	if (!this.directed) {
        	this.adjacencyList.get(v2).put(v1, w);
       	}
    	return true;
  	} 

  	public boolean removeVertex(Vertex vertex) {
        if (!containsVertex(vertex)) return false;

        for (TreeMap<Vertex, Double> itMap: this.adjacencyList.values()) {
        	itMap.remove(vertex);
        } 
        this.adjacencyList.remove(vertex);
        return true;
   	} 

  	public boolean removeEdge (Vertex v1, Vertex v2) {
    	if (!containsEdge(v1,v2)) return false;

    	this.adjacencyList.get(v1).remove(v2);
    	
    	if (!this.directed) {
        	this.adjacencyList.get(v2).remove(v1);
    	}
    	
    	return true;
  	} 
  	
	@Override
  	public TreeSet<Vertex> vertexSet() {
    	return new TreeSet<>(this.adjacencyList.keySet());
  	}


  	public TreeSet<Vertex> getNeighbors(Vertex v) {
  		TreeMap<Vertex, Double> neighbors = this.adjacencyList.get(v);
  		return neighbors == null ? null : new TreeSet<>(neighbors.keySet());
  	}

  	@Override
  	public String toString() {
    	return this.adjacencyList.toString();
  	} 

	private TreeMap<Vertex, Par<Vertex, Double>> Dijkstra(Vertex source, Vertex destination) {
  		final double INFINITY = Double.MAX_VALUE;
      	TreeMap<Vertex, Par<Vertex, Double>> DS = new TreeMap<>();    	
    	TreeSet<Vertex> V_minus_S = new TreeSet<>();
     	
    	for (Vertex e : this.adjacencyList.keySet()) {
    		if (source.equals(e)) continue;
    		V_minus_S.add(e);
    	}
    	
    	for (Vertex v : V_minus_S){
    		DS.put(v, new Par<>(isAdjacent(source,v) ? source: null, isAdjacent(source,v) ? getWeight(source, v) : INFINITY));
    	}
    	
		DS.put(source,  new Par<>(source, 0.0));
		while (!V_minus_S.isEmpty()) {
		    double minWeight = INFINITY;
		    Vertex from = null;
		    
		    for (Vertex v : V_minus_S){
		    	if (DS.get(v).getValue() < minWeight) {
		    		minWeight = DS.get(v).getValue();
		    		from = v;
		    	}
		    }
	    	if (from == null) break;
	    	
			V_minus_S.remove(from);
				
		    for (Vertex v : V_minus_S){
		    	if (isAdjacent(from,v)){
		    		if (DS.get(from).getValue() + getWeight(from,v) < DS.get(v).getValue()){
		    			DS.put(v, new Par<>(from, DS.get(from).getValue() + getWeight(from,v)));
		    		}
		    	}
		    }
		}
		if (DS.get(destination) == null) {
			throw new RuntimeException("The vertex " + destination + " is not reachable from " + source);
		}
		return DS;
	}
	
	public ArrayList<Par<Vertex, Double>> getDijkstra(Vertex source, Vertex destination) {
		ArrayList<Par<Vertex, Double>> path = null;
    	Stack<Vertex> pila = null;
		TreeMap<Vertex, Par<Vertex, Double>> salidaDijkstra = null;
		
		if (source == null || destination == null) return null;
    	
    	if (source.equals(destination))	return null;
    	
    	
    	try {
    		salidaDijkstra = Dijkstra(source, destination);	
    	}catch(Exception e) {
    		System.out.println(e.getMessage());
    		return null;
    	}
    	path = new ArrayList<>();
    	pila = new Stack<>();
    	
		pila.push(destination);
		while (!pila.peek().equals(source)) {
			pila.push(salidaDijkstra.get(pila.peek()).getKey());
		}
		while (!pila.isEmpty()) {
			Vertex current = pila.pop();
			path.add(new Par<>(current, salidaDijkstra.get(current).getValue()));
		}
		return path;
	}
	
	public String getDijkstraWithInformation(Vertex source, Vertex destination) {
		ArrayList<Par<Vertex, Double>> resultDijkstra = getDijkstra(source, destination);
		if (resultDijkstra == null) return null;
		ArrayList<String> result = new ArrayList<>();
		result.add(resultDijkstra.get(0).getKey()+" <0.0>");
		//1 for() "clásico"		
		for(int i = 0; i< resultDijkstra.size()-1; i++) {
			double diff = resultDijkstra.get(i+1).getValue() - resultDijkstra.get(i).getValue();
			result.add(resultDijkstra.get(i+1).getKey() + " <+" + diff + " = " +resultDijkstra.get(i+1).getValue()+">");
		}
		return result.toString();
	} 
	
  
  	public ArrayList<Vertex> toArrayDepthFirstRecursive(Vertex start) {
  		if (!this.adjacencyList.containsKey(start)) return null;
  		ArrayList<Vertex> result = new ArrayList<>();
		TreeMap<Vertex,Boolean> visited = new TreeMap<>();
		for (Vertex v : this.adjacencyList.keySet()){
			visited.put(v,false);
		}
		
		toArrayDepthFirstRecursive(start, result, visited);
		return result;
	}
	
	private void toArrayDepthFirstRecursive(Vertex current, ArrayList<Vertex> result, TreeMap<Vertex,Boolean> visited) {
		result.add(current);
		visited.put(current, true);
		for (Vertex to : this.adjacencyList.get(current).descendingKeySet()) {
			if(visited.get(to)) {
				continue;
			}
			toArrayDepthFirstRecursive(to,result,visited);
		}
	}
	
	public ArrayList<Vertex> toArrayDepthFirst(Vertex start) {
		if (!this.adjacencyList.containsKey(start)) return null;
		ArrayList<Vertex> resultado = new ArrayList<>();
		Stack<Vertex> stack = new Stack<>();
		TreeMap<Vertex, Boolean> visited = new TreeMap<>();
		for (Vertex vertex : this.adjacencyList.keySet()) {
			visited.put(vertex, false);
		}
	
		stack.push(start);
		while (!stack.isEmpty()) {
			Vertex current = stack.pop();
			if (visited.get(current)) continue;
			visited.put(current, true);
			resultado.add(current);
			for (Vertex to : this.adjacencyList.get(current).keySet()) {
    			if (visited.get(to)) continue;
				stack.push(to);
			}
		}
		return resultado;
	}
	
	public ArrayList<Vertex> toArrayBreadthFirst(Vertex start) {
		if (!this.adjacencyList.containsKey(start)) return null;
		ArrayList<Vertex> resultado = new ArrayList<>();
		LinkedList<Vertex> queue = new LinkedList<>();
		TreeMap<Vertex, Boolean> visited = new TreeMap<>();
		for (Vertex vertex: this.adjacencyList.keySet()) {
			visited.put(vertex, false);
		}
		queue.add(start);
		while (!queue.isEmpty()) {
			Vertex current = queue.poll();
			if (visited.get(current)) 
				continue;
			visited.put(current, true);
			resultado.add(current);
			for (Vertex to : adjacencyList.get(current).keySet()) {
				if (visited.get(to)) 
					continue;
				queue.add(to);
			}
		}
		return resultado;
	}
	
	
	
	@Override
	public Iterator<Vertex> iterator() {
        return this.adjacencyList.keySet().iterator();
  	} 
 }
