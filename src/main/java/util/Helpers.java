package util;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class Helpers {
    public static int lerNumero(Scanner scanner) {
        while (true) {
            try {
                int numero = scanner.nextInt();
                scanner.nextLine();
                return numero;
            } catch (InputMismatchException exception) {
                scanner.nextLine();
                System.out.println("Digite apenas números.");
            }
        }
    }

    public static String lerTexto(Scanner scanner) {
        while (true) {
            String texto = scanner.nextLine();

            if (texto == null || texto.isBlank()) {
                exibirMensagemErro("O texto não pode ser vazio nem nulo.");
                continue;
            }

            return texto;
        }
    }

    public static void exibirMensagemErro(String mensagem) {
        System.out.printf("### ERRO: %s ###%n", mensagem);
    }
}
