package pedido;

import cardapio.Lanche;

import java.util.HashMap;
import java.util.NoSuchElementException;

public class GerenciadorPedidos {
    private HashMap<Integer, Pedido> pedidos = new HashMap<>();
    private int contadorPedidos = 1;

    Pedido criarPedido(String nomeCliente){
      Pedido pedido =  new Pedido(contadorPedidos, nomeCliente);

      pedidos.put(pedido.getIdPedido(), pedido);
      contadorPedidos++;

      return pedido;
    }

    Pedido buscarPedido(int idPedido){
        // grave problema de NPE - null pointer exception
        if (!pedidos.containsKey(idPedido)) {
            throw new NoSuchElementException("Pedido nao encontrado");
        }
        Pedido pedidoEncontrado = pedidos.get(idPedido);
        return pedidoEncontrado;
    }

    HashMap<Integer, Pedido> getPedidos(){
        return pedidos;
    }

    public double calcularTotalDoDia(){
        double totalDoDia = 0;
        for (Pedido pedido : pedidos.values()){
            totalDoDia += pedido.calcularTotal();
        }
        return totalDoDia;
    }


}
