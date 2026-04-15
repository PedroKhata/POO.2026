package joaoPedroOliveira.banco;

import java.io.Serial;
import java.io.Serializable;

public class Poupanca extends ContaComum implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public void juros(double tx) {
        credito(saldo() * tx);
    }
    public Poupanca(int n, Pessoa p) {
        super(n, p);
    }
}
