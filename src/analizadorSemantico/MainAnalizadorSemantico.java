package src.analizadorSemantico;

import src.analizeSintatico.ConstantesTokens;
import src.analizeSintatico.GerenciadorErros;
import src.simbolos.TabelaDeSimbolos;
import utils.VetorDinamico;

public class MainAnalizadorSemantico {
    private static VetorDinamico variaveisDeclaradas = new VetorDinamico();
    public static void main(TabelaDeSimbolos tabelaDeSimbolos, VetorDinamico vetorTokens) {
        for (int i = 0; i < vetorTokens.obterTamanho(); i++) {
            processarTokenPorTipo(tabelaDeSimbolos, vetorTokens, i);
        }
    }

    private static int processarTokenPorTipo(
            TabelaDeSimbolos tabelaDeSimbolos,
            VetorDinamico vetorTokens,
            int indiceAtual) {
        String tipoToken = vetorTokens.obterElemento(indiceAtual);
        switch (tipoToken) {
            case ConstantesTokens.VAR:
                String nomeVariavel = tabelaDeSimbolos.obterLexema(indiceAtual + 1);
                variaveisDeclaradas = VariavelAnalizadorSemantico.declararVariavel(variaveisDeclaradas, nomeVariavel);
                break;
            case ConstantesTokens.EOF:
                break;
            case ConstantesTokens.IDENTIFIER:
                VariavelAnalizadorSemantico.verificarDeclaracao(variaveisDeclaradas, tabelaDeSimbolos.obterLexema(indiceAtual));
                break;
            default:
                
                break;
        }
        return indiceAtual;
    }
}
