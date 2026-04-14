package joaoPedroOliveira.estoque;

import java.util.ArrayList;
import java.util.Date;

public class Registro {
    private Date data;
    private String tipo;
    private int quantidade;
    private double valor;

    public Registro(Date data, String tipo, int quantidade, double valor) {
        this.data = data;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public String formatarTexto() {
        int dia = data.getDate();
        int mes = data.getMonth() + 1;
        int ano = data.getYear() + 1900;
        String d = dia + "/" + mes + "/" + ano;
        return "Data: " + d + ". " + tipo +
                ". Valor: " + valor + ". Quantidade: "
                + quantidade + ".\n";
    }

    public Date getData() {
        return data;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValor() {
        return valor;
    }

    public String getTipo() {
        return tipo;
    }
}
