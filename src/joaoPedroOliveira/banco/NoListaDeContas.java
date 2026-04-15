package joaoPedroOliveira.banco;

public class NoListaDeContas implements RepositorioDeContas {
    private Conta conta = null;
    private NoListaDeContas prox = null;

    public void cadastrar(Conta c) throws ContaJaCadastrada{
        if (prox == null) {
            conta = c;
            prox = new NoListaDeContas();
        } else {
            if (conta.numero() == c.numero()) {
                throw new ContaJaCadastrada(c.numero());
            }
            prox.cadastrar(c);
        }
    }

    public Conta pesquisar(int n) throws ContaInexistente {
        if (prox == null) {
            throw new ContaInexistente(n);
        }
        if (conta.numero() == n) {
            return conta;
        }
        return prox.pesquisar(n);
    }
}
