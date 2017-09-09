import java.util.ArrayList;

public class Jogador {
	 // ArrayList (lista) com as Cartas do jogador
    private ArrayList<Carta> cartas;
    // Nome do jogador: Jogador 1 e 2

    private String nome;

    // Construtor com parametro: recebe nome do jogador
    public Jogador(String n) {
        // Instancia ArrayList das cartas
        cartas = new ArrayList<>();
        nome = n;
    }
    // Adiciona carta 
    public void receberCarta(Carta carta) {
        cartas.add(carta);
    }
    // Retorna numero de cartas 
    public int getNumeroCartas() {
        return cartas.size();
    }
    // Retorna carta indicada (sem descarta-la)
    public Carta getCarta(int p) {
        // Verifica se carta existe
        if (p<0 || p >= cartas.size()) {
            return null;
        }
        return cartas.get(p);
    }
    // Retorna nome do jogador
   public String getNome() {
        return nome;
    }

}
