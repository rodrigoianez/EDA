package eda1.practicas.prueba01;

import java.util.ArrayList;
import java.util.Arrays;

import eda1.practicas.auxiliar.AVLTree;
import eda1.practicas.auxiliar.Par;

public class ArrayListPar_vs_TreePar {
	
	private ArrayList<Par<String, ArrayList<Integer>>> array;
	private AVLTree<Par<String, AVLTree<Integer>>> arbol;
	
	public ArrayListPar_vs_TreePar() {
		this.array = new ArrayList<>();
		this.arbol = new AVLTree<>();
	}
	
	public void addArbol(String clave, Integer...datos) {
		Par<String, AVLTree<Integer>> current = this.arbol.find(new Par<>(clave, null));
		if (current == null) {
			this.arbol.add(current = new Par<>(clave, new AVLTree<>()));
		}
		for (Integer dato: datos) {
			current.getValue().add(dato);
		}
	}
	
	public void addArray(String clave, Integer...datos) {
		int index = array.indexOf(new Par <>(clave, null));
		
		if (index == -1) {
			array.add(new Par<>(clave, new ArrayList<>(datos)));
		} else {
			array.get(index).setValue(new ArrayList<>(datos));
		}
	}
	
	public String arbolToString() {
		return this.arbol.toString();
	}
	
	public String arrayToString() {
		return this.array.toString();
	}
	
	public static void main(String[] args) {
		ArrayListPar_vs_TreePar prueba = new ArrayListPar_vs_TreePar();
		prueba.addArbol("clave02", 0, 5, 2, 3, 8, 4, 4, 5);
		prueba.addArbol("clave01", 3, 1, 2, 4, 2, 4);
		prueba.addArbol("clave02", 0, 8, 6, 7, 8);
		prueba.addArbol("clave01", 1, 5, 3, 5, 8);
		prueba.addArray("clave02", 0, 5, 2, 3, 8, 4, 4, 5);
		prueba.addArray("clave01", 3, 1, 2, 4, 2, 4);
		prueba.addArray("clave02", 0, 8, 6, 7, 8);
		prueba.addArray("clave01", 1, 5, 3, 5, 8);
	
		//Aquí puedes mostrar por consola prueba.arbolToString() y prueba.arrayToString()
		
		System.out.println(prueba.arbolToString().equals(prueba.arrayToString()) ? "¡¡¡OK!!!" : "¡¡¡Error!!!");
	}

}
