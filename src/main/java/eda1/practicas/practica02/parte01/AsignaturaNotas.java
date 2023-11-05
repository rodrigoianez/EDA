package eda1.practicas.practica02.parte01;

import eda1.practicas.auxiliar.Format;

import java.util.ArrayList;
import java.util.Iterator;

public class AsignaturaNotas implements Comparable<AsignaturaNotas>, Iterable<String>{
    private final Asignatura asignatura; 
    private final ArrayList<String> notas; 
    
    public AsignaturaNotas(Asignatura asignatura) {
    	this.asignatura = asignatura;
    	this.notas = new ArrayList<>();
    }

    public AsignaturaNotas(String asignaturaId) {
    	this.asignatura = new Asignatura(asignaturaId);
    	this.notas = new ArrayList<>();
    	
    }
    
    public Asignatura getAsignatura() {
    	return this.asignatura;
    }

    public void addNotas(Double... notas) {
        for(Double nota : notas) {
        	this.notas.add(nota == null ? "0.00" : Format.formatDouble(nota,2));
        	
        }
    }

    public String getNotaMedia() {
    	if(this.notas.size() == 0) return "0.00";
        double suma = .0;
        // 1 for()
        for(String nota : this.notas) {
        	suma += Double.parseDouble(nota);
        	
        }
        return Format.formatDouble(suma/this.notas.size(),2);
    }

    public void clear() {
        this.notas.clear();
    }

    @Override
    public String toString() {
        return this.asignatura + " -> " + this.notas + " <" + getNotaMedia() + ">";
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (this == other) return true;
        if (!(other instanceof AsignaturaNotas)) return false;
        return this.compareTo((AsignaturaNotas)other) == 0;    }

    @Override
    public int compareTo(AsignaturaNotas other) {
        return this.asignatura.compareTo(other.asignatura);
        		
    }

	@Override
	public Iterator<String> iterator() {
		return this.notas.iterator();
	}
}