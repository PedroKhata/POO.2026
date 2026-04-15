package joaoPedroOliveira.banco;

public interface RepositorioDeContas {
    public void cadastrar(Conta c) throws ContaJaCadastrada;
    public Conta pesquisar(int n) throws ContaInexistente;
}
