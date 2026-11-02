package cardapio;

public class LancheFactory {

    public static Lanche xBurguer(){
        return new Lanche("X-Burguer", 16.00);
    }

    public static Lanche xSalada(){
        return new Lanche("X-Salada", 18.00);
    }

    public static Lanche xBacon(){
        return new Lanche("X-Bacon", 22.00);
    }

}
