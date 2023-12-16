package eda1.practicas.practica02.parte02;

import java.util.ArrayList;
import java.util.Iterator;

import eda1.practicas.auxiliar.AVLTree;
import eda1.practicas.auxiliar.Par;

public class GestionClientes  implements Iterable <Par<String,ArrayList<Par<String,Integer>>>>{

	private AVLTree<Cliente> arbol;
	
	public GestionClientes() {
		this.arbol = new AVLTree<>();
	}

	public void addCliente(String clienteId) {
		this.arbol.add(new Cliente(clienteId));
		
	}

	public void addMascota(String clienteId, String mascotaId, String especieId) {
		
		Cliente cliente = this.arbol.find(new Cliente(clienteId));
		if (cliente == null) return;
		cliente.addMascota(mascotaId, especieId);
		
	}

	public int addCita(String clienteId, String mascotaId, String especieId) {
		Cliente cliente = this.arbol.find(new Cliente(clienteId));
		if (cliente == null) return -1;
		return cliente.addCita(mascotaId, especieId);
		
	}

	public void clear() {
		arbol.clear();
		
	}

	public int size() {
		return arbol.size();
	}

	@Override
	public Iterator<Par<String,ArrayList<Par<String,Integer>>>> iterator() {
		
		AVLTree<Par<String,ArrayList<Par<String,Integer>>>> datos = new AVLTree<>();
		for (Cliente cliente : arbol) {
			Par<String, ArrayList<Par<String,Integer>>> par1 = new Par<>(cliente.getNombre(), new ArrayList<>());
			datos.add(par1);
			for(Par<Mascota, ArrayList<ArrayList<String>>> par2 : cliente) {
				par1.getValue().add(new Par<>(par2.getKey().toString(), par2.getValue().size()));
			}
		}
		
		return datos.iterator();
	}

	public void addValoracion(String clienteId, String mascotaId, String especieId, int citaId, String valoracion) {
		Cliente cliente = this.arbol.find(new Cliente(clienteId));
		if (cliente == null) return;
		cliente.addValoracion(mascotaId, especieId, citaId, valoracion);
	}
}
