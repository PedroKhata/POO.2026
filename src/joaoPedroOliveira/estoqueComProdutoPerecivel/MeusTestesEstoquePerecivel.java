package joaoPedroOliveira.estoqueComProdutoPerecivel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import org.junit.Test;

public class MeusTestesEstoquePerecivel {
    @Test
    public void garantirVendaSemEstoqueSuficiente() {
        Estoque estoque = new Estoque();
        Produto prod1 = new ProdutoPerecivel(17, "Iogurte", 5, 1.5);
        estoque.incluir(prod1);

        Date dataHoje = new Date();
        Date dataFutura = new Date();
        dataFutura.setTime(dataHoje.getTime() + 86400000);

        estoque.comprar(17, 5, 2.0, dataFutura);
        estoque.comprar(17, 5, 2.0, dataFutura);

        assertEquals(10, estoque.quantidade(17));

        double retornoVenda = estoque.vender(17, 15);

        assertEquals(-1, retornoVenda, 0.001);

        assertEquals(10, estoque.quantidade(17));
    }

    @Test
    public void vendaSemLotesVencidos() {
        Estoque estoque = new Estoque();
        Produto prod1 = new ProdutoPerecivel(20, "Queijo", 5, 1.0);
        estoque.incluir(prod1);

        Date dataHoje = new Date();
        Date loteA = new Date(dataHoje.getTime() + (86400000L * 5));
        Date loteB = new Date(dataHoje.getTime() + (86400000L * 2));
        Date loteC = new Date(dataHoje.getTime() + (86400000L * 10));

        estoque.comprar(20, 10, 5.0, loteC);
        estoque.comprar(20, 10, 5.0, loteB);
        estoque.comprar(20, 10, 5.0, loteA);

        assertEquals(30, estoque.quantidade(20));

        loteB.setTime(dataHoje.getTime() - (86400000L * 2));

        double retornoVenda = estoque.vender(20, 15);

        assertEquals(150.0, retornoVenda, 0.001);

        assertEquals(15, estoque.quantidade(20));

        assertEquals(10, estoque.quantidadeVencidos(20));
    }

    @Test
    public void verificarTempoDeVencimento() {
        ProdutoPerecivel produto = new ProdutoPerecivel(30, "Iogurte", 5, 1.0);
        produto.compra(10, 5.0);

        long tempoBase = 1700000000000L;
        Date dataValidade = new Date(tempoBase);
        produto.adicionarLote(10, dataValidade);

        Date dataExata = new Date(tempoBase);
        double retornoExato = produto.venda(5, dataExata);

        assertEquals(-1.0, retornoExato, 0.001);

        assertEquals(10, produto.getQuantidade());

        Date dataAnterior = new Date(tempoBase - 1);
        double retornoVenda = produto.venda(5, dataAnterior);

        assertEquals(50.0, retornoVenda, 0.001);

        assertEquals(5, produto.getQuantidade());
    }

    @Test
    public void nullReturnEmEstoqueNovo() {
        Estoque estoque = new Estoque();

        ArrayList<Produto> abaixoDoMinimo = estoque.estoqueAbaixoDoMinimo();
        ArrayList<Produto> vencidos = estoque.estoqueVencido();

        assertNotNull(abaixoDoMinimo);
        assertNotNull(vencidos);

        assertEquals(0, abaixoDoMinimo.size());
        assertEquals(0, vencidos.size());
    }
}
