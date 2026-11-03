package app;

public class Main {
    private static final RestauranteFacade restaurante = new RestauranteFacade();
    private static final MenuConsole menu = new MenuConsole();

    public static void main(String[] args) {
        int opcaoMenuPrincipal = menu.lerOpcaoMenuPrincipal();

        while (opcaoMenuPrincipal != 0) {
            switch (opcaoMenuPrincipal) {
                case 1 -> menu.registrarNovoPedido(restaurante);
                case 2 -> menu.exibirPedidosDoDia(restaurante.listarPedidos());
                case 3 -> menu.exibirFechamentoRestaurante(restaurante);
                case 4 -> menu.buscarPedidoParaModificacao(restaurante);
                default -> throw new IllegalArgumentException("Opção inválida.");
            }

            menu.irParaMenuPrincipal();
            opcaoMenuPrincipal = menu.lerOpcaoMenuPrincipal();
        };

        menu.sairDoMenuConsole();
    }
}
