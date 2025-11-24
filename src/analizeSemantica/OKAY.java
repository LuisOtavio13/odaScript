package src.analizeSemantica;

import src.simbolos.TabelaDeSimbolos;
import utils.VetorDinamico;

public class OKAY {

    public static void main(TabelaDeSimbolos tabelaDeSimbolos, VetorDinamico vetorTokens) {

        for (int indiceAtual = 0; indiceAtual < vetorTokens.obterTamanho(); indiceAtual++) {
            String tokenAtual = vetorTokens.obterElemento(indiceAtual);

            GerenciadorErros.debugPosicao("processando token", indiceAtual);

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

                GerenciadorErros.debug("fim do arquivo alcancado");
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
}
