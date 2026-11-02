package app;

import cardapio.ComBaconExtra;
import cardapio.ComCheddar;
import cardapio.ItemCardapio;
import pedido.Pedido;

public class Main {
    private static final RestauranteFacade restaurante = new RestauranteFacade();
    private static final MenuConsole menu = new MenuConsole();

    private static ItemCardapio incluirAdicionais(ItemCardapio lanche) {
        int adicionalEscolhido;
        ItemCardapio lanchePosAdicionais;

        do {
            adicionalEscolhido = menu.lerAdicionaisLanche();

            lanchePosAdicionais = switch (adicionalEscolhido) {
                case 0 -> lanche;
                case 1 -> new ComCheddar(lanche);
                case 2 -> new ComBaconExtra(lanche);
                default -> throw new IllegalArgumentException("Adicional nao existe");
            };
        } while (adicionalEscolhido != 0);

        return lanchePosAdicionais;
    }

    private static void lerTodosItens(Pedido novoPedido) {
        int opcaoLanche;

        do {
            opcaoLanche = menu.lerOpcaoLanche();
            ItemCardapio lanche = restaurante.criarLanche(opcaoLanche);

            if (opcaoLanche != 0) {
                lanche = incluirAdicionais(lanche);
            }

            restaurante.adicionarItemAoPedido(novoPedido, lanche);
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
                    throw new IllegalArgumentException("Opção inválida.");
            }
        } while (opcaoMenuPrincipal != 0);
    }
}

