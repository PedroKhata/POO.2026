package joaoPedroOliveira.banco;

public class SaldoInsuficiente extends Exception {
    public SaldoInsuficiente(int n, double s) {
        super("A conta de número " + n +
                " não possui saldo suficiente para a operação. Saldo: "
                + s + ".");
    }
}
