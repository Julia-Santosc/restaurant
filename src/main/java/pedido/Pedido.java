package pedido;

import cardapio.Lanche;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class Pedido {

    private int idPedido;
    private String nomeCliente;
    private ArrayList<Lanche> itens = new ArrayList<>();

    public Pedido(int idPedido, String nomeCliente) {
        this.idPedido = idPedido;
        setNomeCliente(nomeCliente);
    }

    public void adicionarItem(Lanche lanche){
      itens.add(lanche);
    }

    public ArrayList<Lanche> getItens() {
        return itens;
    }

    public double calcularTotal() {
        double valorTotal = 0;
        for (Lanche lanche : itens) {
            valorTotal += lanche.getPreco();
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
