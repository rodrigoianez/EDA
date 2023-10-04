package eda1.practicas.practica02.parte01;

import java.util.*;

import eda1.practicas.auxiliar.Format;

public class Estudiante implements Comparable<Estudiante>, Iterable<AsignaturaNotas>{
    private final String alumnoId; //Identificador de alumno (clave única)
    private final ArrayList<AsignaturaNotas> matricula; //Relación 1:N, 1 estudiante --> N asiganturas; 1 asignatura --> M notas

    public Estudiante(String alumnoId) {
        //2 líneas
    	//...
    }

    public void addAsignaturas(Asignatura... asignaturas) {
    	//1 for()
        //...
    }

    public boolean addNotas(String asignaturaId, Double... notas) {
    	//Buscamos la asignatura (indexOf); Si no está, false; si está, añadimos la nota
    	//3 líneas
    	//...
        return true;
    }

    public String getNotaMedia() {
        double suma = .0;
        //1 for()
        return //...
    }

    public String getNotaMedia(String asignaturaId) {
    	//Buscamos asignatura; si no está se devuelve null; en caso contrario devolvemos la nota media
    	//2 líneas
    	//...
        return //...
    }

    public void clear() {
    	//1 for()
        //...
        this.matricula.clear();
    }

    @Override
    public String toString() {
    	//Cuidado con el orden..hay que ordenar this.matricula, ¿verdad? ¿Qué comparator utilizamos? 
        String result = "Estudiante con id = " + this.alumnoId;
        //1 for()
        //...  
        return result + "\n";
    }

    @Override
    public int compareTo(Estudiante other) {
        return this.alumnoId.compareTo(other.alumnoId);
    }

    @Override
    public Iterator<AsignaturaNotas> iterator() {
        return //...
    }
    
}