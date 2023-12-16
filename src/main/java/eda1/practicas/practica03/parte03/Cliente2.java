package eda1.practicas.practica03.parte03;

import eda1.practicas.practica02.parte02.Cliente;

public class Cliente2 extends Cliente {

	public Cliente2(String nombre) {
		super(nombre);
	}
	
	public String toString() {
		return this.getNombre() + " -> <>";
	}
	

}
