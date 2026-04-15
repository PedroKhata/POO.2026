package joaoPedroOliveira.banco;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class TesteConta {

    @Test
    public void testarCreditoDebito() throws SaldoInsuficiente {
        Pessoa p = new Pessoa(1);
        Conta c1 = new ContaComum(1, p);
        c1.credito(100);
        c1.credito(10);
        assertEquals(110, c1.saldo(), 0.0001);
        c1.debito(20);
        assertEquals(90, c1.saldo(), 0.0001);
    }

    @Test
    public void testarDebitoContaComumSemSaldo() {
        Pessoa p = new Pessoa(1);
        Conta c = new ContaComum(1, p);
        c.credito(50);
        try {
            c.debito(100);
            fail("Deveria ter lançado SaldoInsuficiente");
        } catch (SaldoInsuficiente si) {
            assertEquals(50, c.saldo(), 0.0001);
        }
    }

    @Test
    public void testarDebitoContaEspecial() throws SaldoInsuficiente {
        Pessoa p = new Pessoa(1);
        ContaEspecial c = new ContaEspecial(1, p, 500);
        c.debito(300);
        assertEquals(-300, c.saldo(), 0.0001);
    }

    @Test
    public void testarDebitoContaEspecialEstourandoLimite() {
        Pessoa p = new Pessoa(1);
        ContaEspecial c = new ContaEspecial(1, p, 500);
        try {
            c.debito(600);
            fail("Deveria ter lançado SaldoInsuficiente");
        } catch (SaldoInsuficiente si) {
            assertEquals(0, c.saldo(), 0.0001); // Saldo continua 0
        }
    }

    @Test
    public void testarDebitoContaImposto() throws SaldoInsuficiente {
        Pessoa p = new Pessoa(1);
        ContaImposto c = new ContaImposto(1, p, 0.1);
        c.credito(1000);
        c.debito(100);
        assertEquals(890, c.saldo(), 0.0001);
    }
}