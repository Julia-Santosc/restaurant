package pedido;

import cardapio.Lanche;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private int idPedido;
    private String nomeCliente;
    private ArrayList<Lanche> itens = new ArrayList<>();

    public Pedido(int numero, String nomeCliente) {
        this.idPedido = numero;

        if (nomeCliente == null || nomeCliente.isBlank()){
            throw new IllegalArgumentException("Nome do cliente nao pode ser vazio nem nulo.");
        }
    }

    void adicionarItem(Lanche lanche){
      itens.add(lanche);
    }

    public ArrayList<Lanche> getItens() {
        return itens;
    }

    double calcularTotal() {
        double valorTotal = 0;
        for (Lanche lanche : itens) {
            valorTotal += lanche.getPreco();
        }
        return valorTotal;
    }

    public int getIdPedido() {
        return idPedido;
    }
}
