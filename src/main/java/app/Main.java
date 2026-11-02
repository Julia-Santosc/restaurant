package app;

import pedido.Pedido;

public class Main {
    public static void main(String[] args) {
        RestauranteFacade restaurante = new RestauranteFacade();

        Pedido comandaDaJulia = restaurante.criarNovoPedido("Julia");
        restaurante.adicionarItemAoPedido(comandaDaJulia, 4);
        restaurante.adicionarItemAoPedido(comandaDaJulia, 1);
        restaurante.adicionarItemAoPedido(comandaDaJulia, 3);

        Pedido comandaJoao = restaurante.criarNovoPedido("Joao");
        restaurante.adicionarItemAoPedido(comandaJoao, 3);

        System.out.println(restaurante.calcularTotalDoDia());
    }
}
