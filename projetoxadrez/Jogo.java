//  Nome: Arthur Felipe Bravo Pita
//  RA: 770987
//  Data (entrega): 08.01.2021

package projetoxadrez;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import projetoxadrez.Pecas.*;
public class Jogo {
    // ------- Atributos -------
    private Jogador j1;
    private Jogador j2;
    private Tabuleiro tabuleiro = new Tabuleiro();
    private Peca pecas[] = new Peca[32];
    
    private int estadoJogo;
    private boolean vezJogador;

    // ------- Contructors, Getters, Setters -------
    public Jogo(String Player_One, String Player_Two) {
        this.j1 = new Jogador(Player_One, "Brancas");
        this.j2 = new Jogador(Player_Two, "Pretas");
        this.estadoJogo = 0;        // início=0, jogando=1, xeque=2, xeque-mate=3
        this.vezJogador = true;     // brancas(j1): true // pretas(j2): false
        this.inicia();
        this.recebeEntradas();
    }
    public Jogo() {
        this.recuperarJogo();
        this.recebeEntradas();
    }
    
    private int getEstadoJogo() {
        return estadoJogo;
    }
    private void setEstadoJogo(int status) {    
        if (status == 0 || status == 1 || status == 2 || status == 3)
            this.estadoJogo = status;
        else
            System.out.println("Erro ao definir estado de jogo");
    }
    private boolean getVezJogador() {
        return vezJogador;
    }
    private void setVezJogador(boolean vez) {
        this.vezJogador = vez;
    }

