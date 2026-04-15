package joaoPedroOliveira.banco;

import java.io.Serial;
import java.io.Serializable;

public class ContaComum extends Conta implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public ContaComum(int n, Pessoa p) {
        super(n, p);
    }

    public void debito(double v) throws SaldoInsuficiente {
        if (v <= saldo) {
            saldo = saldo - v;
            extrato = extrato + "Débito: " + v +
                    ". Saldo: " + saldo + ".\n";
        } else {
            throw new SaldoInsuficiente(numero(), saldo());
        }
    }
}
