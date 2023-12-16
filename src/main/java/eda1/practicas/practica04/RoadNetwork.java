package eda1.practicas.practica04;

import java.io.File;
import java.util.Scanner;

public class RoadNetwork extends Network<String> {
	
	private enum Secciones {
			Type, Vertex, Edge;
		
			void load(RoadNetwork net, String linea){
				switch (this){
				case Type:
					net.setDirected(!linea.equals("Not Directed"));
					break;
				case Vertex:
					net.addVertex(linea);
					break;
				case Edge:
					String[] unit = linea.split("[ \t]+");
					net.addEdge(unit[0], unit[1], Double.parseDouble(unit[2]));
					break;
				}
				
			}
	}
	
	public boolean load(String filename){
		Scanner scan = null;
		Secciones seccion = null; 
		this.adjacencyList.clear();
		try{
			scan = new Scanner(new File(filename));
		}catch(Exception e){
			return false;
		}
		while(scan.hasNextLine()){
			String line = scan.nextLine().trim();
			if (line.isEmpty()) continue;
			if (line.startsWith("%")) continue;
			
			if (line.toLowerCase().equals("@type")){
				seccion = Secciones.Type;
				continue;
			}
			if (line.toLowerCase().equals("@vertex")){
				seccion = Secciones.Vertex;
				continue;
			}
			if (line.toLowerCase().equals("@edges")){
				seccion = Secciones.Edge;
				continue;
			}
			seccion.load(this, line);
		}
		return true;
	}
}
