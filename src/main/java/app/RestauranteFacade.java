package app;

import cardapio.ItemCardapio;
import cardapio.Lanche;
import cardapio.LancheFactory;
import pedido.GerenciadorPedidos;
import pedido.Pedido;

import java.util.Collection;
import java.util.HashSet;

public class RestauranteFacade {
    private final GerenciadorPedidos gerenciadorPedidos;

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

    public double calcularTotalDoDia() {
        return gerenciadorPedidos.calcularTotalDoDia();
    }

    public double calcularTotalGorjetas() {
        return gerenciadorPedidos.calcularTotalGorjetas();
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
    }

    public HashSet<String> pegarCombinacoesDoDia() {
        return gerenciadorPedidos.pegarCombinacoesDoDia();
    }
}
