package joaoPedroOliveira.banco;

import java.io.Serial;
import java.io.Serializable;

public abstract class Conta implements Serializable {
	@Serial
    private static final long serialVersionUID = 1L;

	private int numero;
	protected double saldo;
	protected String extrato = "";
	private Pessoa dono;

	public Pessoa getDono() {
		return dono;
	}

	public void setDono(Pessoa dono) {
		this.dono = dono;
	}

	public Conta(int n, Pessoa p) {
		numero = n;	
		dono = p;
		p.setConta(this);
	}
	
	public void credito(double v) {
		saldo = saldo + v;
		extrato = extrato + "Crédito: " + v + ". Saldo: " + saldo + ".\n";
	}

	public abstract void debito(double v) throws SaldoInsuficiente;
	
	public String extrato() {
		return extrato;
	}
	
	public int numero() {
		return numero;
	}
	
	public double saldo() {
		return saldo;
	}
}
