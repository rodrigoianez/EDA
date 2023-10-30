package eda1.practicas.practica02.parte01;

import eda1.practicas.auxiliar.Par;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class Practica02Parte01ExtraTest {

	String directorioEntrada = System.getProperty("user.dir") + File.separator +
			"src" + File.separator +
			"main" + File.separator +
			"java" + File.separator +
			"eda1" + File.separator +
			"practicas" + File.separator +
			"practica02" + File.separator +
			"parte01" + File.separator;

	
	//Añade a la parte01 de la práctica02 el código necesario para que: (1) el test deje de tener errores sintácticos y (2) funcione correctamente
	//En la salida esperada se muestran, ordenadas por cuatrimestre e identificador de asignatura, las asignaturas ofertadas por el centro y la nota media de la asignatura
	//No hay que utilizar la clase AsignaturaNotasComp, ¿verdad?
	
	@Test
	@Order(0)
	public void test00() {
		GestionEstudiantes gestion = new GestionEstudiantes("ESI");
		assertTrue(gestion.load(directorioEntrada + "datos.txt"));
		String salidaEsperada =  "Cuatrimestre 1: \n\t ec <5.09>, ip <4.27>\n" +
							     "Cuatrimestre 2: \n\t la <4.43>, mp <5.48>\n" +
						 	     "Cuatrimestre 3: \n\t eda1 <8.17>, si <4.23>\n" +
								 "Cuatrimestre 4: \n\t eda2 <4.03>, pss <4.98>\n" +
								 "Cuatrimestre 5: \n\t lps <5.22>, pis1 <3.08>\n" +
								 "Cuatrimestre 6: \n\t dra <3.87>\n" +
								 "Cuatrimestre 7: \n\t pis2 <3.02>\n";
		
		String salidaReal = "";
		//Vamos a analizar antes la Clase Par que está en el paquete eda1.practicas.auxiliar
		for(Par<Integer, ArrayList<Par<String, String>>> parMain: gestion) { 
			salidaReal += "Cuatrimestre " + parMain.getKey() + ": \n\t ";
			for (Par<String, String> parSecond: parMain.getValue()) {
				salidaReal += parSecond.toString();
				salidaReal += parSecond.equals(parMain.getValue().get(parMain.getValue().size()-1)) ? "\n" : ", ";
				
			}
		}
		assertEquals(salidaEsperada, salidaReal);
		gestion.clear();
	}
}