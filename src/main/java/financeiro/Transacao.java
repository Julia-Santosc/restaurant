package financeiro;

import java.util.Objects;

public class Transacao {
    private final int idPedido;
    private final double valor;
    private final double gorjeta;

    public Transacao(int idPedido, double valor, double gorjeta) {
        this.idPedido = idPedido;
        this.valor = valor;
        this.gorjeta = gorjeta;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public double getValor() {
        return valor;
    }

    public double getGorjeta() {
        return gorjeta;
    }

    @Override
    public boolean equals(Object outro) {
        if (this == outro) return true;
        if (!(outro instanceof Transacao)) return false;
        Transacao transacao = (Transacao) outro;
        return idPedido == transacao.idPedido;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPedido);
    }
}
