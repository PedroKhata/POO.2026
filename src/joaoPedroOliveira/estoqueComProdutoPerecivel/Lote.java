package joaoPedroOliveira.estoqueComProdutoPerecivel;

import java.util.Date;

public class Lote {
    private int quantidade;
    private Date dataDeValidade;

    public Lote(int quant, Date validade) {
        setQuantidade(quant);
        setDataDeValidade(validade);
    }

    public boolean verificarVencimento(Date dataAtual) {
        long atual = dataAtual.getTime();
        long venc = getDataDeValidade().getTime();

        return atual > venc;
    }

    public void descontarQuantidade(int quant) {
        if (quant > getQuantidade() || quant <= 0) {
            return;
        } else {
            setQuantidade(quantidade - quant);
        }
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setDataDeValidade(Date dataDeValidade) {
        this.dataDeValidade = dataDeValidade;
    }

    public Date getDataDeValidade() {
        return dataDeValidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
