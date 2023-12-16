package eda1.practicas.practica03.parte02;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class GestionRepositorios {
	private final TreeMap<String, TreeMap<String, Integer>> articulos;
	private final TreeMap<String, TreeMap<String, TreeSet<String>>> datos;

	public GestionRepositorios() {
		this.articulos = new TreeMap<>();
		this.datos = new TreeMap<>();
	}

	public boolean add(String repositorioId, String articuloId, String... autoresId) {
		if (getRepositorio(articuloId) != null && !repositorioId.equals(getRepositorio(articuloId))) {
			return false;
		}
		TreeMap<String, TreeSet<String>> aux = this.datos.get(repositorioId);
		if (aux == null) {
			this.datos.put(repositorioId, aux = new TreeMap<String, TreeSet<String>>());
		}
		TreeSet<String> ts = aux.get(articuloId);
		if (ts == null) {
			aux.put(articuloId, ts = new TreeSet<String>());
			this.articulos.put(articuloId, new TreeMap<>());
		}
		for (String autor : autoresId) {
			ts.add(autor);
		}
		return true;
	}

	public String getRepositorio(String articuloId) {
		// 1 for()
		for (Entry<String, TreeMap<String, TreeSet<String>>> par : this.datos.entrySet()) {
			if (par.getValue().containsKey(articuloId)) {
				return par.getKey();
			}
		}
		return null;
	}

	public boolean loadArticulo(String articuloId, String file) {
		TreeMap<String, Integer> palabrasFrecuencia = this.articulos.get(articuloId);
		if (palabrasFrecuencia == null)
			return false;
		Scanner scan = null;
		try {
			scan = new Scanner(new File(file));
		} catch (IOException e) {
			return false;
		}
		while (scan.hasNextLine()) {
			String linea = scan.nextLine();
			if (linea.isEmpty())
				continue;
			for (String palabra : linea.split("[0123456789/(/)+-;,.¿?¡! ]+")) {
				palabra = palabra.toLowerCase().trim();
				if (palabra.isEmpty() || StopWords.isStopWord(palabra))
					continue;
				Integer n = palabrasFrecuencia.get(palabra);
				palabrasFrecuencia.put(palabra, n == null ? 1 : n + 1);
			}
		}
		return true;
	}

	public boolean load(String directorioEntrada, String file) {
		Scanner scan = null;
		String repositorioId = "";
		this.articulos.clear();
		this.datos.clear();

		try {
			scan = new Scanner(new File(directorioEntrada + file));
		} catch (IOException e) {
			return false;
		}

		while (scan.hasNextLine()) {
			String lineIn = scan.nextLine().trim();
			if (lineIn.isEmpty())
				continue;
			if (lineIn.startsWith("%"))
				continue;
			if (lineIn.startsWith("@")) {
				repositorioId = lineIn.substring(1);
				continue;
			}
			String[] items = lineIn.split("[ ]+");
			add(repositorioId, items[0], Arrays.copyOfRange(items, 2, items.length));
			loadArticulo(items[0],directorioEntrada+items[1]);
		}
		scan.close();
		return true;
	}

	public TreeSet<String> getArticulosId(String autorId) {
		TreeSet<String> result = new TreeSet<>();
		for (TreeMap<String, TreeSet<String>> par : datos.values()) {

			for (Entry<String, TreeSet<String>> par2 : par.entrySet()) {
				if (par2.getValue().contains(autorId)) {
					result.add(par2.getKey());
				}
			}
		}
		return result;
	}

	public TreeSet<String> getCoAutores(String autorId) {
		TreeSet<String> result = new TreeSet<>();
		for (TreeMap<String, TreeSet<String>> par : datos.values()) {
			for (TreeSet<String> par2 : par.values()) {
				if (par2.contains(autorId)) {
					result.addAll(par2);
				}
			}
		}
		result.remove(autorId);
		return result;
	}

	public TreeSet<String> getPalabrasClave(String autorId, int minFrec) {
		TreeMap<String, Integer> macroPalabrasFrecuencia = new TreeMap<>();
		TreeSet<String> resultado = new TreeSet<>();
		for (String articulo : getArticulosId(autorId)) {
			TreeMap<String, Integer> palabras = articulos.get(articulo);
			for (Entry<String, Integer> par : palabras.entrySet()) {
				Integer numero = macroPalabrasFrecuencia.get(par.getKey());
				macroPalabrasFrecuencia.put(par.getKey(), par.getValue() + (numero == null ? 0 : numero));
			}
		}
		for (Entry<String, Integer> par : macroPalabrasFrecuencia.entrySet()) {
			if (par.getValue() >= minFrec) {
				resultado.add(par.getKey() + "=" + par.getValue());
			}
		}
		return resultado;
	}

	public int size() {
		return this.datos.size();
	}

	public void clear() {
		this.articulos.clear();
		this.datos.clear();
	}

	@Override
	public String toString() {
		String result = "";
		for (Entry<String, TreeMap<String, TreeSet<String>>> par : this.datos.entrySet()) {
			result += par.getKey() + "\n";
			for (Entry<String, TreeSet<String>> par2 : par.getValue().entrySet()) {
				result += "\t" + par2.getKey() + " <" + this.articulos.get(par2.getKey()).size() + " palabras> "
						+ par2.getValue() + "\n";
			}

		}
		return result;
	}
}