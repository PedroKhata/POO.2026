package joaoPedroOliveira.estoqueComProdutoPerecivel;

import java.util.ArrayList;
import java.util.Date;

public class Estoque implements InterfaceEstoque {
    private ArrayList<Produto> produtos = new ArrayList<>();

    public int quantidade(int cod) {
        Produto p = pesquisar(cod);
        if (p == null) {
            return -1;
        }
        return p.getQuantidade();
    }

    @Override
    public Produto pesquisar(int cod) {
        for (Produto produto : produtos) {
            if (produto.getCodigo() == cod) {
                return produto;
            }
        }
        return null;
    }

    @Override
    public boolean incluir(Produto p) {
        if (p == null) {
            return false;
        } else if (pesquisar(p.getCodigo()) != null) {
            return false;
        } else if (p.getDescricao() == null || p.getDescricao().isEmpty()) {
            return false;
        } else if (p.getCodigo() <= 0 || p.getMin() <= 0 || p.getLucro() <= 0) {
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
        } else if ((preco <= 0) || (quant <= 0)) {
            return false;
        }

        if (p instanceof ProdutoPerecivel) {
            if ((val == null) || (val.getTime() < new Date().getTime())) {
                return false;
            }
            ((ProdutoPerecivel) p).adicionarLote(quant, val);
            p.compra(quant, preco);

        } else {
            if (val != null) {
                return false;
            }
            p.compra(quant, preco);
        }
        return true;
    }

    @Override
    public double vender(int cod, int quant) {
        Produto p = pesquisar(cod);
        if (p == null) {
            return -1;
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
    public int quantidadeVencidos(int cod) {
        Produto p = pesquisar(cod);
        if (p == null) {
            return 0;
        }

        if (p instanceof ProdutoPerecivel) {
            return ((ProdutoPerecivel) p).verificarQuantVencidos(new Date());
        }

        return 0;
    }

    @Override
    public void adicionarFornecedor(int cod, Fornecedor f) {
        Produto p = pesquisar(cod);
        if (p != null && f != null) {
            p.getFornecedores().add(f);
        }
    }

    @Override
    public double precoDeVenda(int cod) {
        Produto p = pesquisar(cod);
        if (p != null) {
            return p.getPrecoDeVenda();
        }
        return 0;
    }

    @Override
    public double precoDeCompra(int cod) {
        Produto p = pesquisar(cod);
        if (p != null) {
            return p.getPrecoDeCompra();
        }
        return 0;
    }

    public String movimentacao(int cod, Date inicio, Date fim) {
        Produto p = pesquisar(cod);
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
}
