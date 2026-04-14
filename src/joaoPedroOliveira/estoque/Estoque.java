package joaoPedroOliveira.estoque;

import joaoPedroOliveira.estoqueComProdutoPerecivel.InterfaceEstoque;

import java.util.ArrayList;
import java.util.Date;

public class Estoque {
    private ArrayList<Produto> produtos = new ArrayList<>();

    public Estoque() {

    }

    public Produto buscarProduto(int cod) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getCod() == cod) {
                return produtos.get(i);
            }
        }
        return null;
    }

    public void incluir(Produto p) {
        if (buscarProduto(p.getCod()) == null) {
            produtos.add(p);
        }
    }

    public int quantidade(int cod) {
        Produto p = buscarProduto(cod);
        if (p != null) {
            return p.getQuantidade();
        }
        return -1;
    }

    public void comprar(int cod, int quant, double preco) {
        Produto p = buscarProduto(cod);
        if (p != null) {
            p.compra(quant, preco);
        }
    }

    public double vender(int cod, int quant) {
        double flag = -1;
        Produto p = buscarProduto(cod);
        if (p != null) {
            flag = p.venda(quant);
        }
        return flag;
    }

    public double precoDeVenda(int cod) {
        Produto p = buscarProduto(cod);
        if (p != null) {
            return p.getPrecoDeVenda();
        }
        return -1;
    }

    public double precoDeCompra(int cod) {
        Produto p = buscarProduto(cod);
        if (p != null) {
            return p.getPrecoDeCompra();
        }
        return -1;
    }

    public ArrayList<Fornecedor> fornecedores(int cod) {
        Produto p = buscarProduto(cod);
        if (p != null) {
            return p.getFornecedores();
        }
        return null;
    }

    public ArrayList<Produto> estoqueAbaixoDoMinimo() {
        ArrayList<Produto> prodsAbaixo = new ArrayList<>();
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getQuantidade() < produtos.get(i).getMin()) {
                prodsAbaixo.add(produtos.get(i));
            }
        }
        return prodsAbaixo;
    }

    public String movimentacao(int cod, Date inicio, Date fim) {
        Produto p = buscarProduto(cod);
        String s = "";
        if (p != null) {
            ArrayList<Registro> r = p.getRegistros();
            for (int i = 0; i < r.size(); i++) {
                if (inicio.getTime() <= r.get(i).getData().getTime() && r.get(i).getData().getTime() < fim.getTime()) {
                    s = s + r.get(i).formatarTexto();
                }
            }
        }
        return s;
    }

    public void adicionarFornecedor(int cod, Fornecedor f) {
        Produto p = buscarProduto(cod);
        if (p != null) {
            p.getFornecedores().add(f);
        }
    }
}

