package blackjack.cartas;

/*enum que define os nipes de um baralho*/
public enum Nipe {
	C("Copas"), E("Espadas"), O("Ouros"), P("Paus"); 	
	
	private String descricao;
	private Nipe(String descricao) {
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}
}
