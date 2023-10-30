package eda1.practicas.practica02.parte02;

import eda1.practicas.auxiliar.AVLTree;
import eda1.practicas.auxiliar.Par;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Cliente {
    private final String nombre;
    private final AVLTree<Par<Mascota, ArrayList<ArrayList<String>>>> data;

    public Cliente(String nombre) {
        this.nombre = nombre.trim().toLowerCase();
        this.data = new AVLTree<>();
    }

    public boolean addMascota(String nombre, String especie) {
    	
        Par<Mascota, ArrayList<ArrayList<String>>> mascotaAux = new Par<Mascota, ArrayList<ArrayList<String>>>(new Mascota(nombre, especie), null);
        Par<Mascota, ArrayList<ArrayList<String>>> mascotaCurr = this.data.find(mascotaAux);
        
        //Si la mascota existe, nos vamos...nada que hacer
        //En caso contrario, lo añadimos a la colección arborescente this.data teniendo en cuenta que debemos inicializar su ArrayList<> asociado (de primer nivel)
        //3 líneas
        
        if(mascotaCurr != null) return false;
        mascotaAux.setValue(new ArrayList<>());
       	return this.data.add(mascotaAux);
    }

    public Integer addCita(String nombre, String especie) {
        Par<Mascota, ArrayList<ArrayList<String>>> mascotaCurr = this.data.find(new Par<Mascota, ArrayList<ArrayList<String>>>(new Mascota(nombre, especie), null));
        //Si no existe la mascota, nos vamos
        //En caso contrario, añadimos un nuevo Arraylist<> de segundo nivel (cita)
        if (mascotaCurr != null) {
        	mascotaCurr.getValue().add(new ArrayList<>());
        } else return null;
        return mascotaCurr.getValue().size()-1;
    }

    public boolean addValoracion(String nombre, String especie, int citaId, String valoracion) {
    	//Vamos intentar resolver el problema creando, como mucho, 2 variables locales
    	//A mi me salen 6 líneas...¿y a tí?
    	if(citaId < 0 || valoracion == null || valoracion.isBlank()) return false;
    	Par<Mascota, ArrayList<ArrayList<String>>> mascotaCurr = this.data.find(new Par<Mascota, ArrayList<ArrayList<String>>>(new Mascota(nombre, especie), null));
    	if(mascotaCurr == null) return false;
    	if(citaId >= mascotaCurr.getValue().size()) return false;
    	return mascotaCurr.getValue().get(citaId).add(valoracion);
    }

    public ArrayList<String> getValoraciones(String nombre, String especie, int citaId) {
        if (citaId < 0) return null;
        Par<Mascota, ArrayList<ArrayList<String>>> mascotaCurr = this.data.find(new Par<Mascota, ArrayList<ArrayList<String>>>(new Mascota(nombre, especie), null));
        if(mascotaCurr == null || citaId >= mascotaCurr.getValue().size()) return null;
        return mascotaCurr.getValue().get(citaId);
    }
    
    public void clear() {
        for (Par<Mascota, ArrayList<ArrayList<String>>> par : data) {
        	for(ArrayList<String> array : par.getValue()) {
        		array.clear();
        	}
        par.getValue().clear();
        }
        this.data.clear();
    }

    public int size() {
        return this.data.size();
    }

    @Override
    public String toString() {
    	return this.nombre + " -> " + this.data.isEmpty() == null ? "<>" : this.data.toString();
    }
}