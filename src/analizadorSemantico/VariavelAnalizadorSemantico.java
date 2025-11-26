package src.analizadorSemantico;

import src.ast.AstPattter;
import src.ast.Var;

import utils.VetorDinamico;

public class VariavelAnalizadorSemantico {

    private static final String VARIAVEL_JA_DECLARADA = "Variável %s já declarada";
    private static final String VARIAVEL_NAO_DECLARADA = "Variável %s não declarada";
    private static final int NAO_ENCONTRADO = -1;

    public static  Retorno declararVariavel(VetorDinamico vetorDinamico, String nomeVariavel) {
        if (nomeVariavel == null || nomeVariavel.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome de variável não pode ser vazio");
        }

        if (vetorDinamico.buscarElemento(nomeVariavel) != NAO_ENCONTRADO) {
            throw new IllegalStateException(
                String.format(VARIAVEL_JA_DECLARADA, nomeVariavel)
            );
        }
        vetorDinamico.adicionar(nomeVariavel);
        AstPattter astVar = new Var(nomeVariavel);
        Retorno vetor = new Retorno();
        vetor.setListaRetornoAST(astVar);
        vetor.setListaRetorno(vetorDinamico);
        return vetor;
    }

    public static void verificarDeclaracao(VetorDinamico vetorDinamico, String nomeVariavel) {
        if (nomeVariavel == null || nomeVariavel.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome de variável não pode ser vazio");
        }

        if (vetorDinamico.buscarElemento(nomeVariavel) == NAO_ENCONTRADO) {
            throw new IllegalStateException(
                String.format(VARIAVEL_NAO_DECLARADA, nomeVariavel)
            );
        }
    }
}
