package joaoPedroOliveira.banco;

import java.io.Serial;
import java.io.Serializable;

public class Conta implements Serializable {
	@Serial
    private static final long serialVersionUID = 1L;

	private int numero;
	private double saldo;
	private String extrato = "";
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
	
	public void debito(double v) {
		if (v <= saldo) {
			saldo = saldo - v;
			extrato = extrato + "Débito: " + v + ". Saldo: " + saldo + ".\n";
		}
	}
	
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
