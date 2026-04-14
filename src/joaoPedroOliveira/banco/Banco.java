package joaoPedroOliveira.banco;

public class Banco {
    private RepositorioDeContas contas = new ArrayListDeContas();

    public Banco() {
    }

    public Banco(String arquivoDeTeste) {
        if (contas instanceof ArquivoDeContas){
            this.contas = new ArquivoDeContas(arquivoDeTeste);
        }
    }

    public void cadastro(Conta c) {
        contas.cadastrar(c);
    }

    public void saque(int n, double val) throws SaldoInsuficiente {
        Conta c = contas.pesquisar(n);
        if (c != null) {
            c.debito(val);
            if (contas instanceof ArquivoDeContas) {
                ((ArquivoDeContas)contas).atualizar(c);
            }
        }
    }

    public void deposito(int n, double val) {
        Conta c = contas.pesquisar(n);
        if (c != null) {
            c.credito(val);
            if (contas instanceof ArquivoDeContas) {
                ((ArquivoDeContas) contas).atualizar(c);
            }
        }
    }

    public double saldo(int n) {
        Conta c = contas.pesquisar(n);
        if (c != null) {
            return c.saldo();
        }
        return -9999999;
    }

    public void rendeJuros(int num, double t) {
        Conta c = contas.pesquisar(num);
        if (c != null && c instanceof Poupanca) {
            ((Poupanca) c).juros(t);
        }
    }

    public String extrato(int n) {
        Conta c = contas.pesquisar(n);
        if (c != null) {
            return c.extrato();
        }
        return "";
    }

    public void transfere(int de, int para, double val) throws SaldoInsuficiente {
        if (de == para || val <= 0) {
            return;
        } else {
            Conta origem = contas.pesquisar(de);
            Conta destino = contas.pesquisar(para);
            if (origem != null && destino != null) {
                if (origem.saldo() >= val) {
                    origem.debito(val);
                    destino.credito(val);
                    if (contas instanceof ArquivoDeContas) {
                        ((ArquivoDeContas)contas).atualizar(destino);
                        ((ArquivoDeContas)contas).atualizar(origem);
                    }
                }
            }
        }
    }
    // Apenas para testes
    public void hardReset() {
        if (contas instanceof ArquivoDeContas) {
            ((ArquivoDeContas)contas).deletarDados();
        }
    }
}
