package app;

import cardapio.Lanche;

public class Main {
    public static void main(String[] args) {
        Lanche xburguer = new Lanche("X-Burguer", 12.0);

        System.out.println(xburguer.getPreco());
    }
}
