package src.analizeSintatico;

import src.simbolos.TabelaDeSimbolos;
import utils.VetorDinamico;


public class ContextoAnalise {
    
    
    private final TabelaDeSimbolos tabelaDeSimbolos;  
    private final int indiceAtual;                     
    private final VetorDinamico vetorTokens;           

    
    public ContextoAnalise(TabelaDeSimbolos tabelaDeSimbolos, int indiceAtual, VetorDinamico vetorTokens) {
        validarParametros(tabelaDeSimbolos, vetorTokens);
        validarIndice(indiceAtual, vetorTokens);
        
        this.tabelaDeSimbolos = tabelaDeSimbolos;
        this.indiceAtual = indiceAtual;
        this.vetorTokens = vetorTokens;
    }

    private void validarParametros(TabelaDeSimbolos tabela, VetorDinamico vetor) {
        if (tabela == null || vetor == null) {
            throw new IllegalArgumentException("tabela de simbolos e vetor de tokens nao podem ser null");
        }
    }

   
    private void validarIndice(int indice, VetorDinamico vetor) {
        if (indice < 0 || indice >= vetor.obterTamanho()) {
            throw new IndexOutOfBoundsException(
                String.format("indice invalido: %d. intervalo [0, %d)", indice, vetor.obterTamanho())
            );
        }
    }

    
    public TabelaDeSimbolos obterTabelaDeSimbolos() {
        return tabelaDeSimbolos;
    }

    public int obterIndiceAtual() {
        return indiceAtual;
    }

   
    public VetorDinamico obterVetorTokens() {
        return vetorTokens;
    }

    
    @Override
    public String toString() {
        return String.format(
            "contextoAnalise{indice=%d, tokens=%d, simbolos=%d}",
            indiceAtual,
            vetorTokens.obterTamanho(),
            tabelaDeSimbolos.obterQuantidadeSimbolos()
        );
    }
}
