package joaoPedroOliveira.banco;

public class VetorDeContas implements RepositorioDeContas {
    private Conta contas[] = new Conta[10];
    private int pos = 0;

    public void cadastrar(Conta c) {
        contas[pos++] = c;
    }

    public Conta pesquisar(int n) {
        for (int i = 0; i < pos; i++) {
            if (contas[i].numero() == n) {
                return contas[i];
            }
        }
        return null;
    }

    @Override
    public void atualizar(Conta destino) {

    }

    @Override
    public void deletarDados() {

    }
}
