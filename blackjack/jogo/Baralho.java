package blackjack.jogo;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import blackjack.cartas.Carta;
import blackjack.cartas.Nipe;
import blackjack.cartas.Simbolo;
import blackjack.jogadores.Dealer;
import blackjack.jogadores.Jogador;

/*Classe que representa um baralho*/
public class Baralho {
	private List<Carta> cartas;
	private int qtdCartasDisponiveis;

	public Baralho(int qtdJogadores) {
		criaBaralho(qtdJogadores);
	}

	private void criaBaralho(int qtdJogadores) {
		cartas = new LinkedList<>();
		Nipe[] nipes = Nipe.values();
		Simbolo[] simbolos = Simbolo.values();
		for(int h = 0; h <= qtdJogadores/10; h++){
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 13; j++) {
					cartas.add(new Carta(nipes[i], simbolos[j]));
				}
			}
		}
		qtdCartasDisponiveis = cartas.size();
	}

	public int getQtdCartasDisponiveis() {
		return qtdCartasDisponiveis;
	}

	//Embaralha o baralho
	public void embaralhar() {
		Random randBase = new Random();
		Random randTroca = new Random();
		for (int i = 0; i < 1000; i++) {
			int indiceBase = randBase.nextInt(qtdCartasDisponiveis);
			int indiceTroca = randTroca.nextInt(qtdCartasDisponiveis);
			Carta base = cartas.get(indiceBase);
			Carta troca = cartas.get(indiceTroca);
			cartas.set(indiceBase, troca);
			cartas.set(indiceTroca, base);
		}
	}

	public void mostraBaralho() {
		for (int i = 0; i < qtdCartasDisponiveis; i++) {
			System.out.print(cartas.get(i) + " ");
			if (i % 5 == 1)
				System.out.println();
		}
	}

	//Remove uma carta do baralho e adiciona a mão de um jogador
	public Carta pegaCarta() {
		
		if (qtdCartasDisponiveis > 0) {
			Carta carta = cartas.get(0);
			cartas.remove(0);
			qtdCartasDisponiveis = cartas.size();
			return carta;
		}
		return null;
		
	}
	
	//Verifica se a carta existe no baralho
	public boolean contem(Carta carta){
		return cartas.contains(carta);
	}
	
	//Verifica quais quartas já foram sacadas
	public List<Carta> getCartasSacadas(Dealer dealer, List<Jogador> jogadores){
		List<Carta> cartasSacadas = new LinkedList<>();
		cartasSacadas.add(dealer.getCartas().get(1));
		for(Jogador jogador : jogadores){
			cartasSacadas.addAll(jogador.getCartas());
		}
		return cartasSacadas;
	}
}
