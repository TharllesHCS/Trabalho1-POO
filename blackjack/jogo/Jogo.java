package blackjack.jogo;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import blackjack.cartas.Carta;
import blackjack.jogadores.Dealer;
import blackjack.jogadores.Jogador;

public class Jogo {
	private List<Jogador> jogadores = new LinkedList<>();
	private Baralho baralho;
	private Scanner entrada;
	private Dealer dealer;
	private Jogador jogadorVez;

	public Jogo(boolean testeRapido) {
		if(testeRapido){
			int qtdJogadores = 3;
			iniciar(qtdJogadores);
			for (int i = 1; i <= qtdJogadores; i++) {
				addJogador("Player " + i);
			}
		}else{
			System.out.println("Informe a quantidade de jogadores : ");
			entrada = new Scanner(System.in);
			int qtdJogadores = Integer.parseInt(entrada.nextLine());
			iniciar(qtdJogadores);
			for (int i = 1; i <= qtdJogadores; i++) {
				System.out.println("Informe o nome do jogador " + i + " : ");
				entrada = new Scanner(System.in);
				addJogador(entrada.nextLine());
			}
		}
		jogar();
		resultado();
	}

	//Inicializa o jogo montando o baralho, embaralhando e distribuindo as cartas do dealer
	private void iniciar(int qtdJogadores) {
		baralho = new Baralho(qtdJogadores);
		baralho.embaralhar();
		dealer = new Dealer(baralho.pegaCarta(), baralho.pegaCarta());
	}

	//Distribui cartas do jogador
	private void addJogador(String nome) {
		jogadores.add(new Jogador(nome, baralho.pegaCarta(), baralho.pegaCarta()));
	}

	//Jogo
	private void jogar() {
		jogadorVez = jogadores.get(0);
		while (jogadorVez != null) {
			if (baralho.getQtdCartasDisponiveis() <= 0) {
				break;
			}
			System.out.println("---------- "+ jogadorVez.getNome() + " -> " + jogadorVez.getPontos() + " pontos -------------");
			
			Probablidade probablidade = new Probablidade(baralho.getCartasSacadas(dealer, jogadores), baralho.getQtdCartasDisponiveis());
			System.out.println("Cartas                  : " + jogadorVez.getCartas());
			System.out.println("Chance de ganhar        : " + jogadorVez.chanceVencer(dealer, probablidade) + "%");
			System.out.println("Chance de ser eliminado : " + jogadorVez.chanceEliminacao(probablidade) + "%");
			dealer.mostra();
			System.out.print("Sacar outra carta ? ");
			entrada = new Scanner(System.in);
			if (entrada.next().equals("s")) {
				Carta carta = baralho.pegaCarta();
				jogadorVez.setCarta(carta);
				System.out.println("Carta sacada : " + carta);
				if (jogadorVez.getPontos() >= 21) {
					System.out.println("21 ou mais! acabaram suas jogadas!");
					jogadorVez.parar();
				}
			} else {
				jogadorVez.parar();
			}
			jogadorVez = proximoJogador();
			entrada = new Scanner(System.in);
			System.out.println("Precione enter para ir para o proximo jogador...");
			entrada.nextLine();
		}
	}

	//Decide de quem é a proxima vez
	private Jogador proximoJogador() {
		int jogadorAtual = jogadores.indexOf(jogadorVez);
		int proximoJogador = jogadorAtual;
		
		do{
			proximoJogador++;
			if(proximoJogador == jogadores.size()){
				proximoJogador = 0;
			}
			if(!jogadores.get(proximoJogador).isParou()){
				return jogadores.get(proximoJogador);
			}
		}while(proximoJogador != jogadorAtual);
		return null;
	}
	
	

	//Mostra resultado
	private void resultado() {
		dealer.viraCarta();
		dealer.mostra();
		for (Jogador p : jogadores) {
			System.out.println(p);
		}
		System.out.println("Jogador(es) vencedor(es) : " + decideVitoria());
	}

	//Calcula vencedor
	private String decideVitoria() {
		List<Jogador> vencedores = new LinkedList<>();
		for (Jogador p : jogadores) {
			if (p.getPontos() <= 21 && p.getPontos() > dealer.getPontos()) {
				vencedores.add(p);
			}
		}

		if (!vencedores.isEmpty()) {
			String retorno = "";
			boolean empate = true;
			for (Jogador vitorioso : vencedores) {
				retorno += vitorioso.getNome();
				if (vencedores.indexOf(vitorioso) != vencedores.size() - 1) {
					retorno += ", ";
				}
				if (vitorioso.getPontos() != dealer.getPontos()) {
					empate = false;
				}
			}
			if (empate) {
				retorno = retorno + ", " + dealer.getNome();
			}
			return retorno;
		} else {
			return dealer.getNome();
		}

	}
}
