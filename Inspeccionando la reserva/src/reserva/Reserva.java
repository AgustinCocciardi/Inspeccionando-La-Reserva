package reserva;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Reserva {

	private int cantidadDeNodos;
	private int cantidadAristas;
	private int[] estado;
	private int[][] matrizAdyacencia;
	private int[] formasDeLlegar;
	private int entrada;
	private int salida;
	private final int VISITADO = 1;
	private final int NOVISITADO = 0;
	private LinkedList cola = new LinkedList();
	
	public Reserva(Scanner entrada){
		int nodo1, nodo2;
		this.cantidadDeNodos = entrada.nextInt();
		this.cantidadAristas = entrada.nextInt();
		this.matrizAdyacencia = new int[this.cantidadDeNodos][this.cantidadDeNodos];
		for(int[] row : matrizAdyacencia)
			Arrays.fill(row, 0);
		this.formasDeLlegar = new int[this.cantidadDeNodos];
		Arrays.fill(this.formasDeLlegar, 0);
		this.estado = new int[this.cantidadDeNodos];
		Arrays.fill(estado, NOVISITADO);
		for(int i=0; i<this.cantidadAristas; i++){
			nodo1 = entrada.nextInt() - 1;
			nodo2 = entrada.nextInt() - 1;
			this.matrizAdyacencia[nodo1][nodo2] = 1;
		}
		this.entrada = 0;
		this.salida = this.cantidadDeNodos - 1;
	}
	
	public void resolver(PrintWriter salida){
		int nodoU;
		this.cola.offer(this.entrada);
		this.formasDeLlegar[this.entrada] = 1;
		while(this.cola.isEmpty() == false){
			nodoU = (Integer) this.cola.poll();
			for(int i=0; i<this.cantidadDeNodos; i++){
				if(i != nodoU && this.matrizAdyacencia[nodoU][i] == 1){
					if(this.estado[i] == NOVISITADO){
						this.estado[i] = VISITADO;
						this.cola.offer(i);
					}
					this.formasDeLlegar[i] += this.formasDeLlegar[nodoU];
				}
			}
		}
		salida.println(this.formasDeLlegar[this.salida]);
	}

	public static void main(String[] args) throws IOException {
		Scanner entrada = new Scanner(new FileReader("reserva.in"));
		Reserva reserva = new Reserva(entrada);
		entrada.close();
		PrintWriter salida = new PrintWriter(new FileWriter("reserva.out"));
		reserva.resolver(salida);
		salida.close();
	}

}