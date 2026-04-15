package joaoPedroOliveira.banco;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class TesteConta {
    @Test
    public void testarCredito() throws SaldoInsuficiente {
        Pessoa p = new Pessoa(1);
        Conta c1 = new ContaComum(1, p);
        c1.credito(100);
        c1.credito(10);
        assertEquals(110, c1.saldo());
        c1.debito(20);
        assertEquals(90, c1.saldo());
    }
}
