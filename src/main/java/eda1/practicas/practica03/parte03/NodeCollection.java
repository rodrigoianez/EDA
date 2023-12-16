package eda1.practicas.practica03.parte03;
import java.util.*;
import java.util.Map.Entry;
public class NodeCollection<T extends Comparable<T>> {	
	private final HashMap<Node<T>, HashSet<Node<T>>> data;
	public NodeCollection(){
		this.data = new HashMap<>(); 
	}
	public void add(Node<T> nodeO, Node<T> nodeD) {
		HashSet<Node<T>> setValues = this.data.get(nodeO);	
		if(setValues == null) {
			this.data.put(nodeO, setValues = new HashSet<>());
		}
		setValues.add(nodeD);
	}
	public int size() {
		return this.data.size();
	}
	public void clear(){
		for (HashSet<Node<T>> it : this.data.values()) {
			it.clear();
		}
		this.data.clear();
	}
	public HashSet<Node<T>> getNeighbours(Node<T> node) { 
		return this.data.get(node);
	}
	@Override
	public String toString() {
		return this.data.toString();
	}
	public String toStringOrdered(Comparator<Node<T>> comp) {
		TreeMap<Node<T>, TreeSet<Node<T>>> result = new TreeMap<>(comp);
		for (Entry<Node<T>, HashSet<Node<T>>> it : data.entrySet()) {
			TreeSet<Node<T>> aux = new TreeSet<>(comp);
			aux.addAll(it.getValue());
			result.put(it.getKey(), aux);
		}
		return result.toString();
	}

}

