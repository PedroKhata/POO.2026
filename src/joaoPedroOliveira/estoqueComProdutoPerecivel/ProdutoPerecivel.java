package joaoPedroOliveira.estoqueComProdutoPerecivel;

import joaoPedroOliveira.estoque.Produto;

import java.util.ArrayList;
import java.util.Date;

public class ProdutoPerecivel extends Produto {
    private ArrayList<Lote> lotes = new ArrayList<>();

    public ProdutoPerecivel(int cod, String desc, int min, double lucro) {
        super(cod, desc, min, lucro);
    }

    public void adicionarLote(int quant, Date vencimento) {
        Lote l = new Lote(quant, vencimento);
        lotes.add(l);
    }

    public int verificarQuantVencidos(Date atual) {
        int total = 0;
        long tAtual = atual.getTime();
        for (int i = 0; i < lotes.size(); i++) {
            long tLote = lotes.get(i).getDataDeValidade().getTime();
            if (tAtual > tLote) {
                total += lotes.get(i).getQuantidade();
            }
        }
        return total;
    }

    public int verificarQuantValidos(Date atual) {
        int total = 0;
        long tAtual = atual.getTime();
        for (int i = 0; i < lotes.size(); i++) {
            long tLote = lotes.get(i).getDataDeValidade().getTime();
            if (tAtual <= tLote) {
                total += lotes.get(i).getQuantidade();
            }
        }
        return total;
    }

    public void organizarLotes() {
        boolean swap;
        int n = lotes.size();
        for (int i = 0; i < n - 1; i++) {
            swap = false;
            for (int j = 0; j < n - i - 1; j++) {
                long tAtual = lotes.get(j).getDataDeValidade().getTime();
                long tProx = lotes.get(j + 1).getDataDeValidade().getTime();
                if (tAtual > tProx) {
                    Lote aux = lotes.get(j);
                    lotes.set(j, lotes.get(j + 1));
                    lotes.set(j + 1, aux);
                    swap = true;
                }
            }
            if (!swap) break;
        }
    }

    public void limpeza() {
        for (int i = lotes.size() - 1; i >= 0; i--) {
            if (lotes.get(i).getQuantidade() == 0) {
                lotes.remove(i);
            }
        }
    }

    public double venda(int quant, Date dataAtual) {
        int aux = quant;
        int q;
        if (quant <= 0 || verificarQuantValidos(dataAtual) < quant) {
            return -1;
        } else {
            organizarLotes();
            for (int i = 0; i < lotes.size(); i++) {
                long tAtual = dataAtual.getTime();
                long tVenc = lotes.get(i).getDataDeValidade().getTime();
                if (tAtual <= tVenc) {
                    if (quant <= lotes.get(i).getQuantidade()) {
                        q = lotes.get(i).getQuantidade() - quant;
                        lotes.get(i).setQuantidade(q);
                        quant = 0;
                    } else {
                        quant -= lotes.get(i).getQuantidade();
                        lotes.get(i).setQuantidade(0);
                    }
                }
                if (quant == 0) break;
            }
        }
        limpeza();
        return aux * getPrecoDeVenda();
    }

    public ArrayList<Lote> getLotes() {
        return lotes;
    }
}