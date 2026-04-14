package joaoPedroOliveira.estoque;

import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestesEstoqueResumido {

    @Test
    public void produtosAbaixoDoEstoqueMinimo() {
        Estoque estoque = new Estoque();
        Fornecedor forn1 = new Fornecedor(48, "Nestle");
        Fornecedor forn2 = new Fornecedor(19, "Ambev");
        Produto prod1 = new Produto(12, "Sorvete", 5, 1);
        Produto prod2 = new Produto(15, "Cerveja", 5, 1);
        Produto prod3 = new Produto(18, "Cerveja Pilsen", 5, 1);

        estoque.incluir(prod1);
        estoque.incluir(prod2);
        estoque.incluir(prod3);
        estoque.comprar(12, 3, 5);
        estoque.comprar(15, 1, 10);
        estoque.comprar(18, 5, 8);
        ArrayList<Produto> produtosAbaixoDoMinimo = estoque.estoqueAbaixoDoMinimo();
        assertEquals(2, produtosAbaixoDoMinimo.size());
    }

    @Test
    public void compraItens() {
        Estoque estoque = new Estoque();
        Fornecedor forn1 = new Fornecedor(48, "Nestle");
        Produto prod1 = new Produto(12, "Sorvete", 5, 0.5);
        estoque.incluir(prod1);
        estoque.comprar(12, 10, 4);
        estoque.comprar(12, 20, 7);
        assertEquals(6, estoque.precoDeCompra(12), 0.0001);
        assertEquals(9, estoque.precoDeVenda(12), 0.0001);
        // Verifica se a quantidade de itens foi atualizada corretamente
        assertEquals(30, estoque.quantidade(12));
    }

    @Test
    public void compraItensProdutoNaoIncluido() {
        Estoque estoque = new Estoque();
        estoque.comprar(12, 10, 4);
        assertEquals(-1, estoque.quantidade(12));
    }

    @Test
    public void compraQuantidadeNegativaDeItens() {
        Estoque estoque = new Estoque();
        Fornecedor forn1 = new Fornecedor(48, "Nestle");
        Produto prod1 = new Produto(12, "Sorvete", 5, 1);
        estoque.incluir(prod1);
        estoque.comprar(12, -10, 4);
        assertEquals(0, estoque.quantidade(12));
    }

    @Test
    public void vendeQuantidadeNegativaDeItens() {
        Estoque estoque = new Estoque();
        Fornecedor forn1 = new Fornecedor(48, "Nestle");
        Produto prod1 = new Produto(12, "Sorvete", 5, 1);
        estoque.incluir(prod1);
        estoque.comprar(12, 10, 4);
        estoque.vender(12, -5);
        assertEquals(10, estoque.quantidade(12));
    }


    @Test
    public void quantidadeProdutoNaoIncluido() {
        Estoque estoque = new Estoque();
        int retorno = estoque.quantidade(0);
        assertEquals(-1, retorno);
    }

    @Test
    public void verificaFornecedorProduto() {
        Estoque estoque = new Estoque();
        Fornecedor forn1 = new Fornecedor(48, "Nestle");
        Fornecedor forn2 = new Fornecedor(49, "Kibon");
        Fornecedor forn3 = new Fornecedor(50, "Quick");
        Produto prod1 = new Produto(12, "Sorvete", 5, 1);
        estoque.incluir(prod1);
        estoque.adicionarFornecedor(12, forn1);
        estoque.adicionarFornecedor(12, forn2);
        estoque.adicionarFornecedor(12, forn3);
        ArrayList<Fornecedor> forns = estoque.fornecedores(12);
        assertEquals(3, forns.size());
    }

    @Test
    public void vendeItens() throws InterruptedException {
        Estoque estoque = new Estoque();
        Fornecedor forn1 = new Fornecedor(48, "Nestle");
        Produto prod1 = new Produto(12, "Sorvete", 5, 1);
        estoque.incluir(prod1);

        Date d1 = new Date();
        Thread.sleep(100);

        estoque.comprar(12, 20, 5);
        estoque.comprar(12, 10, 5);

        // Verifica se o valor total da venda esta correto
        assertEquals(30, estoque.vender(12, 3), 0.0001);
        Thread.sleep(100);
        Date d2 = new Date();

        assertEquals(10, estoque.vender(12, 1), 0.0001);
        Thread.sleep(100);
        Date d3 = new Date();

        assertEquals(20, estoque.vender(12, 2), 0.0001);
        Thread.sleep(100);
        Date d4 = new Date();

        // Lógica para montar a String de data esperada (ex: 12/4/2026)
        int dia = d1.getDate();
        int mes = d1.getMonth() + 1;
        int ano = d1.getYear() + 1900;
        String strdt = dia + "/" + mes + "/" + ano;

        // Teste 1: Movimentação entre d1 e d2
        String movs = estoque.movimentacao(12, d1, d2);
        String movsEsperado1 = "Data: " + strdt + ". Compra. Valor: 5.0. Quantidade: 20.\n" +
                "Data: " + strdt + ". Compra. Valor: 5.0. Quantidade: 10.\n" +
                "Data: " + strdt + ". Venda. Valor: 10.0. Quantidade: 3.\n";
        assertEquals(movsEsperado1, movs);

        // Teste 2: Movimentação entre d1 e d3
        movs = estoque.movimentacao(12, d1, d3);
        String movsEsperado2 = "Data: " + strdt + ". Compra. Valor: 5.0. Quantidade: 20.\n" +
                "Data: " + strdt + ". Compra. Valor: 5.0. Quantidade: 10.\n" +
                "Data: " + strdt + ". Venda. Valor: 10.0. Quantidade: 3.\n" +
                "Data: " + strdt + ". Venda. Valor: 10.0. Quantidade: 1.\n";
        assertEquals(movsEsperado2, movs);

        // Teste 3: Movimentação entre d1 e d4
        movs = estoque.movimentacao(12, d1, d4);
        String movsEsperado3 = "Data: " + strdt + ". Compra. Valor: 5.0. Quantidade: 20.\n" +
                "Data: " + strdt + ". Compra. Valor: 5.0. Quantidade: 10.\n" +
                "Data: " + strdt + ". Venda. Valor: 10.0. Quantidade: 3.\n" +
                "Data: " + strdt + ". Venda. Valor: 10.0. Quantidade: 1.\n" +
                "Data: " + strdt + ". Venda. Valor: 10.0. Quantidade: 2.\n";
        assertEquals(movsEsperado3, movs);
    }
}
