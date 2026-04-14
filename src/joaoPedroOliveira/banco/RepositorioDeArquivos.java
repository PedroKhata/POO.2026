package joaoPedroOliveira.banco;

public interface RepositorioDeArquivos extends RepositorioDeContas {
    void atualizar(Conta c);
    void deletarDados(String n);
}
