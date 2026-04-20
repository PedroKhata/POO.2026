package joaoPedroOliveira.estoqueComProdutoPerecivel;

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
        for (Lote l : lotes) {
            if (l.verificarVencimento(atual)) {
                total += l.getQuantidade();
            }
        }
        return total;
    }

    public int verificarQuantValidos(Date atual) {
        int total = 0;
        for (Lote l : lotes) {
            if (!l.verificarVencimento(atual)) {
                total += l.getQuantidade();
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
        if (quant <= 0 || verificarQuantValidos(dataAtual) < quant) {
            return -1;
        } else {
            organizarLotes();
            for (Lote l : lotes) {
                if (!l.verificarVencimento(dataAtual)) {
                    quant = l.descontarQuantidade(quant);
                }
                if (quant == 0) break;
            }
        }
        limpeza();
        return super.venda(aux);
    }

    public ArrayList<Lote> getLotes() {
        return lotes;
    }
}