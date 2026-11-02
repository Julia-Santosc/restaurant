package app;

import cardapio.ComBaconExtra;
import cardapio.ComCheddar;
import cardapio.ItemCardapio;
import cardapio.Lanche;
import pedido.Pedido;

import java.util.Collection;
import java.util.Scanner;

public class MenuConsole {
    private final Scanner sc = new Scanner(System.in);

    private void exibirMenuPrincipal() {
        System.out.println("=== MENU PRINCIPAL ===");
        System.out.println("1 - Registrar novo pedido");
        System.out.println("2 - Listar pedidos do dia");
        System.out.println("3 - Fechar restaurante");
        System.out.println("4 - Modificar pedido");
        System.out.println("0 - Sair");
        System.out.print("\nDigite a opção desejada: ");
    }

    private void exibirOpcoesLanches() {
        System.out.println("\n=== CARDÁPIO ===");
        System.out.println("1 - X-Burguer (R$ 16,00)");
        System.out.println("2 - X-Salada (R$ 18,00)");
        System.out.println("3 - X-Bacon (R$ 22,00)");
        System.out.println("0 - Finalizar Pedido");
        System.out.print("Digite a opcao desejada: ");
    }

    private void exibirIngredientesLanche() {
        System.out.println("\nIngredientes: ");
        System.out.println("1 - Queijo");
        System.out.println("2 - Alface");
        System.out.println("3 - Tomate");
        System.out.println("4 - Cebola");
        System.out.println("0 - Sair");
        System.out.print("Digite a opção desejada: ");
    }

    private void exibirAdicionaisLanche() {
        System.out.println("\nAdicionais:");
        System.out.println("1 - Cheddar");
        System.out.println("2 - Bacon extra");
        System.out.println("0 - Nenhum / terminar");
        System.out.print("Digite o adicional desejado: ");
    }

    private void exibirSubMenuDeModificacao(){
        System.out.println("\n=== MODIFICAR PEDIDO ===");
        System.out.println("1 - Acrescentar item");
        System.out.println("2 - Remover item");
        System.out.println("3 - Cancelar pedido");
        System.out.println("0 - Voltar");
        System.out.print("Digite a opção desejada: ");
    }

    public int lerOpcaoMenuPrincipal() {
        exibirMenuPrincipal();
        int opcaoEscolhida = sc.nextInt();
        sc.nextLine();

        return opcaoEscolhida;
    }

    public int lerOpcaoLanche() {
        exibirOpcoesLanches();
        int opcaoLanche = sc.nextInt();
        sc.nextLine();

        return opcaoLanche;
    }

    public int lerIngredientesLanche() {
        exibirIngredientesLanche();
        int opcaoIngrediente = sc.nextInt();
        sc.nextLine();

        return opcaoIngrediente;
    }

    public int lerAdicionaisLanche() {
        exibirAdicionaisLanche();
        int adicionalEscolhido = sc.nextInt();
        sc.nextLine();

        return adicionalEscolhido;
    }

    public int lerSubMenuDeModificacao(){
        exibirSubMenuDeModificacao();
        int modificacaoEscolhida = sc.nextInt();
        sc.nextLine();

        return modificacaoEscolhida;
    }

    public String lerNomeCliente() {
        System.out.print("Digite seu nome: ");

        return sc.nextLine();
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
    }

    public void exibirPedidosDoDia(Collection<Pedido> pedidosDoDia) {
        if (pedidosDoDia.isEmpty()) {
            System.out.println("\nNENHUM PEDIDO REGISTRADO HOJE");
            return;
        }

        System.out.println("\n=== PEDIDOS DO DIA ===");
        pedidosDoDia.forEach((Pedido pedido) -> exibirPedido(pedido));
    }

    public void exibirFechamentoRestaurante(Double totalDosPedidos) {
        System.out.println("\n=== TOTAL VENDIDO ===");
        System.out.printf("    R$ %.2f %n", totalDosPedidos);
    }

    public void sairDoMenuConsole() {
        System.out.println("\n=== SAINDO DO SISTEMA ===");
        sc.close();
    }

    public void irParaMenuPrincipal() {
        System.out.println("\n=== RETORNANDO AO MENU PRINCIPAL... ===\n");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void registrarNovoPedido(RestauranteFacade restaurante) {
        String nomeCliente = lerNomeCliente();
        Pedido novoPedido = restaurante.criarNovoPedido(nomeCliente);

        adicionarItensAoPedido(restaurante, novoPedido);
        exibirPedido(novoPedido);
    }

    public void abrirSubMenuModificacao(RestauranteFacade restaurante, Pedido pedido) {
        int opcaoEscolhida = lerSubMenuDeModificacao();

        while (opcaoEscolhida != 0) {
            switch (opcaoEscolhida) {
                case 1 -> adicionarItensAoPedido(restaurante, pedido);
                case 2 -> System.out.println(2);
                case 3 -> System.out.println(3);
                default -> throw new IllegalArgumentException("Opção inválida.");
            }

            System.out.println("\n=== PEDIDO ATUALIZADO ===");
            exibirPedido(pedido);
            opcaoEscolhida = lerSubMenuDeModificacao();
        }
    }

    private ItemCardapio incluirAdicionais(ItemCardapio lanche) {
        int adicionalEscolhido = lerAdicionaisLanche();

        while (adicionalEscolhido != 0) {
            lanche = switch (adicionalEscolhido) {
                case 1 -> new ComCheddar(lanche);
                case 2 -> new ComBaconExtra(lanche);
                default -> throw new IllegalArgumentException("Adicional nao existe");
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
                default -> throw new IllegalArgumentException("Ingrediente nao existe");
            };
            ingredienteEscolhido = lerIngredientesLanche();
        }
    }

    private void adicionarItensAoPedido(RestauranteFacade restaurante, Pedido novoPedido) {
        int opcaoLanche = lerOpcaoLanche();

        while (opcaoLanche != 0) {
            Lanche lanche = restaurante.criarLanche(opcaoLanche);
            retirarIngredientes(lanche);
            ItemCardapio lancheFinalizado = incluirAdicionais(lanche);
            restaurante.adicionarItemAoPedido(novoPedido, lancheFinalizado);

            opcaoLanche = lerOpcaoLanche();
        }
    }

    public void buscarPedidoParaModificacao(RestauranteFacade restaurante) {
        System.out.print("Digite o número do pedido que deseja modificar: ");
        int idPedido = sc.nextInt();
        sc.nextLine();

        Pedido pedido = restaurante.buscarPedido(idPedido);
        if (pedido == null) {
            System.out.println("Pedido não encontrado.");
            return;
        }

        exibirPedido(pedido);
        abrirSubMenuModificacao(restaurante, pedido);
    }
}
