package main;

import java.util.Iterator;
import java.util.LinkedList;

public class Mascota implements Comparable<Mascota>, Iterable<Cita> {
    private final String nombre;
    private final String especie;
    private final LinkedList<Cita> historial;

    public Mascota(String nombre, String especie) {
    	
    	this.nombre = nombre == null || nombre.isBlank() ? "sinNombre" : nombre.trim().toLowerCase();
    	this.especie = especie == null || especie.isBlank() ? "sinEspecie" : especie.trim().toLowerCase();
    	this.historial = new LinkedList<Cita>();
    }	

    public Cita addCita() {
       
    	//Cita c = new Cita();
    	//this.historial.add(c);
    	//return c;

    	 this.historial.add(new Cita());
    	 return this.historial.getLast();
    }

    public Cita getCita(int citaId) {
    	if(citaId < 0 ) return null;
    	int index = this.historial.indexOf(new Cita(citaId));
    	return index < 0 ? null : this.historial.get(index);
    	
    }

    public void clear() {
    	for(Cita c : historial) {
    		
    		c.clear();
    	}
        this.historial.clear();
    }

    public int size() {
        return this.historial.size();
    }

    @Override
    public String toString() {
        return  this.nombre + " <" + this.especie + "> [" + size() + " cita" + (size() == 1 ? "]" : "s]");
    }

    public String toStringExtended() {
        return this.toString() + ": " + this.historial.toString();
    }

    @Override
    public int compareTo(Mascota other) {
    	int c = this.nombre.compareTo(other.nombre);
    	return c == 0 ? this.especie.compareTo(other.especie) : c;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (!(obj instanceof Mascota)) return false;
        return this.compareTo((Mascota)obj) == 0; 
    }

    @Override
    public Iterator<Cita> iterator() {
        return this.historial.iterator();
    }
}