    // ------- Métodos -------
    // atribui cada uma das 32 peças às suas respectivas posições iniciais
    private void inicia() {
        if (getEstadoJogo()==0) {
            Peca auxiliar[] = new Peca[64];

            auxiliar[0] = new Torre("Pretas");
            auxiliar[1] = new Cavalo("Pretas");
            auxiliar[2] = new Bispo("Pretas");
            auxiliar[3] = new Dama("Pretas");
            auxiliar[4] = new Rei("Pretas");
            auxiliar[5] = new Bispo("Pretas");
            auxiliar[6] = new Cavalo("Pretas");
            auxiliar[7] = new Torre("Pretas");
            for (int i = 8; i <= 15; i++)
                auxiliar[i] = new Peao("Pretas");

            // espaço VAZIO entre as peças pretas e brancas na organização inicial do jogo
            for (int j=16; j<=47; j++)
                auxiliar[j] = null;

            for (int i = 48; i <= 55; i++)
                auxiliar[i] = new Peao("Brancas");
            auxiliar[56] = new Torre("Brancas");
            auxiliar[57] = new Cavalo("Brancas");
            auxiliar[58] = new Bispo("Brancas");
            auxiliar[59] = new Dama("Brancas");
            auxiliar[60] = new Rei("Brancas");
            auxiliar[61] = new Bispo("Brancas");
            auxiliar[62] = new Cavalo("Brancas");
            auxiliar[63] = new Torre("Brancas");

            // inicialização das peças brancas & pretas no tabuleiro (posições iniciais)
            tabuleiro.inicializa(auxiliar);

            // passagem das peças BRANCAS criadas para o JOGADOR 1
            for (int i=48; i<=63; i++)
                j1.recebePeca(auxiliar[i]);

            // passagem das peças PRETAS criadas para o JOGADOR 2
            for (int i=0; i<=15; i++)
                j2.recebePeca(auxiliar[i]);

            for (int j=0; j<=15; j++)
                pecas[j] = auxiliar[j];

            for (int j=0, k=48; j<=15; j++, k++)
                pecas[j] = auxiliar[k];

            tabuleiro.desenhaTabuleiro();
            setEstadoJogo(1);
        } else
            System.out.println("Erro ao iniciar peças no tabuleiro");
    }
    private void recebeEntradas() {
        // Utiliza a classe Scanner para ler entradas
        Scanner ler =  new Scanner(System.in);

        String entrada;
        char carac1='0', carac2='0';
        int num1=0, num2=0;

        while (getEstadoJogo() != 3) {
            System.out.println("\nPara salvar e encerrar, digite '00' a seguir");
            do {
                System.out.print("Origem: ");
                entrada = ler.nextLine();

                // salvar e encerrar a partida
                if (entrada.equals("00")) {
                    salvarJogo();
                    ler.close();
                    return;
                }

                try {
                    num1 = Integer.parseInt(String.valueOf(entrada.charAt(0)));
                    carac1 = entrada.charAt(1);
                } catch (NumberFormatException | StringIndexOutOfBoundsException exce) {
                    System.out.println("Exceção: entrada inválida");
                }
            } while (num1 < 1 || num1 > 8 || carac1 < 'a' || carac1 > 'h');

            do {
                System.out.print("Destino: ");
                entrada = ler.nextLine();

                try {
                    num2 = Integer.parseInt(String.valueOf(entrada.charAt(0)));
                    carac2 = entrada.charAt(1);
                } catch (NumberFormatException | StringIndexOutOfBoundsException exce) {
                    System.out.println("Exceção: entrada inválida");
                }
            } while (num2 < 1 || num2 > 8 || carac2 < 'a' || carac2 > 'h');

            this.jogada(num1, carac1, num2, carac2);
        }
    }
    private void jogada(int linOrg, char colOrg, int linDest, char colDest) {
        if ( tabuleiro.atualiza(getVezJogador(), linOrg-1, colOrg, linDest-1, colDest) ) {

            tabuleiro.desenhaTabuleiro();

            // Jogo normal, jogador se colocou em xeque
            if (getEstadoJogo()==1 && ((tabuleiro.checaXeque().equals("branco") && getVezJogador())
                    || (tabuleiro.checaXeque().equals("preto") && !getVezJogador())) ) {
                System.out.println("Próprio rei colocado em ameça - XEQUE-MATE");
                setEstadoJogo(3);

            // Jogo em xeque, mas jogador não tirou do xeque
            } else if (getEstadoJogo()==2 && ((tabuleiro.checaXeque().equals("branco") && getVezJogador())
                    || (tabuleiro.checaXeque().equals("preto") && !getVezJogador())) ) {
                System.out.println("O rei não foi salvo - XEQUE-MATE");
                setEstadoJogo(3);

            // Jogo normal, jogador colocou adversário em xeque
            } else if (getEstadoJogo()==1 && ((tabuleiro.checaXeque().equals("branco") && !getVezJogador())
                    || (tabuleiro.checaXeque().equals("preto") && getVezJogador())) ) {
                System.out.println("Jogo está em XEQUE");
                setEstadoJogo(2);

            // Jogo em xeque, mas jogador tirou do xeque
            } else if (getEstadoJogo()==2 && tabuleiro.checaXeque().equals("nenhuma")) {
                System.out.println("Saiu do XEQUE");
                setEstadoJogo(1);
            }

            setVezJogador( !getVezJogador() );
        } else
            System.out.println("Erro: Jogada não realizada");
    }

    private void salvarJogo() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o nome do arquivo:");
        String nomeArq = scan.next();
        nomeArq = nomeArq.concat(".txt");

        try {
            File externo = new File(nomeArq);
            if (externo.createNewFile())
                System.out.println("Arquivo criado");
            else
                System.out.println("Arquivo de mesmo nome substituído");
        } catch (IOException exce) {
            System.out.println("Exceção ao tentar criar o arquivo");
            return;
        }

