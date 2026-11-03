package app;

import cardapio.ComBaconExtra;
import cardapio.ComCheddar;
import cardapio.ItemCardapio;
import cardapio.Lanche;
import pedido.Pedido;
import util.Helpers;

import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;

public class MenuConsole {
    private final Scanner scanner = new Scanner(System.in);

    public int lerOpcaoMenuPrincipal() {
        System.out.println("=== MENU PRINCIPAL ===");
        System.out.println("1 - Registrar novo pedido");
        System.out.println("2 - Listar pedidos do dia");
        System.out.println("3 - Fechar restaurante");
        System.out.println("4 - Modificar pedido");
        System.out.println("0 - Sair");
        System.out.print("\nDigite a opção desejada: ");

        return Helpers.lerNumero(scanner);
    }

    public int lerOpcaoLanche() {
        System.out.println("\n=== CARDÁPIO ===");
        System.out.println("1 - X-Burguer (R$ 16,00)");
        System.out.println("2 - X-Salada (R$ 18,00)");
        System.out.println("3 - X-Bacon (R$ 22,00)");
        System.out.println("0 - Finalizar Pedido");
        System.out.print("Digite a opcao desejada: ");

        return Helpers.lerNumero(scanner);
    }

    public int lerIngredientesLanche() {
        System.out.println("\nIngredientes: ");
        System.out.println("1 - Queijo");
        System.out.println("2 - Alface");
        System.out.println("3 - Tomate");
        System.out.println("4 - Cebola");
        System.out.println("0 - Sair");
        System.out.print("Digite a opção desejada: ");

        return Helpers.lerNumero(scanner);
    }

    public int lerAdicionaisLanche() {
        System.out.println("\nAdicionais:");
        System.out.println("1 - Cheddar");
        System.out.println("2 - Bacon extra");
        System.out.println("0 - Nenhum / terminar");
        System.out.print("Digite o adicional desejado: ");

        return Helpers.lerNumero(scanner);
    }

    public int lerSubMenuDeModificacao() {
        System.out.println("\n=== MODIFICAR PEDIDO ===");
        System.out.println("1 - Acrescentar item");
        System.out.println("2 - Remover item");
        System.out.println("3 - Cancelar pedido");
        System.out.println("0 - Voltar");
        System.out.print("Digite a opção desejada: ");

        return Helpers.lerNumero(scanner);
    }

    public String lerNomeCliente() {
        System.out.print("Digite seu nome: ");

        return Helpers.lerTexto(scanner);
    }

    public void exibirPedido(Pedido pedido) {
        System.out.printf("%n--- PEDIDO #%d - %s ---%n", pedido.getIdPedido(), pedido.getNomeCliente());
        System.out.println("Itens:");
        int largura = pedido.getItens().stream()
                .mapToInt(item -> item.getDescricao().length())
                .max()
                .orElse(0);
        largura = Math.max(largura, "Total:".length());

        String formatoItem = "     - %-" + largura + "s  R$ %6.2f%n";
        String formatoTotal = "     %-" + (largura + 2) + "s  R$ %6.2f%n";

        for (ItemCardapio item : pedido.getItens()) {
            System.out.printf(formatoItem, item.getDescricao(), item.getPreco());
        }

        System.out.println("     " + "-".repeat(largura + 13));
        System.out.printf(formatoTotal, "Total:", pedido.calcularTotal());
        System.out.printf(formatoTotal, "Gorjeta:", pedido.getGorjeta());
    }

    public void exibirPedidosDoDia(Collection<Pedido> pedidosDoDia) {
        if (pedidosDoDia.isEmpty()) {
            System.out.println("\nNENHUM PEDIDO REGISTRADO HOJE");
            return;
        }

        System.out.println("\n=== PEDIDOS DO DIA ===");
        pedidosDoDia.forEach(this::exibirPedido);
    }

    public void exibirFechamentoRestaurante(RestauranteFacade restaurante) {
        HashSet<String> combinacoes = restaurante.pegarCombinacoesDoDia();

        System.out.println("\n=== COMBINAÇÕES DO DIA ===");
        combinacoes.forEach(System.out::println);

        double totalVendido = restaurante.calcularTotalDoDia();
        double totalGorjetas = restaurante.calcularTotalGorjetas();

        System.out.println("\n=== FECHAMENTO DO DIA ===");
        System.out.printf("Total vendido:   R$ %8.2f%n", totalVendido);
        System.out.printf("Total gorjetas:  R$ %8.2f%n", totalGorjetas);
        System.out.printf("Total geral:     R$ %8.2f%n", totalVendido + totalGorjetas);
    }

    public void sairDoMenuConsole() {
        System.out.println("\n=== SAINDO DO SISTEMA ===");
        scanner.close();
    }

