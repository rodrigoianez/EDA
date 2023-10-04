package eda1.practicas.practica02.parte01;

import java.util.Comparator;

public class AsignaturaNotasComp implements Comparator<AsignaturaNotas>{

	@Override
	public int compare(AsignaturaNotas asigNotas01, AsignaturaNotas asigNotas02) {
		//Orden alternativo propuesto
		//Clave principal: cuatrimestre (ascendente)
		//Clave secundaria: identificador de asignatura (asendente)
		//2 líneas
		int cmp = Integer.compare(...);
		return cmp != 0 ? //...
	}

}
