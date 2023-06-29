package tecnicas;

import java.util.ArrayList;

import grafo.Arco;
import utils.UnionFind;

public class Greedy extends Algoritmo {

	private ArrayList<Integer> estaciones = new ArrayList<>();
	private UnionFind unionFind;
	private int metrica;
	private int kms;

	// O(N^2)
	@Override
	public ArrayList<Arco<Integer>> solucion(ArrayList<Arco<Integer>> candidatos) {
		ArrayList<Arco<Integer>> solucion = new ArrayList<Arco<Integer>>();

		// O(N^2)
		this.ordenarCandidatosMenorKm(candidatos);

		// O(N)
		this.getEstaciones(candidatos, estaciones);
		this.unionFind = new UnionFind(this.estaciones.size());

		// O(N)
		while (!candidatos.isEmpty() && solucion.size() < this.estaciones.size() - 1) {
			this.metrica++;
			Arco<Integer> arco = candidatos.remove(0);
			if (this.arcoFactible(arco)) {
				solucion.add(arco);
				this.unionFind.union(this.estaciones.indexOf(arco.getVerticeOrigen()),
						this.estaciones.indexOf(arco.getVerticeDestino()));
				setKms(this.getKms()+arco.getEtiqueta());
			}

		}

		return solucion;
	}

	// Notacion big O(N^2)
	private void ordenarCandidatosMenorKm(ArrayList<Arco<Integer>> candidatos) {
		for (int i = 0; i < candidatos.size(); i++) {
			for (int j = 0; j < candidatos.size() - 1; j++) {
				Arco<Integer> arcoActual = candidatos.get(j);
				Arco<Integer> arcoSiguiente = candidatos.get(j + 1);
				if (arcoActual.getEtiqueta() > arcoSiguiente.getEtiqueta()) {
					candidatos.set(j, arcoSiguiente);
					candidatos.set(j + 1, arcoActual);
				}
			}
		}
	}

	private boolean arcoFactible(Arco<Integer> arco) {
		int destino = this.unionFind.find(this.estaciones.indexOf(arco.getVerticeDestino()));
		int origen = this.unionFind.find(this.estaciones.indexOf(arco.getVerticeOrigen()));

		if (destino != origen)
			return true;

		return false;
	}

	public ArrayList<Integer> getEstaciones() {
		return estaciones;
	}

	@Override
	public String getAlgoritmo() {
		return "Greedy";
	}

	@Override
	public int getMetrica() {
		return metrica;
	}

	@Override
	public int getKms() {
		return kms;
	}
	
	@Override
	public void setKms(int kms) {
		// TODO Auto-generated method stub
		this.kms = kms;
	}

}
