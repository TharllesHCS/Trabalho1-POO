package blackjack.cartas;

/*
 * Classe que define uma carta
 *  
 */
public class Carta {
	private Nipe nipe;
	private Simbolo simbolo;
	private boolean virada = true;
	
	public Carta(Nipe nipe, Simbolo simbolo) {
		this.nipe = nipe;
		this.simbolo = simbolo;
	}

	public Nipe getNipe() {
		return nipe;
	}

	public Simbolo getSimbolo() {
		return simbolo;
	}
	

	/*Verifica se pode mostrar a carta*/
	public boolean isVirada() {
		return virada;
	}

	public void setVirada(boolean virada) {
		this.virada = virada;
	}

	/*Apresentação de uma carta, mostrando a descrição do simbolo e nipe ou a parte de traz da casa, caso esteja desvirada*/
	@Override
	public String toString() {
		if(virada){
			return simbolo.getDescricao() + " de " + nipe.getDescricao();
		}else{
			return "**********";
		}
	}
	
	/*Define a igualdade entre cartas*/
	@Override
	public boolean equals(Object obj) {
		Carta c = (Carta) obj;
		if(nipe.equals(c.nipe) && simbolo.equals(c.simbolo)){
			return true;
		}else{
			return false;
		}
	}

	
}	
