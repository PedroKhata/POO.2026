package joaoPedroOliveira.banco;

import java.util.ArrayList;

public class ArrayListDeContas {
	private ArrayList<Conta> contas = new ArrayList<Conta>();
	
	public void cadastrar(Conta c) {
		contas.add(c);
	}
	
	public Conta pesquisar(int n) {	
		for (Conta conta : contas) {
			if (conta.numero() == n) {
				return conta;
			}
		}
		return null;
	}
}
