package tpe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ServicioDFS {

	private enum color {
		BLANCO, // no visitado
		AMARILLO, // descubierto
		NEGRO // visitado
	}

	private Grafo<?> grafo;
	private HashMap<Integer, color> vertices;

	public ServicioDFS(Grafo<?> grafo) {
		this.grafo = grafo;
		this.vertices = new HashMap<Integer, color>();
	}

	/**
	 * O(V+A) V es la cantidad de vertices del grafo y A es la cantidad de arcos del
	 * grafo
	 */
	public List<Integer> dfsForest() {
		Iterator<Integer> aux = this.grafo.obtenerVertices();
		ArrayList<Integer> vertices = new ArrayList<>();

		while (aux.hasNext()) {
			this.vertices.put(aux.next(), color.BLANCO);
		}

		aux = this.grafo.obtenerVertices();

		while (aux.hasNext()) {
			int vertice = aux.next();
			if (this.vertices.get(vertice) == color.BLANCO) {
				vertices.addAll(this.dfsForest(vertice));
			}
		}
		return new ArrayList<Integer>(vertices);
	}

	/**
	 * DFS (Depth-First Search)
	 * 
	 * O(V+A) V es la cantidad de vertices y A la cantidad de arcos
	 * 
	 * @param vertice - vertice inial del recorrido
	 */
	private List<Integer> dfsForest(int vertice) {
		this.vertices.put(vertice, color.AMARILLO);
		ArrayList<Integer> vertices = new ArrayList<Integer>();
		vertices.add(vertice);

		Iterator<Integer> aux = this.grafo.obtenerAdyacentes(vertice);

		while (aux.hasNext()) {
			Integer verticeAux = aux.next();
			if (this.vertices.get(verticeAux).equals(color.BLANCO)) {
				vertices.addAll(this.dfsForest(verticeAux));
			}
		}
		this.vertices.put(vertice, color.NEGRO);

		return vertices;
	}

}
