package cardapio;

public class Lanche implements ItemCardapio {
    private String nome;
    private double preco;
    private boolean queijo = true;
    private boolean alface = true;
    private boolean tomate = true;
    private boolean cebola = true;

    public Lanche(String nome, double preco) {
        setNome(nome);
        setPreco(preco);
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome não pode ser nulo nem vazio.");
        }

        this.nome = nome;
    }

    public void setPreco(double preco) {
        if (preco <= 0) {
            throw new IllegalArgumentException("O valor não pode ser negativo. Valor informado: " + preco);
        }

        this.preco = preco;
    }

    public String getNomeBase() {
        return nome;
    }

    public String getDescricao() {
        String descricao = nome;

        if (!queijo) {
            descricao += " sem queijo";
        }

        if (!alface) {
            descricao += " sem alface";
        }

        if (!tomate) {
            descricao += " sem tomate";
        }

        if (!cebola) {
            descricao += " sem cebola";
        }

        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public boolean getQueijo() {
        return queijo;
    }

    public void setQueijo(boolean queijo) {
        this.queijo = queijo;
    }

    public boolean getAlface() {
        return alface;
    }

    public void setAlface(boolean alface) {
        this.alface = alface;
    }

    public boolean getTomate() {
        return tomate;
    }

    public void setTomate(boolean tomate) {
        this.tomate = tomate;
    }

    public boolean getCebola() {
        return cebola;
    }

    public void setCebola(boolean cebola) {
        this.cebola = cebola;
    }
}

