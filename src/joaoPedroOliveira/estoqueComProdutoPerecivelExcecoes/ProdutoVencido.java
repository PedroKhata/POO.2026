package joaoPedroOliveira.estoqueComProdutoPerecivelExcecoes;

public class ProdutoVencido extends Exception {
    public ProdutoVencido(int q1, int q2) {
        super("Estoque de itens dentro da validade é insuficiente. " +
                "Solicitado: " + q1 + ", Disponível: " + q2 + ".");
    }
}
