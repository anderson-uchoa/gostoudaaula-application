package br.com.gostoudaaula.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		int op = 0;
		boolean status = true;
		while (status) {
			try {
				op = Integer
						.parseInt(JOptionPane
								.showInputDialog("Escolha o jogo que deseja\n1) mega sena\n2) 	loto fácil\n"));
			} catch (Exception e) {
				status = false;
				break;
			}

			Integer sorteados = 0;
			Integer qtdNumeros = 0;

			switch (op) {
			case 1:
				sorteados = 6;
				qtdNumeros = 60;
				new Main().geraNumeros(sorteados, qtdNumeros);
				break;

			case 2:
				sorteados = 15;
				qtdNumeros = 25;
				new Main().geraNumeros(sorteados, qtdNumeros);
				break;

			default:
				JOptionPane.showMessageDialog(null, "Opção inválida");
				break;
			}
		}
	}

	public void geraNumeros(Integer sorteados, Integer qtdNumeros) {
		Set<Integer> lista = new TreeSet<Integer>();
		while (lista.size() < sorteados) {
			int numero = new Random().nextInt(qtdNumeros) + 1;
			lista.add(numero);
		}
		List<Integer> listaOrdenanda = new ArrayList<Integer>(lista);
		Collections.sort(listaOrdenanda);
		JOptionPane.showMessageDialog(null, listaOrdenanda);
	}
}
