package eda1.practicas.practica02.parte01;

import java.util.*;

import eda1.practicas.auxiliar.Format;

public class Estudiante implements Comparable<Estudiante>, Iterable<AsignaturaNotas>{
    private final String alumnoId; //Identificador de alumno (clave única)
    private final ArrayList<AsignaturaNotas> matricula; //Relación 1:N, 1 estudiante --> N asiganturas; 1 asignatura --> M notas

    public Estudiante(String alumnoId) {
        //2 líneas
    	this.alumnoId = alumnoId.toLowerCase();
    	this.matricula = new ArrayList<>();
    }

    public void addAsignaturas(Asignatura... asignaturas) {
    	//1 for()
        for(Asignatura asignatura : asignaturas) {
        	AsignaturaNotas asignaturaNotas = new AsignaturaNotas(asignatura);
        	if(!this.matricula.contains(asignaturaNotas)) {
        		this.matricula.add(asignaturaNotas);
        	}
        }
    }

    public boolean addNotas(String asignaturaId, Double... notas) {
    	//Buscamos la asignatura (indexOf); Si no está, false; si está, añadimos la nota
    	//3 líneas
    	int indice = this.matricula.indexOf(new AsignaturaNotas(asignaturaId));
    	if(indice == -1)return false;
    	this.matricula.get(indice).addNotas(notas);
        return true;
    }

    public String getNotaMedia() {
        double suma = .0;
        for (AsignaturaNotas asignaturaNotas : matricula) {
			suma += Double.parseDouble(asignaturaNotas.getNotaMedia());
		}
        return this.matricula.size() == 0 ? "0.00" : Format.formatDouble(suma/this.matricula.size(),2); 
    }

    public String getNotaMedia(String asignaturaId) {
    	//Buscamos asignatura; si no está se devuelve null; en caso contrario devolvemos la nota media
    	//2 líneas
    	int indice = this.matricula.indexOf(new AsignaturaNotas(asignaturaId));
        return indice == -1 ? null : this.matricula.get(indice).getNotaMedia();
    }

    public void clear() {
    	for (AsignaturaNotas asignaturaNotas : matricula) {
			asignaturaNotas.clear();
		}
        this.matricula.clear();
    }

    @Override
    public String toString() {
    	//Cuidado con el orden..hay que ordenar this.matricula, ¿verdad? ¿Qué comparator utilizamos? 
        String result = "Estudiante con id = " + this.alumnoId;
        //1 for()
        this.matricula.sort(new AsignaturaNotasComp());
        for (AsignaturaNotas asignaturaNotas : matricula) {
			result += "\n\t" + asignaturaNotas.toString();
		}
        return result + "\n";
    }

    @Override
    public int compareTo(Estudiante other) {
        return alumnoId.compareTo(other.alumnoId);
    }

    @Override
    public Iterator<AsignaturaNotas> iterator() {
        return matricula.iterator();    
    }
    
}