package pedido;

import lombok.ToString;

import java.util.HashMap;

@ToString
public class GerenciadorPedidos {
    private HashMap<Integer, Pedido> pedidos = new HashMap<>();
    private int contadorPedidos = 1;

    public Pedido criarPedido(String nomeCliente){
      Pedido pedido =  new Pedido(contadorPedidos, nomeCliente);

      pedidos.put(pedido.getIdPedido(), pedido);
      contadorPedidos++;

      return pedido;
    }

    public Pedido buscarPedido(int idPedido){
        return pedidos.get(idPedido);
    }

    public HashMap<Integer, Pedido> getPedidos(){
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
