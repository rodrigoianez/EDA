package eda1.ejercicios.ejercicio01;

import java.util.ArrayList;
import java.util.Iterator;

public class Estilo {
	
	public static void main(String[]args) {
		
		ArrayList<String> arr = new ArrayList<String>();
		
		for(int i = 1; i<=50;i++) {
				arr.add(String.valueOf(Math.random() < .9 ? i : -i));
		}
		System.out.println(arr.toString());
		
		int i = 0;
		while(true) {
			if(i == arr.size()) {
				System.out.println("No se ha encontrado ningún valor negativo");
				break;
			}
			if(Integer.valueOf(arr.get(i))>=0) {
				i++;
				continue;
			}
			
			System.out.println("He encontrado el primer negativo" + arr.get(i));
			i++;
			break;
		}
		System.out.println("He terminado");
			
	}
}

