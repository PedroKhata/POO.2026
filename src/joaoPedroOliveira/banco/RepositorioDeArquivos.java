package joaoPedroOliveira.banco;

public interface RepositorioDeArquivos extends RepositorioDeContas {
    public void atualizar(Conta c);
    public void deletarDados();
}
