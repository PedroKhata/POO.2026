package joaoPedroOliveira.banco;

import java.util.ArrayList;

public class ArrayListDeContas implements RepositorioDeContas {
    private ArrayList<Conta> contas = new ArrayList<Conta>();

    public void cadastrar(Conta c) throws ContaJaCadastrada {
        try {
            pesquisar(c.numero());
            throw new ContaJaCadastrada(c.numero());
        } catch (ContaInexistente i) {
            contas.add(c);
        }
    }

    public Conta pesquisar(int n) throws ContaInexistente {
        for (Conta conta : contas) {
            if (conta.numero() == n) {
                return conta;
            }
        }
        throw new ContaInexistente(n);
    }
}
