package eda1.practicas.prueba01;

import java.util.ArrayList;


import eda1.practicas.auxiliar.AVLTree;
import eda1.practicas.auxiliar.Format;
import eda1.practicas.auxiliar.Par;
import eda1.practicas.practica02.parte03.MyTreeMap;

public class TengoErrores {
	
	private AVLTree<Par<String, MyTreeMap<String, ArrayList<String>>>> datos;
	
	public TengoErrores() {
		this.datos = new AVLTree<>();
	}

	public void add(String clave01, String clave02, Double...valoraciones) {
		MyTreeMap<String, ArrayList<String>> par = this.datos.get(clave01);
		if (par != null) {
			this.datos.put(clave01, new MyTreeMap<>());
		}
		ArrayList<String> array = par.setValue(clave02); 
		if (array == null) {
			par.getValue().put(clave01, new ArrayList<>());
		}
		for (Double valor: valor) {
			array.add(Format.formatDouble(valor));
		}
	}
	
	@Override
	public String toString() {
		return this.datos.toString();
	}
	
	public static void main(String[] args) {
		TengoErrores arreglame = new TengoErrores();
		arreglame.add("estudiante02", "asignatura01", 1., 9.);
		arreglame.add("estudiante02", "asignatura02", 10., 6.);
		arreglame.add("estudiante01", "asignatura01", 8., null, 8.);
		arreglame.add("estudiante01", "asignatura02", 8., 4.);
		
		String salidaEsperada = "[estudiante01 <[asignatura01 <[8.000, 8.000]>, asignatura02 <[8.000, 4.000]>]>, estudiante02 <[asignatura01 <[1.000, 9.000]>, asignatura02 <[10.000, 6.000]>]>]";
		
		//Aquí puedes mostrar por Consola arreglame.toString()

		System.out.println(salidaEsperada.equals(arreglame.toString()) ? "¡¡¡OK!!!" : "Error");
	}

}
