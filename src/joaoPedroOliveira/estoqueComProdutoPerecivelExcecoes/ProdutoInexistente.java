package joaoPedroOliveira.estoqueComProdutoPerecivelExcecoes;

public class ProdutoInexistente extends Exception {
    public ProdutoInexistente(int p) {
        super("O produto de código " + p + " não existe em nosso estoque.");
    }
}
