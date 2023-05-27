package tpe;

import java.util.List;

public class main {

	public static <T> void main(String[] args) {

		GrafoDirigido<T> grafo = new GrafoDirigido<T>();

		grafo.agregarVertice(1);
		grafo.agregarVertice(2);
		grafo.agregarVertice(3);
		grafo.agregarVertice(4);
		grafo.agregarVertice(5);
		grafo.agregarVertice(6);
		grafo.agregarVertice(7);

		grafo.agregarVertice(10);

		grafo.agregarArco(1, 3, null);

		grafo.agregarArco(2, 1, null);
		grafo.agregarArco(2, 3, null);

		grafo.agregarArco(3, 5, null);

		grafo.agregarArco(5, 6, null); // origen = 7 destino = 3 => [7, 5, 6, 4, 3]
		grafo.agregarArco(5, 3, null); // origen = 7 destino = 3 => [7, 5, 3]

		grafo.agregarArco(6, 4, null);
		grafo.agregarArco(6, 7, null);

		grafo.agregarArco(4, 5, null);
		grafo.agregarArco(4, 3, null);

		grafo.agregarArco(7, 5, null);

		grafo.agregarArco(10, 6, null);
		grafo.agregarArco(6, 10, null);

		grafo.borrarVertice(10);

		bfsForest(grafo);
		dfsForest(grafo);
		caminos(grafo);
	}

	public static <T> void bfsForest(Grafo<T> grafo) {

		ServicioBFS bfs = new ServicioBFS(grafo);
		List<Integer> bfsForest = bfs.bfsForest();
		System.out.println("BFS Forest:");
		for (Integer vertice : bfsForest) {
			System.out.println(vertice);
		}
	}

	public static <T> void dfsForest(Grafo<T> grafo) {
		ServicioDFS dfs = new ServicioDFS(grafo);
		List<Integer> dfsForest = dfs.dfsForest();
		System.out.println("DFS Forest:");
		for (Integer vertice : dfsForest) {
			System.out.println(vertice);
		}

	}

	public static <T> void caminos(Grafo<T> grafo) {
		ServicioCaminos<T> recorrido = new ServicioCaminos(grafo, 7, 3, 6);
		List<List<Integer>> caminos = recorrido.caminos();
		System.out.println("Caminos encontrados:");
		for (List<Integer> camino : caminos) {
			System.out.println(camino);
		}
	}
	
	// Facundo Sanchez - Tudai Olavarria - Programacion 3

}
