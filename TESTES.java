//  Nome: Arthur Felipe Bravo Pita
//  RA: 770987
//  Data (entrega): 30.10.2020

package projetoxadrez;
import projetoxadrez.Pecas.*;
public class TESTES {
    public static void main(String[] args) {
        // ------- Teste 1 -------
        Rei rei = new Rei("Branco");
        for (int x=0; x<=7; x++) {
            for (int y=97; y<=104; y++) {
                System.out.print("(" + x + "," + (char)y + ")= " + rei.checaMovimento(4, 'd', x, (char)y));
                System.out.print(" / ");
            }
            System.out.println();
        }
        // ------- Teste 2 -------
        System.out.println("Cor: " + rei.isBranco() );
        System.out.println("Em Jogo: " + rei.getEmJogo() );
        System.out.println("Desenho: " + rei.desenho() );
        rei.setEmJogo(false);
        System.out.println("Em Jogo: " + rei.getEmJogo() );
        System.out.println("Desenho: " + rei.desenho() );
        
        // ------- Teste 3 -------
        Dama dama = new Dama("Branco");
        for (int x=0; x<=7; x++) {
            for (int y=97; y<=104; y++) {
                System.out.print("(" + x + "," + (char)y + ")= " + dama.checaMovimento(4, 'd', x, (char)y));
                System.out.print(" / ");
            }
            System.out.println();
        }
        // ------- Teste 4 -------
        System.out.println("Cor: " + dama.isBranco() );
        System.out.println("Em Jogo: " + dama.getEmJogo() );
        System.out.println("Desenho: " + dama.desenho() );
        dama.setEmJogo(false);
        System.out.println("Em Jogo: " + dama.getEmJogo() );
        System.out.println("Desenho: " + dama.desenho() );

        // ------- Teste 5 -------
        Cavalo cavalo = new Cavalo("Branco");
        for (int x=0; x<=7; x++) {
            for (int y=97; y<=104; y++) {
                System.out.print("(" + x + "," + (char)y + ")= " + cavalo.checaMovimento(4, 'd', x, (char)y));
                System.out.print(" / ");
            }
            System.out.println();
        }
        // ------- Teste 6 -------
        System.out.println("Cor: " + cavalo.isBranco() );
        System.out.println("Em Jogo: " + cavalo.getEmJogo() );
        System.out.println("Desenho: " + cavalo.desenho() );
        cavalo.setEmJogo(false);
        System.out.println("Em Jogo: " + cavalo.getEmJogo() );
        System.out.println("Desenho: " + cavalo.desenho() );

        // ------- Teste 7 -------
        Bispo bispo = new Bispo("Branco");
        for (int x=0; x<=7; x++) {
            for (int y=97; y<=104; y++) {
                System.out.print("(" + x + "," + (char)y + ")= " + bispo.checaMovimento(4, 'd', x, (char)y));
                System.out.print(" / ");
            }
            System.out.println();
        }
        // ------- Teste 8 -------
        System.out.println("Cor: " + bispo.isBranco() );
        System.out.println("Em Jogo: " + bispo.getEmJogo() );
        System.out.println("Desenho: " + bispo.desenho() );
        bispo.setEmJogo(false);
        System.out.println("Em Jogo: " + bispo.getEmJogo() );
        System.out.println("Desenho: " + bispo.desenho() );
        
        // ------- Teste 9 -------
        Torre torre = new Torre("Branco");
        for (int x=0; x<=7; x++) {
            for (int y=97; y<=104; y++) {
                System.out.print("(" + x + "," + (char)y + ")= " + torre.checaMovimento(4, 'd', x, (char)y));
                System.out.print(" / ");
            }
            System.out.println();
        }
        // ------- Teste 10 -------
        System.out.println("Cor: " + torre.isBranco() );
        System.out.println("Em Jogo: " + torre.getEmJogo() );
        System.out.println("Desenho: " + torre.desenho() );
        torre.setEmJogo(false);
        System.out.println("Em Jogo: " + torre.getEmJogo() );
        System.out.println("Desenho: " + torre.desenho() );
        
        // ------- Teste 11 -------
        Peao peao = new Peao("Branco");
        for (int x=0; x<=7; x++) {
            for (int y=97; y<=104; y++) {
                System.out.print("(" + x + "," + (char)y + ")= " + peao.checaMovimento(4, 'd', x, (char)y));
                System.out.print(" / ");
            }
            System.out.println();
        }
        // ------- Teste 12 -------
        System.out.println("Cor: " + peao.isBranco() );
        System.out.println("Em Jogo: " + peao.getEmJogo() );
        System.out.println("Desenho: " + peao.desenho() );
        peao.setEmJogo(false);
        System.out.println("Em Jogo: " + peao.getEmJogo() );
        System.out.println("Desenho: " + peao.desenho() );
        
        // ------- Teste 13 -------
        for (int x=0; x<=7; x++) {
            for (int y=97; y<=104; y++) {
                System.out.print("(" + x + "," + (char)y + ")= " + peao.checaMovimento(1, 'd', x, (char)y));
                System.out.print(" / ");
            }
            System.out.println();
        }
        // ------- Teste 14 -------
        Peao peao2 = new Peao("Preto");
        for (int x=0; x<=7; x++) {
            for (int y=97; y<=104; y++) {
                System.out.print("(" + x + "," + (char)y + ")= " + peao2.checaMovimento(4, 'd', x, (char)y));
                System.out.print(" / ");
            }
            System.out.println();
        }
        // ------- Teste 15 -------
        for (int x=0; x<=7; x++) {
            for (int y=97; y<=104; y++) {
                System.out.print("(" + x + "," + (char)y + ")= " + peao2.checaMovimento(6, 'd', x, (char)y));
                System.out.print(" / ");
            }
            System.out.println();
        }

        // ------- Teste 16 -------
        Jogador jogador = new Jogador("Player","Preta");

        // ------- Teste 17 -------
        Posicao posicao = new Posicao(2,'b',false);
        System.out.println("Ocupada? " + posicao.getOcupada());
        System.out.println("Cor: " + posicao.isBranco());
        
        // ------- Teste 18 -------
        Posicao posicao2 = new Posicao(-5,'z',true);
        System.out.println("Ocupada? " + posicao2.getOcupada());
        System.out.println("Cor: " + posicao2.isBranco());
        
        // ------- Teste 19 -------
        Tabuleiro tabuleiro = new Tabuleiro();
        tabuleiro.desenhaTabuleiro();

        // ------- Teste 20 -------
        Jogo jogo = new Jogo("Primeiro","Segundo");
    }
}