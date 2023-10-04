package eda1.practicas.practica02.parte01;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.File;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class Practica02Parte01Test {

	String directorioEntrada = System.getProperty("user.dir") + File.separator +
			"src" + File.separator +
			"main" + File.separator +
			"java" + File.separator +
			"eda1" + File.separator +
			"practicas" + File.separator +
			"practica02" + File.separator +
			"parte01" + File.separator;

	@Test
	@Order(0)
	public void test00() {
		Asignatura asignatura01 = new Asignatura("EDA1", 3);
		Asignatura asignatura02 = new Asignatura(null, 9);
		Asignatura asignatura03 = new Asignatura("EDA1", 0);
		Asignatura asignatura04 = new Asignatura(null, 1);
		Asignatura asignatura05 = new Asignatura("", -1);
		Asignatura asignatura06 = new Asignatura("DRA", 8);
		Asignatura asignatura07 = new Asignatura("LPS", 7);
		Asignatura asignatura08 = new Asignatura("EDA2", 4);
		Asignatura asignatura09 = new Asignatura("IP", 1);
		Asignatura asignatura10 = new Asignatura("HMIS", 6);
		Asignatura asignatura11 = new Asignatura("MP", 2);
		Asignatura asignatura12 = new Asignatura("PSS", 4);
		Asignatura asignatura13 = new Asignatura("SSP", 5);
		Asignatura asignatura14 = new Asignatura("SSP", 4);

		assertEquals("eda1 (2º-1C)", asignatura01.toString());
		assertEquals("sinNombre (?º-?C)", asignatura02.toString());
		assertEquals("eda1 (?º-?C)", asignatura03.toString());
		assertEquals("sinNombre (1º-1C)", asignatura04.toString());
		assertEquals("sinNombre (?º-?C)", asignatura05.toString());
		assertEquals("dra (4º-2C)", asignatura06.toString());
		assertEquals("lps (4º-1C)", asignatura07.toString());
		assertEquals("eda2 (2º-2C)", asignatura08.toString());
		assertEquals("ip (1º-1C)", asignatura09.toString());
		assertEquals("hmis (3º-2C)", asignatura10.toString());
		assertEquals("mp (1º-2C)", asignatura11.toString());
		assertEquals("pss (2º-2C)", asignatura12.toString());

		assertTrue(new Asignatura("eda1", 0).equals(new Asignatura("eda1", -9)));
		assertTrue(new Asignatura("eda1", 3).compareTo(new Asignatura("eda1", 8)) == 0);
		assertTrue(asignatura01.compareTo(asignatura13) < 0);
		assertTrue(asignatura12.compareTo(asignatura13) < 0);
		assertTrue(asignatura14.compareTo(asignatura12) > 0);
		assertTrue(asignatura14.compareTo(asignatura14) == 0);
		assertTrue(asignatura14.equals(asignatura14));
		assertTrue(asignatura02.equals(new Asignatura(null, -1)));
		assertTrue(asignatura04.equals(new Asignatura(null, 15)));
		
		asignatura01.clear();
		asignatura02.clear();
		asignatura03.clear();
		asignatura04.clear();
		asignatura05.clear();
		asignatura06.clear();
		asignatura07.clear();
		asignatura08.clear();
		asignatura09.clear();
		asignatura10.clear();
		asignatura11.clear();
		asignatura12.clear();
		asignatura13.clear();
		asignatura14.clear();
	}

	@Test
	@Order(1)
	public void test01() {
		Asignatura asignatura01 = new Asignatura("EDA1", 3);
		Asignatura asignatura02 = new Asignatura("EDA2", 4);
		Asignatura asignatura03 = new Asignatura("IP", 1);
		Asignatura asignatura04 = new Asignatura("MP", 2);

		asignatura01.addDocentes("pepe", "pepa", "pepe", "pepa", "juan", "PePe", "maria");
		asignatura02.addDocentes("juan", "zacarias", "amalia", "zacarias", "juan");
		asignatura02.addDocentes();

		String[] salidaEsperada1 = {"pepe", "pepa", "juan", "maria"};
		String[] salidaEsperada2 = {"juan", "zacarias", "amalia"};

		int cont = 0;
		for (String docente: asignatura01) {
			assertEquals(salidaEsperada1[cont++], docente);
		}
		
		cont = 0;
		for (String docente: asignatura02) {
			assertEquals(salidaEsperada2[cont++], docente);
		}
		
		assertEquals("[juan, maria, pepa, pepe]", asignatura01.toStringDocentes(null));
		assertEquals("[juan, maria, pepa, pepe]", asignatura01.toStringDocentes(Comparator.naturalOrder()));
		assertEquals("[pepe, pepa, maria, juan]", asignatura01.toStringDocentes(Comparator.reverseOrder()));
		assertEquals("[amalia, juan, zacarias]", asignatura02.toStringDocentes(null));
		assertEquals("[amalia, juan, zacarias]", asignatura02.toStringDocentes(Comparator.naturalOrder()));
		assertEquals("[zacarias, juan, amalia]", asignatura02.toStringDocentes(Comparator.reverseOrder()));

		assertTrue(asignatura01.esDocente("pepa"));
		assertTrue(asignatura01.esDocente("PEPe"));
		assertFalse(asignatura01.esDocente("zacarias"));
		assertTrue(asignatura02.esDocente("zacarias"));
		
		asignatura01.clear();
		asignatura02.clear();
		asignatura03.clear();
		asignatura04.clear();
		assertEquals("[]", asignatura01.toStringDocentes(null));
		assertEquals("[]", asignatura02.toStringDocentes(null));
		assertEquals("[]", asignatura03.toStringDocentes(null));
		assertEquals("[]", asignatura04.toStringDocentes(null));
	}
	
	@Test
	@Order(2)
	public void test02() {
		Asignatura asignatura01 = new Asignatura("EDA1", 3);
		Asignatura asignatura02 = new Asignatura("EDA2", 4);

		AsignaturaNotas asigNotas01 = new AsignaturaNotas(asignatura01);
		AsignaturaNotas asigNotas02 = new AsignaturaNotas(asignatura02);
		AsignaturaNotas asigNotas03 = new AsignaturaNotas(asignatura01);

		assertEquals("eda1 (2º-1C) -> [] <0.00>", asigNotas01.toString());
		assertEquals("eda2 (2º-2C) -> [] <0.00>", asigNotas02.toString());
		assertEquals("eda1 (2º-1C) -> [] <0.00>", asigNotas03.toString());

		asigNotas01.addNotas();
		asigNotas01.addNotas(null, null);
		asigNotas01.addNotas(10.0, 10., .10);

		assertEquals("eda1 (2º-1C) -> [0.00, 0.00, 10.00, 10.00, 0.10] <4.02>", asigNotas01.toString());

		asigNotas02.addNotas(5.0, 10., null, null, 5.0);

		assertEquals("eda2 (2º-2C) -> [5.00, 10.00, 0.00, 0.00, 5.00] <4.00>", asigNotas02.toString());

		assertEquals("4.02", asigNotas01.getNotaMedia());
		assertEquals("4.00", asigNotas02.getNotaMedia());

		assertTrue(asigNotas01.equals(asigNotas03));
		assertFalse(asigNotas01.equals(asigNotas02));
		assertTrue(asigNotas01.compareTo(asigNotas02) < 0);
		assertTrue(asigNotas02.compareTo(asigNotas01) > 0);
		assertTrue(asigNotas01.compareTo(asigNotas03) == 0);


		asignatura01.clear();
		asignatura02.clear();
		asigNotas01.clear();
		asigNotas02.clear();
		assertEquals("eda1 (2º-1C) -> [] <0.00>", asigNotas01.toString());
		assertEquals("eda2 (2º-2C) -> [] <0.00>", asigNotas02.toString());
	}

	@Test
	@Order(3)
	public void test03() {
		Asignatura asignatura01 = new Asignatura("EDA1", 3);
		Asignatura asignatura02 = new Asignatura("EDA2", 4);
		Asignatura asignatura03 = new Asignatura("ip", 1);
		Asignatura asignatura04 = new Asignatura("mp", 2);

		Estudiante estudiante01 = new Estudiante("pepe");
		Estudiante estudiante02 = new Estudiante("pepa");

		estudiante01.addAsignaturas(asignatura01, asignatura02, asignatura04, asignatura01);
		estudiante02.addAsignaturas(asignatura01, asignatura03);
		estudiante02.addAsignaturas(asignatura01, asignatura03);

		assertEquals("Estudiante con id = pepe" +
							  "\n\tmp (1º-2C) -> [] <0.00>" +
							  "\n\teda1 (2º-1C) -> [] <0.00>" +
							  "\n\teda2 (2º-2C) -> [] <0.00>\n", estudiante01.toString());
		assertEquals("Estudiante con id = pepa" +
							  "\n\tip (1º-1C) -> [] <0.00>" +
							  "\n\teda1 (2º-1C) -> [] <0.00>\n", estudiante02.toString());

		assertTrue(estudiante01.addNotas("eda1", 3.0, 2.0, 5.0));
		assertTrue(estudiante01.addNotas("EDA1", 10.0, 5.0));
		assertTrue(estudiante01.addNotas("eda1", 10.0, 3.33333, 2.5));
		assertFalse(estudiante01.addNotas("eda01", 10.0, 5.0));
		assertTrue(estudiante01.addNotas("eda2", 3.0, 3.0, 3.0));
		assertTrue(estudiante01.addNotas("mp", 10.0, 5.0));
		
		assertEquals("Estudiante con id = pepe" +
							  "\n\tmp (1º-2C) -> [10.00, 5.00] <7.50>" +
							  "\n\teda1 (2º-1C) -> [3.00, 2.00, 5.00, 10.00, 5.00, 10.00, 3.33, 2.50] <5.10>" +
							  "\n\teda2 (2º-2C) -> [3.00, 3.00, 3.00] <3.00>\n", estudiante01.toString());

		assertTrue(estudiante02.addNotas("eda1", 5., 7., 10.));
		assertFalse(estudiante02.addNotas("EDA2", 10.0, 5.0));
		assertTrue(estudiante02.addNotas("ip", 10.0, 10., 10.));
		assertTrue(estudiante02.addNotas("ip", 10.0, 10., 10.));

		assertEquals("Estudiante con id = pepa" +
							  "\n\tip (1º-1C) -> [10.00, 10.00, 10.00, 10.00, 10.00, 10.00] <10.00>" +
							  "\n\teda1 (2º-1C) -> [5.00, 7.00, 10.00] <7.33>\n", estudiante02.toString());

		assertEquals("5.10", estudiante01.getNotaMedia("eda1"));
		assertEquals(null, estudiante01.getNotaMedia("ip"));
		assertEquals("5.20", estudiante01.getNotaMedia());
		assertEquals(null, estudiante02.getNotaMedia("mp"));
		assertEquals("8.67", estudiante02.getNotaMedia());

		asignatura01.clear();
		asignatura02.clear();
		asignatura03.clear();
		asignatura04.clear();
		estudiante01.clear();
		estudiante02.clear();
		assertEquals("Estudiante con id = pepe\n", estudiante01.toString());
		assertEquals("Estudiante con id = pepa\n", estudiante02.toString());
	}

	@Test
	@Order(4)
	public void test04() {
		GestionEstudiantes gestion = new GestionEstudiantes("ESI");
		Estudiante estudiante01 = new Estudiante("john");
		Estudiante estudiante02 = new Estudiante("britney");

		Asignatura asignatura01 = new Asignatura("eda1", 3);
		Asignatura asignatura02 = new Asignatura("eda2", 4);
		Asignatura asignatura03 = new Asignatura("ip", 1);
		Asignatura asignatura04 = new Asignatura("mp", 2);

		asignatura01.addDocentes("pepe", "pepa", "emilio");
		asignatura02.addDocentes("pepe", "loli", "fernando");
		asignatura03.addDocentes("pepa", "jesus");
		asignatura04.addDocentes("juan", "emilio");

		assertEquals("ESI", gestion.toString());
		gestion.addAsignaturas(asignatura01, asignatura02, asignatura03, asignatura04, asignatura04);
		gestion.addEstudiantes(estudiante01, estudiante02, estudiante01);

		assertTrue(gestion.addMatricula("john", "eda1", "mp"));
		assertTrue(gestion.addMatricula("britney", "eda2", "ip"));
		assertFalse(gestion.addMatricula("jenny", "eda2", "ip"));

		assertTrue(gestion.addNotas("john", "eda1", 10.0, 5.0, 7.0));
		assertTrue(gestion.addNotas("john", "mp", 10., .10));
		assertTrue(gestion.addNotas("britney", "eda2", 5., 5., 7.));
		assertTrue(gestion.addNotas("britney", "ip", 10., 10., 3.));


		assertEquals("6.19", gestion.getNotaMedia("john"));
		assertEquals("6.67", gestion.getNotaMedia("britney"));
		assertEquals(null, gestion.getNotaMedia("angelica"));

		assertEquals("7.33", gestion.getNotaMedia("john", "eda1"));
		assertEquals(null, gestion.getNotaMedia("john", "eda21"));
		assertEquals(null, gestion.getNotaMedia("jonathan", "eda1"));
		assertEquals("7.67", gestion.getNotaMedia("britney", "ip"));

		assertTrue(gestion.addMatricula("britney", "eda1"));
		assertTrue(gestion.addNotas("britney", "eda1", 10., 10., 10.));

		assertEquals("10.00", gestion.getNotaMedia("britney", "eda1"));
		assertEquals("8.67", gestion.getNotaMediaAsignatura("eda1"));
		assertEquals("7.67", gestion.getNotaMediaAsignatura("ip"));

		assertEquals("[emilio, juan, pepa, pepe]", gestion.getEquipoDocenteEstudiante("john"));
		assertEquals("[]", gestion.getEquipoDocenteEstudiante("johny"));
		assertEquals("[emilio, fernando, jesus, loli, pepa, pepe]", gestion.getEquipoDocenteEstudiante("britney"));
		asignatura01.clear();
		asignatura02.clear();
		asignatura03.clear();
		asignatura04.clear();
		estudiante01.clear();
		estudiante02.clear();
		gestion.clear();
	}

	@Test
	@Order(5)
	public void test05() {
		GestionEstudiantes gestion = new GestionEstudiantes("ESI");
		assertFalse(gestion.load("datos.txt"));
		assertTrue(gestion.load(directorioEntrada + "datos.txt"));
		assertEquals("5.11", gestion.getNotaMedia("antonio"));
		assertEquals("0.00", gestion.getNotaMedia("AntonIa"));
		assertEquals("4.14", gestion.getNotaMedia("andrEs"));
		assertEquals(null, gestion.getNotaMedia("antonios"));
		assertEquals("8.17", gestion.getNotaMediaAsignatura("eda1"));
		assertEquals("4.03", gestion.getNotaMediaAsignatura("eda2"));
		assertEquals("5.22", gestion.getNotaMediaAsignatura("lps"));
		assertEquals(null, gestion.getNotaMediaAsignatura("eda01"));
		assertEquals("8.41", gestion.getNotaMedia("andrea", "eda1"));
		assertEquals("5.65", gestion.getNotaMedia("andrea", "pss"));
		assertEquals("5.17", gestion.getNotaMedia("andrea", "dra"));
		assertEquals(null, gestion.getNotaMedia("andreas", "pss"));

		gestion.clear();
		assertFalse(gestion.load(directorioEntrada + "datosErroneo.txt"));
		assertTrue(gestion.load(directorioEntrada + "datos.txt"));
		assertTrue(gestion.load(directorioEntrada + "datos.txt"));
		assertTrue(gestion.load(directorioEntrada + "datos.txt"));
		assertEquals("5.11", gestion.getNotaMedia("antonio"));
		assertEquals("0.00", gestion.getNotaMedia("AntonIa"));
		assertEquals("4.14", gestion.getNotaMedia("andrEs"));
		assertEquals(null, gestion.getNotaMedia("antonios"));
		assertEquals("8.17", gestion.getNotaMediaAsignatura("eda1"));
		assertEquals("4.03", gestion.getNotaMediaAsignatura("eda2"));
		assertEquals("5.22", gestion.getNotaMediaAsignatura("lps"));
		assertEquals(null, gestion.getNotaMediaAsignatura("eda01"));
		assertEquals("8.41", gestion.getNotaMedia("andrea", "eda1"));
		assertEquals("5.65", gestion.getNotaMedia("andrea", "pss"));
		assertEquals("5.17", gestion.getNotaMedia("andrea", "dra"));
		assertEquals(null, gestion.getNotaMedia("andreas", "pss"));
		
		assertEquals("[antonio, esteban, joaquin, manel, paco]", gestion.getEquipoDocenteEstudiante("antonio"));
		assertEquals("[antonio, esteban, irene, joaquin, julio, manel, mercedes, natalia, paco, pepa]", gestion.getEquipoDocenteEstudiante("andrea"));
		assertEquals("[joaquin, manel, paco]", gestion.getEquipoDocenteEstudiante("mario"));
		assertEquals("[]", gestion.getEquipoDocenteEstudiante("natalia"));

		gestion.clear();
	}
}