package joaoPedroOliveira.estoqueComProdutoPerecivel;

import java.util.ArrayList;
import java.util.Date;

public class Produto {
    private int cod;
    private String desc;
    private int min;
    private double lucro;
    private int quantidade = 0;
    private double precoDeCompra = 0.0;
    private double precoDeVenda = 0.0;
    private ArrayList<Fornecedor> fornecedores = new ArrayList<>();
    private ArrayList<Registro> registros = new ArrayList<>();

    public Produto(int cod, String desc, int min, double lucro) {
        this.cod = cod;
        this.desc = desc;
        this.min = min;
        this.lucro = lucro;
    }

    public void compra(int quant, double val) {
        if (quant > 0 && val >= 0) {
            precoDeCompra = ((quantidade * precoDeCompra) + (quant * val)) / (quantidade + quant);
            precoDeVenda = precoDeCompra + (precoDeCompra * lucro);
            quantidade += quant;
            Registro r = new Registro(new Date(), "Compra", quant, val);
            registros.add(r);
        } else {
            return;
        }
    }

    public double venda(int quant) {
        if (quantidade > 0 && quant <= quantidade && quant > 0) {
            quantidade -= quant;
            Registro r = new Registro(new Date(), "Venda", quant, precoDeVenda);
            registros.add(r);
            return quant * precoDeVenda;
        } else  {
            return -1;
        }
    }

    public int getCodigo() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getDescricao() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public double getLucro() {
        return lucro;
    }

    public void setLucro(double lucro) {
        this.lucro = lucro;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoDeCompra() {
        return precoDeCompra;
    }

    public void setPrecoDeCompra(double precoDeCompra) {
        this.precoDeCompra = precoDeCompra;
    }

    public double getPrecoDeVenda() {
        return precoDeVenda;
    }

    public void setPrecoDeVenda(double precoDeVenda) {
        this.precoDeVenda = precoDeVenda;
    }

    public ArrayList<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public ArrayList<Registro> getRegistros() {
        return registros;
    }
}