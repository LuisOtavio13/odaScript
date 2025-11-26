package src.analizeSintatico;

import src.simbolos.TabelaDeSimbolos;
import utils.VetorDinamico;

public class OKAY {

    public static void main(TabelaDeSimbolos tabelaDeSimbolos, VetorDinamico vetorTokens) {
        for (int indiceAtual = 0; indiceAtual < vetorTokens.obterTamanho(); indiceAtual++) {
            String tokenAtual = vetorTokens.obterElemento(indiceAtual);
            indiceAtual = processarTokenPorTipo(
                    tokenAtual,
                    tabelaDeSimbolos,
                    vetorTokens,
                    indiceAtual);
        }
    }

    private static int processarTokenPorTipo(

            String tipoToken,
            TabelaDeSimbolos tabelaDeSimbolos,
            VetorDinamico vetorTokens,
            int indiceAtual
        ){

        switch (tipoToken) {
            case ConstantesTokens.VAR:
                indiceAtual = processarDeclaracaoVariavel(
                        new ContextoAnalise(tabelaDeSimbolos, indiceAtual, vetorTokens));
                break;

            case ConstantesTokens.EOF:
                break;

            case ConstantesTokens.IDENTIFIER:
                indiceAtual = processarAtribuicao(
                    new ContextoAnalise(tabelaDeSimbolos, indiceAtual, vetorTokens)
                );
                break;

            default:
                GerenciadorErros.tokenDesconhecido(tipoToken, indiceAtual);
                break;
        }

        return indiceAtual;
    }

    private static int processarDeclaracaoVariavel(ContextoAnalise contextoAnalise) {
        try {
            return Var.iniciarProcessamento(contextoAnalise);
        } catch (Exception excecao) {
            GerenciadorErros.excecaoGenerica(excecao);
            return contextoAnalise.obterIndiceAtual();
        }
    }

    private static int processarAtribuicao(ContextoAnalise contextoAnalise) {
        try {
            return Atribuicao.iniciarProcessamento(contextoAnalise);
        } catch (Exception excecao) {
            GerenciadorErros.excecaoGenerica(excecao);
            return contextoAnalise.obterIndiceAtual();
        }
    }
}
