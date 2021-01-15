//  Nome: Arthur Felipe Bravo Pita
//  RA: 770987
//  Data (entrega): 08.01.2021

package projetoxadrez;
public abstract class Peca {
    // ------- Atributos -------
    private boolean branco;
    private boolean emJogo;
    protected boolean podeComer = false;
    
    // ------- Contructors, Getters, Setters -------
    public Peca(String corIni) {
        if (corIni=="Branco" || corIni=="branco" || corIni=="Branca" || corIni=="branca" || corIni=="Brancas" || corIni=="brancas")
            this.branco = true;
        else
            this.branco = false;
        this.emJogo = true;
    }

    public boolean isBranco() {
        return branco;
    }
    public boolean getEmJogo() {
        return emJogo;
    }
    public void setEmJogo(boolean emJogo) {
        this.emJogo = emJogo;
    }
    public void setPodeComer(boolean permissao) {
        this.podeComer = permissao;
    }

    // ------- Métodos -------
    abstract public String desenho();
    abstract public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino);

}