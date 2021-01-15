//  Nome: Arthur Felipe Bravo Pita
//  RA: 770987
//  Data (entrega): 08.01.2021

package projetoxadrez;
import java.util.Scanner;
import java.util.InputMismatchException;
public class Gerenciador {
    public static void main(String[] args) {
        System.out.println("Bem-vindo ao jogo de Xadrez");
        Scanner ler =  new Scanner(System.in);

        String opcao="3";
        while (!opcao.equals("0")) {
            System.out.println("Opções:");
            System.out.println("0 - Sair do jogo");
            System.out.println("1 - Iniciar jogo");
            System.out.println("2 - Retomar jogo");

            try {
                opcao = ler.nextLine();
                switch (opcao) {
                    case "0":
                        System.out.println("Encerrando do jogo...");
                        ler.close();
                        System.exit(0);
                        
                    case "1":
                        // Ao criar o jogo com seus dois jogadores, já se atribui automaticamente
                        // as peças brancas ao primeiro jogador e as pretas ao segundo
                        String nome1, nome2;
                        System.out.print("Nome do Jogador 1 (peças brancas): ");
                        nome1 = ler.next();
                        System.out.print("Nome do Jogador 2 (peças pretas): ");
                        nome2 = ler.next();
                        new Jogo(nome1, nome2);
                        
                        // Encerra o jogo + fecha scanner
                        System.out.println("Obrigado por jogar!");
                        ler.close();
                        System.exit(0);

                    case "2":
                        new Jogo();

                        // Encerra o jogo + fecha scanner
                        System.out.println("Obrigado por jogar!");
                        ler.close();
                        System.exit(0);

                    default:
                        System.out.println("Opção incorreta - Insira novamente");
                        break;
                }
            } catch (InputMismatchException excep) {
                System.out.println("Entrada inválida");
                opcao = "3";
            }
        }
    }
}