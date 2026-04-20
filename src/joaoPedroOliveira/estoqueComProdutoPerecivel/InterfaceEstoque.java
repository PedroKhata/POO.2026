package joaoPedroOliveira.estoqueComProdutoPerecivel;

import java.util.ArrayList;
import java.util.Date;

public interface InterfaceEstoque {
    public boolean incluir(Produto p);
    public boolean comprar(int cod, int quant, double preco, Date val);
    public double vender(int cod, int quant);
    public Produto pesquisar(int cod);
    public ArrayList<Produto> estoqueAbaixoDoMinimo();
    public ArrayList<Produto> estoqueVencido();
    public int quantidadeVencidos(int cod);
    public void adicionarFornecedor(int cod, Fornecedor f);
    public double precoDeVenda(int cod);
    public double precoDeCompra(int cod);
}
