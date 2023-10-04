package eda1.practicas.practica01;

import eda1.practicas.auxiliar.Format;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class Practica01Test {
	private final String directorioEntrada = System.getProperty("user.dir") + File.separator +
											"src" + File.separator +
											"main" + File.separator +
											"java" + File.separator +
											"eda1" + File.separator +
											"practicas" + File.separator +
											"practica01" + File.separator;							           
	@Test
	@Order(0)
	public void test00(){
		Cita.inicializaNumCitas();
		Cita cita01 = new Cita();
		Cita cita02 = new Cita();
		Cita cita03 = new Cita();
		Cita cita04 = new Cita();
		Cita cita05 = new Cita();
		Cita cita06 = new Cita();

		assertTrue(cita01.addValoracion("primera revisión realizada"));
		assertTrue(cita02.addValoracion("segunda revisión realizada"));
		assertTrue(cita03.addValoracion("vacunada primera dosis"));
		assertFalse(cita03.addValoracion(""));
		assertFalse(cita03.addValoracion(null));

		assertEquals("Cita #0001 -> [primera revisión realizada]", cita01.toString());
		assertEquals("Cita #0002 -> [segunda revisión realizada]", cita02.toString());
		assertEquals("Cita #0003 -> [vacunada primera dosis]", cita03.toString());
		assertEquals("Cita #0004 -> <sin texto>", cita04.toString());
		assertEquals("Cita #0005 -> <sin texto>", cita05.toString());
		assertEquals("Cita #0006 -> <sin texto>", cita06.toString());

		assertTrue(cita01.addValoracion("todo está correcTO...la próxima cita será dentro de  un mes"));
		assertTrue(cita02.addValoracion("para LA Siguiente cita se le vacunará con la primera dosis (1/200)"));
		assertTrue(cita03.addValoracion("Se aconseja la desparasitación inmediata"));
		assertTrue(cita06.addValoracion("nueva valoración"));

		assertEquals("Cita #0001 -> [primera revisión realizada, todo está correcto...la próxima cita será dentro de  un mes]", cita01.toString());
		assertEquals("Cita #0002 -> [segunda revisión realizada, para la siguiente cita se le vacunará con la primera dosis (1/200)]", cita02.toString());
		assertEquals("Cita #0003 -> [vacunada primera dosis, se aconseja la desparasitación inmediata]", cita03.toString());
		assertEquals("Cita #0006 -> [nueva valoración]", cita06.toString());

		assertTrue(cita01.contienePalabra("revisión"));
		assertTrue(cita01.contienePalabra("correcto    "));
		assertTrue(cita02.contienePalabra("revisión"));
		assertTrue(cita03.contienePalabra("    vacuna"));
		assertTrue(cita03.contienePalabra("   DESPArasiTación"));
		assertFalse(cita04.contienePalabra("DESPArasiTación"));
		assertFalse(cita04.contienePalabra("revisión"));
		assertFalse(cita05.contienePalabra(null));
		assertFalse(cita05.contienePalabra(""));

		//Finalizando
		cita01.clear();
		cita02.clear();
		cita03.clear();
		cita04.clear();
		cita05.clear();
		cita06.clear();
	}

	@Test
	@Order(1)
	public void test01() {
		Cita.inicializaNumCitas();

		Mascota mascota01 = new Mascota("Kike", "loro");
		Mascota mascota02 = new Mascota("loLO", "perro");
		Mascota mascota03 = new Mascota("pEPa", "gato");

		assertNotNull(mascota02.addCita());
		assertNotNull(mascota03.addCita());
		assertNotNull(mascota03.addCita());

		assertEquals("kike <loro> [0 citas]", mascota01.toString());
		assertEquals("lolo <perro> [1 cita]", mascota02.toString());
		assertEquals("pepa <gato> [2 citas]", mascota03.toString());
		assertEquals("pepa <gato> [2 citas]: [Cita #0002 -> <sin texto>, Cita #0003 -> <sin texto>]", mascota03.toStringExtended());

		for (int i=0; i<1000; i++){
			assertNotNull(mascota02.addCita());
		}
		assertEquals("lolo <perro> [1001 citas]", mascota02.toString());

		assertNotNull(mascota01.addCita());
		assertEquals("kike <loro> [1 cita]", mascota01.toString());
		assertEquals("kike <loro> [1 cita]: [Cita #1004 -> <sin texto>]", mascota01.toStringExtended());

		assertNull(mascota01.getCita(1));
		assertNotNull(mascota01.getCita(1004));
		assertTrue(mascota01.getCita(1004).addValoracion("Primera revisión realizada correctamente")); //Ojo con getCita(citaId), ya que puede devolver null

		Cita cita = mascota01.addCita();

		assertTrue(cita.addValoracion("Segunda revisión realizada correctamente"));
		assertTrue(mascota01.getCita(1005).addValoracion("Debe volver PAsado maÑANa"));

		assertEquals("kike <loro> [2 citas]: [Cita #1004 -> [primera revisión realizada correctamente], " +
		 		                		              "Cita #1005 -> [segunda revisión realizada correctamente, debe volver pasado mañana]]", mascota01.toStringExtended());

		assertNotNull(mascota02.addCita());
		assertNotNull(mascota02.addCita());

		assertTrue(mascota01.addCita().addValoracion("Tercera revisión realizada correctamente"));

		assertEquals("kike <loro> [3 citas]: [Cita #1004 -> [primera revisión realizada correctamente], " +
											          "Cita #1005 -> [segunda revisión realizada correctamente, debe volver pasado mañana], "+
											          "Cita #1008 -> [tercera revisión realizada correctamente]]", mascota01.toStringExtended());

		mascota01.clear();

		assertEquals("kike <loro> [0 citas]: []", mascota01.toStringExtended());
		assertNotNull(mascota01.addCita());
		assertEquals("kike <loro> [1 cita]: [Cita #1009 -> <sin texto>]", mascota01.toStringExtended());

		//Finalizando
		assertTrue(mascota01.size() == 1);
		assertTrue(mascota02.size() == 1003);
		assertTrue(mascota03.size() == 2);
		mascota01.clear();
		mascota02.clear();
		mascota03.clear();
		assertTrue(mascota01.size() == 0);
		assertTrue(mascota02.size() == 0);
		assertTrue(mascota03.size() == 0);
	}

	@Test
	@Order(2)
	public void test02() {
		Cita.inicializaNumCitas();
		Mascota mascota01 = new Mascota("AlfrED         ", "perro");
		Mascota mascota02 = new Mascota(null, null);
		Mascota mascota03 = new Mascota("", "");
		Mascota mascota04 = new Mascota(null, "");
		Mascota mascota05 = new Mascota("",null);

		for (int i=0; i<6; i++){
			Cita cita = mascota01.addCita();
			cita.addValoracion(Format.formatInt(i, 4));
		}

		assertEquals("alfred <perro> [6 citas]", mascota01.toString());
		assertEquals("sinNombre <sinEspecie> [0 citas]", mascota02.toString());
		assertEquals("sinNombre <sinEspecie> [0 citas]", mascota03.toString());
		assertEquals("sinNombre <sinEspecie> [0 citas]", mascota04.toString());
		assertEquals("sinNombre <sinEspecie> [0 citas]", mascota05.toString());

		assertEquals("alfred <perro> [6 citas]: [Cita #0001 -> [0000], Cita #0002 -> [0001], Cita #0003 -> [0002], Cita #0004 -> [0003], Cita #0005 -> [0004], Cita #0006 -> [0005]]", mascota01.toStringExtended());

		assertNotNull(mascota01.getCita(1));
		assertNotNull(mascota01.getCita(5));
		assertNull(mascota01.getCita(250));
		assertNull(mascota01.getCita(-1));

		assertTrue(mascota01.getCita(1).addValoracion("valoración urgente"));
		assertTrue(mascota01.getCita(5).addValoracion("valoración urgente"));

		assertEquals("alfred <perro> [6 citas]: [Cita #0001 -> [0000, valoración urgente], Cita #0002 -> [0001], Cita #0003 -> [0002], Cita #0004 -> [0003], Cita #0005 -> [0004, valoración urgente], Cita #0006 -> [0005]]", mascota01.toStringExtended());

		//Finalizando
		mascota01.clear();
		mascota02.clear();
		mascota03.clear();
	}

	@Test
	@Order(3)
	public void test03() {
		Cita.inicializaNumCitas();
		Mascota mascota01 = new Mascota("kike", "sus_scrofa");
		Mascota mascota02 = new Mascota("kike", "perro");
		Mascota mascota03 = new Mascota("kike", "perro");
		Mascota mascota04 = new Mascota("pepa", "sus_scrofa");

		assertFalse(mascota01.equals(mascota02));
		assertFalse(mascota01.equals(mascota03));
		assertTrue(mascota02.equals(mascota03));
		assertTrue(mascota01.equals(new Mascota("KikE     ", "  sus_ScrOFa")));

		assertTrue(mascota01.compareTo(mascota02) > 0);
		assertTrue(mascota02.compareTo(mascota01) < 0);
		assertTrue(mascota01.compareTo(mascota03) > 0);
		assertTrue(mascota03.compareTo(mascota01) < 0);
		assertTrue(mascota01.compareTo(mascota04) < 0);
		assertTrue(mascota04.compareTo(mascota01) > 0);
		assertTrue(mascota02.compareTo(mascota03) == 0);
		assertTrue(mascota03.compareTo(mascota02) == 0);
		assertTrue(mascota02.compareTo(mascota04) < 0);
		assertTrue(mascota04.compareTo(mascota02) > 0);
		assertTrue(mascota03.compareTo(mascota04) < 0);
		assertTrue(mascota04.compareTo(mascota03) > 0);

		ArrayList<Mascota> arr = new ArrayList<>();
		arr.add(mascota01);
		if (!arr.contains(mascota02)) arr.add(mascota02);
		if (!arr.contains(mascota03)) arr.add(mascota03);
		if (!arr.contains(mascota04)) arr.add(mascota04);
		assertEquals("[kike <sus_scrofa> [0 citas], kike <perro> [0 citas], pepa <sus_scrofa> [0 citas]]", arr.toString());

		arr.sort(Comparator.naturalOrder());
		assertEquals("[kike <perro> [0 citas], kike <sus_scrofa> [0 citas], pepa <sus_scrofa> [0 citas]]", arr.toString());

		arr.sort(null);
		assertEquals("[kike <perro> [0 citas], kike <sus_scrofa> [0 citas], pepa <sus_scrofa> [0 citas]]", arr.toString());

		arr.sort(Comparator.reverseOrder());
		assertEquals("[pepa <sus_scrofa> [0 citas], kike <sus_scrofa> [0 citas], kike <perro> [0 citas]]", arr.toString());

		arr.add(new Mascota("alfred", "gato"));

		arr.sort(null);
		assertEquals("[alfred <gato> [0 citas], kike <perro> [0 citas], kike <sus_scrofa> [0 citas], pepa <sus_scrofa> [0 citas]]", arr.toString());

		mascota01.clear();
		mascota02.clear();
		mascota03.clear();
		mascota04.clear();
		arr.clear();
	}

	@Test
	@Order(4)
	public void test04() {
		Cita.inicializaNumCitas();
		Cliente.inicializaNumClientes();
		Cliente cliente01 = new Cliente("pepe");
		for (int i=0; i<1000; i++) {
			new Cliente("");
		}
		Cliente cliente02 = new Cliente("pepa");

		assertEquals("00001.- pepe -> []", cliente01.toString());
		assertEquals("01002.- pepa -> []", cliente02.toString());


		assertNull(cliente01.addCita("kike", "loro")); //Este cliente no tiene esta mascota
		cliente01.addMascota("kike", "loro");
		cliente01.addMascota("kike", "perro");
		cliente01.addMascota("pepa", "gato");

		Cita cita01 = cliente01.addCita("kike", "loro");
		cita01.addValoracion("primera visita de kike loro");

		Cita cita02 = cliente01.addCita("kike", "loro");
		cita02.addValoracion("segunda visita de kike loro");

		Cita cita03 = cliente01.addCita("kike", "perro");
		cita03.addValoracion("primera visita de kike perro");

		Cita cita04 = cliente01.addCita("kike", "perro");
		cita04.addValoracion("segunda visita de kike perro");

		Cita cita05 = cliente01.addCita("pepa", "gato");
		cita05.addValoracion("primera visita de pepa gato");

		Cita cita06 = cliente01.addCita("pepa", "gato");
		cita06.addValoracion("segunda visita de pepa gato");

		assertTrue(cliente02.addMascota("berta", "perro"));
		assertFalse(cliente02.addMascota("Berta", "PERRO")); //Ya existe

		for (int i=0; i<1000; i++) {
			cliente02.addCita("berta", "perro").addValoracion(Format.formatInt(i, 4));
		}

		assertEquals("00001.- pepe -> [kike <loro> [2 citas], kike <perro> [2 citas], pepa <gato> [2 citas]]", cliente01.toString());
		assertEquals("01002.- pepa -> [berta <perro> [1000 citas]]", cliente02.toString());

		assertEquals("[[1, 2], [3, 4], [5, 6]]", cliente01.getCitasId().toString());
		assertTrue(cliente02.getCitasId().get(0).size() == 1000);
		assertEquals("[[2], [4], [6]]", cliente01.getCitasId("segunda").toString());
		assertEquals("[[1], [3], [5]]", cliente01.getCitasId("primera").toString());
		assertEquals("[[1, 2], [3, 4], [5, 6]]", cliente01.getCitasId("visita").toString());
		assertEquals("[[], [], [5, 6]]",cliente01.getCitasId("gato").toString());
		assertEquals("[[7]]", cliente02.getCitasId("0000").toString());
		assertEquals("[[1006]]", cliente02.getCitasId("0999").toString());
		assertEquals("[[]]", cliente02.getCitasId("pepe").toString());
		assertEquals("[1, 2]", cliente01.getCitasId("kike", "loro").toString());
		assertEquals("[3, 4]", cliente01.getCitasId("kike", "perro").toString());
		assertEquals("[5, 6]", cliente01.getCitasId("pepa", "gato").toString());
		assertNull(cliente01.getCitasId("pepa", "loro"));
		assertNull(cliente01.getCitasId(null, null));
		assertNull(cliente01.getCitasId("", ""));


		assertEquals("00001.- pepe -> {kike <loro> [2 citas]: [Cita #0001 -> [primera visita de kike loro], Cita #0002 -> [segunda visita de kike loro]], " +
											   "kike <perro> [2 citas]: [Cita #0003 -> [primera visita de kike perro], Cita #0004 -> [segunda visita de kike perro]], " +
						 			  		   "pepa <gato> [2 citas]: [Cita #0005 -> [primera visita de pepa gato], Cita #0006 -> [segunda visita de pepa gato]]}", cliente01.toStringExtended());

		assertNull(cliente01.getCita(8));
		assertNotNull(cliente01.getCita(6));
		assertTrue(cliente01.getCita(3).addValoracion("finalizando práctica01"));
		assertTrue(cliente01.getCita(6).addValoracion("finalizando práctica01"));

		assertEquals("00001.- pepe -> {kike <loro> [2 citas]: [Cita #0001 -> [primera visita de kike loro], Cita #0002 -> [segunda visita de kike loro]], " +
											   "kike <perro> [2 citas]: [Cita #0003 -> [primera visita de kike perro, finalizando práctica01], Cita #0004 -> [segunda visita de kike perro]], " +
											   "pepa <gato> [2 citas]: [Cita #0005 -> [primera visita de pepa gato], Cita #0006 -> [segunda visita de pepa gato, finalizando práctica01]]}", cliente01.toStringExtended());

		cliente01.clear();
		cliente02.clear();
	}

	@Test
	@Order(5)
	public void test05() {
		Cliente.inicializaNumClientes();
		Cita.inicializaNumCitas();
		Cliente cliente01 = new Cliente("pepe");
		Cliente cliente02 = new Cliente("pepa");
		assertFalse(cliente01.load("cliente01.txt")); //False porque no encuentra el archivo
		assertTrue(cliente01.load(directorioEntrada + "cliente01.txt"));
		assertTrue(cliente02.load(directorioEntrada + "cliente02.txt"));
		assertEquals("00001.- pepe -> [kike <perro> [1000 citas], kike <gato> [200 citas], kike <loro> [1000 citas]]", cliente01.toString());
		assertEquals("00002.- pepa -> [pepa <perro> [1 cita], pepa <gato> [2 citas], pepa <loro> [1 cita]]", cliente02.toString());
		assertEquals("kike <perro> [1000 citas]", cliente01.getMascota(1).toString());
		assertEquals("pepa <loro> [1 cita]", cliente02.getMascota(2204).toString());
		assertNull(cliente01.getMascota(2205));
		assertNull(cliente02.getMascota(1));

		cliente01.clear();
		cliente02.clear();
	}
}