        try {
            FileWriter externo2 = new FileWriter(nomeArq);

            externo2.write(j1.getNome() + "\n");
            externo2.write(j2.getNome() + "\n");
            externo2.write(getEstadoJogo() + "\n");

            if ( getVezJogador() ) externo2.write("true\n");
            else externo2.write("false\n");

            tabuleiro.salvaTabuleiro(externo2);

            externo2.close();
            System.out.println("Escrito no arquivo");
        } catch (IOException exce) {
            System.out.println("Exceção ao tentar escrever no arquivo");
            return;
        }
        scan.close();
    }
    private void recuperarJogo() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o nome do arquivo:");
        String nomeArq = scan.next();
        nomeArq = nomeArq.concat(".txt");

        String vetorPecas[] = new String[64];

        try {
            Scanner in = new Scanner(new File(nomeArq));

            this.j1 = new Jogador(in.nextLine(), "Brancas");

            this.j2 = new Jogador(in.nextLine(), "Pretas");
            this.estadoJogo = Integer.parseInt(in.nextLine());  // início=0, jogando=1, xeque=2, xeque-mate=3

            if (in.nextLine().equals("true"))                   // brancas(j1): true // pretas(j2): false
                this.setVezJogador(true);
            else this.setVezJogador(false);

            for (int i=0; i<64; i++)
                vetorPecas[i] = in.nextLine();

            in.close();
        } catch (FileNotFoundException exce){
            System.out.println("Exceção ao tentar ler arquivo");
            return;
        }
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        Peca novasPecas[] = new Peca[64];
        for (int i=0, k=0; i<64; i++) {
            switch (vetorPecas[i]) {
                case "vazia":
                    novasPecas[i] = null;
                    break;
                case "B ":
                    novasPecas[i] = new Bispo("Brancas");
                    j1.recebePeca(novasPecas[i]);
                    pecas[k] = novasPecas[i];
                    k++;
                    break;
                case "C ":
                    novasPecas[i] = new Cavalo("Brancas");
                    j1.recebePeca(novasPecas[i]);
                    pecas[k] = novasPecas[i];
                    k++;
                    break;
                case "D ":
                    novasPecas[i] = new Dama("Brancas");
                    j1.recebePeca(novasPecas[i]);
                    pecas[k] = novasPecas[i];
                    k++;
                    break;
                case "p ":
                    novasPecas[i] = new Peao("Brancas");
                    j1.recebePeca(novasPecas[i]);
                    pecas[k] = novasPecas[i];
                    k++;
                    break;
                case "R ":
                    novasPecas[i] = new Rei("Brancas");
                    j1.recebePeca(novasPecas[i]);
                    pecas[k] = novasPecas[i];
                    k++;
                    break;
                case "T ":
                    novasPecas[i] = new Torre("Brancas");
                    j1.recebePeca(novasPecas[i]);
                    pecas[k] = novasPecas[i];
                    k++;
                    break;
                case "B*":
                    novasPecas[i] = new Bispo("Pretas");
                    j2.recebePeca(novasPecas[i]);
                    pecas[k] = novasPecas[i];
                    k++;
                    break;
                case "C*":
                    novasPecas[i] = new Cavalo("Pretas");
                    j2.recebePeca(novasPecas[i]);
                    pecas[k] = novasPecas[i];
                    k++;
                    break;
                case "D*":
                    novasPecas[i] = new Dama("Pretas");
                    j2.recebePeca(novasPecas[i]);
                    pecas[k] = novasPecas[i];
                    k++;
                    break;
                case "p*":
                    novasPecas[i] = new Peao("Pretas");
                    j2.recebePeca(novasPecas[i]);
                    pecas[k] = novasPecas[i];
                    k++;
                    break;
                case "R*":
                    novasPecas[i] = new Rei("Pretas");
                    j2.recebePeca(novasPecas[i]);
                    pecas[k] = novasPecas[i];
                    k++;
                    break;
                case "T*":
                    novasPecas[i] = new Torre("Pretas");
                    j2.recebePeca(novasPecas[i]);
                    pecas[k] = novasPecas[i];
                    k++;
                    break;
                default:
                    System.out.println("Erro ao atribuir peça de arquivo");
                    break;
            }
        }
        tabuleiro.inicializa(novasPecas);
        tabuleiro.desenhaTabuleiro();
    }
}