//  Nome: Arthur Felipe Bravo Pita
//  RA: 770987
//  Data (entrega): 08.01.2021

package projetoxadrez;
import java.io.FileWriter;
import java.io.IOException;
public class Tabuleiro {
    // ------- Constantes das Cores -------
    public static final String ANSI_RESET  = "\u001B[0m";
    public static final String ANSI_BLACK  = "\u001B[41m";

    // ------- Atributos -------
    private Posicao posi[][] = new Posicao[8][8];
    
    // ------- Contructors, Getters, Setters -------
    public Tabuleiro() {
        for (int i=0; i<8; i++)
            for (int j=0; j<8; j++)
                posi[i][j] = new Posicao(i,(char)(j+97),false);
    }

    // ------- Métodos -------

    // organização das primeiras peças, configuração inicial
    public void inicializa(Peca[] elem) {
        int cont=0;
        for (int i=7; i>=0; i--) {
            for (int j=0; j<8; j++) {
                if (elem[cont] != null)
                    posi[i][j].setOcupada(true);
                posi[i][j].preencher(elem[cont]);
                cont++;
    }   }   }

    // manutenção a cada jogada
    public boolean atualiza(boolean corJogadorDaVez, int linOrg, char colOrg, int linDest, char colDest) {
        
        // verifiação se há uma peça na casa
        if ( !posi[linOrg][(int)colOrg-97].getOcupada() ) {
            System.out.println("Casa vazia/sem peças para mover");
            return false;
        }
        
        // verificação de movimento apenas das peças do próprio time
        if (corJogadorDaVez != posi[linOrg][(int)colOrg-97].getPecaDaCasa().isBranco()) {
            System.out.println("Erro ao tentar mover uma peça adversária");
            return false;
        }

        // caso o destino esteja ocupado
        if ( posi[linDest][(int)colDest-97].getOcupada() ) {

            // caso seja uma peça da mesma cor
            if (posi[linDest][(int)colDest-97].getPecaDaCasa().isBranco() == posi[linOrg][(int)colOrg-97].getPecaDaCasa().isBranco()) {
                System.out.println("Casa já ocupada pelo próprio jogador");
                return false;

            // caso seja uma peça adversária, a substitui
            } else {
                boolean retorno;
                // no caso de ser um PEÃO, temos que ativar a possibilidade de captura
                if (posi[linOrg][(int)colOrg-97].getPecaDaCasa().desenho().equals("p ") || posi[linOrg][(int)colOrg-97].getPecaDaCasa().desenho().equals("p*"))
                    posi[linOrg][(int)colOrg-97].getPecaDaCasa().setPodeComer(true);

                if ( this.checaMovimento(posi[linOrg][(int)colOrg-97].getPecaDaCasa(), linOrg, colOrg, linDest, colDest) ) {
                    posi[linDest][(int)colDest-97].getPecaDaCasa().setEmJogo(false);
                    posi[linDest][(int)colDest-97].setOcupada(true);
                    posi[linDest][(int)colDest-97].preencher(posi[linOrg][(int)colOrg-97].getPecaDaCasa());
                    posi[linOrg][(int)colOrg-97].setOcupada(false);
                    posi[linOrg][(int)colOrg-97].preencher(null);
                    retorno = true;
                } else {
                    System.out.println("Jogada não permitida");
                    retorno = false;
                }

                // desativando a possibilidade de captura do PEÃO
                if (posi[linDest][(int)colDest-97].getPecaDaCasa().desenho().equals("p ") || posi[linDest][(int)colDest-97].getPecaDaCasa().desenho().equals("p*"))
                    posi[linDest][(int)colDest-97].getPecaDaCasa().setPodeComer(false);
                return retorno;
            }

        // caso o destino esteja livre
        } else {
            if ( this.checaMovimento(posi[linOrg][(int)colOrg-97].getPecaDaCasa(), linOrg, colOrg, linDest, colDest) ) {
                posi[linDest][(int)colDest-97].setOcupada(true);
                posi[linDest][(int)colDest-97].preencher(posi[linOrg][(int)colOrg-97].getPecaDaCasa());
                posi[linOrg][(int)colOrg-97].setOcupada(false);
                posi[linOrg][(int)colOrg-97].preencher(null);
                return true;
            } else {
                System.out.println("Jogada não permitida");
                return false;
            }
        }
    }

