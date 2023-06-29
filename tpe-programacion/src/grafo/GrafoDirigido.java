package grafo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GrafoDirigido<T> implements Grafo<T> {

	// Listas
	private HashMap<Integer, ArrayList<Arco<T>>> vertices;
	private int cantidadArcos;

	public GrafoDirigido() {
		this.vertices = new HashMap<Integer, ArrayList<Arco<T>>>();
		this.cantidadArcos = 0;
	}

	/**
	 * Complejidad: O(1)
	 * 
	 * @param verticeiD identificador
	 */
	@Override
	public void agregarVertice(int verticeId) {
		if (!this.contieneVertice(verticeId)) {
			this.vertices.put(verticeId, new ArrayList<Arco<T>>());
		}
	}

	/**
	 * Complejidad: O(V+A) donde V es la cantidad de vertices y A la cantidad de
	 * arcos.
	 * 
	 * @param verticeiD identificador
	 */
	@Override
	public void borrarVertice(int verticeId) {
		if (this.contieneVertice(verticeId)) {
			this.vertices.remove(verticeId);
			Iterator<Arco<T>> arcos = this.obtenerArcos();
			while (arcos.hasNext()) {
				Arco<T> arco = arcos.next();
				if (arco.getVerticeDestino() == verticeId) {
					this.borrarArco(arco.getVerticeOrigen(), arco.getVerticeDestino());
				}
			}
		}
	}

	/**
	 * Complejidad: O(A) A son los Arcos a iterar por existeArco
	 * 
	 * @param verticeId1 identificador de vertice origen
	 * @param verticeId2 identificador de vertice destino
	 * @param etiqueta   etiqueta
	 */

	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		if ((this.contieneVertice(verticeId1) && this.contieneVertice(verticeId2))
				&& !this.existeArco(verticeId1, verticeId2)) {
			Arco<T> arco = new Arco<T>(verticeId1, verticeId2, etiqueta);
			this.vertices.get(verticeId1).add(arco);
			this.cantidadArcos++;
		}
	}

	/**
	 * Complejidad: O(A) A es la cantidad de Arcos por el metodo existeArco
	 * 
	 * @param verticeId1 identificador de vertice origen
	 * @param verticeId2 identificador de vertice destino
	 */
	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		if (this.existeArco(verticeId1, verticeId2)) {
			this.vertices.get(verticeId1).remove(this.obtenerArco(verticeId1, verticeId2));
			this.cantidadArcos--;
		}
	}

	/**
	 * Complejidad: O(1)
	 * 
	 * @param verticeId identificador de vertice
	 */
	@Override
	public boolean contieneVertice(int verticeId) {
		return this.vertices.get(verticeId) != null;
	}

	/**
	 * Complejidad: O(A) A es la cantidad de arcos
	 * 
	 * @param verticeId1 identificador de vertice origen
	 * @param verticeId2 identificador de vertice destino
	 */
	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		ArrayList<Arco<T>> arcos = this.vertices.get(verticeId1);

		Iterator<Arco<T>> iterator = arcos.iterator();

		while (iterator.hasNext()) {
			Arco<T> arco = iterator.next();
			if (arco.getVerticeOrigen() == verticeId1 && arco.getVerticeDestino() == verticeId2) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Complejidad: O(A) A es la cantidad de Arcos que debe iterar
	 * 
	 * @param verticeId1 identificador de vertice origen
	 * @param verticeId2 identificador de vertice destino
	 */
	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		if (this.contieneVertice(verticeId1)) {
			ArrayList<Arco<T>> arcos = this.vertices.get(verticeId1);
			Iterator<Arco<T>> iterator = arcos.iterator();
			while (iterator.hasNext()) {
				Arco<T> arco = iterator.next();
				if (arco.getVerticeDestino() == verticeId2) {
					return arco;
				}
			}
		}
		return null;
	}

	/**
	 * Complejidad: O(1)
	 */
	@Override
	public int cantidadVertices() {
		return this.vertices.size();
	}

	/**
	 * Complejidad: O(V+A) V es la cantidad de vertices y A la cantidad de arcos
	 */
	@Override
	public int cantidadArcos() {
		return this.cantidadArcos;
	}

	/**
	 * Complejidad: O(V) V es la cantidad de vertices
	 */
	@Override
	public Iterator<Integer> obtenerVertices() {
		ArrayList<Integer> vertices = new ArrayList<Integer>();
		vertices.addAll(this.vertices.keySet());

		return vertices.iterator();
	}

	/**
	 * Complejidad: O(A) A es la cantidad de Arcos de un vertice
	 * 
	 * @param verticeId identificador de vertice
	 */
	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		ArrayList<Integer> verticesAdyacentes = new ArrayList<>();
		if (this.contieneVertice(verticeId)) {
			ArrayList<Arco<T>> arcos = this.vertices.get(verticeId);
			Iterator<Arco<T>> iterator = arcos.iterator();
			while (iterator.hasNext()) {
				verticesAdyacentes.add(iterator.next().getVerticeDestino());
			}
		}
		return verticesAdyacentes.iterator();
	}

	/**
	 * Complejidad: O(V+A) V es la cantidad de vertices y A la cantidad de arcos
	 */
	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		ArrayList<Arco<T>> arcos = new ArrayList<Arco<T>>();
		Iterator<Integer> vertices = this.vertices.keySet().iterator();
		while (vertices.hasNext()) {
			arcos.addAll(this.vertices.get(vertices.next()));
		}
		return arcos.iterator();
	}

	/**
	 * Complejidad: O(A) A la cantidad de arcos
	 * 
	 * @param verticeId identificador de vertice
	 */
	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		ArrayList<Arco<T>> arcos = new ArrayList<Arco<T>>();
		arcos.addAll(this.vertices.get(verticeId));
		return arcos.iterator();
	}

	@Override
	public String toString() {
		return "Vertices: \n" + vertices;
	}

}
