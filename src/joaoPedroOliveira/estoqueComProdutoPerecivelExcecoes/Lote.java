package joaoPedroOliveira.estoqueComProdutoPerecivelExcecoes;

import java.util.Date;

public class Lote {
    private int quantidade;
    private Date dataDeValidade;

    public Lote(int quant, Date validade) {
        setQuantidade(quant);
        setDataDeValidade(validade);
    }

    public boolean verificarVencimento(Date dataAtual) {
        long tAtual = dataAtual.getTime();
        long tVenc = getDataDeValidade().getTime();

        return tAtual >= tVenc;
    }

    public int descontarQuantidade(int quant) {
        if (quant <= getQuantidade()) {
            setQuantidade(getQuantidade() - quant);
            return 0;
        } else {
            int aux = getQuantidade();
            setQuantidade(0);
            return (quant - aux);
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
