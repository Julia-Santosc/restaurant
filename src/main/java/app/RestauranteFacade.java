package app;

import cardapio.Lanche;
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

        Lanche lanche = switch (numeroDoLanche) {
            case 1 -> new Lanche("X-Burguer", 16.00);
            case 2 -> new Lanche("X-Salada", 18.00);
            case 3 -> new Lanche("X-Bacon", 22.00);
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
