package tecnicas;

import java.util.ArrayList;

import grafo.Arco;
import utils.Estado;
import utils.UnionFind;

public class Backtracking extends Algoritmo {

	private ArrayList<Arco<Integer>> mejorSolucion = new ArrayList<Arco<Integer>>();
	private ArrayList<Integer> estaciones = new ArrayList<>();
	private int metrica;
	private int kms;

	public Backtracking() {
		this.estaciones = new ArrayList<>();
		this.kms = Integer.MIN_VALUE;
		this.metrica = 0;
	}

	@Override
	public ArrayList<Arco<Integer>> solucion(ArrayList<Arco<Integer>> candidatos) {
		this.setKms(Integer.MAX_VALUE);
		this.getEstaciones(candidatos, estaciones);
		Estado estado = new Estado(estaciones.size());
		this.backtracking(candidatos, estado);
		return this.mejorSolucion;
	}

	// O(2^A) A es la cantidad de arcos
	private void backtracking(ArrayList<Arco<Integer>> candidatos, Estado e) {
		this.metrica++;
		if (e.getPosicion() == candidatos.size()) {
			if (e.getUnion().numberOfSets() == 1) {
				if (this.mejorSolucion.isEmpty()) {
					this.setKms(e.getKms());
					this.mejorSolucion.addAll(e.getSolucionParcial());
				} else {
					if (e.getKms() <= this.getKms()) {
						this.setKms(e.getKms());
						this.mejorSolucion.clear();
						this.mejorSolucion.addAll(e.getSolucionParcial());
					}
				}
			}

		} else {
			int posicionActual = e.getPosicion();
			int kmActual = e.getKms();
			Arco<Integer> arco = candidatos.get(posicionActual);

			int origenEstacion = this.estaciones.indexOf(arco.getVerticeOrigen());
			int destinoEstacion = this.estaciones.indexOf(arco.getVerticeDestino());

			if (this.arcoFactible(arco, e) && kmActual + arco.getEtiqueta() < this.getKms()) {
				UnionFind unionAux = e.getUnion().clone();
				e.getUnion().union(origenEstacion, destinoEstacion);
				e.addArco(arco);
				e.setKms(kmActual + arco.getEtiqueta());
				e.setPosicion(posicionActual + 1);
				this.backtracking(candidatos, e);
				e.setUnion(unionAux);
				e.removeArco(arco);
				e.setPosicion(posicionActual);
				e.setKms(kmActual);

			}
			e.setPosicion(posicionActual + 1);
			this.backtracking(candidatos, e);
			e.setPosicion(posicionActual);
		}
	}

	private boolean arcoFactible(Arco<Integer> arco, Estado e) {
		int destino = e.getUnion().find(this.estaciones.indexOf(arco.getVerticeDestino()));
		int origen = e.getUnion().find(this.estaciones.indexOf(arco.getVerticeOrigen()));

		if (destino != origen)
			return true;

		return false;
	}

	@Override
	public String getAlgoritmo() {
		// TODO Auto-generated method stub
		return "Backtraking";
	}

	@Override
	public int getMetrica() {
		// TODO Auto-generated method stub
		return this.metrica;
	}

	@Override
	public int getKms() {
		// TODO Auto-generated method stub
		return this.kms;
	}

	public void setKms(int kms) {
		// TODO Auto-generated method stub
		this.kms = kms;
	}

}
