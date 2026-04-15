package joaoPedroOliveira.banco;

import java.io.Serial;
import java.io.Serializable;

public class ContaEspecial extends Conta implements Serializable {
    private double limite;
    @Serial
    private static final long serialVersionUID = 1L;

    public ContaEspecial(int n, Pessoa p, double l) {
        super(n, p);
        limite = l;
    }

    public void debito(double v) throws SaldoInsuficiente {
        if (v <= saldo + limite) {
            saldo = saldo - v;
            extrato = extrato + "Débito: " + v +
                    ". Saldo: " + saldo + ".\n";
        } else {
            throw new SaldoInsuficiente(numero(), saldo());
        }
    }
}
