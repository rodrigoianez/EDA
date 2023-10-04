package eda1.practicas.practica02.parte01;


import eda1.practicas.auxiliar.AVLTree;
import eda1.practicas.auxiliar.Format;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GestionEstudiantes {
    private final String centroId; //Identificador de Centro/Facultad
    private final AVLTree<Asignatura> asignaturasOfertadas; //Conjunto de asignaturas ofertadas por el Centro en un curso académico en concreto
    private final AVLTree<Estudiante> estudiantesMatriculados; //Conjunto de alumnos matriculados en el Centro en un curso académico en concreto

    public GestionEstudiantes(String centroId) {
        this.centroId = centroId.trim();
        this.asignaturasOfertadas = new AVLTree<>();
        this.estudiantesMatriculados = new AVLTree<>();
    }

    public void clear() {
        this.asignaturasOfertadas.clear();
        this.estudiantesMatriculados.clear();
    }

    public void addAsignaturas(Asignatura... asignaturas) {
        for (Asignatura asignatura: asignaturas) {
            //1 línea
        	//...
        }
    }

    public void addEstudiantes(Estudiante... estudiantes){
    	//¿por qué Eclipse señala como error this.estudiantes?
        for (Estudiante estudiante: this.estudiantes) {
            //1 línea
        	//...
        }
    }

    public boolean addMatricula(String estudianteId, String...asignaturasId) {
    	//Método esencial...importante...básico...relevante.
    	//Si el estudiante con identificador estudianteId no existe, devuelve false y finaliza
    	//En este caso se hará uso del método find()
        //2 líneas
    	//...
    	//Asignatura por asignatura, se va comprobando si existe en la colección this.asignaturasOfertadas
    	//Si no existe, se ignora y se pasa a la siguiente
    	//Si existe, se añade su referencia al estudiante (addAsignatura) para que conste en su matrícula
    	//1 for()
        //...
        return true;
        //Pregunta: ¿Para qué necesitamos this.asignaturasOfertadas? ¿Qué implicaría su NO utilización? (pista: 1 objeto, múltiples referencias...)
    }

    public boolean addNotas(String estudianteId, String asignaturaId, Double... notas){
    	//Si el estudiante no existe (find()), se devuelve false.
    	//2 líneas
        //...
        return estudianteCurr.addNotas(asignaturaId, notas);
    }

    public String getNotaMedia(String estudianteId) {
    	//Si el estudiante con identificador estudianteId no existe, se devuelve null; en caso contrario, se devuelve su nota media
    	//2 líneas
    	//...

    }

    public String getNotaMedia(String estudianteId, String asignaturaId) {
    	//2 líneas
    	//...
    }

    public String getNotaMediaAsignatura(String asignaturaId) {
    	int cont = 0;
    	double suma = .0;
    	//1 for()
    	//...
        return cont == 0 ? null : Format.formatDouble(suma/cont);
    }

    public String getEquipoDocenteEstudiante(String estudianteId) {
        //Si el estudiante con identificador estudianteId no existe, se devuelve "[]"
    	ArrayList<String> result = new ArrayList<>();
        //2 for() anidados
    	//...
    	//Cuidado con el orden...
    	//...
        return result.toString();
    }

    public boolean load(String fileName) {
        Scanner scan;
        String line;
        String[] items;
        this.asignaturasOfertadas.clear();
        this.estudiantesMatriculados.clear();
        try {
            scan = new Scanner(new File(fileName));
        } catch (IOException e) {
            return false;
        }
        while (scan.hasNextLine()) {
            line = scan.nextLine().trim();
            if (line.isEmpty()) continue;
            if (line.startsWith("#")) continue;
            if (line.startsWith("@")) {
                items = line.split("[ ]+");
                switch (items[0]){
                    case "@Asignaturas":
                    	//1 for()
                    	//...
                        break;
                    case "@Estudiantes":
                    	//1 for()
                    	//...
                        break;
                    case "@Matriculas":
                    	//2 for() anidados
                    	//...
                        break;
                    case "@Notas":
                    	//2 for() anidados
                        break;
                    default:
                        return false;
                }
            }
        }
        scan.close();
        return true;
    }

    @Override
    public String toString() {
        return this.centroId;
    }
}
