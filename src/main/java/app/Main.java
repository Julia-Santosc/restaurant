package app;

import pedido.Pedido;

public class Main {
    public static void main(String[] args) {
        RestauranteFacade restaurante = new RestauranteFacade();
        MenuConsole menu = new MenuConsole();

        int opcaoMenuPrincipal;

        do {
            opcaoMenuPrincipal = menu.exibirMenuPrincipal();
            switch (opcaoMenuPrincipal) {
                case 1:
                    String nomeCliente = menu.lerNomeCliente();
                    Pedido novoPedido = restaurante.criarNovoPedido(nomeCliente);
                    int opcaoLanche;

                    do {
                        opcaoLanche = menu.lerOpcaoLanche();
                        restaurante.adicionarItemAoPedido(novoPedido, opcaoLanche);
                    } while (opcaoLanche != 0);

                    menu.exibirPedido(novoPedido);
                    menu.irMenuPrincipal();
                    break;
                case 2:
                    menu.exibirPedidosDoDia(restaurante.listarPedidos().values());
                    menu.irMenuPrincipal();
                    break;
                case 3:
                    menu.exibirFechamentoRestaurante(restaurante.calcularTotalDoDia());
                    menu.irMenuPrincipal();
                    break;
                case 0:
                    menu.sairDoMenuConsole();
                    break;
                default:
                    throw new IllegalArgumentException("Opcao Invalida");
            }
        } while (opcaoMenuPrincipal != 0);
    }
}

