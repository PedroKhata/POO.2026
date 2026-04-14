package joaoPedroOliveira.banco;

public class Banco {
    private ArquivoDeContas contas;

    public Banco() {
        this.contas = new ArquivoDeContas();
    }

    public Banco(String arquivoDeTeste) {
        this.contas = new ArquivoDeContas(arquivoDeTeste);
    }

    public void cadastro(Conta c) {
        contas.cadastrar(c);
    }

    public void saque(int n, double val) {
        Conta c = contas.pesquisar(n);
        if (c != null) {
            c.debito(val);
            contas.atualizar(c);
        }
    }

    public void deposito(int n, double val) {
        Conta c = contas.pesquisar(n);
        if (c != null) {
            c.credito(val);
            contas.atualizar(c);
        }
    }

    public double saldo(int n) {
        Conta c = contas.pesquisar(n);
        if (c != null) {
            return c.saldo();
        }
        return -9999999;
    }

    public String extrato(int n) {
        Conta c = contas.pesquisar(n);
        if (c != null) {
            return c.extrato();
        }
        return "";
    }

    public void transfere(int de, int para, double val) {
        if (de == para || val <= 0) {
            return;
        }
        Conta origem = contas.pesquisar(de);
        Conta destino = contas.pesquisar(para);

        if (origem != null && destino != null) {
            if (origem.saldo() >= val) {
                origem.debito(val);
                destino.credito(val);
                contas.atualizar(destino);
                contas.atualizar(origem);
            }
        }
    }
    // Apenas para testes
    public void hardReset() {
        contas.deletarDados();
    }
}
