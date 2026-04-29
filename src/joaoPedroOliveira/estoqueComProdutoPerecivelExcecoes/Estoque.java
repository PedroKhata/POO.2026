package joaoPedroOliveira.estoqueComProdutoPerecivelExcecoes;

import java.util.ArrayList;
import java.util.Date;

public class Estoque implements InterfaceEstoqueComExcecoes {
    private ArrayList<Produto> produtos = new ArrayList<>();

    @Override
    public int quantidade(int cod) throws ProdutoInexistente {
        return pesquisar(cod).getQuantidade();
    }

    @Override
    public Produto pesquisar(int cod) throws ProdutoInexistente {
        for (Produto produto : produtos) {
            if (produto.getCodigo() == cod) {
                return produto;
            }
        }
        throw new ProdutoInexistente(cod);
    }

    @Override
    public void incluir(Produto p) throws ProdutoJaCadastrado, DadosInvalidos {
        if (p == null) {
            throw new DadosInvalidos("");
        }
        if (p.getDescricao() == null || p.getDescricao().trim().isEmpty()) {
            throw new DadosInvalidos("");
        }
        if (p.getCodigo() <= 0 || p.getMin() <= 0 || p.getLucro() <= 0) {
            throw new DadosInvalidos("");
        }
        try {
            pesquisar(p.getCodigo());
            throw new ProdutoJaCadastrado(p.getCodigo());
        } catch (ProdutoInexistente _){

        }
        produtos.add(p);
    }

    @Override
    public void comprar(int cod, int quant, double preco, Date val) throws ProdutoInexistente, DadosInvalidos, ProdutoNaoPerecivel {
        Produto p = pesquisar(cod);
        if ((preco <= 0) || (quant <= 0)) {
            throw new DadosInvalidos("");
        }

        if (p instanceof ProdutoPerecivel) {
            if ((val == null) || (val.getTime() < new Date().getTime())) {
                throw new DadosInvalidos("");
            }
            ((ProdutoPerecivel) p).adicionarLote(quant, val);
            p.compra(quant, preco);

        } else {
            if (val != null) {
                throw new ProdutoNaoPerecivel();
            }
            p.compra(quant, preco);
        }
    }

    @Override
    public double vender(int cod, int quant) throws ProdutoInexistente, ProdutoVencido, DadosInvalidos {
        Produto p = pesquisar(cod);
        if (quant > p.getQuantidade()) {
            throw new DadosInvalidos("");
        }
        if (p instanceof ProdutoPerecivel) {
            return ((ProdutoPerecivel) p).venda(quant, new Date());
        } else {
            return p.venda(quant);
        }
    }

    @Override
    public ArrayList<Produto> estoqueAbaixoDoMinimo() {
        ArrayList<Produto> e = new ArrayList<>();
        for (Produto p : produtos) {
            if (p.getQuantidade() < p.getMin()) {
                e.add(p);
            }
        }
        return e;
    }

    @Override
    public ArrayList<Produto> estoqueVencido() {
        ArrayList<Produto> vencidos = new ArrayList<>();
        Date hoje = new Date();

        for (Produto p : produtos) {
            if (p instanceof ProdutoPerecivel) {
                if (((ProdutoPerecivel) p).verificarQuantVencidos(hoje) > 0) {
                    vencidos.add(p);
                }
            }
        }
        return vencidos;
    }

    @Override
    public int quantidadeVencidos(int cod) throws ProdutoInexistente {
        Produto p = pesquisar(cod);
        if (p instanceof ProdutoPerecivel) {
            return ((ProdutoPerecivel) p).verificarQuantVencidos(new Date());
        }
        return 0;
    }

    public void adicionarFornecedor(int cod, Fornecedor f) throws ProdutoInexistente {
        Produto p = pesquisar(cod);
        if (p != null && f != null) {
            p.getFornecedores().add(f);
        }
    }

    public double precoDeVenda(int cod) throws ProdutoInexistente {
        Produto p = pesquisar(cod);
        return p.getPrecoDeVenda();

    }

    public double precoDeCompra(int cod) throws ProdutoInexistente {
        Produto p = pesquisar(cod);
        return p.getPrecoDeCompra();
    }

    public String movimentacao(int cod, Date inicio, Date fim) throws ProdutoInexistente {
        Produto p = pesquisar(cod);
        String s = "";

        ArrayList<Registro> r = p.getRegistros();
        for (int i = 0; i < r.size(); i++) {
            if (inicio.getTime() <= r.get(i).getData().getTime() && r.get(i).getData().getTime() < fim.getTime()) {
                s = s + r.get(i).formatarTexto();
            }
        }
        return s;
    }
}
