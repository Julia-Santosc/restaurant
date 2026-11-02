package app;

import pedido.Pedido;

public class Main {
    private static RestauranteFacade restaurante = new RestauranteFacade();
    private static MenuConsole menu = new MenuConsole();

    private static void lerTodosItens(Pedido novoPedido) {
        int opcaoLanche;

        do {
            opcaoLanche = menu.lerOpcaoLanche();
            restaurante.adicionarItemAoPedido(novoPedido, opcaoLanche);
        } while (opcaoLanche != 0);
    }

    public static void main(String[] args) {
        int opcaoMenuPrincipal;

        do {
            opcaoMenuPrincipal = menu.exibirMenuPrincipal();
            switch (opcaoMenuPrincipal) {
                case 1:
                    String nomeCliente = menu.lerNomeCliente();
                    Pedido novoPedido = restaurante.criarNovoPedido(nomeCliente);

                    lerTodosItens(novoPedido);
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

