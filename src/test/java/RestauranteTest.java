import cardapio.*;
import org.junit.jupiter.api.Test;
import pedido.GerenciadorPedidos;
import pedido.Pedido;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RestauranteTest {
    @Test
    public void lanche_com_preco_negativo_recusado() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Lanche("Lanche Negativo", -10.0);
        });
    }

    @Test
    public void lanche_com_adicionais_tem_preco_calculado() {
        ItemCardapio lanche = LancheFactory.xBurguer(); // custa 16.00
        lanche = new ComCheddar(lanche); // adiciona 4.00
        lanche = new ComBaconExtra(lanche); // adiciona 6.00

        assertEquals(26.0, lanche.getPreco(), 0.001);
    }

    @Test
    public void combinacoes_repetidas_contam_uma_vez() {
        GerenciadorPedidos gerenciador = new GerenciadorPedidos();

        gerenciador.criarPedido("Julia").adicionarItem(LancheFactory.xBacon());
        gerenciador.criarPedido("Joao").adicionarItem(LancheFactory.xBacon());
        gerenciador.criarPedido("Maria").adicionarItem(new ComCheddar(LancheFactory.xBacon()));

        assertEquals(2, gerenciador.pegarCombinacoesDoDia().size());
    }

    @Test
    public void total_do_dia_soma_todos_os_pedidos() {
        GerenciadorPedidos gerenciador = new GerenciadorPedidos();

        gerenciador.criarPedido("Julia").adicionarItem(LancheFactory.xBurguer());
        gerenciador.criarPedido("Joao").adicionarItem(LancheFactory.xSalada());

        assertEquals(34.0, gerenciador.calcularTotalDoDia(), 0.001);
    }

    @Test
    public void pedido_cancelado_sai_do_total_e_da_busca() {
        GerenciadorPedidos gerenciador = new GerenciadorPedidos();

        Pedido pedidoMantido = gerenciador.criarPedido("Julia");
        pedidoMantido.adicionarItem(LancheFactory.xBurguer());
        Pedido pedidoCancelado = gerenciador.criarPedido("Joao");
        pedidoCancelado.adicionarItem(LancheFactory.xSalada());

        assertEquals(34.0, gerenciador.calcularTotalDoDia(), 0.001);

        gerenciador.cancelarPedido(pedidoCancelado.getIdPedido());

        assertEquals(16.0, gerenciador.calcularTotalDoDia(), 0.001);
        assertNull(gerenciador.buscarPedido(pedidoCancelado.getIdPedido()));
    }
}
