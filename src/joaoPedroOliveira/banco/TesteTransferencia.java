package joaoPedroOliveira.banco;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class TesteTransferencia {

	@Test
	public void testeTransferenciaComSaldoInsuficiente() throws ContaJaCadastrada, ContaInexistente {
		Banco b = new Banco("teste_transf.dat");
		b.hardReset();

		Pessoa p1 = new Pessoa(1);
		Conta c1 = new ContaComum(100, p1);
		Pessoa p2 = new Pessoa(2);
		Conta c2 = new ContaComum(200, p2);

		b.cadastro(c1);
		b.cadastro(c2);
		b.deposito(100, 50);

		try {
			b.transfere(100, 200, 1000);
			fail("Deveria ter lançado SaldoInsuficiente");
		} catch (SaldoInsuficiente si) {
			Banco bancoLeitura = new Banco("teste_transf.dat");
			assertEquals(50, bancoLeitura.saldo(100), 0.0001);
			assertEquals(0, bancoLeitura.saldo(200), 0.0001);
		}
	}

	@Test
	public void testeTransferenciaComOrigemInexistente() throws ContaJaCadastrada, SaldoInsuficiente, ContaInexistente {
		Banco b = new Banco("teste_transf.dat");
		b.hardReset();

		Pessoa p1 = new Pessoa(1);
		Conta destino = new ContaComum(200, p1);
		b.cadastro(destino);

		try {
			b.transfere(999, 200, 50);
			fail("Deveria ter lançado ContaInexistente");
		} catch (ContaInexistente ci) {
			assertEquals(0, b.saldo(200), 0.0001);
		}
	}

	@Test
	public void testeTransferenciaComDestinoInexistente() throws ContaJaCadastrada, SaldoInsuficiente, ContaInexistente {
		Banco b = new Banco("teste_transf.dat");
		b.hardReset();

		Pessoa p1 = new Pessoa(1);
		Conta origem = new ContaComum(100, p1);
		b.cadastro(origem);
		b.deposito(100, 500);

		try {
			b.transfere(100, 999, 200);
			fail("Deveria ter lançado ContaInexistente");
		} catch (ContaInexistente ci) {
			Banco bancoLeitura = new Banco("teste_transf.dat");
			assertEquals(500, bancoLeitura.saldo(100), 0.0001);
		}
	}

	@Test
	public void testeTransferenciaComContaImposto() throws ContaJaCadastrada, ContaInexistente, SaldoInsuficiente {
		Banco b = new Banco("teste_transf.dat");
		b.hardReset();

		Pessoa p1 = new Pessoa(1);
		Conta origemImposto = new ContaImposto(100, p1, 0.1);
		Pessoa p2 = new Pessoa(2);
		Conta destinoComum = new ContaComum(200, p2);

		b.cadastro(origemImposto);
		b.cadastro(destinoComum);
		b.deposito(100, 1000);

		b.transfere(100, 200, 100);

		assertEquals(890, b.saldo(100), 0.0001);
		assertEquals(100, b.saldo(200), 0.0001);

		Banco bancoL = new Banco("teste_transf.dat");
		assertEquals(890, bancoL.saldo(100), 0.0001);
		assertEquals(100, bancoL.saldo(200), 0.0001);
	}

	@Test
	public void testeTransferenciaComContaEspecial() throws ContaJaCadastrada, ContaInexistente, SaldoInsuficiente {
		Banco b = new Banco("teste_transf.dat");
		b.hardReset();

		Pessoa p1 = new Pessoa(1);
		Conta origemEspecial = new ContaEspecial(100, p1, 500);
		Pessoa p2 = new Pessoa(2);
		Conta destinoComum = new ContaComum(200, p2);

		b.cadastro(origemEspecial);
		b.cadastro(destinoComum);

		b.transfere(100, 200, 300);

		assertEquals(-300, b.saldo(100), 0.0001);
		assertEquals(300, b.saldo(200), 0.0001);

		Banco bancoL = new Banco("teste_transf.dat");
		assertEquals(-300, bancoL.saldo(100), 0.0001);
		assertEquals(300, bancoL.saldo(200), 0.0001);
	}
}
