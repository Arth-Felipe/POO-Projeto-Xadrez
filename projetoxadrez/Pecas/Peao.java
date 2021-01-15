//  Nome: Arthur Felipe Bravo Pita
//  RA: 770987
//  Data (entrega): 08.01.2021

package projetoxadrez.Pecas;
import projetoxadrez.Peca;
public class Peao extends Peca {
    // ------- Contructors, Getters, Setters -------
    public Peao(String corIni) {
        super(corIni);
    }
    
    // ------- Métodos -------
    @Override
    public String desenho() {
        if (isBranco()) // branco
            return "p "; // " \u2659 "; //pawn
        else // preto
            return "p*"; // " \u2659*";
    }
    @Override
    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        if (getEmJogo()) {
            int variacaoColuna = (int) colunaDestino - (int) colunaOrigem;
            
            // Condições pro movimento
            if (isBranco()) { //branco
                if (Math.abs(variacaoColuna)==0)
                    //posição inicial ou não
                    return (linhaOrigem==1) ? (linhaDestino==2 || linhaDestino==3) : ((linhaDestino-linhaOrigem) == 1);
                else return this.podeComer && Math.abs(variacaoColuna)==1 && (linhaDestino-linhaOrigem)==1;

            } else { //preto
                if (Math.abs(variacaoColuna)==0)
                    //posição inicial ou não
                    return (linhaOrigem==6) ? (linhaDestino==5 || linhaDestino==4) : ((linhaOrigem-linhaDestino) == 1);
                else return this.podeComer && Math.abs(variacaoColuna)==1 && (linhaOrigem-linhaDestino)==1;
            }
        }
        return false;
    }
}