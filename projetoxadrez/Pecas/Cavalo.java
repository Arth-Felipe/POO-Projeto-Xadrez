//  Nome: Arthur Felipe Bravo Pita
//  RA: 770987
//  Data (entrega): 08.01.2021

package projetoxadrez.Pecas;
import projetoxadrez.Peca;
public class Cavalo extends Peca {
    // ------- Contructors, Getters, Setters -------
    public Cavalo(String corIni) {
        super(corIni);
    }
    
    // ------- Métodos -------
    @Override
    public String desenho() {
        if (isBranco()) // branco
            return "C "; // " \u2658 "; //knight
        else // preto
            return "C*"; // " \u2658*";
    }
    @Override
    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        if (getEmJogo()) {
            int variacaoLinha = linhaDestino - linhaOrigem;
            int variacaoColuna = (int) colunaDestino - (int) colunaOrigem;
            
            // Condições pro movimento
            if (Math.abs(variacaoColuna)==2 && Math.abs(variacaoLinha)==1)
                return true;
            else return Math.abs(variacaoLinha)==2 && Math.abs(variacaoColuna)==1;
        }
        return false;
    }
}