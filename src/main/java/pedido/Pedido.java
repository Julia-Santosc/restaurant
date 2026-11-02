package pedido;

import cardapio.ItemCardapio;
import lombok.ToString;

import java.util.ArrayList;

@ToString
public class Pedido {

    private int idPedido;
    private String nomeCliente;
    private ArrayList<ItemCardapio> itens = new ArrayList<>();
    public Pedido(int idPedido, String nomeCliente) {
        this.idPedido = idPedido;
        setNomeCliente(nomeCliente);
    }

    public void adicionarItem(ItemCardapio item){
      itens.add(item);
    }

    public ArrayList<ItemCardapio> getItens() {
        return itens;
    }

    public double calcularTotal() {
        double valorTotal = 0;
        for (ItemCardapio item : itens) {
            valorTotal += item.getPreco();
        }
        return valorTotal;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setNomeCliente(String nomeCliente) {
        if (nomeCliente == null || nomeCliente.isBlank()){
            throw new IllegalArgumentException("Nome do cliente nao pode ser vazio nem nulo.");
        }

        this.nomeCliente = nomeCliente;
    }

    public String getNomeCliente(){
        return nomeCliente;
    }
}
