//  Nome: Arthur Felipe Bravo Pita
//  RA: 770987
//  Data (entrega): 08.01.2021

package projetoxadrez.Pecas;
import projetoxadrez.Peca;
public class Torre extends Peca {
    // ------- Contructors, Getters, Setters -------
    public Torre(String corIni) {
        super(corIni);
    }
    
    // ------- Métodos -------
    @Override
    public String desenho() {
        if (isBranco()) // branco
            return "T "; // " \u2656 "; //rook
        else // preto
            return "T*"; // " \u2656*";
    }
    @Override
    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        if (getEmJogo()) {
            int variacaoLinha = linhaDestino - linhaOrigem;
            int variacaoColuna = (int) colunaDestino - (int) colunaOrigem;
            
            // Condições pro movimento
            if (variacaoLinha==0 && variacaoColuna!=0)
                return true;
            else return variacaoLinha!=0 && variacaoColuna==0;
        }
        return false;
    }
}