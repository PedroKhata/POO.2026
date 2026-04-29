package joaoPedroOliveira.estoqueComProdutoPerecivelExcecoes;

public class ProdutoNaoPerecivel extends Exception {
    public ProdutoNaoPerecivel() {
        super("Não é possível registrar lote de produtos não perecíveis");
    }
}
