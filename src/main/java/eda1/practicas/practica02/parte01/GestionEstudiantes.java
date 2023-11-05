package eda1.practicas.practica02.parte01;


import eda1.practicas.auxiliar.AVLTree;
import eda1.practicas.auxiliar.Format;
import eda1.practicas.auxiliar.Par;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class GestionEstudiantes implements Iterable<Par<Integer, ArrayList<Par<String, String>>>>{

	private final String centroId; 
	private final AVLTree<Asignatura> asignaturasOfertadas; 
	private final AVLTree<Estudiante> estudiantesMatriculados; 

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
			this.asignaturasOfertadas.add(asignatura);
		}
	}

	public void addEstudiantes(Estudiante... estudiantes) {
		for (Estudiante estudiante : estudiantes) {
			this.estudiantesMatriculados.add(estudiante);
		}
	}

	public boolean addMatricula(String estudianteId, String... asignaturasId) {
		Estudiante estudiante = this.estudiantesMatriculados.find(new Estudiante(estudianteId));
		if (estudiante == null)
			return false;
		for (String asignatura : asignaturasId) {
			Asignatura asignaturaAux = this.asignaturasOfertadas.find(new Asignatura(asignatura));
			if (asignaturaAux == null)
				continue;
			estudiante.addAsignaturas(asignaturaAux);

		}
		return true;
	}

	public boolean addNotas(String estudianteId, String asignaturaId, Double... notas) {
		Estudiante estudianteCurr = this.estudiantesMatriculados.find(new Estudiante(estudianteId));
		if (estudianteCurr == null)
			return false;
		return estudianteCurr.addNotas(asignaturaId, notas);
	}

	public String getNotaMedia(String estudianteId) {
		Estudiante estudiante = this.estudiantesMatriculados.find(new Estudiante(estudianteId));
		return estudiante == null ? null : estudiante.getNotaMedia();
	}

	public String getNotaMedia(String estudianteId, String asignaturaId) {
		Estudiante estudiante = this.estudiantesMatriculados.find(new Estudiante(estudianteId));
		return estudiante == null ? null : estudiante.getNotaMedia(asignaturaId);
	}

	public String getNotaMediaAsignatura(String asignaturaId) {
		int cont = 0;
		double suma = .0;
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
		Estudiante estudiante = this.estudiantesMatriculados.find(new Estudiante(estudianteId));
		if (estudiante == null)
			return "[]";
		ArrayList<String> result = new ArrayList<>();
		for (AsignaturaNotas asignatura : estudiante) {
			for (String profesor : asignatura.getAsignatura()) {
				if (!result.contains(profesor)) {
					result.add(profesor);
				}
			}
		}
		result.sort(null);
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
			if (line.isEmpty())
				continue;
			if (line.startsWith("#"))
				continue;
			if (line.startsWith("@")) {
				items = line.split("[ ]+");
				switch (items[0]) {
				case "@Asignaturas":
					
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

					int nEstudiantes = Integer.parseInt(items[1]);
					for (int i = 0; i < nEstudiantes; i++) {
						line = scan.nextLine().trim();
						Estudiante estudiante = new Estudiante(line);
						this.estudiantesMatriculados.add(estudiante);
					}
					break;
				case "@Matriculas":

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
