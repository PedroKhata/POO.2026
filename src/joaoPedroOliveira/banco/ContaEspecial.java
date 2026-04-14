package joaoPedroOliveira.banco;

public class ContaEspecial extends Conta {
    private double limite;

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
