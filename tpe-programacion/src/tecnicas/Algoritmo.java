package tecnicas;

import java.util.ArrayList;
import grafo.Arco;

public abstract class Algoritmo {

	public abstract ArrayList<Arco<Integer>> solucion(ArrayList<Arco<Integer>> candidatos);

	public abstract String getAlgoritmo();

	public abstract int getMetrica();

	public abstract int getKms();

	protected void getEstaciones(ArrayList<Arco<Integer>> arcos, ArrayList<Integer> estaciones) {
		for (Arco<Integer> arco : arcos) {
			if (!estaciones.contains(arco.getVerticeOrigen())) {
				estaciones.add(arco.getVerticeOrigen());
			}
			if (!estaciones.contains(arco.getVerticeDestino())) {
				estaciones.add(arco.getVerticeDestino());
			}
		}
	}

}
