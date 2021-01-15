//  Nome: Arthur Felipe Bravo Pita
//  RA: 770987
//  Data (entrega): 08.01.2021

package projetoxadrez;
public class Jogador {
    // ------- Atributos -------
    private String nome;
    private boolean branco;
    private Peca pecasJogador[] = new Peca[16];
    
    // ------- Contructors, Getters, Setters -------
    public Jogador(String nomeJogador, String corIni) {
        this.nome = nomeJogador;
        if (corIni=="Branco" || corIni=="branco" || corIni=="Branca" || corIni=="branca" || corIni=="Brancas" || corIni=="brancas")
            this.branco = true;
        else
            this.branco = false;
    }
    public String getNome() {
        return nome;
    }
    public boolean isBranco() {
        return branco;
    }

    // ------- Métodos -------
    private int i=0;
    public void recebePeca(Peca nova) {
        pecasJogador[i] = nova;
        i++;
    }
    // Quais peças suas ainda estão ativas
    public void pecasAtivas() {
        System.out.print("Peças Ativas: ");
        for (int k=0; k<16; k++)
            if (pecasJogador[k].getEmJogo())
                System.out.print(pecasJogador[k].desenho() + " ");
        System.out.println();
    }
}