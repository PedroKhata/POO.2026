package joaoPedroOliveira.banco;

public class NoListaDeContas implements RepositorioDeContas {
    private Conta conta = null;
    private NoListaDeContas prox = null;

    public void cadastrar(Conta c) {
        if (prox == null) {
            conta = c;
            prox = new NoListaDeContas();
        } else {
            prox.cadastrar(c);
        }
    }

    public Conta pesquisar(int n) {
        if (prox == null) {
            return null;
        }
        if (conta.numero() == n) {
            return conta;
        }
        return prox.pesquisar(n);
    }
}
