package joaoPedroOliveira.banco;

import java.io.Serial;
import java.io.Serializable;

public class Pessoa implements Serializable {
	@Serial
    private static final long serialVersionUID = 1L;

	private int cpf;
	private String nome;
	private Conta conta;
	
	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Pessoa(int oCpf) {
		cpf = oCpf;
	}
	
	public int getCpf() {
		return cpf;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
}
