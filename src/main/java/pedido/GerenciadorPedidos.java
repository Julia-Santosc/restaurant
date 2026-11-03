package pedido;

import cardapio.ItemCardapio;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class GerenciadorPedidos {
    private final HashMap<Integer, Pedido> pedidos = new HashMap<>();
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

    public double calcularTotalGorjetas() {
        double totalGorjetas = 0;
        for (Pedido pedido : pedidos.values()) {
            totalGorjetas += pedido.getGorjeta();
        }
        return totalGorjetas;
    }

    public void removerItemDoPedido(Pedido pedido, int indiceDoItemParaRemover) {
        if (indiceDoItemParaRemover < 0 || indiceDoItemParaRemover >= pedido.getItens().size()) {
            throw new IllegalArgumentException("O item não está presente no pedido.");
        }

        pedido.getItens().remove(indiceDoItemParaRemover);
    }

    public void cancelarPedido(int idPedido) {
        pedidos.remove(idPedido);
    }

    public HashSet<String> pegarCombinacoesDoDia() {
        HashSet<String> combinacoesDoDia = new HashSet<>();

        for (Pedido pedido : pedidos.values()) {
            for (ItemCardapio item : pedido.getItens()) {
                String[] partes = item.getDescricao().split(" \\+ ");
                Arrays.sort(partes, 1, partes.length);
                combinacoesDoDia.add(String.join(" + ", partes));
            }
        }

        return combinacoesDoDia;
    }
}
