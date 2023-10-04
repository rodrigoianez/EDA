package main;

import eda1.practicas.auxiliar.Format;

import java.util.ArrayList;

public class Cita {
    private static int numCitas = 0; 
    private final int citaId; 
    private final ArrayList<String> valoraciones; 

    public static void inicializaNumCitas() {
        numCitas = 0; //se podría escribir this.numCitas = 0 ????? No, ya que numCitas se ha definido como una variable static.
    }

    public Cita() {
    	this.citaId = ++numCitas;
    	this.valoraciones = new ArrayList<>();
    }

    public Cita(int citaId) {
    	this.citaId = citaId;
    	this.valoraciones = null; 
    }	

    public int getCitaId() {
        return this.citaId;
    }

    public boolean addValoracion(String valoracion) {
    	if(valoracion == null|| valoracion.isBlank()) return false; 
        this.valoraciones.add(valoracion.trim().toLowerCase());  
    	return true;
    }

    public boolean contienePalabra(String palabra){
    	if(palabra == null|| palabra.isBlank()) return false; 
    	for (String valoracion : valoraciones) {
    		if (valoracion.contains(palabra.trim().toLowerCase())) return true;
    	}
        return false;
    }

    public void clear() {
        this.valoraciones.clear();
    }

    @Override
    public String toString() {
        return "Cita #" + Format.formatInt(citaId, 4)  + " -> " + (this.valoraciones == null|| this.valoraciones.isEmpty() ? "<sin texto>" : this.valoraciones);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof  Cita)) return false; 
        return this.citaId == ((Cita)o).citaId;
    }
}	