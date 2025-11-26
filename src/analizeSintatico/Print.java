package src.analizeSintatico;

import utils.VetorDinamico;

public class Print {

    private static final int INCREMENTO_AVANCO = 1;

    public static int iniciarProcessamento(ContextoAnalise contextoAnalise) {
        if (contextoAnalise == null) {
            throw new IllegalArgumentException("Contexto não pode ser null");
        }

        VetorDinamico vetorTokens = contextoAnalise.obterVetorTokens();
        int indiceAtual = contextoAnalise.obterIndiceAtual();

        // print está no indiceAtual, avança
        indiceAtual += INCREMENTO_AVANCO;

        // Próximo token deve ser um valor válido ou identificador
        String tokenAtual = vetorTokens.obterElemento(indiceAtual);
        
        if (!ValidadorToken.ehOperandoValido(tokenAtual) && !tokenAtual.equals(ConstantesTokens.IDENTIFIER)) {
            GerenciadorErros.valorInvalido(indiceAtual);
            throw new IllegalStateException("Valor inválido para print");
        }

        indiceAtual += INCREMENTO_AVANCO;

        // Esperamos END
        if (!vetorTokens.obterElemento(indiceAtual).equals(ConstantesTokens.END)) {
            GerenciadorErros.endFaltando("Print");
            throw new IllegalStateException("END esperado após print");
        }

        indiceAtual += INCREMENTO_AVANCO;

        return indiceAtual;
    }
}
