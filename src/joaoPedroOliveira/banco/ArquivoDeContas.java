package joaoPedroOliveira.banco;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ArquivoDeContas implements RepositorioDeArquivos {
    private String nomeArquivo;

    // Construtor padrão
    public ArquivoDeContas() {
        this.nomeArquivo = "banco_dados.dat";
    }

    // Construtor para testes
    public ArquivoDeContas(String nomeArquivoTeste) {
        this.nomeArquivo = nomeArquivoTeste;
    }

    @SuppressWarnings("unchecked")
    private ArrayList<Conta> carregarContas() {
        File arquivo = new File(nomeArquivo);
        if (!arquivo.exists() || arquivo.length() == 0) {
            return new ArrayList<Conta>();
        }
        try (FileInputStream arq = new FileInputStream(arquivo);
             ObjectInputStream obj = new ObjectInputStream(arq)) {
            return (ArrayList<Conta>) obj.readObject();

        } catch (Exception erro) {
            System.out.println("Erro ao carregar o arquivo!\n" + erro.getMessage());
            return new ArrayList<Conta>();
        }
    }

    private void salvarContas(ArrayList<Conta> contas) {
        try (FileOutputStream arq = new FileOutputStream(nomeArquivo);
             ObjectOutputStream obj = new ObjectOutputStream(arq)) {
            obj.writeObject(contas);

        } catch (IOException erro) {
            System.out.println("Erro ao salvar no arquivo!\n" + erro.getMessage());
        }
    }

    public void cadastrar(Conta c) throws ContaJaCadastrada {
        try {
            pesquisar(c.numero());
            throw new ContaJaCadastrada(c.numero());
        } catch (ContaInexistente i) {
            ArrayList<Conta> contas = carregarContas();
            contas.add(c);
            salvarContas(contas);
        }
    }

    public Conta pesquisar(int n) throws ContaInexistente {
        ArrayList<Conta> contas = carregarContas();
        Conta c;
        for (int i = 0; i < contas.size(); i++) {
            c = contas.get(i);
            if (c.numero() == n) {
                return c;
            }
        }
        throw new ContaInexistente(n);
    }

    public void atualizar(Conta c) {
        ArrayList<Conta> contas = carregarContas();

        for (int i = 0; i < contas.size(); i++) {
            if (contas.get(i).numero() == c.numero()) {
                contas.set(i, c);
                break;
            }
        }
        salvarContas(contas);
    }
    // Apenas para testes
    public void deletarDados() {
        File arq = new File(nomeArquivo);
        if (arq.exists()) {
            arq.delete();
        }
    }
}