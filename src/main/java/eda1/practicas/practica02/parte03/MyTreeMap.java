package eda1.practicas.practica02.parte03;

import eda1.practicas.auxiliar.BSTree;
import eda1.practicas.auxiliar.Par;

import java.util.ArrayList;

public class MyTreeMap<K extends Comparable<K>,V> {
	
	private final BSTree<Par<K,V>> treePair;

	public MyTreeMap(){
		this.treePair = new BSTree<>();
	}

	public V put(K key, V value) {
		//EL método put inserta el par (key, value) dentro de la estructura arbórea
		//Si la clave ya existe --> actualiza el antiguo valor con el nuevo que se le indica como parámetro de entrada (value)
		//Si la clave no existe --> se inserta
		//Siempre devuelve el valor antiguo (null si es la primera vez que se inserta)
		
		Par<K,V> parCurr = this.treePair.find(new Par<>(key, null));
		V vOld = null;
		if (parCurr == null) {
			this.treePair.add(new Par<>(key, value));
		}else {
			vOld = parCurr.setValue(value);
		}
		return vOld;
	}
	
	public V get(K key) {
		//Devolvemos el valor asociado a la clave; null si key no existe
		Par<K,V> parCurr = this.treePair.find(new Par<>(key, null));
		return parCurr == null ? null : parCurr.getValue();
	}
	
	public boolean containsKey(K key) {
		return this.get(key) != null;
	}
	
	public void clear() {
		this.treePair.clear();
	}
	
	public boolean isEmpty() {
		return this.treePair.isEmpty();
	}
 
	public int size() {
		return this.treePair.size();
	}

	public ArrayList<K> keySet(){
		ArrayList<K> resultado  = new ArrayList<>();
		//Resultado almacenará el conjunto de referencias a las claves contenidas en el árbol
		//1 for
		for (Par<K, V> par : treePair) {
			resultado.add(par.getKey());
		}
		return resultado;
	}
	
	public ArrayList<V> valueSet(){
		ArrayList<V> resultado  = new ArrayList<>();
		//Resultado almacenará el conjunto de referencias a los valores contenidos en el árbol
		//1 for
		for (Par<K, V> par : treePair) {
			resultado.add(par.getValue());
		}
		return resultado;
	}
	
	public ArrayList<Par<K,V>> pairSet(){
		ArrayList<Par<K,V>> resultado  = new ArrayList<>();
		//Resultado almacenará el conjunto de referencias a los pares contenidos en el árbol
		//1 for
		for (Par<K, V> par : treePair) {
			resultado.add(par);
		}
		return resultado;
	}

	@Override
	public String toString() {
		return this.pairSet().toString();
	}
}