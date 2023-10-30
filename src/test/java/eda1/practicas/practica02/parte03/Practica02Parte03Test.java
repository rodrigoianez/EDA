package eda1.practicas.practica02.parte03;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import eda1.practicas.auxiliar.Par;

 import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class Practica02Parte03Test {

	@Test
	@Order(0)
	public void test00() {
		MyTreeMap<String, ArrayList<Integer>> miMapa = new MyTreeMap<>();
		
		ArrayList<Integer> array1 = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9, 11));
		ArrayList<Integer> array2 = new ArrayList<>(Arrays.asList(2, 4, 6, 8, 10, 12));
		ArrayList<Integer> array3 = new ArrayList<>(Arrays.asList(1, 3, 5));
		ArrayList<Integer> array4 = new ArrayList<>(Arrays.asList(2, 4, 6));
		
		assertTrue(miMapa.put("pares", array2) == null);
		assertTrue(miMapa.put("impares", array1) == null);

		assertEquals("[impares <[1, 3, 5, 7, 9, 11]>, pares <[2, 4, 6, 8, 10, 12]>]", miMapa.toString());
		
		assertTrue(miMapa.put("pares", array4) != null);
		assertTrue(miMapa.put("impares", array3) != null);
		assertEquals("[impares <[1, 3, 5]>, pares <[2, 4, 6]>]", miMapa.toString());
		
		ArrayList<Integer> aux = miMapa.get("impares");
		aux.clear();
		assertEquals("[impares <[]>, pares <[2, 4, 6]>]", miMapa.toString());
		
		aux.addAll(Arrays.asList(1, 3, 5, 7));
		assertEquals("[impares <[1, 3, 5, 7]>, pares <[2, 4, 6]>]", miMapa.toString());
		
		ArrayList<String> claves = miMapa.keySet();
		assertEquals("[impares, pares]", claves.toString());
		claves.clear();
		assertEquals("[impares <[1, 3, 5, 7]>, pares <[2, 4, 6]>]", miMapa.toString());
		
		ArrayList<ArrayList<Integer>> valores = miMapa.valueSet();
		assertEquals("[[1, 3, 5, 7], [2, 4, 6]]", valores.toString());
		valores.clear();
		assertEquals("[impares <[1, 3, 5, 7]>, pares <[2, 4, 6]>]", miMapa.toString());
		
		ArrayList<Par<String, ArrayList<Integer>>> pares = miMapa.pairSet();
		assertEquals("[impares <[1, 3, 5, 7]>, pares <[2, 4, 6]>]", pares.toString());
		pares.clear();
		assertEquals("[impares <[1, 3, 5, 7]>, pares <[2, 4, 6]>]", miMapa.toString());
		
		miMapa.clear();
		assertEquals("[]", miMapa.toString());
		
	}
	
	@Test
	@Order(1)
	public void test01() {
		GestionEstudiantes gestion = new GestionEstudiantes("ESI");
	
		assertTrue(gestion.addNotas("john", "eda1", 10.0, 5.0, 7.0));
		assertTrue(gestion.addNotas("john", "mp", 10., .10));
		assertTrue(gestion.addNotas("britney", "eda2", 5., 5., 7.));
		assertTrue(gestion.addNotas("britney", "ip", 10., 10., 3.));


		assertEquals("6.42", gestion.getNotaMedia("john"));
		assertEquals("6.67", gestion.getNotaMedia("britney"));
		assertEquals(null, gestion.getNotaMedia("angelica"));

		assertEquals("7.33", gestion.getNotaMedia("john", "eda1"));
		assertEquals(null, gestion.getNotaMedia("john", "eda21"));
		assertEquals(null, gestion.getNotaMedia("jonathan", "eda1"));
		assertEquals("7.67", gestion.getNotaMedia("britney", "ip"));

		assertTrue(gestion.addNotas("britney", "eda1", 10., 10., 10.));

		assertEquals("10.00", gestion.getNotaMedia("britney", "eda1"));
		assertEquals("8.67", gestion.getNotaMediaAsignatura("eda1"));
		assertEquals("7.67", gestion.getNotaMediaAsignatura("ip"));
		assertEquals(null, gestion.getNotaMediaAsignatura("ip1"));
		
		gestion.clear();
	}

}
