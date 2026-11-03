package cardapio;

public class ComBaconExtra implements ItemCardapio {

    private final ItemCardapio lanche;

    public ComBaconExtra(ItemCardapio lanche) {
        this.lanche = lanche;
    }

    @Override
    public String getDescricao() {
        return lanche.getDescricao() + " + bacon extra";
    }

    @Override
    public double getPreco() {
        return lanche.getPreco() + 6.00;
    }

    @Override
    public String getNomeBase() {
        return lanche.getNomeBase();
    }
}
