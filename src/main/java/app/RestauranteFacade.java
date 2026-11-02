package app;

import cardapio.ItemCardapio;
import cardapio.Lanche;
import cardapio.LancheFactory;
import pedido.GerenciadorPedidos;
import pedido.Pedido;

import java.util.HashMap;

public class RestauranteFacade {
    private GerenciadorPedidos gerenciadorPedidos;

    public RestauranteFacade(){
        this.gerenciadorPedidos = new GerenciadorPedidos();
    }

    public Pedido criarNovoPedido(String nomeCliente){
        Pedido novoPedido = gerenciadorPedidos.criarPedido(nomeCliente);
        return novoPedido;
    }

    public void adicionarItemAoPedido(Pedido pedido, int numeroDoLanche) {
        if (numeroDoLanche == 0) {
            return;
        };

        ItemCardapio lanche = switch (numeroDoLanche) {
            case 1 -> LancheFactory.xburguer();
            case 2 -> LancheFactory.xsalada();
            case 3 -> LancheFactory.xbacon();
            default -> throw new IllegalArgumentException("Item selecionado nao existe");
        };

        pedido.adicionarItem(lanche);
    }

    public HashMap<Integer, Pedido> listarPedidos() {
        return gerenciadorPedidos.getPedidos();
    }

    public double calcularTotalDoDia() {
        return gerenciadorPedidos.calcularTotalDoDia();
    }
}
