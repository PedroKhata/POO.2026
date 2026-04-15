package joaoPedroOliveira.banco;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class TerceiroTesteBanco {

    @Test
    public void testeCadastroContaComum() throws ContaJaCadastrada, ContaInexistente {
        Pessoa p1 = new Pessoa(1);
        Conta c1 = new ContaEspecial(100, p1, 1000);
        Pessoa p2 = new Pessoa(2);
        Conta c2 = new ContaComum(200, p2);
        Pessoa p3 = new Pessoa(3);
        Conta c3 = new Poupanca(300, p3);
        Banco b = new Banco();

        b.cadastro(c3);
        b.cadastro(c1);
        b.cadastro(c2);

        assertEquals(0, b.saldo(100), 0.0001);
        try {
            b.saldo(101);
            fail("Deveria ter lançado ContaInexistente ao consultar saldo da conta 101!");
        } catch (ContaInexistente ci) {

        }
    }

    @Test
    public void testeSaqueDeposito() throws SaldoInsuficiente, ContaJaCadastrada, ContaInexistente {
        Pessoa p1 = new Pessoa(1);
        Conta c1 = new ContaComum(100, p1);
        Banco b = new Banco();
        b.cadastro(c1);

        b.deposito(100, 99);
        assertEquals(99, b.saldo(100), 0.0001);

        b.saque(100, 9);
        assertEquals(90, b.saldo(100), 0.0001);

        try {
            b.deposito(101, 99);
            fail("Deveria ter lançado ContaInexistente ao depositar na conta 101!");
        } catch (ContaInexistente ci) {

        }

        try {
            b.saque(101, 99);
            fail("Deveria ter lançado ContaInexistente ao sacar da conta 101!");
        } catch (ContaInexistente ci) {

        }
    }

    @Test
    public void testeSaqueDepositoPoupanca() throws SaldoInsuficiente, ContaJaCadastrada, ContaInexistente {
        Pessoa p1 = new Pessoa(1);
        Conta c1 = new Poupanca(100, p1);
        Banco b = new Banco();
        b.cadastro(c1);

        b.deposito(100, 99);
        assertEquals(99, b.saldo(100), 0.0001);

        b.saque(100, 9);
        assertEquals(90, b.saldo(100), 0.0001);


        try {
            b.deposito(101, 99);
            fail("Deveria ter lançado ContaInexistente");
        } catch (ContaInexistente ci) {

        }

        try {
            b.saque(101, 99);
            fail("Deveria ter lançado ContaInexistente");
        } catch (ContaInexistente i) {

        }
    }

    @Test
    public void testeJurosPoupanca() throws ContaJaCadastrada, ContaInexistente, NaoEhPoupanca {
        Pessoa p1 = new Pessoa(1);
        Conta c1 = new Poupanca(100, p1);
        Banco b = new Banco();
        b.cadastro(c1);

        b.deposito(100, 99);
        assertEquals(99, b.saldo(100), 0.0001);

        b.rendeJuros(100, 0.1);
        assertEquals(108.90, b.saldo(100), 0.0001);
    }

    @Test
    public void testeJurosContaComum() throws ContaJaCadastrada, ContaInexistente {
        Pessoa p1 = new Pessoa(1);
        Conta c1 = new ContaComum(100, p1);
        Banco b = new Banco();
        b.cadastro(c1);

        b.deposito(100, 99);
        assertEquals(99, b.saldo(100), 0.0001);

        try {
            b.rendeJuros(100, 0.1);
            fail("Deveria ter lançado NaoEhPoupanca");
        } catch (NaoEhPoupanca np) {
            assertEquals(99, b.saldo(100), 0.0001);
        }
    }

    @Test
    public void testeSaqueAcimaDoSaldo() throws SaldoInsuficiente, ContaJaCadastrada, ContaInexistente {
        Pessoa p1 = new Pessoa(1);
        Conta c1 = new ContaComum(100, p1);
        Banco b = new Banco();
        b.cadastro(c1);

        b.deposito(100, 99);
        assertEquals(99, b.saldo(100), 0.0001);

        b.saque(100, 99);
        assertEquals(0, b.saldo(100), 0.0001);

        try {
            b.saque(100, 1);
            fail("Não deveria ter sacado acima do saldo!");
        } catch (SaldoInsuficiente si) {
            assertEquals(0, b.saldo(100), 0.0001);
        }
    }

    @Test
    public void testeSaqueEmContaEspecial() throws SaldoInsuficiente, ContaJaCadastrada, ContaInexistente {
        Pessoa p1 = new Pessoa(1);
        Conta c1 = new ContaEspecial(100, p1, 1000);
        Banco b = new Banco();
        b.cadastro(c1);

        b.deposito(100, 99);
        assertEquals(99, b.saldo(100), 0.0001);

        b.saque(100, 99);
        assertEquals(0, b.saldo(100), 0.0001);

        b.saque(100, 1000);
        assertEquals(-1000, b.saldo(100), 0.0001);

        try {
            b.saque(100, 1);
            fail("Não deveria ter sacado acima do limite!");
        } catch (SaldoInsuficiente si) {
            assertEquals(-1000, b.saldo(100), 0.0001);
        }
    }

    @Test
    public void testeContaImposto() throws SaldoInsuficiente, ContaJaCadastrada, ContaInexistente {
        Pessoa p1 = new Pessoa(1);
        Conta c1 = new ContaImposto(100, p1, 0.01);
        Banco b = new Banco();
        b.cadastro(c1);

        b.deposito(100, 110);
        assertEquals(110, b.saldo(100), 0.0001);

        b.saque(100, 100);
        assertEquals(9, b.saldo(100), 0.0001);

        try {
            b.saque(100, 9);
            fail("Não deveria ter sacado acima do saldo sem imposto!");
        } catch (SaldoInsuficiente si) {
            assertEquals(9, b.saldo(100), 0.0001);
        }
    }
}