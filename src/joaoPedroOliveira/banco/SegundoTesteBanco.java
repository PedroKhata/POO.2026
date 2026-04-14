package joaoPedroOliveira.banco;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class SegundoTesteBanco {

    @Test
    public void testeCadastroContaComum() {
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
        assertEquals(-9999999, b.saldo(101), 0.0001);
    }

    @Test
    public void testeSaqueDeposito() throws SaldoInsuficiente {
        Pessoa p1 = new Pessoa(1);
        Conta c1 = new ContaComum(100, p1);
        Banco b = new Banco();
        b.cadastro(c1);
        b.deposito(100, 99);
        assertEquals(99, b.saldo(100), 0.0001);
        b.saque(100, 9);
        assertEquals(90, b.saldo(100), 0.0001);
        b.deposito(101, 99);
        assertEquals(-9999999, b.saldo(101), 0.0001);
        b.saque(101, 99);
        assertEquals(-9999999, b.saldo(101), 0.0001);
    }

    @Test
    public void testeSaqueDepositoPoupanca() throws SaldoInsuficiente {
        Pessoa p1 = new Pessoa(1);
        Conta c1 = new Poupanca(100, p1);
        Banco b = new Banco();
        b.cadastro(c1);
        b.deposito(100, 99);
        assertEquals(99, b.saldo(100), 0.0001);
        b.saque(100, 9);
        assertEquals(90, b.saldo(100), 0.0001);
        b.deposito(101, 99);
        assertEquals(-9999999, b.saldo(101), 0.0001);
        b.saque(101, 99);
        assertEquals(-9999999, b.saldo(101), 0.0001);
    }

    @Test
    public void testeJurosPoupanca() {
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
    public void testeJurosContaComum() {
        Pessoa p1 = new Pessoa(1);
        Conta c1 = new ContaComum(100, p1);
        Banco b = new Banco();
        b.cadastro(c1);
        b.deposito(100, 99);
        assertEquals(99, b.saldo(100), 0.0001);
        b.rendeJuros(100, 0.1);
        assertEquals(99, b.saldo(100), 0.0001);
    }

    @Test
    public void testeSaqueAcimaDoSaldo() throws SaldoInsuficiente {
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
        } catch (SaldoInsuficiente e) {
            assertEquals(0, b.saldo(100), 0.0001);
        }
    }

    @Test
    public void testeSaqueEmContaEspecial() throws SaldoInsuficiente {
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
        } catch (SaldoInsuficiente e) {
            assertEquals(-1000, b.saldo(100), 0.0001);
        }
    }

    @Test
    public void testeContaImposto() throws SaldoInsuficiente {
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
        } catch (SaldoInsuficiente e) {
            assertEquals(9, b.saldo(100), 0.0001);
        }
    }
}