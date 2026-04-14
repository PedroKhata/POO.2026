package joaoPedroOliveira.banco;

public class NaoEhPoupanca extends Exception {
    public NaoEhPoupanca(int n) {
        super("A conta de número " + n +
                " não é de poupança, portanto, não rende juros.");
    }
}
