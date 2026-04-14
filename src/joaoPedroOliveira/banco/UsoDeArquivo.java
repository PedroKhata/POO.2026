package joaoPedroOliveira.banco;

public class UsoDeArquivo {
    public static void main(String[] args) {
        Banco b = new Banco();
        Pessoa p1 = new Pessoa(1);
        Conta c1 = new Conta(100, p1);
        b.cadastro(c1);
        Pessoa p2 = new Pessoa(2);
        Conta c2 = new Conta(200, p2);
        b.cadastro(c2);

        b.deposito(100, 1000);
        b.deposito(200, 1000);
        System.out.println("Extrato Conta 1: ");
        System.out.println(b.extrato(100));
        System.out.println("Extrato Conta 2: ");
        System.out.println(b.extrato(200));
    }
}
