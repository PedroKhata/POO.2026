package joaoPedroOliveira.banco;

public class UsoDeConta {

    public static void main(String[] args) throws SaldoInsuficiente {
        Pessoa p1 = new Pessoa(100);
        p1.setNome("Pedro");
        Pessoa p2 = new Pessoa(200);
        p2.setNome("Raimundo");

        Conta c = new ContaComum(1, p1);
        Conta c1 = new ContaComum(1, p1);

        c.credito(100);
        c.credito(10);
        c.debito(10);
        c.debito(1);
        System.out.println(p1.getConta().extrato());
        System.out.println(c1.extrato());
    }
}
