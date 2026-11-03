package app;

import cardapio.ItemCardapio;
import cardapio.Lanche;
import cardapio.LancheFactory;
import financeiro.Caixa;
import financeiro.Transacao;
import pedido.GerenciadorPedidos;
import pedido.Pedido;

import java.util.Collection;
import java.util.HashSet;

public class RestauranteFacade {
    private final GerenciadorPedidos gerenciadorPedidos;
    private final Caixa caixa = Caixa.getInstancia();

    public RestauranteFacade() {
        this.gerenciadorPedidos = new GerenciadorPedidos();
    }

    public Pedido criarNovoPedido(String nomeCliente) {
        return gerenciadorPedidos.criarPedido(nomeCliente);
    }

    public Lanche criarLanche(int numeroDoLanche) {
        return switch (numeroDoLanche) {
            case 1 -> LancheFactory.xBurguer();
            case 2 -> LancheFactory.xSalada();
            case 3 -> LancheFactory.xBacon();
            default -> throw new IllegalArgumentException("Item selecionado não existe.");
        };
    }

    public void adicionarItemAoPedido(Pedido pedido, ItemCardapio item) {
        pedido.adicionarItem(item);
    }

    public Collection<Pedido> listarPedidos() {
        return gerenciadorPedidos.getPedidos().values();
    }

    public void fecharPedido(Pedido pedido) {
        if (gerenciadorPedidos.buscarPedido(pedido.getIdPedido()) == null) {
            return;
        }

        Transacao transacao = new Transacao(pedido.getIdPedido(), pedido.calcularTotal(), pedido.getGorjeta());
        caixa.registrarTransacao(transacao);
    }

    public double calcularTotalDoDia() {
        return caixa.calcularTotalVendido();
    }

    public double calcularTotalGorjetas() {
        return caixa.calcularTotalGorjetas();
    }

    public void registrarGorjeta(Pedido pedido, double gorjeta) {
        pedido.setGorjeta(gorjeta);
    }

    public Pedido buscarPedido(int idPedido) {
        return gerenciadorPedidos.buscarPedido(idPedido);
    }

    public void removerItemDoPedido(Pedido pedido, int indiceDoItemParaRemover) {
        gerenciadorPedidos.removerItemDoPedido(pedido, indiceDoItemParaRemover);
    }

    public void cancelarPedido(int idPedido) {
        gerenciadorPedidos.cancelarPedido(idPedido);
        caixa.removerTransacao(idPedido);
    }

    public HashSet<String> pegarCombinacoesDoDia() {
        return gerenciadorPedidos.pegarCombinacoesDoDia();
    }
}
