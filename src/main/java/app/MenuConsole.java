package app;

import pedido.Pedido;

import java.util.Collection;
import java.util.Scanner;

public class MenuConsole {
    private final Scanner sc = new Scanner(System.in);

    private void exibirOpcoesLanches() {
        System.out.println("1 - X-Burguer (R$ 16,00)");
        System.out.println("2 - X-Salada (R$ 18,00)");
        System.out.println("3 - X-Bacon (R$ 22,00)");
        System.out.println("0 - Finalizar Pedido");
    }

    private void exibirAdicionaisLanche() {
        System.out.println("\nAdicionais:");
        System.out.println("1 - Cheddar");
        System.out.println("2 - Bacon extra");
        System.out.println("0 - Nenhum / terminar");
    }


    public int lerOpcaoLanche() {
        exibirOpcoesLanches();
        System.out.print("Digite a opção desejada: ");

        int opcaoLanche = sc.nextInt();
        sc.nextLine();

        return opcaoLanche;
    }

    public int lerAdicionaisLanche(){
        exibirAdicionaisLanche();
        System.out.print("Digite o adicional desejado: ");

        int adicionalEscolhido = sc.nextInt();
        sc.nextLine();

        return adicionalEscolhido;
    }

    public String lerNomeCliente() {
        System.out.print("Digite seu nome: ");
        String nome = sc.nextLine();

        return nome;
    }

    public int exibirMenuPrincipal() {
        System.out.println("=== RESTAURANTE ===");
        System.out.println("1 - Registrar novo pedido");
        System.out.println("2 - Listar pedidos do dia");
        System.out.println("3 - Fechar restaurante");
        System.out.println("0 - Sair");
        System.out.print("\nDigite a opção desejada: ");

        int opcaoEscolhida = sc.nextInt();
        sc.nextLine();

        return opcaoEscolhida;
    }

    public void exibirPedido(Pedido pedido) {
        System.out.printf("--- PEDIDO #%d - %s ---%n", pedido.getIdPedido(), pedido.getNomeCliente());
        System.out.println("Itens:");
        pedido.getItens().forEach((lanche) -> {
            System.out.printf("     - %-15s R$ %6.2f%n", lanche.getDescricao(), lanche.getPreco());
        });

        System.out.println("  " + "-".repeat(33));
        System.out.printf("     %-17s R$ %6.2f%n%n", "Total:", pedido.calcularTotal());
    }

    public void exibirPedidosDoDia(Collection<Pedido> pedidosDoDia) {
        if (pedidosDoDia.isEmpty()) {
            System.out.println("\nNENHUM PEDIDO REGISTRADO HOJE\n");
            return;
        }

        System.out.println("\n=== PEDIDOS DO DIA ===");
        pedidosDoDia.forEach((Pedido pedido) -> exibirPedido(pedido));
    }

    public void exibirFechamentoRestaurante(Double totalDosPedidos) {
        System.out.println("\n=== TOTAL VENDIDO ===");
        System.out.printf("    R$ %.2f %n%n", totalDosPedidos);
    }

    public void sairDoMenuConsole() {
        System.out.println("\n=== SAINDO DO SISTEMA ===");
        sc.close();
    }

    public void irMenuPrincipal() {
        System.out.println("=== RETORNANDO AO MENU PRINCIPAL... ===\n");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}

