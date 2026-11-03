package financeiro;

import java.util.HashSet;

public class Caixa {
    private static final Caixa INSTANCIA = new Caixa();

    private final HashSet<Transacao> transacoes = new HashSet<>();

    private Caixa() {
    }

    public static Caixa getInstancia() {
        return INSTANCIA;
    }

    public void registrarTransacao(Transacao transacao) {
        transacoes.remove(transacao);
        transacoes.add(transacao);
    }

    public void removerTransacao(int idPedido) {
        transacoes.remove(new Transacao(idPedido, 0, 0));
    }

    public double calcularTotalVendido() {
        double total = 0;
        for (Transacao transacao : transacoes) {
            total += transacao.getValor();
        }

        return total;
    }

    public double calcularTotalGorjetas() {
        double total = 0;
        for (Transacao transacao : transacoes) {
            total += transacao.getGorjeta();
        }

        return total;
    }
}
