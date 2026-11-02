import app.RestauranteFacade;
import cardapio.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import pedido.Pedido;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
