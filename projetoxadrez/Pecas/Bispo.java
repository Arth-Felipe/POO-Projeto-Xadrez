//  Nome: Arthur Felipe Bravo Pita
//  RA: 770987
//  Data (entrega): 08.01.2021

package projetoxadrez.Pecas;
import projetoxadrez.Peca;
public class Bispo extends Peca {
    // ------- Contructors, Getters, Setters -------
    public Bispo(String corIni) {
        super(corIni);
    }
    
    // ------- Métodos -------
    @Override
    public String desenho() {
        if (isBranco()) // branco
            return "B "; // " \u2657 "; //bishop
        else // preto
            return "B*"; // " \u2657*";
    }
    @Override
    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        if (getEmJogo()) {
            int variacaoLinha = linhaDestino - linhaOrigem;
            int variacaoColuna = (int) colunaDestino - (int) colunaOrigem;
            
            // Condições pro movimento
            return Math.abs(variacaoLinha) == Math.abs(variacaoColuna);
        }
        return false;
    }
}