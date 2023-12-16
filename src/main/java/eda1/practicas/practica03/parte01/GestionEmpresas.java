package eda1.practicas.practica03.parte01;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class GestionEmpresas {

	private final TreeMap<String, TreeMap<String, TreeSet<String>>> datos = new TreeMap<>();

	public boolean load(String fileName) {
		Scanner scan = null;
		this.datos.clear();
		try {
			scan = new Scanner(new File(fileName));
		} catch (IOException e) {
			return false;
		}
		while (scan.hasNextLine()) {
			String line = scan.nextLine().trim();
			if (line.isEmpty()) continue;
			if (line.startsWith("@")) continue;
			String[] elementos = line.split(" - ");		
			add(elementos[0], elementos[1], elementos[2]);
		}
		scan.close();
		return true;
	}

	public boolean add(String empresaId, String proyectoId, String ciudad) {
		TreeMap<String, TreeSet<String>> proyCurr = this.datos.get(empresaId);
		if (proyCurr == null) {
			this.datos.put(empresaId, proyCurr = new TreeMap<String, TreeSet<String>>());
		}
		TreeSet<String> ciudadesCurr = proyCurr.get(proyectoId);
		if (ciudadesCurr == null) {
			proyCurr.put(proyectoId, ciudadesCurr = new TreeSet<String>());
		}
		return ciudadesCurr.add(ciudad);
	}

	public int size() {
		return this.datos.size();
	}

	public Integer getNumProyectosEmpresa(String empresaId) {
		TreeMap<String, TreeSet<String>> proyectoCurr = this.datos.get(empresaId);
		if(proyectoCurr == null) return null;
		return proyectoCurr.size(); 
	}

	public Integer getNumCiudadesProyecto(String proyectoId) {
		for (TreeMap<String, TreeSet<String>> proyectoCurr : this.datos.values()) {
			TreeSet<String> aux = proyectoCurr.get(proyectoId);
			if (aux != null) return aux.size();
		}
		return null;
	}

	public Integer getNumCiudadesEmpresa(String empresaId) {
		TreeMap<String, TreeSet<String>> proyectocurr = this.datos.get(empresaId);
		if (proyectocurr == null) return null;
		TreeSet<String> aux = new TreeSet<String>();
		for (TreeSet<String> aux2 : proyectocurr.values()) {
			aux.addAll(aux2);
		}
		return aux.size();
	}
	

	@Override
	public String toString() {
		String result = "";
		for (Entry<String, TreeMap<String, TreeSet<String>>> par : this.datos.entrySet()) {
			result += par.getKey() + " -> [";
			for (Entry<String, TreeSet<String>> par2 : par.getValue().entrySet()) {
				result += par2.getKey() + ": " + par2.getValue().toString();
				if (par2.equals(par.getValue().lastEntry())) {
					result += "]\n";
				} else {
					result += ", ";
				}
			}
		}
		return result;
	}

	public TreeSet<String> getEmpresasCiudad(String ciudad) {
		TreeSet<String> empresasIdAux = new TreeSet<>();
		for (Entry<String, TreeMap<String, TreeSet<String>>> par : this.datos.entrySet()) {
			for (TreeSet<String> aux : par.getValue().values()) {
				if (aux.contains(ciudad)) {
					empresasIdAux.add(par.getKey());
					break;
				}
			}
		}
		return empresasIdAux;
	}

	public ArrayList<String> getProyectosCiudad(String ciudad) {
		ArrayList<String> proyectosIDaux = new ArrayList<>();
		for (Entry<String, TreeMap<String, TreeSet<String>>> par : this.datos.entrySet()) {
			for (Entry<String, TreeSet<String>> aux : par.getValue().entrySet()) {
				if (aux.getValue().contains(ciudad)) {
					proyectosIDaux.add(aux.getKey());
					
				}
			}
		}
		return proyectosIDaux;
	}
	
	 public TreeSet<String> getCiudadesEmpresa(String empresaId) {
		 TreeMap<String, TreeSet<String>> proyectocurr = this.datos.get(empresaId);
		 if (proyectocurr == null) return null;
		 TreeSet<String> aux = new TreeSet<String>();
	   	 for (TreeSet<String> aux2 : proyectocurr.values()) {
	   		 aux.addAll(aux2);
   		 }
		 return aux;
	}
}