

public class Carta {
    // Definicao dos naipes
    public static enum Naipes { Paus, Copas, Espadas, Ouros };


    // Nomes dos valores das cartas
    public static String[] Simbolo = { "As", "2", "3", "4", "5", "6",
        "7", "8", "9", "10", "J", "Q", "K"
    };



    // Atributos da carta: valor e naipe da carta
    private int valor;
    private Naipes naipe;



    // Metodos de acesso para naipe e valor
    protected void setNaipe(Naipes naipe) {
        this.naipe = naipe;
    }


    public Naipes getNaipe() {


        return naipe;


    }



    protected void setValor(int valor) {
        this.valor = valor;
    }
    protected int getValor() {
        return valor;
    }

    // Representacao textual ca carta:
    // "<valor> de <naipe>"
    public String toString() {
        return Simbolo[valor-1] + " de " + naipe; 
    }

    // construtor
    public Carta(int valor, Naipes naipe) {
        setNaipe(naipe);
        setValor(valor);
    }
	
}
