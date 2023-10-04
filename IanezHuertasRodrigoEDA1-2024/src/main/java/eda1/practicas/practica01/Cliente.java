package main;

import eda1.practicas.auxiliar.Format;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Cliente implements Comparable<Cliente>, Iterable<Mascota>{
    private static int numClientes = 0;
    private final String nombre;
    private final ArrayList<Mascota> mascotas;

    public static void inicializaNumClientes() {
        numClientes = 0;
    }

    public Cliente(String nombre) {
        this.nombre = Format.formatInt(++numClientes, 5) + ".- " + ( nombre == null || nombre.isBlank() ? "sinNombre" : nombre.toLowerCase());
        this.mascotas = new ArrayList<>();
    }

    public boolean addMascota(String nombre, String especie) {
    	Mascota mascota = new Mascota(nombre,especie);
    	int index = this.mascotas.indexOf(mascota);
    	if(index != -1) return false;
    	return this.mascotas.add(mascota);
    		
    
    	
    }

    public Cita addCita(String nombre, String especie) {
    	int index = this.mascotas.indexOf(new Mascota(nombre, especie));
    	return index != -1 ? this.mascotas.get(index).addCita() : null;
    }

    public void clear() {
    	for (Mascota mascota : mascotas) {
    		mascota.clear();
    	}
        this.mascotas.clear();
    }

    public int size() {
        return this.mascotas.size();
    }

    public ArrayList<ArrayList<Integer>> getCitasId(){
    	//Hacemos uso de estas dos variables locales
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> aux;
        //2 for() anidados tipo forEach
        //En el primer for() se itera sobre las mascotas y, para cada mascota, se itera sobre las citas
        
        for (Mascota mascota : mascotas) {
        	aux = new ArrayList<Integer>();
        	for(Cita cita : mascota) {
        		aux.add(cita.getCitaId());
        	}
        	result.add(aux);
        }
        
        return result;
    }

    public ArrayList<ArrayList<Integer>> getCitasId(String palabra) {
    	//Hacemos uso de estas dos variables locales
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> aux;
        //2 for() anidados tipo forEach
        //Muy similar al anterior, solo que en este caso queremos únicamente las citas que incluyan entre sus valoraciones la palabra que se indica como parámetro de entrada
        
        for (Mascota mascota : mascotas) {
        	aux = new ArrayList<Integer>();
        	for(Cita cita : mascota) {
        		if(cita.contienePalabra(palabra)) {
        			aux.add(cita.getCitaId());
        		}
        	}
        	result.add(aux);
        }
        return result;
    }

    public ArrayList<Integer> getCitasId(String nombre, String especie){
        //Si la mascota con clave (nombre, especie) no existe, se devuelve null;
        ArrayList<Integer> result = new ArrayList<>();
        int index= this.mascotas.indexOf(new Mascota(nombre,especie));
        if(index == -1) return null;        
        //1 for() tipo forEach
        for(Cita cita : this.mascotas.get(index)) {
        	result.add(cita.getCitaId());
        	
        }
        return result;
    }

    public Cita getCita(int citaId){
        Cita result = null;
        //1 for()
        //Interesa el uso de break, ¿verdad?
        for(Mascota mascota : mascotas) {
        	result = mascota.getCita(citaId);
        	if(result != null) break;
        }
        return result;
    }

    public Mascota getMascota(int citaId){
        //1 for() con 1 único if
        for(Mascota mascota : mascotas) {
        	if(mascota.getCita(citaId) != null) {
        		return mascota;
        	}
        }
        return null;
    }

    @Override
    public String toString() {
        return this.nombre + " -> " + this.mascotas.toString();
    }

    public String toStringExtended() {
        String result = this.nombre + " -> {";
        //1 for() tipo forEach
        //Hacemos uso del método toStringExtended() de mascota
        //Cuidado con la última coma...
        int i = 0;
        for (Mascota m : mascotas) {
        	
        	result += (i++ != 0 ? ", ": "") + m.toStringExtended();
        }
        return result + "}";
    }

    public boolean load(String nombreArchivo) {
    	Scanner scan;
        try {
            scan = new Scanner(new File(nombreArchivo));
        }catch(Exception e) {
            return false;
        }
        while (scan.hasNextLine()) {
            String linea = scan.nextLine(); 
            if (linea.isEmpty()) continue;
            if (linea.startsWith("%")) continue;
            String[] items = linea.split("[ ]+"); 
            int posInicial = items[0].isEmpty() ? 1 : 0;
            Mascota mascota = new Mascota(items[posInicial], items[posInicial+1]);
            
            int index = this.mascotas.indexOf(mascota); 
            if (index != -1) continue; 
            this.mascotas.add(mascota);
            int nCitas = Integer.parseInt(items[posInicial+2]);
            for(int i = 0; i < nCitas; i++) {
            	mascota.addCita();
            }
        }
        scan.close();
        return true;
    }
    
    public boolean equals(Object obj) {
    	
    	 if (this == obj) return true;
         if (!(obj instanceof Cliente)) return false;
         return this.compareTo((Cliente)obj) == 0; 
     }

	@Override
	public int compareTo(Cliente o) {
		
		return this.nombre.substring(8).compareTo(o.nombre.substring(8)); //¿split?
	}

	@Override
	public Iterator<Mascota> iterator() {
		
		return this.mascotas.iterator();
	}

    	
    
}