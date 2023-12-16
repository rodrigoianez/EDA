package eda1.practicas.practica02.parte02;

import org.junit.jupiter.api.MethodOrderer;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import eda1.practicas.auxiliar.Par;
import eda1.practicas.auxiliar.Format;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class Practica02Parte02ExtraTest {
		 			
	@Test
	@Order(0)
	public void test00() {
		final int NUM_CLIENTES = (int) (Math.random() * 50 + 1);
		final int NUM_MASCOTAS_CLIENTE = (int) (Math.random() * 30 + 1);
		final int NUM_CITAS_MASCOTA = (int) (Math.random() * 100 + 1);
		final int NUM_VALORACIONES_CITA = (int) (Math.random() * 5 + 1);
		
	
		//Sin afectar a la funcionalidad de la Práctica02 - parte02, añade el código que sea necesario para que este test: (1) deje de tener errores y (2) funcione correctamente
		//Documenta, por ahora de manera informal y con total nivel de detalle: (1) cuál es el objetivo de este test; y (2) cuáles han sido los pasos que has seguido para la resolución de este problema
		GestionClientes gestion = new GestionClientes();
		for (int i=0; i<NUM_CLIENTES; i++) {
			String clienteId = "cliente" + Format.formatInt(i, 3);
			gestion.addCliente(clienteId);
			for (int j=0; j<NUM_MASCOTAS_CLIENTE; j++) {
				String mascotaId = "mascota" + Format.formatInt(j, 3);
				String especieId = j%2==0 ? "perro" : "gato";
				gestion.addMascota(clienteId, mascotaId, especieId);
				for (int k=0; k<NUM_CITAS_MASCOTA; k++) {
					int citaId = gestion.addCita(clienteId, mascotaId, especieId);
					for (int l=0; l<NUM_VALORACIONES_CITA; l++) {
						gestion.addValoracion(clienteId, mascotaId, especieId, citaId, "valoracion" + Format.formatInt(l, 3));
					}
				} 
			}
		}
		
		assertTrue(gestion.size()==NUM_CLIENTES);
		
		int numCitas = 0;
		for (Par<String, ArrayList<Par<String, Integer>>> parMain: gestion) {
			for (Par<String, Integer> parSecond: parMain.getValue()) {
				numCitas += parSecond.getValue();
			}
		}
		assertTrue(numCitas == NUM_CLIENTES * NUM_MASCOTAS_CLIENTE * NUM_CITAS_MASCOTA);
		gestion.clear();
		
		assertTrue(gestion.size()==0);
	}
}