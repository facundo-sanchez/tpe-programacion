package tpe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ServicioBFS {

	private enum estado {
		VISITADO, NO_VISITADO
	}

	private Grafo<?> grafo;
	private ArrayList<Integer> fila;
	private HashMap<Integer, estado> vertices;

	public ServicioBFS(Grafo<?> grafo) {
		this.grafo = grafo;
		this.fila = new ArrayList<Integer>();
		this.vertices = new HashMap<Integer, estado>();
	}

	/**
	 * O(V+A) V es la cantidad de vertices del grafo y A es la cantidad de arcos del
	 * grafo
	 */
	public List<Integer> bfsForest() {
		ArrayList<Integer> resultado = new ArrayList<Integer>();
		Iterator<Integer> aux = this.grafo.obtenerVertices();

		while (aux.hasNext()) {
			this.vertices.put(aux.next(), estado.NO_VISITADO);
		}

		aux = this.grafo.obtenerVertices();

		while (aux.hasNext()) {
			int vertice = aux.next();
			if (this.vertices.get(vertice) == estado.NO_VISITADO) {
				resultado.addAll(this.bfsForest(vertice));
			}
		}
		return new ArrayList<Integer>(resultado);
	}

	/**
	 * Recorrido BFS (Breadth-First Search).
	 * 
	 * O(V+A) V es la cantidad de vertices en el grafo y A la cantidad de arcos
	 * 
	 * @param vertice - vertice inicial del recorrido
	 */
	private List<Integer> bfsForest(int vertice) {
		ArrayList<Integer> resultado = new ArrayList<Integer>();
		this.vertices.put(vertice, estado.VISITADO);
		this.fila.add(vertice);

		while (!this.fila.isEmpty()) {
			int verticeAux = this.fila.remove(0);

			Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(verticeAux);
			resultado.add(verticeAux);
			while (adyacentes.hasNext()) {
				int adyacente = adyacentes.next();
				if (this.vertices.get(adyacente) == estado.NO_VISITADO) {
					this.fila.add(adyacente);
					this.vertices.put(adyacente, estado.VISITADO);
				}
			}
		}
		return resultado;
	}
}
