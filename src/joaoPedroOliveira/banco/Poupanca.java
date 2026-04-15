package joaoPedroOliveira.banco;

public class Poupanca extends ContaComum {
    public void juros(double tx) {
        credito(saldo() * tx);
    }
    public Poupanca(int n, Pessoa p) {
        super(n, p);
    }
}
