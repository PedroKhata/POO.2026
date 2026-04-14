package joaoPedroOliveira.banco;

public class ContaImposto extends Conta {
    private double imposto;

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
