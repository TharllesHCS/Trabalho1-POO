
public class Baralho {
	// Baralho contem um array de cartas
    private Carta cartas[];
    // Estado embaralhado ou ordenado
    private boolean embaralhado;
    // Quantidade de cartas disponiveis
    private int disponiveis;

    // Construtor
    // prepara um baralho simples (uma carta de cada valor e naipe)


    public Baralho() {
        // Inicializa numero de cartas disponiveis
        disponiveis = Carta.Naipes.values().length * Carta.Simbolo.length;
        // Inicializa arrays de cartas
        cartas = new Carta[disponiveis];
        // lacos aninhados criam uma carta de cada valor e naipe
        for (Carta.Naipes n : Carta.Naipes.values()) {
            for (int v = 1; v<=Carta.Simbolo.length; v++) {
                int pos = v + n.ordinal() * Carta.Simbolo.length - 1;
                cartas[pos] = new Carta(v, n);
            }
        }
        // baralho esta ordenado
        embaralhado = false;
    }

    // Retira uma carta do baralho (a ultima)
    // array de cartas serah usado como uma pilha!
    public Carta getCarta() {
        if (disponiveis > 0) {
            return cartas[--disponiveis];
        }
        return null;
    }
    // Verifica se esta ou nao embaralhado
    public boolean isEmbaralhado() {
        return embaralhado;
    }
    // Retorna quantidade de cartas disponiveis
    public int getDisponiveis() {
        return disponiveis;
    }
    // Embaralha cartas
    public void embaralhar() {
        for (int n=0; n < disponiveis; n++) {
            Carta aux = cartas[n];
            int p = (int)(Math.random() * disponiveis);
            cartas[n] = cartas[p];
            cartas[p] = aux;
        }
    }
    // Exibe monte (pilha) de cartas
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int n=0; n < disponiveis; n++) {
            sb.append(cartas[n]);
            sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
    
}