    // checagens de adequação dos movimentos (checaMovimento de cada peça, dentro limites, caminho livre)
    private boolean checaMovimento(Peca elemento, int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        // não mover peça pra fora do tabuleiro, nem para o mesmo lugar + checagens da peça + caminho livre
        return linhaDestino>=0 && linhaDestino<=7 && (int)colunaDestino>=97 && (int)colunaDestino<=104
                && (linhaOrigem!=linhaDestino || colunaOrigem!=colunaDestino)
                && elemento.checaMovimento(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)
                && caminhoLivre(elemento, linhaOrigem, colunaOrigem, linhaDestino, colunaDestino);
    }
    private boolean caminhoLivre(Peca elem, int linOrg, char colOrg, int linDest, char colDest) {
        int varLin = linDest - linOrg;
        int varCol = (int) colDest - (int) colOrg;

        // cavalo dispensa checagem de caminho livre, visto que salta outras peças
        if (elem.desenho().equals("C ") || elem.desenho().equals("C*"))
            return true;

        // movimento VERTICAL
        if (varLin!=0 && varCol==0) {

            // movimento para CIMA
            if (varLin > 0) {
                for (int w=1; linDest > (linOrg+w); w++) {
                    if ( posi[linOrg+w][(int)colOrg-97].getOcupada() )
                        return false;
                }
                return true;

            // movimento para BAIXO
            } else {
                for (int w=1; linDest < (linOrg-w); w++) {
                    if ( posi[linOrg-w][(int)colOrg-97].getOcupada() )
                        return false;
                }
                return true;
            }

        // movimento HORIZONTAL
        } else if (varLin==0 && varCol!=0) {

            // movimento para DIREITA
            if (varCol > 0) {
                for (int w=1; (int)colDest > ((int)colOrg+w); w++) {
                    if ( posi[linOrg][(int)colOrg-97+w].getOcupada() )
                        return false;
                }
                return true;

            // movimento para ESQUERDA
            } else {
                for (int w=1; (int)colDest < ((int)colOrg-w); w++) {
                    if ( posi[linOrg][(int)colOrg-97-w].getOcupada() )
                        return false;
                }
                return true;
            }
            
        // movimento DIAGONAL
        } else if (Math.abs(varLin) == Math.abs(varCol)) {
            
            // movimento para CIMA e para DIREITA
            if ( varLin>0 && varCol>0 ) {
                for (int w=1; linDest > linOrg+w; w++) {
                    if ( posi[linOrg+w][(int)colOrg-97+w].getOcupada() )
                        return false;
                }
                return true;

            // movimento para CIMA e para ESQUERDA
            } else if ( varLin>0 && varCol<0 ) {
                for (int w=1; linDest > linOrg+w; w++) {
                    if ( posi[linOrg+w][(int)colOrg-97-w].getOcupada() ) // linOrg colOrg // linDest colDest
                        return false;
                }
                return true;

            // movimento para BAIXO e para DIREITA
            } else if ( varLin<0 && varCol>0 ) {
                for (int w=1; linDest < (linOrg-w); w++) {
                    if ( posi[linOrg-w][(int)colOrg-97+w].getOcupada() )
                        return false;
                }
                return true;

            // movimento para BAIXO e para ESQUERDA
            } else if ( varLin<0 && varCol<0 ) {
                for (int w=1; linDest < (linOrg-w); w++) {
                    if ( posi[linOrg-w][(int)colOrg-97-w].getOcupada() )
                        return false;
                }
                return true;
            }
        }
        
        System.out.println("Erro ao analisar caminho");
        return false;
    }
    public void desenhaTabuleiro() {
        // Impressão das letras que representam as colunas (cima)
        System.out.print("   " + (char)(97));
        for (int k=1; k<8; k++)
            System.out.print("  " + (char)(k+97));
        System.out.println();

        for (int i=7; i>=0; i--) {
            // Impressão dos números que representam as linhas (esquerda)
            System.out.print((i+1)+ " ");
            
            for (int j=0; j<8; j++) {
                // Imprime 'desenho()' da peça
                if (posi[i][j].getOcupada()) {
                    if (posi[i][j].isBranco())
                        System.out.print(" " + posi[i][j].getPecaDaCasa().desenho() + ANSI_RESET);
                    else
                        System.out.print(ANSI_BLACK + " " + posi[i][j].getPecaDaCasa().desenho() + ANSI_RESET);
                } else {
                    if (posi[i][j].isBranco())
                        System.out.print("   " + ANSI_RESET);
                    else
                        System.out.print(ANSI_BLACK + "   " + ANSI_RESET);
                }
            }
            // Impressão dos números que representam as linhas (direita)
            System.out.println(" " + (i+1));
        }
        
        // Impressão das letras que representam as colunas (baixo)
        System.out.print("   " + (char)(97));
        for (int k=1; k<8; k++)
            System.out.print("  " + (char)(k+97));
        System.out.println();
    }
    public String checaXeque() {
        int result = 0; // 0 = nenhuma em xeque, 1 = nas brancas, 2 = nas pretas, 3 = em ambas

        // varre as posições procurando os respectivos reis
        Posicao reiBranco=null, reiPreto=null;
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                if (posi[i][j].getOcupada() && posi[i][j].getPecaDaCasa().desenho().equals("R "))
                    reiBranco = posi[i][j];
                if (posi[i][j].getOcupada() && posi[i][j].getPecaDaCasa().desenho().equals("R*"))
                    reiPreto = posi[i][j];
        }   }

        if (reiBranco==null) return "preto";
        if (reiPreto==null) return "branco";

        // varre as posições procurando peças da cor oposta que consigam atacar a posição do rei
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                // verificação do xeque nas BRANCAS (peças PRETAS atacando)
                if (posi[i][j].getOcupada() && (posi[i][j].getPecaDaCasa().isBranco() == false)
                    && checaMovimento(posi[i][j].getPecaDaCasa(), i, (char)(j+97), reiBranco.getLinha(), reiBranco.getColuna()))
                    result += 1;

                // verificação do xeque nas PRETAS (peças BRANCAS atacando)
                if (posi[i][j].getOcupada() && (posi[i][j].getPecaDaCasa().isBranco() == true)
                    && checaMovimento(posi[i][j].getPecaDaCasa(), i, (char)(j+97), reiPreto.getLinha(), reiPreto.getColuna()))
                    result += 2;
                
                if (result == 3)
                    return "ambas";
        }   }

        if (result==1)
            return "branco";
        else if (result==2)
            return "preto";
        else // if (result==0)
            return "nenhuma";
    }
    public void salvaTabuleiro(FileWriter externo2) {
        for (int i=7; i>=0; i--) {
            for (int j=0; j<8; j++) {
                try {
                    if (posi[i][j].getOcupada())
                        externo2.write(posi[i][j].getPecaDaCasa().desenho() + "\n");
                    else
                        externo2.write("vazia\n");
                } catch (IOException exce) {
                    System.out.println("Exceção ao tentar escrever PEÇA no arquivo");     
    }   }   }   }
}