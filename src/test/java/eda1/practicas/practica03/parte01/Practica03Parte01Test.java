package eda1.practicas.practica03.parte01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.File;


public class Practica03Parte01Test {

	String directorioEntrada = System.getProperty("user.dir") + File.separator +
							   "src" + File.separator +
							   "main" + File.separator +
							   "java" + File.separator +
							   "eda1" + File.separator +
							   "practicas" + File.separator +
							   "practica03" + File.separator +
							   "parte01" + File.separator;

	
	@Test
	public void test01() {

		GestionEmpresas gestionEmpresas = new GestionEmpresas();
		

		assertFalse(gestionEmpresas.load(directorioEntrada + "datos"));
		assertTrue(gestionEmpresas.load(directorioEntrada + "datos.txt"));
		assertTrue(gestionEmpresas.load(directorioEntrada + "datos.txt"));
		assertTrue(gestionEmpresas.size() == 6); //6 empresas
		gestionEmpresas.load(directorioEntrada + "datos.txt"); //Cada vez que cargo archivo, se debe vaciar la estructura
		assertTrue(gestionEmpresas.size() == 6);

		assertEquals("empresa01 -> [proyecto01_01: [ciudad01, ciudad04, ciudad14], proyecto01_02: [ciudad07, ciudad09, ciudad12], proyecto01_03: [ciudad08, ciudad17, ciudad19]]\n" +
					 "empresa02 -> [proyecto02_01: [ciudad01, ciudad02, ciudad03, ciudad04], proyecto02_02: [ciudad04, ciudad05, ciudad06, ciudad07, ciudad08, ciudad09, ciudad10], proyecto02_03: [ciudad02, ciudad03, ciudad05, ciudad09, ciudad11, ciudad12]]\n" +
					 "empresa03 -> [proyecto03_01: [ciudad04, ciudad06, ciudad07, ciudad10, ciudad15], proyecto03_02: [ciudad01, ciudad07, ciudad09, ciudad11, ciudad13], proyecto03_03: [ciudad05, ciudad09, ciudad16, ciudad18]]\n" +
					 "empresa04 -> [proyecto04_01: [ciudad02, ciudad03, ciudad07, ciudad10], proyecto04_02: [ciudad01, ciudad03, ciudad04, ciudad05, ciudad09], proyecto04_03: [ciudad01, ciudad04, ciudad06, ciudad09, ciudad13], proyecto04_04: [ciudad03, ciudad04, ciudad05, ciudad09, ciudad10], proyecto04_05: [ciudad03, ciudad04, ciudad07, ciudad09, ciudad13]]\n" +
					 "empresa05 -> [proyecto05_01: [ciudad02, ciudad04, ciudad09, ciudad13, ciudad18], proyecto05_02: [ciudad01, ciudad03, ciudad04, ciudad05, ciudad06, ciudad07, ciudad09]]\n" +
					 "empresa06 -> [proyecto06_01: [ciudad03, ciudad04, ciudad10]]\n", 
					  gestionEmpresas.toString());
		
		assertTrue(gestionEmpresas.getNumProyectosEmpresa("empresa05") == 2);
		assertTrue(gestionEmpresas.getNumProyectosEmpresa("EmpresA05") == null);
		assertTrue(gestionEmpresas.getNumCiudadesEmpresa("empresa04") == 10);
		assertTrue(gestionEmpresas.getNumCiudadesEmpresa("emPresa04") == null);
 		assertTrue(gestionEmpresas.getNumCiudadesProyecto("proyecto03_03") == 4);
 		assertTrue(gestionEmpresas.getNumCiudadesProyecto("proyecto03_33") == null);

 		assertTrue(gestionEmpresas.getNumProyectosEmpresa("empresa05s") == null);
		assertTrue(gestionEmpresas.getNumCiudadesProyecto("jWork") == null);
		assertTrue(gestionEmpresas.getNumCiudadesEmpresa("Macrosoft") == null);

		assertEquals("[empresa01, empresa02, empresa03, empresa04, empresa05]", gestionEmpresas.getEmpresasCiudad("ciudad09").toString());
		assertEquals("[]", gestionEmpresas.getEmpresasCiudad("CiudaD09").toString());
	
	
		
		assertEquals("[proyecto01_01, proyecto02_01, proyecto02_02, proyecto03_01, proyecto04_02, proyecto04_03, proyecto04_04, proyecto04_05, proyecto05_01, proyecto05_02, proyecto06_01]", 
   				      gestionEmpresas.getProyectosCiudad("ciudad04").toString());


		assertEquals("[ciudad01, ciudad04, ciudad07, ciudad08, ciudad09, ciudad12, ciudad14, ciudad17, ciudad19]", gestionEmpresas.getCiudadesEmpresa("empresa01").toString());
		assertEquals("[ciudad01, ciudad02, ciudad03, ciudad04, ciudad05, ciudad06, ciudad07, ciudad09, ciudad13, ciudad18]", gestionEmpresas.getCiudadesEmpresa("empresa05").toString());
		
		assertTrue(gestionEmpresas.getCiudadesEmpresa("empresa25") == null);

		assertEquals("[]", gestionEmpresas.getEmpresasCiudad("ciudad09s").toString());

		assertEquals("[]", gestionEmpresas.getProyectosCiudad("ciudad24").toString());
	}
}