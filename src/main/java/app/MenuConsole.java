package app;

import cardapio.Lanche;
import pedido.Pedido;

import java.util.Scanner;

public class MenuConsole {
    Scanner sc = new Scanner(System.in);

    private void exibirOpcoesLanches(){

        System.out.println("1 - Hambúrguer (R$ 18,00)");
        System.out.println("2 - X - Salada (R$ 25,00)");
        System.out.println("3 - X - Frango (R$ 27,00)");
        System.out.println("4 - X - Bacon (R$ 28,00)");
    }

    public void lerOpcaoLanche(){
        exibirOpcoesLanches();
        int opcaoLanche = sc.nextInt();
    }

    public void exibirPedido(){
        System.out.println("Seu pedido: ");
    }
}
