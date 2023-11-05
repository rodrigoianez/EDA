package eda1.practicas.practica02.parte01;

import java.util.Comparator;

public class AsignaturaNotasComp implements Comparator<AsignaturaNotas>{

	@Override
	public int compare(AsignaturaNotas asigNotas01, AsignaturaNotas asigNotas02) {
		int cmp = Integer.compare(asigNotas01.getAsignatura().getCuatrimestre(),	
				asigNotas02.getAsignatura().getCuatrimestre());
		return cmp != 0 ? cmp : asigNotas01.compareTo(asigNotas02);
	}

}
