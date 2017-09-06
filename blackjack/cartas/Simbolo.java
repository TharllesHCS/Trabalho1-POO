package blackjack.cartas;

/*enum que define os simblolos das cartas de um baralho*/
public enum Simbolo {
	A("As", 11), N2("Dois", 2), N3("Tres", 3), N4("Quatro", 4), N5("Cinco", 5),
	N6("Seis", 6), N7("Sete", 7), N8("Oito", 8), N9("Nove", 9), N10("Dez", 10),
	J("Valete", 10) ,Q("Dama", 10), K("Rei", 10);
	
	private String descricao;
	private int pontuacao;
	
	private Simbolo(String descricao, int pontuacao) {
		this.descricao = descricao;
		this.pontuacao = pontuacao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public int getPontuacao() {
		return pontuacao;
	}
	
}
