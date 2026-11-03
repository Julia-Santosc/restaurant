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

    public void removerItemDoPedido(Pedido pedido, int indiceDoItemParaRemover) {
        if (indiceDoItemParaRemover < 0 || indiceDoItemParaRemover >= pedido.getItens().size()) {
            throw new IllegalArgumentException("O item não está presente no pedido.");
        }

        pedido.getItens().remove(indiceDoItemParaRemover);
    }
}
