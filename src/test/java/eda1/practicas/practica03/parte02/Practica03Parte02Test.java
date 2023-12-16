package eda1.practicas.practica03.parte02;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class Practica03Parte02Test {

	String directorioEntrada = System.getProperty("user.dir") + File.separator +
								"src" + File.separator +
								"main" + File.separator +
								"java" + File.separator +
								"eda1" + File.separator +
							    "practicas" + File.separator +
								"practica03" + File.separator +
								"parte02" + File.separator;

	
	@Test
	@Order(0)
	public void test00() {
		GestionRepositorios gestion = new GestionRepositorios(); 
		assertTrue(gestion.size() == 0);
		assertTrue(gestion.getRepositorio("articulo01") == null);
		assertTrue(gestion.getRepositorio("articulo02") == null);
		assertTrue(gestion.getRepositorio("articulo03") == null);
		assertTrue(gestion.getRepositorio("articulo04") == null);
		assertTrue(gestion.add("repositorio01", "articulo01", "pepe", "maRia", "juan"));
		assertEquals("repositorio01", gestion.getRepositorio("articulo01"));
		assertTrue(gestion.add("repositorio02", "articulo02", "pepe", "maRia", "juan"));
		assertEquals("repositorio02", gestion.getRepositorio("articulo02"));
		assertTrue(gestion.add("repositorio01", "articulo03", "adela", "maRia", "zacarias"));
		assertEquals("repositorio01", gestion.getRepositorio("articulo03"));
		assertTrue(gestion.add("repositorio01", "articulo04", "adela", "juan"));
		assertEquals("repositorio01", gestion.getRepositorio("articulo04"));
		assertTrue(gestion.add("repositorio01", "articulo04",  "manuel", "zacarias", "juan"));
		assertTrue(gestion.getRepositorio("articulo01") != null);
		assertEquals("repositorio01", gestion.getRepositorio("articulo04"));
		assertFalse(gestion.add("repositorio02", "articulo04",  "manuel", "zacarias", "juan"));
		assertTrue(gestion.getRepositorio("articulo01") != null);
		assertTrue(gestion.getRepositorio("articulo02") != null);
		assertTrue(gestion.getRepositorio("articulo03") != null);
		assertTrue(gestion.getRepositorio("articulo04") != null);
		
		assertEquals("repositorio01\n" + 
						"\tarticulo01 <0 palabras> [juan, maRia, pepe]\n" +
						"\tarticulo03 <0 palabras> [adela, maRia, zacarias]\n" +
						"\tarticulo04 <0 palabras> [adela, juan, manuel, zacarias]\n" +
				     "repositorio02\n" +
						"\tarticulo02 <0 palabras> [juan, maRia, pepe]\n", gestion.toString());

		assertEquals("[articulo01, articulo02]", gestion.getArticulosId("pepe").toString());
		assertEquals("[articulo03, articulo04]", gestion.getArticulosId("adela").toString());
		assertEquals("[]", gestion.getArticulosId("maria").toString());
		assertEquals("[articulo01, articulo02, articulo03]", gestion.getArticulosId("maRia").toString());
		assertEquals("[articulo01, articulo02, articulo04]", gestion.getArticulosId("juan").toString());
		assertEquals("[articulo03, articulo04]", gestion.getArticulosId("zacarias").toString());
		assertEquals("[adela, juan, maRia, manuel]", gestion.getCoAutores("zacarias").toString());
		assertEquals("[adela, maRia, manuel, pepe, zacarias]", gestion.getCoAutores("juan").toString());
		assertEquals("[juan, maRia]", gestion.getCoAutores("pepe").toString());
		assertEquals("[juan, maRia, manuel, zacarias]",gestion.getCoAutores("adela").toString());
	
		
		assertTrue(gestion.loadArticulo("articulo01", directorioEntrada + "articulo01.txt"));
		assertTrue(gestion.loadArticulo("articulo02", directorioEntrada + "articulo02.txt"));
		assertFalse(gestion.loadArticulo("articulo20", directorioEntrada + "articulo03.txt"));
		
		assertEquals("repositorio01\n" + 
						"\tarticulo01 <201 palabras> [juan, maRia, pepe]\n" +
						"\tarticulo03 <0 palabras> [adela, maRia, zacarias]\n" +
						"\tarticulo04 <0 palabras> [adela, juan, manuel, zacarias]\n" +
					 "repositorio02\n" +
						"\tarticulo02 <100 palabras> [juan, maRia, pepe]\n", gestion.toString());

		assertTrue(gestion.loadArticulo("articulo01", directorioEntrada + "articulo01.txt"));

		assertEquals("repositorio01\n" + 
						"\tarticulo01 <201 palabras> [juan, maRia, pepe]\n" +
						"\tarticulo03 <0 palabras> [adela, maRia, zacarias]\n" +
						"\tarticulo04 <0 palabras> [adela, juan, manuel, zacarias]\n" +
					 "repositorio02\n" +
						"\tarticulo02 <100 palabras> [juan, maRia, pepe]\n", gestion.toString());


		gestion.clear();
		assertTrue(gestion.size() == 0);
	}
	
	@Test
	@Order(1)
	public void test01() {
		GestionRepositorios gestion = new GestionRepositorios();
		assertFalse(gestion.load(directorioEntrada, "datosRepositoriosRios.txt"));
		assertTrue(gestion.load(directorioEntrada, "datos.txt"));
		assertTrue(gestion.size() == 3);
		assertTrue(gestion.load(directorioEntrada, "datos.txt"));
		assertTrue(gestion.size() == 3);
			
		assertEquals("[Titulo01_01, Titulo01_04, Titulo03_01, Titulo03_04]", gestion.getArticulosId("Juan").toString());
		assertEquals("[Titulo01_02, Titulo01_03, Titulo02_02, Titulo02_03, Titulo03_02, Titulo03_03]", gestion.getArticulosId("Manuela").toString());
		assertEquals("[]", gestion.getArticulosId("teresa").toString());

		assertEquals("[Alberto, Alejandro]", gestion.getCoAutores("Manuela").toString());
		assertEquals("[Alberto, Alejandro, Juan, Macarena]", gestion.getCoAutores("Pedra").toString());
		assertEquals("[]", gestion.getCoAutores("Manuel").toString());
		
		assertEquals("[algoritmo=7, algoritmos=8, base=7, clasificacion=9, como=7, datos=24, mineria=9, oa=16, tecnicas=9]", gestion.getPalabrasClave("Pedra", 7).toString());
		
		assertEquals("[clasificacion=7, datos=12, oa=16]", gestion.getPalabrasClave("Juan", 7).toString());
		assertEquals("[algoritmo=5, algoritmos=5, aprendizaje=5, clasificacion=7, como=5, datos=12, manera=6, oa=16, permite=5, repositorio=6]", gestion.getPalabrasClave("Juan", 5).toString());
		
		for (String autor: Arrays.asList("Juan", "Macarena", "Pedra", "Alberto", "Manuela", "Alejandro")) {  
			for (int i=1; i<=10; i++) {
				assertTrue(gestion.getPalabrasClave(autor, i).containsAll(gestion.getPalabrasClave(autor, i+1)));
			}
		}
 		assertTrue(gestion.getPalabrasClave("Pedra", 6).size() == 15);
		assertTrue(gestion.getPalabrasClave("Pedra", 1).size() == 365);
		assertTrue(gestion.getPalabrasClave("pedra", 1).size() == 0);
		
		
		gestion.clear();
	}
}