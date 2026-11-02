package cardapio;

public class LancheFactory {

    public static Lanche xburguer(){
        return new Lanche("X-Burguer", 16.00);
    }

    public static Lanche xsalada(){
        return new Lanche("X-Salada", 18.00);
    }

    public static  Lanche xbacon(){
        return new Lanche("X-Bacon", 22);
    }

}
