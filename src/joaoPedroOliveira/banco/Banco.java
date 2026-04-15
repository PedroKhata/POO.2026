package joaoPedroOliveira.banco;

public class Banco {
    private RepositorioDeContas contas = new ArrayListDeContas();

    public Banco() {
    }

    public Banco(String arquivoDeTeste) {
        this.contas = new ArquivoDeContas(arquivoDeTeste);
    }

    public void cadastro(Conta c) throws ContaJaCadastrada {
        contas.cadastrar(c);
    }

    public void saque(int n, double val) throws SaldoInsuficiente, ContaInexistente {
        Conta c = contas.pesquisar(n);
        if (c != null) {
            c.debito(val);
            if (contas instanceof ArquivoDeContas) {
                ((ArquivoDeContas)contas).atualizar(c);
            }
        } else {
            throw new ContaInexistente(n);
        }
    }

    public void deposito(int n, double val) throws ContaInexistente {
        Conta c = contas.pesquisar(n);
        if (c != null) {
            c.credito(val);
            if (contas instanceof ArquivoDeContas) {
                ((ArquivoDeContas) contas).atualizar(c);
            }
        } else {
            throw new ContaInexistente(n);
        }
    }

    public double saldo(int n) throws ContaInexistente {
        Conta c = contas.pesquisar(n);
        if (c != null) {
            return c.saldo();
        } else {
            throw new ContaInexistente(n);
        }
    }

    public void rendeJuros(int num, double t) throws NaoEhPoupanca, ContaInexistente {
        Conta c = contas.pesquisar(num);
        if (c != null) {
            if (c instanceof Poupanca) {
                ((Poupanca) c).juros(t);
            } else {
                throw new NaoEhPoupanca(num);
            }
        } else {
            throw new ContaInexistente(num);
        }
    }

    public String extrato(int n) throws ContaInexistente {
        Conta c = contas.pesquisar(n);
        if (c != null) {
            return c.extrato();
        } else {
            throw new ContaInexistente(n);
        }
    }

    public void transfere(int de, int para, double val) throws SaldoInsuficiente, ContaInexistente {
        if (de == para || val <= 0) {
           return;
        }

        Conta origem = contas.pesquisar(de);
        Conta destino = contas.pesquisar(para);

        if (origem.saldo() >= val) {
            origem.debito(val);
            destino.credito(val);

            if (contas instanceof ArquivoDeContas) {
                ((ArquivoDeContas)contas).atualizar(destino);
                ((ArquivoDeContas)contas).atualizar(origem);
            }
        } else {
            throw new SaldoInsuficiente(origem.numero(), origem.saldo());
        }
    }
    // Apenas para testes
    public void hardReset() {
        if (contas instanceof ArquivoDeContas) {
            ((ArquivoDeContas)contas).deletarDados();
        }
    }
}
