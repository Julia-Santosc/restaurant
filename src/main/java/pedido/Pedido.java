package pedido;

import cardapio.ItemCardapio;

import java.util.ArrayList;

public class Pedido {

    private final int idPedido;
    private String nomeCliente;
    private final ArrayList<ItemCardapio> itens = new ArrayList<>();
    private double gorjeta = 0;

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
            throw new IllegalArgumentException("O nome do cliente não pode ser vazio nem nulo.");
        }

        this.nomeCliente = nomeCliente;
    }

    public String getNomeCliente(){
        return nomeCliente;
    }

    public double getGorjeta() {
        return gorjeta;
    }

    public void setGorjeta(double gorjeta) {
        if (gorjeta < 0) {
            throw new IllegalArgumentException("A gorjeta não pode ser negativa.");
        }

        this.gorjeta = gorjeta;
    }
}
