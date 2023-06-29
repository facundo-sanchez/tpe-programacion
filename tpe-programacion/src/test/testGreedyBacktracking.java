package test;

import java.util.ArrayList;
import java.util.List;

import grafo.Arco;
import tecnicas.Algoritmo;
import tecnicas.Backtracking;
import tecnicas.Greedy;
import utils.CSVReader;
import utils.Timer;

public class testGreedyBacktracking {

	public static <T> void main(String[] args) {

		String path0 = "./datasets/dataset0.txt";
		String path1 = "./datasets/dataset1.txt";
		String path2 = "./datasets/dataset2.txt";
		String path3 = "./datasets/dataset3.txt";
		CSVReader cs = new CSVReader(path1);
		cs.read();

		// Solucion Greedy
		Greedy greedy = new Greedy();
		imprimirSolucion(greedy, cs.getArcos());

		// Solucion Backtraking
		Backtracking back = new Backtracking();
		imprimirSolucion(back, cs.getArcos());

	}

	public static void imprimirSolucion(Algoritmo algoritmo, ArrayList<Arco<Integer>> arcos) {
		Timer t = new Timer();
		t.start();
		List<Arco<Integer>> solucion = algoritmo.solucion(arcos);
		System.out.println(algoritmo.getAlgoritmo());
		for (Arco<Integer> arco : solucion) {
			int origen = arco.getVerticeOrigen();
			int destino = arco.getVerticeDestino();
			System.out.print("E" + origen + "-E" + destino + ", ");
		}
		System.out.println("\nKilometros totales: " + algoritmo.getKms());
		System.out.println("Metrica: " + algoritmo.getMetrica());
		System.out.println("Tiempo transcurrido en la solucion: " + t.stop());
		System.out.println("\n");
	}

	// Facundo Sanchez - Tudai Olavarria - Programacion 3
}
