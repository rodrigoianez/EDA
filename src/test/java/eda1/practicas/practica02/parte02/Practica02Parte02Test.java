package eda1.practicas.practica02.parte02;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import eda1.practicas.auxiliar.Par;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class Practica02Parte02Test {
		 			
	
	@Test
	@Order(0)
	public void test00() {
		//Test ya resuleto para estudiar la funcionalidad de la clase Par<K,V> (eda1.practicas.auxiliar)
		
		//Ejemplo sencillo
		Par<String, Integer> parSimple = new Par<>("clave01", 0);
		assertEquals("clave01 <0>",parSimple.toString());
		assertEquals("clave01", parSimple.getKey());
		assertTrue(parSimple.getValue() == 0);
		parSimple.setValue(2);
		assertEquals("clave01 <2>",parSimple.toString());
		assertTrue(parSimple.compareTo(new Par<>("clave02", 234234)) < 0);
		assertTrue(parSimple.compareTo(new Par<>("clave00", -11234234)) > 0);
		assertTrue(parSimple.compareTo(new Par<>("clave01", 11234234)) == 0);
		
		//Ejemplo menos sencillo
		Par<String, ArrayList<Integer>> parMenosSimple = new Par<>("clave01", null);
		assertEquals("clave01 <null>",parMenosSimple.toString());
		assertEquals("clave01", parMenosSimple.getKey());
		assertTrue(parMenosSimple.getValue() == null);
		parMenosSimple.setValue(new ArrayList<>());
		assertEquals("[]", parMenosSimple.getValue().toString());
		
		parMenosSimple.getValue().add(3);
		parMenosSimple.getValue().add(5);
		parMenosSimple.getValue().add(7);
		assertEquals("[3, 5, 7]", parMenosSimple.getValue().toString());
		
		ArrayList<Integer> auxMenosSimple = parMenosSimple.getValue();
		
		auxMenosSimple.clear();
		assertEquals("[]", parMenosSimple.getValue().toString());
		
		auxMenosSimple.add(3);
		assertEquals("[3]", parMenosSimple.getValue().toString());
		
		auxMenosSimple = null;
		
		assertEquals("[3]", parMenosSimple.getValue().toString());
		assertEquals("clave01 <[3]>",parMenosSimple.toString());
		
		//Ejemplo de mayor complejidad (poca)
		
		Par<Mascota, ArrayList<String>> parPocoComplejo = new Par<>(new Mascota("toby", "perro"), new ArrayList<>());
		assertEquals("toby-perro <[]>", parPocoComplejo.toString());

		for (String valor: List.of("uno", "dos", "tres", "cuatro", "cinco")) {
			parPocoComplejo.getValue().add(valor);
		}

		assertEquals("toby-perro <[uno, dos, tres, cuatro, cinco]>", parPocoComplejo.toString());
		
		assertTrue(parPocoComplejo.compareTo(new Par<>(new Mascota("toby", "gato"), null)) > 0);
		assertTrue(parPocoComplejo.compareTo(new Par<>(new Mascota("toby", "z"), null)) < 0);
		assertTrue(parPocoComplejo.compareTo(new Par<>(new Mascota("toby", "perro"), new ArrayList<>())) == 0);

		ArrayList<String> auxPocoComplejo = parPocoComplejo.getValue();
		auxPocoComplejo.clear();
		assertEquals("toby-perro <[]>", parPocoComplejo.toString());
		
		//Ejemplo de mayor complejidad (media)
		
		Par<Mascota, ArrayList<ArrayList<String>>> parComplejo = new Par<>(new Mascota("toby", "perro"), new ArrayList<>());
		assertEquals("toby-perro <[]>", parComplejo.toString());
		
		//¿Lo completas tú....?
	}
	
	@Test
	@Order(1)
	public void test01() {
		Cliente cliente = new Cliente("pepe");
		assertTrue(cliente.addMascota("kiKE", "peRRo"));
		assertTrue(cliente.addMascota("kikE", "lorO"));
		assertTrue(cliente.addMascota("ramona", "gato"));
		assertFalse(cliente.addMascota("ramona", "gato"));
		
		assertEquals("[kike-loro <[]>, kike-perro <[]>, ramona-gato <[]>]", cliente.toString());
		
		Integer citaId;
		
		assertNotNull(citaId = cliente.addCita("kike", "perro"));
		assertTrue(citaId == 0);
		
		assertEquals("[kike-loro <[]>, kike-perro <[[]]>, ramona-gato <[]>]", cliente.toString());


		assertFalse(cliente.addValoracion("kike", "perro", -1, "valoracion de prueba"));
		assertFalse(cliente.addValoracion("kike", "perro", 0, ""));
		assertFalse(cliente.addValoracion("kike", "perro", 0, null));
		
		
		assertTrue(cliente.addValoracion("kike", "perro", citaId, "valoracion #1"));
		assertTrue(cliente.addValoracion("kike", "perro", citaId, "valoracion #2"));
		
		assertEquals("[kike-loro <[]>, kike-perro <[[valoracion #1, valoracion #2]]>, ramona-gato <[]>]", cliente.toString());
		
		assertNotNull(cliente.addCita("kike", "perro"));
		assertNotNull(cliente.addCita("kike", "perro"));
		assertNotNull(cliente.addCita("kike", "perro"));
		
		assertEquals("[kike-loro <[]>, kike-perro <[[valoracion #1, valoracion #2], [], [], []]>, ramona-gato <[]>]", cliente.toString());
		
		assertNull(cliente.addCita("kike", "perra"));
		assertFalse(cliente.addValoracion("kikes", "perro", 2, "valoracion #3"));
		assertTrue(cliente.addValoracion("kike", "perro", 1, "valoracion #3"));
		assertFalse(cliente.addValoracion("kike", "perro", 5, "valoracion #4"));
		assertTrue(cliente.addValoracion("kike", "perro", 3, "valoracion #4"));
		
		assertEquals("[kike-loro <[]>, kike-perro <[[valoracion #1, valoracion #2], [valoracion #3], [], [valoracion #4]]>, ramona-gato <[]>]", cliente.toString());
		
		assertFalse(cliente.addValoracion("ramona", "gato", 0, "valoracion #5"));
		assertNotNull(cliente.addCita("ramona", "gato"));
		assertNotNull(cliente.addCita("ramona", "gato"));
		assertTrue(cliente.addValoracion("ramona", "gato", 1, "valoracion #5"));
		assertTrue(cliente.addValoracion("ramona", "gato", 0, "valoracion #6"));
		
		assertEquals("[kike-loro <[]>, kike-perro <[[valoracion #1, valoracion #2], [valoracion #3], [], [valoracion #4]]>, ramona-gato <[[valoracion #6], [valoracion #5]]>]", cliente.toString());
		
		assertNull(cliente.getValoraciones("kike",  "loro", 0));
		assertNull(cliente.getValoraciones("ramona",  "gato", -1));
		assertNotNull(cliente.getValoraciones("kike",  "perro", 0));
		
		ArrayList<String> valoraciones = cliente.getValoraciones("kike", "perro", 0);
		
		assertEquals("[valoracion #1, valoracion #2]", valoraciones.toString());
		
		cliente.clear();
		
		assertEquals("[]", cliente.toString());
		assertEquals("[]", valoraciones.toString()); //Tiene que estar vacío, ¿verdad?
	}
}