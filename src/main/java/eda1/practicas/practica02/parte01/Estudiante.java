package eda1.practicas.practica02.parte01;

import java.util.*;

import eda1.practicas.auxiliar.Format;

public class Estudiante implements Comparable<Estudiante>, Iterable<AsignaturaNotas>{
    private final String alumnoId;
    private final ArrayList<AsignaturaNotas> matricula; 

    public Estudiante(String alumnoId) {
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