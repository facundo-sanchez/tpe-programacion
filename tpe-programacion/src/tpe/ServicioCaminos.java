package tpe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ServicioCaminos<T> {

	private Grafo<T> grafo;
	private int origen;
	private int destino;
	private int lim;

	private ArrayList<Arco<T>> arcos;
	private List<List<Integer>> caminos;

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
		ArrayList<Integer> caminoActual = new ArrayList<Integer>();
		caminoActual.add(this.origen);
		this.caminos(caminoActual, origen);
		return new ArrayList<List<Integer>>(this.caminos);
	}

	/**
	 * O(A*C) A es la cantidad de arcos del grafo y C la cantidad de caminos
	 * encontrados en el recorrido
	 */
	private void caminos(List<Integer> caminoActual, int vertice) {
		Iterator<Arco<T>> arcos = this.grafo.obtenerArcos(vertice);

		if (vertice != this.destino) {
			while (arcos.hasNext()) {
				Arco<T> arco = arcos.next();
				int verticeAux = arco.getVerticeDestino();
				// verificamos si el arco y el destino estan en la lista.
				/*
				 * sino validamos !caminosActual.contains el resultado va ser [[7, 6, 4, 5, 3]]
				 * y es incorrecto. para origen:7 destino: 3 [[7, 5, 6, 4, 3]]
				 */
				if (!this.arcos.contains(arco) && !caminoActual.contains(verticeAux)) {
					this.arcos.add(arco);
					caminoActual.add(verticeAux);
					this.caminos(caminoActual, verticeAux);
					caminoActual.remove(caminoActual.get(caminoActual.size() - 1));
					this.arcos.remove(arco);
				}
			}
		} else {
			if (this.arcos.size() <= this.lim)
				this.caminos.add(new ArrayList<Integer>(caminoActual));
		}
	}

}
