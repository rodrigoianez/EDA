package eda1.practicas.practica03.parte03;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import eda1.practicas.practica02.parte02.Cliente;
import eda1.practicas.practica02.parte02.Mascota;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class Practica03Parte03Test {
	@Test
	@Order(0)
	public void test00() {
		Node.initializeNumNodes();

		Node<Integer> node01 = new Node<>(3, 5, 6);
		Node<Integer> node02 = new Node<>(3, 5, 6);
		Node<Integer> node03 = new Node<>(7);
		Node<Integer> node04 = new Node<>(8, 3, 9, 6);
		Node<Integer> node05 = new Node<>(8, 3, 9, 5);
		Node<Integer> node06 = new Node<>(1);


		assertEquals("[3, 5, 6]"   , node01.toString());
		assertEquals("[3, 5, 6]"   , node02.toString());
		assertEquals("[7]"         , node03.toString());
		assertEquals("[8, 3, 9, 6]", node04.toString());
		assertEquals("[8, 3, 9, 5]", node05.toString());
		assertEquals("Node #6 -> [1]", node06.toStringWithNodeId());
		assertEquals("Node #1 -> [3, 5, 6]"   , node01.toStringWithNodeId());
		assertEquals("Node #2 -> [3, 5, 6]"   , node02.toStringWithNodeId());
		assertEquals("Node #3 -> [7]"         , node03.toStringWithNodeId());
		assertEquals("Node #4 -> [8, 3, 9, 6]", node04.toStringWithNodeId());
		assertEquals("Node #5 -> [8, 3, 9, 5]", node05.toStringWithNodeId());
		assertEquals("Node #6 -> [1]", node06.toStringWithNodeId());
		

		//Añadimos los nodos en una colección tipo ArrayList<T>. ¿Podríamos añadirlos en un TreeSet<T>?
		ArrayList<Node<Integer>> array = new ArrayList<>();

		array.add(node01);
		if (!array.contains(node02)) array.add(node02);//Como node02 == node01, no se inserta
		if (!array.contains(node03)) array.add(node03); 
		if (!array.contains(node04)) array.add(node04);
		if (!array.contains(node05)) array.add(node05);
		if (!array.contains(node06)) array.add(node06);

		assertEquals("[[3, 5, 6], [7], [8, 3, 9, 6], [8, 3, 9, 5], [1]]", array.toString());
	
		array.sort(null);
		assertEquals("[[1], [3, 5, 6], [7], [8, 3, 9, 5], [8, 3, 9, 6]]", array.toString());

		array.sort(Comparator.reverseOrder());
		assertEquals("[[8, 3, 9, 6], [8, 3, 9, 5], [7], [3, 5, 6], [1]]", array.toString());
		

		array.clear();
		assertEquals("[]", array.toString());
	}

	@Test
	@Order(1)
	public void test01() {
		Node.initializeNumNodes();;

		Node<Mascota> node01 = new Node<>(new Mascota("mascota00", "especie01"), new Mascota("mascota00", "especie02"));
		Node<Mascota> node02 = new Node<>(new Mascota("mascota00", "especie01"), new Mascota("mascota00", "especie02"));
		Node<Mascota> node03 = new Node<>(new Mascota("mascota00", "especie01"));
		Node<Mascota> node04 = new Node<>(new Mascota("mascota00", "especie00"), new Mascota("mascota00", "especie03"));
		
		TreeSet<Node<Mascota>> treeSet = new TreeSet<>();

		treeSet.add(node01);
		treeSet.add(node02);
		treeSet.add(node03);
		treeSet.add(node04);
		
		assertEquals("[[mascota00-especie00, mascota00-especie03], [mascota00-especie01], [mascota00-especie01, mascota00-especie02]]", treeSet.toString());
		treeSet.clear();
	}
	
	@Test
	@Order(2)
	public void test02() {
		Node.initializeNumNodes();

		Node<Cliente> node01 = new Node<>(new Cliente2("pepe"), new Cliente2("angela"));
		Node<Cliente> node02 = new Node<>(new Cliente2("pepe"), new Cliente2("angela"));
		Node<Cliente> node03 = new Node<>(new Cliente2("angela"));
		Node<Cliente> node04 = new Node<>(new Cliente2("antonio"), new Cliente2("zacarias"));
		Node<Cliente> node05 = new Node<>(new Cliente2("zacarias"), new Cliente2("emilio"));

		TreeSet<Node<Cliente>> treeSet = new TreeSet<>();

		treeSet.add(node01);
		treeSet.add(node02);
		treeSet.add(node03);
		treeSet.add(node04);
		treeSet.add(node05);
		
		assertEquals("[[angela -> <>], [antonio -> <>, zacarias -> <>], [pepe -> <>, angela -> <>], [zacarias -> <>, emilio -> <>]]", treeSet.toString());
		treeSet.clear();
	}
	
	
	@Test
	@Order(3)
	public void test03() {
		Node.initializeNumNodes();;
		Node<Integer> node01 = new Node<>(5, 1);
		Node<Integer> node02 = new Node<>(5, 5);
		Node<Integer> node03 = new Node<>(1, 5, 3);
		Node<Integer> node04 = new Node<>(1, 5, 3);

		NodeCollection<Integer> nodes = new NodeCollection<>();
		nodes.add(node02, node01);
		nodes.add(node02, node03);
		nodes.add(node03, node03);
		nodes.add(node01, node03);
		nodes.add(node01, node02);
		nodes.add(node04, node01);

		assertTrue(nodes.size() == 3);
		assertTrue(nodes.getNeighbours(node01).size()==2);
		assertEquals("[[5, 1], [1, 5, 3]]", nodes.getNeighbours(node04).toString());
		assertTrue(nodes.getNeighbours(node03).equals(nodes.getNeighbours(node04)));
		assertTrue(nodes.getNeighbours(new Node<>(8))==null);
		assertEquals("{[1, 5, 3]=[[1, 5, 3], [5, 1]], [5, 1]=[[1, 5, 3], [5, 5]], [5, 5]=[[1, 5, 3], [5, 1]]}", nodes.toStringOrdered(null));
		assertEquals("{[5, 5]=[[5, 1], [1, 5, 3]], [5, 1]=[[5, 5], [1, 5, 3]], [1, 5, 3]=[[5, 1], [1, 5, 3]]}", nodes.toStringOrdered(Comparator.reverseOrder()));
		assertEquals(nodes.toStringOrdered(null), nodes.toStringOrdered(Comparator.naturalOrder()));
		
		HashSet<Node<Integer>> aux = nodes.getNeighbours(node04);
		assertEquals("[[5, 1], [1, 5, 3]]", aux.toString());
		
		nodes.clear();
		
		assertEquals("[]", aux.toString());
		assertTrue(nodes.size() == 0);
	}
}