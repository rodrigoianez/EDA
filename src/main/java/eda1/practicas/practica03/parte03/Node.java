package eda1.practicas.practica03.parte03;
import java.util.ArrayList; 
import java.util.List;
import java.util.Objects;
public class Node<T extends Comparable<T>> implements Comparable<Node<T>>{
	private static int numNodes = 0; 
	private final int id;
	protected final ArrayList<T> components;
	public static void initializeNumNodes(){
	}
	@SafeVarargs
	public Node(T...components) {
		this.id = ++numNodes;
		this.components = new ArrayList<>(List.of(components));
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Node)) return false;
		return this.compareTo((Node<T>) o) == 0;
	}
	public String toString() {
		return this.components.toString();
	}
	public Object toStringWithNodeId() {
		return "Node #" + this.id + " -> " + toString();
	}
	public int compareTo(Node<T> o) {
		int long1 = this.components.size();
		int long2 = o.components.size();
		int longMax = Math.min(long1, long2);
		for (int i = 0; i < longMax; i++) {
			int comp = this.components.get(i).compareTo(o.components.get(i));
			if(comp!=0) return comp;
		}
		return Integer.compare(long1, long2);
	}
		
	public int hashCode(){
		return Objects.hash(components);
	}
}
