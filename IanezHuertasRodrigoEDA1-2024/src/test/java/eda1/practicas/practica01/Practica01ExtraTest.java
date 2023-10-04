package eda1.practicas.practica01;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class Practica01ExtraTest {
							         
	//Vamos a modificar la clase Cliente de la Practica01 (sin que afecte al resto del código) para que, independientemente del id de cada cliente,
	//e independientemente de las mascotas que tenga asignadas, se considere que dos clientes son iguales sii (si y solo si) sus nombres son iguales.
	//La modificación consiste en implementar un único metodo...¿cuál?
	
	@Test
	@Order(0)
	public void test00Extra(){
		Cliente.inicializaNumClientes();
		ArrayList<Cliente> clientes = new ArrayList<>();
		
		String[] clienteNames = {"Pepe", "Maria", "Pepe", "MaRiA", "Maria", "pePE", "Pepe", "PEPE", "Maria", "maria", "pepe", "Jesus"};
		
		for (String clienteName : clienteNames){ //teóricamente, añadimos 12 clientes
			Cliente cliente = new Cliente(clienteName);
			if (clientes.contains(cliente)) continue;
			clientes.add(cliente);
		}
		
		assertEquals(3, clientes.size()); // ¿Por qué hay solo tres clientes? ¿Y por qué jesus tiene el id 00012?
		assertEquals("[00001.- pepe -> [], 00002.- maria -> [], 00012.- jesus -> []]", clientes.toString());

		for (Cliente cliente: clientes){
			cliente.clear();
		}
		clientes.clear();
	}
	
	//En este caso, como puedes comprobar, hay errores en el código del test; ¿cómo es posible?
	//Hay que modificar la clase Cliente para que: (1) no haya errores; y (2) el test se ejecute correctamente (verde)
	//La modificación consiste en implementar un único metodo...¿cuál?

	@Test
	@Order(1)
	public void test01Extra(){
		Cliente.inicializaNumClientes();
		ArrayList<Cliente> clientes = new ArrayList<>();

		String[] clienteNames = {"Pepe", "Maria", "Pepe", "MaRiA", "Maria", "pePE", "Pepe", "PEPE", "Maria", "maria", "pepe", "Jesus"};

		for (String clienteName : clienteNames){
			Cliente cliente = new Cliente(clienteName);
			if (clientes.contains(cliente)) continue;
			clientes.add(cliente);
		}
		assertEquals("[00001.- pepe -> [], 00002.- maria -> [], 00012.- jesus -> []]", clientes.toString());
		clientes.sort(Comparator.naturalOrder());
		assertEquals("[00012.- jesus -> [], 00002.- maria -> [], 00001.- pepe -> []]", clientes.toString());
		clientes.sort(Comparator.reverseOrder());
		assertEquals("[00001.- pepe -> [], 00002.- maria -> [], 00012.- jesus -> []]", clientes.toString());

		for (Cliente cliente: clientes){
			cliente.clear();
		}
		clientes.clear();
	}

	//En este caso, como puedes comprobar una vez más, hay errores en el código del test...
	//Hay que modificar la clase Cliente para que: (1) no haya errores; y (2) el test se ejecute correctamente (verde)
	//La modificación consiste en implementar un único metodo...¿cuál?

	@Test
	@Order(2)
	public void test02Extra(){
		Cliente.inicializaNumClientes();
		Cliente cliente01 = new Cliente("pepe");
		for (int i=0; i<10; i++){
			cliente01.addMascota("", "");
		}	
		for (Mascota mascota: cliente01) {
			for (int j=0; j<10; j++) {
				mascota.addCita();
			}
		}
		
		assertTrue(cliente01.size() == 1); // ¿Por qué solo 1 mascota y 10 citas y no 10 mascotas y 100 citas?
		assertEquals("00001.- pepe -> [sinNombre <sinEspecie> [10 citas]]", cliente01.toString());

		cliente01.clear();
	}
}