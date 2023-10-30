package eda1.practicas.practica02.parte01;

import eda1.practicas.auxiliar.AVLTree;
import eda1.practicas.auxiliar.Format;
import eda1.practicas.auxiliar.Par;
import eda1.practicas.practica02.parte03.Stirnf;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class GestionEstudiantes implements Iterable<Par<Integer, ArrayList<Par<String, String>>>>{

	private final String centroId; // Identificador de Centro/Facultad
	private final AVLTree<Asignatura> asignaturasOfertadas; // Conjunto de asignaturas ofertadas por el Centro en un
															// curso académico en concreto
	private final AVLTree<Estudiante> estudiantesMatriculados; // Conjunto de alumnos matriculados en el Centro en un
																// curso académico en concreto

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
		for (Asignatura asignatura : asignaturas) {
			// 1 línea
			this.asignaturasOfertadas.add(asignatura);
		}
	}

	public void addEstudiantes(Estudiante... estudiantes) {
		// ¿por qué Eclipse señala como error this.estudiantes?
		for (Estudiante estudiante : estudiantes) {
			// 1 línea
			this.estudiantesMatriculados.add(estudiante);
		}
	}

	public boolean addMatricula(String estudianteId, String... asignaturasId) {
		// Método esencial...importante...básico...relevante.
		// Si el estudiante con identificador estudianteId no existe, devuelve false y
		// finaliza
		// En este caso se hará uso del método find()
		// 2 líneas
		Estudiante estudiante = this.estudiantesMatriculados.find(new Estudiante(estudianteId));
		if (estudiante == null)
			return false;

		// Asignatura por asignatura, se va comprobando si existe en la colección
		// this.asignaturasOfertadas
		// Si no existe, se ignora y se pasa a la siguiente
		// Si existe, se añade su referencia al estudiante (addAsignatura) para que
		// conste en su matrícula
		// 1 for()
		for (String asignatura : asignaturasId) {
			Asignatura asignaturaAux = this.asignaturasOfertadas.find(new Asignatura(asignatura));
			if (asignaturaAux == null)
				continue;
			estudiante.addAsignaturas(asignaturaAux);

		}
		return true;
		// Pregunta: ¿Para qué necesitamos this.asignaturasOfertadas? ¿Qué implicaría su
		// NO utilización? (pista: 1 objeto, múltiples referencias...)
	}

	public boolean addNotas(String estudianteId, String asignaturaId, Double... notas) {
		// Si el estudiante no existe (find()), se devuelve false.
		// 2 líneas
		Estudiante estudianteCurr = this.estudiantesMatriculados.find(new Estudiante(estudianteId));
		if (estudianteCurr == null)
			return false;
		return estudianteCurr.addNotas(asignaturaId, notas);
	}

	public String getNotaMedia(String estudianteId) {
		// Si el estudiante con identificador estudianteId no existe, se devuelve null;
		// en caso contrario, se devuelve su nota media
		// 2 líneas
		Estudiante estudiante = this.estudiantesMatriculados.find(new Estudiante(estudianteId));
		return estudiante == null ? null : estudiante.getNotaMedia();
	}

	public String getNotaMedia(String estudianteId, String asignaturaId) {
		// 2 líneas
		Estudiante estudiante = this.estudiantesMatriculados.find(new Estudiante(estudianteId));
		return estudiante == null ? null : estudiante.getNotaMedia(asignaturaId);
	}

	public String getNotaMediaAsignatura(String asignaturaId) {
		int cont = 0;
		double suma = .0;
		// 1 for()
		for (Estudiante estudiante : estudiantesMatriculados) {
			String aux = estudiante.getNotaMedia(asignaturaId);
			if (aux != null) {
				suma += Double.parseDouble(aux);
				cont++;
			}
		}
		return cont == 0 ? null : Format.formatDouble(suma / cont);
	}

	public String getEquipoDocenteEstudiante(String estudianteId) {
		// Si el estudiante con identificador estudianteId no existe, se devuelve "[]"
		Estudiante estudiante = this.estudiantesMatriculados.find(new Estudiante(estudianteId));
		if (estudiante == null)
			return "[]";
		ArrayList<String> result = new ArrayList<>();
		// 2 for() anidados
		for (AsignaturaNotas asignatura : estudiante) {
			for (String profesor : asignatura.getAsignatura()) {
				if (!result.contains(profesor)) {
					result.add(profesor);
				}
			}
		}
		// Cuidado con el orden...
		result.sort(null);
		return result.toString();
	}

	/**
	 * @param fileName
	 * @return
	 */
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
			if (line.isEmpty())
				continue;
			if (line.startsWith("#"))
				continue;
			if (line.startsWith("@")) {
				items = line.split("[ ]+");
				switch (items[0]) {
				case "@Asignaturas":
					// 1 for()
					
					int nAsignaturas = Integer.parseInt(items[1]);
					for (int i = 0; i < nAsignaturas; i++) {
						line = scan.nextLine().trim();
						items = line.split("[ ]+");
						Asignatura asignatura = new Asignatura(items[0], Integer.parseInt(items[1]));
						items = Arrays.copyOfRange(items, 2, items.length);
						asignatura.addDocentes(items);
						this.asignaturasOfertadas.add(asignatura);
					}
					break;
				case "@Estudiantes":
					// 1 for()

					int nEstudiantes = Integer.parseInt(items[1]);
					for (int i = 0; i < nEstudiantes; i++) {
						line = scan.nextLine().trim();
						Estudiante estudiante = new Estudiante(line);
						this.estudiantesMatriculados.add(estudiante);
					}
					break;
				case "@Matriculas":
					// 2 for() anidados

					int nMatriculas = Integer.parseInt(items[1]);
					for (int i = 0; i < nMatriculas; i++) {
						line = scan.nextLine().trim();
						items = line.split("[ ]+");
						addMatricula(items[0], Arrays.copyOfRange(items, 1, items.length));
						for (int j = 1; j < items.length; j++) {
							addMatricula(items[0], items[j]);
						}
					}
					break;
				case "@Notas":
					// 2 for() anidados
					int nNotas = Integer.parseInt(items[1]);
					for(int i = 0; i < nNotas; i++) {
						line = scan.nextLine().trim();
						items = line.split("[ \t]+");
						Double [] d = new Double[items.length-2];
						for(int j = 0; j < d.length; j++) {
							d[j] = Double.parseDouble(items[j+2]);
						}
						addNotas(items[0], items[1], d);
					}
					
					
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
	@Override
	public Iterator<Par<Integer, ArrayList<Par<String, String>>>> iterator() {
		AVLTree<Par<Integer, ArrayList<Par<String, String>>>> arbol = new AVLTree<>();
		for (Asignatura asignatura : asignaturasOfertadas) {
			Par<Integer, ArrayList<Par<String, String>>> par = arbol.find(new Par<>(asignatura.getCuatrimestre(), null));
			if(par == null) {
				arbol.add(par = new Par<>(asignatura.getCuatrimestre(), new ArrayList<>()));
			}
			par.getValue().add(new Par<>(asignatura.getAsignaturaId(), getNotaMediaAsignatura(asignatura.getAsignaturaId())));
		}
		return arbol.iterator();
	}
}
