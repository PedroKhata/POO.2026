package joaoPedroOliveira.estoqueComProdutoPerecivelExcecoes;

public class ProdutoJaCadastrado extends Exception {
    public ProdutoJaCadastrado(int p) {
        super("Já existe um produto com o código " + p + "cadastrado em nosso estoque");
    }
}
