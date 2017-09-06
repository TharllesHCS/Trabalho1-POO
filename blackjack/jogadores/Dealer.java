package blackjack.jogadores;

import blackjack.cartas.Carta;

/*Classe que representa a mesa, ou jogador do cassino a ser batido*/
public class Dealer extends Jogador{

	public Dealer(Carta carta1, Carta carta2) {
		super("Dealer", carta1, carta2);
		cartas.get(0).setVirada(false);
	}
		
	public void viraCarta(){
		cartas.get(0).setVirada(true);
	}
	
	public void mostra(){
		System.out.println(this);
	}
}
