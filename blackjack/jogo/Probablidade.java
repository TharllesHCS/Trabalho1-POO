package blackjack.jogo;

import java.util.List;

import blackjack.cartas.Carta;
import blackjack.cartas.Nipe;
import blackjack.cartas.Simbolo;

public class Probablidade{
	
	private List<Carta> cartasSacadas;
	private int cartasDisponiveis;
	
	public Probablidade(List<Carta> cartasSacadas, int cartasDisponiveis) {
		this.cartasDisponiveis = cartasDisponiveis;
		this.cartasSacadas = cartasSacadas;
	}
	
	public double chanceSacarCartas(int pontuacao) {
		int qtdCartas = 0;
		
		for(Nipe nipe : Nipe.values()){
			for(Simbolo simbolo : Simbolo.values()){
				int valor = simbolo.getPontuacao();
				if(simbolo.equals(Simbolo.A)){
					valor -= 10;
				}
				if(valor <= pontuacao && !cartasSacadas.contains(new Carta(nipe, simbolo))){
					qtdCartas++;
				}
			}
		}
		
		return 1 - qtdCartas/(double)cartasDisponiveis;
		
	}
	
	
	public double chanceCartasSacada(int pontuacao){
		int qtdCartas = 0;
		
		for(Nipe nipe : Nipe.values()){
			for(Simbolo simbolo : Simbolo.values()){
				int valor = simbolo.getPontuacao();
				if(valor >= pontuacao && !cartasSacadas.contains(new Carta(nipe, simbolo))){
					qtdCartas++;
				}
			}
		}
		
		return qtdCartas/(double)cartasDisponiveis;
		
	}
}
