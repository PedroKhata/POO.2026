package joaoPedroOliveira.estoqueComProdutoPerecivel;

import joaoPedroOliveira.estoque.Fornecedor;
import joaoPedroOliveira.estoque.Produto;

import java.util.ArrayList;
import java.util.Date;

public class Estoque implements InterfaceEstoque {
    private ArrayList<Produto> produtos = new ArrayList<>();

    @Override
    public Produto pesquisar(int cod) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getCod() == cod) {
                return produtos.get(i);
            }
        }
        return null;
    }

    @Override
    public boolean incluir(Produto p) {
        if (p == null) {
            return false;
        } else if (pesquisar(p.getCod()) != null) {
            return false;
        } else if (p.getDesc() == null || p.getDesc().isEmpty()) {
            return false;
        } else if (p.getCod() <= 0 || p.getMin() <= 0 || p.getLucro() <= 0) {
            return false;
        } else {
            produtos.add(p);
            return true;
        }
    }

    @Override
    public boolean comprar(int cod, int quant, double preco, Date val) {
        Produto p = pesquisar(cod);
        if (p == null){
            return false;
        } else if (preco <= 0 || quant <= 0) {
            return false;
        } else if (p instanceof ProdutoPerecivel) {
            ProdutoPerecivel pp = (ProdutoPerecivel) p;
            if (val != null) {
                pp.adicionarLote(quant, val);
            } else {
                return false;
            }
        } else {
            if (val != null) {
                return false;
            }
            p.compra(quant, preco);
        }
        p.setPrecoDeCompra(((p.getQuantidade() *
                p.getPrecoDeCompra()) + (quant * preco)) /
                (p.getQuantidade() + quant));
        p.setPrecoDeVenda(p.getPrecoDeCompra() + (p.getPrecoDeCompra() * p.getLucro()));
        return true;
    }

    @Override
    public double vender(int cod, int quant) {
        return 0;
    }

    @Override
    public ArrayList<Produto> estoqueAbaixoDoMinimo() {
        return null;
    }

    @Override
    public ArrayList<Produto> estoqueVencido() {
        return null;
    }

    @Override
    public int quantidadeVencidos(int cod) {
        return 0;
    }

    @Override
    public void adicionarFornecedor(int cod, Fornecedor f) {

    }

    @Override
    public double precoDeVenda(int cod) {
        return 0;
    }

    @Override
    public double precoDeCompra(int cod) {
        return 0;
    }
}
