package joaoPedroOliveira.banco;

public class ContaComum extends Conta {
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
