package joaoPedroOliveira.banco;

public interface RepositorioDeContas {
    public void cadastrar(Conta c);
    public Conta pesquisar(int n);
    public void atualizar(Conta destino);
    public void deletarDados();
}
