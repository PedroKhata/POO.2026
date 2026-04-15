package joaoPedroOliveira.banco;

import java.io.Serial;
import java.io.Serializable;

public class ContaImposto extends Conta implements Serializable {
    private double imposto;
    @Serial
    private static final long serialVersionUID = 1L;

    public ContaImposto(int n, Pessoa p, double i) {
        super(n, p);
        imposto = i;
    }

    public void debito(double v) throws SaldoInsuficiente {
        double valComImposto = v + v * imposto;
        if (valComImposto <= saldo) {
            saldo = saldo - valComImposto;
            extrato = extrato + "Débito: " + valComImposto +
                    ". Saldo: " + saldo + ".\n";
        } else {
            throw new SaldoInsuficiente(numero(), saldo());
        }
    }
}
