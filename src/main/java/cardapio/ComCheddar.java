package cardapio;

public class ComCheddar implements ItemCardapio {

    private final ItemCardapio lanche;

    public ComCheddar(ItemCardapio lanche) {
        this.lanche = lanche;
    }

    @Override
    public String getDescricao() {
        return lanche.getDescricao() + " + cheddar";
    }

    @Override
    public double getPreco() {
        return lanche.getPreco() + 4.0;
    }

    @Override
    public String getNomeBase() {
        return lanche.getNomeBase();
    }
}
