//  Nome: Arthur Felipe Bravo Pita
//  RA: 770987
//  Data (entrega): 08.01.2021

package projetoxadrez.Pecas;
import projetoxadrez.Peca;
public class Dama extends Peca {
    // ------- Contructors, Getters, Setters -------
    public Dama(String corIni) {
        super(corIni);
    }
    
    // ------- Métodos -------
    @Override
    public String desenho() {
        if (isBranco()) // branco
            return "D "; // " \u2655 "; //queen
        else // preto
            return "D*"; // " \u2655*";
    }
    @Override
    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        if (getEmJogo()) {
            int variacaoLinha = linhaDestino - linhaOrigem;
            int variacaoColuna = (int) colunaDestino - (int) colunaOrigem;
            
            // Condições pro movimento
            if (variacaoLinha==0 && variacaoColuna!=0)
                return true;
            else if (variacaoLinha!=0 && variacaoColuna==0)
                return true;
            else return Math.abs(variacaoLinha) == Math.abs(variacaoColuna);
        }
        return false;
    }
}