    public void irParaMenuPrincipal() {
        System.out.println("\n=== RETORNANDO AO MENU PRINCIPAL... ===\n");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void registrarNovoPedido(RestauranteFacade restaurante) {
        String nomeCliente = lerNomeCliente();
        Pedido novoPedido = restaurante.criarNovoPedido(nomeCliente);

        adicionarItensAoPedido(restaurante, novoPedido);
        registrarGorjeta(restaurante, novoPedido);
        exibirPedido(novoPedido);
    }

    private void registrarGorjeta(RestauranteFacade restaurante, Pedido pedido) {
        System.out.print("\nTeve gorjeta? [S] Sim [N] Não: ");
        if (!Helpers.lerTexto(scanner).trim().equalsIgnoreCase("s")) {
            return;
        }

        while (true) {
            System.out.print("Valor da gorjeta: R$ ");
            try {
                restaurante.registrarGorjeta(pedido, Helpers.lerDecimal(scanner));
                return;
            } catch (IllegalArgumentException exception) {
                Helpers.exibirMensagemErro(exception.getMessage());
            }
        }
    }

    public void abrirSubMenuModificacao(RestauranteFacade restaurante, Pedido pedido) {
        int opcaoEscolhida = lerSubMenuDeModificacao();

        while (opcaoEscolhida != 0) {
            switch (opcaoEscolhida) {
                case 1 -> adicionarItensAoPedido(restaurante, pedido);
                case 2 -> removerItemDoPedido(restaurante, pedido);
                case 3 -> cancelarPedido(restaurante, pedido.getIdPedido());
                default -> Helpers.exibirMensagemErro("Opção inválida.");
            }

            opcaoEscolhida = lerSubMenuDeModificacao();
        }
    }

    private ItemCardapio incluirAdicionais(ItemCardapio lanche) {
        int adicionalEscolhido = lerAdicionaisLanche();

        while (adicionalEscolhido != 0) {
            lanche = switch (adicionalEscolhido) {
                case 1 -> new ComCheddar(lanche);
                case 2 -> new ComBaconExtra(lanche);
                default -> {
                    Helpers.exibirMensagemErro("Adicional nao existe");
                    yield lanche;
                }
            };

            adicionalEscolhido = lerAdicionaisLanche();
        }

        return lanche;
    }

    private void retirarIngredientes(Lanche lanche) {
        int ingredienteEscolhido = lerIngredientesLanche();

        while (ingredienteEscolhido != 0) {
            switch (ingredienteEscolhido) {
                case 1 -> lanche.setQueijo(false);
                case 2 -> lanche.setAlface(false);
                case 3 -> lanche.setTomate(false);
                case 4 -> lanche.setCebola(false);
                default -> Helpers.exibirMensagemErro("Ingrediente nao existe");
            }

            ingredienteEscolhido = lerIngredientesLanche();
        }
    }

    private void adicionarItensAoPedido(RestauranteFacade restaurante, Pedido pedido) {
        int opcaoLanche = lerOpcaoLanche();

        while (opcaoLanche != 0) {
            Lanche lanche;

            try {
                lanche = restaurante.criarLanche(opcaoLanche);
            } catch (Exception exception) {
                Helpers.exibirMensagemErro(exception.getMessage());
                opcaoLanche = lerOpcaoLanche();
                continue;
            }

            retirarIngredientes(lanche);
            ItemCardapio lancheFinalizado = incluirAdicionais(lanche);
            restaurante.adicionarItemAoPedido(pedido, lancheFinalizado);

            opcaoLanche = lerOpcaoLanche();
        }

        System.out.println("\n=== PEDIDO ATUALIZADO ===");
        exibirPedido(pedido);
    }

    private void exibirItensParaRemocao(Pedido pedido) {
        System.out.printf("%n--- PEDIDO #%d - %s ---%n", pedido.getIdPedido(), pedido.getNomeCliente());

        for (int indice = 0; indice < pedido.getItens().size(); indice++) {
            ItemCardapio itemCardapio = pedido.getItens().get(indice);
            System.out.printf("Item #%d - %-45s R$ %6.2f%n", indice + 1, itemCardapio.getDescricao(), itemCardapio.getPreco());
        }
    }

    private void removerItemDoPedido(RestauranteFacade restauranteFacade, Pedido pedido) {
        exibirItensParaRemocao(pedido);
        System.out.print("Digite o número do item que deseja remover: ");
        int numeroItem = Helpers.lerNumero(scanner);

        try {
            restauranteFacade.removerItemDoPedido(pedido, numeroItem - 1);
        } catch (IllegalArgumentException exception) {
            Helpers.exibirMensagemErro(exception.getMessage());
            return;
        }

        System.out.println("\n=== PEDIDO ATUALIZADO ===");
        exibirPedido(pedido);
    }

    public void buscarPedidoParaModificacao(RestauranteFacade restaurante) {
        System.out.print("Digite o número do pedido que deseja modificar: ");
        int idPedido = Helpers.lerNumero(scanner);

        Pedido pedido = restaurante.buscarPedido(idPedido);
        if (pedido == null) {
            System.out.println("Pedido não encontrado.");
            return;
        }

        exibirPedido(pedido);
        abrirSubMenuModificacao(restaurante, pedido);
    }

    private void cancelarPedido(RestauranteFacade restaurante, int idPedido) {
        restaurante.cancelarPedido(idPedido);
        System.out.println("\n=== PEDIDO CANCELADO ===");
    }

    public void avisoOpcaoInvalida() {
        Helpers.exibirMensagemErro("Opção inválida. Tente novamente.");
    }
}
