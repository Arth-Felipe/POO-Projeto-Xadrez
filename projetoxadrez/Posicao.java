//  Nome: Arthur Felipe Bravo Pita
//  RA: 770987
//  Data (entrega): 08.01.2021

package projetoxadrez;
public class Posicao {
    // ------- Atributos -------
    private int linha;
    private char coluna;
    private boolean branco;     // true = branco; false = preto
    private boolean ocupada;    // true = está ocupada
    private Peca pecaDaCasa = null;
    
    // ------- Contructors, Getters, Setters -------
    public Posicao(int lin, char col, boolean situacao) {
        if (lin>=0 && lin<=7) this.linha = lin;
        else System.out.println("Erro ao atribuir linha");
        
        if ((int)col>=97 && (int)col<=104) this.coluna = col;
        else System.out.println("Erro ao atribuir coluna");

        // de início, algumas posições já são ocupadas por peças
        this.ocupada = situacao;
        
        // se linha e coluna tem mesma paridade, casa branca (true). caso contrário, preta (false)
        this.branco = (lin%2==0 && col%2==0) || (lin%2!=0 && col%2!=0);
    }

    public int getLinha() {
        return linha;
    }
    public char getColuna() {
        return coluna;
    }
    public boolean isBranco() {
        return branco;
    }

    // está ocupada?
    public boolean getOcupada() {
        return ocupada;
    }
    public void setOcupada(boolean ocup) {
        this.ocupada = ocup;
    }

    // qual peça que está ocupando?
    public Peca getPecaDaCasa() {
        return pecaDaCasa;
    }
    public void preencher(Peca nova) {
        this.pecaDaCasa = nova;
    }
}