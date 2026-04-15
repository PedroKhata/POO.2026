package joaoPedroOliveira.banco;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class TesteArquivoDeContas {
    @Test
    public void testeContaJaCadastradaComArquivo() throws ContaInexistente, ContaJaCadastrada {
        Banco b = new Banco("teste_banco.dat");
        b.hardReset();

        Pessoa p1 = new Pessoa(1);
        Conta c1 = new ContaComum(100, p1);
        b.cadastro(c1);

        try {
            Pessoa p2 = new Pessoa(2);
            Conta c2 = new ContaComum(100, p2);
            b.cadastro(c2);
            fail("Deveria ter lançado ContaJaCadastrada");
        } catch (ContaJaCadastrada erro) {
            Banco bancoL = new Banco("teste_banco.dat");
            assertEquals(0, bancoL.saldo(100), 0.0001);
        }
    }

    @Test
    public void testeContaInexistenteComArquivo() throws ContaJaCadastrada {
        Banco b = new Banco("teste_banco.dat");
        b.hardReset();

        Pessoa p1 = new Pessoa(1);
        Conta c1 = new ContaComum(100, p1);
        b.cadastro(c1);

        try {
            b.saldo(999);
            fail("Deveria ter lançado ContaInexistente");
        } catch (ContaInexistente ci) {

        }
    }

    @Test
    public void testeSaldoInsuficiente() throws ContaJaCadastrada, ContaInexistente {
        Banco b = new Banco("teste_banco.dat");
        b.hardReset();

        Pessoa p1 = new Pessoa(1);
        Conta c1 = new ContaComum(100, p1);
        b.cadastro(c1);
        b.deposito(100, 50);

        try {
            b.saque(100, 100);
            fail("Deveria ter lançado SaldoInsuficiente");
        } catch (SaldoInsuficiente si) {

            Banco banco = new Banco("teste_banco.dat");
            assertEquals(50, banco.saldo(100), 0.0001);
        }
    }

    @Test
    public void testeNaoEhPoupancaComArquivo() throws ContaJaCadastrada, ContaInexistente {
        Banco b = new Banco("teste_banco.dat");
        b.hardReset();

        Pessoa p1 = new Pessoa(1);
        Conta c1 = new ContaComum(100, p1);
        b.cadastro(c1);
        b.deposito(100, 500);

        try {
            b.rendeJuros(100, 0.1);
            fail("Deveria ter lançado NaoEhPoupanca");
        } catch (NaoEhPoupanca np) {
            Banco banco = new Banco("teste_banco.dat");
            assertEquals(500, banco.saldo(100), 0.0001);
        }
    }

    @Test
    public void testeContaEspecialComArquivo() throws SaldoInsuficiente, ContaJaCadastrada, ContaInexistente {
        Banco b = new Banco("teste_banco.dat");
        b.hardReset();

        Pessoa p1 = new Pessoa(1);
        Conta c1 = new ContaEspecial(100, p1, 1000);
        b.cadastro(c1);
        b.deposito(100, 200);

        b.saque(100, 500);
        assertEquals(-300, b.saldo(100), 0.0001);

        Banco bancoLeitura1 = new Banco("teste_banco.dat");
        assertEquals(-300, bancoLeitura1.saldo(100), 0.0001);

        try {
            b.saque(100, 800);
            fail("Deveria ter lançado SaldoInsuficiente");
        } catch (SaldoInsuficiente si) {
            Banco bancoL2 = new Banco("teste_banco.dat");
            assertEquals(-300, bancoL2.saldo(100), 0.0001);
        }
    }

    @Test
    public void testeContaImpostoComArquivo() throws SaldoInsuficiente, ContaJaCadastrada, ContaInexistente {
        Banco b = new Banco("teste_banco.dat");
        b.hardReset();

        Pessoa p1 = new Pessoa(1);
        Conta c1 = new ContaImposto(100, p1, 0.1);
        b.cadastro(c1);
        b.deposito(100, 1000);

        b.saque(100, 100);
        assertEquals(890, b.saldo(100), 0.0001);

        Banco bancoL1 = new Banco("teste_banco.dat");
        assertEquals(890, bancoL1.saldo(100), 0.0001);

        try {
            b.saque(100, 850);
            fail("Deveria ter lançado SaldoInsuficiente");
        } catch (SaldoInsuficiente si) {
            Banco bancoL2 = new Banco("teste_banco.dat");
            assertEquals(890, bancoL2.saldo(100), 0.0001);
        }
    }
}