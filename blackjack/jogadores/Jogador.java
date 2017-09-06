package blackjack.jogadores;

import java.util.LinkedList;
import java.util.List;

import blackjack.cartas.Carta;
import blackjack.cartas.Simbolo;
import blackjack.jogo.Probablidade;

public class Jogador {
	private String nome;
	protected List<Carta> cartas = new LinkedList<>();
	private boolean parou = false;
	protected int pontos;

	public Jogador(String nome, Carta carta1, Carta carta2) {
		this.nome = nome;
		cartas.add(carta1);
		cartas.add(carta2);
	}
	
	public String getNome() {
		return nome;
	}

	public void setCarta(Carta carta) {
		cartas.add(carta);
	}
	
	public List<Carta> getCartas() {
		return cartas;
	}

	//Soma os pontos das cartas obtidas até o momento
	protected void somaPontos() {
		pontos = 0;
		boolean as = false;
		for (Carta carta : cartas) {
			if(carta.isVirada()){
				if (carta.getSimbolo().equals(Simbolo.A)) {
					as = true;
				}
				pontos += carta.getSimbolo().getPontuacao();
			}			
		}
		if(as && pontos > 21){
			for(Carta carta : cartas){
				if(carta.getSimbolo().equals(Simbolo.A)){
					pontos -= 10;
					if(pontos <= 21){
						break;
					}
				}
			}
			
		}
	}
	
	public int getPontos() {
		somaPontos();
		return pontos;
	}

	//Verifica se parou de jogar
	public boolean isParou() {
		return parou;
	}
	
	//Para de jogar
	public void parar(){
		parou = true;
	}
	
	//Calcula a chance de ser eliminado ao sacar uma carta dada a probabilidade dessa carta estar no baralho	
	public double chanceEliminacao(Probablidade probablidade) {
		int pontosConquistaveis = 21 - getPontos();
		if(pontosConquistaveis >= 10){
			return 0;
		}else{
			return probablidade.chanceSacarCartas(pontosConquistaveis)*100;	
		}
	}
	
	//Calcula a chance de vencer com as cartas que tem, dada a probabilidade da soma de seus pontos ser maior que a do dealer
	public double chanceVencer(Dealer dealer, Probablidade probablidade) {
		int pontosDealer = dealer.getPontos();
		int pontosJogador = getPontos();
		int diffPontos = pontosJogador - pontosDealer;
		
		if(diffPontos > 2){
			double chanceCartas = probablidade.chanceCartasSacada(diffPontos);
			return 100 - chanceCartas * 100;
		}
		return 0;
	}
	
	@Override
	public String toString() {
		return nome + " : " + getPontos() + " pontos -> " + cartas;
	}
	
	@Override
	public boolean equals(Object obj) {
		return nome.equals(((Jogador)obj).getNome());
	}
	
}
