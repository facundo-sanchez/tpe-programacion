package servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import grafo.Arco;
import grafo.Grafo;

public class ServicioCaminos<T> {

	private Grafo<T> grafo;
	private ArrayList<Arco<T>> arcos;
	private List<List<Integer>> caminos;
	private int origen;
	private int destino;
	private int lim;

	public ServicioCaminos(Grafo<T> grafo, int origen, int destino, int lim) {
		this.grafo = grafo;
		this.origen = origen;
		this.destino = destino;
		this.lim = lim;
		this.arcos = new ArrayList<Arco<T>>();
		this.caminos = new ArrayList<List<Integer>>();

	}

	/**
	 * O(V + A* C) V es la cantidad de vertices, A es la cantidad de arcos del grafo
	 * y C la cantidad de caminos encontrados en el recorrido
	 */
	public List<List<Integer>> caminos() {
		if (this.lim > 0 && this.grafo != null) {
			ArrayList<Integer> caminoActual = new ArrayList<Integer>();
			caminoActual.add(this.origen);
			this.caminos(caminoActual, origen, 0);
		}
		return new ArrayList<List<Integer>>(this.caminos);
	}

	/**
	 * O(A*C) A es la cantidad de arcos del grafo y C la cantidad de caminos
	 * encontrados en el recorrido
	 */
	private void caminos(List<Integer> camino, int vertice, int cont) {
		Iterator<Arco<T>> arcos = this.grafo.obtenerArcos(vertice);
		if (vertice != this.destino) {
			while (arcos.hasNext()) {
				Arco<T> arco = arcos.next();
				int verticeAux = arco.getVerticeDestino();
				if (!this.arcos.contains(arco)) {
					this.arcos.add(arco);
					camino.add(verticeAux);
					this.caminos(camino, verticeAux, cont);
					camino.remove(camino.size() - 1);
					this.arcos.remove(arco);
				}
			}
		} else {
			if (this.arcos.size() <= this.lim)
				this.caminos.add(new ArrayList<Integer>(camino));
		}
	}

	public int getOrigen() {
		return origen;
	}

	public int getDestino() {
		return destino;
	}

}
