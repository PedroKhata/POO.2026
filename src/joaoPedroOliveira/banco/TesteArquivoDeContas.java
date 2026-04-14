package joaoPedroOliveira.banco;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TesteArquivoDeContas {
    @Test
    public void testeArquivo() {
        Banco b = new Banco("teste_arq_contas.dat");
        b.hardReset();
        //Obs.: hardReset limpa o .dat
        Pessoa p1 = new Pessoa(1);
        Conta c1 = new Conta(100, p1);
        Pessoa p2 = new Pessoa(2);
        Conta c2 = new Conta(200, p2);
        b.cadastro(c1);
        b.cadastro(c2);
        b.deposito(100, 1000);
        b.deposito(200, 1000);
        assertEquals(1000, b.saldo(100), 0.0001);
        assertEquals(1000, b.saldo(200), 0.0001);

        Banco banco = new Banco("teste_arq_contas.dat");

        assertEquals(1000, banco.saldo(100), 0.0001);
        assertEquals(1000, banco.saldo(200), 0.0001);
    }

    @Test
    public void testeArquivoComTransferencia() {
        Banco b = new Banco("teste_arq_contas.dat");
        b.hardReset();

        Pessoa p1 = new Pessoa(1);
        Conta c1 = new Conta(100, p1);
        Pessoa p2 = new Pessoa(2);
        Conta c2 = new Conta(200, p2);
        b.cadastro(c1);
        b.cadastro(c2);

        b.deposito(100, 1000);
        b.deposito(200, 1000);

        b.transfere(100, 200, 1000);
        assertEquals(0, b.saldo(100), 0.0001);
        assertEquals(2000, b.saldo(200), 0.0001);

        Banco banco = new Banco("teste_arq_contas.dat");

        assertEquals(0, banco.saldo(100), 0.0001);
        assertEquals(2000, banco.saldo(200), 0.0001);

        banco.transfere(200, 100, 500);
        assertEquals(500, banco.saldo(100), 0.0001);
        assertEquals(1500, banco.saldo(200), 0.0001);
    }
}