package eda1.practicas.practica02.parte01;

import eda1.practicas.auxiliar.Format;

import java.util.ArrayList;
import java.util.Iterator;

public class AsignaturaNotas implements Comparable<AsignaturaNotas>, Iterable<String>{
    private final Asignatura asignatura; 
    private final ArrayList<String> notas; //Atención al parámetro String...uso de eda1.practicas.auxiliar/Format

    public AsignaturaNotas(Asignatura asignatura) {
    	//2 líneas
    	//...
    }

    public AsignaturaNotas(String asignaturaId) {
    	//2 líneas
    	//...
    }
    
    public Asignatura getAsignatura() {
    	return this.asignatura;
    }

    public void addNotas(Double... notas) {
    	//Vamos a permitir que se inserten notas nulas. En caso de encontrar un null, internamente lo almacenamos como el String "0.00"
    	//1 for()
        //...
    }

    public String getNotaMedia() {
        double suma = .0;
        // 1 for()
        //...
        return //...;
    }

    public void clear() {
        this.notas.clear();
    }

    @Override
    public String toString() {
        return //...
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (this == other) return true;
        if (!(other instanceof AsignaturaNotas)) return false;
        return //...
    }

    @Override
    public int compareTo(AsignaturaNotas other) {
    	//Clave principal: this.asignatura (orden ascendente)
        return //...
    }

	@Override
	public Iterator<String> iterator() {
		//¿Seguro? Revisa bien esta propuesta....
		return this.notas.iterator();
	}
}