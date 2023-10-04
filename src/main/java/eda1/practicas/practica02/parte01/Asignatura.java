package eda1.practicas.practica02.parte01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class Asignatura implements Comparable<Asignatura>, Iterable<String>{
    private final String asignaturaId; //código de 
    private final int cuatrimestre; //dato informativo que utilizaremos para ordenar según criterio distinto al orden natural
    private final ArrayList<String> docentesId; //colección de docentes responsables de la asignatura
    
    public Asignatura(String asignaturaId, int cuatrimestre) {
    	//Si el parámetro asignaturaId es null o vacío, se le asignará el id "sinNombre" (atención mayúsculas y espacios en blanco delante y detrás....)
        //Si el parámetro cuatrimestre es menor que 1 o mayor que 8 se le asigna el valor 0
    	//Se inicializa la colección docentesId (fijaros siempre en el uso del singular/plural)
    	//3 líneas
    	//...
    }

    public Asignatura(String asignaturaId) {
    	//Este constructor lo queremos para hacer búsquedas, ¿verdad?
        //cuatrimestre a 0 y docentesId a null
    }

    public int getCuatrimestre() {
        return this.cuatrimestre;
    }

    public String getAsignaturaId() {
        return this.asignaturaId;
    }

    
    public void addDocentes(String... docentesId) {
    	//De forma interna, tratamos al parámetro docentesId (fíjate en la s) como si fuese un array simple
    	//1 for()
    	//...
    }

    public boolean esDocente(String docenteId) {
        if (docenteId == null || docenteId.isEmpty()) return false;
        return //...
    }

    public void clear() {
        this.docentesId.clear();
    }

    public String toStringDocentes(Comparator<String> comp) {
        //Ordenamos según la lógica del comparador comp. Coste de la operación?
    	//...
        return this.docentesId.toString();
    }

    @Override
    public String toString() {
    	//Método casi casi de regalo :)
        String cuatrimestre;
        switch (this.cuatrimestre) {
            case 1: cuatrimestre = "1º-1C"; break;
            case 2: cuatrimestre = "1º-2C"; break;
            case 3: cuatrimestre = "2º-1C"; break;
            case 4: cuatrimestre = "2º-2C"; break;
            case 5: cuatrimestre = "3º-1C"; break;
            case 6: cuatrimestre = "3º-2C"; break;
            case 7: cuatrimestre = "4º-1C"; break;
            case 8: cuatrimestre = "4º-2C"; break;

            default: cuatrimestre = "?º-?C";
        }
        return //...
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Asignatura)) return false;
        return this.compareTo((Asignatura)other) == 0;
    }

    @Override
    public int compareTo(Asignatura other) {
    	//Clave única this.asignaturaId (orden ascendente)
    	//1 única línea
        return //...
    }

	@Override
	public Iterator<String> iterator() {
		//Iterar sobre una asignatura equivale a iterar sobre la colección de profesores responsables de la misma
		//1 única línea
		return //...
	}
}