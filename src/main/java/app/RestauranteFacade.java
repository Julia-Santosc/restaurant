package app;

import cardapio.ItemCardapio;
import cardapio.Lanche;
import cardapio.LancheFactory;
import pedido.GerenciadorPedidos;
import pedido.Pedido;

import java.util.Collection;

public class RestauranteFacade {
    private final GerenciadorPedidos gerenciadorPedidos;

    public RestauranteFacade(){
        this.gerenciadorPedidos = new GerenciadorPedidos();
    }

    public Pedido criarNovoPedido(String nomeCliente){
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

    public Pedido buscarPedido(int idPedido){
        return gerenciadorPedidos.buscarPedido(idPedido);
    }

    public void removerItemDoPedido(Pedido pedido, int indiceDoItemParaRemover) {
        if (indiceDoItemParaRemover < 0 || indiceDoItemParaRemover >= pedido.getItens().size()) {
            throw new IllegalArgumentException("O item não está presente no pedido.");
        }

        pedido.getItens().remove(indiceDoItemParaRemover);
    }
}
