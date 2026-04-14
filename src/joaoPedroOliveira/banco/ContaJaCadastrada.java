package joaoPedroOliveira.banco;

public class ContaJaCadastrada extends Exception {
    public ContaJaCadastrada(int n) {
        super("Já existe uma conta de número " + n +
                " cadastrada em nosso banco.");
    }
}
