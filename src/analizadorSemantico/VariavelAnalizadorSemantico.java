package src.analizadorSemantico;

import utils.VetorDinamico;

public class VariavelAnalizadorSemantico {

    private static final String VARIAVEL_JA_DECLARADA = "Variável %s já declarada";
    private static final String VARIAVEL_NAO_DECLARADA = "Variável %s não declarada";
    private static final int NAO_ENCONTRADO = -1;

    public static VetorDinamico declararVariavel(VetorDinamico vetorDinamico, String nomeVariavel) {
        if (nomeVariavel == null || nomeVariavel.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome de variável não pode ser vazio");
        }

        if (vetorDinamico.buscarElemento(nomeVariavel) != NAO_ENCONTRADO) {
            throw new IllegalStateException(
                String.format(VARIAVEL_JA_DECLARADA, nomeVariavel)
            );
        }

        vetorDinamico.adicionar(nomeVariavel);
        return vetorDinamico;
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
