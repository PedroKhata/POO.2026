package joaoPedroOliveira.banco;

public class ContaInexistente extends Exception {
    public ContaInexistente(int n) {
        super("A conta de número " + n +
                " não existe.");
    }
}
