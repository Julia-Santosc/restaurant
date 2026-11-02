package cardapio;

import lombok.ToString;

@ToString
public class Lanche implements ItemCardapio {
    private String nome;
    private double preco;

    public Lanche(String nome, double preco) {
        setNome(nome);
        setPreco(preco);
    }

    public String getDescricao() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome não pode ser nulo nem vazio.");
        }

        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco <= 0) {
            throw new IllegalArgumentException("O valor não pode ser negativo. Valor informado: " + preco);
        }

        this.preco = preco;
    }
}

