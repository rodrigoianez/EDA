package eda1.practicas.practica02.parte02;

import eda1.practicas.auxiliar.AVLTree;

import eda1.practicas.auxiliar.Par;

import java.util.ArrayList;
import java.util.Iterator;

public class Cliente implements Comparable<Cliente>, Iterable<Par<Mascota, ArrayList<ArrayList<String>>>>{
	
    protected final String nombre;
    
    public String getNombre() {
		return nombre;
	}

	protected final AVLTree<Par<Mascota, ArrayList<ArrayList<String>>>> datos;

    public Cliente(String nombre) {
        this.nombre = nombre.trim().toLowerCase();
        this.datos = new AVLTree<>();
    }

    public boolean addMascota(String nombre, String especie) {
    	
        Par<Mascota, ArrayList<ArrayList<String>>> mascotaAux = new Par<Mascota, ArrayList<ArrayList<String>>>(new Mascota(nombre, especie), null);
        Par<Mascota, ArrayList<ArrayList<String>>> mascotaCurr = this.datos.find(mascotaAux);
        
        if(mascotaCurr != null) return false;
        mascotaAux.setValue(new ArrayList<>());
       	return this.datos.add(mascotaAux);
    }

    public Integer addCita(String nombre, String especie) {
        Par<Mascota, ArrayList<ArrayList<String>>> mascotaCurr = this.datos.find(new Par<Mascota, ArrayList<ArrayList<String>>>(new Mascota(nombre, especie), null));
        if (mascotaCurr != null) {
        	mascotaCurr.getValue().add(new ArrayList<>());
        } else return null;
        return mascotaCurr.getValue().size()-1;
    }

    public boolean addValoracion(String nombre, String especie, int citaId, String valoracion) {
    	if(citaId < 0 || valoracion == null || valoracion.isBlank()) return false;
    	Par<Mascota, ArrayList<ArrayList<String>>> mascotaCurr = this.datos.find(new Par<Mascota, ArrayList<ArrayList<String>>>(new Mascota(nombre, especie), null));
    	if(mascotaCurr == null) return false;
    	if(citaId >= mascotaCurr.getValue().size()) return false;
    	return mascotaCurr.getValue().get(citaId).add(valoracion);
    }

    public ArrayList<String> getValoraciones(String nombre, String especie, int citaId) {
        if (citaId < 0) return null;
        Par<Mascota, ArrayList<ArrayList<String>>> mascotaCurr = this.datos.find(new Par<Mascota, ArrayList<ArrayList<String>>>(new Mascota(nombre, especie), null));
        if(mascotaCurr == null || citaId >= mascotaCurr.getValue().size()) return null;
        return mascotaCurr.getValue().get(citaId);
    }
    
    public void clear() {
        for (Par<Mascota, ArrayList<ArrayList<String>>> par : datos) {
        	for(ArrayList<String> array : par.getValue()) {
        		array.clear();
        	}
        par.getValue().clear();
        }
        this.datos.clear();
    }

    public int size() {
        return datos.size();
    }

    @Override
    public String toString() {
    	return this.nombre + " -> " + this.datos.isEmpty() == null ? "<>" : this.datos.toString();
    }

	@Override
	public int compareTo(Cliente o) {
		
		return this.nombre.compareTo(o.nombre);
		
	}

	@Override
	public Iterator<Par<Mascota, ArrayList<ArrayList<String>>>> iterator() {
		
		return this.datos.iterator();
	}
}