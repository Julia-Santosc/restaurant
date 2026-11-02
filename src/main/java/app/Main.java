package app;

import cardapio.Lanche;
import pedido.Pedido;

public class Main {
    public static void main(String[] args) {
        Lanche xburguer = new Lanche("X-Burguer", 16.0);
        Lanche xBacon = new Lanche("X-Bacon", 22.0);

        Pedido pedido = new Pedido(1, "Joao");
        pedido.adicionarItem(xburguer);
        pedido.adicionarItem(xBacon);

        System.out.println(pedido.calcularTotal());
    }
}
