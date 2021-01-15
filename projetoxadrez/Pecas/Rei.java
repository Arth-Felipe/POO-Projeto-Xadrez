//  Nome: Arthur Felipe Bravo Pita
//  RA: 770987
//  Data (entrega): 08.01.2021

package projetoxadrez.Pecas;
import projetoxadrez.Peca;
public class Rei extends Peca {
    // ------- Contructors, Getters, Setters -------
    public Rei(String corIni) {
        super(corIni);
    }
    
    // ------- Métodos -------
    @Override
    public String desenho() {
        if (isBranco()) // branco
            return "R "; // " \u2654 "; //king
        else // preto
            return "R*"; // " \u2654*";
    }
    @Override
    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        if (getEmJogo()) {
            int variacaoLinha = linhaDestino - linhaOrigem;
            int variacaoColuna = (int) colunaDestino - (int) colunaOrigem;
            
            // Condições pro movimento
            if (variacaoLinha==0 && Math.abs(variacaoColuna)==1)
                return true;
            else if (Math.abs(variacaoLinha)==1 && variacaoColuna==0)
                return true;
            else return Math.abs(variacaoLinha) == 1 && Math.abs(variacaoColuna) == 1;
        }
        return false;
    }
}