package eda1.practicas.prueba01;

import java.util.ArrayList;

import eda1.practicas.auxiliar.AVLTree;
import eda1.practicas.auxiliar.Format;
import eda1.practicas.auxiliar.Par;
import eda1.practicas.practica02.parte02.Cliente;
import eda1.practicas.practica02.parte02.Mascota;

public class ClienteExtends extends Cliente{

	public ClienteExtends(String nombre) {
		super(nombre);
	}

	/*
	 * param result
	 * param cont
	 */
	public String resume() {
		String result = "[";
		for(Par<Mascota, ArrayList<ArrayList<String>>> par :  datos) {
			result += par.getKey();
			int cont = 0;
			for (ArrayList<String> par2 : par.getValue()) {
				 cont = par2.size();
			}
			result+= " <" + cont + ">, ";
		}
		return result.toString();
	}
	
	public static void main(String[] args) {
		ClienteExtends cliente = new ClienteExtends("pepe");

		cliente.addMascota("mascota01", "especie");
		cliente.addMascota("mascota02", "especie");
		cliente.addMascota("mascota03", "especie");
		
		for (int i=0; i<50; i++) {
			cliente.addCita("mascota01", "especie");
			for (int j=0; j<10; j++) {
				cliente.addValoracion("mascota01", "especie", i, "valoración" + Format.formatInt(j));
			}
			cliente.addCita("mascota02", "especie");
			for (int j=0; j<20; j++) {
				cliente.addValoracion("mascota02", "especie", i, "valoración" + Format.formatInt(j));
			}
			cliente.addCita("mascota03", "especie");
			for (int j=0; j<30; j++) {
				cliente.addValoracion("mascota03", "especie", i, "valoración" + Format.formatInt(j));
			}
		}

		String salidaEsperada = "[mascota01-especie <10>, mascota02-especie <20>, mascota03-especie <30>]";
		//Aquí puedes mostrar por Consola cliente.resume()
		System.out.println(cliente.resume());
		System.out.println(salidaEsperada.equals(cliente.resume()) ? "¡¡¡OK!!!" : "¡¡¡Error!!!");
	}
}
