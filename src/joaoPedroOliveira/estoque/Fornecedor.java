package joaoPedroOliveira.estoque;

import java.util.ArrayList;
import java.util.Date;

public class Fornecedor {
    private int cnpj;
    private String nome;
    private ArrayList<Produto> produtos = new ArrayList<>();

    public Fornecedor(int cnpj, String nome) {
        this.cnpj = cnpj;
        this.nome = nome;
    }

    public int getCnpj() {
        return cnpj;
    }

    public void setCnpj(int cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    public void adicionarProduto(Produto p) {
        this.produtos.add(p);
    }
}