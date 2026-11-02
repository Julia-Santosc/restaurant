package app;

import cardapio.ItemCardapio;
import cardapio.LancheFactory;
import pedido.GerenciadorPedidos;
import pedido.Pedido;

import java.util.HashMap;

public class RestauranteFacade {
    private final GerenciadorPedidos gerenciadorPedidos;

    public RestauranteFacade(){
        this.gerenciadorPedidos = new GerenciadorPedidos();
    }

    public Pedido criarNovoPedido(String nomeCliente){
        return gerenciadorPedidos.criarPedido(nomeCliente);
    }

    public ItemCardapio criarLanche(int numeroDoLanche) {
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

    public HashMap<Integer, Pedido> listarPedidos() {
        return gerenciadorPedidos.getPedidos();
    }

    public double calcularTotalDoDia() {
        return gerenciadorPedidos.calcularTotalDoDia();
    }
}
