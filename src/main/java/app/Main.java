package app;

import cardapio.ComBaconExtra;
import cardapio.ComCheddar;
import cardapio.ItemCardapio;
import cardapio.Lanche;
import pedido.Pedido;

public class Main {
    private static final RestauranteFacade restaurante = new RestauranteFacade();
    private static final MenuConsole menu = new MenuConsole();

    private static ItemCardapio incluirAdicionais(ItemCardapio lanche) {
        int adicionalEscolhido = menu.lerAdicionaisLanche();

        while (adicionalEscolhido != 0) {
            lanche = switch (adicionalEscolhido) {
                case 1 -> new ComCheddar(lanche);
                case 2 -> new ComBaconExtra(lanche);
                default -> throw new IllegalArgumentException("Adicional nao existe");
            };

            adicionalEscolhido = menu.lerAdicionaisLanche();
        }

        return lanche;
    }

    private static Lanche retirarIngredientes(Lanche lanche) {
        int ingredienteEscolhido = menu.lerIngredientesLanche();

        while (ingredienteEscolhido != 0) {
            switch (ingredienteEscolhido) {
                case 1 -> lanche.setQueijo(false);
                case 2 -> lanche.setAlface(false);
                case 3 -> lanche.setTomate(false);
                case 4 -> lanche.setCebola(false);
                default -> throw new IllegalArgumentException("Ingrediente nao existe");
            };
            ingredienteEscolhido = menu.lerIngredientesLanche();
        }
        return lanche;
    }

    private static void lerTodosItens(Pedido novoPedido) {
        int opcaoLanche = menu.lerOpcaoLanche();

        while (opcaoLanche != 0) {
            Lanche lanche = restaurante.criarLanche(opcaoLanche);
            retirarIngredientes(lanche);
            ItemCardapio lancheFinalizado = incluirAdicionais(lanche);
            restaurante.adicionarItemAoPedido(novoPedido, lancheFinalizado);

            opcaoLanche = menu.lerOpcaoLanche();
        }
    }

    private static void registrarNovoPedido() {
        String nomeCliente = menu.lerNomeCliente();
        Pedido novoPedido = restaurante.criarNovoPedido(nomeCliente);

        lerTodosItens(novoPedido);
        menu.exibirPedido(novoPedido);
    }

    public static void main(String[] args) {
        int opcaoMenuPrincipal = menu.exibirMenuPrincipal();

        while (opcaoMenuPrincipal != 0) {
            switch (opcaoMenuPrincipal) {
                case 1 -> registrarNovoPedido();
                case 2 -> menu.exibirPedidosDoDia(restaurante.listarPedidos());
                case 3 -> menu.exibirFechamentoRestaurante(restaurante.calcularTotalDoDia());
                default -> throw new IllegalArgumentException("Opção inválida.");
            }

            menu.irParaMenuPrincipal();
            opcaoMenuPrincipal = menu.exibirMenuPrincipal();
        };

        menu.sairDoMenuConsole();
    }
}

