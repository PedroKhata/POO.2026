package joaoPedroOliveira.banco;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TesteBanco {
	@Test
	public void testeCadastroContaComum() {
		Pessoa p1 = new Pessoa(1);
		Conta c1 = new ContaEspecial(100, p1, 1000);
		Pessoa p2 = new Pessoa(2);
		Conta c2 = new ContaComum(200, p2);
		Pessoa p3 = new Pessoa(3);
		Conta c3 = new Poupanca(300, p3);
		Banco b = new Banco();
		b.cadastro(c3);
		b.cadastro(c1);
		b.cadastro(c2);
		assertEquals(0, b.saldo(100), 0.0001);
		assertEquals(-9999999, b.saldo(101), 0.0001);
	}
	
	@Test
	public void testeSaqueDeposito() throws SaldoInsuficiente {
		Pessoa p1 = new Pessoa(1);
		Conta c1 = new ContaComum(100, p1);
		Banco b = new Banco();
		b.cadastro(c1);
		b.deposito(100, 99);
		assertEquals(99, b.saldo(100), 0.0001);
		b.saque(100, 9);
		assertEquals(90, b.saldo(100), 0.0001);
		b.deposito(101, 99);
		assertEquals(-9999999, b.saldo(101), 0.0001);
		b.saque(101, 99);
		assertEquals(-9999999, b.saldo(101), 0.0001);
	}
	
	@Test
	public void testeSaqueAcimaDoSaldoArquivo() {
		Banco b = new Banco("teste_arq_contas.dat");
		b.hardReset();
		Pessoa p1 = new Pessoa(1);
		Conta c1 = new ContaComum(100, p1);

		b.cadastro(c1);	
		b.deposito(100, 99);
		assertEquals(99, b.saldo(100), 0.0001);
		b.saque(100, 99);
		assertEquals(0, b.saldo(100), 0.0001);
		b.saque(100, 1);
		assertEquals(0, b.saldo(100), 0.0001);
	}

	@Test
	public void testeTransferenciaArquivo() {
		Banco b = new Banco("teste_arq_contas.dat");
		b.hardReset();
		Pessoa p1 = new Pessoa(1);
		Pessoa p2 = new Pessoa(2);
		Conta c1 = new ContaComum(100, p1);
		Conta c2 = new ContaComum(200, p2);

		b.cadastro(c1);
		b.cadastro(c2);
		b.deposito(100, 500);
		assertEquals(500, b.saldo(100), 0.0001);
		b.transfere(100, 200, 500);
		assertEquals(0, b.saldo(100), 0.0001);
		assertEquals(500, b.saldo(200), 0.0001);
		b.transfere(200, 100, 250);
		assertEquals(250, b.saldo(100), 0.0001);
		assertEquals(250, b.saldo(200), 0.0001);
		b.transfere(111, 222, 250);
		assertEquals(-9999999, b.saldo(222), 0.0001);
		assertEquals(-9999999, b.saldo(111), 0.0001);
		b.transfere(100, 200, 500);
		assertEquals(250, b.saldo(100), 0.0001);
		assertEquals(250, b.saldo(200), 0.0001);
	}
}
