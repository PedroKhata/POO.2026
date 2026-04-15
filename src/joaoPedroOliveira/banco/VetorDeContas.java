package joaoPedroOliveira.banco;

public class VetorDeContas implements RepositorioDeContas {
    private Conta contas[] = new Conta[10];
    private int pos = 0;

    public void cadastrar(Conta c) throws ContaJaCadastrada {
        for (int i = 0; i < pos; i++) {
            if (contas[i].numero() == c.numero()) {
                throw new ContaJaCadastrada(c.numero());
            }
        }
        contas[pos++] = c;
    }

    public Conta pesquisar(int n) throws ContaInexistente {
        for (int i = 0; i < pos; i++) {
            if (contas[i].numero() == n) {
                return contas[i];
            }
        }
        throw new ContaInexistente(n);
    }
}
